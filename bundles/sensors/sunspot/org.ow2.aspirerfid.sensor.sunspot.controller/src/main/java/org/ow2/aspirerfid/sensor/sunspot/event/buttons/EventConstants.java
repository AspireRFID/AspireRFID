package org.ow2.aspirerfid.sensor.sunspot.event.buttons;

public interface EventConstants {

	public enum SwitchButton {SW1, SW2};
	
	public static final String buttonSW1Topic = "org/ow2/aspirerfid/sensor/sunspot/event/buttons/sw1/";

	public static final String buttonSW2Topic = "org/ow2/aspirerfid/sensor/sunspot/event/buttons/sw2/";
	
	public static final String spotAddressKey = "spot.address";
	
	public static final String pressed = "pressed";
	public static final String released = "released";
	
}
