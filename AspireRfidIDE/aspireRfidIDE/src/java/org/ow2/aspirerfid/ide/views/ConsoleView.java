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

package org.ow2.aspirerfid.ide.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;


/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class ConsoleView extends ViewPart {

	
	public static final String ID = "org.ow2.aspirerfid.ide.views.ConsoleView"; //$NON-NLS-1$

	private StyledText debugStyledText;
	
	/**
	 * Create contents of the view part
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

		debugStyledText = new StyledText(container, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);

		// =========override System.out.print to print in the DebugConsoleView======
		OutputStream out = new OutputStream() {
			private StringBuffer buffer = new StringBuffer();
			private final Object obj = new Object();

			@Override
			public void write(final int b) throws IOException {
				synchronized (obj) {
					if (debugStyledText.isDisposed())
						return;

					buffer.append((char) b);
				}
			}
			@Override
			public void write(byte[] b, int off, int len) throws IOException {
				super.write(b, off, len);
				flush();
			}
			@Override
			public void flush() throws IOException {
				synchronized (obj) {
					final String newText = buffer.toString();
					debugStyledText.append(newText);
//					debugStyledText.setCaretOffset(debugStyledText.getCharCount());

					buffer = new StringBuffer();
				}
			}

		};
		final PrintStream oldOut = System.out;
		System.setOut(new PrintStream(out));
		debugStyledText.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				System.setOut(oldOut);
			}
		});
		// ==================================================================
		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

}
