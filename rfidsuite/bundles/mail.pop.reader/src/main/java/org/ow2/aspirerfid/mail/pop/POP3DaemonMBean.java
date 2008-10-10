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

/**
 * POP3 Daemon MBean
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public interface POP3DaemonMBean {
	/**
	 * Sets time between each mail checking
	 * 
	 * @param time
	 *            time in milliseconds
	 */
	public void setCallbackTime(int time);

	/**
	 * Gets time between each mail checking
	 * 
	 * @return time time in milliseconds
	 */
	public int getCallbackTime();

	/**
	 * @param host
	 *            TODO Javadoc
	 */
	public void setHost(String host);

	/**
	 * @return TODO Javadoc
	 */
	public String getHost();

	/**
	 * @param port
	 *            TODO Javadoc
	 */
	public void setPort(int port);

	/**
	 * @return TODO Javadoc
	 */
	public int getPort();

	/**
	 * @param login
	 *            TODO Javadoc
	 */
	public void setLogin(String login);

	/**
	 * @return TODO Javadoc
	 */
	public String getLogin();

	/**
	 * @param pass
	 *            TODO Javadoc
	 */
	public void setPass(String pass);

	/**
	 * @return TODO Javadoc
	 */
	public String getPass();

	/**
	 * @param lvl
	 *            TODO Javadoc
	 */
	public void setDebugLevel(int lvl);

	/**
	 * @return TODO Javadoc
	 */
	public int getDebugLevel();
}
