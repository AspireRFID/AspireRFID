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
 * ParameterMissingException is used together with <code>Parameter</code> and its 
 * child classes. Exception occurs when a parameter is missing.
 * 
 * @see org.ow2.aspirerfid.reader.rp.impl.core.msg.command.Parameter
 * @see org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ValueParameter
 * @see org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ListParameter
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 *
 */
public class ParameterMissingException extends TextCommandParserException {

	/**
	 * the serialVersionUID
	 */
	private static final long serialVersionUID = -1692001837878722167L;

	public ParameterMissingException(String msg) {
		super(msg);
	}
}
