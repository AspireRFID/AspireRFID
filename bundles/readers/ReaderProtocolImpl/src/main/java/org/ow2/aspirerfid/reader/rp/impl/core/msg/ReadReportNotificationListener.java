/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Accada (www.accada.org).
 *
 * Accada is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Accada is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Accada; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

/*
 * Created on 12.02.2004
 *
 */
package org.ow2.aspirerfid.reader.rp.impl.core.msg;

import java.io.StringWriter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


import org.ow2.aspirerfid.reader.rp.impl.core.DataSelector;
import org.ow2.aspirerfid.reader.rp.impl.core.FieldName;
import org.ow2.aspirerfid.reader.rp.impl.core.NotificationChannel;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderDevice;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderProtocolException;
import org.ow2.aspirerfid.reader.rp.impl.core.Trigger;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.EPC;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.Notification;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.ReadReportType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.ReaderType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.SourceInfoType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.TagFieldValueParamType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.TagType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.TagEventType.EventTriggers;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.util.CompareSet;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.util.HexUtil;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.ReadReport;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.ReaderInfoType;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.SourceReport;
import org.apache.log4j.Logger;


/**
 * Implementation of the interface <code>NotificationListener</code>. The
 * reader notifies the <code>ReadReportNotificationListener</code> about a new
 * ReadReport which has to be sent to the corresponding host.
 *
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 *
 */

public class ReadReportNotificationListener implements NotificationListener {

	//============================= fields ==========================================

	/** The logger. */
	private static Logger log ;
	/** Instance of incoming message buffer. */
	private static ReadReportNotificationListener messageBuffer;

	/** The <code>OutgoingMessageDispatcher</code> which is used to deliver outgoing messages. */
	private OutgoingMessageDispatcher outMsgDispatcher;

	/** The singleton factory used to create empty replies. */
	private static org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.ObjectFactory notificationFactory;

	/** The message serializer. */
	private MessageSerializer serializer = null;

	/** Container for the notification channel connections. */
	private NotificationChannelConnections notifyChannelConnections = null;

	//================================= constructors =====================================

	/**
	 * Constructs an <code>ReadReportNotificationListener</code> object.
	 */
	private ReadReportNotificationListener()
	{
		log = Logger.getLogger(getClass().getName());
		notifyChannelConnections = NotificationChannelConnections.getInstance();
		notificationFactory = new org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.ObjectFactory();
	}

	/**
	 * Creates the single instance of a
	 * <code>ReadReportNotificationListener</code>.
	 */
	public static ReadReportNotificationListener getInstance(){
		if (messageBuffer == null){
			Clients clients = Clients.getInstance();
			OutgoingMessageDispatcher dispatcher = OutgoingMessageDispatcher.getInstance();
			dispatcher.initialize(clients);
			messageBuffer = getInstance(dispatcher);
		}
		return messageBuffer;
	}

	/**
	 * Creates the single instance of a
	 * <code>ReadReportNotificationListener</code>.
	 *
	 * @param outMsgDispatcher
	 *            The <code>OutgoingMessageDispatcher</code> to use for
	 *            sending the notifications to the host.
	 */
	public static ReadReportNotificationListener getInstance(OutgoingMessageDispatcher outMsgDispatcher){
		if (messageBuffer == null){
			messageBuffer = new ReadReportNotificationListener();
			messageBuffer.initialize(outMsgDispatcher);
		}
		return messageBuffer;
	}

	//=================================== methods =======================================

	public void initialize(OutgoingMessageDispatcher outMsgDispatcher) {
		this.outMsgDispatcher = outMsgDispatcher;
	}

