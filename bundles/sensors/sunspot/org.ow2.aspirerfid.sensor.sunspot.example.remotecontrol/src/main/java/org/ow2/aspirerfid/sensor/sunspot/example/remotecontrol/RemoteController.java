package org.ow2.aspirerfid.sensor.sunspot.example.remotecontrol;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.osgi.framework.BundleContext;
import org.osgi.service.event.Event;
import org.osgi.service.wireadmin.Consumer;
import org.osgi.service.wireadmin.Wire;
import org.osgi.service.wireadmin.WireConstants;
import org.osgi.util.measurement.Measurement;
import org.ow2.aspirerfid.sensor.sunspot.service.SunSpotService;
import org.ow2.aspirerfid.sensor.sunspot.service.SunSpotService.LEDColor;

/**
 * @author lionel
 *
 */
public class RemoteController implements Consumer {
	
	private SunSpotService spotService;
	
	private Robot robot; 
	
	private Wire[] connectedWires;
	
	private Class[] flavors;
	
	private BundleContext context;
	
	private boolean mouseEnabled;
	
	private int sw1Action;
	
	private int sw2Action;
	
	private JFrame gui;
	
	private double accelThreshold = 0.21;
	
	private double accelSensitivity = 5;
	
	private byte sw1Mode;
	private byte sw2Mode;
	
	public final byte KEYBOARD_MODE = 0;
	public final byte MOUSE_MODE = 1;
	
	/**
	 * list of LEDs to turn in GREEN
	 */
	private int[] greenLeds = new int[] {0,2,4,6};
	
	/**
	 * list of LEDs to turn in RED
	 */
	private int[] redLeds =  new int[]{1,3,5,7};
	
	private long ledDelay = 800;
	
