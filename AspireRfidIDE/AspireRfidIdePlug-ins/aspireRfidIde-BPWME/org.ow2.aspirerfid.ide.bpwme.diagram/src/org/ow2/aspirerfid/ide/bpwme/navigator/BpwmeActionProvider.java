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

package org.ow2.aspirerfid.ide.bpwme.navigator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

/**
 * Action provider for the navigator
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class BpwmeActionProvider extends CommonActionProvider
{

    private OpenChildAction openAction;


    public BpwmeActionProvider()
    {

    }

    @Override
    public void init(ICommonActionExtensionSite site)
    {
        ICommonViewerSite viewSite = site.getViewSite();
        if (viewSite instanceof ICommonViewerWorkbenchSite) 
        {
            ICommonViewerWorkbenchSite workbenchSite = (ICommonViewerWorkbenchSite) viewSite;
            openAction = new OpenChildAction(workbenchSite.getPage(), workbenchSite.getSelectionProvider());
        }
    }
    
    
    

    @Override
    public void restoreState(IMemento memento)
    {
        super.restoreState(memento);
    }

    @Override
    public void saveState(IMemento memento)
    {
        super.saveState(memento);
    }

    @Override
    public void fillActionBars(IActionBars actionBars)
    {
        if (openAction.isEnabled())
        {
            actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openAction);
        }
    }
    
    @Override
    public void fillContextMenu(IMenuManager menu)
    {
        if (openAction.isEnabled())
        {
            menu.appendToGroup(ICommonMenuConstants.GROUP_OPEN, openAction);
        }
    }

    
    /**
     * 
     * 
     * 
     */
    class OpenChildAction extends Action
    {
        private ISelectionProvider provider;
        private Child data;

        public OpenChildAction(IWorkbenchPage workbenchPage, ISelectionProvider selectionProvider)
        {
            super("Open item");
            provider = selectionProvider;
        }
        

        @Override
        public void run()
        {
            
            if (data != null)
            {
                System.out.println("Run on " + data.getName());
            } 
            super.run();
        }

        @Override
        public boolean isEnabled()
        {
            ISelection selection = provider.getSelection();
            if (!selection.isEmpty())
            {
                IStructuredSelection sSelection = (IStructuredSelection) selection;
                if (sSelection.size() == 1 && sSelection.getFirstElement() instanceof Child) 
                {
                    data = (Child)sSelection.getFirstElement();
                    return true;
                }
            }
            return false;
        }

        
        
    }


}
