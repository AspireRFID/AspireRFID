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

package org.ow2.aspirerfid.reader.rp.impl.core.msg.reply;

import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ErrorType;

/**
 * @author regli
 */
public class TextReplyParserHelper {

	public static ObjectFactory replyFactory = new ObjectFactory(); 

	private String id;
	private int statusCode;
	private ErrorType errorType;
	private ArrayList values = new ArrayList();
	
	public void setId(String id) {
		this.id = id;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}

	public void addValue(String value) {
		values.add(value);
	}
	
	public Reply buildReplyTree() {
		if (statusCode > 0 && errorType == null) {
			errorType = new ErrorType();
		}
		Reply reply = replyFactory.createReply();
		reply.setId(id);
		reply.setResultCode(statusCode);
		reply.setError(errorType);
		reply.setAny(values.toArray(new String[0]));
		return reply;
	}

}