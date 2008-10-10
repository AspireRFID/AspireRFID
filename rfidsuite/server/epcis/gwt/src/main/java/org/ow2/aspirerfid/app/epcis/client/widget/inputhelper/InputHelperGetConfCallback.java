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

import java.util.HashMap;

import org.ow2.aspirerfid.app.epcis.client.IndexModule;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 */
public class InputHelperGetConfCallback implements AsyncCallback {

	private InputHelper inputHelper;

	/**
	 * TODO Javadoc
	 * 
	 * @param ih
	 */
	public InputHelperGetConfCallback(InputHelper ih) {
		inputHelper = ih;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
	 */
	public void onFailure(Throwable caught) {
		IndexModule.getKikooBox().showError(
				"Input Helper : Get configuration failed", 2000);
		inputHelper.display(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
	 */
	public void onSuccess(Object result) {
		inputHelper.display((HashMap) result);
	}

}
