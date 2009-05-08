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

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.ow2.aspirerfid.reader.rp.impl.core.DataSelector;
import org.ow2.aspirerfid.reader.rp.impl.core.EventType;
import org.ow2.aspirerfid.reader.rp.impl.core.FieldName;
import org.ow2.aspirerfid.reader.rp.impl.core.NotificationChannel;
import org.ow2.aspirerfid.reader.rp.impl.core.ReadPoint;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderDevice;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderProtocolException;
import org.ow2.aspirerfid.reader.rp.impl.core.Source;
import org.ow2.aspirerfid.reader.rp.impl.core.TagField;
import org.ow2.aspirerfid.reader.rp.impl.core.TagFieldValue;
import org.ow2.aspirerfid.reader.rp.impl.core.TagSelector;
import org.ow2.aspirerfid.reader.rp.impl.core.Trigger;
import org.ow2.aspirerfid.reader.rp.impl.core.TriggerType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.Command;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.DataSelectorCommand;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.EventTypeCommand;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.FieldNameCommand;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.HexStringListType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.NotificationChannelCommand;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReadPointCommand;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.SourceCommand;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TagFieldCommand;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TagSelectorCommand;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TriggerCommand;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TriggerTypeCommand;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.SourceCommand.Kill;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.SourceCommand.Write;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.AddressReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.BooleanReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.DataSelectorListParamType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.DataSelectorListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.DataSelectorReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.DataSelectorReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.EPC;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.EpcReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ErrorType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.EventTimeType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.EventTypeListParamType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.EventTypeListReturnValue;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.EventTypeReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.FieldNameListParamType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.FieldNameListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.FieldNameReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.HexStringReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.IntReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.NoParamType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.NotificationChannelListParamType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.NotificationChannelListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.NotificationChannelReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.NotificationChannelReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ReadPointListParamType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ReadPointListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ReadPointReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ReadPointReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ReadReportType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ReaderDeviceReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.Reply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.SourceListParamType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.SourceListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.SourceReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.SourceReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.StringReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagFieldListParamType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagFieldListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagFieldReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagFieldReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagSelectorListParamType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagSelectorListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagSelectorReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagSelectorReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TimeStampReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TriggerListParamType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TriggerListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TriggerReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TriggerReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TriggerTypeListReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TriggerTypeReply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TriggerTypeReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TriggerValueReturnType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.NotificationChannelReply.ReadQueuedData;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.SourceReply.RawReadIDs;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.SourceReply.ReadIDs;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagEventType.EventTriggers;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.Connection;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.ServerConnection;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.util.CompareSet;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.util.HexUtil;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.util.SocketUtil;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.ReadReport;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.SourceInfoType;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.SourceReport;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagFieldValueParamType;
import org.apache.log4j.Logger;

/**
 * The command dispatcher dispatches the commands by invoking the methods on the
 * ReaderDevice and creates the response. The commands and replies don't depend
 * on a specific message format the <code>CommandDispatcher</code> uses an
 * intermediate representation (IR) of the commands and responses. As the IR
 * tree we use the classes generated by JAXB. An XML message is mapped
 * automatically into such an IR so get there the IR for free and don't need
 * another level of indirection (which would deteriorate performance).<br>
 * Other message formats (e.g., the text message format) are required to parse
 * the message into the JAXB classes IR before invoking the command dispatcher.
 * <br>
 * <br>
 * Because every EPC reader has only one instance of a
 * <code>CommandDispatcher</code> this is a singleton class.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 */
public class CommandDispatcher {
	// ====================================================================
	// ---------------------------- Fields ------------------------------//
	// ====================================================================

	/** The logger. */
	static private Logger log;

	/** The singleton instance of the <code>CommandDipatcher</code>. */
	private static CommandDispatcher dispatcher;

	/** The singleton factory used to create empty replies. */
	private static org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ObjectFactory replyFactory;

	/** The ReaderDevice we are working on. */
	private static ReaderDevice readerDevice;

	/** The <code>MessagLayer</code> holding this dispatcher. */
	private MessageLayer msgLayer = null;

	// ====================================================================
	// ----------------------- Constructors -----------------------------//
	// ====================================================================

	private CommandDispatcher(final MessageLayer msgLayer) {
		log = Logger.getLogger(getClass().getName());
		replyFactory = new org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ObjectFactory();
		this.msgLayer = msgLayer;
		try {
			readerDevice = ReaderDevice.getInstance();
		} catch (ReaderProtocolException e) {
			log.error(e);
		}
	}

	// ====================================================================
	// ------------------------- Methods --------------------------------//
	// ====================================================================
	/**
	 * Returns the single Instance of a <code>ServiceDispatcher</code>.
	 * 
	 * @param msgLayer
	 *            The <code>MessageLayer</code> holding this
	 *            <code>MessageDispatcher</code>.
	 * @return The <code>ServiceDispatcher</code>.
	 */
	public static CommandDispatcher getInstance(final MessageLayer msgLayer) {
		if (dispatcher == null) {
			dispatcher = new CommandDispatcher(msgLayer);
		}
		return dispatcher;
	}

	/**
	 * Dispatches a <code>Command</code>.
	 * 
	 * @param command
	 *            The <code>Command</code> to execute.
	 * @param im
	 *            The <code>IncomingMessage</code> that caused this command
	 *            invocation.
	 * @return The <code>Reply</code> of the command execution or a
	 *         <code>null</code> reply if anything went wrong.
	 */
	public Reply dispatch(final Command command, final IncomingMessage im)
	/* throws EventLayerException */{
		try {
			Reply reply = replyFactory.createReply();

			if (command.getReaderDevice() != null) {
				ReaderDeviceCommand rdCommand = command.getReaderDevice();
				ReaderDeviceReply rdReply = executeCommand(rdCommand, command
						.getTargetName(), im);
				reply.setReaderDevice(rdReply);
			}

			else if (command.getSource() != null) {
				SourceCommand srcCommand = command.getSource();
				SourceReply srcReply = executeCommand(srcCommand, command
						.getTargetName(), im);
				reply.setSource(srcReply);
			}

			else if (command.getReadPoint() != null) {
				ReadPointCommand rdpCommand = command.getReadPoint();
				ReadPointReply rpReply = executeCommand(rdpCommand, command
						.getTargetName(), im);
				reply.setReadPoint(rpReply);
			}

			else if (command.getTrigger() != null) {
				TriggerCommand tgCommand = command.getTrigger();
				TriggerReply tgReply = executeCommand(tgCommand, command
						.getTargetName(), im);
				reply.setTrigger(tgReply);
			}

			else if (command.getTagSelector() != null) {
				TagSelectorCommand tsCommand = command.getTagSelector();
				TagSelectorReply tsReply = executeCommand(tsCommand, command
						.getTargetName(), im);
				reply.setTagSelector(tsReply);
			}

			else if (command.getNotificationChannel() != null) {
				NotificationChannelCommand notificationCommand = command
						.getNotificationChannel();
				NotificationChannelReply notReply = executeCommand(
						notificationCommand, command.getTargetName(), im);
				reply.setNotificationChannel(notReply);
			}

			else if (command.getDataSelector() != null) {
				DataSelectorCommand dsCommand = command.getDataSelector();
				DataSelectorReply dsReply = executeCommand(dsCommand, command
						.getTargetName(), im);
				reply.setDataSelector(dsReply);
			}

			else if (command.getEventType() != null) {
				EventTypeCommand etCommand = command.getEventType();
				EventTypeReply etReply = executeCommand(etCommand, command
						.getTargetName(), im);
				reply.setEventType(etReply);
			}

			else if (command.getTriggerType() != null) {
				TriggerTypeCommand ttCommand = command.getTriggerType();
				TriggerTypeReply ttReply = executeCommand(ttCommand, command
						.getTargetName(), im);
				reply.setTriggerType(ttReply);
			}

			else if (command.getFieldName() != null) {
				FieldNameCommand fnCommand = command.getFieldName();
				FieldNameReply fnReply = executeCommand(fnCommand, command
						.getTargetName(), im);
				reply.setFieldName(fnReply);
			}

			else if (command.getTagField() != null) {
				TagFieldCommand tfCommand = command.getTagField();
				TagFieldReply tfReply = executeCommand(tfCommand, command
						.getTargetName(), im);
				reply.setTagField(tfReply);
			}

			// TODO: Exception werfen, wenn kein Kommando ausgef�hrt??

			/* If no exception is thrown, everything went well :-) */
			reply.setId(command.getId());
			/* TODO: Typ in Schema �ndern auf HexStringReturn Type */
			reply.setResultCode(MessagingConstants.NO_ERROR);
			return reply;
		} catch (ReaderProtocolException e) {
			Reply errorReply = handleReaderProtocolException(e, command.getId());
			return errorReply;
		} catch (Exception e) {
		    e.printStackTrace();
			log.error("Unexpected exception while dispatching commands: " + e);
			Reply errorReply = handleReaderProtocolException(command.getId(),
					MessagingConstants.ERROR_UNKNOWN,
					MessagingConstants.ERROR_UNKNOWN_STR, e.getMessage());
			return errorReply;
		}
	}

