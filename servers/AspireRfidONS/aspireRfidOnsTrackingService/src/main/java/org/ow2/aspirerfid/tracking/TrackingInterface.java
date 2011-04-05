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
