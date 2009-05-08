/*
 * Copyright © 2008-2010, Aspire
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


package org.ow2.aspirerfid.ide.ecspec.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.accada.ale.xsd.ale.epcglobal.LRProperty;
import org.accada.ale.xsd.ale.epcglobal.LRSpec;
import org.accada.ale.util.DeserializerUtil;
import org.accada.ale.util.SerializerUtil;
import org.accada.ale.xsd.ale.epcglobal.LRSpec.*;

/**
 * @author Vasso Koletti e-mail: vkol@ait.edu.gr
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class LRSpecBuilder {

	private LRSpec lrspec;
	@SuppressWarnings("unused")
	private List<String> allReaders;

	public LRSpecBuilder() {
		lrspec = new LRSpec();
		lrspec.setIsComposite(false);
		lrspec.setReaders(new Readers());
		lrspec.setProperties(new Properties());
	}

	public Boolean getIsComposite() {
		return lrspec.isIsComposite();
	}

	public void setIsComposite(Boolean value) {
		lrspec.setIsComposite(value);
	}

	public void setLogicalReaders(String logicalReaderName) {
		lrspec.getReaders().getReader().add(logicalReaderName);
	}

	public List<String> getLogicalReaders() {
		return lrspec.getReaders().getReader();
	}

	public void setLRProperty(int index, String propertyName,
			String propertyValue) {

		LRProperty property = new LRProperty();
		property.setName(propertyName);
		property.setValue(propertyValue);

		setProperties(index, property);
	}

	public void setProperties(int index, LRProperty property) {
		lrspec.getProperties().getProperty().add(index, property);
	}

	public List<LRProperty> getProperties() {
		return lrspec.getProperties().getProperty();
	}

	public void generateXml(String filename) throws IOException {
		FileWriter file = new FileWriter(new File(filename));
		SerializerUtil.serializeLRSpec(lrspec, file);
		file.close();
	}

	/**
	 * This method loads the ec specification from a file.
	 * 
	 * @param filename
	 *            of ec specification file
	 * @return ec specification
	 * @throws Exception
	 *             if specification could not be loaded
	 */
	public LRSpec getLRSpecFromFile(String filename) throws Exception {
		FileInputStream inputStream = new FileInputStream(filename);
		return DeserializerUtil.deserializeLRSpec(inputStream);

	}
}
