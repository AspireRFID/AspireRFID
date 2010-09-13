package org.ow2.aspirerfid.ide.bpwme.dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

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
/**
 * Used when want to open a master data editor in the BPWME editor
 * Called by MasterEditor.newReadPointButton.addSelectionListener()
 * 
 * @author yluo
 *
 */
public class OpenMasterDataEditorDialog extends Dialog{	
	private Shell parent;

	public OpenMasterDataEditorDialog(Shell parent) {
		super(parent);
		this.parent = parent;
	}

	private Composite composite1;
	private Button createNew;
	private Button openFrom;
	private List list1;
	private Group group2;
	private Group group1;
	private Button cancelbutton;
	private Button okButton;
	private Button inWorkspace;

	private void initGUI() {
		try {
			{
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * return the user choice from the options
	 */
	public void open() {
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
			    
	}

	private void createContents(final Shell shell) {
		getComposite1();
	}

	private Point getMiddlePoint(Shell shell) {
		int x = shell.getParent().getLocation().x+(shell.getParent().getBounds().width-shell.getSize().x)/2;
		int y = shell.getParent().getLocation().y+(shell.getParent().getBounds().height-shell.getSize().y)/2;
		Point location = new Point(x,y);
		return location;
	}

	private Composite getComposite1() {
		if(composite1 == null) {
			composite1 = new Composite(parent, SWT.NONE);
			FormLayout composite1Layout = new FormLayout();
			composite1.setLayout(composite1Layout);
			composite1.setSize(400, 150);
			{
				group2 = new Group(composite1, SWT.NONE);
				FillLayout group2Layout = new FillLayout(org.eclipse.swt.SWT.HORIZONTAL);
				group2.setLayout(group2Layout);
				FormData group2LData = new FormData();
				group2LData.width = 180;
				group2LData.height = 80;
				group2LData.left =  new FormAttachment(0, 1000, 0);
				group2LData.top =  new FormAttachment(0, 1000, 0);
				group2LData.bottom =  new FormAttachment(1000, 1000, -54);
				group2LData.right =  new FormAttachment(1000, 1000, -214);
				group2.setLayoutData(group2LData);
				group2.setText("Available diagram in workspace");
				{
					list1 = new List(group2, SWT.NONE);
				}
			}
			{
				group1 = new Group(composite1, SWT.NONE);
				GridLayout group1Layout = new GridLayout();
				group1Layout.makeColumnsEqualWidth = true;
				group1.setLayout(group1Layout);
				FormData group1LData = new FormData();
				group1LData.width = 190;
				group1LData.height = 80;
				group1LData.right =  new FormAttachment(971, 1000, 0);
				group1LData.top =  new FormAttachment(3, 1000, 0);
				group1LData.bottom =  new FormAttachment(650, 1000, 0);
				group1LData.left =  new FormAttachment(481, 1000, 0);
				group1.setLayoutData(group1LData);
				group1.setText("Choose Action");
				{
					createNew = new Button(group1, SWT.RADIO | SWT.LEFT);
					GridData createNewLData = new GridData();
					createNew.setLayoutData(createNewLData);
					createNew.setText("Create New Diagram");
				}
				{
					openFrom = new Button(group1, SWT.RADIO | SWT.LEFT);
					GridData openFromLData = new GridData();
					openFrom.setLayoutData(openFromLData);
					openFrom.setText("Open From Existing Diagram");
				}
				{
					inWorkspace = new Button(group1, SWT.RADIO | SWT.LEFT);
					GridData inWorkspaceLData = new GridData();
					inWorkspace.setLayoutData(inWorkspaceLData);
					inWorkspace.setText("Use Opened Diagram in Workspace");
				}
			}
			{
				okButton = new Button(composite1, SWT.PUSH | SWT.CENTER);
				FormData okButtonLData = new FormData();
				okButtonLData.width = 80;
				okButtonLData.height = 30;
				okButtonLData.left =  new FormAttachment(528, 1000, 0);
				okButtonLData.right =  new FormAttachment(739, 1000, 0);
				okButtonLData.top =  new FormAttachment(760, 1000, 0);
				okButtonLData.bottom =  new FormAttachment(920, 1000, 0);
				okButton.setLayoutData(okButtonLData);
				okButton.setText("OK");
			}
			{
				cancelbutton = new Button(composite1, SWT.PUSH | SWT.CENTER);
				FormData cancelbuttonLData = new FormData();
				cancelbuttonLData.width = 86;
				cancelbuttonLData.height = 23;
				cancelbuttonLData.left =  new FormAttachment(768, 1000, 0);
				cancelbuttonLData.right =  new FormAttachment(972, 1000, 0);
				cancelbuttonLData.top =  new FormAttachment(760, 1000, 0);
				cancelbuttonLData.bottom =  new FormAttachment(920, 1000, 0);
				cancelbutton.setLayoutData(cancelbuttonLData);
				cancelbutton.setText("Cacel");
			}
		}
		return composite1;
	}
	
	public static void main(String args[]) {
		Display display = new Display ();
		final Shell shell = new Shell (display);
	    shell.setLayout(new RowLayout());
	    shell.setSize(500, 200);
		OpenMasterDataEditorDialog od = new OpenMasterDataEditorDialog(shell);
		od.open();
	}

}
