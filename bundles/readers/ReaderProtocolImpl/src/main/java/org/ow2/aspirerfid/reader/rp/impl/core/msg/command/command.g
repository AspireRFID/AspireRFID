header { 
package org.accada.reader.msg.command;
}

{
import org.accada.reader.msg.command.impl.CommandImpl;
}

class TextCommandParser extends Parser;
options {
	defaultErrorHandler = false;
}
//TODO: Grammatik noch verfollständigen (z.B. hexstrings)
tokens {
	READERDEVICE = "ReaderDevice";
	RD = "RD";
	SOURCE = "Source";
	SRC = "SRC";
	READPOINT = "ReadPoint";
	RP = "RP";
	TAGSELECTOR = "TagSelector";
	RF = "RF";
	DATASELECTOR = "DataSelector";
	DS = "DS";
	NOTIFICATIONCHANNEL = "NotificationChannel";
	NC = "NC";
	TRIGGER = "Trigger";
	TRG = "TRG";
	COMMANDCHANNEL = "CommandChannel";
	CC = "CC";
	EVENTTYPE = "EventType";
	ET ="ET";
	TRIGGERTYPE = "TriggerType";
	TT = "TT";
	FIELDNAME = "FieldName";
	FN = "FN";
	IOPORTS = "IOPorts";
	IOP = "IOP";
	TAGFIELD = "TagField";
	TF = "TF";
	CMD_GETEPC = "getEPC";
	CMD_GE = "gE";
	CMD_GETMANUFACTURER = "getManufacturer";
	CMD_GMAN = "gMan";	
	CMD_GETMODEL = "getModel";
	CMD_GMOD = "gMod";
	CMD_GETHANDLE = "getHandle";
	CMD_GH = "gH";
	CMD_SETHANDLE = "setHandle";
	CMD_SH = "sH";
	CMD_GETNAME = "getName";
	CMD_GN = "gN";
	CMD_SETNAME = "setName";
	CMD_SN = "sN";
	CMD_GETROLE = "getRole";
	CMD_GR = "gR";
	CMD_SETROLE = "setRole";
	CMD_SR = "sR";
	CMD_GET_TIME_TICKS = "getTimeTicks";
	CMD_GTIC = "gTic";
	CMD_GET_TIME_UTC = "getTimeUTC";
	CMD_GUTC = "gUTC";
	CMD_SET_TIME_UTC = "setTimeUTC";
	CMD_SUTC = "sUTC";
	CMD_GET_MANUFACTURER_DESCRIPTION = "getManufacturerDescription";
	CMD_GMD = "gMD";
	CMD_GET_CURRENT_SOURCE = "getCurrentSource";
	CMD_GCS = "gCS";
	CMD_SET_CURRENT_SOURCE = "setCurrentSource";
	CMD_SCS = "sCS";
	CMD_GET_CURRENT_DATA_SELECTOR = "getCurrentDataSelector";
	CMD_GCDS = "gCDS";
	CMD_SET_CURRENT_DATA_SELECTOR = "setCurrentDataSelector";
	CMD_SCDS = "sCDS";
	CMD_REMOVE_SOURCES = "removeSources";
	CMD_RSRC = "rSRC";
	CMD_REMOVE_ALL_SOURCES = "removeAllSources";
	CMD_RASRC = "raSRC";
	CMD_GET_SOURCE = "getSource";
	CMD_GSRC = "gSRC";
	CMD_GET_ALL_SOURCES = "getAllSources";
	CMD_GASRC = "gaSRC";
	CMD_REMOVE_DATA_SELECTORS = "removeDataSelectors";
	CMD_RDS = "rDS";
	CMD_REMOVE_ALL_DATA_SELECTORS = "removeAllDataSelectors";
	CMD_RADS = "raDS";
	CMD_GET_DATA_SELECTOR = "getDataSelector";
	CMD_GDS = "gDS";
	CMD_GET_ALL_DATA_SELECTORS = "getAllDataSelectors";
	CMD_GADS = "gaDS";
	CMD_REMOVE_NOTIFICATION_CHANNELS = "removeNotificationChannels";
	CMD_RNC = "rNC";
	CMD_REMOVE_ALL_NOTIFICATION_CHANNELS = "removeAllNotificationChannels";
	CMD_RANC = "raNC";
	CMD_GET_NOTIFICATION_CHANNEL = "getNotificationChannel";
	CMD_GNC = "gNC";
	CMD_GET_ALL_NOTIFICATION_CHANNELS = "getAllNotificationChannels";
	CMD_GANC = "gaNC";
	CMD_REMOVE_TRIGGERS = "removeTriggers";
	CMD_RTRG = "rTRG";
	CMD_REMOVE_ALL_TRIGGERS = "removeAllTriggers";
	CMD_RATRG = "raTRG";
	CMD_GET_TRIGGER = "getTrigger";
	CMD_GTRG = "gTRG";
	CMD_GET_ALL_TRIGGERS = "getAllTriggers";
	CMD_GATRG = "gaTRG";
	CMD_REMOVE_TAG_SELECTORS = "removeTagSelectors";
	CMD_RTS = "rTS";
	CMD_REMOVE_ALL_TAG_SELECTORS = "removeAllTagSelectors";
	CMD_RATS = "raTS";
	CMD_GET_TAG_SELECTOR = "getTagSelector";
	CMD_GTS = "gTS";
	CMD_GET_ALL_TAG_SELECTORS = "getAllTagSelectors";
	CMD_GATS = "gaTS";
	CMD_REMOVE_TAG_FIELDS = "removeTagFields";
	CMD_RTF = "rTF";
	CMD_REMOVE_ALL_TAG_FIELDS = "removeAllTagFields";
	CMD_RATF = "raTF";
	CMD_GET_TAG_FIELD = "getTagField";
	CMD_GTF = "gTF";
	CMD_GET_ALL_TAG_FIELDS = "getAllTagFields";
	CMD_GATF = "gaTF";
	CMD_RESET_TO_DEFAULT_SETTINGS = "resetToDefaultSettings";
	CMD_RESET = "reset";
	CMD_REBOOT = "reboot";
	CMD_BOOT = "boot";
	CMD_GOODBYE = "goodbye";
	CMD_BYE = "bye";
	CMD_GET_READ_POINT = "getReadPoint";
	CMD_GRP = "gRP";
	CMD_GET_ALL_READ_POINTS = "getAllReadPoints";
	CMD_GARP = "gaRP";
	CMD_CREATE = "create";
	CMD_C = "c";
	CMD_IS_FIXED = "isFixed";
	CMD_ISFX = "isFX";
	CMD_ADD_READ_POINTS = "addReadPoints";
	CMD_ARP = "aRP";
	CMD_REMOVE_READ_POINTS = "removeReadPoints";
	CMD_RRP = "rRP";
	CMD_REMOVE_ALL_READ_POINTS = "removeAllReadPoints";
	CMD_RARP = "raRP";
	CMD_ADD_READ_TRIGGERS = "addReadTriggers";
	CMD_ART = "aRT";
	CMD_REMOVE_READ_TRIGGERS = "removeReadTriggers";
	CMD_RRT = "rRT";
	CMD_REMOVE_ALL_READ_TRIGGERS = "removeAllReadTriggers";
	CMD_RART = "raRT";
	CMD_GET_READ_TRIGGER = "getReadTrigger";
	CMD_GRT = "gRT";
	CMD_GET_ALL_READ_TRIGGERS = "getAllReadTriggers";
	CMD_GART = "gaRT";
	CMD_ADD_TAG_SELECTORS = "addTagSelectors";
	CMD_ATS = "aTS";
	CMD_GET_GLIMPSED_TIMEOUT = "getGlimpsedTimeout";
	CMD_GGTO = "gGTO";
	CMD_SET_GLIMPSED_TIMEOUT = "setGlimpsedTimeout";
	CMD_SGTO = "sGTO";
	CMD_GET_OBSERVED_THRESHOLD = "getObservedThreshold";
	CMD_GOTH = "gOTH";
	CMD_SET_OBSERVED_THRESHOLD = "setObservedThreshold";
	CMD_SOTH = "sOTH";
	CMD_GET_OBSERVED_TIMEOUT = "getObservedTimeout";
	CMD_GOTO = "gOTO";
	CMD_SET_OBSERVED_TIMEOUT = "setObservedTimeout";
	CMD_SOTO = "sOTO";
	CMD_GET_LOST_TIMEOUT = "getLostTimeout";
	CMD_GLTO = "gLTO";
	CMD_SET_LOST_TIMEOUT = "setLostTimeout";
	CMD_SLTO = "sLTO";
	CMD_RAW_READ_IDS = "rawReadIDs";
	CMD_RRID = "rrid";
	CMD_READ_IDS = "readIDs";
	CMD_RID = "rid";
	CMD_READ = "read";
	CMD_R = "r";
	CMD_WRITE_ID = "writeID";
	CMD_WID = "wid";
	CMD_WRITE = "write";
	CMD_W = "w";
	CMD_KILL = "kill";
	CMD_K = "k";
	CMD_GET_READ_CYCLES_PER_TRIGGER = "getReadCyclesPerTrigger";
	CMD_GRCPT = "gRCPT";
	CMD_SET_READ_CYCLES_PER_TRIGGER = "setReadCyclesPerTrigger";
	CMD_SRCPT = "sRCPT";
	CMD_GET_MAX_READ_DUTY_CYCLE = "getMaxReadDutyCycles";
	CMD_GMRDC = "gMRDC";
	CMD_SET_MAX_READ_DUTY_CYCLE = "setMaxReadDutyCycles";
	CMD_SMRDC = "sMRDC";
	CMD_GET_READ_TIMEOUT = "getReadTimeout";
	CMD_GRTO = "gRTO";
	CMD_SET_READ_TIMEOUT = "setReadTimeout";
	CMD_SRTO = "sRTO";
	CMD_SET_SESSION = "setSession";
	CMD_SSS = "sSS";
	CMD_GET_SESSION = "getSession";
	CMD_GSS = "gSS";
	CMD_GET_MAX_NUMBER_SUPPORTED = "getMaxNumberSupported";
	CMD_GMAX = "gMax";
	CMD_GMX = "gMx";
	CMD_GET_TYPE = "getType";
	CMD_GT = "gT";
	CMD_GET_VALUE = "getValue";
	CMD_GV = "gV";
	CMD_FIRE = "fire";
	CMD_F = "f";
	CMD_GET_MASK = "getMask";
	CMD_GM = "gM";
	CMD_GET_INCLUSIVE_FLAG = "getInclusiveFlag";
	CMD_GIF = "gIF";
	CMD_GET_ADDRESS = "getAddress";
	CMD_GADR = "gAdr";
	CMD_GET_EFFECTIVE_ADDRESS = "getEffectiveAddress";
	CMD_GEADR = "gEAdr";
	CMD_SET_ADDRESS = "setAddress";
	CMD_SADR = "sAdr";
	CMD_SET_DATA_SELECTOR = "setDataSelector";
	CMD_SDS = "sDS";
	CMD_ADD_SOURCES = "addSources";
	CMD_ASRC = "aSRC";
	CMD_ADD_NOTIFICATION_TRIGGERS = "addNotificationTriggers";
	CMD_ANT = "aNT";
	CMD_REMOVE_NOTIFICATION_TRIGGERS = "removeNotificatonTriggers";
	CMD_RNT = "rNT";
	CMD_REMOVE_ALL_NOTIFICATION_TRIGGERS = "removeAllNotificationTriggers";
	CMD_RANT = "raNT";
	CMD_GET_NOTIFICATION_TRIGGER = "getNotificationTrigger";
	CMD_GNT = "gNT";
	CMD_GET_ALL_NOTIFICATION_TRIGGERS = "getAllNotificationTriggers";
	CMD_GANT = "gaNT";
	CMD_READ_QUEUED_DATA = "readQueuedData";
	CMD_RQD = "rqd";
	CMD_ADD_FIELD_NAMES = "addFieldNames";
	CMD_AFN = "aFN";
	CMD_REMOVE_FIELD_NAMES = "removeFieldNames";
	CMD_RFN = "rFN";
	CMD_REMOVE_ALL_FIELD_NAMES = "removeAllFieldNames";
	CMD_RAFN = "raFN";
	CMD_GET_ALL_FIELD_NAMES = "getAllFieldNames";
	CMD_GAFN = "gaFN";
	CMD_ADD_EVENT_FILTERS = "addEventFilters";
	CMD_AEF = "aEF";
	CMD_REMOVE_EVENT_FILTERS = "removeEventFilters";
	CMD_REF = "rEF";
	CMD_REMOVE_ALL_EVENT_FILTERS = "removeAllEventFilters";
	CMD_RAEF = "raEF";
	CMD_GET_ALL_EVENT_FILTERS = "getAllEventFilters";
	CMD_GAEF = "gaEF";
	CMD_ADD_TAG_FIELD_NAMES = "addTagFieldNames";
	CMD_ATFN = "aTFN";
	CMD_REMOVE_TAG_FIELD_NAMES = "removeTagFieldNames";
	CMD_RTFN = "rTFN";
	CMD_REMOVE_ALL_TAG_FIELD_NAMES = "removeAllTagFieldNames";
	CMD_RATFN = "raTFN";
	CMD_GET_ALL_TAG_FIELD_NAMES = "getAllTagFieldNames";
	CMD_GATFN = "gaTFN";
	CMD_GET_SUPPORTED_TYPES = "getSupportedTypes";
	CMD_GST = "gST";
	CMD_GET_SUPPORTED_NAMES = "getSupportedNames";
	CMD_GSN = "gSN";
	CMD_GET_TAG_FIELD_NAME = "getTagFieldName";
	CMD_GTFN = "gTFN";
	CMD_SET_TAG_FIELD_NAME = "setTagFieldName";
	CMD_STFN = "sTFN";
	CMD_GET_MEMORY_BANK = "getMemoryBank";
	CMD_GMB = "gMB";
	CMD_SET_MEMORY_BANK = "setMemoryBank";
	CMD_SMB = "sMB";
	CMD_GET_OFFSET = "getOffset";
	CMD_GOFF = "gOFF";
	CMD_SET_OFFSET = "setOffset";
	CMD_SOFF = "sOFF";
	CMD_GET_LENGTH = "getLength";
	CMD_GLEN = "gLEN";
	CMD_SET_LENGTH = "setLength";
	CMD_SLEN = "sLEN";
	
	
	
}
{
	TextCommandParserHelper helper = new TextCommandParserHelper();
}
	
