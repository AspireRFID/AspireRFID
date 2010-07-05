/*
   Copyright 2005-2008, Aspire
   
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
**/
package org.ow2.aspirerfid.sensorconsumer;

import java.awt.Image;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.wireadmin.Consumer;
import org.osgi.service.wireadmin.Wire;
import org.osgi.service.wireadmin.WireConstants;
import org.osgi.util.measurement.Measurement;
import org.osgi.util.measurement.State;
import org.osgi.util.position.Position;

public class SensorConsumer implements Consumer, Runnable {

	private String m_PID;

	private long m_pollDelay;

	private static final Class[] m_flavors = new Class[] {
				Measurement.class, State.class, Image.class,
				Position.class,
				Double.class, Float.class,
				Long.class, Integer.class, Short.class,
				Boolean.class,
				double[].class, float[].class,
				long[].class, int[].class, short[].class, byte[].class,
				boolean[].class,
				String.class
			};

	private BundleContext m_bundleContext;

	/**
	 * registration of the Consumer service
	 */
	private ServiceRegistration m_serviceRegistration;

	private boolean m_end;

	private Wire[] m_wires;

	private Thread m_thread;
	
	public SensorConsumer(BundleContext bc) {
		m_bundleContext = bc;
	}

	public void activate() {
		System.out.print("ACTIVATE");
		// registration of the Consumer service
		Hashtable<String, Object> props = new Hashtable<String, Object>();
		props.put(Constants.SERVICE_PID, m_PID);
		props.put(WireConstants.WIREADMIN_CONSUMER_FLAVORS, m_flavors);
		m_serviceRegistration = m_bundleContext.registerService(new String[] { Consumer.class.getName() }, this, props);

		// start the thread
		m_end = false;
		m_thread = new Thread(this);
		m_thread.setName("SensorConsumer poll");
		m_thread.start();
	}

	public void deactivate() {
		System.out.print("DEACTIVATE");
		// stop the thread
		m_end = true;
		m_thread.interrupt();
		
		// unregistration of the consumer service
		m_serviceRegistration.unregister();
	}

	/**
	 * @see org.osgi.service.wireadmin.Consumer#updated(org.osgi.service.wireadmin.Wire, java.lang.Object)
	 */	
	public void updated(Wire wire, Object o) {

		Dictionary d = new Properties();

		String source=(String)wire.getProperties().get(WireConstants.WIREADMIN_PRODUCER_PID);
		if(source!=null) d.put("source", source);
		
		if (o instanceof Measurement) {
			Measurement m = (Measurement) o;
			d.put("value", new Double(m.getValue()));
			d.put("timestamp", new Long(m.getTime()));
		} else	if (o instanceof State) {
			State s = (State) o;
			d.put("value", new Integer(s.getValue()));
			d.put("state", s.getName());
			d.put("timestamp", new Long(s.getTime()));
		} else	if (o instanceof Position) {
			// TODO test nullity of lat,long and alt 
			Position p = (Position) o;
			Measurement m;
			Long t=null;

			m=p.getLatitude();
			if(m!=null) {
				d.put("latitude", new Double(m.getValue()));
				if(t==null) t=new Long(m.getTime());
			}

			m=p.getLongitude();
			if(m!=null) {
				d.put("longitude", new Double(m.getValue()));
				if(t==null) t=new Long(m.getTime());
			}

			m=p.getAltitude();
			if(m!=null) {
				d.put("altitude", new Double(m.getValue()));
				if(t==null) t=new Long(m.getTime());
			}

			m=p.getSpeed();
			if(m!=null) {
				d.put("speed", new Double(m.getValue()));
				if(t==null) t=new Long(m.getTime());
			}

			m=p.getTrack();
			if(m!=null) {
				d.put("track", new Double(m.getValue()));
				if(t==null) t=new Long(m.getTime());
			}

			if(t!=null) {
				d.put("timestamp", t);
			}
		} else	{
			d.put("timestamp", new Long(System.currentTimeMillis()));
			d.put("value", o);
		}

		System.out.println(toJSON(d));
	}

	private String toJSON(Dictionary d) {
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		Enumeration e=d.keys();
		while(e.hasMoreElements()) {
			String k=(String) e.nextElement();
			sb.append(k).append("=").append(d.get(k)).append(";");		
		}
		sb.append("}");
		return sb.toString();
	}
	
	public void run() {
		while (!m_end) {
			synchronized (this) {
				if (m_wires != null) {
					for (Wire wire : m_wires) {
						if (wire.isValid() && wire.isConnected()) {
							updated(wire, wire.poll());
						}
					}
				}
			}
			try {
				Thread.sleep(m_pollDelay);
			} catch (InterruptedException e) {
			}
		}
	}
	
	public void producersConnected(Wire[] wires) {
		synchronized (this) {
			m_wires = wires;
		}
	}
}
