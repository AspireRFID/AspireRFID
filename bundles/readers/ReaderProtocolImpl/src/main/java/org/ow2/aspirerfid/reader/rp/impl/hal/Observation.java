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

package org.ow2.aspirerfid.reader.rp.impl.hal;


import java.util.Date;




/**
 * The <code>Observation</code> class provides a set of methods and fields
 * that represent a result of an identification process. Every
 * <code>Observation</code> object is uniquely defined by its timestamp, which
 * specifies when a certain observation has been made.
 * </p>
 * Every Observation is associated to a read point. Thereby the term read point
 * refers to a reader's antenna.
 * 
 * @author Matthias Lampe, lampe@acm.org
 * @author Christian Floerkemeier, floerkem@mit.edu
 */
public class Observation {
	
	
	//-------------------fields---------------------------------------------------

	/**
	 * The name of the HAL controller instance where the tags were identified
	 */	
	private String halName = null;
	
	/**
	 * Name of the read point where the tags were identified
	 */
	private String readPointName = null;
	
	/**
	 * The timestamp when the tags where identified
	 */
	private long timestamp;
	
	/**
	 * The IDs of detected tags.
	 */
	private String[] ids = null;
	
	/**
	 * The tag descriptor of the  detected tags
	 */
	private TagDescriptor[] tagDescriptors = null;
	
	
	/**
	 * Flag the indicates whether the identify operation has been performed successfully or not.
	 */
	public boolean successful = true;

	
	//	---------------- Constructor(s) -----------------------------------------------
	
	/**
	 * creates an empty Observation.
	 */
	public Observation() { }

	
	/**
	 * creates an Observation with the given parameters.
	 */
	public Observation(String halName, String readPointName, long timestamp,
         String[] ids, TagDescriptor[] tagDescriptors, boolean successful) {
		setHalName(halName);
		setReadPointName(readPointName);
		setTimestamp(timestamp);
		setIds(ids);
      setTagDescriptors(tagDescriptors);
		setSuccessful(successful);
	}

	//------------------- Getter/Setter Methods ------------------------------------------

	/**
	 * Returns the name of the HAL.
	 * 
	 * @return The name of the HAL
	 */
	public String getHalName() {
		return halName;
	}
	
	/**
	 * Sets the HAL controller instance name.
	 * 
	 * @param halName the HAL controller instance name to set
	 */
	public void setHalName(String halName) {
		this.halName = halName;
	}

	
	/**
	 * Gets the name of the read point involved in the identification.
	 * 
	 * @return The name of the read point
	 */
	public String getReadPointName() {
		return readPointName;
	}

	/**
	 * Sets the name of the read point involved in the identification.
	 * 
	 * @param readPointName
	 *            The name of read point to set
	 */
	public void setReadPointName(String readPointName) {
		this.readPointName = readPointName;
	}


	/**
	 * Gets the timestamp.
	 * 
	 * @return The timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets The timestamp.
	 * 
	 * @param timestamp
	 *            The timestamp
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}


	/**
	 * Gets the identified tags.
	 * 
	 * @return The tag IDs
	 */
	public String[] getIds() {
		return ids;
	}

	/**
	 * Sets the IDs of the identified tags.
	 * 
	 * @param ids the tag IDs
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
	/**
	 * Gets the tag descriptor of the  detected tags.
	 * 
	 * @return The tag descriptors of the detected tags
	 */
	public TagDescriptor[] getTagDescriptors() {
		return tagDescriptors;
	}

	/**
	 * Sets The tag descriptors of the detected tags.
	 * 
	 * @param tagDescriptors the tag descriptord of the detected tags
	 */
	public void setTagDescriptors(TagDescriptor[] tagDescriptors) {
		this.tagDescriptors = tagDescriptors;
	}
	
	/**
	 * gets the successful flag.
	 */
	public boolean getSuccessful() {
		return successful;
	}

	/**
	 * sets the successful flag.
	 */
	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	
	//------------------- Method declarations ------------------------------------------
	
	/**
	 * Checks if the observation contains a specific ID.
	 * 
	 * @param id the 
	 * @return <code>true</code> it the ID has been found, <code>false</code>
	 *         otherwise
	 */
	public boolean containsId(String id){
		for (int i = 0; i < ids.length; i++){
			if (ids[i].equals(id)){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Returns a <code>String</code> containing all the observation information.
	 * 
	 * @return The observation as string
	 */
	public String toString(){
		String string = null;
		Date date = new Date(this.timestamp);
		string = "\n************************************";
		string = string + "\n Observation: "
			+ "\n HalName: " + this.halName
			+ "\n ReadPointName: " + this.readPointName
			+ "\n Timestamp: " + date.toString()
			+ "\n Tags: ";
		if (this.ids.length > 0){	
			for (int i=0; i<ids.length; i++){
				string = string + "\n    " + ids[i]; 
			}
		}
		string = string + "\n Successful: " + Boolean.toString(successful);
		
		string = string + "\n************************************";
		return string;
	}


}
