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

package org.ow2.aspirerfid.reader.rp.impl.core.msg;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.Notification;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.AddressReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.BooleanReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.DataSelectorListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.DataSelectorReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.DataSelectorReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.EpcReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ErrorType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.EventTypeListReturnValue;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.EventTypeReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.FieldNameListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.FieldNameReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.HexStringReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.IntReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.NotificationChannelListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.NotificationChannelReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.NotificationChannelReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ReadPointListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ReadPointReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ReadPointReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ReadReportType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ReaderDeviceReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.Reply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.SourceInfoType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.SourceListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.SourceReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.SourceReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.StringListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.StringReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagEventType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagFieldListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagFieldReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagFieldReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagFieldValueParamType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagSelectorListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagSelectorReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagSelectorReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TimeStampReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TriggerListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TriggerReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TriggerReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TriggerTypeListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TriggerTypeReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TriggerTypeReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TriggerValueReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.NotificationChannelReply.ReadQueuedData;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ReadReportType.SourceReport;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.SourceReply.RawReadIDs;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.SourceReply.Read;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.SourceReply.ReadIDs;


/**
 * Serializes a JAXB object tree into a String using the text message format
 * defined in the EPC Reader Protocol Specification.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 */
public class TextMessageSerializer implements MessageSerializer {
	/* String tokens */
	public static final String EXCLAMATION = "!";

	public static final String TERMINATOR = ">";

	public static final String COMMA = ",";

	public static final String DOUBLEQUOTE = "\"";

	public static final String LF = "\n";

	public static final String ERR = "ERR";

	public static final String OK = "OK";

	public static final String GOODBYE = "GOODBYE";

	public static final String ISO_8601_MILLIS_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSz"; //$NON-NLS-1$

	public String serialize(Reply r) throws MessageSerializingException {
		StringWriter writer = new StringWriter();
		writer.write(serializeReplyHeader(r));
		if (r.getReaderDevice() != null) {
			ReaderDeviceReply rdReply = r.getReaderDevice();
			writer.write(serializeReaderDevice(rdReply));
		} else if (r.getSource() != null) {
			SourceReply srcReply = r.getSource();
			writer.write(serializeSource(srcReply));
		} else if (r.getReadPoint() != null) {
			ReadPointReply rpReply = r.getReadPoint();
			writer.write(serializeReadPoint(rpReply));
		} else if (r.getTagSelector() != null) {
			TagSelectorReply tsReply = r.getTagSelector();
			writer.write(serializeTagSelector(tsReply));
		} else if (r.getDataSelector() != null) {
			DataSelectorReply dsReply = r.getDataSelector();
			writer.write(serializeDataSelector(dsReply));
		} else if (r.getNotificationChannel() != null) {
			NotificationChannelReply ncReply = r.getNotificationChannel();
			writer.write(serializeNotificationChannel(ncReply));
		} else if (r.getTrigger() != null) {
			TriggerReply trgReply = r.getTrigger();
			writer.write(serializeTrigger(trgReply));
		} else if (r.getFieldName() != null) {
			FieldNameReply fnReply = r.getFieldName();
			writer.write(serializeFieldName(fnReply));
		} else if (r.getTagField() != null) {
			TagFieldReply tfReply = r.getTagField();
			writer.write(serializeTagField(tfReply));
		} else if (r.getError() != null) {
			ErrorType error = r.getError();
			writer.write(serializeError(r.getResultCode(), error));
		}

		writer.write(TERMINATOR);
		return writer.toString();
	}

	public String serialize(Notification n) throws MessageSerializingException {
		StringWriter writer = new StringWriter();
		writer.write(serializeReplyHeader(n));
		if (n.getGoodBye() != null) {
			writer.write(GOODBYE);
			writer.write(LF);
		} else if (n.getReadReport() != null) {
			writer.write(serializeNotificationReadReport(n.getReadReport()));
		}
		// TODO Gibts ReaderInfo in n.getReader() im Text-Format nicht?

		return writer.toString();
	}

