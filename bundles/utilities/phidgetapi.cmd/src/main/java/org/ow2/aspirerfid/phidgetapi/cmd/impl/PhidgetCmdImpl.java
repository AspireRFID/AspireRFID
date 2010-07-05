/*
   Copyright 2005-2008, OW2 Aspire RFID project 
   
   This library is free software; you can redistribute it and/or modify it 
   under the terms of the GNU Lesser General Public License as published by 
   the Free Software Foundation (the "LGPL"); either version 2.1 of the 
   License, or (at your option) any later version. If you do not alter this 
   notice, a recipient may use your version of this file under either the 
   LGPL version 2.1, or (at his option) any later version.
   
   You should have received a copy of the GNU Lesser General Public License 
   along with this library; if not, write to the Free Software Foundation, 
   Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
   
   This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
   KIND, either express or implied. See the GNU Lesser General Public 
   License for the specific language governing rights and limitations.

   Contact: OW2 Aspire RFID project <X AT Y DOT org> (with X=aspirerfid and Y=ow2)

   LGPL version 2.1 full text http://www.gnu.org/licenses/lgpl-2.1.txt    
 */
package org.ow2.aspirerfid.phidgetapi.cmd.impl;

import java.io.PrintStream;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.felix.shell.Command;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.phidgets.Manager;
import com.phidgets.Phidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;

/**
 * This class creates a shell command
 * 
 * @author Didier Donsez
 */
public class PhidgetCmdImpl implements Command, BundleActivator {

	public String getName() {
		return "phidget";
	}

	public String getUsage() {
		return "phidget help";
	}

	public String getShortDescription() {
		return "inspect phidgets";
	}

	private void printUsage(PrintStream out) {
		out.println(getName()
						+ " prop                  : list the properties\n"
						+ getName()
						+ " list                  : list the available phidgets\n"
						+ getName()
						+ " listen                : start attach/detach listening\n"
						+ getName()
						+ " stopListen            : stop attach/detach listening\n"
						+ getName()
						+ " help                  : display this help\n");
		return;
	}

	public void execute(String commandLine, PrintStream out, PrintStream err) {

		StringTokenizer st = new StringTokenizer(commandLine, " ");

		// Ignore the command name.
		st.nextToken();
		if (st.countTokens() < 1) {
			printUsage(err);
			return;
		}

		String option = st.nextToken();

		if (option.equals("list")) {
			listPhidgets(commandLine, out, err);
		} else if (option.equals("listen")) {
			startListen(null, out, err);
		} else if (option.equals("stopListen")) {
			stopListen(null, out, err);
		} else if (option.equals("prop")) {
			listFrameworkProperties(null, out, err);
		} else {
			printUsage(out);
		}
	}

	private void listFrameworkProperties(String commandLine, PrintStream out,
			PrintStream err) {
		out.println("List of framework properties");
		out.println("Version: " + Phidget.getLibraryVersion());

	}


	private void listPhidgets(String commandLine, PrintStream out, PrintStream err) {
		Vector phidgets=manager.getPhidgets();
		Enumeration e=phidgets.elements();
		int cpt=0;
		while(e.hasMoreElements()){
			Phidget phidget=(Phidget)e.nextElement();
			try {
				out.println(PhidgetUtil.toString(phidget));
			} catch (PhidgetException e1) {
				err.println(e1.getMessage()+"with"+phidget);
			}
			cpt++;
		}
		out.println(cpt+" phidget(s)");
	}

	private Manager manager;

	AttachListener al;
	DetachListener dl;
	
	private void startListen(final String commandLine, final PrintStream out, final PrintStream err) {
		stopListen( commandLine,  out, err);
		
		al=new AttachListener() {
			public void attached(AttachEvent ae) {
				out.println("attachment of " + ae.getSource());
			}
		};
		
		manager.addAttachListener(al);
		
		
		dl=new DetachListener() {
			public void detached(DetachEvent de) {
				out.println("detachment of " + de.getSource());
			}
		};
		manager.addDetachListener(dl);
		
	}
	
	private void stopListen(String commandLine, PrintStream out, PrintStream err) {
		if(al!=null) { manager.removeAttachListener(al); }
		if(dl!=null) {manager.removeDetachListener(dl); }
	}
	
	/**
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */

	private transient BundleContext bundleContext;

	private transient ServiceRegistration serviceRegistration;

	public void start(BundleContext context) throws Exception {
		bundleContext = context;
		manager = new Manager();
		manager.open();
		Dictionary properties = new Hashtable();
		properties.put("categories", new String[] { "shell" });
		serviceRegistration = bundleContext.registerService(Command.class.getName(), this, properties);
	}

	/**
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		stopListen(null, null, null);
		manager.close();
		serviceRegistration.unregister();
	}
	
	
	
	
}