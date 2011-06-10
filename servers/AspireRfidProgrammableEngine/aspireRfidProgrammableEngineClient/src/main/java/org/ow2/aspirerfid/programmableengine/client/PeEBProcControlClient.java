package org.ow2.aspirerfid.programmableengine.client;

import java.net.URL;
import java.util.HashMap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import org.ow2.aspirerfid.commons.apdl.model.EBProc;
import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.ow2.aspirerfid.commons.pe.exceptions.EBProcValidationException;
import org.ow2.aspirerfid.commons.pe.exceptions.NoSuchEBProcIdException;
import org.ow2.aspirerfid.commons.pe.exceptions.NotCompletedExecutionException;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineCLCBProcControlInterface;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineEBProcControlInterface;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineOLCBProcControlInterface;

public class PeEBProcControlClient implements ProgrammEngineEBProcControlInterface {
	
	
	/**
	 * The Programmable Engine ebProcControl client
	 */
	private ProgrammEngineEBProcControlInterface ebProcControlClient = null;
	
	/** logger. */
	public static final Logger LOG = Logger.getLogger(PeEBProcControlClient.class);

	/**
	 * The configuration
	 */
	private XMLConfiguration config = null;

	private String peEBProcControlEndpoint = null;

	private String DEFAULT_peEndpoint = "http://localhost:8080/aspireRfidProgrammableEngine";
	
	/**
	 * The Object where the various steps success or not feedback will be stored
	 */
	private HashMap<String, String> ebProcControlFeedback = null;
	
	public PeEBProcControlClient() {

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
			this.peEBProcControlEndpoint = DEFAULT_peEndpoint + "/ebproccontrol";

		} else if (!peEndPoint.endsWith("/")) {
			this.peEBProcControlEndpoint = peEndPoint + "ebproccontrol";

		} else if (peEndPoint.endsWith("/")) {
			this.peEBProcControlEndpoint = peEndPoint + "/ebproccontrol";
		}

		initializeWS();

	}
	
	public PeEBProcControlClient(String peEndPoint) {
		
		if (peEndPoint.equals(null) || peEndPoint.equals("") || peEndPoint == null) {
			this.peEBProcControlEndpoint = DEFAULT_peEndpoint + "/ebproccontrol";

		} else if (!peEndPoint.endsWith("/")) {
			this.peEBProcControlEndpoint = peEndPoint + "ebproccontrol";

		} else if (peEndPoint.endsWith("/")) {
			this.peEBProcControlEndpoint = peEndPoint + "/ebproccontrol";
		}

		initializeWS();

	}

	/**
	 * This method initializes the Web Service connection using CXF with Spring
	 */
	private void initializeWS() {

		// Start CXF encodeClient Creation
		// Note that it is important to keep the ProxyFactoryBean ID unique e.g.
		// in this case the "ebProcControlClientFactory"

		// Start CXF decodeClient Creation
		JaxWsProxyFactoryBean ebProcControlClientFactory = new JaxWsProxyFactoryBean();
		ebProcControlClientFactory.setServiceClass(ProgrammEngineEBProcControlInterface.class);
		ebProcControlClientFactory.setAddress(peEBProcControlEndpoint);
		LOG.debug("Creating factory with endpoint: " + peEBProcControlEndpoint);
		ebProcControlClient = (ProgrammEngineEBProcControlInterface) ebProcControlClientFactory.create();
	}
	
	
	@Override
	public EBProc getEBProc(String elementaryBProcID, HashMap<String, String> endPoints) throws NoSuchEBProcIdException {
		EBProc ebProc = new EBProc();
		ebProc = ebProcControlClient.getEBProc(elementaryBProcID, endPoints);
		return ebProc;
	}

	@Override
	public HashMap<String, String> register(EBProc elementaryBProc) throws EBProcValidationException,
			NotCompletedExecutionException {
		ebProcControlFeedback = null;
		ebProcControlFeedback = ebProcControlClient.register(elementaryBProc);

		for (String ebProcControlFeedbackKey : ebProcControlFeedback.keySet()) {
			LOG.debug("For feedback ID: " + ebProcControlFeedbackKey + " the feddback value is: "
					+ ebProcControlFeedback.get(ebProcControlFeedbackKey));
		}

		return ebProcControlFeedback;
	}

	@Override
	public HashMap<String, String> start(EBProc elementaryBProc) throws NoSuchEBProcIdException {
		ebProcControlFeedback = null;
		ebProcControlFeedback = ebProcControlClient.start(elementaryBProc);

		for (String ebProcControlFeedbackKey : ebProcControlFeedback.keySet()) {
			LOG.debug("For feedback ID: " + ebProcControlFeedbackKey + " the feddback value is: "
					+ ebProcControlFeedback.get(ebProcControlFeedbackKey));
		}

		return ebProcControlFeedback;
	}

	@Override
	public HashMap<String, String> stop(EBProc elementaryBProc) throws NoSuchEBProcIdException {
		ebProcControlFeedback = null;
		ebProcControlFeedback = ebProcControlClient.stop(elementaryBProc);

		for (String ebProcControlFeedbackKey : ebProcControlFeedback.keySet()) {
			LOG.debug("For feedback ID: " + ebProcControlFeedbackKey + " the feddback value is: "
					+ ebProcControlFeedback.get(ebProcControlFeedbackKey));
		}

		return ebProcControlFeedback;
	}

	@Override
	public HashMap<String, String> unregister(EBProc elementaryBProc) throws NoSuchEBProcIdException {
		ebProcControlFeedback = null;
		ebProcControlFeedback = ebProcControlClient.unregister(elementaryBProc);

		for (String ebProcControlFeedbackKey : ebProcControlFeedback.keySet()) {
			LOG.debug("For feedback ID: " + ebProcControlFeedbackKey + " the feddback value is: "
					+ ebProcControlFeedback.get(ebProcControlFeedbackKey));
		}

		return ebProcControlFeedback;
	}

	@Override
	public HashMap<String, String> update(EBProc elementaryBProc) throws EBProcValidationException,
			NotCompletedExecutionException {
		ebProcControlFeedback = null;
		ebProcControlFeedback = ebProcControlClient.update(elementaryBProc);

		for (String ebProcControlFeedbackKey : ebProcControlFeedback.keySet()) {
			LOG.debug("For feedback ID: " + ebProcControlFeedbackKey + " the feddback value is: "
					+ ebProcControlFeedback.get(ebProcControlFeedbackKey));
		}

		return ebProcControlFeedback;
	}

}