command_line returns [TextCommandParserHelper parserHelper]
	{
		parserHelper = helper;
		String id = null;
	}
	:	(id=command_id)? (object_type_name (SHARP target_name)? DOT)? command_name (parameter_list)? LF
		{
			parserHelper.setId(id);
		}
	;

command_id returns [String s]
{
	int r = 0;
	s = null;
}
	:	EXCLAMATION r=dec_val { s = Integer.toString(r); }
	;
	
object_type_name
	:	( READERDEVICE 			{ helper.setObject(READERDEVICE); }
		| RD					{ helper.setObject(READERDEVICE); }
		| SOURCE				{ helper.setObject(SOURCE); }
		| SRC 					{ helper.setObject(SOURCE); }
		| READPOINT 			{ helper.setObject(READPOINT); }
		| RP 					{ helper.setObject(READPOINT); }
		| TAGSELECTOR 			{ helper.setObject(TAGSELECTOR); }
		| RF 					{ helper.setObject(TAGSELECTOR); }
		| DATASELECTOR 			{ helper.setObject(DATASELECTOR); }
		| DS 					{ helper.setObject(DATASELECTOR); }
		| NOTIFICATIONCHANNEL	{ helper.setObject(NOTIFICATIONCHANNEL); }
		| NC 					{ helper.setObject(NOTIFICATIONCHANNEL); }
		| TRIGGER 				{ helper.setObject(TRIGGER); }
		| TRG 					{ helper.setObject(TRIGGER); }
		| COMMANDCHANNEL 		{ helper.setObject(COMMANDCHANNEL); }
		| CC 					{ helper.setObject(COMMANDCHANNEL); }
		| EVENTTYPE			 	{ helper.setObject(EVENTTYPE); }
		| ET 					{ helper.setObject(EVENTTYPE); }
		| TRIGGERTYPE		 	{ helper.setObject(TRIGGERTYPE); }
		| TT		 			{ helper.setObject(TRIGGERTYPE); }
		| FIELDNAME 			{ helper.setObject(FIELDNAME); }
		| FN					{ helper.setObject(FIELDNAME); }
		| IOPORTS				{ helper.setObject(IOPORTS); }
		| IOP 					{ helper.setObject(IOPORTS); }
		| TAGFIELD				{ helper.setObject(TAGFIELD); }
		| TF					{ helper.setObject(TF); }
		)
	;
	
