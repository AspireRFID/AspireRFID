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

package org.ow2.aspirerfid.programmableengine.encode;


import javax.jws.WebService;

import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammableEngineEncoderInterface;
import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;



/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
@WebService(endpointInterface = "org.ow2.aspirerfid.commons.pe.interfaces.ProgrammableEngineEncoderInterface")                      
public class ProgrammableEngineEncoderInterfaceImpl implements ProgrammableEngineEncoderInterface {




	
    public Integer encode(OLCBProc openLoopCBProc) { 
    	
    	EncodeImplementation encodeImplementation = new EncodeImplementation(openLoopCBProc);
    	
    	return encodeImplementation.getReplyID();
    }

}