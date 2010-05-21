/*
   Copyright 2005-2010, OW2 Aspire RFID project 
   
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

   Contact: OW2 Aspire RFID project <X AT Y DOT org> (with X=aspirerfid and Y=ow2)

   LGPL version 2.1 full text http://www.gnu.org/licenses/lgpl-2.1.txt    
 */
package org.ow2.aspirerfid.ale.server.readers.rp.proximarf;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * 
 * @author Gabriel Pedraza Ferreira
 *
 */
public interface ProximaRFLibrary extends Library {

	ProximaRFLibrary INSTANCE = (ProximaRFLibrary) Native.loadLibrary("RFID_Core", ProximaRFLibrary.class);

	int s_create();

	void s_close(int handler);

	int s_getch(int handler);

	void s_putch(int handler, char ch);

	void s_write(int handler, String data, int len);

	void s_write_string(int handler, String str);

	int s_getlength(int handler);

	int s_seekp(int handler, int pos);

	int s_tellp(int handler);

	String s_get_string(int handler);

	int RFID(int request_handler);
	
}
