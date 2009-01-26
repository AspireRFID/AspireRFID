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
package org.ow2.aspirerfid.app.epcis.client.util;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * Util for GWT application.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class Util {

	//
	// Default image URL
	//
	/**
	 * TODO Javadoc
	 */
	public static final String ADD_IMG_URL = "";
	/**
	 * TODO Javadoc
	 */
	public static final String MODIFY_IMG_URL = "img/modify.gif";
	/**
	 * TODO Javadoc
	 */
	public static final String DELETE_IMG_URL = "img/ico_delete.gif";
	/**
	 * TODO Javadoc
	 */
	public static final String LOADING_IMG_URL = "img/loading_2.gif";

	//
	//
	//
	/**
	 * TODO Javadoc
	 */
	public static final HTML NEW_LINE = new HTML("<br />");

	//
	// Internals variables
	//
	/**
	 * TODO Javadoc
	 */
	private static final Image loadingMessage = new Image(LOADING_IMG_URL);

	//
	// Defaults methods
	// 
	/**
	 * Default AsyncCallback.onFailure
	 * 
	 * @param caught
	 *            Exception
	 */
	public static void defaultOnFailure(Throwable caught) {
		caught.printStackTrace();
		System.out.println("failure: " + caught.getMessage());
	}

	/**
	 * Default loading message
	 * 
	 * @return loading widget
	 */
	public static Widget defaultLoadingMessage() {
		loadingMessage.setTitle("Loading, please wait");
		loadingMessage.setStyleName("loading");
		return loadingMessage;
	}

	/**
	 * TODO Javadoc
	 */
	public static AsyncCallback nullCallback = new AsyncCallback() {

		public void onFailure(Throwable caught) {
		}

		public void onSuccess(Object result) {
		}

	};
}
