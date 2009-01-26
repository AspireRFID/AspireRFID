/*
   Copyright 2005-2008, OW2 Aspire RFID project 
   
   This library is free software; you can redistribute it and/or modify it 
   under the terms of the GNU Lesser General Public License as published by 
   the Free Software Foundation (the "LGPL"); either version 2.1 of the 
   License, or (at your option) any later version. If you do not alter this 
   notice, a recipient may use your version of this file under either the 
   LGPL version 2.1, or (at his option) any later version.
   
   You should have received a copy of the GNU Lesser General Public License 
   along with this library; if not, write to the Free Software Foundation, 
   Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
   
   This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
   KIND, either express or implied. See the GNU Lesser General Public 
   License for the specific language governing rights and limitations.

   Contact: OW2 Aspire RFID project <X AT Y DOT org> (with X=aspirerfid and Y=ow2)

   LGPL version 2.1 full text http://www.gnu.org/licenses/lgpl-2.1.txt    
*/
package org.ow2.aspirerfid.rxtx.cmd.impl;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.TooManyListenersException;

import org.apache.felix.shell.Command;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.ow2.aspirerfid.rxtx.cmd.IOWrapper;
import org.ow2.aspirerfid.rxtx.cmd.wrapper.AsciiWrapper;
import org.ow2.aspirerfid.rxtx.cmd.wrapper.HexWrapper;

import rxtx.sample.SerialParameters;

/**
 * This class creates a shell command
 * @author Didier Donsez
 */
public class RXTXCmdImpl implements Command, SerialPortEventListener, BundleActivator {

	SerialPort serialPort = null;

	OutputStream outputStream = null;

	InputStream inputStream = null;

	String logFileName = null;
	OutputStream logOutputStream = null;

	IOWrapper currentWrapper = new HexWrapper();

	// TODO resizable buffer
	byte[] readBuffer = new byte[256];

	public RXTXCmdImpl(BundleContext context) {
	}

	public String getName() {
		return "rxtx";
	}

	public String getUsage() {
		return "rxtx help";
	}

	public String getShortDescription() {
		return "send/receive bytes to/from a serial port";
	}

	private void printUsage(PrintStream out) {
		out
				.println(getName()
						+ " list                  : list the available ports\n"
						+ getName()
						+ " open <portname> <baudrate> <flowctrlin> <flowctrlout> <databits> <stopbits> <parity>\n"
						+ "               flowctrl=None,Xon/XoffOut,Xon/XoffIn,RTS/CTSIn,RTS/CTSOut\n"
						+ "                            : open a serial port\n"
						+ "               databits=5,6,7,8\n"
						+ "               stopbits=1,1.5,2\n"
						+ "               parity=None,Odd,Even\n"
						+ "                            : open a serial port\n"
						+ getName()
						+ " close                 : close the current opened port\n"
						+ getName()
						+ " send <bytes in hexa>  : send message bytes\n"
						+ getName()
						+ " binary                : switch IO in hex\n"
						+ getName()
						+ " ascii                 : switch IO in ascii\n"
						+ getName()
						+ " buffersize <size>     : set IO buffer size\n"
						+ getName()
						+ " sendurl <url>         : send bytes contained at the url\n"
						+ getName()
						+ " logfile <filepath>    : log received bytes in the file\n"
						+ getName()
						+ " logfile               : stop to log bytes\n"
						+ getName()
						+ " iowrapper <classname> : set a wrapper for IO parsing and printing. The class must be provided by a external bundle\n"
						+ getName()
						+ " help                  : display this help\n");
		return;
	}

