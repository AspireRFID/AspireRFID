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
package org.ow2.aspirerfid.onewireapi.cmd.impl;

import java.io.PrintStream;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

import org.apache.felix.shell.Command;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceRegistration;

import com.dalsemi.onewire.OneWireAccessProvider;
import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;
import com.dalsemi.onewire.application.monitor.DeviceMonitor;
import com.dalsemi.onewire.container.OneWireContainer;
import com.dalsemi.onewire.container.TemperatureContainer;

/**
 * This class creates a shell command
 * 
 * @author Didier Donsez
 */
public class OneWireCmdImpl implements Command, BundleActivator {

	public String getName() {
		return "ow";
	}

	public String getUsage() {
		return "ow help";
	}

	public String getShortDescription() {
		return "inspect onewire devices";
	}

	private void printUsage(PrintStream out) {
		out
				.println(getName()
						+ " prop                  : list the properties\n"
						+ getName()
						+ " adapters              : list the available adapters\n"
						+ getName()
						+ " list                  : list the available devices on the default adapter\n"
						+ getName()
						+ " list <name> <port>    : list the available devices on the adapter\n"
						+ getName()
						+ " listAll               : list the available devices on all adapters\n"
						+ getName()
						+ " monitor <name> <port> : start a monitor on the adapter\n"
						+ getName()
						+ " stopMonitor           : stop the current monitor on the adapter\n"
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
			listDevicesFromDefaultAdapter(commandLine, out, err);
		} else if (option.equals("listAll")) {
			listDevicesFromAllAdapters(null, out, err);
		} else if (option.equals("adapters")) {
			listAdapters(null, out, err);
		} else if (option.equals("monitor")) {
			startMonitor(null, out, err);
		} else if (option.equals("stopMonitor")) {
			stopMonitor(null, out, err);
		} else if (option.equals("prop")) {
			listFrameworkProperties(null, out, err);
		} else {
			printUsage(out);
		}
	}

	private void listFrameworkProperties(String commandLine, PrintStream out,
			PrintStream err) {
		out.println("List of framework properties");
		out.println("Version: " + OneWireAccessProvider.getVersion());

	}

	private void listAdapters(String commandLine, PrintStream out,
			PrintStream err) {
		Enumeration enumAdapters = OneWireAccessProvider.enumerateAllAdapters();
		while (enumAdapters.hasMoreElements()) {
			DSPortAdapter adapter = (DSPortAdapter) enumAdapters.nextElement();
			try {
				out.println("Adapter: " + adapter.getAdapterName() + " Port: "
						+ adapter.getPortName());
			} catch (OneWireException e) {
				e.printStackTrace(err);
			}
		}
	}

	private void listDevice(DSPortAdapter adapter, PrintStream out,
			PrintStream err) {

		try {
			out.println("Adapter: " + adapter.getAdapterName() + " Port: "
					+ adapter.getPortName());

			// get exclusive use of adapter
			adapter.beginExclusive(true);

			// clear any previous search restrictions
			adapter.setSearchAllDevices();
			adapter.targetAllFamilies();
			adapter.setSpeed(adapter.SPEED_REGULAR);

			// enumerate through all the 1-Wire devices found
			Enumeration enumDevices = adapter.getAllDeviceContainers();
			while (enumDevices.hasMoreElements()) {
				OneWireContainer owd = (OneWireContainer) enumDevices
						.nextElement();
				out.println(owd.getAddressAsString() + ": " + owd.getName()
						+ " (" + owd.getAlternateNames() + ") "
						+ owd.getDescription());
			}

			// end exclusive use of adapter
			adapter.endExclusive();

			// free port used by adapter
			adapter.freePort();
		} catch (OneWireException e) {
			err.println(e);
		}
	}

	private void listDevicesFromAllAdapters(String commandLine,
			PrintStream out, PrintStream err) {

		Enumeration enumAdapters = OneWireAccessProvider.enumerateAllAdapters();
		while (enumAdapters.hasMoreElements()) {
			DSPortAdapter adapter = (DSPortAdapter) enumAdapters.nextElement();
			listDevice(adapter, out, err);
		}
	}

