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
package org.ow2.aspirerfid.ale.server.readers.rp.simple;

import java.util.Map;
import java.util.Random;

import org.ow2.aspirerfid.ale.server.Tag;
import org.ow2.aspirerfid.ale.server.readers.rp.base.AbstractAdaptor;
import org.ow2.aspirerfid.ale.server.readers.rp.base.AbstractReader;

/**
 * 
 * @author Gabriel Pedraza Ferreira
 *
 */
public class SimpleReader extends AbstractReader {

	private String PREFIX_GROUP_1 = "urn:epc:pat:gid-96:145.";
	private String PREFIX_GROUP_2 = "urn:epc:pat:gid-96:82.";

	private String[] group1 = new String[] { "12", "56", "87", "233", "255" };
	private String[] group2 = new String[] { "20", "25", "30", "35", "40" };

	public SimpleReader(AbstractAdaptor adaptor, Map<String, String> properties) {
		super(adaptor, properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void closeReader() {
		setState(STATE_NO_READER);
	}

	@Override
	protected void openReader() {
		setState(STATE_READY);
	}

	@Override
	protected void read() {

		byte[] data = { 53, 0, 0, 9, 16, 0, 0, -64, 0, 0, 1, 45 };
		Tag tag = new Tag(adaptor.getName(), data, getRandomTag(), System.currentTimeMillis());
		synchronized (readingBuffer) {
			readingBuffer.add(tag);
		}

	}

	@Override
	protected void setSettings(Map<String, String> properties) {
		// TODO Auto-generated method stub

	}

	private String getRandomTag() {
		Random rand = new Random();
		int randGroup = rand.nextInt(10);
		int elementNumber = rand.nextInt(5);
		int suffix = 100 + rand.nextInt(100);

		String temp = "";

		if (randGroup % 2 == 0)
			temp = PREFIX_GROUP_1 + group1[elementNumber] + "." + suffix;
		else
			temp = PREFIX_GROUP_2 + group2[elementNumber] + "." + suffix;

		return temp;
	}

}
