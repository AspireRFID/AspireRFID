package org.ow2.aspirerfid.ide.bpwme.diagram.application;

import java.io.File;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorPlugin;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.Messages;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceConstants;

public class WizardNewProjectCreationPage extends WizardPage{

	private Text projectNameEditor;
	
	public WizardNewProjectCreationPage(String pageName) {
		super(pageName);
	}

	@Override
	public void createControl(Composite parent) {
		Composite plate = new Composite(parent, SWT.NONE);
		plate.setLayout(new GridLayout(2, false));
		Label label = new Label(plate, SWT.NONE);
		label.setText("Project Name: ");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
				false, 1, 1));
		projectNameEditor = new Text(plate, SWT.SINGLE | SWT.BORDER);
		projectNameEditor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));
		
		projectNameEditor.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				setPageComplete(validatePage());
			}
		});
		setControl(plate);
		setPageComplete(validatePage());
	}
	
	public URI getURI() {
		IPreferenceStore store = BpwmeDiagramEditorPlugin.getInstance().getPreferenceStore();
		String home = store.getString(PreferenceConstants.P_BPWME_DIR);
		String name = projectNameEditor.getText().trim();
		
		String filePath = home + File.separator + name + File.separator + name + ".bpwme_diagram";		
		return URI.createFileURI(filePath);
	}
	
	private boolean validatePage() {
		String name = projectNameEditor.getText().trim();
		//1. project name have to be valid
		if(name.length() == 0) {
			setErrorMessage("File name cannot be empty");
			return false;
		}
		//2. project name have to be unique
		//if some other file exists in the current directory, we have to use another name
		if(isProjectExist(name)) {
			setErrorMessage("Project already exist, change name to another one");
			return false;
		}
		
		if(!name.matches("[a-zA-Z0-9]+")) {
			setErrorMessage("Name can only contain numbers and letters");
			return false;
		}
		
		setErrorMessage(null);
		return true;
	}
	
	public String getProjectName() {
		return projectNameEditor.getText().trim();
	}
	
	private boolean isProjectExist(String name) {
		IPreferenceStore store = BpwmeDiagramEditorPlugin.getInstance().getPreferenceStore();
		String home = store.getString(PreferenceConstants.P_BPWME_DIR);

		//if the directory does not exist, return false
		File dir = new File(home + File.separator + name);
		if(!dir.exists()) {
			return false;
		}else {//if the directory does not include any diagram file, return false
			String[] children = dir.list();
			for(String child : children) {
				if(child.endsWith(".bpwme_diagram")) {
					return true;
				}
			}
		}
		return false;
	}

}
