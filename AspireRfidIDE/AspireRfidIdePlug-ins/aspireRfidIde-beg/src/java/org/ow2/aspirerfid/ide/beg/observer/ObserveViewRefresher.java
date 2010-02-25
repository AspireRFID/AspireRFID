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

package org.ow2.aspirerfid.ide.beg.observer;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.ide.beg.Activator;
import org.ow2.aspirerfid.commons.beg.model.EventStatus;
import org.ow2.aspirerfid.ide.beg.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.beg.utils.BegEngineClient;
import org.ow2.aspirerfid.ide.beg.views.BegObservationView;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
public class ObserveViewRefresher extends Thread {

	/*
	 * Could use alternatively the Observer Pattern (implements Observer,extends
	 * Observable, notifyObservers()) a nice tutorial can be found at:
	 * http://elope.wordpress.com/2006/04/07/tutorial-on-observer-pattern-2/
	 */

	private boolean activated = true;
	private String eventID = "";
	EventStatus eventStatus = new EventStatus();

	/** Returns the preference store for this UI plug-in */
	IPreferenceStore begConfigPreferences = Activator.getDefault().getPreferenceStore();

	/** Returns the preference store for this UI plug-in */
	IPreferenceStore preferences = Activator.getDefault().getPreferenceStore();

	public ObserveViewRefresher(String eventID) {

		this.eventID = eventID;

		if (PreferenceConstants.begEngineClient == null) {

			PreferenceConstants.begEngineClient = new BegEngineClient(begConfigPreferences.getString(PreferenceConstants.P_BegEngineURL));
		}

		start(); // Calls run()

	}

	public void run() {

		while (activated) {

			eventStatus = PreferenceConstants.begEngineClient.getEpcListForEvent(eventID);

			BegObservationView begObservationView = getBegObservationView();
			if (!begObservationView.equals(null))

				
					begObservationView.fillObservation(eventStatus);
			
			try {
				Thread.sleep(preferences.getInt(PreferenceConstants.P_ObservatorRefreshRate) * 1000);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private BegObservationView getBegObservationView() {

		for (int i = 0; i < PlatformUI.getWorkbench().getWorkbenchWindows().length; i++) {
			IWorkbenchWindow iWorkbenchWindow = PlatformUI.getWorkbench().getWorkbenchWindows()[i];
			for (int j = 0; j < iWorkbenchWindow.getPages().length; j++) {
				IWorkbenchPage iWorkbenchPage = iWorkbenchWindow.getPages()[j];
				for (int k = 0; k < iWorkbenchPage.getViewReferences().length; k++) {
					if (iWorkbenchPage.getViewReferences()[k].getId().equals(BegObservationView.ID)) {
						return (BegObservationView) iWorkbenchPage.getViewReferences()[k].getView(false);
					}
				}
			}
		}
		return null;

	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

}
