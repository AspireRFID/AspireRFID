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
package org.ow2.aspirerfid.ws.report;

import org.ow2.aspirerfid.event.export.api.ExportEventDispatcher;
import org.ow2.aspirerfid.util.HashtableParser;

/**
 * This class registers a service which is provided as a web service
 * 
 * @author Fran�ois Fornaciari
 * @version 2007
 */
public class ReportServiceImpl implements ReportService {

	private ExportEventDispatcher exportEventDispatcher;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.ws.report.ReportService#pushXmlReport(java.lang.String)
	 */
	public void pushXmlReport(String data) {
		if (exportEventDispatcher != null) {
			try {
				exportEventDispatcher.receive(HashtableParser
						.stringToHashtable(data));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