target_name
	:	t:IDENT
		{
			helper.setTargetName(t.getText());
		}
	;
	
command_name
	:	( CMD_CREATE						{ helper.setCommand(CMD_CREATE); }
		| CMD_C								{ helper.setCommand(CMD_CREATE); }
		| CMD_GETEPC						{ helper.setCommand(CMD_GETEPC); }
		| CMD_GE							{ helper.setCommand(CMD_GETEPC); }
		| CMD_GETMANUFACTURER				{ helper.setCommand(CMD_GETMANUFACTURER); }
		| CMD_GMAN							{ helper.setCommand(CMD_GETMANUFACTURER); }
		| CMD_GETMODEL						{ helper.setCommand(CMD_GETMODEL); }
		| CMD_GMOD							{ helper.setCommand(CMD_GETMODEL); }
		| CMD_GETHANDLE						{ helper.setCommand(CMD_GETHANDLE); }
		| CMD_GH							{ helper.setCommand(CMD_GETHANDLE); }
		| CMD_SETHANDLE						{ helper.setCommand(CMD_SETHANDLE); }
		| CMD_SH							{ helper.setCommand(CMD_SETHANDLE); }
		| CMD_GETNAME						{ helper.setCommand(CMD_GETNAME); }
		| CMD_GN							{ helper.setCommand(CMD_GETNAME); }
		| CMD_SETNAME						{ helper.setCommand(CMD_SETNAME); }
		| CMD_SN							{ helper.setCommand(CMD_SETNAME); }
		| CMD_GETROLE						{ helper.setCommand(CMD_GETROLE); }
		| CMD_GR							{ helper.setCommand(CMD_GETROLE); }
		| CMD_SETROLE						{ helper.setCommand(CMD_SETROLE); }
		| CMD_SR							{ helper.setCommand(CMD_SETROLE); }
		| CMD_GET_TIME_TICKS				{ helper.setCommand(CMD_GET_TIME_TICKS); }
		| CMD_GTIC							{ helper.setCommand(CMD_GET_TIME_TICKS); }
		| CMD_GET_TIME_UTC					{ helper.setCommand(CMD_GET_TIME_UTC); }
		| CMD_GUTC							{ helper.setCommand(CMD_GET_TIME_UTC); }
		| CMD_SET_TIME_UTC					{ helper.setCommand(CMD_SET_TIME_UTC); }
		| CMD_SUTC							{ helper.setCommand(CMD_SET_TIME_UTC); }
		| CMD_GET_MANUFACTURER_DESCRIPTION	{ helper.setCommand(CMD_GET_MANUFACTURER_DESCRIPTION); }
		| CMD_GMD							{ helper.setCommand(CMD_GET_MANUFACTURER_DESCRIPTION); }
		| CMD_GET_CURRENT_SOURCE			{ helper.setCommand(CMD_GET_CURRENT_SOURCE); }
		| CMD_GCS							{ helper.setCommand(CMD_GET_CURRENT_SOURCE); }
		| CMD_SET_CURRENT_SOURCE			{ helper.setCommand(CMD_SET_CURRENT_SOURCE); }
		| CMD_SCS							{ helper.setCommand(CMD_SET_CURRENT_SOURCE); }
		| CMD_GET_CURRENT_DATA_SELECTOR		{ helper.setCommand(CMD_GET_CURRENT_DATA_SELECTOR); }
		| CMD_GCDS							{ helper.setCommand(CMD_GET_CURRENT_DATA_SELECTOR); }
		| CMD_SET_CURRENT_DATA_SELECTOR		{ helper.setCommand(CMD_SET_CURRENT_DATA_SELECTOR); }
		| CMD_SCDS							{ helper.setCommand(CMD_SET_CURRENT_DATA_SELECTOR); }
		| CMD_REMOVE_SOURCES				{ helper.setCommand(CMD_REMOVE_SOURCES); }
		| CMD_RSRC							{ helper.setCommand(CMD_REMOVE_SOURCES); }
		| CMD_REMOVE_ALL_SOURCES			{ helper.setCommand(CMD_REMOVE_ALL_SOURCES); }
		| CMD_RASRC							{ helper.setCommand(CMD_REMOVE_ALL_SOURCES); }
		| CMD_GET_SOURCE					{ helper.setCommand(CMD_GET_SOURCE); }
		| CMD_GSRC							{ helper.setCommand(CMD_GET_SOURCE); }
		| CMD_GET_ALL_SOURCES				{ helper.setCommand(CMD_GET_ALL_SOURCES); }
		| CMD_GASRC							{ helper.setCommand(CMD_GET_ALL_SOURCES); }
		| CMD_REMOVE_DATA_SELECTORS			{ helper.setCommand(CMD_REMOVE_DATA_SELECTORS); }
		| CMD_RDS							{ helper.setCommand(CMD_REMOVE_DATA_SELECTORS); }
		| CMD_REMOVE_ALL_DATA_SELECTORS		{ helper.setCommand(CMD_REMOVE_ALL_DATA_SELECTORS); }
		| CMD_RADS							{ helper.setCommand(CMD_REMOVE_ALL_DATA_SELECTORS); }
		| CMD_GET_DATA_SELECTOR				{ helper.setCommand(CMD_GET_DATA_SELECTOR); }
		| CMD_GDS							{ helper.setCommand(CMD_DATA_SELECTOR); }
		| CMD_GET_ALL_DATA_SELECTORS		{ helper.setCommand(CMD_GET_ALL_DATA_SELECTORS); }
		| CMD_GADS							{ helper.setCommand(CMD_GET_ALL_DATA_SELECTORS); }
		| CMD_REMOVE_NOTIFICATION_CHANNELS	{ helper.setCommand(CMD_REMOVE_NOTIFICATION_CHANNELS); }
		| CMD_RNC							{ helper.setCommand(CMD_REMOVE_NOTIFICATION_CHANNELS); }
		| CMD_REMOVE_ALL_NOTIFICATION_CHANNELS	{ helper.setCommand(CMD_REMOVE_ALL_NOTIFICATION_CHANNELS); }
		| CMD_RANC							{ helper.setCommand(CMD_REMOVE_ALL_NOTIFICATION_CHANNELS); }
		| CMD_GET_NOTIFICATION_CHANNEL		{ helper.setCommand(CMD_GET_NOTIFICATION_CHANNEL); }
		| CMD_GNC							{ helper.setCommand(CMD_GET_NOTIFICATION_CHANNEL); }
		| CMD_GET_ALL_NOTIFICATION_CHANNEL	{ helper.setCommand(CMD_GET_ALL_NOTIFICATION_CHANNEL); }
		| CMD_GANC							{ helper.setCommand(CMD_GET_ALL_NOTIFICATION_CHANNEL); }
		| CMD_REMOVE_TRIGGERS				{ helper.setCommand(CMD_REMOVE_TRIGGERS); }
		| CMD_RTRG							{ helper.setCommand(CMD_REMOVE_TRIGGERS); }
		| CMD_REMOVE_ALL_TRIGGERS			{ helper.setCommand(CMD_REMOVE_ALL_TRIGGERS); }
		| CMD_RATRG							{ helper.setCommand(CMD_REMOVE_ALL_TRIGGERS); }
		| CMD_GET_TRIGGER					{ helper.setCommand(CMD_GET_TRIGGER); }
		| CMD_GTRG							{ helper.setCommand(CMD_GET_TRIGGER); }
		| CMD_GET_ALL_TRIGGERS				{ helper.setCommand(CMD_GET_ALL_TRIGGERS); }
		| CMD_GATRG							{ helper.setCommand(CMD_GET_ALL_TRIGGERS); }
		| CMD_REMOVE_TAG_SELECTORS			{ helper.setCommand(CMD_REMOVE_TAG_SELECTORS); }
		| CMD_RTS							{ helper.setCommand(CMD_REMOVE_TAG_SELECTORS); }
		| CMD_REMOVE_ALL_TAG_SELECTORS		{ helper.setCommand(CMD_REMOVE_ALL_TAG_SELECTORS); }
		| CMD_RATS							{ helper.setCommand(CMD_REMOVE_ALL_TAG_SELECTORS); }
		| CMD_GET_TAG_SELECTOR				{ helper.setCommand(CMD_GET_TAG_SELECTOR); }
		| CMD_GTS							{ helper.setCommand(CMD_GET_TAG_SELECTOR); }
		| CMD_GET_ALL_TAG_SELECTORS			{ helper.setCommand(CMD_GET_ALL_TAG_SELECTORS); }
		| CMD_GATS							{ helper.setCommand(CMD_GET_ALL_TAG_SELECTORS); }
		| CMD_REMOVE_TAG_FIELDS				{ helper.setCommand(CMD_REMOVE_TAG_FIELDS); }
		| CMD_RTF							{ helper.setCommand(CMD_REMOVE_TAG_FIELDS); }
		| CMD_REMOVE_ALL_TAG_FIELDS			{ helper.setCommand(CMD_REMOVE_ALL_TAG_FIELDS); }
		| CMD_RATF							{ helper.setCommand(CMD_REMOVE_ALL_TAG_FIELDS); }
		| CMD_GET_TAG_FIELD					{ helper.setCommand(CMD_GET_TAG_FIELD); }
		| CMD_GTF							{ helper.setCommand(CMD_GET_TAG_FIELD); }
		| CMD_GET_ALL_TAG_FIELDS			{ helper.setCommand(CMD_GET_ALL_TAG_FIELDS); }
		| CMD_RESET_TO_DEFAULT_SETTINGS		{ helper.setCommand(CMD_RESET_TO_DEFAULT_SETTINGS); }
		| CMD_RESET							{ helper.setCommand(CMD_RESET_TO_DEFAULT_SETTINGS); }
		| CMD_REBOOT						{ helper.setCommand(CMD_REBOOT); }
		| CMD_BOOT							{ helper.setCommand(CMD_REBOOT); }
		| CMD_GOODBYE						{ helper.setCommand(CMD_GOODBYE); }
		| CMD_BYE							{ helper.setCommand(CMD_GOODBYE); }
		| CMD_GET_READ_POINT				{ helper.setCommand(CMD_GET_READ_POINT); }
		| CMD_GRP							{ helper.setCommand(CMD_GET_READ_POINT); }
		| CMD_GET_ALL_READ_POINTS			{ helper.setCommand(CMD_GET_ALL_READ_POINTS); }
		| CMD_GARP							{ helper.setCommand(CMD_GET_ALL_READ_POINTS); }
		| CMD_IS_FIXED						{ helper.setCommand(CMD_IS_FIXED); }
		| CMD_ISFX							{ helper.setCommand(CMD_IS_FIXED); }
		| CMD_ADD_READ_POINTS 				{ helper.setCommand(CMD_ADD_READ_POINTS); }
		| CMD_ARP							{ helper.setCommand(CMD_ADD_READ_POINTS); }
		| CMD_REMOVE_READ_POINTS 			{ helper.setCommand(CMD_REMOVE_READ_POINTS); }
		| CMD_RRP							{ helper.setCommand(CMD_REMOVE_READ_POINTS); }
		| CMD_REMOVE_ALL_READ_POINTS		{ helper.setCommand(CMD_REMOVE_ALL_READ_POINTS); }
		| CMD_RARP							{ helper.setCommand(CMD_REMOVE_ALL_READ_POINTS); }
		| CMD_ADD_READ_TRIGGERS				{ helper.setCommand(CMD_ADD_READ_TRIGGERS); }
		| CMD_ART							{ helper.setCommand(CMD_ADD_READ_TRIGGERS); }
		| CMD_REMOVE_READ_TRIGGERS			{ helper.setCommand(CMD_REMOVE_READ_TRIGGERS); }
		| CMD_RRT							{ helper.setCommand(CMD_REMOVE_READ_TRIGGERS); }
		| CMD_REMOVE_ALL_READ_TRIGGERS		{ helper.setCommand(CMD_REMOVE_ALL_READ_TRIGGERS); }
		| CMD_RART							{ helper.setCommand(CMD_REMOVE_ALL_READ_TRIGGERS); }
		| CMD_GET_READ_TRIGGER				{ helper.setCommand(CMD_GET_READ_TRIGGER); }
		| CMD_GRT							{ helper.setCommand(CMD_GET_READ_TRIGGER); }
		| CMD_GET_ALL_READ_TRIGGERS			{ helper.setCommand(CMD_GET_ALL_READ_TRIGGERS); }
		| CMD_GART							{ helper.setCommand(CMD_GET_ALL_READ_TRIGGERS); }
		| CMD_ADD_TAG_SELECTORS				{ helper.setCommand(CMD_ADD_TAG_SELECTORS); }
		| CMD_ATS							{ helper.setCommand(CMD_ADD_TAG_SELECTORS); }
		| CMD_GET_GLIMPSED_TIMEOUT			{ helper.setCommand(CMD_GET_GLIMPSED_TIMEOUT); }
		| CMD_GGTO							{ helper.setCommand(CMD_GET_GLIMPSED_TIMEOUT); }
		| CMD_SET_GLIMPSED_TIMEOUT			{ helper.setCommand(CMD_SET_GLIMPSED_TIMEOUT); }
		| CMD_SGTO							{ helper.setCommand(CMD_SET_GLIMPSED_TIMEOUT); }
		| CMD_GET_OBSERVED_THRESHOLD		{ helper.setCommand(CMD_GET_OBSERVED_THRESHOLD); }
		| CMD_GOTH							{ helper.setCommand(CMD_GET_OBSERVED_THRESHOLD); }
		| CMD_SET_OBSERVED_THRESHOLD		{ helper.setCommand(CMD_SET_OBSERVED_THRESHOLD); }
		| CMD_SOTH							{ helper.setCommand(CMD_SET_OBSERVED_THRESHOLD); }
		| CMD_GET_OBSERVED_TIMEOUT			{ helper.setCommand(CMD_GET_OBSERVED_TIMEOUT); }
		| CMD_GOTO							{ helper.setCommand(CMD_GET_OBSERVED_TIMEOUT); }
		| CMD_SET_OBSERVED_TIMEOUT			{ helper.setCommand(CMD_SET_OBSERVED_TIMEOUT); }
		| CMD_SOTO							{ helper.setCommand(CMD_SET_OBSERVED_TIMEOUT); }
		| CMD_GET_LOST_TIMEOUT				{ helper.setCommand(CMD_GET_LOST_TIMEOUT); }
		| CMD_GLTO							{ helper.setCommand(CMD_GET_LOST_TIMEOUT); }
		| CMD_SET_LOST_TIMEOUT				{ helper.setCommand(CMD_SET_LOST_TIMEOUT); }
		| CMD_SLTO							{ helper.setCommand(CMD_SET_LOST_TIMEOUT); }
		| CMD_RAW_READ_IDS					{ helper.setCommand(CMD_RAW_READ_IDS); }
		| CMD_RRID							{ helper.setCommand(CMD_RAW_READ_IDS); }
		| CMD_READ_IDS						{ helper.setCommand(CMD_READ_IDS); }
		| CMD_RID							{ helper.setCommand(CMD_READ_IDS); }
		| CMD_READ							{ helper.setCommand(CMD_READ); }
		| CMD_R								{ helper.setCommand(CMD_READ); }
		| CMD_WRITE_ID						{ helper.setCommand(CMD_WRITE_ID); }
		| CMD_WID							{ helper.setCommand(CMD_WRITE_ID); }
		| CMD_WRITE							{ helper.setCommand(CMD_WRITE); }
		| CMD_W								{ helper.setCommand(CMD_WRITE); }
		| CMD_KILL							{ helper.setCommand(CMD_KILL); }
		| CMD_K								{ helper.setCommand(CMD_KILL); }
		| CMD_GET_READ_CYCLES_PER_TRIGGER	{ helper.setCommand(CMD_GET_READ_CYCLES_PER_TRIGGER); }
		| CMD_GRCPT							{ helper.setCommand(CMD_GET_READ_CYCLES_PER_TRIGGER); }
		| CMD_SET_READ_CYCLES_PER_TRIGGER	{ helper.setCommand(CMD_SET_READ_CYCLES_PER_TRIGGER); }
		| CMD_SRCPT							{ helper.setCommand(CMD_SET_READ_CYCLES_PER_TRIGGER); }
		| CMD_GET_MAX_READ_DUTY_CYCLE		{ helper.setCommand(CMD_GET_MAX_READ_DUTY_CYCLE); }
		| CMD_GMRDC							{ helper.setCommand(CMD_GET_MAX_READ_DUTY_CYCLE); }
		| CMD_SET_MAX_READ_DUTY_CYCLE		{ helper.setCommand(CMD_SET_MAX_READ_DUTY_CYCLE); }
		| CMD_SMRDC							{ helper.setCommand(CMD_SET_MAX_READ_DUTY_CYCLE); }
		| CMD_GET_READ_TIMEOUT				{ helper.setCommand(CMD_GET_READ_TIMEOUT); }
		| CMD_GRTO							{ helper.setCommand(CMD_GET_READ_TIMEOUT); }
		| CMD_SET_READ_TIMEOUT				{ helper.setCommand(CMD_SET_READ_TIMEOUT); }
		| CMD_SRTO							{ helper.setCommand(CMD_SET_READ_TIMEOUT); }
		| CMD_GET_SESSION					{ helper.setCommand(CMD_GET_SESSION); }
		| CMD_GSS							{ helper.setCommand(CMD_GET_SESSION); }
		| CMD_SET_SESSION					{ helper.setCommand(CMD_SET_SESSION); }
		| CMD_SSS							{ helper.setCommand(CMD_SET_SESSION); }
		| CMD_GET_MAX_NUMBER_SUPPORTED		{ helper.setCommand(CMD_GET_MAX_NUMBER_SUPPORTED); }
		| CMD_GMAX							{ helper.setCommand(CMD_GET_MAX_NUMBER_SUPPORTED); }
		| CMD_GMX							{ helper.setCommand(CMD_GET_MAX_NUMBER_SUPPORTED); }
		| CMD_GET_TYPE						{ helper.setCommand(CMD_GET_TYPE); }
		| CMD_GT							{ helper.setCommand(CMD_GET_TYPE); }
		| CMD_GET_VALUE						{ helper.setCommand(CMD_GET_VALUE); }
		| CMD_GV							{ helper.setCommand(CMD_GET_VALUE); }
		| CMD_FIRE							{ helper.setCommand(CMD_FIRE); }
		| CMD_F								{ helper.setCommand(CMD_FIRE); }
		| CMD_GET_MASK						{ helper.setCommand(CMD_GET_MASK); }
		| CMD_GM							{ helper.setCommand(CMD_GET_MASK); }
		| CMD_GET_INCLUSIVE_FLAG			{ helper.setCommand(CMD_GET_INCLUSIVE_FLAG); }
		| CMD_GIF							{ helper.setCommand(CMD_GET_INCLUSIVE_FLAG); }
		| CMD_GET_ADDRESS					{ helper.setCommand(CMD_GET_ADDRESS); }
		| CMD_GADR							{ helper.setCommand(CMD_GET_ADDRESS); }
		| CMD_GET_EFFECTIVE_ADDRESS			{ helper.setCommand(CMD_GET_EFFECTIVE_ADDRESS); }
		| CMD_GEADR							{ helper.setCommand(CMD_GET_EFFECTIVE_ADDRESS); }
		| CMD_SET_ADDRESS					{ helper.setCommand(CMD_SET_ADDRESS); }
		| CMD_SADR							{ helper.setCommand(CMD_SET_ADDRESS); }
		| CMD_SET_DATA_SELECTOR				{ helper.setCommand(CMD_SET_DATA_SELECTOR); }
		| CMD_SDS							{ helper.setCommand(CMD_SET_DATA_SELECTOR); }
		| CMD_ADD_SOURCES					{ helper.setCommand(CMD_ADD_SOURCES); }
		| CMD_ASRC							{ helper.setCommand(CMD_ADD_SOURCES); }
		| CMD_ADD_NOTIFICATIOIN_TRIGGERS	{ helper.setCommand(CMD_ADD_NOTIFICATION_TRIGGERS); }
		| CMD_ANT							{ helper.setCommand(CMD_ADD_NOTIFICATION_TRIGGERS); }
		| CMD_REMOVE_NOTIFICATION_TRIGGERS	{ helper.setCommand(CMD_REMOVE_NOTIFICATION_TRIGGERS); }
		| CMD_RNT							{ helper.setCommand(CMD_REMOVE_NOTIFICATION_TRIGGERS); }
		| CMD_REMOVE_ALL_NOTIFICATION_TRIGGERS { helper.setCommand(CMD_REMOVE_ALL_NOTIFICATION_TRIGGERS); }
		| CMD_RANT							{ helper.setCommand(CMD_REMOVE_ALL_NOTIFICATION_TRIGGERS); }
		| CMD_GET_NOTIFICATION_TRIGGER		{ helper.setCommand(CMD_GET_NOTIFICATION_TRIGGER); }
		| CMD_GNT							{ helper.setCommand(CMD_GET_NOTIFICATION_TRIGGER); }
		| CMD_GET_ALL_NOTIFICATION_TRIGGERS { helper.setCommand(CMD_GET_ALL_NOTIFICATION_TRIGGERS); }
		| CMD_GANT							{ helper.setCommand(CMD_GET_ALL_NOTIFICATION_TRIGGERS); }
		| CMD_READ_QUEUED_DATA				{ helper.setCommand(CMD_READ_QUEUED_DATA); }
		| CMD_RQD							{ helper.setCommand(CMD_READ_QUEUED_DATA); }
		| CMD_ADD_FIELD_NAMES				{ helper.setCommand(CMD_ADD_FIELD_NAMES); }
		| CMD_AFN							{ helper.setCommand(CMD_ADD_FIELD_NAMES); }
		| CMD_REMOVE_FIELD_NAMES			{ helper.setCommand(CMD_REMOVE_FIELD_NAMES); }
		| CMD_RFN							{ helper.setCommand(CMD_REMOVE_FIELD_NAMES); }
		| CMD_REMOVE_ALL_FIELD_NAMES		{ helper.setCommand(CMD_REMOVE_ALL_FIELD_NAMES); }
		| CMD_RAFN							{ helper.setCommand(CMD_REMOVE_ALL_FIELD_NAMES); }
		| CMD_GET_ALL_FIELD_NAMES			{ helper.setCommand(CMD_GET_ALL_FIELD_NAMES); }
		| CMD_GAFN							{ helper.setCommand(CMD_GET_ALL_FIELD_NAMES); }
		| CMD_ADD_EVENT_FILTERS				{ helper.setCommand(CMD_ADD_EVENT_FILTERS); }
		| CMD_AEF							{ helper.setCommand(CMD_ADD_EVENT_FILTERS); }
		| CMD_REMOVE_EVENT_FILTERS			{ helper.setCommand(CMD_REMOVE_EVENT_FILTERS); }
		| CMD_REF							{ helper.setCommand(CMD_REMOVE_EVENT_FILTERS); }
		| CMD_REMOVE_ALL_EVENT_FILTERS		{ helper.setCommand(CMD_REMOVE_ALL_EVENT_FILTERS); }
		| CMD_RAEF							{ helper.setCommand(CMD_REMOVE_ALL_EVENT_FILTERS); }
		| CMD_GET_ALL_EVENT_FILTERS			{ helper.setCommand(CMD_GET_ALL_EVENT_FILTERS); }
		| CMD_GAEF							{ helper.setCommand(CMD_GET_ALL_EVENT_FILTERS); }
		| CMD_ADD_TAG_FIELD_NAMES			{ helper.setCommand(CMD_ADD_TAG_FIELD_NAMES); }
		| CMD_ATFN							{ helper.setCommand(CMD_ADD_TAG_FIELD_NAMES); }
		| CMD_REMOVE_TAG_FIELD_NAMES		{ helper.setCommand(CMD_REMOVE_TAG_FIELD_NAMES); }
		| CMD_RTFN							{ helper.setCommand(CMD_REMOVE_TAG_FIELD_NAMES); }
		| CMD_REMOVE_ALL_TAG_FIELD_NAMES	{ helper.setCommand(CMD_REMOVE_ALL_TAG_FIELD_NAMES); }
		| CMD_RATFN							{ helper.setCommand(CMD_REMOVE_ALL_TAG_FIELD_NAMES); }
		| CMD_GET_ALL_TAG_FIELD_NAMES		{ helper.setCommand(CMD_GET_ALL_TAG_FIELD_NAMES); }
		| CMD_GATFN							{ helper.setCommand(CMD_GET_ALL_TAG_FIELD_NAMES); }
		| CMD_GET_SUPPORTED_TYPES			{ helper.setCommand(CMD_GET_SUPPORTED_TYPES); }
		| CMD_GST							{ helper.setCommand(CMD_GET_SUPPORTED_TYPES); }
		| CMD_GET_SUPPORTED_NAMES			{ helper.setCommand(CMD_GET_SUPPORTED_NAMES); }
		| CMD_GSN							{ helper.setCommand(CMD_GET_SUPPORTED_NAMES); }
		| CMD_GET_TAG_FIELD_NAME			{ helper.setCommand(CMD_GET_TAG_FIELD_NAME); }
		| CMD_GTFN							{ helper.setCommand(CMD_GET_TAG_FIELD_NAME); }
		| CMD_SET_TAG_FIELD_NAME			{ helper.setCommand(CMD_SET_TAG_FIELD_NAME); }
		| CMD_STFN							{ helper.setCommand(CMD_SET_TAG_FIELD_NAME); }
		| CMD_GET_MEMORY_BANK				{ helper.setCommand(CMD_GET_MEMORY_BANK); }
		| CMD_GMB							{ helper.setCommand(CMD_GET_MEMORY_BANK); }
		| CMD_SET_MEMORY_BANK				{ helper.setCommand(CMD_SET_MEMORY_BANK); }
		| CMD_SMB							{ helper.setCommand(CMD_SET_MEMORY_BANK); }
		| CMD_GET_OFFSET					{ helper.setCommand(CMD_GET_OFFSET); }
		| CMD_GOFF							{ helper.setCommand(CMD_GET_OFFSET); }
		| CMD_SET_OFFSET					{ helper.setCommand(CMD_SET_OFFSET); }
		| CMD_SOFF							{ helper.setCommand(CMD_SET_OFFSET); }
		| CMD_GET_LENGTH					{ helper.setCommand(CMD_GET_LENGTH); }
		| CMD_GLEN							{ helper.setCommand(CMD_GET_LENGTH); }
		| CMD_SET_LENGTH					{ helper.setCommand(CMD_SET_LENGTH); }
		| CMD_SLEN							{ helper.setCommand(CMD_SET_LENGTH); }
		
		)
	;
	
