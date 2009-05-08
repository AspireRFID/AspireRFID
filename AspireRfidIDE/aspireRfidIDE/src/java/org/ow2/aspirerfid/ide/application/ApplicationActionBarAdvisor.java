/*
 * Copyright © 2008-2010, Aspire
 * 
 * Aspire is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 2.1 as published by
 * the Free Software Foundation (the "LGPL").
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library in the file COPYING-LGPL-2.1; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public License
 * for the specific language governing rights and limitations.
 */

package org.ow2.aspirerfid.ide.application;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;


/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

//    private IWorkbenchAction showViewMenuAction;
//    private IWorkbenchAction preferencesAction;
    private IWorkbenchAction introAction;
    
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window) {
		
//		{
//		introAction = ActionFactory.INTRO.create(window);
//		register(introAction);
//		}
		
//		{
//			preferencesAction = ActionFactory.PREFERENCES.create(window);
//			register(preferencesAction);
//		}
//		{
//			showViewMenuAction = ActionFactory.SHOW_VIEW_MENU.create(window);
//			register(showViewMenuAction);
//		}
		
		
		super.makeActions(window);

		// register actions
		register(ActionFactory.SAVE.create(window));
		register(ActionFactory.SAVE_AS.create(window));
		register(ActionFactory.SAVE_ALL.create(window));

		register(ActionFactory.CLOSE.create(window));
		register(ActionFactory.CLOSE_ALL.create(window));

		register(ActionFactory.PRINT.create(window));

		register(ActionFactory.QUIT.create(window));

		register(ActionFactory.UNDO.create(window));
		register(ActionFactory.REDO.create(window));

		register(ActionFactory.CUT.create(window));
		register(ActionFactory.COPY.create(window));
		register(ActionFactory.PASTE.create(window));

		register(ActionFactory.DELETE.create(window));
		register(ActionFactory.SELECT_ALL.create(window));

		register(ActionFactory.OPEN_NEW_WINDOW.create(window));

		register(ActionFactory.PREFERENCES.create(window));
		
	}
	
	
	


	protected void fillMenuBar(IMenuManager menuBar) {
		
//		MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
//		menuBar.add(helpMenu);
//		
//		helpMenu.add(introAction);

		
		
		
//		final MenuManager windowMenu = new MenuManager("&Window",IWorkbenchActionConstants.M_WINDOW);
//		menuBar.add(windowMenu);

//		windowMenu.add(showViewMenuAction);

//		windowMenu.add(new Separator());

//		windowMenu.add(preferencesAction);
	//	windowMenu.()
	}

}
