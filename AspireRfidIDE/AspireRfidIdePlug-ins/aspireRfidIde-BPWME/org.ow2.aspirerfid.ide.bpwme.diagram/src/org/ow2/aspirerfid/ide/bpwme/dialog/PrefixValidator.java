package org.ow2.aspirerfid.ide.bpwme.dialog;

import org.eclipse.jface.dialogs.IInputValidator;

public class PrefixValidator implements IInputValidator {

	private String prefix;
	
	public PrefixValidator(String prefix) {
		this.prefix = prefix;
	}
	
	@Override
	public String isValid(String newText) {
		if (newText.startsWith(prefix)) {
			return null;
		}else {
			return "Should start with " + prefix;
		}
	}

}
