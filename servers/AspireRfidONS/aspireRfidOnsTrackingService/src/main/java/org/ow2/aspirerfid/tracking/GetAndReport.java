package org.ow2.aspirerfid.tracking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.ow2.aspirerfid.commons.epcglobal.commons.EPC;
import org.ow2.aspirerfid.commons.epcis.model.AggregationEventType;
import org.ow2.aspirerfid.commons.epcis.model.ArrayOfString;
import org.ow2.aspirerfid.commons.epcis.model.AttributeType;
import org.ow2.aspirerfid.commons.epcis.model.EPCISEventType;
import org.ow2.aspirerfid.commons.epcis.model.ObjectEventType;
import org.ow2.aspirerfid.commons.epcis.model.Poll;
import org.ow2.aspirerfid.commons.epcis.model.QuantityEventType;
import org.ow2.aspirerfid.commons.epcis.model.QueryParam;
import org.ow2.aspirerfid.commons.epcis.model.QueryParams;
import org.ow2.aspirerfid.commons.epcis.model.QueryResults;
import org.ow2.aspirerfid.commons.epcis.model.TransactionEventType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyType;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.ImplementationExceptionResponse;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.NoSuchNameExceptionResponse;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.QueryParameterExceptionResponse;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.QueryTooComplexExceptionResponse;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.QueryTooLargeExceptionResponse;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.SecurityExceptionResponse;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.ValidationExceptionResponse;
import org.ow2.aspirerfid.commons.ons.resolverOns;
import org.ow2.aspirerfid.commons.tracking.db.HibernateUtil;
import org.ow2.aspirerfid.commons.tracking.db.ReportedEpcid;
import org.ow2.aspirerfid.commons.tracking.query.GeoTagReadPoint;
import org.ow2.aspirerfid.commons.tracking.query.MasterDataQueryClient;
import org.ow2.aspirerfid.commons.tracking.query.QueryControlClient;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.CompanyInfo;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.Tag;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.TagIdList;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.Trackerdocument;
import org.ow2.aspirerfid.commons.utils.TimeParser;
import org.ow2.aspirerfid.tracking.client.TrackingInterfaceClient;
import org.xbill.DNS.NAPTRRecord;
import org.xbill.DNS.Record;

/**
 * @author Kostas Mourtzoukos (komo) e-mail: komo@ait.edu.gr
 *
 */

/**
 * @author maddog
 *
 */
public class GetAndReport implements Runnable {

	private static Properties genProps = new Properties();
	private static final Logger LOG = Logger.getLogger(TrackingInterfaceImpl.class);	
	private static final String defaultQueryUrl = "http://localhost:8080/aspireRfidEpcisRepository/query";
	