	/**
	 * Executes a command on the ReaderDevice object.
	 * 
	 * @param command
	 *            The command to be executed.
	 * @param targetName
	 *            The unique identifier (object name) of a
	 *            <code>ReaderDevice</code> instance or <code>null</code> if
	 *            it is a static method.
	 * @param im
	 *            The <code>IncomingMessage</code> that caused this command.
	 * @return The reply of a command execution.
	 * @throws ReaderProtocolException
	 *             Exceptions from the Trigger
	 */
	private ReaderDeviceReply executeCommand(final ReaderDeviceCommand command,
			final String targetName, final IncomingMessage im)
			throws ReaderProtocolException, DatatypeConfigurationException {
		log.debug("Calling a ReaderDevice Command.");
		ReaderDeviceReply reply = replyFactory.createReaderDeviceReply();

		if (command.getGetEPC() != null) {
			log.debug("Calling ReaderDevice.getEPC()");
			EpcReturnType epcReturn = replyFactory.createEpcReturnType();
			EPC epc = replyFactory.createEPC();
			epc.setValue(readerDevice.getEPC());
			epcReturn.setReturnValue(epc);
			reply.setGetEPC(epcReturn);
		}

		else if (command.getGetManufacturer() != null) {
			log.debug("Calling ReaderDevice.getManufacturer()");
			StringReturnType stringReturn = replyFactory
					.createStringReturnType();
			stringReturn.setReturnValue(readerDevice.getManufacturer());
			reply.setGetManufacturer(stringReturn);
		}

		else if (command.getGetModel() != null) {
			log.debug("Calling ReaderDevice.getModel()");
			StringReturnType stringReturn = replyFactory
					.createStringReturnType();
			stringReturn.setReturnValue(readerDevice.getModel());
			reply.setGetModel(stringReturn);
		}

		else if (command.getGetHandle() != null) {
			log.debug("Calling ReaderDevice.getHandle()");
			IntReturnType intReturn = replyFactory.createIntReturnType();
			intReturn.setReturnValue(readerDevice.getHandle());
			reply.setGetHandle(intReturn);
		}

		else if (command.getSetHandle() != null) {
			log.debug("Calling ReaderDevice.setHandle()");
			int handle = command.getSetHandle().getHandle();
			readerDevice.setHandle(handle);
			// return void
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setSetHandle(voidType);
		}

		else if (command.getGetName() != null) {
			log.debug("Calling ReaderDevice.getName()");
			StringReturnType stringReturn = replyFactory
					.createStringReturnType();
			stringReturn.setReturnValue(readerDevice.getName());
			reply.setGetName(stringReturn);
		}

		else if (command.getSetName() != null) {
			log.debug("Calling ReaderDevice.setName()");
			String name = command.getSetName().getName();
			readerDevice.setName(name);
			// return void
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setSetName(voidType);
		}

		else if (command.getGetRole() != null) {
			log.debug("Calling ReaderDevice.getRole()");
			StringReturnType stringReturn = replyFactory
					.createStringReturnType();
			stringReturn.setReturnValue(readerDevice.getRole());
			reply.setGetRole(stringReturn);
		}

		else if (command.getSetRole() != null) {
			log.debug("Calling ReaderDevice.setRole()");
			String role = command.getSetRole().getRole();
			readerDevice.setRole(role);
			// return void
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setSetRole(voidType);
		}

		else if (command.getGetTimeTicks() != null) {
			log.debug("Calling ReaderDevice.getTimeTicks()");
			IntReturnType intReturn = replyFactory.createIntReturnType();
			// TODO: cast von long auf int evtl. problematisch
			intReturn.setReturnValue((int) readerDevice.getTimeTicks());
			reply.setGetTimeTicks(intReturn);
		}

		else if (command.getGetTimeUTC() != null) {
			log.debug("Calling ReaderDevice.getTimeUTC()");
			Calendar cal = toCalendar(readerDevice.getTimeUTC());
			TimeStampReturnType timeStampReturn = replyFactory
					.createTimeStampReturnType();
			timeStampReturn.setReturnValue(calendarToXMLGregorianCalendar(cal));
			reply.setGetTimeUTC(timeStampReturn);
		}

		else if (command.getSetTimeUTC() != null) {
			log.debug("Calling ReaderDevice.setTimeUTC()");
			XMLGregorianCalendar utc = command.getSetTimeUTC().getUtc();
			int year = utc.getYear();
			int month = utc.getMonth();
			int day = utc.getDay();
			int hour = utc.getHour();
			int minute = utc.getMinute();
			int second = utc.getSecond();
			Date date = new Date(year, month, day, hour, minute, second);
			readerDevice.setTimeUTC(date);
			// return void
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setSetTimeUTC(voidType);
		}

		else if (command.getGetManufacturerDescription() != null) {
			log.debug("Calling ReaderDevice.getManufacturerDescription()");
			StringReturnType stringReturn = replyFactory
					.createStringReturnType();
			stringReturn.setReturnValue(readerDevice
					.getManufacturerDescription());
			reply.setGetManufacturerDescription(stringReturn);
		}

		else if (command.getGetCurrentSource() != null) {
			log.debug("Calling ReaderDevice.getCurrentSource()");
			Source curSource = readerDevice.getCurrentSource();
			SourceReturnType sourceReturn = replyFactory
					.createSourceReturnType();
			sourceReturn.setReturnValue(curSource.getName());
			reply.setGetCurrentSource(sourceReturn);
		}

		else if (command.getSetCurrentSource() != null) {
			log.debug("Calling ReaderDevice.setCurrentSource()");
			Source curSource = readerDevice.getSource(command
					.getSetCurrentSource().getCurrentSource());
			readerDevice.setCurrentSource(curSource);
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setSetCurrentSource(voidType);
		}

		else if (command.getGetCurrentDataSelector() != null) {
			log.debug("Calling ReaderDevice.getCurrentDataSelector()");
			DataSelector curDS = readerDevice.getCurrentDataSelector();
			DataSelectorReturnType dataSelectorReturn = replyFactory
					.createDataSelectorReturnType();
			dataSelectorReturn.setReturnValue(curDS.getName());
			reply.setGetCurrentDataSelector(dataSelectorReturn);
		}

		else if (command.getSetCurrentDataSelector() != null) {
			log.debug("Calling ReaderDevice.setCurrentDataSelector()");
			DataSelector curDS = readerDevice.getDataSelector(command
					.getSetCurrentDataSelector().getCurrentDataSelector());
			readerDevice.setCurrentDataSelector(curDS);
			// return void
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setSetCurrentDataSelector(voidType);
		}

		else if (command.getRemoveSources() != null) {
			log.debug("Calling ReaderDevice.removeSources()");
			org.ow2.aspirerfid.reader.rp.impl.core.msg.command.SourceListParamType.List valueElems = command
					.getRemoveSources().getSources().getList();
			Source[] rdSources = getSource(valueElems);
			readerDevice.removeSources(rdSources);
			// return void
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setRemoveSources(voidType);
		}

		else if (command.getRemoveAllSources() != null) {
			log.debug("Calling ReaderDevice.removeAllSources()");
			readerDevice.removeAllSources();
			// return void
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setRemoveAllSources(voidType);
		}

		else if (command.getGetSource() != null) {
			log.debug("Calling ReaderDevice.getSource()");
			Source source = readerDevice.getSource(command.getGetSource()
					.getName());
			SourceReturnType sourceReturn = replyFactory
					.createSourceReturnType();
			sourceReturn.setReturnValue(source.getName());
			reply.setGetSource(sourceReturn);
		}

		else if (command.getGetAllSources() != null) {
			log.debug("Calling ReaderDevice.getAllSources()");
			Source[] sources = readerDevice.getAllSources();
			SourceListReturnType sourceListReturn = replyFactory
					.createSourceListReturnType();
			SourceListParamType sourceListItems = replyFactory
					.createSourceListParamType();
			SourceListParamType.List list = replyFactory
					.createSourceListParamTypeList();
			List valueList = list.getValue();
			for (int i = 0; i < sources.length; i++) {
				valueList.add(sources[i].getName());
			}
			sourceListItems.setList(list);
			sourceListReturn.setReturnValue(sourceListItems);
			reply.setGetAllSources(sourceListReturn);
		}

		else if (command.getRemoveDataSelectors() != null) {
			log.debug("Calling ReaderDevice.removeDataSelectors()");
			org.ow2.aspirerfid.reader.rp.impl.core.msg.command.DataSelectorListParamType.List valueElems = command
					.getRemoveDataSelectors().getDataSelectors().getList();
			DataSelector[] rdDataSelectors = getDataSelector(valueElems);
			readerDevice.removeDataSelectors(rdDataSelectors);

			// return void
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setRemoveDataSelectors(voidType);
		}

		else if (command.getRemoveAllDataSelectors() != null) {
			log.debug("Calling ReaderDevice.removeAllDataSelectors()");
			readerDevice.removeAllDataSelectors();
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setRemoveAllDataSelectors(voidType);
		}

		else if (command.getGetDataSelector() != null) {
			log.debug("Calling ReaderDevice.getDataSelector()");
			DataSelector ds = readerDevice.getDataSelector(command
					.getGetDataSelector().getName());
			DataSelectorReturnType dataSelectorReturn = replyFactory
					.createDataSelectorReturnType();
			dataSelectorReturn.setReturnValue(ds.getName());
			reply.setGetDataSelector(dataSelectorReturn);
		}

		else if (command.getGetAllDataSelectors() != null) {
			log.debug("Calling ReaderDevice.getAllDataSelectors()");
			DataSelector[] dataSelectors = readerDevice
					.getAllDataSelectors();
			DataSelectorListReturnType dataSelectorListReturn = replyFactory
					.createDataSelectorListReturnType();
			DataSelectorListParamType dataSelectorListItems = replyFactory
					.createDataSelectorListParamType();
			DataSelectorListParamType.List list = replyFactory
					.createDataSelectorListParamTypeList();
			List valueList = list.getValue();
			for (int i = 0; i < dataSelectors.length; i++) {
				valueList.add(dataSelectors[i].getName());
			}

			dataSelectorListItems.setList(list);
			dataSelectorListReturn.setReturnValue(dataSelectorListItems);
			reply.setGetAllDataSelectors(dataSelectorListReturn);
		}

		else if (command.getRemoveNotificationChannels() != null) {
			log.debug("Calling ReaderDevice.removeNotificationChannels()");
			org.ow2.aspirerfid.reader.rp.impl.core.msg.command.NotificationChannelListParamType.List valueElems = command
					.getRemoveNotificationChannels().getChannels()
					.getList();
			NotificationChannel[] rdNotificationChannels = getNotificationChannels(valueElems);
			readerDevice.removeNotificationChannels(rdNotificationChannels);
			// return void
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setRemoveNotificationChannels(voidType);
		}

		else if (command.getRemoveAllNotificationChannels() != null) {
			log
					.debug("Calling ReaderDevice.removeAllNotificationChannels()");
			readerDevice.removeAllNotificationChannels();
			// return void
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setRemoveAllNotificationChannels(voidType);
		}

		else if (command.getGetNotificationChannel() != null) {
			log.debug("Calling ReaderDevice.getNotificationChannel()");
			NotificationChannel channel = readerDevice
					.getNotificationChannel(command
							.getGetNotificationChannel().getName());
			NotificationChannelReturnType notificationChannelReturn = replyFactory
					.createNotificationChannelReturnType();
			notificationChannelReturn.setReturnValue(channel.getName());
			reply.setGetNotificationChannel(notificationChannelReturn);
		}

		else if (command.getGetAllNotificationChannels() != null) {
			log.debug("Calling ReaderDevice.getAllNotificationChannels()");

			NotificationChannel[] channels = readerDevice
					.getAllNotificationChannels();
			NotificationChannelListReturnType notificationChannelListReturn = replyFactory
					.createNotificationChannelListReturnType();
			NotificationChannelListParamType notificationListItems = replyFactory
					.createNotificationChannelListParamType();
			NotificationChannelListParamType.List list = replyFactory
					.createNotificationChannelListParamTypeList();
			List valueList = list.getValue();
			for (int i = 0; i < channels.length; i++) {
				valueList.add(channels[i].getName());
			}
			notificationListItems.setList(list);
			notificationChannelListReturn
					.setReturnValue(notificationListItems);
			reply
					.setGetAllNotificationChannels(notificationChannelListReturn);
		}

		else if (command.getRemoveTriggers() != null) {
			log.debug("Calling ReaderDevice.removeTriggers()");

			org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TriggerListParamType.List valueElems = command
					.getRemoveTriggers().getTriggers().getList();
			Trigger[] rdTriggers = getTriggers(valueElems);
			// call removeTriggers on RD
			readerDevice.removeTriggers(rdTriggers);
			// return void
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setRemoveTriggers(voidType);
		}

		else if (command.getRemoveAllTriggers() != null) {
			log.debug("Calling ReaderDevice.removeAllTriggers()");
			readerDevice.removeAllTriggers();
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setRemoveAllTriggers(voidType);
		}

		else if (command.getGetTrigger() != null) {
			log.debug("Calling ReaderDevice.getTrigger()");
			Trigger trigger = readerDevice.getTrigger(command
					.getGetTrigger().getName());
			TriggerReturnType triggerReturn = replyFactory
					.createTriggerReturnType();
			triggerReturn.setReturnValue(trigger.getName());
			reply.setGetTrigger(triggerReturn);
		}

		else if (command.getGetAllTriggers() != null) {
			log.debug("Calling ReaderDevice.getAllTriggers()");
			Trigger[] triggers = readerDevice.getAllTriggers();
			TriggerListReturnType triggerListReturn = replyFactory
					.createTriggerListReturnType();
			TriggerListParamType triggerListItems = replyFactory
					.createTriggerListParamType();
			TriggerListParamType.List list = replyFactory
					.createTriggerListParamTypeList();
			List valueList = list.getValue();
			for (int i = 0; i < triggers.length; i++) {
				valueList.add(triggers[i].getName());
			}
			triggerListItems.setList(list);
			triggerListReturn.setReturnValue(triggerListItems);
			reply.setGetAllTriggers(triggerListReturn);
		}

		else if (command.getRemoveTagSelectors() != null) {
			log.debug("Calling ReaderDevice.removeTagSelectors()");
			org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TagSelectorListParamType.List valueElems = command
					.getRemoveTagSelectors().getSelectors().getList();
			List valueList = valueElems.getValue(); // List of strings with
			// the tag selector
			// names

			/*
			 * aquiring phase - fetch all tag selectors from the RD if a tag
			 * selector doesn't exist an exception then just this one should
			 * be ignored (all others should be removed).
			 */
			TagSelector[] rdTagSelectors = new TagSelector[valueList.size()];
			int index = 0;
			for (Iterator it = valueList.iterator(); it.hasNext();) {
				String tagSelectorName = (String) it.next();
				try {
					TagSelector tagSelector = readerDevice
							.getTagSelector(tagSelectorName);
					rdTagSelectors[index] = tagSelector;
					index++;
				} catch (ReaderProtocolException e) {
					// TagSelector not found ignore it
				}
			}
			// call removeTagSelectors on RD
			readerDevice.removeTagSelectors(rdTagSelectors);
			// return void
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setRemoveTagSelectors(voidType);
		}

		else if (command.getRemoveAllTagSelectors() != null) {
			log.debug("Calling ReaderDevice.removeAllTagSelectors()");
			readerDevice.removeAllTagSelectors();
			// return void
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setRemoveAllTagSelectors(voidType);
		}

		else if (command.getGetTagSelector() != null) {
			log.debug("Calling ReaderDevice.getTagSelector()");
			TagSelector tagSelector = readerDevice.getTagSelector(command
					.getGetTagSelector().getName());
			TagSelectorReturnType tagSelectorReturn = replyFactory
					.createTagSelectorReturnType();
			tagSelectorReturn.setReturnValue(tagSelector.getName());
			reply.setGetTagSelector(tagSelectorReturn);
		}

		else if (command.getGetAllTagSelectors() != null) {
			log.debug("Calling ReaderDevice.getAllTagSelectors()");
			TagSelector[] tagSelectors = readerDevice.getAllTagSelectors();
			TagSelectorListReturnType tagSelectorListReturn = replyFactory
					.createTagSelectorListReturnType();
			TagSelectorListParamType tagSelectorListItems = replyFactory
					.createTagSelectorListParamType();
			TagSelectorListParamType.List list = replyFactory
					.createTagSelectorListParamTypeList();
			List valueList = list.getValue();
			for (int i = 0; i < tagSelectors.length; i++) {
				valueList.add(tagSelectors[i].getName());
			}
			tagSelectorListItems.setList(list);
			tagSelectorListReturn.setReturnValue(tagSelectorListItems);
			reply.setGetAllTagSelectors(tagSelectorListReturn);
		}

		else if (command.getRemoveTagFields() != null) {
			log.debug("Calling ReaderDevice.removeTagFields()");
			org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TagFieldListParamType.List valueElems = command
					.getRemoveTagFields().getFields().getList();
			List valueList = valueElems.getValue(); // List of strings with
			// the tag field names

			/*
			 * aquiring phase - fetch all tag fields from the RD if a tag
			 * fields doesn't exist an exception then just this one should
			 * be ignored (all others should be removed).
			 */
			TagField[] rdTagFields = new TagField[valueList.size()];
			int index = 0;
			for (Iterator it = valueList.iterator(); it.hasNext();) {
				String tagFieldName = (String) it.next();
				try {
					TagField tagField = readerDevice
							.getTagField(tagFieldName);
					rdTagFields[index] = tagField;
					index++;
				} catch (ReaderProtocolException e) {
					// TagField not found ignore it
				}
			}
			// call removeTagFields on RD
			readerDevice.removeTagFields(rdTagFields);
			// return void
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setRemoveTagFields(voidType);
		}

		else if (command.getRemoveAllTagFields() != null) {
			log.debug("Calling ReaderDevice.removeAllTagFields()");
			readerDevice.removeAllTagFields();
			// return void
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setRemoveAllTagFields(voidType);
		}

		else if (command.getGetTagField() != null) {
			log.debug("Calling ReaderDevice.getTagField()");
			TagField tagField = readerDevice.getTagField(command
					.getGetTagField().getName());
			TagFieldReturnType tagFieldReturn = replyFactory
					.createTagFieldReturnType();
			tagFieldReturn.setReturnValue(tagField.getName());
			reply.setGetTagField(tagFieldReturn);
		}

		else if (command.getGetAllTagFields() != null) {
			log.debug("Calling ReaderDevice.getAllTagFields()");
			TagField[] tagFields = readerDevice.getAllTagFields();
			TagFieldListReturnType tagFieldListReturn = replyFactory
					.createTagFieldListReturnType();
			TagFieldListParamType tagFieldListItems = replyFactory
					.createTagFieldListParamType();
			TagFieldListParamType.List list = replyFactory
					.createTagFieldListParamTypeList();
			List valueList = list.getValue();

			for (int i = 0; i < tagFields.length; i++) {
				valueList.add(tagFields[i].getName());
			}
			tagFieldListItems.setList(list);
			tagFieldListReturn.setReturnValue(tagFieldListItems);
			reply.setGetAllTagFields(tagFieldListReturn);
		}

		else if (command.getResetToDefaultSettings() != null) {
			log.debug("Calling ReaderDevice.resetToDefaultSettings()");
			readerDevice.resetToDefaultSettings();
			msgLayer.reset();
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setResetToDefaultSettings(voidType);
		}

		else if (command.getReboot() != null) {
			log.debug("Calling ReaderDevice.reboot()");
			readerDevice.reboot();
			reboot();
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setReboot(voidType);
		}

		else if (command.getGoodbye() != null) {
			log.debug("Calling ReaderDevice.goodbye()");
			NoParamType voidType = replyFactory.createNoParamType();
			reply.setGoodbye(voidType);

			/*
			 * set a close request on the connection. The client should then
			 * be implicitly removed from <code>Clients</code> by closing
			 * the connection.
			 */
			im.getConnection().requestClose();
			return reply;
		}

		else if (command.getGetReadPoint() != null) {
			log.debug("Calling ReaderDevice.getReadPoint()");
			ReadPoint readPoint = readerDevice.getReadPoint(command
					.getGetReadPoint().getName());
			ReadPointReturnType readPointReturn = replyFactory
					.createReadPointReturnType();
			readPointReturn.setReturnValue(readPoint.getName());
			reply.setGetReadPoint(readPointReturn);
		}

		else if (command.getGetAllReadPoints() != null) {
			log.debug("Calling ReaderDevice.getAllReadPoints()");
			ReadPoint[] readPoints = readerDevice.getAllReadPoints();
			ReadPointListReturnType readPointListReturn = replyFactory
					.createReadPointListReturnType();
			ReadPointListParamType readPointListItems = replyFactory
					.createReadPointListParamType();
			ReadPointListParamType.List list = replyFactory
					.createReadPointListParamTypeList();
			List valueList = list.getValue();
			for (int i = 0; i < readPoints.length; i++) {
				valueList.add(readPoints[i].getName());
			}
			readPointListItems.setList(list);
			readPointListReturn.setReturnValue(readPointListItems);
			reply.setGetAllReadPoints(readPointListReturn);
		}

		return reply;
	}