	public RemoteController(BundleContext ctx){
		try {
			context = ctx;
			mouseEnabled = true;
			sw1Action = KeyEvent.VK_LEFT;
			sw2Action = KeyEvent.VK_RIGHT;
			sw1Mode = KEYBOARD_MODE;
			sw2Mode = KEYBOARD_MODE;
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public void start(){
		spotService.blinkLEDs(2);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}

	public void stop(){
		gui.dispose();
	}
	
	/**
	 * iPOJO callback method to load flavors classes
	 * @param flavorClasses
	 */
	public void setFlavors (String[] flavorClasses){
		Class[] accel = new Class[flavorClasses.length];
		for (int i=0; i < flavorClasses.length; i++){
			try {
				accel[i] = context.getBundle().loadClass(flavorClasses[i]);
			} catch (ClassNotFoundException e) {
				System.err.println("Class not found: "+ flavorClasses[i]);
			}
		}
		flavors = accel;
	}
	
    private void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(false);

        //Create and set up the window.
        gui = new JFrame("SunSPOT Controller Configuration GUI");
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        gui.setSize(400, 300);
        gui.setLocation(400, 300);
        
        //Create and set up the content pane.
        JComponent newContentPane = new ConfigurationGUI(this);
        newContentPane.setOpaque(true); //content panes must be opaque
        gui.setContentPane(newContentPane);

        //Display the window.
        gui.pack();
        gui.setVisible(true);
    }
	
	/**
	 * Method that makes the used SunSPOT blink to locate it
	 */
	public void locateSpot(){
		spotService.blinkLEDs(3);
	}
	
	public void buttonStateChanged(Event evt){
		
		String topic = evt.getTopic();
		int index = topic.lastIndexOf("sw");
		String cutTopic = topic.substring(index);
		String button = cutTopic.substring(0,3);
		String action = cutTopic.substring(4);
		String spotAddress = (String) evt.getProperty("spot.address");
		
		if (spotAddress.equals(spotService.getAddress())) {
			
			if (button.equals("sw1") && action.equals("pressed")) {

				
				switch (sw1Mode){
					case KEYBOARD_MODE:
						// light a green LED for 1 sec
						spotService.setLEDs(greenLeds, LEDColor.GREEN, ledDelay);
						// and simulate a key press
						robot.keyPress(sw1Action);
						robot.delay(300);
						robot.keyRelease(sw1Action);
						break;
					case MOUSE_MODE:
						// light a green LED until switch is released
						spotService.setLEDs(greenLeds, LEDColor.GREEN);
						// or a mouse button press
						robot.mousePress(sw1Action);
						break;
				}
			}
			if (button.equals("sw2") && action.equals("pressed")){

			
				switch (sw2Mode){
					case KEYBOARD_MODE:
						// light a red LED for 1 second
						spotService.setLEDs(redLeds, LEDColor.RED, ledDelay);
						// and simulate a key press
						robot.keyPress(sw2Action);
						robot.delay(300);
						robot.keyRelease(sw2Action);
						break;
					case MOUSE_MODE:
						// light a red LED until released
						spotService.setLEDs(redLeds, LEDColor.RED);
						// or a mouse button press
						robot.mousePress(sw2Action);
						break;
				}
			}
			
			if (button.equals("sw1") && action.equals("released")) {
				
				switch (sw1Mode){
					case MOUSE_MODE:
						// switch off LEDs
						spotService.ledsOff();
						// release mouse button 
						robot.mouseRelease(sw1Action);
						break;
				}
			}
			
			if (button.equals("sw2") && action.equals("released")){			
				switch (sw2Mode){
					case MOUSE_MODE:
						// switch off LEDs
						spotService.ledsOff();
						// release mouse button
						robot.mouseRelease(sw2Action);
						break;
				}
			}
			
			
		}
		
	}

	/**
	 * @param sw1Mode the sw1Mode to set
	 */
	public void setSw1Mode(byte sw1Mode) {
		this.sw1Mode = sw1Mode;
	}

	/**
	 * @param sw2Mode the sw2Mode to set
	 */
	public void setSw2Mode(byte sw2Mode) {
		this.sw2Mode = sw2Mode;
	}

	public void producersConnected(Wire[] wires) {
		connectedWires = wires;
	}

	public void updated(Wire wire, Object o) {
		
		if (mouseEnabled) {
			
			// TODO : check if wire is connected
			String producerPID = (String)wire.getProperties().get(WireConstants.WIREADMIN_PRODUCER_PID); 
			if (producerPID.contains(spotService.getAddress())) {
				
				if (o instanceof Measurement[]) {
					Measurement[] accelerations = (Measurement[]) o;
					if (Math.abs(accelerations[0].getValue()) > accelThreshold
							|| Math.abs(accelerations[1].getValue()) > accelThreshold) {
						Point mousePosition = MouseInfo.getPointerInfo().getLocation();
						robot.mouseMove((int)Math.round(mousePosition.getX() + Math.pow(accelerations[0].getValue()*accelSensitivity,3)),
								(int)Math.round(mousePosition.getY() + Math.pow(accelerations[1].getValue()*accelSensitivity,3)));
					}
					
				}
			}
		}
		
	}

	/**
	 * @return the mouseEnabled
	 */
	public boolean isMouseEnabled() {
		return mouseEnabled;
	}

	/**
	 * @param mouseEnabled the mouseEnabled to set
	 */
	public void setMouseEnabled(boolean mouseEnabled) {
		this.mouseEnabled = mouseEnabled;
	}

	/**
	 * @return the sw1Action
	 */
	public int getSw1Action() {
		return sw1Action;
	}

	/**
	 * @param sw1Action the sw1Action to set
	 */
	public void setSw1Action(int sw1Action) {
		this.sw1Action = sw1Action;
	}

	/**
	 * @return the sw2Action
	 */
	public int getSw2Action() {
		return sw2Action;
	}

	/**
	 * @param sw2Action the sw2Action to set
	 */
	public void setSw2Action(int sw2Action) {
		this.sw2Action = sw2Action;
	}
	
	
	
}
