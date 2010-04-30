/*
 * Copyright (C) 2008-2010 Loic Schmidt - INRIA
 * 
 * This file is part of AspireRFID.
 *
 * AspireRFID is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * AspireRFID is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with AspireRFID. If not, see
 * <http://www.gnu.org/licenses/>.
 */


package org.ow2.aspirerfid.tdt;

import java.util.*;
import java.lang.*;
import java.io.*;

import javax.xml.bind.MarshalException;
import javax.xml.bind.ValidationException;

import org.epcglobalinc.tdt.*;
import org.fosstrak.tdt.*;

/**
 * @author Loic Schmidt e-mail: loic.schmidt@lifl.fr
 *
 *	<p>This class provides methods for translating some codes (such as an electronic
 *	product code (EPC) or all GS1 codes) between various levels of representation
 *	including BINARY, TAG_ENCODING, PURE_IDENTITY, LEGACY and GS1_AI_ENCODING
 *	formats.  An additional output level ONS_HOSTNAME may be
 *	defined for some coding schemes. When the class AspireTDTEngine is
 *	constructed, the path to a local directory must be specified,
 *	by passing it as a single string parameter to the constructor
 *	method (without any trailing slash or file separator).  e.g.
 *      <pre><code>AspireTDTEngine engine = new AspireTDTEngine("/opt/TDT");</code></pre></p>
 *      
 *      <p>The specified directory must contain two subdirectories named
 *      auxiliary and schemes.  The Tag Data Translation definition files
 *      for the various coding schemes should be located inside the
 *      subdirectory called <code>schemes</code>. The Bar Code 
 *      information files for translation should be located inside the 
 *      subdirectory <code>schemes/barcode</code>. Any auxiliary lookup files
 *      (such as <code>ManagerTranslation.xml</code>) should be located inside the
 *      subdirectory called <code>auxiliary</code>.</p>
 *
 * @version Revision: 0.5 Date: 2009/11/23
 */

public class AspireTDTEngine {

    //--------------------------/
    //- Class/Member Variables -/
    //--------------------------/

	// FossTrack Engine and Output Level Type
	private TDTEngine EpcTdt;
	private HashMap<String, LevelTypeList> EPCLevelTypeList = new HashMap<String, LevelTypeList>();

	// For GS1 Standards
	private GS1TDTEngine Gs1Tdt;
	private LinkedList<String> GS1LevelTypeList = new LinkedList<String>();

	
    //---------------/
    //- Constructor -/
    //---------------/

    /**
     *	AspireTDTEngine - constructor for a new Tag Data Translation engine
     *	 
     *	@param path the string value of the path to a
     *	configuration directory consisting of two
     *	subdirectories, <code>schemes</code> and <code>auxiliary</code>.
     *
     *  <p>When the class TDTEngine is constructed, the path to a
     *  local directory must be specified, by passing it as a
     *  single string parameter to the constructor method
     *  (without any trailing slash or file separator).  e.g.
     *  <code><pre>AspireTDTEngine engine = new AspireTDTEngine("/opt/TDT");</pre></code></p>
     *  <p>The
     *  specified directory must contain two subdirectories named
     *  auxiliary and schemes.  The Tag Data Translation definition
     *  files for the various EPC coding schemes should be located
     *  inside the subdirectory called <code>schemes</code>. The Bar Code 
     *  information files for translation should be located inside the 
     *  subdirectory <code>schemes/barcode</code>. Any auxiliary
     *  lookup files (such as <code>ManagerTranslation.xml</code>) should be
     *  located inside the subdirectory called <code>auxiliary</code>.
     */
	@Deprecated
	public AspireTDTEngine(String path)
		throws IOException,javax.xml.bind.JAXBException,javax.xml.parsers.ParserConfigurationException, org.xml.sax.SAXException
	{
		// init fosstrack tdt engine
		this.EpcTdt= new TDTEngine(path);
		this.EPCLevelTypeList.put("BINARY",LevelTypeList.BINARY);
		this.EPCLevelTypeList.put("TAG_ENCODING",LevelTypeList.TAG_ENCODING);
		this.EPCLevelTypeList.put("PURE_IDENTITY",LevelTypeList.PURE_IDENTITY);
		this.EPCLevelTypeList.put("LEGACY",LevelTypeList.LEGACY);
		this.EPCLevelTypeList.put("ONS_HOSTNAME",LevelTypeList.ONS_HOSTNAME);

		// init the gs1 tdt engine
		this.Gs1Tdt= new GS1TDTEngine(path);
		this.GS1LevelTypeList.add("GS1_AI_ENCODING");
		this.GS1LevelTypeList.add("LEGACY");
	}
	
