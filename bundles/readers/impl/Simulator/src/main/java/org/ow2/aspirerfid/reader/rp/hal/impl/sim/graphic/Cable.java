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

import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JLabel;
import org.ow2.aspirerfid.reader.rp.hal.impl.sim.multi.GraphicSimulatorServer;


/**
 * @author regli
 */
public class Cable extends JLabel{

	/**	the serial version uid */
	private static final long serialVersionUID = 1L;
	
	/** graphic simulator to which this cable belongs to */
	private final IGraphicSimulator simulator;
	
	/** the position of the reader */
	private final int readerPaneX;

	/** the position of the corresponding antenna */
	private Point antennaPos;

	/**
	 * the constructor creates a new cable from the antenna to the device
	 * 
	 * @param antennaPos position of the antenna in pane
	 */
	public Cable(Point antennaPos, IGraphicSimulator simulator) {
		this.antennaPos = antennaPos;
		this.readerPaneX = -1;
		this.simulator = simulator;
		setBounds(0, 0, simulator.getProperty("WindowWidth"), simulator.getProperty("WindowHeight"));
	}
	
	/**
	 * the constructor creates a new cable from the antenna to the device
	 * 
	 * @param antennaPos position of the antenna in pane
	 */
	public Cable(Point antennaPos, int readerPaneX, GraphicSimulatorServer simulator) {
		this.simulator = simulator;
		this.readerPaneX = readerPaneX;
		this.antennaPos = antennaPos;
		setBounds(0, 0, simulator.getProperty("WindowWidth"), simulator.getProperty("WindowHeight"));
	}
	
	public void setAntennaPos(Point antennaPos) {
		this.antennaPos = antennaPos;
	}
	
	/**
	 * paints the cable
	 * 
	 * @param g the graphic representation of the component
	 */
	protected void paintComponent(Graphics g) {
		if(readerPaneX < 0) {
			int startX = antennaPos.x + simulator.getProperty("AntennaWidth") / 2;
			int mainCableX = simulator.getProperty("AntennaPaneX") + simulator.getProperty("AntennaPaneWidth") / 2;
			g.drawLine(startX, antennaPos.y, startX, antennaPos.y - 10);
			g.drawLine(startX, antennaPos.y - 10, mainCableX, antennaPos.y - 10);
			g.drawLine(mainCableX, antennaPos.y - 10, mainCableX, simulator.getProperty("FramePadding") + simulator.getProperty("ReaderHeight"));
		} else {
			int startX = antennaPos.x + simulator.getProperty("AntennaWidth") / 2;
			int mainCableX = readerPaneX + simulator.getProperty("ReaderWidth") / 2;
			g.drawLine(startX, antennaPos.y - 10, mainCableX, antennaPos.y - 10);
			g.drawLine(startX, antennaPos.y, startX, antennaPos.y - 10);
			g.drawLine(mainCableX, antennaPos.y, mainCableX, antennaPos.y - 10);
		}
	}
}