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

package org.ow2.aspirerfid.ide.bpwme.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.ow2.aspirerfid.commons.apdl.model.ApdlDataField;
import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.ow2.aspirerfid.commons.apdl.model.ObjectFactory;
import org.ow2.aspirerfid.commons.apdl.utils.DeserializerUtil;
import org.ow2.aspirerfid.commons.apdl.utils.Serializer;
import org.ow2.aspirerfid.commons.epcis.model.AttributeType;
import org.ow2.aspirerfid.commons.epcis.model.EPCISMasterDataBodyType;
import org.ow2.aspirerfid.commons.epcis.model.EPCISMasterDataDocumentType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementListType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyListType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyType;
import org.ow2.aspirerfid.ide.bpwme.CLCBProc;
import org.ow2.aspirerfid.ide.bpwme.EBProc;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.OLCBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.WorkflowMapEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorPlugin;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.bpwme.diagram.simpleditor.PathEditorInput;
import org.ow2.aspirerfid.ide.bpwme.diagram.simpleditor.SimpleEditor;
import org.ow2.aspirerfid.ide.bpwme.ecspec.model.ExtraProperty;
import org.ow2.aspirerfid.ide.bpwme.ecspec.utils.ECSpecBuilder;
import org.ow2.aspirerfid.ide.bpwme.ecspec.utils.LRSpecBuilder;
import org.ow2.aspirerfid.ide.bpwme.ecspec.views.ECSpecEditor;
import org.ow2.aspirerfid.ide.bpwme.impl.OLCBProcImpl;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataBuilder;
import org.ow2.aspirerfid.ide.bpwme.master.views.MasterEditor;

/**
 * Basic hack class do the dirty work.
 * Singleton pattern to return the instance anywhere.
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */

public class MainControl {
	/**
	 * FileAction is to record what is the most recent
	 * action of the user, open a file or create a new file.
	 * If it's open a file, should do the model binding work.
	 * If it's create a new file, don't do these things.
	 */
	public enum FileAction{
		NewAction, OpenAction, Restart, OpenFromAction
	}
	
	/**
	 * Four event types for user to select.
	 * 
	 */
	public enum EventType{
		OBJECT_EVENT,AGGREGATION_EVENT,QUANTITY_EVENT,TRANSACTION_EVENT;
		
		//from String to enum
		public static EventType createEvent(String name) {
			if(name.equals("ObjectEvent")) {
				return EventType.OBJECT_EVENT;
			}else if(name.equals("AggregationEvent")) {
				return EventType.AGGREGATION_EVENT;
			}else if(name.equals("QuantityEvent")) {
				return EventType.QUANTITY_EVENT;
			}else if(name.equals("TransactionEvent")) {
				return EventType.TRANSACTION_EVENT;
			}
			return null;
		}
		
		//from enum to String
		@Override
		public String toString() {
			String name = name();
			if(name.equals("OBJECT_EVENT")) {
				return "ObjectEvent";
			}else if(name.equals("AGGREGATION_EVENT")) {
				return "AggregationEvent";
			}else if(name.equals("QUANTITY_EVENT")) {
				return "QuantityEvent";
			}else if(name.equals("TRANSACTION_EVENT")) {
				return "TransactionEvent";
			}else {
				//not suppose to be here
				return null;
			}
		}
	}
	
	private static MainControl mainControl;
	private static String fileSeparator =
		System.getProperty("file.separator");
	private static String userHome = System.getProperty("user.home");
	//the folder that apdl xml file should reside in
//	private static final String P_PE_ApdlFilesPath =
//		userHome+fileSeparator+"AspireRFID"+fileSeparator+"IDE"+fileSeparator
//		+"APDLs"+fileSeparator;
	//apdl file uri
	private URI apdlURI;
	private URI bpwmeURI;
	//assistant file path. assistant file now is to record
	//the event type of each ebproc
	//private String assistantPath; 
	private FileAction fa;

	private OLCBProc olcbProc;
	//private EBProc selectedEbProc;
//	public LRSpecBuilder lrsb;
//	public ECSpecBuilder ecsb;
	//private SimpleEditor simpleEditor;
	public ObjectFactory objectFactory;
	private HashMap<Integer, Object> objectMap;
	//public HashMap<String, EventType> ebprocMap;
	public ECSpecEditor ecEditor;
	public WorkflowMapEditPart wme;
	public OLCBProcEditPart olcbep;
	//public ArrayList<ApdlDataField> candidateLRList;
	
	public Vector<ExtraProperty> extraLLRPProperty;
	public Vector<ExtraProperty> extraRPProperty;
	public Vector<ExtraProperty> extraHALProperty;

