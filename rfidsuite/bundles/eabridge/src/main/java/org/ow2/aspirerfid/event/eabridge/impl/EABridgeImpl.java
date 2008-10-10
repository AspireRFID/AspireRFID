/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.event.eabridge.impl;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.ow2.aspirerfid.event.eabridge.EABridge;
import org.ow2.aspirerfid.event.export.api.ExportEventDispatcher;

/**
 * This class is the principal class of the EABridge Bundle. TODO make this
 * EAbridge used many Export Event at the same times.
 * 
 * @author <a href="g_brouss@yahoo.fr">mike oullion</a>
 * @author <a href="oullion.m@voila.fr>gilles broussillon</a>
 * @author Guillaume Surrel
 * @version 2007 0.1
 */

public class EABridgeImpl implements EABridge, EventHandler {

	/**
	 * just a static variable for internal used
	 */
	private static final int TOPICBLACKLISTED = 1;

	/**
	 * just a static variable for internal used
	 */
	private static final int TOPICEXPORTED = 2;

	/**
	 * a constant to define the topic used by the local admin
	 */
	private static final String LOCALADMINOSGITOPIC = "org/ow2/aspirerfid/eabridge/localadmin";

	/**
	 * Private variable - to store the bundlecontext
	 */
	private BundleContext bundleContext;

	/**
	 * private variable - This typed vector only contains string , each string
	 * represent the name of a topic unexportable
	 */
	private Vector topicBlacklisted = new Vector();

	/**
	 * Private variable - This typed vector only contains couple of <String,
	 * ServiceRegistration> each string identify a topic and its handler through
	 * its ServiceRegistration
	 */
	private Hashtable osgiTopicHandled = new Hashtable();

	/**
	 * Private variable - Typed vector which store each Event object published
	 * by the bridge to do reemit it after
	 */
	private Vector osgiEventPublished = new Vector();

	/**
	 * Private variable - like osgiEventPublished, this vector store the hash
	 * representation of each message sent through export Event
	 */
	private Vector exportEventPublished = new Vector();

	private EventAdmin eventAdmin;

	private ExportEventDispatcher exportEventDispatcher;

