/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Accada (www.accada.org).
 *
 * Accada is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Accada is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Accada; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package org.ow2.aspirerfid.reader.rp.impl.core.msg;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TextLexer;


/**
 * @author regli
 */
public class Context {
	
	private static final String COMMAND_PACKAGE = "org.ow2.aspirerfid.reader.rp.impl.core.msg.command";
	private static final String REPLY_PACKAGE = "org.ow2.aspirerfid.reader.rp.impl.core.msg.reply";
	private static final String NOTIFICATION_PACKAGE = "org.ow2.aspirerfid.reader.rp.impl.core.msg.notification";
	
	private static final String[] JAXB_PACKAGES = new String[]{COMMAND_PACKAGE, REPLY_PACKAGE, NOTIFICATION_PACKAGE};
	
	private static final int COMMAND_CONTEXT = 0;
	private static final int REPLY_CONTEXT = 1;
	private static final int NOTIFICATION_CONTEXT = 2;

	public static final Context COMMAND = new Context(COMMAND_CONTEXT);
	public static final Context REPLY = new Context(REPLY_CONTEXT);
	public static final Context NOTIFICATION = new Context(NOTIFICATION_CONTEXT);

	private final int context;
	private JAXBContext jaxbcontext = null;

	private Context(final int context) {
		this.context = context;
		try {
         JAXBContext.newInstance(JAXB_PACKAGES[context]);
      } catch (JAXBException e) {
         // failed, try at getJAXBContext() call
      }
	}
	
	public JAXBContext getJAXBContext() throws JAXBException {
      if (jaxbcontext == null) {
			jaxbcontext = JAXBContext.newInstance(JAXB_PACKAGES[context], getClass().getClassLoader());
		
      }
		return jaxbcontext;
	}
	
}