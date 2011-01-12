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

package org.ow2.aspirerfid.rxtx.cmd.console;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import org.ow2.aspirerfid.rxtx.cmd.IOWrapper;
import org.ow2.aspirerfid.rxtx.cmd.wrapper.HexWrapper;


/**
 * Standalone Serial Console 
 * @author Didier Donsez
 * TODO add a shutdown hook to close properly the port when Ctrl-C
 */
public class SerialConsole implements SerialPortEventListener {

	SerialPort serialPort = null;

	OutputStream outputStream = null;

	InputStream inputStream = null;

	String logFileName = null;
	OutputStream logOutputStream = null;

	IOWrapper currentWrapper = new HexWrapper();

	// TODO resizable buffer
	byte[] readBuffer = new byte[256];
	
	
	PrintStream out;
	PrintStream err;
	
	String[] args;
	public SerialConsole(String[] args) {
		this.args=args;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SerialConsole serialConsole=new SerialConsole(args);
		serialConsole.start();
	}

	public void start() {
		out=System.out;
		err=System.err;
		listPorts(out,err);
		open();
		try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			stop();
		}
	}
	
	private void open() {

		CommPortIdentifier portId = null;
		try {
			portId = CommPortIdentifier.getPortIdentifier(args[0]);
		} catch (NoSuchPortException e1) {
			err.println("No Such Port:" + args[0]);
			return;
		}
		if (portId.getPortType() != CommPortIdentifier.PORT_SERIAL) {
			err.println(args[0] + " is not a serial port");
			return;
		}
		try {
			serialPort = (SerialPort) portId.open(this.getClass().getCanonicalName(), 2000);
		} catch (PortInUseException e) {
			err.println(args[0] + " already used");
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
			serialPort.setSerialPortParams(
								Integer.parseInt(args[1]),
								args.length<3 ? SerialPort.DATABITS_8 : Integer.parseInt(args[2]),
								args.length<4 ? SerialPort.STOPBITS_1 : Integer.parseInt(args[3]),
								args.length<5 ? SerialPort.PARITY_NONE : Integer.parseInt(args[4])
						);
		} catch (UnsupportedCommOperationException e) {
			err.println(e);
			return;
		}
	}

	public void stop() {
		close();
	}
	
	private void close() {
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

	public void listPorts(PrintStream out, PrintStream err) {
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

						System.out.print("SerialConsole:" + "(" + numBytes + " bytes received):\n");

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
				System.err.print("SerialConsole" + ":" + e);
			}
			break;
		}
	}
}
