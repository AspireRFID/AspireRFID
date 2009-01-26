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
package org.ow2.aspirerfid.epc.ale.impl;

import java.net.URI;

/**
 * This class defines a subscription.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class ECSubscription {
    /**
     * The event cycle specification name
     */
    private String ecSpecName = null;
    
    /**
     * The subscription URI
     */
    private URI uri = null;
    
    /**
     * Create a subscription
     * 
     * @param ecSpecName
     *            The event cycle specification name
     * @param uri
     *            The subscription URI
     */
    public ECSubscription(final String ecSpecName, final URI uri) {
        this.ecSpecName = ecSpecName;
        this.uri = uri;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(final Object o) {
        if (!(o instanceof ECSubscription)) {
            return false;
        }
        
        final ECSubscription subscription = (ECSubscription) o;
        final URI uri = subscription.getUri();
        
        return (this.getEcSpecName().compareTo(subscription.getEcSpecName()) == 0)
                && (this.getUri().compareTo(uri) == 0);
    }
    
    /**
     * Get the event cycle specification name
     * 
     * @return the ecSpecName
     */
    public String getEcSpecName() {
        return this.ecSpecName;
    }
    
    /**
     * Get the subscription URI
     * 
     * @return The subscription URI
     */
    public URI getUri() {
        return this.uri;
    }
    
    /**
     * Set the event cycle specification name
     * 
     * @param ecSpecName
     *            The event cycle specification name to set
     */
    public void setEcSpecName(final String ecSpecName) {
        this.ecSpecName = ecSpecName;
    }
    
    /**
     * Set the subscription URI
     * 
     * @param uri
     *            The subscription URI to set
     */
    public void setUri(final URI uri) {
        this.uri = uri;
    }
    
    public String toString() {
        return this.getEcSpecName() + "," + this.getUri();
    }
}
