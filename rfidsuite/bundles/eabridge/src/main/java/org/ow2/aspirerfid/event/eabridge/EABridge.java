/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.event.eabridge;

import org.osgi.service.event.EventHandler;

/**
 * This interface define a Event Admin (OSGi R4) Bridge
 * 
 * @author mike oullion
 * @author gilles broussillon
 * @version 0.1
 */
public interface EABridge extends EventHandler {

	/**
	 * Method used to designate topics which must be send on the MOM
	 * 
	 * @param topic
	 *            a String[] of specific topics you want to export
	 */
	public void exportTopics(String[] topic);

	/**
	 * Method used to undesignate topics which must be sent through JMS
	 * 
	 * @param topic
	 *            a String[] of specific topics you want to unexport
	 */
	public void unexportTopics(String[] topic);

	/**
	 * Method used to designate topics which must NOT be export
	 * 
	 * @param topic
	 *            a String[] to designate the appropriate topics
	 */
	public void exportableTopics(String[] topic);

	/**
	 * Method used to designate topics which now can be exported
	 * 
	 * @param topic
	 *            a String[] to designate the appropriate topics
	 */
	public void unexportableTopics(String[] topic);

	/**
	 * Method used to know all exported topics
	 * 
	 * @return a string's Tab which contain all exported topics
	 */
	public String[] getExportedTopics();

	/**
	 * Method used to know all exported topics
	 * 
	 * @return a string's Tab which contain all unexportable topics
	 */
	public String[] getUnexportableTopics();
}
