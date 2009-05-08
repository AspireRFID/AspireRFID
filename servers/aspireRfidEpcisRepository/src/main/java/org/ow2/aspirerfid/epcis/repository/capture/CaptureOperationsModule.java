/*
 * Copyright � 2008-2010, Aspire 
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
package org.ow2.aspirerfid.epcis.repository.capture;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;



import org.fosstrak.epcis.utils.TimeParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.ow2.aspirerfid.epcis.repository.EpcisConstants;
import org.ow2.aspirerfid.epcis.repository.InvalidFormatException;
import org.ow2.aspirerfid.epcis.repository.model.Action;
import org.ow2.aspirerfid.epcis.repository.model.AggregationEvent;
import org.ow2.aspirerfid.epcis.repository.model.BaseEvent;
import org.ow2.aspirerfid.epcis.repository.model.BusinessLocationAttrId;
import org.ow2.aspirerfid.epcis.repository.model.BusinessLocationId;
import org.ow2.aspirerfid.epcis.repository.model.BusinessStepAttrId;
import org.ow2.aspirerfid.epcis.repository.model.BusinessStepId;
import org.ow2.aspirerfid.epcis.repository.model.BusinessTransaction;
import org.ow2.aspirerfid.epcis.repository.model.BusinessTransactionAttrId;
import org.ow2.aspirerfid.epcis.repository.model.BusinessTransactionId;
import org.ow2.aspirerfid.epcis.repository.model.BusinessTransactionTypeAttrId;
import org.ow2.aspirerfid.epcis.repository.model.BusinessTransactionTypeId;
import org.ow2.aspirerfid.epcis.repository.model.DispositionAttrId;
import org.ow2.aspirerfid.epcis.repository.model.DispositionId;
import org.ow2.aspirerfid.epcis.repository.model.EPCClass;
import org.ow2.aspirerfid.epcis.repository.model.EPCClassAttrId;
import org.ow2.aspirerfid.epcis.repository.model.EventFieldExtension;
import org.ow2.aspirerfid.epcis.repository.model.ObjectEvent;
import org.ow2.aspirerfid.epcis.repository.model.QuantityEvent;
import org.ow2.aspirerfid.epcis.repository.model.ReadPointAttrId;
import org.ow2.aspirerfid.epcis.repository.model.ReadPointId;
import org.ow2.aspirerfid.epcis.repository.model.TransactionEvent;
import org.ow2.aspirerfid.epcis.repository.model.VocabularyAttrCiD;
import org.ow2.aspirerfid.epcis.repository.model.VocabularyAttributeElement;
import org.ow2.aspirerfid.epcis.repository.model.VocabularyElement;
import org.ow2.aspirerfid.epcis.repository.query.SimpleEventQueryDTO.Operation;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * CaptureOperationsModule implements the core capture operations. Converts XML
 * events delivered by HTTP POST into SQL and inserts them into the database.
 * <p>
 * TODO: the parsing of the xml inputstream should be done in the
 * CaptureOperationsServlet; this class should implement EpcisCaptureInterface
 * such that CaptureOperationsServlet can call its capture method and provide it
 * with the parsed events.
 * 
 * @author David Gubler
 * @author Alain Remund
 * @author Marco Steybe
 * @author Nikos Kefalakis (nkef)
 */
public class CaptureOperationsModule {

    private static final Log LOG = LogFactory.getLog(CaptureOperationsModule.class);

    private static final Map<String, Class<?>> vocClassMap = new HashMap<String, Class<?>>();

    static {
        vocClassMap.put(EpcisConstants.BUSINESS_LOCATION_ID, BusinessLocationId.class);
        vocClassMap.put(EpcisConstants.BUSINESS_STEP_ID, BusinessStepId.class);
        vocClassMap.put(EpcisConstants.BUSINESS_TRANSACTION_ID, BusinessTransactionId.class);
        vocClassMap.put(EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID, BusinessTransactionTypeId.class);
        vocClassMap.put(EpcisConstants.DISPOSITION_ID, DispositionId.class);
        vocClassMap.put(EpcisConstants.EPC_CLASS_ID, EPCClass.class);
        vocClassMap.put(EpcisConstants.READ_POINT_ID, ReadPointId.class);
    }
    
    //(nkef)
    private static final Map<String, Class<?>> vocAttributeClassMap = new HashMap<String, Class<?>>();
    private static final Map<String, String> vocAttributeTablesMap = new HashMap<String, String>();
    private static Map<String, String> vocabularyTablenameMap = new HashMap<String, String>();
    
    static {
    	vocAttributeClassMap.put(EpcisConstants.BUSINESS_LOCATION_ID, BusinessLocationAttrId.class);
    	vocAttributeClassMap.put(EpcisConstants.BUSINESS_STEP_ID, BusinessStepAttrId.class);
    	vocAttributeClassMap.put(EpcisConstants.BUSINESS_TRANSACTION_ID, BusinessTransactionAttrId.class);
    	vocAttributeClassMap.put(EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID, BusinessTransactionTypeAttrId.class);
    	vocAttributeClassMap.put(EpcisConstants.DISPOSITION_ID, DispositionAttrId.class);
    	vocAttributeClassMap.put(EpcisConstants.EPC_CLASS_ID, EPCClassAttrId.class);
    	vocAttributeClassMap.put(EpcisConstants.READ_POINT_ID, ReadPointAttrId.class);

    	vocAttributeTablesMap.put(EpcisConstants.BUSINESS_LOCATION_ID, "voc_BizLoc_attr");
    	vocAttributeTablesMap.put(EpcisConstants.BUSINESS_STEP_ID, "voc_BizStep_attr");
    	vocAttributeTablesMap.put(EpcisConstants.BUSINESS_TRANSACTION_ID, "voc_BizTrans_attr");
    	vocAttributeTablesMap.put(EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID, "voc_BizTransType_attr");
    	vocAttributeTablesMap.put(EpcisConstants.DISPOSITION_ID, "voc_Disposition_attr");
    	vocAttributeTablesMap.put(EpcisConstants.EPC_CLASS_ID, "voc_EPCClass_attr");
    	vocAttributeTablesMap.put(EpcisConstants.READ_POINT_ID, "voc_ReadPoint_attr");
    
        vocabularyTablenameMap.put(EpcisConstants.BUSINESS_STEP_ID, "voc_BizStep");
        vocabularyTablenameMap.put(EpcisConstants.BUSINESS_LOCATION_ID, "voc_BizLoc");
        vocabularyTablenameMap.put(EpcisConstants.BUSINESS_TRANSACTION_ID, "voc_BizTrans");
        vocabularyTablenameMap.put(EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID, "voc_BizTransType");
        vocabularyTablenameMap.put(EpcisConstants.DISPOSITION_ID, "voc_Disposition");
        vocabularyTablenameMap.put(EpcisConstants.EPC_CLASS_ID, "voc_EPCClass");
        vocabularyTablenameMap.put(EpcisConstants.READ_POINT_ID, "voc_ReadPoint");
    
    
    }
    
   
    

