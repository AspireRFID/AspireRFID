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

package org.ow2.aspirerfid.reader.rp.impl.core.msg;

/**
 * This is a helper class because there are no Enums in Java 1.4.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 */
public final class MessageFormat {

	/** Unique constant representing the XML format. */
	private static final int XML_FORMAT = 1;

	/** Unique constant representing the TEXT format. */
	private static final int TEXT_FORMAT = 2;

	/** Message format is XML. */
	public static final MessageFormat XML = new MessageFormat(XML_FORMAT);

	/** Message format is Text. */
	public static final MessageFormat TEXT = new MessageFormat(TEXT_FORMAT);

	/** The message format. */
	private int messageFormat;

	/**
	 * Private constructor to hide the default construction from public use.
	 * 
	 * @param value
	 *            The message type defined by the constants XML_FORMAT and
	 *            TEXT_FORMAT.
	 */
	private MessageFormat(final int value) {
		this.messageFormat = value;
	}
}
