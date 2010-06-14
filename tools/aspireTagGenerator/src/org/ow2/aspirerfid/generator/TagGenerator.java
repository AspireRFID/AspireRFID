/**
 * Copyright (c) 2008-2010, AspireRFID
 *
 * This library is free software; you can redistribute it and/or
 * modify it either under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation
 * (the "LGPL"). If you do not alter this
 * notice, a recipient may use your version of this file under the LGPL.
 *
 * You should have received a copy of the LGPL along with this library
 * in the file COPYING-LGPL-2.1; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY
 * OF ANY KIND, either express or implied. See the LGPL  for
 * the specific language governing rights and limitations.
 *
 * Contact: AspireRFID mailto:aspirerfid@ow2.org
 */

package org.ow2.aspirerfid.generator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.ow2.aspirerfid.parser.Parser;


public class TagGenerator {
	
	/*
	 * Syntax :
	 */
	private static String printSyntax() {
		return "\t java TagGenerator.jar [<input file>] [<output file>]\n" 
		      +	"\t  the '-' argument stands for stdin or stdout.\n" +
		      	"Spec file example : \n" +
		      	"sgln-96:1.211298.070875.43981/10\n" +
		      	"sgln-96:1.211298.070875.[10000-10099]/1\n";			
	}
	
	/*
	 * Command Line interface.
	 */
	public static void main(String[] args) throws Exception{
		Parser aParser;
		InputStream input = System.in;
		OutputStream output = System.out;
		
		// open files if any
		if (args.length != 0) {
			if (args.length != 2) {
				System.err.println("Invalid syntax call.");
				System.err.println("Syntax : \n" + printSyntax());
				System.exit(-1);
			}
			// open input
			if (args[0].equals("-"))
				input = System.in;
			else
				input = new FileInputStream(args[0]);
			
			// open output
			if (args[1].equals("-"))
				output = System.out;
			else
				output = new FileOutputStream(args[1]);
			
		}
		try {
			// Parse input and generate output
			aParser = new Parser(input, output);		
			aParser.generatePatterns();
		} finally{
			// close streams
			output.close();		
			input.close();
		}

		System.out.println("done");
	}
}
