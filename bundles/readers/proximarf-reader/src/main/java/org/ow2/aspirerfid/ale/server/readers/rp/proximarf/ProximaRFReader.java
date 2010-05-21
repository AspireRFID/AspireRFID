/*
   Copyright 2005-2010, OW2 Aspire RFID project 
   
   This library is free software; you can redistribute it and/or modify it 
   under the terms of the GNU Lesser General Public License as published by 
   the Free Software Foundation (the "LGPL"); either version 2.1 of the 
   License, or (at your option) any later version. If you do not alter this 
   notice, a recipient may use your version of this file under either the 
   LGPL version 2.1, or (at his option) any later version.
   
   You should have received a copy of the GNU Lesser General Public License 
   along with this library; if not, write to the Free Software Foundation, 
   Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
   
   This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
   KIND, either express or implied. See the GNU Lesser General Public 
   License for the specific language governing rights and limitations.

   Contact: OW2 Aspire RFID project <X AT Y DOT org> (with X=aspirerfid and Y=ow2)

   LGPL version 2.1 full text http://www.gnu.org/licenses/lgpl-2.1.txt    
 */
package org.ow2.aspirerfid.ale.server.readers.rp.proximarf;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.ow2.aspirerfid.ale.server.Tag;
import org.ow2.aspirerfid.ale.server.readers.rp.base.AbstractAdaptor;
import org.ow2.aspirerfid.ale.server.readers.rp.base.AbstractReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 
 * @author Gabriel Pedraza Ferreira
 *
 */
public class ProximaRFReader extends AbstractReader {

	
	static {
	    System.loadLibrary("RFID_Core");
	}
	
	private Map<String, String> settings; 
	
	public ProximaRFReader(AbstractAdaptor adaptor, Map<String, String> properties) {
		super(adaptor, properties);
	}

	@Override
	protected void closeReader() {
		String command = "<RFID><command>Close</command></RFID>";
		executeCommand(command);
		setState(STATE_NO_READER);
	}

	@Override
	protected void openReader() {
		String port = settings.get("SerialPort");
						
		String command = "<RFID><command>Initialize</command><device>AP2</device><port>$SerialPort</port><baud>57600</baud> "
		      + "<parity>0</parity><stop-bits>0</stop-bits><flow-control>none</flow-control> "
		      + "<manufacturer-code></manufacturer-code><memory-addressing-bits>16</memory-addressing-bits></RFID> ";
		command = replace(command, "$SerialPort", port);
		executeCommand(command);
		setState(STATE_READY);
	}

	@Override
	protected void read() {
		String command = "<RFID> <command>GetTagInfo</command> </RFID>";

		String reponse = executeCommand(command);
		String tagId = getTagFromXML(reponse);

		if (tagId != null) {
			Tag tag = new Tag(adaptor.getName(), tagIDToByte(tagId), convertISO15693toPureURI(tagId), System.currentTimeMillis());
			synchronized (readingBuffer) {				
				readingBuffer.add(tag);
			}
		}
	}

	@Override
	protected void setSettings(Map<String, String> properties) {
		settings = properties;
	}

	public String getTagFromXML(String fragment) {
		try {
			// Create a DOM builder and parse the fragment
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			Document tempDoc = factory.newDocumentBuilder().parse(new InputSource(new StringReader(fragment)));

			// Import the nodes of the new document into doc so that they
			// will be compatible with doc
			Element rfidElement = (Element) tempDoc.getFirstChild();

			NodeList list1 = rfidElement.getElementsByTagName("error-code");

			for (int i = 0; i < list1.getLength(); i++) {
				Node node = list1.item(i);
				System.out.println(node.getTextContent());

			}

			if (list1.getLength() > 0) {
				if (list1.item(0).getTextContent().equalsIgnoreCase("0")) { // there is no error
					NodeList list2 = rfidElement.getElementsByTagName("tag-id");
					if (list2.getLength() > 0) {
						return list2.item(0).getTextContent();
					}
				}
			}

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String executeCommand(String xmlRequest) {
		// Loads the native library
		ProximaRFLibrary lib = ProximaRFLibrary.INSTANCE;

		String xmlResponse = null;

		// Create stream
		int request = ProximaRFLibrary.INSTANCE.s_create();
		// lib.s_write(request, xmlRequest, xmlRequest.length());
		// Write XML request into the stream
		lib.s_write(request, xmlRequest, xmlRequest.length());
		// Call RFID function
		int response = lib.RFID(request);
		// Read the response
		xmlResponse = lib.s_get_string(response);

		// Close both streams
		lib.s_close(request);
		lib.s_close(response);

		return xmlResponse;
	}

	private String replace(String str, String pattern, String replace) {
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();
		while ((e = str.indexOf(pattern, s)) >= 0) {
			result.append(str.substring(s, e));
			result.append(replace);
			s = e + pattern.length();
		}
		result.append(str.substring(s));
		return result.toString();
	}
	
}
