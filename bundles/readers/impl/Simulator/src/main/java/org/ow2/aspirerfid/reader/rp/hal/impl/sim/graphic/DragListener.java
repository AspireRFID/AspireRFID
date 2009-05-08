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

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

/**
 * @author regli
 */
public class DragListener extends MouseAdapter implements MouseMotionListener {
	
	/** tag which the listener belongs to */
	private final Tag tag;
	
	/** mouse position where the dragging starts */
	private Point mouseStartPos;
	/** tag position where the dragging starts */ 
	private Rectangle tagStartPos;

	/**
	 * the constructor creates a new listener
	 * 
	 * @param tag to listen to
	 */
	public DragListener(Tag tag) {
		this.tag = tag;
	}

	/**
	 * implements the mousePressed method of the MouseListener interface.
	 * executes the mouse pressed action and if this tag is selected it executes
	 * the mouse pressed actions of the other selected tags.
	 * 
	 * @param e mouse event
	 */
	public void mousePressed(MouseEvent e) {
		if(!tag.isSelected()) {
			tag.getSimulator().hideActiveContextMenuAndSelection();
			if(tag.isGrouped()) {
				Iterator tagIt = tag.getGroupMembers().iterator();
				while(tagIt.hasNext()) {
					((Tag)tagIt.next()).select(tag.getGroupMembers());
				}
			} else {
				tag.select();
			}
		}
		
		Iterator tagsIt = tag.getSelectionMembers().iterator();
		while(tagsIt.hasNext()) {
			((Tag)tagsIt.next()).getDragListener().mousePressedAction(e);
		}
	}
	
	/**
	 * sets the dragging start positions
	 * 
	 * @param e mouse event
	 */
	public void mousePressedAction(MouseEvent e) {
		mouseStartPos = e.getPoint();
		tagStartPos = tag.getBounds();
		mouseStartPos.translate(tagStartPos.x, tagStartPos.y);		
	}

	/**
	 * implements the mouseDragged method of the MouseMotionListener interface
	 * executes the mouse dragged action and if this tag is selected it executes
	 * the mouse dragged actions of the other selected tags.
	 * 
	 * @param e mouse event
	 */
	public void mouseDragged(MouseEvent e) {
		Iterator tagsIt = tag.getSelectionMembers().iterator();
		while(tagsIt.hasNext()) {
			((Tag)tagsIt.next()).getDragListener().mouseDraggedAction(e);
		}
	}
	
	/**
	 * sets the new tag position
	 * 
	 * @param e mouse event
	 */
	public void mouseDraggedAction(MouseEvent e) {
		Point curMousePos = e.getPoint();
		Rectangle curCmpPos = tag.getBounds();
		curMousePos.translate(curCmpPos.x, curCmpPos.y);
		int xDiff = curMousePos.x + - mouseStartPos.x;
		int yDiff = curMousePos.y + - mouseStartPos.y;
		tag.setBounds(tagStartPos.x + xDiff, tagStartPos.y + yDiff, tagStartPos.width, tagStartPos.height);
	}
	
	/**
	 * implements the mouseMoved method of the MouseMotionListener interface
	 */
	public void mouseMoved(MouseEvent e) {
	}
}