	/**
	 * Executes a command on the Source object.
	 * 
	 * @param command
	 *            The command to be executed.
	 * @param targetName
	 *            The unique identifier (object name) of a <code>Source</code>
	 *            instance or <code>null</code> if it is a static method.
	 * @param im
	 *            The <code>IncomingMessage</code> that caused this command.
	 * @return The reply of a command execution.
	 * @throws ReaderProtocolException
	 *             Exceptions from the Trigger
	 */
	private SourceReply executeCommand(final SourceCommand command,
			final String targetName, final IncomingMessage im)
			throws ReaderProtocolException {
		Source source = null;
		try {
			log.debug("Calling a Source Command.");
			SourceReply reply = replyFactory.createSourceReply();

			if (command.getCreate() != null) {
				log.debug("Calling Source.create()");
				source = Source.create(command.getCreate().getName(),
						readerDevice);
				SourceReturnType sourceReturn = replyFactory
						.createSourceReturnType();
				sourceReturn.setReturnValue(source.getName());
				reply.setCreate(sourceReturn);
			}

			else if (command.getGetName() != null) {
				log.debug("Calling Source.getName()");
				// Irrelevant command, simply return the targetName
				StringReturnType stringReturn = replyFactory
						.createStringReturnType();
				stringReturn.setReturnValue(targetName);
				reply.setGetName(stringReturn);
			}

			else {
				// Check the existence of the target
				if (targetName == null) {
					throw new ReaderProtocolException(
							MessagingConstants.ERROR_SOURCE_NOT_FOUND_STR,
							MessagingConstants.ERROR_SOURCE_NOT_FOUND);
				} else {
					source = readerDevice.getSource(targetName);
				}

				if (command.getIsFixed() != null) {
					log.debug("Calling Source.isFixed()");
					BooleanReturnType boolReturn = replyFactory
							.createBooleanReturnType();
					boolReturn.setReturnValue(source.isFixed());
					reply.setIsFixed(boolReturn);
				}

				else if (command.getAddReadPoints() != null) {
					log.debug("Calling Source.addReadPoints()");

					org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReadPointListParamType.List valueElems = command
							.getAddReadPoints().getReadPoints().getList();
					List valueList = valueElems.getValue(); // List of strings
					// with the read
					// points names

					/*
					 * aquiring phase - fetch all read points from the RD if a
					 * read point doesn't exist an exception is thrown in the RD
					 * and we throw this exception up to the dispatch method. So
					 * either all triggers are added or nothing.
					 */
					ReadPoint[] rdReadPoints = new ReadPoint[valueList.size()];
					int index = 0;
					for (Iterator it = valueList.iterator(); it.hasNext();) {
						String readPointName = (String) it.next();
						ReadPoint readPoint = readerDevice
								.getReadPoint(readPointName);
						rdReadPoints[index] = readPoint;
						index++;
					}
					// call addReadPoints on RD
					source.addReadPoints(rdReadPoints);
					// return void
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setAddReadPoints(voidType);
				}

				else if (command.getRemoveReadPoints() != null) {
					log.debug("Calling Source.removeReadPoints()");

					org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReadPointListParamType.List valueElems = command
							.getRemoveReadPoints().getReadPoints().getList();
					List valueList = valueElems.getValue(); // List of strings
					// with the source
					// names

					/*
					 * aquiring phase - fetch all read points from the RD if a
					 * read point doesn't exist an exception then just this one
					 * should be ignored (all others should be removed).
					 */
					ReadPoint[] rdReadPoints = new ReadPoint[valueList.size()];
					int index = 0;
					for (Iterator it = valueList.iterator(); it.hasNext();) {
						String readPointName = (String) it.next();
						try {
							ReadPoint readPoint = readerDevice
									.getReadPoint(readPointName);
							rdReadPoints[index] = readPoint;
							index++;
						} catch (ReaderProtocolException e) {
							// readpoint not found ignore it
						}
					}
					// call removeReadPoints on RD
					source.removeReadPoints(rdReadPoints);
					// return void
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setRemoveReadPoints(voidType);

				}

				else if (command.getRemoveAllReadPoints() != null) {
					log.debug("Calling Source.removeAllReadPoints()");
					source.removeAllReadPoints();
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setRemoveAllReadPoints(voidType);
				}

				else if (command.getGetReadPoint() != null) {
					log.debug("Calling Source.getReadPoint()");
					ReadPoint readPoint = source.getReadPoint(command
							.getGetReadPoint().getName());
					ReadPointReturnType readPointReturn = replyFactory
							.createReadPointReturnType();
					readPointReturn.setReturnValue(readPoint.getName());
					reply.setGetReadPoint(readPointReturn);
				}

				else if (command.getGetAllReadPoints() != null) {
					log.debug("Calling Source.getAllReadPoints()");
					ReadPoint[] readPoints = source.getAllReadPoints();
					ReadPointListReturnType readPointListReturn = replyFactory
							.createReadPointListReturnType();
					ReadPointListParamType readPointListItems = replyFactory
							.createReadPointListParamType();
					ReadPointListParamType.List list = replyFactory
							.createReadPointListParamTypeList();
					List valueList = list.getValue();
					for (int i = 0; i < readPoints.length; i++) {
						valueList.add(readPoints[i].getName());
					}
					readPointListItems.setList(list);
					readPointListReturn.setReturnValue(readPointListItems);
					reply.setGetAllReadPoints(readPointListReturn);
				}

				else if (command.getAddReadTriggers() != null) {
					log.debug("Calling Source.addReadTriggers()");

					org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TriggerListParamType.List valueElems = command
							.getAddReadTriggers().getTriggers().getList();
					List valueList = valueElems.getValue(); // List of strings
					// with the read
					// points names

					/*
					 * aquiring phase - fetch all read trigger from the RD if a
					 * read trigger doesn't exist an exception is thrown in the
					 * RD and we throw this exception up to the dispatch method.
					 * So either all triggers are added or nothing.
					 */
					Trigger[] rdReadTriggers = new Trigger[valueList.size()];
					int index = 0;
					for (Iterator it = valueList.iterator(); it.hasNext();) {
						String readTriggerName = (String) it.next();
						Trigger readTrigger = readerDevice
								.getTrigger(readTriggerName);
						rdReadTriggers[index] = readTrigger;
						index++;
					}
					// call addReadPoints on RD
					source.addReadTriggers(rdReadTriggers);
					// return void
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setAddReadTriggers(voidType);
				}

				else if (command.getRemoveReadTriggers() != null) {
					log.debug("Calling Source.removeReadTriggers()");

					org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TriggerListParamType.List valueElems = command
							.getRemoveReadTriggers().getTriggers().getList();
					List valueList = valueElems.getValue(); // List of strings
					// with the read
					// trigger names

					/*
					 * aquiring phase - fetch all read triggers from the RD if a
					 * read trigger doesn't exist an exception then just this
					 * one should be ignored (all others should be removed).
					 */
					Trigger[] rdReadTriggers = new Trigger[valueList.size()];
					int index = 0;
					for (Iterator it = valueList.iterator(); it.hasNext();) {
						String readTriggerName = (String) it.next();
						try {
							Trigger readTrigger = source
									.getReadTrigger(readTriggerName);
							rdReadTriggers[index] = readTrigger;
							index++;
						} catch (ReaderProtocolException e) {
							// readpoint not found ignore it
						}
					}
					// call removeReadTriggers on RD
					source.removeReadTriggers(rdReadTriggers);
					// return void
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setRemoveReadPoints(voidType);
				}

				else if (command.getRemoveAllReadTriggers() != null) {
					log.debug("Calling Source.removeAllReadTriggers()");
					;
					source.removeAllReadTriggers();
					// return void
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setRemoveAllReadTriggers(voidType);
				}

				else if (command.getGetReadTrigger() != null) {
					log.debug("Calling Source.getReadTrigger()");
					Trigger readTrigger = source.getReadTrigger(command
							.getGetReadTrigger().getName());
					TriggerReturnType triggerReturn = replyFactory
							.createTriggerReturnType();
					triggerReturn.setReturnValue(readTrigger.getName());
					reply.setGetReadTrigger(triggerReturn);
				}

				else if (command.getGetAllReadTriggers() != null) {
					log.debug("Calling Source.getAllReadTriggers()");

					Trigger[] readTriggers = source.getAllReadTriggers();
					TriggerListReturnType triggerListReturn = replyFactory
							.createTriggerListReturnType();
					TriggerListParamType triggerListItems = replyFactory
							.createTriggerListParamType();
					TriggerListParamType.List list = replyFactory
							.createTriggerListParamTypeList();
					List valueList = list.getValue();
					for (int i = 0; i < readTriggers.length; i++) {
						valueList.add(readTriggers[i].getName());
					}
					triggerListItems.setList(list);
					triggerListReturn.setReturnValue(triggerListItems);
					reply.setGetAllReadTriggers(triggerListReturn);
				}

				else if (command.getAddTagSelectors() != null) {
					log.debug("Calling Source.addTagSelectors()");

					org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TagSelectorListParamType.List valueElems = command
							.getAddTagSelectors().getSelectors().getList();
					List valueList = valueElems.getValue(); // List of strings
					// with the tag
					// selector names

					/*
					 * aquiring phase - fetch all tag selectors from the RD if a
					 * tag selectors doesn't exist an exception is thrown in the
					 * RD and we throw this exception up to the dispatch method.
					 * So either all triggers are added or nothing.
					 */
					TagSelector[] rdTagSelectors = new TagSelector[valueList
							.size()];
					int index = 0;
					for (Iterator it = valueList.iterator(); it.hasNext();) {
						String tagSelectorName = (String) it.next();
						TagSelector tagSelector = readerDevice
								.getTagSelector(tagSelectorName);
						rdTagSelectors[index] = tagSelector;
						index++;
					}
					// call addTagSelectors on RD
					source.addTagSelectors(rdTagSelectors);
					// return void
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setAddTagSelectors(voidType);
				}

				else if (command.getRemoveTagSelectors() != null) {
					log.debug("Calling Source.removeTagSelectors()");
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setRemoveTagSelectors(voidType);
					//

					org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TagSelectorListParamType.List valueElems = command
							.getRemoveTagSelectors().getSelectors().getList();
					List valueList = valueElems.getValue(); // List of strings
					// with the tag
					// selector names

					/*
					 * aquiring phase - fetch all tag selectors from the RD if a
					 * tag selector doesn't exist an exception then just this
					 * one should be ignored (all others should be removed).
					 */
					TagSelector[] rdTagSelectors = new TagSelector[valueList
							.size()];
					int index = 0;
					for (Iterator it = valueList.iterator(); it.hasNext();) {
						String tagSelectorName = (String) it.next();
						try {
							TagSelector tagSelector = source
									.getTagSelector(tagSelectorName);
							rdTagSelectors[index] = tagSelector;
							index++;
						} catch (ReaderProtocolException e) {
							// TagSelector not found ignore it
	
						}
					}
					// call removeTagSelectors on RD
					source.removeTagSelectors(rdTagSelectors);
					// return void
				}

				else if (command.getRemoveAllTagSelectors() != null) {
					log.debug("Calling Source.removeAllTagSelectors()");

					source.removeAllTagSelectors();
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setRemoveAllTagSelectors(voidType);
				}

				else if (command.getGetTagSelector() != null) {
					log.debug("Calling Source.getTagSelector()");

					TagSelector tagSelector = source.getTagSelector(command
							.getGetTagSelector().getName());
					TagSelectorReturnType tagSelectorReturn = replyFactory
							.createTagSelectorReturnType();
					tagSelectorReturn.setReturnValue(tagSelector.getName());
					reply.setGetTagSelector(tagSelectorReturn);
				}

				else if (command.getGetAllTagSelectors() != null) {
					log.debug("Calling Source.getTagAllSelectors()");

					TagSelector[] tagSelectors = source.getAllTagSelectors();
					TagSelectorListReturnType tagSelectorListReturn = replyFactory
							.createTagSelectorListReturnType();
					TagSelectorListParamType tagSelectorListItems = replyFactory
							.createTagSelectorListParamType();
					TagSelectorListParamType.List list = replyFactory
							.createTagSelectorListParamTypeList();
					List valueList = list.getValue();
					for (int i = 0; i < tagSelectors.length; i++) {
						valueList.add(tagSelectors[i].getName());
					}
					tagSelectorListItems.setList(list);
					tagSelectorListReturn.setReturnValue(tagSelectorListItems);
					reply.setGetAllTagSelectors(tagSelectorListReturn);
				}

				else if (command.getGetGlimpsedTimeout() != null) {
					log.debug("Calling Source.getGlimpsedTimeout()");

					IntReturnType intReturn = replyFactory
							.createIntReturnType();
					intReturn.setReturnValue(source.getGlimpsedTimeout());
					reply.setGetGlimpsedTimeout(intReturn);
				}

				else if (command.getSetGlimpsedTimeout() != null) {
					log.debug("Calling Source.setGlimpsedTimeout()");
					int timeout = command.getSetGlimpsedTimeout().getTimeout();

					source.setGlimpsedTimeout(timeout);
					// return void
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setSetGlimpsedTimeout(voidType);
				}

				else if (command.getGetObservedThreshold() != null) {
					log.debug("Calling Source.getObservedThreshold()");

					IntReturnType intReturn = replyFactory
							.createIntReturnType();
					intReturn.setReturnValue(source.getObservedThreshold());
					reply.setGetObservedThreshold(intReturn);
				}

				else if (command.getSetObservedThreshold() != null) {
					log.debug("Calling Source.setObservedThreshold()");
					int threshold = command.getSetObservedThreshold()
							.getThreshold();

					source.setObservedThreshold(threshold);
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setSetObservedThreshold(voidType);
				}

				else if (command.getGetObservedTimeout() != null) {
					log.debug("Calling Source.getObservedTimeout()");

					IntReturnType intReturn = replyFactory
							.createIntReturnType();
					intReturn.setReturnValue(source.getObservedTimeout());
					reply.setGetObservedTimeout(intReturn);
				}

				else if (command.getSetObservedTimeout() != null) {
					log.debug("Calling Source.setObservedTimeout()");
					int timeout = command.getSetObservedTimeout().getTimeout();

					source.setObservedTimeout(timeout);
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setSetObservedTimeout(voidType);
				}

				else if (command.getGetLostTimeout() != null) {
					log.debug("Calling Source.getLostTimeout()");

					IntReturnType intReturn = replyFactory
							.createIntReturnType();
					intReturn.setReturnValue(source.getLostTimeout());
					reply.setGetLostTimeout(intReturn);
				}

				else if (command.getSetLostTimeout() != null) {
					log.debug("Calling Source.setLostTimeout()");
					int timeout = command.getSetLostTimeout().getTimeout();

					source.setLostTimeout(timeout);
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setSetLostTimeout(voidType);
				}

				else if (command.getRawReadIDs() != null) {
					log.debug("Calling Source.rawReadIDs");

					ReadReport report;
					// check if the optional parameter dataSelector is set
					DataSelector ds = null;
					if (command.getRawReadIDs().getDataSelector() != null
							&& !command.getRawReadIDs().getDataSelector()
									.equals("null")) {
						ds = readerDevice.getDataSelector(command
								.getRawReadIDs().getDataSelector());
						report = source.rawReadIDs(ds);
					} else {
						ds = readerDevice.getCurrentDataSelector();
					}
					report = source.rawReadIDs(ds);
					RawReadIDs rawReadTypeReturn = replyFactory
							.createSourceReplyRawReadIDs();
					ReadReportType reportReply = createReadReportReply(report,
							ds);
					rawReadTypeReturn.setReturnValue(reportReply);
					reply.setRawReadIDs(rawReadTypeReturn);
				}

				else if (command.getReadIDs() != null) {
					log.debug("Calling Source.readIDs()");
					ReadReport report;
					// Check if the optional parameter dataSelector is set
					DataSelector ds = null;
					if (command.getReadIDs().getDataSelector() != null
							&& !command.getReadIDs().getDataSelector().equals(
									"null")) {
						ds = readerDevice.getDataSelector(command.getReadIDs()
								.getDataSelector());
					} else {
						ds = readerDevice.getCurrentDataSelector();
					}
					report = source.readIDs(ds);

					ReadIDs readIDReturn = replyFactory
							.createSourceReplyReadIDs();
					ReadReportType reportReply = createReadReportReply(report,
							ds);
					readIDReturn.setReturnValue(reportReply);
					reply.setReadIDs(readIDReturn);
				}

				else if (command.getRead() != null) {
					log.debug("Calling Source.read()");

					ReadReport report = null;
					// Check if the optional parameter dataSelector is set
					DataSelector ds = null;
					if (command.getRead().getDataSelector() != null
							&& !command.getRead().getDataSelector().equals(
									"null")) {
						ds = readerDevice.getDataSelector(command.getRead()
								.getDataSelector());
						/*
						 * TODO: passwords currently not implemented in the HAL -
						 * set passwords to null
						 */

					} else {
						ds = readerDevice.getCurrentDataSelector();
					}
					report = source.read(ds, null);

					SourceReply.Read readReturn = replyFactory
							.createSourceReplyRead();
					ReadReportType reportReply = createReadReportReply(report,
							ds);
					readReturn.setReturnValue(reportReply);

					reply.setRead(readReturn);
				}

				else if (command.getWriteID() != null) {
					log.debug("Calling Source.writeID()");
					String[] passwords = null;
					TagSelector[] selectors = null;

					if (command.getWriteID().getPasswords() != null) {
						passwords = getString(command.getWriteID()
								.getPasswords());
					}
					if (command.getWriteID().getSelectors() != null) {
						selectors = getTagSelector(command.getWriteID()
								.getSelectors());
					}
					String id = new String(command.getWriteID().getId());
					source.writeID(id, passwords, selectors);

					// return void
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setWriteID(voidType);
				}

				else if (command.getWrite() != null) {
					log.debug("Calling Source.write()");
					Write writeCommand = command.getWrite();
					String[] passwords = null;
					TagSelector[] selectors = null;

					TagFieldValue[] tfValues = null;
					if (writeCommand.getData() != null) {
						tfValues = getTagFieldValue(writeCommand.getData());
					}
					if (writeCommand.getPasswords() != null) {
						passwords = getString(writeCommand.getPasswords());
					}
					if (writeCommand.getSelectors() != null) {
						selectors = getTagSelector(writeCommand.getSelectors());
					}
					source.write(tfValues, passwords, selectors);
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setWrite(voidType);
				}

				else if (command.getKill() != null) {
					log.debug("Calling Source.kill()");
					Kill killCommand = command.getKill();
					String[] passwords = null;
					TagSelector[] selectors = null;

					if (killCommand.getPasswords() != null) {
						passwords = getString(killCommand.getPasswords());
					}
					if (killCommand.getSelectors() != null) {
						selectors = getTagSelector(killCommand.getSelectors());
					}
					source.kill(passwords, selectors);
					// return void
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setKill(voidType);
				}

				else if (command.getGetReadCyclesPerTrigger() != null) {
					log.debug("Calling Source.getReadCyclesPerTrigger()");

					IntReturnType intReturn = replyFactory
							.createIntReturnType();
					intReturn.setReturnValue(source.getReadCyclesPerTrigger());
					reply.setGetReadCyclesPerTrigger(intReturn);
				}

				else if (command.getSetReadCyclesPerTrigger() != null) {
					log.debug("Calling Source.setReadCyclesPerTrigger()");
					int readCycles = command.getSetReadCyclesPerTrigger()
							.getCycles();

					source.setReadCyclesPerTrigger(readCycles);
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setSetReadCyclesPerTrigger(voidType);
				}

				else if (command.getGetMaxReadDutyCycle() != null) {
					log.debug("Calling Source.getMayReadDutyCycle()");

					IntReturnType intReturn = replyFactory
							.createIntReturnType();
					intReturn.setReturnValue(source.getMaxReadDutyCycles());
					reply.setGetMaxReadDutyCycle(intReturn);
				}

				else if (command.getSetMaxReadDutyCycle() != null) {
					log.debug("Calling Source.setMaxReadDutyCycle()");
					int dutyCycles = command.getSetMaxReadDutyCycle()
							.getDutyCycle();

					source.setMaxReadDutyCycles(dutyCycles);
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setSetMaxReadDutyCycle(voidType);
				}

				else if (command.getGetReadTimeout() != null) {
					log.debug("Calling Source.getReadTimeout()");

					IntReturnType intReturn = replyFactory
							.createIntReturnType();
					intReturn.setReturnValue(source.getReadTimeout());
					reply.setGetReadTimeout(intReturn);
				}

				else if (command.getSetReadTimeout() != null) {
					log.debug("Calling Source.setReadTimeout()");
					int readTimeout = command.getSetReadTimeout().getTimeout();

					source.setReadTimeout(readTimeout);
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setSetReadTimeout(voidType);
				}

				else if (command.getGetSession() != null) {
					log.debug("Calling Source.getSession() not yet supported.");

					// TODO: Fehler in XSD
				}

				else if (command.getSetSession() != null) {
					log.debug("Calling Source.setSession() not yet supported.");
					// TODO: Fehler in XSD
				}
			}
			return reply;
		} catch (JAXBException e) {
			log.error(e);
			return null;
		}
	}