    /**
     * The XSD schema which validates the incoming messages.
     */
    private Schema schema;
    
    /**
     * The XSD schema which validates the MasterData incoming messages.(nkef)
     */
    private Schema masterDataSchema;

	/**
     * Whether we should insert new vocabulary or throw an error message.
     */
    private boolean insertMissingVoc = true;

    /**
     * Whether the dbReset operation is allowed or not.
     */
    private boolean dbResetAllowed = false;

    /**
     * The name of the SQL script used to clean and refill the database with
     * test data.
     */
    private File dbResetScript = null;

    /**
     * Interface to the database.
     */
    private SessionFactory sessionFactory;

    /**
     * Initializes the EPCIS schema used for validating incoming capture
     * requests. Loads the WSDL and XSD files from the classpath (the schema is
     * bundled with epcis-commons.jar).
     * 
     * @return An instantiated schema validation object.
     */
    private Schema initEpcisSchema(String xsdFile) {
        InputStream is = this.getClass().getResourceAsStream(xsdFile);
        if (is != null) {
            try {
                SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Source schemaSrc = new StreamSource(is);
                schemaSrc.setSystemId(CaptureOperationsServlet.class.getResource(xsdFile).toString());
                Schema schema = schemaFactory.newSchema(schemaSrc);
                LOG.debug("EPCIS schema file initialized and loaded successfully");
                return schema;
            } catch (Exception e) {
                LOG.warn("Unable to load or parse the EPCIS schema", e);
            }
        } else {
            LOG.error("Unable to load the EPCIS schema file from classpath: cannot find resource " + xsdFile);
        }
        LOG.warn("Schema validation will not be available!");
        return null;
    }

