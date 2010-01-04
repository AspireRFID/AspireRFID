/*
 * Copyright (C) 2008-2010, Aspire
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


package org.ow2.aspirerfid.programmableengine.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.ow2.aspirerfid.programmableengine.model.*;

import org.apache.log4j.Logger;

/**
 * This class provides some methods to deserialize ec specifications and reports.
 * 
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class DeserializerUtil {

	/**	logger. */
	public static final Logger LOG = Logger.getLogger(DeserializerUtil.class);

	
	/**
	 * This method deserializes an OLCBProc specification from an input stream.
	 * 
	 * @param inputStream to deserialize
	 * @return OLCBProc
	 * @throws Exception if deserialization fails
	 */
	public static OLCBProc deserializeOLCBProc(InputStream inputStream) throws Exception {
		OLCBProc openLoopCBProc = new OLCBProc();
		
		
		try {
			
			// initialize jaxb context and unmarshaller
			JAXBContext context = JAXBContext.newInstance(OLCBProc.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			
			JAXBElement<OLCBProc> olcbProcJAXBElement = (JAXBElement<OLCBProc>)unmarshaller.unmarshal(inputStream);
			
			openLoopCBProc = olcbProcJAXBElement.getValue();
						
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return openLoopCBProc;
	}
	
	/**
	 * This method deserializes an APDL Spec from a file.
	 * 
	 * @param pathName of the file containing the APDL Spec
	 * @return OLCBProc object
	 * @throws FileNotFoundException if the file could not be found
	 * @throws Exception if deserialization fails
	 */
	public static OLCBProc deserializeOLCBProcFile(String pathName) throws FileNotFoundException, Exception {
		
		FileInputStream inputStream = new FileInputStream(pathName);
		
		return deserializeOLCBProc(inputStream);
	}	
}