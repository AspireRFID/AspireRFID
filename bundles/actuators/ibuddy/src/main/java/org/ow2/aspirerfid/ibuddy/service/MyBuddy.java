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

package org.ow2.aspirerfid.ibuddy.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ow2.aspirerfid.ibuddy.service.IIBuddy.HeadColor;
import org.ow2.aspirerfid.ibuddy.service.IIBuddy.HeartState;
import org.ow2.aspirerfid.ibuddy.service.IIBuddy.Orientation;


/**
 * TODO
 * @author Daniel Lovera and Clément Deschamps and Mehdi Damou
 */
public class MyBuddy implements Runnable {

	private IBuddyDescriptor buddy;
	private Map<Action,List<String>> buddyWillDo = null;
	private int waiting = 1000;

	public MyBuddy(IBuddyDescriptor buddyDescriptor) {
		this.buddy = buddyDescriptor;
	}
	
	public MyBuddy(IBuddyDescriptor buddyDescriptor , Map<Action,List<String>> actions) {
		this.buddy = buddyDescriptor;
		this.buddyWillDo = actions;
		Thread t = new Thread(this);
		t.start();
	}
		
	private void flapwings(int time) {
		
		try {
			for (int i = 0; i < time; i++) {
				buddy.sendWingsState(IIBuddy.WingsState.UP);
				Thread.sleep(waiting);
				buddy.sendWingsState(IIBuddy.WingsState.DOWN);
				Thread.sleep(waiting);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void colorhead(HeadColor pColor){
		try{
			buddy.sendHeadColor(pColor);
			Thread.sleep(waiting);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void heartState(HeartState hearst){
		
		try {
			buddy.sendHeartState(hearst);
			Thread.sleep(waiting);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setOrientation(Orientation or){
		
		try {
			buddy.sendOrientation(or);
			Thread.sleep(waiting);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void reset() {
		buddy.sendReset();
	}
	
	public void run() {
		try {
			buddy.open();
//			buddy.sendReset();
			doAllActions();
//			buddy.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void doAllActions() throws InterruptedException {
		List<String> actions = null;
		for (Iterator<Action> iterator = buddyWillDo.keySet().iterator(); iterator.hasNext();) {
			Action type = (Action) iterator.next();
			switch (type) {
			case ROTATE:
				 actions = buddyWillDo.get(type);
				 for (int i = 0; i < actions.size(); i++) {
					 if (i==0){
						 waiting = Integer.parseInt(actions.get(i));
					 }
					 else {
						 setOrientation(Orientation.valueOf(actions.get(i)));
						
					 }
				}	
				break;
			case FLAP:
				 actions = buddyWillDo.get(type);
				 
				 for (int i = 0; i < actions.size(); i++) {
					 if (i==0){
						 waiting = Integer.parseInt(actions.get(i));
					 }
					 else {
						 flapwings(Integer.parseInt(actions.get(i))*2);
					 }
				 }	
				break;
			case HEAD:
				 actions = buddyWillDo.get(type);
				 
				 for (int i = 0; i < actions.size(); i++) {
					 if (i==0){
						 waiting = Integer.parseInt(actions.get(i));
					 }
					 else {
						 colorhead(HeadColor.valueOf(actions.get(i)));
					 }			
				}
				break;
			case HEART:
				 actions = buddyWillDo.get(type);
				 
				 for (int i = 0; i < actions.size(); i++) {
					 if (i==0){
						 waiting = Integer.parseInt(actions.get(i));
					 }
					 else {
						 heartState(HeartState.valueOf(actions.get(i)));
						
					 }	
				}
				break;
			case RESET:
				reset();
				break;
			
			default:
				break;
			}
		}
	}
}
