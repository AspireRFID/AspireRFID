package org.ow2.aspirerfid.ide.bpwme.diagram.preferences;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Widget;

public class TwoListEditor extends FieldEditor{

	private List mainList;

	
	private List subList;
    /**
     * The button box containing the Add, Remove, Up, and Down buttons;
     * <code>null</code> if none (before creation or after disposal).
     */
    private Composite buttonBox;

    /**
     * The Add button.
     */
    private Button addButton;

    /**
     * The Remove button.
     */
    private Button removeButton;

    /**
     * The Up button.
     */
    private Button upButton;

    /**
     * The Down button.
     */
    private Button downButton;

    private SelectionListener selectionListener;
	
    public TwoListEditor(Composite parent) {
		createControl(parent);
	}
    
	@Override
	protected void adjustForNumColumns(int numColumns) {
        Control control = getLabelControl();
        ((GridData) control.getLayoutData()).horizontalSpan = numColumns;
        ((GridData) mainList.getLayoutData()).horizontalSpan = 1;
        ((GridData) subList.getLayoutData()).horizontalSpan = 1;
	}

	@Override
	protected void doFillIntoGrid(Composite parent, int numColumns) {
        Control control = getLabelControl(parent);
        GridData gd = new GridData();
        gd.horizontalSpan = numColumns;
        control.setLayoutData(gd);
//		GridData gd;
        mainList = getMainListControl(parent);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.verticalAlignment = GridData.FILL;
        gd.horizontalSpan = 1;
        gd.grabExcessHorizontalSpace = true;

//        gd = new GridData();
//        gd.verticalAlignment = GridData.FILL;
//        gd.grabExcessVerticalSpace = true;
//        gd.horizontalAlignment = GridData.FILL;
//        gd.grabExcessHorizontalSpace = true;
        mainList.setLayoutData(gd);
        
        subList = getSubListControl(parent);

        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.verticalAlignment = GridData.FILL;
        gd.horizontalSpan = 1;
        gd.grabExcessHorizontalSpace = true;

//        gd = new GridData();
//        gd.verticalAlignment = GridData.FILL;
//        gd.grabExcessVerticalSpace = true;
//        gd.horizontalAlignment = GridData.FILL;
//        gd.grabExcessHorizontalSpace = true;
        subList.setLayoutData(gd);

        buttonBox = getButtonBoxControl(parent);
        gd = new GridData();
        gd.verticalAlignment = GridData.BEGINNING;
        buttonBox.setLayoutData(gd);
	}

	private List getSubListControl(Composite parent) {
        if (subList == null) {
        	subList = new List(parent, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL
                    | SWT.H_SCROLL);
        	subList.setFont(parent.getFont());
        	subList.addSelectionListener(getSelectionListener());
        	subList.addDisposeListener(new DisposeListener() {
                public void widgetDisposed(DisposeEvent event) {
                	subList = null;
                }
            });
        } else {
            checkParent(subList, parent);
        }
        return subList;
	}

	private List getMainListControl(Composite parent) {
        if (mainList == null) {
        	mainList = new List(parent, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL
                    | SWT.H_SCROLL);
        	mainList.setFont(parent.getFont());
        	mainList.addSelectionListener(getSelectionListener());
        	mainList.addDisposeListener(new DisposeListener() {
                public void widgetDisposed(DisposeEvent event) {
                	mainList = null;
                }
            });
        } else {
            checkParent(mainList, parent);
        }
        return mainList;
	}

    public void createSelectionListener() {
        selectionListener = new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                Widget widget = event.widget;
                if (widget == addButton) {
                    //addPressed();
                } else if (widget == removeButton) {
                    //removePressed();
                } else if (widget == upButton) {
                    //upPressed();
                } else if (widget == downButton) {
                    //downPressed();
                } else if (widget == mainList) {
                    //selectionChanged();
                } else if (widget == subList) {
                	//
                }
            }
        };
    }
	
	
    private SelectionListener getSelectionListener() {
        if (selectionListener == null) {
			createSelectionListener();
		}
        return selectionListener;
    }
    
    
	
    public Composite getButtonBoxControl(Composite parent) {
        if (buttonBox == null) {
            buttonBox = new Composite(parent, SWT.NULL);
            GridLayout layout = new GridLayout();
            layout.marginWidth = 0;
            buttonBox.setLayout(layout);
            createButtons(buttonBox);
            buttonBox.addDisposeListener(new DisposeListener() {
                public void widgetDisposed(DisposeEvent event) {
                    addButton = null;
                    removeButton = null;
                    upButton = null;
                    downButton = null;
                    buttonBox = null;
                }
            });

        } else {
            checkParent(buttonBox, parent);
        }

        //selectionChanged();
        return buttonBox;
    }
    
    
    private void createButtons(Composite box) {
        addButton = createPushButton(box, "ListEditor.add");//$NON-NLS-1$
        removeButton = createPushButton(box, "ListEditor.remove");//$NON-NLS-1$
        upButton = createPushButton(box, "ListEditor.up");//$NON-NLS-1$
        downButton = createPushButton(box, "ListEditor.down");//$NON-NLS-1$
    }

    private Button createPushButton(Composite parent, String key) {
        Button button = new Button(parent, SWT.PUSH);
        button.setText(JFaceResources.getString(key));
        button.setFont(parent.getFont());
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        int widthHint = convertHorizontalDLUsToPixels(button,
                IDialogConstants.BUTTON_WIDTH);
        data.widthHint = Math.max(widthHint, button.computeSize(SWT.DEFAULT,
                SWT.DEFAULT, true).x);
        button.setLayoutData(data);
        button.addSelectionListener(getSelectionListener());
        return button;
    }
    
	@Override
	protected void doLoad() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doLoadDefault() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doStore() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNumberOfControls() {
		return 3;
	}

}
