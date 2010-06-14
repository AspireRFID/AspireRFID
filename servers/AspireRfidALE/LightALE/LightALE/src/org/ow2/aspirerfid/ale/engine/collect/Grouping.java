/**
 * Copyright (c) 2008-2010, AspireRFID
 *
 * This library is free software; you can redistribute it and/or
 * modify it either under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation
 * (the "LGPL"). If you do not alter this
 * notice, a recipient may use your version of this file under the LGPL.
 *
 * You should have received a copy of the LGPL along with this library
 * in the file COPYING-LGPL-2.1; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY
 * OF ANY KIND, either express or implied. See the LGPL  for
 * the specific language governing rights and limitations.
 *
 * Contact: AspireRFID mailto:aspirerfid@ow2.org
 */

package org.ow2.aspirerfid.ale.engine.collect;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import org.ow2.aspirerfid.ale.codec.EpcCodec;
import org.ow2.aspirerfid.ale.engine.collect.grouping.Epc;
import org.ow2.aspirerfid.ale.engine.collect.grouping.GroupOperator;
import org.ow2.aspirerfid.ale.engine.collect.grouping.Groups;
import org.ow2.aspirerfid.ale.engine.collect.grouping.UriRepresentation;
import org.ow2.aspirerfid.ale.epc.reports.DecathStat;
import org.ow2.aspirerfid.ale.epc.reports.ECReport;
import org.ow2.aspirerfid.ale.epc.reports.ECReportGroup;
import org.ow2.aspirerfid.ale.epc.reports.ECReportGroupCount;
import org.ow2.aspirerfid.ale.epc.reports.ECReportGroupList;
import org.ow2.aspirerfid.ale.epc.reports.ECReportGroupListMember;
import org.ow2.aspirerfid.ale.epc.spec.ECReportOutputSpec;
import org.ow2.aspirerfid.ale.epc.spec.ECReportSpec;
import org.ow2.aspirerfid.ale.epc.spec.ECSpec;


/**
 * Aggregation engine.
 * Wrapper Class of the {@link Groups} class with a pool of reusable EPC objects.
 * Plus an interface for grouping incoming tags as arrays of bytes, by wrapping 
 * sgtin96 and sgln96 codecs.
 * 
 * @author rdagher
 *
 */
public class Grouping {
	/**
	 * Operator for grouping tags.
	 */
	private GroupOperator groupOperator;
	
	/**
	 * Pool of received sgtin96 and sgtln96 tags.
	 */
	private MemberPool aMemberPool;
	
	/**
	 * Aggregated groups hash table.
	 * key: groupName, value: array of Members belonging to group.
	 */
	private Hashtable groups ;
	
	/**
	 * Maximum number of aggregated tags.
	 */
	private int maxTags;
	
	/**
	 * Flag indicating to beep on detection of a tag.
	 */
	protected boolean beepOnDetection;
	
	/**
	 * Flag indicating to on detection of a new tag.
	 */
	protected boolean beepOnNewDetection;
	
	/**
	 * 
	 * Constructor
	 */
	public Grouping() {
		String prop;

		/* Retrieve Configuration */
		// beeps
		prop = System.getProperty("icom.ale.beepOnDetection");
		beepOnDetection = (prop == null) ? false : prop.equalsIgnoreCase("true");
		prop = System.getProperty("icom.ale.beepOnNewDetection");
		beepOnNewDetection = (prop == null) ? false : prop.equalsIgnoreCase("true");
		// maximum tags
		prop = System.getProperty("icom.ale.maxTags");
		maxTags = (prop == null) ? 2000 : Integer.parseInt(prop);
		
		/* Init Members */
		groupOperator = new GroupOperator();
		
		aMemberPool = new MemberPool(maxTags);
		
		groups = new Hashtable();
		groups.put("default", new ArrayList());
	}
	
	/**
	 * Adds a list grouping pattern to the group list.
	 * @param patternList
	 * @param Uri representation of the patterns.
	 * @see #addGroupPattern(String)
	 */
	public void addGroupPattern(String[] patternList, UriRepresentation uriRepresentation) {
		this.groupOperator.setUriRepresentation(uriRepresentation);
		
		for (int i = 0 ; i< patternList.length; i++)
			addGroupPattern(patternList[i]);	
	}
	
