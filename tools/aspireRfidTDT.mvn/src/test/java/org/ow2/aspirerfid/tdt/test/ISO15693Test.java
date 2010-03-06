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
 * @author Didier Donsez
 *
 */
public class ISO15693Test extends TestCase {

	private AspireTDTEngine engine;
	private HashMap<String,String> extraparams;
	private String input="E062000000019705";
	
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
		
	}
	protected  void tearDown() {
		
	}
	
	public void testLEGACY() {
       HashMap<String,String> extraparams = new HashMap<String, String>();
       extraparams.put("dataType","iso");
       extraparams.put("outputFormat","LEGACY");
       assertEquals("iso15693;mfgcode=98;serial=104197",engine.convert(input, extraparams));
     }

	public void testBINARY() {
	       HashMap<String,String> extraparams = new HashMap<String, String>();
	       extraparams.put("dataType","iso");
	       extraparams.put("outputFormat","BINARY");
	       assertEquals("1110000001100010000000000000000000000000000000011001011100000101",engine.convert(input, extraparams));
	     }
	
	public void testTAG_ENCODING() {
	       HashMap<String,String> extraparams = new HashMap<String, String>();
	       extraparams.put("dataType","iso");
	       extraparams.put("outputFormat","TAG_ENCODING");
	       assertEquals("urn:iso:tag:15693-64:98.104197",engine.convert(input, extraparams));
	}
	

	public void testPURE_IDENTITY() {
	       HashMap<String,String> extraparams = new HashMap<String, String>();
	       extraparams.put("dataType","iso");
	       extraparams.put("outputFormat","PURE_IDENTITY");
	       assertEquals("urn:iso:id:15693:98.104197",engine.convert(input, extraparams));
	}
	
	public void testONS_HOSTNAME() {
	       HashMap<String,String> extraparams = new HashMap<String, String>();
	       extraparams.put("dataType","iso");
	       extraparams.put("outputFormat","ONS_HOSTNAME");
	       assertEquals("104197.98.15693.onsiso.com",engine.convert(input, extraparams));
	}	
}
