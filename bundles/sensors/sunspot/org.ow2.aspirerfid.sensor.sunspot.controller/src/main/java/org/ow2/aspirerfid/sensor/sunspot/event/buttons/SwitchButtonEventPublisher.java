package org.ow2.aspirerfid.sensor.sunspot.event.buttons;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.ow2.aspirerfid.sensor.sunspot.event.buttons.EventConstants.SwitchButton;

/**
 * @author lionel
 * This class notifies event consumers
 * when a switch button of a SunSPOT is pressed or released.
 */
public class SwitchButtonEventPublisher {
	
	private EventAdmin eventAdminService;
	
	public void fireButtonPressedEvent(SwitchButton button, String spotAddress) {
		
		Event evt = null;
		Dictionary<String, String> props = new Hashtable<String, String>();
		switch (button) {
			case SW1:
				props.put(EventConstants.spotAddressKey, spotAddress);
				evt = new Event(EventConstants.buttonSW1Topic
						+EventConstants.pressed, props);
				break;
			case SW2:
				props.put(EventConstants.spotAddressKey, spotAddress);
				evt = new Event(EventConstants.buttonSW2Topic
						+EventConstants.pressed, props);
				break;
		}
		eventAdminService.postEvent(evt);		
	}
	
	public void fireButtonReleasedEvent(SwitchButton button, String spotAddress) {
		
		Event evt = null;
		Dictionary<String, String> props = new Hashtable<String, String>();
		switch (button) {
			case SW1:
				props.put(EventConstants.spotAddressKey, spotAddress);
				evt = new Event(EventConstants.buttonSW1Topic
						+EventConstants.released, props);
				break;
			case SW2:
				props.put(EventConstants.spotAddressKey, spotAddress);
				evt = new Event(EventConstants.buttonSW2Topic
						+EventConstants.released, props);
				break;
		}
		
		eventAdminService.postEvent(evt);
		
	}
	
}