	public MainControl() throws Exception {
		//create apdl file directory if it's not exist
		IPreferenceStore store = BpwmeDiagramEditorPlugin.getInstance().getPreferenceStore();
		File directory = new File(store.getString(PreferenceConstants.P_APDL_DIR));
		if(!directory.exists()) {
			directory.mkdirs();
		}
		
		String apdlFileString = store.getString(PreferenceConstants.P_APDL_FILE);
		if(!apdlFileString.equals("")) {
			//this is a restart
			fa = FileAction.Restart;
			setAPDLURI(apdlFileString);
		}
		
		
		//initialization for some objects
		objectFactory = new ObjectFactory();
		//candidateLRList = new ArrayList<ApdlDataField>();
		objectMap = new HashMap<Integer, Object>();
		//ebprocMap = new HashMap<String, EventType>();
		extraLLRPProperty = new Vector<ExtraProperty>();
		extraRPProperty = new Vector<ExtraProperty>();
		extraHALProperty = new Vector<ExtraProperty>();

		extraLLRPProperty.add(new ExtraProperty("ConnectionPointAddress",ExtraProperty.LLRP_TYPE));
		extraLLRPProperty.add(new ExtraProperty("ConnectionPointPort",ExtraProperty.LLRP_TYPE));
		extraLLRPProperty.add(new ExtraProperty("PhysicalReaderSource",ExtraProperty.LLRP_TYPE));
		extraLLRPProperty.add(new ExtraProperty("RoSpecID",ExtraProperty.LLRP_TYPE));

		extraRPProperty.add(new ExtraProperty("ConnectionPointAddress",ExtraProperty.RP_TYPE));
		extraRPProperty.add(new ExtraProperty("ConnectionPointPort",ExtraProperty.RP_TYPE));
		extraRPProperty.add(new ExtraProperty("PhysicalReaderSource",ExtraProperty.RP_TYPE));
		extraRPProperty.add(new ExtraProperty("RoSpecID",ExtraProperty.RP_TYPE));
	}

	
	public HashMap<Integer, Object> getObjectMap() {
		return objectMap;
	}

	public void setBpwmeURI(URI bpwmeURI) {
		this.bpwmeURI = bpwmeURI;
	}
	
	public URI getBpwmeURI() {
		return bpwmeURI;
	}
	
	/**
	 * From diagram URI get Apdl URI.
	 * Also create the assistant file under the same directory.
	 * @param diagramFileURI
	 */
	public void setAPDLFileName(URI diagramFileURI) {
		IPreferenceStore store = BpwmeDiagramEditorPlugin.getInstance().getPreferenceStore();
		String dot = "\\.";
		String[] names = diagramFileURI.lastSegment().split(dot);
		if(names.length == 2) {
			apdlURI = URI.createFileURI(store.getString(PreferenceConstants.P_APDL_DIR)+names[0]+".xml");
			store.setValue(PreferenceConstants.P_APDL_FILE, apdlURI.toFileString());
			File f = new File(apdlURI.toFileString());		
			if(!f.exists()) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			IPath location= new Path(f.getAbsolutePath());
			PathEditorInput input= new PathEditorInput(location);
			SimpleEditor.setEditorInput(input);
			
			//URI apdlAssistant = URI.createFileURI(P_PE_ApdlFilesPath+names[0]+".assistant.xml");
			//assistantPath = apdlAssistant.toFileString();
			//System.out.println("assistant:" + assistantPath);
		} else {
			System.err.println("Error in MainControl.getFileName");
		}
	}
	
	/**
	 * Set the apdl uri with the given file name
	 * @param apdlFileName
	 */
	public void setAPDLURI(String apdlFileName) {
		apdlURI = URI.createFileURI(apdlFileName);
		
	}
	
