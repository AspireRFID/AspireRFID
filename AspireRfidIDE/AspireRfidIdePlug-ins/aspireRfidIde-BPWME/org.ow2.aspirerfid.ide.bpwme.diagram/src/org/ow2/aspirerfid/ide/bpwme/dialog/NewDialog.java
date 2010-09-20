package org.ow2.aspirerfid.ide.bpwme.dialog;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;

/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class NewDialog extends Dialog {

	public NewDialog(Shell parent) {
		this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	}

	public NewDialog(Shell parent, int style) {
		super(parent, style);
		this.parent = parent;
		setText("");
	}


	public void open() {
		// Create the dialog window
		Shell shell = new Shell(parent, getStyle());
		shell.setText(getText());
		createContents(shell);
		shell.pack();
		//		    shell.setLocation(getMiddlePoint(shell));
		shell.open();
		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		// Return the entered value, or null
		return;
	}


	private void createContents(final Shell shell) {
		shell.setLayout(new GridLayout(2, true));
		{
			actionGroup = new Group(shell, SWT.NONE);
			GridLayout actionGroupLayout = new GridLayout();
			actionGroupLayout.makeColumnsEqualWidth = true;
			actionGroupLayout.numColumns = 2;
			actionGroup.setLayout(actionGroupLayout);
			GridData actionGroupLData = new GridData();
			actionGroupLData.verticalSpan = 4;
			actionGroupLData.horizontalSpan = 2;
			actionGroup.setLayoutData(actionGroupLData);
			actionGroup.setText("choose action");
			{
				openDefaultButton = new Button(actionGroup, SWT.RADIO | SWT.LEFT);
				//				GridData button1LData = new GridData();
				//				openDefaultButton.setLayoutData(button1LData);
				openDefaultButton.setText("open defalut diagram");
				openDefaultButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent evt) {
						openDefaultButtonWidgetSelected(evt);
					}
				});
			}
			{
				textFileLocation = new Text(actionGroup, SWT.BORDER);
				GridData text1LData = new GridData();
				text1LData.horizontalAlignment = GridData.FILL;
				textFileLocation.setLayoutData(text1LData);
				textFileLocation.setText("file path");
			}
			{
				openInWorkspaceButton = new Button(actionGroup, SWT.RADIO | SWT.LEFT);
				//GridData button2LData = new GridData();
				//button2LData.widthHint = 161;
				//button2LData.heightHint = 16;
				//button2.setLayoutData(button2LData);
				openInWorkspaceButton.setText("open diagram in workspace");
				openInWorkspaceButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent evt) {
						openInWorkspaceButtonWidgetSelected(evt);
					}
				});
			}
			{
				GridData listViewer1LData = new GridData();
				listViewer1LData.verticalSpan = 3;
				listViewer1LData.horizontalAlignment = GridData.FILL;
				listViewer1 = new ListViewer(actionGroup, SWT.BORDER);
				listViewer1.getControl().setLayoutData(listViewer1LData);
			}
			{
				openFileButton = new Button(actionGroup, SWT.RADIO | SWT.LEFT);
				//				GridData button3LData = new GridData();
				//				button3LData.widthHint = 166;
				//				button3LData.heightHint = 16;
				//				button3.setLayoutData(button3LData);
				openFileButton.setText("open diagram file");
				openFileButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent evt) {
						openFileButtonWidgetSelected(evt);
					}
				});
			}
			{
				newDiagramButton = new Button(actionGroup, SWT.RADIO | SWT.LEFT);
				//				GridData button4LData = new GridData();
				//				button4.setLayoutData(button4LData);
				newDiagramButton.setText("new diagram");
				newDiagramButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent evt) {
						newDiagramButtonWidgetSelected(evt);
					}
				});
			}
			
		    Button ok = new Button(shell, SWT.PUSH);
		    ok.setText("OK");
		    ok.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		    ok.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent evt) {					
						shell.close();
						okButtonWidgetSelected(evt);
					}
				});
		    Button cancel = new Button(shell, SWT.PUSH);
		    cancel.setText("Cancel");
		    cancel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		    
		    cancel.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					shell.close();
				}
			});
		}

	}

	private void openDefaultButtonWidgetSelected(SelectionEvent evt) {
		action = ActionType.OpenDefault;
		//set the text with the one in the field
	}

	private void openInWorkspaceButtonWidgetSelected(SelectionEvent evt) {
		action = ActionType.OpenInWorkspace;
	}

	private void openFileButtonWidgetSelected(SelectionEvent evt) {
		action = ActionType.OpenFile;
	}

	private void newDiagramButtonWidgetSelected(SelectionEvent evt) {
		action = ActionType.NewFile;
	}
	
	private void okButtonWidgetSelected(SelectionEvent evt) {
		switch(action) {
		case NewFile:
			MainUtil.executeCommand(
					"org.ow2.aspirerfid.ide.MasterDataEditorGMF.newMasterDataEditorGMF.command");
			break;
		case OpenDefault:
			
			break;
		case OpenFile:
			MainUtil.executeCommand(
					"org.ow2.aspirerfid.ide.MasterDataEditorGMF.openMasterDataEditorGMFFromFile.command");
			break;
		case OpenInWorkspace:
			break;
		}
	}
	
	
	private void cancelButtonWidgetSelected(SelectionEvent evt) {
		switch(action) {
		
		}
		System.out.println(action);
	}
	
	private Button openDefaultButton;
	private Group actionGroup;
	private Button openInWorkspaceButton;
	private Button newDiagramButton;
	private ListViewer listViewer1;
	private Text textFileLocation;
	private Button openFileButton;
	private Shell parent;
	private ActionType action = ActionType.OpenDefault;
	
	enum ActionType{
		OpenDefault,
		OpenInWorkspace,
		OpenFile,
		NewFile;
	}
	
}
