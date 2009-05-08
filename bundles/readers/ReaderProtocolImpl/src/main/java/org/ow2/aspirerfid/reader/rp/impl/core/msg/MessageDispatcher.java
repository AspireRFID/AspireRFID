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

import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.Command;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.Reply;
import org.apache.log4j.Logger;



/**
 * The <code>ServiceDispatcher</code> receives plain messages in XML or TEXT format from the 
 * <code>IncomingMessageBuffer</code>. This dispatcher then parses the messages and 
 * passes the intermediate representation (IR) of a parsed command to the 
 * <code>MessageDispatcher</code>. Resulting replies from the <code>MessageDispatcher</code> 
 * are serialized into the plain message format and are sent to the 
 * <code>OutgoingMessageDispatcher</code>.<br>
 * <br>
 * The class is a singleton.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 */
public class MessageDispatcher extends Thread {
	
    
	//====================================================================
	//---------------------------- Fields ------------------------------//
	//====================================================================
	
    /** The clients part of the system */
	private Clients clients;
	
	/** The dispatcher for all outgoing messages */
	private OutgoingMessageDispatcher outMsgDispatcher;
	
	/** The logger */
	private static Logger log;
	
	/** The message parser which extracts the parameters from the incomming message. */
	private MessageParser parser;
	
	/** The factory creating the message parser */
	private MessageFactory messageFactory;
	
	/** The service dispatcher */
	private static MessageDispatcher dispatcher;
	
	/** Indicates if the thread shall be suspended. */
	private boolean suspendThread = false;
	
	/** The message buffer that buffers the incoming messages. */
	private IncomingMessageBuffer buffer;
	
	/** The message serializer */
	private MessageSerializer serializer = null;
	
	/** Command Dispatcher which handles and dispatches the command invocations */
	private static CommandDispatcher commandDispatcher = null;
	
	private static boolean isStopping = false;
	
	//====================================================================
	//------------------------ Constructor -----------------------------//
	//====================================================================
	
	/**
	 * Constructs a new instance of <code>ServiceDispatcher</code>.
	 * * @param msgLayer The <code>MessageLayer</code> holding this <code>MessageDispatcher</code>.
	 */
	private MessageDispatcher(MessageLayer msgLayer) {
		super();
		log = Logger.getLogger(getClass().getName());
		commandDispatcher = CommandDispatcher.getInstance(msgLayer);
	}
	
	//====================================================================
	//-------------------------- Methods -------------------------------//
	//====================================================================
	
	/**
	 * Returns the single Instance of a <code>ServiceDispatcher</code>.
	 *
	 * @param msgLayer The <code>MessageLayer</code> holding this <code>MessageDispatcher</code>.
	 * @return The <code>ServiceDispatcher</code>.
	 */
	public static MessageDispatcher getInstance(MessageLayer msgLayer) {
		if (dispatcher == null || isStopping) {
			isStopping = false;
			dispatcher = new MessageDispatcher(msgLayer);
		}
		return dispatcher;
	}
	
	/**
	 * Initializes the Dispatcher.
	 * 
	 * @param buffer the <code>IncommingMessageBuffer</code>
	 * @param clients the clients
	 * @param outMsgDispatcher the <code>OutgoingMessageDispatcher</code>
	 * @exception Exception
	 */
	public void initialize(IncomingMessageBuffer buffer, Clients clients, 
	        OutgoingMessageDispatcher outMsgDispatcher) throws Exception {
		log.info("Initializing the dispatcher!");
		this.buffer = buffer;
		this.clients = clients;
		this.outMsgDispatcher = outMsgDispatcher;

		this.messageFactory = MessageFactory.getInstance();
	}
	
	/**
	 * Checks <code>IncommingMessageBuffer</code> for new requests and executes them.
	 */
	public void run() {
		String response = null;
		IncomingMessage im = null;
		log.info("Dispatcher is now waiting for incoming messages!");
		while (!suspendThread) {
			im = (IncomingMessage)this.buffer.get();
			response = this.dispatchMessage(im);
			log.debug("Response message: " + response);
				
			outMsgDispatcher.sendMessage(clients.getClientID(im.getConnection()), response);				
		}
	}
	
	private String dispatchMessage(IncomingMessage im)  {
		Command command = null;
		Reply 	reply 	= null;
		String  inMsg   = null;
		String  outMsg	= null;
		MessageFormat senderMsgFormat = null;
		MessageFormat receiverMsgFormat = null;
		
		log.debug("dispatch message");
		
		try {
			inMsg = im.getMessage();
			senderMsgFormat = im.getConnection().getSenderMessageFormat();
			receiverMsgFormat = im.getConnection().getReceiverMessageFormat();
			
			parser = messageFactory.createParser(senderMsgFormat, Context.COMMAND);
			serializer = messageFactory.createSerializer(receiverMsgFormat, Context.COMMAND);
			
			command = (Command)parser.parseCommandMessage(inMsg);
			reply = commandDispatcher.dispatch(command, im);
			if (reply != null) {
				outMsg = serializer.serialize(reply);
			} else {
				outMsg = null;
			}
			
		} catch (MessageParsingException e) {
			reply = parser.createParserErrorReply(e);
			try {
				outMsg = serializer.serialize(reply);
			} catch (MessageSerializingException e1) {
				log.error("Could not create the error reply for a MessageParsingException.");
			}
		} catch (MessageSerializingException e) {
			log.error(e);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return outMsg;
	}
	
	/**
	 * Supends the thread.
	 */
	public static void stopDispatcher() {
		isStopping = true;
		dispatcher.suspendThread = true;
		dispatcher.suspend();
	}
	

}