HOW TO USE Aspire TDTEngine
---------------------------

AspireTDTEngine engine = new AspireTDTEngine("."); // path to directory containing the subdirectories 'schemes' and 'auxiliary'

HashMap<String,String> extraparams = new HashMap<String, String>(); // a HashMap providing extra parameters needed in addition to the input value
		
extraparams.put("dataType","gs1"); // permitted values are 'gs1', 'ISO' and 'uCode' (not case sensitive) (ISO and uCode not yet implemented)
extraparams.put("outputFormat","LEGACY"); // permitted values are 'BINARY', 'TAG_ENCODING', 'PURE_IDENTITY', 'LEGACY' and 'ONS_HOSTNAME' OR 'GS1_AI_ENCODING'

/** PARAMETERS FOR EPC OUTPUT FORMAT **/
extraparams.put("taglength","64"); // for inbound levels 'PURE_IDENTITY' or 'LEGACY', the taglength must be specified as "64" or "96"
extraparams.put("filter","1"); // for inbound levels 'PURE_IDENTITY' or 'LEGACY', the filter value must be specified - range depends on coding scheme.
extraparams.put("companyprefixlength","7"); // for inbound levels 'PURE_IDENTITY' or 'LEGACY', the companyprefixlength (length of the EAN.UCC Company Prefix) must be specified for GS1 coding schemes

/** PARAMETERS FOR GS1 OUTPUT FORMAT **/
extraparams.put("gs1symbol","]d2"); // for outputFormat 'GS1_AI_ENCODING', the GS1 Bar Code Type must be specified (permitted values are ']E0', ']E1', ']E2', ']E3', ']E4', ']I1', ']C1', ']e0' or  ']d2')
extraparams.put("codelength","12"); // size of the bar code (mandatory with gs1symbol values ]E0 an ]E4), permitted values are 12 (for UPC-A), 13 (for EAN-13) or 8 (for EAN-8)