	/**
	 * @see NotificationListener#notifyHost(ReadReport, String, DataSelector)
	 */
	public synchronized void notifyHost(ReadReport report, String channelName, DataSelector dataSelector) {
		try {
			String outMsg = null;
			Clients clients = Clients.getInstance();

			Notification notification = createNotification(report, channelName, dataSelector);

			// TODO IDs der notifications werden einfach raufgez�hlt. Korrekt so??
			notification.setId(Integer.toString(NotificationChannelConnections.nextMessageId()));

			MessageFactory factory = MessageFactory.getInstance();
			MessageFormat format = notifyChannelConnections.getChannelFormat(channelName);
			serializer = factory.createSerializer(format, Context.NOTIFICATION);
			outMsg = serializer.serialize(notification);

			log.debug("Notification message: " + outMsg);

			NotificationChannel notifChan = null;
			try {
				notifChan = ReaderDevice.getInstance().getNotificationChannel(channelName);
			} catch (ReaderProtocolException rpe) {
				log.error("Unknown NotificationChannel: " + channelName);
			}

			Date curTime = new Date();

			if (notifChan != null) {
				notifChan.setLastNotificationAttempt(curTime);
			}

			outMsgDispatcher.sendRequest(clients.getNotificationChannelClientID(channelName), outMsg);

			if (notifChan != null) {
				notifChan.setLastSuccessfulNotification(curTime);
			}


		} catch (MessageSerializingException e) {
			log.error(e);
		}
	}

