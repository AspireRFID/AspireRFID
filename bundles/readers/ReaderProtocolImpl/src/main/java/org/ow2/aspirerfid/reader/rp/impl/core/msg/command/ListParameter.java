/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Accada (www.accada.org).
 *
 * Accada is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Accada is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Accada; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package org.ow2.aspirerfid.reader.rp.impl.core.msg.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.util.HexUtil;



/**
 * This class is used as a helper class in the text parser. During
 * parsing the parameter arrays and lists are parsed into such a 
 * <code>ListParameter</code> object.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 * @see org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TextCommandParserHelper
 *
 */
public class ListParameter extends Parameter {
	private List paramList;
	
	public ListParameter() {
		paramList = new ArrayList();
	}
	
	/**
	 * Adds a parameter to a parameter list.
	 * @param p The parameter to add to the parameter list.
	 */
	public void addParameter(Parameter p) {
		paramList.add(p);
	}
	
	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 * 
	 * @return an iterator over the elements in this list in proper sequence.
	 * 
	 */
	public Iterator iterator() {
		return paramList.iterator();
	}
	
	/**
	 * Returns the number of elements in this list. If this list contains more
	 * than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
	 * 
	 * @return the number of elements in this parameter list
	 */
	public int size() {
		return paramList.size();
	}
	
	/**
	 * Creates a copy of the internal list of parameters. The list items are
	 * represented as String.
	 * 
	 * @return List of String
	 * @throws TextCommandParserException
	 *             If list contains a parameter with wrong type.
	 */
	public List toStringList() throws TextCommandParserException {
		try {
			List stringList = new ArrayList(size());
			Iterator it = iterator();
			while(it.hasNext()) {
				ValueParameter p = (ValueParameter)it.next();
				stringList.add(p.getStringValue());
			}
			return stringList;
		} catch (ClassCastException e) {
			throw new TextCommandParserException("Parameter in the list has wrong type.");
		}
	}
	
	/**
	 * Creates a copy of the internal list of parameters. The list items are
	 * represented as <code>TagFieldValueParamType</code> used in
	 * <source>source.write(...)</code>.
	 * 
	 * @return List of <code>TagFieldValueParamType</code>
	 * @throws TextCommandParserException
	 *             If list contains a parameter with wrong type.
	 * @throws JAXBException
	 *             If list contains a parameter with wrong type. 
	 * @see org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TagFieldValueParamType
	 */
	public List toTagFieldValueList() throws TextCommandParserException, JAXBException {
		try {
			List tfvList = new ArrayList(size());
			Iterator it = iterator();
			while(it.hasNext()) {
				PairParameter p = (PairParameter)it.next();
				TagFieldValueParamType tfvParam = TextCommandParserHelper.cmdFactory.createTagFieldValueParamType();
				tfvParam.setTagFieldName(p.getField());
				tfvParam.setTagFieldValue(HexUtil.hexToByteArray(p.getValue()));
				tfvList.add(tfvParam);
			}
			return tfvList;
		} catch (ClassCastException e) {
			throw new TextCommandParserException("Parameter in the list has wrong type.");
		}
	}
	
	/**
	 * Creates a copy of the internal list of parameters. The list items are 
	 * represented as <code>byte[]</code> used in
	 * <source>source.writeID(...)</code>.
	 * 
	 * @return List of <code>byte[]</code>
	 * @throws TextCommandParserException
	 *             If list contains a parameter with wrong type.
	 * @throws JAXBException
	 *             If list contains a parameter with wrong type. 
	 */
	public List toHexStringListType() throws TextCommandParserException {
		try {
			List hexList = new ArrayList(size());
			Iterator it = iterator();
			while(it.hasNext()) {
				ValueParameter p = (ValueParameter)it.next();
				byte[] hexArray = HexUtil.hexToByteArray(p.getStringValue());
				hexList.add(hexArray);
			}
			return hexList;
		} catch (ClassCastException e) {
			throw new TextCommandParserException("Parameter in the list has wrong type.");
		}
	}
	
}
