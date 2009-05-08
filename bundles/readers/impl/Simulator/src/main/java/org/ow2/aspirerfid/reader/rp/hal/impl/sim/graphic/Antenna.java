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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import org.ow2.aspirerfid.reader.rp.hal.impl.sim.util.ResourceLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author regli
 */
public class Antenna extends JComponent {
	
	/** the serial version uid */
	private static final long serialVersionUID = 1L;
	
	/** the logger */
	private static Log log = LogFactory.getLog(Antenna.class);
	
	private String defaultfilename = "/images/rfid-antenna_default.png";
	
	/** the id of the rfid antenna */
	private final String id;
	/** the image icon of the rfid antenna */
	private final Icon icon;
	/** contains the tags which are in the range of this antenna */
	private final Set tags = new HashSet();
	/** graphic simulator to which this antenna belongs to */
	private final IGraphicSimulator simulator;
	/** cable from the antenna to the reader */
	private Cable cable;

	/**
	 * the constructor creates a new graphical representation of a rfid antenna 
	 * 
	 * @param id of the new rfid antenna
	 * @param simulator graphic simulator to which this rfid antenna belongs to
	 */
	public Antenna(String id, final IGraphicSimulator simulator) {
		super();
		
		// set properties
		this.id = id;
		this.simulator = simulator;
		String filename = simulator.getProperties().getString("AntennaImage");
      URL fileurl = ResourceLocator.getURL(filename, defaultfilename);
      icon = new ImageIcon(fileurl);
	}

	public void setPosition(Point pos) {
		setBounds(new java.awt.Rectangle(pos, new Dimension(simulator.getProperty("AntennaWidth"), simulator.getProperty("AntennaHeight"))));
		if (cable != null) {
			cable.setAntennaPos(pos);
		}
	}
	
	/**
	 * paints the rfid antenna
	 * 
	 * @param g the graphic representation of the component
	 */
	protected void paintComponent(Graphics g) {
		icon.paintIcon(this, g, 0, 0);
		g.setColor(Color.BLACK);
		g.setFont(new Font(simulator.getProperties().getString("AntennaLabelFont"), 0, simulator.getProperty("AntennaLabelSize")));
		g.drawString(id, (15 - id.length()) * 7 / 2, simulator.getProperty("AntennaHeight"));
	}
	
	/**
	 * checks if the tag is in the range of the antenna
	 * 
	 * @param tag to check
	 */
	public void checkTag(Tag tag, Reader reader) {
		if (getBounds().intersects(tag.getBounds())) {
			if (tags.add(tag)) {
//            simulator.enterEvent(reader.getId(), getId(), tag.getId());
            simulator.enterEvent(reader.getId(), getId(), tag.getSimTag());
				log.info("'" + tag.getId() + "' ENTER the range of antenna '" + id + "' of reader '" + reader.getId() + "'");
			}
		} else {
			if (tags.remove(tag)) {
//            simulator.exitEvent(reader.getId(), getId(), tag.getId());
            simulator.exitEvent(reader.getId(), getId(), tag.getSimTag());
				log.info("'" + tag.getId() + "' EXIT the range of antenna '" + id + "' of reader '" + reader.getId() + "'");
			}
		}
	}
	
	/**
	 * checks if the tag is in the range of the antenna
	 * 
	 * @param tag to check
	 */
	public void checkTag(Tag tag) {
		if (getBounds().intersects(tag.getBounds())) {
			if (tags.add(tag)) {
				log.info("'" + tag.getId() + "' ENTER the range of antenna '" + id + "'");
//            simulator.enterEvent(null, getId(), tag.getId());
            simulator.enterEvent(null, getId(), tag.getSimTag());
			}
		} else {
			if (tags.remove(tag)) {
				log.info("'" + tag.getId() + "' EXIT the range of antenna '" + id + "'");
//            simulator.exitEvent(null, getId(), tag.getId());
            simulator.exitEvent(null, getId(), tag.getSimTag());
			}
		}
	}
	
	/**
	 * returns the id of the rfid antenna
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * returns a string representation of the rfid antenna
	 * 
	 * @return string representation
	 */
	public String toString() {
		return id;
	}

	/**
	 * set cable
	 * 
	 * @param cable
	 */
	public void setCable(Cable cable) {
		this.cable = cable;
	}
	
	/**
	 * get cable
	 * 
	 * @return cable
	 */
	public Cable getCable() {
		return cable;
	}
}