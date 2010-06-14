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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import org.ow2.aspirerfid.ale.codec.EpcCodec;
import org.ow2.aspirerfid.ale.codec.Sgln96Codec;
import org.ow2.aspirerfid.ale.codec.Sgtin96Codec;
import org.ow2.aspirerfid.ale.codec.gs1.Gs1Converter;
import org.ow2.aspirerfid.ale.engine.collect.Grouping;
import org.ow2.aspirerfid.ale.engine.collect.grouping.Sgtin96;
import org.ow2.aspirerfid.ale.engine.collect.grouping.UriRepresentation;
import org.ow2.aspirerfid.ale.engine.com.IhmCom;
import org.ow2.aspirerfid.ale.engine.input.RPChannel;
import org.ow2.aspirerfid.ale.engine.input.Tag;
import org.ow2.aspirerfid.ale.engine.input.connectors.IntermecIP30Channel;
import org.ow2.aspirerfid.ale.engine.input.connectors.PsionCaenChannel;
import org.ow2.aspirerfid.ale.engine.input.connectors.RPConnector;
import org.ow2.aspirerfid.ale.engine.input.connectors.RPSimChannel;
import org.ow2.aspirerfid.ale.engine.lr.IcomLRFactory;
import org.ow2.aspirerfid.ale.engine.lr.IcomLRPropSetter;
import org.ow2.aspirerfid.ale.epc.lr.ALELR;
import org.ow2.aspirerfid.ale.epc.lr.LRSpec;
import org.ow2.aspirerfid.ale.epc.reports.DecathStat;
import org.ow2.aspirerfid.ale.epc.reports.ECReport;
import org.ow2.aspirerfid.ale.epc.reports.ECReportGroup;
import org.ow2.aspirerfid.ale.epc.reports.ECReportGroupListMember;
import org.ow2.aspirerfid.ale.epc.reports.ECReports;
import org.ow2.aspirerfid.ale.epc.spec.ALE;
import org.ow2.aspirerfid.ale.epc.spec.ECBoundarySpec;
import org.ow2.aspirerfid.ale.epc.spec.ECFieldSpec;
import org.ow2.aspirerfid.ale.epc.spec.ECFilterListMember;
import org.ow2.aspirerfid.ale.epc.spec.ECFilterSpec;
import org.ow2.aspirerfid.ale.epc.spec.ECGroupSpec;
import org.ow2.aspirerfid.ale.epc.spec.ECReportOutputSpec;
import org.ow2.aspirerfid.ale.epc.spec.ECReportSetSpec;
import org.ow2.aspirerfid.ale.epc.spec.ECReportSpec;
import org.ow2.aspirerfid.ale.epc.spec.ECSpec;
import org.ow2.aspirerfid.ale.epc.spec.ECStatProfileName;
import org.ow2.aspirerfid.ale.epc.spec.ECTrigger;
import org.ow2.aspirerfid.ale.epc.spec.TriggerType;

import pops.rp.imp.factory.ReaderFactory;

import com.caen.RFIDLibrary.CAENRFIDLogicalSource;
import com.caen.RFIDLibrary.CAENRFIDPort;
import com.caen.RFIDLibrary.CAENRFIDProtocol;
import com.caen.RFIDLibrary.CAENRFIDReader;
import com.caen.RFIDLibrary.CAENRFIDTag;


public class TestCase {	
		
	/*
	 * Test case : using an RP simulator.
	 */
	public static void main(String[] args) throws Exception{
		
		/* Test configuration item */
		Config testConfig = new Config();

		// parse from file
		//readConfig("\\b\\m\\agg\\"+"patterns.txt", testConfig);
		Config.readConfig(args[0]+"testConfig.txt", testConfig);
		testConfig.workingDirectory = args[0];
		System.setProperty("user.dir", args[0]); // set system property
		System.setProperty("icom.ale.beepOnDetection", testConfig.beepOnDetection?"true":"false"); // set system property
		System.setProperty("icom.ale.beepOnNewDetection", testConfig.beepOnNewDetection?"true":"false"); // set system property
		System.setProperty("icom.ale.maxTags", Integer.toString((testConfig.maxTags))); // set system property
		System.setProperty("icom.ale.rpLink.defaultTriggerLauncher", testConfig.DefaultTriggerLauncher?"true":"false"); // set system property
		
		switch (testConfig.testNumber) {	
			/* Test 0 : validate CAEN reader  */
			case 0:
				TestCase_0();
				break;
				
			/* Test 1 : Grouping -> build a report  */
			case 1:
				TestCase_1(testConfig);
				break;		
				
			/* Test 2 : ALE -> build the same report using immediate mode */
			case 2:
				TestCase_2(testConfig);
				break;
				
			/* Test 3 : decode all observed tags  */
			case 3:
				TestCase_3(testConfig);
				break;
				
			/* Test 4 : bench the duration of the beep call */
			case 4:
				TestCase_4();
				break;
				
			/* Test 5 : Geiger */
			case 5:
				TestCase_5(testConfig);
				break;
			
			default :
				System.out.println("Invalid test number");			
		}
		
	}

