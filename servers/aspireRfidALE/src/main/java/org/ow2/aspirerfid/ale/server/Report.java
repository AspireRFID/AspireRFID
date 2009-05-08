/*
 * Copyright (C) 2008-2010, Aspire 
 *
 * This file contains the source code of the Accada library by ETH Zurich (www.accada.org),
 * licensed under the terms of the GNU Lesser General Public License version 2.1 in 2007
 * and modified for the needs of the Aspire project.
 *
 * Aspire is free software; you can redistribute it and/or 
 * modify it under  the terms of the GNU Lesser General Public 
 * License version 2.1 as published by the Free Software Foundation (the 
 * "LGPL"). 
 *
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this library in the file COPYING-LGPL-2.1; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301 USA. 
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY 
 * OF ANY KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations. 
 */

package org.ow2.aspirerfid.ale.server;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.accada.ale.util.ECReportSetEnum;
import org.accada.ale.util.HexUtil;
import org.accada.ale.wsdl.ale.epcglobal.ECSpecValidationException;
import org.accada.ale.wsdl.ale.epcglobal.ECSpecValidationExceptionResponse;
import org.accada.ale.wsdl.ale.epcglobal.ImplementationException;
import org.accada.ale.wsdl.ale.epcglobal.ImplementationExceptionResponse;
import org.accada.ale.xsd.ale.epcglobal.ECFilterSpec;
import org.accada.ale.xsd.ale.epcglobal.ECReport;
import org.accada.ale.xsd.ale.epcglobal.ECReportGroup;
import org.accada.ale.xsd.ale.epcglobal.ECReportGroupCount;
import org.accada.ale.xsd.ale.epcglobal.ECReportGroupList;
import org.accada.ale.xsd.ale.epcglobal.ECReportGroupListMember;
import org.accada.ale.xsd.ale.epcglobal.ECReportSpec;
import org.accada.ale.xsd.epcglobal.EPC;
import org.ow2.aspirerfid.ale.server.Tag;
import org.accada.tdt.types.LevelTypeList;