	public AspireTDTEngine()
		throws IOException,javax.xml.bind.JAXBException,org.xml.sax.SAXException,javax.xml.parsers.ParserConfigurationException
	{
		// init fosstrack tdt engine
		this.EpcTdt= new TDTEngine();
		this.EPCLevelTypeList.put("BINARY",LevelTypeList.BINARY);
		this.EPCLevelTypeList.put("TAG_ENCODING",LevelTypeList.TAG_ENCODING);
		this.EPCLevelTypeList.put("PURE_IDENTITY",LevelTypeList.PURE_IDENTITY);
		this.EPCLevelTypeList.put("LEGACY",LevelTypeList.LEGACY);
		this.EPCLevelTypeList.put("ONS_HOSTNAME",LevelTypeList.ONS_HOSTNAME);

		// init the gs1 tdt engine
		this.Gs1Tdt= new GS1TDTEngine();
		this.GS1LevelTypeList.add("GS1_AI_ENCODING");
		this.GS1LevelTypeList.add("LEGACY");
	}



	
    //-----------/
    //- Methods -/
    //-----------/

    /** 
     *	Translates the input string of a specified input format to a
     *	specified output representation of the same coding scheme.  For
     *	example, the input string value may be a tag-encoding URI and
     *	the outbound level specified in the inputParameters by string outboundlevel
     *	may be BINARY, in which case the return value is a binary
     *	representation expressed as a string.
     * 
     *
     *  @param input input tag or bar code coding
     *  @param inputParameters a map with any additional properties.
     *  @return output tag or bar code coding
     */
	public String convert(String input, Map<String,String> inputParameters) throws TDTException {

		if (!inputParameters.containsKey("dataType")) throw new TDTException("No data type in parameters");
		if (!inputParameters.containsKey("outputFormat")) throw new TDTException("No output format in parameters");

		String inputData=input;
		String outputData=input;
		String dataType=inputParameters.get("dataType");
		String outputFormat=inputParameters.get("outputFormat");

		if (dataType.equalsIgnoreCase("GS1")) {
			// check the input LeveleType
			boolean inputIsGs1AIEncoding=(input.indexOf("]")==0);
			boolean inputIsLegacy=(input.indexOf("=")!=-1);

			// if output format is GS1_AI_ENCODING
			if (outputFormat.equalsIgnoreCase("GS1_AI_ENCODING")) {
				if(!inputIsGs1AIEncoding && !inputIsLegacy) inputData=this.EpcTdt.convert(inputData, inputParameters,LevelTypeList.LEGACY);
				outputData=this.Gs1Tdt.convert(inputData, inputParameters);
			// else if output format is EPC TDT format
			} else if (this.EPCLevelTypeList.containsKey(outputFormat.toUpperCase())) {
				if(inputIsGs1AIEncoding) {
				  inputParameters.put("outputFormat","LEGACY");
				  outputData=this.Gs1Tdt.convert(inputData, inputParameters);
				  if(!outputFormat.equalsIgnoreCase("LEGACY")) outputData=this.EpcTdt.convert(outputData, inputParameters,EPCLevelTypeList.get(outputFormat));
				} else {
				  outputData=this.EpcTdt.convert(outputData, inputParameters,EPCLevelTypeList.get(outputFormat));
				}
			}
		// ISO 15693 or 14443
		} else if (dataType.equalsIgnoreCase("ISO"))  {
			outputData=ISOTDTEngine.convert(inputData, inputParameters);
		} else if (dataType.equalsIgnoreCase("phonenumber")) {
			outputData=PhoneNumberTDTEngine.convert(inputData, inputParameters);
		} else if (dataType.equalsIgnoreCase("GSMA")) {
			outputData=GSMATDTEngine.convert(inputData, inputParameters);
		} else if (dataType.equalsIgnoreCase("onewire")) {
			outputData=OneWireTDTEngine.convert(inputData, inputParameters);
		} else {
			// error : no data type specified
			throw new TDTException("Invalid data type in parameters");
		}
		return outputData;


	}
}
