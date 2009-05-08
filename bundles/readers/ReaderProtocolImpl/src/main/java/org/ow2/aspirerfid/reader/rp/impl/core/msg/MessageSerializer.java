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

import org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.Notification;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.Reply;

/**
 * A <code>MessageSerializer</code> serializes the intermediate representation (IR) 
 * of a <code>Reply</code> into a String message.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 */
public interface MessageSerializer {
	/**
	 * Serializes a reply into a string.
	 * @param r Reply to be serialised.
	 * @return serialized reply as a string
	 */
	public String serialize(Reply r) throws MessageSerializingException;

	/**
	 * Serializes a notification into a string.
	 * @param n Notification to be serialised.
	 * @return serialized notification as a string
	 */
	public String serialize(Notification n) throws MessageSerializingException;

}
