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

package org.ow2.aspirerfid.ale.engine;

import gnu.javax.comm.wce.WCECommDriver;

import java.awt.Toolkit;
import java.util.Arrays;

import com.caen.RFIDLibrary.CAENRFIDLogicalSource;
import com.caen.RFIDLibrary.CAENRFIDPort;
import com.caen.RFIDLibrary.CAENRFIDProtocol;
import com.caen.RFIDLibrary.CAENRFIDReader;
import com.caen.RFIDLibrary.CAENRFIDTag;
import com.intermec.datacollection.rfid.BasicBRIReader;
import com.intermec.datacollection.rfid.BasicReaderException;

public class Geiger {
	
	 public static void IntermecTestCase() throws Exception {
		 BasicBRIReader myReader;
		 
		 // connect to reader
		 myReader = new BasicBRIReader();
		 myReader.open();
		 
		 try {
	         /* Scan for a tag */
	         //urn:epc:tag:sgln-96:1.211298.070875.43981
	         //urn:epc:raw:96.x3238CE5882B629000000ABCD
	         byte[] tag = {0x32,0x38,(byte) 0xCE,0x58,(byte) 0x82,0x29,(byte) 0xB6,0x00,0x00,0x00,(byte) 0xAB,(byte) 0xCD};
	         Scanner aScanner = new Scanner();
	         aScanner.start();
	         //int[] dbs = {25, 55, 85, 100} ;
	         int[] dbs = {25 /*32 mw*/, 65 /*200 mw*/, 85 /*500 mw*/, 100 /*1W*/} ;
	         long[] periods = {125,250,500,700}; // mS
	         for (int i = 0 ; i<10;) {
		         aScanner.scan(new IntermecIP30BRIWrapper(myReader), 
		        		 dbs, periods, tag);
		         Thread.sleep(100);
	         }
	         aScanner.end();
		 }
		 finally {
	         // Disconnect
			 System.out.println("Disconnecting");
			 myReader.close(); 
			 System.out.println("Disconnected");
		 }
		 System.out.println("Done");	 
		 
	 }
	 
	 
	 public static void CAEN_TestCase() throws Exception {
	        CAENRFIDReader myReader = new CAENRFIDReader();
	        CAENRFIDLogicalSource[] myReaderSources;
	        CAENRFIDLogicalSource mySource_0 ;
	        CAENRFIDTag[] myRFIDTags;
	        int timeout = 0;
	        
	        try {
	            // Load com library
	    		System.loadLibrary("javaxcomm");
	    		// power up reader
		        WCECommDriver.powerUpCaenReader();
		        
		        Thread.sleep(300);
		        
		        /* Connect to reader on COM1 */
		        System.out.println("Connecting to reader : " + "RS232 : COM1");
		        myReader.Connect(CAENRFIDPort.CAENRFID_RS232, "COM1:");
		        System.out.println("Connection Success");
		        
		        /* configure Reader */
		        // set protocol  : default is CAENRFID_EPC_C1G2
		        myReader.SetProtocol(CAENRFIDProtocol.CAENRFID_EPC_C1G2);
		        // set bitRate
		        //System.out.println("Setting BitRate");
		        //myReader.SetBitRate(CAENRFIDBitRate.TX10RX40);
		        //myReader.SetBitRate(CAENRFIDBitRate.TX40RX40);
		        //myReader.SetBitRate(CAENRFIDBitRate.TX40RX160);
		        //System.out.println("SetBitRate OK");
		        // set RF regulation
		        //myReader.SetRFRegulation(CAENRFIDRFRegulations.ETSI_302208);
		        
		        
		        // get and setup Logical Source 0
		        myReaderSources = myReader.GetSources();
		        mySource_0 = myReaderSources[0];
		        // set Q value
		        //mySource_0.SetQ_EPC_C1G2(3);
		        // set session 
		        //mySource_0.SetSession_EPC_C1G2(CAENRFIDLogicalSourceConstants.EPC_C1G2_SESSION_S0);
		        // set Power
//		        myReader.SetPower(power);  // 500 mW
//		        System.out.println("SetPower OK");
		        
		        // Tweak : Reset connection, otherwise won't work !!!
		        myReader.Disconnect();
		        myReader.Connect(CAENRFIDPort.CAENRFID_RS232, "COM1:");
		        
		        // set Power
		        System.out.println("SetPower 10 mw" );
		        myReader.SetPower(10);  // 10 mW
		        System.out.println("SetPower OK " + myReader.GetPower());
		                 
		         /* Scan for a tag */
		         //urn:epc:tag:sgln-96:1.211298.070875.43981
		         //urn:epc:raw:96.x3238CE5882B629000000ABCD
		         byte[] tag = {0x32,0x38,(byte) 0xCE,0x58,(byte) 0x82,0x29,(byte) 0xB6,0x00,0x00,0x00,(byte) 0xAB,(byte) 0xCD};
		         Scanner aScanner = new Scanner();
		         aScanner.start();
		         int[] powers = {25, 100, 200, 500} ; // mw
		         long[] periods = {100,125,250,500}; // mS
		         for (int i = 0 ; i<10;) {
			         aScanner.scan(new CaenReaderWrapper(myReader, mySource_0), 
						  		           powers, periods, tag);
			         //Thread.sleep(100);
		         }
		         aScanner.end();
		         
		         // Disconnect
				 System.out.println("Disconnecting");
				 myReader.Disconnect(); 
				 System.out.println("Disconnected");
				 
				 System.out.println("Done");
	        }
	         finally {
	             // Power Down CAEN Reader device on PSION
	             WCECommDriver.powerDownCaenReader();  
	           }    
	    }
		 
	}


	//////////// Geiger Test ////////////////