	/**
	 * Serializes the reply header
	 * 
	 * @param r
	 * @return the reply header
	 */
	private String serializeReplyHeader(Reply r) {
		StringBuffer sBuf = new StringBuffer();
		if (r.getId() != null) {
			sBuf.append(EXCLAMATION);
			sBuf.append(r.getId());
			sBuf.append(" ");
		}
		sBuf.append(OK);
		sBuf.append(LF);
		return sBuf.toString();
	}

	/**
	 * Serializes the notification header
	 * 
	 * @param n
	 *            the notification
	 * @return the notification header
	 */
	private String serializeReplyHeader(Notification n) {
		StringBuffer sBuf = new StringBuffer();
		if (n.getId() != null) {
			sBuf.append(EXCLAMATION);
			sBuf.append(n.getId());
			sBuf.append(LF);
		}
		return sBuf.toString();
	}

	private void writeReplyLine(StringWriter writer, String val) {
		writer.write(val);
		writer.write(LF);
	}

	private void writeReplyLine(StringWriter writer, int val) {
		writer.write(new Integer(val).toString());
		writer.write(LF);
	}

	private void writeReplyLine(StringWriter writer, StringReturnType s) {
		writeReplyLine(writer, s.getReturnValue());
	}

	private void writeReplyLine(StringWriter writer, IntReturnType i) {
		writeReplyLine(writer, i.getReturnValue());
	}

	private void writeReplyLine(StringWriter writer, BooleanReturnType b) {
		writeReplyLine(writer, (b.isReturnValue() ? "true" : "false"));
	}

	/**
	 * Writes a timestamp to the output formatted in ISO 8601 i.e.
	 * YYYY-MM-DDThh:mm:ss.SSSz
	 * 
	 * @param writer
	 *            The StringWriter for the output
	 * @param t
	 *            The timestamp to be formatted.
	 */
	private void writeReplyLine(StringWriter writer, TimeStampReturnType t) {
		Calendar cal = xmlGregorianCalendarToCalendar(t.getReturnValue());
		writeReplyLine(writer, toISO8601(cal));
	}

	/**
	 * Converts a calendar into a ISO 8601 formatted String. i.e.
	 * YYYY-MM-DDThh:mm:ss.SSSz
	 * 
	 * @param cal
	 *            The Calendar
	 * @return String with timestamp formatted as ISO 8601
	 */
	private String toISO8601(Calendar cal) {
		SimpleDateFormat formatter = new SimpleDateFormat();
		formatter.applyPattern(ISO_8601_MILLIS_PATTERN);
		return formatter.format(cal.getTime());
	}

	private XMLGregorianCalendar calendarToXMLGregorianCalendar(Calendar cal) throws DatatypeConfigurationException{
		GregorianCalendar gregorianCalendar = new GregorianCalendar(cal.getTimeZone());
		gregorianCalendar.setTimeInMillis(cal.getTimeInMillis());
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
	}
	
	private Calendar xmlGregorianCalendarToCalendar(XMLGregorianCalendar xmlCal) {
		Calendar cal = new GregorianCalendar();
		cal.set(xmlCal.getYear(), xmlCal.getMonth(), xmlCal.getDay(), xmlCal.getHour(), xmlCal.getMinute(), xmlCal.getSecond());
		return cal;
	}
	
	private void writeReplyLine(StringWriter writer, HexStringReturnType h) {
		String hexString = new String(h.getReturnValue());
		writeReplyLine(writer, hexString);
	}

	private void writeReplyLine(StringWriter writer, AddressReturnType a) {
		writeReplyLine(writer, a.getReturnValue());
	}

	private void writeStringListLine(StringWriter writer, List valueList) {
		for (Iterator it = valueList.iterator(); it.hasNext();) {
			writer.write((String) it.next());
			writer.write(LF);
		}
	}

	private void writeReplyLine(StringWriter writer,
			StringListReturnType strList) {
		List valueList = strList.getReturnValue().getList().getValue();
		writeStringListLine(writer, valueList);
	}

	private void writeReplyLine(StringWriter writer,
			SourceListReturnType sourceList) {
		List valueList = sourceList.getReturnValue().getList().getValue();
		writeStringListLine(writer, valueList);
	}

	private void writeReplyLine(StringWriter writer,
			DataSelectorListReturnType dsList) {
		List valueList = dsList.getReturnValue().getList().getValue();
		writeStringListLine(writer, valueList);
	}

