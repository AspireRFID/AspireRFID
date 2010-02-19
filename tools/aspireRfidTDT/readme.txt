HOW TO USE Aspire TDTEngine
---------------------------

AspireTDTEngine engine = new AspireTDTEngine(); // directories containing the subdirectories 'schemes' and 'auxiliary' are now in the jar file

HashMap<String,String> extraparams = new HashMap<String, String>(); // a HashMap providing extra parameters needed in addition to the input value


		/** MANDATORY PARAMETERS FOR TDT **/
		extraparams.put("dataType","gs1"); // permitted values are 'gs1', 'ISO' or 'phone' (not case sensitive) (ISO is for ISO 15693, ISO 14443, NFC and MAC address)
		extraparams.put("outputFormat","LEGACY");
		// permitted values are :
		// for EPC Tags and GS1 Bar Code (EPC TDS Compliant)* : 'LEGACY', 'BINARY', 'TAG_ENCODING', 'PURE_IDENTITY', 'ONS_HOSTNAME' and 'GS1_AI_ENCODING'
		// for GS1 Bar Code (not EPC TDS Compliant) :'LEGACY' and 'GS1_AI_ENCODING'
		// for ISO Tags, NFC and MAC addess: 'HEXA', 'LEGACY', 'BINARY', 'TAG_ENCODING', 'PURE_IDENTITY', 'ONS_HOSTNAME'
		// for Phone number with country prefix: 'DECIMAL', 'LEGACY', 'BINARY', 'TAG_ENCODING', 'PURE_IDENTITY', 'ONS_HOSTNAME'


		/** PARAMETERS FOR EPC Tags TDT **/
		/* These parameters are mandatory for inbound levels 'PURE_IDENTITY', 'LEGACY' or 'GS1_AI_ENCODING', */
		/* and if outputFormat=GS1_AI_ENCODING */
		extraparams.put("taglength","96"); // the taglength must be specified as "64" or "96"
		extraparams.put("filter","1"); // the filter value must be specified - range depends on coding scheme.
		extraparams.put("companyprefixlength","6"); // the companyprefixlength (length of the EAN.UCC Company Prefix) must be specified for GS1 coding schemes


		/** PARAMETERS FOR GS1 Bar Code TDT **/
		/* These parameters are mandatory if outputFormat=GS1_AI_ENCODING */
		extraparams.put("gs1symbol","]e0"); // GS1 Bar Code Type (permitted values are ']E0', ']E1', ']E2', ']E3', ']E4', ']I1', ']C1', ']e0' or  ']d2')
		extraparams.put("codelength","12"); // size of the bar code (mandatory with gs1symbol values ]E0 an ]E3), permitted values are 12 (for UPC-A), 13 (for EAN-13) or 8 (for UPC-E)

		/** PARAMETERS FOR PHONE NUMBER TDT **/
		extraparams.put("countryprefixlength","96"); // the countryprefixlength must be specified as "1", "2" or "3"