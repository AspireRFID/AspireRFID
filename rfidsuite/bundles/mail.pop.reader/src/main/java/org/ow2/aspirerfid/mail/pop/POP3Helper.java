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
package org.ow2.aspirerfid.mail.pop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Properties;

import org.ow2.aspirerfid.mail.pop.exception.POP3ResponseErrorException;
import org.ow2.aspirerfid.mail.pop.exception.TimeoutException;
import org.ow2.aspirerfid.util.Logger;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class POP3Helper {

	private static final int TIME_BEFORE_RETRY = 500; // in millisecond

	private static final int TIMEOUT = 10000; // in millisecond

	private static final String OK = "+OK";

	private static final String ERROR = "-ERR";

	private String host;

	private int port;

	private String login;

	private String pass;

	private Socket socket;

	private PrintWriter to;

	private BufferedReader from;

	private boolean isConnected;

	private Logger log;

	private int debugLevel;

	/**
	 * Constructor
	 * 
	 * @param p
	 *            properties files which contain server config
	 */
	public POP3Helper(Properties p) {
		debugLevel = Logger.WARNING;
		log = new Logger("POP3Helper", debugLevel);
		this.host = p.getProperty("host");
		this.port = (int) Integer.parseInt(p.getProperty("port"));
		this.login = p.getProperty("account.login");
		this.pass = p.getProperty("account.pass");
		isConnected = false;
	}

	/**
	 * Constructor
	 * 
	 * @param host
	 *            server mail host
	 * @param port
	 *            server mail port
	 * @param login
	 *            POP3 account login
	 * @param pass
	 *            POP3 account pass
	 */
	public POP3Helper(String host, int port, String login, String pass) {
		debugLevel = Logger.WARNING;
		log = new Logger("POP3Reader", debugLevel);
		this.host = host;
		this.port = port;
		this.login = login;
		this.pass = pass;
		isConnected = false;
	}

	/**
	 * Get all messages
	 * 
	 * @return list of all message
	 */
	public String[] GetAllMessages() {
		connect();

		String[] messages = null;
		try {
			command("USER " + login, "USER");
			command("PASS " + pass, "PASS");
			command("LIST", "LIST");

			LinkedList messagesId = listProcess();

			// Gets all messages content
			messages = new String[messagesId.size()];
			for (int i = 0; i < messagesId.size(); i++) {
				command("RETR " + messagesId.get(i) + "\r", "RETR");
				messages[i] = retrProcess();
			}

		} catch (Exception e) {
			log.log(Logger.ERROR, e.toString());
			messages = new String[1];
			messages[0] = "";
		}

		disconnect();
		return messages;
	}

	/**
	 * Get all messages
	 * 
	 * @return list of all message
	 */
	public String[] GetAndRemoveAllMessages() {
		connect();

		String[] messages = null;
		try {
			command("USER " + this.login, "USER");
			command("PASS " + this.pass, "PASS");
			command("LIST", "LIST");

			LinkedList messagesId = listProcess();

			// Gets all messages content
			if (messagesId.size() > 0) {
				messages = new String[messagesId.size()];
				for (int i = 0; i < messagesId.size(); i++) {
					command("RETR " + messagesId.get(i) + "\r", "RETR");
					messages[i] = retrProcess();
					command("DELE " + messagesId.get(i) + "\r", "DELE");
				}
			}
		} catch (Exception e) {
			log.log(Logger.ERROR, e.toString());
			messages = null;
		}

		disconnect();
		return messages;
	}

	/**
	 * @param FullMail
	 * @return TODO Javadoc
	 */
	public String removeMailHeader(String FullMail) {
		int i = FullMail.indexOf("\n\n");
		if (i == -1) {
			// error :x
			return FullMail;
		}

		return FullMail.substring(i + 2, FullMail.length());
	}

	/**
	 * Connect to POP3 server
	 */
	private void connect() {
		isConnected = true;
		try {
			socket = new Socket(InetAddress.getByName(host), port);
			to = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream())), true);
			from = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));

			// wait connection
			long maxTime = System.currentTimeMillis() + TIMEOUT;
			while (!(from.readLine()).startsWith(OK)) {
				if (maxTime < System.currentTimeMillis()) {
					throw new TimeoutException();
				}
				sleep(TIME_BEFORE_RETRY);
			}
		} catch (UnknownHostException e) {
			isConnected = false;
			log.log(Logger.ERROR, "Mail Server connection : Unknow host");
			// e.printStackTrace();
		} catch (IOException e) {
			isConnected = false;
			log.log(Logger.ERROR, "Mail Server connection : Timeout");
			// e.printStackTrace();
		} catch (TimeoutException e) {
			isConnected = false;
			log.log(Logger.ERROR, "Mail Server connection : Timeout");
			// e.printStackTrace();
		}
	}

	/**
	 * Disconnect from POP3 server
	 */
	private void disconnect() {
		if (isConnected) {
			try {
				command("QUIT", "QUIT");
			} catch (POP3ResponseErrorException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				// we don't care, we want quit
			}
		}
		if (isConnected && socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Execute command on the POP3 server
	 * 
	 * @param commandLine
	 *            command line you want execute
	 * @param commandName
	 *            name of the command, helpful for debug
	 * @throws POP3ResponseErrorException
	 *             the command line make an error
	 * @throws TimeoutException
	 *             server timeout
	 */
	private void command(String commandLine, String commandName)
			throws POP3ResponseErrorException, TimeoutException {
		// clear buffer
		while (ready(from)) {
			readLine(from);
		}

		// send command
		String response = "";
		to.println(commandLine + "\r");

		// wait a response
		long maxTime = System.currentTimeMillis() + TIMEOUT;
		while (true) {
			// check response
			response = "";
			boolean rdy = ready(from);

			if (rdy) {
				response = readLine(from);
				if (response.startsWith(OK)) {
					log.log(Logger.DEBUG, "Response : " + response + "\r");
					break;
				}
				if (response.startsWith(ERROR)) {
					throw new POP3ResponseErrorException(commandName
							+ " return an error message : '" + response + "'");
				}
			}

			// timeout ?
			if (System.currentTimeMillis() > maxTime) {
				throw new TimeoutException();
			}
			sleep(TIME_BEFORE_RETRY);

		}
	}

	/**
	 * Process associate to the LIST command
	 * 
	 * @return a list which contain the id of each message present in the
	 *         current account
	 */
	private LinkedList listProcess() {
		/*
		 * list response format : "id size"
		 */
		LinkedList msgDescription = new LinkedList();

		String line;
		do {
			line = readLine(from);
			if (line.compareTo(".") != 0) {
				// get id
				int i = line.indexOf(" ");
				int size = Integer.parseInt(line.substring(0, i));
				msgDescription.add(new Integer(size));
			}
		} while (line.compareTo(".") != 0);

		return msgDescription;
	}

	/**
	 * Process associate to RETR command
	 * 
	 * @return message
	 */
	private String retrProcess() {
		String msg = "";
		do {
			msg += readLine(from) + "\n";
		} while (!msg.endsWith("\n.\n"));

		// remove \n.\n
		return msg.substring(0, msg.length() - 3);
	}

	/**
	 * Like BufferedReader.ready without care of exception
	 * 
	 * @param b
	 *            BufferedReader
	 * @return true, if the BufferedReader is ready to be read
	 */
	private boolean ready(BufferedReader b) {
		try {
			return from.ready();
		} catch (IOException e) {
		}

		return false;
	}

	/**
	 * Like BufferedReader.readLine without care of exception
	 * 
	 * @param b
	 *            BufferedReader
	 * @return buffer line
	 */
	private String readLine(BufferedReader b) {
		try {
			return from.readLine();
		} catch (IOException e) {
		}

		return ERROR;
	}

	private void sleep(int ms) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            TODO Javadoc
	 */
	public void setHost(String host) {
		this.isConnected = false;
		this.host = host;
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            TODO Javadoc
	 */
	public void setLogin(String login) {
		this.isConnected = false;
		this.login = login;
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass
	 *            TODO Javadoc
	 */
	public void setPass(String pass) {
		this.isConnected = false;
		this.pass = pass;
	}

	/**
	 * @return TODO Javadoc
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            TODO Javadoc
	 */
	public void setPort(int port) {
		this.isConnected = false;
		this.port = port;
	}

	/**
	 * @return TODO Javadoc
	 */
	public int getDebugLevel() {
		return debugLevel;
	}

	/**
	 * @param debugLevel
	 *            TODO Javadoc
	 */
	public void setDebugLevel(int debugLevel) {
		log = new Logger("POP3Daemon", debugLevel);
		this.debugLevel = debugLevel;
	}

}
