package org.ow2.aspirerfid.ide.bpwme.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.ow2.aspirerfid.ide.bpwme.diagram.providers.BpwmeElementTypes;


/**
 * @generated
 */
public class ConnectionItemSemanticEditPolicy extends
		BpwmeBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ConnectionItemSemanticEditPolicy() {
		super(BpwmeElementTypes.Connection_4001);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}
