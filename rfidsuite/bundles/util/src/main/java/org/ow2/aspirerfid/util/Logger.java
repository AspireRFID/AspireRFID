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
package org.ow2.aspirerfid.util;

/**
 * Logger
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class Logger {

	/**
	 * Log Level ERROR.
	 */
	public static final int ERROR = 1;

	/**
	 * Log Level WARNING.
	 */
	public static final int WARNING = 2;

	/**
	 * Log Level INFO.
	 */
	public static final int INFO = 3;

	/**
	 * Log Level DEBUG.
	 */
	public static final int DEBUG = 4;

	private String name;

	private int level;

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            name of the logger
	 * @param level
	 *            trace level
	 */
	public Logger(String name, int level) {
		this.name = name;
		this.level = level;
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param level
	 * @param msg
	 */
	public void log(int level, String msg) {
		String message = null;
		if (this.level >= level) {
			switch (level) {
			case DEBUG:
				message = "[" + name + "] DEBUG: " + msg;
				System.err.println(message);
				break;
			case ERROR:
				message = "[" + name + "] ERROR: " + msg;
				System.err.println(message);
				break;
			case INFO:
				message = "[" + name + "] INFO: " + msg;
				System.err.println(message);
				break;
			case WARNING:
				message = "[" + name + "] WARNING: " + msg;
				System.err.println(message);
				break;
			default:
				System.err.println("[" + name + "] UNKNOWN[" + level + "]: "
						+ msg);
				break;
			}
		}
	}

}