parameter_list
	{
		Parameter p = null;
	}	
	:	p=parameter
		{
			helper.writeParameter(p);
		}
	 ( COMMA p=parameter
	 	{
	 		helper.writeParameter(p);
	 	}
	  )*
	;

parameter returns [Parameter param]
		{
			Parameter valParam = null;
			ListParameter listParam = null;
			ListParameter workingList = null;
			PairParameter pairParam = null;
			param = null;
			String hex = null;
			
		}		
	:	( i:INT 
		{
			int r = Integer.parseInt(i.getText());
			valParam = new ValueParameter(r);
		}
		| s:IDENT (ASSIGN hex=hex_val)?
		{
			if (s != null && hex != null) {
				//it's a field-value-pair
				valParam = new PairParameter(s.getText(), hex);
			} else {
				//it's a normal ValueParameter
				valParam = new ValueParameter(s.getText());
			}
		}
		)	
		{
			if (helper.topState() == TextCommandParserHelper.STATE_LIST_PARAMETER) {
				//Add a parameter to the current working list
				try {
					workingList = helper.popList();
					workingList.addParameter(valParam);
					helper.pushList(workingList);
					param = workingList;
				} catch (TextCommandParserException e) {/* nothing to handle */}
				//TODO: Evtl. recognition exception werfen
			} else {
				param = valParam;
			}
		}
		| listParam=list_val 
		{
			if (helper.topState() == TextCommandParserHelper.STATE_LIST_PARAMETER) {
				//We have a nested list, add the current list to the (parent) working list
				try {
					workingList = helper.popList();
					workingList.addParameter(listParam);
					param = workingList;
				} catch (TextCommandParserException e) {/* nothing to handle */}
				//TODO: Evtl. recognition exception werfen
			} else {
				//Normal case, push list to the parameter stack
				param = listParam;
			}		
		}
		| valParam=keyword2ident
		{
			/* because a keyword has higher priority than an identifier, command_name is 
			   matched here mistakenly. Just handle it as a normal identifier. */
			if (helper.topState() == TextCommandParserHelper.STATE_LIST_PARAMETER) {
				//Add a parameter to the current working list
				try {
					workingList = helper.popList();
					workingList.addParameter(valParam);
					helper.pushList(workingList);
					param = workingList;
				} catch (TextCommandParserException e) {/* nothing to handle */}
				//TODO: Evtl. recognition exception werfen
			} else {
				param = valParam;
			}  
			   
		}
	;
	
