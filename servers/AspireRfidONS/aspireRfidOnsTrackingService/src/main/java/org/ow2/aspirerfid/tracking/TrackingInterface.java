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

package org.ow2.aspirerfid.tracking;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.Tag;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.TagEvent;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.TagIdList;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.Trackerdocument;

/**
 * @author Kostas Mourtzoukos (komo) e-mail: komo@ait.edu.gr
 *
 */

@SuppressWarnings("unused")
@WebService(name="TrackingReportInterface", targetNamespace="http://tracking.aspirerfid.ow2.org/")
public interface TrackingInterface {
	
	@WebMethod()
	@WebResult(name = "epcTagEvents")
	List<TagEvent> query(@WebParam(name = "epcId") String epcId);
	
	@WebMethod()
	@WebResult(name = "status")
	boolean report(@WebParam(name = "epcTagList") Trackerdocument epcTagList);

}
