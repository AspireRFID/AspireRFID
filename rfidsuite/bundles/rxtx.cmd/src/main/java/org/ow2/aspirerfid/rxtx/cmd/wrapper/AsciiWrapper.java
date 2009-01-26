/*
   Copyright 2005-2008, OW2 Aspire RFID project 
   
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

package org.ow2.aspirerfid.rxtx.cmd.wrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.StringTokenizer;

import org.ow2.aspirerfid.rxtx.cmd.IOWrapper;


/**
 * This class provides a wrapper to display Ascii symbols from messages.
 * @author Didier Donsez
 */
public class AsciiWrapper implements IOWrapper {

	private int linesize=16;

	private String separator=" ";

	/**
	 * @see org.ow2.aspirerfid.rxtx.cmd.IOWrapper#parse(java.lang.String)
	 */
	public byte[] parse(String string) throws ParseException {
		return string.getBytes();
	}

	/**
	 * @see org.ow2.aspirerfid.rxtx.cmd.IOWrapper#parse(java.util.StringTokenizer)
	 */
	
	public byte[] parse(StringTokenizer st) throws ParseException {
		int c=0;
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			while (st.hasMoreTokens()) {
				if(c!=0) byteArrayOutputStream.write(separator.getBytes());
				byteArrayOutputStream.write(st.nextToken().getBytes());
				c++;
			}
			return byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			throw new ParseException(e.toString(),c); 
		}
	}

	/**
	 * @see org.ow2.aspirerfid.rxtx.cmd.IOWrapper#format(byte[])
	 */
	public String format(byte[] bytes) throws ParseException {
		return new String(bytes);
	}

	/**
	 * @see org.ow2.aspirerfid.rxtx.cmd.IOWrapper#format(byte[], int, int)
	 */
	public String format(byte[] bytes, int offset, int length)
			throws ParseException {
		return new String(bytes, offset, length);
	}

	public int getLineSize() {
		return linesize;
	}

	public void setLineSize(int linesize) {
		this.linesize = linesize;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}
}