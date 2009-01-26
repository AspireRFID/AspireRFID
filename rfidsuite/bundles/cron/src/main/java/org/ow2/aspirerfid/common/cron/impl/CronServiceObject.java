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
package org.ow2.aspirerfid.common.cron.impl;

import java.io.Serializable;

import org.ow2.aspirerfid.common.cron.TimedObject;

/**
 * Class CronServiceObject contains all the informations needed to a new
 * schedule operation
 * 
 * @author Unknown
 * @version 2006
 */
public class CronServiceObject {
	private TimedObject timedObject;
	private Serializable serializable;
	private CronParse cron;
	/**
	 * TODO Javadoc
	 */
	public long ttbc;

	/**
	 * Class constructor
	 * 
	 * @param timedObject
	 *            the business object
	 * @param serializable
	 *            the serializable to run according to time conditions
	 * @param cron
	 *            the time condition in a cron format
	 */
	public CronServiceObject(TimedObject timedObject,
			Serializable serializable, CronParse cron) {
		this.timedObject = timedObject;
		this.serializable = serializable;
		this.cron = cron;
	}

	/**
	 * This method return the serializable the CronServiceObject run when
	 * calling the execute command
	 * 
	 * @return the serializable number
	 */
	public Serializable getSerializable() {
		return serializable;
	}

	/**
	 * This method return the business object
	 * 
	 * @return the business object
	 */
	public TimedObject getTimedObject() {
		return timedObject;
	}

	/**
	 * This method set the serializable to run when calling the execute command
	 * 
	 * @param serializable
	 *            the serializable to set
	 */
	public void setSerializable(Serializable serializable) {
		this.serializable = serializable;
		;
	}

	/**
	 * This method set the business object
	 * 
	 * @param object
	 *            the business object to set
	 */
	public void setTimedObject(TimedObject object) {
		timedObject = object;
	}

	/**
	 * This method return the CronParse object
	 * 
	 * @return the CronParse object
	 */
	public CronParse getCron() {
		return cron;
	}

	/**
	 * This method set the CronParse object
	 * 
	 * @param parse
	 *            the object that parse the cron
	 */
	public void setCron(CronParse parse) {
		cron = parse;
	}

	/**
	 * This command call the doReact method of the business object
	 */
	public void execute() {
		timedObject.doReact(serializable);
	}

	/**
	 * Returns the next.
	 * 
	 * @return long the next time to doReact
	 */
	public long getNext() {
		ttbc = cron.getNext();
		return ttbc;
	}

}
