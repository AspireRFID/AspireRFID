package org.ow2.aspirerfid.ide.bpwme.ecspec.views;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.ow2.aspirerfid.commons.apdl.model.EBProc;
import org.ow2.aspirerfid.ide.bpwme.ecspec.utils.ECSpecBuilder;
import org.ow2.aspirerfid.ide.bpwme.ecspec.utils.LRSpecBuilder;

public class ECLRInput implements IEditorInput{

	private EBProc ebproc;
	private LRSpecBuilder lrsb;
	private ECSpecBuilder ecsb;
	
	public void setEBProc(EBProc ebp) {
		this.ebproc = ebp;
		this.lrsb.setEBProc(ebp);
		this.ecsb.setEBProc(ebp);
	}
	
	public ECLRInput(EBProc ebp) {
		this.ebproc = ebp;
		this.lrsb = new LRSpecBuilder(ebp);
		this.ecsb = new ECSpecBuilder(ebp);
	}
	
	
	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return "EC LR Editor";
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return "";
	}

	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}
	
	public LRSpecBuilder getLRSpecBuilder() {
		return lrsb;
	}
	
	public ECSpecBuilder getECSpecBuilder() {
		return ecsb;
	}
	
	public EBProc getEbproc() {
		return ebproc;
	}

}
