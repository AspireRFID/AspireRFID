package org.ow2.aspirerfid.ide.bpwme.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.commands.EBProcCreateCommand;
import org.ow2.aspirerfid.ide.bpwme.diagram.providers.BpwmeElementTypes;


/**
 * @generated
 */
public class CLCBProcCLCBCompartmentItemSemanticEditPolicy extends
		BpwmeBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public CLCBProcCLCBCompartmentItemSemanticEditPolicy() {
		super(BpwmeElementTypes.CLCBProc_3001);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (BpwmeElementTypes.EBProc_3002 == req.getElementType()) {
			//
			return getGEFWrapper(new EBProcCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
