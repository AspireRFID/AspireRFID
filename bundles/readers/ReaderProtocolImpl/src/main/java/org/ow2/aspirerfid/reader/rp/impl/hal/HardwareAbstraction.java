/*
 * Copyright (c) 2008-2010, Aspire 
 * 
 * This file contains the source code of the Accada library by ETH Zurich (www.accada.org), 
 * licensed under the terms of the GNU Lesser General Public License version 2.1 in 2007 and 
 * modified for the needs of the Aspire project.
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

package org.ow2.aspirerfid.reader.rp.impl.hal;

import org.ow2.aspirerfid.reader.rp.impl.hal.Observation;
import org.ow2.aspirerfid.reader.rp.impl.hal.UnsignedByteArray;
import org.ow2.aspirerfid.reader.rp.impl.hal.Trigger;
import org.ow2.aspirerfid.reader.rp.impl.hal.HardwareException;
import org.ow2.aspirerfid.reader.rp.impl.hal.UnsupportedOperationException;


/**
 * The standardized interface defines an abstraction of the underlaying reader hardware. The HardwareAbstraction interface defines 
 * a set of methods that can be implemented by any so called HAL controller class. A class that implements 
 * the interface agrees to implement all the methods defined in the interface, thereby agreeing to certain behavior.
 * 
 * The functionality provided by the interface is kept as generally as possible in order not to exclude certain
 * readers. The hardware itself has to comply with the following minimal requirements in order to be supported by the framework.
 * 
 * The minimal assumption about the proprietary RFID systems is that the reader allows the identification of tags by a unique serial number.
 * The tag's unique serial number, i.e. identifier, has to be specified as a String representing a hexadecimal number. The hexadecimal digits
 * are the ordinary, base-10 digits '0' through '9' plus the letters 'A' through 'F'. In the hexadecimal system, these digits represent 
 * the values 0 through 15, respectively. A hexadecimal integer is a sequence of hexadecimal digits, such as 34A7, FF8, 174204. Depending
 * on the concrete hardware this String will be converted into a convenient format, for example a byte array.
 *
 * In the HAL model, the memory is devided into memory banks that can be addressed. In each memory bank the memory can be accessed
 * in block units, whereas the blocksize has to be specified in a corresponding properties file. Despite the block size, memory is
 * specified in bytes. Since Java does not support unsigned bytes as basic types the class <code>UnsignedByteArray</code> is used to represent
 * array of bytes. In general, a HAL controller instance can offer it's own memory bank model, i.e. the number of memory banks and
 * the content of certain memory banks such as EPCs, Tag IDs, or Passwords. To avoid confusion the following memory model is suggested
 * which is given in the EPCglobal Class 1 Generation 2 specification:
 * 		memory bank 0: protected
 * 		memory bank 1: EPC (Electronic Product Code or other object IDs, read only)
 * 		memory bank 2: Tag ID (factory programmed ID, read only)
 * 		memory bank 3: user memory (read/write)
 * In addition, more memory banks could be used for user memory or to retrieve values of sensors mounted on a tag. Even if the underlaying
 * reader hardware does not support EPCs, the memory bank model could be implemented using the provided memory of the reader hardware.
 * 
 * The HAL controller implementation has to decide which of the IDs should be the master ID of the tag that is returned when
 * performing an <code>identify</code> command. In an <code>Observation</code> the type of ID has to be specified.
 * 
 * HAL controller implementations have to take the memory block size of  the underlaying reader hardware into account. When performing a 
 * read operation only the requested number of bytes should be returned to the client even if more bytes have to be read to satisfy the 
 * block size constraints. When writing data to tag memory a preceding read operation might be necessary to avoid overwriting of memory
 * with empty data used to fit the block size.
 *
 * @author Matthias Lampe, lampe@acm.org
 * @author Christian Floerkemeier, floerkem@mit.edu
 * @author Nektarios Leontiadis, nele@ait.edu.gr
 */
public interface HardwareAbstraction {
		
	//--------- Identify

	public void disconnect();
	/**
	 * Identifies the tags in a reader's field. The concrete behaviour of this
	 * method depends on the concrete implementation of the underlaying
	 * hardware.
	 * 
	 * If the controller is a single-read-point-reader the array
	 * <code>readPointNames</code> should contain only one read point.
	 * 
	 * Tags that have been locked during the identification can be reactivated
	 * by calling the <code>reset()</code> method.
	 * 
	 * @param readPointNames Array that contains the names of all read points to be scanned
	 * 
	 * @return An array that contains for each read point an <code>Observation</code> object
	 * 
	 * @throws ReadPointNotFoundException, if the read point can not be found.
	 * @throws HardwareException, if the operation can not be performed.
	 */
	Observation[] identify(String[] readPointNames) throws ReadPointNotFoundException, HardwareException;
	

	
	//--------- Asynchronous Identify

