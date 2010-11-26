package org.ow2.aspirerfid.ide.bpwme.diagram.preferences;

import java.util.ArrayList;
import java.util.HashSet;
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
	
	/**
	 * Add attribute to a given preference name. If the attribute already
	 * exist, add nothing.
	 * @param preferenceName
	 * @param attributeName
	 */
	public static void addAttribute(String preferenceName, String attributeName) {
		IPreferenceStore store = BpwmeDiagramEditorPlugin.getInstance().getPreferenceStore();
		String stringList = store.getString(preferenceName);
		StringTokenizer st = new StringTokenizer(stringList, "," + "\n\r");
		HashSet<String> h = new HashSet<String>();
		while (st.hasMoreElements()) {
			h.add((String) st.nextElement());
		}
		//if this is not new attribute
		if(h.contains(attributeName)) {
			return;
		}else {
			h.add(attributeName);
			StringBuffer sb = new StringBuffer();
			for(String attr:h) {
				sb.append(attr);
				sb.append(",");
			}
			store.setValue(preferenceName, sb.toString());
		}

	}
}
