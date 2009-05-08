/*
 * Copyright (c) 2008-2010, Aspire 
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


package org.ow2.aspirerfid.demotools.tcpcapture;

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

/**
 * @author nkef@ait.edu.gr
 *
 */
public class TcpMessageCapturer extends JFrame implements ActionListener {
	
	private static int DEFAULT_PORT = 9999;
	
	private JTextArea inText;
	
	private Font monospaceFont;
	
	private int fontSize;
	
	private int port;
	
	
	public TcpMessageCapturer(int port) {
		super("TCP Message Capturer");
		this.port = port;
		fontSize = 12;
		buildUI();
	}

	public TcpMessageCapturer() {
		this(DEFAULT_PORT);
	}

	private void buildUI() {
		this.setContentPane(getInputPanel());
		
        final JPopupMenu popmen = new JPopupMenu();

        JMenuItem menu1 = new JMenuItem( "Clear content");
        menu1.setActionCommand("Clear content");
        menu1.addActionListener(this);
        JMenuItem menu2 = new JMenuItem("Increase font");
        menu2.setActionCommand("Increase font");
        menu2.addActionListener(this);
        JMenuItem menu3 = new JMenuItem("Decrease font");
        menu3.setActionCommand("Decrease font");
        menu3.addActionListener(this);
        popmen.add(menu1);
        popmen.addSeparator();
        popmen.add(menu2);
        popmen.add(menu3);
        
        inText.add(popmen);
        inText.addMouseListener( new MouseAdapter() {
            public void mouseReleased( MouseEvent me ) {
                if ( me.isPopupTrigger() )
                  popmen.show( me.getComponent(), me.getX(), me.getY() );
              }
            } );
        
	}
	
	private JPanel getInputPanel() {
		JPanel inPane = new JPanel();
		inPane.setLayout(new GridBagLayout());

        inText = new JTextArea(5, 20);
        inText.setLineWrap(true);
        setFontSize(fontSize);
        JScrollPane scrollPane = new JScrollPane(inText,
                                       JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                       JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        inPane.add(scrollPane, c);
        inPane.setBorder(
                BorderFactory.createCompoundBorder(
                    BorderFactory.createCompoundBorder(
                                    BorderFactory.createTitledBorder("Waiting for notifications on port " + port + "..."),
                                    BorderFactory.createEmptyBorder(5,5,5,5)),
                    inPane.getBorder()));
        
        
        return inPane;
	}
	
	private void setFontSize(int size) {
		monospaceFont = new Font( "Monospaced", Font.PLAIN, size );
		inText.setFont(monospaceFont);
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
	
	//---------- EVENT HANDLERS -------------
	public void actionPerformed( ActionEvent e )
	{
		if (e.getActionCommand().equals("Clear content")) {
			inText.setText("");
		} else if (e.getActionCommand().equals("Increase font")) {
			if (fontSize < 50) {
				fontSize += 5;
				setFontSize(fontSize);
			}
		} else if (e.getActionCommand().equals("Decrease font")) {
			if (fontSize > 7) {
				fontSize -= 5;
				setFontSize(fontSize);
			}
			
		}
	}
	/**
	 * starts the TCP GUI Testclient.
	 * 
	 * @param args the first command line parameter is the TCP port. if omitted port 9999 is used.
	 */
	public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        TcpMessageCapturer client;
        int port;
        // check if args[0] is tcp-port
        if (args.length == 1){
        	port = Integer.parseInt(args[0]);
            client = new TcpMessageCapturer(port);
        } else
        	client = new TcpMessageCapturer();
        
        // Set up the window.
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Display the window.
        client.pack();
        client.setSize(new Dimension(500,730));
        client.setVisible(true);
        client.run();

	}

}
