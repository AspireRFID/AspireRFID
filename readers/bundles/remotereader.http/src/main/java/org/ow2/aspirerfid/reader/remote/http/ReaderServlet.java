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
 */package org.ow2.aspirerfid.reader.remote.http;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
/**
 * Servlet that works as a simple HTTP interface for receiving
 * RFID tag reading from external applications. <br>
 * Information will be provided via HTTP GET, as in the sample querystring:<br>
 * id=35654321000000112345678B&timestamp=34988728748728&readerid=readerX
 *
 */
public class ReaderServlet extends HttpServlet {
	private HttpService httpService;
	private static final String SEPARATOR = " ";

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RfidPropertyAdapter propAdapter = new RfidPropertyAdapter(getRequestParameters(req));
		if (!propAdapter.isValid()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, 
					"MandatoryFieldSetIncomplete " + getMandatoryParamString());
		} else {
			RfidHttpReader.getInstance().sendEvent(propAdapter.getTagRead()); 
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
	}
	
	//Using this method since there is no getParameterMap() in servlet 2.1 API
	private Map getRequestParameters(HttpServletRequest req) {
		Map map = new HashMap();
		Enumeration paramNames = req.getParameterNames();
		
		while (paramNames.hasMoreElements()) {
			String key = (String)paramNames.nextElement();
			map.put(key, req.getParameter(key));
		}
		return map;
	}

	private String getMandatoryParamString() {
		StringBuilder sb = new StringBuilder();
		for (String mandatoryField : RfidPropertyAdapter.MANDATORY_FIELDS) {
			sb.append(mandatoryField).append(SEPARATOR);
		}
		if (sb.length() > 0) {
			sb.delete(sb.length() - 1, sb.length());
		}
		return sb.toString();
	}
	private void bind() {
		System.out.println("binding to: " + httpService);
		// TODO Auto-generated method stub
		try {
			httpService.registerServlet("/HttpTagReader", new ReaderServlet(), null, null);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamespaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}