package org.ow2.aspirerfid.ide.bpwme.diagram.sheet;

import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.gmf.runtime.common.ui.services.properties.extended.PropertySource;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.ow2.aspirerfid.commons.epcis.model.EPCISMasterDataDocumentType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementType;
import org.ow2.aspirerfid.ide.bpwme.BpwmePackage;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceUtil;
import org.ow2.aspirerfid.ide.bpwme.impl.EBProcImpl;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataBuilder;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataUtil;
import org.ow2.aspirerfid.ide.bpwme.utils.EBProcUtil;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;

public class EBProcProperties extends PropertySource {

	private String[] actions = {"ADD", "OBSERVE", "DELETE"};


	public EBProcProperties(Object element) {
		super(element);
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {

		EBProcImpl epi = (EBProcImpl)getElement();
		MainControl mc = MainControl.getMainControl();
		org.ow2.aspirerfid.commons.apdl.model.EBProc ebproc = 
			(org.ow2.aspirerfid.commons.apdl.model.EBProc) mc.getMapObject(epi.hashCode());
		MasterDataBuilder mdb = MasterDataBuilder.getInstance();
		mdb.setEBProc(ebproc);		

		PropertyDescriptor[] propertyDescriptors = new PropertyDescriptor[3 + 8];

		propertyDescriptors[0] = new TextPropertyDescriptor("Id", "Id");
		propertyDescriptors[1] = new TextPropertyDescriptor("Name", "Name");
		propertyDescriptors[2] = new TextPropertyDescriptor("Description", "Description");

		//read only text fields
		propertyDescriptors[3] = new PropertyDescriptor("EventName", "Event Name");
		propertyDescriptors[4] = new PropertyDescriptor("EventType", "Event Type");
		propertyDescriptors[5] = new PropertyDescriptor("BusinessLocation", "Business Location");
		//combo box fields
		propertyDescriptors[6] = new ComboBoxPropertyDescriptor("Action","Action",actions);
		propertyDescriptors[7] = new ComboBoxPropertyDescriptor("ReadPoint","Read Point",mdb.getReadPoints());
		propertyDescriptors[8] = new ComboBoxPropertyDescriptor("BusinessStep","Business Step",mdb.getBusinessSteps());
		propertyDescriptors[9] = new ComboBoxPropertyDescriptor("Disposition","Disposition",mdb.getDispositions());
		propertyDescriptors[10] = new ComboBoxPropertyDescriptor("TransactionType","Transaction Type",mdb.getTransactions());

		for (int i = 0; i < 3; i ++) {
			propertyDescriptors[i].setCategory("General");
		}		
		for (int i = 3; i < 11; i ++) {
			propertyDescriptors[i].setCategory("Transaction Description");
		}
		return propertyDescriptors;
	}

	@Override
	public Object getPropertyValue(Object id) {
		EBProcImpl epi = (EBProcImpl)getElement();
		MainControl mc = MainControl.getMainControl();
		org.ow2.aspirerfid.commons.apdl.model.EBProc ebproc = 
			(org.ow2.aspirerfid.commons.apdl.model.EBProc) mc.getMapObject(epi.hashCode());
		MasterDataBuilder mdb = MasterDataBuilder.getInstance();
		mdb.setEBProc(ebproc);
		if(id.equals("EventName")) {
			//return "fake";
			String result = mdb.getAttribute("urn:epcglobal:epcis:mda:event_name");
			if(result != null) {
				return result;
			}
			else return "Something is wrong";

		}else if(id.equals("EventType")) {
			String result = mdb.getAttribute("urn:epcglobal:epcis:mda:event_type");
			if(result != null) {
				return result;
			}
			else return "Something is wrong";

		}else if(id.equals("BusinessLocation")) {
			String result = mdb.getAttribute("urn:epcglobal:epcis:mda:business_location");
			if(result != null) {
				return result;
			}
			else return "Something is wrong";

		}else if(id.equals("Action")) {
			String result = mdb.getAttribute("urn:epcglobal:epcis:mda:action");
			if(result == null) {
				return -1;
			}else {
				return EBProcUtil.getAttributeIndex(result, actions);
			}			
		}else if(id.equals("ReadPoint")) {
			String result = mdb.getAttribute("urn:epcglobal:epcis:mda:read_point");
			if(result == null) {
				return -1;
			}else {
				return EBProcUtil.getAttributeIndex(result, mdb.getReadPoints()); 
			}
		}else if(id.equals("BusinessStep")) {
			String result = mdb.getAttribute("urn:epcglobal:epcis:mda:business_step");
			if(result == null) {
				return -1;
			}else {
				return EBProcUtil.getAttributeIndex(result, mdb.getBusinessSteps()); 
			}
		}else if(id.equals("Disposition")) {
			String result = mdb.getAttribute("urn:epcglobal:epcis:mda:disposition");
			if(result == null) {
				return -1;
			}else {
				return EBProcUtil.getAttributeIndex(result, mdb.getDispositions()); 
			}
		}else if(id.equals("TransactionType")){
			String result = mdb.getAttribute("urn:epcglobal:epcis:mda:transaction_type");
			if(result == null) {
				return -1;
			}else {
				return EBProcUtil.getAttributeIndex(result, mdb.getTransactions()); 
			}
		}else if(id.equals("Id")) {
			return epi.getId();
		}else if(id.equals("Name")) {
			return epi.getName();
		}else if(id.equals("Description")) {
			return epi.getDescription();
		}else {
			System.out.println("Wrong id in get " + id);
			return "fake";
		}
	}


	@Override
	public void setPropertyValue(Object id, Object value) {
		EBProcImpl epi = (EBProcImpl)getElement();
		MainControl mc = MainControl.getMainControl();
		org.ow2.aspirerfid.commons.apdl.model.EBProc ebproc = 
			(org.ow2.aspirerfid.commons.apdl.model.EBProc) mc.getMapObject(epi.hashCode());
		MasterDataBuilder mdb = MasterDataBuilder.getInstance();
		EPCISMasterDataDocumentType doc = MasterDataUtil.getEPCISMasterDataDocument(ebproc);
		VocabularyElementType vocabularyElement = MasterDataUtil.getEBProcVocabularyElement(doc);
		mdb.setEBProc(ebproc);
		
		if(id.equals("Name")) {
			epi.setName((String)value);
			ebproc.setName((String)value);
			MasterDataUtil.setVocabularyElementAttribute(vocabularyElement, 
					"urn:epcglobal:epcis:mda:event_name", 
					(String)value);
			return;
		}else if(id.equals("Description")) {
			epi.setDescription((String)value);
			ebproc.setDescription((String)value);
			return;
		}else if(id.equals("Id")) {
			epi.setId((String)value);
			ebproc.setId((String)value);
			MasterDataUtil.setVocabularyElementID(vocabularyElement, (String)value);
			return;
		}else if(id.equals("Action")) {
			Integer index = (Integer)value;
			mdb.setAttribute("urn:epcglobal:epcis:mda:action", actions[index]);
		}else if(id.equals("ReadPoint")) {
			Integer index = (Integer)value;
			String[] readPoints = mdb.getReadPoints();
			mdb.setAttribute("urn:epcglobal:epcis:mda:read_point", readPoints[index]);
			String bizLocation = mdb.getBusinessLocation(readPoints[index]);
			mdb.setAttribute("urn:epcglobal:epcis:mda:business_location", bizLocation);
		}else if(id.equals("BusinessStep")) {
			Integer index = (Integer)value;
			String[] businessSteps = mdb.getBusinessSteps();
			mdb.setAttribute("urn:epcglobal:epcis:mda:business_step", businessSteps[index]);
		}else if(id.equals("Disposition")) {
			Integer index = (Integer)value;
			String[] dispositions = mdb.getDispositions();
			mdb.setAttribute("urn:epcglobal:epcis:mda:disposition", dispositions[index]);
		}else if(id.equals("TransactionType")) {
			Integer index = (Integer)value;
			String[] transactions = mdb.getTransactions();
			mdb.setAttribute("urn:epcglobal:epcis:mda:transaction_type", transactions[index]);
		}
		epi.setFake("fake");
		mc.saveObject();
	}


	@Override
	public boolean isPropertySet(Object id) {
		return true;
	}

}
