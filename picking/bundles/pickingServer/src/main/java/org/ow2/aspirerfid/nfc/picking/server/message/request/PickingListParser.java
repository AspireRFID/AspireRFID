/*
 *  Copyright (C) Aspire
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.ow2.aspirerfid.nfc.picking.server.message.request;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.ow2.aspirerfid.nfc.picking.util.Range;
import org.ow2.aspirerfid.nfc.picking.util.UidRanges;
import org.ow2.aspirerfid.nfc.picking.util.XmlTags;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Simple parser of the XML message that contains the picking list
 * @author kiev
 *
 */
public class PickingListParser {
	private List<UidRanges> pickingList;
	private List<String> pickedUIDs;
	
	public static void main(String[] args) throws Exception {
		new PickingListParser("G:\\aspire\\branches\\applications\\picking\\bundles\\pickingServer\\src\\main\\resources\\list.xml");
	}
	
	PickingListParser(String message) throws Exception {
		DocumentBuilderFactory b = DocumentBuilderFactory.newInstance();
		Document doc = b.newDocumentBuilder().parse(new ByteArrayInputStream(message.getBytes()));
		parseList(doc);
		parsePickedItems(doc);
	}
	
	public List<String> getPickedUIDs() {
		return pickedUIDs;
	}
	
	private void parsePickedItems(Document doc) {
		NodeList n = doc.getElementsByTagName("tag");
		pickedUIDs = new ArrayList<String>();
		for (int i = 0; i < n.getLength(); i++) {
			String value = n.item(i).getAttributes().getNamedItem("id").getNodeValue();
			pickedUIDs.add(value);
			System.out.println(value);
			
		}
		pickingList = Collections.unmodifiableList(pickingList);
	}

	private void parseList(Document doc) {
		NodeList n = doc.getElementsByTagName("prod");
		pickingList = new ArrayList<UidRanges>();
		for (int i = 0; i < n.getLength(); i++) {
			UidRanges r = processPickingItem(n.item(i));
			pickingList.add(r);
		}
		pickingList = Collections.unmodifiableList(pickingList);
	}
	
	public List<UidRanges> getPickingList() {
		return pickingList;
	}

	private UidRanges processPickingItem(Node item) {
		NodeList list = item.getChildNodes();
		String name = null;
		String quantity = null;
		Vector ranges = null;

		for (int i = 0; i < list.getLength(); i++) {
			Node current = list.item(i);
			if (XmlTags.QUANTITY.equals(current.getNodeName())) {
				quantity = current.getTextContent();
			} else if (XmlTags.PROD_NAME.equals(current.getNodeName())) {
				name = current.getTextContent().trim();
			} else if (XmlTags.RANGES.equals(current.getNodeName())) {
				ranges = processRanges(current.getChildNodes());
			}
		}
		return new UidRanges(name, ranges, quantity);
	}

	/**
	 * @param ranges
	 * @return
	 */
	private Vector processRanges(NodeList ranges) {
		Vector result = new Vector();
		String initRange = null;
		String finalRange = null;
		for (int i = 0; i < ranges.getLength(); i++) {
			Node current = ranges.item(i);
			if (XmlTags.RANGE.equals(current.getNodeName())) {
				for (int j = 0; j < current.getChildNodes().getLength(); j++) {
					Node node = current.getChildNodes().item(j);
					if (XmlTags.INIT.equals(node.getNodeName())) {
						initRange = node.getTextContent();
					} else if (XmlTags.FINAL.equals(node.getNodeName())) {
						finalRange = node.getTextContent();
					}
				}
				result.add(new Range(initRange, finalRange));
			}
		}
		return result;
	}
}