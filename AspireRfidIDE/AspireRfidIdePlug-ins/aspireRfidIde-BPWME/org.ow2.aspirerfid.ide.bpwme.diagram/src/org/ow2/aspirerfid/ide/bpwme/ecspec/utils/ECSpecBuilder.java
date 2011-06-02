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
import org.eclipse.ui.IEditorPart;
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
import org.ow2.aspirerfid.ide.bpwme.ecspec.views.ECSpecEditor;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataUtil;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;
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
	private boolean isDirty;
	
	
	public ECSpecBuilder() {
		listeners = new ArrayList<Viewer>();
		isDirty = false;
	}
	
	//build a new ecspec builder, if ecspec field is null, set the values to default
	public ECSpecBuilder(EBProc ebproc) {
		ECSpec ecs = getECSpec(ebproc);
		if(ecs != null) {
			this.listeners = new ArrayList<Viewer>();
			this.ebproc = ebproc;
			this.ecspec = ecs;
			isDirty = false;
			return;
		}
		MainControl mc = MainControl.getMainControl();
		this.listeners = new ArrayList<Viewer>();
		this.ebproc = ebproc;
		
		ObjectFactory of = new ObjectFactory();
		ApdlDataField adf = mc.objectFactory.createApdlDataField();
		adf.setECSpec(of.createECSpec());
		adf.setType("ECSpec");
		//nkef
		adf.setName(ebproc.getId());
		this.ebproc.getApdlDataFields().getApdlDataField().add(adf);		
		this.ecspec = adf.getECSpec();
		
		//nkef
		ecspec.setIncludeSpecInReports(false);
		
		ecspec.setLogicalReaders(of.createECSpecLogicalReaders());
		ecspec.setReportSpecs(of.createECSpecReportSpecs());
		ecspec.setBoundarySpec(of.createECBoundarySpec());
		ecspec.setExtension(of.createECSpecExtension());
		ecspec.getBoundarySpec().setExtension(of.createECBoundarySpecExtension());
		ecspec.getBoundarySpec().getExtension().setStartTriggerList(of.createECBoundarySpecExtensionStartTriggerList());
		ecspec.getBoundarySpec().getExtension().setStopTriggerList(of.createECBoundarySpecExtensionStopTriggerList());
		ecspec.getBoundarySpec().getExtension().setWhenDataAvailable(false);
		
		setDuration(PreferenceConstants.P_DURARION);
		setRepeatPeriod(PreferenceConstants.P_REPEAT_PERIOD);
		setStableSetInterval(PreferenceConstants.P_STABLE_SET_INTERVAL);
		
		EPCISMasterDataDocumentType doc = MasterDataUtil.getEPCISMasterDataDocument(ebproc);
		VocabularyElementType vocabularyElement = MasterDataUtil.getEBProcVocabularyElement(doc);
		AttributeType attr = MasterDataUtil.getVocabularyElementAttribute(vocabularyElement, "urn:epcglobal:epcis:mda:event_type");
		EventType type = EventType.createEvent(attr.getOtherAttributes().get(new QName("value")));
		
		if(type == null) {
			System.out.println("Cannot find type definition in " + ebproc.getId());
			return;
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
		isDirty = true;
	}
	
	public boolean isDirty() {
		return isDirty;
	}
	
	public void setEBProc(EBProc ebp) {
		this.ebproc = ebp;
		ECSpec ecs = getECSpec(ebp);
		if(ecs != null) {
			this.ecspec = ecs;
		}else {
			//System.out.println("set nohave");
			MainControl mc = MainControl.getMainControl();
			ApdlDataField adf = mc.objectFactory.createApdlDataField();
			adf.setECSpec(new ECSpec());
			adf.setType("ECSpec");
			
			//nkef
			adf.setName(ebproc.getId());
			
			ebproc.getApdlDataFields().getApdlDataField().add(adf);		
			this.ecspec = adf.getECSpec();
			
			ObjectFactory of = new ObjectFactory();
			
			//nkef
			ecspec.setIncludeSpecInReports(false);
			
			ecspec.setLogicalReaders(of.createECSpecLogicalReaders());
			ecspec.setReportSpecs(of.createECSpecReportSpecs());
			ecspec.setBoundarySpec(of.createECBoundarySpec());
			ecspec.setExtension(of.createECSpecExtension());
			ecspec.getBoundarySpec().setExtension(of.createECBoundarySpecExtension());
			ecspec.getBoundarySpec().getExtension().setStartTriggerList(of.createECBoundarySpecExtensionStartTriggerList());
			ecspec.getBoundarySpec().getExtension().setStopTriggerList(of.createECBoundarySpecExtensionStopTriggerList());
			ecspec.getBoundarySpec().getExtension().setWhenDataAvailable(false);
			
			setDuration(PreferenceConstants.P_DURARION,ECTimeUnit.MS);
			setRepeatPeriod(PreferenceConstants.P_REPEAT_PERIOD,ECTimeUnit.MS);
			setStableSetInterval(PreferenceConstants.P_STABLE_SET_INTERVAL,ECTimeUnit.MS);
			//System.out.println("set done2");
			EPCISMasterDataDocumentType doc = MasterDataUtil.getEPCISMasterDataDocument(ebproc);
			VocabularyElementType vocabularyElement = MasterDataUtil.getEBProcVocabularyElement(doc);
			AttributeType attr = MasterDataUtil.getVocabularyElementAttribute(vocabularyElement, "urn:epcglobal:epcis:mda:event_type");
			EventType type = EventType.createEvent(attr.getOtherAttributes().get(new QName("value")));
			
			if(type == null) {
				System.out.println("Cannot find type definition in " + ebproc.getId());
				return;
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
			ECSpecUtil.setECEditorDirty();
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
		
	private ECReportSpec createBiz() {
		ECReportSpec bizTransactionReport = new ECReportSpec();
		bizTransactionReport.setReportOnlyOnChange(true);
		
		//nkef
		bizTransactionReport.setReportIfEmpty(false);
		
		bizTransactionReport.setReportName("bizTransactionIDs");
		
		//nkef
		ECReportSetSpec bizTransReportECReportSetSpec = new ECReportSetSpec();
		bizTransReportECReportSetSpec.setSet("CURRENT");
		bizTransactionReport.setReportSet(bizTransReportECReportSetSpec);
		
		bizTransactionReport.setFilterSpec(new ECFilterSpec());
		bizTransactionReport.setGroupSpec(new ECGroupSpec());
		IncludePatterns ip = new IncludePatterns();
		//ip.getIncludePattern().add("urn:epc:pat:gid-96:145.12.*");
		bizTransactionReport.getFilterSpec().setIncludePatterns(ip);
		
		//nkef
		ECReportOutputSpec bizTransReportECReportOutputSpec = new ECReportOutputSpec();
		bizTransReportECReportOutputSpec.setIncludeCount(true);
		bizTransReportECReportOutputSpec.setIncludeEPC(true);
		bizTransReportECReportOutputSpec.setIncludeRawDecimal(true);
		bizTransReportECReportOutputSpec.setIncludeRawHex(true);
		bizTransReportECReportOutputSpec.setIncludeTag(true);
		bizTransactionReport.setOutput(bizTransReportECReportOutputSpec);
		
		return bizTransactionReport;
	}
	
	private ECReportSpec createBizP() {
		ECReportSpec bizTransactionParentReport = new ECReportSpec();
		bizTransactionParentReport.setReportOnlyOnChange(true);
		
		//nkef
		bizTransactionParentReport.setReportIfEmpty(false);
		
		bizTransactionParentReport.setReportName("bizTransactionParentIDs");
		
		//nkef
		ECReportSetSpec bizTransReportECReportSetSpec = new ECReportSetSpec();
		bizTransReportECReportSetSpec.setSet("CURRENT");
		bizTransactionParentReport.setReportSet(bizTransReportECReportSetSpec);		
		
		bizTransactionParentReport.setFilterSpec(new ECFilterSpec());
		bizTransactionParentReport.setGroupSpec(new ECGroupSpec());
		IncludePatterns ip = new IncludePatterns();
		//ip.getIncludePattern().add("urn:epc:pat:gid-96:145.19.*");
		bizTransactionParentReport.getFilterSpec().setIncludePatterns(ip);
		
		//nkef
		ECReportOutputSpec bizTransReportECReportOutputSpec = new ECReportOutputSpec();
		bizTransReportECReportOutputSpec.setIncludeCount(true);
		bizTransReportECReportOutputSpec.setIncludeEPC(true);
		bizTransReportECReportOutputSpec.setIncludeRawDecimal(true);
		bizTransReportECReportOutputSpec.setIncludeRawHex(true);
		bizTransReportECReportOutputSpec.setIncludeTag(true);
		bizTransactionParentReport.setOutput(bizTransReportECReportOutputSpec);
		
		return bizTransactionParentReport;
	}
	
	private ECReportSpec createTran() {
		ECReportSpec transactionItemReport = new ECReportSpec();
		transactionItemReport.setReportOnlyOnChange(true);
		
		//nkef
		transactionItemReport.setReportIfEmpty(false);
		
		transactionItemReport.setReportName("transactionItems");
		
		//nkef
		ECReportSetSpec bizTransReportECReportSetSpec = new ECReportSetSpec();
		bizTransReportECReportSetSpec.setSet("ADDITIONS");
		transactionItemReport.setReportSet(bizTransReportECReportSetSpec);
		
		transactionItemReport.setFilterSpec(new ECFilterSpec());
		transactionItemReport.setGroupSpec(new ECGroupSpec());
		IncludePatterns ip = new IncludePatterns();
		//ip.getIncludePattern().add("urn:epc:pat:gid-96:145.223.*");
		transactionItemReport.getFilterSpec().setIncludePatterns(ip);
		
		//nkef
		ECReportOutputSpec bizTransReportECReportOutputSpec = new ECReportOutputSpec();
		bizTransReportECReportOutputSpec.setIncludeCount(true);
		bizTransReportECReportOutputSpec.setIncludeEPC(true);
		bizTransReportECReportOutputSpec.setIncludeRawDecimal(true);
		bizTransReportECReportOutputSpec.setIncludeRawHex(true);
		bizTransReportECReportOutputSpec.setIncludeTag(true);
		transactionItemReport.setOutput(bizTransReportECReportOutputSpec);
		
		return transactionItemReport;
	}
	
	private ECReportSpec createPare() {
		ECReportSpec parentObjectReport = new ECReportSpec();
		parentObjectReport.setReportOnlyOnChange(true);
		
		//nkef
		parentObjectReport.setReportIfEmpty(false);
		
		parentObjectReport.setReportName("parentObjects");
		
		//nkef
		ECReportSetSpec bizTransReportECReportSetSpec = new ECReportSetSpec();
		bizTransReportECReportSetSpec.setSet("ADDITIONS");
		parentObjectReport.setReportSet(bizTransReportECReportSetSpec);		
		
		parentObjectReport.setFilterSpec(new ECFilterSpec());
		parentObjectReport.setGroupSpec(new ECGroupSpec());
		IncludePatterns ip = new IncludePatterns();
		//ip.getIncludePattern().add("urn:epc:pat:gid-96:145.56.*");
		parentObjectReport.getFilterSpec().setIncludePatterns(ip);
		
		//nkef
		ECReportOutputSpec bizTransReportECReportOutputSpec = new ECReportOutputSpec();
		bizTransReportECReportOutputSpec.setIncludeCount(true);
		bizTransReportECReportOutputSpec.setIncludeEPC(true);
		bizTransReportECReportOutputSpec.setIncludeRawDecimal(true);
		bizTransReportECReportOutputSpec.setIncludeRawHex(true);
		bizTransReportECReportOutputSpec.setIncludeTag(true);
		parentObjectReport.setOutput(bizTransReportECReportOutputSpec);
		
		return parentObjectReport;
	}
	
	public void setECSpec(ECSpec ecspec) {
		this.ecspec = ecspec;
		notifyListeners();
	}
	
	public void addListener(Viewer listener) {
		listeners.add(listener);
	}
	
	//TODO check the whole process
	public void notifyListeners() {		
		for(Viewer v : listeners) {
			//System.out.println(v);
			v.refresh();
		}
	}
	
	//logical reader related
	public boolean addLogicalReader(String id) {
		boolean result = ecspec.getLogicalReaders().getLogicalReader().add(id);
		notifyListeners();
		return result;		
	}
	
	public void changeLogicalReaderName(String oldName, String newName) {
		boolean result = ecspec.getLogicalReaders().getLogicalReader().remove(oldName);
		if(result) {
			ecspec.getLogicalReaders().getLogicalReader().add(newName);			
		}
		notifyListeners();
	}

	public void setLogicalReaders(List<String> list) {
		ecspec.getLogicalReaders().getLogicalReader().clear();
		ecspec.getLogicalReaders().getLogicalReader().addAll(list);
		notifyListeners();
	}
	

	public List<String> getLogicalReaders() {
		if(ecspec != null) {
			return ecspec.getLogicalReaders().getLogicalReader();	
		}else {
			return new ArrayList<String>();
		}
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
	
	public boolean getWhenDataAvailable() {
		if(ecspec != null) {
			return  ecspec.getBoundarySpec().getExtension().isWhenDataAvailable();
		}else {
			return false;
		}
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
	
	private void setRepeatPeriod(long time, String timeUnit){
		if(ecspec != null) {
			ECTime ectime = new ECTime();
			ectime.setValue(time);
			ectime.setUnit(timeUnit);
			ecspec.getBoundarySpec().setRepeatPeriod(ectime);
		}
	}
	
	public long getRepeatPeriod()
	{
		if(ecspec != null) {
			return ecspec.getBoundarySpec().getRepeatPeriod().getValue();
		}else {
			return 0;
		}
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
		if(ecspec != null) {
			return ecspec.getBoundarySpec().getDuration().getValue();
		}else {
			return 0;
		}
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
		if(ecspec != null) {
			return ecspec.getBoundarySpec().getStableSetInterval().getValue();
		}else {
			return 0;
		}
		
	}
	
	public List<String> getStartTriggerList()
	{
		if(ecspec != null) {
			return ecspec.getBoundarySpec().getExtension().getStartTriggerList().getStartTrigger();
		} else {
			return new ArrayList<String>();
		}
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
		if(ecspec != null) {
			return ecspec.getBoundarySpec().getExtension().getStopTriggerList().getStopTrigger();
		}else {
			return new ArrayList<String>();
		}
		
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
		if(ecspec != null) {
			return ecspec.getReportSpecs().getReportSpec();
		}else {
			return new ArrayList<ECReportSpec>();
		}		
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
	
	public void setReportName(String value, ECReportSpec reportSpec) {
		reportSpec.setReportName(value);
		notifyListeners();
	}

	public void clearListeners() {
		listeners.clear();		
	}
	
}
