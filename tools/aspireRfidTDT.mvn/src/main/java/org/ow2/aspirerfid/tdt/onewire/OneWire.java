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

package org.ow2.aspirerfid.tdt.onewire;


/**
 * @author Didier Donsez



 *	<p>This class represents a OneWire UID
 *
 
http://www.maxim-ic.com/products/ibutton/ibuttons/
The Device Address (ie GUID)  is 64 bits long : CRC (8bits) + unique Device Id (48bits) + Family ID (8bits)
Family determines the services provided by the device (0x21 for temperature loggers)


For instance
urn:ow:id:5D0000091A7C2101
urn:signage/ibutton#5D0000091A7C2101



Acoording to Maxim "
Who Is Using the iButton? With over 175 million iButtons currently in circulation, the list of users is very long. You can read about many of the applications here."

*
 */

public class OneWire  {




}
