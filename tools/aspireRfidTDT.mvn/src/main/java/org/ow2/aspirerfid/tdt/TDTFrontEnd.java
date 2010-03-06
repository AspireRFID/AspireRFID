
package org.ow2.aspirerfid.tdt;

import java.math.BigInteger;
import java.util.HashMap;

public class TDTFrontEnd { // demonstration front-end application which makes use of the TDT package

    public static final void main(String args[]) {
	try {

		AspireTDTEngine engine = new AspireTDTEngine(); // path to directory containing the subdirectories 'schemes' and 'auxiliary'

		HashMap<String,String> extraparams = new HashMap<String, String>(); // a HashMap providing extra parameters needed in addition to the input value

		/** MANDATORY PARAMETERS FOR TDT **/
		extraparams.put("dataType","gs1"); // permitted values are 'gs1', 'ISO' or 'phone' (not case sensitive)
		extraparams.put("outputFormat","LEGACY");
		// permitted values are :
		// for EPC Tags and GS1 Bar Code (EPC TDS Compliant)* : 'LEGACY', 'BINARY', 'TAG_ENCODING', 'PURE_IDENTITY', 'ONS_HOSTNAME' and 'GS1_AI_ENCODING'
		// for GS1 Bar Code (not EPC TDS Compliant) :'LEGACY' and 'GS1_AI_ENCODING'
		// for ISO Tags :'HEXA', 'LEGACY', 'BINARY', 'TAG_ENCODING', 'PURE_IDENTITY', 'ONS_HOSTNAME'


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


		
//	        String inbound = "gtin=00037000302414;serial=33554431";
//		String inbound = "gtin=03583782350398;serial=00000001";
//		String inbound = "urn:epc:id:sgtin:00370001.23456.101";
//		String inbound = "1010100000000000001000001110110001000010000011111110011000110010";
	//	String inbound = "001100000101010000000010010000100010000000011101100010000100000000000000000011111110011000110010";
//		String inbound = "001100000101010000000010010000100010000000011101100010000100000000000000000011111110011000110010";
//		String inbound = "cageordodaac=1D381;serial=16522293";
//		String inbound = "cageordodaac=2S194;serial=12345678901";
//		String inbound = "1100000011100101011101111110101000000011100101100010000000000000000000000000000000000000000000";
		extraparams.put("outputFormat","BINARY");

//		extraparams.put("outputFormat","BINARY");

/*		String outbound = engine.convert(inbound, extraparams);
		System.out.println("Input string " + inbound);
		System.out.println("Output is "+outbound);
*/
	//	String inbound="gtin=03583781545313;serial=00000001";
		String inbound="00"+(new BigInteger("30395DFA804042C000000003",16)).toString(2);
		System.out.println("Input string " + inbound);
	//	inbound="001100000011100101011101111110101000000011100101100001111100000000000000000000000000000000000001";
	//	System.out.println("Input string " + inbound);



		String outbound = engine.convert(inbound, extraparams);
		System.out.println(" as BINARY:           "+outbound);
		BigInteger test=new BigInteger(outbound,2);
      System.out.println(" as HEX:              "+test.toString(16));

      extraparams.put("outputFormat","TAG_ENCODING");
      String o3 = engine.convert(outbound, extraparams);
      System.out.println(" as TAG_ENCODING:     " + o3);

		extraparams.put("outputFormat","PURE_IDENTITY");
		o3 = engine.convert(outbound, extraparams);
		System.out.println(" as PURE_IDENTITY:    " + o3);

		extraparams.put("outputFormat","LEGACY");
		String o4 = engine.convert(outbound, extraparams);
		System.out.println(" as LEGACY:           " + o4);

		extraparams.put("outputFormat","ONS_HOSTNAME");
		String o5 = engine.convert(outbound, extraparams);
		System.out.println(" as ONS_HOSTNAME:     " + o5);

		extraparams.put("outputFormat","GS1_AI_ENCODING");
		String o6 = engine.convert(outbound, extraparams);
		System.out.println(" as GS1_AI_ENCODING:  " + o6);



//		extraparams.put("outputFormat","LEGACY");
//		
		/*TDTEngine EpcTdt= new TDTEngine();
		HashMap<String,String> inputParameters = new HashMap<String, String>();
		String outbound=EpcTdt.convert(inbound, inputParameters,LevelTypeList.LEGACY);

		//String outbound = engine.convert(inbound, extraparams);
		System.out.println(" as LEGACY      :  " + outbound);
		outbound=EpcTdt.convert(inbound, inputParameters, LevelTypeList.TAG_ENCODING);
		System.out.println(" as TAG_ENCODING:  " + outbound);*/


		System.out.println(" ===> TEST HF");		
		testISO("iso15693;mfgcode=98;serial=00104197",engine);
		testISO("iso14443single;uid0=08;serial=00197",engine);
		testISO("iso14443double;mfgcode=08;serial=00197",engine);
		testISO("iso14443triple;mfgcode=08;serial=00197",engine);
		testISO("oui=08;address=197",engine);
		testISO("00:1f:3b:bf:6a:e9",engine);


		
		System.out.println(" ===> TEST PN");
		testPN("+33112345678",engine);
	}
	catch (Exception e) { 
	    e.printStackTrace(System.out);
	}
    }
   
