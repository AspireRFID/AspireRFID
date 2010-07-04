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

package org.ow2.aspirerfid.vellemandvm1200.producer;

import org.osgi.util.measurement.Unit;

/**
 * 
 * @author Didier Donsez
 *
 */
public class UnitUtil {
	
	public static Unit getUnit(String unitStr) {
		if(unitStr==null){
			return Unit.unity;
		} else if("C".equals(unitStr)){ // different of Collomb
			return Unit.K;
		} else if("V".equals(unitStr)){
			return Unit.V;	
		} else if("A".equals(unitStr)){
			return Unit.A;	
		} else if("F".equals(unitStr)){
			return Unit.F;	
		} else if("Hz".equals(unitStr)){
			return Unit.Hz;	
		} else if("Ohm".equals(unitStr)){
			return Unit.Ohm;	
		} else if("S".equals(unitStr)){
			return Unit.S;	
		} else if("cd".equals(unitStr)){
			return Unit.cd;	
		} else if("lx".equals(unitStr)){
			return Unit.lx;	
		} else if("T".equals(unitStr)){
			return Unit.T;	
		} else return null;
	}
	
	public static double convertValue(double value, String unitStr) {
		if("C".equals(unitStr)){ // convert to Kelvin
			return value+273.15;
		} else return value;
	}

	public static double convertError(double error, String unitStr) {
		return error;
	}
		
}
