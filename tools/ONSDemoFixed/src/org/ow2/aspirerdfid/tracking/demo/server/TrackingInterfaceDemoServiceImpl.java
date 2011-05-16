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

package org.ow2.aspirerdfid.tracking.demo.server;

import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.ow2.aspirerdfid.tracking.demo.client.TagEventSerialObject;
import org.ow2.aspirerdfid.tracking.demo.client.TrackingInterfaceDemoService;
import org.ow2.aspirerfid.commons.ons.resolverOns;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.TagEvent;
import org.ow2.aspirerfid.tracking.GetAndReport;
import org.ow2.aspirerfid.tracking.client.TrackingInterfaceClient;
import org.xbill.DNS.NAPTRRecord;
import org.xbill.DNS.Record;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


/**
 * @author Mourtzoukos Konstantinos {email: komo@ait.edu.gr}
 *
 */
public class TrackingInterfaceDemoServiceImpl extends RemoteServiceServlet
		implements TrackingInterfaceDemoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Properties genProps = new Properties();
	private static final Logger LOG = Logger
			.getLogger(TrackingInterfaceDemoServiceImpl.class);
	private static resolverOns resolver = null;

	static final Comparator<TagEventSerialObject> DATE_ORDER = new Comparator<TagEventSerialObject>() {
		public int compare(TagEventSerialObject tag1, TagEventSerialObject tag2) {
			return tag2.getTimeDate().compareTo(tag1.getTimeDate());
		}
	};

	@Override
	public List<TagEventSerialObject> getTagData(String tag) {

		// Query the ONS for a suitable tracking interface for the tag
		loadProperties();

		try {
			resolver = new resolverOns(genProps
					.getProperty("localONS_Resolver"));
			LOG.info("ONS RESOLVER INITIALISED");
		} catch (UnknownHostException e1) {
			LOG.error("Unable to initialize DNS resolver");
			LOG.error("Only local information available");
			LOG.error(e1.toString());
			e1.printStackTrace();
		}

		if (resolver != null) {
			Record[] records = new Record[0];
			List<NAPTRRecord> naptrrec = new ArrayList<NAPTRRecord>();
			try {
				// records =
				// resolver.getAnyResult(epcToDnsRequest(tdoc.getTagIdList().getTag().get(0).getId()),Type.ANY);
				records = resolver.getAnyResultEPC(tag);
				if (records != null) {
					for (int i = 0; i < records.length; i++) {
						if (records[i] instanceof NAPTRRecord) {
							NAPTRRecord nrec = (NAPTRRecord) records[i];
							LOG.info("NAPTR RECORD:" + nrec.getRegexp());
							// From the available answer, extract ws/tracking
							// points.
							if ((nrec.getService().equals("EPC+ws"))
									&& (nrec.getRegexp()
											.endsWith("tracking.wsdl!"))) {
								naptrrec.add(nrec);
							}
						}
					}
					if (naptrrec.isEmpty()) {
						LOG
								.error("No available tracking interface for this class of EPCs");
						LOG.error("TAG:" + tag);
						return null;
					}

					naptrrec = sortNaptrRecordsByPreference(naptrrec);
					boolean RepSuc = true;
					// Try one option after the other until we succeed in one
					for (NAPTRRecord o : naptrrec) {
						String ep = o.getRegexp();
						ep = (ep.split("!"))[2];
						ep = ep.substring(0, ep.length() - 5);
						TrackingInterfaceClient tclient = new TrackingInterfaceClient(
								ep);

						List<TagEvent> rList = tclient.queryTag(tag);
						List<TagEventSerialObject> rFinalList = new ArrayList<TagEventSerialObject>(
								0);
						if (rList.isEmpty()) {
							RepSuc = false;
							continue;
						}

						for (TagEvent tagevent : rList) {
							TagEventSerialObject temp = new TagEventSerialObject(
									tagevent.getTag().getId(), tagevent
											.getTag().getTime()
											.toGregorianCalendar().getTime(),
									tagevent.getTag().getGeoCoords(), tagevent
											.getCompanyInfo().getName(),
									tagevent.getCompanyInfo().getDescription(),
									tagevent.getCompanyInfo().getAddress(),
									tagevent.getCompanyInfo().getCountry(),
									tagevent.getCompanyInfo().getRegion(),
									tagevent.getCompanyInfo().getEmail(),
									tagevent.getCompanyInfo().getTel(),
									tagevent.getCompanyInfo().getFax());
							if (tagevent.getTag().getEpcClassProperty() != null) {
								temp.setEpcClassProperties(tagevent.getTag()
										.getEpcClassProperty()
										.getJSONEncPropList());
							} else {
								temp.setEpcClassProperties("");
							}
							rFinalList.add(temp);
							System.out.println("TagId: "
									+ tagevent.getTag().getId());
							System.out.println("Date and Time: "
									+ tagevent.getTag().getTime().toString());
							System.out.println("GeoTag: "
									+ tagevent.getTag().getGeoCoords());
							System.out.println("------------");
						}
						// Sort the data objects by date
						Collections.sort(rFinalList, DATE_ORDER);
						Collections.reverse(rFinalList);
						return rFinalList;

					}
					if (!RepSuc) {
						LOG.error("All available endpoints failed");
						LOG.error("Epc:" + tag);
						return null;
					}

				} else {
					LOG.error("No DNS records found for this class of EPCs");
					LOG.error("Sample epc:" + tag);
					return null;
				}

			} catch (Exception e) {
				LOG.error("Wrong EPC string");
				LOG.error(e.toString());
				e.printStackTrace();
			}

		} else {
			LOG.error("No available ONS rerver");
		}

		return null;

	}

	private void loadProperties() {
		try {
			InputStream ins = GetAndReport.class
					.getResourceAsStream("/trackingDemo.properties");
			genProps.load(ins);
			System.out.println("LOADED PROPERTIES");
			ins.close();
		} catch (Exception e) {
			System.out.println("ERROR LOADING PROPERTIES");
			e.printStackTrace();
		}
	}

	private ArrayList<NAPTRRecord> sortNaptrRecordsByPreference(
			List<NAPTRRecord> nlist) {
		ArrayList<NAPTRRecord> res = new ArrayList<NAPTRRecord>();
		ArrayList<NAPTRRecord> res0 = new ArrayList<NAPTRRecord>();
		ArrayList<NAPTRRecord> res1 = new ArrayList<NAPTRRecord>();
		ArrayList<NAPTRRecord> res2 = new ArrayList<NAPTRRecord>();
		for (NAPTRRecord naptrRecord : nlist) {
			if (naptrRecord.getPreference() == 0) {
				res0.add(naptrRecord);
			}
			if (naptrRecord.getPreference() == 1) {
				res1.add(naptrRecord);
			}
			if (naptrRecord.getPreference() == 2) {
				res2.add(naptrRecord);
			}
		}
		res.addAll(res0);
		res.addAll(res1);
		res.addAll(res2);
		return res;
	}
}
