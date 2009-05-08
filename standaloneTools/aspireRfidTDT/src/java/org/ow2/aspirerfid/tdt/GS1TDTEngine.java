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
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.fosstrak.tdt.TDTException;

/**
 *
 *	<p>This class provides methods for translating GS1 System codes
 *      between two levels of representation: LEGACY and GS1_AI_ENCODING.
 *	When the class GS1TDTEngine is constructed, the path to a local
 *      directory must be specified, by passing it as a single string
 *      parameter to the constructor method (without any trailing slash
 *      or file separator). e.g. <pre><code>GS1TDTEngine engine = new GS1TDTEngine("/opt/TDT");</code></pre></p>
 *      
 *      <p>The specified directory must contain one subdirectory named
 *      schemes/barcode. The Tag Data Translation definition
 *      files for the various GS1 application indentifiers should be located
 *      inside the subdirectory called <code>schemes/barcode</code>.
 *
 * @version Revision: 0.1 Date: 2008/09/22
 */

public class GS1TDTEngine {

    //--------------------------/
    //- Class/Member Variables -/
    //--------------------------/


    /** 
     * GS1ApplicationIdentifiers is a map of application identifer
     * to GS1ApplicationIdentifier.
     */
	private HashMap<String,GS1ApplicationIdentifier> GS1ApplicationIdentifiers= new HashMap<String,GS1ApplicationIdentifier>();
    /** 
     * GS1DataTitle is a map of data title to application identifer
     */
	private HashMap<String,String> GS1DataTitle= new HashMap<String,String>();

    /** 
     * GS1ApplicationIdentifier is an object with
     * useful information about one GS1 application identifier
     * such as the content, the data title, some lengths, etc...
     */
	private class GS1ApplicationIdentifier {
		private String dataContent;
		private String dataTitle;
		private int AILength;
		private LinkedList<Integer> fixedSizeFields = new LinkedList<Integer>();
		private int variableSizeField;
		private boolean FNC1Required;
	
		private GS1ApplicationIdentifier(String dataContent, String dataTitle, int aiLength, LinkedList<Integer> fixedLengths, int variableLength, boolean FNC1Required){
			this.dataContent=dataContent;
			this.dataTitle=dataTitle;
			this.AILength=aiLength;
			this.fixedSizeFields=fixedLengths;
			this.variableSizeField=variableLength;
			this.FNC1Required=FNC1Required;
		}
		private String getDataContent(){return this.dataContent;}	
		private String getDataTitle(){return this.dataTitle;}
		private int getAILength() {return this.AILength;}
		private LinkedList<Integer> getFixedLengths() {return this.fixedSizeFields;}
		private int getFixedLength() {
			int size=0;
			for (int i=0;i<this.fixedSizeFields.size();i++) size+=this.fixedSizeFields.get(i).intValue();
			return size;
		}
		private int getVariableSizeField(){return this.variableSizeField;}
		private boolean isFNC1Required(){return this.FNC1Required;}
	}

    //---------------/
    //- Constructor -/
    //---------------/