	/**
	 * Identifies the tags in a reader's field in a continuous way that operates asynchronously.
	 * 
	 * If the controller is a single-read-point-reader the array <code>readPointNames</code>
	 * should contain only one read point.
	 * 
	 * @param readPointNames Assay that contains the names of all read points to be scanned
	 * @param trigger The trigger that indicates the type of asynchronous identify
	 * 
	 * @throws ReadPointNotFoundException, if the read point can not be found.
	 * @throws HardwareException, if the operation can not be performed.
	 * @throws UnsupportedOperationException, if the operation is not supported by the controller implementation.
	 */
	void startAsynchronousIdentify(String[] readPointNames, Trigger trigger) throws ReadPointNotFoundException, HardwareException, UnsupportedOperationException;
	
	
	/**
	 * Stops the asynchronous identification for a specific listener.
	 * 
	 * @throws HardwareException, if the operation can not be performed.
	 * @throws UnsupportedOperationException, if the operation is not supported by the controller implementation.
	 */
	void stopAsynchronousIdentify() throws HardwareException, UnsupportedOperationException;
	
		
	/**
	 * Checks whether asynchronous identify is running.
	 * 
	 * @return true, if asynchronous identify is running, false otherwise
	 * 
	 * @throws HardwareException, if the operation can not be performed.
	 * @throws UnsupportedOperationException, if the operation is not supported by the controller implementation.
	 */
	boolean isAsynchronousIdentifyRunning() throws HardwareException, UnsupportedOperationException;

	
	/**
	 * Adds an asynchronous identify listener.
	 * 
	 * @throws HardwareException, if the operation can not be performed.
	 * @throws UnsupportedOperationException, if the operation is not supported by the controller implementation.
	 */
	void addAsynchronousIdentifyListener(AsynchronousIdentifyListener listener) throws HardwareException, UnsupportedOperationException;
	
	
	/**
	 * removes an asynchronous identify listener.
	 * 
	 * @throws HardwareException, if the operation can not be performed.
	 * @throws UnsupportedOperationException, if the operation is not supported by the controller implementation.
	 */
	void removeAsynchronousIdentifyListener(AsynchronousIdentifyListener listener) throws HardwareException, UnsupportedOperationException;
	
	
	/**
	 * Checks whether this HAL controller implementation supports the <code>startAsynchronousIdentify()</code>,
	 * <code>stopAsynchronousIdentify()</code>, <code>isAsynchronousIdentifyRunning()</code>,
	 * <code>addAsynchronousIdentifyListener()</code> and <code>removeAsynchronousIdentifyListener()</code>
	 * methods.
	 * 
	 * @return true, if methods are supported, false otherwise
	 */
	boolean supportsAsynchronousIdentify();
	


	//--------- Read and Write

	/**
	 * Reads data from a specified tag, if in range. The transponder id should be a
	 * result of a previous <code>identify</code>.
	 * 
	 * The data on a tag's memory
	 * can be read in block units. Therefore the <code>length</code>
	 * parameter, that indicates the number of bytes that have to be read, has
	 * to contain a multiple of the blocksize. 
	 * 
	 * @param readPointName The name of the read point on which the read attempt will be done
	 * @param id ID of the tag from which the data will be read
	 * @param memoryBank The number of the memory bank of the data
	 * @param offset The offset of the data in bytes
	 * @param length The number of bytes to be read
	 * @param passwords An optional list of one or more passwords (or lock code)
	 * 
	 * @return The data that has been read from the tag
	 * 
	 * @throws ReadPointNotFoundException, if the read point can not be found.
	 * @throws OutOfBoundsException, if memoryBank, offset or length exceeds the allowed number. 
	 * @throws HardwareException, if the operation can not be performed.
	 * @throws UnsupportedOperationException, if the operation is not supported by the controller implementation.
	 */
	UnsignedByteArray readBytes(String readPointName, String id, int memoryBank, int offset, int length, String[] passwords) 
						throws ReadPointNotFoundException, OutOfBoundsException, HardwareException, UnsupportedOperationException;
	