	private void writeReplyLine(StringWriter writer,
			NotificationChannelListReturnType ncList) {
		List valueList = ncList.getReturnValue().getList().getValue();
		writeStringListLine(writer, valueList);
	}

	private void writeReplyLine(StringWriter writer,
			TriggerListReturnType triggerList) {
		List valueList = triggerList.getReturnValue().getList().getValue();
		writeStringListLine(writer, valueList);
	}

	private void writeReplyLine(StringWriter writer,
			TagSelectorListReturnType tsList) {
		List valueList = tsList.getReturnValue().getList().getValue();
		writeStringListLine(writer, valueList);
	}

	private void writeReplyLine(StringWriter writer,
			TagFieldListReturnType tfList) {
		List valueList = tfList.getReturnValue().getList().getValue();
		writeStringListLine(writer, valueList);
	}

	private void writeReplyLine(StringWriter writer,
			ReadPointListReturnType rpList) {
		List valueList = rpList.getReturnValue().getList().getValue();
		writeStringListLine(writer, valueList);
	}

	private void writeReplyLine(StringWriter writer,
			EventTypeListReturnValue evList) {
		List valueList = evList.getReturnValue().getList().getValue();
		writeStringListLine(writer, valueList);
	}

	private void writeReplyLine(StringWriter writer,
			TriggerTypeListReturnType trgList) {
		List valueList = trgList.getReturnValue().getList().getValue(); //?? stimmt dat nun?
		writeStringListLine(writer, valueList);
	}

	private void writeReplyLine(StringWriter writer,
			FieldNameListReturnType fnList) {
		List valueList = fnList.getReturnValue().getList().getValue();
		writeStringListLine(writer, valueList);
	}

	/**
	 * Serialize a ReadReport used in a reply.
	 * 
	 * @param writer
	 *            The output writer
	 * @param report
	 *            The ReadReport
	 */
	private void writeReplyLine(StringWriter writer, ReadReportType report) {

		List srcReportList = report.getSourceReport();
		Iterator srcReportIt = srcReportList.iterator();
		while (srcReportIt.hasNext()) {
			SourceReport srcReport = (SourceReport) srcReportIt.next();
			String sourceName = null;
			if (srcReport.getSourceInfo() != null) {
				SourceInfoType sourceInfo = srcReport.getSourceInfo();
				sourceName = sourceInfo.getSourceName();
			}
			List tagList = srcReport.getTag();
			Iterator tagListIt = tagList.iterator();
			// Add all tags
			while (tagListIt.hasNext()) {
				/*
				 * a Vector of Strings with all attributes that have to be
				 * reported
				 */
				Vector reportAttributes = new Vector();

				// --- Add the source name ---
				if (sourceName != null) {
					reportAttributes.add(sourceName);
				}

				// --- Add the tag properties ---
				TagType tag = (TagType) tagListIt.next();
				if (tag.getTagID() != null) {
					byte[] tagID = tag.getTagID();
					StringBuffer tagIDString = new StringBuffer();
					for (int i = 0; i < tagID.length; i++) {
						String hexString = Integer.toHexString(new Byte(tagID[i]).intValue()).toString().toUpperCase();
						if (hexString.length() == 1) {
							tagIDString.append("0");
						}
						tagIDString.append(hexString);
					}
					reportAttributes.add(tagIDString.toString());
				}
				if (tag.getTagIDAsPureURI() != null) {
					reportAttributes.add(tag.getTagIDAsPureURI());
				}
				if (tag.getTagIDAsTagURI() != null) {
					reportAttributes.add(tag.getTagIDAsTagURI());
				}
				if (tag.getTagType() != null) {
					reportAttributes.add(tag.getTagType());
				}

				// --- Add the tag events ---
				if (tag.getTagEvent() != null) {
					Iterator tagEventIt = tag.getTagEvent().iterator();
					while (tagEventIt.hasNext()) {
						TagEventType tagEvent = (TagEventType) tagEventIt
								.next();
						if (tagEvent.getEventType() != null) {
							reportAttributes.add(tagEvent.getEventType());
						}
						if (tagEvent.getTime() != null
								&& tagEvent.getTime().getEventTimeTick() != null) {
							reportAttributes.add(tagEvent.getTime()
									.getEventTimeTick());
						} else if (tagEvent.getTime() != null
								&& tagEvent.getTime().getEventTimeUTC() != null) {
							reportAttributes.add(toISO8601(xmlGregorianCalendarToCalendar(tagEvent.getTime()
									.getEventTimeUTC())));
						}
						// TODO: Should we really report the event triggers???
						// if (tagEvent.getEventTriggers() != null &&
						// tagEvent.getEventTriggers().getTrigger() != null) {
						// //add all the trigger names
						// EventTriggersType eventTrigger =
						// tagEvent.getEventTriggers();
						// Iterator eventTriggerIt =
						// eventTrigger.getTrigger().iterator();
						// while(eventTriggerIt.hasNext()) {
						// String triggerName = (String)eventTriggerIt.next();
						// reportAttributes.add(triggerName);
						// }
						// }

					}
				}

				// --- Add the tag fields ---
				if (tag.getTagFields() != null) {
					Iterator tagFieldIt = tag.getTagFields().iterator();
					while (tagFieldIt.hasNext()) {
						TagFieldValueParamType tfvp = (TagFieldValueParamType) tagFieldIt
								.next();
						// TODO: How to serialize the tagfield
						// name-value-pairs??
						if (tfvp.getTagFieldName() != null) {
							reportAttributes.add(tfvp.getTagFieldName());
						}
						if (tfvp.getTagFieldValue() != null) {
							reportAttributes.add(new String(tfvp
									.getTagFieldValue()));
						}
					}
				}

				// --- Serialize the tag line ---
				writeTagLine(writer, reportAttributes);
			}
		}
	}

