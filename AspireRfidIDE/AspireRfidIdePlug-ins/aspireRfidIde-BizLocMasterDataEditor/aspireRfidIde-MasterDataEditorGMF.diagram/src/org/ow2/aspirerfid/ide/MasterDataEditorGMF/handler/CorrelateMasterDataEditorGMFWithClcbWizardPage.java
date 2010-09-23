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

import java.io.File;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class CorrelateMasterDataEditorGMFWithClcbWizardPage extends WizardPage {
	
	/**
	 * File path
	 */
	private Text filePathEditor;

	/**
	 * IStructuredSelection
	 */
	private final IStructuredSelection currentSelection;
	
	/**
	 * @return the filePathEditor
	 */
	public Text getFilePathEditor() {
		return filePathEditor;
	}

	/**
	 * Constructor
	 */
	public CorrelateMasterDataEditorGMFWithClcbWizardPage(String name, IStructuredSelection currentSelection) {
		super(name);
		this.currentSelection = currentSelection;
	}

	/**
	 * Get the currentSelection
	 */
	protected IStructuredSelection getSelection() {
		return currentSelection;
	}

	/**
	 * Create the contents of the page
	 */
	public void createControl(Composite parent) {
		Composite plate = new Composite(parent, SWT.NONE);
		plate.setLayout(new GridLayout(2, false));
		setPageComplete(false);
		
		Label filePathLabel = new Label(plate, SWT.NONE);
		filePathLabel.setText("File: ");
		filePathLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false, 2, 1));
		
		filePathEditor = new Text(plate, SWT.SINGLE | SWT.BORDER);
		filePathEditor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Button button = new Button(plate, SWT.PUSH);
		button.setText(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.WizardNewFileCreationPage_BrowseButton);
		button.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		
		filePathEditor.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				setPageComplete(validatePage());
			}
		});
		
		button.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
				dialog.setText(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.WizardNewFileCreationPage_SelectNewFileDialog);
				String[] filterExt = {"*.masterdataeditorgmf_diagram"};
				dialog.setFilterExtensions(filterExt);
				String fileName = dialog.open();
				
				if (fileName != null) {
					filePathEditor.setText(fileName);
					setPageComplete(validatePage());
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		setControl(plate);
	}
	
	/**
	 * @generated NOT
	 */
	protected boolean validatePage() {
		String fileName = filePathEditor.getText().trim();
		if (fileName.length() == 0) {
			setErrorMessage(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.WizardNewFileCreationPage_EmptyFileNameError);
			return false;
		}
		if (!fileName.endsWith(".masterdataeditorgmf_diagram")) {
			setErrorMessage("File name should have the extension masterdataeditorgmf_diagram.");
			return false;
		}
		String filePath = filePathEditor.getText().trim();
		if (!new Path("").isValidPath(filePath)) { //$NON-NLS-1$
			setErrorMessage(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.WizardNewFileCreationPage_InvalidFileNameError);
			return false;
		}
		File file = new File(filePathEditor.getText());
		if (!file.exists()) {
			setErrorMessage("File name does not exist.");
			return false;
		}
		setErrorMessage(null);
		return true;
	}


}