/**
 * 
 * @author rdagher
 *
 */
class Scanner {
	public static long NOT_FOUND_PERIOD_MS = 1000;
	
	private Ticker aTikker;
	
	private Thread aTikkerThread;
	
	
	public void start() {
		aTikker = new Ticker();
		aTikker.period.setTimeout(NOT_FOUND_PERIOD_MS); // 1s beep
		aTikkerThread = new Thread(aTikker);
		aTikkerThread.start();
	}
	
	
	public void end() {
		aTikker.alive = false;
	}
	
	/**
	 * 
	 * @param aReader
	 * @param powers example  50mw, 200mw, 500mw
	 * @param periods example 125ms, 250 ms, 500ms
	 * @param id
	 * @throws Exception 
	 */
	public void scan (Reader aReader, int[] powers, long[] periods, byte[] id) throws Exception {
		int idx = -1 ; // the index of the 
		
		// for each power look for tag 
		for (int i = 0 ; i< powers.length; i++) {
			aReader.setPower(powers[i]);
			// if tag observed for first time
			if ((idx == -1) &&(aReader.hasTagInField(id))) {
				idx = i; // buffer power index
				System.out.println("Found @ power " + powers[i]);
				break; // Attention : added for intermec
			}
		}
		
		// if tag observed, tell tikker to beep at the given period
		if (idx != -1) {
			aTikker.period.setTimeout(periods[idx]);
		} else {
			aTikker.period.setTimeout(NOT_FOUND_PERIOD_MS);
		}		
	}
}

/**
 * Reader abtraction for scanner.
 * @author rdagher
 *
 */
interface Reader{
	public void setPower(int pw) throws Exception;	
	public boolean hasTagInField(byte[] tagId) throws Exception;
}

/**
 * Caen Wrapper.
 * @author rdagher
 *
 */
class CaenReaderWrapper implements Reader {
	private CAENRFIDReader myReader;
	private CAENRFIDLogicalSource mySource_0 ;