	private List<GeoTagReadPoint> readpoints = new ArrayList<GeoTagReadPoint>();
	private static QueryControlClient queryClient = null;
	private static resolverOns resolver = null;
	private List<Trackerdocument> trDocumentReportList = new ArrayList<Trackerdocument>();

	
	@Override
	public void run() {
		LOG.info("Get and Report Thread is up and running");
		// Load properties
		loadProperties();
		
		// Load all read point information from local epcis.
		if(readpoints.isEmpty()){
			fetchReadPoints();
		}
		
		// Epcis query client
		String queryUrl = "";
		if ((queryUrl = genProps.getProperty("localEpcisInterface")) == null) {
			LOG.error("NO EPCIS SETTING FOUND-USING DEFAULT");
			queryUrl = defaultQueryUrl;
		}
		queryClient = new QueryControlClient(queryUrl);
			
		// ONS Client
		
		try {
			resolver = new resolverOns(genProps.getProperty("localONS_Resolver"));
		} catch (UnknownHostException e1) {
			LOG.error("Unable to initialize DNS resolver");
			LOG.error("Only local information available");
			LOG.error(e1.toString());
			e1.printStackTrace();
		}
		Calendar cal = null;
		try {
			cal = TimeParser.parseAsCalendar(genProps.getProperty("startTimeDate"));
		} catch (ParseException e) {
			LOG.error("Error getting calendar value");
			LOG.error(e.toString());
			e.printStackTrace();
			cal = new GregorianCalendar(1980,1,1);
		}
		
		// For each epc class, retrieve events and report them		
		while (true) {
			List<QueryParam> internalQueryParams = new ArrayList<QueryParam>();

			// Add all kind of events
			ArrayOfString events = new ArrayOfString();

			events.getString().add("ObjectEvent");
			events.getString().add("AggregationEvent");
			events.getString().add("QuantityEvent");
			events.getString().add("TransactionEvent");
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue(events);
			internalQueryParams.add(queryParam);
			
			// Add time constraints
			QueryParam queryParam2 = new QueryParam();
			queryParam2.setName("GE_recordTime");
			queryParam2.setValue(cal);
			internalQueryParams.add(queryParam2);
			
			// Set new time checkpoint for next round
			cal.setTime(new Date());
			genProps.setProperty("startTimeDate", prettyStringCalendar(cal));
			saveProperties();
			
			// Create poll and add all parameters
			QueryParams queryParams = new QueryParams();
			queryParams.getParam().addAll(internalQueryParams);

			Poll poll = new Poll();
			poll.setQueryName("SimpleEventQuery");
			poll.setParams(queryParams);

			// Run the epcis query
			try {
				QueryResults epcis_results = queryClient.poll(poll);
				LOG.info("RUN QUERY");
				if (epcis_results != null
						&& epcis_results.getResultsBody() != null
						&& epcis_results.getResultsBody().getEventList() != null) {
					// processEvents(epcis_results.getResultsBody().getEventList().getObjectEventOrAggregationEventOrQuantityEvent(),epcId);
					processEvents(epcis_results.getResultsBody().getEventList()
							.getObjectEventOrAggregationEventOrQuantityEvent());
				}
				if(!attermptToReport()){
					LOG.error("Error Reporting epc events");
				}
				else LOG.info("EPC events reported succesfully");
				
			} catch (ImplementationExceptionResponse implementationExceptionResponse) {
				LOG.error(implementationExceptionResponse.toString());
			} catch (SecurityExceptionResponse securityExceptionResponse) {
				LOG.error(securityExceptionResponse.toString());
			} catch (ValidationExceptionResponse validationExceptionResponse) {
				LOG.error(validationExceptionResponse.toString());
			} catch (NoSuchNameExceptionResponse noSuchNameExceptionResponse) {
				LOG.error(noSuchNameExceptionResponse.toString());
			} catch (QueryParameterExceptionResponse queryParameterExceptionResponse) {
				LOG.error(queryParameterExceptionResponse.toString());
			} catch (QueryTooLargeExceptionResponse queryTooLargeExceptionResponse) {
				LOG.error(queryTooLargeExceptionResponse.toString());
			} catch (QueryTooComplexExceptionResponse queryTooComplexExceptionResponse) {
				LOG.error(queryTooComplexExceptionResponse.toString());
			}
			try {
				Thread.sleep(Long.parseLong(genProps.getProperty("pollPeriod")));
			} catch (InterruptedException e) {
				LOG.error(e.toString());
				e.printStackTrace();
			}
		}

		

	}

