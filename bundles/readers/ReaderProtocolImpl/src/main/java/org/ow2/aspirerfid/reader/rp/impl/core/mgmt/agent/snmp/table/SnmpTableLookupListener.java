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

package org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table;

import org.snmp4j.agent.MOServerLookupEvent;
import org.snmp4j.agent.MOServerLookupListener;

/**
 * An object of this class performs an update of a <code>SnmpTable</code> on
 * callback notifications of lookup events on a <code>MOServer</code>
 * instance.
 */
class SnmpTableLookupListener implements MOServerLookupListener {
	
	/**
	 * The table
	 */
	private SnmpTable table;
	
	/**
	 * The constructor.
	 * 
	 * @param table
	 *            The <code>SnmpTable</code> which has to be updated on lookup
	 *            events
	 */
	public SnmpTableLookupListener(SnmpTable table) {
		this.table = table;
	}
	
	/**
	 * A <code>MOServer</code> instance has lookup up a managed object for
	 * which the listener has been registered.
	 * 
	 * @param event
	 *            A <code>MOServerLookupEvent</code> describing the lookup
	 *            query and the managed object that has been looked up
	 */
	public void lookupEvent(MOServerLookupEvent event) {
		table.update();
	}
	
	public void queryEvent(MOServerLookupEvent event)
	{
		table.update();
	}
}
