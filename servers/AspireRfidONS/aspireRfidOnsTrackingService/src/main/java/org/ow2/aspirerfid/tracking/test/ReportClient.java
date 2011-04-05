package org.ow2.aspirerfid.tracking.test;

import java.math.BigInteger;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.CompanyInfo;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.EpcClassProperty;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.Tag;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.TagIdList;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.Trackerdocument;
import org.ow2.aspirerfid.tracking.client.TrackingInterfaceClient;

/**
 * @author Kostas Mourtzoukos (komo) e-mail: komo@ait.edu.gr
 *
 */

public class ReportClient {

	public static final Logger LOG = Logger.getLogger(ReportClient.class);

	/**
	 * Client invoking the report funtion of the tracking interface
	 * It will report 9 different events for the same tag
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		XMLGregorianCalendar xgc = null;

		// TODO Auto-generated method stub
		TrackingInterfaceClient tclient = new TrackingInterfaceClient("");
		Trackerdocument tdoc = new Trackerdocument();

		// Company Info
		CompanyInfo cinfo = new CompanyInfo();
		cinfo.setAddress("19.7Km Markopoulou Ave");
		cinfo.setCountry("GR");
		cinfo.setEmail("info@example.gr");
		cinfo.setTel("+302106671836");
		cinfo.setDescription("Widget R US");
		cinfo.setFax("+302106672478");
		cinfo.setName("Widget.inc");
		cinfo.setRegion("Markopoulo");

		tdoc.setCompanyInfo(cinfo);

		// Creation Date;
		try {
			DatatypeFactory dtf = DatatypeFactory.newInstance();
			xgc = dtf.newXMLGregorianCalendar(new GregorianCalendar());
			tdoc.setCreationDate(xgc);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		EpcClassProperty eprop = new EpcClassProperty();
		eprop
				.setJSONEncPropList("[{\"Manufacturer\":\"Widget.Inc\"},{\"Color\":\"Green\"}]");

		// URI
		tdoc.setUri("http://www.widgetsRus.gr");
		// TagList
		TagIdList tlist = new TagIdList();

		Tag tagobj = new Tag();
		tagobj.setId("urn:epc:id:gid:1.3.131");
		tagobj.setGeoCoords("38.04:23.67");
		tagobj.setTime(xgc);
		tagobj.setEpcClassProperty(eprop);
		tlist.getTag().add(tagobj);

		tagobj = new Tag();
		tagobj.setId("urn:epc:id:gid:1.3.131");
		tagobj.setGeoCoords("38.50:23.00");
		tagobj.setTime(xgc);
		tagobj.setEpcClassProperty(eprop);
		tlist.getTag().add(tagobj);

		tagobj = new Tag();
		tagobj.setId("urn:epc:id:gid:1.3.131");
		tagobj.setGeoCoords("39.01:20.00");
		tagobj.setTime(xgc);
		tagobj.setEpcClassProperty(eprop);
		tlist.getTag().add(tagobj);

		tagobj = new Tag();
		tagobj.setId("urn:epc:id:gid:1.3.131");
		tagobj.setGeoCoords("39.50:19.30");
		tagobj.setTime(xgc);
		tagobj.setEpcClassProperty(eprop);
		tlist.getTag().add(tagobj);

		tagobj = new Tag();
		tagobj.setId("urn:epc:id:gid:1.3.131");
		tagobj.setGeoCoords("40.30:12.20");
		tagobj.setTime(xgc);
		tagobj.setEpcClassProperty(eprop);
		tlist.getTag().add(tagobj);

		tagobj = new Tag();
		tagobj.setId("urn:epc:id:gid:1.3.131");
		tagobj.setGeoCoords("41.10:9.50");
		tagobj.setTime(xgc);
		tagobj.setEpcClassProperty(eprop);
		tlist.getTag().add(tagobj);

		tagobj = new Tag();
		tagobj.setId("urn:epc:id:gid:1.3.131");
		tagobj.setGeoCoords("43.70:7.30");
		tagobj.setTime(xgc);
		tagobj.setEpcClassProperty(eprop);
		tlist.getTag().add(tagobj);

		tagobj = new Tag();
		tagobj.setId("urn:epc:id:gid:1.3.131");
		tagobj.setGeoCoords("46.20:3.18");
		tagobj.setTime(xgc);
		tagobj.setEpcClassProperty(eprop);
		tlist.getTag().add(tagobj);

		tagobj = new Tag();
		tagobj.setId("urn:epc:id:gid:1.3.131");
		tagobj.setGeoCoords("48.74:1.00");
		tagobj.setTime(xgc);
		tagobj.setEpcClassProperty(eprop);
		tlist.getTag().add(tagobj);

		tlist.setIdCount(new BigInteger("9"));
		tdoc.setTagIdList(tlist);

		tclient.reportTagList(tdoc);

	}

}