	/**
	 * Adds a grouping pattern to the group list.
	 * 
	 * @param gPatternUri The group pattern String to add.
	 * @throws IllegalArgumentException if the given patternGroup is disjoint with the pattern group list
	 */
	private void addGroupPattern (String gPatternUri) {
		groupOperator.addPattern(gPatternUri);
	}
	/**
	 * Aggregate a received tag.
	 * @param header tag type.
	 * @param tagData received tag data : identity epc.
	 * @param timeStamp reception time
	 * @param groupingWrapper a grouping wrapper.
	 * @return true if Threshold of maximum aggregated tags is reached, false otherwise.
	 */
	public boolean aggregate(byte header, byte[] tagData, long timeStamp, Epc groupingWrapper) {
		boolean maxReached;
		
		switch (header) {
			// sgtin-96 or sgln-96 aggregation
			case EpcCodec.SGTIN_96_HEADER: 
			case EpcCodec.SGLN_96_HEADER:
				// Aggregate for epc update Pool grouping and statistics
				maxReached = updateMember(false, this.aMemberPool, tagData, timeStamp, groupingWrapper);
				break;
			
			// Unsupported Aggregation
			default : 
				maxReached = updateMember(true, this.aMemberPool, tagData, timeStamp, groupingWrapper);
		}
		
		return maxReached;
	}
	
	/**
	 * Update grouping and statistics of a received tag.
	 * @param currentPool
	 * @param tagData
	 * @param timeStamp
	 * @param groupingWrapper
	 * @param forceDefault if true, the tag will be added to default member
	 * @return true if Threshold of maximum aggregated tags is reached, false otherwise.
	 */
	private boolean updateMember(boolean forceDefault, MemberPool currentPool, byte[] tagData, long timeStamp, Epc groupingWrapper) {
		int idx;
		String tagGroup = "default"; 	// group of the current tag
		ArrayList memberList;			// current group member list
		Member currentMember;			// current member
		boolean maxReached = false;
		
		// update grouping and statistics
		idx = currentPool.contains(tagData) ;
		if (idx == -1) {			
			// update pool
			if (currentPool.current >= this.maxTags) {
				// Threshold of maximum aggregated tags is reached.
				maxReached = true;
			}
			else {
				// new tag received
				// get a new one from the pool
				currentMember = currentPool.getMember();
				// set it up :
				  // tags
				currentMember.tag = tagData;
				  // stats
				currentMember.firstSightingTime = timeStamp;
				currentMember.lastSightingTime = timeStamp;
				currentMember.numReadings = 1;
				 // Build group and add it to hashTable
				if (!forceDefault)
					tagGroup = groupOperator.group(groupingWrapper);
				// create entry in table if necessary
				if (!this.groups.containsKey(tagGroup)) {
					this.groups.put(tagGroup, new ArrayList());
				}
				memberList = (ArrayList) this.groups.get(tagGroup);
				memberList.add(currentMember);
			}
			//beep
			if(this.beepOnDetection || this.beepOnNewDetection) beep();
		}else {			
			// already existent element : update it
			currentMember = currentPool.pool[idx];
			currentMember.lastSightingTime = timeStamp;
			currentMember.numReadings++;
			//beep
			if(this.beepOnDetection) beep();
		}
		
		return maxReached;
	}
	
	/**
	 * Generate the final report.
	 * @param spec the spec object ((with restricstion)) to include fields accordingly.
	 * @return the report for the given spec.
	 */
	public ECReport buildReporGroups(ECSpec spec) {
		ECReport report;
		
		/* Build report */
		report = buildReporGroups(spec, this.groups);
		
		/* finalize */
		cleanUp();
		
		return report;
	}
		
	/**
	 * Clean up procedure for recycling aggregation object.
	 */
	public void cleanUp() {
		this.groupOperator.cleanUp();
		this.aMemberPool.reset();
	}

	// Factory Methods //
	
	private static void beep() {
		Toolkit.getDefaultToolkit().beep();
	}
	