	/**
	 * Checks whether this HAL controller implementation supports the <code>readBytes()</code> method.
	 * 
	 * @return true, if method is supported, false otherwise
	 */
	boolean supportsReadBytes();
	
		
	/**
	 * Writes data to a specific tag, if in range. The transponder id should be
	 * a result of a previous <code>identify</code>.
	 * 
	 * @param readPointName The name of the read point on which the write attempt will be done
	 * @param id ID of the tag to which the data will be written
	 * @param memoryBank The number of the memory bank of the data
	 * @param offset The offset of the data in bytes
	 * @param data The byte data to be written to the tag
	 * @param passwords An optional list of one or more passwords (or lock code)
	 * 
	 * @throws ReadPointNotFoundException, if the read point can not be found.
	 * @throws OutOfBoundsException, if memoryBank, offset or length exceeds the allowed number. 
	 * @throws HardwareException, if the operation can not be performed.
	 * @throws UnsupportedOperationException, if the operation is not supported by the controller implementation.
	 */
	void writeBytes(String readPointName, String id, int memoryBank, int offset, UnsignedByteArray data, String[] passwords)
				throws ReadPointNotFoundException, OutOfBoundsException, HardwareException, UnsupportedOperationException;
	
	/**
	 * Checks whether this HAL controller implementation supports the <code>writeBytes()</code> method.
	 * 
	 * @return true, if method is supported, false otherwise
	 */
	boolean supportsWriteBytes();

	
	
	//--------- Kill and ProgramID

	/**
	 * Kills the specified tag, if in range. A killed tag doesn't respond to
	 * requests any longer.
	 * 
	 * @param readPointName the name of the read point on which the kill attempt will be done
	 * @param id id of the tag that will be killed
	 * @param passwords an optional list of one or more passwords (or lock code) 
	 * 
	 * @throws ReadPointNotFoundException, if the read point can not be found.
	 * @throws HardwareException, if the operation can not be performed.
	 * @throws UnsupportedOperationException, if the operation is not supported by the controller implementation.
	 */
	void kill(String readPointName, String id, String[] passwords) throws ReadPointNotFoundException, HardwareException, UnsupportedOperationException;	
	
	/**
	 * Checks whether this HAL controller implementation supports the <code>kill()</code> method.
	 * 
	 * @return true, if method is supported, false otherwise
	 */
	boolean supportsKill();

	
	/**
	 * Writes the given ID onto a tag. 
	 * If the physical environment supports the setting of a new tag ID this
	 * method can be used to access the appropriate blocks within the memory.
	 * 
	 * @param readPointName The name of the read point on which the write attempt will be done
	 * @param id the new ID for the tag
	 * @param passwords an optional list of one or more passwords (or lock code)
	 * 
	 * @throws ReadPointNotFoundException, if the read point can not be found.
	 * @throws HardwareException, if the operation can not be performed.
	 * @throws UnsupportedOperationException, if the operation is not supported by the controller implementation.
	 */
	void writeId(String readPointName, String id, String[] passwords) throws ReadPointNotFoundException, HardwareException, UnsupportedOperationException;
	
	/**
	 * Checks whether this HAL controller implementation supports the <code>writeId()</code> method.
	 * 
	 * @return true, if method is supported, false otherwise
	 */
	boolean supportsWriteId();

	

	//--------- HAL Controller Management
	
	/**
	 * Returns the HAL name. The HAL name identifies the HAL controller instance and should be
	 * specified in a properties file.
	 * 
	 * @return The HAL name
	 */
	String getHALName();
	

	/**
	 * Gets the names of all available read points.
	 * 
	 * @return An array containing the names of all available read points
	 */
	String[] getReadPointNames();

	
	/**
	 * Gets the names of the supported parameters. The parameters which are
	 * configurable should be listed in the associated properties file.
	 * 
	 * @return The parameter names of all supported paramters
	 * 
	 * @throws HardwareException, if the operation can not be performed.
	 * @throws UnsupportedOperationException, if the operation is not supported by the controller implementation.
	 */
	String[] getAllParameterNames() throws HardwareException, UnsupportedOperationException;
	
	
	/**
	 * Gets the value of a given parameter. A call of the <code>getAllParameterNames()</code> 
	 * should precede this method in order not to request undefined parameters.
	 * 
	 * @param param The parameter name to be read
	 * 
	 * @return The parameter value
	 * 
	 * @throws HardwareException, if the operation can not be performed.
	 * @throws UnsupportedOperationException, if the operation is not supported by the controller implementation.
	 */
	String getParameter(String param) throws HardwareException, UnsupportedOperationException;
	
	
	/**
	 * Sets a given parameter. A call of the <code>getAllParameterNames()</code>
	 * should precede this method in order not to set undefined parameters.
	 * 
	 * @param param The parameter name
	 * @param value The parameter value
	 * 
	 * @throws HardwareException, if the operation can not be performed.
	 * @throws UnsupportedOperationException, if the operation is not supported by the controller implementation.
	 */
	void setParameter(String param, String value) throws HardwareException, UnsupportedOperationException;
	
