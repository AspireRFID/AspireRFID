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

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class Parent extends Child
{
    private Child[] children = new Child[0];
    private Object rootElement;

    /**
     * Constructor 
     */
    public Parent(String name)
    {
        super(name);
    }

    public void setChildren(Child[] children)
    {
        if (children != null)
        {
            setChildrensParent(null, this.children);
        }
        this.children = children;
        setChildrensParent(this, this.children);
    }

    /**
     * Sets children's parent
     * @param parent parent to be set
     * @param children children to set the parent
     */
    private static void setChildrensParent(Parent parent, Child[] children)
    {
        for (int i = 0; i < children.length; i++)
        {
            children[i].setParent(parent);
        }
    }

    // getter and setter
    public Child[] getChildren()
    {
        return children;
    }

    public void setRoot(Object parentElement)
    {
        this.rootElement = parentElement;
    }

    public Object getRoot()
    {
        return rootElement;
    }

}
