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
package org.ow2.aspirerfid.event.export.ws;

import java.net.MalformedURLException;
import java.util.Map;

import org.codehaus.xfire.client.Client;
import org.osgi.framework.BundleContext;
import org.ow2.aspirerfid.event.export.api.ExportEventSpecialized;
import org.ow2.aspirerfid.event.export.uri.BadUriFormatException;
import org.ow2.aspirerfid.event.export.uri.NotExpectedProtocolException;
import org.ow2.aspirerfid.event.export.uri.SoapUri;
import org.ow2.aspirerfid.util.Logger;
import org.ow2.aspirerfid.util.RFIDConstants;

/**
 * Implementation of the Export Event Web Service.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class ExportEventWSImpl implements ExportEventSpecialized {

	/**
	 * Logger
	 */
	private Logger logger;

	/**
	 * @param context
	 *            TODO Javadoc
	 */
	public ExportEventWSImpl(BundleContext context) {
		this.logger = new Logger("Export WS", Logger.INFO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.event.export.api.ExportEventSpecialized#getType()
	 */
	public String getType() {
		return ExportEventSpecialized.TYPE_WS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.event.export.api.ExportEventSpecialized#publish(java.util.Map)
	 */
	public void publish(Map report) {
		SoapUri uri = getUri(report);
		try {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			Thread.currentThread().setContextClassLoader(
					this.getClass().getClassLoader());
			Client client = new Client(uri.getHttpAddress());
			client.invoke("pushXmlReport", new Object[] { report.toString() });
			client.close();
			Thread.currentThread().setContextClassLoader(classLoader);
		} catch (Exception e) {
			logger.log(Logger.ERROR, "Report could not be sent");
		}
	}

	/**
	 * Build a Soap Uri from a received message
	 * 
	 * @param event
	 *            TODO Javadoc
	 * @return the Soap Uri
	 */
	private SoapUri getUri(Map report) {
		String uri = (String) report.get(RFIDConstants.DEST_URI);
		SoapUri soapUri = null;
		try {
			soapUri = new SoapUri(uri);
		} catch (NotExpectedProtocolException e3) {
			logger.log(Logger.ERROR, "getUri : Not ExpectedProtocol");
			e3.printStackTrace();
		} catch (BadUriFormatException e3) {
			logger.log(Logger.ERROR, "getUri : BadUriFormatException");
			e3.printStackTrace();
		} catch (MalformedURLException e) {
			logger.log(Logger.ERROR, "getUri : MalformedURLException");
			e.printStackTrace();
		}

		return soapUri;
	}

}
