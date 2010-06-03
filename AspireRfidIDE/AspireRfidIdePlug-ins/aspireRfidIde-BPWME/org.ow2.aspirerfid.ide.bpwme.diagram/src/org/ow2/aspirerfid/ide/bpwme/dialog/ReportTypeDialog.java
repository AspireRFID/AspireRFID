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
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl.EventType;


/**
 * Dialog for setting the event type, should be triggered when one ebproc is created
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */

public class ReportTypeDialog extends Dialog{

	private Shell parent;
	private Combo chooseBox;
	private String choice;
	private String[] options;
	private String message;
	//	private String input;

	public ReportTypeDialog(Shell parent) {
		this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	}

	public ReportTypeDialog(Shell parent, int style) {
		super(parent, style);
		this.parent = parent;
		options = new String[]{"Object Event","Aggregation Event","Quantity Event","Transaction Event"};
		message = "";
		//	    input = "";
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

	/*
	 * return the user choice from the options
	 */
	public EventType open() {
		Shell shell = new Shell(parent, getStyle());
		shell.setText(getText());
		createContents(shell);
		shell.pack();
		//shell.setLocation(400, 400);
		shell.setLocation(getMiddlePoint(shell));
		shell.open();
		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		if((choice == null) | (choice.equals(""))) {
			return null;
		} else if(choice.equals("Object Event")) {
			return EventType.OBJECT_EVENT;
		} else if(choice.equals("Aggregation Event")) {
			return EventType.AGGREGATION_EVENT;
		} else if(choice.equals("Quantity Event")) {
			return EventType.QUANTITY_EVENT;
		} else if(choice.equals("Transaction Event")) {
			return EventType.TRANSACTION_EVENT;
		}
		return null;
	}

	private void createContents(final Shell shell) {
		shell.setLayout(new GridLayout(2, true));
		//shell.setLayout(new RowLayout());
		final Button b[] = new Button[4];
		b[0] = new Button(shell, SWT.RADIO);
		b[0].setText("Object Event");

		b[1] = new Button(shell, SWT.RADIO);
		b[1].setText("Aggregation Event");

		b[2] = new Button(shell, SWT.RADIO);
		b[2].setText("Quantity Event");

		b[3] = new Button(shell, SWT.RADIO);
		b[3].setText("Transaction Event");

		b[0].setSelection(true);

		// Show the message
		//	    Label label = new Label(shell, SWT.NONE);
		//	    label.setText(message);	    
		//	    GridData data = new GridData();
		//	    data.horizontalSpan = 2;
		//	    label.setLayoutData(data);
		//		
		//	    // Display the input box
		////	    final Text text = new Text(shell, SWT.BORDER);
		////	    data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		////	    //data.horizontalSpan = 2;
		////	    text.setLayoutData(data);
		////	    text.setText(input);
		//	    
		//	    
		//		chooseBox = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		//		chooseBox.setItems(options);
		//	    //chooseBox.setSize(200, 100);
		//		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		//	    data.horizontalSpan = 2;
		//	    chooseBox.setLayoutData(data);




		Button ok = new Button(shell, SWT.PUSH);
		ok.setText("OK");
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		ok.setLayoutData(data);	    
		ok.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				//		    	  input = text.getText();
				for(Button temp:b) {
					if(temp.getSelection() == true) {
						choice = temp.getText();
					}
				}
				//choice = chooseBox.getText();
				shell.close();
			}
		});

		Button cancel = new Button(shell, SWT.PUSH);
		cancel.setText("Cancel");
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		cancel.setLayoutData(data);
		//TODO the semantic of cancel button is a little not clear.
		cancel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				choice = "";
				shell.close();
			}
		});

		shell.setDefaultButton(ok);
	}

	public void setOption(String[] options) {
		this.options = options;
	}


	private Point getMiddlePoint(Shell shell) {
		int x = shell.getParent().getLocation().x+(shell.getParent().getBounds().width-shell.getSize().x)/2;
		int y = shell.getParent().getLocation().y+(shell.getParent().getBounds().height-shell.getSize().y)/2;
		Point location = new Point(x,y);
		return location;
	}
}