	/**
	 * Executes a command on the ReadPoint object.
	 * 
	 * @param command
	 *            The command to be executed.
	 * @param targetName
	 *            The unique identifier (object name) of a
	 *            <code>ReadPoint</code> instance or <code>null</code> if it
	 *            is a static method.
	 * @param im
	 *            The <code>IncomingMessage</code> that caused this command.
	 * @return The reply of a command execution.
	 * @throws ReaderProtocolException
	 *             Exceptions from the Trigger
	 */
	private ReadPointReply executeCommand(final ReadPointCommand command,
			final String targetName, final IncomingMessage im) {
		log.debug("Calling a ReaderPoint Command.");
		ReadPointReply reply = replyFactory.createReadPointReply();

		if (command.getGetName() != null) {
			log.debug("Calling ReadPoint.getName()");
			StringReturnType stringReturn = replyFactory
					.createStringReturnType();
			// Irrelevant command, simply return the targetName
			stringReturn.setReturnValue(targetName);
			reply.setGetName(stringReturn);
		}

		return reply;
	}

	/**
	 * Executes a command on the Trigger object.
	 * 
	 * @param command
	 *            The command to be executed.
	 * @param targetName
	 *            The unique identifier (object name) of a <code>Trigger</code>
	 *            instance or <code>null</code> if it is a static method.
	 * @param im
	 *            The <code>IncomingMessage</code> that caused this command.
	 * @return The reply of a command execution.
	 * @throws ReaderProtocolException
	 *             Exceptions from the Trigger
	 */
	private TriggerReply executeCommand(final TriggerCommand command,
			final String targetName, final IncomingMessage im)
			throws ReaderProtocolException {
		TriggerReply reply = replyFactory.createTriggerReply();
		Trigger trigger = null;
		log.debug("Calling a Trigger Command.");

		if (command.getCreate() != null) {
			log.debug("Calling Trigger.create()");
			org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TriggerCommand.Create createCommand = command
					.getCreate();
			String name = createCommand.getName();
			String triggerTypeName = createCommand.getTriggerType();
			String triggerValue = createCommand.getTriggerValue();
			trigger = Trigger.create(name, triggerTypeName, triggerValue,
					readerDevice);
			TriggerReturnType triggerReturn = replyFactory
					.createTriggerReturnType();
			triggerReturn.setReturnValue(trigger.getName());
			reply.setCreate(triggerReturn);
		}

		else if (command.getGetName() != null) {
			log.debug("Calling Trigger.getName()");
			StringReturnType stringReturn = replyFactory
					.createStringReturnType();
			// irrelevant command, simply return the targetName
			stringReturn.setReturnValue(targetName);
			reply.setGetName(stringReturn);
		}

		else {
			// Check the existence of the target
			if (targetName == null) {
				throw new ReaderProtocolException(
						MessagingConstants.ERROR_TRIGGER_NOT_FOUND_STR,
						MessagingConstants.ERROR_TRIGGER_NOT_FOUND);
			} else {
				trigger = readerDevice.getTrigger(targetName);
			}

			if (command.getGetMaxNumberSupported() != null) {
				log.debug("Calling Trigger.getMaxNumberSupported()");

				IntReturnType intReturn = replyFactory
						.createIntReturnType();
				intReturn.setReturnValue(trigger.getMaxNumberSupported());
				reply.setGetMaxNumberSupported(intReturn);
			}

			else if (command.getGetType() != null) {
				log.debug("Calling Trigger.getType()");

				TriggerTypeReturnType triggerTypeReturn = replyFactory
						.createTriggerTypeReturnType();
				triggerTypeReturn.setReturnValue(trigger.getType());
				reply.setGetType(triggerTypeReturn);
			}

			else if (command.getGetValue() != null) {
				log.debug("Calling Trigger.getValue()");

				TriggerValueReturnType triggerValueReturn = replyFactory
						.createTriggerValueReturnType();
				triggerValueReturn.setReturnValue(trigger.getValue());
				reply.setGetValue(triggerValueReturn);
			}

			else if (command.getFire() != null) {
				log.debug("Calling Trigger.fire()");

				trigger.fire();
				// return void
				NoParamType voidType = replyFactory.createNoParamType();
				reply.setFire(voidType);
			}
		}

		return reply;
	}

