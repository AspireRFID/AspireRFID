/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.ons.webservice.util;

import javax.naming.NamingException;

import org.ow2.aspirerfid.ons.webservice.beans.util.ServerTypeResolver;

/**
 * Utility class for getting JNDI reference depending on the application server
 * type.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class JNDIReferenceResolver {

	/**
	 * Returns the JNDI remote reference to the specified Session Bean
	 * 
	 * @param interfaceClass
	 *            Remote Bean interface
	 * @param beanClass
	 *            Remote Bean implementation
	 * @return The JNDI remote reference to the specified Session Bean
	 * @throws NamingException
	 *             Thrown if the initial context factory is not supported
	 */
	public static String getRemoteJNDIReference(Class interfaceClass,
			Class beanClass) throws NamingException {

		if (ServerTypeResolver.serverType() == ServerTypeResolver.JBOSS) {
			return "ons/" + beanClass.getSimpleName() + "/remote";
		} else if (ServerTypeResolver.serverType() == ServerTypeResolver.JONAS) {
			return beanClass.getName() + "_" + interfaceClass.getName()
					+ "@Remote";
		} else {
			throw new NamingException("Application server type not supported");
		}
	}

	/**
	 * Returns the JNDI local reference to the specified Session Bean
	 * 
	 * @param interfaceClass
	 *            Remote Bean interface
	 * @param beanClass
	 *            Remote Bean implementation
	 * @return The JNDI remote reference to the specified Session Bean
	 * @throws NamingException
	 *             Thrown if the initial context factory is not supported
	 */
	public static String getLocalJNDIReference(Class interfaceClass,
			Class beanClass) throws NamingException {
		if (ServerTypeResolver.serverType() == ServerTypeResolver.JBOSS) {
			return "ons/" + beanClass.getSimpleName() + "/local";
		} else if (ServerTypeResolver.serverType() == ServerTypeResolver.JONAS) {
			return beanClass.getName() + "_" + interfaceClass.getName()
					+ "@Local";
		} else {
			throw new NamingException("Application server type not supported");
		}
	}
}
