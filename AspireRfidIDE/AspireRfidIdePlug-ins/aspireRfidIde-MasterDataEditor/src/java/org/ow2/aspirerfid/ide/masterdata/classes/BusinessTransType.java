/*
 * Copyright © 2008-2010, Aspire
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


package org.ow2.aspirerfid.ide.masterdata.classes;
/**
 * @author Eystathios Mertikas e-mail: efme@ait.edu.gr
 * 
 */
public class BusinessTransType extends EPCIS_Class{
	
	
	public BusinessTransType()
	{
		super("urn:epcglobal:epcis:vtype:BusinessTransactionType","bizTransactionTypeAttribute");
	}
	public BusinessTransType(String EPC_Tag, String Name)
	{
		super(EPC_Tag, Name,"urn:epcglobal:epcis:vtype:BusinessTransactionType","bizTransactionTypeAttribute");
	}
	
	

}