    /**
     *	GS1TDTEngine - constructor for a new GS1 translation engine
     *	 
     *	@param confdir the string value of the path to a
     *	configuration directory consisting of one
     *	subdirectories, <code>schemes/barcode</code>.
     *
     *  <p>When the class GS1TDTEngine is constructed, the path to a
     *  local directory must be specified, by passing it as a
     *  single string parameter to the constructor method
     *  (without any trailing slash or file separator).  e.g.
     *  <code><pre>GS1TDTEngine engine = new GS1TDTEngine("/opt/TDT");</pre></code></p>
     *  <p>The specified directory must contain one subdirectory named
     *  schemes/barcode. The Tag Data Translation definition
     *  files for the various GS1 application indentifiers should be located
     *  inside the subdirectory called <code>schemes/barcode</code>.
     */
	public GS1TDTEngine (String confdir)
		 throws FileNotFoundException,
			ParserConfigurationException,
			SAXException,
			IOException,
			TDTException
	{
		
		// lit le fichier GS1AI.xml pour trouver les infos de GS1Spec sur les AI, les Data Content et les length
                DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();                    
		DocumentBuilder db=fabrique.newDocumentBuilder();
		Document xml=db.parse(confdir+"/schemes/barcode/GS1ApplicationIdentifiers.xml");
		NodeList nd=xml.getElementsByTagName("AI");
		for (int i=0; i<nd.getLength(); i++) {
			NodeList ndchildren=nd.item(i).getChildNodes();
			// traitement de chaque AI
			String id=new String();
			String dataContent=new String();
			String dataTitle=new String("");
			int aiLength=-1;
			int variableLength=-1;
			boolean fnc1Required=false;
			LinkedList<Integer> fixedLengths = new LinkedList<Integer>();

			for (int j=0;j<ndchildren.getLength();j++) {
				if (ndchildren.item(j).getNodeName().equalsIgnoreCase("id")) id=ndchildren.item(j).getTextContent();
				if (ndchildren.item(j).getNodeName().equalsIgnoreCase("datacontent")) dataContent=ndchildren.item(j).getTextContent();
				if (ndchildren.item(j).getNodeName().equalsIgnoreCase("datatitle")) dataTitle=ndchildren.item(j).getTextContent();
				if (ndchildren.item(j).getNodeName().equalsIgnoreCase("ailength")) aiLength=Integer.parseInt(ndchildren.item(j).getTextContent());
				if (ndchildren.item(j).getNodeName().equalsIgnoreCase("fixedlength")) fixedLengths.add(Integer.parseInt(ndchildren.item(j).getTextContent()));
				if (ndchildren.item(j).getNodeName().equalsIgnoreCase("variablelength")) variableLength=Integer.parseInt(ndchildren.item(j).getTextContent());
				if (ndchildren.item(j).getNodeName().equalsIgnoreCase("fnc1")) fnc1Required=(Integer.parseInt(ndchildren.item(j).getTextContent())==1);
			}
			this.GS1ApplicationIdentifiers.put(id,new GS1ApplicationIdentifier(dataContent,dataTitle,aiLength,fixedLengths,variableLength,fnc1Required));
			this.GS1DataTitle.put(dataTitle,id);
		}
	}

    //-----------/
    //- Methods -/
    //-----------/

    /** 
     *	Translates the input string to a specified output format
     *  (LEGACY or GS1_AI_ENCODING). For example, the input
     *  string value may be a GS1_AI_ENCODING format and
     *	the output format specified in the inputParameters by string
     *  'outputFormat' may be LEGACY, in which case
     *  the return value is a legacy representation expressed as a string.
     * 
     *
     *  @param input input tag or bar code coding
     *  @param inputParameters a map with any additional properties.
     *  @return output tag or bar code coding
     */
	public String convert(String input, Map<String,String> inputParameters) throws TDTException {
		// on test le input format (GS1_AI_ENCODING or LEGACY)
		// si il y a une "]" dans le input, alors on a du GS1_AI_ENCODING, sinon, on a du LEGACY
		boolean inputIsGs1AIEncoding=(input.indexOf("]")==0);

		// si on est en GS1, on va parser le code pour trouver le symbole et donc le type de code bar lu
		String symbol=new String();
		String inputData=input;
		String outputData=new String();
		
		if(inputIsGs1AIEncoding) { // c'est du GS1_AI_ENCODING, le symbole=les trois premiers caracteres du input
			symbol=input.substring(0,3);
			inputData=input.substring(3);
		} else { // c'est pas du GS1_AI_ENCODING, le symbole est dans les inputParameters
			symbol=inputParameters.get("gs1symbol");
		}

		// on appel ensuite la bonne methode pour la translation
		if (inputParameters.get("outputFormat").compareTo("LEGACY")==0) {
			if (!inputIsGs1AIEncoding) outputData=inputData;
			if (inputIsGs1AIEncoding) outputData=this.convertGS1AIEncodingToLegacy(symbol,inputData,inputParameters);
		} else if (inputParameters.get("outputFormat").compareTo("GS1_AI_ENCODING")==0) {
			if (inputIsGs1AIEncoding) outputData=this.convertGS1AIEncodingToGS1AIEncoding(symbol, inputData,inputParameters);
			if (!inputIsGs1AIEncoding) outputData=this.convertLegacyToGS1AIEncoding(symbol, inputData,inputParameters);
		} else {
			String input2=inputData;
			if (inputIsGs1AIEncoding) input2=this.convertGS1AIEncodingToLegacy(symbol, inputData,inputParameters);
			
		}
		return outputData;
	}