    private static String testISO(String input, AspireTDTEngine engine){

	System.out.println("Input in LEGACY : "+input);

	HashMap<String,String> extraparams = new HashMap<String, String>();

	extraparams.put("dataType","iso");
	extraparams.put("outputFormat","HEXA");
	String output = engine.convert(input, extraparams);
	System.out.println(" -> to HEXA          : "+output);
	extraparams.put("outputFormat","LEGACY");
	output = engine.convert(output, extraparams);
	System.out.println(" -> to LEGACY        : "+output);
	extraparams.put("outputFormat","BINARY");
	output = engine.convert(output, extraparams);
	System.out.println(" -> to BINARY        : "+output);
	extraparams.put("outputFormat","TAG_ENCODING");
	output = engine.convert(output, extraparams);
	System.out.println(" -> to TAG_ENCODING  : "+output);
	extraparams.put("outputFormat","PURE_IDENTITY");
	output = engine.convert(output, extraparams);
	System.out.println(" -> to PURE_IDENTITY : "+output);
	extraparams.put("outputFormat","ONS_HOSTNAME");
	output = engine.convert(output, extraparams);
	System.out.println(" -> to ONS_HOSTNAME  : "+output);

	return input;
    }

    private static String testPN(String input, AspireTDTEngine engine){

		System.out.println("Input in LEGACY : "+input);

		HashMap<String,String> extraparams = new HashMap<String, String>();

		extraparams.put("dataType","phonenumber");
		extraparams.put("countryprefixlength","2");
		extraparams.put("outputFormat","LEGACY");
		String output = engine.convert(input, extraparams);
		System.out.println(" -> to LEGACY        : "+output);
		extraparams.put("outputFormat","DECIMAL");
		output = engine.convert(output, extraparams);
		System.out.println(" -> to DECIMAL       : "+output);
		extraparams.put("outputFormat","BINARY");
		output = engine.convert(output, extraparams);
		System.out.println(" -> to BINARY        : "+output);
		extraparams.put("outputFormat","TAG_ENCODING");
		output = engine.convert(output, extraparams);
		System.out.println(" -> to TAG_ENCODING  : "+output);
		extraparams.put("outputFormat","PURE_IDENTITY");
		output = engine.convert(output, extraparams);
		System.out.println(" -> to PURE_IDENTITY : "+output);
		extraparams.put("outputFormat","ONS_HOSTNAME");
		output = engine.convert(output, extraparams);
		System.out.println(" -> to ONS_HOSTNAME  : "+output);

	return input;
    }

}
