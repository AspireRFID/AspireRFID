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

package org.ow2.aspirerfid.reader.rp.impl.hal.util;


import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
* Collection of usefull methods needed by several servlets.
*
* @author Matthias Lampe, lampe@lampe.net
* @version 1.0, 08/99
*/

public class Tools
{
	//----- Public Methods --------------------------------------------------

	/**
	* replaces the keys of the given hashtable in the text by the values.
	* All occurances of the keys will be replaced.
	*
	* @param text the text containing strings to be replaced
	* @param table the hashtable containing the key-value pairs for the replacement
	*
	* @return the new text with the replaced strings
	*/

	public static String replaceStrings(String text, Hashtable table)
	{
		String wb = new String(text);

		//---- iterate through all keys
		for (Enumeration e = table.keys(); e.hasMoreElements(); )
		{
			//---- get key-value pair
			String searchFor = (String) e.nextElement();
			String replaceBy = (String) table.get(searchFor);

			//---- do replacement

			wb = replaceString(wb, searchFor, replaceBy);
		}

		return wb;
	}


	/**
	* replaces the string xx in the text by the string yy.
	* All occurances of xx will be replaced.
	*
	* @param text the text containing strings to be replaced
	* @param searchFor string to be replaced by replaceBy
	* @param replaceBy string that will replace searchFor
	*
	* @return the new text with the replaced strings
	*/

	public static String replaceString(String text, String searchFor, String replaceBy)
	{
		//---- create work buffer
		StringBuffer wb = new StringBuffer(text);

		//---- Check arguments
		if (text != null && searchFor != null)
		{
			if  (replaceBy == null)
				replaceBy = "";

			//---- search for 'searchFor' and replace by 'replaceBy'
			//     until end of text
			int pos = 0;	// position in work buffer

		replaceLoop:
			while (pos < wb.length())
			{
				//---- search for string 'searchFor'
				int start = wb.toString().indexOf(searchFor, pos);
				if (start == -1)		// no string found
					break replaceLoop;
				int end = start + searchFor.length();

				//---- replace by string 'replaceBy'
				wb.replace(start, end, replaceBy);

				//---- increment position
				pos = start + replaceBy.length();
			}
		}

		return wb.toString();
	}


	/**
	* returns a vector of strings that are the tokens of the
	* original string delimeted by whitespaces.
	*
	* @param list the string to be tokenized.
	*
	* @return the vector of tokens.
	*/

	public static Vector getTokens(String list)
	{
		// result vector
		Vector result = new Vector();

		// string tokenizer with whitespace as delimeter
		StringTokenizer st = new StringTokenizer(list);

		//--- Generate list of tokens
		while (st.hasMoreTokens())
			result.add(st.nextToken());

		return result;
	}


	/**
	* reads the content of a text file into a string.
	*
	* @param filename the filename of the file.
	*
	* @return the string containing the content of the file.
	*/

	public static String readFile(String filename)
				throws IOException
	{
		//---- create work buffer
		StringBuffer wb = new StringBuffer();

		//---- get file reader
		File file = new File(filename);
		System.out.println("Tools.readFile(): file.getAbsolutePath()="+file.getAbsolutePath());
		FileReader in = new FileReader(file);

		//--- read file content into string buffer
		char[] cbuf = new char[1];
		int res;
		while ((res = in.read(cbuf, 0, 1)) != -1)
			wb.append(cbuf);

		//---- close file reader
		in.close();

		//---- return string
		return wb.toString();
	}


	/**
	* writes the content of a string to a file.
	*
	* @param filename the filename of the file.
	* @param text the string to be written to the file.
	*/

	public static void writeFile(String filename, String text)
				throws IOException
	{
		//---- get file writer
		File file = new File(filename);
		System.out.println("Tools.writeFile(): file.getAbsolutePath()="+file.getAbsolutePath());
		FileWriter out = new FileWriter(file);

		//--- write string to file
		out.write(text, 0, text.length());
		out.flush();

		//---- close file writer
		out.close();
	}


	/**
	* converts the values of the keys of the given hashtable to HTML.
	*
	* @param table the hashtable containing the key-value pairs
	*
	* @return the hashtable with the converted values
	*/

	public static Hashtable convertTableToHTML(Hashtable table)
	{
		Hashtable wh = new Hashtable();
		String convertedValue;

		//---- iterate through all keys
		for (Enumeration e = table.keys(); e.hasMoreElements(); )
		{
			//---- get key-value pair
			String key = (String) e.nextElement();
			String value = (String) table.get(key);

			//---- do conversion
			convertedValue = convertTextToHtml(value);

			//---- add to new hashtable
			wh.put(key, convertedValue);
		}

		return wh;
	}