	private static ECReport buildReporGroups(ECSpec spec, Hashtable groups) {
		ECReportSpec reportSpec;
		ECReportOutputSpec reportOutputspec;
		ECReport report ;
		ECReportGroup [] reportGroups;
		ECReportGroupList groupList;
		int groupNumber = groups.size(); // number of aggregated groups
		
		/* Report Spec :
		 *  Demonstrator : Only one report is handled : spec -> report
		 */
		reportSpec = spec.getReportSpecs()[0];
		reportOutputspec = reportSpec.getOutput();
		
		/* build report 
		 * Demonstrator : Only one report is handled : spec -> report
		 */
		//report = newReport(1);
		report = new ECReport();
		// report Groups : 
		reportGroups = new ECReportGroup[groupNumber];
								
		/* Fill report */		
		// Report Name
		report.setReportName(new String(reportSpec.getReportName()));
				
		/* Group Members */		
		ArrayList memberList;
		ECReportGroupListMember[] aGroupListMember;
		int idx = 0;
		// for each group
		for (java.util.Enumeration keys = groups.keys(); keys.hasMoreElements(); idx++) {
			String currentGroup = (String)keys.nextElement();
			memberList = (ArrayList) groups.get(currentGroup);
			int numTags = memberList.size() ; // number of tags in group
			
			reportGroups[idx] = new ECReportGroup();
			aGroupListMember = new ECReportGroupListMember[numTags];
			// group List
			groupList = new ECReportGroupList();
				
			// Build ECReportGroupListMember
			// for each tag belonging to group
			for (int i = 0 ; i< numTags; i++) {
				// build member
				aGroupListMember[i] = buildGroupListMember((Member) memberList.get(i), reportOutputspec);	
			} // end for each tag belonging to group
			
			// set group count
			if (reportOutputspec.isIncludeCount())
				reportGroups[idx].setGroupCount(new ECReportGroupCount(numTags));

			// set Group members
			groupList.setMembers(aGroupListMember);
			reportGroups[idx].setGroupList(groupList);
			
			// set group Name
			reportGroups[idx].setGroupName(currentGroup);
		}// end for each group		
		
		// set report groups
		report.setGroups(reportGroups); // by reference
		
		return report;
	}
		
	private static ECReportGroupListMember buildGroupListMember(Member aMember, ECReportOutputSpec reportOutputspec) {
		ECReportGroupListMember groupListMember = new ECReportGroupListMember();
		DecathStat tagStat = new DecathStat();
		
		// set member id
		groupListMember.setTagId(aMember.tag);
		
		// set member's tag representation : decoded by server on arrival
		groupListMember.setEpc(null);
		groupListMember.setTag(null);
		groupListMember.setRawDecimal(null);
		groupListMember.setRawHex(null);
		
		//tagStat.setFirstSightingTime(getDate(aMember.firstSightingTime));
		//tagStat.setLastSightingTime(getDate(aMember.lastSightingTime));
		tagStat.setFirstSightingTime(Long.toString(aMember.firstSightingTime));
		tagStat.setLastSightingTime(Long.toString(aMember.lastSightingTime));
		tagStat.setNumReadings(aMember.numReadings);
		tagStat.setStatBlocks(null); // not supported
		groupListMember.setStats(tagStat);	
		
		return groupListMember;
	}
	
//	private static Date aDate = new Date();
//	private synchronized static String getDate(long timestamp) {
//		aDate.setTime(timestamp);
//		return ECReports.ISO_8601_SDF.format(aDate);
//	}
	
