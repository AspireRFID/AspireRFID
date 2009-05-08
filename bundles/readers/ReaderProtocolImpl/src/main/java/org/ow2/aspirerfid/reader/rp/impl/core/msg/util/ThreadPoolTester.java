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

package org.ow2.aspirerfid.reader.rp.impl.core.msg.util;
//TODO: auslagern in JUnit test case
public class ThreadPoolTester implements Runnable {

	public void run() {
		System.out.println("Thread is running.");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread stopped.");
	}
	
	public static void main(String[] args) {
		ThreadPool pool = new ThreadPool(2);
		ThreadPoolTester t1 = new ThreadPoolTester();
		ThreadPoolTester t2 = new ThreadPoolTester();
		ThreadPoolTester t3 = new ThreadPoolTester();
		try {
			pool.execute(t1);
			pool.execute(t2);
			pool.execute(t3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
