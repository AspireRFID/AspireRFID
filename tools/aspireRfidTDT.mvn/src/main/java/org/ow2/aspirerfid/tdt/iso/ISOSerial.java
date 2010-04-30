/*
 * Copyright (C) 2008-2010, Aspire
 * 
 * Aspire is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 2.1 as published by
 * the Free Software Foundation (the "LGPL").
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library in the file COPYING-LGPL-2.1; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public License
 * for the specific language governing rights and limitations.
 */

package org.ow2.aspirerfid.tdt.iso;

/**
 * @author Loic Schmidt e-mail: loic.schmidt@lifl.fr
 *
 *	<p>This interface represents a ISO UID
 *      
 * @version Revision: 0.5 Date: 2009/11/23
 */

public interface ISOSerial {

    //-----------/
    //- Methods -/
    //-----------/
    //
    /** 
     *	Convert the isoSerial in other representation
     * 
     *  @param outputFormat format representation for output
     *  @return output ISO tag code in the new representation
     */
    public String convert(String outputFormat);




}
