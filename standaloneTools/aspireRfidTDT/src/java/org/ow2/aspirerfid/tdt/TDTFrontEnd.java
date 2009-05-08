/*
 * Copyright (c) 2008-2010, Aspire
 * 
 * Aspire is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 2.1 as published by
 * the Free Software Foundation (the "LGPL").
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library in the file COPYING-LGPL-2.1; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public License
 * for the specific language governing rights and limitations.
 */

package org.ow2.aspirerfid.tdt;

import java.util.*;


public class TDTFrontEnd { // demonstration front-end application which makes use of the TDT package

    public static final void main(String args[]) {
	try {
	    AspireTDTEngine engine = new AspireTDTEngine("."); // path to directory containing the subdirectories 'schemes' and 'auxiliary'


	    HashMap<String,String> extraparams = new HashMap<String, String>(); // a HashMap providing extra parameters needed in addition to the input value
		
	    extraparams.put("dataType","gs1"); // permitted values are 'gs1', 'ISO' and 'uCode' (not case sensitive)
	    extraparams.put("outputFormat","LEGACY"); // permitted values are 'BINARY', 'TAG_ENCODING', 'PURE_IDENTITY', 'LEGACY' and 'ONS_HOSTNAME' OR 'GS1_AI_ENCODING'

		/** PARAMETERS FOR EPC Tags TDT **/
	   extraparams.put("taglength","64"); // for inbound levels 'PURE_IDENTITY' or 'LEGACY', the taglength must be specified as "64" or "96"
	   extraparams.put("filter","1"); // for inbound levels 'PURE_IDENTITY' or 'LEGACY', the filter value must be specified - range depends on coding scheme.
	   extraparams.put("companyprefixlength","7"); // for inbound levels 'PURE_IDENTITY' or 'LEGACY', the companyprefixlength (length of the EAN.UCC Company Prefix) must be specified for GS1 coding schemes
		/** PARAMETERS FOR GS1 Bar Code TDT **/
	   extraparams.put("gs1symbol","]d2"); // for outputFormat 'GS1_AI_ENCODING', the GS1 Bar Code Type must be specified (permitted values are ']E0', ']E1', ']E2', ']E3', ']E4', ']I1', ']C1', ']e0' or  ']d2'
	   extraparams.put("codelength","12"); // size of the bar code (mandatory with gs1symbol values ]E0 an ]E4), permitted values are 12 (for UPC-A), 13 (for EAN-13) or 8 (for EAN-8)


	    HashMap<String,String> extraparams2 = new HashMap<String, String>();		
	    extraparams2.put("dataType","gs1");
	    extraparams2.put("outputFormat","GS1_AI_ENCODING");
	   extraparams2.put("taglength","64");
	   extraparams2.put("filter","1");
	   extraparams2.put("companyprefixlength","7");
	   extraparams2.put("codelength","8");
	   extraparams2.put("gs1symbol","]E3");

	    HashMap<String,String> extraparams3 = new HashMap<String, String>();		
	    extraparams3.put("dataType","gs1");
	    extraparams3.put("outputFormat","LEGACY");
	   extraparams3.put("taglength","64");
	   extraparams3.put("filter","1");
	   extraparams3.put("companyprefixlength","7");
	   extraparams3.put("codelength","8");
	   extraparams3.put("gs1symbol","]E3");




		String inbound;
		String outbound;
//		String inbound = "(01)95012345678903(21)00041000";
/*
gtin=00037000302414;serial=1041970










*/


//	    inbound = "gtin=00037000302414;serial=00041000";
//	    inbound = "1000100000000000001000001110110001000011111111111111111111111110";
//		inbound = "urn:epc:id:sgtin:00370001.23456.101";
//		inbound = "1010100000000000001000001110110001000010000011111110011000110010";
//		inbound = "001100000101010000000010010000100010000000011101100010000100000000000000000011111110011000110010";
//		inbound = "001100000101010000000010010000100010000000011101100010000100000000000000000011111110011000110010";
//		inbound = "cageordodaac=1D381;serial=16522293";//		inbound = "cageordodaac=2S194;serial=12345678901";
//		String inhex = "2F02032533139342DFDC1C35";
/*
		inbound="]e0(00)000370003024147856";
		System.out.println("Input string = "+inbound);	
		outbound = engine.convert(inbound, extraparams3);
		System.out.println("Output = "+outbound);
		inbound=outbound;
		System.out.println("Input string = "+inbound);	
		outbound = engine.convert(inbound, extraparams2);
		System.out.println("Output = "+outbound);
		
		inbound="gtin=00037000302414;serial=1041970";
		System.out.println("Input string = "+inbound);	
		outbound = engine.convert(inbound, extraparams3);
		System.out.println("Output = "+outbound);

		inbound=outbound;
		System.out.println("Input string = "+inbound);	
		outbound = engine.convert(inbound, extraparams2);
		System.out.println("Output = "+outbound);

		inbound=outbound;
		System.out.println("Input string = "+inbound);	
		outbound = engine.convert(inbound, extraparams3);
		System.out.println("Output = "+outbound);

		inbound=outbound;
		System.out.println("Input string = "+inbound);	
		outbound = engine.convert(inbound, extraparams);
		System.out.println("Output = "+outbound);

		inbound="urn:epc:tag:sscc-64:1.0037000.0302414785";
		System.out.println("Input string = "+inbound);	
		outbound = engine.convert(inbound, extraparams);
		System.out.println("Output = "+outbound);

		inbound="urn:epc:tag:sscc-64:1.0037000.0302414785";
		System.out.println("Input string = "+inbound);	
		outbound = engine.convert(inbound, extraparams3);
		System.out.println("Output = "+outbound);

		inbound=outbound;
		System.out.println("Input string = "+inbound);	
		outbound = engine.convert(inbound, extraparams2);
		System.out.println("Output = "+outbound);

		inbound=outbound;
		System.out.println("Input string = "+inbound);	
		outbound = engine.convert(inbound, extraparams);
		System.out.println("Output = "+outbound);

		inbound="]e0(00)000370003024147856";
		System.out.println("Input string = "+inbound);	
		outbound = engine.convert(inbound, extraparams);
		System.out.println("Output = "+outbound);
*/
		inbound="]e0(254)1041970"+'\u001D'+"(414)0003700030241";
		System.out.println("Input string = "+inbound);	
		outbound = engine.convert(inbound, extraparams);
		System.out.println("Output = "+outbound);
/*
		inbound="]e0(21)1041970"+'\u001D'+"(01)00037000302414";
		System.out.println("Input string = "+inbound);	
		outbound = engine.convert(inbound, extraparams);
		System.out.println("Output = "+outbound);

		inbound="(8003)00037000302414274877906943";
		System.out.println("Input string = "+inbound);	
		outbound = engine.convert(inbound, extraparams);
		System.out.println("Output = "+outbound);

		inbound="(8004)00370003024149267890123";
		System.out.println("Input string = "+inbound);	
		outbound = engine.convert(inbound, extraparams);
		System.out.println("Output = "+outbound);
		

		inbound="(01)00037000302414(21)1041970";
		outbound = engine.convert(inbound, extraparams2);
		System.out.println("Input string = "+inbound);	
		System.out.println("Output = "+outbound);

		inbound="(00)000370003024147856";
		outbound = engine.convert(inbound, extraparams2);
		System.out.println("Input string = "+inbound);	
		System.out.println("Output = "+outbound);

		inbound="(414)0003700030241(254)1041970";
		outbound = engine.convert(inbound, extraparams2);
		System.out.println("Input string = "+inbound);	
		System.out.println("Output = "+outbound);

		inbound="(8003)00037000302414274877906943";
		outbound = engine.convert(inbound, extraparams2);
		System.out.println("Input string = "+inbound);	
		System.out.println("Output = "+outbound);

		inbound="(8004)00370003024149267890123";
		outbound = engine.convert(inbound, extraparams2);
		System.out.println("Input string = "+inbound);	
		System.out.println("Output = "+outbound);*/


	}
	catch (Exception e) { 
	    e.printStackTrace(System.out);
	}
    }
	
}
