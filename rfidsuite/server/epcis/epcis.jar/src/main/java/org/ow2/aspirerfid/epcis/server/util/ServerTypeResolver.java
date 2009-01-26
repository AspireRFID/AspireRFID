/*
 * Copyright 2005-2008, Aspire This library is free software; you can
 * redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation (the "LGPL");
 * either version 2.1 of the License, or (at your option) any later version. If
 * you do not alter this notice, a recipient may use your version of this file
 * under either the LGPL version 2.1, or (at his option) any later version. You
 * should have received a copy of the GNU Lesser General Public License along
 * with this library; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA. This software is
 * distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express
 * or implied. See the GNU Lesser General Public License for the specific
 * language governing rights and limitations.
 */
package org.ow2.aspirerfid.epcis.server.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Utility class for getting the application server type depending on the
 * initial context factory property.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class ServerTypeResolver {
    /**
     * JBoss initial context factory
     */
    private static final String JBOSS_INITIAL_CONTEXT_FACTORY = "org.jnp.interfaces.NamingContextFactory";
    
    /**
     * JOnAS initial context factory
     */
    private static final String JONAS_INITIAL_CONTEXT_FACTORY = "org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory";
    
    /**
     * JOnAS application server
     */
    public final static int JONAS = 1;
    
    /**
     * JBoss application server
     */
    public final static int JBOSS = 2;
    
    /**
     * Returns the application server type
     * 
     * @return The application server type
     * @throws NamingException
     *             Thrown if the initial context factory is not supported
     */
    public static int serverType() throws NamingException {
        Context context = new InitialContext();
        Hashtable env = context.getEnvironment();
        String initialContextFactory = (String) env
                .get(Context.INITIAL_CONTEXT_FACTORY);
        
        if (initialContextFactory.equals(JBOSS_INITIAL_CONTEXT_FACTORY)) {
            return JBOSS;
        } else if (initialContextFactory.equals(JONAS_INITIAL_CONTEXT_FACTORY)) {
            return JONAS;
        } else {
            throw new NamingException("Application server type not supported");
        }
    }
}
