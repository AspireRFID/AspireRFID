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

package org.ow2.aspirerfid.reader.rp.impl.core.msg.command;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Stack;
import java.util.Vector;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.GetDataSelector;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.GetNotificationChannel;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.GetReadPoint;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.GetSource;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.GetTagField;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.GetTagSelector;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.GetTrigger;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.RemoveDataSelectors;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.RemoveNotificationChannels;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.RemoveSources;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.RemoveTagFields;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.RemoveTagSelectors;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.RemoveTriggers;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.SetCurrentDataSelector;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.SetCurrentSource;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.SetHandle;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.SetName;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.SetRole;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ReaderDeviceCommand.SetTimeUTC;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.SourceCommand.AddReadPoints;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.SourceCommand.RemoveReadPoints;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.util.HexUtil;
import org.apache.log4j.Logger;



/**
 * TextCommandParserHelper provides general utilities for the text parser.
 * In particular it generates the tree of the intermediate representation (IR).<br />
 * <br />
 * How parameters are parsed:<br />
 * During the parsing process the <code>TextCommandParser</code> puts parameters
 * to the parameter queue (FIFO). While creating the IR the parameters are again read from
 * the parameter queue. To be able to handle cases where there are nested parameter lists
 * the parser distincts different parsing states which can be modified by <code>pushState()</code>, 
 * <code>popState()</code> and <code>topState()</code>. If the parser is in the state 
 * <code>STATE_LIST_PARAMETER</code> (parsing a parameter list) it works on a so called
 * working list. Parameters are added to the working list and the whole working list is
 * added to the local parameter queue. In this way it's possible to handle recursive (nested)
 * lists of parameters.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 * @see org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TextCommandParser
 */
public class TextCommandParserHelper {
	
	/** The logger. */
	private Logger log;
	
	/** state: normal state (no special meaning) */
	public static final int STATE_NONE = -1;
	
	/** state: parsing a list */
	public static final int STATE_LIST_PARAMETER = 1;
	
	/** state: parsing a parameter pair */
	public static final int STATE_PAIR_PARAMETER = 2;
	
	/** the factory object to create the IR tree */
	public static ObjectFactory cmdFactory = new ObjectFactory(); 
	
	/** the object type */
	private int objectType;
	
	/** the type of the command */
	private int commandType;
	
	/** the command id */
	private String id;
	
	/** the target name (identiefier for object instances) */
	private String targetName;
	
	/** the parameter queue */
	private Vector parameters = new Vector();
	
	/** the working list stack */
	private Stack listStack = new Stack();
	
	/** the parsing state stack */
	private Stack stateStack = new Stack();
	
	/**
	 * Constructor for the TextCommandParserHelper.
	 */
	public TextCommandParserHelper() {
		log = Logger.getLogger(getClass().getName());
	}
	
	/**
	 * Set the command id.
	 * @param id The unique id of the command.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Get the command id.
	 * @return the id of the command used to identify corresponding request-response pairs.
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Get the targetName which identifies an object instance.
	 * @return Returns the targetName.
	 */
	public String getTargetName() {
		return targetName;
	}

	/**
	 * Set the targetName which identifies an object instance.
	 * @param targetName The targetName to set.
	 */
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	
	/**
	 * Sets the object defined by the constants in <code>TextCommandParserTokenTypes</code><br />
	 * e.g., TextCommandParserTokenTypes.READERDEVICE, TextCommandParserTokenTypes.SOURCE etc.
	 * @param objectType the object type
	 * @see TextCommandParserTokenTypes
	 */
	public void setObject(int objectType) {
		this.objectType = objectType;
	}

	/**
	 * Sets the command method defined by constants in <code>TextCommandParserTokenTypes</code><br />
	 * e.g., TextCommandParserTokenTypes.CMD_CREATE, TextCommandParserTokenTypes.GET_NAME etc.
	 * @param commandType the command method
	 */
	public void setCommand(int commandType) {
		this.commandType = commandType;
	}
	
	/**
	 * Puts a <code>Parameter</code> to the parameter queue.
	 * @param param The <code>Parameter</code> to be pushed to the stack.
	 */
	public void writeParameter(Parameter param) {
		parameters.add(param);
	}
	
	/**
	 * Puts a integer parameter to the parameter queue. Internally integer
	 * parameters are pushed as <code>ValueParameter</code>s.
	 * @param i The value of the integer parameter
	 */
	public void writeParameter(int i) {
		Parameter param = new ValueParameter(i);
		writeParameter(param);
	}
	
	/**
	 * Puts a string parameter to the parameter queue. Internally string
	 * parameters are pushed as <code>ValueParameter</code>s.
	 * @param s The value of the string parameter
	 */
	public void writeParameter(String s) {
		Parameter param = new ValueParameter(s);
		writeParameter(param);
	}
	
	/**
	 * Puts a boolean parameter to the parameter queue. Internally boolean
	 * parameters are pushed as <code>ValueParameter</code>s.
	 * @param b The value of the boolean parameter
	 */
	public void writeParameter(boolean b) {
		Parameter param = new ValueParameter(b);
		writeParameter(param);
	}
	
	/**
	 * Pushes a parsing state to the local state stack.
	 * These states are used by the parser to identify different
	 * parsing states.
	 * @param state The state
	 */
	public void pushState(int state) {
		stateStack.push(new Integer(state));
	}
	
	/**
	 * Reads top element of the parsing state stack without removing it.
	 * @return the top element of the parsing state.
	 */
	public int topState() {
		if (stateStack.isEmpty()) {
			return STATE_NONE;
		} else {
			Integer i = (Integer)stateStack.peek();
			return i.intValue();
		}
	}
	
	/**
	 * Pops the parsing state stack and removes the element.
	 * @return the top of the parsing stack
	 */
	public int popState() {
		if (stateStack.isEmpty()) {
			return STATE_NONE;
		} else {
			Integer i = (Integer)stateStack.pop();
			return i.intValue();
		}
	}
	
	/**
	 * Gets the head of the parameter queue and removes the element.
	 * @return the first parameter in the queue as a <code>ValueParameter</code>.
	 * @throws TextCommandParserException if no more parameters are available or the parameter is not of type <code>ValueParameter</code>
	 */
	public ValueParameter readValueParameter() throws TextCommandParserException {
		if (!hasParameters()) {
			throw new ParameterMissingException("No more parameters available. Parameter queue is empty.");
		}
		Parameter ret = (Parameter)parameters.get(0);
		if (ret instanceof ValueParameter) {
			parameters.remove(0);
			return (ValueParameter)ret;
		}
		throw new ParameterWrongTypeException("Wrong parameter type. Expected a ValueParameter instead of a " + ret.getClass());
	}
	