	private void listDevicesFromDefaultAdapter(String commandLine, PrintStream out, PrintStream err) {

		StringTokenizer st = new StringTokenizer(commandLine, " ");

		try {
			// Ignore the command name.
			st.nextToken();
			// Ignore the option.
			st.nextToken();

			if (st.countTokens() == 0) {
				DSPortAdapter adapter = (DSPortAdapter) OneWireAccessProvider
						.getDefaultAdapter();
				listDevice(adapter, out, err);
			} else if (st.countTokens() == 2) {
				DSPortAdapter adapter = (DSPortAdapter) OneWireAccessProvider
						.getAdapter(st.nextToken(), st.nextToken());
				listDevice(adapter, out, err);
			} else {
				printUsage(err);
				return;
			}
		} catch (OneWireIOException e) {
			e.printStackTrace(err);
			return;
		} catch (OneWireException e) {
			e.printStackTrace(err);
			return;
		}
	}

	private void displayDevice(String commandLine, PrintStream out, PrintStream err) {

		StringTokenizer st = new StringTokenizer(commandLine, " ");

		try {
			// Ignore the command name.
			st.nextToken();
			// Ignore the option.
			st.nextToken();

			DSPortAdapter adapter=null;
			if (st.countTokens() == 1) {
				adapter = (DSPortAdapter) OneWireAccessProvider.getDefaultAdapter();
			} else if (st.countTokens() == 3) {
				adapter = (DSPortAdapter) OneWireAccessProvider.getAdapter(st.nextToken(), st.nextToken());
			} else {
				printUsage(err);
				return;
			}
		} catch (OneWireIOException e) {
			e.printStackTrace(err);
			return;
		} catch (OneWireException e) {
			e.printStackTrace(err);
			return;
		}
		
//        OneWireContainer owc = access.getDeviceContainer();
//		displayDevice(owc, out, err);

	}

	
	private DeviceMonitor dm;

	private void stopMonitor(String commandLine, PrintStream out, PrintStream err) {
		if(dm!=null) {
			dm.killMonitor();
			dm=null;
		}
	}

	private void startMonitor(String commandLine, PrintStream out, PrintStream err) {
		stopMonitor( commandLine,  out, err);
		
		DSPortAdapter adapter=null;

		try {
			StringTokenizer st=null;
			if(commandLine!=null) {
				st = new StringTokenizer(commandLine, " ");
				// Ignore the command name.
				st.nextToken();
				// Ignore the option.
				st.nextToken();
			}
			if (st==null || st.countTokens() == 0) {
				adapter = (DSPortAdapter) OneWireAccessProvider.getDefaultAdapter();
			} else if (st.countTokens() == 2) {
				adapter = (DSPortAdapter) OneWireAccessProvider.getAdapter(st.nextToken(), st.nextToken());
			} else {
				printUsage(err);
				return;
			}
		} catch (OneWireIOException e) {
			e.printStackTrace(err);
			return;
		} catch (OneWireException e) {
			e.printStackTrace(err);
			return;
		}
		try {
		
		// clear any previous search restrictions
		adapter.setSearchAllDevices();
		adapter.targetAllFamilies();
		adapter.setSpeed(DSPortAdapter.SPEED_REGULAR);
		// SPEED_OVERDRIVE, SPEED_HYPERDRIVE, SPEED_FLEX

				
			// create a network monitor
			dm = new DeviceMonitor(adapter);
	
			
			OneWireTrace oneWireTrace=new OneWireTrace(bundleContext,out);
	
			// add this to the event listers
			dm.addDeviceMonitorEventListener(oneWireTrace);
	
			// start the monitor
			Thread thread=new Thread(dm);
			thread.setName(DeviceMonitor.class.getName());
			thread.start();
			
		} catch (OneWireIOException e) {
			e.printStackTrace(err);
			return;
		} catch (OneWireException e) {
			e.printStackTrace(err);
			return;
		}

	}
	