	/**
	 * Executes a command on the TagSelector object.
	 * 
	 * @param command
	 *            The command to be executed.
	 * @param targetName
	 *            The unique identifier (object name) of a
	 *            <code>TagSelector</code> instance or <code>null</code> if
	 *            it is a static method.
	 * @param im
	 *            The <code>IncomingMessage</code> that caused this command.
	 * @return The reply of a command execution.
	 * @throws ReaderProtocolException
	 *             Exceptions from the TagSelector
	 */
	private TagSelectorReply executeCommand(final TagSelectorCommand command,
			final String targetName, final IncomingMessage im)
			throws ReaderProtocolException {

		log.debug("Calling a TagSelector Command.");
		TagSelector tagSelector = null;
		TagSelectorReply reply = replyFactory.createTagSelectorReply();

		if (command.getCreate() != null) {
			log.debug("Calling TagSelector.create()");
			TagSelectorCommand.Create createCommand = command
					.getCreate();
			// prepare parameters
			String name = createCommand.getName();
			String tagFieldName = createCommand.getField();
			TagField tagField = readerDevice.getTagField(tagFieldName);
			String value = new String(createCommand.getValue());
			String mask = new String(createCommand.getMask());
			boolean inclusiveFlag = createCommand.isInclusiveFlag();
			// call RD
			tagSelector = TagSelector.create(name, tagField, value, mask,
					inclusiveFlag, readerDevice);
			// create reply
			TagSelectorReturnType tagSelectorReturn = replyFactory
					.createTagSelectorReturnType();
			tagSelectorReturn.setReturnValue(tagSelector.getName());
			reply.setCreate(tagSelectorReturn);
		}

		else if (command.getGetName() != null) {
			log.debug("Calling TagSelector.getName()");
			StringReturnType stringReturn = replyFactory
					.createStringReturnType();
			// irrelevant command, simply return the targetName
			stringReturn.setReturnValue(targetName);
			reply.setGetName(stringReturn);
		}

		else {
			// Check the existence of the target
			if (targetName == null) {
				throw new ReaderProtocolException(
						MessagingConstants.ERROR_TAGSELECTOR_NOT_FOUND_STR,
						MessagingConstants.ERROR_TAGSELECTOR_NOT_FOUND);
			} else {
				tagSelector = readerDevice.getTagSelector(targetName);
			}

			if (command.getGetMaxNumberSupported() != null) {
				log.debug("Calling TagSelector.getMaxNumberSupported()");

				IntReturnType intReturn = replyFactory
						.createIntReturnType();
				intReturn.setReturnValue(tagSelector
						.getMaxNumberSupported());
				reply.setGetMaxNumberSupported(intReturn);
			}

			else if (command.getGetTagField() != null) {
				log.debug("Calling TagSelector.getTagField()");

				TagFieldReturnType tagFieldReturn = replyFactory
						.createTagFieldReturnType();
				tagFieldReturn.setReturnValue(tagSelector.getTagField()
						.getName());
				reply.setGetTagField(tagFieldReturn);
			}

			else if (command.getGetValue() != null) {
				log.debug("Calling TagSelector.getValue()");

				HexStringReturnType hexStringReturn = replyFactory
						.createHexStringReturnType();
				hexStringReturn.setReturnValue(HexUtil
						.hexToByteArray(tagSelector.getValue()));
				reply.setGetValue(hexStringReturn);
			}

			else if (command.getGetMask() != null) {
				log.debug("Calling TagSelector.getMask()");

				HexStringReturnType hexStringReturn = replyFactory
						.createHexStringReturnType();
				hexStringReturn.setReturnValue(HexUtil
						.hexToByteArray(tagSelector.getMask()));
				reply.setGetMask(hexStringReturn);
			}

			else if (command.getGetInclusiveFlag() != null) {
				log.debug("Calling TagSelector.getInclusiveFlag()");

				BooleanReturnType boolReturn = replyFactory
						.createBooleanReturnType();
				boolReturn.setReturnValue(tagSelector.getInclusiveFlag());
				reply.setGetInclusiveFlag(boolReturn);
			}
		}
		return reply;
	}

	private NotificationChannelReply executeCommand(
			final NotificationChannelCommand command, final String targetName,
			final IncomingMessage im) throws ReaderProtocolException {

		NotificationChannel notificationChannel = null;

		try {
			log.debug("Calling a NotificationChannel Command.");
			NotificationChannelReply reply = replyFactory
					.createNotificationChannelReply();
			if (command.getCreate() != null) {
				try {
					String name = null;
					Address addr = null;
					log.debug("NotificationChannel.create("
							+ command.getCreate().getName() + ", "
							+ command.getCreate().getAddress() + ")");

					// prepare the parameters
					name = command.getCreate().getName();
					addr = new Address(command.getCreate().getAddress());

					Connection conn = im.getConnection();
					ReceiverHandshakeMessage receiverHandshake = conn
							.getReceiverHandshake();
					SenderHandshakeMessage senderHandshake = conn
							.getSenderHandshake();

					int port = createNotificationChannelConnection(addr, name,
							senderHandshake, receiverHandshake);
					if (addr.getMode() != null
			            && addr.getMode().equals(Address.MODE_LISTEN)) {
					   addr.setPort(port);
					}

               NotificationChannel channel = NotificationChannel.create(
                     name, addr.toString(), readerDevice);

					NotificationChannelReturnType notificationChannelReturn = replyFactory
							.createNotificationChannelReturnType();

					notificationChannelReturn.setReturnValue(channel.getName());
					reply.setCreate(notificationChannelReturn);
				} catch (MalformedURLException e) {
					log
							.debug("Invalid address used for the NotificationChannel.");
					throw new ReaderProtocolException(
							MessagingConstants.ERROR_PARAMETER_ILLEGAL_VALUE_STR,
							MessagingConstants.ERROR_PARAMETER_ILLEGAL_VALUE,
							"Cannot parse the parameter addr.");
				}
			}

			else if (command.getGetName() != null) {
				log.debug("NotificationChannel.getName()");
				// Irrelevant command, just return the target name from the
				// command
				StringReturnType stringReturn = replyFactory
						.createStringReturnType();
				stringReturn.setReturnValue(targetName);
				reply.setGetName(stringReturn);
			}

			else {
				// Check the existence of the target
				if (targetName == null) {
					throw new ReaderProtocolException(
							MessagingConstants.ERROR_CHANNEL_NOT_FOUND_STR,
							MessagingConstants.ERROR_CHANNEL_NOT_FOUND);
				} else {
					notificationChannel = readerDevice
							.getNotificationChannel(targetName);
				}

				if (command.getGetAddress() != null) {
					log.debug("NotificationChannel.getGetAddress()");

					AddressReturnType addressReturn = replyFactory
							.createAddressReturnType();
					addressReturn.setReturnValue(notificationChannel
							.getAddress().toString());
					reply.setGetAddress(addressReturn);
				}

				else if (command.getGetEffectiveAddress() != null) {
					log.debug("NotificationChannel.getEffectiveAddress()");

					AddressReturnType addressReturn = replyFactory
							.createAddressReturnType();
					addressReturn.setReturnValue(notificationChannel
							.getEffectiveAddress());
					reply.setGetEffectiveAddress(addressReturn);
				}

				else if (command.getSetAddress() != null) {
					log.debug("NotificationChannel.setAddress("
							+ command.getSetAddress().getAddress() + ")");

					String url = command.getSetAddress().getAddress();
					String oldUrl = notificationChannel.getEffectiveAddress();
					if (url == null || url.equals("")) {
						throw new ReaderProtocolException(
								MessagingConstants.ERROR_PARAMETER_INVALID_FORMAT_STR,
								MessagingConstants.ERROR_PARAMETER_INVALID_FORMAT);
					} else {
						try {
							if (oldUrl != null && !oldUrl.equals("")) {
								/*
								 * there is an existing NotificationChannel
								 * which should be closed first
								 */
								// TODO: existierende Verbindung schliessen
							}

							Address addr = new Address(url);

							Connection conn = im.getConnection();
							ReceiverHandshakeMessage receiverHandshake = conn
									.getReceiverHandshake();
							SenderHandshakeMessage senderHandshake = conn
									.getSenderHandshake();

							int port = createNotificationChannelConnection(
									addr, targetName, senderHandshake,
									receiverHandshake);
							addr.setPort(port);
							notificationChannel.setAddress(addr.toString());

							IntReturnType addressReturn = replyFactory
									.createIntReturnType();
							addressReturn.setReturnValue(port);
							reply.setSetAddress(addressReturn);
						} catch (MalformedURLException e) {
							log
									.debug("Invalid address used for the NotificationChannel.");
							throw new ReaderProtocolException(
									MessagingConstants.ERROR_PARAMETER_ILLEGAL_VALUE_STR,
									MessagingConstants.ERROR_PARAMETER_ILLEGAL_VALUE,
									"Cannot parse the parameter addr.");
						}
					}
				}

				else if (command.getGetDataSelector() != null) {
					log.debug("NotificationChannel.getDataSelector()");
					DataSelectorReturnType dataSelectorReturn = replyFactory
							.createDataSelectorReturnType();
					dataSelectorReturn.setReturnValue("GetDataSelectorFromRP");
					reply.setGetDataSelector(dataSelectorReturn);
				}

				else if (command.getSetDataSelector() != null) {
					log.debug("NotificationChannel.setDataSelector()");
					DataSelector ds = readerDevice.getDataSelector(command
							.getSetDataSelector().getDataSelector());

					notificationChannel.setDataSelector(ds);

					// return void
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setSetDataSelector(voidType);
				}

				else if (command.getAddSources() != null) {
					log.debug("NotificationChannel.addSources()");

					org.ow2.aspirerfid.reader.rp.impl.core.msg.command.SourceListParamType.List valueElems = command
							.getAddSources().getSources().getList();
					List valueList = valueElems.getValue(); // List of strings
					// with the source
					// names

					/*
					 * aquiring phase - fetch all sources from the RD if a
					 * source doesn't exist an exception is thrown in the RD and
					 * we throw this exception up to the dispatch method. So
					 * either all sources are added or nothing.
					 */
					Source[] rdSources = new Source[valueList.size()];
					int index = 0;
					for (Iterator it = valueList.iterator(); it.hasNext();) {
						String sourceName = (String) it.next();
						Source source = readerDevice.getSource(sourceName);
						rdSources[index] = source;
						index++;
					}
					// call addSources on RD
					notificationChannel.addSources(rdSources);
					// return void
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setAddSources(voidType);
				}

				else if (command.getRemoveSources() != null) {
					log.debug("NotificationChannel.removeSources()");

					org.ow2.aspirerfid.reader.rp.impl.core.msg.command.SourceListParamType.List valueElems = command
							.getRemoveSources().getSources().getList();
					Source[] rdSources = getSource(valueElems);
					// call removeSources on RD
					notificationChannel.removeSources(rdSources);
					// return void
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setRemoveSources(voidType);
				}

				else if (command.getRemoveAllSources() != null) {
					log.debug("NotificationChannel.removeAllSources()");

					notificationChannel.removeAllSources();
					// return void
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setRemoveAllSources(voidType);
				}

				else if (command.getGetSource() != null) {
					log.debug("NotificationChannel.getSources()");

					Source rdSource = notificationChannel.getSource(command
							.getGetSource().getName());

					SourceReturnType sourceReturn = replyFactory
							.createSourceReturnType();
					sourceReturn.setReturnValue(rdSource.getName());
					reply.setGetSource(sourceReturn);
				}

				else if (command.getGetAllSources() != null) {
					log.debug("NotificationChannel.getAllSources()");

					Source[] channels = notificationChannel.getAllSources();
					SourceListReturnType sourceListReturn = replyFactory
							.createSourceListReturnType();
					SourceListParamType sourceListItems = replyFactory
							.createSourceListParamType();
					SourceListParamType.List list = replyFactory
							.createSourceListParamTypeList();
					List valueList = list.getValue();
					for (int i = 0; i < channels.length; i++) {
						valueList.add(channels[i].getName());
					}
					sourceListItems.setList(list);
					sourceListReturn.setReturnValue(sourceListItems);
					reply.setGetAllSources(sourceListReturn);
				}

				else if (command.getAddNotificationTriggers() != null) {
					log.debug("NotificationChannel.addNotificationTriggers()");

					org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TriggerListParamType.List valueElems = command
							.getAddNotificationTriggers().getTriggers()
							.getList();
					List valueList = valueElems.getValue(); // List of strings
					// with the trigger
					// names

					/*
					 * aquiring phase - fetch all triggers from the RD if a
					 * trigger doesn't exist an exception is thrown in the RD
					 * and we throw this exception up to the dispatch method. So
					 * either all triggers are added or nothing.
					 */
					Trigger[] rdTriggers = new Trigger[valueList.size()];
					int index = 0;
					for (Iterator it = valueList.iterator(); it.hasNext();) {
						String triggerName = (String) it.next();
						Trigger trigger = readerDevice.getTrigger(triggerName);
						rdTriggers[index] = trigger;
						index++;
					}
					// call addNotificationTriggers on RD
					notificationChannel.addNotificationTriggers(rdTriggers);
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setAddNotificationTriggers(voidType);
				}

				else if (command.getRemoveNotificationTriggers() != null) {
					log
							.debug("NotificationChannel.removeNotificationTriggers()");

					org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TriggerListParamType.List valueElems = command
							.getRemoveNotificationTriggers().getTriggers()
							.getList();
					List valueList = valueElems.getValue(); // List of strings
					// with the trigger
					// names

					/*
					 * aquiring phase - fetch all triggers from the RD if a
					 * trigger doesn't exist an exception then just this one
					 * should be ignored (all others should be removed).
					 */
					Trigger[] rdTriggers = new Trigger[valueList.size()];
					int index = 0;
					for (Iterator it = valueList.iterator(); it.hasNext();) {
						String triggerName = (String) it.next();
						try {
							Trigger trigger = readerDevice
									.getTrigger(triggerName);
							rdTriggers[index] = trigger;
							index++;
						} catch (ReaderProtocolException e) {
							// trigger not found ignore it
						}
					}
					// call removeNotificationTriggers on RD
					notificationChannel.removeNotificationTriggers(rdTriggers);
					// return void
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setRemoveNotificationTriggers(voidType);
				}

				else if (command.getRemoveAllNotificationTriggers() != null) {
					log
							.debug("NotificationChannel.removeAllNotificationTriggers()");

					notificationChannel.removeAllNotificationTriggers();
					// return void
					NoParamType voidType = replyFactory.createNoParamType();
					reply.setRemoveAllNotificationTriggers(voidType);
				}

				else if (command.getGetAllNotificationTriggers() != null) {
					log
							.debug("NotificationChannel.getAllNotificationTriggers()");

					Trigger[] triggers = notificationChannel
							.getAllNotificationTriggers();
					TriggerListReturnType triggerListReturn = replyFactory
							.createTriggerListReturnType();
					TriggerListParamType triggerListItems = replyFactory
							.createTriggerListParamType();
					TriggerListParamType.List list = replyFactory
							.createTriggerListParamTypeList();
					List valueList = list.getValue();
					for (int i = 0; i < triggers.length; i++) {
						valueList.add(triggers[i].getName());
					}
					triggerListItems.setList(list);
					triggerListReturn.setReturnValue(triggerListItems);
					reply.setGetAllNotificationTriggers(triggerListReturn);
				}

				else if (command.getGetNotificationTrigger() != null) {
					log.debug("NotificationChannel.getNotificationTrigger()");

					Trigger rdTrigger = notificationChannel
							.getNotificationTrigger(command
									.getGetNotificationTrigger().getName());

					TriggerReturnType triggerReturn = replyFactory
							.createTriggerReturnType();
					triggerReturn.setReturnValue(rdTrigger.getName());
					reply.setGetNotificationTrigger(triggerReturn);
				}

				else if (command.getReadQueuedData() != null) {
					log.debug("NotificationChannel.readQueuedData()");

					ReadReport report;
					report = notificationChannel.readQueuedData(command
							.getReadQueuedData().isClearBuffer());
					ReadReportType reportReply = createReadReportReply(report);
					ReadQueuedData readQueuedDataReturn = replyFactory
							.createNotificationChannelReplyReadQueuedData();
					readQueuedDataReturn.setReturnValue(reportReply);
					reply.setReadQueuedData(readQueuedDataReturn);
				}
			}
			return reply;
		} catch (JAXBException e) {
			log.error(e);
			return null;
		}
	}