	/**
	 * Rebuild the in memory models from the existing apdl file.
	 * Called when open an existing diagram
	 */
	public void rebuild() {
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(apdlURI.toFileString());
			olcbProc = deserializeOLCBProc(inputStream);
			//System.out.println(mc.olcbProc);
			inputStream.close();
			
			MasterDataBuilder mdb = MasterDataBuilder.getInstance();
			mdb.setOLCBProc(olcbProc);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO it does not work when I call the same method
	//Deserializer.deserializeOLCBProc() in aspirerfid commons
	private OLCBProc deserializeOLCBProc(InputStream inputStream) {
		OLCBProc openLoopCBProc = null;
		try {
			String JAXB_CONTEXT = "org.ow2.aspirerfid.commons.apdl.model";			
			// initialize jaxb context and unmarshaller
			JAXBContext context = JAXBContext.newInstance(JAXB_CONTEXT);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			
			openLoopCBProc = ((JAXBElement<OLCBProc>)unmarshaller.unmarshal(inputStream)).getValue();						
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return openLoopCBProc;
	}
	
	public OLCBProc createOLCBProc() {
		//1. create object
		olcbProc = objectFactory.createOLCBProc();
		//TODO 1.2 create related master data documents
		
		//2. save it to file
		saveObject();
		return olcbProc;
	}
	
	public org.ow2.aspirerfid.commons.apdl.model.CLCBProc createCLCBProc() {
		org.ow2.aspirerfid.commons.apdl.model.CLCBProc clcbProc= objectFactory.createCLCBProc();
		olcbProc.getCLCBProc().add(clcbProc);
		saveObject();
		return clcbProc;
	}

	public org.ow2.aspirerfid.commons.apdl.model.EBProc createEBProc(org.ow2.aspirerfid.commons.apdl.model.CLCBProc clcb) {
		org.ow2.aspirerfid.commons.apdl.model.EBProc ebproc = objectFactory.createEBProc();
		clcb.getEBProc().add(ebproc);
		ebproc.setApdlDataFields(objectFactory.createApdlDataFields());
		saveObject();
		return ebproc;
	}
	
//	public static EPCISMasterDataDocumentType getEPCISMasterDataDocument(org.ow2.aspirerfid.commons.apdl.model.EBProc ebproc) {
//		EPCISMasterDataDocumentType doc;
//		boolean newDoc = true;
//		for(ApdlDataField adf: ebproc.getApdlDataFields().getApdlDataField()) {
//			if((doc = adf.getEPCISMasterDataDocument()) != null) {
//				newDoc = false;
//				return doc;
//			}
//		}
//		if(newDoc) {
//			ApdlDataField adf = new ApdlDataField();
//			adf.setType("EPCISMasterDataDocument");
//			adf.setName("RecievingMasterData");
//			doc = new EPCISMasterDataDocumentType();
//			adf.setEPCISMasterDataDocument(doc);
//			ebproc.getApdlDataFields().getApdlDataField().add(adf);
//			return doc;			
//		}
//		return null;
//	}
//	
//	public static void setVocabularyElementAttribute(VocabularyElementType vocabularyElement, 
//			String name, String value) {
//		//if the attribute exists, update it
//		AttributeType attr = getVocabularyElementAttribute(vocabularyElement, name);
//		//else add a new one
//		if(attr == null) {
//			attr = new AttributeType();
//			attr.setId(name);
//			vocabularyElement.getAttribute().add(attr);
//		}
//		attr.getOtherAttributes().put(new QName("value"), value);
//	}
//	
//	public static AttributeType getVocabularyElementAttribute(VocabularyElementType vocabularyElement, String name) {
//		for(AttributeType attr : vocabularyElement.getAttribute()) {
//			if(attr.getId().equals(name)) {
//				return attr;
//			}
//		}
//		return null;
//	}
//	
//	public static void setVocabularyElementID(VocabularyElementType vocabularyElement, String id) {
//		vocabularyElement.setId(id);
//	}
//	
//	//get or create vocabulary element in the ebproc level
//	public static VocabularyElementType getVocabularyElement(EPCISMasterDataDocumentType doc) {
//		EPCISMasterDataBodyType body;
//		VocabularyListType vocabularyList;
//		VocabularyType vocabulary;
//		VocabularyElementListType vocabularyElementList;
//		VocabularyElementType vocabularyElement;
//		//chain effect
//		if((body = doc.getEPCISBody()) == null) {
//			body = new EPCISMasterDataBodyType();
//			doc.setEPCISBody(body);		
//		}
//		
//		if((vocabularyList = body.getVocabularyList()) == null) {
//			vocabularyList = new VocabularyListType();
//			body.setVocabularyList(vocabularyList);
//		}
//		
//		if(vocabularyList.getVocabulary().size() == 0) {
//			vocabulary = new VocabularyType();
//			vocabulary.setType("urn:epcglobal:epcis:vtype:BusinessTransaction");
//			vocabularyList.getVocabulary().add(vocabulary);
//		}else {
//			vocabulary = vocabularyList.getVocabulary().get(0);
//		}
//
//		if((vocabularyElementList = vocabulary.getVocabularyElementList()) == null) {
//			vocabularyElementList = new VocabularyElementListType();
//			vocabulary.setVocabularyElementList(vocabularyElementList);			
//		}
//		
//		if(vocabularyElementList.getVocabularyElement().size() == 0) {
//			vocabularyElement = new VocabularyElementType();			
//			vocabularyElementList.getVocabularyElement().add(vocabularyElement);
//		}else {
//			vocabularyElement = vocabularyElementList.getVocabularyElement().get(0);
//		}
//		
//		return vocabularyElement;
//	}

	public boolean removeCLCBProc(org.ow2.aspirerfid.commons.apdl.model.CLCBProc clcb) {
		boolean result = olcbProc.getCLCBProc().remove(clcb);
		saveObject();
		return result;
	}

	/**
	 * Use a new thread to serialize the model.
	 * Let the program work in parallel.
	 */
	public void saveObject() {
		new WriteToFile(olcbProc).start();
	}

	/**
	 * Map the diagram to the logical model (both in memory).
	 * This method should be called only after the diagram is initialized.
	 * We call it in "OpenDiagram" after the diagram is opened by an editor.
	 */
	public void mapModels() {
		//1. from diagram get the logical model
		OLCBProcImpl opi = (OLCBProcImpl)((View)olcbep.getModel()).getElement();
		//2. from apdl file get the real model
		OLCBProc op = olcbProc;
		OLCBProcAssistant oa = new OLCBProcAssistant(op);
		//3. do the model mapping by id
		objectMap.clear();
		if(!opi.getId().equals(op.getId())) {
			System.err.println("Wrong Mapping in MC.mapModels()");
			return;
		}
		addMap(opi.hashCode(), op);
		for(CLCBProc cpi : opi.getCLCBProc()) {
			org.ow2.aspirerfid.commons.apdl.model.CLCBProc cp;
			if((cp = oa.getCLCB(cpi.getId())) != null) {
				addMap(cpi.hashCode(), cp);
				for(EBProc ebi : cpi.getEBProc()) {
					org.ow2.aspirerfid.commons.apdl.model.EBProc ep;
					if((ep = oa.getEBProc(cp, ebi.getId())) != null) {
						addMap(ebi.hashCode(), ep);
					}else {
						System.err.println("Wrong Mapping in MC.mapModels()");
						return;
					}
				}
			}else {
				System.err.println("Wrong Mapping");
				return;
			}
		}
	}
	
	
	public OLCBProc getOLCBProc() {
		return olcbProc;
	}

	public void setOLCBProc(OLCBProc olcbProc) {
		this.olcbProc = olcbProc;
	}
	
	public void addMap(int hashCode, Object object) {
		objectMap.put(hashCode, object);
	}
	public void delMap(int hashCode) {
		objectMap.remove(hashCode);
	}
	public Object getMapObject(int hashCode) {
		return objectMap.get(hashCode);
	}
	
	/**
	 * Get the parent for the given ebproc
	 * An overall search is involved
	 * @param ebProc
	 * @return
	 */
	public org.ow2.aspirerfid.commons.apdl.model.CLCBProc getParent(org.ow2.aspirerfid.commons.apdl.model.EBProc ebProc) {
		for(org.ow2.aspirerfid.commons.apdl.model.CLCBProc clcb : olcbProc.getCLCBProc()) {
			if(clcb.getEBProc().contains(ebProc)) {
				return clcb;
			}
		}
		return null;
	}

	/*
	public void getSimpleEditor() {
		SimpleEditor.getEditor();
	}*/

	public static MainControl getMainControl() {
		if(mainControl == null) {
			try {
				mainControl = new MainControl();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mainControl;
	}

	public URI getApdlURI() {
		return apdlURI;
	}
	
//	public void saveAssistantFile() {
//		try {
//			FileOutputStream fout = new FileOutputStream(assistantPath);
//			ObjectOutputStream oos = new ObjectOutputStream(fout);
//			oos.writeObject(ebprocMap);
//			oos.close();
//		}
//		catch (Exception e) { e.printStackTrace();
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public void loadAssistantFile() {
//		try {
//			FileInputStream fin = new FileInputStream(assistantPath);
//			ObjectInputStream ois = new ObjectInputStream(fin);
//			ebprocMap = (HashMap<String, EventType>) ois.readObject();
//			ois.close();
//		}
//		catch (Exception e) {
//			e.printStackTrace(); 		
//		}
//	}
	
	public void setFileAction(FileAction fa) {
		this.fa = fa;
	}
	
	public FileAction getFileAction() {
		return fa;
	}
	
	
}

/**
 * A new thread to write the OLCB Process to file.
 * It's needed because it may take some time.
 * @author Yongming Luo
 *
 */
class WriteToFile extends Thread {
	OLCBProc olcbProc;
	public WriteToFile(OLCBProc olcbProc) {
		this.olcbProc = olcbProc;
	}
	@Override
	public void run() {
		super.run();
		FileWriter fw;
		try {
			MainControl mc = MainControl.getMainControl();
			fw = new FileWriter(mc.getApdlURI().toFileString());
			Serializer.serializeOLCBProc(olcbProc, fw);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
