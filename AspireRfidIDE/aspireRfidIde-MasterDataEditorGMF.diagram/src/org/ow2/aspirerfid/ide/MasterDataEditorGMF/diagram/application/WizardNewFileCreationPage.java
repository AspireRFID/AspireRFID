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

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.application;

import org.eclipse.core.runtime.IPath;
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
 * @generated
 */
public class WizardNewFileCreationPage extends WizardPage {

	/**
	 * @generated
	 */
	private final IStructuredSelection currentSelection;

	/**
	 * @generated
	 */
	private String initialFileName;

	/**
	 * @generated
	 */
	private IPath initialContainerFullPath;

	/**
	 * File name
	 */
	private Text fileNameEditor;
	
	/**
	 * File path
	 */
	private Text filePathEditor;
	
	/**
	 * Browse button
	 */
	private Button button;
	
	/**
	 * isClcbProcSelected
	 */
	private boolean isClcbProcSelected;
	
	/**
	 * @generated NOT
	 */
	public WizardNewFileCreationPage(String name,
			IStructuredSelection currentSelection) {
		super(name);
		this.currentSelection = currentSelection;
	}

	/**
	 * @generated NOT
	 */
	public WizardNewFileCreationPage(String name,
			IStructuredSelection currentSelection, boolean isClcbProcSelected) {
		super(name);
		this.currentSelection = currentSelection;
		this.isClcbProcSelected = isClcbProcSelected;
	}

	/**
	 * @generated
	 */
	protected IStructuredSelection getSelection() {
		return currentSelection;
	}

	/**
	 * @generated
	 */
	public String getFileName() {
		if (filePathEditor == null) {
			return initialFileName;
		}
		IPath path = getFilePath();
		if (path == null || path.isEmpty() || path.hasTrailingSeparator()) {
			return null;
		}
		return path.lastSegment();
	}

	/**
	 * @generated
	 */
	public void setFileName(String fileName) {
		if (filePathEditor == null) {
			initialFileName = fileName;
			return;
		}
		setFilePath(getContainerFullPath(), fileName);
	}

	/**
	 * @generated
	 */
	public IPath getContainerFullPath() {
		if (filePathEditor == null) {
			return initialContainerFullPath;
		}
		IPath path = getFilePath();
		if (path == null || path.isEmpty()) {
			return null;
		}
		if (path.hasTrailingSeparator()) {
			return path;
		}
		path = path.removeLastSegments(1);
		if (path.isEmpty()) {
			return null;
		}
		return path.addTrailingSeparator();
	}

	/**
	 * @generated
	 */
	public void setContainerFullPath(IPath containerPath) {
		if (filePathEditor == null) {
			initialContainerFullPath = containerPath;
			return;
		}
		setFilePath(containerPath, getFileName());
	}

	/**
	 * @generated
	 */
	protected IPath getFilePath() {
		String fileName = filePathEditor.getText().trim() + fileNameEditor.getText().trim();
		if (fileName.length() == 0) {
			return null;
		}
		return new Path(filePathEditor.getText() + fileNameEditor.getText());
	}

	/**
	 * @generated NOT
	 */
	protected void setFilePath(IPath containerPath, String fileName) {
		if (fileName == null) {
			fileName = ""; //$NON-NLS-1$
		} else {
			fileName = fileName.trim();
		}
		if (containerPath == null) {
			filePathEditor.setText(fileName);
		} else {
			if (!containerPath.hasTrailingSeparator()) {
				containerPath = containerPath.addTrailingSeparator();
			}
//			IPath path = fileName.length() == 0 ? containerPath : containerPath
//					.append(fileName);
			filePathEditor.setText(containerPath.toOSString());
		}
		setPageComplete(validatePage());
	}
	
	/**
	 * @generated NOT
	 */
	public void createControl(Composite parent) {
		Composite plate = new Composite(parent, SWT.NONE);
		plate.setLayout(new GridLayout(2, false));
		
		Label fileNameLabel = new Label(plate, SWT.NONE);
		fileNameLabel.setText("File name: ");
		fileNameLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false, 2, 1));

		fileNameEditor = new Text(plate, SWT.SINGLE | SWT.BORDER);
		fileNameEditor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		fileNameEditor.setText(getFileName());
		
		Label filePathLabel = new Label(plate, SWT.NONE);
		filePathLabel.setText("File path: ");
		filePathLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false, 2, 1));
		
		filePathEditor = new Text(plate, SWT.SINGLE | SWT.BORDER);
		filePathEditor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		button = new Button(plate, SWT.PUSH);
		button.setText(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.WizardNewFileCreationPage_BrowseButton);
		button.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		
		fileNameEditor.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				setPageComplete(validatePage());
			}
		});
		
		if (isClcbProcSelected) {
			filePathEditor.setEditable(false);
			button.setVisible(false);
		}
		else {
			button.addSelectionListener(new SelectionListener() {
	
				public void widgetSelected(SelectionEvent e) {
					FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
					dialog.setText(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.WizardNewFileCreationPage_SelectNewFileDialog);
					String[] filterExt = {"*.masterdataeditorgmf_diagram"};
					dialog.setFilterExtensions(filterExt);
					dialog.setFileName(getFileName());
					String fileName = dialog.open();
					
					if (fileName != null) {
						fileNameEditor.setText(fileName.substring(fileName.lastIndexOf(System.getProperty("file.separator"))+1).replaceFirst(".masterdataeditorgmf_diagram", ""));
						filePathEditor.setText(fileName.substring(0, fileName.lastIndexOf(System.getProperty("file.separator"))+1));
						setPageComplete(validatePage());
					}
				}
	
				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});
		}

		// init
		setFilePath(initialContainerFullPath, initialFileName);
		setControl(plate);
	}

	/**
	 * @generated NOT
	 */
	protected boolean validatePage() {
		String fileName = fileNameEditor.getText().trim();
		if (fileName.length() == 0) {
			setErrorMessage(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.WizardNewFileCreationPage_EmptyFileNameError);
			return false;
		}
		if (fileName.endsWith(".")) {
			setErrorMessage("File name must not end in \".\"");
			return false;
		}
		if (fileName.indexOf("\\") > -1 || fileName.indexOf("/") > -1 || fileName.indexOf(":") > -1 || fileName.indexOf("*") > -1 || fileName.indexOf("?") > -1 
				|| fileName.indexOf("\"") > -1 || fileName.indexOf("<") > -1 || fileName.indexOf(">") > -1 || fileName.indexOf("|") > -1) {
			setErrorMessage("File name can't contain any of the following characters:\n\t\\ / : * ? \" < > |");
			return false;
		}
		String filePath = filePathEditor.getText().trim();
		if (!new Path("").isValidPath(filePath)) { //$NON-NLS-1$
			setErrorMessage(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.WizardNewFileCreationPage_InvalidFileNameError);
			return false;
		}
		setErrorMessage(null);
		return true;
	}
}