	/**
	 * Executes a command on the DataSelector object.
	 * 
	 * @param command
	 *            The command to be executed.
	 * @param targetName
	 *            The unique identifier (object name) of a
	 *            <code>DataSelector</code> instance or <code>null</code> if
	 *            it is a static method.
	 * @param im
	 *            The <code>IncomingMessage</code> that caused this command.
	 * @return The reply of a command execution.
	 * @throws ReaderProtocolException
	 *             Exceptions from the DataSelector
	 */
	private DataSelectorReply executeCommand(final DataSelectorCommand command,
			final String targetName, final IncomingMessage im)
			throws ReaderProtocolException {
		DataSelector dataSelector = null;
		log.debug("Calling a DataSelector Command.");
		DataSelectorReply reply = replyFactory.createDataSelectorReply();

		if (command.getCreate() != null) {
			log.debug("Calling DataSelector.create()");
			// prepare the parameters
			String name = command.getCreate().getName();
			DataSelector ds = DataSelector.create(name, readerDevice);

			DataSelectorReturnType dataSelectorReturn = replyFactory
					.createDataSelectorReturnType();
			dataSelectorReturn.setReturnValue(ds.getName());
			reply.setCreate(dataSelectorReturn);
		}

		else if (command.getGetName() != null) {
			log.debug("Calling DataSelector.getName()");
			StringReturnType stringReturn = replyFactory
					.createStringReturnType();
			// Irrelevant method, simply return the targetName
			stringReturn.setReturnValue(targetName);
			reply.setGetName(stringReturn);
		}

		else {

			// Check the existence of the target
			if (targetName == null) {
				throw new ReaderProtocolException(
						MessagingConstants.ERROR_DATASELECTOR_NOT_FOUND_STR,
						MessagingConstants.ERROR_DATASELECTOR_NOT_FOUND);
			} else {
				dataSelector = readerDevice.getDataSelector(targetName);
			}

			if (command.getAddFieldNames() != null) {
				log.debug("Calling DataSelector.addFieldNames()");

				// prepare parameters
				org.ow2.aspirerfid.reader.rp.impl.core.msg.command.FieldNameListParamType.List valueElems = command
						.getAddFieldNames().getFieldNames().getList();
				List valueList = valueElems.getValue(); /*
														 * List of strings
														 * with the field
														 * names
														 */
				String[] rdFields = new String[valueList.size()];
				int index = 0;
				for (Iterator it = valueList.iterator(); it.hasNext();) {
					String fieldName = (String) it.next();
					rdFields[index] = fieldName;
					index++;
				}
				// call addFieldNames on RD
				dataSelector.addFieldNames(rdFields);
				// return void
				NoParamType voidType = replyFactory.createNoParamType();
				reply.setAddFieldNames(voidType);
			}

			else if (command.getRemoveFieldNames() != null) {
				log.debug("Calling DataSelector.removeFieldNames()");

				// prepare parameters
				org.ow2.aspirerfid.reader.rp.impl.core.msg.command.FieldNameListParamType.List valueElems = command
						.getRemoveFieldNames().getFieldNames().getList();
				List valueList = valueElems.getValue(); /*
														 * List of strings
														 * with the field
														 * names
														 */
				String[] rdFields = new String[valueList.size()];
				int index = 0;
				for (Iterator it = valueList.iterator(); it.hasNext();) {
					String fieldName = (String) it.next();
					rdFields[index] = fieldName;
					index++;
				}
				// call removeFieldNames on RD
				dataSelector.removeFieldNames(rdFields);
				// return void
				NoParamType voidType = replyFactory.createNoParamType();
				reply.setRemoveFieldNames(voidType);
			}

			else if (command.getRemoveAllFieldNames() != null) {
				log.debug("Calling DataSelector.removeAllFieldNames()");

				dataSelector.removeAllFieldNames();
				// return void
				NoParamType voidType = replyFactory.createNoParamType();
				reply.setRemoveAllFieldNames(voidType);
			}

			else if (command.getGetAllFieldNames() != null) {
				log.debug("Calling DataSelector.getAllFieldNames()");

				String[] fieldNames = dataSelector.getAllFieldNames();
				FieldNameListReturnType fieldNameListReturn = replyFactory
						.createFieldNameListReturnType();
				FieldNameListParamType rdFieldNameListItems = replyFactory
						.createFieldNameListParamType();
				FieldNameListParamType.List list = replyFactory
						.createFieldNameListParamTypeList();
				List valueList = list.getValue();
				for (int i = 0; i < fieldNames.length; i++) {
					valueList.add(fieldNames[i]);
				}
				rdFieldNameListItems.setList(list);
				fieldNameListReturn.setReturnValue(rdFieldNameListItems);
				reply.setGetAllFieldNames(fieldNameListReturn);
			}

			else if (command.getAddEventFilters() != null) {
				log.debug("Calling DataSelector.addEventFilters()");

				// prepare parameters
				org.ow2.aspirerfid.reader.rp.impl.core.msg.command.EventTypeListParamType.List valueElems = command
						.getAddEventFilters().getEventType().getList();
				List valueList = valueElems.getValue(); /*
														 * List of strings
														 * with the event
														 * types
														 */
				String[] rdEvents = new String[valueList.size()];
				int index = 0;
				for (Iterator it = valueList.iterator(); it.hasNext();) {
					String eventTypeName = (String) it.next();
					rdEvents[index] = eventTypeName;
					index++;
				}
				// call removeFieldNames on RD
				dataSelector.addEventFilters(rdEvents);
				// return void
				NoParamType voidType = replyFactory.createNoParamType();
				reply.setAddEventFilters(voidType);
			}

			else if (command.getRemoveEventFilters() != null) {
				log.debug("Calling DataSelector.removeEventFilters()");

				// prepare parameters
				org.ow2.aspirerfid.reader.rp.impl.core.msg.command.EventTypeListParamType.List valueElems = command
						.getRemoveEventFilters().getEventType().getList();
				List valueList = valueElems.getValue(); /*
														 * List of strings
														 * with the event
														 * types
														 */
				String[] rdEvents = new String[valueList.size()];
				int index = 0;
				for (Iterator it = valueList.iterator(); it.hasNext();) {
					String eventTypeName = (String) it.next();
					rdEvents[index] = eventTypeName;
					index++;
				}
				// call removeFieldNames on RD
				dataSelector.removeEventFilters(rdEvents);
				// return void
				NoParamType voidType = replyFactory.createNoParamType();
				reply.setRemoveEventFilters(voidType);
			}

			else if (command.getRemoveAllEventFilters() != null) {
				log.debug("Calling DataSelector.removeAllEventFilters()");

				dataSelector.removeAllEventFilters();
				// return void
				NoParamType voidType = replyFactory.createNoParamType();
				reply.setRemoveAllEventFilters(voidType);
			}

			else if (command.getGetAllEventFilters() != null) {
				log.debug("Calling DataSelector.getAllEventFilters()");

				String[] evTypes = dataSelector.getAllEventFilters();
				EventTypeListReturnValue eventTypeListReturn = replyFactory
						.createEventTypeListReturnValue();
				EventTypeListParamType eventTypeListItems = replyFactory
						.createEventTypeListParamType();
				EventTypeListParamType.List list = replyFactory
						.createEventTypeListParamTypeList();
				List valueList = list.getValue();
				for (int i = 0; i < evTypes.length; i++) {
					valueList.add(evTypes[i]);
				}
				eventTypeListItems.setList(list);
				eventTypeListReturn.setReturnValue(eventTypeListItems);
				reply.setGetAllEventFilters(eventTypeListReturn);
			}

			else if (command.getAddTagFieldNames() != null) {
				log.debug("Calling DataSelector.addTagFieldNames()");

				// prepare parameters
				org.ow2.aspirerfid.reader.rp.impl.core.msg.command.StringListParamType.List valueElems = command
						.getAddTagFieldNames().getFieldNames().getList();
				List valueList = valueElems.getValue(); /*
														 * List of strings
														 * with the field
														 * names
														 */
				String[] rdFields = new String[valueList.size()];
				int index = 0;
				for (Iterator it = valueList.iterator(); it.hasNext();) {
					String fieldName = (String) it.next();
					rdFields[index] = fieldName;
					index++;
				}
				// call addTagFieldNames on RD
				dataSelector.addTagFieldNames(rdFields);

				// return void
				NoParamType voidType = replyFactory.createNoParamType();
				reply.setAddTagFieldNames(voidType);
			}

			else if (command.getRemoveTagFieldNames() != null) {
				log.debug("Calling DataSelector.removeTagFieldNames()");

				// prepare parameters
				org.ow2.aspirerfid.reader.rp.impl.core.msg.command.StringListParamType.List valueElems = command
						.getRemoveTagFieldNames().getFieldNames().getList();
				List valueList = valueElems.getValue(); /*
														 * List of strings
														 * with the field
														 * names
														 */
				String[] rdFields = new String[valueList.size()];
				int index = 0;
				for (Iterator it = valueList.iterator(); it.hasNext();) {
					String fieldName = (String) it.next();
					rdFields[index] = fieldName;
					index++;
				}
				// call removeTagFieldNames on RD
				dataSelector.removeTagFieldNames(rdFields);

				// return void
				NoParamType voidType = replyFactory.createNoParamType();
				reply.setRemoveTagFieldNames(voidType);
			}

			else if (command.getRemoveAllTagFieldNames() != null) {
				log.debug("Calling DataSelector.removeAllTagFieldNames()");

				dataSelector.removeAllTagFieldNames();
				// return void
				NoParamType voidType = replyFactory.createNoParamType();
				reply.setRemoveAllTagFieldNames(voidType);
			}
		}
		return reply;
	}

