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
import org.ow2.aspirerfid.ide.bpwme.ecspec.model.*;

/**
 * Return Spec object by choosing different types
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class ComplexDialog extends Dialog{

	private Shell parent;
	private Combo chooseBox;
	private String choice;
	private String[] options;
	private String message;
	private String input;
	
	public ComplexDialog(Shell parent) {
	    this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	}
	
	public ComplexDialog(Shell parent, int style) {
	    super(parent, style);
	    this.parent = parent;
	    options = new String[]{"LLRP Spec","RP Spec","HAL Spec"};
	    message = "";
	    input = "Default Reader";
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
	public Spec open() {
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
	    if((choice == null) || (choice.equals(""))) {
	    	return null;
	    }
	    if(choice.equals("LLRP Spec")) {
	    	return new LLRPSpec(input);
	    }else if(choice.equals("RP Spec")) {
	    	return new RPSpec(input);
	    }else if(choice.equals("HAL Spec")) {
	    	return new HALSpec(input);
	    }
		return null;
	    
	}
	
	private void createContents(final Shell shell) {
		shell.setLayout(new GridLayout(2, true));
		//shell.setLayout(new RowLayout());

		// Show the message
	    Label label = new Label(shell, SWT.NONE);
	    label.setText(message);	    
	    GridData data = new GridData();
	    data.horizontalSpan = 2;
	    label.setLayoutData(data);
		
	    // Display the input box
	    final Text text = new Text(shell, SWT.BORDER);
	    data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
	    //data.horizontalSpan = 2;
	    text.setLayoutData(data);
	    text.setText(input);
	    
	    
		chooseBox = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		chooseBox.setItems(options);
	    //chooseBox.setSize(200, 100);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
	    //data.horizontalSpan = 2;
	    chooseBox.setLayoutData(data);
	    
		
		
		
	    Button ok = new Button(shell, SWT.PUSH);
	    ok.setText("OK");
	    data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
	    ok.setLayoutData(data);	    
	    ok.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent event) {
		    	  input = text.getText();
		    	  choice = chooseBox.getText();
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
	
	public void setOption(Vector<ExtraProperty> options) {
		this.options = new String[options.size()];
		for(int i = 0; i < options.size(); i++) {
			this.options[i] = options.get(i).toString();
		}
	}
	
	  private Point getMiddlePoint(Shell shell) {
	       int x = shell.getParent().getLocation().x+(shell.getParent().getBounds().width-shell.getSize().x)/2;
	       int y = shell.getParent().getLocation().y+(shell.getParent().getBounds().height-shell.getSize().y)/2;
	       Point location = new Point(x,y);
	       return location;
	  }
}
