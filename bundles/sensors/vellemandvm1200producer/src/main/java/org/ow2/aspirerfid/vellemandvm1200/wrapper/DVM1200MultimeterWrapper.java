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
package org.ow2.aspirerfid.vellemandvm1200.wrapper;

import java.text.ParseException;
import java.util.StringTokenizer;

import org.ow2.aspirerfid.vellemandvm1200.data.DVM1200MultimeterAcquisition;

/**
 * format the date sent by the USB Multimeter
 * @author Julien Vey, Didier Donsez
 */
public class DVM1200MultimeterWrapper /*implements IOWrapper*/ {

    public String format(byte[] bytes) throws ParseException {
    	DVM1200MultimeterAcquisition acquisition=new DVM1200MultimeterAcquisition(bytes);
        return acquisition.toString();
    }

    public String format(byte[] bytes, int offset, int length) throws ParseException {
    	byte[] b2=new byte[length];
    	System.arraycopy(bytes, offset, b2, 0, length);
        return format(b2);
    }

    public byte[] parse(String string) throws ParseException {
        // TODO Auto-generated method stub
        return null;
    }

    public byte[] parse(StringTokenizer st) throws ParseException {
        // TODO Auto-generated method stub
        return null;
    }

}
