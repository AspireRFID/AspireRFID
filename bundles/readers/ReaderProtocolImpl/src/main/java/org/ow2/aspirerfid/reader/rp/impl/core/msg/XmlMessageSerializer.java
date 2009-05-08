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

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.notification.Notification;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.Reply;
import org.apache.log4j.Logger;

/**
 * The <code>XmlMessageSerializer</code> serializes an reply or notification
 * object into an outgoing XML message.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 */
public class XmlMessageSerializer implements MessageSerializer {
	// ====================================================================
	// ---------------------------- Fields ------------------------------//
	// ====================================================================

	/** String which defines the command package used for the JAXBContext. */
	private static final String REPLY_PACKAGE = "org.ow2.aspirerfid.reader.rp.impl.core.msg.reply";

	/** String which defines the command package used for the JAXBContext. */
	private static final String NOTIFICATION_PACKAGE = "org.ow2.aspirerfid.reader.rp.impl.core.msg.notification";

	/** The logger. */
	static private Logger log;

	/** JAXBContext capable of handling classes generated into the reply package. */
	private JAXBContext replyContext = null;

	/**
	 * JAXBContext capable of handling classes generated into the notification
	 * package.
	 */
	private JAXBContext notificationContext = null;

	/** Unmarshaller for the XML binding for the reply object. */
	private Marshaller replyMarshaller = null;

	/** Unmarshaller for the XML binding for the notification object. */
	private Marshaller notificationMarshaller = null;

	// ====================================================================
	// ------------------------- Constructor ----------------------------//
	// ====================================================================
	/**
	 * Constructor for a XmlMessageSerializer.
	 */
	public XmlMessageSerializer() {
		log = Logger.getLogger(getClass().getName());
		try {
			replyContext = JAXBContext.newInstance(REPLY_PACKAGE, getClass().getClassLoader());

			replyMarshaller = replyContext.createMarshaller();
			replyMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);

			notificationContext = JAXBContext.newInstance(NOTIFICATION_PACKAGE, getClass().getClassLoader());

			notificationMarshaller = notificationContext.createMarshaller();
			notificationMarshaller.setProperty(
					Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		} catch (JAXBException e) {
			log.error(e);
		}
	}

	// ====================================================================
	// ------------------------- Methods --------------------------------//
	// ====================================================================

	/**
	 * Serializes a reply into an XML string.
	 * 
	 * @param r
	 *            Reply to be serialised.
	 * @return serialized reply as an XML string
	 */
	public String serialize(final Reply r) throws MessageSerializingException {
		// Marshal into a StringBuffer
		StringWriter sw = new StringWriter();
		try {
			replyMarshaller.marshal(r, sw);
		} catch (JAXBException e) {
			throw new MessageSerializingException(e);
		}
		return sw.getBuffer().toString();

	}

	/**
	 * Serializes a notification into an XML string.
	 * 
	 * @param n
	 *            Notification to be serialised.
	 * @return serialized notification as a XML string
	 * @throws MessageSerializingException
	 * 
	 */
	public String serialize(Notification n) throws MessageSerializingException {
		// Marshal into a StringBuffer
		StringWriter sw = new StringWriter();
		try {
			notificationMarshaller.marshal(n, sw);
		} catch (JAXBException e) {
			throw new MessageSerializingException(e);
		}
		return sw.getBuffer().toString();
	}

}
