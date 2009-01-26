/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.app.epcis.client.widget.inputhelper;

import org.ow2.aspirerfid.app.epcis.client.widget.tags.TagInput;

import asquare.gwt.tk.client.ui.ModalDialog;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class InputHelperButtonOpenClickListener implements ClickListener {

	private TagInput tagInput;

	/**
	 * TODO Javadoc
	 * 
	 * @param tagInput
	 */
	public InputHelperButtonOpenClickListener(TagInput tagInput) {
		this.tagInput = tagInput;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
	 */
	public void onClick(Widget sender) {

		ModalDialog dialog = new ModalDialog();
		dialog.setCaption("Tag Input Helper : ", false);
		dialog.show();

		dialog.add(new InputHelper(dialog, tagInput));

		// dialog.setSize("300px", "150px");
		// RootPanel.get().getOffsetWidth()/2,
		// RootPanel.get().getOffsetHeight()/2
		// );

	}

}