	/**
	 * Serialize a ReadReport used in a notification.
	 * 
	 * @param writer
	 *            The output writer
	 * @param report
	 *            The ReadReport
	 */
	private void writeReplyLine(StringWriter writer,
			org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.ReadReportType report) {

		List srcReportList = report.getSourceReport();
		Iterator srcReportIt = srcReportList.iterator();
		while (srcReportIt.hasNext()) {
			org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.ReadReportType.SourceReport srcReport = (org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.ReadReportType.SourceReport) srcReportIt
					.next();
			String sourceName = null;
			if (srcReport.getSourceInfo() != null) {
				org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.SourceInfoType sourceInfo = srcReport
						.getSourceInfo();
				sourceName = sourceInfo.getSourceName();
			}
			List tagList = srcReport.getTag();
			Iterator tagListIt = tagList.iterator();
			// Add all tags
			while (tagListIt.hasNext()) {
				/*
				 * a Vector of Strings with all attributes that have to be
				 * reported
				 */
				Vector reportAttributes = new Vector();

				// --- Add the source name ---
				if (sourceName != null) {
					reportAttributes.add(sourceName);
				}

				// --- Add the tag properties ---
				org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.TagType tag = (org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.TagType) tagListIt
						.next();
				if (tag.getTagID() != null) {
					reportAttributes.add(new String(tag.getTagID()));
				}
				if (tag.getTagIDAsPureURI() != null) {
					reportAttributes.add(tag.getTagIDAsPureURI());
				}
				if (tag.getTagIDAsTagURI() != null) {
					reportAttributes.add(tag.getTagIDAsTagURI());
				}
				if (tag.getTagType() != null) {
					reportAttributes.add(tag.getTagType());
				}

				// --- Add the tag events ---
				if (tag.getTagEvent() != null) {
					Iterator tagEventIt = tag.getTagEvent().iterator();
					while (tagEventIt.hasNext()) {
						org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.TagEventType tagEvent = (org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.TagEventType) tagEventIt
								.next();
						if (tagEvent.getEventType() != null) {
							reportAttributes.add(tagEvent.getEventType());
						}
						if (tagEvent.getTime() != null
								&& tagEvent.getTime().getEventTimeTick() != null) {
							reportAttributes.add(tagEvent.getTime()
									.getEventTimeTick());
						} else if (tagEvent.getTime() != null
								&& tagEvent.getTime().getEventTimeUTC() != null) {
							reportAttributes.add(toISO8601(xmlGregorianCalendarToCalendar(tagEvent.getTime().getEventTimeUTC())));
						}
						// TODO: Should we really report the event triggers???
						// if (tagEvent.getEventTriggers() != null &&
						// tagEvent.getEventTriggers().getTrigger() != null) {
						// //add all the trigger names
						// org.accada.reader.msg.notification.EventTriggersType
						// eventTrigger = tagEvent.getEventTriggers();
						// Iterator eventTriggerIt =
						// eventTrigger.getTrigger().iterator();
						// while(eventTriggerIt.hasNext()) {
						// String triggerName = (String)eventTriggerIt.next();
						// reportAttributes.add(triggerName);
						// }
						// }

					}
				}

				// --- Add the tag fields ---
				if (tag.getTagFields() != null) {
					Iterator tagFieldIt = tag.getTagFields().iterator();
					while (tagFieldIt.hasNext()) {
						org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.TagFieldValueParamType tfvp = (org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.TagFieldValueParamType) tagFieldIt
								.next();
						// TODO: How to serialize the tagfield
						// name-value-pairs??
						if (tfvp.getTagFieldName() != null) {
							reportAttributes.add(tfvp.getTagFieldName());
						}
						if (tfvp.getTagFieldValue() != null) {
							reportAttributes.add(new String(tfvp
									.getTagFieldValue()));
						}
					}
				}

				// --- Serialize the tag line ---
				writeTagLine(writer, reportAttributes);
			}
		}
	}

