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


package org.ow2.aspirerfid.programmableengine.objects;

import java.util.ArrayList;
import java.util.Hashtable;

import org.ow2.aspirerfid.commons.ale.model.ale.ECSpec;
import org.ow2.aspirerfid.commons.epcis.model.EPCISMasterDataDocumentType;
import org.ow2.aspirerfid.commons.ale.model.alelr.LRSpec;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class ProcessedEBProc {

	private String id;
	private String name;
	private ECSpec ecSpec;
	private Hashtable<String,LRSpec> lrSpecs;
	private EPCISMasterDataDocumentType epcisMasterDataDocument;
	private String ecSpecSubscriptionURI;
	private String definedECSpecName;
	private String aleClientEndPoint;
	private String aleLrClientEndPoint;
	private String epcisClientCaptureEndPoint;
	private String epcisClientQueryEndPoint;
	private String begEngineEndpoint;
//	private String clCBProcID;
	
	
	
	
	
	


	public String getBegEngineEndpoint() {
		return begEngineEndpoint;
	}
	public void setBegEngineEndpoint(String begEngineEndpoint) {
		this.begEngineEndpoint = begEngineEndpoint;
	}
	public ECSpec getEcSpec() {
		return ecSpec;
	}
	public void setEcSpec(ECSpec ecSpec) {
		this.ecSpec = ecSpec;
	}
	public Hashtable<String, LRSpec> getLrSpecs() {
		return lrSpecs;
	}
	public void setLrSpecs(Hashtable<String, LRSpec> lrSpecs) {
		this.lrSpecs = lrSpecs;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EPCISMasterDataDocumentType getEpcisMasterDataDocument() {
		return epcisMasterDataDocument;
	}
	public void setEpcisMasterDataDocument(EPCISMasterDataDocumentType epcisMasterDataDocument) {
		this.epcisMasterDataDocument = epcisMasterDataDocument;
	}
	public String getEcSpecSubscriptionURI() {
		return ecSpecSubscriptionURI;
	}
	public void setEcSpecSubscriptionURI(String ecSpecSubscriptionURI) {
		this.ecSpecSubscriptionURI = ecSpecSubscriptionURI;
	}
	public String getDefinedECSpecName() {
		return definedECSpecName;
	}
	public void setDefinedECSpecName(String definedECSpecName) {
		this.definedECSpecName = definedECSpecName;
	}
	public String getAleClientEndPoint() {
		return aleClientEndPoint;
	}
	public void setAleClientEndPoint(String aleClientEndPoint) {
		this.aleClientEndPoint = aleClientEndPoint;
	}
	public String getAleLrClientEndPoint() {
		return aleLrClientEndPoint;
	}
	public void setAleLrClientEndPoint(String aleLrClientEndPoint) {
		this.aleLrClientEndPoint = aleLrClientEndPoint;
	}
	public String getEpcisClientCaptureEndPoint() {
		return epcisClientCaptureEndPoint;
	}
	public void setEpcisClientCaptureEndPoint(String epcisClientCaptureEndPoint) {
		this.epcisClientCaptureEndPoint = epcisClientCaptureEndPoint;
	}
	public String getEpcisClientQueryEndPoint() {
		return epcisClientQueryEndPoint;
	}
	public void setEpcisClientQueryEndPoint(String epcisClientQueryEndPoint) {
		this.epcisClientQueryEndPoint = epcisClientQueryEndPoint;
	}
	
}