	private void displayTemperatureContainer(TemperatureContainer tc, PrintStream out, PrintStream err) throws OneWireException, OneWireException {

       double  max       = tc.getMaxTemperature();
       double  min       = tc.getMinTemperature();
       boolean hasAlarms = tc.hasTemperatureAlarms();

       out.println("= This device " + (hasAlarms ? "has"
                                                        : "does not have") + " alarms");
       out.println("= Maximum temperature: " + max);
       out.println("= Minimum temperature: " + min);

       double high  = 0.0;
       double low   = 0.0;
       byte[] state = tc.readDevice();

       if (hasAlarms)
       {
          high = tc.getTemperatureAlarm(tc.ALARM_HIGH, state);
          low  = tc.getTemperatureAlarm(tc.ALARM_LOW, state);

          out.println("= High temperature alarm set to : "
                             + high);
          out.println("= Low temperature alarm set to  : " + low);
       }

       double  resol      = 0.0;
       boolean selectable = tc.hasSelectableTemperatureResolution();

       if (hasAlarms)
       {
          resol = tc.getTemperatureAlarmResolution();

          out.println("= Temperature alarm resolution  : "
                             + resol);
       }

       double   tempres    = tc.getTemperatureResolution(state);
       double[] resolution = null;

       out.println("= Temperature resolution        : "
                          + tempres);
       out.println("= Resolution is selectable      : "
                          + selectable);

       if (selectable)
          try
          {
             resolution = tc.getTemperatureResolutions();

             for (int i = 0; i < resolution.length; i++)
                out.println("= Available resolution " + i
                                   + "        : " + resolution [i]);
          }
          catch (Exception e)
          {
             out.println(
                "= Could not get available resolutions : "
                + e.toString());
          }

       if (hasAlarms)
       {
          out.println(
             "= Setting high temperature alarm to 28.0 C...");
          tc.setTemperatureAlarm(tc.ALARM_HIGH, 28.0, state);
          out.println(
             "= Setting low temperature alarm to 23.0 C...");
          tc.setTemperatureAlarm(tc.ALARM_LOW, 23.0, state);
       }

       if (selectable)
          try
          {
             out.println("= Setting temperature resolution to "
                                + resolution [0] + "...");
             tc.setTemperatureResolution(resolution [0], state);
          }
          catch (Exception e)
          {
             out.println("= Could not set resolution: "
                                + e.toString());
          }

       try
       {
          tc.writeDevice(state);
          out.println("= Device state written.");
       }
       catch (Exception e)
       {
          out.println(
             "= Could not write device state, all changes lost.");
          out.println("= Exception occurred: " + e.toString());
       }

       out.println("= Doing temperature conversion...");

       try
       {
          tc.doTemperatureConvert(state);
       }
       catch (Exception e)
       {
          out.println(
             "= Could not complete temperature conversion...");
       }

       state = tc.readDevice();

       if (hasAlarms)
       {
          high = tc.getTemperatureAlarm(tc.ALARM_HIGH, state);
          low  = tc.getTemperatureAlarm(tc.ALARM_LOW, state);

          out.println("= High temperature alarm set to : "
                             + high);
          out.println("= Low temperature alarm set to  : " + low);
       }

       double temp = tc.getTemperature(state);

       out.println("= Reported temperature: " + temp);
    }
	
	/**
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */

	private transient BundleContext bundleContext;

	private transient ServiceRegistration serviceRegistration;

	public void start(BundleContext context) throws BundleException {
		bundleContext = context;
		Dictionary properties = new Hashtable();
		properties.put("categories", new String[] { "script" });
		serviceRegistration = bundleContext.registerService(Command.class
				.getName(), this, properties);
	}

	/**
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws BundleException {
		stopMonitor(null, null, null);
		serviceRegistration.unregister();
	}

}