    /** 
     *  The convertLegacyToGS1AIEncoding method aim is to convert a
     *  Legacy input to GS1_AI_ENCODING.
     */
	private String convertLegacyToGS1AIEncoding(String symbol, String input, Map<String,String> inputParameters) {

		String outputData=new String();
		// first: checking the symbol
		if (symbol.compareTo("]E0")==0) { //EAN-13, UPC-A or UPC-E
			String encodedGTIN=new String();
			String[] infos=input.split("[;]");
			for (int i=0;i<infos.length;i++) {
				if (!infos[i].isEmpty()) {
					String[] info=infos[i].split("[=]");
					if(info[0].equalsIgnoreCase("gtin")) encodedGTIN=info[1];
				}
			}
			if(encodedGTIN.isEmpty())  throw new TDTException("Invalid input for ]E0 Symbol");
			outputData=this.convertE0L2G(encodedGTIN, Integer.parseInt(inputParameters.get("codelength")));
		} else if (symbol.compareTo("]E1")==0) { // Two-digit Add-On Symbol
			if(input.length()!=2) throw new TDTException("Invalid input size for Two-Digit Add-On Symbol");
			outputData="]E1"+input;
		} else if (symbol.compareTo("]E2")==0) { // Five-digit Add-On Symbol
			if(input.length()!=5) throw new TDTException("Invalid input size for Five-Digit Add-On Symbol");
			outputData="]E2"+input;
		} else if (symbol.compareTo("]E3")==0) { // EAN-13, UPC-A or UPC-E with Add-On Symbol
			// removing Add-On symbol
			String encodedGTIN=new String();
			String addon=new String();
			String[] infos=input.split("[;]");
			for (int i=0;i<infos.length;i++) {
				if (!infos[i].isEmpty()) {
					String[] info=infos[i].split("[=]");
					if(info[0].equalsIgnoreCase("gtin")) encodedGTIN=info[1];
					if(info[0].equalsIgnoreCase("additional_id")) addon=info[1];
				}
			}
			if(encodedGTIN.isEmpty())  throw new TDTException("Invalid input for ]E3 Symbol");
			outputData=this.convertE0L2G(encodedGTIN, Integer.parseInt(inputParameters.get("codelength")));
			outputData+=addon;
		} else if (symbol.compareTo("]E4")==0) { // EAN-8
			String[] infos=input.split("[;]");
			for (int i=0;i<infos.length;i++) {
				if (!infos[i].isEmpty()) {
					String[] info=infos[i].split("[=]");
					if(info[0].equalsIgnoreCase("gtin")) {
						if((info[1].length()>8) && (Integer.parseInt(info[1].substring(0,info[1].length()-8))!=0))
								throw new TDTException("GTIN size is not valid");
						outputData="]E4"+info[1].substring(info[1].length()-8);
					}
				}
			}
		} else if (symbol.compareTo("]I1")==0) { // ITF-14
			String[] infos=input.split("[;]");
			for (int i=0;i<infos.length;i++) {
				if (!infos[i].isEmpty()) {
					String[] info=infos[i].split("[=]");
					if(info[0].equalsIgnoreCase("gtin")) {
						outputData="]I1"+info[1];
					}
				}
			}
		} else if ((symbol.compareTo("]C1")==0) || (symbol.compareTo("]e0")==0) || (symbol.compareTo("]d2")==0)) { 
			// GS1-128 or GS1 DataBar or Data Matrix (All can carry the Application Identifiers)

			String[] infos=input.split("[;]");
			String result= new String();
			boolean isSGLN=false;
			for (int i=0;i<infos.length;i++) {
				if (!infos[i].isEmpty()) {
					String[] info=infos[i].split("[=]");
					// is the DataTitle known ?
					if ((isSGLN) && (info[0].equalsIgnoreCase("serial"))) info[0]="gln_extension";
					if (this.GS1DataTitle.containsKey(info[0].toUpperCase())) {
						GS1ApplicationIdentifier GS1AI=this.GS1ApplicationIdentifiers.get(this.GS1DataTitle.get(info[0].toUpperCase()));
						// an AI has been found
						// checking the length of the code with the AI fixed lengths, variable length and FNC1 required
						int borneInf=GS1AI.getFixedLength();
						int borneSup=borneInf;
						if (GS1AI.getVariableSizeField()!=-1) borneSup+=GS1AI.getVariableSizeField();
						if(borneInf>info[1].length() || borneSup<info[1].length())
								throw new TDTException(GS1AI.getDataTitle()+" size not valid");

						// test for "serial" and "gln_extension" AI(254)
						result+=this.GS1DataTitle.get(info[0].toUpperCase())+info[1];
						if(GS1AI.isFNC1Required() && (i!=(infos.length-1))) result+='\u001D';
						if(info[0].equalsIgnoreCase("gln")) isSGLN=true;
					} else {
						throw new TDTException("Unknown Application Identifier : BarCode not GS1Compliant");
					}
				}
			}
			outputData=symbol+result;
		} else {
			throw new TDTException("Unknown Symbology Identifier : Bar Code not GS1Compliant");
		}
		return outputData;		
	}


