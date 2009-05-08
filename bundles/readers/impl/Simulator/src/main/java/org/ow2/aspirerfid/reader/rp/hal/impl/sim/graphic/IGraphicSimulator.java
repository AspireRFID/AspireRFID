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

import javax.swing.JPopupMenu;

import org.apache.commons.configuration.XMLConfiguration;


public interface IGraphicSimulator {

	/**
	 * removes the antenna from the layered pane
	 * 
	 * @param antenna which will be removed
	 */
	void removeAntenna(Antenna antenna, Reader reader);

	/**
	 * removes the tag from the layered pane
	 * 
	 * @param tag which will be removed
	 */
	void removeTag(Tag tag);

	/**
	 * returns the translation listener
	 * 
	 * @return translation listener
	 */
	TranslationListener getTranslationListener();

	/**
	 * returns the localized gui text configuration
	 *
	 * @return gui text resource bundle
	 */
	XMLConfiguration getGuiText();

	/**
	 * returns the simulator property configuration
	 * 
	 * @return properties
	 */
	XMLConfiguration getProperties();

	/**
	 * gets a property value as integer
	 * 
	 * @param key of the property
	 * @return property as integer value
	 */
	int getProperty(String key);

	/**
	 * sets active context menu
	 * 
	 * @param contextMenu which is active
	 */
	void setActiveContextMenu(JPopupMenu contextMenu);

	/**
	 * hide active context menu
	 */
	void hideActiveContextMenu();

	/**
	 * hide active context menu and hide selection
	 */
	void hideActiveContextMenuAndSelection();

	/**
	 * adds an enter event to the simulator controller
	 * 
	 * @param readerId
	 * @param antennaId 
	 * @param tagId
	 */
	void enterEvent(String readerId, String antennaId, String tagId);

   /**
    * adds an enter event to the simulator controller
    * 
    * @param readerId
    * @param antennaId 
    * @param tag  the sim.Tag
    */
   void enterEvent(String readerId, String antennaId,
         org.ow2.aspirerfid.reader.rp.hal.impl.sim.Tag tag);

   /**
    * adds an exit event to the simulator controller
    * 
    * @param readerId
    * @param antennaId 
    * @param tagId
    */
   void exitEvent(String readerId, String antennaId, String tagId);

	/**
	 * adds an exit event to the simulator controller
	 * 
	 * @param readerId
	 * @param antennaId 
	 * @param tag the sim.Tag
	 */
	void exitEvent(String readerId, String antennaId,
         org.ow2.aspirerfid.reader.rp.hal.impl.sim.Tag tag);

}