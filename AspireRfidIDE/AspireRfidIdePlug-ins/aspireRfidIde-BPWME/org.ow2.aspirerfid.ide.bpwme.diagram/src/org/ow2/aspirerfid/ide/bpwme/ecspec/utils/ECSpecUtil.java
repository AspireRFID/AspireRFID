package org.ow2.aspirerfid.ide.bpwme.ecspec.utils;

import org.eclipse.ui.IEditorPart;
import org.ow2.aspirerfid.ide.bpwme.ecspec.views.ECSpecEditor;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;

public class ECSpecUtil {
	public static void setECEditorDirty() {
		IEditorPart editor = MainUtil.getEditor("org.ow2.aspirerfid.ide.bpwme.ecspec.views.ECSpecEditor");
		if(editor == null) {
			return;
		}
		ECSpecEditor ese = (ECSpecEditor)editor;
		ese.setDirty(true);
	}

}
