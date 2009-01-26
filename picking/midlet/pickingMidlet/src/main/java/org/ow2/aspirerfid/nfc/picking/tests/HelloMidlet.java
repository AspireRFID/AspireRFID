package org.ow2.aspirerfid.nfc.picking.tests;

import javax.microedition.midlet.MIDletStateChangeException;

import org.ow2.aspirerfid.nfc.midlet.generic.GenericMidlet;
import org.ow2.aspirerfid.nfc.midlet.tests.HelloScreen;

/**
 * This is a Hello World example of the architecture. Each screen of the
 * application is a class that implements the screen interface.
 * 
 * @author @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 27/08/2008
 */
public class HelloMidlet extends GenericMidlet {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.microedition.midlet.MIDlet#startApp()
	 */
	protected void startApp() throws MIDletStateChangeException {
		this.setActiveScreen(new HelloScreen(this));
	}

}
