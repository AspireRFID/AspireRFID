package org.ow2.aspirerfid.commons.beg.interfaces;

import javax.jws.*;

import org.ow2.aspirerfid.commons.beg.model.EventStatus;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementType;
import java.util.List;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
@WebService(name = "BegInterface", targetNamespace = "http://config.beg.aspirerfid.ow2.org/")


public interface BegInterface {
	@WebMethod()
	@WebResult(name = "eventStatus")
	public EventStatus getEpcListForEvent(@WebParam(name = "eventID") String eventID);

	@WebMethod()
	@WebResult(name = "isStopped")
	public boolean stopBegForEven(@WebParam(name = "eventID") String eventID);

	@WebMethod
	@WebResult(name = "startedEvents")
	public java.util.List<java.lang.String> getStartedEvents();

	@WebMethod
	@WebResult(name = "isStarted")
	public boolean startBegForEvent(@WebParam(name = "vocabularyElementType") VocabularyElementType vocabularyElementType,
			@WebParam(name = "repositoryCaptureURL") String repositoryCaptureURL, @WebParam(name = "begListeningPort") String begListeningPort);

	@WebMethod
	@WebResult(name = "availabelEvents")
	public List<VocabularyElementType> getEventList(@WebParam(name = "repositoryQueryURL") String repositoryQueryURL);

}