	private static void TestCase_4() {
	    int TIMES = 20;
	    
        long[] times = new long [TIMES];
                
        for (int i = 0; i < TIMES; i++) {
            times[i] = System.currentTimeMillis();
            Toolkit.getDefaultToolkit().beep();
            times[i] = System.currentTimeMillis() - times[i];
        }
        long mean = 0 ;
        for (int i = 0; i < TIMES; i++) {
          System.out.println("times [" + i + "] = " + times[i]);
          mean += times[i];
        }
        mean = (mean/TIMES);
        System.out.println("mean = " + mean);
	}
	
	private static void TestCase_3(Config testConfig) throws Exception{
		
		RPChannel tagChannel; 	/* RP channel */
		HashSet observedTags = new HashSet();
		Sgln96Codec aSgln96Codec = new Sgln96Codec();
		Sgtin96Codec aSgtin96Codec = new Sgtin96Codec();
		
		System.out.println("Config :" + testConfig.simu + ", '" + testConfig.pda + "'");
		
		
		/* Connect RP */
		tagChannel = getRPChannel(testConfig);
		
		/* Init */
		//tagChannel.init(null, null);
		tagChannel.initReaderDevice();
		tagChannel.initAcquisition(null /* no sgln start tag */, null /* no filtering*/);
		System.out.println("init OK");
		tagChannel.start();
		System.out.println("start OK");
		
		System.out.println("Current tags decoding :");
		System.out.println("______________________");
		
		try {
			/* decode all you see */
			do {
				// event cycle
				do {
					/* Wait for Acquisition report : blocking call. */
					Tag currentTag = tagChannel.nextTag();
					if (currentTag == null) 
						break;
					byte[] currentTagId = currentTag.getId();
					byte header ;
					String hexUri ;
					try {
						header = EpcCodec.decodeHeader(currentTagId);
						switch(header) {
							// sgtin 96
							case EpcCodec.SGTIN_96_HEADER :
								aSgtin96Codec.decodeTagData(currentTagId);
								hexUri = aSgtin96Codec.getRawHexUri();
								// dump if first observation
								if (!observedTags.contains(hexUri)) {
									Toolkit.getDefaultToolkit().beep();
									observedTags.add(hexUri);
									System.out.println(hexUri);
									System.out.println(aSgtin96Codec.getTagUri());									
									System.out.println("GTIN :" + Gs1Converter.toString(Gs1Converter.GTIN_LENGTH, Gs1Converter.computeGS1(header, aSgtin96Codec.getCompanyPrefix(), aSgtin96Codec.getItemReference(), aSgtin96Codec.getPartition())));
									System.out.print("\n");							
								}
								break;
								
							// sgln 96
							case EpcCodec.SGLN_96_HEADER :
								aSgln96Codec.decodeTagData(currentTagId);
								hexUri = aSgln96Codec.getRawHexUri();
								// dump if first observation
								if (!observedTags.contains(hexUri)) {
									Toolkit.getDefaultToolkit().beep();
									observedTags.add(hexUri);
									System.out.println(hexUri);
									System.out.println(aSgln96Codec.getTagUri());
									System.out.println("GLN :" + Gs1Converter.toString(Gs1Converter.GLN_LENGTH, Gs1Converter.computeGS1(header, aSgln96Codec.getCompanyPrefix(), aSgln96Codec.getLocationReference(), aSgln96Codec.getPartition())));
									System.out.print("\n");	
								}						
								break;
							
							// unsupported, just display data in hex
							default :
								hexUri = byteArrayTohex(currentTagId, "Unsupported Header :\n urn:epc:raw:96.x", true);
								if (!observedTags.contains(hexUri)) {
									Toolkit.getDefaultToolkit().beep();
									observedTags.add(hexUri);
									System.out.print(hexUri + "\n\n");
								}
						}
					} catch (Exception e) {
						hexUri = byteArrayTohex(currentTagId, "Error Encoding Tag:\n urn:epc:raw:96.x", true);
						if (!observedTags.contains(hexUri)) {
							Toolkit.getDefaultToolkit().beep();
							observedTags.add(hexUri);
							System.out.print(hexUri + "\n\n");
						}
					}
				} while (tagChannel.hasTags());
				// reset
				//tagChannel.init(null, null);	
				
			}while(!tagChannel.isDone());
			
		} finally {
			tagChannel.clean();
		}
		System.out.println("Done. Total observed tags : " + observedTags.size());
	}

