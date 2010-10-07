package org.ow2.aspirerfid.ide.bpwme.diagram.preferences;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.InputDialog;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;

/*
 * Modified from org.eclipse.jface.preference.ListEditor
 */


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
    
    private Set<String> keySet;
	
    public TwoListEditor(Set<String> keySet, Composite parent) {
		createControl(parent);
		this.keySet = keySet;
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

        mainList = getMainListControl(parent);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.verticalAlignment = GridData.FILL;
        gd.horizontalSpan = 1;
        gd.grabExcessHorizontalSpace = true;
        mainList.setLayoutData(gd);
        
        subList = getSubListControl(parent);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.verticalAlignment = GridData.FILL;
        gd.horizontalSpan = 1;
        gd.grabExcessHorizontalSpace = true;
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
                    addPressed();
                } else if (widget == removeButton) {
                    removePressed();
                } else if (widget == upButton) {
                    upPressed();
                } else if (widget == downButton) {
                    downPressed();
                } else if (widget == mainList) {
                    mainSelectionChanged();
                } else if (widget == subList) {
                	subSelectionChanged();
                }
            }
        };
    }
	

	protected Shell getShell() {
        if (addButton == null) {
			return null;
		}
        return addButton.getShell();
    }
    
	protected String getNewInputObject() {
		InputDialog inputDialog = new InputDialog(getShell(), "Input Dialog","Input the attribute value", "", null);
		inputDialog.open();
		String input = inputDialog.getValue();
		return input;
	}    
	
    protected void downPressed() {
    	swap(false);
    	doStore();
	}

	protected void upPressed() {
		swap(true);
		doStore();
	}

	protected void removePressed() {
        setPresentsDefaultValue(false);
        int index = subList.getSelectionIndex();
        if (index >= 0) {
        	subList.remove(index);
            subSelectionChanged();
        }
		doStore();
	}
	
	
    private void swap(boolean up) {
        setPresentsDefaultValue(false);
        int index = subList.getSelectionIndex();
        int target = up ? index - 1 : index + 1;

        if (index >= 0) {
            String[] selection = subList.getSelection();
            Assert.isTrue(selection.length == 1);
            subList.remove(index);
            subList.add(selection[0], target);
            subList.setSelection(target);
        }
        subSelectionChanged();
        
    }

	protected void addPressed() {
        setPresentsDefaultValue(false);
        String input = getNewInputObject();
        
        if (input != null) {
            int index = subList.getSelectionIndex();
            if (index >= 0) {
            	subList.add(input, index + 1);
			} else {
				subList.add(input, 0);
			}
            subSelectionChanged();
            doStore();
        }
	}
	
    protected void subSelectionChanged() {

        int index = subList.getSelectionIndex();
        int size = subList.getItemCount();

        removeButton.setEnabled(index >= 0);
        upButton.setEnabled(size > 1 && index > 0);
        downButton.setEnabled(size > 1 && index >= 0 && index < size - 1);
    }
	
	
	
	protected void mainSelectionChanged() {
    	String[] selection = mainList.getSelection();
		if(selection.length < 1) {
			return;
		}
		String subOption = getPreferenceStore().getString(selection[0]);
		String[] subOptions = parseString(subOption);
		subList.removeAll();
		for(int i = 0; i < subOptions.length; i++) {
			subList.add(subOptions[i]);
		}
	}
    
    
	protected String[] parseString(String stringList) {
		StringTokenizer st = new StringTokenizer(stringList, "," + "\n\r");//$NON-NLS-1$ //File.pathSeparator
		ArrayList<String> v = new ArrayList<String>();
		while (st.hasMoreElements()) {
			v.add((String) st.nextElement());
		}
		return (String[]) v.toArray(new String[v.size()]);
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
		if(mainList != null) {
			Iterator<String> iter = keySet.iterator();
			while(iter.hasNext()) {
				mainList.add(iter.next());
			}
		}
		if(subList != null) {
			
		}
	}

	@Override
	protected void doLoadDefault() {
		if(mainList != null) {
			mainList.removeAll();
			Iterator<String> iter = keySet.iterator();
			while(iter.hasNext()) {
				String name = iter.next();
				mainList.add(name);
				getPreferenceStore().setValue(name, getPreferenceStore().getDefaultString(name));
			}
		}
		if(subList != null) {
			subList.removeAll();
		}		
	}

	protected String createList(String[] items) {
		StringBuffer path = new StringBuffer("");//$NON-NLS-1$

		for (int i = 0; i < items.length; i++) {
			path.append(items[i]);
			path.append(",");
		}
		return path.toString();
	}
	
	@Override
	protected void doStore() {
        String s = createList(subList.getItems());
        String[] name = mainList.getSelection();
        if ((s != null)&&(name.length > 0)) {
			getPreferenceStore().setValue(name[0], s);
		}
	}

	@Override
	public int getNumberOfControls() {
		return 3;
	}

}
