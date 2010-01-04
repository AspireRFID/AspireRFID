/*
 * Copyright (C) 2008-2010, Aspire
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

package org.ow2.aspirerfid.programmableengine.epcisclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ow2.aspirerfid.programmableengine.model.AttributeType;
import org.ow2.aspirerfid.programmableengine.model.EPCISMasterDataBodyType;
import org.ow2.aspirerfid.programmableengine.model.ObjectFactory;
import org.ow2.aspirerfid.programmableengine.model.EPCISMasterDataDocumentType;
import org.ow2.aspirerfid.programmableengine.model.VocabularyElementListType;
import org.ow2.aspirerfid.programmableengine.model.VocabularyElementType;
import org.ow2.aspirerfid.programmableengine.model.VocabularyListType;
import org.ow2.aspirerfid.programmableengine.model.VocabularyType;
import org.w3c.dom.Element;



/**
 * This client provides access to the repository's Capture Operations Module
 * through the Master Data capture interface.
 * 
 * @author Nikos Kefalakis(nkef)
 */
public class MasterDataCaptureClient {

    private static final String PROPERTY_FILE = "/captureclient.properties";
    private static final String PROPERTY_CAPTURE_URL = "default.url";
    
	/**
	 * The logger.
	 */
	private static Log log = LogFactory.getLog(MasterDataCaptureClient.class);


    

    /**
     * The URL String at which the Capture Operations Module listens.
     */
    private String captureUrl;

    private Properties props = new Properties();

    /**
     * Constructs a new MasterDataCaptureClient which connects to the Capture Operations
     * Module listening at the default URL.
     */
    public MasterDataCaptureClient() {
        this(null);
    }

    /**
     * Constructs a new MasterDataCaptureClient which connects to the Capture Operations
     * Module listening at the given URL.
     * 
     * @param url
     *            The URL at which the capture service listens.
     */
    public MasterDataCaptureClient(final String url) {

        // set the URL
        if (url != null || url.equals("")) {
            captureUrl = url;
        } else {
        	log.error("EPCIS Client Capture EndPoint is Empty. Please set one");
        }
    }

    /**
     * Send the XML available from the given InputStream to the repository for
     * capturing.
     * 
     * @param xmlStream
     *            A stream providing an EPCISDocument which contains a list of
     *            events inside the EPCISBody element.
     * @return The HTTP response code from the repository.
     * @throws IOException
     *             If an error sending the document occurred.
     */
    public int capture(final InputStream xmlStream) throws IOException {
        return doPost(xmlStream, "text/xml");
    }

    /**
     * Send the given XML String to the repository for capturing.
     * 
     * @param eventXml
     *            The XML String consisting of an EPCISDocument which in turn
     *            contains a list of events inside the EPCISBody element.
     * @return The HTTP response code from the repository.
     * @throws IOException
     *             If an error sending the document occurred.
     */
    public int capture(final String eventXml) throws IOException {
        return doPost(eventXml, "text/xml");
    }

    /**
     * Send the given EPCISDocumentType to the repository for capturing.
     * 
     * @param epcisDoc
     *            The EPCISDocument containing a list of events inside the
     *            EPCISBody element.
     * @return The HTTP response code from the repository.
     * @throws IOException
     *             If an error sending the document occurred.
     * @throws JAXBException
     *             If an error serializing the given document into XML occurred.
     */
    public int capture(final EPCISMasterDataDocumentType epcisMasterDataDoc) throws IOException, JAXBException {
        StringWriter writer = new StringWriter();
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBContext context = JAXBContext.newInstance("org.fosstrak.epcis.model");
        JAXBElement<EPCISMasterDataDocumentType> item = objectFactory.createEPCISMasterDataDocument(epcisMasterDataDoc);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(item, writer);
        
        log.debug("Jaxb data: \n"+writer.toString());
        
        return capture(writer.toString());
    }
    
    
    
