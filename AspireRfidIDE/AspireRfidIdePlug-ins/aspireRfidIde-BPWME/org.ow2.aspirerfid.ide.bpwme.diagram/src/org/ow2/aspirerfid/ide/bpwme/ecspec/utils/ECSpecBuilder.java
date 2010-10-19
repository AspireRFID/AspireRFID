/*
 * Copyright © 2008-2010, Aspire
 * 
 * Aspire is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 2.1 as published by
 * the Free Software Foundation (the "LGPL").
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library in the file COPYING-LGPL-2.1; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public License
 * for the specific language governing rights and limitations.
 */

package org.ow2.aspirerfid.ide.bpwme.ecspec.utils;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
//import org.ow2.aspirerfid.commons.ale.model.ale.ECSpec;
/*
 * borrow from org.ow2.aspirerfid.ide.ecspec.utils.ECSpecBuilder
 */

import org.eclipse.jface.viewers.Viewer;
import org.ow2.aspirerfid.commons.ale.model.ale.*;
import org.ow2.aspirerfid.commons.ale.model.ale.ECFilterSpec.IncludePatterns;
import org.ow2.aspirerfid.commons.ale.model.ale.ECSpec.*;
import org.ow2.aspirerfid.commons.ale.utils.ECTimeUnit;
import org.ow2.aspirerfid.commons.apdl.model.ApdlDataField;
import org.ow2.aspirerfid.commons.apdl.model.EBProc;
import org.ow2.aspirerfid.commons.epcis.model.AttributeType;
import org.ow2.aspirerfid.commons.epcis.model.EPCISMasterDataDocumentType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementType;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.bpwme.ecspec.model.Spec;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataUtil;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl.EventType;

