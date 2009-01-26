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
package org.ow2.aspirerfid.epcis.server;

import java.util.Date;

/**
 * This Remote Bean allows an external EPC IS to request Tag information through
 * a web service.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public interface TagInformationRemote {
    /**
     * Provides tag information from a specific tag. The result is a <b>JSON
     * String</>. The data returned contains the tag name, the gateway name,
     * the GPS coordinates, the temperature and the date of the recorded tag.
     * 
     * @param tag
     *            A specific tag
     * @return Tag information
     */
    public String tagHistory(String tag);
    
    /**
     * Provides tag information from a specific tag. The result is a <b>JSON
     * String</>. The data returned contains the creation date and the
     * description.
     * 
     * @param tag
     *            A specific tag
     * @return Tag information
     */
    public String generalTagInformation(String tag);
    
    /**
     * Add information for a tag.
     * 
     * @param tag
     * @param creationDate
     * @param description
     */
    public void addTagInformation(String tag, Date creationDate,
            String description);
    
    /**
     * Remove information for a tag.
     * 
     * @param tag
     */
    public void removeTagInformation(String tag);
}
