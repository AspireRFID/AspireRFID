/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.reader.fictive;

import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Vector;

import org.ow2.aspirerfid.reader.util.config.Configuration;

/**
 * Class implementing the random chose of a list of tags from a list read in a
 * properties parameter
 * 
 * @author Anne Robert
 * @version 2006
 */
public class RfidSimulator {

	private Random random;
	private String[] tagList;
	private int max;

	/**
	 * set the maximum of fictitiously read tag in a step. The number of read
	 * tags is a number between 0 and max
	 * 
	 * @param max
	 */
	public void setMaxTagRead(int max) {
		this.max = max;

	}

	/**
	 * get the maximum of read tag in a step. The number of read tags is a
	 * number between 0 and max
	 * 
	 * @return the max read tag
	 */
	public int getMaxTagRead() {
		return max;
	}

	/**
	 * function to choose randomly the sequence of read tags. It will build a
	 * vector of length between 0 and Max tags. The tags are chosen in the tag
	 * list. There is no duplicated tags in this vector
	 * 
	 * @return TODO Javadoc
	 */
	public Vector detectRfidTags() {
		int NbTags; // number of tags fictitiously read
		Vector res; // result of the function

		// Check we are able to build a tag array
		if ((tagList == null) || (max == 0)) {
			return null;
		}

		// choose the number of read tags
		// The read tags is maximized by the max attribute and the
		// Length of tagList array
		NbTags = random.nextInt() % max;
		if (NbTags > tagList.length)
			NbTags = tagList.length;

		// Build the result array
		res = new Vector();
		while (res.size() < NbTags) {
			// choose a tag
			int nTag = Math.abs(random.nextInt()) % tagList.length;
			// Check the tag is not already in the array
			if (!res.contains(tagList[nTag])) {
				// not already in the result, add it
				res.addElement(tagList[nTag]);
			}
		}
		// the Vector is fully build, return it
		return res;
	}

	/**
	 * constructor of the object. read the tag list, the maximum read in a step
	 * in the properties parameter
	 * 
	 * @param properties
	 */
	public RfidSimulator(Properties properties) {
		// initialize attributes
		max = 0;
		tagList = null;

		try {
			// read initial value of attributes in properties
			tagList = loadTagList(properties);
			String maxStr = properties.getProperty("maxRaedRFID");
			if (maxStr == null) {
				max = tagList.length / 3;
				if ((tagList.length > 0) && (max == 0))
					max = 1;
			} else {
				max = new Integer(maxStr).intValue();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Initialize local variable
		random = new Random();
	}

	/**
	 * set the tag list in which the tags are randomly chose.
	 * 
	 * @param tagList
	 */
	public void setTagList(String[] tagList) {
		this.tagList = tagList;
	}

	/**
	 * get the tag list in which the tags are randomly chose.
	 * 
	 * @return the tagList
	 */
	public String[] getTagList() {
		return tagList;
	}

	/**
	 * load the list of tags potentially read
	 * 
	 * @param properties
	 *            the properties object in which the tags ID are read
	 * @return the tag list
	 * @throws Exception
	 *             Exceptions thrown by configuration utilities
	 */
	private String[] loadTagList(Properties properties) throws Exception {

		// Load tag list from properties
		String[] res;

		Vector vTagList = Configuration.getVector(properties, "tagList");

		if (vTagList == null) {
			// The property is not defined
			return null;
		} else {
			// Translate the Vector to String array
			res = new String[vTagList.size()];
			// Scan the Vector
			Iterator Iter = vTagList.iterator();
			int i = 0;
			while (Iter.hasNext()) {
				// Copy each vector element in the array
				res[i] = (String) (Iter.next());
				i += 1;
			}
			// return the build array
			return res;
		}
	}
}