	/**
	 * Writes a tag line of a read report to the output writer. The attributes
	 * are delimited by ","
	 * 
	 * @param writer
	 *            The output writer
	 * @param reportAttributes
	 *            A Vector of String with the attributes.
	 */
	private void writeTagLine(StringWriter writer, Vector reportAttributes) {
		StringBuffer sBuf = new StringBuffer();
		Iterator attributesIt = reportAttributes.iterator();
		while (attributesIt.hasNext()) {
			sBuf.append((String) attributesIt.next());
			if (attributesIt.hasNext()) {
				sBuf.append(COMMA);
			}
		}
		writeReplyLine(writer, sBuf.toString());
	}

	/**
	 * Serialize all non-void ReaderDevice commands.
	 * 
	 * @param reply
	 * @return
	 */
	private String serializeReaderDevice(ReaderDeviceReply reply) {
		StringWriter writer = new StringWriter();
		if (reply.getGetEPC() != null) {
			EpcReturnType epcType = reply.getGetEPC();
			writeReplyLine(writer, epcType.getReturnValue().getValue());
		} else if (reply.getGetManufacturer() != null) {
			writeReplyLine(writer, reply.getGetManufacturer());
		} else if (reply.getGetModel() != null) {
			writeReplyLine(writer, reply.getGetModel());
		} else if (reply.getGetHandle() != null) {
			writeReplyLine(writer, reply.getGetHandle());
		} else if (reply.getGetName() != null) {
			writeReplyLine(writer, reply.getGetName());
		} else if (reply.getGetRole() != null) {
			writeReplyLine(writer, reply.getGetRole());
		} else if (reply.getGetTimeTicks() != null) {
			writeReplyLine(writer, reply.getGetTimeTicks());
		} else if (reply.getGetTimeUTC() != null) {
			writeReplyLine(writer, reply.getGetTimeUTC());
		} else if (reply.getGetManufacturerDescription() != null) {
			writeReplyLine(writer, reply.getGetManufacturerDescription());
		} else if (reply.getGetCurrentSource() != null) {
			SourceReturnType src = reply.getGetCurrentSource();
			writeReplyLine(writer, src.getReturnValue());
		} else if (reply.getGetCurrentDataSelector() != null) {
			DataSelectorReturnType ds = reply.getGetCurrentDataSelector();
			writeReplyLine(writer, ds.getReturnValue());
		} else if (reply.getGetSource() != null) {
			SourceReturnType src = reply.getGetSource();
			writeReplyLine(writer, src.getReturnValue());
		} else if (reply.getGetAllSources() != null) {
			writeReplyLine(writer, reply.getGetAllSources());
		} else if (reply.getGetDataSelector() != null) {
			DataSelectorReturnType ds = reply.getGetDataSelector();
			writeReplyLine(writer, ds.getReturnValue());
		} else if (reply.getGetAllDataSelectors() != null) {
			writeReplyLine(writer, reply.getGetAllDataSelectors());
		} else if (reply.getGetNotificationChannel() != null) {
			NotificationChannelReturnType nc = reply
					.getGetNotificationChannel();
			writeReplyLine(writer, nc.getReturnValue());
		} else if (reply.getGetAllNotificationChannels() != null) {
			writeReplyLine(writer, reply.getGetAllNotificationChannels());
		} else if (reply.getGetTrigger() != null) {
			TriggerReturnType trigger = reply.getGetTrigger();
			writeReplyLine(writer, trigger.getReturnValue());
		} else if (reply.getGetAllTriggers() != null) {
			writeReplyLine(writer, reply.getGetAllTriggers());
		} else if (reply.getGetTagSelector() != null) {
			TagSelectorReturnType ts = reply.getGetTagSelector();
			writeReplyLine(writer, ts.getReturnValue());
		} else if (reply.getGetAllTagSelectors() != null) {
			writeReplyLine(writer, reply.getGetAllTagSelectors());
		} else if (reply.getGetTagField() != null) {
			TagFieldReturnType tf = reply.getGetTagField();
			writeReplyLine(writer, tf.getReturnValue());
		} else if (reply.getGetAllTagFields() != null) {
			writeReplyLine(writer, reply.getGetAllTagFields());
		} else if (reply.getGetReadPoint() != null) {
			ReadPointReturnType rp = reply.getGetReadPoint();
			writeReplyLine(writer, rp.getReturnValue());
		} else if (reply.getGetAllReadPoints() != null) {
			writeReplyLine(writer, reply.getGetAllReadPoints());
		}

		return writer.toString();
	}