    /**
     *  (nkef)
     *  Alter, A vocabulary's Element URI.

     * @param vocabularyType
     * @param oldVocabularyElementURI
     * @param newVocabularyElementURI
     * @return true if the method Succeeds false otherwise
     */
    public boolean simpleMasterDataAlter(String vocabularyType,String oldVocabularyElementURI,String newVocabularyElementURI){
		try {

			VocabularyType vocabulary = new VocabularyType();
			VocabularyElementListType vocabularyElementList = new VocabularyElementListType();
			VocabularyListType vocabularyList = new VocabularyListType();

			vocabulary.setType(vocabularyType);

			VocabularyElementType vocabularyElement = new VocabularyElementType();

			vocabularyElement.setId(oldVocabularyElementURI+"#"+newVocabularyElementURI);
			vocabularyElement.getOtherAttributes().put(new QName("mode"), "2");

			vocabularyElementList.getVocabularyElement().add(vocabularyElement);

			vocabulary.setVocabularyElementList(vocabularyElementList);

			vocabularyList.getVocabulary().add(vocabulary);

			EPCISMasterDataDocumentType epcisMasterDataDoc = new EPCISMasterDataDocumentType();
			EPCISMasterDataBodyType epcisMasterDataBody = new EPCISMasterDataBodyType();

			epcisMasterDataBody.setVocabularyList(vocabularyList);
			epcisMasterDataDoc.setEPCISBody(epcisMasterDataBody);
			epcisMasterDataDoc.setSchemaVersion(new BigDecimal("1.0"));
			epcisMasterDataDoc.setCreationDate(getCurrentTime());

			int httpResponseCode = this.capture(epcisMasterDataDoc);
			if (httpResponseCode != 200) {
				log.debug("The Master Data could NOT be captured!\n");
				return false;
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	
    	return true;
    }
    
    
    /**
     *  (nkef)
     *  Insert, a vocabulary's Element .
     *  
     * @param vocabularyType
     * @param vocabularyElementURI
     * @return true if the method Succeeds false otherwise
     */
    public boolean simpleMasterDataInsert(String vocabularyType,String vocabularyElementURI){
		try {

			VocabularyType vocabulary = new VocabularyType();
			VocabularyElementListType vocabularyElementList = new VocabularyElementListType();
			VocabularyListType vocabularyList = new VocabularyListType();

			vocabulary.setType(vocabularyType);

			VocabularyElementType vocabularyElement = new VocabularyElementType();

			vocabularyElement.setId(vocabularyElementURI);
			vocabularyElement.getOtherAttributes().put(new QName("mode"), "1");

			vocabularyElementList.getVocabularyElement().add(vocabularyElement);

			vocabulary.setVocabularyElementList(vocabularyElementList);

			vocabularyList.getVocabulary().add(vocabulary);

			EPCISMasterDataDocumentType epcisMasterDataDoc = new EPCISMasterDataDocumentType();
			EPCISMasterDataBodyType epcisMasterDataBody = new EPCISMasterDataBodyType();

			epcisMasterDataBody.setVocabularyList(vocabularyList);
			epcisMasterDataDoc.setEPCISBody(epcisMasterDataBody);
			epcisMasterDataDoc.setSchemaVersion(new BigDecimal("1.0"));
			epcisMasterDataDoc.setCreationDate(getCurrentTime());

			int httpResponseCode = this.capture(epcisMasterDataDoc);
			if (httpResponseCode != 200) {
				log.debug("The Master Data could NOT be captured!\n");
				return false;
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	
    	return true;
    }
    
    
    /**
     *  (nkef)
     *  Delete a vocabulary's Element .
     *  When using delete  only the element with its attributes
     *  can be deleted.
     *  
     * @param vocabularyType
     * @param vocabularyElementURI
     * @return true if the method Succeeds false otherwise
     */
    public boolean simpleMasterDataSingleDelete(String vocabularyType,String vocabularyElementURI){
		try {

			VocabularyType vocabulary = new VocabularyType();
			VocabularyElementListType vocabularyElementList = new VocabularyElementListType();
			VocabularyListType vocabularyList = new VocabularyListType();

			vocabulary.setType(vocabularyType);

			VocabularyElementType vocabularyElement = new VocabularyElementType();

			vocabularyElement.setId(vocabularyElementURI);
			vocabularyElement.getOtherAttributes().put(new QName("mode"), "3");

			vocabularyElementList.getVocabularyElement().add(vocabularyElement);

			vocabulary.setVocabularyElementList(vocabularyElementList);

			vocabularyList.getVocabulary().add(vocabulary);

			EPCISMasterDataDocumentType epcisMasterDataDoc = new EPCISMasterDataDocumentType();
			EPCISMasterDataBodyType epcisMasterDataBody = new EPCISMasterDataBodyType();

			epcisMasterDataBody.setVocabularyList(vocabularyList);
			epcisMasterDataDoc.setEPCISBody(epcisMasterDataBody);
			epcisMasterDataDoc.setSchemaVersion(new BigDecimal("1.0"));
			epcisMasterDataDoc.setCreationDate(getCurrentTime());

			int httpResponseCode = this.capture(epcisMasterDataDoc);
			if (httpResponseCode != 200) {
				log.debug("The Master Data could NOT be captured!\n");
				return false;
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	
    	return true;
    }
    
    /**
     *  (nkef)
     *  Delete a vocabulary's Element with its direct or indirect descendants.
     *  The element with its attributes and with
     *  all its children elements and its children's attributes.
     *  
     * @param vocabularyType
     * @param vocabularyElementURI
     * @return true if the method Succeeds false otherwise
     */
    public boolean simpleMasterDataDeleteWithDescendants(String vocabularyType,String vocabularyElementURI){
		try {

			VocabularyType vocabulary = new VocabularyType();
			VocabularyElementListType vocabularyElementList = new VocabularyElementListType();
			VocabularyListType vocabularyList = new VocabularyListType();

			vocabulary.setType(vocabularyType);

			VocabularyElementType vocabularyElement = new VocabularyElementType();

			vocabularyElement.setId(vocabularyElementURI);
			vocabularyElement.getOtherAttributes().put(new QName("mode"), "4");

			vocabularyElementList.getVocabularyElement().add(vocabularyElement);

			vocabulary.setVocabularyElementList(vocabularyElementList);

			vocabularyList.getVocabulary().add(vocabulary);

			EPCISMasterDataDocumentType epcisMasterDataDoc = new EPCISMasterDataDocumentType();
			EPCISMasterDataBodyType epcisMasterDataBody = new EPCISMasterDataBodyType();

			epcisMasterDataBody.setVocabularyList(vocabularyList);
			epcisMasterDataDoc.setEPCISBody(epcisMasterDataBody);
			epcisMasterDataDoc.setSchemaVersion(new BigDecimal("1.0"));
			epcisMasterDataDoc.setCreationDate(getCurrentTime());

			int httpResponseCode = this.capture(epcisMasterDataDoc);
			if (httpResponseCode != 200) {
				log.debug("The Master Data could NOT be captured!\n");
				return false;
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	
    	return true;
    }
    
    /**
     *  (nkef)This Method Remains for Backwards compatibility Reasons.
     *  Insert, Update or Delete a vocabulary's Element .
     *  When using delete mode either only the element with its attributes
     *  can be deleted or the element with its attributes and with
     *  all its children elements and its children's attributes.
     *  when using insert mode if the Vocabulary element is not inserted
     *  yet it will be inserted. 
     *  
     * @param vocabularyType
     * @param vocabularyElementURI
     *  For using the alter URI mode (2) the Old URI with the new one should
     *  be given at the "vocabularyElementURI" parameter merged together
     *  with the "#" sign between them (e.g. "urn:epcglobal:old#urn:epcglobal:new").
     * @param mode
     *        1: insert((it can be anything except 2,3,4))
     *        2: alterURI
     *        3: singleDelete
     *        4: Delete element with it's direct or indirect descendants
     * @return true if the method Succeeds false otherwise
     */
    public boolean simpleMasterDataEdit(String vocabularyType,String vocabularyElementURI,String mode){
		try {

			VocabularyType vocabulary = new VocabularyType();
			VocabularyElementListType vocabularyElementList = new VocabularyElementListType();
			VocabularyListType vocabularyList = new VocabularyListType();

			vocabulary.setType(vocabularyType);

			VocabularyElementType vocabularyElement = new VocabularyElementType();

			vocabularyElement.setId(vocabularyElementURI);
			vocabularyElement.getOtherAttributes().put(new QName("mode"), mode);

			vocabularyElementList.getVocabularyElement().add(vocabularyElement);

			vocabulary.setVocabularyElementList(vocabularyElementList);

			vocabularyList.getVocabulary().add(vocabulary);

			EPCISMasterDataDocumentType epcisMasterDataDoc = new EPCISMasterDataDocumentType();
			EPCISMasterDataBodyType epcisMasterDataBody = new EPCISMasterDataBodyType();

			epcisMasterDataBody.setVocabularyList(vocabularyList);
			epcisMasterDataDoc.setEPCISBody(epcisMasterDataBody);
			epcisMasterDataDoc.setSchemaVersion(new BigDecimal("1.0"));
			epcisMasterDataDoc.setCreationDate(getCurrentTime());

			int httpResponseCode = this.capture(epcisMasterDataDoc);
			if (httpResponseCode != 200) {
				log.debug("The Master Data could NOT be captured!\n");
				return false;
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	
    	return true;
    }
    
    

    /**
     *  (nkef)
     *  Insert or Update a vocabulary's Element Attribute.
     *  If the Vocabulary is not inserted yet it will be inserted.
     *  The  vocabularyURI, vocabularyAttribute pair should be unique
     *  so if it already exists it will be changed to the vocabularyAttribute 
     *  entered or simply rewrite it. 
     * 
     * @param vocabularyType
     * @param vocabularyURI
     * @param vocabularyAttribute
     * @param vocabularyAttributeValue
     * @return true if the method Succeeds false otherwise
     */
    public boolean simpleMasterDataAndAttributeInsertOrAlter(String vocabularyType, String vocabularyURI, String vocabularyAttribute, String vocabularyAttributeValue){
		try {

			VocabularyType vocabulary = new VocabularyType();
			VocabularyElementListType vocabularyElementList = new VocabularyElementListType();
			VocabularyListType vocabularyList = new VocabularyListType();

			vocabulary.setType(vocabularyType);

			VocabularyElementType vocabularyElement = new VocabularyElementType();

			vocabularyElement.setId(vocabularyURI);
//			vocabularyElement.getOtherAttributes().put(new QName("mode"), "1");

			AttributeType attribute = new AttributeType();
			attribute.setId("urn:epcglobal:epcis:mda:"+vocabularyAttribute);
			
//			attribute.getOtherAttributes().put(new QName("mode"), mode);

			Object element = new Object();
						
			((Element)element).setNodeValue(vocabularyAttributeValue);
			attribute.getAny().add(((Element)element));

			vocabularyElement.getAttribute().add(attribute);

			vocabularyElementList.getVocabularyElement().add(vocabularyElement);

			vocabulary.setVocabularyElementList(vocabularyElementList);

			vocabularyList.getVocabulary().add(vocabulary);

			EPCISMasterDataDocumentType epcisMasterDataDoc = new EPCISMasterDataDocumentType();
			EPCISMasterDataBodyType epcisMasterDataBody = new EPCISMasterDataBodyType();

			epcisMasterDataBody.setVocabularyList(vocabularyList);
			epcisMasterDataDoc.setEPCISBody(epcisMasterDataBody);
			epcisMasterDataDoc.setSchemaVersion(new BigDecimal("1.0"));
			epcisMasterDataDoc.setCreationDate(getCurrentTime());

			
			int httpResponseCode = this.capture(epcisMasterDataDoc);
			if (httpResponseCode != 200) {
				log.debug("The Master Data could NOT be captured!\n");
				return false;
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	return true;
    }
    
    

    /**
     *  (nkef)
     *  Delete a vocabulary's Element Attribute.
     * 
     * @param vocabularyType
     * @param vocabularyURI
     * @param vocabularyAttribute
     * @param vocabularyAttributeValue
     * @return true if the method Succeeds false otherwise
     */
    public boolean simpleMasterDataAndAttributeDelete(String vocabularyType, String vocabularyURI, String vocabularyAttribute, String vocabularyAttributeValue,String mode){
		try {

			VocabularyType vocabulary = new VocabularyType();
			VocabularyElementListType vocabularyElementList = new VocabularyElementListType();
			VocabularyListType vocabularyList = new VocabularyListType();

			vocabulary.setType(vocabularyType);

			VocabularyElementType vocabularyElement = new VocabularyElementType();

			vocabularyElement.setId(vocabularyURI);

			AttributeType attribute = new AttributeType();
			attribute.setId("urn:epcglobal:epcis:mda:"+vocabularyAttribute);
			
			attribute.getOtherAttributes().put(new QName("mode"), "3");
			
			Object element = new Object();
			((Element)element).setNodeValue("null");
			attribute.getAny().add(((Element)element));
			
			vocabularyElement.getAttribute().add(attribute);

			vocabularyElementList.getVocabularyElement().add(vocabularyElement);

			vocabulary.setVocabularyElementList(vocabularyElementList);

			vocabularyList.getVocabulary().add(vocabulary);

			EPCISMasterDataDocumentType epcisMasterDataDoc = new EPCISMasterDataDocumentType();
			EPCISMasterDataBodyType epcisMasterDataBody = new EPCISMasterDataBodyType();

			epcisMasterDataBody.setVocabularyList(vocabularyList);
			epcisMasterDataDoc.setEPCISBody(epcisMasterDataBody);
			epcisMasterDataDoc.setSchemaVersion(new BigDecimal("1.0"));
			epcisMasterDataDoc.setCreationDate(getCurrentTime());

			
			int httpResponseCode = this.capture(epcisMasterDataDoc);
			if (httpResponseCode != 200) {
				log.debug("The Master Data could NOT be captured!\n");
				return false;
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	return true;
    }
    
    
    
    

    /**
     *  (nkef)This Method Remains for Backwards compatibility Reasons.
     *  Insert, Update, Delete a vocabulary's Element Attribute.
     *  If the Vocabulary is not inserted yet it will be inserted.
     *  The  vocabularyURI, vocabularyAttribute pair should be unique
     *  so if it already exists it will be changed to the vocabularyAttribute 
     *  entered or simply rewrite it. 
     * 
     * @param vocabularyType
     * @param vocabularyURI
     * @param vocabularyAttribute
     * @param vocabularyAttributeValue
     * @param mode
     *      1: Insert (optional, is kept for backwards compatibility (it can be anything except 3))
     *      2: Alter Attribute Value (optional, is kept for backwards compatibility(it can be anything except 3))
     *      3: Delete Attribute (required)
     * @return true if the method Succeeds false otherwise
     */
    public boolean simpleMasterDataAndAttributeEdit(String vocabularyType, String vocabularyURI, String vocabularyAttribute, String vocabularyAttributeValue,String mode){
		try {

			VocabularyType vocabulary = new VocabularyType();
			VocabularyElementListType vocabularyElementList = new VocabularyElementListType();
			VocabularyListType vocabularyList = new VocabularyListType();

			vocabulary.setType(vocabularyType);

			VocabularyElementType vocabularyElement = new VocabularyElementType();

			vocabularyElement.setId(vocabularyURI);
//			vocabularyElement.getOtherAttributes().put(new QName("mode"), "1");

			AttributeType attribute = new AttributeType();
			attribute.setId("urn:epcglobal:epcis:mda:"+vocabularyAttribute);
			
			attribute.getOtherAttributes().put(new QName("mode"), mode);
			
//			attribute.getOtherAttributes().put(new QName("mode"), alterAttrValue?"true":"false");
//			attribute.getOtherAttributes().put(new QName("delete"), deleteAttribute?"true":"false");
			
			Object element = new Object();
			
			if(mode.equals("3")){
				((Element)element).setNodeValue("null");
				attribute.getAny().add(((Element)element));
			}else{
				((Element)element).setNodeValue(vocabularyAttributeValue);
				attribute.getAny().add(((Element)element));
			}
			
			
			vocabularyElement.getAttribute().add(attribute);

			vocabularyElementList.getVocabularyElement().add(vocabularyElement);

			vocabulary.setVocabularyElementList(vocabularyElementList);

			vocabularyList.getVocabulary().add(vocabulary);

			EPCISMasterDataDocumentType epcisMasterDataDoc = new EPCISMasterDataDocumentType();
			EPCISMasterDataBodyType epcisMasterDataBody = new EPCISMasterDataBodyType();

			epcisMasterDataBody.setVocabularyList(vocabularyList);
			epcisMasterDataDoc.setEPCISBody(epcisMasterDataBody);
			epcisMasterDataDoc.setSchemaVersion(new BigDecimal("1.0"));
			epcisMasterDataDoc.setCreationDate(getCurrentTime());

			
			int httpResponseCode = this.capture(epcisMasterDataDoc);
			if (httpResponseCode != 200) {
				log.debug("The Master Data could NOT be captured!\n");
				return false;
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	return true;
    }
    
    
    
    
    
    
    private XMLGregorianCalendar getCurrentTime(){
		// get the current time and set the eventTime
		XMLGregorianCalendar now = null;
		try {
		    DatatypeFactory dataFactory = DatatypeFactory.newInstance();
		    now = dataFactory.newXMLGregorianCalendar(new GregorianCalendar());
		    log.debug("Event Time:"+now.getHour()+":"+now.getMinute()+":"+":"+now.getSecond()+"\n");
		} catch (DatatypeConfigurationException e) {
		    e.printStackTrace();
		}

		return now;
		
    }

    /**
     * Invokes the dbReset operation in the Capture Module deleting all event
     * data in the EPCIS database. This operation is only allowed if the
     * corresponding property is set in the repository's configuration.
     * 
     * @return The response from the capture module.
     * @throws IOException
     *             If a communication error occurred.
     */
    public int dbReset() throws IOException {
        String formParam = "dbReset=true";
        return doPost(formParam, "application/x-www-form-urlencoded");

    }

    /**
     * Opens a connection to the capture module.
     * 
     * @param contentType
     *            The HTTP content-type, e.g., <code>text/xml</code>
     * @return The HTTP connection object.
     * @throws IOException
     */
    private HttpURLConnection getConnection(final String contentType) throws IOException {
        URL serviceUrl = new URL(captureUrl);
        HttpURLConnection connection = (HttpURLConnection) serviceUrl.openConnection();
        connection.setRequestProperty("content-type", contentType);
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        return connection;
    }

    /**
     * Send data to the repository's capture operation using HTTP POST. The data
     * will be sent using the given content-type.
     * 
     * @param data
     *            The data to send.
     * @return The HTTP response message
     * @throws IOException
     *             If an error on the HTTP layer occurred.
     */
    private int doPost(final String data, final String contentType) throws IOException {
        HttpURLConnection connection = getConnection(contentType);
        // write the data
        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
        wr.write(data);
        wr.flush();
        wr.close();

        return connection.getResponseCode();
    }

    /**
     * Send data to the repository's capture operation using HTTP POST. The data
     * will be sent using the given content-type.
     * 
     * @param data
     *            The data to send.
     * @return The HTTP response message from the repository.
     * @throws IOException
     *             If an error on the HTTP layer occurred.
     */
    private int doPost(final InputStream data, final String contentType) throws IOException {
        HttpURLConnection connection = getConnection(contentType);
        // read from input and write to output
        OutputStream os = connection.getOutputStream();
        int b;
        while ((b = data.read()) != -1) {
            os.write(b);
        }
        os.flush();
        os.close();

        return connection.getResponseCode();
    }

    /**
     * @return The URL String at which the Capture Operations Module listens.
     */
    public String getCaptureUrl() {
        return captureUrl;
    }

    /**
     * @param url
     *            The new URL String to which this client should connect.
     */
    public void setCaptureUrl(final String url) {
        this.captureUrl = url;
    }
}