    /**
     *	Fetch all available readpoint from the EPCIS repository
     *	If not GeoTagging information is included, add fefault from properties file 
     */
    private void fetchReadPoints(){
    	readpoints.clear();
    	// Load general properties
    	try {
    		LOG.debug("Loading settings");
    		InputStream ins = GetAndReport.class.getResourceAsStream("/tracking.properties");
    		genProps.load(ins);
    		ins.close();		
		} catch (Exception e) {
			LOG.error("Error during loading partner epc classes");
			LOG.error(e.toString());	
		}   	
    	
    	
    	LOG.info("FETCHING READ POINTS");
    	String queryUrl = "";
    	MasterDataQueryClient queryClient = null;
		if((queryUrl = genProps.getProperty("localEpcisInterface"))==null){
			LOG.error("NO EPCIS SETTING FOUND-USING DEFAULT");
			queryUrl = defaultQueryUrl;
		}
    	List<VocabularyElementType> vocabularyElementList = null;

    	queryClient = new MasterDataQueryClient(queryUrl);
    	List<VocabularyType> vocabularyList = null;

    	try {
    		queryClient.clearParameters();

    		QueryParam queryParam = new QueryParam();
    		queryParam.setName("eventType");
    		queryParam.setValue("SimpleMasterDataQuery");
    		queryClient.addParameter(queryParam);

    		QueryParam param2 = new QueryParam();
    		param2.setName("includeAttributes");
    		param2.setValue(true);
    		queryClient.addParameter(param2);
    		QueryParam param3 = new QueryParam();
    		param3.setName("includeChildren");
    		param3.setValue(true);
    		queryClient.addParameter(param3);

    		ArrayOfString vocabularyType = new ArrayOfString();

    		vocabularyType.getString().add("urn:epcglobal:epcis:vtype:ReadPoint");
    		QueryParam param5 = new QueryParam();
    		param5.setName("vocabularyName");
    		param5.setValue(vocabularyType);
    		queryClient.addParameter(param5);

    		vocabularyList = queryClient.runMasterDataQuery();

    		Object o = vocabularyList.get(0);
    		if (o instanceof JAXBElement<?>) {
    			o = ((JAXBElement<?>) o).getValue();
    		}
    		VocabularyType vocabulary = (VocabularyType) o;
    		// table[row][0] = vocabulary.getType();
    		LOG.info("Vocabulary type" +vocabulary.getType().toString());

    		vocabularyElementList = vocabulary.getVocabularyElementList().getVocabularyElement();
    		for (Object v : vocabularyElementList) {
    			VocabularyElementType vocabularyElement = (VocabularyElementType) v;
    			GeoTagReadPoint rPoint = new GeoTagReadPoint(vocabularyElement.getId(), "", "");
    			LOG.info("Read point name:" + vocabularyElement.getId().toString());
    			List<AttributeType> attributeTypeList;
    			attributeTypeList = vocabularyElement.getAttribute();
    			for (Object att : attributeTypeList) {
    				AttributeType attribute = (AttributeType) att;
    				String aname = attribute.getId();
    				LOG.debug("Attribute Name:" + aname);
    				if (aname.equals("urn:epcglobal:epcis:mda:GeoCoordinates")) 
    				{
						String[] coords = attribute.getOtherAttributes().get(new QName("value")).split(",");
						rPoint.setLatitude(coords[0]);
						rPoint.setLongtitude(coords[1]);
						break;
					}
    				// If readpoint contains no info, enter default
    				rPoint.setLatitude(genProps.getProperty("lattitude"));
    				rPoint.setLongtitude(genProps.getProperty("longtitude"));
    			}
    			readpoints.add(rPoint);
    		}

    	} catch (ParseException e) {
    		LOG.error("Unable to parse a Time value.");
    		LOG.error(e.toString());
    	} catch (Exception e) {
    		LOG.error("Unexpected error while invoking EPCIS Query Interface.");
    		LOG.error(e.toString());
    	}
    }