    /** 
     *  The convertGS1AIEncodingToLegacy method aim is to convert a
     *  GS1_AI_ENCODING input to a LEGACY representation.
     *  If the input GS1 code is also EPC compliant, then 
     *  the LEGACY output format is EPC TDS compliant.
     */

	private String convertGS1AIEncodingToLegacy(String symbol, String input, Map<String,String> inputParameters) {
		String outputData=new String();
		// first: GS1 symbol checking
		if (symbol.compareTo("]E0")==0) { //EAN-13, UPC-A or UPC-E
			outputData=this.convertE0G2L(input);
		} else if (symbol.compareTo("]E1")==0) { // Two-digit Add-On Symbol
			if(input.length()!=2) throw new TDTException("Invalid input size for Two-Digit Add-On Symbol");
			outputData="additional_id="+input;
		} else if (symbol.compareTo("]E2")==0) { // Five-digit Add-On Symbol
			if(input.length()!=5) throw new TDTException("Invalid input size for Five-Digit Add-On Symbol");
			outputData="additional_id="+input;
		} else if (symbol.compareTo("]E3")==0) { // EAN-13, UPC-A or UPC-E with Add-On Symbol
			String addon=new String();
			int index=0;
			// input with Five-digit Add-On Symbol
			if(input.length()==13 || input.length()==17 || input.length()==18) index=5;
			// input with Two-digit Add-On Symbol
			else if(input.length()==10 || input.length()==14 || input.length()==15) index=2;
			// Error : size not compatible
			else throw new TDTException("Illegal GTIN size : Bar Code not GS1Compliant");
			// remove the addon
			addon=input.substring(input.length()-index);
			// convert like ]E0
			outputData=this.convertE0G2L(input.substring(0,input.length()-index));
			// add the addon
			outputData+=";additional_id="+addon;
		} else if (symbol.compareTo("]E4")==0) { // EAN-8
			// gtin 14 translation
			outputData="gtin=000000"+input;
		} else if (symbol.compareTo("]I1")==0) { // ITF-14
			// ITF-14 can carry all GTIN representation but always have a 14 digits GTIN
			outputData="gtin="+input;
		} else if ((symbol.compareTo("]C1")==0) || (symbol.compareTo("]e0")==0) || (symbol.compareTo("]d2")==0)) { 
			// GS1-128 or GS1 DataBar or Data Matrix (All can carry the Application Identifiers)
			// we go through the input to find AI
			String inputData=input.replaceAll("[()]","");
			HashMap<String,String> result=new HashMap<String,String>();
			boolean isGLN=false;
			boolean isGLNEXT=false;
			while (!inputData.isEmpty()) {
				int i=0;
				// looking for an AI
				while (i<inputData.length() && !this.GS1ApplicationIdentifiers.containsKey(inputData.substring(0,i))) i++;
				if (i==inputData.length()) throw new TDTException("Unknown Application Identifier : Bar Code not GS1Compliant");
				// an AI has been found
				GS1ApplicationIdentifier AI=this.GS1ApplicationIdentifiers.get(inputData.substring(0,i));
				
				String AINum=inputData.substring(0,AI.getAILength());
				if (!isGLN) isGLN=(AINum.compareTo("414")==0);
				if (!isGLNEXT) isGLNEXT=(AINum.compareTo("254")==0);
				inputData=inputData.substring(AI.getAILength());
				String number=new String();
				// we can now check the value and its length
				if(!AI.isFNC1Required()) {
					if(AI.getFixedLength()>inputData.length())
						throw new TDTException("Illegal size for value of ("+AINum+") : Bar Code not GS1Compliant");
					number=inputData.substring(0,AI.getFixedLength());
					inputData=inputData.substring(AI.getFixedLength());
				} else {
					// two cases: first, it is not the last, so it is ending with a char <GS>
					// second, it is the last AI, so maybe a <GS>, maybe not
					int index=inputData.indexOf('\u001D');
					if(index!=-1) {
						number=inputData.substring(0,index);
						inputData=inputData.substring(index+1);
					} else {
						number=inputData;
						inputData="";
					}
					int infBound=AI.getFixedLength();
					int supBound=infBound+AI.getVariableSizeField();
					if(number.length()>supBound && number.length()<infBound)
						throw new TDTException("Illegal size for value of ("+AINum+") : Bar Code not GS1Compliant");
				}
				result.put(AINum,number);				
			}
			// sorting the Map and processing the output
			Set<String> tmp_keys=result.keySet();
			LinkedList<String> keys=new LinkedList<String>(tmp_keys);
			if(isGLN && isGLNEXT) Collections.sort(keys,Collections.reverseOrder());
			else Collections.sort(keys);
			Iterator it=keys.iterator();
			while (it.hasNext()) {
				String key=(String) it.next();
				if(!outputData.isEmpty()) outputData+=";";
				String title=this.GS1ApplicationIdentifiers.get(key).getDataTitle().toLowerCase();
				if(title.compareTo("gln_extension")==0) title="serial";
				outputData+=title+"="+result.get(key);
			}
		} else {
			throw new TDTException("Unknown Symbology Identifier : Bar Code not GS1Compliant");
		}
		return outputData;
	}

