/**
 * Copyright (c) 2008-2010, Aspire 
 * 
 * Aspire is free software; you can redistribute it and/or 
 * modify it under  the terms of the GNU Lesser General Public 
 * License version 2.1 as published by the Free Software Foundation (the 
 * "LGPL"). 
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this library in the file COPYING-LGPL-2.1; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301 USA. 
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY 
 * OF ANY KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations. 
 * 
 */

package org.ow2.aspirerfid.connectors.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.ow2.aspirerfid.commons.utils.Configurator;

/**
 * A servlet to handle simple registration operations via POST or GET requests
 * when the connector client is deployed within an application server context.
 * 
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 *
 */
public class RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = -2761455575084503289L;
    private static Logger logger;
    
    static {
	logger = Logger.getLogger(RegistrationServlet.class);
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
	super();
	try {
	    Configurator.loadProperties("application.properties", RegistrationServlet.class);
	} catch (IOException e) {
	    logger.fatal(e);
	}
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	// super.doGet(request, response);
	String tid, sid, type;
	boolean resp = false;
	sid = request.getParameter("sid");
	if (sid != null) {
	    tid = request.getParameter("tid");
	    type = request.getParameter("type");
	    if (request.getParameter("sub") != null && tid != null && type != null) {
		resp = doRegister(tid, type, sid);
	    }
	    if (request.getParameter("unsub") != null) {
		resp = doUnregister(sid);
	    }
	}

	response.getWriter().print(resp);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String tid, sid, type;
	boolean resp = false;
	sid = request.getParameter("sid");
	if (sid != null) {
	    tid = request.getParameter("tid");
	    type = request.getParameter("type");
	    if (request.getParameter("sub") != null && tid != null && type != null) {
		resp = doRegister(tid, type, sid);
	    }
	    if (request.getParameter("unsub") != null) {
		resp = doUnregister(sid);
	    }
	}

	response.getWriter().print(resp);
    }

    private boolean doRegister(String tid, String type, String sid) {
	return RegistrationManager.register(tid, type, sid);
    }

    private boolean doUnregister(String sid) {
	return RegistrationManager.unregister(sid);
    }
}