    @SuppressWarnings("unchecked")
	private boolean processEvents(final List<Object> eventList ) {

    	Hashtable reportData = new Hashtable();
    	String company = "";
    	
		
		// Open Hibernate session to local database
		Session session = null;
		SessionFactory sesFact = null;
		try {
			sesFact = HibernateUtil.getSessionFactory();
			//session = HibernateUtil.getSessionFactory().openSession();
			LOG.info("Hibernate Session Open");
		} catch (HibernateException e) {
			LOG.error("ERROR OPENNING HIBERNATE SESSION");
			LOG.error(e.toString());
			e.printStackTrace();
		}
		
    	
		// Parse events, add tags to the document
		TagIdList tlist = null;
		
		
        int nofEvents = eventList.size();
        
        LOG.debug("\n\n" + nofEvents + " events returned by the server:\n\n");
        for (Object o : eventList) {
            if (o instanceof JAXBElement<?>) {
                o = ((JAXBElement<?>) o).getValue();
            }
            EPCISEventType event = (EPCISEventType) o;
            LOG.debug("[ EPCISEvent ]\n");
            String eventTime = prettyStringCalendar(event.getEventTime().toGregorianCalendar());
            LOG.debug("eventTime:\t" + eventTime + "\n");       
            String recordTime = prettyStringCalendar(event.getRecordTime().toGregorianCalendar());
            LOG.debug("recordTime:\t" + recordTime + "\n");            
            LOG.debug("timeZoneOffset:\t" + event.getEventTimeZoneOffset() + "\n");

			if (event instanceof ObjectEventType) {
				LOG.debug("[ ObjectEvent ]\n");
				ObjectEventType e = (ObjectEventType) event;
				LOG.debug("epcList:\t");
				for (EPC epc : e.getEpcList().getEpc()) {
					String lat = "";
					String longt = "";
					LOG.debug(" '" + epc.getValue() + "'");
					company = getCompanyIdentifier(epc.getValue());
					// Discard epc than are company property
					if (company.equals(genProps.get("companyIdentifier")))
						continue;
					// Non-company property
					Tag tagobj = new Tag();
					tagobj.setId(epc.getValue());
					if (e.getReadPoint() != null) {
						LOG.debug("readPoint:\t" + e.getReadPoint().getId()
								+ "\n");
						if (!readpoints.isEmpty()) {
							for (GeoTagReadPoint gtrp : readpoints) {
								if (gtrp.getId().equals(
										e.getReadPoint().getId())) {
									tagobj.setGeoCoords(gtrp.getLatitude()
											+ ":" + gtrp.getLongtitude());
									lat = gtrp.getLatitude();
									longt = gtrp.getLongtitude();
								}
							}
						}
					} else {
						// If no read point, enter default info from
						// properties file
						LOG.debug("readPoint:\tnull\n");
						tagobj.setGeoCoords(genProps.getProperty("lattitude")
								+ ":" + genProps.getProperty("longtitude"));
					}
					tagobj.setTime(event.getRecordTime());

					// Check to see if tag/geocoords pair is already reported
					session = sesFact.openSession();
					Transaction tx = session.beginTransaction();
					List uriList = null;
					try {
						Query q = session.createQuery("select u.id from "
								+ "ReportedEpcid as u where u.tagId=:tagID "
								+ "and u.latitude=:latTitude "
								+ "and u.longtitude=:longTitude");
						q.setString("tagID", epc.getValue());
						q.setString("latTitude", lat);
						q.setString("longTitude", longt);
						uriList = q.list();
						session.getTransaction().commit();
					} catch (RuntimeException e1) {
						if (tx != null)
							tx.rollback();
						LOG.error(e1.toString());
						e1.printStackTrace();
					} finally {
						session.close();
					}
					if (uriList.isEmpty()) {
						// Enter the new Tag/Geocord pair to the apropriate list
						// If the first for this company, create a new list in the 
						// hash table
						if(reportData.get(company) == null){
							tlist = new TagIdList();
							tlist.getTag().add(tagobj);
							reportData.put(company, tlist);
						}
						else{
							tlist = (TagIdList) reportData.get(company);
							tlist.getTag().add(tagobj);
							reportData.put(company, tlist);
						}
						// Add tag/geocoords pair to the reported ones
						session = sesFact.openSession();
						Transaction tx1 = session.beginTransaction();
						ReportedEpcid repId = new ReportedEpcid(epc.getValue(),
								lat, longt);
						try {
							session.save(repId);
							session.getTransaction().commit();
						} catch (RuntimeException e1) {
							if (tx1 != null)
								tx.rollback();
							LOG.error(e1.toString());
							e1.printStackTrace();
						} finally {
							session.close();
						}
					}
				}
				LOG.debug("\n");
				LOG.debug("action:\t\t" + e.getAction().toString() + "\n");
				LOG.debug("bizStep:\t" + e.getBizStep() + "\n");
				LOG.debug("disposition:\t" + e.getDisposition() + "\n");
				LOG.debug("\n");

            } else if (event instanceof TransactionEventType) {
				LOG.debug("[ TransactionEvent ]\n");
				TransactionEventType e = (TransactionEventType) event;
				LOG.debug("parentID:\t" + e.getParentID() + "\n");
				LOG.debug("epcList:\t");
				// This is cheating. We add the parent epc to the list of
				// children so we can parse the list only one :)
				EPC eadd = new EPC();
				eadd.setValue(e.getParentID());
				e.getEpcList().getEpc().add(eadd);

				for (EPC epc : e.getEpcList().getEpc()) {
					String lat = "";
					String longt = "";
					LOG.debug(" '" + epc.getValue() + "'");
					company = getCompanyIdentifier(epc.getValue());
					// Discard epc than are company property
					if (company.equals(genProps.get("companyIdentifier")))
						continue;
					// Non-company property
					Tag tagobj = new Tag();
					tagobj.setId(epc.getValue());
					if (e.getReadPoint() != null) {
						LOG.debug("readPoint:\t" + e.getReadPoint().getId()
								+ "\n");
						if (!readpoints.isEmpty()) {
							for (GeoTagReadPoint gtrp : readpoints) {
								if (gtrp.getId().equals(
										e.getReadPoint().getId())) {
									tagobj.setGeoCoords(gtrp.getLatitude()
											+ ":" + gtrp.getLongtitude());
									lat = gtrp.getLatitude();
									longt = gtrp.getLongtitude();
								}
							}
						}
					} else {
						// If no read point, enter default info from
						// properties file
						LOG.debug("readPoint:\tnull\n");
						tagobj.setGeoCoords(genProps.getProperty("lattitude")
								+ ":" + genProps.getProperty("longtitude"));
					}
					tagobj.setTime(event.getRecordTime());

					// Check to see if tag/geocoords pair is already reported
					session = sesFact.openSession();
					Transaction tx = session.beginTransaction();
					List uriList = null;
					try {
						Query q = session.createQuery("select u.id from "
								+ "ReportedEpcid as u where u.tagId=:tagID "
								+ "and u.latitude=:latTitude "
								+ "and u.longtitude=:longTitude");
						q.setString("tagID", epc.getValue());
						q.setString("latTitude", lat);
						q.setString("longTitude", longt);
						uriList = q.list();
						session.getTransaction().commit();
					} catch (RuntimeException e1) {
						if (tx != null)
							tx.rollback();
						LOG.error(e1.toString());
						e1.printStackTrace();
					} finally {
						session.close();
					}
					if (uriList.isEmpty()) {
						// Enter the new Tag/Geocord pair to the apropriate list
						// If the first for this company, create a new list in the 
						// hash table
						if(reportData.get(company) == null){
							tlist = new TagIdList();
							tlist.getTag().add(tagobj);
							reportData.put(company, tlist);
						}
						else{
							tlist = (TagIdList) reportData.get(company);
							tlist.getTag().add(tagobj);
							reportData.put(company, tlist);
						}
						// Add tag/geocoords pair to the reported ones
						session = sesFact.openSession();
						Transaction tx1 = session.beginTransaction();
						ReportedEpcid repId = new ReportedEpcid(epc.getValue(),
								lat, longt);
						try {
							session.save(repId);
							session.getTransaction().commit();
						} catch (RuntimeException e1) {
							if (tx1 != null)
								tx.rollback();
							LOG.error(e1.toString());
							e1.printStackTrace();
						} finally {
							session.close();
						}
					}

				}
				LOG.debug("\n");
				LOG.debug("action:\t\t" + e.getAction().toString() + "\n");
				LOG.debug("bizStep:\t" + e.getBizStep() + "\n");
				LOG.debug("disposition:\t" + e.getDisposition() + "\n");
				LOG.debug("\n");

            } else if (event instanceof AggregationEventType) {
                LOG.debug("[ AggregationEvent ]\n");
                AggregationEventType e = (AggregationEventType) event;
                LOG.debug("parentID:\t" + e.getParentID() + "\n");
                LOG.debug("childEPCs:\t");
                for (EPC epc : e.getChildEPCs().getEpc()) {
					String lat = "";
					String longt = "";
					LOG.debug(" '" + epc.getValue() + "'");
					company = getCompanyIdentifier(epc.getValue());
					// Discard epc than are company property
					if (company.equals(genProps.get("companyIdentifier")))
						continue;
					Tag tagobj = new Tag();
					tagobj.setId(epc.getValue());
					if (e.getReadPoint() != null) {
						LOG.debug("readPoint:\t" + e.getReadPoint().getId()
								+ "\n");
						if (!readpoints.isEmpty()) {
							for (GeoTagReadPoint gtrp : readpoints) {
								if (gtrp.getId().equals(
										e.getReadPoint().getId())) {
									tagobj.setGeoCoords(gtrp.getLatitude()
											+ ":" + gtrp.getLongtitude());
									lat = gtrp.getLatitude();
									longt = gtrp.getLongtitude();
								}
							}
						}
					} else {
						// If no read point, enter default info from
						// properties file
						LOG.debug("readPoint:\tnull\n");
						tagobj.setGeoCoords(genProps.getProperty("lattitude")
								+ ":" + genProps.getProperty("longtitude"));
					}
					tagobj.setTime(event.getRecordTime());

					// Check to see if tag/geocoords pair is already reported
					session = sesFact.openSession();
					Transaction tx = session.beginTransaction();
					List uriList = null;
					try {
						Query q = session.createQuery("select u.id from "
								+ "ReportedEpcid as u where u.tagId=:tagID "
								+ "and u.latitude=:latTitude "
								+ "and u.longtitude=:longTitude");
						q.setString("tagID", epc.getValue());
						q.setString("latTitude", lat);
						q.setString("longTitude", longt);
						uriList = q.list();
						session.getTransaction().commit();
					} catch (RuntimeException e1) {
						if (tx != null)
							tx.rollback();
						LOG.error(e1.toString());
						e1.printStackTrace();
					} finally {
						session.close();
					}
					if (uriList.isEmpty()) {
						// Enter the new Tag/Geocord pair to the apropriate list
						// If the first for this company, create a new list in the 
						// hash table
						if(reportData.get(company) == null){
							tlist = new TagIdList();
							tlist.getTag().add(tagobj);
							reportData.put(company, tlist);
						}
						else{
							tlist = (TagIdList) reportData.get(company);
							tlist.getTag().add(tagobj);
							reportData.put(company, tlist);
						}
						// Add tag/geocoords pair to the reported ones
						session = sesFact.openSession();
						Transaction tx1 = session.beginTransaction();
						ReportedEpcid repId = new ReportedEpcid(epc.getValue(),
								lat, longt);
						try {
							session.save(repId);
							session.getTransaction().commit();
						} catch (RuntimeException e1) {
							if (tx1 != null)
								tx.rollback();
							LOG.error(e1.toString());
							e1.printStackTrace();
						} finally {
							session.close();
						}
					}			
                }
                LOG.debug("\n");
                LOG.debug("action:\t\t" + e.getAction().toString() + "\n");
                LOG.debug("bizStep:\t" + e.getBizStep() + "\n");
                LOG.debug("disposition:\t" + e.getDisposition() + "\n");
                LOG.debug("\n");

            } else if (event instanceof QuantityEventType) {
            	// Nothing to report here
            	// Just debug print
                LOG.debug("[ QuantityEvent ]\n");
                QuantityEventType e = (QuantityEventType) event;
                LOG.debug("quantity:\t" + e.getQuantity() + "\n");
                LOG.debug("ecpClass:\t" + e.getEpcClass() + "\n");
                LOG.debug("bizStep:\t" + e.getBizStep() + "\n");
                LOG.debug("disposition:\t" + e.getDisposition() + "\n");
                if (e.getReadPoint() != null) {
                    LOG.debug("readPoint:\t" + e.getReadPoint().getId() + "\n");
                } else {
                    LOG.debug("readPoint:\tnull\n");
                }
                LOG.debug("\n");
            }
        }
        
        Enumeration htkeys = reportData.keys();
        
        while (htkeys.hasMoreElements()) {
        	tlist = (TagIdList) reportData.get(htkeys.nextElement());
        	tlist.setIdCount(BigInteger.valueOf((long)tlist.getTag().size()));
			// List are ready. Now prepare and send each document.
			//Prepare Report Document
			Trackerdocument tdoc = new Trackerdocument();
			// Company Info
			CompanyInfo cinfo = new CompanyInfo();
			cinfo.setAddress(genProps.getProperty("companyAddress",
					"DEFAULT ADDRESS"));
			cinfo.setCountry(genProps.getProperty("companyCountryCode",
					"DEFAULT COUNTRY"));
			cinfo.setDescription(genProps.getProperty("companyDesc",
					"DEFAULT COMPANY_DESC"));
			cinfo.setEmail(genProps.getProperty("companyEmail",
					"info@example.com"));
			cinfo.setFax(genProps.getProperty("companyFax", "555-5555555"));
			cinfo.setTel(genProps.getProperty("companyTel", "555-5555555"));
			cinfo.setName(genProps.getProperty("companyName", "Example.inc"));
			cinfo.setRegion(genProps.getProperty("companyRegion",
					"DEFAULT REGION"));
			tdoc.setCompanyInfo(cinfo);
			// Creation Date
			XMLGregorianCalendar xgc = null;
			try {
				DatatypeFactory dtf = DatatypeFactory.newInstance();
				xgc = dtf.newXMLGregorianCalendar(new GregorianCalendar());
				tdoc.setCreationDate(xgc);
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
			// URI
			tdoc.setUri(genProps.getProperty("exportedEpcisInterface"));
			// Insert list of EPC events
			tdoc.setTagIdList(tlist);
			// Add the document to the report list
			trDocumentReportList.add(tdoc);
		}
		return true;
    }
    
    
    /**
     * For each document in the queue, try to locate an appropriate 
     * end point by querying the ONS server. Then attempt to report
     * the document. If all end-point fail, put the document in a 
     * queue. Queue holds the latest 10 documents.
     * 
     * @return Status
     */
    private boolean attermptToReport(){
    	List<Trackerdocument> temp = new ArrayList<Trackerdocument>();
    	boolean RepSuc ;
		// Now we need to query the ONS to find where to report the document
		if(resolver != null){
			for (Trackerdocument tdoc : trDocumentReportList) {
				 Record[] records = new Record[0];
				 List<NAPTRRecord> naptrrec = new ArrayList<NAPTRRecord>();
				 try {
					//records = resolver.getAnyResult(epcToDnsRequest(tdoc.getTagIdList().getTag().get(0).getId()),Type.ANY);
					records = resolver.getAnyResultEPC(tdoc.getTagIdList().getTag().get(0).getId());
					if(records != null){
						for (int i = 0; i < records.length; i++) {
							if(records[i] instanceof NAPTRRecord){
								NAPTRRecord nrec = (NAPTRRecord) records[i];
								LOG.info("NAPTR RECORD:" + nrec.getRegexp());
								// From the available answer, extract ws/tracking points.
								if((nrec.getService().equals("EPC+ws"))&&(nrec.getRegexp().endsWith("tracking.wsdl!"))){
									naptrrec.add(nrec);
								}
							}
						}
						if(naptrrec.isEmpty()){
							LOG.error("No available tracking interface for this class of EPCs");
							LOG.error("Sample epc:" + tdoc.getTagIdList().getTag().get(0).getId());
							temp.add(tdoc);
							continue;
						}
						// Get a sorted by Preference number list of option
						// to comply with ONS standart
						naptrrec = sortNaptrRecordsByPreference(naptrrec);
				    	RepSuc = false;
						// Try one option after the other until we succeed in one
						for (NAPTRRecord o : naptrrec) {
							String ep = o.getRegexp();
							ep = (ep.split("!"))[2];
		                    ep = ep.substring(0, ep.length()-5);
							TrackingInterfaceClient tclient = new TrackingInterfaceClient(ep);
							if(tclient.reportTagList(tdoc)){
						    	RepSuc = true;
								LOG.info("Document reported succesfully");
								LOG.info("Interface endpoint:" + ep);
								LOG.info("Reported a total of " + tdoc.getTagIdList().getIdCount().toString() + "objects");
								break;
							}
						}
						if(!RepSuc){
							LOG.error("All available endpoints failed");
							LOG.error("Sample epc:" + tdoc.getTagIdList().getTag().get(0).getId());
							temp.add(tdoc);
						}
						
					}
					else{
						LOG.error("No DNS records found for this class of EPCs");
						LOG.error("Sample epc:" + tdoc.getTagIdList().getTag().get(0).getId());
						temp.add(tdoc);
						continue;
					}
				} catch (Exception e) {
					LOG.error("Wrong EPC string");
					LOG.error(e.toString());
					e.printStackTrace();
				}				
				
				
			}
			// Make sure we only keep the 10 newer pending documents
			// Rest are purged to preserve some memory
			if(temp.size()>10){
				for (int i = 0; i < (temp.size() - 10); i++) {
					temp.remove(0);
				}
			}
			trDocumentReportList.clear();
			trDocumentReportList.addAll(temp);
		}
		else{
			LOG.error("No available ONS rerver");
			return false;
		} 	
    	return true;
    }
    
    
    /**
     * Sorts the array of NAPTR records return by the ONS server
     * by preference to conform with the ONS standard
     * 
     * @param nlist
     * @return
     */
    private ArrayList<NAPTRRecord> sortNaptrRecordsByPreference(List<NAPTRRecord> nlist){
    	ArrayList<NAPTRRecord> res = new ArrayList<NAPTRRecord>();
    	ArrayList<NAPTRRecord> res0 = new ArrayList<NAPTRRecord>();
    	ArrayList<NAPTRRecord> res1 = new ArrayList<NAPTRRecord>();
    	ArrayList<NAPTRRecord> res2 = new ArrayList<NAPTRRecord>();
    	for (NAPTRRecord naptrRecord : nlist) {
			if(naptrRecord.getPreference() == 0){
				res0.add(naptrRecord);
			}
			if(naptrRecord.getPreference() == 1){
				res1.add(naptrRecord);
			}
			if(naptrRecord.getPreference() == 2){
				res2.add(naptrRecord);
			}
		}
		res.addAll(res0);
		res.addAll(res1);
		res.addAll(res2);
    	return res;
    }
    
    private String prettyStringCalendar(final Calendar cal) {
        if (cal == null) {
            return null;
        }
        // set to current timezone
        cal.setTimeZone(TimeZone.getDefault());
        return TimeParser.format(cal);
    }
    
    
    /**
     * Extract the company identifier from a EPC URI - to filter company owned EPC tags
     * @param epctag
     * @return company identifier
     */
    private String getCompanyIdentifier(String epctag){
    	String serial = "";
    	String company;
    	StringTokenizer st = new StringTokenizer(epctag, ":");
    	while(st.hasMoreTokens()) {
    		serial = st.nextToken();
    	}
    	st = new StringTokenizer(serial,".");
    	company = st.nextToken();
    	return company;
    	
    }
	
    /**
     * Formats a valid DNS request from an EPC tag, according
     * to the procedure described in the ONS standard document
     * Not used any more, functionality transfered to ONS client
     *  
     * @param epc tag
     * @return DNS request
     */
    @SuppressWarnings("unused")
	private String epcToDnsRequest(String epc){
    	LOG.info("Original epc:" + epc);
    	String token;
    	String result;
    	StringTokenizer st = new StringTokenizer(epc, ":");
    	token = st.nextToken(); // urn
    	token = st.nextToken(); // epc
    	token = st.nextToken(); // id
    	result = "." + st.nextToken() + ".id.onsepc.com"; // epc type
    	token = st.nextToken(); // last part
    	
    	st = new StringTokenizer(token,".");
    	result = "." + st.nextToken() + result; // company prefix
    	result = st.nextToken() + result; // epc class
    	LOG.info("DNS request:" + result);
    	return result;
    }
    
    
    /**
     * 
     */
    private void loadProperties(){
    	try {
    		InputStream ins = GetAndReport.class.getResourceAsStream("/tracking.properties");
    		genProps.load(ins);
    		ins.close();	
		} catch (Exception e) {
			LOG.error("ERROR LOADING PROPERTIES");
			e.printStackTrace();
		}   	
    }
    
    
    /**
     * Save an updated properties file. Used to save the last time a poll was made
     * so as to only poll the EPC-IS repository for newer events 
     */
    private void saveProperties(){
    	try {
			FileOutputStream ous = new FileOutputStream(new File(GetAndReport.class.getResource("/tracking.properties").toURI()));
			genProps.store(ous, "");
			ous.close();
    	} catch (FileNotFoundException e) {
    		LOG.error("");
			e.printStackTrace();
		} catch (URISyntaxException e) {
			LOG.error("");
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error("");
			e.printStackTrace();
		} 
    	
    	
    }

}