	private int timeout = 5;
	private int retry = 5;
	
	
	/**
	 * Constructor
	 * @param myReader
	 * @param mySource_0
	 */
	public CaenReaderWrapper(CAENRFIDReader myReader,
			CAENRFIDLogicalSource mySource_0) {
		this.myReader = myReader;
		this.mySource_0 = mySource_0;
		
	}

	public boolean hasTagInField(byte[] tagId) throws Exception {
		boolean found = false;
		CAENRFIDTag[] myRFIDTags = null;
		
		  // for all cycles
	      for (int i = 0; (i < timeout) && (myRFIDTags == null) ; i++) {
	    	  myRFIDTags = mySource_0.InventoryTag();
	        //myRFIDTags = mySource_0.InventoryTag(tagId, (short)96/*bits*/, (short)0/* start index*/);
	      }
          
          /* Analyze received Tags */
          if (myRFIDTags != null) {
            for (int i = 0 ; (i < myRFIDTags.length) && (!found); i++) {
                byte[] id = myRFIDTags[i].GetId();
                
                if (Arrays.equals(id, tagId)) {
                     found = true;
                     //System.out.print("tag @source : " + myRFIDTags[i].GetSource().GetName() + "\n[" );
                
                    //for (int j = 0 ; j < id.length; j++)
                    //  System.out.print(TestCase.byteTohex(id[j]));
                }    		  
            }
          }
		return found;
	}

	public void setPower(int pw) throws Exception {
		this.myReader.SetPower(pw);
	}	
}

/**
 * Intermec IP30 BRI Reader Wrapper.
 * @author rdagher
 *
 */
class IntermecIP30BRIWrapper implements Reader {
	private BasicBRIReader myReader;
	private StringBuffer cmd ;
	private StringBuffer pwCmd ;
	
	/**
	 * Constructor
	 * @param myReader
	 */
	public IntermecIP30BRIWrapper(BasicBRIReader myReader) {
		this.myReader = myReader;
		// scan command
		this.cmd = new StringBuffer();
		cmd.append("RD WHERE HEX(1:4,12)=H");
		
		// set power command
		this.pwCmd = new StringBuffer();
		pwCmd.append("ATTRIBUTE FIELDSTRENGTH="); // numerical value
	}

	public boolean hasTagInField(byte[] tagId) {
		boolean found = false;
		String rsp = null;
		int abyte;
		
		// convert tag id to BRI syntax by appending it to scan command
		cmd.setLength(22);
		for(int i = 0 ; i<tagId.length; i++) {
			abyte = (tagId[i] & 0xFF);
			if (abyte < 16) cmd.append('0');
			cmd.append(Integer.toHexString(abyte));
		}
		//System.out.println("Sending cmd : " + cmd.toString());

		// scan tag
		try {
			rsp = this.myReader.execute(cmd.toString());
			// found if no timeout -> the response starts with H32...
			//System.out.println("RX: " + rsp);
			found = ((rsp!=null) && rsp.startsWith("H32"));
		} catch (BasicReaderException e) {
			found = false;
		}

		return found;
	}

	public void setPower(int db) throws Exception {
		pwCmd.setLength(24);
		pwCmd.append(db);
		//System.out.println("Sending pwCmd : " + pwCmd.toString());
		this.myReader.execute(pwCmd.toString());
	}	
}


/**
 * Tickker thread.
 * @author rdagher
 *
 */
class Ticker implements Runnable{
	PeriodSemaphore period = new PeriodSemaphore();
	boolean alive = true; // atomic
	
	public void run() {
		while(alive) {
			
			Toolkit.getDefaultToolkit().beep();
			try {
				Thread.sleep(period.getTimeout());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}	
}


class PeriodSemaphore {
	private long timeout = 0 ;

	/**
	 * Getter for timeout
	 * @return the timeout
	 */
	public synchronized long getTimeout() {
		return timeout;
	}

	/**
	 * Setter for timeout
	 * @param timeout the value to set
	 */
	public synchronized void setTimeout(long timeout) {
		this.timeout = timeout;
	}	
}