	/**
	 * Checks whether the HAL controller implementation supports the <code>getAllParameterNames()</code>,
	 * <code>getParameter()</code> and <code>setParameter()</code> methods.
	 * 
	 * @return true, if methods are supported, false otherwise
	 */
	boolean supportsParameters();

	
	/**
	 * Resets the reader. This includes the unlocking of all previously locked
	 * (muted) tags. Furthermore, if anything should not work as desired due to
	 * any failures, this method reinitializes the hardware abstraction.
	 * 
	 * @throws HardwareException, if the operation can not be performed.
	 */
	void reset() throws HardwareException;
	
	/**
	 * Checks whether this HAL controller implementation supports the <code>reset()</code> method.
	 * 
	 * @return true, if method is supported, false otherwise
	 */
	boolean supportsReset();

	
	/**
	 * Returns the current transmit power level of a certain read point.
	 * 
	 * @param readPointName The name of the read point
	 * @param normalize Specifies whether the power level should be returned in a normalized form (i.e. in a range from 0 to 255)
	 * @return The current transmit power level of the read point
	 * 
	 * @throws ReadPointNotFoundException, if the read point can not be found.
	 * @throws HardwareException, if the operation can not be performed.
	 * @throws UnsupportedOperationException, if the operation is not supported by the controller implementation.
	 */
	int getReadPointPowerLevel(String readPointName, boolean normalize) throws ReadPointNotFoundException, HardwareException, UnsupportedOperationException;
	
	/**
	 * Checks whether this HAL controller implementation supports the <code>getReadPointPowerLevel()</code> method.
	 * 
	 * @return true, if method is supported, false otherwise
	 */
	boolean supportsGetReadPointPowerLevel();

	
	/**
	 * Returns the current noise level observed at a certain read point.
	 * 
	 * @param readPointName The name of the read point
	 * @param normalize Specifies whether the noise level should be returned in a
	 *                  normalized form (i.e. in a range from 0 to 255)
	 *                  
	 * @return The current noise level observed at the read point
	 * 
	 * @throws ReadPointNotFoundException, if the read point can not be found.
	 * @throws HardwareException, if the operation can not be performed.
	 * @throws UnsupportedOperationException, if the operation is not supported by the controller implementation.
	 */
	int getReadPointNoiseLevel(String readPointName, boolean normalize) throws ReadPointNotFoundException, HardwareException, UnsupportedOperationException;
	
	/**
	 * Checks whether this HAL controller implementation supports the <code>getReadPointNoiseLevel()</code> method.
	 * 
	 * @return true, if method is supported, false otherwise
	 */
	boolean supportsGetReadPointNoiseLevel();

	
	/**
	 * Starts up a read point.
	 * 
	 * @param readPointName The name of the read point
	 * 
	 * @throws ReadPointNotFoundException, if the read point can not be found.
	 * @throws HardwareException, if the operation can not be performed.
	 * @throws UnsupportedOperationException, if the operation is not supported by the controller implementation.
	 */
	void startUpReadPoint(String readPointName) throws ReadPointNotFoundException, HardwareException, UnsupportedOperationException;
	
	/**
	 * Checks whether this HAL controller implementation supports the <code>startUpReadPoint()</code> method.
	 * 
	 * @return true, if method is supported, false otherwise
	 */
	boolean supportsStartUpReadPoint();

	
	/**
	 * Shuts down a read point.
	 * 
	 * @param readPointName The name of the read point
	 * 
	 * @throws ReadPointNotFoundException, if the read point can not be found.
	 * @throws HardwareException, if the operation can not be performed.
	 * @throws UnsupportedOperationException, if the operation is not supported by the controller implementation.
	 */
	void shutDownReadPoint(String readPointName) throws ReadPointNotFoundException, HardwareException, UnsupportedOperationException;
	
	/**
	 * Checks whether this HAL controller implementation supports the <code>shutDownReadPoint()</code> method.
	 * 
	 * @return true, if method is supported, false otherwise
	 */
	boolean supportsShutDownReadPoint();

	
	/**
	 * Checks whether a read point is ready (i.e. it has been started up).
	 * 
	 * @param readPointName The name of the read point
	 * 
	 * @return <code>true</code> it the antenna is ready,
	 *         <code>false</code> otherwise
	 * 
	 * @throws ReadPointNotFoundException, if the read point can not be found.
	 * @throws HardwareException, if the operation can not be performed.
	 * @throws UnsupportedOperationException, if the operation is not supported by the controller implementation.
	 */
	boolean isReadPointReady(String readPointName) throws ReadPointNotFoundException, HardwareException, UnsupportedOperationException;
	
	/**
	 * Checks whether this HAL controller implementation supports the <code>isReadPointReady()</code> method.
	 * 
	 * @return true, if method is supported, false otherwise
	 */
	boolean supportsIsReadPointReady();

}