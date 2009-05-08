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

import org.ow2.aspirerfid.reader.rp.impl.core.DataSelector;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.ReadReport;

/**
 * <code>NotificationListener</code> is an interface that has to be
 * implemented by all classes that are interested in the notification messages.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 */
public interface NotificationListener {

	/**
	 * This method is called by the reader core to notify a listener that there
	 * is a new message.
	 * 
	 * @param report
	 *            The <code>ReadReport</code> to send to the Host.
	 * @param notificationChannelName
	 *            The name of the <code>NotificationChannel</code> which is
	 *            used to send the <code>ReadReport</code> to the Host.
	 * @param ds The DataSelector used for filtering the report.
	 */
	public void notifyHost(ReadReport report, String notificationChannelName,
			DataSelector ds);

}
