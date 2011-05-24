package org.ow2.aspirerfid.programmableengine.client;

import java.net.URL;
import java.util.HashMap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.ow2.aspirerfid.commons.pe.exceptions.NoSuchOLCBProcIdException;
import org.ow2.aspirerfid.commons.pe.exceptions.NotCompletedExecutionException;
import org.ow2.aspirerfid.commons.pe.exceptions.OLCBProcValidationException;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineCLCBProcControlInterface;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineEBProcControlInterface;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineOLCBProcControlInterface;

public class PEOLCBProcControlClient implements ProgrammEngineOLCBProcControlInterface {

	/**
	 * The Programmable Engine olcbProcControl client
	 */
	private ProgrammEngineOLCBProcControlInterface olcbProcControlClient = null;

	/** logger. */
	public static final Logger LOG = Logger.getLogger(ProgrammableEngineClient.class);

	/**
	 * The configuration
	 */
	private XMLConfiguration config = null;

	private String peOLCBProcControlEndpoint = null;

	private String DEFAULT_peEndpoint = "http://localhost:8080/aspireRfidProgrammableEngine";

	/**
	 * The Object where the various steps success or not feedback will be stored
	 */
	private HashMap<String, String> olcbProcControlFeedback = null;

	public PEOLCBProcControlClient() {

		String peEndPoint = null;

		// read parameters from configuration file
		config = new XMLConfiguration();
		config.setListDelimiter(',');
		URL fileurl = this.getClass().getResource("/PeClientParameters.xml");
		// sets the parameters according to the properties file

		try {
			config.load(fileurl);
		} catch (ConfigurationException e) {
			String message = "Couldn't get WarehouseParameters at: " + fileurl.getFile() + "\n" + e.getMessage();
			LOG.debug(message);
			e.printStackTrace();
		}

		peEndPoint = config.getString("PeEndPoint");

		if (peEndPoint.equals(null) || peEndPoint.equals("") || peEndPoint == null) {
			this.peOLCBProcControlEndpoint = DEFAULT_peEndpoint + "/olcbproccontrol";

		} else if (!peEndPoint.endsWith("/")) {
			this.peOLCBProcControlEndpoint = peEndPoint + "olcbproccontrol";

		} else if (peEndPoint.endsWith("/")) {
			this.peOLCBProcControlEndpoint = peEndPoint + "/olcbproccontrol";
		}

		initializeWS();

	}

	public PEOLCBProcControlClient(String peEndPoint) {

		if (peEndPoint.equals(null) || peEndPoint.equals("") || peEndPoint == null) {
			this.peOLCBProcControlEndpoint = DEFAULT_peEndpoint + "/olcbproccontrol";

		} else if (!peEndPoint.endsWith("/")) {
			this.peOLCBProcControlEndpoint = peEndPoint + "olcbproccontrol";

		} else if (peEndPoint.endsWith("/")) {
			this.peOLCBProcControlEndpoint = peEndPoint + "/olcbproccontrol";
		}

		initializeWS();

	}

	/**
	 * This method initializes the Web Service connection using CXF with Spring
	 */
	private void initializeWS() {

		// Start CXF encodeClient Creation
		// Note that it is important to keep the ProxyFactoryBean ID unique e.g.
		// in this case the "olcbProcControlClientFactory"
		JaxWsProxyFactoryBean olcbProcControlClientFactory = new JaxWsProxyFactoryBean();
		olcbProcControlClientFactory.setServiceClass(ProgrammEngineOLCBProcControlInterface.class);
		olcbProcControlClientFactory.setAddress(peOLCBProcControlEndpoint);
		LOG.debug("Creating factory with endpoint: " + peOLCBProcControlEndpoint);
		olcbProcControlClient = (ProgrammEngineOLCBProcControlInterface) olcbProcControlClientFactory.create();

	}

	@Override
	public OLCBProc getOLCBProc(String openLoopCBProcID, HashMap<String, String> endPoints)
			throws NoSuchOLCBProcIdException {
		OLCBProc olcbProc = new OLCBProc();
		olcbProc = olcbProcControlClient.getOLCBProc(openLoopCBProcID, endPoints);
		return olcbProc;
	}

	@Override
	public HashMap<String, String> register(OLCBProc openLoopCBProc) throws OLCBProcValidationException,
			NotCompletedExecutionException {
		olcbProcControlFeedback = null;
		olcbProcControlFeedback = olcbProcControlClient.register(openLoopCBProc);

		for (String olcbProcControlFeedbackKey : olcbProcControlFeedback.keySet()) {
			LOG.debug("For feedback ID: " + olcbProcControlFeedbackKey + " the feddback value is: "
					+ olcbProcControlFeedback.get(olcbProcControlFeedbackKey));
		}

		return olcbProcControlFeedback;
	}

	@Override
	public HashMap<String, String> start(OLCBProc openLoopCBProc) throws NoSuchOLCBProcIdException {
		olcbProcControlFeedback = null;
		olcbProcControlFeedback = olcbProcControlClient.start(openLoopCBProc);

		for (String olcbProcControlFeedbackKey : olcbProcControlFeedback.keySet()) {
			LOG.debug("For feedback ID: " + olcbProcControlFeedbackKey + " the feddback value is: "
					+ olcbProcControlFeedback.get(olcbProcControlFeedbackKey));
		}

		return olcbProcControlFeedback;
	}

	@Override
	public HashMap<String, String> stop(OLCBProc openLoopCBProc) throws NoSuchOLCBProcIdException {
		olcbProcControlFeedback = null;
		olcbProcControlFeedback = olcbProcControlClient.stop(openLoopCBProc);

		for (String olcbProcControlFeedbackKey : olcbProcControlFeedback.keySet()) {
			LOG.debug("For feedback ID: " + olcbProcControlFeedbackKey + " the feddback value is: "
					+ olcbProcControlFeedback.get(olcbProcControlFeedbackKey));
		}

		return olcbProcControlFeedback;
	}

	@Override
	public HashMap<String, String> unregister(OLCBProc openLoopCBProc) throws NoSuchOLCBProcIdException {
		olcbProcControlFeedback = null;
		olcbProcControlFeedback = olcbProcControlClient.unregister(openLoopCBProc);

		for (String olcbProcControlFeedbackKey : olcbProcControlFeedback.keySet()) {
			LOG.debug("For feedback ID: " + olcbProcControlFeedbackKey + " the feddback value is: "
					+ olcbProcControlFeedback.get(olcbProcControlFeedbackKey));
		}

		return olcbProcControlFeedback;
	}

	@Override
	public HashMap<String, String> update(OLCBProc openLoopCBProc) throws OLCBProcValidationException,
			NotCompletedExecutionException {
		olcbProcControlFeedback = null;
		olcbProcControlFeedback = olcbProcControlClient.update(openLoopCBProc);
		
		for (String olcbProcControlFeedbackKey : olcbProcControlFeedback.keySet()) {
			LOG.debug("For feedback ID: " + olcbProcControlFeedbackKey + " the feddback value is: "
					+ olcbProcControlFeedback.get(olcbProcControlFeedbackKey));
		}

		return olcbProcControlFeedback;
	}

}
