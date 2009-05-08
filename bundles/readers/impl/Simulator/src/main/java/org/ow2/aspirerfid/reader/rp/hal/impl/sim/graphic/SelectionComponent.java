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
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.JComponent;

/**
 * @author regli
 */
public class SelectionComponent extends JComponent {
	
	/** the tags this selection contains */
	private final TreeSet selectedTags = new TreeSet();
	
	/** the position where the selection starts */
	private Point start;
	/** the x position of the selection */
	private int y;
	/** the y position of the selection */
	private int x;
	/** the width position of the selection */
	private int width;
	/** the height position of the selection */
	private int height;
	
	/**
	 * contructor
	 */
	public SelectionComponent() {
		super();
		setVisible(false);
	}
	
	/**
	 * set the point of the start position
	 * 
	 * @param start point
	 */
	public void setStartPoint(Point start) {
		this.start = start;
		setValues(start);
		setVisible(true);
	}
	
	/**
	 * set the point of the current mouse position
	 *  
	 * @param current point
	 */
	public void setCurrentPoint(Point current) {
		setValues(current);
		repaint();
	}
	
	/**
	 * compute x, y, width and height from start and current point
	 * 
	 * @param current point
	 */
	private void setValues(Point current) {
		if(start.x < current.x) {
			x = start.x;
			width = current.x - start.x;
		} else {
			x = current.x;
			width = start.x - current.x;
		}
		if(start.y < current.y) {
			y = start.y;
			height = current.y - start.y;
		} else {
			y = current.y;
			height = start.y - current.y;
		}
	}
	
	/**
	 * paints the selection rectangle
	 * 
	 * @param g the graphic representation of the selection
	 */
	protected void paintComponent(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawRect(x, y, width, height);
	}

	/**
	 * set all tags in hash set tags which intersects with the selection as selected
	 *  
	 * @param tags hash set with selection candidates
	 */
	public void select(HashSet tags) {
		Tag curTag; 
		selectedTags.clear();

		// select all tags in selection
		Iterator tagsIt = tags.iterator();
		while(tagsIt.hasNext()) {
			curTag = (Tag)tagsIt.next();
			if(new Rectangle(x, y, width, height).intersects(curTag.getBounds())) {
				curTag.select(selectedTags);
				selectedTags.add(curTag);
				
				// select grouped tags also
				if(curTag.isGrouped()) {
					Tag groupMemberTag;
					Iterator groupedTagIt = curTag.getGroupMembers().iterator();
					while(groupedTagIt.hasNext()) {
						groupMemberTag = (Tag)groupedTagIt.next();
						groupMemberTag.select(selectedTags);
						selectedTags.add(groupMemberTag);
					}
				}
			}
		}
		setVisible(false);
	}
	
	public void unselect(HashSet tags) {
		Iterator tagsIt = tags.iterator();
		while(tagsIt.hasNext()) {
			((Tag)tagsIt.next()).unselect();
		}
	}

	public boolean isActive() {
		return isVisible();
	}
	
	/**
	 * returns all the tags which are in this selection
	 * 
	 * @return tags hash set
	 */
	public TreeSet getTags() {
		return selectedTags;
	}
}
