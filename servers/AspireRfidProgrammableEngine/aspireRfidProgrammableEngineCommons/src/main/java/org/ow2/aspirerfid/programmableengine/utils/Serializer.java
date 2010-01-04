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

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.ow2.aspirerfid.programmableengine.model.OLCBProc;
import org.ow2.aspirerfid.programmableengine.model.ObjectFactory;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class Serializer {

	/**
	 * This method serializes an OLCBProc Object to an xml and writes it into a file.
	 * 
	 * @param spec
	 *            the OLCBProc to be written into a file
	 * @param pathName
	 *            the file where to store
	 * @throws IOException
	 *             whenever an io problem occurs
	 */
	public static void serializeLRSpec(OLCBProc openLoopCBProc, StringWriter writer) throws IOException {

		ObjectFactory objectFactory = new ObjectFactory();
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(OLCBProc.class);
			JAXBElement<OLCBProc> item = objectFactory.createOLCBProc(openLoopCBProc);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(item, writer);
		}
		catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