    /**
     * Resets the database.
     * 
     * @throws SQLException
     *             If something goes wrong resetting the database.
     * @throws IOException
     *             If something goes wrong reading the reset script.
     * @throws UnsupportedOperationsException
     *             If database resets are not allowed.
     */
    @SuppressWarnings("deprecation")
	public void doDbReset() throws SQLException, IOException {
        if (dbResetAllowed) {
            Session session = null;
            try {
                session = sessionFactory.openSession();
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    Connection connection = session.connection();
                    LOG.info("Running db reset script from file " + dbResetScript);
                    Statement stmt = connection.createStatement();
                    BufferedReader reader = new BufferedReader(new FileReader(dbResetScript));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (!line.startsWith("--")) {
                            LOG.debug("SQL: " + line);
                            stmt.addBatch(line);
                        }
                    }
                    stmt.executeBatch();
                    tx.commit();
                } catch (Exception e) {
                    LOG.error("dbReset failed: " + e.toString(), e);
                    if (tx != null) {
                        tx.rollback();
                    }
                    throw new SQLException(e.toString());
                }
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Implements the EPCIS capture operation. Takes an input stream, extracts
     * the payload into an XML document, validates the document against the
     * EPCIS schema, and captures the EPCIS events given in the document.
     * 
     * @throws IOException
     *             If an error occurred while validating the request or writing
     *             the response.
     * @throws ParserConfigurationException
     * @throws SAXException
     *             If the XML document is malformed or invalid
     * @throws InvalidFormatException
     */
    public void doCapture(InputStream in, Principal principal) throws SAXException, IOException, InvalidFormatException {

        // parse the payload as XML document
        Document document;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(in);
            LOG.debug("Payload successfully parsed as XML document");
            if (LOG.isDebugEnabled()) {
                try {
                    TransformerFactory tfFactory = TransformerFactory.newInstance();
                    Transformer transformer = tfFactory.newTransformer();
                    StringWriter writer = new StringWriter();
                    transformer.transform(new DOMSource(document), new StreamResult(writer));
                    String xml = writer.toString();
                    if (xml.length() > 100 * 1024) {
                        // too large, do not log
                        xml = null;
                    } else {
                        LOG.debug("Incoming contents:\n\n" + writer.toString() + "\n");
                    }
                } catch (Exception e) {
                    // never mind ... do not log
                }
            }
            //validate incoming document against its! schema(changed by nkef)
            if(isEPCISDocument(document)){
                // validate the XML document against the EPCISDocument schema
                if (schema != null) {
                    Validator validator = schema.newValidator();
                    try {
                        validator.validate(new DOMSource(document), null);
                    } catch (SAXParseException e) {
                        // TODO: we need to ignore XML element order, the following
                        // is only a hack to pass some of the conformance tests
                        if (e.getMessage().contains("parentID")) {
                            LOG.warn("Ignoring XML validation exception: " + e.getMessage());
                        } else {
                            throw e;
                        }
                    }
                    LOG.info("Incoming capture request was successfully validated against the EPCISDocument schema");
                } else {
                    LOG.warn("Schema validator unavailable. Unable to validate EPCIS capture event against schema!");
                }
            }else if (isEPCISMasterDataDocument(document)){
            	
            	//TODO: Validate EPCIS Master Data incoming Document
            	
            	
            }
            
        } catch (ParserConfigurationException e) {
            throw new SAXException(e);
        }

        // handle the capture operation
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction tx = null;
            try {
            	tx = session.beginTransaction();
                LOG.debug("DB connection opened.");
            	if(isEPCISDocument(document)){
            		handleEventDocument(session, document);
            	}else if (isEPCISMasterDataDocument(document)){
            		handleMasterDataDocument(session, document);
            	}
                tx.commit();
                // return OK
                LOG.info("EPCIS Capture Interface request succeeded");
            } catch (SAXException e) {
                LOG.error("EPCIS Capture Interface request failed: " + e.toString());
                if (tx != null) {
                    tx.rollback();
                }
                throw e;
            } catch (InvalidFormatException e) {
                LOG.error("EPCIS Capture Interface request failed: " + e.toString());
                if (tx != null) {
                    tx.rollback();
                }
                throw e;
            } catch (Exception e) {
                // Hibernate throws RuntimeExceptions, so don't let them
                // (or anything else) escape without clean up
                LOG.error("EPCIS Capture Interface request failed: " + e.toString(), e);
                if (tx != null) {
                    tx.rollback();
                }
                throw new IOException(e.toString());
            }
        } finally {
            if (session != null) {
                session.close();
            }
            // sessionFactory.getStatistics().logSummary();
            LOG.debug("DB connection closed");
        }
    }

    
    /**
     * @param document
     * @return
     */
    private boolean isEPCISDocument(Document document){
		return document.getDocumentElement().getLocalName().equals("EPCISDocument");
    }
    
    
    /**
     * @param document
     * @return
     */
    private boolean isEPCISMasterDataDocument(Document document){
    	return document.getDocumentElement().getLocalName().equals("EPCISMasterDataDocument");
    }
    

    /**
     * (nkef)
     * Parses the entire document and handles the supplied Master Data (nkef).
     * 
     * @throws Exception
     * @throws DOMException
     */
    private void handleMasterDataDocument(Session session, Document document) throws DOMException, SAXException,
            InvalidFormatException {
    	
       //Handle Vocabulary List 
	    NodeList vocabularyList = document.getElementsByTagName("VocabularyList");
	    if (vocabularyList.item(0).hasChildNodes()){
	        NodeList vocabularys = vocabularyList.item(0).getChildNodes();
	
	        // walk through all supplied vocabularies
	        int vocabularyCount = 0;
	        for (int i = 0; i < vocabularys.getLength(); i++) {
	            Node vocabularyNode = vocabularys.item(i);
	            String nodeName = vocabularyNode.getNodeName();
	            if (nodeName.equals("Vocabulary")) {
	            	
	            String vocabularyType = vocabularyNode.getAttributes().getNamedItem("type").getNodeValue();
	            
	            if (EpcisConstants.VOCABULARY_TYPES.contains(vocabularyType)){
	            	
	                LOG.debug("processing " + i + ": '" + nodeName + "':"+vocabularyType+".");
	                handleVocabulary(session, vocabularyNode, vocabularyType);
	                vocabularyCount++;
	                if (vocabularyCount % 50 == 0) {
	                    session.flush();
	                    session.clear();
	                }
	            }
	            } else if (!nodeName.equals("#text") && !nodeName.equals("#comment")) {
	                throw new SAXException("Encountered unknown vocabulary '" + nodeName + "'.");
	            }
	        }
        }

        
    }
    
    
    /**
     * (nkef)
     * Takes an XML document node, parses it as EPCIS Master Data and inserts the data
     * into the database. The parse routine is generic for all Vocabulary types; 
     * 
     * @param vocabularyNode
     *            The current vocabulary node.
     * @param vocabularyType
     *            The current vocabulary type.
     * @throws Exception
     * @throws DOMException
     */
    private void handleVocabulary(Session session, final Node vocabularyNode, final String vocabularyType) throws DOMException,
            SAXException, InvalidFormatException {
        if (vocabularyNode == null) {
            // nothing to do
            return;
        } else if (vocabularyNode.getChildNodes().getLength() == 0) {
            throw new SAXException("Vocabulary element '" + vocabularyNode.getNodeName() + "' has no children elements.");
        }
        Node curVocabularyNode = null;
        Node curVocabularyElementNode = null;
        Node curVocabularyAttributeNode = null;
        String curVocabularyURI = null;
        String curVocabularyAttribute = null;
        String curVocabularyAttributeValue = null;
        VocabularyElement curVocabularyElement = null;
        


  
        for (int i = 0; i < vocabularyNode.getChildNodes().getLength(); i++) {
        	curVocabularyNode = vocabularyNode.getChildNodes().item(i);
            String curVocabularyNodeName = curVocabularyNode.getNodeName();
            
            if (curVocabularyNodeName.equals("#text") || curVocabularyNodeName.equals("#comment")) {
                // ignore text or comments
                LOG.debug("  ignoring text or comment: '" + curVocabularyNode.getTextContent().trim() + "'");
                continue;
            }
            
            for (int j = 0; j < curVocabularyNode.getChildNodes().getLength(); j++) {
            	curVocabularyElementNode = curVocabularyNode.getChildNodes().item(j);
                String curVocabularyElementNodeName = curVocabularyElementNode.getNodeName();
                
                if (curVocabularyElementNodeName.equals("#text") || curVocabularyElementNodeName.equals("#comment")) {
                    // ignore text or comments
                    LOG.debug("  ignoring text or comment: '" + curVocabularyElementNode.getTextContent().trim() + "'");
                    continue;
                }
                
                
                LOG.debug("  handling vocabulary field: '" + curVocabularyElementNodeName + "'");
                curVocabularyURI = curVocabularyElementNode.getAttributes().getNamedItem("id").getNodeValue();
                
                String vocabularyElementEditMode = curVocabularyElementNode.getAttributes().getNamedItem("mode").getNodeValue();
                
                curVocabularyElement = getOrEditVocabularyElement(session,vocabularyType,curVocabularyURI,
                		vocabularyElementEditMode);
        
                
                //*****************************************
                if(curVocabularyElement!=null){
	                for (int k = 0; k < curVocabularyElementNode.getChildNodes().getLength(); k++) {
	                	curVocabularyAttributeNode = curVocabularyElementNode.getChildNodes().item(k);
	                    String curVocabularyAttributeElementNodeName = curVocabularyAttributeNode.getNodeName();
	                    
	                    if (curVocabularyAttributeElementNodeName.equals("#text") || curVocabularyAttributeElementNodeName.equals("#comment")) {
	                        // ignore text or comments
	                        LOG.debug("  ignoring text or comment: '" + curVocabularyAttributeNode.getTextContent().trim() + "'");
	                        continue;
	                    }
	                    
	                    
	                    LOG.debug("  handling vocabulary field: '" + curVocabularyAttributeElementNodeName + "'");
	                    curVocabularyAttribute = curVocabularyAttributeNode.getAttributes().getNamedItem("id").getNodeValue();
	      
	                    curVocabularyAttributeValue = curVocabularyAttributeNode.getFirstChild().getNodeValue();
	 
	                    String vocabularyAttributeEditMode = curVocabularyAttributeNode.getAttributes().getNamedItem("mode").getNodeValue();
	                                  
	                    getOrEditVocabularyAttributeElement(session,vocabularyType,curVocabularyElement.getId(),curVocabularyAttribute,
	                    		curVocabularyAttributeValue,vocabularyAttributeEditMode);
	                }
                }
                //*****************************************
                
            }
            
        }
 
        if (vocabularyType.equals(EpcisConstants.BUSINESS_LOCATION_ID)) {
  
        }
  
    }

    /**
     * Parses the entire document and handles the supplied events.
     * 
     * @throws Exception
     * @throws DOMException
     */
    private void handleEventDocument(Session session, Document document) throws DOMException, SAXException,
            InvalidFormatException {

    	//Handle Event list
        NodeList eventList = document.getElementsByTagName("EventList");
        
        if (eventList.item(0).hasChildNodes()){
	        NodeList events = eventList.item(0).getChildNodes();
	
	        // walk through all supplied events
	        int eventCount = 0;
	        for (int i = 0; i < events.getLength(); i++) {
	            Node eventNode = events.item(i);
	            String nodeName = eventNode.getNodeName();
	
	            if (nodeName.equals(EpcisConstants.OBJECT_EVENT) || nodeName.equals(EpcisConstants.AGGREGATION_EVENT)
	                    || nodeName.equals(EpcisConstants.QUANTITY_EVENT)
	                    || nodeName.equals(EpcisConstants.TRANSACTION_EVENT)) {
	                LOG.debug("processing event " + i + ": '" + nodeName + "'.");
	                handleEvent(session, eventNode, nodeName);
	                eventCount++;
	                if (eventCount % 50 == 0) {
	                    session.flush();
	                    session.clear();
	                }
	            } else if (!nodeName.equals("#text") && !nodeName.equals("#comment")) {
	                throw new SAXException("Encountered unknown event '" + nodeName + "'.");
	            }
	        }
        }

    }

    
    /**
     * Takes an XML document node, parses it as EPCIS event and inserts the data
     * into the database. The parse routine is generic for all event types; the
     * query generation part has some if/elses to take care of different event
     * parameters.
     * 
     * @param eventNode
     *            The current event node.
     * @param eventType
     *            The current event type.
     * @throws Exception
     * @throws DOMException
     */
    private void handleEvent(Session session, final Node eventNode, final String eventType) throws DOMException,
            SAXException, InvalidFormatException {
        if (eventNode == null) {
            // nothing to do
            return;
        } else if (eventNode.getChildNodes().getLength() == 0) {
            throw new SAXException("Event element '" + eventNode.getNodeName() + "' has no children elements.");
        }
        Node curEventNode = null;

        // A lot of the initialized variables have type URI. This type isn't to
        // compare with the URI-Type of the standard. In fact, most of the
        // variables having type URI are declared as Vocabularies in the
        // Standard. Commonly, we use String for the Standard-Type URI.

        Timestamp eventTime = null;
        Timestamp recordTime = new Timestamp(System.currentTimeMillis());
        String eventTimeZoneOffset = null;
        String action = null;
        String parentId = null;
        Long quantity = null;
        String bizStepUri = null;
        String dispositionUri = null;
        String readPointUri = null;
        String bizLocationUri = null;
        String epcClassUri = null;

        List<String> epcs = null;
        List<BusinessTransaction> bizTransList = null;
        List<EventFieldExtension> fieldNameExtList = new ArrayList<EventFieldExtension>();

        for (int i = 0; i < eventNode.getChildNodes().getLength(); i++) {
            curEventNode = eventNode.getChildNodes().item(i);
            String nodeName = curEventNode.getNodeName();

            if (nodeName.equals("#text") || nodeName.equals("#comment")) {
                // ignore text or comments
                LOG.debug("  ignoring text or comment: '" + curEventNode.getTextContent().trim() + "'");
                continue;
            }

            LOG.debug("  handling event field: '" + nodeName + "'");
            if (nodeName.equals("eventTime")) {
                String xmlTime = curEventNode.getTextContent();
                LOG.debug("    eventTime in xml is '" + xmlTime + "'");
                try {
                    eventTime = TimeParser.parseAsTimestamp(xmlTime);
                } catch (ParseException e) {
                    throw new SAXException("Invalid date/time (must be ISO8601).", e);
                }
                LOG.debug("    eventTime parsed as '" + eventTime + "'");
            } else if (nodeName.equals("recordTime")) {
                // ignore recordTime
            } else if (nodeName.equals("eventTimeZoneOffset")) {
                eventTimeZoneOffset = checkEventTimeZoneOffset(curEventNode.getTextContent());
            } else if (nodeName.equals("epcList") || nodeName.equals("childEPCs")) {
                epcs = handleEpcs(eventType, curEventNode);
            } else if (nodeName.equals("bizTransactionList")) {
                bizTransList = handleBizTransactions(session, curEventNode);
            } else if (nodeName.equals("action")) {
                action = curEventNode.getTextContent();
                if (!action.equals("ADD") && !action.equals("OBSERVE") && !action.equals("DELETE")) {
                    throw new SAXException("Encountered illegal 'action' value: " + action);
                }
            } else if (nodeName.equals("bizStep")) {
                bizStepUri = curEventNode.getTextContent();
            } else if (nodeName.equals("disposition")) {
                dispositionUri = curEventNode.getTextContent();
            } else if (nodeName.equals("readPoint")) {
                Element attrElem = (Element) curEventNode;
                Node id = attrElem.getElementsByTagName("id").item(0);
                readPointUri = id.getTextContent();
            } else if (nodeName.equals("bizLocation")) {
                Element attrElem = (Element) curEventNode;
                Node id = attrElem.getElementsByTagName("id").item(0);
                bizLocationUri = id.getTextContent();
            } else if (nodeName.equals("epcClass")) {
                epcClassUri = curEventNode.getTextContent();
            } else if (nodeName.equals("quantity")) {
                quantity = Long.valueOf(curEventNode.getTextContent());
            } else if (nodeName.equals("parentID")) {
                checkEpcOrUri(curEventNode.getTextContent(), false);
                parentId = curEventNode.getTextContent();
            } else {
                String[] parts = nodeName.split(":");
                if (parts.length == 2) {
                    LOG.debug("    treating unknown event field as extension.");
                    String prefix = parts[0];
                    String localname = parts[1];
                    // String namespace =
                    // document.getDocumentElement().getAttribute("xmlns:" +
                    // prefix);
                    String namespace = curEventNode.lookupNamespaceURI(prefix);
                    String value = curEventNode.getTextContent();
                    EventFieldExtension evf = new EventFieldExtension(prefix, namespace, localname, value);
                    fieldNameExtList.add(evf);
                } else {
                    // this is not a valid extension
                    throw new SAXException("    encountered unknown event field: '" + nodeName + "'.");
                }
            }
        }
        if (eventType.equals(EpcisConstants.AGGREGATION_EVENT)) {
            // for AggregationEvents, the parentID is only optional for
            // action=OBSERVE
            if (parentId == null && ("ADD".equals(action) || "DELETE".equals(action))) {
                throw new InvalidFormatException("'parentID' is required if 'action' is ADD or DELETE");
            }
        }

        String nodeName = eventNode.getNodeName();
        VocabularyElement bizStep = bizStepUri != null ? getOrEditVocabularyElement(session,
                EpcisConstants.BUSINESS_STEP_ID, String.valueOf(bizStepUri),"1") : null;
        VocabularyElement disposition = dispositionUri != null ? getOrEditVocabularyElement(session,
                EpcisConstants.DISPOSITION_ID, String.valueOf(dispositionUri),"1") : null;
        VocabularyElement readPoint = readPointUri != null ? getOrEditVocabularyElement(session,
                EpcisConstants.READ_POINT_ID, String.valueOf(readPointUri),"1") : null;
        VocabularyElement bizLocation = bizLocationUri != null ? getOrEditVocabularyElement(session,
                EpcisConstants.BUSINESS_LOCATION_ID, String.valueOf(bizLocationUri),"1") : null;
        VocabularyElement epcClass = epcClassUri != null ? getOrEditVocabularyElement(session,
                EpcisConstants.EPC_CLASS_ID, String.valueOf(epcClassUri),"1") : null;

        BaseEvent be;
        if (nodeName.equals(EpcisConstants.AGGREGATION_EVENT)) {
            AggregationEvent ae = new AggregationEvent();
            ae.setParentId(parentId);
            ae.setChildEpcs(epcs);
            ae.setAction(Action.valueOf(action));
            be = ae;
        } else if (nodeName.equals(EpcisConstants.OBJECT_EVENT)) {
            ObjectEvent oe = new ObjectEvent();
            oe.setAction(Action.valueOf(action));
            if (epcs != null && epcs.size() > 0) {
                oe.setEpcList(epcs);
            }
            be = oe;
        } else if (nodeName.equals(EpcisConstants.QUANTITY_EVENT)) {
            QuantityEvent qe = new QuantityEvent();
            qe.setEpcClass((EPCClass) epcClass);
            if (quantity != null) {
                qe.setQuantity(quantity.longValue());
            }
            be = qe;
        } else if (nodeName.equals(EpcisConstants.TRANSACTION_EVENT)) {
            TransactionEvent te = new TransactionEvent();
            te.setParentId(parentId);
            te.setEpcList(epcs);
            te.setAction(Action.valueOf(action));
            be = te;
        } else {
            throw new SAXException("Encountered unknown event element '" + nodeName + "'.");
        }

        be.setEventTime(eventTime);
        be.setRecordTime(recordTime);
        be.setEventTimeZoneOffset(eventTimeZoneOffset);
        be.setBizStep((BusinessStepId) bizStep);
        be.setDisposition((DispositionId) disposition);
        be.setBizLocation((BusinessLocationId) bizLocation);
        be.setReadPoint((ReadPointId) readPoint);
        if (bizTransList != null && bizTransList.size() > 0) {
            be.setBizTransList(bizTransList);
        }
        if (!fieldNameExtList.isEmpty()) {
            be.setExtensions(fieldNameExtList);
        }

        session.save(be);
    }

    /**
     * Parses the xml tree for epc nodes and returns a list of EPC URIs.
     * 
     * @param eventType
     * @param epcNode
     *            The parent Node from which EPC URIs should be extracted.
     * @return An array of vocabularies containing all the URIs found in the
     *         given node.
     * @throws SAXException
     *             If an unknown tag (no &lt;epc&gt;) is encountered.
     * @throws InvalidFormatException
     * @throws DOMException
     */
    private List<String> handleEpcs(final String eventType, final Node epcNode) throws SAXException, DOMException,
            InvalidFormatException {
        List<String> epcList = new ArrayList<String>();

        boolean isEpc = false;
        boolean epcRequired = false;
        boolean atLeastOneNonEpc = false;
        for (int i = 0; i < epcNode.getChildNodes().getLength(); i++) {
            Node curNode = epcNode.getChildNodes().item(i);
            if (curNode.getNodeName().equals("epc")) {
                isEpc = checkEpcOrUri(curNode.getTextContent(), epcRequired);
                if (isEpc) {
                    // if one of the values is an EPC, then all of them must be
                    // valid EPCs
                    epcRequired = true;
                } else {
                    atLeastOneNonEpc = true;
                }
                epcList.add(curNode.getTextContent());
            } else {
                if (curNode.getNodeName() != "#text" && curNode.getNodeName() != "#comment") {
                    throw new SAXException("Unknown XML tag: " + curNode.getNodeName(), null);
                }
            }
        }
        if (atLeastOneNonEpc && isEpc) {
            throw new InvalidFormatException(
                    "One of the provided EPCs was a 'pure identity' EPC, so all of them must be 'pure identity' EPCs");
        }
        return epcList;
    }

    /**
     * @param epcOrUri
     *            The EPC or URI to check.
     * @param epcRequired
     *            <code>true</code> if an EPC is required (will throw an
     *            InvalidFormatException if the given <code>epcOrUri</code> is
     *            an invalid EPC, but might be a valid URI), <code>false</code>
     *            otherwise.
     * @return <code>true</code> if the given <code>epcOrUri</code> is a
     *         valid EPC, <code>false</code> otherwise.
     * @throws InvalidFormatException
     */
    protected boolean checkEpcOrUri(String epcOrUri, boolean epcRequired) throws InvalidFormatException {
        boolean isEpc = false;
        if (epcOrUri.startsWith("urn:epc:id:")) {
            // check if it is a valid EPC
            checkEpc(epcOrUri);
            isEpc = true;
        } else {
            // childEPCs in AggregationEvents, and epcList in
            // TransactionEvents might also be simple URIs
            if (epcRequired) {
                throw new InvalidFormatException(
                        "One of the provided EPCs was a 'pure identity' EPC, so all of them must be 'pure identity' EPCs");
            }
            checkUri(epcOrUri);
        }
        return isEpc;
    }

    /**
     * Parses the xml tree for epc nodes and returns a List of BizTransaction
     * URIs with their corresponding type.
     * 
     * @param bizNode
     *            The parent Node from which BizTransaction URIs should be
     *            extracted.
     * @return A List of BizTransaction.
     * @throws SAXException
     *             If an unknown tag (no &lt;epc&gt;) is encountered.
     */
    private List<BusinessTransaction> handleBizTransactions(Session session, Node bizNode) throws SAXException {
        List<BusinessTransaction> bizTransactionList = new ArrayList<BusinessTransaction>();

        for (int i = 0; i < bizNode.getChildNodes().getLength(); i++) {
            Node curNode = bizNode.getChildNodes().item(i);
            if (curNode.getNodeName().equals("bizTransaction")) {
                String bizTransTypeUri = curNode.getAttributes().item(0).getTextContent();
                String bizTransUri = curNode.getTextContent();
                BusinessTransactionId bizTrans = (BusinessTransactionId) getOrEditVocabularyElement(session,
                        EpcisConstants.BUSINESS_TRANSACTION_ID, bizTransUri.toString(),"1");
                BusinessTransactionTypeId type = (BusinessTransactionTypeId) getOrEditVocabularyElement(session,
                        EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID, bizTransTypeUri.toString(),"1");

                Criteria c0 = session.createCriteria(BusinessTransaction.class);
                c0.add(Restrictions.eq("bizTransaction", bizTrans));
                c0.add(Restrictions.eq("type", type));
                BusinessTransaction bizTransaction = (BusinessTransaction) c0.uniqueResult();

                if (bizTransaction == null) {
                    bizTransaction = new BusinessTransaction();
                    bizTransaction.setBizTransaction(bizTrans);
                    bizTransaction.setType(type);
                    session.save(bizTransaction);
                }

                bizTransactionList.add(bizTransaction);

            } else {
                if (!curNode.getNodeName().equals("#text") && !curNode.getNodeName().equals("#comment")) {
                    throw new SAXException("Unknown XML tag: " + curNode.getNodeName(), null);
                }
            }
        }
        return bizTransactionList;
    }

    /**
     * (changed by nkef)
     * Inserts vocabulary into the database by searching for already existing
     * entries; if found, the corresponding ID is returned. If not found, the
     * vocabulary is extended if "insertmissingvoc" is true; otherwise an
     * SQLException is thrown
     * 
     * @param tableName
     *            The name of the vocabulary table.
     * @param uri
     *            The vocabulary adapting the URI to be inserted into the
     *            vocabulary table.
     * @return The ID of an already existing vocabulary table with the given
     *         uri.
     * @throws UnsupportedOperationException
     *             If we are not allowed to insert a missing vocabulary.
     */
    public VocabularyElement getOrEditVocabularyElement(Session session, String vocabularyType,
            String vocabularyElementURI,String mode) throws SAXException {
    	boolean alterURI = false;
    	boolean singleDelete = false;
        boolean wdDelete = false;
        Long vocabularyElementID = null;
        
        if(mode.equals("2")){
        	alterURI = true;
        }else if(mode.equals("3")){
        	singleDelete = true;
        }else if(mode.equals("4")){
        	wdDelete = true;
        }
		
        Class<?> c = vocClassMap.get(vocabularyType);
        Criteria c0 = session.createCriteria(c);
        c0.setCacheable(true);
        c0.add(Restrictions.eq("uri", alterURI ? vocabularyElementURI.split("#")[0] : vocabularyElementURI));
        VocabularyElement ve;
        try {
            ve = (VocabularyElement) c0.uniqueResult();
        } catch (ObjectNotFoundException e) {
            ve = null;
        }
        if(ve != null){
        	vocabularyElementID = ve.getId();
        }
        
        
        if (ve == null || ((singleDelete || alterURI || wdDelete) && ve!=null)) {
            // the uri does not yet exist: insert it if allowed. According to
            // the specs, some vocabulary is not allowed to be extended; this is
            // currently ignored here
            if (!insertMissingVoc) {
                throw new UnsupportedOperationException("Not allowed to add new vocabulary - use existing vocabulary");
            } else {
                // VocabularyElement subclasses should always have public
                // zero-arg constructor to avoid problems here

                if(alterURI){
                	ve.setUri(vocabularyElementURI.split("#")[1]);
                	session.update(ve);
                	session.flush();
                	return ve;
                	
                }else if(singleDelete){
                	Object vocabularyElementObject = session.get(c, vocabularyElementID);
                	if(vocabularyElementObject!= null)
                	session.delete(vocabularyElementObject);
                	deleteVocabularyElementAttributes(session,vocabularyType,vocabularyElementID);
                	session.flush();
                	return null;
                }else if (wdDelete){
                	Object vocabularyElementObject = session.get(c, vocabularyElementID);
                	if(vocabularyElementObject!= null)
                	session.delete(vocabularyElementObject);
                	deleteVocabularyElementAttributes(session,vocabularyType,vocabularyElementID);
                	deleteVocabularyElementDescendants(session,vocabularyType,vocabularyElementURI);
                	session.flush();
                	return null;

                }else{
                	
                    try {
                        ve = (VocabularyElement) c.newInstance();
                    } catch (InstantiationException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                	
                	ve.setUri(vocabularyElementURI);
                	session.save(ve);
                }

                session.flush();
            }
        }
        return ve;
    }
    
    /**
     * (nkef)
     * Delete the a vocabulary's Element Descendants and all of their Attributes
     * 
     * @param session
     * @param vocabularyType
     * @param vocabularyElementURI
     */
    private void deleteVocabularyElementDescendants(Session session,String vocabularyType, String vocabularyElementURI){
        Class<?> c = vocClassMap.get(vocabularyType);
        List vocElementChildrens = session.createSQLQuery("SELECT * FROM "+vocabularyTablenameMap.get(vocabularyType)+" WHERE uri LIKE '"+vocabularyElementURI+",%'").addEntity(c).list();
    	for(int i = 0 ; i<vocElementChildrens.size(); i++){
    		session.delete((VocabularyElement)vocElementChildrens.get(i));
    		deleteVocabularyElementAttributes(session,vocabularyType,((VocabularyElement)vocElementChildrens.get(i)).getId());
    	}
    	session.flush();
    }
    
    /**
     * (nkef)
     * Delete selected id vocabulary elements attributes
     * 
     * @param session
     * @param vocabularyType
     * @param vocabularyElementID
     */
    private void deleteVocabularyElementAttributes(Session session,String vocabularyType, Long vocabularyElementID){
    	Class<?> c = vocAttributeClassMap.get(vocabularyType);
        List vocAttributeElements = session.createSQLQuery("select * FROM "+vocAttributeTablesMap.get(vocabularyType)+" where id = '"+vocabularyElementID+"'").addEntity(c).list();
        	for(int i = 0 ; i<vocAttributeElements.size(); i++){
        		session.delete((VocabularyAttributeElement)vocAttributeElements.get(i));
        	}
    		session.flush();

    }

	/**
	 * (nkef)
     * Inserts vocabulary attribute into the database by searching for already existing
     * entries; if found, the corresponding ID is returned. If not found, the
     * vocabulary is extended if "insertmissingvoc" is true; otherwise an
     * SQLException is thrown
     * 
     * @param tableName
     *            The name of the vocabulary table.
     * @param uri
     *            The vocabulary adapting the URI to be inserted into the
     *            vocabulary table.
     * @return The ID of an already existing vocabulary table with the given
     *         uri.
     * @throws UnsupportedOperationException
     *             If we are not allowed to insert a missing vocabulary.
     */
    public VocabularyAttributeElement getOrEditVocabularyAttributeElement(Session session, String vocabularyType,
            Long vocabularyElementID, String vocabularyAttributeElement, String vocabularyAttributeElementValue,
            String mode) throws SAXException {
    
    	boolean alterAttrValue = false;
    	boolean deleteAttribute = false;
    	if(mode.equals("2")){
    		alterAttrValue = true;
    	}else if (mode.equals("3")){
    		deleteAttribute = true;
    	}
        Class<?> c = vocAttributeClassMap.get(vocabularyType);
        Criteria c0 = session.createCriteria(c);
        c0.setCacheable(true);
        
        VocabularyAttrCiD vocabularyAttrCiD = new VocabularyAttrCiD();
        vocabularyAttrCiD.setAttribute(vocabularyAttributeElement);
        vocabularyAttrCiD.setId(vocabularyElementID);
        
        c0.add(Restrictions.idEq(vocabularyAttrCiD));
        
        VocabularyAttributeElement vocAttributeElement = null;

        try {
        	vocAttributeElement = (VocabularyAttributeElement) c0.uniqueResult();
        } catch (ObjectNotFoundException e) {
        	vocAttributeElement = null;
        }
        
        
        if (vocAttributeElement == null || (alterAttrValue && vocAttributeElement!=null) || (deleteAttribute && vocAttributeElement!=null) ) {
            // the uri does not yet exist: insert it if allowed. According to
            // the specs, some vocabulary is not allowed to be extended; this is
            // currently ignored here
            if (!insertMissingVoc) {
                throw new UnsupportedOperationException("Not allowed to add new vocabulary - use existing vocabulary");
            } else {
                // VocabularyAttributeElement subclasses should always have public
                // zero-arg constructor to avoid problems here
                try {
                	vocAttributeElement = (VocabularyAttributeElement) c.newInstance();
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                vocAttributeElement.setVocabularyAttrCiD(vocabularyAttrCiD);
                vocAttributeElement.setValue(vocabularyAttributeElementValue);
                
                
                if(alterAttrValue){
                	session.merge(vocAttributeElement);
                }else if (deleteAttribute ) {
                	Object vocabularyAttr = session.get(c, vocabularyAttrCiD);
                	if(vocabularyAttr!= null)
                	session.delete(vocabularyAttr);
                	session.flush();
                	return null;
                }else{
                	session.save(vocAttributeElement);
                }
                
                
                session.flush();
            }
        }
        return vocAttributeElement;
    }
    
    
    
    
    

    /**
     * TODO: javadoc!
     * 
     * @param textContent
     * @return
     * @throws InvalidFormatException
     */
    protected String checkEventTimeZoneOffset(String textContent) throws InvalidFormatException {
        // first check the provided String against the expected pattern
        Pattern p = Pattern.compile("[+-]\\d\\d:\\d\\d");
        Matcher m = p.matcher(textContent);
        boolean matches = m.matches();
        if (matches) {
            // second check the values (hours and minutes)
            int h = Integer.parseInt(textContent.substring(1, 3));
            int min = Integer.parseInt(textContent.substring(4, 6));
            if ((h < 0) || (h > 14)) {
                matches = false;
            } else if (h == 14 && min != 0) {
                matches = false;
            } else if ((min < 0) || (min > 59)) {
                matches = false;
            }
        }
        if (matches) {
            return textContent;
        } else {
            throw new InvalidFormatException("'eventTimeZoneOffset' has invalid format: " + textContent);
        }
    }

    /**
     * TODO: javadoc!
     * 
     * @param textContent
     * @return
     * @throws InvalidFormatException
     */
    private boolean checkUri(String textContent) throws InvalidFormatException {
        try {
            new URI(textContent);
        } catch (URISyntaxException e) {
            throw new InvalidFormatException(e.getMessage(), e);
        }
        return true;
    }

    /**
     * Check EPC according to 'pure identity' URI as specified in Tag Data
     * Standard.
     * 
     * @param textContent
     * @throws InvalidFormatException
     */
    protected void checkEpc(String textContent) throws InvalidFormatException {
        String uri = textContent;
        if (!uri.startsWith("urn:epc:id:")) {
            throw new InvalidFormatException("Invalid 'pure identity' EPC format: must start with \"urn:epc:id:\"");
        }
        uri = uri.substring("urn:epc:id:".length());

        // check the patterns for the different EPC types
        String epcType = uri.substring(0, uri.indexOf(":"));
        uri = uri.substring(epcType.length() + 1);
        LOG.debug("Checking pattern for EPC type " + epcType + ": " + uri);
        Pattern p;
        if ("gid".equals(epcType)) {
            p = Pattern.compile("((0|[1-9][0-9]*)\\.){2}(0|[1-9][0-9]*)");
        } else if ("sgtin".equals(epcType) || "sgln".equals(epcType) || "grai".equals(epcType)) {
            p = Pattern.compile("([0-9]+\\.){2}([0-9]|[A-Z]|[a-z]|[\\!\\(\\)\\*\\+\\-',:;=_]|(%(([0-9]|[A-F])|[a-f]){2}))+");
        } else if ("sscc".equals(epcType)) {
            p = Pattern.compile("[0-9]+\\.[0-9]+");
        } else if ("giai".equals(epcType)) {
            p = Pattern.compile("[0-9]+\\.([0-9]|[A-Z]|[a-z]|[\\!\\(\\)\\*\\+\\-',:;=_]|(%(([0-9]|[A-F])|[a-f]){2}))+");
        } else {
            throw new InvalidFormatException("Invalid 'pure identity' EPC format: unknown EPC type: " + epcType);
        }
        Matcher m = p.matcher(uri);
        if (!m.matches()) {
            throw new InvalidFormatException("Invalid 'pure identity' EPC format: pattern \"" + uri
                    + "\" is invalid for EPC type \"" + epcType + "\" - check with Tag Data Standard");
        }

        // check the number of digits for the different EPC types
        boolean exceeded = false;
        int count1 = uri.indexOf(".");
        if ("sgtin".equals(epcType)) {
            int count2 = uri.indexOf(".", count1 + 1) - (count1 + 1);
            if (count1 + count2 > 13) {
                exceeded = true;
            }
        } else if ("sgln".equals(epcType)) {
            int count2 = uri.indexOf(".", count1 + 1) - (count1 + 1);
            if (count1 + count2 > 12) {
                exceeded = true;
            }
        } else if ("grai".equals(epcType)) {
            int count2 = uri.indexOf(".", count1 + 1) - (count1 + 1);
            if (count1 + count2 > 12) {
                exceeded = true;
            }
        } else if ("sscc".equals(epcType)) {
            int count2 = uri.length() - (count1 + 1);
            if (count1 + count2 > 17) {
                exceeded = true;
            }
        } else if ("giai".equals(epcType)) {
            int count2 = uri.length() - (count1 + 1);
            if (count1 + count2 > 30) {
                exceeded = true;
            }
        } else {
            // nothing to count
        }
        if (exceeded) {
            throw new InvalidFormatException(
                    "Invalid 'pure identity' EPC format: check allowed number of characters for EPC type '" + epcType
                            + "'");
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean isDbResetAllowed() {
        return dbResetAllowed;
    }

    public void setDbResetAllowed(boolean dbResetAllowed) {
        this.dbResetAllowed = dbResetAllowed;
    }

    public void setDbResetScript(String dbResetScript) {
        URL url = this.getClass().getResource(dbResetScript);
        this.dbResetScript = new File(url.getFile());
    }

    public boolean isInsertMissingVoc() {
        return insertMissingVoc;
    }

    public void setInsertMissingVoc(boolean insertMissingVoc) {
        this.insertMissingVoc = insertMissingVoc;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public void setEpcisSchemaFile(String epcisSchemaFile) {
        Schema schema = initEpcisSchema(epcisSchemaFile);
        setSchema(schema);
    }
    
    //(nkef)
    public Schema getMasterDataSchema() {
		return masterDataSchema;
	}
    //(nkef)
	public void setMasterDataSchema(Schema masterDataSchema) {
		this.masterDataSchema = masterDataSchema;
	}
}
