/**
 * Copyright (c) 2008-2010, Aspire 
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
 * 
 */

package org.ow2.aspirerfid.connectors.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import org.apache.log4j.Logger;
import org.ow2.aspirerfid.commons.connector.interfaces.QueryResultsProcessorIfce;

/**
 * This class implements a simple web server listening for responses from the
 * EPCIS Query Callback interface. The server is not multi-threaded, so it will
 * only accept one request at a time. It will only allow one instance
 * (singleton) and will be bound to a predefined port on localhost.
 * 
 * @author Marco Steybe
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 */
public final class QueryCallbackListener extends Thread {

    private static final int PORT = 8899;
    private static final Logger logger;

    private static QueryCallbackListener instance = null;

    private static ServerSocket server = null;

    private static boolean isRunning = false;

    private String response = null;
    
    private static QueryResultsProcessorIfce resultProcessor;

    static
    {
	logger = Logger.getLogger(QueryCallbackListener.class);
    }
    /**
     * Instantiates a new SubscriptionResponseListener listening on the given
     * port.
     * 
     * @throws IOException
     *             If an error setting up the communication socket occurred.
     */
    private QueryCallbackListener() throws IOException {
        logger.info("Listening for query callbacks on port " + PORT + " ...");
        server = new ServerSocket(PORT);
        resultProcessor = null;
    }

    /**
     * @return The only instance of this class (singleton).
     * @throws IOException
     *             If an error setting up the communication socket occurred.
     */
    public static QueryCallbackListener getInstance() throws IOException {
        if (instance == null) {
            instance = new QueryCallbackListener();
        }
        return instance;
    }
    
    public void setResultsProcessor(QueryResultsProcessorIfce con) 
    {
	resultProcessor = con;
    }
    
    public QueryResultsProcessorIfce getResultsProcessor()
    {
	return resultProcessor;
    }

    /**
     * Keeps this listener running until {@link #stopRunning()} is called.
     * 
     * @see java.lang.Thread#run()
     */
    public void run() {
        isRunning = true;
        while (isRunning) {
            Socket client = null;
            try {
                client = server.accept();
                logger.info("Reading query response");
                handleConnection(client);
            } catch (SocketException e) {
                // server socket closed (stopRunning was called)
            } catch (IOException e) {
        	logger.error(e);
                e.printStackTrace();
            } finally {
                if (client != null) {
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Handles an incoming HTTP connection, reading the contents, and parsing it
     * as XML.
     * 
     * @param client
     *            The client Socket.
     * @throws IOException
     *             If an I/O error occurred.
     */
    private void handleConnection(final Socket client) throws IOException {
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        InputStream is = client.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        logger.info("Handling response");
        // read content length
        String prefix = "content-length: ";
        String inputLine = in.readLine().toLowerCase();
        while (!inputLine.startsWith(prefix)) {
            // continue reading ...
            inputLine = in.readLine().toLowerCase();
        }

        // parse content length
        String length = inputLine.substring(prefix.length());
        int len = Integer.parseInt(length);

        inputLine = in.readLine();
        while (!inputLine.equals("")) {
            // continue reading ...
            inputLine = in.readLine();
        }

        char[] xml = new char[len];
        in.read(xml);
        String xmlStr = new String(xml);
        parseResponse(xmlStr.trim());

        // write response
        out.write("HTTP/1.0 200 OK\n\n");
        out.flush();

        out.close();
        in.close();
    }

    /**
     * Extracts the XML contents from the given String.
     * 
     * @param resp
     *            The response from which the XML contents should be extracted.
     */
    private void parseResponse(final String resp) {
        if (resp.startsWith("<?xml")) {
            // remove xml declaration
            int index = resp.indexOf("?>") + 2;
            if (index >= 0) {
                response = resp.substring(index).trim();
                if(resultProcessor != null)
                    resultProcessor.fetchQueryResult(response);
            }
        }
    }

    /**
     * @return Wheter this thread is running.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Stops this thread from running.
     */
    public void stopRunning() {
        isRunning = false;
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        instance = null;
    }

}
