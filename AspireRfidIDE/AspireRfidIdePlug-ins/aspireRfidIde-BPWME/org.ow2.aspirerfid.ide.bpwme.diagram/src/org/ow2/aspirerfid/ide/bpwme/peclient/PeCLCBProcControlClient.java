package org.ow2.aspirerfid.ide.bpwme.peclient;

import java.net.URL;
import java.util.HashMap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import org.ow2.aspirerfid.commons.apdl.model.CLCBProc;
import org.ow2.aspirerfid.commons.pe.exceptions.CLCBProcValidationException;
import org.ow2.aspirerfid.commons.pe.exceptions.NoSuchCLCBProcIdException;
import org.ow2.aspirerfid.commons.pe.exceptions.NotCompletedExecutionException;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineCLCBProcControlInterface;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineEBProcControlInterface;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineOLCBProcControlInterface;

public class PeCLCBProcControlClient implements ProgrammEngineCLCBProcControlInterface {

	/**
	 * The Programmable Engine clcbProcControl client
	 */
	private ProgrammEngineCLCBProcControlInterface clcbProcControlClient = null;
	
	
	/** logger. */
	public static final Logger LOG = Logger.getLogger(PeCLCBProcControlClient.class);

	/**
	 * The configuration
	 */
	private XMLConfiguration config = null;

	private String peCLCBProcControlEndpoint = null;

	private String DEFAULT_peEndpoint = "http://localhost:8080/aspireRfidProgrammableEngine";
	
	/**
	 * The Object where the various steps success or not feedback will be stored
	 */
	private HashMap<String, String> clcbProcControlFeedback = null;
	
	public PeCLCBProcControlClient() {

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
			this.peCLCBProcControlEndpoint = DEFAULT_peEndpoint + "/clcbproccontrol";

		} else if (!peEndPoint.endsWith("/")) {
			this.peCLCBProcControlEndpoint = peEndPoint + "clcbproccontrol";

		} else if (peEndPoint.endsWith("/")) {
			this.peCLCBProcControlEndpoint = peEndPoint + "/clcbproccontrol";
		}

		initializeWS();

	}
	
	public PeCLCBProcControlClient(String peEndPoint) {
		
		if (peEndPoint.equals(null) || peEndPoint.equals("") || peEndPoint == null) {
			this.peCLCBProcControlEndpoint = DEFAULT_peEndpoint + "/clcbproccontrol";

		} else if (!peEndPoint.endsWith("/")) {
			this.peCLCBProcControlEndpoint = peEndPoint + "clcbproccontrol";

		} else if (peEndPoint.endsWith("/")) {
			this.peCLCBProcControlEndpoint = peEndPoint + "/clcbproccontrol";
		}

		initializeWS();

	}
	
	/**
	 * This method initializes the Web Service connection using CXF with Spring
	 */
	private void initializeWS() {

		// Start CXF encodeClient Creation
		// Note that it is important to keep the ProxyFactoryBean ID unique e.g.
		// in this case the "clcbProcControlClientFactory"

		// Start CXF decodeClient Creation
		JaxWsProxyFactoryBean clcbProcControlClientFactory = new JaxWsProxyFactoryBean();
		clcbProcControlClientFactory.setServiceClass(ProgrammEngineCLCBProcControlInterface.class);
		clcbProcControlClientFactory.setAddress(peCLCBProcControlEndpoint);
		LOG.debug("Creating factory with endpoint: " + peCLCBProcControlEndpoint);
		clcbProcControlClient = (ProgrammEngineCLCBProcControlInterface) clcbProcControlClientFactory.create();


	}

	
	@Override
	public CLCBProc getCLCBProc(String closeLoopCBProcID, HashMap<String, String> endPoints)
			throws NoSuchCLCBProcIdException {
		CLCBProc clcbProc = new CLCBProc();
		clcbProc = clcbProcControlClient.getCLCBProc(closeLoopCBProcID, endPoints);
		
		return clcbProc;
	}

	@Override
	public HashMap<String, String> register(CLCBProc closeLoopCBProc) throws CLCBProcValidationException,
			NotCompletedExecutionException {
		clcbProcControlFeedback = null;
		clcbProcControlFeedback = clcbProcControlClient.register(closeLoopCBProc);

		for (String clcbProcControlFeedbackKey : clcbProcControlFeedback.keySet()) {
			LOG.debug("For feedback ID: " + clcbProcControlFeedbackKey + " the feddback value is: "
					+ clcbProcControlFeedback.get(clcbProcControlFeedbackKey));
		}

		return clcbProcControlFeedback;
	}

	@Override
	public HashMap<String, String> start(CLCBProc closeLoopCBProc) throws NoSuchCLCBProcIdException {
		clcbProcControlFeedback = null;
		clcbProcControlFeedback = clcbProcControlClient.start(closeLoopCBProc);

		for (String clcbProcControlFeedbackKey : clcbProcControlFeedback.keySet()) {
			LOG.debug("For feedback ID: " + clcbProcControlFeedbackKey + " the feddback value is: "
					+ clcbProcControlFeedback.get(clcbProcControlFeedbackKey));
		}

		return clcbProcControlFeedback;
	}

	@Override
	public HashMap<String, String> stop(CLCBProc closeLoopCBProc) throws NoSuchCLCBProcIdException {
		clcbProcControlFeedback = null;
		clcbProcControlFeedback = clcbProcControlClient.stop(closeLoopCBProc);

		for (String clcbProcControlFeedbackKey : clcbProcControlFeedback.keySet()) {
			LOG.debug("For feedback ID: " + clcbProcControlFeedbackKey + " the feddback value is: "
					+ clcbProcControlFeedback.get(clcbProcControlFeedbackKey));
		}

		return clcbProcControlFeedback;
	}

	@Override
	public HashMap<String, String> unregister(CLCBProc closeLoopCBProc) throws NoSuchCLCBProcIdException {
		clcbProcControlFeedback = null;
		clcbProcControlFeedback = clcbProcControlClient.unregister(closeLoopCBProc);

		for (String clcbProcControlFeedbackKey : clcbProcControlFeedback.keySet()) {
			LOG.debug("For feedback ID: " + clcbProcControlFeedbackKey + " the feddback value is: "
					+ clcbProcControlFeedback.get(clcbProcControlFeedbackKey));
		}

		return clcbProcControlFeedback;
	}

	@Override
	public HashMap<String, String> update(CLCBProc closeLoopCBProc) throws CLCBProcValidationException,
			NotCompletedExecutionException {
		clcbProcControlFeedback = null;
		clcbProcControlFeedback = clcbProcControlClient.update(closeLoopCBProc);
		
		for (String clcbProcControlFeedbackKey : clcbProcControlFeedback.keySet()) {
			LOG.debug("For feedback ID: " + clcbProcControlFeedbackKey + " the feddback value is: "
					+ clcbProcControlFeedback.get(clcbProcControlFeedbackKey));
		}

		return clcbProcControlFeedback;
	}

}
