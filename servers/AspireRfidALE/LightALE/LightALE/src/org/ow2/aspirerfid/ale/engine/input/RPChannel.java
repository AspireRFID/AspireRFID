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

package org.ow2.aspirerfid.ale.engine.input;

import java.util.StringTokenizer;

import org.ow2.aspirerfid.ale.engine.collect.Filtering;
import org.ow2.aspirerfid.ale.engine.input.connectors.SGLN96Trigger;
import org.ow2.aspirerfid.ale.engine.lr.IcomLRPropSetter;
import org.ow2.aspirerfid.ale.engine.triggers.TriggerLauncher;
import org.ow2.aspirerfid.ale.epc.reports.ECInitiationCondition;
import org.ow2.aspirerfid.ale.epc.reports.ECTerminationCondition;
import org.ow2.aspirerfid.ale.epc.spec.ECFilterSpec;
import org.ow2.aspirerfid.ale.epc.spec.ECTrigger;


/**
 * Rp connector class.
 * @author rdagher
 *
 */
public abstract class RPChannel implements IcomLRPropSetter{

	/**
	 * Starts the default trigger launcher thread.
	 */
	protected void startDefaultTriggerLauncher() {
		defaultTriggerLauncher.restart();
	}
	
	/**
	 * Kills the default trigger launcher thread.
	 */
	protected void killDefaultTriggerLauncher() {
		defaultTriggerLauncher.kill();
	}
	
	/**
	 * Factory method for decoding SGLN96Trigger from ECspec.
	 * 
	 * @param sglnStartTrigger The trigger from ECSpec for enabling tag reporting to 
	 * uper layer.
	 * @return The trigger object representation or null if an error occured.
	 */
	protected static SGLN96Trigger decodeStartTrigger(ECTrigger sglnStartTrigger) {
		SGLN96Trigger sglnSpecTrigger = null;
		String sgln96Uri = null;
		StringTokenizer tokenizer;
		
		try {
			sgln96Uri = sglnStartTrigger.getTriggerData();
			tokenizer = new StringTokenizer(sgln96Uri.substring(sgln96Uri.lastIndexOf(':')+1), ".");
			sglnSpecTrigger = new SGLN96Trigger(Byte.parseByte(tokenizer.nextToken()), 
													 Long.parseLong(tokenizer.nextToken()),
													 Integer.parseInt(tokenizer.nextToken()), 
													 Long.parseLong(tokenizer.nextToken()));
		} catch (Exception e) {
			System.err.println("invalid uri '" + sgln96Uri +"'\n" + e.toString()); // log error
			sglnSpecTrigger = null; // ignore SGLN trigger
		}
		
		return sglnSpecTrigger;
	}
	
	/**
	 * Stop Trigger Launcher Thread.
	 */
	private TriggerLauncher defaultTriggerLauncher;
	
	/**
	 * Flag indicating if default trigger launcher is installed.
	 */
	protected boolean isDefaultTriggerLauncherInstalled = false;
	
	/**
	 * Time stamp generator with an origin of time that can be shifted.  
	 */
	protected OriginBasedTiming timing ;
	
	/**
	 * Reader device configuration cache.
	 */
	protected RPChannelConfig readerconfig;
	
	/**
	 *  EPC Compliant Software Filtering Engine.
	 */
	protected Filtering filteringEngine ;
	
	/**
	 * Flag indicating if filtering function is activated or not. For better performance 
	 * when no filtering is specified.
	 */
	protected boolean filtering;
	
	/**
	 * Flag indicating whether the reader is connected or not.
	 */
	protected boolean connected;
	
	/**
	 * Constructor
	 */
	public RPChannel() {
		String prop;

		/* Retrieve Configuration */
		prop = System.getProperty("icom.ale.rpLink.defaultTriggerLauncher");
		isDefaultTriggerLauncherInstalled = (prop == null) ? false : prop.equalsIgnoreCase("true");
		if (isDefaultTriggerLauncherInstalled)
			defaultTriggerLauncher = new TriggerLauncher();
		
		/* Timing */
		timing = new OriginBasedTiming();
		
		/* EPC Software Filtering Engine */
		filteringEngine = new Filtering();
		
		/* Reader device configuration cache */
		readerconfig = new RPChannelConfig();
	}

	/**
	 * Initialise the reader device (Low Level) communication.
	 * 
	 * @param address Communication address with the reader.
	 */
	public abstract void initReaderDevice() throws Exception;
	
	/**
	 * Set up the acquisition layer.
	 * @param sglnStartTrigger The sgln start trigger for starting tag reporting.
	 * @param filterSpec filtering spec. May be null if no filtering.
	 */
	public abstract void initAcquisition(ECTrigger sglnStartTrigger, ECFilterSpec filterSpec);
	
	/**
	 * Get the reader name. Used for LRSpec Management.
	 * @return the reader's name.
	 */
	public abstract String getReaderName();
	
	/**
	 * Activates channel and starts acquisition process.
	 */
	public abstract void start();
	
	/**
	 * Cleanup at end.
	 */
	public abstract void clean();
	
	/**
	 * Indicates if channel has done receiving all tags due to a termination
	 * condition.
	 */
	public abstract boolean isDone();
	
	/**
	 * Indicates if channel has more tags to read.
	 */
	public abstract boolean hasTags();
	
	/**
	 * Blocking read access to get tag data array containing the EPC code.
	 * 
	 * @return the tag array of bytes of the TAG
	 */
	public abstract Tag nextTag();
	
	
	/**
	 * Determines the condition that started the reading process.
	 * @return 
	 */
	public abstract ECInitiationCondition getInitCondition();
	
	/**
	 * Determines what trigger started the reading process.
	 * @return 
	 */
	public abstract ECTrigger getInitTrigger();
	
	/**
	 * Determines the condition that stopped the reading process.
	 * @return
	 */
	public abstract ECTerminationCondition getTermCondition();
	
	/**
	 *  Determines what trigger stopped the reading process.
	 * @return
	 */
	public abstract ECTrigger getTermTrigger();

}