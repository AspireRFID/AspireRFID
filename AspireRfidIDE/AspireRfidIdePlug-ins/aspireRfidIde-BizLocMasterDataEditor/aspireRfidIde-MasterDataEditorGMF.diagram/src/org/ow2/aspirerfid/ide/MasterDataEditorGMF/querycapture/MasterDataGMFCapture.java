/*
 * Copyright © 2008-2010, Aspire
 * 
 * Aspire is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 2.1 as published by
 * the Free Software Foundation (the "LGPL").
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library in the file COPYING-LGPL-2.1; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public License
 * for the specific language governing rights and limitations.
 */

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.preferences.DiagramConfiguratorPreferenceConstants;
//import org.ow2.aspirerfid.ide.masterdata.classes.EpcisConstants;
//import org.ow2.aspirerfid.ide.masterdata.tools.MasterDataCaptureClient;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.tools.EpcisConstants;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.tools.MasterDataCaptureClient;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public final class MasterDataGMFCapture {
	
	/**
	 * The endpoint URL for the capture service.
	 */
	private static String captureUrl = DiagramConfiguratorPreferenceConstants.P_CaptureURL;
	
	/**
	 * The MasterDataCaptureClient for the capture service.
	 */
	private static MasterDataCaptureClient captureClient = new MasterDataCaptureClient(captureUrl);
	
	/**
	 * The logger.
	 */
	private static Log log = LogFactory.getLog(MasterDataGMFCapture.class);
		
	/**
	 * Insert in EPCIS the Company, Warehouse, Container attributes
	 */
	public static void insertInEPCIS() {
		MasterDataEditParts.setInsertedIntoEpcis(false);
		boolean simpleMasterDataInsertionSucceeded = false;
		
		//alter the BizLoc
		if (!MasterDataEditParts.getAlteredBizLocURIs().isEmpty()) {
			Set<Entry<String, String>> set = MasterDataEditParts.getAlteredBizLocURIs().entrySet();
			Iterator<Entry<String, String>> itr = set.iterator();
			
			while (itr.hasNext()) {
				Entry<String, String> item = itr.next();
				simpleMasterDataInsertionSucceeded = captureClient.simpleMasterDataAlter(EpcisConstants.BUSINESS_LOCATION_ID, item.getKey(), item.getValue());
			}
		}
		
		//insert in BizLoc
		simpleMasterDataInsertionSucceeded = captureClient.simpleMasterDataMassInsert(EpcisConstants.BUSINESS_LOCATION_ID, MasterDataEditParts.getInsertedBizLocURIs());
		
		//delete from BizLoc
		if (!MasterDataEditParts.getDeletedBizLocURIs().isEmpty())
			simpleMasterDataInsertionSucceeded = captureClient.simpleMasterDataMassDelete(EpcisConstants.BUSINESS_LOCATION_ID, MasterDataEditParts.getDeletedBizLocURIs());
		
		//insert the BizLoc attributes
		if (!MasterDataEditParts.getBizLocUriAttributesValues().isEmpty()) {
			Set<Entry<String, HashMap<String, String>>> set = MasterDataEditParts.getBizLocUriAttributesValues().entrySet();
			Iterator<Entry<String, HashMap<String, String>>> itr = set.iterator();
			
			while (itr.hasNext()) {
				Entry<String, HashMap<String, String>> item = itr.next();
				simpleMasterDataInsertionSucceeded = captureClient.simpleMasterDataAndMassAttributeInsertOrAlter(EpcisConstants.BUSINESS_LOCATION_ID, item.getKey(), item.getValue());
			}
		}
		
		//alter the ReadPoint	
		if (!MasterDataEditParts.getAlteredReadPointURIs().isEmpty()) {
			Set<Entry<String, String>> set = MasterDataEditParts.getAlteredReadPointURIs().entrySet();
			Iterator<Entry<String, String>> itr = set.iterator();
			
			while (itr.hasNext()) {
				Entry<String, String> item = itr.next();
				simpleMasterDataInsertionSucceeded = captureClient.simpleMasterDataAlter(EpcisConstants.READ_POINT_ID, item.getKey(), item.getValue());
			}
		}
		
		//insert in ReadPoint
		simpleMasterDataInsertionSucceeded = captureClient.simpleMasterDataMassInsert(EpcisConstants.READ_POINT_ID, MasterDataEditParts.getInsertedReadPointURIs());
		
		//delete from ReadPoint
		if (!MasterDataEditParts.getDeletedReadPointURIs().isEmpty())
			simpleMasterDataInsertionSucceeded = captureClient.simpleMasterDataMassDelete(EpcisConstants.READ_POINT_ID, MasterDataEditParts.getDeletedReadPointURIs());
		
		//insert the ReadPoint attributes
		if (!MasterDataEditParts.getReadPointUriAttributesValues().isEmpty()) {
			Set<Entry<String, HashMap<String, String>>> set = MasterDataEditParts.getReadPointUriAttributesValues().entrySet();
			Iterator<Entry<String, HashMap<String, String>>> itr = set.iterator();
			
			while (itr.hasNext()) {
				Entry<String, HashMap<String, String>> item = itr.next();
				simpleMasterDataInsertionSucceeded = captureClient.simpleMasterDataAndMassAttributeInsertOrAlter(EpcisConstants.READ_POINT_ID, item.getKey(), item.getValue());
			}
		}

		if (simpleMasterDataInsertionSucceeded) {
			log.debug("Master Data saccesfuly saved!\n");
			MasterDataEditParts.setInsertedIntoEpcis(true);
		}
		else {
			log.debug("The Master Data could NOT be captured!\n");
			MasterDataEditParts.setInsertedIntoEpcis(false);
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
