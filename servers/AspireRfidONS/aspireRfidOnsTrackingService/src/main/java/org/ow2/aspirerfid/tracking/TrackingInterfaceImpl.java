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

package org.ow2.aspirerfid.tracking;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.jws.WebService;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
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
import org.ow2.aspirerfid.commons.tracking.db.EpcClassProperties;
import org.ow2.aspirerfid.commons.tracking.db.EpcidForeignData;
import org.ow2.aspirerfid.commons.tracking.db.HibernateUtil;
import org.ow2.aspirerfid.commons.tracking.db.RemoteUri;
import org.ow2.aspirerfid.commons.tracking.query.GeoTagReadPoint;
import org.ow2.aspirerfid.commons.tracking.query.MasterDataQueryClient;
import org.ow2.aspirerfid.commons.tracking.query.QueryControlClient;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.CompanyInfo;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.EpcClassProperty;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.Tag;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.TagEvent;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.TagIdList;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.Trackerdocument;
import org.ow2.aspirerfid.commons.utils.TimeParser;

/**
 * @author Kostas Mourtzoukos (komo) e-mail: komo@ait.edu.gr
 *
 */


/**
 * @author maddog
 *
 */
/**
 * @author maddog
 *
 */
@WebService(endpointInterface="org.ow2.aspirerfid.tracking.TrackingInterface")
public class TrackingInterfaceImpl implements TrackingInterface {
	
	RemoteUri remUri = null;
	EpcClassProperties epcClasProp = null;
	
	private static Properties props = new Properties(); 
	private static List<GeoTagReadPoint> readpoints = new ArrayList<GeoTagReadPoint>();
	
	public static final Logger LOG = Logger.getLogger(TrackingInterfaceImpl.class);
	public static final String defaultQueryUrl = "http://localhost:8080/aspireRfidEpcisRepository/query";
	
	
	/**
	 * Obtain a list of TagEvents for a specific EPC Tag
	 * @param epcId The EPC tag
	 * @return List<TagEvent>
	 */
	@SuppressWarnings("unchecked")
	@Override
	
	public List<TagEvent> query(String epcId) {
		try {
			props.load(TrackingInterfaceImpl.class.getResourceAsStream("/tracking.properties"));
			LOG.info("Properties loaded succesfully");
		} catch (IOException e) {
			LOG.error("Error loading properties file");
		} 
		
		// On load, load readpoint list form epcis
		if(readpoints.isEmpty()){
			fetchReadPoints();
		}
		
		
		// Query client
		QueryControlClient queryClient = null;
		
		Session session = null;
		List<TagEvent> tagList = new ArrayList<TagEvent>();
		LOG.debug("GOT HERE");
		LOG.debug("Got request for:" + epcId);
		
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			LOG.info("Hibernate Session Open");
		} catch (HibernateException e) {
			LOG.error("ERROR OPENNING HIBERNATE SESSION");
			LOG.error(e.toString());
			e.printStackTrace();
		}
		
		// First get result stored on the local tracking database
		
		// Create Hibernate Query
		Query q = session.createQuery("select efd.tagId, efd.latitude, efd.longtiitude," +
				" efd.date, efd.remoteUri, efd.epcClassProperties from " +
				"EpcidForeignData as efd where efd.tagId=:tId");
		q.setString("tId", epcId);
		