	/**
	 * Gets the head of the parameter queue and removes the element.
	 * @return the first parameter in the queue as a <code>ListParameter</code>.
	 * @throws TextCommandParserException if no more parameters are available or the parameter is not of type <code>ListParameter</code>
	 */
	public ListParameter readListParameter() throws TextCommandParserException {
		if (!hasParameters()) {
			throw new ParameterMissingException("No more parameters available. Parameter queue is empty.");
		}
		Parameter ret = (Parameter)parameters.get(0);
		if (ret instanceof ListParameter) {
			parameters.remove(0);
			return (ListParameter)ret;
		}
		throw new ParameterWrongTypeException("Wrong parameter type. Expected a ListParameter instead of a " + ret.getClass());
	}
	
	/**
	 * Gets the head of the parameter queue and removes the element.
	 * @return the first parameter in the queue as a <code>Parameter</code>.
	 * @throws TextCommandParserException if no more parameters are available or the parameter is not of type <code>Parameter</code>
	 */
	public Parameter readParameter() throws TextCommandParserException {
		if (!hasParameters()) {
			throw new ParameterMissingException("No more parameters available. Parameter queue is empty.");
		}
		Parameter ret = (Parameter)parameters.get(0);
		parameters.remove(0);
		return ret;
	}
	
	/**
	 * Flag indicating if there are more parameters available in the parameter queue.
	 * @return <code>true</code> if the parameter queue is not empty, <code>false</code> otherwise.
	 */
	public boolean hasParameters() {
		return (parameters.size() != 0);
	}
	
	/**
	 * Pushes a working list to the working list stack.
	 * @param p The current working list
	 */
	public void pushList(ListParameter p) {
		listStack.push(p);
	}
	
	/**
	 * Pops a working list from the working list stack.
	 * @return the current working list
	 * @throws TextCommandParserException if the working list stack is empty.
	 */
	public ListParameter popList() throws TextCommandParserException {
		if (listStack.isEmpty()) {
			throw new ParameterMissingException("List paramater stack is empty. Could not parse the parameter lists correctly.");
		}
		return (ListParameter)listStack.pop();
	}
	
