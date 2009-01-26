package org.ow2.aspirerfid.nfc.picking.server;

import java.util.Hashtable;
import java.util.List;

import org.osgi.service.event.Event;

/**
 * Utility class useful for the propagation of events.
 * 
 * @author kiev
 *
 */
public class PickingEventAdapter {
	/**
	 * Topic for event publication
	 */
	public static final String TOPIC = "org/aspire/nfc/picking";
	public static final String TAGS_PROPERTY = "tags";
	private Event event;
	
	public PickingEventAdapter(Event event) {
		this.event = event;
	}
	
	public PickingEventAdapter(List<String> readTags) {
		Hashtable data = new Hashtable();
		data.put(TAGS_PROPERTY, readTags);
		this.event = new Event(TOPIC,data);
	}
	
	/**
	 * Extracts the information from the event
	 */
	public List<String> getTags() {
		if (!TOPIC.equals(event.getTopic())) {
			throw new RuntimeException("Topic does not match " + TOPIC);
		}
		return (List<String>)event.getProperty(TAGS_PROPERTY);
	}

	public Event getEvent() {
		return event;
	}
}