	public void execute(String commandLine, PrintStream out, PrintStream err) {

		StringTokenizer st = new StringTokenizer(commandLine, " ");

		// Ignore the command name.
		st.nextToken();
		if (st.countTokens() < 1) {
			printUsage(err);
			return;
		}

		String option = st.nextToken();

		if (option.equals("list")) {
			listPorts(null, out, err);
		} else if (option.equals("binary")) {
			currentWrapper = new HexWrapper();
		} else if (option.equals("ascii")) {
			currentWrapper = new AsciiWrapper();
		} else if (option.equals("buffersize")) {
			if (st.countTokens() != 1) {
				printUsage(err);
				return;
			}
			// TODO synchronization
			int newsize = 0;
			try {
				newsize = Integer.parseInt(st.nextToken());
			} catch (NumberFormatException e) {
				err.println(e);
				return;
			}
			synchronized (this) {
				readBuffer = new byte[newsize];
			}
			return;
		} else if (option.equals("iowrapper")) {
			String classname = st.nextToken();
			Class clazz = null;
			try {
				clazz = this.getClass().getClassLoader().loadClass(classname);
			} catch (ClassNotFoundException e) {
				err.println(e);
				return;
			}
			IOWrapper wrapper = null;
			try {
				wrapper = (IOWrapper) clazz.newInstance();
			} catch (InstantiationException e) {
				err.println(e);
				return;
			} catch (IllegalAccessException e) {
				err.println(e);
				return;
			}
			if (wrapper != null)
				currentWrapper = wrapper;
			return;
		} else if (option.equals("sendurl")) {
			err.println("Not implemented");
			return;
		} else if (option.equals("logfile")) {

			if(logOutputStream!=null) {
				try {
					logOutputStream.close();
				} catch (IOException e) {
					err.println(e);
					return;
				}
				logOutputStream=null;
				out.println("Close the current log file : "+logFileName); 
			}

			if (st.countTokens() != 1) {
				if(logOutputStream!=null) {
					try {
						logOutputStream.close();
					} catch (IOException e) {
						err.println(e);
						return;
					}
					logOutputStream=null;
					out.println("Close the current log file: "+logFileName); 
				} else {
					err.println("No current log file");
				}
				return;
			}

			logFileName = st.nextToken();
			File file = new File(logFileName);
			try {
				logOutputStream = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				err.println(e);
				logFileName=null;
				return;
			}
			return;
		} else if (option.equals("open")) {
			if (st.countTokens() != 7) {
				printUsage(err);
				return;
			}
			SerialParameters serialParameters = new SerialParameters();
			serialParameters.setPortName(st.nextToken());
			serialParameters.setBaudRate(st.nextToken());
			serialParameters.setFlowControlIn(st.nextToken());
			serialParameters.setFlowControlOut(st.nextToken());
			serialParameters.setDatabits(st.nextToken());
			serialParameters.setStopbits(st.nextToken());
			serialParameters.setParity(st.nextToken());

			CommPortIdentifier portId = null;
			try {
				portId = CommPortIdentifier.getPortIdentifier(serialParameters
						.getPortName());
			} catch (NoSuchPortException e1) {
				err.println("No Such Port:" + serialParameters.getPortName());
				return;
			}
			if (portId.getPortType() != CommPortIdentifier.PORT_SERIAL) {
				err.println(serialParameters.getPortName()
						+ " is not a serial port");
				return;
			}
			try {
				serialPort = (SerialPort) portId.open("RXTXCmd", 2000);
			} catch (PortInUseException e) {
				err.println(serialParameters.getPortName() + " already used");
				return;
			}

			try {
				outputStream = serialPort.getOutputStream();
			} catch (IOException e) {
				err.println(e);
				return;
			}

			try {
				inputStream = serialPort.getInputStream();
			} catch (IOException e) {
				err.println(e);
				return;
			}

			try {
				serialPort.addEventListener(this);
				serialPort.notifyOnDataAvailable(true);
			} catch (TooManyListenersException e) {
				err.println(e);
				return;
			}

			try {
				serialPort.setSerialPortParams(serialParameters.getBaudRate(),
						serialParameters.getDatabits(), serialParameters
								.getStopbits(), serialParameters.getParity());
			} catch (UnsupportedCommOperationException e) {
				err.println(e);
				return;
			}

		} else if (option.equals("close")) {
			if (serialPort == null) {
				err.println("No opened port");
				return;
			}
			close(commandLine, out, err);
		} else if (option.equals("send")) {
			if (serialPort == null) {
				err.println("No opened port");
				return;
			}
			if (st.countTokens() < 1) {
				printUsage(err);
				return;
			}

			byte[] msg = null;
			int start = commandLine.indexOf("\"");
			int last = commandLine.lastIndexOf("\"");
			try {
				if (start >= 0 && start < last) {
					String str = commandLine.substring(start + 1, last);
					msg = currentWrapper.parse(str);
				} else {
					msg = currentWrapper.parse(st);
				}
			} catch (ParseException e) {
				err.println(e);
				return;
			}

			try {
				outputStream.write(msg);
				outputStream.flush();
			} catch (IOException e) {
				err.println(e);
				return;
			}

		} else {
			printUsage(out);
		}

	}