	/**
	 * Builds the IR for a text command.
	 * @return The IR of a parsed command.
	 * @throws TextCommandParserException
	 * @throws DatatypeConfigurationException 
	 */
	public Command buildCommandTree() throws TextCommandParserException, DatatypeConfigurationException {
		Command command = cmdFactory.createCommand();
		command.setId(id);
		command.setTargetName(targetName);
		switch (objectType) {
		case TextCommandParserTokenTypes.READERDEVICE: 
		{
			command.setReaderDevice(getReaderDeviceCommand());
			break;
		}
		case TextCommandParserTokenTypes.SOURCE:
		{
			command.setSource(getSourceCommand());
			break;
		}
		case TextCommandParserTokenTypes.READPOINT:
		{
			command.setReadPoint(getReadPointCommand());
			break;
		}
		case TextCommandParserTokenTypes.TAGSELECTOR:
		{
			command.setTagSelector(getTagSelectorCommand());
			break;
		}
		case TextCommandParserTokenTypes.DATASELECTOR:
		{
			command.setDataSelector(getDataSelectorCommand());
			break;
		}
		case TextCommandParserTokenTypes.NOTIFICATIONCHANNEL:
		{
			command.setNotificationChannel(getNotificationChannelCommand());
			break;
		}
		case TextCommandParserTokenTypes.TRIGGER:
		{
			command.setTrigger(getTriggerCommand());
			break;
		}
		case TextCommandParserTokenTypes.EVENTTYPE:
		{
			command.setEventType(getEventTypeCommand());
			break;
		}
		case TextCommandParserTokenTypes.TRIGGERTYPE:
		{
			command.setTriggerType(getTriggerTypeCommand());
			break;
		}
		case TextCommandParserTokenTypes.FIELDNAME:
		{
			command.setFieldName(getFieldNameCommand());
			break;
		}
		case TextCommandParserTokenTypes.TAGFIELD:
		{
			command.setTagField(getTagFieldCommand());
			break;
		}
		}
		return command;
	}
	
	
	/**
	 * Creates the IR for a ReaderDevice commmand.
	 * @return The ReaderDevice IR
	 * @throws TextCommandParserException
	 * @throws DatatypeConfigurationException 
	 */
	private ReaderDeviceCommand getReaderDeviceCommand() throws TextCommandParserException, DatatypeConfigurationException {
		ReaderDeviceCommand rdCommand = cmdFactory.createReaderDeviceCommand();
		switch (commandType) {
		case TextCommandParserTokenTypes.CMD_GETEPC:
		{	
			NoParamType voidType = cmdFactory.createNoParamType();
			rdCommand.setGetEPC(voidType);
			break;
		}
		case TextCommandParserTokenTypes.CMD_GETMANUFACTURER:
		{
			NoParamType voidType = cmdFactory.createNoParamType();
			rdCommand.setGetManufacturer(voidType);
			break;
		}
		case TextCommandParserTokenTypes.CMD_GETMODEL:
		{
			NoParamType voidType = cmdFactory.createNoParamType();
			rdCommand.setGetModel(voidType);
			break;
		}
		case TextCommandParserTokenTypes.CMD_SETHANDLE:
		{
			SetHandle handle = cmdFactory.createReaderDeviceCommandSetHandle();
			handle.setHandle(readValueParameter().getIntValue());
			rdCommand.setSetHandle(handle);
			break;
		}
		case TextCommandParserTokenTypes.CMD_GETNAME:
		{
			NoParamType voidType = cmdFactory.createNoParamType();
			rdCommand.setGetName(voidType);
			break;
		}
		//--------------------------------------------------------------------------
		case TextCommandParserTokenTypes.CMD_SETNAME:
		{
			SetName name = cmdFactory.createReaderDeviceCommandSetName();
			name.setName(readValueParameter().getStringValue());
			rdCommand.setSetName(name);
			break;
		}
		case TextCommandParserTokenTypes.CMD_GETROLE:
		{
			rdCommand.setGetRole(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_SETROLE:
		{
			SetRole role = cmdFactory.createReaderDeviceCommandSetRole();
			role.setRole(readValueParameter().getStringValue());
			rdCommand.setSetRole(role);
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_TIME_TICKS:
		{
			rdCommand.setGetTimeTicks(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_TIME_UTC:
		{
			rdCommand.setGetTimeUTC(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_SET_TIME_UTC:
		{
			SetTimeUTC time = cmdFactory.createReaderDeviceCommandSetTimeUTC();
			time.setUtc(DatatypeFactory.newInstance().newXMLGregorianCalendar()); //TODO Korrekte Zeit setzen
			rdCommand.setSetTimeUTC(time);
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_MANUFACTURER_DESCRIPTION:
		{
			rdCommand.setGetManufacturerDescription(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_CURRENT_SOURCE:
		{
			rdCommand.setGetCurrentSource(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_SET_CURRENT_SOURCE:
		{
			SetCurrentSource source = cmdFactory.createReaderDeviceCommandSetCurrentSource();
			source.setCurrentSource(readValueParameter().getStringValue());
			rdCommand.setSetCurrentSource(source);
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_CURRENT_DATA_SELECTOR:
		{
			rdCommand.setGetCurrentDataSelector(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_SET_CURRENT_DATA_SELECTOR:
		{
			SetCurrentDataSelector ds = cmdFactory.createReaderDeviceCommandSetCurrentDataSelector();
			ds.setCurrentDataSelector(readValueParameter().getStringValue());
			rdCommand.setSetCurrentDataSelector(ds);
			break;
		}
		case TextCommandParserTokenTypes.CMD_REMOVE_SOURCES:
		{
			RemoveSources sources = cmdFactory.createReaderDeviceCommandRemoveSources();
			SourceListParamType.List list = cmdFactory.createSourceListParamTypeList();
			SourceListParamType listType = cmdFactory.createSourceListParamType();
			list.getValue().addAll(readListParameter().toStringList());
			listType.setList(list);
			sources.setSources(listType);
			rdCommand.setRemoveSources(sources);
			break;
		}
		case TextCommandParserTokenTypes.CMD_REMOVE_ALL_SOURCES:
		{
			rdCommand.setRemoveAllSources(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_SOURCE:
		{
			GetSource source = cmdFactory.createReaderDeviceCommandGetSource();
			source.setName(readValueParameter().getStringValue());
			rdCommand.setGetSource(source);
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_ALL_SOURCES:
		{
			rdCommand.setGetAllSources(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_REMOVE_DATA_SELECTORS:
		{
			RemoveDataSelectors ds = cmdFactory.createReaderDeviceCommandRemoveDataSelectors();
			DataSelectorListParamType.List list = cmdFactory.createDataSelectorListParamTypeList();
			DataSelectorListParamType listType = cmdFactory.createDataSelectorListParamType();
			list.getValue().addAll(readListParameter().toStringList());
			listType.setList(list);
			ds.setDataSelectors(listType);
			rdCommand.setRemoveDataSelectors(ds);
			break;
		}
		case TextCommandParserTokenTypes.CMD_REMOVE_ALL_DATA_SELECTORS:
		{
			rdCommand.setRemoveAllDataSelectors(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_DATA_SELECTOR:
		{
			GetDataSelector ds = cmdFactory.createReaderDeviceCommandGetDataSelector();
			ds.setName(readValueParameter().getStringValue());
			rdCommand.setGetDataSelector(ds);
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_ALL_DATA_SELECTORS:
		{
			rdCommand.setGetAllDataSelectors(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_REMOVE_NOTIFICATION_CHANNELS:
		{
			RemoveNotificationChannels ncs = cmdFactory.createReaderDeviceCommandRemoveNotificationChannels();
			NotificationChannelListParamType.List list = cmdFactory.createNotificationChannelListParamTypeList();
			NotificationChannelListParamType listType = cmdFactory.createNotificationChannelListParamType();
			list.getValue().addAll(readListParameter().toStringList());
			listType.setList(list);
			ncs.setChannels(listType);
			rdCommand.setRemoveNotificationChannels(ncs);
			break;
		}
		case TextCommandParserTokenTypes.CMD_REMOVE_ALL_NOTIFICATION_CHANNELS:
		{
			rdCommand.setRemoveAllNotificationChannels(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_NOTIFICATION_CHANNEL:
		{
			GetNotificationChannel nc = cmdFactory.createReaderDeviceCommandGetNotificationChannel();
			nc.setName(readValueParameter().getStringValue());
			rdCommand.setGetNotificationChannel(nc);
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_ALL_NOTIFICATION_CHANNELS:
		{
			rdCommand.setGetAllNotificationChannels(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_REMOVE_TRIGGERS:
		{
			RemoveTriggers trgs = cmdFactory.createReaderDeviceCommandRemoveTriggers();
			TriggerListParamType.List list = cmdFactory.createTriggerListParamTypeList();
			TriggerListParamType listType = cmdFactory.createTriggerListParamType();
			list.getValue().addAll(readListParameter().toStringList());
			listType.setList(list);
			trgs.setTriggers(listType);
			rdCommand.setRemoveTriggers(trgs);
			break;
		}
		case TextCommandParserTokenTypes.CMD_REMOVE_ALL_TRIGGERS:
		{
			rdCommand.setRemoveAllTriggers(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_TRIGGER:
		{
			GetTrigger trg = cmdFactory.createReaderDeviceCommandGetTrigger();
			trg.setName(readValueParameter().getStringValue());
			rdCommand.setGetTrigger(trg);
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_ALL_TRIGGERS:
		{
			rdCommand.setGetAllTriggers(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_REMOVE_TAG_SELECTORS:
		{
			RemoveTagSelectors tss = cmdFactory.createReaderDeviceCommandRemoveTagSelectors();
			TagSelectorListParamType.List list = cmdFactory.createTagSelectorListParamTypeList();
			TagSelectorListParamType listType = cmdFactory.createTagSelectorListParamType();
			list.getValue().addAll(readListParameter().toStringList());
			listType.setList(list);
			tss.setSelectors(listType);
			rdCommand.setRemoveTagSelectors(tss);
			break;
		}
		case TextCommandParserTokenTypes.CMD_REMOVE_ALL_TAG_SELECTORS:
		{
			rdCommand.setRemoveAllTagSelectors(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_TAG_SELECTOR:
		{
			GetTagSelector ts = cmdFactory.createReaderDeviceCommandGetTagSelector();
			ts.setName(readValueParameter().getStringValue());
			rdCommand.setGetTagSelector(ts);
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_ALL_TAG_SELECTORS:
		{
			rdCommand.setGetAllTagSelectors(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_REMOVE_TAG_FIELDS:
		{
			RemoveTagFields tfs = cmdFactory.createReaderDeviceCommandRemoveTagFields();
			TagFieldListParamType.List list = cmdFactory.createTagFieldListParamTypeList();
			TagFieldListParamType listType = cmdFactory.createTagFieldListParamType();
			list.getValue().addAll(readListParameter().toStringList());
			listType.setList(list);
			tfs.setFields(listType);
			rdCommand.setRemoveTagFields(tfs);
			break;
		}
		case TextCommandParserTokenTypes.CMD_REMOVE_ALL_TAG_FIELDS:
		{
			rdCommand.setRemoveAllTagFields(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_TAG_FIELD:
		{
			GetTagField tf = cmdFactory.createReaderDeviceCommandGetTagField();
			tf.setName(readValueParameter().getStringValue());
			rdCommand.setGetTagField(tf);
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_ALL_TAG_FIELDS:
		{
			rdCommand.setGetAllTagFields(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_RESET_TO_DEFAULT_SETTINGS:
		{
			rdCommand.setResetToDefaultSettings(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_REBOOT:
		{
			rdCommand.setReboot(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_GOODBYE:
		{
			rdCommand.setGoodbye(cmdFactory.createNoParamType());
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_READ_POINT:
		{
			GetReadPoint rp = cmdFactory.createReaderDeviceCommandGetReadPoint();
			rp.setName(readValueParameter().getStringValue());
			rdCommand.setGetReadPoint(rp);
			break;
		}
		case TextCommandParserTokenTypes.CMD_GET_ALL_READ_POINTS:
		{
			rdCommand.setGetAllReadPoints(cmdFactory.createNoParamType());
			break;
		}
		default: {
			throw new CommandNotSupportedException("Method not supported by object ReaderDevice.");
		}
		//--------------------------------------------------------------------------
		}
		return rdCommand;
	}
	
	/**
	 * Creates the IR for a Source commmand.
	 * @return The Source IR
	 * @throws TextCommandParserException
	 */
	private SourceCommand getSourceCommand() throws TextCommandParserException {
		try{
			SourceCommand srcCommand = cmdFactory.createSourceCommand();
			switch (commandType) {
			case TextCommandParserTokenTypes.CMD_CREATE:
			{
				SourceCommand.Create c = cmdFactory.createSourceCommandCreate();
				c.setName(readValueParameter().getStringValue());
				srcCommand.setCreate(c);
				break;
			}	
			case TextCommandParserTokenTypes.CMD_GETNAME:
			{
				srcCommand.setGetName(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_IS_FIXED:
			{
				srcCommand.setIsFixed(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_ADD_READ_POINTS:
			{
				AddReadPoints rps = cmdFactory.createSourceCommandAddReadPoints();
				ReadPointListParamType.List list = cmdFactory.createReadPointListParamTypeList();
				ReadPointListParamType listType = cmdFactory.createReadPointListParamType();
				list.getValue().addAll(readListParameter().toStringList());
				listType.setList(list);
				rps.setReadPoints(listType);
				srcCommand.setAddReadPoints(rps);
				break;
			}
			case TextCommandParserTokenTypes.CMD_REMOVE_READ_POINTS:
			{
				RemoveReadPoints rps = cmdFactory.createSourceCommandRemoveReadPoints();
				ReadPointListParamType.List list = cmdFactory.createReadPointListParamTypeList();
				ReadPointListParamType listType = cmdFactory.createReadPointListParamType();
				list.getValue().addAll(readListParameter().toStringList());
				listType.setList(list);
				rps.setReadPoints(listType);
				srcCommand.setRemoveReadPoints(rps);
				break;
			}
			case TextCommandParserTokenTypes.CMD_REMOVE_ALL_READ_POINTS:
			{
				srcCommand.setRemoveAllReadPoints(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_GET_READ_POINT:
			{
				SourceCommand.GetReadPoint rp = cmdFactory.createSourceCommandGetReadPoint();
				rp.setName(readValueParameter().getStringValue());
				srcCommand.setGetReadPoint(rp);
				break;
			}
			case TextCommandParserTokenTypes.CMD_GET_ALL_READ_POINTS:
			{
				srcCommand.setGetAllReadPoints(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_ADD_READ_TRIGGERS:
			{
				SourceCommand.AddReadTriggers rtrg = cmdFactory.createSourceCommandAddReadTriggers();
				TriggerListParamType.List list = cmdFactory.createTriggerListParamTypeList();
				TriggerListParamType listType = cmdFactory.createTriggerListParamType();
				list.getValue().addAll(readListParameter().toStringList());
				listType.setList(list);
				rtrg.setTriggers(listType);
				srcCommand.setAddReadTriggers(rtrg);
				break;
			}
			case TextCommandParserTokenTypes.CMD_REMOVE_READ_TRIGGERS:
			{
				SourceCommand.RemoveReadTriggers rtrgs = cmdFactory.createSourceCommandRemoveReadTriggers();
				TriggerListParamType.List list = cmdFactory.createTriggerListParamTypeList();
				TriggerListParamType listType = cmdFactory.createTriggerListParamType();
				list.getValue().addAll(readListParameter().toStringList());
				listType.setList(list);
				rtrgs.setTriggers(listType);
				srcCommand.setRemoveReadTriggers(rtrgs);
				break;
			}
			case TextCommandParserTokenTypes.CMD_REMOVE_ALL_READ_TRIGGERS:
			{
				srcCommand.setRemoveAllReadTriggers(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_GET_READ_TRIGGER:
			{
				SourceCommand.GetReadTrigger rtrg = cmdFactory.createSourceCommandGetReadTrigger();
				rtrg.setName(readValueParameter().getStringValue());
				srcCommand.setGetReadTrigger(rtrg);
				break;
			}
			case TextCommandParserTokenTypes.CMD_GET_ALL_READ_TRIGGERS:
			{
				srcCommand.setGetAllReadTriggers(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_ADD_TAG_SELECTORS:
			{
				SourceCommand.AddTagSelectors tss = cmdFactory.createSourceCommandAddTagSelectors();
				TagSelectorListParamType.List list = cmdFactory.createTagSelectorListParamTypeList();
				TagSelectorListParamType listType = cmdFactory.createTagSelectorListParamType();
				list.getValue().addAll(readListParameter().toStringList());
				listType.setList(list);
				tss.setSelectors(listType);
				srcCommand.setAddTagSelectors(tss);
				break;
			}
			case TextCommandParserTokenTypes.CMD_REMOVE_TAG_SELECTORS:
			{	
				SourceCommand.RemoveTagSelectors tss = cmdFactory.createSourceCommandRemoveTagSelectors();
				TagSelectorListParamType.List list = cmdFactory.createTagSelectorListParamTypeList();
				TagSelectorListParamType listType = cmdFactory.createTagSelectorListParamType();
				list.getValue().addAll(readListParameter().toStringList());
				listType.setList(list);
				tss.setSelectors(listType);
				srcCommand.setRemoveTagSelectors(tss);
				break;
			}
			case TextCommandParserTokenTypes.CMD_REMOVE_ALL_TAG_SELECTORS:
			{
				srcCommand.setRemoveAllTagSelectors(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_GET_TAG_SELECTOR:
			{
				SourceCommand.GetTagSelector ts = cmdFactory.createSourceCommandGetTagSelector();
				ts.setName(readValueParameter().getStringValue());
				srcCommand.setGetTagSelector(ts);
				break;
			}
			case TextCommandParserTokenTypes.CMD_GET_ALL_TAG_SELECTORS:
			{
				srcCommand.setGetAllTagSelectors(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_GET_GLIMPSED_TIMEOUT:
			{
				srcCommand.setGetGlimpsedTimeout(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_SET_GLIMPSED_TIMEOUT:
			{
				SourceCommand.SetGlimpsedTimeout glmps = cmdFactory.createSourceCommandSetGlimpsedTimeout();
				glmps.setTimeout(readValueParameter().getIntValue());
				srcCommand.setSetGlimpsedTimeout(glmps);
				break;
			}
			case TextCommandParserTokenTypes.CMD_GET_OBSERVED_THRESHOLD:
			{
				srcCommand.setGetObservedThreshold(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_SET_OBSERVED_THRESHOLD:
			{
				SourceCommand.SetObservedThreshold t = cmdFactory.createSourceCommandSetObservedThreshold();
				t.setThreshold(readValueParameter().getIntValue());
				srcCommand.setSetObservedThreshold(t);
				break;
			}
			case TextCommandParserTokenTypes.CMD_GET_OBSERVED_TIMEOUT:
			{
				srcCommand.setGetObservedTimeout(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_SET_OBSERVED_TIMEOUT:
			{
				SourceCommand.SetObservedTimeout t = cmdFactory.createSourceCommandSetObservedTimeout();
				t.setTimeout(readValueParameter().getIntValue());
				srcCommand.setSetObservedTimeout(t);
				break;
			}
			case TextCommandParserTokenTypes.CMD_GET_LOST_TIMEOUT:
			{
				srcCommand.setGetLostTimeout(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_SET_LOST_TIMEOUT:
			{
				SourceCommand.SetLostTimeout t = cmdFactory.createSourceCommandSetLostTimeout();
				t.setTimeout(readValueParameter().getIntValue());
				srcCommand.setSetLostTimeout(t);
				break;
			}
			case TextCommandParserTokenTypes.CMD_RAW_READ_IDS:
			{
				SourceCommand.RawReadIDs r = cmdFactory.createSourceCommandRawReadIDs();
				//Set DataSelector if optional parameter is available
				if (hasParameters()) {
					r.setDataSelector(readValueParameter().getStringValue());
				}
				srcCommand.setRawReadIDs(r);
				break;
			}
			case TextCommandParserTokenTypes.CMD_READ_IDS:
			{
				SourceCommand.ReadIDs r = cmdFactory.createSourceCommandReadIDs();
				//Set DataSelector if optional parameter is available
				if (hasParameters()) {
					r.setDataSelector(readValueParameter().getStringValue());
				}
				srcCommand.setReadIDs(r);
				break;
			}
			case TextCommandParserTokenTypes.CMD_READ:
			{
				SourceCommand.Read r = cmdFactory.createSourceCommandRead();
				
				// Set DataSelector if optional parameter is available
				if (hasParameters()) {
					r.setDataSelector(readValueParameter().getStringValue());
				}
				
				// Set passwords if optional parameter is available
				if (hasParameters()) {
					HexStringListType.List list = cmdFactory.createHexStringListTypeList();
					HexStringListType listType = cmdFactory.createHexStringListType();
					list.getValue().addAll(readListParameter().toHexStringListType());
					listType.setList(list);
					r.setPasswords(listType);
				}
				
				srcCommand.setRead(r);
				break;
			}
			case TextCommandParserTokenTypes.CMD_WRITE_ID:
			{
				SourceCommand.WriteID w = cmdFactory.createSourceCommandWriteID();
				w.setId(HexUtil.hexToByteArray(readValueParameter().getStringValue()));
				
				//Set selectors if optional parameter is available
				if (hasParameters()) {
					TagSelectorListParamType.List list = cmdFactory.createTagSelectorListParamTypeList();
					TagSelectorListParamType listType = cmdFactory.createTagSelectorListParamType();
					list.getValue().addAll(readListParameter().toStringList());
					listType.setList(list);
					w.setSelectors(listType);
				}
				
				//Set passwords if optional parameter is available
				if (hasParameters()) {
					HexStringListType.List list = cmdFactory.createHexStringListTypeList();
					HexStringListType listType = cmdFactory.createHexStringListType();
					list.getValue().addAll(readListParameter().toHexStringListType());
					listType.setList(list);
					w.setPasswords(listType);
				}
				
				srcCommand.setWriteID(w);
				break;
			}
			case TextCommandParserTokenTypes.CMD_WRITE:
			{
				SourceCommand.Write w = cmdFactory.createSourceCommandWrite();
				//Set data
				TagFieldValueListParamType.List tfList = cmdFactory.createTagFieldValueListParamTypeList();
				TagFieldValueListParamType tfListType = cmdFactory.createTagFieldValueListParamType();
				tfList.getValue().addAll(readListParameter().toTagFieldValueList());
				tfListType.setList(tfList);
				w.setData(tfListType);
				
				//Set Selectors if optional parameter is available
				if (hasParameters()) {
					TagSelectorListParamType.List list = cmdFactory.createTagSelectorListParamTypeList();
					TagSelectorListParamType listType = cmdFactory.createTagSelectorListParamType();
					list.getValue().addAll(readListParameter().toStringList());
					listType.setList(list);
					w.setSelectors(listType);
				}
				//Set passwords if optional parameter available
				if (hasParameters()) {
					HexStringListType.List list = cmdFactory.createHexStringListTypeList();
					HexStringListType listType = cmdFactory.createHexStringListType();
					list.getValue().addAll(readListParameter().toHexStringListType());
					listType.setList(list);
					w.setPasswords(listType);
				}
				
				srcCommand.setWrite(w);
				break;
			}
			case TextCommandParserTokenTypes.CMD_KILL:
			{
				SourceCommand.Kill k = cmdFactory.createSourceCommandKill();
				
				// Set Selectors if optional parameter is available
				if (hasParameters()) {
					TagSelectorListParamType.List list = cmdFactory.createTagSelectorListParamTypeList();
					TagSelectorListParamType listType = cmdFactory.createTagSelectorListParamType();
					list.getValue().addAll(readListParameter().toStringList());
					listType.setList(list);
					k.setSelectors(listType);
				}
				// Set passwords if optional parameter available
				if (hasParameters()) {
					HexStringListType.List list = cmdFactory.createHexStringListTypeList();
					HexStringListType listType = cmdFactory.createHexStringListType();
					list.getValue().addAll(readListParameter().toHexStringListType());
					listType.setList(list);
					k.setPasswords(listType);
				}
				
				srcCommand.setKill(k);
				break;
			}
			case TextCommandParserTokenTypes.CMD_GET_READ_CYCLES_PER_TRIGGER:
			{
				srcCommand.setGetReadCyclesPerTrigger(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_GET_MAX_READ_DUTY_CYCLE:
			{
				srcCommand.setGetMaxReadDutyCycle(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_SET_READ_CYCLES_PER_TRIGGER:
			{
				SourceCommand.SetReadCyclesPerTrigger c = cmdFactory.createSourceCommandSetReadCyclesPerTrigger();
				c.setCycles(readValueParameter().getIntValue());
				srcCommand.setSetReadCyclesPerTrigger(c);
				break;
			}
			case TextCommandParserTokenTypes.CMD_SET_MAX_READ_DUTY_CYCLE:
			{
				SourceCommand.SetMaxReadDutyCycle c = cmdFactory.createSourceCommandSetMaxReadDutyCycle();
				c.setDutyCycle(readValueParameter().getIntValue());
				srcCommand.setSetMaxReadDutyCycle(c);
				break;
			}
			case TextCommandParserTokenTypes.CMD_GET_READ_TIMEOUT:
			{
				srcCommand.setGetReadTimeout(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_SET_READ_TIMEOUT:
			{
				SourceCommand.SetReadTimeout t = cmdFactory.createSourceCommandSetReadTimeout();
				t.setTimeout(readValueParameter().getIntValue());
				srcCommand.setSetReadTimeout(t);
				break;
			}
			case TextCommandParserTokenTypes.CMD_GET_SESSION:
			{
				srcCommand.setGetSession(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_SET_SESSION:
			{
				SourceCommand.SetSession s = cmdFactory.createSourceCommandSetSession();
				s.setSession(readValueParameter().getIntValue());
				srcCommand.setSetSession(s);
				break;
			}
			default: {
				throw new CommandNotSupportedException("Method not supported by object Source.");
			}
			}

			return srcCommand;
		} catch (JAXBException e) {
			log.error(e);
			return null;
		} 
	}
	/**
	 * Creates the IR for a ReaderPoint commmand.
	 * @return The ReadPoint IR
	 * @throws TextCommandParserException
	 */
	private ReadPointCommand getReadPointCommand() throws TextCommandParserException {
		ReadPointCommand rpCommand = cmdFactory.createReadPointCommand();
		switch (commandType) {
		case TextCommandParserTokenTypes.CMD_GETNAME: {
			rpCommand.setGetName(cmdFactory.createNoParamType());
			break;
		}
		default: {
			throw new CommandNotSupportedException("Method not supported by object ReadPoint.");
		}
		}
		return rpCommand; 
	}
	/**
	 * Creates the IR for a TriggerCommand commmand.
	 * @return The TriggerCommand IR
	 * @throws TextCommandParserException
	 */
		private TriggerCommand getTriggerCommand() throws TextCommandParserException {
			TriggerCommand trgCommand = cmdFactory.createTriggerCommand();
			switch (commandType) {
			case TextCommandParserTokenTypes.CMD_CREATE: {
				TriggerCommand.Create c = cmdFactory.createTriggerCommandCreate();
				c.setName(readValueParameter().getStringValue());
				c.setTriggerType(readValueParameter().getStringValue());
				c.setTriggerValue(readValueParameter().getStringValue());
				trgCommand.setCreate(c);
				break;
			}
			case TextCommandParserTokenTypes.CMD_GET_MAX_NUMBER_SUPPORTED: {
				trgCommand.setGetMaxNumberSupported(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_GETNAME: {
				trgCommand.setGetName(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_GET_TYPE: {
				trgCommand.setGetType(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_GET_VALUE: {
				trgCommand.setGetValue(cmdFactory.createNoParamType());
				break;
			}
			case TextCommandParserTokenTypes.CMD_FIRE: {
				trgCommand.setFire(cmdFactory.createNoParamType());
				break;
			}
			default: {
				throw new CommandNotSupportedException("Method not supported by object Trigger.");
			}
			}
			return trgCommand; 
		}	
		/**
		 * Creates the IR for a TagSelector commmand.
		 * @return The TagSelectorCommand IR
		 * @throws TextCommandParserException
		 */
			private TagSelectorCommand getTagSelectorCommand() throws TextCommandParserException {
				TagSelectorCommand tsCommand = cmdFactory.createTagSelectorCommand();
				switch (commandType) {
				case TextCommandParserTokenTypes.CMD_CREATE: {
					TagSelectorCommand.Create c = cmdFactory.createTagSelectorCommandCreate();
					c.setName(readValueParameter().getStringValue());
					c.setField(readValueParameter().getStringValue());
					c.setValue(HexUtil.hexToByteArray(readValueParameter().getStringValue()));
					c.setMask(HexUtil.hexToByteArray(readValueParameter().getStringValue()));
					c.setInclusiveFlag(readValueParameter().getBooleanValue());
					tsCommand.setCreate(c);
					break;
				}
				case TextCommandParserTokenTypes.CMD_GETNAME: {
					tsCommand.setGetName(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_MAX_NUMBER_SUPPORTED: {
					tsCommand.setGetMaxNumberSupported(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_TAG_FIELD: {
					tsCommand.setGetTagField(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_VALUE: {
					tsCommand.setGetValue(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_MASK: {
					tsCommand.setGetMask(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_INCLUSIVE_FLAG: {
					tsCommand.setGetInclusiveFlag(cmdFactory.createNoParamType());
					break;
				}
				default: {
					throw new CommandNotSupportedException("Method not supported by object TagSelector.");
				}
				}
				return tsCommand; 
			}	
			/**
			 * Creates the IR for a NotificationChannel commmand.
			 * @return The NotificationChannelCommand IR
			 * @throws TextCommandParserException
			 */
			private NotificationChannelCommand getNotificationChannelCommand() throws TextCommandParserException {
				NotificationChannelCommand ncCommand = cmdFactory.createNotificationChannelCommand();
				switch (commandType) {
				case TextCommandParserTokenTypes.CMD_CREATE: {
					NotificationChannelCommand.Create c = cmdFactory.createNotificationChannelCommandCreate();
					c.setName(readValueParameter().getStringValue());
					c.setAddress(readValueParameter().getStringValue());
					ncCommand.setCreate(c);
					break;
				}
				case TextCommandParserTokenTypes.CMD_GETNAME: {
					ncCommand.setGetName(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_ADDRESS: {
					ncCommand.setGetAddress(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_EFFECTIVE_ADDRESS: {
					ncCommand.setGetEffectiveAddress(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_SET_ADDRESS: {
					NotificationChannelCommand.SetAddress a = cmdFactory.createNotificationChannelCommandSetAddress();
					a.setAddress(readValueParameter().getStringValue());
					ncCommand.setSetAddress(a);
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_DATA_SELECTOR: {
					ncCommand.setGetDataSelector(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_SET_DATA_SELECTOR: {
					NotificationChannelCommand.SetDataSelector ds = cmdFactory.createNotificationChannelCommandSetDataSelector();
					ds.setDataSelector(readValueParameter().getStringValue());
					ncCommand.setSetDataSelector(ds);
					break;
				}
				case TextCommandParserTokenTypes.CMD_ADD_SOURCES: {
					NotificationChannelCommand.AddSources srcs = cmdFactory.createNotificationChannelCommandAddSources();
					SourceListParamType.List list = cmdFactory.createSourceListParamTypeList();
					SourceListParamType listType = cmdFactory.createSourceListParamType();
					list.getValue().addAll(readListParameter().toStringList());
					listType.setList(list);
					srcs.setSources(listType);
					ncCommand.setAddSources(srcs);
					break;
				}
				case TextCommandParserTokenTypes.CMD_REMOVE_SOURCES: {
					NotificationChannelCommand.RemoveSources srcs = cmdFactory.createNotificationChannelCommandRemoveSources();
					SourceListParamType.List list = cmdFactory.createSourceListParamTypeList();
					SourceListParamType listType = cmdFactory.createSourceListParamType();
					list.getValue().addAll(readListParameter().toStringList());
					listType.setList(list);
					srcs.setSources(listType);
					ncCommand.setRemoveSources(srcs);
					break;
				}
				case TextCommandParserTokenTypes.CMD_REMOVE_ALL_SOURCES: {
					ncCommand.setRemoveAllSources(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_SOURCE: {
					NotificationChannelCommand.GetSource src = cmdFactory.createNotificationChannelCommandGetSource();
					src.setName(readValueParameter().getStringValue());
					ncCommand.setGetSource(src);
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_ALL_SOURCES: {
					ncCommand.setGetAllSources(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_ADD_NOTIFICATION_TRIGGERS: {
					NotificationChannelCommand.AddNotificationTriggers trgs = cmdFactory.createNotificationChannelCommandAddNotificationTriggers();
					TriggerListParamType.List list = cmdFactory.createTriggerListParamTypeList();
					TriggerListParamType listType = cmdFactory.createTriggerListParamType();
					list.getValue().addAll(readListParameter().toStringList());
					listType.setList(list);
					trgs.setTriggers(listType);
					ncCommand.setAddNotificationTriggers(trgs);
					break;
				}
				case TextCommandParserTokenTypes.CMD_REMOVE_NOTIFICATION_TRIGGERS: {
					NotificationChannelCommand.RemoveNotificationTriggers trgs = cmdFactory.createNotificationChannelCommandRemoveNotificationTriggers();
					TriggerListParamType.List list = cmdFactory.createTriggerListParamTypeList();
					TriggerListParamType listType = cmdFactory.createTriggerListParamType();
					list.getValue().addAll(readListParameter().toStringList());
					listType.setList(list);
					trgs.setTriggers(listType);
					ncCommand.setRemoveNotificationTriggers(trgs);
					break;
				}
				case TextCommandParserTokenTypes.CMD_REMOVE_ALL_NOTIFICATION_TRIGGERS: {
					ncCommand.setRemoveAllNotificationTriggers(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_NOTIFICATION_TRIGGER: {
					NotificationChannelCommand.GetNotificationTrigger trg = cmdFactory.createNotificationChannelCommandGetNotificationTrigger();
					trg.setName(readValueParameter().getStringValue());				
					ncCommand.setGetNotificationTrigger(trg);
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_ALL_NOTIFICATION_TRIGGERS: {
					ncCommand.setGetAllNotificationTriggers(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_READ_QUEUED_DATA: {
					NotificationChannelCommand.ReadQueuedData rq = cmdFactory.createNotificationChannelCommandReadQueuedData();
					//Set the ClearBuffer flag if the optional parameter is available
					if (hasParameters()) {
						rq.setClearBuffer(readValueParameter().getBooleanValue());
					}
					ncCommand.setReadQueuedData(rq);
					break;
				}
				default: {
					throw new CommandNotSupportedException("Method not supported by object NotificationChannel.");
				}
				}
				return ncCommand; 
			}	
			
			/**
			 * Creates the IR for a DataSelector commmand.
			 * @return The DataSelectorCommand IR
			 * @throws TextCommandParserException
			 */
			private DataSelectorCommand getDataSelectorCommand() throws TextCommandParserException {
				DataSelectorCommand dsCommand = cmdFactory.createDataSelectorCommand();
				switch (commandType) {
				case TextCommandParserTokenTypes.CMD_CREATE: {
					DataSelectorCommand.Create c = cmdFactory.createDataSelectorCommandCreate();
					c.setName(readValueParameter().getStringValue());
					dsCommand.setCreate(c);
					break;
				}
				case TextCommandParserTokenTypes.CMD_GETNAME: {
					dsCommand.setGetName(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_ADD_FIELD_NAMES: {
					DataSelectorCommand.AddFieldNames fns = cmdFactory.createDataSelectorCommandAddFieldNames();
					FieldNameListParamType.List list = cmdFactory.createFieldNameListParamTypeList();
					FieldNameListParamType listType = cmdFactory.createFieldNameListParamType();
					list.getValue().addAll(readListParameter().toStringList());
					listType.setList(list);
					fns.setFieldNames(listType);
					dsCommand.setAddFieldNames(fns);
					break;
				}
				case TextCommandParserTokenTypes.CMD_REMOVE_FIELD_NAMES: {
					DataSelectorCommand.RemoveFieldNames fns = cmdFactory.createDataSelectorCommandRemoveFieldNames();
					FieldNameListParamType.List list = cmdFactory.createFieldNameListParamTypeList();
					FieldNameListParamType listType = cmdFactory.createFieldNameListParamType();
					list.getValue().addAll(readListParameter().toStringList());
					listType.setList(list);
					fns.setFieldNames(listType);
					dsCommand.setRemoveFieldNames(fns);
					break;
				}
				case TextCommandParserTokenTypes.CMD_REMOVE_ALL_FIELD_NAMES: {
					dsCommand.setRemoveAllFieldNames(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_ALL_FIELD_NAMES: {
					dsCommand.setGetAllFieldNames(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_ADD_EVENT_FILTERS: {
					DataSelectorCommand.AddEventFilters efs = cmdFactory.createDataSelectorCommandAddEventFilters();
					EventTypeListParamType.List list = cmdFactory.createEventTypeListParamTypeList();
					EventTypeListParamType listType = cmdFactory.createEventTypeListParamType();
					list.getValue().addAll(readListParameter().toStringList());
					listType.setList(list);
					efs.setEventType(listType);
					dsCommand.setAddEventFilters(efs);
					break;
				}
				case TextCommandParserTokenTypes.CMD_REMOVE_EVENT_FILTERS: {
					DataSelectorCommand.RemoveEventFilters efs = cmdFactory.createDataSelectorCommandRemoveEventFilters();
					EventTypeListParamType.List list = cmdFactory.createEventTypeListParamTypeList();
					EventTypeListParamType listType = cmdFactory.createEventTypeListParamType();
					list.getValue().addAll(readListParameter().toStringList());
					listType.setList(list);
					efs.setEventType(listType);
					dsCommand.setRemoveEventFilters(efs);
					break;
				}
				case TextCommandParserTokenTypes.CMD_REMOVE_ALL_EVENT_FILTERS: {
					dsCommand.setRemoveAllEventFilters(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_ALL_EVENT_FILTERS: {
					dsCommand.setGetAllEventFilters(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_ADD_TAG_FIELD_NAMES: {
					DataSelectorCommand.AddTagFieldNames tfs = cmdFactory.createDataSelectorCommandAddTagFieldNames();
					StringListParamType.List list = cmdFactory.createStringListParamTypeList();
					StringListParamType listType = cmdFactory.createStringListParamType();
					list.getValue().addAll(readListParameter().toStringList());
					listType.setList(list);
					tfs.setFieldNames(listType);
					dsCommand.setAddTagFieldNames(tfs);
					break;
				}
				case TextCommandParserTokenTypes.CMD_REMOVE_TAG_FIELD_NAMES: {
					DataSelectorCommand.RemoveTagFieldNames tfs = cmdFactory.createDataSelectorCommandRemoveTagFieldNames();
					StringListParamType.List list = cmdFactory.createStringListParamTypeList();
					StringListParamType listType = cmdFactory.createStringListParamType();
					list.getValue().addAll(readListParameter().toStringList());
					listType.setList(list);
					tfs.setFieldNames(listType);
					dsCommand.setRemoveTagFieldNames(tfs);
					break;
				}
				case TextCommandParserTokenTypes.CMD_REMOVE_ALL_TAG_FIELD_NAMES: {
					dsCommand.setRemoveAllTagFieldNames(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_ALL_TAG_FIELD_NAMES: {
					dsCommand.setGetAllTagFieldNames(cmdFactory.createNoParamType());
					break;
				}
				default: {
					throw new CommandNotSupportedException("Method not supported by object DataSelector.");
				}
				}
				return dsCommand; 
			}	
			/**
			 * Creates the IR for a EventType commmand.
			 * @return The EventTypeCommand IR
			 */
			private EventTypeCommand getEventTypeCommand() throws TextCommandParserException {
				EventTypeCommand evCommand = cmdFactory.createEventTypeCommand();
				switch (commandType) {
				case TextCommandParserTokenTypes.CMD_GET_SUPPORTED_TYPES: {
					evCommand.setGetSupportedTypes(cmdFactory.createNoParamType());
					break;
				}
				default: {
					throw new CommandNotSupportedException("Method not supported by object EventType.");
				}
				}
				return evCommand;
			}	
			/**
			 * Creates the IR for a TriggerType commmand.
			 * @return The TriggerTypeCommand IR
			 */
			private TriggerTypeCommand getTriggerTypeCommand() throws TextCommandParserException {
				TriggerTypeCommand ttCommand = cmdFactory.createTriggerTypeCommand();
				switch (commandType) {
				case TextCommandParserTokenTypes.CMD_GET_SUPPORTED_TYPES: {
					ttCommand.setGetSupportedTypes(cmdFactory.createNoParamType());
					break;
				}
				default: {
					throw new CommandNotSupportedException("Method not supported by object TriggerType.");
				}
				}
				return ttCommand;
			}
			/**
			 * Creates the IR for a FieldName commmand.
			 * @return The FieldNameCommand IR
			 */
			private FieldNameCommand getFieldNameCommand() throws TextCommandParserException {
				FieldNameCommand fnCommand = cmdFactory.createFieldNameCommand();
				switch (commandType) {
				case TextCommandParserTokenTypes.CMD_GET_SUPPORTED_NAMES: {
					fnCommand.setGetSupportedNames(cmdFactory.createNoParamType());
					break;
				}
				default: {
					throw new CommandNotSupportedException("Method not supported by object FieldName.");
				}
				}
				return fnCommand;
			}					
			/**
			 * Creates the IR for a TagField commmand.
			 * @return The TagFieldCommand IR
			 * @throws TextCommandParserException
			 */
			private TagFieldCommand getTagFieldCommand() throws TextCommandParserException {
				TagFieldCommand tfCommand = cmdFactory.createTagFieldCommand();
				switch (commandType) {
				case TextCommandParserTokenTypes.CMD_CREATE: {
					TagFieldCommand.Create c = cmdFactory.createTagFieldCommandCreate();
					c.setName(readValueParameter().getStringValue());
					tfCommand.setCreate(c);
					break;
				}
				case TextCommandParserTokenTypes.CMD_GETNAME: {
					tfCommand.setGetName(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_TAG_FIELD_NAME: {
					tfCommand.setGetTagFieldName(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_SET_TAG_FIELD_NAME: {
					TagFieldCommand.SetTagFieldName tagFieldName = cmdFactory.createTagFieldCommandSetTagFieldName();
					tagFieldName.setTagFieldName(readValueParameter().getStringValue());
					tfCommand.setSetTagFieldName(tagFieldName);
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_MEMORY_BANK: {
					tfCommand.setGetMemoryBank(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_SET_MEMORY_BANK: {
					TagFieldCommand.SetMemoryBank memoryBank = cmdFactory.createTagFieldCommandSetMemoryBank();
					memoryBank.setMemoryBank(BigInteger.valueOf(readValueParameter().getIntValue()));
					tfCommand.setSetMemoryBank(memoryBank);
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_OFFSET: {
					tfCommand.setGetOffset(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_SET_OFFSET: {
					TagFieldCommand.SetOffset offset = cmdFactory.createTagFieldCommandSetOffset();
					offset.setOffset(readValueParameter().getIntValue());
					tfCommand.setSetOffset(offset);
					break;
				}
				case TextCommandParserTokenTypes.CMD_GET_LENGTH: {
					tfCommand.setGetLength(cmdFactory.createNoParamType());
					break;
				}
				case TextCommandParserTokenTypes.CMD_SET_LENGTH: {
					TagFieldCommand.SetLength length = cmdFactory.createTagFieldCommandSetLength();
					length.setLength(readValueParameter().getIntValue());
					tfCommand.setSetLength(length);
					break;
				}
				default: {
					throw new CommandNotSupportedException("Method not supported by object TagField.");
				}
				}
				return tfCommand;
			}

			
}
