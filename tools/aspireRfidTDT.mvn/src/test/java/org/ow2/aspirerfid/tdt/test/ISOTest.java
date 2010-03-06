/**
 * 
 */
package org.ow2.aspirerfid.tdt.test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import junit.framework.TestCase;

import org.ow2.aspirerfid.tdt.AspireTDTEngine;
import org.xml.sax.SAXException;

/**
 * @author donsez
 *
 */
public class ISOTest extends TestCase {

	AspireTDTEngine engine;
	HashMap<String,String> extraparams;
	
	protected  void setUp(){

		try {
			engine = new AspireTDTEngine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // path to directory containing the subdirectories 'schemes' and 'auxiliary'

		extraparams = new HashMap<String, String>(); // a HashMap providing extra parameters needed in addition to the input value

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
		
	}
	protected  void tearDown() {
		
	}
	
	public void testConvertISO15693() {
       HashMap<String,String> extraparams = new HashMap<String, String>();
       extraparams.put("dataType","iso");
       extraparams.put("outputFormat","LEGACY");
       assertEquals("iso15693;mfgcode=98;serial=104197",engine.convert("E062000000019705", extraparams));
     }

	public void testConvertISO14443() {
        assertEquals("iso14443double;mfgcode=08;serial=00197","iso14443double;mfgcode=08;serial=00197");
     }

	public void testConvertOUI() {
        assertEquals("oui=08;address=197","oui=08;address=197");
     }

	public void testConvertMAC() {
        assertEquals("00:1f:3b:bf:6a:e9","00:1f:3b:bf:6a:e9");
     }

	
	
/*
Here is a small example for ISO 15693 translation :

       AspireTDTEngine engine = new AspireTDTEngine();
       HashMap<String,String> extraparams = new HashMap<String, String>();
       extraparams.put("dataType","iso");
       extraparams.put("outputFormat","LEGACY");
       String input = new String("E062000000019705");
       String output = engine.convert("E062000000019705", extraparams);
       System.out.println("Input in HEXA : "+input);
       System.out.println(" -> to LEGACY        : "+output);

And here is the result of the example:

       Input in HEXA : E062000000019705
         -> to LEGACY        : iso15693;mfgcode=98;serial=104197

And other representation of ISO 15693 ID:
    -> to BINARY        : 1110000001100010000000000000000000000000000000011001011100000101
    -> to TAG_ENCODING  : urn:iso:tag:15693-64:98.104197
    -> to PURE_IDENTITY : urn:iso:id:15693:98.104197
    -> to ONS_HOSTNAME  : 104197.98.15693.onsiso.com  */
	
	
	
	
}
