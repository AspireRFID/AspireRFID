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

package org.ow2.aspirerfid.reader.rp.hal.impl.sim.graphic;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author regli
 */
public class TranslationListener implements ComponentListener {

	/** list of readers which listen to the tranlations */
	private static final List readers = new ArrayList(); 
	
	/** list of antennas which listen to the tranlations */
	private static final List antennas = new ArrayList();
	
	/**
	 * implements the componentResized method of the ComponentListener interface
	 * 
	 * @param e component event
	 */
	public void componentResized(ComponentEvent e) {
	}

	/**
	 * implements the componentMoved method of the ComponentListener interface
	 * 
	 * @param e component event
	 */
	public void componentMoved(ComponentEvent e) {
		Tag tag = (Tag)e.getComponent();
		Iterator readerIt = readers.iterator();
		while (readerIt.hasNext()) {
			((Reader)readerIt.next()).checkTag(tag);
		}
		Iterator antennaIt = antennas.iterator();
		while (antennaIt.hasNext()) {
			((Antenna)antennaIt.next()).checkTag(tag);
		}
	}

	/**
	 * implements the componentShown method of the ComponentListener interface
	 * 
	 * @param e component event
	 */
	public void componentShown(ComponentEvent e) {
	}
	
	/**
	 * implements the componentHidden method of the ComponentListener interface
	 * 
	 * @param e component event
	 */
	public void componentHidden(ComponentEvent e) {
	}

	/**
	 * adds the reader to the list of readers which listen to the tranlations
	 * 
	 * @param reader to add
	 */
	public void add(Reader reader) {
		readers.add(reader);
	}
	
	/**
	 * adds the antenna to the list of antennas which listen to the tranlations
	 * 
	 * @param antenna to add
	 */
	public void add(Antenna antenna) {
		antennas.add(antenna);
	}
}