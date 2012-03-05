/*
 * Copyright (C) 2008-2010, Aspire
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


package org.ow2.aspirerfid.beg.core;

import java.io.*;

import javax.jws.*;
import javax.xml.bind.annotation.*;
import java.util.*;


import org.ow2.aspirerfid.commons.beg.model.BusinessCtx;
import org.ow2.aspirerfid.commons.beg.model.EventStatus;
import org.ow2.aspirerfid.commons.beg.interfaces.BegInterface;

import java.util.logging.Logger;
import org.ow2.aspirerfid.beg.core.capture.CaptureReport;
import javax.xml.bind.JAXBElement;
import java.text.ParseException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


import org.ow2.aspirerfid.commons.epcis.model.ArrayOfString;
import org.ow2.aspirerfid.commons.epcis.model.QueryParam;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyType;

import org.ow2.aspirerfid.beg.core.query.MasterDataQueryClient;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */

@WebService(endpointInterface="org.ow2.aspirerfid.commons.beg.interfaces.BegInterface")
public class BegInterfaceImpl implements BegInterface
{

	private static final Logger LOG = Logger.getLogger(BegInterfaceImpl.class.getName());
	
	
	private HashMap<String, CaptureReport> eventGenerators = new HashMap<String, CaptureReport>();

	/**
	 * @param repositoryQueryURL
	 * @return List of VocabularyElementType
	 */
	public List<VocabularyElementType> getEventList(String repositoryQueryURL) {
		// SetUp the EPCIS Repository Query URL
		MasterDataQueryClient queryClient = new MasterDataQueryClient(repositoryQueryURL);

		List<VocabularyElementType> vocabularyElementList = null;

		// Contains the data for the result table.
		List<VocabularyType> vocabularyList = null;

		try {
			queryClient.clearParameters();
			
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataQuery");
			queryClient.addParameter(queryParam);

			QueryParam param2 = new QueryParam();
			param2.setName("includeAttributes");
			param2.setValue(true);
			queryClient.addParameter(param2);
			QueryParam param3 = new QueryParam();
			param3.setName("includeChildren");
			param3.setValue(false);
			queryClient.addParameter(param3);

			ArrayOfString vocabularyType = new ArrayOfString();

			vocabularyType.getString().add("urn:epcglobal:epcis:vtype:BusinessTransaction");
			QueryParam param5 = new QueryParam();
			param5.setName("vocabularyName");
			param5.setValue(vocabularyType);
			queryClient.addParameter(param5);

			vocabularyList = queryClient.runMasterDataQuery();

		}
		catch (ParseException e) {
			String msg = "Unable to parse a Time value.";
			System.out.print("\n" + msg + "\n");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			System.out.print(sw.toString());
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, msg + "\n" + e.getMessage(), "Service invocation error", JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception e) {
			String msg = "Unexpected error while invoking EPCIS Query Interface.";
			System.out.print("\n" + msg + "\n");
			System.out.print(e.toString());
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			System.out.print(sw.toString());
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, msg + "\n" + e.toString(), "Service invocation error", JOptionPane.ERROR_MESSAGE);
		}

		for (Object o : vocabularyList) {
			if (o instanceof JAXBElement<?>) {
				o = ((JAXBElement<?>) o).getValue();
			}
			VocabularyType vocabulary = (VocabularyType) o;

			vocabularyElementList = vocabulary.getVocabularyElementList().getVocabularyElement();

		}

		
		
//		return cleanVocabularyElementsParents(vocabularyElementList);
		
		return vocabularyElementList;

	}

	public EventStatus getEpcListForEvent(String eventID) {
		EventStatus eventStatus = new EventStatus();
		if (eventGenerators.containsKey(eventID)) {
			eventStatus.setEpcList(eventGenerators.get(eventID).getEpcListForTransaction());
			eventStatus.setTransactionID(eventGenerators.get(eventID).getBizTransactionID());

			
			
		}
		return eventStatus;
	}

	/**
	 * @param vocabularyElementType
	 * @param repositoryCaptureURL
	 * @param begListeningPort
	 * @return boolean
	 */
	public boolean startBegForEvent(VocabularyElementType vocabularyElementType, String repositoryCaptureURL, String begListeningPort) {

		CaptureReport captureReport;
		try {
			captureReport = new CaptureReport(vocabularyElementType, repositoryCaptureURL, begListeningPort);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		eventGenerators.put(vocabularyElementType.getId(), captureReport);

		return true;
	}

	/**
	 * @param eventID
	 * @return boolean
	 */
	public boolean stopBegForEven(String eventID) {

		if (eventGenerators.containsKey(eventID)) {

			try {
				eventGenerators.get(eventID).setActivated(false);
				eventGenerators.get(eventID).getServerSocket().close();
				eventGenerators.remove(eventID);
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}

			return true;
		}
		return false;
	}

	public ArrayList<String> getStartedEvents() {

		ArrayList<String> startedEvents = new ArrayList<String>();
		startedEvents.addAll(eventGenerators.keySet());
		if (!startedEvents.isEmpty()) {
			return startedEvents;
		}
		return null;
	}

	private List<VocabularyElementType> cleanVocabularyElementsParents(List<VocabularyElementType> vocabularyElementList) {

		String trueVocabularyElementID = null;
		String[] compositeVocabularyElementID = null;

		for (VocabularyElementType vocabularyElementType : vocabularyElementList) {
			compositeVocabularyElementID = vocabularyElementType.getId().split(",");
			if (compositeVocabularyElementID.length > 1) {
				trueVocabularyElementID = compositeVocabularyElementID[compositeVocabularyElementID.length - 1];
				vocabularyElementType.setId(trueVocabularyElementID);
			}
		}

		return vocabularyElementList;
	}




}