/**
 * Hold ecspecs and notify changes to obersers.
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class ECSpecBuilder {
	private ECSpec ecspec;
	private EBProc ebproc;
	private List<Viewer> listeners;
	private ArrayList<Spec> specList;
	
//	public ECSpecBuilder() {
//		
//		listeners = new ArrayList<Viewer>(); 
//		
//		ObjectFactory of = new ObjectFactory();
//		ecspec = of.createECSpec();
//		ecspec.setLogicalReaders(of.createECSpecLogicalReaders());
//		ecspec.setReportSpecs(of.createECSpecReportSpecs());
//		ecspec.setBoundarySpec(of.createECBoundarySpec());
//		ecspec.setExtension(of.createECSpecExtension());
//		ecspec.getBoundarySpec().setExtension(of.createECBoundarySpecExtension());
//		ecspec.getBoundarySpec().getExtension().setStartTriggerList(of.createECBoundarySpecExtensionStartTriggerList());
//		ecspec.getBoundarySpec().getExtension().setStopTriggerList(of.createECBoundarySpecExtensionStopTriggerList());
//	}
	
	//bind the existing ECSpec abd EBProc to the ecspec builder
	public ECSpecBuilder(EBProc ebproc ,ECSpec ecspe) {
		listeners = new ArrayList<Viewer>();
		this.ebproc = ebproc;
		this.ecspec = ecspe;
//		ObjectFactory of = new ObjectFactory();
//		ecspec.setLogicalReaders(of.createECSpecLogicalReaders());
//		ecspec.setReportSpecs(of.createECSpecReportSpecs());
//		ecspec.setBoundarySpec(of.createECBoundarySpec());
//		ecspec.setExtension(of.createECSpecExtension());
//		ecspec.getBoundarySpec().setExtension(of.createECBoundarySpecExtension());
//		ecspec.getBoundarySpec().getExtension().setStartTriggerList(of.createECBoundarySpecExtensionStartTriggerList());
//		ecspec.getBoundarySpec().getExtension().setStopTriggerList(of.createECBoundarySpecExtensionStopTriggerList());
//		iniECSB(ebproc);
	}
	
	
	//build a new ecspec builder, set the values to default
	public ECSpecBuilder(EBProc ebproc) {
		ECSpec ecs = getECSpec(ebproc);
		if(ecs != null) {
			System.out.println("have");
			listeners = new ArrayList<Viewer>();
			this.ebproc = ebproc;
			this.ecspec = ecs;
			return;
		}
		System.out.println("nohave");
		MainControl mc = MainControl.getMainControl();
		listeners = new ArrayList<Viewer>();
		this.ebproc = ebproc;
		
		ApdlDataField adf = mc.objectFactory.createApdlDataField();
		adf.setECSpec(new ECSpec());
		adf.setType("ECSpec");
		adf.setName("Sample ECSpec");
		ebproc.getApdlDataFields().getApdlDataField().add(adf);		
		this.ecspec = adf.getECSpec();
		
		ObjectFactory of = new ObjectFactory();
		ecspec.setLogicalReaders(of.createECSpecLogicalReaders());
		ecspec.setReportSpecs(of.createECSpecReportSpecs());
		ecspec.setBoundarySpec(of.createECBoundarySpec());
		ecspec.setExtension(of.createECSpecExtension());
		ecspec.getBoundarySpec().setExtension(of.createECBoundarySpecExtension());
		ecspec.getBoundarySpec().getExtension().setStartTriggerList(of.createECBoundarySpecExtensionStartTriggerList());
		ecspec.getBoundarySpec().getExtension().setStopTriggerList(of.createECBoundarySpecExtensionStopTriggerList());

		setDuration(PreferenceConstants.P_DURARION);
		setRepeatPeriod(PreferenceConstants.P_REPEAT_PERIOD);
		setStableSetInterval(PreferenceConstants.P_STABLE_SET_INTERVAL);
		
		EPCISMasterDataDocumentType doc = MasterDataUtil.getEPCISMasterDataDocument(ebproc);
		VocabularyElementType vocabularyElement = MasterDataUtil.getEBProcVocabularyElement(doc);
		AttributeType attr = MasterDataUtil.getVocabularyElementAttribute(vocabularyElement, "urn:epcglobal:epcis:mda:event_type");
		EventType type = EventType.createEvent(attr.getOtherAttributes().get(new QName("value")));
		
		if(type == null) {
			System.out.println("Cannot find type definition in " + ebproc.getId());
		}
		
		switch(type) {
		case AGGREGATION_EVENT:
			ecspec.getReportSpecs().getReportSpec().add(createBiz());
			ecspec.getReportSpecs().getReportSpec().add(createTran());
			ecspec.getReportSpecs().getReportSpec().add(createPare());
			break;
		case OBJECT_EVENT:
			ecspec.getReportSpecs().getReportSpec().add(createBiz());
			ecspec.getReportSpecs().getReportSpec().add(createTran());
			break;
		case QUANTITY_EVENT:
			ecspec.getReportSpecs().getReportSpec().add(createBiz());
			ecspec.getReportSpecs().getReportSpec().add(createTran());
			break;
		case TRANSACTION_EVENT:
			ecspec.getReportSpecs().getReportSpec().add(createBizP());
			ecspec.getReportSpecs().getReportSpec().add(createBiz());
			ecspec.getReportSpecs().getReportSpec().add(createTran());
			break;
		}
		
	}
	
	public void setEBProc(EBProc ebp) {
		this.ebproc = ebp;
		ECSpec ecs = getECSpec(ebp);
		if(ecs != null) {
			System.out.println("have");
			this.ecspec = ecs;
		}else {
			System.out.println("nohave");
			MainControl mc = MainControl.getMainControl();
			ApdlDataField adf = mc.objectFactory.createApdlDataField();
			adf.setECSpec(new ECSpec());
			adf.setType("ECSpec");
			adf.setName("Sample ECSpec");
			ebproc.getApdlDataFields().getApdlDataField().add(adf);		
			this.ecspec = adf.getECSpec();
			
			ObjectFactory of = new ObjectFactory();
			ecspec.setLogicalReaders(of.createECSpecLogicalReaders());
			ecspec.setReportSpecs(of.createECSpecReportSpecs());
			ecspec.setBoundarySpec(of.createECBoundarySpec());
			ecspec.setExtension(of.createECSpecExtension());
			ecspec.getBoundarySpec().setExtension(of.createECBoundarySpecExtension());
			ecspec.getBoundarySpec().getExtension().setStartTriggerList(of.createECBoundarySpecExtensionStartTriggerList());
			ecspec.getBoundarySpec().getExtension().setStopTriggerList(of.createECBoundarySpecExtensionStopTriggerList());

			setDuration(PreferenceConstants.P_DURARION);
			setRepeatPeriod(PreferenceConstants.P_REPEAT_PERIOD);
			setStableSetInterval(PreferenceConstants.P_STABLE_SET_INTERVAL);
			
			EPCISMasterDataDocumentType doc = MasterDataUtil.getEPCISMasterDataDocument(ebproc);
			VocabularyElementType vocabularyElement = MasterDataUtil.getEBProcVocabularyElement(doc);
			AttributeType attr = MasterDataUtil.getVocabularyElementAttribute(vocabularyElement, "urn:epcglobal:epcis:mda:event_type");
			EventType type = EventType.createEvent(attr.getOtherAttributes().get(new QName("value")));
			
			if(type == null) {
				System.out.println("Cannot find type definition in " + ebproc.getId());
			}
			
			switch(type) {
			case AGGREGATION_EVENT:
				ecspec.getReportSpecs().getReportSpec().add(createBiz());
				ecspec.getReportSpecs().getReportSpec().add(createTran());
				ecspec.getReportSpecs().getReportSpec().add(createPare());
				break;
			case OBJECT_EVENT:
				ecspec.getReportSpecs().getReportSpec().add(createBiz());
				ecspec.getReportSpecs().getReportSpec().add(createTran());
				break;
			case QUANTITY_EVENT:
				ecspec.getReportSpecs().getReportSpec().add(createBiz());
				ecspec.getReportSpecs().getReportSpec().add(createTran());
				break;
			case TRANSACTION_EVENT:
				ecspec.getReportSpecs().getReportSpec().add(createBizP());
				ecspec.getReportSpecs().getReportSpec().add(createBiz());
				ecspec.getReportSpecs().getReportSpec().add(createTran());
				break;
			}
		}	
		notifyListeners();
	}
	
	private ECSpec getECSpec(EBProc ebp) {
		if(ebp.getApdlDataFields().getApdlDataField().size() == 0) {
			return null;
		}
		for(ApdlDataField apdl:ebp.getApdlDataFields().getApdlDataField()) {
			if(apdl.getType().equals("ECSpec")) {
				return apdl.getECSpec();
			}
		}
		return null;
	}
	
//	private void iniECSB(EBProc ebproc) {
//		setDuration(4500);
//		setRepeatPeriod(4500);
//		setStableSetInterval(0);
//		MainControl mc = MainControl.getMainControl();
//		EventType type = mc.ebprocMap.get(ebproc.getId());
//		//TODO if type is null, means this is an open of exising apdl file
//		//now the solution is to give him a default type
//		//after master data is configured, everything will be fine
//		//other solution is to store the type somewhere
//		if(type == null) {
//			type = EventType.AGGREGATION_EVENT;
//		}	
//	}
	
	private ECReportSpec createBiz() {
		ECReportSpec bizTransactionReport = new ECReportSpec();
		bizTransactionReport.setReportOnlyOnChange(true);
		bizTransactionReport.setReportName("bizTransactionIDs");
		bizTransactionReport.setFilterSpec(new ECFilterSpec());
		bizTransactionReport.setGroupSpec(new ECGroupSpec());
		IncludePatterns ip = new IncludePatterns();
		//ip.getIncludePattern().add("urn:epc:pat:gid-96:145.12.*");
		bizTransactionReport.getFilterSpec().setIncludePatterns(ip);
		return bizTransactionReport;
	}
	
	private ECReportSpec createBizP() {
		ECReportSpec bizTransactionParentReport = new ECReportSpec();
		bizTransactionParentReport.setReportOnlyOnChange(true);
		bizTransactionParentReport.setReportName("bizTransactionParentIDs");
		bizTransactionParentReport.setFilterSpec(new ECFilterSpec());
		bizTransactionParentReport.setGroupSpec(new ECGroupSpec());
		IncludePatterns ip = new IncludePatterns();
		//ip.getIncludePattern().add("urn:epc:pat:gid-96:145.19.*");
		bizTransactionParentReport.getFilterSpec().setIncludePatterns(ip);
		return bizTransactionParentReport;
	}
	
	private ECReportSpec createTran() {
		ECReportSpec transactionItemReport = new ECReportSpec();
		transactionItemReport.setReportOnlyOnChange(true);
		transactionItemReport.setReportName("transactionItems");
		transactionItemReport.setFilterSpec(new ECFilterSpec());
		transactionItemReport.setGroupSpec(new ECGroupSpec());
		IncludePatterns ip = new IncludePatterns();
		//ip.getIncludePattern().add("urn:epc:pat:gid-96:145.223.*");
		transactionItemReport.getFilterSpec().setIncludePatterns(ip);
		return transactionItemReport;
	}
	
	private ECReportSpec createPare() {
		ECReportSpec parentObjectReport = new ECReportSpec();
		parentObjectReport.setReportOnlyOnChange(true);
		parentObjectReport.setReportName("parentObjects");
		parentObjectReport.setFilterSpec(new ECFilterSpec());
		parentObjectReport.setGroupSpec(new ECGroupSpec());
		IncludePatterns ip = new IncludePatterns();
		//ip.getIncludePattern().add("urn:epc:pat:gid-96:145.56.*");
		parentObjectReport.getFilterSpec().setIncludePatterns(ip);
		return parentObjectReport;
	}
	
	public void setECSpec(ECSpec ecspec) {
		this.ecspec = ecspec;
		notifyListeners();
	}
	
	public void addListener(Viewer listener) {
		listeners.add(listener);
	}
	
	public void notifyListeners() {
		for(Viewer v : listeners) {
			v.refresh();
		}
//		MainControl mc = MainControl.getMainControl();
//		if(mc.ecEditor != null) {
//			mc.ecEditor.setDirty(true);
//		}
	}
	
	//logical reader related
	public boolean addLogicalReader(String id) {
		boolean result = ecspec.getLogicalReaders().getLogicalReader().add(id);
		notifyListeners();
		return result;
		
	}

	public void setLogicalReaders(List<String> list) {
		ecspec.getLogicalReaders().getLogicalReader().clear();
		ecspec.getLogicalReaders().getLogicalReader().addAll(list);
		notifyListeners();
	}
	

	public List<String> getLogicalReaders() {
		return ecspec.getLogicalReaders().getLogicalReader();
	}

	public boolean removeLogicalReader(String id) {
		boolean result = ecspec.getLogicalReaders().getLogicalReader().remove(id);
		notifyListeners();
		return result;
	}
	//boundary related
	public void setWhenDataAvailiable(boolean availiable) {
		ecspec.getBoundarySpec().getExtension().setWhenDataAvailable(availiable);
		notifyListeners();
	}
	/*
	public static void main(String args[]) {
		ECSpecBuilder ecs = new ECSpecBuilder();
		System.out.println(ecs);
	}*/
	public void setRepeatPeriod(long time)
	{
		setRepeatPeriod(time, ECTimeUnit.MS);
		notifyListeners();
	}
	
	private void setRepeatPeriod(long time, String timeUnit)
	{
		ECTime ectime = new ECTime();
		ectime.setValue(time);
		ectime.setUnit(timeUnit);
		ecspec.getBoundarySpec().setRepeatPeriod(ectime);
	}
	
	public long getRepeatPeriod()
	{
		return ecspec.getBoundarySpec().getRepeatPeriod().getValue();
	}
	
	public void setDuration(long time)
	{
		setDuration(time, ECTimeUnit.MS);
		notifyListeners();
	}
	
	private void setDuration(long time, String timeUnit)
	{
		ECTime ectime = new ECTime();
		ectime.setValue(time);
		ectime.setUnit(timeUnit);
		ecspec.getBoundarySpec().setDuration(ectime);
	}
	
	public long getDuration()
	{
		return ecspec.getBoundarySpec().getDuration().getValue();
	}
	
	public void setStableSetInterval(long time)
	{
		setStableSetInterval(time, ECTimeUnit.MS);
		notifyListeners();
	}
	
	private void setStableSetInterval(long time, String timeUnit)
	{
		ECTime ectime = new ECTime();
		ectime.setValue(time);
		ectime.setUnit(timeUnit);
		ecspec.getBoundarySpec().setStableSetInterval(ectime);
	}
	
	public long getStableSetInterval()
	{
		return ecspec.getBoundarySpec().getStableSetInterval().getValue();
	}
	
	public List<String> getStartTriggerList()
	{
		return ecspec.getBoundarySpec().getExtension().getStartTriggerList().getStartTrigger();
	}
	
	public void addStartTrigger(String newStart) {
		ecspec.getBoundarySpec().getExtension().getStartTriggerList().
		getStartTrigger().add(newStart);
		notifyListeners();
	}
	
	public void changeStartTrigger(String oldStart, String newStart) {
		ecspec.getBoundarySpec().getExtension().getStartTriggerList().
		getStartTrigger().remove(oldStart);
		ecspec.getBoundarySpec().getExtension().getStartTriggerList().
		getStartTrigger().add(newStart);
		notifyListeners();
	}
	
	public void removeStartTrigger(String oldStart) {
		ecspec.getBoundarySpec().getExtension().getStartTriggerList().
		getStartTrigger().remove(oldStart);
		notifyListeners();
	}
	
	public void addStopTrigger(String newStop) {
		ecspec.getBoundarySpec().getExtension().getStopTriggerList().
		getStopTrigger().add(newStop);
		notifyListeners();
	}
	
	public void changeStopTrigger(String oldStop, String newStop) {
		ecspec.getBoundarySpec().getExtension().getStopTriggerList().
		getStopTrigger().remove(oldStop);
		ecspec.getBoundarySpec().getExtension().getStopTriggerList().
		getStopTrigger().add(newStop);
		notifyListeners();
	}
	
	public void removeStopTrigger(String oldStop) {
		ecspec.getBoundarySpec().getExtension().getStopTriggerList().
		getStopTrigger().remove(oldStop);
		notifyListeners();
	}
	
	public void setStartTriggerList(List<String> list) {
		ecspec.getBoundarySpec().getExtension().getStartTriggerList().getStartTrigger().clear();
		ecspec.getBoundarySpec().getExtension().getStartTriggerList().getStartTrigger().addAll(list);
		notifyListeners();
	}
	
	public List<String> getStopTriggerList()
	{
		return ecspec.getBoundarySpec().getExtension().getStopTriggerList().getStopTrigger();
	}
	
	public void setStopTriggerList(List<String> list) {
		ecspec.getBoundarySpec().getExtension().getStopTriggerList().getStopTrigger().clear();
		ecspec.getBoundarySpec().getExtension().getStopTriggerList().getStopTrigger().addAll(list);
		notifyListeners();
	}
	//report related
	public boolean addECReportSpec(ECReportSpec reportSpec) {
		if (ecspec.getReportSpecs() == null) {
			ReportSpecs rep = new ReportSpecs();
			ecspec.setReportSpecs(rep);
		}
		return ecspec.getReportSpecs().getReportSpec().add(reportSpec);
	}

	public List<ECReportSpec> getECReportSpecs() {
		return ecspec.getReportSpecs().getReportSpec();
	}

	public boolean removeECReportSpec(ECReportSpec reportSpec) {
		return ecspec.getReportSpecs().getReportSpec().remove(reportSpec);
	}
	
	public List<String> getIncludePatterns(ECReportSpec reportSpec) {
		return reportSpec.getFilterSpec().getIncludePatterns().getIncludePattern();
		/*
		for(ECReportSpec temp: ecspec.getReportSpecs().getReportSpec()) {
			if(temp == reportSpec) {
				return temp.getFilterSpec().getIncludePatterns().getIncludePattern();
			}
		}
		return null;*/
	}
	
	public void addIncludePattern(String pattern, ECReportSpec reportSpec) {
		reportSpec.getFilterSpec().getIncludePatterns().getIncludePattern().add(pattern);
	}
	
	public boolean removeIncludePattern(String pattern, ECReportSpec reportSpec) {
		return reportSpec.getFilterSpec().getIncludePatterns().getIncludePattern().remove(pattern);
	}
	
	
	public boolean addFilter(String filter, ECReportSpec reportSpec) {
		boolean result = reportSpec.getFilterSpec().getIncludePatterns().getIncludePattern().add(filter);
		notifyListeners();
		return result;
	}
	
	public boolean removeFilter(String filter, ECReportSpec reportSpec) {
		boolean result = reportSpec.getFilterSpec().getIncludePatterns().getIncludePattern().remove(filter);
		notifyListeners();
		return result;
	}
	public boolean changeFilter(String oldFilter, String newFilter, ECReportSpec reportSpec) {
		reportSpec.getFilterSpec().getIncludePatterns().getIncludePattern().remove(oldFilter);
		reportSpec.getFilterSpec().getIncludePatterns().getIncludePattern().add(newFilter);
		notifyListeners();
		return true;
	}
	//two boolean value
	public void setReportIfEmpty(boolean value, ECReportSpec reportSpec) {
		reportSpec.setReportIfEmpty(value);
		notifyListeners();
	}
	public void setReportOnChange(boolean value, ECReportSpec reportSpec) {
		reportSpec.setReportOnlyOnChange(value);
		notifyListeners();
	}
	
}
