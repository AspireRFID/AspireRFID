package org.ow2.aspirerfid.sensor.sunspot.controller;

public interface LifecycleController {

	/**
	 * Sets the state of a ComponentInstance.
	 * Invalidate or invalidate an instance.
	 * @param valid
	 */
	public void setState(boolean valid);
	
}