	/**
	 * Serialize all non-void Source commands.
	 * 
	 * @param reply
	 * @return
	 */
	private String serializeSource(SourceReply reply) {
		StringWriter writer = new StringWriter();
		if (reply.getGetName() != null) {
			writeReplyLine(writer, reply.getGetName());
		} else if (reply.getIsFixed() != null) {
			writeReplyLine(writer, reply.getIsFixed());
		} else if (reply.getGetReadPoint() != null) {
			ReadPointReturnType rp = reply.getGetReadPoint();
			writeReplyLine(writer, rp.getReturnValue());
		} else if (reply.getGetAllReadPoints() != null) {
			writeReplyLine(writer, reply.getGetAllReadPoints());
		} else if (reply.getGetReadTrigger() != null) {
			TriggerReturnType trigger = reply.getGetReadTrigger();
			writeReplyLine(writer, trigger.getReturnValue());
		} else if (reply.getGetAllReadTriggers() != null) {
			writeReplyLine(writer, reply.getGetAllReadTriggers());
		} else if (reply.getGetTagSelector() != null) {
			TagSelectorReturnType ts = reply.getGetTagSelector();
			writeReplyLine(writer, ts.getReturnValue());
		} else if (reply.getGetAllTagSelectors() != null) {
			writeReplyLine(writer, reply.getGetAllTagSelectors());
		} else if (reply.getGetGlimpsedTimeout() != null) {
			writeReplyLine(writer, reply.getGetGlimpsedTimeout());
		} else if (reply.getGetObservedThreshold() != null) {
			writeReplyLine(writer, reply.getGetObservedThreshold());
		} else if (reply.getGetObservedTimeout() != null) {
			writeReplyLine(writer, reply.getGetObservedTimeout());
		} else if (reply.getGetLostTimeout() != null) {
			writeReplyLine(writer, reply.getGetLostTimeout());
		} else if (reply.getRawReadIDs() != null) {
			RawReadIDs rawRead = reply.getRawReadIDs();
			writeReplyLine(writer, rawRead.getReturnValue());
		} else if (reply.getReadIDs() != null) {
			ReadIDs readId = reply.getReadIDs();
			writeReplyLine(writer, readId.getReturnValue());
		} else if (reply.getRead() != null) {
			Read read = reply.getRead();
			writeReplyLine(writer, read.getReturnValue());
		} else if (reply.getGetReadCyclesPerTrigger() != null) {
			writeReplyLine(writer, reply.getGetReadCyclesPerTrigger());
		} else if (reply.getGetMaxReadDutyCycle() != null) {
			writeReplyLine(writer, reply.getGetMaxReadDutyCycle());
		} else if (reply.getGetReadTimeout() != null) {
			writeReplyLine(writer, reply.getGetReadTimeout());
		}
		// FIXME: reply.getGetSession fehlt!

		return writer.toString();
	}