		// Execute Query
		Iterator results = q.list().iterator();
		LOG.info("Retrieved objects");
		while ( results.hasNext() ) {
			Tag toAddTag = new Tag();
			Object[] row = (Object[]) results.next();
			//id
			toAddTag.setId(row[0].toString());
			LOG.info("Id: " + toAddTag.getId());
			// coordinates
			toAddTag.setGeoCoords(row[1].toString()+ ":" + row[2].toString() );
			LOG.info("Coordinates: " + toAddTag.getGeoCoords());
			// Date Time
			Date date = (Date) row[3];
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			XMLGregorianCalendar date2 = null;
			try {
				date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
			toAddTag.setTime(date2);
			LOG.info("Date/Time"+ toAddTag.getTime().toString());
			
			// Add EPC class properties and UserData
			EpcClassProperty epcCProp = new EpcClassProperty();
			epcCProp.setJSONEncPropList(((EpcClassProperties) row[5]).getProperties());
			toAddTag.setEpcClassProperty(epcCProp); 
			
			TagEvent tEv = new TagEvent();
			tEv.setTag(toAddTag);
			// Remote URI
			RemoteUri rUri = (RemoteUri) row[4];
			
			CompanyInfo cInfo = new CompanyInfo();
			cInfo.setAddress(rUri.getAddress());
			cInfo.setCountry(rUri.getCountry());
			cInfo.setDescription(rUri.getDescription());
			cInfo.setEmail(rUri.getEmail());
			cInfo.setFax(rUri.getFax());
			cInfo.setName(rUri.getName());
			cInfo.setRegion(rUri.getRegion());
			cInfo.setTel(rUri.getTel());
			
			tEv.setCompanyInfo(cInfo);
			LOG.info("URI:" + rUri.getUri());
						
			tagList.add(tEv);
			}
		String queryUrl = "";
		// Now query local ONS for any other data.
		if((queryUrl = props.getProperty("localEpcisInterface"))==null){
			LOG.error("NO EPCIS SETTING FOUND-USING DEFAULT");
			queryUrl = defaultQueryUrl;
		}
		queryClient = new QueryControlClient(queryUrl);
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
        // Select only events for specific TagId
        ArrayOfString tags = new ArrayOfString();

        tags.getString().add(epcId);
        queryParam = new QueryParam();
        queryParam.setName("MATCH_epc");
        queryParam.setValue(tags);
        internalQueryParams.add(queryParam);
        // Create poll and add all parameters
        QueryParams queryParams = new QueryParams();
        queryParams.getParam().addAll(internalQueryParams);
        
        Poll poll = new Poll();
        poll.setQueryName("SimpleEventQuery");
        poll.setParams(queryParams);
        
        try {
            QueryResults epcis_results = queryClient.poll(poll);
            LOG.info("RUN QUERY");
            if (epcis_results != null && epcis_results.getResultsBody() != null && epcis_results.getResultsBody().getEventList() != null) {
                //processEvents(epcis_results.getResultsBody().getEventList().getObjectEventOrAggregationEventOrQuantityEvent(),epcId);
                tagList.addAll(processEvents(epcis_results.getResultsBody().getEventList().getObjectEventOrAggregationEventOrQuantityEvent(),epcId));
            }

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
        
        

        
		return tagList;
	}

	/**
	 * Report a list of TagEvents, allong with compan indormation 
	 * @param epcTagList
	 * @return Status
	 */
	@SuppressWarnings("unchecked")
	@Override
	
	public boolean report(Trackerdocument epcTagList) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			LOG.info("Hibernate Session Open");
		} catch (HibernateException e) {
			LOG.error("ERROR OPENNING HIBERNATE SESSION");
			LOG.error(e.toString());
			e.printStackTrace();
		}
		
