package org.ow2.aspirerfid.ide.bpwme.diagram.preferences;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.eclipse.jface.preference.IPreferenceStore;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorPlugin;

public class PreferenceUtil {
	
	/**
	 * Look up into the preference, find the stored value, return the settings
	 * @param name
	 * @return
	 */
	public static String[] getAttributes(String name) {
		IPreferenceStore store = BpwmeDiagramEditorPlugin.getInstance().getPreferenceStore();
		String stringList = store.getString(name);
		StringTokenizer st = new StringTokenizer(stringList, "," + "\n\r");
		ArrayList<String> v = new ArrayList<String>();
		while (st.hasMoreElements()) {
			v.add((String) st.nextElement());
		}
		return (String[]) v.toArray(new String[v.size()]);
	}
}