	public void close(String commandLine, PrintStream out, PrintStream err) {
		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException e) {
				err.println(e);
				return;
			}
			outputStream = null;
		}

		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				err.println(e);
				return;
			}
			inputStream = null;
		}

		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
			serialPort = null;
		}

	}

	public void listPorts(String commandLine, PrintStream out, PrintStream err) {
		out.println("List of ports from RXTX");
		Enumeration ports = CommPortIdentifier.getPortIdentifiers();
		while (ports.hasMoreElements()) {
			CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
			String type;
			switch (port.getPortType()) {
			case CommPortIdentifier.PORT_PARALLEL:
				type = "Parallel";
				break;
			case CommPortIdentifier.PORT_SERIAL:
				type = "Serial";
				break;
			default: /// Shouldn't happen
				type = "Unknown";
				break;
			}
			out.println(port.getName() + ": " + type);
		}
	}

	/**
	 * @see gnu.io.SerialPortEventListener#serialEvent(gnu.io.SerialPortEvent)
	 */
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:

			try {
				int numBytes = 0;
				int numBytesAvailables = 0;
				synchronized (this) {

					while ((numBytesAvailables=inputStream.available()) > 0) {
						numBytes = inputStream.read(readBuffer);

						System.out.print(getName() + " (" + numBytes + " bytes received):\n");

						try {
							System.out.println(currentWrapper.format(readBuffer, 0, numBytes));

							if(logOutputStream!=null){								
								logOutputStream.write(readBuffer, 0, numBytes);
								logOutputStream.flush();
							}
						} catch (ParseException e) {
							System.err.println(e);
							return;
						}
					}
				}
			} catch (IOException e) {
				System.err.print(getName() + ":" + e);
			}
			break;
			
			/*
			try {
				int numBytes = 0;
				synchronized (this) {
					//System.out.print("received:");
					while ((numBytes = inputStream.available()) > 0) {
						numBytes = inputStream.read(readBuffer, currentPos, numBytes);
						currentPos=currentPos+numBytes;							
						//System.out.println(HexString.hexify(readBuffer, 0, currentPos, 16, " "));
						int len;
						if(currentPos>2 && currentPos>=(len=readBuffer[1])){
							
							byte[] b=new byte[len];
							System.arraycopy(readBuffer,0,b,0,len);
							currentPos=0;
							
							readTRPDetailsResponseFrame = new ReadTRPDetailsResponseFrame(b);
							
							if (readTRPDetailsResponseFrame.noErrorReturned()) {
								tagId = Long.toString(readTRPDetailsResponseFrame.getTagId());
								tagItListener.process(readerId, tagId);
							}
						}
					}
				}
			} catch (IOException e) {
				System.err.print(readerId + ":" + e);
			}
			 */
			
			
			
			
		}
	}

	/**
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext arg0) throws Exception {
	}

	/**
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext arg0) throws Exception {
		if (logOutputStream != null) {
			try {
				logOutputStream.close();
			} catch (IOException e) {
				// do nothing
			}
		}
		close(null,System.out,System.err);
	}
}