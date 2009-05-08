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

package org.ow2.aspirerfid.reader.rp.impl.core.msg.transport;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.util.ThreadPool;

/**
 * This is the thread pool used by all connections.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 *
 */
public class ConnectionThreadPool extends ThreadPool{
	
	public static int DEFAULT_NUM_OF_THREADS = 16;

	private static ConnectionThreadPool instance;
	
	public static void create(int numOfWorkers) {
		if (instance == null) {
			instance = new ConnectionThreadPool(numOfWorkers);
		}
	}
	
	public static ConnectionThreadPool getInstance() {
		if (instance == null) {
			create(DEFAULT_NUM_OF_THREADS);
		}
		return instance;
	}
	
	private ConnectionThreadPool(int numOfWorkers) {
		super(numOfWorkers);
	}
}
