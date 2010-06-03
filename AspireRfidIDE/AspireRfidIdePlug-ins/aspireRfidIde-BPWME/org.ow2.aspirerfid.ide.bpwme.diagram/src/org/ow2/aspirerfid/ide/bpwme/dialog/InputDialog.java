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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Modified from eclipse input dialog
 * @since 3.2
 *
 */
public class InputDialog extends Dialog {
	  private String message;
	  private String input;
	  private Shell parent;

	  /**
	   * InputDialog constructor
	   *
	   * @param parent the parent
	   */
	    public InputDialog(Shell parent) {
	    // Pass the default styles here
	    this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	  }

	  /**
	   * InputDialog constructor
	   *
	   * @param parent the parent
	   * @param style the style
	   */
	  public InputDialog(Shell parent, int style) {
	    // Let users override the default styles
	    super(parent, style);
	    this.parent = parent;
	    message = "";
	    input = "";
	    setText("Input Dialog");
	    setMessage("Please enter a value:");
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

	  /**
	   * Gets the input
	   *
	   * @return String
	   */
	  public String getInput() {
	    return input;
	  }

	  /**
	   * Sets the input
	   *
	   * @param input the new input
	   */
	  public void setInput(String input) {
	    this.input = input;
	  }

	  /**
	   * Opens the dialog and returns the input
	   *
	   * @return String
	   */
	  public String open() {
	    // Create the dialog window
	    Shell shell = new Shell(parent, getStyle());
	    shell.setText(getText());
	    createContents(shell);
	    shell.pack();
	    shell.setLocation(getMiddlePoint(shell));
	    shell.open();
	    Display display = parent.getDisplay();
	    while (!shell.isDisposed()) {
	    	if (!display.readAndDispatch()) {
	        display.sleep();
	      }
	    }
	    
	    // Return the entered value, or null
	    return input;
	  }

	  /**
	   * Creates the dialog's contents
	   *
	   * @param shell the dialog window
	   */
	  private void createContents(final Shell shell) {
	    shell.setLayout(new GridLayout(2, true));

	    // Show the message
	    Label label = new Label(shell, SWT.NONE);
	    label.setText(message);
	    GridData data = new GridData();
	    data.horizontalSpan = 2;
	    label.setLayoutData(data);

	    // Display the input box
	    final Text text = new Text(shell, SWT.BORDER);
	    data = new GridData(GridData.FILL_HORIZONTAL);
	    data.horizontalSpan = 2;
	    text.setLayoutData(data);
	    text.setText(input);
	    // Create the OK button and add a handler
	    // so that pressing it will set input
	    // to the entered value
	    Button ok = new Button(shell, SWT.PUSH);
	    ok.setText("OK");
	    data = new GridData(GridData.FILL_HORIZONTAL);
	    ok.setLayoutData(data);
	    ok.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent event) {
	        input = text.getText();
	        shell.close();
	      }
	    });

	    // Create the cancel button and add a handler
	    // so that pressing it will set input to null
	    Button cancel = new Button(shell, SWT.PUSH);
	    cancel.setText("Cancel");
	    data = new GridData(GridData.FILL_HORIZONTAL);
	    cancel.setLayoutData(data);
	    //TODO the semantic of cancel button is a little not clear.
	    cancel.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent event) {
	        input = "";
	        shell.close();
	      }
	    });

	    // Set the OK button as the default, so
	    // user can type input and press Enter
	    // to dismiss
	   shell.setDefaultButton(ok);
	  }
	
	  //return the middle of the workspace for putting the dialog
	  private Point getMiddlePoint(Shell shell) {
	       int x = shell.getParent().getLocation().x+(shell.getParent().getBounds().width-shell.getSize().x)/2;
	       int y = shell.getParent().getLocation().y+(shell.getParent().getBounds().height-shell.getSize().y)/2;
	       Point location = new Point(x,y);
	       return location;
	  }
}