keyword2ident returns [ValueParameter p] 
		{	p = null;	}
		: 
		{
			Token s = LT(1);
			p = new ValueParameter(s.getText());
		}
		( READERDEVICE | RD	| SOURCE | SRC | READPOINT | RP | TAGSELECTOR | RF | DATASELECTOR | DS | NOTIFICATIONCHANNEL | NC | TRIGGER | TRG | COMMANDCHANNEL | CC | EVENTTYPE | ET | TRIGGERTYPE | TT | FIELDNAME | FN  | IOPORTS	| IOP | TAGFIELD | TF
		| CMD_CREATE
		| CMD_C				
		| CMD_GETEPC		
		| CMD_GE			
		| CMD_GETMANUFACTURER	
		| CMD_GMAN				
		| CMD_GETMODEL		
		| CMD_GMOD			
		| CMD_GETHANDLE		
		| CMD_GH			
		| CMD_SETHANDLE		
		| CMD_SH			
		| CMD_GETNAME		
		| CMD_GN			
		| CMD_SETNAME		
		| CMD_SN			
		| CMD_GETROLE		
		| CMD_GR			
		| CMD_SETROLE		
		| CMD_SR			
		| CMD_GET_TIME_TICKS
		| CMD_GTIC			
		| CMD_GET_TIME_UTC	
		| CMD_GUTC			
		| CMD_SET_TIME_UTC	
		| CMD_SUTC			
		| CMD_GET_MANUFACTURER_DESCRIPTION	
		| CMD_GMD			
		| CMD_GET_CURRENT_SOURCE
		| CMD_GCS			
		| CMD_SET_CURRENT_SOURCE
		| CMD_SCS			
		| CMD_GET_CURRENT_DATA_SELECTOR	
		| CMD_GCDS			
		| CMD_SET_CURRENT_DATA_SELECTOR
		| CMD_SCDS			
		| CMD_REMOVE_SOURCES
		| CMD_RSRC			
		| CMD_REMOVE_ALL_SOURCES
		| CMD_RASRC			
		| CMD_GET_SOURCE	
		| CMD_GSRC			
		| CMD_GET_ALL_SOURCES
		| CMD_GASRC			
		| CMD_REMOVE_DATA_SELECTORS
		| CMD_RDS			
		| CMD_REMOVE_ALL_DATA_SELECTORS
		| CMD_RADS			
		| CMD_DATA_SELECTOR	
		| CMD_GDS			
		| CMD_GET_ALL_DATA_SELECTORS
		| CMD_GADS	
		| CMD_REMOVE_NOTIFICATION_CHANNELS	
		| CMD_RNC			
		| CMD_REMOVE_ALL_NOTIFICATION_CHANNELS
		| CMD_RANC			
		| CMD_GET_NOTIFICATION_CHANNEL
		| CMD_GNC			
		| CMD_GET_ALL_NOTIFICATION_CHANNEL		
		| CMD_GANC			
		| CMD_REMOVE_TRIGGERS
		| CMD_RTRG			
		| CMD_REMOVE_ALL_TRIGGERS
		| CMD_RATRG			
		| CMD_GET_TRIGGER	
		| CMD_GTRG			
		| CMD_GET_ALL_TRIGGERS
		| CMD_GATRG			
		| CMD_REMOVE_TAG_SELECTORS
		| CMD_RTS			
		| CMD_REMOVE_ALL_TAG_SELECTORS
		| CMD_RATS			
		| CMD_GET_TAG_SELECTOR
		| CMD_GTS			
		| CMD_GET_ALL_TAG_SELECTORS
		| CMD_GATS			
		| CMD_REMOVE_TAG_FIELDS
		| CMD_RTF			
		| CMD_REMOVE_ALL_TAG_FIELDS
		| CMD_RATF			
		| CMD_GET_TAG_FIELD	
		| CMD_GTF			
		| CMD_GET_ALL_TAG_FIELDS
		| CMD_RESET_TO_DEFAULT_SETTINGS
		| CMD_RESET		
		| CMD_REBOOT	
		| CMD_BOOT		
		| CMD_GOODBYE	
		| CMD_BYE		
		| CMD_GET_READ_POINT
		| CMD_GRP			
		| CMD_GET_ALL_READ_POINTS
		| CMD_GARP
		| CMD_IS_FIXED
		| CMD_ISFX
		| CMD_ADD_READ_POINTS
		| CMD_ARP
		| CMD_REMOVE_READ_POINTS
		| CMD_RRP
		| CMD_REMOVE_ALL_READ_POINTS
		| CMD_RARP
		| CMD_ADD_READ_TRIGGERS
		| CMD_ART
		| CMD_REMOVE_READ_TRIGGERS
		| CMD_RRT
		| CMD_REMOVE_ALL_READ_TRIGGERS
		| CMD_RART
		| CMD_GET_READ_TRIGGER
		| CMD_GRT
		| CMD_GET_ALL_READ_TRIGGERS
		| CMD_GART
		| CMD_ADD_TAG_SELECTORS
		| CMD_ATS
		| CMD_GET_GLIMPSED_TIMEOUT
		| CMD_GGTO
		| CMD_SET_GLIMPSED_TIMEOUT
		| CMD_SGTO
		| CMD_GET_OBSERVED_THRESHOLD
		| CMD_GOTH
		| CMD_SET_OBSERVED_THRESHOLD
		| CMD_SOTH
		| CMD_GET_OBSERVED_TIMEOUT
		| CMD_GOTO
		| CMD_SET_OBSERVED_TIMEOUT
		| CMD_SOTO
		| CMD_GET_LOST_TIMEOUT
		| CMD_GLTO
		| CMD_SET_LOST_TIMEOUT
		| CMD_SLTO
		| CMD_RAW_READ_IDS
		| CMD_RRID
		| CMD_READ_IDS
		| CMD_RID
		| CMD_READ
		| CMD_R
		| CMD_WRITE_ID
		| CMD_WID
		| CMD_WRITE
		| CMD_W
		| CMD_KILL
		| CMD_K
		| CMD_GET_READ_CYCLES_PER_TRIGGER
		| CMD_GRCPT
		| CMD_SET_READ_CYCLES_PER_TRIGGER
		| CMD_SRCPT
		| CMD_GET_MAX_READ_DUTY_CYCLE
		| CMD_GMRDC
		| CMD_SET_MAX_READ_DUTY_CYCLE
		| CMD_SMRDC
		| CMD_GET_READ_TIMEOUT
		| CMD_GRTO
		| CMD_SET_READ_TIMEOUT
		| CMD_SRTO
		| CMD_GET_SESSION
		| CMD_GSS
		| CMD_SET_SESSION
		| CMD_SSS
		| CMD_GET_MAX_NUMBER_SUPPORTED
		| CMD_GMAX
		| CMD_GET_TYPE
		| CMD_GT
		| CMD_GET_VALUE
		| CMD_GV
		| CMD_FIRE
		| CMD_F
		| CMD_GET_MASK
		| CMD_GM
		| CMD_GET_INCLUSIVE_FLAG
		| CMD_GIF
		| CMD_GET_ADDRESS
		| CMD_GADR
		| CMD_GET_EFFECTIVE_ADDRESS
		| CMD_GEADR
		| CMD_SET_ADDRESS
		| CMD_SADR
		| CMD_SET_DATA_SELECTOR
		| CMD_SDS
		| CMD_ADD_SOURCES
		| CMD_ASRC
		| CMD_ADD_NOTIFICATIOIN_TRIGGERS
		| CMD_ANT
		| CMD_REMOVE_NOTIFICATION_TRIGGERS
		| CMD_RNT
		| CMD_REMOVE_ALL_NOTIFICATION_TRIGGERS
		| CMD_RANT
		| CMD_GET_NOTIFICATION_TRIGGER
		| CMD_GNT
		| CMD_GET_ALL_NOTIFICATION_TRIGGERS
		| CMD_GANT
		| CMD_READ_QUEUED_DATA
		| CMD_RQD
		| CMD_ADD_FIELD_NAMES
		| CMD_AFN
		| CMD_REMOVE_FIELD_NAMES
		| CMD_RFN
		| CMD_REMOVE_ALL_FIELD_NAMES
		| CMD_RAFN
		| CMD_GET_ALL_FIELD_NAMES
		| CMD_GAFN
		| CMD_ADD_EVENT_FILTERS
		| CMD_AEF
		| CMD_REMOVE_EVENT_FILTERS
		| CMD_REF
		| CMD_REMOVE_ALL_EVENT_FILTERS
		| CMD_RAEF
		| CMD_GET_ALL_EVENT_FILTERS
		| CMD_GAEF
		| CMD_ADD_TAG_FIELD_NAMES
		| CMD_ATFN
		| CMD_REMOVE_TAG_FIELD_NAMES
		| CMD_RTFN
		| CMD_REMOVE_ALL_TAG_FIELD_NAMES
		| CMD_RATFN
		| CMD_GET_ALL_TAG_FIELD_NAMES
		| CMD_GATFN
		| CMD_GET_SUPPORTED_TYPES
		| CMD_GST
		| CMD_GET_SUPPORTED_NAMES
		| CMD_GSN
		| CMD_GET_TAG_FIELD_NAME
		| CMD_GTFN
		| CMD_SET_TAG_FIELD_NAME
		| CMD_STFN
		| CMD_GET_MEMORY_BANK
		| CMD_GMB
		| CMD_SET_MEMORY_BANK
		| CMD_SMB
		| CMD_GET_OFFSET
		| CMD_GOFF
		| CMD_SET_OFFSET
		| CMD_SOFF
		| CMD_GET_LENGTH
		| CMD_GLEN
		| CMD_SET_LENGTH
		| CMD_SLEN
					
		);
	
