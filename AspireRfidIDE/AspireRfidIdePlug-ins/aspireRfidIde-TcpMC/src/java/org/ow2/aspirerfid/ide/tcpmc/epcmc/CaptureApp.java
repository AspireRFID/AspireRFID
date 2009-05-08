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


package org.ow2.aspirerfid.ide.tcpmc.epcmc;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.accada.ale.util.DeserializerUtil;
import org.accada.ale.xsd.ale.epcglobal.ECReport;
import org.accada.ale.xsd.ale.epcglobal.ECReportGroup;
import org.accada.ale.xsd.ale.epcglobal.ECReportGroupListMember;
import org.accada.ale.xsd.ale.epcglobal.ECReports;
import org.accada.ale.xsd.epcglobal.EPC;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.ide.tcpmc.views.EpcTcpMessageCapturerView;


/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class CaptureApp extends Thread {

	/**
	 * The logger.
	 */
	private static Log log = LogFactory.getLog(CaptureApp.class);

	private ServerSocket serverSocket = null;

	private int port;

	private boolean activated = false;

	EpcTcpMessageCapturerView epcTcpMessageCapturerView = null;

	public CaptureApp(int port) throws IOException {
		this.port = port;
		epcTcpMessageCapturerView = getEpcTcpMessageCapturerView();
		serverSocket = new ServerSocket(port);
		activated = true;
		start(); // Calls run()
	}

	private void handleReports(ECReports reports) throws IOException, JAXBException {
		log.debug("****Handling incomming reports****");

		List<ECReport> theReports = reports.getReports().getReport();
		// collect all the tags
		List<EPC> epcs = new LinkedList<EPC>();
		if (theReports != null) {
			for (ECReport report : theReports) {
				// log.debug("Report Count: "+report.getGroup().size());
				log.debug("****Report Name:" + report.getReportName() + "****");
				if (report.getGroup() != null) {
					for (ECReportGroup group : report.getGroup()) {
						log.debug("Group Count: " + group.getGroupCount().getCount());
						log.debug("Group Name: " + group.getGroupName());
						if (group.getGroupList() != null) {
							for (ECReportGroupListMember member : group.getGroupList().getMember()) {
								if (member.getEpc() != null) {
									log.debug("***Recieved Group Values***");
									log.debug("RawDecimal Value: " + member.getRawDecimal().getValue());
									{
										if (!(member.getEpc() == null))
											epcs.add(member.getEpc());
										log.debug("Epc Value: " + member.getEpc().getValue());
										if ((member.getEpc() == null))
											log.debug("Epc Value: null");
									}
									log.debug("RawHex Value: " + member.getRawHex().getValue());
									log.debug("Tag Value: " + member.getTag().getValue());
									// log.debug("Group Value:"+member.getExtension().getFieldList().toString());
								}
							}
						}
					}
				}
			}
		}

		if (epcs.size() == 0) {
			log.debug("no epc received - generating no event");
			epcTcpMessageCapturerView.clearObservationTable();
			return;
		}
		else {

			if (!epcTcpMessageCapturerView.equals(null))
				epcTcpMessageCapturerView.fillObservationTable(epcs);

		}
	}

	public void run() {

		log.debug("Listening to port (" +port+") for messages");
		
		while (activated) {
			try {
				Socket s = serverSocket.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

				String data = in.readLine();
				String buf = "";
				// ignore the http header
				data = in.readLine();
				data = in.readLine();
				data = in.readLine();
				data = in.readLine();

				log.debug("Recieved Data From Port " + port);
				while (data != null) {
					buf += data;
					data = in.readLine();
					log.debug("XML DATA: " + data);
				}
				// log.debug("Recieved Buffer:"+buf);

				// create a stream from the buf
				InputStream parseStream = new ByteArrayInputStream(buf.getBytes());

				// parse the string

				ECReports reports = DeserializerUtil.deserializeECReports(parseStream);
				if (reports != null) {
					handleReports(reports);
				}
			}
			catch (Exception e) {
				log.debug("ERROR: " + e.getMessage() + "\n");
			}
		}
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
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