	private static void TestCase_2(Config testConfig) throws Exception {
		ALE theAle;			  	/* ALE subset  : READ API view */
		ALELR theAleLr;			  	/* ALE subset : LR API view */
		RPChannel tagChannel; 	/* RP channel */
		IhmCom ihmLink ;      	/* IHM connector */
		//EventFifo anEventFifo;  /* Ihm aggregation fifo */
		ECSpec spec;			/* Spec object*/
		String[] patternList ;// = {"urn:epc:pat:sgtin-96:*.*.[60000-70000].*", "urn:epc:pat:sgtin-96:*.*.[100000-200000].*", "urn:epc:pat:sgtin-96:*.0290000.*.*"};
		ECReports reports;
		
		
		/* Read Patterns from Configuration	*/	
		patternList = testConfig.grpPatternList;
		
		/* Connect RP */
		tagChannel = getRPChannel(testConfig);
		
		/* Connect IHM */
		// com fifo
		//anEventFifo = new EventFifo();
		
		// no instance
		ihmLink = null;		
		//ihmLink = new IhmCom(anEventFifo , 20000 /* max tags*/);

		theAle = new IcomAle(tagChannel, ihmLink );
		theAleLr = (ALELR)theAle;
		
		/* build spec */
		spec = buildSpec(patternList, testConfig.sglnTrigger);
		
		/* Set RF Power to Max*/
		String readerName = tagChannel.getReaderName();
		LRSpec lrSpec = theAleLr.getLRSpec(readerName);	
		System.out.println("Power = " + theAleLr.getPropertyValue(readerName,IcomLRFactory.DECATHLON_PROP_NAMES[1] /*power*/));
		lrSpec.updatePropertyByName(IcomLRFactory.DECATHLON_PROP_NAMES[1] /*power*/, Integer.toString(IcomLRPropSetter.POWER_MAX_VALUE));
		theAleLr.update(tagChannel.getReaderName(), lrSpec);
		System.out.println("Power = " + theAleLr.getPropertyValue(readerName,IcomLRFactory.DECATHLON_PROP_NAMES[1] /*power*/));	
		
		/* Immediate mode */
		reports = theAle.immediate(spec);
		
		if (reports != null) {
			/* Offline Operations done here for validation purpose */
			// complete report : by decoding string representation of tags
			decodeReports(reports, spec.getReportSpecs()[0].getOutput());
			// dump Reports
			if (testConfig.printReport)
				dumpReports(reports);
			// log Reports
			logReports(reports, testConfig.workingDirectory + "report.txt");
		} else {
			System.out.println("No report.");
		}
		System.out.println("Done");
	}

	private static void TestCase_1(Config testConfig) throws Exception {
		/* Spec object*/
		ECSpec spec;
		String[] patternList = {"urn:epc:pat:sgtin-96:*.*.[60000-70000].*", "urn:epc:pat:sgtin-96:*.*.[100000-200000].*", "urn:epc:pat:sgtin-96:*.0290000.*.*"};
		Sgtin96 sgtin96Wrapper = new Sgtin96();
		/* Corresponding Output Report Object */
		ECReport report;
				
		/* grouping engine instance */
		Grouping grEngine = new Grouping();
		grEngine.addGroupPattern(patternList, UriRepresentation.EPC_PAT_TAG);
		
		/* RP simulator */
		RPChannel tagChannel;
		
		/* Connect RP */
		tagChannel = getRPChannel(testConfig);
		
		Tag rxTag;
				
		try {
			/* build specs */
			spec = buildSpec(patternList, testConfig.sglnTrigger);
 			
			/* init RP channel */
//			tagChannel.init("no Reader", null); // reader
//			tagChannel.init("no Reader", spec); // spec
			tagChannel.initReaderDevice(); 		// reader
			tagChannel.initAcquisition(spec.getBoundarySpec().getStartTriggerList()[0] /* start trigger */, 
									   spec.getReportSpecs()[0].getFilterSpec() /* filtering spec*/); 
			
			tagChannel.start();
			/* Perform 5 event cycles */
			for (int i = 0 ; i< 5 ; i++) {
				//an event cycle : read, aggregate
				do {
					// read tag : blocking
					rxTag = tagChannel.nextTag();
					// aggregate if sgtin-96
					byte[] id = rxTag.getId();
					byte header = EpcCodec.decodeHeader(id); 
					if (header == EpcCodec.SGTIN_96_HEADER) {
						sgtin96Wrapper.setTagData(id);
						//grEngine.aggregate(id, rxTag.getTimestamp(), null);
						grEngine.aggregate(header, id, rxTag.getTimestamp(), sgtin96Wrapper);
					}
				}while(tagChannel.hasTags());
				// restart 
				tagChannel.start();
				// simulate delay
				Thread.sleep(500);
			}
			
			/* build report*/
			report = grEngine.buildReporGroups(spec);
			
		}
		finally {
			/* clean up */
			tagChannel.clean();
		}
		
		/* Dump Report */
		dumpReport(report, " ");
	}
	
