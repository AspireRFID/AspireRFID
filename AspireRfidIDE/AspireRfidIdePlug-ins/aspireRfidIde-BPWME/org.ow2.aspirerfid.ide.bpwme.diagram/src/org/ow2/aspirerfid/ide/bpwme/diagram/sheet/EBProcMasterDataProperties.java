package org.ow2.aspirerfid.ide.bpwme.diagram.sheet;

import org.eclipse.gmf.runtime.common.ui.services.properties.extended.PropertySource;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.ow2.aspirerfid.ide.bpwme.impl.EBProcImpl;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataBuilder;
import org.ow2.aspirerfid.ide.bpwme.utils.EBProcUtil;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;

public class EBProcMasterDataProperties extends PropertySource {
	
	private String[] actions = {"ADD", "OBSERVE", "DELETE"};
	
	public EBProcMasterDataProperties(Object element) {
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
				
		PropertyDescriptor[] propertyDescriptors = new PropertyDescriptor[8];
		//read only text fields
		propertyDescriptors[0] = new PropertyDescriptor("EventName", "Event Name");
		propertyDescriptors[1] = new PropertyDescriptor("EventType", "Event Type");
		propertyDescriptors[2] = new PropertyDescriptor("BusinessLocation", "Business Location");
		//combo box fields
		propertyDescriptors[3] = new ComboBoxPropertyDescriptor("Action","Action",actions);
		propertyDescriptors[4] = new ComboBoxPropertyDescriptor("ReadPoint","Read Point",mdb.getReadPoints());
		propertyDescriptors[5] = new ComboBoxPropertyDescriptor("BusinessStep","Business Step",mdb.getBusinessSteps());
		propertyDescriptors[6] = new ComboBoxPropertyDescriptor("Disposition","Disposition",mdb.getDispositions());
		propertyDescriptors[7] = new ComboBoxPropertyDescriptor("TransactionType","Transaction Type",mdb.getTransactions());
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
		}else {
			return -1;
		}
	}
	
	@Override
	public void setPropertyValue(Object id, Object value) {
		EBProcImpl epi = (EBProcImpl)getElement();
		MainControl mc = MainControl.getMainControl();
		org.ow2.aspirerfid.commons.apdl.model.EBProc ebproc = 
			(org.ow2.aspirerfid.commons.apdl.model.EBProc) mc.getMapObject(epi.hashCode());
		MasterDataBuilder mdb = MasterDataBuilder.getInstance();
		mdb.setEBProc(ebproc);
		Integer index = (Integer)value;

		if(id.equals("Action")) {
			mdb.setAttribute("urn:epcglobal:epcis:mda:action", actions[index]);
		}else if(id.equals("ReadPoint")) {
			String[] readPoints = mdb.getReadPoints();
			mdb.setAttribute("urn:epcglobal:epcis:mda:read_point", readPoints[index]);
			String bizLocation = mdb.getBusinessLocation(readPoints[index]);
			mdb.setAttribute("urn:epcglobal:epcis:mda:business_location", bizLocation);
		}else if(id.equals("BusinessStep")) {
			String[] businessSteps = mdb.getBusinessSteps();
			mdb.setAttribute("urn:epcglobal:epcis:mda:business_step", businessSteps[index]);
		}else if(id.equals("Disposition")) {
			String[] dispositions = mdb.getDispositions();
			mdb.setAttribute("urn:epcglobal:epcis:mda:disposition", dispositions[index]);
		}else if(id.equals("TransactionType")) {
			String[] transactions = mdb.getTransactions();
			mdb.setAttribute("urn:epcglobal:epcis:mda:transaction_type", transactions[index]);
		}
		
		epi.setFake("fake");
		mc.saveObject();

		super.setPropertyValue(id, value);
	}
}
