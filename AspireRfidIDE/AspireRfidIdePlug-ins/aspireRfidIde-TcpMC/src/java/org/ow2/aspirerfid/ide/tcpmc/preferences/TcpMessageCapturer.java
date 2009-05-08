package org.ow2.aspirerfid.ide.tcpmc.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.ow2.aspirerfid.ide.tcpmc.Activator;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 *
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class TcpMessageCapturer
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	public TcpMessageCapturer() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Raw/EPC TCP Message Capturer Preferences");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		Composite fieldEditorParent = getFieldEditorParent();
		{
			StringFieldEditor stringFieldEditor = new StringFieldEditor(PreferenceConstants.P_RawTcpListeningPort, "Raw TCP Listening Port:", fieldEditorParent);
			addField(stringFieldEditor);
		}

		{
			addField(new StringFieldEditor(PreferenceConstants.P_RawEpcListeningPort, "EPC TCP Listening Port:", fieldEditorParent));
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}