list_val returns [ListParameter listParam]
	{
		Parameter p = null;
		listParam = null;
		ListParameter workingList = null;
	}
	:	LBRACKET 
		{
			//Create a new list and push it to the list stack to use it as the working list
			workingList = new ListParameter();
			//workingList.addParameter(p);
			helper.pushList(workingList);
			helper.pushState(TextCommandParserHelper.STATE_LIST_PARAMETER);
		}
		(p=parameter ( COMMA parameter )* )? RBRACKET
		{
			//Remove the working list and return it
			try {
				workingList = helper.popList();
				helper.popState();
				listParam = workingList;
			} catch (TextCommandParserException e) { /* nothing to handle here */ }
			//TODO: Evtl. RecognitionException werfen	
		
		}
	;
	
dec_val returns [int r]
{
	r = 0;
}
	:	i:INT {  r = Integer.parseInt(i.getText()); }
	;

hex_val returns [String hex]
	{
		hex = null;
	}
	: 	s:IDENT 
		{
			hex = s.getText();
		}
		| i:INT
		{
			hex = i.getText();
		}
;

class TextLexer extends Lexer;
options {
	caseSensitiveLiterals  = false;
	k=2;
	}
WS	:	(' '
	|	'\t')
		{ _ttype = Token.SKIP; }
	;
	
LF
	:
		(	"\r\n"  // Evil DOS
		|	'\r'    // Macintosh
		|	'\n'    // Unix (the right way)
		)
		{ newline(); }
	;
	
SHARP: "#" ;

DOT  : "." ;

EXCLAMATION
     : "!" ;
     
COMMA: ',' ;

LBRACKET
	:	'{'
	;

RBRACKET
	:	'}'
	;

ASSIGN:	'='
	;
	
ESCAPE
	:	"\\"
	;

	
protected
DIGIT
	:	'0'..'9'
	;

INT	:	('1'..'9') ('0'..'9')*
	;
	


IDENT
	options {testLiterals=true;}
	:	('A'..'Z' | 'a'..'z') ('A'..'Z'|'a'..'z'|'0'..'9')*
	;
	

	