    /** 
     *  The convertGS1AIEncodingToGS1AIEncoding method aim is to convert a
     *  GS1_AI_ENCODING input to an other GS1AIEncoding representation.
     *  e.g. an input may be for GS1-128 bar code (symbol ]e0) and the
     *  output needed is for EAN-13 (symbol ]E0 or ]E3, so only GTIN)
     */
	private String convertGS1AIEncodingToGS1AIEncoding(String symbol, String input, Map<String,String> inputParameters) {
		String outputSymbol=inputParameters.get("gs1symbol");
		String outputData=input;
		if(outputSymbol.compareTo(symbol)!=0) {
			outputData=this.convertGS1AIEncodingToLegacy(symbol,input,inputParameters);
			outputData=this.convertLegacyToGS1AIEncoding(outputSymbol,outputData,inputParameters);
		}
		return outputData;
	}

	/***************************************************
		TRANSLATE METHODS FOR EAN/UPC
			   (]E0 et ]E3)
	****************************************************/

	private String convertE0G2L(String input) {
		String inputData=input;
		String outputData=new String("gtin=");
		if(input.length()==8) {
			// transformation du code zero-suppress en gtin-12
			int X6=Integer.parseInt(input.substring(6,7));
			//inputData=inputData.substring(1);
			switch (X6) {
				case 0:
					inputData="0"+inputData.charAt(1)+inputData.charAt(2)+"00000"+inputData.charAt(3)+inputData.charAt(4)+inputData.charAt(5);
				break;
				case 1:
					inputData="0"+inputData.charAt(1)+inputData.charAt(2)+"10000"+inputData.charAt(3)+inputData.charAt(4)+inputData.charAt(5);
				break;
				case 2:
					inputData="0"+inputData.charAt(1)+inputData.charAt(2)+"20000"+inputData.charAt(3)+inputData.charAt(4)+inputData.charAt(5);
				break;
				case 3:
					inputData="0"+inputData.charAt(1)+inputData.charAt(2)+inputData.charAt(3)+"00000"+inputData.charAt(4)+inputData.charAt(5);
				break;
				case 4:
					inputData="0"+inputData.charAt(1)+inputData.charAt(2)+inputData.charAt(3)+inputData.charAt(4)+"00000"+inputData.charAt(5);
				break;
				case 5:
					inputData="0"+inputData.charAt(1)+inputData.charAt(2)+inputData.charAt(3)+inputData.charAt(4)+inputData.charAt(5)+"00005";
				break;
				case 6:
					inputData="0"+inputData.charAt(1)+inputData.charAt(2)+inputData.charAt(3)+inputData.charAt(4)+inputData.charAt(5)+"00006";
				break;
				case 7:
					inputData="0"+inputData.charAt(1)+inputData.charAt(2)+inputData.charAt(3)+inputData.charAt(4)+inputData.charAt(5)+"00007";
				break;
				case 8:
					inputData="0"+inputData.charAt(1)+inputData.charAt(2)+inputData.charAt(3)+inputData.charAt(4)+inputData.charAt(5)+"00008";
				break;
				case 9:
					inputData="0"+inputData.charAt(1)+inputData.charAt(2)+inputData.charAt(3)+inputData.charAt(4)+inputData.charAt(5)+"00009";
				break;
			}
			inputData+=getCheckDigit(inputData);
		}
		// gtin 14
		outputData="gtin=";
		for (int i=0;i<14-inputData.length();i++) outputData+="0";
		outputData+=inputData;
		return outputData;
	}