	/**
	 * Serialize all non-void ReadPoint commands.
	 * 
	 * @param reply
	 * @return
	 */
	private String serializeReadPoint(ReadPointReply reply) {
		StringWriter writer = new StringWriter();
		if (reply.getGetName() != null) {
			writeReplyLine(writer, reply.getGetName());
		}
		return writer.toString();
	}

	/**
	 * Serialize all non-void Trigger commands.
	 * 
	 * @param reply
	 * @return
	 */
	private String serializeTrigger(TriggerReply reply) {
		StringWriter writer = new StringWriter();
		if (reply.getCreate() != null) {
			TriggerReturnType trigger = reply.getCreate();
			writeReplyLine(writer, trigger.getReturnValue());
		} else if (reply.getGetMaxNumberSupported() != null) {
			writeReplyLine(writer, reply.getGetMaxNumberSupported());
		} else if (reply.getGetName() != null) {
			writeReplyLine(writer, reply.getGetName());
		} else if (reply.getGetType() != null) {
			TriggerTypeReturnType trgType = reply.getGetType();
			writeReplyLine(writer, trgType.getReturnValue());
		} else if (reply.getGetValue() != null) {
			TriggerValueReturnType trgValue = reply.getGetValue();
			writeReplyLine(writer, trgValue.getReturnValue());
		}

		return writer.toString();
	}

	/**
	 * Serialize all non-void TagSelector commands.
	 * 
	 * @param reply
	 * @return
	 */
	private String serializeTagSelector(TagSelectorReply reply) {
		StringWriter writer = new StringWriter();
		if (reply.getCreate() != null) {
			TagSelectorReturnType ts = reply.getCreate();
			writeReplyLine(writer, ts.getReturnValue());
		} else if (reply.getGetMaxNumberSupported() != null) {
			writeReplyLine(writer, reply.getGetMaxNumberSupported());
		} else if (reply.getGetName() != null) {
			writeReplyLine(writer, reply.getGetName());
		} else if (reply.getGetTagField() != null) {
			TagFieldReturnType tagField = reply.getGetTagField();
			writeReplyLine(writer, tagField.getReturnValue());
		} else if (reply.getGetValue() != null) {
			writeReplyLine(writer, reply.getGetValue());
		} else if (reply.getGetMask() != null) {
			writeReplyLine(writer, reply.getGetMask());
		} else if (reply.getGetInclusiveFlag() != null) {
			writeReplyLine(writer, reply.getGetInclusiveFlag());
		}

		return writer.toString();
	}

	/**
	 * Serialize all non-void DataSelector commands.
	 * 
	 * @param reply
	 * @return
	 */
	private String serializeDataSelector(DataSelectorReply reply) {
		StringWriter writer = new StringWriter();
		if (reply.getCreate() != null) {
			DataSelectorReturnType ts = reply.getCreate();
			writeReplyLine(writer, ts.getReturnValue());
		} else if (reply.getGetName() != null) {
			writeReplyLine(writer, reply.getGetName());
		} else if (reply.getGetAllFieldNames() != null) {
			writeReplyLine(writer, reply.getGetAllFieldNames());
		} else if (reply.getGetAllEventFilters() != null) {
			writeReplyLine(writer, reply.getGetAllEventFilters());
		} else if (reply.getGetAllTagFieldNames() != null) {
			writeReplyLine(writer, reply.getGetAllTagFieldNames());
		}
		return writer.toString();
	}

