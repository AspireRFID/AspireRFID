/**
 * Copyright (c) 2008-2010, AspireRFID
 *
 * This library is free software; you can redistribute it and/or
 * modify it either under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation
 * (the "LGPL"). If you do not alter this
 * notice, a recipient may use your version of this file under the LGPL.
 *
 * You should have received a copy of the LGPL along with this library
 * in the file COPYING-LGPL-2.1; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY
 * OF ANY KIND, either express or implied. See the LGPL  for
 * the specific language governing rights and limitations.
 *
 * Contact: AspireRFID mailto:aspirerfid@ow2.org
 */

package test;

import org.ow2.aspirerfid.rp.api.DataSelector;
import org.ow2.aspirerfid.rp.api.ReaderDevice;
import org.ow2.aspirerfid.rp.api.Source;
import org.ow2.aspirerfid.rp.api.factories.BaseFactory;
import org.ow2.aspirerfid.rp.api.factories.DataSelectorFactory;
import org.ow2.aspirerfid.rp.api.factories.NotificationChannelFactory;
import org.ow2.aspirerfid.rp.api.factories.SourceFactory;
import org.ow2.aspirerfid.rp.api.factories.TagFieldFactory;
import org.ow2.aspirerfid.rp.api.factories.TagSelectorFactory;
import org.ow2.aspirerfid.rp.api.factories.TriggerFactory;
import org.ow2.aspirerfid.rp.api.readreport.ReadReport;
import org.ow2.aspirerfid.rp.imp.factory.ReaderFactory;



public class test {

	public static void main (String[] args) throws Exception {
			
		if ("CAEN".equalsIgnoreCase(args[0]))
			Test_rawReadIDs_CAEN();
		else if ("INTERMEC".equalsIgnoreCase(args[0]))
			Test_rawReadIDs_Intermec();
		else 
			throw new IllegalArgumentException("Pass the reader type as argument : CAEN or INTERMEC");
		
	}
	
	public static void Test_Support(ReaderDevice reader) throws Exception {
		
		System.out.println("Reader Information :");
		System.out.println("\tName: " + reader.getName());
		
		BaseFactory.setCurrentReaderDevice(reader);
		System.out.println("\tMaxDataSelectors: " + DataSelectorFactory.getMaxNumberSupported());
		System.out.println("\tMaxSource: " + SourceFactory.getMaxNumberSupported());
		System.out.println("\tMaxNotifChannels: " + NotificationChannelFactory.getMaxNumberSupported());
		System.out.println("\tMaxTriggers: " + TriggerFactory.getMaxNumberSupported());
		System.out.println("\tMaxTagField " + TagFieldFactory.getMaxNumberSupported());
		System.out.println("\tMaxTagSelectors " + TagSelectorFactory.getMaxNumberSupported());
	}
	
	/**
	 * This test case validates the rawReadIDs method.
	 * The latter displays all tags in fields without applying any
	 * of the programmed tag selectors.
	 * 
	 * @throws Exception
	 */
	public static void Test_rawReadIDs_Intermec() throws Exception {
		ReaderDevice myReader;
				
		// Reader singleton
		myReader = ReaderFactory.getReader(ReaderFactory.BRI_READER, "BRIReaderDevice");
		
		// Test It
		Test_rawReadIDs(myReader);
	}
	
	/**
	 * This test case validates the rawReadIDs method.
	 * The latter displays all tags in fields without applying any
	 * of the programmed tag selectors.
	 * 
	 * @throws Exception
	 */
	public static void Test_rawReadIDs_CAEN() throws Exception {
		ReaderDevice myReader;
				
		// Reader singleton
		myReader = ReaderFactory.getReader(ReaderFactory.CAEN_READER, "CAENReaderDevice");
		
		// Test It
		Test_rawReadIDs(myReader);
	}
	
	/**
	 * This test case validates the rawReadIDs method.
	 * The latter displays all tags in fields without applying any
	 * of the programmed tag selectors.
	 * 
	 * @throws Exception
	 */
	public static void Test_rawReadIDs(ReaderDevice myReader) throws Exception {
		Source mySource;
		DataSelector myDataSelector;
				
		// Establish Connections
		ReaderFactory.connectReader(myReader);
		
		// Dump reader Info
		Test_Support(myReader);
		
		// Reader's Data Selector : default singleton for selecting IDs
		// This is is the default data selector that supports at least the tag ID field
		myDataSelector = myReader.getCurrentDataSelector(); // get Default Data Selector and update it
		
		// TODO :  validate reader support for EPCID, which is mandatory
		//myDataSelector.getAllTagFieldNames()
		
		// Reader's source : Singleton
		mySource = myReader.getCurrentSource();
		
		// Perform 10 read cycles
		for( int i = 0; i < 10 ; i++) {
			System.out.println("Read Cycle " + i + " at " + myReader.getTimeTicks() + " ticks");
			
			// Inventory
			ReadReport myReadReport = mySource.rawReadIDs(myDataSelector);
			
			if (myReadReport == null) {
				System.err.println("Error : mySource.rawReadIDs returned a null report ");
			}
			else {
				// Display read Tags
				String[] tagIds = myReadReport.getTagIds(mySource.getName());
				if (tagIds == null) {
					System.err.println("Error : myReadReport.getTagIds returned null ");
				}
				else if (tagIds.length == 0) {
					System.out.println("\t NO TAGS");
				}
				else {
					for(int j = 0 ; j < tagIds.length; j++)
						System.out.println("\tObserved : 0x" + tagIds[j]);
				}
			}
		}
		
		// graceful disconnect : equivalent to ReaderFactory.disconnectReader(myReader);
		myReader.goodbye();
		
		System.out.println("Done");
	}
	
}
