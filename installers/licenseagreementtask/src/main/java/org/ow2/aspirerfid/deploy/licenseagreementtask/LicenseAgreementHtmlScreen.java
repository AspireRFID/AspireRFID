/*
   Copyright 2005-2008, OW2 Aspire RFID project 
   
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
package org.ow2.aspirerfid.deploy.licenseagreementtask;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

/**
 * This class displays a license agreement screen
 * @author Didier Donsez
 */

public class LicenseAgreementHtmlScreen extends JWindow implements HyperlinkListener {
    private JEditorPane documentViewer;
    private JProgressBar progressBar;
	
    private int total;
    private static final int MIN = 0;
    private static final int MAX = 200;

    public LicenseAgreementHtmlScreen(URL url, boolean requireAgreement, final AgreementItf licenseAgreement) throws IOException {
    	
        JPanel panel1 = (JPanel) getContentPane();

        documentViewer =new JEditorPane(url);
        documentViewer.setEditable (false);
        documentViewer.addHyperlinkListener (this);
        documentViewer.setBorder(BorderFactory.createEtchedBorder());

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());

        panel2.add(documentViewer, BorderLayout.NORTH);
        
        if(! requireAgreement){
	        progressBar = new JProgressBar(MIN, MAX);
	        progressBar.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
	        panel2.add(progressBar, BorderLayout.SOUTH);
        } else {
        	
        	JButton agree=new JButton("I agree");
        	agree.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent actionevent) {
					licenseAgreement.agree();
				}
        	});
        	
        	JButton dontagree=new JButton("I do not agree");
        	dontagree.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent actionevent) {
					licenseAgreement.dontagree();
				}
        	});
        	
            JPanel panel3 = new JPanel();
            panel3.setLayout(new FlowLayout());
        	panel3.add(agree);
        	panel3.add(dontagree);
	        panel2.add(panel3, BorderLayout.SOUTH);
        }
       
        panel1.setLayout(new BorderLayout());
        panel1.add(panel2, BorderLayout.SOUTH);

        panel1.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        pack();

        // center on the user screen
        Dimension size = getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - size.width) / 2;
        int y = (screenSize.height - size.height) / 2;
        setBounds(x, y, size.width, size.height);
    }
    
    public void hyperlinkUpdate (HyperlinkEvent event) {
      if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED){
        if (event instanceof HTMLFrameHyperlinkEvent) {
	          HTMLDocument doc = (HTMLDocument)documentViewer.getDocument ();
	          doc.processHTMLFrameHyperlinkEvent ((HTMLFrameHyperlinkEvent)event);
        } else {
	    	try {
				documentViewer.setPage(event.getURL());
			} catch (IOException e) {
				e.printStackTrace(); // TODO should be logged by the task
			}
        }
      }
    }

    public void actionPerformed(ActionEvent a) {
        if (total < MAX) {
            total++;
        } else {
            total = MIN;
        }
        progressBar.setValue(total);
    }    
}