	private static RPChannel getRPChannel(Config testConfig) {
		RPChannel channel = null;
		
		if (testConfig.simu) {
			channel = new RPSimChannel(testConfig.bench);
		}
		else {
			if (testConfig.wrapRP) {
				// RP wrappers
				if (testConfig.pda.equals("Caen")) {
					channel = new RPConnector(ReaderFactory.getReader(ReaderFactory.CAEN_READER, IcomLRFactory.READER_NAMES[0]), IcomLRFactory.READER_NAMES[0]);
				}
				else if (testConfig.pda.equals("Intermec")) {
					channel = new RPConnector(ReaderFactory.getReader(ReaderFactory.BRI_READER, IcomLRFactory.READER_NAMES[1]), IcomLRFactory.READER_NAMES[1]);
				}
				else
					throw new IllegalArgumentException("invalid pda field : Psion or Intermec");	
		
			} else {
				// HAL directly
				if (testConfig.pda.equals("Caen")) {
					channel = new PsionCaenChannel(testConfig.bench);
				}
				else if (testConfig.pda.equals("Intermec")) {
					channel = new IntermecIP30Channel();
				}
				else
					throw new IllegalArgumentException("invalid pda field : Psion or Intermec");	
			}

		}
		
		System.out.println("Reader Abstraction class : " + channel.getClass());
		return channel;
	}
	
	private static ECSpec buildSpec(String[] patternList, String sglnStartTriggerUri) throws Exception {
		StringBuffer sBuffer = new StringBuffer();
		
		/* Spec object*/
		ECSpec spec;
		ECReportSpec[] reportSpecs ;
		ECReportSetSpec reportSet;
		ECGroupSpec groupSpec;
		ECReportOutputSpec output;
		ECStatProfileName[] statProfileNames;	
		
		reportSpecs = new ECReportSpec[1];
		reportSet = ECReportSetSpec.CURRENT;
		// grouping spec :  a list of patterns in epc-tag format
		groupSpec = new ECGroupSpec();
		groupSpec.setPatternList(patternList);
		groupSpec.setFieldspec(new ECFieldSpec("epc", "epc", "epc-pure"));
		// output spec
		output = new ECReportOutputSpec(true, true, true, false /* no raw-dec*/,true /* include count*/, null /* no fields only ID*/);			
		// Stat spec
		statProfileNames = new ECStatProfileName [1];
		statProfileNames[0] = ECStatProfileName.DecathlonStats;
		
		// build boundary spec
		ECTrigger[] startTriggerList = new ECTrigger[1];
		ECTrigger[] stopTriggerList= new ECTrigger[1];
		startTriggerList[0] = new ECTrigger(TriggerType.buildTriggerURI(sBuffer, TriggerType.TAG_TRIGGER, sglnStartTriggerUri));
		stopTriggerList[0] = new ECTrigger(TriggerType.buildTriggerURI(sBuffer, TriggerType.END_TRIGGER, null));
		ECBoundarySpec boudnarySpec = new ECBoundarySpec(startTriggerList, null, stopTriggerList, null, null, false);
	
		// build filtering spec.
		// TODO get it from config.
		// Filtering patters.
//		String[] incPatterns_1 = {"urn:epc:pat:sgln-96:*.*.*.*", "urn:epc:pat:sgtin-96:1.0652642.[80000-900000].*","urn:epc:pat:sgtin-96:1.0652642.[800-90000].*"};
//		String[] incPatterns_2 = {"urn:epc:pat:sgln-96:*.*.*.*", "urn:epc:idpat:sgtin:0652642.[800-900000].*","urn:epc:idpat:sgtin:0652642.[800-9000].*"};
//		String[] excPatterns_1 = {"urn:epc:idpat:sgtin:*.*.100"};
//		String[] excPatterns_2 = {"urn:epc:pat:sgtin-96:1.0652643.[80000-90000].*"};
		String[] incPatterns_1 = {"urn:epc:pat:sgln-96:*.*.*.*", "urn:epc:pat:sgtin-96:*.*.*.*"};
		String[] incPatterns_2 = {"urn:epc:pat:sgln-96:1.211298.*.*","urn:epc:pat:sgtin-96:1.211298.*.*"};
		String[] excPatterns_1 = {"urn:epc:idpat:sgtin:*.*.10"};
		String[] excPatterns_2 = {"urn:epc:pat:sgtin-96:1.0652643.*.[50-100]"};
		ECFilterListMember[] members = new ECFilterListMember[4];
		ECFilterSpec filterSpec = new ECFilterSpec(members);
		//members
		members[0] = new ECFilterListMember(true /* include */, new ECFieldSpec("epc", "epc", "epc-tag"), incPatterns_1);
		members[1] = new ECFilterListMember(true /* include */, new ECFieldSpec("epc", "epc", "epc-pure"), incPatterns_2);
		members[2] = new ECFilterListMember(false /* exclude */, new ECFieldSpec("epc", "epc", "epc-pure"), excPatterns_1);
		members[3] = new ECFilterListMember(false /* exclude */, new ECFieldSpec("epc", "epc", "epc-tag"), excPatterns_2);
		
		
		// build input spec
		reportSpecs[0] = new ECReportSpec("Decathlon Inventory Demo", reportSet,
											null /*filterSpec*/, groupSpec,
											output, false,
											false, statProfileNames) ;
			
		spec = new ECSpec(null, boudnarySpec, reportSpecs, true);
		return spec;
	}
	
	/*Log Utilities */
	
