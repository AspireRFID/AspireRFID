package org.ow2.aspirerfid.ide.bpwme.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.commands.CLCBProcCreateCommand;
import org.ow2.aspirerfid.ide.bpwme.diagram.providers.BpwmeElementTypes;


/**
 * @generated
 */
public class OLCBProcOLCBCompartmentItemSemanticEditPolicy extends
		BpwmeBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public OLCBProcOLCBCompartmentItemSemanticEditPolicy() {
		super(BpwmeElementTypes.OLCBProc_2001);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (BpwmeElementTypes.CLCBProc_3001 == req.getElementType()) {
			return getGEFWrapper(new CLCBProcCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
