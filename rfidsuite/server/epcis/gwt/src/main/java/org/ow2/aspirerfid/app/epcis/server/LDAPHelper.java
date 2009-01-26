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
package org.ow2.aspirerfid.app.epcis.server;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.naming.Binding;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.ldap.LdapContext;

import org.ow2.aspirerfid.app.epcis.client.widget.topology.ArchiElement;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 * 
 */
public class LDAPHelper {
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public static DirContext getInitialDirContext() {
        try {
            InputStream inputStream = LDAPHelper.class
                    .getResourceAsStream("/server.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            DirContext dirContex = new InitialDirContext(properties);
            return dirContex;
        } catch (Exception e) {
            System.out.println("LDAP server is unreachable.");
            return null;
        }
    }
    
    /**
     * TODO Javadoc
     * 
     * @param archi
     * @param branch
     * @param parent
     */
    public static void readBranch(List archi, LdapContext branch,
            ArchiElement parent) {
        ArchiElement newElement = null;
        
        try {
            String name = branch.getAttributes("").get("cn").toString();
            if (branch.getAttributes("").get("objectClass").get().equals(
                    "rfidPremise")) {
                newElement = new ArchiElement(parent, name,
                        ArchiElement.TYPE_PREMISE);
            }
            
            if (branch.getAttributes("").get("objectClass").get().equals(
                    "rfidEdge")) {
                newElement = new ArchiElement(parent, name,
                        ArchiElement.TYPE_EDGE);
            }
            
            if (branch.getAttributes("").get("objectClass").get().equals(
                    "rfidReaders")) {
                NamingEnumeration sub;
                sub = branch.listBindings("");
                while (sub.hasMoreElements()) {
                    Binding binding = (Binding) sub.nextElement();
                    LdapContext subReader = (LdapContext) binding.getObject();
                    newElement = new ArchiElement(parent, subReader
                            .getAttributes("").get("cn").toString(),
                            ArchiElement.TYPE_READER);
                    archi.add(newElement);
                }
                newElement = null;
                
            }
            
            if (branch.getAttributes("").get("objectClass").get().equals(
                    "rfidServer")) {
                newElement = new ArchiElement(parent, name,
                        ArchiElement.TYPE_SERVER);
            }
            
            if (newElement != null) {
                archi.add(newElement);
            } else {
                return;
            }
            
            NamingEnumeration subEnum;
            subEnum = branch.listBindings("");
            while (subEnum.hasMoreElements()) {
                Binding binding = (Binding) subEnum.nextElement();
                LdapContext subBranch = (LdapContext) binding.getObject();
                readBranch(archi, subBranch, newElement);
            }
        } catch (NamingException e) {
            System.out.println("LDAP : readBranch error");
            e.printStackTrace();
        }
    }
    
}
