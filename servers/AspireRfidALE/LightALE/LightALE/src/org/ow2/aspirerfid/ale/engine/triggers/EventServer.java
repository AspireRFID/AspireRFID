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

package org.ow2.aspirerfid.ale.engine.triggers;

/**
 * An EventServer Objects wraps a list of events and permits subscription and 
 * notification of events.
 * 
 * @author rdagher
 *
 */
public class EventServer {
	
	/**
	 * Start Trigger Event.
	 */
	public final static TriggerEvent START_TRIGGER = new TriggerEvent("start");

	/**
	 * Pause Trigger Event.
	 */
	public final static TriggerEvent PAUSE_TRIGGER = new TriggerEvent("pause");
	
	/**
	 * End Trigger Event.
	 */
	public final static TriggerEvent END_TRIGGER = new TriggerEvent("end");
	
	/**
	 * Number of handled events.
	 */
	public final static int EVENTS_NUMBER = 3;
	
	/**
	 * Prohibited.
	 * Constructor
	 */
	private EventServer() {
		PAUSE_TRIGGER.setEvtData(Boolean.FALSE);
	}
	
	/**
	 * Access the Event Server Singleton. 
	 * @return the Event Server Singleton.
	 */
	public synchronized static EventServer getInstance() {
		
		// create on first call
		if (theServer == null)
			theServer = new EventServer();
		
		return theServer;
	}
	
	/**
	 * The Event Server Singleton.
	 */
	private static EventServer theServer = null;
	
	
	/**
	 * Fires an event.
	 * @param evt one of the following events : 
	 * {@link EventServer#START_TRIGGER START_TRIGGER},
	 * {@link EventServer#PAUSE_TRIGGER PAUSE_TRIGGER},
	 * {@link EventServer#END_TRIGGER END_TRIGGER}.
	 * @return true if a listener was attached to fired event, and false otherwise.
	 */
	public boolean fire (TriggerEvent evt) {
		int idx ;
		boolean ret = true;
		
		idx = getIndex(evt);
		
		if (idx != -1) {
			if (this.listeners[idx] != null)
				this.listeners[idx].triggerNotify(evt);
			else
				ret = false;
		}
		else
			throw new IllegalArgumentException("Invalid Event parameter");
		
		return ret;
	}

	
	/**
	 * Adds a listener to be notified when event is fired.
	 * @param evt one of the following events : 
	 * {@link EventServer#START_TRIGGER START_TRIGGER},
	 * {@link EventServer#PAUSE_TRIGGER PAUSE_TRIGGER},
	 * {@link EventServer#END_TRIGGER END_TRIGGER}.
	 * @param evtListner one listener.
	 */
	public void addEventListner(TriggerEvent evt, TriggerEventListener evtListner) {
		int idx ;
		
		idx = getIndex(evt);
		
		if (idx != -1)
			this.listeners[idx] = evtListner;
		else
			throw new IllegalArgumentException("Invalid Event parameter");
	}
	
	
	/**
	 * Removes a listener to be notified when event is fired.
	 * @param evt one of the following events : 
	 * {@link EventServer#START_TRIGGER START_TRIGGER},
	 * {@link EventServer#PAUSE_TRIGGER PAUSE_TRIGGER},
	 * {@link EventServer#END_TRIGGER END_TRIGGER}.
	 * @param evtListner one listener.
	 */
	public void removeEventListner(TriggerEvent evt, TriggerEventListener evtListner) {
		int idx ;
		
		idx = getIndex(evt);
		
		if (idx != -1)
			this.listeners[idx] = null;
		else
			throw new IllegalArgumentException("Invalid Event parameter");
	}
	
	/**
	 * Gives the index of the event handler in the event table {@link EventServer#listeners listeners}.
	 * 
	 * @param evt one of the following events : 
	 * {@link EventServer#START_TRIGGER START_TRIGGER},
	 * {@link EventServer#PAUSE_TRIGGER PAUSE_TRIGGER},
	 * {@link EventServer#END_TRIGGER STOP_TRIGGE
	 * @return the index in the table, or -1 if event not found.
	 */
	private static int getIndex(TriggerEvent evt) {
		int idx ;
		
		if (evt == START_TRIGGER)
			idx = 0 ;
		else if (evt == PAUSE_TRIGGER)
			idx = 1  ;
		else if (evt == END_TRIGGER)
			idx = 2  ;
		else 
			idx = -1  ;
		return idx;
	}
	
	/**
	 * A table of listeners to be notified when an event is fired.
	 */
	private TriggerEventListener [] listeners = new TriggerEventListener[EVENTS_NUMBER];
		
	
	/*
	 * Test Case.
	 */
	public static void main(String[] args) throws Exception {
		EventServer server = EventServer.getInstance();
		
		TriggerEventListener listner = new TriggerEventListener() {
			public void triggerNotify(TriggerEvent evt) {
				System.out.println("Event fired : " + evt.getSource());
			}			
		};
		
		server.addEventListner(START_TRIGGER, listner);
		server.addEventListner(END_TRIGGER, listner);
		server.addEventListner(PAUSE_TRIGGER, listner);
		
		System.out.println("fire = " + server.fire(START_TRIGGER));
		System.out.println("fire = " + server.fire(END_TRIGGER));
		System.out.println("fire = " + server.fire(PAUSE_TRIGGER) + "\n");
		
		server.removeEventListner(START_TRIGGER, listner);
		System.out.println("fire = " + server.fire(START_TRIGGER)); // no effect
		System.out.println("fire = " + server.fire(END_TRIGGER));
		System.out.println("fire = " + server.fire(PAUSE_TRIGGER));
		
	}
}