	/**
	 * Serialize all non-void NotificationChannel commands.
	 * 
	 * @param reply
	 * @return
	 */
	private String serializeNotificationChannel(NotificationChannelReply reply) {
		StringWriter writer = new StringWriter();
		if (reply.getCreate() != null) {
			NotificationChannelReturnType nc = reply.getCreate();
			writeReplyLine(writer, nc.getReturnValue());
		} else if (reply.getGetName() != null) {
			writeReplyLine(writer, reply.getGetName());
		} else if (reply.getGetAddress() != null) {
			writeReplyLine(writer, reply.getGetAddress());
		} else if (reply.getGetEffectiveAddress() != null) {
			writeReplyLine(writer, reply.getGetEffectiveAddress());
		} else if (reply.getSetAddress() != null) {
			writeReplyLine(writer, reply.getSetAddress());
		} else if (reply.getGetDataSelector() != null) {
			DataSelectorReturnType ds = reply.getGetDataSelector();
			writeReplyLine(writer, ds.getReturnValue());
		} else if (reply.getGetSource() != null) {
			SourceReturnType source = reply.getGetSource();
			writeReplyLine(writer, source.getReturnValue());
		} else if (reply.getGetAllSources() != null) {
			writeReplyLine(writer, reply.getGetAllSources());
		} else if (reply.getGetNotificationTrigger() != null) {
			TriggerReturnType trigger = reply.getGetNotificationTrigger();
			writeReplyLine(writer, trigger.getReturnValue());
		} else if (reply.getGetAllNotificationTriggers() != null) {
			writeReplyLine(writer, reply.getGetAllNotificationTriggers());
		} else if (reply.getReadQueuedData() != null) {
			ReadQueuedData rq = reply.getReadQueuedData();
			writeReplyLine(writer, rq.getReturnValue());
		}
		return writer.toString();
	}

	/**
	 * Serialize all non-void EventType commands.
	 * 
	 * @param reply
	 * @return
	 */
	private String serializeEventType(EventTypeReply reply) {
		StringWriter writer = new StringWriter();
		if (reply.getGetSupportedTypes() != null) {
			writeReplyLine(writer, reply.getGetSupportedTypes());
		}
		return writer.toString();
	}

	/**
	 * Serialize all non-void TriggerType commands.
	 * 
	 * @param reply
	 * @return
	 */
	private String serializeTriggerType(TriggerTypeReply reply) {
		StringWriter writer = new StringWriter();
		if (reply.getGetSupportedTypes() != null) {
			writeReplyLine(writer, reply.getGetSupportedTypes());
		}
		return writer.toString();
	}

	/**
	 * Serialize all non-void FieldName commands.
	 * 
	 * @param reply
	 * @return
	 */
	private String serializeFieldName(FieldNameReply reply) {
		StringWriter writer = new StringWriter();
		if (reply.getGetSupportedNames() != null) {
			writeReplyLine(writer, reply.getGetSupportedNames());
		}
		return writer.toString();
	}

	/**
	 * Serialize all non-void TagField commands.
	 * 
	 * @param reply
	 * @return
	 */
	private String serializeTagField(TagFieldReply reply) {
		StringWriter writer = new StringWriter();
		if (reply.getCreate() != null) {
			TagFieldReturnType tf = reply.getCreate();
			writeReplyLine(writer, tf.getReturnValue());
		} else if (reply.getGetName() != null) {
			writeReplyLine(writer, reply.getGetName());
		} else if (reply.getGetTagFieldName() != null) {
			writeReplyLine(writer, reply.getGetTagFieldName());
		} else if (reply.getGetMemoryBank() != null) {
			writeReplyLine(writer, reply.getGetMemoryBank());
		} else if (reply.getGetOffset() != null) {
			writeReplyLine(writer, reply.getGetOffset());
		} else if (reply.getGetLength() != null) {
			writeReplyLine(writer, reply.getGetLength());
		}
		return writer.toString();
	}

	private String serializeNotificationReadReport(List reportList) {
		StringWriter writer = new StringWriter();
		Iterator reportListIt = reportList.iterator();
		while (reportListIt.hasNext()) {
			org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.ReadReportType report = (org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.ReadReportType) reportListIt
					.next();
			writeReplyLine(writer, report);
		}
		return writer.toString();
	}

	/**
	 * Serializes an Error into the text format
	 * 
	 * @param resultCode
	 *            The result code
	 * @param error
	 *            The error with description, cause etc.
	 * @return
	 */
	private String serializeError(int resultCode, ErrorType error) {
		StringWriter writer = new StringWriter();
		writer.write(ERR);
		writer.write(COMMA);
		writer.write(String.valueOf(resultCode));
		if (error.getName() != null) {
			writer.write(COMMA);
			writer.write(error.getName());
			if (error.getCause() != null) {
				writer.write(COMMA);
				writer.write(error.getCause());
			}
			if (error.getDescription() != null) {
				writer.write(COMMA);
				writer.write(DOUBLEQUOTE);
				writer.write(error.getDescription());
				writer.write(DOUBLEQUOTE);
			}
		}
		writer.write(LF);
		return writer.toString();
	}

}
