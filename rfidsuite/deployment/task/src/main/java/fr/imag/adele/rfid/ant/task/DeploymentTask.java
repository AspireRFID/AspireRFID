/*
 * RFID Suite
 *
 * The "RFID Suite" cannot be copied, modified, distributed IN ANY FORM 
 * without the permission and written consent of the owner 
 * the Université Joseph Fourier, LIG laboratory, ADELE team (UJF-LIG-ADELE).
 *
 * UJF-LIG-ADELE retains all rights over to this program in either the original
 * or modified forms, and no violation, deletion, or change of the copyright
 * notice is allowed.
 *
 * Furthermore, UJF-LIG-ADELE will have no liability or responsibility to any
 * user with respect  to loss or damage caused directly or indirectly by this 
 * program.
 *
 * Copyright : Université Joseph Fourier, LIG laboratory, ADELE team, 
 * 2005-2008.
 *
 * Contact: Didier Donsez mailto:Didier.Donsez@imag.fr
 */
package fr.imag.adele.rfid.ant.task;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Binding;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.ldap.LdapContext;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * This task performs the deployment.
 * @author François Fornaciari
 */
public class DeploymentTask extends Task {
	private MBeanServerConnection serverConnection;

	private JMXConnector jmxConnector;

	private boolean startFictiveReader;

	public boolean isStartFictiveReader() {
		return startFictiveReader;
	}

	public void setStartFictiveReader(boolean startFictiveReader) {
		this.startFictiveReader = startFictiveReader;
	}