	/**
	 * Creates a JAXB notification using the ReadReport from the ReaderDevice.
	 *
	 * @param report
	 *            The <code>ReadReport</code> from the ReaderDevice
	 * @param channelName
	 *            The name of the <code>NotificationChannel</code> to use to
	 *            send the <code>ReadReport</code>.
	 * @param dataSelector
	 *            The DataSelector to check, which information properties are
	 *            expected.
	 * @return the JAXB notification to serialize in XML or Text format.
	 */
	private Notification createNotification(ReadReport report, String channelName, DataSelector dataSelector) {
		try {
			Notification notification = notificationFactory
					.createNotification();

			// create a set with all fields from the data selector
			String[] fields = dataSelector.getAllFieldNames();
			CompareSet dataSelectorFields = new CompareSet(fields.length);
			for (int i = 0; i < fields.length; i++) {
				dataSelectorFields.add(fields[i]);
			}

			log.debug("All data selector fields associated with this notification "
					+ dataSelectorFields.toString());
			//add the reader info
			//FIXME: @Markus: Bug in report -> containsReaderInfo auch true, wenn alle Werte von ReaderInfo auf null sind
			if (report.containsReaderInfo()) {
				ReaderInfoType readerInfo = report.getReaderInfo();
				ReaderType readerType = notificationFactory.createReaderType();

				if (readerInfo.getEpc() != null) {
					EPC epc = notificationFactory.createEPC();
					epc.setValue(readerInfo.getEpc());
					readerType.setReaderEPC(epc);
				}

				readerType.setReaderName(readerInfo.getName());
				if (readerInfo.getHandle() != -1) {
					readerType.setReaderHandle(readerInfo.getHandle());
				}
				readerType.setReaderRole(readerInfo.getRole());

				org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.EventTimeType time = notificationFactory.createEventTimeType();
				if (readerInfo.getNowTick() >= 0) {
					time.setEventTimeTick(Long.toString(readerInfo.getNowTick()));
				}
				if (readerInfo.getNowUTC() != null) {
					time.setEventTimeUTC(calendarToXMLGregorianCalendar(toCalendar(readerInfo.getNowUTC())));
				}

				notification.setReader(readerType);
			}

			//Name of the Trigger that caught the notification
			if (report.containsNotificationInfo()) {
				String triggerName = null;
				Iterator it = report.getNotificationInfo().getChannelTriggers().keySet().iterator();
				/* there should be only one entry in the hashtable, so take the first one */
				if (it.hasNext()) {
					triggerName = (String)it.next();
				}
				notification.setNotifyTriggerName(triggerName);
			}

			//Name of the NotificationChannel on which the Notification is sent on
			notification.setNotifyChannelName(channelName);

			//create and add ReadReport
			ReadReportType reportItems = notificationFactory.createReadReportType();
			List sourceList = reportItems.getSourceReport();

			//add the source reports
			Iterator sourceReportIt = report.getSourceReports().values().iterator();
			while (sourceReportIt.hasNext()) {
				org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.ReadReportType.SourceReport sourceReport = notificationFactory
						.createReadReportTypeSourceReport();
				SourceReport rdSourceReport = (SourceReport) sourceReportIt.next();

				//set the source info
				if (rdSourceReport.containsSourceInfo()) {
					org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.SourceInfoType sourceInfo = notificationFactory
							.createSourceInfoType();
					org.ow2.aspirerfid.reader.rp.impl.core.readreport.SourceInfoType rdSourceInfo = rdSourceReport.getSourceInfo();
					if (rdSourceInfo.getSourceFrequency() != -1) {
						sourceInfo.setSourceFrequency(Integer.toString(rdSourceInfo
								.getSourceFrequency()));
					}
					if (dataSelectorFields != null
							&& ((dataSelectorFields.contains(FieldName.SOURCE_NAME))
									|| (dataSelectorFields.contains(FieldName.ALL_SOURCE))
									|| (dataSelectorFields.contains(FieldName.ALL)) )) {

						sourceInfo.setSourceName(rdSourceInfo.getSourceName());
						log.debug("Adding source name " + rdSourceInfo.getSourceName() + " when generating XML instance of notification");
					}
					else {
						log.debug("Neglecting source name when generating XML instance of notification");
					}
					sourceInfo.setSourceProtocol(rdSourceInfo.getSourceProtocol());
					sourceReport.setSourceInfo(sourceInfo);

				}

				//add all the tags
				List tagList = sourceReport.getTag();
				//Collection tags = report.getAllTags().values();
				Collection tags = rdSourceReport.getAllTags().values();
				Iterator tagIt = tags.iterator();
				while (tagIt.hasNext()) {
					org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagType rdTag = (org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagType) tagIt
							.next();
					org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.TagType tag = notificationFactory
							.createTagType();
					if (dataSelectorFields != null
							&& (dataSelectorFields.contains(FieldName.TAG_ID)
									|| dataSelectorFields.contains(FieldName.ALL)
									|| dataSelectorFields
											.contains(FieldName.ALL_TAG) || dataSelectorFields
									.contains(FieldName.ALL_SUPPORTED))) {
						tag.setTagID(HexUtil.hexToByteArray(rdTag.getId()));
					}
					tag.setTagIDAsPureURI(rdTag.getIdAsPureURI());
					tag.setTagIDAsTagURI(rdTag.getIdAsTagURI());
					tag.setTagType(rdTag.getTagType());
					getTagEvents(tag, rdTag);
					getTagFields(tag, rdTag);
					tagList.add(tag);
				}
				//add source report to the source list
				sourceList.add(sourceReport);
			}

			notification.getReadReport().add(reportItems);

			return notification;
		} catch (Exception e) {
			log.error(e);
			return null;
		} 
		}