	private static void logReports(ECReports reports, String reportFileName) throws Exception {
		BufferedWriter dous = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(reportFileName)));
		long durationTime = System.currentTimeMillis();

		System.out.println("Building Report...");
		
		try {
			buildXmlReports(dous, reports, ECReports.ALEID);
			dous.newLine();
		} finally{
			dous.close();
		}
		durationTime = System.currentTimeMillis()-durationTime;
		System.out.println("Report build time = " + durationTime + " ms");
	}
	
	/* Dump utilites */
	private static void dumpReport(ECReport report, String ident) throws Exception {
		StringBuffer sbuffer = new StringBuffer();
		
		buildXmlReport(sbuffer, new OutputStreamWriter(System.out), report, ident);
	}
	
	private static void dumpReports(ECReports reports) throws Exception {
		buildXmlReports(new OutputStreamWriter(System.out), reports, ECReports.ALEID);
		System.out.print("\n");
	}
	
	/* Offline decoding reports utility */
	private static void decodeReports(ECReports reports, ECReportOutputSpec reportOutpuSpec) {
		ECReport[] reportsItems = reports.getReports();
		// for each report
		for (int i = 0 ; i < reportsItems.length; i++) {
			ECReportGroup[] groups = reportsItems[i].getGroups();
			// for each group of the report
			for (int j = 0 ; j < groups.length; j++) {
				ECReportGroupListMember [] grouplistMembers = groups[j].getGroupList().getMembers();
				// for each member of the group : decode				
				for (int k = 0; k < grouplistMembers.length; k++)
					decodeGroupMember(grouplistMembers[k], reportOutpuSpec);
			}
		}		
	}
	
	/* XML rendering Utilities */	
	private static void buildXmlReports(Writer aWriter, ECReports reports, String ALEID) throws Exception {
		ECReport[] theReports;
		StringBuffer sbuffer = new StringBuffer();
				
		sbuffer.append("<ale:ECReports \n");		
		sbuffer.append("	creationDate=").append(ECReports.ISO_8601_SDF.format(new Date())).append("\n");
		sbuffer.append("	specName=").append(reports.getSpecName()).append("\n");
		sbuffer.append("	date=").append(reports.getDate()).append("\n");
		sbuffer.append("	ALEID=").append(ALEID).append("\n");
		sbuffer.append("	totalMilliseconds=").append(reports.getTotalMilliseconds()).append("\n");
		sbuffer.append("	initiationCondition=").append(reports.getInitiationCondition()).append("\n");
		sbuffer.append("	initiationTrigger=").append(reports.getInitiationTrigger().getTriggerURI()).append("\n");
		sbuffer.append("	terminationCondition=").append(reports.getTerminationCondition()).append("\n");
		sbuffer.append("	terminationTrigger=").append(reports.getTerminationTrigger().getTriggerURI()).append("\n");
		
		aWriter.write(sbuffer.toString());
		sbuffer.setLength(0);
		
		/* append the reports */
		sbuffer.append("\t<reports>\n");
		theReports = reports.getReports();
		for (int i = 0 ; i <theReports.length; i++) {
			buildXmlReport(sbuffer, aWriter, theReports[i], "\t\t");
		}
		sbuffer.append("\t</reports>\n");
		
		/* close */
		sbuffer.append("</ale:ECReports>\n");
		
		aWriter.write(sbuffer.toString());
		sbuffer.setLength(0);		
	}
	
	private static void buildXmlReport(StringBuffer sbuffer, Writer aWriter, ECReport report, String ident) throws Exception {
		ECReportGroup[] reportGroups = report.getGroups();
		ECReportGroup reportGroup;
		ECReportGroupListMember[] list; // list of member
		ECReportGroupListMember member; // member of a group
		DecathStat memberStats;
				
		sbuffer.append(ident).append("<Report name='").append(report.getReportName()).append("'>\n");
		for (int i = 0 ; i< reportGroups.length; i++, sbuffer.append("\n")) {
			reportGroup = reportGroups[i];
			list = reportGroup.getGroupList().getMembers();
			sbuffer.append(ident).append("\t<Group name='").append(reportGroup.getGroupName()).append("'>\n");
			sbuffer.append(ident).append("\t\t<Count ").append(reportGroup.getGroupCount().getCount()).append("/Count>\n");
			// dump Members
			for (int j = 0 ; j < list.length; j++) {
				String val;
				
				member = list[j];
				memberStats = (DecathStat) member.getStats();
				sbuffer.append(ident).append("\t\t<Member>\n"); // begin member
				// TAG Id
				// epc-pure
				val = member.getEpc();
				if (val != null) sbuffer.append(ident).append("\t\t\t<tag> ").append(val).append( "</tag>\n");
				// epc-tag
				val = member.getTag();
				if (val != null) sbuffer.append(ident).append("\t\t\t<tag> ").append(val).append("</tag>\n");				
				// epc-rawDec
				val = member.getRawDecimal();
				if (val != null) sbuffer.append(ident).append("\t\t\t<tag> ").append(val).append("</tag>\n");				
				// epc-rawHex
				val = member.getRawHex();
				if (val != null) sbuffer.append(ident).append("\t\t\t<tag> ").append(val).append("</tag>\n");
				// STAT
				if (memberStats != null) {
					sbuffer.append(ident).append("\t\t\t<Stat>\n"); // begin stat
					sbuffer.append(ident).append("\t\t\t\t<first sight> ").append(getDate(memberStats.getFirstSightingTime())).append(" </first sight>\n");
					sbuffer.append(ident).append("\t\t\t\t<last sight> ").append(getDate(memberStats.getLastSightingTime())).append(" </last sight>\n");
					//sbuffer.append(ident).append("\t\t\t\t<first sight> ").append(memberStats.getFirstSightingTime()).append(" </first sight>\n");
					//sbuffer.append(ident).append("\t\t\t\t<last sight> ").append(memberStats.getLastSightingTime()).append(" </last sight>\n");
					sbuffer.append(ident).append("\t\t\t\t<count> ").append(memberStats.getNumReadings()).append(" </count>\n");
					sbuffer.append(ident).append("\t\t\t</Stat>\n"); // end stat
				}				
				sbuffer.append(ident).append("\t\t</Member>\n"); // end member
				aWriter.write(sbuffer.toString());
				sbuffer.setLength(0);
			}
			sbuffer.append(ident).append("\t</Group>");
			// log and write
			aWriter.write(sbuffer.toString());
			sbuffer.setLength(0);
		}
	}
	/*
	 * Decoding utility.
	 */
	private final static Sgtin96Codec sgtin96Codec = new Sgtin96Codec(); 
	private final static Sgln96Codec sgln96Codec = new Sgln96Codec();
	private static void decodeGroupMember(ECReportGroupListMember groupListMember, ECReportOutputSpec reportOutputspec) {
		byte[] tagId = groupListMember.getTagId();
		
		try {
			switch (EpcCodec.decodeHeader(tagId)) {
				/* Sgtin-96*/
				case EpcCodec.SGTIN_96_HEADER:
					// wrap and decode
					sgtin96Codec.decodeTagData(tagId);
					// set member's tag representation : String
					if (reportOutputspec.isIncludeEPC())
						groupListMember.setEpc(sgtin96Codec.getPureUri());
					if (reportOutputspec.isIncludeTag())
						groupListMember.setTag(sgtin96Codec.getTagUri());
					if (reportOutputspec.isIncludeRawDecimal())
						groupListMember.setRawDecimal(sgtin96Codec.getRawDecUri());
					if (reportOutputspec.isIncludeRawHex())
						groupListMember.setRawHex(sgtin96Codec.getRawHexUri());
					break;
				
				/* Sgln-96*/
				case EpcCodec.SGLN_96_HEADER: 
					// wrap and decode
					sgln96Codec.decodeTagData(tagId);
					// set member's tag representation : String
					if (reportOutputspec.isIncludeEPC())
						groupListMember.setEpc(sgln96Codec.getPureUri());
					if (reportOutputspec.isIncludeTag())
						groupListMember.setTag(sgln96Codec.getTagUri());
					if (reportOutputspec.isIncludeRawDecimal())
						groupListMember.setRawDecimal(sgln96Codec.getRawDecUri());
					if (reportOutputspec.isIncludeRawHex())
						groupListMember.setRawHex(sgln96Codec.getRawHexUri());
					break;
									
				default : 
					// Error decoding Header
					// give only hex format
					String rawHex = byteArrayTohex(tagId, "urn:epc:raw:96.x", true);
					groupListMember.setRawHex(rawHex);			
			}
		}
		catch (Exception e) {
			// Error decoding id
			// give only hex format
			String rawHex = byteArrayTohex(tagId, "urn:epc:raw:96.x", true);
			groupListMember.setRawHex(rawHex);	
		}
	}
	
	/* date conversion Utility*/
	private final static Date theDate = new Date();
	private final static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	private static String getDate(String timestamp) {
		theDate.setTime(Long.parseLong(timestamp));
		//return ECReports.ISO_8601_SDF.format(theDate);
		return formatter.format(theDate);
	}
	
	/*
	 * Byte of arrays to string in hex format conversion utility.
	 */
	private static String byteArrayTohex(byte[] id, String prefix, boolean swap) {
	  	StringBuffer hex = new StringBuffer();
	  	
	  	// prefix
	  	if (prefix != null)
	  		hex.append(prefix);
	  	
	  	// bytes
	  	if (!swap)
		  	for (int i = 0 ; i<id.length ; i++)
		  		hex.append(byteTohex(id[i]));
	  	else
		  	for (int i = id.length-1 ; i>=0; i--)
		  		hex.append(byteTohex(id[i]));
	  	
	  	return hex.toString();
	}
	
	/*
	 * Byte to hex conversion utility.
	 */
	private static String byteTohex(byte n) {
	  	String hex;
	  	hex = Integer.toHexString(n & 0xFF);
	  	
	  	if (hex.length()<2) 
	  		hex = '0' + hex;
	  	
	  	return hex.toUpperCase();
	}
	
