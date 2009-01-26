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
package org.ow2.aspirerfid.event.export.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.osgi.framework.BundleContext;
import org.ow2.aspirerfid.util.Logger;

/**
 * Send a mail
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class MailSender {
	private Logger logger;

	/**
	 * @param context
	 *            TODO Javadoc
	 * @param logger
	 */
	public MailSender(BundleContext context, Logger logger) {
		this.logger = logger;
	}

	/**
	 * @param _host
	 *            TODO Javadoc
	 * @param _port
	 * @param _from
	 * @param _to
	 * @param _subject
	 * @param _message
	 */
	public void sendMail(String _host, String _port, String _from, String _to,
			String _subject, String _message) {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", _host);
		props.setProperty("mail.smtp.port", _port);
		Session session = Session.getDefaultInstance(props, null);

		InternetAddress from = null;
		InternetAddress to = null;
		try {
			from = new InternetAddress(_from);
			to = new InternetAddress(_to);
		} catch (AddressException e) {
			e.printStackTrace();
		}

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(from);
			message.addRecipient(Message.RecipientType.TO, to);
			message.setSubject(_subject);
			message.setText(_message);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
