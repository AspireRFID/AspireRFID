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

package org.ow2.aspirerfid.reader.rp.impl.core.msg.transport;

import java.util.ArrayList;
import java.util.List;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.IncomingMessageListener;



/**
 * Defines a server connection.
 * 
 * @author Dijana Micijevic, ETH Zurich Switzerland, Winter 2003/04
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 */
public abstract class ServerConnection implements Runnable {
	
	/** the type of the server connection is TCP */
	public static final String TCP_SERVER = "tcp";
	
	/** the type of the server connection is HTTP */
	public static final String HTTP_SERVER = "http";
	
	/** all server connections */
	private static List serverConns = null;

	/**
	 * Creates a given type of server connection with a given port.
	 * @param connectionType type of the server connection
	 * @param port port at which the server connection waits
	 * @param listener listener of incoming messages
	 */
    public static void createServerConnection(String connectionType, int port, IncomingMessageListener listener){
		ServerConnection serverConn = null;
		if (serverConns == null) serverConns = new ArrayList();
    	if(connectionType.equalsIgnoreCase(TCP_SERVER)){
			serverConn = new TCPServerConnection(listener);			
    	} else if (connectionType.equalsIgnoreCase(HTTP_SERVER)){
    		serverConn = new HttpServerConnection(listener);			
    	}
		serverConns.add(serverConn);
		serverConn.open(port);
    }
    
    /**
     * Gets all server connections.
     * @return a list of all server connections
     */
    public static List getServerConnections(){
    	return serverConns;
    }
    
    /** Creates a separate client thread which processes client messages */
    protected abstract void createServerThread();

	/** 
	 * Opens a server connection on the given port.
	 * 
	 * @param port The port  
	 */
    abstract public void open(int port);

	/** Closes the server connection */
	abstract public void close();
}
