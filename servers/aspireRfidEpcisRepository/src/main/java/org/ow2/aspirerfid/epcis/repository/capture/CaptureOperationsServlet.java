/*
 * Copyright � 2008-2010, Aspire 
 *
 * This file contains the source code of the Accada library by ETH Zurich (www.accada.org),
 * licensed under the terms of the GNU Lesser General Public License version 2.1 in 2007
 * and modified for the needs of the Aspire project.
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
 */

package org.ow2.aspirerfid.epcis.repository.capture;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.ow2.aspirerfid.epcis.repository.InvalidFormatException;
import org.xml.sax.SAXException;

/**
 * This CaptureOperationsServlet accepts and analyzes HTTP POST requests and
 * delegates them to the appropriate handler methods in the
 * CaptureOperationsModule. This servlet also initializes the
 * CaptureOperationsModule properly and returns a simple information page upon
 * GET requests.
 * 
 * @author Marco Steybe
 * @author Nikos Kefalakis(nkef)
 */
public class CaptureOperationsServlet extends HttpServlet {

    private static final long serialVersionUID = -5765052834995535731L;

    private static final String APP_CONFIG_LOCATION = "appConfigLocation";
    private static final String PROP_INSERT_MISSING_VOC = "insertMissingVoc";
    private static final String PROP_DB_RESET_ALLOWED = "dbResetAllowed";
    private static final String PROP_DB_RESET_SCRIPT = "dbResetScript";
    private static final String PROP_EPCIS_SCHEMA_FILE = "epcisSchemaFile";
    private static final String PROP_EPCIS_MASTER_DATA_SCHEMA_FILE = "epcisMasterDataSchemaFile";

    private static final Log LOG = LogFactory.getLog(CaptureOperationsServlet.class);

    private CaptureOperationsModule captureOperationsModule;

    /**
     * {@inheritDoc}
     */
    public void init() {
        LOG.debug("Fetching capture operations module from servlet context ...");
        CaptureOperationsModule captureOperationsModule = (CaptureOperationsModule) getServletContext().getAttribute(
                "captureOperationsModule");
        if (captureOperationsModule == null) {
            LOG.debug("Capture operations module not found - initializing manually");
            captureOperationsModule = new CaptureOperationsModule();

            ServletConfig servletConfig = getServletConfig();
            Properties props;
            if (servletConfig == null) {
                props = loadApplicationProperties();
            } else {
                props = loadApplicationProperties(servletConfig);
            }
            SessionFactory hibernateSessionFactory = initHibernate();
            captureOperationsModule.setSessionFactory(hibernateSessionFactory);
            captureOperationsModule.setInsertMissingVoc(Boolean.parseBoolean(props.getProperty(PROP_INSERT_MISSING_VOC,
                    "true")));
            captureOperationsModule.setDbResetAllowed(Boolean.parseBoolean(props.getProperty(PROP_DB_RESET_ALLOWED,
                    "false")));
            captureOperationsModule.setDbResetScript(props.getProperty(PROP_DB_RESET_SCRIPT));
            captureOperationsModule.setEpcisSchemaFile(props.getProperty(PROP_EPCIS_SCHEMA_FILE));
            //added by nkef
            captureOperationsModule.setEpcisSchemaFile(props.getProperty(PROP_EPCIS_MASTER_DATA_SCHEMA_FILE));
        } else {
            LOG.debug("Capture operations module found");
        }
        setCaptureOperationsModule(captureOperationsModule);
    }

    /**
     * Loads the application properties and populates a java.util.Properties
     * instance.
     * 
     * @param servletConfig
     *            The ServletConfig used to locate the application property
     *            file.
     * @return The application properties.
     */
    private Properties loadApplicationProperties(ServletConfig servletConfig) {
        // read application properties from servlet context
        ServletContext ctx = servletConfig.getServletContext();
        String path = ctx.getRealPath("/");
        String appConfigFile = ctx.getInitParameter(APP_CONFIG_LOCATION);
        Properties properties = new Properties();
        try {
            InputStream is = new FileInputStream(path + appConfigFile);
            properties.load(is);
            is.close();
            LOG.info("Loaded application properties from " + path + appConfigFile);
        } catch (IOException e) {
            LOG.error("Unable to load application properties from " + path + appConfigFile, e);
        }
        return properties;
    }

    /**
     * Loads the application properties from classpath and populates a
     * java.util.Properties instance.
     * 
     * @param servletConfig
     *            The ServletConfig used to locate the application property
     *            file.
     * @return The application properties.
     */
    private Properties loadApplicationProperties() {
        // read application properties from classpath
        String resource = "/application.properties";
        InputStream is = this.getClass().getResourceAsStream(resource);
        Properties properties = new Properties();
        try {
            properties.load(is);
            is.close();
            LOG.info("Loaded application properties from classpath:" + resource + " ("
                    + this.getClass().getResource(resource) + ")");
        } catch (IOException e) {
            LOG.error("Unable to load application properties from classpath:" + resource + " ("
                    + this.getClass().getResource(resource) + ")", e);
        }
        return properties;
    }