//	private static void testAsync() throws Exception {
//	    CAENRFIDReader myReader = new CAENRFIDReader();
//	    CAENRFIDLogicalSource[] myReaderSources;
//	    CAENRFIDLogicalSource mySource_0 ;
//	    CAENRFIDTag[] myRFIDTags;
//	    CAENRFIDReceiver arcv;
//	    	    
//	    try {
//	        // Load com library
//			System.loadLibrary("javaxcomm");
//			// power up reader
//	        WCECommDriver.powerUpCaenReader();
//	        
//	        Thread.sleep(300);
//	        
//	        /* Connect to reader on COM1 */
//	        System.out.println("Connecting to reader : " + "RS232 : COM1");
//	        myReader.Connect(CAENRFIDPort.CAENRFID_RS232, "COM1:");
//	        System.out.println("Connection Success");
//	        
//	        /* configure Reader */
//	        // set protocol  : default is CAENRFID_EPC_C1G2
//	        myReader.SetProtocol(CAENRFIDProtocol.CAENRFID_EPC_C1G2);
//	        // set bitRate
//	        //System.out.println("Setting BitRate");
//	        //myReader.SetBitRate(CAENRFIDBitRate.TX10RX40);
//	        //myReader.SetBitRate(CAENRFIDBitRate.TX40RX40);
//	        //myReader.SetBitRate(CAENRFIDBitRate.TX40RX160);
//	        //System.out.println("SetBitRate OK");
//	        // set RF regulation
//	        //myReader.SetRFRegulation(CAENRFIDRFRegulations.ETSI_302208);
//	        
//	        
//	        // get and setup Logical Source 0
//	        myReaderSources = myReader.GetSources();
//	        mySource_0 = myReaderSources[0];
//	        // set Q value
//	        //mySource_0.SetQ_EPC_C1G2(3);
//	        // set session 
//	        //mySource_0.SetSession_EPC_C1G2(CAENRFIDLogicalSourceConstants.EPC_C1G2_SESSION_S0);
//	        // set Power
//	        //myReader.SetPower(500);  // 500 mW
//	        //System.out.println("SetPower OK");
//	        
//	        // Tweak : Reset connection, otherwise won't work !!!
//	        myReader.Disconnect();
//	        myReader.Connect(CAENRFIDPort.CAENRFID_RS232, "COM1:");
//	        
//	        // set Power
//	        System.out.println("SetPower " + 500);
//	        myReader.SetPower(500);  // 500 mW
//	        System.out.println("SetPower OK " + myReader.GetPower() );
//	        
//		    // create a receiver
//		    arcv = new CAENRFIDReceiver(4000);
//		    arcv.addCAENRFIDEventListener(new CAENRFIDEventListener() {
//				
//				public void CAENRFIDTagNofity(CAENRFIDEvent evt) {
//					System.out.println("Rx tag :");
//					System.out.println(evt.getData());				
//				}
//			});
//		    
//	        /* wait for end */
//		    System.out.println("Press any key to end");
//	        System.in.read();
//	    	// remove channel
//	        arcv.KillServer();
//	    }
//	    finally {
//	    	// Power Down CAEN Reader device on PSION
//	        WCECommDriver.powerDownCaenReader(); 
//	    }
//	    System.out.println("Done");
//	}
	
	private static void TestCase_0() throws Exception {
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
	        //myReader.SetPower(500);  // 500 mW
	        //System.out.println("SetPower OK");
	        
	        // Tweak : Reset connection, otherwise won't work !!!
	        myReader.Disconnect();
	        myReader.Connect(CAENRFIDPort.CAENRFID_RS232, "COM1:");
	        
	        // set Power
	        System.out.println("SetPower " + 500);
	        myReader.SetPower(500);  // 500 mW
	        System.out.println("SetPower OK " + myReader.GetPower() );
	        	        
	        /* Inventory */
	        System.out.println("Inventory on : " + mySource_0.GetName());
         	for (int k = 0; k < 5 ; k++) {
         		// Poll source for tags
		    	 timeout = 0;
				 do {
					 myRFIDTags = mySource_0.InventoryTag();
					 System.out.println("Check");
					 Thread.sleep(500);                    // wait 0.5s
					 timeout++;
				 } while ((myRFIDTags == null) && (timeout < 10));
				  
				 if (myRFIDTags == null) {
					 System.out.println(" No Observed Tags !");
					 continue;
				 }
			          
				 /* Dump received Tags */
				 System.out.println(" Observed Tags :");
				 for (int i = 0 ; i < myRFIDTags.length; i++) {
					 byte[] id = myRFIDTags[i].GetId();
					 System.out.print("tag @source : " + myRFIDTags[i].GetSource().GetName() + "\n[" );
					 for (int j = 0 ; j < id.length; j++)
						 System.out.print(Integer.toHexString(0x00FF&(int)id[j]));
					 System.out.println("]");
					 System.out.println("\t -> length = " + myRFIDTags[i].GetLength());
					 System.out.println("\t -> type = " + protocolToString(myRFIDTags[i].GetType()));
			  
					 byte[] epcBank = mySource_0.ReadTagData_EPC_C1G2(myRFIDTags[i],(short)1,(short)0,(short)16) ;
					 System.out.print("\t -> EPC Bank [CRC - EPC Tag Encoding] =\n[ ");
					 for (int j = 0 ; j < epcBank.length; j++)
						 System.out.print(Integer.toHexString(0x00FF&(int)epcBank[j]));
					 System.out.println("]"); 
				 }
	        }
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
	   
	private static void TestCase_5(Config testConfig) throws Exception {
		if (testConfig.pda.equals("Caen"))
			Geiger.CAEN_TestCase();
		else if (testConfig.pda.equals("Intermec"))
			Geiger.IntermecTestCase();
		else
			throw new IllegalArgumentException("Invalid testConfig.pda = " + testConfig.pda);
	}
  
	private static String protocolToString(CAENRFIDProtocol _protocol) {
	    String protocol;
	    
	    if (_protocol == CAENRFIDProtocol.CAENRFID_EPC_C1G1)
	      protocol = "EPC_C1G1";
	    else if (_protocol == CAENRFIDProtocol.CAENRFID_EPC_C1G2)
	      protocol = "EPC_C1G2";  
	    else if (_protocol == CAENRFIDProtocol.CAENRFID_ISO18000_6b)
	      protocol = "ISO18000_6b";
	      else if (_protocol == CAENRFIDProtocol.CAENRFID_EPC119)
	      protocol = "EPC119";  
	    else if (_protocol == CAENRFIDProtocol.CAENRFID_MULTYPROTOCOL)
	      protocol = "MULTYPROTOCOL";
	    else if (_protocol == CAENRFIDProtocol.CAENRFID_ISO18000_6a)
	      protocol = "ISO18000_6a";
	    else
	      protocol = "Unknown";
	      
	    return protocol;
	}
	 
}