	private String convertE0L2G(String encodedGTIN, int gtinsize) {
		// on fait un traitement de GTIN-14 vers GTIN-8, 12 et 13
		switch (gtinsize) {
			case 8:
				// on converti le GTIN-14 en GTIN-12
				if(encodedGTIN.substring(0,2).compareTo("00")!=0)
					throw new TDTException("GTIN size is not valid");
				encodedGTIN=encodedGTIN.substring(2);
				if ((encodedGTIN.charAt(10)=='5' || encodedGTIN.charAt(10)=='6' || encodedGTIN.charAt(10)=='7' || encodedGTIN.charAt(10)=='8' || encodedGTIN.charAt(10)=='9') && (encodedGTIN.substring(6,10).compareTo("0000")==0) && (encodedGTIN.charAt(5)!='0')) {
					encodedGTIN="0"+encodedGTIN.charAt(1)+encodedGTIN.charAt(2)+encodedGTIN.charAt(3)+encodedGTIN.charAt(4)+encodedGTIN.charAt(5)+encodedGTIN.charAt(10);
				} else if ((encodedGTIN.substring(5,10).compareTo("00000")==0) && encodedGTIN.charAt(4)!='0'){
					encodedGTIN="0"+encodedGTIN.charAt(1)+encodedGTIN.charAt(2)+encodedGTIN.charAt(3)+encodedGTIN.charAt(4)+encodedGTIN.charAt(10)+"4";
				} else if ((encodedGTIN.substring(4,8).compareTo("0000")==0) && (encodedGTIN.charAt(3)=='0' || encodedGTIN.charAt(3)=='1' || encodedGTIN.charAt(3)=='2')){
					encodedGTIN="0"+encodedGTIN.charAt(1)+encodedGTIN.charAt(2)+encodedGTIN.charAt(8)+encodedGTIN.charAt(9)+encodedGTIN.charAt(10)+encodedGTIN.charAt(3);
				} else if ((encodedGTIN.substring(4,9).compareTo("00000")==0) && (encodedGTIN.charAt(3)=='3' || encodedGTIN.charAt(3)=='4' || encodedGTIN.charAt(3)=='5' || encodedGTIN.charAt(3)=='6' || encodedGTIN.charAt(3)=='7' || encodedGTIN.charAt(3)=='8' || encodedGTIN.charAt(3)=='9')){
					encodedGTIN="0"+encodedGTIN.charAt(1)+encodedGTIN.charAt(2)+encodedGTIN.charAt(3)+encodedGTIN.charAt(9)+encodedGTIN.charAt(10)+"3";
				} else {
					throw new TDTException("GTIN-14 is not GTIN-8 convertible");
				}
				encodedGTIN+=this.getCheckDigit(encodedGTIN);
			break;
			case 12:
				if(encodedGTIN.substring(0,2).compareTo("00")!=0)
					throw new TDTException("GTIN size is not valid");
				encodedGTIN=encodedGTIN.substring(2);
			break;
			case 13:
				if(encodedGTIN.substring(0,1).compareTo("0")!=0)
					throw new TDTException("GTIN size is not valid");
				encodedGTIN=encodedGTIN.substring(1);
			break;
			default:
				throw new TDTException("Invalid bar code size in parameter (codelength)");
		}
		return "]E0"+encodedGTIN;
	}

