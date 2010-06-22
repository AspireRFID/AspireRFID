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
package org.ow2.aspirerfid.sensor.serialdevice;

import gnu.io.SerialPort;

/**
 * A class that represents parameters for a serial port.
 * @author Didier Donsez
 */
public class SerialParameters {

    private String portName;
    private int baudRate;
    private int databits;
    private int stopbits;
    private int parity;
    private int flowControlIn;
    private int flowControlOut;

    /**
     * Default constructor.
     */
    public SerialParameters() {
        this(	null,
        		9600,
        		SerialPort.DATABITS_8,
        		SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE,
        		SerialPort.FLOWCONTROL_NONE,
        		SerialPort.FLOWCONTROL_NONE
        );
    }

    /**
     * Constructor with portname.
     * @param portName The name of the port.
	 */
    public SerialParameters(String portName) {
        this();
        this.portName = portName;
    }

    
    /**
     * Constructor with all parameter.
     * @param portName The name of the port.
     * @param baudRate The baud rate.
     * @param databits The number of data bits.
     * @param stopbits The number of stop bits.
     * @param parity The type of parity.
     * @param flowControlIn Type of flow control for receiving.
     * @param flowControlOut Type of flow control for sending.
     */
    public SerialParameters(String portName, int baudRate, int databits, int stopbits, int parity, int flowControlIn, int flowControlOut) {

        this.portName = portName;
        this.baudRate = baudRate;
        this.databits = databits;
        this.stopbits = stopbits;
        this.parity = parity;
        this.flowControlIn = flowControlIn;
        this.flowControlOut = flowControlOut;
    }

	public final String getPortName() {
		return portName;
	}

	public final int getBaudRate() {
		return baudRate;
	}

	public final int getDatabits() {
		return databits;
	}

	public final int getStopbits() {
		return stopbits;
	}

	public final int getParity() {
		return parity;
	}

	public final int getFlowControlIn() {
		return flowControlIn;
	}

	public final int getFlowControlOut() {
		return flowControlOut;
	}

	public final void setPortName(String portName) {
		this.portName = portName;
	}

	public final void setBaudRate(int baudRate) {
		this.baudRate = baudRate;
	}

	public final void setDatabits(int databits) {
		this.databits = databits;
	}

	public final void setStopbits(int stopbits) {
		this.stopbits = stopbits;
	}

	public final void setParity(int parity) {
		this.parity = parity;
	}

	public final void setFlowControlIn(int flowControlIn) {
		this.flowControlIn = flowControlIn;
	}

	public final void setFlowControlOut(int flowControlOut) {
		this.flowControlOut = flowControlOut;
	}
}