	/**
	 * Default constructor, may be not used.
	 * 
	 * @param bundleContext
	 */
	public EABridgeImpl(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	/*
	 * @see org.ow2.aspirerfid.event.eabridge.EABridge#exportTopics(java.lang.String[])
	 */
	public void exportTopics(String[] topic) {
		for (int i = 0; i < topic.length; i++) {
			if (isACorrectOSGiTopic(topic[i])) {
				if (!isAnHandledTopic(topic[i])) {
					if (!topicBlacklisted.contains(topic[i])) {
						ServiceRegistration ref = null;
						String[] topics = new String[] { topic[i] };
						Dictionary service = new Hashtable();
						// properties to "mark" the handler for the Localadmin
						// process
						service.put("org.ow2.aspirerfid.localadmin.noref", "YES");
						// properties to indicate the topics
						service.put(EventConstants.EVENT_TOPIC, topics);
						ref = bundleContext.registerService(
								"org.osgi.service.event.EventHandler", this,
								service);
						osgiTopicHandled.put(topic[i], ref);
						ref = null;
					} else {
						System.out.println("topic :" + topic[i]
								+ " is declared as an Unexportable Topic");
						System.out.println("Export Ending...");
					}
				}
			} else {
				System.out
						.println("topic :\""
								+ topic[i]
								+ "\" does not respect the OSGi Release 4 specification");
			}
		}
		updateLocalAdmin(TOPICEXPORTED);

	}

	/*
	 * @see org.ow2.aspirerfid.event.eabridge.EABridge#unexportTopics(java.lang.String[])
	 */
	public void unexportTopics(String[] topic) {
		for (int i = 0; i < osgiTopicHandled.size(); i++) {
			if (isACorrectOSGiTopic(topic[i])) {
				/* If we handle this topic */
				if (isAnHandledTopic(topic[i])) {
					/* We unregister it in the OSGi Framework */
					((ServiceRegistration) osgiTopicHandled.get(topic[i]))
							.unregister();
					/* We remove it of the OSGi Topic's list */
					osgiTopicHandled.remove(topic[i]);
					updateLocalAdmin(TOPICEXPORTED);
				}
			}

			else {
				System.out
						.println("topic :\""
								+ topic[i]
								+ "\"does not respect the OSGi Release 4 specification");
			}
		}
	}

	/*
	 * @see org.osgi.service.event.EventHandler#handleEvent(org.osgi.service.event.Event)
	 */
	public void handleEvent(Event event) {
		synchronized (osgiEventPublished) {
			if (!osgiEventPublished.contains(event)) {
				if (!topicBlacklisted.contains((String) event
						.getProperty(EventConstants.EVENT_TOPIC))) {
					Map eventSerializable = new Hashtable();
					// ++++++++++++++++++++ Creation of a Serializable
					// object with an Event Object
					// eventSerializable.put("EABridge.Event.topic",
					// event.getTopic());
					String[] eventProperties = event.getPropertyNames();
					for (int i = 0; i < eventProperties.length; i++) {
						eventSerializable.put(eventProperties[i], event
								.getProperty(eventProperties[i]));
					}

					exportEventPublished.add(new Integer(eventSerializable
							.hashCode()));
					exportEventDispatcher.publish(eventSerializable);
				}

			} else
				osgiEventPublished.remove(event);
		}
	}

	/**
	 * Method call by ExportEventHandler when a message come
	 * 
	 * @param event
	 * @param eventserializable
	 */
	protected void publishOSGiEvent(Event event, Hashtable eventserializable) {
		synchronized (osgiEventPublished) {
			// we tested if we are the sender of this message
			if (!(containsEventSerializable(eventserializable))) {
				// we tested if this event is on a topic we handled
				if (isAnHandledTopic(event.getTopic())
						&& !topicBlacklisted.contains(event.getTopic())) {
					osgiEventPublished.add(event);
					eventAdmin.postEvent(event);
				}
			} else
				exportEventPublished.remove(new Integer(eventserializable
						.hashCode()));
		}
	}

	/**
	 * This internal method is used to know if we are send this event ( because
	 * we send and listen on the same support)
	 * 
	 * @param event
	 *            a Hashtable which represent an event
	 * @return boolean
	 */
	private boolean containsEventSerializable(Hashtable event) {
		boolean test = false;
		for (int i = 0; i < exportEventPublished.size(); i++) {
			if (((Integer) exportEventPublished.get(i)).intValue() == event
					.hashCode())
				test = true;
		}
		return test;
	}

	/*
	 * @see org.ow2.aspirerfid.event.eabridge.EABridge#getExportedTopics()
	 */
	public String[] getExportedTopics() {

		String[] topicExported = (String[]) osgiTopicHandled.keySet().toArray(
				new String[osgiTopicHandled.size()]);
		return topicExported;
	}

	/*
	 * @see org.ow2.aspirerfid.event.eabridge.EABridge#exportableTopics(java.lang.String[])
	 */
	public void exportableTopics(String[] topic) {
		for (int i = 0; i < topic.length; i++) {
			if (isACorrectOSGiTopic(topic[i])) {
				if (topicBlacklisted.contains(topic[i]))
					topicBlacklisted.remove(topic[i]);
			} else {
				System.out
						.println("topic :\""
								+ topic[i]
								+ "\" does not respect the OSGi Release 4 specification");
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.event.eabridge.EABridge#unexportableTopics(java.lang.String[])
	 */
	public void unexportableTopics(String[] topic) {
		for (int i = 0; i < topic.length; i++) {
			if (isACorrectOSGiTopic(topic[i])) {
				if (!topicBlacklisted.contains(topic[i])) {
					if (isAnHandledTopic(topic[i])) {
						osgiTopicHandled.remove(topic[i]);
					}
					topicBlacklisted.add(topic[i]);
					updateLocalAdmin(TOPICBLACKLISTED);
				}// end if !topicBlacklisted.contains(topic[i])
			} else {
				System.out
						.println("topic : \""
								+ topic[i]
								+ "\" does not respect the OSGi Release 4 specification");
			}
		}// end for

	}

	/*
	 * @see org.ow2.aspirerfid.event.eabridge.EABridge#getUnexportableTopics()
	 */
	public String[] getUnexportableTopics() {
		String[] topicExported = (String[]) topicBlacklisted
				.toArray(new String[topicBlacklisted.size()]);
		return topicExported;
	}

	/**
	 * This internal method is called to verify if the topic name respect the
	 * OSGi R4 spec.
	 * 
	 * @param topic
	 *            the name of the topic
	 * @return boolean true if the name is good
	 */
	private boolean isACorrectOSGiTopic(String topic) {
		Pattern topicPattern = Pattern
				.compile("([-_a-zA-Z0-9]+(/[-_a-zA-Z0-9]+)*(/\\*)?)|\\*");
		Matcher match = topicPattern.matcher(topic);
		boolean test = match.matches();
		return test;
		// return true;
	}

	/**
	 * Method used to send an update to the LocalAdmin by EventAdmin
	 * 
	 * @param selector
	 *            indicated the type of the update ( blacklist table / exported
	 *            table )
	 */
	private void updateLocalAdmin(int selector) {
		// create the event
		Dictionary dic = new Hashtable();
		switch (selector) {
		case TOPICBLACKLISTED:
			// set Status
			dic.put("status", "topicBlacklisted");
			// Create a String Tab and Set it to contains
			dic.put("contains", topicBlacklisted
					.toArray(new String[topicBlacklisted.size()]));
			break;
		case TOPICEXPORTED:
			// set Status
			dic.put("status", "topicExported");
			// Create a String Tab and Set it to contains
			dic.put("contains", osgiTopicHandled.keySet().toArray(
					new String[osgiTopicHandled.size()]));
		default:
			break;
		}
		eventAdmin.postEvent(new Event(LOCALADMINOSGITOPIC, dic));
	}

	/**
	 * Internal method to test if we handled a topic ( method created to manage
	 * problem due to '*' )
	 * 
	 * @param topic
	 *            the topic tested
	 * @return boolean true if we handled this topic
	 */
	private boolean isAnHandledTopic(String topic) {
		boolean test = false;
		// we made an Enumeration with topics handled by the program
		Enumeration listOfTopicHandled = osgiTopicHandled.keys();
		while (listOfTopicHandled.hasMoreElements()) {
			// we take a topic on the list
			String topicHandled = (String) listOfTopicHandled.nextElement();
			// I cut my Topic Name to know each step (ex :"org/ow2/aspirerfid"
			// =>
			// "org","ow2","aspirerfid")
			StringTokenizer st = new StringTokenizer(topicHandled, "" + "/",
					false);
			int nbOfToken = st.countTokens();
			String[] topicTable = new String[nbOfToken];
			// I store this in a String tab.
			for (int i = 0; i < nbOfToken; i++) {
				topicTable[i] = st.nextToken();
			}
			// if the last topic is a wildcard('*')
			if (topicTable[nbOfToken - 1].equals("*")) {
				// we construct the topic handled
				topicHandled = topicTable[0];
				for (int i = 1; i < nbOfToken - 1; i++) {
					topicHandled = topicHandled + "/" + topicTable[i];
				}
				Pattern pattern = Pattern.compile("\\A" + topicHandled);
				Matcher matcher = pattern.matcher(topic);
				test = matcher.find();
			}

			else // this topic Handled is not type of ".../.../.../*"
			{
				// if the topic tested is the topic handled, =>True !!
				if (topicHandled.equals(topic)) {
					test = true;
				}
			}

		}
		return test;
	}

	/**
	 * TODO Javadoc
	 */
	public void start() {
		// initialization of topic Blacklisted
		topicBlacklisted.add(LOCALADMINOSGITOPIC);
		topicBlacklisted.add("*");
		updateLocalAdmin(TOPICBLACKLISTED);
	}

}// end class file