	/**
	 * Insert all the ReadReports from the ReaderDevice ReadReport into the
	 * ReadReport of the JAXB classes.
	 *
	 * @param msgReadReport
	 *            The JAXB ReadReport
	 * @param rdReadReport
	 *            The ReaderDevice ReadReport
	 * @param dataSelectorFields
	 * @return List with all JABX ReadReports
	 * @throws JAXBException
	 */
	private List getReadReports(List msgReadReport, ReadReport rdReadReport, CompareSet dataSelectorFields) throws JAXBException {
		ReadReportType reportItems = notificationFactory.createReadReportType();
		List sourceList = reportItems.getSourceReport();

		//get all Source reports
		Iterator sourceReportIt = rdReadReport.getSourceReports().values().iterator();
		while (sourceReportIt.hasNext()) {
			org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.ReadReportType.SourceReport sourceReport = notificationFactory.createReadReportTypeSourceReport();
			SourceReport rdSourceReport = (SourceReport) sourceReportIt.next();

			//add the source info
			if (rdSourceReport.containsSourceInfo()) {
				SourceInfoType sourceInfo = notificationFactory.createSourceInfoType();
				org.ow2.aspirerfid.reader.rp.impl.core.readreport.SourceInfoType rdSourceInfo = rdSourceReport.getSourceInfo();
				if (rdSourceInfo.getSourceFrequency() >= 0) {
					sourceInfo.setSourceFrequency(Integer.toString(rdSourceInfo.getSourceFrequency()));
				}
				if (dataSelectorFields.contains(FieldName.SOURCE_NAME)) {
					sourceInfo.setSourceName(rdSourceInfo.getSourceName());
				}
				sourceInfo.setSourceProtocol(rdSourceInfo.getSourceProtocol());
				sourceReport.setSourceInfo(sourceInfo);
				//sourceList.add(sourceInfo);
			}

			//add all the tags
			List tagList = sourceReport.getTag();
			Collection tags = rdSourceReport.getAllTags().values();
			Iterator it = tags.iterator();
			while(it.hasNext()) {
				org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagType rdTag = (org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagType)it.next();
				TagType tag = notificationFactory.createTagType();
				if (dataSelectorFields != null && (dataSelectorFields.contains(FieldName.TAG_ID) || dataSelectorFields.contains(FieldName.ALL)  || dataSelectorFields.contains(FieldName.ALL_TAG)  || dataSelectorFields.contains(FieldName.ALL_SUPPORTED))) {
					tag.setTagID(HexUtil.hexToByteArray(rdTag.getId()));
				}
				tag.setTagIDAsPureURI(rdTag.getIdAsPureURI());
				tag.setTagIDAsTagURI(rdTag.getIdAsTagURI());
				tag.setTagType(rdTag.getTagType());
				tagList.add(tag);
			}

			//add source report the the sourceList
			sourceList.add(sourceReport);
		}
		//add read report to the report list
		msgReadReport.add(reportItems);
		return msgReadReport;
	}

	/**
	 * Insert all the tag events from the ReaderDevice TagType into the TagType
	 * of the JAXB classes.
	 *
	 * @param msgTag
	 *            The JAXB TagType
	 * @param rdTag
	 *            The ReaderDevice TagType
	 * @return List with all JAXB TagEvents
	 * @throws JAXBException
	 */
	private List getTagEvents(org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.TagType msgTag, org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagType rdTag) throws JAXBException, DatatypeConfigurationException {
		List eventList = msgTag.getTagEvent();
		Collection tagEvents = rdTag.getAllTagEvents().values();
		Iterator it = tagEvents.iterator();
		while(it.hasNext()) {
			org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.TagEventType tagEvent = notificationFactory.createTagEventType();
			org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagEventType rdEvent = (org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagEventType)it.next();
			tagEvent.setEventType(rdEvent.getEventType());

			org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.EventTimeType time = notificationFactory.createEventTimeType();
			if (rdEvent.getTimeTick() >= 0) {
				time.setEventTimeTick(Long.toString(rdEvent.getTimeTick()));
			}
			if (rdEvent.getTimeUTC() != null) {
				time.setEventTimeUTC(calendarToXMLGregorianCalendar(toCalendar(rdEvent.getTimeUTC())));
			}
			tagEvent.setTime(time);

			EventTriggers eventTriggers = notificationFactory.createTagEventTypeEventTriggers();
			List eventTriggerList = eventTriggers.getTrigger();
			Collection evTriggers = rdEvent.getEventTriggers().values();
			Iterator trgIt = evTriggers.iterator();

			if (!trgIt.hasNext()) {
				eventTriggerList.add("NoTrigger");
			}

			while(trgIt.hasNext()) {
				Trigger rdTrigger = (Trigger)trgIt.next();
				eventTriggerList.add(rdTrigger.getName());
			}

			tagEvent.setEventTriggers(eventTriggers);
			eventList.add(tagEvent);
		}
		return eventList;
	}

