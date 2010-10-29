package org.ow2.aspirerfid.ide.bpwme.utils;

import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditor;

public class EditorListener implements IPartListener2{

	private static EditorListener instance = new EditorListener();
	
	public static EditorListener getInstance() {
		return instance;
	}
	
	public EditorListener() {

	}
	
	@Override
	public void partActivated(IWorkbenchPartReference partRef) {
		
	}

	@Override
	public void partBroughtToTop(IWorkbenchPartReference partRef) {
		
	}

	@Override
	public void partClosed(IWorkbenchPartReference partRef) {
		if(partRef.getId().equals(BpwmeDiagramEditor.ID)) {
			//close the other editors with it
			partRef.getPage().closeAllEditors(true);
		}
	}

	@Override
	public void partDeactivated(IWorkbenchPartReference partRef) {
		
	}

	@Override
	public void partHidden(IWorkbenchPartReference partRef) {
		
	}

	@Override
	public void partInputChanged(IWorkbenchPartReference partRef) {
		
	}

	@Override
	public void partOpened(IWorkbenchPartReference partRef) {
		
	}

	@Override
	public void partVisible(IWorkbenchPartReference partRef) {
		
	}

}