	/**
	 * Executes a command on the EventType object.
	 * 
	 * @param command
	 *            The command to be executed.
	 * @param targetName
	 *            The unique identifier (object name) of a
	 *            <code>EventType</code> instance or <code>null</code> if it
	 *            is a static method.
	 * @param im
	 *            The <code>IncomingMessage</code> that caused this command.
	 * @return The reply of a command execution.
	 * @throws ReaderProtocolException
	 *             Exceptions from the EventType
	 */
	private EventTypeReply executeCommand(final EventTypeCommand command,
			final String targetName, final IncomingMessage im)
			throws ReaderProtocolException {
		log.debug("Calling a EventType Command.");
		EventTypeReply reply = replyFactory.createEventTypeReply();
		if (command.getGetSupportedTypes() != null) {
			log.debug("Calling EventType.getSupportedTypes()");
			// call on RD
			Vector eventTypes = EventType.getSupportedTypes();

			// prepare reply message
			EventTypeListReturnValue eventTypeListReturn = replyFactory
					.createEventTypeListReturnValue();
			EventTypeListParamType eventTypeListItems = replyFactory
					.createEventTypeListParamType();
			EventTypeListParamType.List list = replyFactory
					.createEventTypeListParamTypeList();
			List valueList = list.getValue();

			Iterator it = eventTypes.iterator();
			while (it.hasNext()) {
				valueList.add((String) it.next());
			}
			eventTypeListItems.setList(list);
			eventTypeListReturn.setReturnValue(eventTypeListItems);
			reply.setGetSupportedTypes(eventTypeListReturn);
		}

		return reply;
	}

	/**
	 * Executes a command on the TriggerType object.
	 * 
	 * @param command
	 *            The command to be executed.
	 * @param targetName
	 *            The unique identifier (object name) of a
	 *            <code>TriggerType</code> instance or <code>null</code> if
	 *            it is a static method.
	 * @param im
	 *            The <code>IncomingMessage</code> that caused this command.
	 * @return The reply of a command execution.
	 * @throws ReaderProtocolException
	 *             Exceptions from the TriggerType
	 */
	private TriggerTypeReply executeCommand(final TriggerTypeCommand command,
			final String targetName, final IncomingMessage im) {
		log.debug("Calling a TriggerType Command.");
		TriggerTypeReply reply = replyFactory.createTriggerTypeReply();

		if (command.getGetSupportedTypes() != null) {
			log.debug("Calling TriggerType.getSupportedTypes()");
			// call on RD
			Vector eventTypes = TriggerType.getSupportedTypes();

			// prepare reply message
			TriggerTypeListReturnType triggerTypeListReturn = replyFactory
					.createTriggerTypeListReturnType();
			TriggerTypeListReturnType.ReturnValue returnValue = replyFactory
					.createTriggerTypeListReturnTypeReturnValue();
			
			List valueList = returnValue.getList().getValue(); //?? stimmt dat ??
			
			Iterator it = eventTypes.iterator();
			while (it.hasNext()) {
				valueList.add((String) it.next());
			}
			triggerTypeListReturn.setReturnValue(returnValue);
			reply.setGetSupportedTypes(triggerTypeListReturn);
		}

		return reply;
	}

	/**
	 * Executes a command on the FieldName object.
	 * 
	 * @param command
	 *            The command to be executed.
	 * @param targetName
	 *            The unique identifier (object name) of a
	 *            <code>FieldName</code> instance or <code>null</code> if it
	 *            is a static method.
	 * @param im
	 *            The <code>IncomingMessage</code> that caused this command.
	 * @return The reply of a command execution.
	 * @throws ReaderProtocolException
	 *             Exceptions from the FieldName
	 */
	private FieldNameReply executeCommand(final FieldNameCommand command,
			final String targetName, final IncomingMessage im) {
		log.debug("Calling a FieldName Command.");
		FieldNameReply reply = replyFactory.createFieldNameReply();

		if (command.getGetSupportedNames() != null) {
			log.debug("Calling FieldName.getSupportedNames()");
			// call on RD
			Vector fieldNames = FieldName.getSupportedNames();

			// prepare reply message
			FieldNameListReturnType fieldNameListReturn = replyFactory
					.createFieldNameListReturnType();
			FieldNameListParamType fieldNameListItems = replyFactory
					.createFieldNameListParamType();
			FieldNameListParamType.List list = replyFactory
					.createFieldNameListParamTypeList();

			List valueList = list.getValue();
			Iterator it = fieldNames.iterator();
			while (it.hasNext()) {
				valueList.add((String) it.next());
			}
			fieldNameListItems.setList(list);
			fieldNameListReturn.setReturnValue(fieldNameListItems);
			reply.setGetSupportedNames(fieldNameListReturn);
		}

		return reply;
	}

	/**
	 * Executes a command on the TagField object.
	 * 
	 * @param command
	 *            The command to be executed.
	 * @param targetName
	 *            The unique identifier (object name) of a <code>TagField</code>
	 *            instance or <code>null</code> if it is a static method.
	 * @param im
	 *            The <code>IncomingMessage</code> that caused this command.
	 * @return The reply of a command execution.
	 * @throws ReaderProtocolException
	 *             Exceptions from the TagField
	 */
	private TagFieldReply executeCommand(final TagFieldCommand command,
			final String targetName, final IncomingMessage im)
			throws ReaderProtocolException {
		TagField tagField = null;
		log.debug("Calling a TagField Command.");
		TagFieldReply reply = replyFactory.createTagFieldReply();

		if (command.getCreate() != null) {
			log.debug("Calling TagField.create()");
			// prepare the parameters
			String name = command.getCreate().getName();
			TagField tf = TagField.create(name, readerDevice);

			TagFieldReturnType tagFieldReturn = replyFactory
					.createTagFieldReturnType();
			tagFieldReturn.setReturnValue(tf.getName());
			reply.setCreate(tagFieldReturn);
		}

		else if (command.getGetName() != null) {
			log.debug("Calling TagField.getName()");
			StringReturnType stringReturn = replyFactory
					.createStringReturnType();
			// Irrelevant method, simply return the targetName
			stringReturn.setReturnValue(targetName);
			reply.setGetName(stringReturn);
		}

		else {
			// Check the existence of the target
			if (targetName == null) {
				throw new ReaderProtocolException(
						MessagingConstants.ERROR_TAGFIELD_NOT_FOUND_STR,
						MessagingConstants.ERROR_TAGFIELD_NOT_FOUND);
			} else {
				tagField = readerDevice.getTagField(targetName);
			}

			if (command.getGetTagFieldName() != null) {
				log.debug("Calling TagField.getTagFieldName()");

				StringReturnType stringReturn = replyFactory
						.createStringReturnType();
				stringReturn.setReturnValue(tagField.getTagFieldName());
				reply.setGetTagFieldName(stringReturn);
			}

			else if (command.getSetTagFieldName() != null) {
				log.debug("Calling TagField.setTagFieldName()");

				tagField.setTagFieldName(command.getSetTagFieldName()
						.getTagFieldName());

				// return void
				NoParamType voidType = replyFactory.createNoParamType();
				reply.setSetTagFieldName(voidType);
			}

			else if (command.getGetMemoryBank() != null) {
				log.debug("Calling TagField.getMemoryBank()");

				IntReturnType intReturn = replyFactory
						.createIntReturnType();
				intReturn.setReturnValue(tagField.getMemoryBank());
				reply.setGetMemoryBank(intReturn);
			}

			else if (command.getSetMemoryBank() != null) {
				log.debug("Calling TagField.setMemoryBank()");

				int memBank = command.getSetMemoryBank().getMemoryBank().intValue();
				tagField.setMemoryBank(memBank);

				// return void
				NoParamType voidType = replyFactory.createNoParamType();
				reply.setSetTagFieldName(voidType);
			}

			else if (command.getGetOffset() != null) {
				log.debug("Calling TagField.getOffset()");

				IntReturnType intReturn = replyFactory
						.createIntReturnType();
				intReturn.setReturnValue(tagField.getOffset());
				reply.setGetOffset(intReturn);

			}

			else if (command.getSetOffset() != null) {
				log.debug("Calling TagField.setOffset()");

				int offset = command.getSetOffset().getOffset();
				tagField.setOffset(offset);

				// return void
				NoParamType voidType = replyFactory.createNoParamType();
				reply.setSetTagFieldName(voidType);
			}

			else if (command.getGetLength() != null) {
				log.debug("Calling TagField.getLength()");

				IntReturnType intReturn = replyFactory
						.createIntReturnType();
				intReturn.setReturnValue(tagField.getLength());
				reply.setGetLength(intReturn);

			}

			else if (command.getSetLength() != null) {
				log.debug("Calling TagField.setLength()");

				int length = command.getSetLength().getLength();
				tagField.setLength(length);

				// return void
				NoParamType voidType = replyFactory.createNoParamType();
				reply.setSetTagFieldName(voidType);
			}
		}
		return reply;
	}

	/**
	 * Handles all the errors which can be returned by the ReaderDevice. The
	 * error codes are defined in the ReaderProtocol Specification in chapter 8.
	 * 
	 * @param e
	 *            The <code>ReaderProtocolException</code> thrown by an
	 *            invocation on the ReaderDevice
	 * @param id
	 *            The id of the corresponding command.
	 * @return The <code>Reply</code> consisting of an error reply.
	 */
	private Reply handleReaderProtocolException(
			final ReaderProtocolException e, final String id) {
		return handleReaderProtocolException(id, e.getErrorCode(), e
				.getErrorName(), e.getErrorDescription());
	}

	/**
	 * Handles all the errors which can be returned by the ReaderDevice. The
	 * error codes are defined in the ReaderProtocol Specification in chapter 8.
	 * 
	 * @param id
	 *            The id of the corresponding command that caught the error.
	 * @param resultCode
	 *            The result code of the error (see Reader Protocol
	 *            Specification, section 8.3 )
	 * @param errorName
	 *            The name of the error (e.g., "ERROR_UNKNOWN" ).
	 * @param description
	 *            The error description (e.g., "Unspecified error").
	 * @return The <code>Reply</code> consisting of an error reply.
	 */
	private Reply handleReaderProtocolException(final String id,
			final int resultCode, final String errorName,
			final String description) {
		Reply reply = replyFactory.createReply();
		reply.setId(id);
		reply.setResultCode(resultCode);
		ErrorType error = replyFactory.createErrorType();
		error.setName(errorName);
		error.setDescription(description);
		reply.setError(error);
		return reply;

	}