        Query q = session.createQuery("select u.id from " +
        "RemoteUri as u where u.uri=:uRi");
        q.setString("uRi", epcTagList.getUri());
        List uriList = q.list();
        session.getTransaction().commit();
		if(uriList.isEmpty()){
			// uri does not exist, must be created
			LOG.info("RECIEVED URI:" + epcTagList.getUri() + " IS NEW");
			remUri = new RemoteUri(epcTagList.getUri());
			// Company Name
			if(epcTagList.getCompanyInfo().getName()!= null){
				remUri.setName(epcTagList.getCompanyInfo().getName());
				LOG.info("Company Name:"+ epcTagList.getCompanyInfo().getName());
			}
			else{
				remUri.setName("");
				LOG.info("Company name is null");
			}
			// Company Description
			if(epcTagList.getCompanyInfo().getDescription()!= null){
				remUri.setDescription(epcTagList.getCompanyInfo().getDescription());
				LOG.info("Company Description:"+ epcTagList.getCompanyInfo().getDescription());
			}
			else{
				remUri.setDescription("");
				LOG.info("Company Description is null");
			}			
			// Company Address
			if(epcTagList.getCompanyInfo().getAddress()!= null){
				remUri.setAddress(epcTagList.getCompanyInfo().getAddress());
				LOG.info("Address:" + epcTagList.getCompanyInfo().getAddress());
			}
			else{
				remUri.setAddress("");
				LOG.info("Adress is null");
			}
			// Country Code
			if (epcTagList.getCompanyInfo().getCountry()!= null) {
				remUri.setCountry(epcTagList.getCompanyInfo().getCountry());
				LOG.info("Country Code:" + epcTagList.getCompanyInfo().getCountry());
			}
			else{
				remUri.setCountry("");
				LOG.info("Country Code is null");
			}
			// Region
			if (epcTagList.getCompanyInfo().getRegion()!= null) {
				remUri.setRegion(epcTagList.getCompanyInfo().getRegion());
				LOG.info("Region:" + epcTagList.getCompanyInfo().getRegion());
			}
			else{
				remUri.setRegion("");
				LOG.info("Region is null");
			}
			// E-mail
			if (epcTagList.getCompanyInfo().getEmail()!= null) {
				remUri.setEmail(epcTagList.getCompanyInfo().getEmail());
				LOG.info("Email:" + epcTagList.getCompanyInfo().getEmail());
			}
			else{
				remUri.setEmail("null@null");
				LOG.info("Email is null");
			}
			// Telephone
			if (epcTagList.getCompanyInfo().getTel()!= null) {
				remUri.setTel(epcTagList.getCompanyInfo().getTel().toString());
				LOG.info("Telephone:" + epcTagList.getCompanyInfo().getTel().toString());
			}
			else{
				remUri.setTel("+00-000-0000000");
				LOG.info("Telephone is null");
			}			
			// Fax
			if (epcTagList.getCompanyInfo().getFax()!= null) {
				remUri.setFax(epcTagList.getCompanyInfo().getFax().toString());
				LOG.info("Fax:" + epcTagList.getCompanyInfo().getFax().toString());
			}
			else{
				remUri.setFax("+00-000-0000000");
				LOG.info("Fax is null");
			}
					
		}
		else{
			session.beginTransaction();
			remUri = (RemoteUri) session.get(RemoteUri.class, (Integer) uriList.get(0));
			session.getTransaction().commit();	
			//remUri = (RemoteUri) uriList.get(0);
			LOG.info("RECIEVED URI:" + epcTagList.getUri() + " ALREADY EXISTS");
		}
		// remUri now contains the correct URI for all the tags to be reported
		// URI was either retrieved from the database, or created if new
		
		try {
			TagIdList tlist = epcTagList.getTagIdList();
			for (Tag tagobj : tlist.getTag()) {
				GregorianCalendar cal = tagobj.getTime().toGregorianCalendar();
				// Create new tag with Id, Date, Time, Uri 
				EpcidForeignData epcTag = new EpcidForeignData(remUri, tagobj.getId(), cal.getTime());
				StringTokenizer st = new StringTokenizer(tagobj.getGeoCoords(), ":"); 
				epcTag.setLatitude(st.nextToken());
				epcTag.setLongtiitude(st.nextToken());
				// Set UserData if applicable
				if(tagobj.getUserData()!= null){
					epcTag.setUserData(tagobj.getUserData().toString());
				}
				session.beginTransaction();
		        Query q2 = session.createQuery("select u.id from " +
		        "EpcClassProperties as u where u.properties=:uProp");
		        q2.setString("uProp", tagobj.getEpcClassProperty().getJSONEncPropList());
		        List propList = q2.list();
		        session.getTransaction().commit();
				if(propList.isEmpty()){
					epcClasProp = new EpcClassProperties(tagobj.getEpcClassProperty().getJSONEncPropList());	
				}
				else{
					session.beginTransaction();
					epcClasProp = (EpcClassProperties) session.get(EpcClassProperties.class, (Integer) propList.get(0));
					session.getTransaction().commit();		
				}
				
				/**
				 * EPC CLASS PROPERTIES HOOK HERE
				 * 
				 */
				epcTag.setEpcClassProperties(epcClasProp);
				epcClasProp.addEpcidForeignDatas(epcTag);
				remUri.addEpcidForeignDatas(epcTag);
				session.beginTransaction();
				session.save(epcClasProp);
				session.save(remUri);
				session.save(epcTag);
				LOG.info(tagobj.getId());
				LOG.info(tagobj.getTime().toString());
				LOG.info(tagobj.getGeoCoords());
				LOG.info("--");
				session.getTransaction().commit();
			}	
			
			session.close();
		} catch (HibernateException e) {
			LOG.error(e.toString());
			e.printStackTrace();
			return false;
		}
				