	/**
	* converts the string to HTML, i.e. replaces newline with <BR> tag.
	*
	* @param text the string to be converted
	*
	* @return the converted string
	*/

	public static String convertTextToHtml(String text)
	{
		text = replaceString(text, "\n\n", "<P>");
		text = replaceString(text, "\n", "<BR>");

		return text;
	}


	/**
	* converts the values of the keys of the given hashtable from HTML
	* to text.
	*
	* @param table the hashtable containing the key-value pairs
	*
	* @return the hashtable with the converted values
	*/

	public static Hashtable convertTableToText(Hashtable table)
	{
		Hashtable wh = new Hashtable();
		String convertedValue;

		//---- iterate through all keys
		for (Enumeration e = table.keys(); e.hasMoreElements(); )
		{
			//---- get key-value pair
			String key = (String) e.nextElement();
			String value = (String) table.get(key);

			//---- do conversion
			convertedValue = convertHtmlToText(value);

			//---- add to new hashtable
			wh.put(key, convertedValue);
		}

		return wh;
	}


	/**
	* converts HTML to a string.
	* The <BR> and <P> tags are replaced with newline.
	*
	* @param text the html text to be converted
	*
	* @return the converted string
	*/

	public static String convertHtmlToText(String text)
	{
		text = replaceString(text, "<P>", "\n\n");
		text = replaceString(text, "<BR>", "\n");

		return text;
	}


	/**
	* returns a string that contains all the key-value pairs of
	* the given hashtable.
	*
	* @param table the hashtable containing the key-value pairs
	*
	* @return the text output of the key-value paris
	*/

	public static String getKeyValuePairs(Hashtable table)
	{
		StringBuffer b = new StringBuffer();

		//---- iterate through all keys
		for (Enumeration e = table.keys(); e.hasMoreElements(); )
		{
			//---- get key-value pair
			String key = (String) e.nextElement();
			String value = (String) table.get(key);

			//---- generate text output
			b.append(key + "=" + value + "\n");
		}

		return b.toString();
	}


	/**
	* returns the current date and time.
	*
	* @return the current date and time.
	*/

	public static Date getTime()
	{
		return Calendar.getInstance().getTime();
	}


	/**
	* encodes a string in URL encoding.
	*
	* The mapping is the following:
	*
	*	a-z,A-Z 	-	a-z,A-Z 	No Changes
	*	0-9 		-	0-9 		No Changes
	*	SPACE 		-	+
	*					Spaces are mapped to the character '+'
	*	All Others 	-	%??
	*					The ascii value of the character is converted
	*					into hex and is preceded by a '%'
	*
	* @param string the original string.
	*
	* @return the URL encoded string.
	*/

	public static String encodeURL(String string)
	{
		StringBuffer result = new StringBuffer();
		String encode;

		//---- iterate through string
		char c;

		for (int i = 0; i < string.length(); i++)
		{
			c = string.charAt(i);
			int code = c;

			//---- mapping
			if (code >= 0 && code <= 31)
				code = 32;				// illegal codes transformed into space

			if (code == 32)
				encode = "+";
			else if ((code >= 48 && code <= 57) || (code >= 65 && code <= 90)
					|| (code >= 97 && code <= 122))
				encode = (new Character(c)).toString();
			else
				encode = "%" + Integer.toHexString(code).toUpperCase();

			//System.out.print("["+c+"|"+code+"|"+encode+"]");

			result.append(encode);
		}
		//System.out.println("\n");

		return result.toString();
	}


	/**
	* decodes a URL encoded string.
	*
	* @param string the URL encoded string
	*
	* @return the decoded string
	*/

	public static String decodeURL(String string)
	{
		StringBuffer result = new StringBuffer();
		String decode;

		//---- iterate through string
		char c;

		for (int i = 0; i < string.length(); i++)
		{
			c = string.charAt(i);
			int code = c;

			//---- mapping
			if (c == '+')
				decode = " ";
			else if (c == '%')
			{
				String hexCode = string.substring(i + 1, i + 3).toLowerCase();
				int v = Integer.parseInt(hexCode, 16);
				char dc = (char) v;
				decode = (new Character(dc)).toString();
				i = i + 2;
			}
			else
				decode = (new Character(c)).toString();

			//System.out.print("["+c+"|"+code+"|"+decode+"]");

			result.append(decode);
		}
		//System.out.println("\n");

		return result.toString();
	}


	/**
	* checks if a string is a valid Chat file name
	*
	* @param name the string to test
	*
	* @return true if name is valid, false otherwise
	*/

	public static boolean isValidName(String name)
	{
		return true;
	}

}