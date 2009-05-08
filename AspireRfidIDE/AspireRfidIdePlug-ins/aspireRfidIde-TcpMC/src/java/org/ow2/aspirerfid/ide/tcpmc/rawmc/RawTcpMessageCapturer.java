/*
 * Copyright © 2008-2010, Aspire 
 *
 * This file contains the source code of the Accada library by ETH Zurich (www.accada.org),
 * licensed under the terms of the GNU Lesser General Public License version 2.1 in 2007
 * and modified for the needs of the Aspire project.
 *
 * Aspire is free software; you can redistribute it and/or 
 * modify it under  the terms of the GNU Lesser General Public 
 * License version 2.1 as published by the Free Software Foundation (the 
 * "LGPL"). 
 *
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this library in the file COPYING-LGPL-2.1; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301 USA. 
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY 
 * OF ANY KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations. 
 */


package org.ow2.aspirerfid.ide.tcpmc.rawmc;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.eclipse.jface.preference.IPreferenceStore;
import org.ow2.aspirerfid.ide.tcpmc.Activator;
import org.ow2.aspirerfid.ide.tcpmc.preferences.PreferenceConstants;



/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@gmail.com
 *
 */
public class RawTcpMessageCapturer extends Thread{//extends JFrame implements ActionListener 
	/** Returns the preference store for this UI plug-in */
	IPreferenceStore preferences = Activator.getDefault().getPreferenceStore();
	
	private static int DEFAULT_PORT = 9999;
	
	private JTextArea inText;
	
	private Font monospaceFont;
	
	private int fontSize;
	
	private int port;
	
	
	public RawTcpMessageCapturer(int port) {
		this.port = port;
		fontSize = 12;
		start(); // Calls run()
	}

	public RawTcpMessageCapturer() {
		this(DEFAULT_PORT);
	}


	
	public void run() {
		ServerSocket ss = null;
      try {
         ss = new ServerSocket(port);
   		while(true) {
            try {
   				Socket s = ss.accept();
   				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
   
   				String data = in.readLine();
   				while(data != null) {
   					inText.append(data + "\n");
   					data = in.readLine();
   					inText.setCaretPosition(inText.getText().length());
   				}
            } catch (Exception e) {
               inText.append("\nERROR: " + e.getMessage());
               System.out.println(e.getMessage());
            }
   		}
      } catch (IOException e1) {
         inText.append("\nERROR: creating ServerSocket on Port " + port +
               " failed.");
         System.out.println(e1.getMessage());
      }
	}

}