	/**
	 * Insert all the tag fields from the ReaderDevice TagField into the
	 * TagField of the JAXB classes.
	 *
	 * @param msgTag
	 *            The JAXB TagType
	 * @param rdTag
	 *            The ReaderDevice TagType
	 * @return List with all JAXB TagFields
	 * @throws JAXBException
	 */
	private List getTagFields(TagType msgTag, org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagType rdTag) throws JAXBException {
		List fieldList = msgTag.getTagFields();

		Collection coll = rdTag.getAllTagFields().values();
		Iterator it = coll.iterator();
		while(it.hasNext()) {
			TagFieldValueParamType tfvpReader = (TagFieldValueParamType)it.next();
			org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.TagFieldValueParamType tfvp = notificationFactory.createTagFieldValueParamType();
			tfvp.setTagFieldName(tfvpReader.getTagFieldName());
			tfvp.setTagFieldValue(tfvpReader.getTagFieldValue());
			fieldList.add(tfvp);
		}
		return fieldList;
	}

	//TODO: Veschieben in eigenen JUnit Test
	public static void serialisationTest() {
//		Marshal into a StringBuffer
		StringWriter sw = new StringWriter();
		Marshaller notificationMarshaller = null;
		JAXBContext notificationContext = null;
		String NOTIFICATION_PACKAGE = "org.ow2.aspirerfid.reader.rp.impl.core.msg.notification";
		org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.ObjectFactory notificationFactory = new org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.ObjectFactory();

		try {
			notificationContext = JAXBContext.newInstance(NOTIFICATION_PACKAGE);

			notificationMarshaller = notificationContext.createMarshaller();
			notificationMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );

			Notification notification = notificationFactory.createNotification();

			notification.setId(Integer.toString(NotificationChannelConnections.nextMessageId()));
			notification.setNotifyChannelName("notifyChannelName");
			notification.setNotifyTriggerName("notifyTriggerName");

			ReaderType reader = notificationFactory.createReaderType();
			reader.setReaderName("Name des Readers");
			reader.setReaderRole("Seine Rolle");
			notification.setReader(reader);


			//Readreport kreiren
			List readReportList = notification.getReadReport();
			ReadReportType report = notificationFactory.createReadReportType();

			//SourceReport und Tag sind in einer xsd:choice, also kann es nur eines von beiden haben!!
			//SourceReport kreiren
			List sourceList = report.getSourceReport();
			ReadReportType.SourceReport source = notificationFactory.createReadReportTypeSourceReport();
			source.getTag(); // TODO: f�llen der tags welche zu dieser source geh�ren
			SourceInfoType sourceInfo = notificationFactory.createSourceInfoType();
			sourceInfo.setSourceName("IRGENDEINE SOURCE");
			sourceInfo.setSourceProtocol("deren Protokoll");
			source.setSourceInfo(sourceInfo);
			sourceList.add(source);

			//TagReport kreiren
			List tagList = report.getTag();
			TagType tag = notificationFactory.createTagType();
			tag.setTagID(HexUtil.hexToByteArray("F0F0"));
			tag.setTagIDAsPureURI("PureURI");
			tag.setTagIDAsTagURI("PureTagURI");
			tagList.add(tag);



			readReportList.add(report);




			notificationMarshaller.marshal(notification,sw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println(sw.getBuffer().toString());


	}

	/**
	 * Converts a <code>Date</code> into a <code>Calendar</code> object
	 * using the UTC timezone.
	 *
	 * @param d
	 *            The <code>Date</code>
	 * @return The corresponding <code>Calendar</code>
	 */
	private Calendar toCalendar(Date d) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+0"));
		cal.setTime(d);
		return cal;
	}

	private XMLGregorianCalendar calendarToXMLGregorianCalendar(Calendar cal) throws DatatypeConfigurationException{
		GregorianCalendar gregCal = new GregorianCalendar();
		gregCal.setTimeInMillis(cal.getTimeInMillis());
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregCal);
	}

	public static void main(String[] args) {
		ReadReportNotificationListener.serialisationTest();
	}



}