package org.ow2.aspirerfid.ide.bpwme.test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.ow2.aspirerfid.commons.epcis.model.*;

public class TestDocType {
	public static void main(String args[]) {
		ObjectFactory factory = new ObjectFactory();
		
		EPCISMasterDataDocumentType doc = new EPCISMasterDataDocumentType();		
		EPCISMasterDataBodyType body = new EPCISMasterDataBodyType();
		doc.setEPCISBody(body);
		VocabularyListType vocabularyList = new VocabularyListType();
		body.setVocabularyList(vocabularyList);
		VocabularyType vocabulary = new VocabularyType();
		VocabularyElementListType vocabularyElementList = new VocabularyElementListType();
		vocabulary.setVocabularyElementList(vocabularyElementList);		
		vocabularyList.getVocabulary().add(vocabulary);
		VocabularyElementType vocabularyElement = new VocabularyElementType();
		vocabularyElement.setId("testVocabularyElement");
		AttributeType attri = factory.createAttributeType();
		attri.setId("what a fake id");
		
		attri.getOtherAttributes().put(new QName("value"), "fakevalue");
		
		vocabularyElement.getAttribute().add(attri);		
		vocabularyElementList.getVocabularyElement().add(vocabularyElement);
		
		
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(EPCISMasterDataDocumentType.class);
			JAXBElement<EPCISMasterDataDocumentType> item = objectFactory.createEPCISMasterDataDocument(doc);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(item, System.out);
		}
		catch (JAXBException e) {
			e.printStackTrace();
		}
		//System.out.println(vocabularyList.getVocabulary());
	}
}
