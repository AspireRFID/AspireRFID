package org.ow2.aspirerfid.ide.bpwme.dialog;

import javax.xml.namespace.QName;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.ow2.aspirerfid.commons.ale.model.alelr.LRProperty;
import org.ow2.aspirerfid.commons.epcis.model.AttributeType;

public class EditLRAttributeDialog extends Dialog {
    /**
     * The title of the dialog.
     */
    private String title;

    private LRProperty lrp;
    
    /**
     * The message to display, or <code>null</code> if none.
     */
    private String nameMessage;
    
    private String attributeMessage;
    
    private String[] options;

    /**
     * The input value; the empty string by default.
     */
    private String nameValue = "";//$NON-NLS-1$
    
    private String attributeValue = "";

    /**
     * Ok button widget.
     */
    private Button okButton;

    /**
     * Input text widget.
     */
    private Combo nameText;
    
    private Text attributeText;

    /**
     * Error message label widget.
     */
    private Text errorMessageText;
    
    /**
     * Error message string.
     */
    private String errorMessage;


    public EditLRAttributeDialog(Shell parentShell, LRProperty lrp) {
    	super(parentShell);
    	this.lrp = lrp;
    	String name = "";
    	String value = "Default LRP Value";
    	if(name != null) {
    		nameValue = name;
    	}
    	if(value != null) {
    		attributeValue = value;
    	}
    	nameMessage = "Edit Attribute Name";
    	attributeMessage = "Edit Attribute Value";
    	
	}
    
    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    protected void buttonPressed(int buttonId) {
        if (buttonId == IDialogConstants.OK_ID) {
            nameValue = nameText.getText();
            attributeValue = attributeText.getText();
        } else {
            nameValue = null;
            attributeValue = null;
        }
        super.buttonPressed(buttonId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        if (title != null) {
			shell.setText(title);
		}
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    protected void createButtonsForButtonBar(Composite parent) {
        // create OK and Cancel buttons by default
        okButton = createButton(parent, IDialogConstants.OK_ID,
                IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID,
                IDialogConstants.CANCEL_LABEL, false);
        //do this here because setting the text will set enablement on the ok
        // button
        nameText.setFocus();
        if (nameValue != null) {
            nameText.setText(nameValue);
            //nameText.selectAll();
        }
        
        if (attributeValue != null) {
        	attributeText.setText(attributeValue);      	
        }
    }

    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    protected Control createDialogArea(Composite parent) {
        // create composite
        Composite composite = (Composite) super.createDialogArea(parent);
        // create message
        if (nameMessage != null) {
            Label label = new Label(composite, SWT.WRAP);
            label.setText(nameMessage);
            GridData data = new GridData(GridData.GRAB_HORIZONTAL
                    | GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL
                    | GridData.VERTICAL_ALIGN_CENTER);
            data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
            label.setLayoutData(data);
            label.setFont(parent.getFont());
        }
        //nameText = new Text(composite, getInputTextStyle());
        nameText = new Combo(composite, SWT.DROP_DOWN);
        nameText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
                | GridData.HORIZONTAL_ALIGN_FILL));
        nameText.setItems(options);
        
        if (attributeMessage != null) {
        	Label label = new Label(composite, SWT.WRAP);
            label.setText(attributeMessage);
            GridData data = new GridData(GridData.GRAB_HORIZONTAL
                    | GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL
                    | GridData.VERTICAL_ALIGN_CENTER);
            data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
            label.setLayoutData(data);
            label.setFont(parent.getFont());
        }
        attributeText = new Text(composite, getInputTextStyle());
        attributeText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
                | GridData.HORIZONTAL_ALIGN_FILL));
        
        
        errorMessageText = new Text(composite, SWT.READ_ONLY | SWT.WRAP);
        errorMessageText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
                | GridData.HORIZONTAL_ALIGN_FILL));
        errorMessageText.setBackground(errorMessageText.getDisplay()
                .getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
        // Set the error message text
        // See https://bugs.eclipse.org/bugs/show_bug.cgi?id=66292
        setErrorMessage(errorMessage);

        applyDialogFont(composite);
        return composite;
    }

    /**
     * Returns the ok button.
     * 
     * @return the ok button
     */
    protected Button getOkButton() {
        return okButton;
    }

    /**
     * Returns the text area.
     * 
     * @return the text area
     */
//    protected Text getText() {
//        return nameText;
//    }

    public void setOptions(String[] options) {
    	this.options = options;    	
    }

    /**
     * Returns the string typed into this input dialog.
     * 
     * @return the input string
     */
    public String getUriValue() {
        return nameValue;
    }
    
    public String getNameValue() {
    	return attributeValue;
    }

    
    public void setTitle(String title) {
		this.title = title;
	}

    /**
     * Sets or clears the error message.
     * If not <code>null</code>, the OK button is disabled.
     * 
     * @param errorMessage
     *            the error message, or <code>null</code> to clear
     * @since 3.0
     */
    public void setErrorMessage(String errorMessage) {
    	this.errorMessage = errorMessage;
    	if (errorMessageText != null && !errorMessageText.isDisposed()) {
    		errorMessageText.setText(errorMessage == null ? " \n " : errorMessage); //$NON-NLS-1$
    		// Disable the error message text control if there is no error, or
    		// no error text (empty or whitespace only).  Hide it also to avoid
    		// color change.
    		// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=130281
    		boolean hasError = errorMessage != null && (StringConverter.removeWhiteSpaces(errorMessage)).length() > 0;
    		errorMessageText.setEnabled(hasError);
    		errorMessageText.setVisible(hasError);
    		errorMessageText.getParent().update();
    		// Access the ok button by id, in case clients have overridden button creation.
    		// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=113643
    		Control button = getButton(IDialogConstants.OK_ID);
    		if (button != null) {
    			button.setEnabled(errorMessage == null);
    		}
    	}
    }
    
//    public DispositionItem start() {
//    	open();
//    	if(nameValue != null) {
//    		DispositionItem di = MasterDataBuilder.getInstance().createNewDispositionItem();
//    		di.setName(attributeValue);
//    		di.setID(nameValue);
//    		return di;
//    	}else {
//    		return null;
//    	}
//    }
    
    public LRProperty start() {
    	open();

    	int code = getReturnCode();
    	if(code == OK) {
        	if(nameValue != null) {
        		lrp.setName(nameValue);
        	}
        	if(attributeValue != null) {
        		lrp.setValue(attributeValue);
        	}
        	return lrp;
    	}else if(code == CANCEL) {
    		return null;
    	}
    	return null;
    }
    
    
	/**
	 * Returns the style bits that should be used for the input text field.
	 * Defaults to a single line entry. Subclasses may override.
	 * 
	 * @return the integer style bits that should be used when creating the
	 *         input text
	 * 
	 * @since 3.4
	 */
	protected int getInputTextStyle() {
		return SWT.SINGLE | SWT.BORDER;
	}
}