/**
 * TestCase configuration item.
 * @author rdagher
 *
 */
class Config{
	// test configuration section
	String workingDirectory;
	String pda;
	int testNumber = 1;
	boolean wrapRP = false;
	boolean bench = false;
	boolean simu = false;
	boolean printReport = false;
	boolean beepOnDetection = false;
	boolean beepOnNewDetection = false;
	int maxTags = 2000;
	boolean DefaultTriggerLauncher = true;
	// ECspec section
	String sglnTrigger; 	  // of the row
	String[] grpPatternList;  // Grouping patterns List
	
	public static void readConfig (String inputFile, Config config) throws IOException {
		HashSet grpPatterns  = new HashSet(50);  // A set of grouping patterns
		BufferedReader dis;
		String aLine ;
		
		dis = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
		
		while ((aLine = dis.readLine()) != null) {	
			// ignore empty lines
			if (aLine.length() == 0) continue;

			// Remove leading and trailing white spaces
			aLine = aLine.trim();
			
			// ignore comments
			if (aLine.startsWith("#")) continue;
			
			// Parse it up
			if (aLine.startsWith("pda"))
				config.pda = aLine.substring(aLine.indexOf('=') + 1);
			else if (aLine.startsWith("testNumber"))
				config.testNumber = Integer.parseInt(aLine.substring(aLine.indexOf('=') + 1));
			else if (aLine.startsWith("bench"))
				config.bench = aLine.substring(aLine.indexOf('=') + 1).equalsIgnoreCase("true");
			else if (aLine.startsWith("wrapRP"))
				config.wrapRP = aLine.substring(aLine.indexOf('=') + 1).equalsIgnoreCase("true");
			else if (aLine.startsWith("simu"))
				config.simu = aLine.substring(aLine.indexOf('=') + 1).equalsIgnoreCase("true");
			else if (aLine.startsWith("printReport"))
				config.printReport = aLine.substring(aLine.indexOf('=') + 1).equalsIgnoreCase("true");
			else if (aLine.startsWith("beepOnDetection"))
				config.beepOnDetection = aLine.substring(aLine.indexOf('=') + 1).equalsIgnoreCase("true");
			else if (aLine.startsWith("beepOnNewDetection"))
				config.beepOnNewDetection = aLine.substring(aLine.indexOf('=') + 1).equalsIgnoreCase("true");
			else if (aLine.startsWith("maxTags"))
				config.maxTags = Integer.parseInt(aLine.substring(aLine.indexOf('=') + 1));
			else if (aLine.startsWith("DefaultTriggerLauncher"))
				config.DefaultTriggerLauncher = aLine.substring(aLine.indexOf('=') + 1).equalsIgnoreCase("true");
			else if (aLine.startsWith("sglnTrigger"))
				config.sglnTrigger = aLine.substring(aLine.indexOf('=') + 1);
			else if (aLine.startsWith("gPattern"))
				grpPatterns.add(aLine.substring(aLine.indexOf('=') + 1));
		}
		config.grpPatternList = (String[]) grpPatterns.toArray(new String[grpPatterns.size()]);
	}
	
//	private static String[] decodeList(String line) {
//		String[] list = null;
//		StringTokenizer tokenizer = new StringTokenizer(line, "");
//		int count = tokenizer.countTokens();
//		
//		list = new String [count];
//		
//		for (int i = 0 ; i < count ; i++) {
//			list[i] = tokenizer.nextToken();
//		}
//				
//		return list;
//	}
}

