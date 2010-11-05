package org.ow2.aspirerfid.ide.bpwme.diagram.neweditor;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.ow2.aspirerfid.ide.bpwme.diagram.simpleditor.PathEditorInput;

public class NewInput implements IEditorInput{

	private PathEditorInput pei;
	private URIEditorInput uei;
	
	public NewInput() {
	}
	
	public void setPei(PathEditorInput pei) {
		this.pei = pei;
	}
	
	public void setUei(URIEditorInput uei) {
		this.uei = uei;
	}
	
	public PathEditorInput getPei() {
		return pei;
	}
	
	public URIEditorInput getUei() {
		return uei;
	}
	
	@Override
	public boolean exists() {
		// if both of the inputs exist
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		
		return null;
	}

	@Override
	public String getName() {
		
		return "New Editor";
	}

	@Override
	public IPersistableElement getPersistable() {
		
		return null;
	}

	@Override
	public String getToolTipText() {
		
		return "Combine Two Editors";
	}

	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

}
