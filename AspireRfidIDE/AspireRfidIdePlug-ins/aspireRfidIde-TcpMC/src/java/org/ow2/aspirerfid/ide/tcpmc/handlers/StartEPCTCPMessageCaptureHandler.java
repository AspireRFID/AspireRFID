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


package org.ow2.aspirerfid.ide.tcpmc.handlers;

import java.io.IOException;
import java.util.HashMap;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.ide.tcpmc.Activator;
import org.ow2.aspirerfid.ide.tcpmc.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.tcpmc.views.EpcTcpMessageCapturerView;
import org.ow2.aspirerfid.ide.tcpmc.epcmc.CaptureApp;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class StartEPCTCPMessageCaptureHandler extends AbstractHandler implements IHandler {
	/** Returns the preference store for this UI plug-in */
	IPreferenceStore preferences = Activator.getDefault().getPreferenceStore();
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		EpcTcpMessageCapturerView epcTcpMessageCapturerView = getEpcTcpMessageCapturerView();
		int port = 0;
		CaptureApp client = null;
		
		
		port = Integer.parseInt(preferences.getString(PreferenceConstants.P_RawEpcListeningPort));
		try {
			client = new CaptureApp(port);
	
		
		if(PreferenceConstants.P_EpcTcpMessageCaptureThreads==null){
			PreferenceConstants.P_EpcTcpMessageCaptureThreads = new HashMap<String, CaptureApp>();
		}
		
		PreferenceConstants.P_EpcTcpMessageCaptureThreads.put("EpcTcpMessageCaptureThread", client);
		epcTcpMessageCapturerView.writeStartMassageToEpcTagsGroup();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private EpcTcpMessageCapturerView getEpcTcpMessageCapturerView() {

		for (int i = 0; i < PlatformUI.getWorkbench().getWorkbenchWindows().length; i++) {
			IWorkbenchWindow iWorkbenchWindow = PlatformUI.getWorkbench().getWorkbenchWindows()[i];
			for (int j = 0; j < iWorkbenchWindow.getPages().length; j++) {
				IWorkbenchPage iWorkbenchPage = iWorkbenchWindow.getPages()[j];
				for (int k = 0; k < iWorkbenchPage.getViewReferences().length; k++) {
					if (iWorkbenchPage.getViewReferences()[k].getId().equals(EpcTcpMessageCapturerView.ID)) {
						return (EpcTcpMessageCapturerView) iWorkbenchPage.getViewReferences()[k].getView(false);
					}
				}
			}
		}
		return null;

	}
}
