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
package org.ow2.aspirerfid.rxtx.cmd;

import java.text.ParseException;
import java.util.StringTokenizer;

/**
 * This interface defines a wrapper
 * to parse messages to send to the serial port
 * and to format byte arrays received from the serial port.
 * @author Didier Donsez
 */
public interface IOWrapper {
	/**
	 * parse the string
	 * @param string
	 * @return a byte array
	 * @throws ParseException
	 */
	byte[] parse(String string) throws ParseException;
	
	/**
	 * parse the tokenized string 
	 * @param st a string tokenizer
	 * @return a byte array
	 * @throws ParseException
	 */
	byte[] parse(StringTokenizer st) throws ParseException;
	
	/**
	 * format the byte array
	 * @param bytes
	 * @return the formatted string
	 * @throws ParseException
	 */
	String format(byte[] bytes) throws ParseException;
	
	/**
	 * format the byte array from an offset position
	 * @return the formatted string
	 * @param offset the offset position
	 * @param length the length
	 * @return the formatted string
	 * @throws ParseException
	 */
	String format(byte[] bytes, int offset, int length) throws ParseException;
}
