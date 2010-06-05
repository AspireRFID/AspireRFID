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

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.handler;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class NewMasterDataEditorGMFWizardPage extends WizardPage {

	/**
	 * IStructuredSelection
	 */
	private final IStructuredSelection currentSelection;

	/**
	 * The company name
	 */
	private static Text companyName;

	/**
	 * Constructor
	 */
	public NewMasterDataEditorGMFWizardPage(String name, IStructuredSelection currentSelection) {
		super(name);
		setTitle("Create a new MasterDataEditorGMF diagram");
		setDescription("Enter the company name.");
		this.currentSelection = currentSelection;
	}

	/**
	 * Get the currentSelection
	 */
	protected IStructuredSelection getSelection() {
		return currentSelection;
	}
	
	/**
	 * Get the companyName
	 */
	public static String getCompanyName() {
		return companyName.getText();
	}

	/**
	 * Create the contents of the page
	 */
	public void createControl(Composite parent) {
		Composite plate = new Composite(parent, SWT.NONE);
		plate.setLayout(new GridLayout(2, false));
		
		Label label = new Label(plate, SWT.NONE);
		label.setText("Company name");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false, 2, 1));
		
		companyName = new Text(plate, SWT.SINGLE | SWT.BORDER);
		companyName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		setControl(plate);
	}


}
