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

package org.ow2.aspirerfid.reader.rp.impl.core.msg.command;

/**
 * CommandNotSupportedException is used together with <code>TextParserHelper</code>.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 *
 */
public class CommandNotSupportedException extends TextCommandParserException {

	/**
	 * the serialVersionUID
	 */
	private static final long serialVersionUID = 2038462841058893189L;

	public CommandNotSupportedException(String msg) {
		super(msg);
	}
}
