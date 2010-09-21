/*
   Copyright 2005-2010, OW2 Aspire RFID project 
   
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
package org.ow2.aspirerfid.ale.server.readers.rp.tagsys;

import java.util.Map;

import org.ow2.aspirerfid.ale.server.Tag;
import org.ow2.aspirerfid.ale.server.readers.rp.base.AbstractAdaptor;
import org.ow2.aspirerfid.ale.server.readers.rp.base.AbstractReader;

import com.tagsys.protocol.stxe.TSTXe;
import com.tagsys.protocol.stxe.TSTXeErrorException;
import com.tagsys.protocol.stxe.TSTXeSerialRXTX;
import com.tagsys.protocol.stxe.TSTXeTCP;
import com.tagsys.reader.mediolx00.TMedioLx00;
import com.tagsys.reader.mediolx00.TMedioLx00BufferEntry;

/**
 * 
 * @author Gabriel Pedraza Ferreira
 *         Kiev Gama
 *
 */
public class TagsysReader extends AbstractReader {

	/**
	 * Reader device (TagsysMedio library)
	 */
	private TMedioLx00 readerDevice;

	/**
	 * Reader protocol (TagsysMedio library)
	 */
	private TSTXe readerProtocol;

	/**
	 * Object that will receive reader's buffer entry (Tagsysmedio library)
	 */
	private TMedioLx00BufferEntry bufferEntry;

	/**
	 * Reader device settings
	 */
	private TagsysSettings mySettings;

	/**
	 * Default constructor
	 * 
	 * @param adaptor
	 *           the base reader adaptor
	 * @param properties
	 *           the settings
	 */
	public TagsysReader(AbstractAdaptor adaptor, Map<String, String> properties) {
		super(adaptor, properties);
		bufferEntry = new TMedioLx00BufferEntry();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ow2.aspirerfid.ale.server.readers.rp.base.AbstractReader#closeReader()
	 */
	@Override
	protected void closeReader() {
		try {
			if (readerDevice != null) {
			   readerDevice.stopRead();
				readerDevice.setRf(TMedioLx00.RF_OFF);
			}
			if (readerProtocol != null)
				readerProtocol.close();
		} catch (TSTXeErrorException e) {
			e.printStackTrace();
		}
		setState(STATE_NO_READER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ow2.aspirerfid.ale.server.readers.rp.base.AbstractReader#openReader()
	 */
	@Override
	protected void openReader() {
		
		// Create corresponding protocol object and attach it to reader object
		try {
			
			// Close previous media if existing
			if (readerProtocol != null)
				readerProtocol.close();
			
			// Determines the protocol to be used
			switch (mySettings.iMediaType) {

				case TagsysSettings.MEDIA_SERIAL:
					readerProtocol = new TSTXeSerialRXTX(mySettings.strSerialName, mySettings.iSerialSpeed);
					break;

				case TagsysSettings.MEDIA_ETHERNET:
					readerProtocol = new TSTXeTCP(mySettings.strIPAddress, mySettings.iTCPPort);
					break;
			}

			readerProtocol.setMode(TSTXe.FAST_MODE);

			if (readerDevice == null) {
				readerDevice = new TMedioLx00(readerProtocol);
			} else {
				readerDevice.changeProtocol(readerProtocol);
			}

			// Reset reader
			readerDevice.restoreDefaults();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}

			readerDevice.setRf(mySettings.iChannel);
			readerDevice.setPower(TMedioLx00.GRANULARITY_500MW, mySettings.iPower);
			readerDevice.setCont(mySettings.boCont);
			readerDevice.setSort(mySettings.boSort);
			// Set ISO15693 uplink to ASK
			readerDevice.setUplinkISO15693(TMedioLx00.ISO15693_ASK, TMedioLx00.ISO15693_1OF4);

			System.out.println("--->" + readerDevice.getReaderVersion().toString());

		   // Determines the tag type to be read
			switch (mySettings.iTagType) {
				case TMedioLx00BufferEntry.TAG_C210:
					readerDevice.startReadC210(TMedioLx00.READ_PARAM_NULL);
					break;
				case TMedioLx00BufferEntry.TAG_C220:
					readerDevice.startReadC220(TMedioLx00.READ_PARAM_NULL);
					break;
				case TMedioLx00BufferEntry.TAG_C240:
					readerDevice.startReadC240(TMedioLx00.READ_PARAM_NULL);
					break;
				case TMedioLx00BufferEntry.TAG_C270:
					readerDevice.startUnselectedReadC270(0, 0, 1);
					break;
				case TMedioLx00BufferEntry.TAG_ISO15693:
					readerDevice.startReadISO15693(false, TMedioLx00.READ_PARAM_NULL);
					break;
				case TMedioLx00BufferEntry.TAG_EPC:
					readerDevice.startReadEPC(false, false, 0);
					break;
			}

			setState(STATE_READY);
		} catch (TSTXeErrorException e) {
			System.err.println("Error opening reader : ");
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.ale.server.readers.rp.base.AbstractReader#read()
	 */
	@Override
	protected void read() {
		boolean boContinue;
		int iMaxScans = 5;
		// List<Tag> tagsToNotify = new LinkedList<Tag>();

		// While there are tags in reader's buffer
		while (iMaxScans-- != 0) {
			try {
				boContinue = readerDevice.getBufferEntry(false, bufferEntry);

			} catch (TSTXeErrorException e) {
				System.err.println(e.getLocalizedMessage());
				setState(STATE_NO_READER);
				return;
			}

			if (!boContinue) {
				break;
			}

			System.out.println(bufferEntry.getTagTypeAsString() + ":" + bufferEntry.getChannelAsString() + ":"
			      + bufferEntry.getDataAsString().substring(0, bufferEntry.getDataAsString().length() - 2));

			String tagType = bufferEntry.getTagTypeAsString();
			String tagId = bufferEntry.getDataAsString().substring(0, bufferEntry.getDataAsString().length() - 2);								
			String tagIDasPureId = tagId;
			
			if (tagType.equalsIgnoreCase("ISO15693"))
				tagIDasPureId = convertISO15693toPureURI(tagId);				
			
			Tag tag = new Tag(adaptor.getName(), tagIDToByte(tagId), tagIDasPureId, System.currentTimeMillis());

			synchronized (readingBuffer) {
				readingBuffer.add(tag);
			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ow2.aspirerfid.ale.server.readers.rp.base.AbstractReader#setSettings
	 * (java.util.Map)
	 */
	@Override
	protected void setSettings(Map<String, String> properties) {
		mySettings = new TagsysSettings();
		mySettings.set(properties);
	}

}
