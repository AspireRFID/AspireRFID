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

package org.ow2.aspirerfid.ide.bpwme.dialog;

import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementType;
import org.ow2.aspirerfid.ide.bpwme.ecspec.model.*;
import org.ow2.aspirerfid.ide.bpwme.master.model.DispositionItem;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataBuilder;

/**
 * Return disposition item object
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class DispositionDialog extends Dialog{

	private Shell parent;
	private String message;
	private String uri;
	private String name;

	public DispositionDialog(Shell parent) {
		this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	}

	public DispositionDialog(Shell parent, int style) {
		super(parent, style);
		this.parent = parent;
		message = "";
		uri = "urn:epcglobal:fmcg:disp:*";
		name = "Default Name";
	}

	/**
	 * Gets the message
	 *
	 * @return String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public void setDefaultURI(String uri) {
		this.uri = uri;
	}


	/*
	 * return the user choice from the options
	 */
	public DispositionItem open() {
		Shell shell = new Shell(parent, getStyle());
		shell.setText(getText());
		createContents(shell);
		//shell.setSize(400, 200);
		shell.pack();

		shell.setLocation(getMiddlePoint(shell));
		shell.open();
		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		if((uri == null) || (name == null)) {
			return null;
		}
		
		DispositionItem di = MasterDataBuilder.getInstance().createNewDispositionItem();
		di.setName(name);
		di.setID(uri);
		
		return di;
	}

	private void createContents(final Shell shell) {
		shell.setLayout(new GridLayout(2, false));

		// Show the message
		Label label = new Label(shell, SWT.NONE);
		label.setText(message);	    
		GridData data = new GridData();
		data.horizontalSpan = 2;
		label.setLayoutData(data);

		// Display the input box
		Label uriLabel = new Label(shell, SWT.NONE);
		uriLabel.setText("URI");    

		final Text uriText = new Text(shell, SWT.BORDER);
		data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.widthHint = 150;
		uriText.setLayoutData(data);
		uriText.setText(uri);

		Label nameLabel = new Label(shell, SWT.NONE);
		nameLabel.setText("Name");

		final Text nameText = new Text(shell, SWT.BORDER);
		data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.widthHint = 150;
		nameText.setLayoutData(data);
		nameText.setText(name);

		Button ok = new Button(shell, SWT.PUSH);
		ok.setText("OK");
		data = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		data.widthHint = 50;
		ok.setLayoutData(data);
		ok.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				uri = uriText.getText();
				name = nameText.getText();
				shell.close();
			}
		});

		Button cancel = new Button(shell, SWT.PUSH);
		cancel.setText("Cancel");
		data = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		data.widthHint = 50;
		cancel.setLayoutData(data);
		cancel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				uri = null;
				name = null;
				shell.close();
			}
		});

		shell.setDefaultButton(ok);
	}

	private Point getMiddlePoint(Shell shell) {
		int x = shell.getParent().getLocation().x+(shell.getParent().getBounds().width-shell.getSize().x)/2;
		int y = shell.getParent().getLocation().y+(shell.getParent().getBounds().height-shell.getSize().y)/2;
		Point location = new Point(x,y);
		return location;
	}
}