	/*
	 * Wrapper for sgtin96 tag. Used for : <br>
	 *   - aggregation : {@link Grouping#aggregate(byte[], long)} <br>
	 *   - reporting   : {@link Grouping#buildGroupListMember(Member, ECReportOutputSpec)}:
	 *
	private final static Sgtin96 sgtin96 = new Sgtin96();
	
	/**
	 * Wrapper for sgln96 tag. Used for : <br>
	 *   - aggregation : {@link Grouping#aggregate(byte[], long)} <br>
	 *   - reporting   : {@link Grouping#buildGroupListMember(Member, ECReportOutputSpec)}:
	 *
	private final static Sgln96 sgln96 = new Sgln96();
	
	/**
	 * builds a {@link ECReportGroupListMember} object according to a given spec.
	 * 
	 * @param aMember Tag member containing stats and Epc wrapper.
	 * @param reportOutputspec spec to format output.
	 * @return {@link ECReportGroupListMember}
	 *
	private static ECReportGroupListMember buildGroupListMember(Member aMember, ECReportOutputSpec reportOutputspec) {
		ECReportGroupListMember groupListMember = null;
		DecathStat tagStat;
		Epc epc;
		
		// wrap and decode tag data
		byte header = EpcCodec.decodeHeader(aMember.tag);
		switch (header) {
			case EpcCodec.SGTIN_96_HEADER: 
				epc = sgtin96 ;
				break;
			case EpcCodec.SGLN_96_HEADER: 
				epc = sgln96 ;
				break;
			default : 
				epc = null;
				
		}
		
		// set member
		if (epc != null) {
			groupListMember = new ECReportGroupListMember();
			tagStat = new DecathStat();
			
			// wrap and decode
			epc.setTagData(aMember.tag);
			
			// set member's tag representation
			if (reportOutputspec.isIncludeEPC())
				groupListMember.setEpc(epc.getPureUri());
			if (reportOutputspec.isIncludeTag())
				groupListMember.setTag(epc.getTagUri());
			if (reportOutputspec.isIncludeRawDecimal())
				groupListMember.setRawDecimal(epc.getRawDecUri());
			if (reportOutputspec.isIncludeRawHex())
				groupListMember.setRawHex(epc.getRawHexUri());
			
			// set member's tag stats
			// tagStat.setFirstSightingTime(new Date(aMember.firstSightingTime).toString());
			// tagStat.setLastSightingTime(new Date(aMember.lastSightingTime).toString());
			tagStat.setFirstSightingTime(Long.toString(aMember.firstSightingTime));
			tagStat.setLastSightingTime(Long.toString(aMember.lastSightingTime));
			tagStat.setNumReadings(aMember.numReadings);
			tagStat.setStatBlocks(null); // not supported
			groupListMember.setStats(tagStat);	
		
		}
		return groupListMember;
	}
	
	*/
}

/**
 * Pool containing Tag members for aggregation (grouping and statistics).
 * @author rdagher
 *
 */
class MemberPool {
	/**
	 * Current free index
	 */
	protected int current;
	
	/**
	 * Pool of reusable Group Member elements
	 */
	protected Member [] pool;

	/**
	 * 
	 * Constructor
	 * @param type {@link EpcCodec#SGTIN_96_HEADER SGTIN_96_HEADER} or {@link EpcCodec#SGLN_96_HEADER SGLN_96_HEADER}, 
	 * @param maxTags
	 */
	public MemberPool(int maxTags) {
		
		this.pool = new Member[maxTags];
		
		for (int i=0; i< maxTags; i++) {
			pool[i] = new Member();
		}
		
		this.current = 0;
	}
	
	 /**
	  * get a free member from the pool
	  * @return ECReportGroupListMember
	  */
	public Member getMember() {
		return pool[current++];
	}
	
	/**
	 * Tests if a report group member is present in pool.
	 * @param gtin
	 * @return the index of the member in the pool if present, -1 otherwise.
	 */	
	public int contains (byte[] tag) {
		int idx = -1;
		
		for (int i = 0; i< current; i++) {
			if (Arrays.equals(tag, pool[i].tag)) {
				idx = i;
				break;
			}
		}
		return idx;
	}
	
		
	/**
	 * Invalidates all pool members.
	 */
	public void reset() {		
		this.current = 0 ;
	}
	
}

/**
 * Pool Member definition.
 * @author rdagher
 *
 */
class Member {
	/**
	 * tag ID reference
	 */
	byte[] tag;
	
	
	//******* Decathlon Statistics *******/
	/**
	 * First Time the tag was captured in event cycle.
	 */
	long firstSightingTime;
	
	/**
	 * Last Time the tag was captured in event cycle.
	 */
	long lastSightingTime;
	
	/**
	 *  Number of sightings of the tag in [<code>firstSightingTime, lastSightingTime</code>] 
	 */
	long numReadings;
}
