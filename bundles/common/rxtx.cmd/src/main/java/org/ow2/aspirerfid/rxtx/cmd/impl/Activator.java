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

package org.ow2.aspirerfid.rxtx.cmd.impl;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.apache.felix.shell.Command;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceRegistration;

/**
 * This class is the activator of the bundle.
 * @author Didier Donsez
 */
public class Activator implements BundleActivator {
	private transient BundleContext m_context = null;
	private transient Map m_commandServiceRegistration = null;

	public void start(BundleContext context) throws BundleException {
		m_context = context;
		m_commandServiceRegistration = new HashMap();

		Dictionary properties=new Hashtable();
		properties.put("categories",
			new String[]{ "script" }
		);
		
		Command command=new RXTXCmdImpl(m_context);
		if(command instanceof BundleActivator)
			try {
				((BundleActivator) command).start(m_context);
			} catch (Exception e) {
				throw new BundleException("Can not start command \""+command.getName()+"\": ",e);
			}
		m_commandServiceRegistration.put(
			m_context.registerService(
				Command.class.getName(),
				command,
				properties),
			command
		);
	}

	public void stop(BundleContext context) throws BundleException {
		Iterator iterator=m_commandServiceRegistration.keySet().iterator();
		while(iterator.hasNext()){
			ServiceRegistration sr=(ServiceRegistration)iterator.next();
			sr.unregister();	
			Command command=(Command)m_commandServiceRegistration.remove(sr);
			if(command instanceof BundleActivator)
				try {
					((BundleActivator) command).stop(m_context);
				} catch (Exception e) {
					System.err.println("Can not stop command \""+command.getName()+"\": "+e);
				}
		}
	}
}
