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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.ow2.aspirerfid.ale.engine.input.connectors.Semaphore;


/**
 * This thread generates the IHM triggers. IHM triggers are :
 * <ul>
 * 	<li> start : start trigger, when fired, the acquisition layer is activated. </li>
 * 	<li> pause : pause trigger, when fired with TRUE flag, the acquisition layer is paused,
 * 		 <br> and when fired with FALSE flag, the acquisition layer is reactivated. </li>
 * 	<li> end  : stop trigger, when fired, the acquisition is terminated, and the ALE 
 *      returns from the immediate call.</li> 
 * </ul>
 * 
 * @author rdagher
 *
 */
public class TriggerLauncher extends Thread {
	public final static String END = "e";
	public final static String START = "s";
	public final static String PAUSE = "p";

	public final static String MSG_END = "Enter '" + END + "' to quit";
	public final static String MSG_START = "Enter '" + START + "' to start";
	public final static String MSG_PAUSE = "Enter '" + PAUSE + "' to pause";

	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		
	private Semaphore reset = new Semaphore();
	
	private Semaphore killed = new Semaphore();

	
	public void run() {
		boolean pause = false; // pause flag
		
		
		while (!killed.getFlag()) {
			String line = "none";
					
			/* Poll for start */ 
			System.out.println(MSG_START);
			System.out.println("At any time,"+ MSG_PAUSE);
			do {
				try {
					line = in.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				} // end try 
			} while (!line.equals(START));
			System.out.println("Start trigger fired");
			/* Fire start trigger */
			EventServer.getInstance().fire(EventServer.START_TRIGGER);
			
			/* Poll for end */ 
			line = "none";
			System.out.println(MSG_END);
			do {
				try {
					line = in.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				} // end try
				
				// fire pause if p is pressed
				if (line.equals(PAUSE)) {
					// toggle pause flag
					pause = !pause;
					EventServer.PAUSE_TRIGGER.setEvtData(pause?Boolean.TRUE:Boolean.FALSE);
					EventServer.getInstance().fire(EventServer.PAUSE_TRIGGER);
				}
			} while (!line.equals(END));
			System.out.println("End trigger fired");
			if(pause) {
				System.out.println("Pause trigger released");
				// toggle pause flag to release paused main thread
				pause = !pause;
				EventServer.PAUSE_TRIGGER.setEvtData(Boolean.FALSE);
				EventServer.getInstance().fire(EventServer.PAUSE_TRIGGER);
			}			
			
			/* Fire stop trigger */
			EventServer.getInstance().fire(EventServer.END_TRIGGER);
			
			/* Wait for reset */
			while(!reset.getFlag() && (!killed.getFlag())) {
				synchronized (reset) {
					try {
						reset.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}	
	}
	
	/**
	 * Thread restart.
	 */
	public void restart() {
		if (!isAlive()) {
			start();
		} else {
			synchronized (reset) {
				reset.setFlag(true);
				reset.notify();
			}
		}
	}
	
	/**
	 * Kills the thread
	 */
	public void kill() {
		killed.setFlag(true);
		restart(); // unblock to kill		
	}
	
}