/**
 * This class represents a report.
 * It filters and groups tags, add them to the report and build ec reports.
 * 
 * @author regli
 * @author sawielan
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class Report {

	/** logger. */
	private static final Logger LOG = Logger.getLogger(Report.class);
	
	/** name of this report. */
	private final String name;
	/** current event cycle delivers tags. */
	private final EventCycle currentEventCycle;

	/** patterns of tags which are included in this report. */
	private final Set<Pattern> includePatterns = new HashSet<Pattern>();
	/** patterns of tags which are excluded from this report. */
	private final Set<Pattern> excludePatterns = new HashSet<Pattern>();
	/** patterns to group the tags of this report. */
	private final Set<Pattern> groupPatterns = new HashSet<Pattern>();
	
	/** Map of tags of the current event cycle for proccesing .(Added By nkef@ait.edu.gr) */
	private Map<String, Tag> currentEventCycleTags = new HashMap<String, Tag>();
	
	/** Map of tags of the last event cycle for proccesing .(Added By nkef@ait.edu.gr) */
	private Map<String, Tag> lastEventCycleTags = new HashMap<String, Tag>();
	
	
	
	/** Map of tags of the last 100 event cycles for proccesing .(Added By nkef@ait.edu.gr) */
	private Map<String, Tag> lastADDITIONEventCycleTags = new HashMap<String, Tag>();
	/** Map of tags of the last 100 event cycles for proccesing .(Added By nkef@ait.edu.gr) */
	private Map<String, Tag> lastDELETIONEventCycleTags = new HashMap<String, Tag>();
	
	//By nkef@ait.edu.gr tool to create rawHex and rawDecimal
	private EpcUriFactory epcUriFactory = new EpcUriFactory();
	
	/** type of this report (current, additions or deletions). */
	private String reportType;

	/** ec report. */
	private ECReport report;
	/** ec report specification. */
	private ECReportSpec reportSpec;
	
	/**
	 * This boolean indicates if the report is ready to create ec reports.
	 * In some cases the last event cycle must be terminated before a new report can be generated.
	 */
	private boolean readyToCreateReport;
	
	/**
	 * Constructor set parameters, read specifiaction and initializes patterns.
	 * 
	 * @param reportSpec defines how the report should be generated
	 * @param currentEventCycle this report belongs to
	 * @throws ImplementationException if an implementation exception occurs
	 */
	public Report(ECReportSpec reportSpec, EventCycle currentEventCycle) throws ImplementationExceptionResponse {
		
		// set name
		name = reportSpec.getReportName();
		
		LOG.debug("Create report '" + name + "'");
		
		// create ECReport
		report = new ECReport();
		
		// set ECReport name
		report.setReportName(name);
		
		// set type
		reportType = reportSpec.getReportSet().getSet();

		// set ECReportSpec
		this.reportSpec = reportSpec;
		
		// set currentEventCycle
		this.currentEventCycle = currentEventCycle;
				
		// init patterns
		initFilterPatterns();
		initGroupPatterns();
		readyToCreateReport = true;

	}

	/**
	 * This method adds a tag to the report.
	 * 
	 * @param tag to add
	 * @throws ECSpecValidationException if the tag is invalid
	 * @throws ImplementationException if an implementation exception occurs
	 */
	public void addTag(Tag tag) throws ECSpecValidationExceptionResponse, ImplementationExceptionResponse {

		// get tag URI
		String tagPureURI = tag.getTagIDAsPureURI();
		String tagURI = epcUriFactory.getTagIDAsTagURI(tag.getTagID());
	
		// check if the tag is a member of this report (use filter patterns and set spec)
		if (isMember(tagPureURI, tagURI)) {
	
				LOG.debug("Event '" + tag + "' is member of report '" + name + "'");
			
				// add tag to report
				addTagToReportGroup(tag);
		}
	}
	
	/**
	 * this method is for compatibility reasons such that eg ReportTest is not broken.
	 * @param tag to add
	 * @throws ECSpecValidationException if the tag is invalid
	 * @throws ImplementationException if an implementation exception occurs
	 */
	public void addTag(org.accada.reader.rprm.core.msg.notification.TagType tag) throws ECSpecValidationExceptionResponse, ImplementationExceptionResponse {
		Tag newtag = new Tag();
		newtag.setTagID(tag.getTagID());
		newtag.setTagIDAsPureURI(tag.getTagIDAsPureURI());
		addTag(newtag);
	}
	
	
	/**
	 * added by nkef@ait.edu.gr
	 * helper method to display tags that were added or deleted.
	 * @param reportTags a map holding the tags that were either added or deleted.
	 */
	private void writeDebugInformation(Set<String> reportedTags) {
		String out = '\n' + "+++++++++++++++++++++++++++++++++++++++++++++++++++++" + '\n';
		out +=  '\t' + "eventcycle " + currentEventCycle.getName() + '\n';
		out +=  '\t' + "round " + currentEventCycle.getRounds() + '\n';
		if (reportedTags.isEmpty()) {
			out += '\t' + "no tags" + '\n';
			out +=  "+++++++++++++++++++++++++++++++++++++++++++++++++++++" + '\n';
			LOG.info(out);
			return;
		}
		
		
		for (String pureURI : reportedTags) {
			out += '\t' + pureURI + '\n';
		}
		out +=  "+++++++++++++++++++++++++++++++++++++++++++++++++++++" + '\n';
		LOG.info(out);
	}
	
	/**
	 * This method returns the new ec report.
	 * 
	 * @return ec report
	 * @throws ECSpecValidationException if a tag is invalid
	 * @throws ImplementationException if an implementation exception occurs
	 */
	public ECReport getECReport() throws ECSpecValidationExceptionResponse, ImplementationExceptionResponse {
		//Added by nkef@ait.edu.gr used to replace the use of the original tag Set
		//(currentEventCycle.getTags()) and (currentEventCycle.getLastEventCycleTags())so as not to alter it and give wrong resalts
		//when ADDIIONS andDELETIONS are used in the same ECSpec
		
		currentEventCycleTags.clear();
		//currentEventCycleTags.addAll(currentEventCycle.getTags());
		for (Tag tag : currentEventCycle.getTags()) {
			currentEventCycleTags.put(tag.getTagIDAsPureURI(), tag);
		}	
		
		lastEventCycleTags.clear();
		//lastEventCycleTags.addAll(currentEventCycle.getLastEventCycleTags());
		for (Tag tag : currentEventCycle.getLastEventCycleTags()) {
			lastEventCycleTags.put(tag.getTagIDAsPureURI(), tag);
		}	
		
		//Every 10 cycles the last readed tags will be resseted
		//for the ADDITIONS and DELETIONS function
		if((currentEventCycle.getRounds()%9000) ==0){
			lastADDITIONEventCycleTags.clear();
			lastDELETIONEventCycleTags.clear();
		}


		//generate new ECReport
		if (reportType.equalsIgnoreCase(ECReportSetEnum.ADDITIONS)) {
			
			Set<String> tempCurrentEventCycleTags = new HashSet<String>();
			tempCurrentEventCycleTags.addAll(currentEventCycleTags.keySet());
			
			
			//added by nkef@ait.edu.gr remove all the last reported tags
			//and then add the new ones for the next report
			tempCurrentEventCycleTags.removeAll(lastADDITIONEventCycleTags.keySet());
			
			
			for (String pureURI : tempCurrentEventCycleTags) {
				lastADDITIONEventCycleTags.put(pureURI, currentEventCycleTags.get(pureURI));
			}
				
			// add tags to report with filtering
			readyToCreateReport = true;
			
			for (String pureURI : tempCurrentEventCycleTags) {
				addTag(currentEventCycleTags.get(pureURI));
			}
			
			
			writeDebugInformation(tempCurrentEventCycleTags);
	
		} else if (reportType.equalsIgnoreCase(ECReportSetEnum.CURRENT)) {

			// get tags from current EventCycle 
			for (Tag tag : currentEventCycle.getTags()) {
				addTag(tag);
			}

		//} else if (reportType == ECReportSetEnum.DELETIONS) {
		} else if (reportType.equalsIgnoreCase(ECReportSetEnum.DELETIONS)) {
			
			// get removed tags
			Set<String> tempLastEventCycleTags = new HashSet<String>();
			tempLastEventCycleTags.addAll(lastEventCycleTags.keySet());
			
			tempLastEventCycleTags.removeAll(currentEventCycleTags.keySet());
			
			tempLastEventCycleTags.removeAll(lastDELETIONEventCycleTags.keySet());
			
			for (String pureURI : tempLastEventCycleTags) {
				lastDELETIONEventCycleTags.put(pureURI, lastEventCycleTags.get(pureURI));
			}
			
			
				
			// add tags to report with filtering
			for (String pureURI : tempLastEventCycleTags) {
				addTag(lastEventCycleTags.get(pureURI));
			}
			writeDebugInformation(tempLastEventCycleTags);
		}

		if (reportSpec.isReportIfEmpty() || !isEmpty()) {
			ECReport temp = report;	
			report = new ECReport();
			report.setReportName(name);	
			return temp;
		} else {
			report = new ECReport();
			report.setReportName(name);
			return null;
		}
	}

	//
	// private methods
	//
	
	/**
	 * This method initializes the filter patterns on the basis of the ec report specification.
	 */
	private void initFilterPatterns() {
	
		LOG.debug("Init filter patterns");
		
		// get filter spec
		ECFilterSpec filterSpec = reportSpec.getFilterSpec();
		if (filterSpec != null) {
			
			// add ECIncludePatterns from spec to includePatterns set
			List<String> ecIncludePatterns = filterSpec.getIncludePatterns().getIncludePattern();
			if (ecIncludePatterns != null) {
				for (String pattern : ecIncludePatterns) {
					try {
						includePatterns.add(new Pattern(pattern, PatternUsage.FILTER));
					} catch (ECSpecValidationExceptionResponse e) {
						e.printStackTrace();
					}
				}
			}
			
			// add ECExcludePatterns from spec to excludePatterns set
			List<String> ecExcludePatterns = filterSpec.getExcludePatterns().getExcludePattern();
			if (ecExcludePatterns != null) {
				for (String pattern : ecExcludePatterns) {
					try {
						excludePatterns.add(new Pattern(pattern, PatternUsage.FILTER));
					} catch (ECSpecValidationExceptionResponse e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	/**
	 * This method initializes the group patterns on the basis of the ec report specification.
	 */
	private void initGroupPatterns() {
		
		LOG.debug("Init group patterns");
		if (reportSpec.getGroupSpec() != null) {
			// get group spec
			List<String> groupSpec = reportSpec.getGroupSpec().getPattern();
			// add ECGroupPatterns from spec to groupPatterns set
			for (String pattern : groupSpec) {
				try {
					groupPatterns.add(new Pattern(pattern, PatternUsage.GROUP));
				} catch (ECSpecValidationExceptionResponse e) {
					e.printStackTrace();
				}	
			}
		}		
	}
	
	/**
	 * This method checks on the basis of the filter patterns if the specified tag could be a member of this report.
	 * 
	 * @param tagURI to check for possible membership
	 * @return true if the tag could be a member of this report and false otherwise
	 * @throws ECSpecValidationException if the tag is invalid
	 * @throws ImplementationException if an implementation exception occurs
	 */
	private boolean isMember(String tagPureURI, String tagURI) throws ECSpecValidationExceptionResponse, ImplementationExceptionResponse {		

		// check if tagURI is member of an exclude pattern
		for (Pattern pattern : excludePatterns) {
			if (pattern.isMember(tagPureURI, tagURI)) {
				return false;
			}
		}
		
		// check if there are include patterns specified
		if (includePatterns.size() == 0) {
			return true;
		} else {
			
			// check if tagURI is a member of an include pattern
			for (Pattern pattern : includePatterns) {
				if (pattern.isMember(tagPureURI, tagURI)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * This method adds a tag to the matching group of the report.
	 * 
	 * @param tag to add
	 * @throws ECSpecValidationException if the tag is invalid
	 * @throws ImplementationException if an implementation exception occurs
	 */
	private void addTagToReportGroup(Tag tag) throws ImplementationExceptionResponse, ECSpecValidationExceptionResponse {
		
		// get tag URI
		String tagPureURI = tag.getTagIDAsPureURI();
		//added by nkef@ait.edu.gr
		String tagURI = epcUriFactory.getTagIDAsTagURI(tag.getTagID());
		
		// get group name (use group patterns)
		String groupName = getGroupName(tagPureURI,tagURI);
		
		LOG.debug("The group name for tag '" + tagPureURI + "' is '" + groupName + "'");
		
		// get matching group
		ECReportGroup matchingGroup = null;
		List<ECReportGroup> groups = report.getGroup();
		//if (groups == null) {
		if (groups.isEmpty()) {
			matchingGroup = null;
		} else {
			for (ECReportGroup group : groups) {
				if (groupName == null) {
					if (group.getGroupName() == null) {
						matchingGroup = group;
					}
				} else {
					if (groupName.equals(group.getGroupName())) {
						matchingGroup = group;
					}
				}
			}
		}
	
		// create group if group does not already exist
		if (matchingGroup == null) {
			
			LOG.debug("Group '" + groupName + "' does not already exist, create it");
						
			// create group
			matchingGroup = new ECReportGroup();
			
			// set name
			matchingGroup.setGroupName(groupName);
			
			// set count
			if (reportSpec.getOutput().isIncludeCount()) {
				ECReportGroupCount groupCount = new ECReportGroupCount();
				groupCount.setCount(0);
				matchingGroup.setGroupCount(groupCount);
			}
			
			// create and set group list
			matchingGroup.setGroupList(new ECReportGroupList());
			
			// add to groups
			report.getGroup().add(matchingGroup);
			
		}
		
		// create group list member
		ECReportGroupListMember groupMember = new ECReportGroupListMember();
		
		//ADDED By nkef@ait.edu.gr
		if (reportSpec.getOutput().isIncludeEPC()) {
			EPC epc = new EPC();
			epc.setValue(tag.getTagIDAsPureURI());
			groupMember.setEpc(epc);
		}
		
		
		
		if (reportSpec.getOutput().isIncludeRawDecimal()) {
			EPC epc = new EPC();
			
			//changed by nkef@ait.edu.gr
			epc.setValue(epcUriFactory.rawDecimal(tag.getTagID()));
			groupMember.setRawDecimal(epc);
		}
		if (reportSpec.getOutput().isIncludeTag()) {
			EPC epc = new EPC();
			
			//changed by nkef@ait.edu.gr
			epc.setValue(tagURI);
			groupMember.setTag(epc);
		}
		if (reportSpec.getOutput().isIncludeRawHex()) {
			if (tag.getTagID() != null) {
				EPC epc = new EPC();
				
				//changed by nkef@ait.edu.gr
				epc.setValue(epcUriFactory.rawHex(tag.getTagID()));
				groupMember.setRawHex(epc);
			}
		}
		
		// add list member to group list
		List<ECReportGroupListMember> members = matchingGroup.getGroupList().getMember();		
		members.add(groupMember);
		
		// increment group counter
		if (reportSpec.getOutput().isIncludeCount()) {
			matchingGroup.getGroupCount().setCount(matchingGroup.getGroupCount().getCount() + 1);
		}
		
		LOG.debug("Tag '" + tagPureURI + "' successfully added to group '" + groupName + "' of report '" + name + "'");
		
	}

	/**
	 * This method get the matching group of this report for the specified tag.
	 * 
	 * @param tagURI to search group for
	 * @return group name
	 * @throws ECSpecValidationException if the tag is invalid
	 * @throws ImplementationException if an implementation exception occurs
	 */
	//changed by nkef@ait.edu.gr
	private String getGroupName(String tagPureURI, String tagURI) throws ImplementationExceptionResponse, ECSpecValidationExceptionResponse {
		
		for (Pattern pattern : groupPatterns) {
			if (pattern.isMember(tagPureURI, tagURI)) {
				return pattern.getGroupName(tagPureURI,tagURI);
			}
		}
			
		return null;
		
	}
	
	/**
	 * This method indicates if the report contains any tags.
	 * 
	 * @return true if the report is empty and false otherwise
	 */
	private boolean isEmpty() {
		
		List<ECReportGroup> groups = report.getGroup();
		if (groups != null) {
			for (ECReportGroup group : groups) {
				ECReportGroupList groupList = group.getGroupList();
				if (groupList.getMember().size() > 0) {
					return false;
				}
			}
		}
		return true;

	}
}