	/**
	 * Converts a <code>Date</code> into a <code>Calendar</code> object
	 * using the UTC timezone.
	 * 
	 * @param d
	 *            The <code>Date</code>
	 * @return The corresponding <code>Calendar</code>
	 */
	private Calendar toCalendar(final Date d) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+0"));
		cal.setTime(d);
		return cal;
	}

	/**
	 * Creates a JAXB <code>ReadReportType</code> message using a
	 * <code>ReadReporty</code> from the ReaderDevice.
	 * 
	 * @param report
	 *            The <code>ReadReport</code> from the ReaderDevice.
	 * @return The <code>ReadReportType</code> used by JAXB to serialize the
	 *         reply.
	 * @throws JAXException
	 */
	private ReadReportType createReadReportReply(final ReadReport report)
			throws JAXBException {
		return createReadReportReply(report, null);
	}

	/**
	 * Creates a JAXB <code>ReadReportType</code> message using a
	 * <code>ReadReporty</code> from the ReaderDevice.
	 * 
	 * @param report
	 *            The <code>ReadReport</code> from the ReaderDevice.
	 * @param dataSelector
	 *            The <code>DataSelector</code> to check, which information
	 *            properties are expected. If there is no such DataSelector the
	 *            parameter can be set to <code>null</code>.
	 * @return The <code>ReadReportType</code> used by JAXB to serialize the
	 *         reply.
	 * @throws JAXException
	 * 
	 */
	private ReadReportType createReadReportReply(final ReadReport report,
			final DataSelector dataSelector) throws JAXBException {
		// create a set with all fields from the data selector
		CompareSet dataSelectorFields = null;
		if (dataSelector != null) {
			String[] fields = dataSelector.getAllFieldNames();
			dataSelectorFields = new CompareSet(fields.length);
			for (int i = 0; i < fields.length; i++) {
				dataSelectorFields.add(fields[i]);
			}
		}

		ReadReportType reportItems = replyFactory.createReadReportType();
		List sourceList = reportItems.getSourceReport();

		// add the source reports
		Iterator sourceReportIt = report.getSourceReports().values().iterator();
		while (sourceReportIt.hasNext()) {
			org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ReadReportType.SourceReport sourceReport = replyFactory
					.createReadReportTypeSourceReport();
			SourceReport rdSourceReport = (SourceReport) sourceReportIt.next();

			// set the source info
			if (rdSourceReport.containsSourceInfo()) {
				org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.SourceInfoType sourceInfo = replyFactory
						.createSourceInfoType();
				SourceInfoType rdSourceInfo = rdSourceReport.getSourceInfo();
				if (rdSourceInfo.getSourceFrequency() != -1) {
					sourceInfo.setSourceFrequency(Integer.toString(rdSourceInfo
							.getSourceFrequency()));
				}
				if (dataSelectorFields != null
						&& dataSelectorFields.contains(FieldName.SOURCE_NAME)) {
					sourceInfo.setSourceName(rdSourceInfo.getSourceName());
				}
				sourceInfo.setSourceProtocol(rdSourceInfo.getSourceProtocol());
				sourceReport.setSourceInfo(sourceInfo);
				
			}

			// add all the tags
			List tagList = sourceReport.getTag();
			Collection tags = report.getAllTags().values();
			Iterator tagIt = tags.iterator();
			while (tagIt.hasNext()) {
				org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagType rdTag = (org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagType) tagIt
						.next();
				org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagType tag = replyFactory
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
				try {
				List eventList = getTagEvents(tag, rdTag);
				List tagFields = getTagFields(tag, rdTag);
				tagList.add(tag);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			// add source report to the source list
			sourceList.add(sourceReport);

		}
		return reportItems;
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
	private List getTagEvents(
			org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagType msgTag,
			org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagType rdTag)
			throws JAXBException, DatatypeConfigurationException {
		List eventList = msgTag.getTagEvent();
		Collection tagEvents = rdTag.getAllTagEvents().values();
		Iterator it = tagEvents.iterator();
		while (it.hasNext()) {
			org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagEventType tagEvent = replyFactory
					.createTagEventType();
			org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagEventType rdEvent = (org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagEventType) it
					.next();
			tagEvent.setEventType(rdEvent.getEventType());

			EventTimeType evTime = replyFactory.createEventTimeType();
						
			if (rdEvent.getTimeTick() >= 0) {
				evTime.setEventTimeTick(Long.toString(rdEvent.getTimeTick()));
			}
			if (rdEvent.getTimeUTC() != null) {
				evTime.setEventTimeUTC(calendarToXMLGregorianCalendar(toCalendar(rdEvent.getTimeUTC())));
			}
			tagEvent.setTime(evTime);
			
			EventTriggers eventTriggers = replyFactory
					.createTagEventTypeEventTriggers();
			List eventTriggerList = eventTriggers.getTrigger();
			Collection evTriggers = rdEvent.getEventTriggers().values();
			Iterator trgIt = evTriggers.iterator();
			while (trgIt.hasNext()) {
				Trigger rdTrigger = (Trigger) trgIt.next();
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
	private List getTagFields(
			org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagType msgTag,
			org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagType rdTag)
			throws JAXBException {
		List fieldList = msgTag.getTagFields();

		Collection coll = rdTag.getAllTagFields().values();
		Iterator it = coll.iterator();
		while (it.hasNext()) {
			TagFieldValueParamType tfvpReader = (TagFieldValueParamType) it
					.next();
			org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TagFieldValueParamType tfvp = replyFactory
					.createTagFieldValueParamType();
			tfvp.setTagFieldName(tfvpReader.getTagFieldName());
			tfvp.setTagFieldValue(HexUtil.hexToByteArray(tfvpReader
					.getTagFieldValue()));
			fieldList.add(tfvp);
		}
		return fieldList;
	}

	/**
	 * Creates an array of Strings using a JAXB list type.
	 * 
	 * @param list
	 *            The <code>HexStringListType</code> from the JAXB
	 *            unmarshaller.
	 * @return An array with the corresponding elements.
	 */
	private String[] getString(final HexStringListType list) {
		List l = list.getList().getValue();
		String[] array = new String[l.size()];
		int index = 0;
		for (Iterator it = l.iterator(); it.hasNext();) {
			array[index] = new String((byte[]) (it.next()));
			index++;

		}
		return array;
	}

	/**
	 * Creates an array of TagSelectors using the JAXB types.
	 * 
	 * @param list
	 *            The <code>TagSelectorListParamType</code> from the JAXB
	 *            unmarshaller.
	 * @return The corresponding collection of <code>TagSelector</code>
	 *         objects.
	 * @throws ReaderProtocolException
	 * 
	 */
	private TagSelector[] getTagSelector(
			final org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TagSelectorListParamType list)
			throws ReaderProtocolException {
		List l = list.getList().getValue();
		TagSelector[] tsArray = new TagSelector[l.size()];
		int index = 0;
		for (Iterator it = l.iterator(); it.hasNext();) {
			String tagSelectorName = (String) it.next();
			tsArray[index] = readerDevice.getTagSelector(tagSelectorName);
			index++;
		}

		return tsArray;
	}

	/**
	 * Creates an array of TagFieldValue using the JAXB types.
	 * 
	 * @param list
	 *            The <code>TagFieldValueListParamType</code> from the JAXB
	 *            unmarshaller.
	 * @return The corresponding collection of <code>TagFieldValue</code>
	 *         objects.
	 * @throws ReaderProtocolException
	 * 
	 */
	private TagFieldValue[] getTagFieldValue(
			final org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TagFieldValueListParamType list)
			throws ReaderProtocolException {
		List l = list.getList().getValue();
		TagFieldValue[] tfvArray = new TagFieldValue[l.size()];
		int index = 0;
		for (Iterator it = l.iterator(); it.hasNext();) {
			org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TagFieldValueParamType tfvPair = (org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TagFieldValueParamType) it
					.next();
			TagField tf = readerDevice.getTagField(tfvPair.getTagFieldName());
			TagFieldValue tfvItem = new TagFieldValue(tf, HexUtil.
               byteArrayToHexString(tfvPair.getTagFieldValue()));
			tfvArray[index] = tfvItem;
			index++;
		}

		return tfvArray;
	}

	/**
	 * Creates an Array of Sources using the JAXB types.
	 * 
	 * @param valueElems
	 *            The list type from the JAXB unmarshaller.
	 * @return The corresponding array of <code>Source</code> objects.
	 */
	private Source[] getSource(
			final org.ow2.aspirerfid.reader.rp.impl.core.msg.command.SourceListParamType.List valueElems) {
		List valueList = valueElems.getValue(); // List of strings with the
		// source names
		/*
		 * aquiring phase - fetch all sources from the RD if a source doesn't
		 * exist an exception then just this one should be ignored (all others
		 * should be removed).
		 */
		Source[] rdSources = new Source[valueList.size()];
		int index = 0;
		for (Iterator it = valueList.iterator(); it.hasNext();) {
			String sourceName = (String) it.next();
			try {
				Source source = readerDevice.getSource(sourceName);
				// rdSources.add(source);
				rdSources[index] = source;
				index++;
			} catch (ReaderProtocolException e) {
				// source not found ignore it
			}
		}
		return rdSources;
	}

	/**
	 * Creates an array of DataSelectors using the JAXB types.
	 * 
	 * @param valueElems
	 *            The list type from the JAXB unmarshaller.
	 * @return The corresponding array of <code>DataSelector</code> objects.
	 */
	private DataSelector[] getDataSelector(
			final org.ow2.aspirerfid.reader.rp.impl.core.msg.command.DataSelectorListParamType.List valueElems) {
		List valueList = valueElems.getValue(); // List of strings with the
		// source names
		/*
		 * aquiring phase - fetch all sources from the RD if a source doesn't
		 * exist an exception then just this one should be ignored (all others
		 * should be removed).
		 */
		DataSelector[] rdDataSelectors = new DataSelector[valueList.size()];
		int index = 0;
		for (Iterator it = valueList.iterator(); it.hasNext();) {
			String dsName = (String) it.next();
			try {
				DataSelector ds = readerDevice.getDataSelector(dsName);
				rdDataSelectors[index] = ds;
				index++;
			} catch (ReaderProtocolException e) {
				// DataSelector not found ignore it
			}
		}
		return rdDataSelectors;
	}

	/**
	 * Creates an array of NotificationChannels using the JAXB types.
	 * 
	 * @param valueElems
	 *            The list type from the JAXB unmarshaller.
	 * @return The corresponding array of <code>NotificationChannel</code>
	 *         objects.
	 */
	private NotificationChannel[] getNotificationChannels(
			final org.ow2.aspirerfid.reader.rp.impl.core.msg.command.NotificationChannelListParamType.List valueElems) {
		List valueList = valueElems.getValue(); // List of strings with the
		// notification channel names
		/*
		 * aquiring phase - fetch all notification channels from the RD if a
		 * channel doesn't exist an exception then just this one should be
		 * ignored (all others should be removed).
		 */
		NotificationChannel[] rdNotificationChannels = new NotificationChannel[valueList
				.size()];
		int index = 0;
		for (Iterator it = valueList.iterator(); it.hasNext();) {
			String channelName = (String) it.next();
			try {
				NotificationChannel channel = readerDevice
						.getNotificationChannel(channelName);
				rdNotificationChannels[index] = channel;
				index++;
			} catch (ReaderProtocolException e) {
				// NotificationChannel not found ignore it
			}
		}
		return rdNotificationChannels;
	}

	/**
	 * Creates an array of Triggers using the JAXB types.
	 * 
	 * @param valueElems
	 *            The list type from the JAXB unmarshaller.
	 * @return The corresponding array of <code>Trigger</code> objects.
	 */
	private Trigger[] getTriggers(
			final org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TriggerListParamType.List valueElems) {
		List valueList = valueElems.getValue(); // List of strings with the
		// trigger names
		/*
		 * aquiring phase - fetch all triggers from the RD if a trigger doesn't
		 * exist an exception then just this one should be ignored (all others
		 * should be removed).
		 */
		Trigger[] rdTriggers = new Trigger[valueList.size()];
		int index = 0;
		for (Iterator it = valueList.iterator(); it.hasNext();) {
			String triggerName = (String) it.next();
			try {
				Trigger trigger = readerDevice.getTrigger(triggerName);
				rdTriggers[index] = trigger;
				index++;
			} catch (ReaderProtocolException e) {
				// trigger not found ignore it
			}
		}
		return rdTriggers;
	}

	/**
	 * Reboots the reader device.
	 */
	private void reboot() {
		log.debug("Rebooting reader device.");
		// Close all server connections
		List servers = ServerConnection.getServerConnections();
		for (Iterator it = servers.iterator(); it.hasNext();) {
			ServerConnection server = (ServerConnection) it.next();
			server.close();
		}

		System.gc();
		try {
			Runtime.getRuntime().exec(
					"java -cp \"" + MessageLayer.getClasspath() + "\" "
							+ MessageLayer.getClassname());
		} catch (IOException e) {
			log.error("Could not reboot the reader device.");
			log.error(e);
		}
		System.exit(0);
	}

	/**
	 * Creates a socket connection for a new NotificationChannel in the message
	 * layer.
	 * 
	 * @param addr
	 *            The address of the NotificationChannel
	 * @param name
	 *            The name of the NotificationChannel
	 * @param senderHandshake
	 *            The handshake of the sender
	 * @param receiverHandshake
	 *            The handshake of the receiver
	 * @return In <i>listen mode</i> the port number on which the reader is
	 *         listening. In <i>connect mode</i> <code>0</code> is returned.
	 * @throws ReaderProtocolException
	 *             If the address is not correct.
	 */
	private int createNotificationChannelConnection(final Address addr,
			final String name, final SenderHandshakeMessage senderHandshake,
			final ReceiverHandshakeMessage receiverHandshake)
			throws ReaderProtocolException {
		int port = 0;
		/*
		 * if the host asks for the LISTEN mode we have to determine a port
		 * number for the listening port on the reader's ServerSocket. The host
		 * can get this port number by calling
		 * NotificationChannel.getEffectiveAddress()
		 */
		if (addr.getMode() != null
				&& addr.getMode().equals(Address.MODE_LISTEN)) {
			// Listen mode only allowed with TCP
			if (addr.getProtocol() == null
					|| !addr.getProtocol().equals(
							MessagingConstants.PROTOCOL_TCP)) {
				throw new ReaderProtocolException(
						MessagingConstants.ERROR_UNKNOWN_STR,
						MessagingConstants.ERROR_UNKNOWN,
						"listen mode only allowed with TCP connections.");
			}
			port = SocketUtil.findFreePort();
			log.debug("NotificationChannel in listen mode, listening on port "
					+ port);
			addr.setPort(port);
		}

		// create the channel in the msg layer
		NotificationChannelConnections notifyConns = NotificationChannelConnections
				.getInstance();
		notifyConns.create(name, addr, senderHandshake, receiverHandshake);

		return port;
	}
	
	private XMLGregorianCalendar calendarToXMLGregorianCalendar(Calendar cal) throws DatatypeConfigurationException{
		GregorianCalendar gregCal = new GregorianCalendar();
		gregCal.setTimeInMillis(cal.getTimeInMillis());
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregCal);
	}

}