	/***************************************************
		CALCUL OR CHECK METHOD FOR THE
			CHECK DIGIT
	****************************************************/

	private String getCheckDigit(String input) {
		// the input must be WITHOUT check digit
		int length=input.length()-1;
		int checkDigit=0;
		for (int i=0; i<input.length();i++) {
			int mult=1;
			if ((i%2)==0) mult=3;
			checkDigit+=mult*Integer.parseInt(Character.toString(input.charAt(length-i)));
		}
		int modulo=checkDigit%10;
		if((checkDigit-(modulo*10))!=0) modulo++;
		checkDigit=(modulo*10)-checkDigit;
		return (new Integer(checkDigit)).toString();		
	}
	
	private boolean isCheckDigitOk(String input) {
		// remove the check digit and compare it with
		// the result of method getCheckDigit(input without checkdigit)
		return (input.substring(0,1).compareTo(this.getCheckDigit(input.substring(0,input.length()-1)))==0);
	}



	/***************************************************
		METHOD FOR PRINT CONTENT OF THE TWO
			HASHMAP ATTRIBUTS
	****************************************************/
	private void printAIs() {
		Set<String> s=this.GS1ApplicationIdentifiers.keySet();
		Iterator it=s.iterator();
		while (it.hasNext()) {
			String key=(String) it.next();
			System.out.print(key+" - "+this.GS1ApplicationIdentifiers.get(key).getDataContent()+" - "+this.GS1ApplicationIdentifiers.get(key).getAILength()+" - ");
			for (int k=0;k<this.GS1ApplicationIdentifiers.get(key).getFixedLengths().size();k++) {
				System.out.print(this.GS1ApplicationIdentifiers.get(key).getFixedLengths().get(k).toString()+" - ");
			}
			System.out.print(this.GS1ApplicationIdentifiers.get(key).getVariableSizeField()+" - "+this.GS1ApplicationIdentifiers.get(key).isFNC1Required());
			System.out.println();
		}
		System.out.println(GS1ApplicationIdentifiers.size());
	}

	private void printDataTitles() {
		Set<String> s=this.GS1DataTitle.keySet();
		Iterator it=s.iterator();
		while (it.hasNext()) {
			String key=(String) it.next();
			System.out.println(key+" - "+this.GS1DataTitle.get(key));
		}
	}
}