		return true;
	}
	
    
	
	/**
	 * Format a calendar to the default system time/date format
	 * @param cal A standard Java Calendar
	 * @return Formated output
	 */
	private static String prettyStringCalendar(final Calendar cal) {
        if (cal == null) {
            return null;
        }
        // set to current timezone
        cal.setTimeZone(TimeZone.getDefault());
        return TimeParser.format(cal);
    }
		
    /**
     * Process and filter bulk events for return
     * @param eventList List of events
     * @param tid EPC Tag in original query (since some events have multiple tags)
     * @return final list of events.
     */
    private List<TagEvent> processEvents(final List<Object> eventList, String tid) {
        int nofEvents = eventList.size();
        List<TagEvent> retL = new ArrayList<TagEvent>();
        LOG.debug("\n\n" + nofEvents + " events returned by the server:\n\n");
        for (Object o : eventList) {
            if (o instanceof JAXBElement<?>) {
                o = ((JAXBElement<?>) o).getValue();
            }
            TagEvent tevent = new TagEvent();
            // EPCIS INTERFACE
            tevent.setRemoteUri(props.getProperty("localEpcisInterface"));
            
            // COMPANY INFO
            CompanyInfo cinfo = new CompanyInfo();
            cinfo.setAddress(props.getProperty("companyAddress","DEFAULT ADDRESS"));
            cinfo.setCountry(props.getProperty("companyCountryCode","NOCOUNTRY"));
            cinfo.setDescription(props.getProperty("companyDesc","COMPANY_DESC"));
            cinfo.setEmail(props.getProperty("companyEmail", "info@example.com"));
            cinfo.setFax(props.getProperty("companyFax","555-5555555"));
            cinfo.setTel(props.getProperty("companyTel", "555-5555555"));
            cinfo.setName(props.getProperty("companyName", "Example.inc"));
            cinfo.setRegion(props.getProperty("companyRegion", "NOREGION"));
            
            tevent.setCompanyInfo(cinfo);
            
            Tag tag = new Tag();
            
            EPCISEventType event = (EPCISEventType) o;
            LOG.debug("[ EPCISEvent ]\n");
            String eventTime = prettyStringCalendar(event.getEventTime().toGregorianCalendar());
            LOG.debug("eventTime:\t" + eventTime + "\n");
            String recordTime = prettyStringCalendar(event.getRecordTime().toGregorianCalendar());
            LOG.debug("recordTime:\t" + recordTime + "\n");
            LOG.debug("timeZoneOffset:\t" + event.getEventTimeZoneOffset() + "\n");
            
            tag.setId(tid);
            tag.setTime(event.getEventTime());
            tag.setEpcClassProperty(null);
            tag.setUserData(null);
            
            if (event instanceof ObjectEventType) {
            	LOG.debug("[ ObjectEvent ]\n");
                ObjectEventType e = (ObjectEventType) event;               
                if (e.getReadPoint() != null) {
                    LOG.debug("readPoint:\t" + e.getReadPoint().getId() + "\n");
                    if (!readpoints.isEmpty()) {
						for (GeoTagReadPoint gtrp : readpoints) {
							if(gtrp.getId().equals(e.getReadPoint().getId())){
								tag.setGeoCoords(gtrp.getLatitude() +  ":" + gtrp.getLongtitude());
							}
						}
					}
                } else {
                	//If no read point, enter default info from properties file
                    LOG.debug("readPoint:\tnull\n");
                    tag.setGeoCoords(props.getProperty("lattitude")  + ":" + props.getProperty("longtitude"));
                }
                LOG.debug("\n");

            } else if (event instanceof TransactionEventType) {
                LOG.debug("[ TransactionEvent ]\n");
                TransactionEventType e = (TransactionEventType) event;
                if (e.getReadPoint() != null) {
                    LOG.debug("readPoint:\t" + e.getReadPoint().getId() + "\n");
                    if (!readpoints.isEmpty()) {
						for (GeoTagReadPoint gtrp : readpoints) {
							if(gtrp.getId().equals(e.getReadPoint().getId())){
								tag.setGeoCoords(gtrp.getLatitude() +  ":" + gtrp.getLongtitude());
							}
						}
					}
                } else {
                	//If no read point, enter default info from properties file
                    LOG.debug("readPoint:\tnull\n");
                    tag.setGeoCoords(props.getProperty("lattitude")  + ":" + props.getProperty("longtitude"));
                }
                LOG.debug("\n");

            } else if (event instanceof AggregationEventType) {
                LOG.debug("[ AggregationEvent ]\n");
                AggregationEventType e = (AggregationEventType) event;
                if (e.getReadPoint() != null) {
                    LOG.debug("readPoint:\t" + e.getReadPoint().getId() + "\n");
                    if (!readpoints.isEmpty()) {
						for (GeoTagReadPoint gtrp : readpoints) {
							if(gtrp.getId().equals(e.getReadPoint().getId())){
								tag.setGeoCoords(gtrp.getLatitude() +  ":" + gtrp.getLongtitude());
							}
						}
					}
                } else {
                	//If no read point, enter default info from properties file
                    LOG.debug("readPoint:\tnull\n");
                    tag.setGeoCoords(props.getProperty("lattitude")  + ":" + props.getProperty("longtitude"));
                }
                LOG.debug("\n");

            } else if (event instanceof QuantityEventType) {
                LOG.debug("[ QuantityEvent ]\n");
                QuantityEventType e = (QuantityEventType) event;
                if (e.getReadPoint() != null) {
                    LOG.debug("readPoint:\t" + e.getReadPoint().getId() + "\n");
                    if (!readpoints.isEmpty()) {
						for (GeoTagReadPoint gtrp : readpoints) {
							if(gtrp.getId().equals(e.getReadPoint().getId())){
								tag.setGeoCoords(gtrp.getLatitude() +  ":" + gtrp.getLongtitude());
							}
						}
					}
                } else {
                	//If no read point, enter default info from properties file
                    LOG.debug("readPoint:\tnull\n");
                    tag.setGeoCoords(props.getProperty("lattitude")  + ":" + props.getProperty("longtitude"));
                }
                LOG.debug("\n");
            }
            tevent.setTag(tag);
            retL.add(tevent);
        }
        return retL;
    }
    
    
    /**
     * 	Loads all readpoint information from the EPCIS repository
     *  If no GeoCoding is available in the EPCIS, default is added.
     */
    private void fetchReadPoints(){
    	readpoints.clear();
    	LOG.info("FETCHING READ POINTS");
    	String queryUrl = ""; 
    	MasterDataQueryClient queryClient = null;
		if((queryUrl = props.getProperty("localEpcisInterface"))==null){
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
    				rPoint.setLatitude(props.getProperty("lattitude"));
    				rPoint.setLongtitude(props.getProperty("longtitude"));
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

	
}