    /**
     * Initializes Hibernate. Reads the configuration from hibernate.cfg.xml
     * located on the classpath (WEB-INF/classes/)
     * 
     * @return The Hibernate SessionFactory.
     * @throws ServletException
     */
    private SessionFactory initHibernate() throws HibernateException {
        LOG.info("Manually initializing Hibernate");
        Configuration c = new Configuration();
        c.configure(); // from WEB-INF/classes/hibernate.cfg.xml
        return c.buildSessionFactory();
    }

    /**
     * Returns a simple information page.
     * 
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     * @param req
     *            The HttpServletRequest.
     * @param rsp
     *            The HttpServletResponse.
     * @throws IOException
     *             If an error occurred while writing the response.
     */
    public void doGet(final HttpServletRequest req, final HttpServletResponse rsp) throws IOException {
        final PrintWriter out = rsp.getWriter();

        // return an HTML info page
        rsp.setContentType("text/html");

        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"");
        out.println("   \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        out.println("<html>");
        out.println("<head><title>EPCIS Capture Service</title></head>");
        out.println("<body>");
        out.println("<p>This service captures EPCIS events sent to it using HTTP POST requests.<br />");
        out.println("The payload of the HTTP POST request is expected to be an XML document conforming to the EPCISDocument schema.</p>");
        out.println("<p>For further information refer to the xml schema files or check the Example <br />");
        out.println("in 'EPC Information Services (EPCIS) Version 1.0 Specification', Section 9.6.</p>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
    }

    /**
     * Implements the EPCIS capture operation. Takes HTTP POST request, extracts
     * the payload into an XML document, validates the document against the
     * EPCIS schema, and captures the EPCIS events given in the document. Errors
     * are caught and returned as simple plaintext messages via HTTP.
     * 
     * @param req
     *            The HttpServletRequest.
     * @param rsp
     *            The HttpServletResponse.
     * @throws IOException
     *             If an error occurred while validating the request or writing
     *             the response.
     */
    public void doPost(final HttpServletRequest req, final HttpServletResponse rsp) throws IOException {
        LOG.info("EPCIS Capture Interface invoked.");
        rsp.setContentType("text/plain");
        final PrintWriter out = rsp.getWriter();

        InputStream is = null;
        // check if we have a POST request with form parameters
        if ("application/x-www-form-urlencoded".equalsIgnoreCase(req.getContentType())) {
            // check if the 'event' or 'dbReset' form parameter are given
            String event = req.getParameter("event");
            String dbReset = req.getParameter("dbReset");
            if (event != null) {
                LOG.info("Found deprecated 'event=' parameter. Refusing to process request.");
                String msg = "Starting from version 0.2.2, the EPCIS repository does not accept the EPCISDocument in the HTTP POST form parameter 'event' anymore. Please provide the EPCISDocument as HTTP POST payload instead.";
                rsp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                out.println(msg);
            } else if (dbReset != null && dbReset.equalsIgnoreCase("true")) {
                LOG.debug("Found 'dbReset' parameter set to 'true'.");
                rsp.setContentType("text/plain");
                try {
                    captureOperationsModule.doDbReset();
                    String msg = "db reset successfull";
                    LOG.info(msg);
                    rsp.setStatus(HttpServletResponse.SC_OK);
                    out.println(msg);
                } catch (SQLException e) {
                    String msg = "An error involving the database occurred";
                    LOG.error(msg, e);
                    rsp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    out.println(msg);
                } catch (IOException e) {
                    String msg = "An unexpected error occurred";
                    LOG.error(msg, e);
                    rsp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    out.println(msg);
                } catch (UnsupportedOperationException e) {
                    String msg = "'dbReset' operation not allowed!";
                    LOG.info(msg);
                    rsp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    out.println(msg);
                }
            }
            out.flush();
            out.close();
            return;
        } else {
            is = req.getInputStream();
            try {
                captureOperationsModule.doCapture(is, req.getUserPrincipal());
                rsp.setStatus(HttpServletResponse.SC_OK);
                out.println("Capture request succeeded.");
            } catch (SAXException e) {
                String msg = "An error processing the XML document occurred";
                LOG.error(msg, e);
                rsp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println(msg);
            } catch (InvalidFormatException e) {
                String msg = "An error parsing the XML contents occurred";
                LOG.error(msg, e);
                rsp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println(msg);
            } catch (final Exception e) {
                String msg = "An unexpected error occurred";
                LOG.error(msg, e);
                rsp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.println(msg);
            }

            out.flush();
            out.close();
        }
    }

    public void setCaptureOperationsModule(CaptureOperationsModule captureOperationsModule) {
        this.captureOperationsModule = captureOperationsModule;
    }
}
