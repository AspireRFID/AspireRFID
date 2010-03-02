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

package org.ow2.aspirerfid.programmableengine.client;


import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.ow2.aspirerfid.commons.apdl.utils.DeserializerUtil;


import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;



/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class ProgrammableEngineClientUI {
	
	
	private String fileSeparator = System.getProperty("file.separator");
	private String userHome = System.getProperty("user.home");
	
	private Text apdlFilePathText;
	/**	logger. */
	public static final Logger LOG = Logger.getLogger(ProgrammableEngineClientUI.class);

	protected Shell shell;
	
	ProgrammableEngineClient peClient = new ProgrammableEngineClient("http://localhost:8080/aspireRfidProgrammableEngine/");

	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String[] args) {
		
		PropertyConfigurator.configure(ProgrammableEngineClientUI.class.getResource("/log4j.properties"));
		
		
		try {
			ProgrammableEngineClientUI window = new ProgrammableEngineClientUI();
			window.open();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window
	 */
	public void open() {
		final Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	/**
	 * Create contents of the window
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setLayout(new FillLayout());
		shell.setSize(610, 516);
		shell.setText("AspireRFID Programmable Engine Client");

		final ScrolledComposite peClientScrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);

		final Composite peClientComposite = new Composite(peClientScrolledComposite, SWT.NONE);
		peClientComposite.setLocation(0, 0);

		final Button encodeApdlButton = new Button(peClientComposite, SWT.NONE);
		encodeApdlButton.addMouseListener(new EncodeApdlButtonMouseListener());
		encodeApdlButton.setText("Encode APDL");
		encodeApdlButton.setBounds(148, 143, 85, 19);

		final Button openApdlFileButton = new Button(peClientComposite, SWT.NONE);
		openApdlFileButton.addMouseListener(new OpenApdlFileButtonMouseListener());
		openApdlFileButton.setText("Open APDL File");
		openApdlFileButton.setBounds(426, 79, 85, 19);

		apdlFilePathText = new Text(peClientComposite, SWT.BORDER);
		apdlFilePathText.setBounds(30, 79, 390, 19);
		peClientComposite.setSize(537, 411);
		peClientScrolledComposite.setContent(peClientComposite);
		//
	}
	private class EncodeApdlButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent arg0) {
			
			OLCBProc openLoopCBProc = new OLCBProc();
			int responceID;
			
			try {
				openLoopCBProc = DeserializerUtil.deserializeOLCBProcFile(apdlFilePathText.getText());
			}
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			

			
//			openLoopCBProc.setId("urn:epc:id:send1");
//			openLoopCBProc.setName("TheFirstOpenLoopCompositeBusinessProcess");
						
			responceID = peClient.encodeAspireRFID(openLoopCBProc);
			
			LOG.debug("******************Encode*******************");
			LOG.debug("The Responce ID is: "+responceID);
			
			LOG.debug("******************Decode*******************");
			
			peClient.decodeAspireRFID("urn:epc:id:returned1");
			
		}
	}
	private class OpenApdlFileButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent arg0) {
			
			
			FileDialog fd = new FileDialog(shell, SWT.OPEN);
	        fd.setText("Open");
//	        fd.setFilterPath("C:/");
	        String[] filterExt = { "*.xml", "*.*" };
	        fd.setFilterExtensions(filterExt);
	        String selected = fd.open();
	        apdlFilePathText.setText(selected);


			
			
		}
	}

}
