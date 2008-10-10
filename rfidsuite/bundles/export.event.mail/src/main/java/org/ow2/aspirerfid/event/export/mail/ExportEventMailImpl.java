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

import java.util.Map;

import org.osgi.framework.BundleContext;
import org.ow2.aspirerfid.event.export.api.ExportEventSpecialized;
import org.ow2.aspirerfid.event.export.uri.BadUriFormatException;
import org.ow2.aspirerfid.event.export.uri.NotExpectedProtocolException;
import org.ow2.aspirerfid.event.export.uri.SmtpUri;
import org.ow2.aspirerfid.util.Logger;
import org.ow2.aspirerfid.util.RFIDConstants;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class ExportEventMailImpl implements ExportEventSpecialized {

	private MailSender mailSender;

	private Logger logger;

	/**
	 * @param context
	 *            TODO Javadoc
	 * @throws Exception
	 */
	public ExportEventMailImpl(BundleContext context) throws Exception {
		this.logger = new Logger("Export MAIL", Logger.INFO);
		mailSender = new MailSender(context, logger);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.event.export.api.ExportEventSpecialized#publish(java.util.Map)
	 */
	public void publish(Map report) {
		SmtpUri uri = getUri(report);
		mailSender.sendMail(uri.getHost(), uri.getPort(), uri.getMailFrom(),
				uri.getMailTo(), (String) report.get("event.topics"), report
						.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.event.export.api.ExportEventSpecialized#getType()
	 */
	public String getType() {
		return ExportEventSpecialized.TYPE_SMTP;
	}

	private SmtpUri getUri(Map event) {
		String uri = (String) event.get(RFIDConstants.DEST_URI);
		SmtpUri smtpUri = null;
		try {
			smtpUri = new SmtpUri(uri);
		} catch (NotExpectedProtocolException e3) {
			logger.log(Logger.ERROR, "getUri : Not ExpectedProtocol");
			e3.printStackTrace();
		} catch (BadUriFormatException e3) {
			logger.log(Logger.ERROR, "getUri : BadUriFormatException");
			e3.printStackTrace();
		}

		return smtpUri;
	}
}
