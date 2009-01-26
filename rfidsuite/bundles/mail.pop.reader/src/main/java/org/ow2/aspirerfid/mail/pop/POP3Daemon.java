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
package org.ow2.aspirerfid.mail.pop;

import java.io.IOException;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Properties;

import org.ow2.aspirerfid.common.cron.CronService;
import org.ow2.aspirerfid.common.cron.TimedObject;
import org.ow2.aspirerfid.event.export.api.ExportEventDispatcher;
import org.ow2.aspirerfid.util.HashtableParser;
import org.ow2.aspirerfid.util.Logger;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class POP3Daemon implements POP3DaemonMBean, TimedObject {

	//
	// Constantes
	//
	private static int DEFAULT_CALLBACK_TIMER = 10000; // in milliseconds

	//
	// Privates variables
	//
	private POP3Helper popHelper = null;

	private ExportEventDispatcher dispatcher = null;

	private CronService cron = null;

	private int callbackTime;

	private Logger log;

	/**
	 * Constructor
	 */
	public POP3Daemon() {
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getResourceAsStream("/mail.properties"));
		} catch (IOException e) {
			log.log(Logger.ERROR, "properties file not found");
			e.printStackTrace();
		}

		popHelper = new POP3Helper(prop);
		callbackTime = DEFAULT_CALLBACK_TIMER;

		log = new Logger("POP3Daemon", popHelper.getDebugLevel());
	}

	//
	// Publics methods
	//
	/**
	 * Check if new mail is coming and send them to dispatcher
	 */
	public void processMail() {
		String[] mails = popHelper.GetAndRemoveAllMessages();

		Hashtable msg = null;

		if (mails != null && dispatcher != null) {
			for (int i = 0; i < mails.length; i++) {
				mails[i] = popHelper.removeMailHeader(mails[i]);
				try {
					msg = HashtableParser.stringToHashtable(mails[i]);
					if (dispatcher != null) {
						dispatcher.receive(msg);
					} else {
						log
								.log(Logger.WARNING,
										"Message cannot be send, dispatcher unavailable");
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.log(Logger.ERROR, "=========== Mail body ============");
					log.log(Logger.ERROR, mails[i]);
					log.log(Logger.ERROR, "==================================");
					log.log(Logger.ERROR,
							"Mail body can't be parsed to hashtable");
				}
			}
		}
	}

	/**
	 * Method call by cron service
	 * 
	 * @param serializable
	 *            TODO Javadoc
	 */
	public void doReact(Serializable serializable) {
		processMail();
	}

	//
	// iPOJO methods
	//
	/**
	 * function call by iPOJO to active the bundle
	 */
	public void start() {
		this.cron.add(this, "popDaemon", cron.cronString(callbackTime));
	}

	/**
	 * function call by iPOJO to deactivate the bundle
	 */
	public void stop() {
		if (cron != null) {
			cron.remove(this, "popDaemon");
		}
	}

	//
	// POP3DaemonMBean
	//
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.mail.pop.POP3DaemonMBean#getCallbackTime()
	 */
	public int getCallbackTime() {
		return callbackTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.mail.pop.POP3DaemonMBean#setCallbackTime(int)
	 */
	public void setCallbackTime(int time) {
		cron.remove(this, "popDaemon");
		callbackTime = time;
		cron.add(this, "popDaemon", cron.cronString(time));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.mail.pop.POP3DaemonMBean#getHost()
	 */
	public String getHost() {
		return popHelper.getHost();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.mail.pop.POP3DaemonMBean#setHost(java.lang.String)
	 */
	public void setHost(String host) {
		popHelper.setHost(host);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.mail.pop.POP3DaemonMBean#getLogin()
	 */
	public String getLogin() {
		return popHelper.getLogin();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.mail.pop.POP3DaemonMBean#setLogin(java.lang.String)
	 */
	public void setLogin(String login) {
		popHelper.setLogin(login);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.mail.pop.POP3DaemonMBean#getPass()
	 */
	public String getPass() {
		return popHelper.getPass();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.mail.pop.POP3DaemonMBean#setPass(java.lang.String)
	 */
	public void setPass(String pass) {
		popHelper.setPass(pass);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.mail.pop.POP3DaemonMBean#getPort()
	 */
	public int getPort() {
		return popHelper.getPort();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.mail.pop.POP3DaemonMBean#setPort(int)
	 */
	public void setPort(int port) {
		popHelper.setPort(port);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.mail.pop.POP3DaemonMBean#getDebugLevel()
	 */
	public int getDebugLevel() {
		return popHelper.getDebugLevel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.mail.pop.POP3DaemonMBean#setDebugLevel(int)
	 */
	public void setDebugLevel(int lvl) {
		log = new Logger("POP3Daemon", lvl);
		popHelper.setDebugLevel(lvl);
	}
}