	/**
	 * Override the execute() method
	 * @exception BuildException
	 */
	public void execute() throws BuildException {
		try {
			// Open the LDAP context
			DirContext dirContex = getInitialDirContext();

			NamingEnumeration rootEnum = dirContex.listBindings("ou=rfid");
			while (rootEnum.hasMoreElements()) {
				Binding binding = (Binding) rootEnum.nextElement();
				LdapContext rootContext = (LdapContext) binding.getObject();
				parseLDAP(rootContext, null, null);
			}

			// Close the context
			dirContex.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parseLDAP(LdapContext context, String gatewayName, String jmxServiceURL)
			throws NamingException {
		if (context.getAttributes("").get("objectClass").get().equals("rfidPremise")
				|| context.getAttributes("").get("objectClass").get().equals("rfidEdge")) {

			String ip = (String) context.getAttributes("").get("ip").get();
			String jmxServicePort = (String) context.getAttributes("").get("jmxServicePort").get();
			gatewayName = (String) context.getAttributes("").get("cn").get();

			jmxServiceURL = "service:jmx:rmi:///jndi/rmi://" + ip + ":" + jmxServicePort + "/"
					+ gatewayName;
			
			System.out.println("Installing bundles on " + gatewayName);			
			installBundles(context, jmxServiceURL);
		} else if (context.getAttributes("").get("objectClass").get().equals("rfidDispatcher")) {
			System.out.println("Configuring dispatcher on " + gatewayName);		
			configureDispatcher(context, gatewayName, jmxServiceURL);
		} else if (context.getAttributes("").get("objectClass").get().equals("ECSpec")) {
			// Do not treat generic nodes but aliased nodes
			if (jmxServiceURL != null) {
				System.out.println("Creating ECSpecs on " + gatewayName);
				createECSpec(context, gatewayName, jmxServiceURL);
			}
		} else if (context.getAttributes("").get("objectClass").get().equals("rfidReaders")) {
			System.out.println("Configuring readers on " + gatewayName);
			configureReaders(context, jmxServiceURL);
		}

		NamingEnumeration subEnum = context.listBindings("");
		while (subEnum.hasMoreElements()) {
			Binding binding = (Binding) subEnum.nextElement();
			LdapContext subContext = (LdapContext) binding.getObject();
			parseLDAP(subContext, gatewayName, jmxServiceURL);
		}
	}

	private DirContext getInitialDirContext() throws NamingException {
		try {
			InputStream inputStream = getClass().getResourceAsStream("/config.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			DirContext dirContex = new InitialDirContext(properties);
			return dirContex;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void openMBeanServerConnection(LdapContext context, String jmxServiceURL)
			throws IOException, NamingException {
		JMXServiceURL url = new JMXServiceURL(jmxServiceURL);
		jmxConnector = JMXConnectorFactory.connect(url, null);
		serverConnection = jmxConnector.getMBeanServerConnection();
	}

	private void closeJmxConnector() throws IOException {
		if (jmxConnector != null) {
			jmxConnector.close();
		}
	}

	private void installBundles(LdapContext context, String jmxServiceUrl) throws NamingException {		
		// TODO username and password
		String obrUrl = (String) context.getAttributes("").get("obrUrl").get();

		try {
			// Open the MBean server connection
			openMBeanServerConnection(context, jmxServiceUrl);

			// Add the OBR Url contained in the attributes of the edge entry
			ObjectName obrMBean = new ObjectName("org.osgi:name=ObrMBean");
			serverConnection.invoke(obrMBean, "obrAddRepository", new Object[] { obrUrl },
					new String[] { String.class.getName() });

			// Install listed bundles from OBR
			// For each bundles bindings
			NamingEnumeration edgeEnum = context.listBindings("cn=bundles");
			while (edgeEnum.hasMoreElements()) {
				Binding binding = (Binding) edgeEnum.nextElement();
				LdapContext bundle = (LdapContext) binding.getObject();

				String bundleName = (String) bundle.getAttributes("").get("bundleName").get();
				String bundleVersion = (String) bundle.getAttributes("").get("bundleVersion").get();

				// Install bundles for the current gateway
				serverConnection.invoke(obrMBean, "obrStartBundle", new Object[] { bundleName,
						bundleVersion }, new String[] { String.class.getName(),
						String.class.getName() });
			}

			// Close the MBean server connection
			closeJmxConnector();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void configureDispatcher(LdapContext context, String gatewayName, String jmxServiceUrl)
			throws NamingException {
		Attributes dispatcherAttributes = context.getAttributes("");

		// Open the MBean server connection
		try {
			openMBeanServerConnection(context, jmxServiceUrl);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		try {
			ObjectName dispatcher = new ObjectName("rfid:type=service,SymbolicName=Dispatcher");

			String[] mbeanAttributes = { "ConsumerTopic", "GatewayName", "DestinationURI",
					"JavaNamingFactoryInitial", "JavaNamingFactoryHost", "JavaNamingFactoryPort",
					"JmsTopicConnectionFactoryBindName", "JmsServerPort", "JmsServerHost",
					"JmsAdminLogin", "JmsAdminPass" };
			String[] mbeanAttributesTypes = { "String", "String", "String", "String", "String",
					"Integer", "String", "Integer", "String", "String", "String" };

			dispatcherAttributes.put("gatewayName", gatewayName);

			// Changes DispatcherMBean attributes
			serverConnection.setAttributes(dispatcher, getAttributeList(dispatcherAttributes,
					mbeanAttributes, mbeanAttributesTypes));

			// Invokes the createConsumer method
			serverConnection.invoke(dispatcher, "createConsumer", new Object[] { context
					.getAttributes("").get("consumerTopic").get() }, new String[] { String.class
					.getName() });

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the MBean server connection
			try {
				closeJmxConnector();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void createECSpec(LdapContext context, String gatewayName, String jmxServiceURL)
			throws NamingException {

		Attributes ecSpecAttributes = context.getAttributes("");

		// Open the MBean server connection
		try {
			openMBeanServerConnection(context, jmxServiceURL);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			// Open the MBean server connection
			openMBeanServerConnection(context, jmxServiceURL);

			ObjectName ecSpecFactory = new ObjectName(
					"rfid:type=service,SymbolicName=ECSpecFactory");

			String[] mbeanAttributes = { "Duration", "EventTopic", "GatewayName",
					"LogicalReaderNames", "IncludeSpecInReports", "RepeatPeriod",
					"StableSetInterval", "StartTrigger", "StopTrigger" };
			String[] mbeanAttributesTypes = { "Long", "String", "String", "StringArray", "boolean",
					"Long", "Long", "String", "String" };

			ecSpecAttributes.put("gatewayName", gatewayName);

			// Changes ECSpecFactoryMBean attributes
			serverConnection.setAttributes(ecSpecFactory, getAttributeList(ecSpecAttributes,
					mbeanAttributes, mbeanAttributesTypes));

			// For each ecReports bindings
			NamingEnumeration ecReportEnum = context.listBindings("cn=ecreports");
			while (ecReportEnum.hasMoreElements()) {
				Binding binding = (Binding) ecReportEnum.nextElement();
				LdapContext ecReportsContext = (LdapContext) binding.getObject();
				Attributes ecReportAttributes = ecReportsContext.getAttributes("");

				String reportName = (String) ecReportAttributes.get("cn").get();
				boolean includeEPC = Boolean.valueOf((String) ecReportAttributes.get("includeEPC")
						.get());
				boolean includeTag = Boolean.valueOf((String) ecReportAttributes.get("includeTag")
						.get());
				boolean includeRawHex = Boolean.valueOf((String) ecReportAttributes.get(
						"includeRawHex").get());
				boolean includeRawDecimal = Boolean.valueOf((String) ecReportAttributes.get(
						"includeRawDecimal").get());
				boolean includeCount = Boolean.valueOf((String) ecReportAttributes.get(
						"includeCount").get());
				String reportSetSpec = (String) ecReportAttributes.get("reportSetSpec").get();
				boolean reportIfEmpty = Boolean.valueOf((String) ecReportAttributes.get(
						"reportIfEmpty").get());
				boolean reportOnlyOnChange = Boolean.valueOf((String) ecReportAttributes.get(
						"reportOnlyOnChange").get());
				boolean includeGPS = Boolean.valueOf((String) ecReportAttributes.get("includeGPS")
						.get());
				boolean includeTemperature = Boolean.valueOf((String) ecReportAttributes.get(
						"includeTemperature").get());
				boolean includeReaderName = Boolean.valueOf((String) ecReportAttributes.get(
						"includeReaderName").get());

				serverConnection
						.invoke(ecSpecFactory, "addECReportSpec", new Object[] { reportName,
								includeEPC, includeTag, includeRawHex, includeRawDecimal,
								includeCount, reportSetSpec, reportIfEmpty, reportOnlyOnChange,
								includeGPS, includeTemperature, includeReaderName }, new String[] {
								String.class.getName(), boolean.class.getName(),
								boolean.class.getName(), boolean.class.getName(),
								boolean.class.getName(), boolean.class.getName(),
								String.class.getName(), boolean.class.getName(),
								boolean.class.getName(), boolean.class.getName(),
								boolean.class.getName(), boolean.class.getName() });

				if (ecReportsContext.search("", new BasicAttributes("cn", "ecgroups"))
						.hasMoreElements()) {
					// For each ecGroups bindings
					NamingEnumeration ecGroupEnum = ecReportsContext.listBindings("cn=ecgroups");
					while (ecGroupEnum.hasMoreElements()) {
						binding = (Binding) ecGroupEnum.nextElement();
						LdapContext ecGroupContext = (LdapContext) binding.getObject();
						Attributes ecGroupAttributes = ecGroupContext.getAttributes("");

						// Create ECGroup
						serverConnection.invoke(ecSpecFactory, "addECGroupSpec", new Object[] {
								reportName, ecGroupAttributes.get("epcPattern").get() },
								new String[] { String.class.getName(), String.class.getName() });
					}
				}

				if (ecReportsContext.search("", new BasicAttributes("cn", "ecfilters"))
						.hasMoreElements()) {

					// For each ecFilterss bindings
					NamingEnumeration ecFilterEnum = ecReportsContext.listBindings("cn=ecfilters");
					while (ecFilterEnum.hasMoreElements()) {
						binding = (Binding) ecFilterEnum.nextElement();
						LdapContext ecFilterContext = (LdapContext) binding.getObject();
						Attributes ecFilterAttributes = ecFilterContext.getAttributes("");

						// Create ECGroup
						serverConnection.invoke(ecSpecFactory, "addECFilterSpec", new Object[] {
								reportName,
								ecFilterAttributes.get("epcPattern").get(),
								Boolean.valueOf((String) ecFilterAttributes
										.get("isIncludingFilter").get()) }, new String[] {
								String.class.getName(), String.class.getName(),
								boolean.class.getName() });
					}
				}
			}

			// Create ECSpec
			serverConnection.invoke(ecSpecFactory, "createECSpec", new Object[] { ecSpecAttributes
					.get("cn").get() }, new String[] { String.class.getName() });

			if (ecSpecAttributes.get("subscriptions") != null) {
				// Subscribe to URIs
				ObjectName ale = new ObjectName("rfid:type=service,SymbolicName=ALE");

				NamingEnumeration subscriptionsEnum = ecSpecAttributes.get("subscriptions")
						.getAll();
				while (subscriptionsEnum.hasMoreElements()) {
					// Create subscriptions
					serverConnection.invoke(ale, "subscribe", new Object[] {
							ecSpecAttributes.get("cn").get(), subscriptionsEnum.nextElement() },
							new String[] { String.class.getName(), String.class.getName() });
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the MBean server connection
			try {
				closeJmxConnector();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void configureReaders(LdapContext context, String jmxServiceURL) throws NamingException {

		// Open the MBean server connection
		try {
			openMBeanServerConnection(context, jmxServiceURL);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			// For each readers bindings
			NamingEnumeration readerEnum = context.listBindings("");
			while (readerEnum.hasMoreElements()) {
				Binding binding = (Binding) readerEnum.nextElement();
				LdapContext readerContext = (LdapContext) binding.getObject();
				Attributes readerAttributes = readerContext.getAttributes("");

				if (readerAttributes.get("objectClass").get().equals("fictiveReader")) {
					ObjectName fictiveReader = new ObjectName(
							"rfid:type=reader,SymbolicName=Fictive");

					String[] mbeanAttributes = { "LogicalName", "RepeatPeriod", "RFIDEventTopic",
							"ReaderGUId", "GpsCoordinates" };
					String[] mbeanAttributesTypes = { "String", "Integer", "String", "String",
							"String" };

					// Changes fictive reader attributes
					serverConnection.setAttributes(fictiveReader, getAttributeList(
							readerAttributes, mbeanAttributes, mbeanAttributesTypes));

					if (startFictiveReader) {
						// Starts fictive reader
						serverConnection.invoke(fictiveReader, "startReader", new Object[] {},
								new String[] {});
					}
				}

				if (readerAttributes.get("objectClass").get().equals("tagsysReader")) {
					ObjectName tagsysReader = new ObjectName(
							"rfid:type=service,SymbolicName=TagsysDriver");

					String[] mbeanAttributes = { "LogicalName", "RepeatPeriod", "RFIDEventTopic",
							"Channel", "BaudRate", "PortName", "TagType", "GpsCoordinates" };
					String[] mbeanAttributesTypes = { "String", "Integer", "String", "Integer",
							"Integer", "String", "String", "String" };

					// Changes tagsys reader attributes
					serverConnection.setAttributes(tagsysReader, getAttributeList(readerAttributes,
							mbeanAttributes, mbeanAttributesTypes));

					// Creates tagsys reader
					serverConnection.invoke(tagsysReader, "newReader", new Object[] {},
							new String[] {});
				}

				if (readerAttributes.get("objectClass").get().equals("tirisReader")) {
					ObjectName tirisReader = new ObjectName(
							"rfid:type=service,SymbolicName=Tiris6350Driver");

					String[] mbeanAttributes = { "LogicalName", "RepeatPeriod", "RFIDEventTopic",
							"BaudRate", "PortName", "DataBits", "StopBits", "FlowControlIn",
							"FlowControlOut", "GpsCoordinates", "Parity" };
					String[] mbeanAttributesTypes = { "String", "Integer", "String", "Integer",
							"String", "Integer", "Integer", "String", "String", "String", "String" };

					// Changes tiris reader attributes
					serverConnection.setAttributes(tirisReader, getAttributeList(readerAttributes,
							mbeanAttributes, mbeanAttributesTypes));

					// Creates tiris reader
					serverConnection.invoke(tirisReader, "newReader", new Object[] {},
							new String[] {});
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the MBean server connection
			try {
				closeJmxConnector();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private AttributeList getAttributeList(Attributes contextAttributes, String[] mbeanAttributes,
			String[] mbeanAttributesTypes) throws NamingException {
		AttributeList attributes = new AttributeList();

		for (int i = 0; i < mbeanAttributes.length; i++) {
			Attribute attribute = null;

			if (contextAttributes.get(mbeanAttributes[i]) != null) {
				if (mbeanAttributesTypes[i].equals("String")
						|| mbeanAttributesTypes[i].equals("boolean")) {
					attribute = new Attribute(mbeanAttributes[i], contextAttributes.get(
							mbeanAttributes[i]).get());
				} else if (mbeanAttributesTypes[i].equals("Integer")) {
					attribute = new Attribute(mbeanAttributes[i], Integer
							.valueOf((String) contextAttributes.get(mbeanAttributes[i]).get()));
				} else if (mbeanAttributesTypes[i].equals("Long")) {
					attribute = new Attribute(mbeanAttributes[i], Long
							.valueOf((String) contextAttributes.get(mbeanAttributes[i]).get()));
				} else if (mbeanAttributesTypes[i].equals("StringArray")) {
					NamingEnumeration namingEnumeration = contextAttributes.get(mbeanAttributes[i])
							.getAll();
					Set hashSet = new HashSet();
					while (namingEnumeration.hasMoreElements()) {
						hashSet.add(namingEnumeration.nextElement());
					}

					attribute = new Attribute(mbeanAttributes[i], hashSet);
				}

				attributes.add(attribute);
			}
		}
		return attributes;
	}
}
