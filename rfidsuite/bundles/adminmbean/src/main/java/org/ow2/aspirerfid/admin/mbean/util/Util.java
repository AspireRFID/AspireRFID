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
package org.ow2.aspirerfid.admin.mbean.util;

import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 * @version 2007
 */
public class Util {
	/**
	 * @param bundle
	 * @return TODO Javadoc
	 */
	public static String getBundleName(Bundle bundle) {
		if (bundle != null) {
			String name = (String) bundle.getHeaders().get(
					Constants.BUNDLE_NAME);
			return (name == null) ? "Bundle "
					+ Long.toString(bundle.getBundleId()) : name + " ("
					+ Long.toString(bundle.getBundleId()) + ")";
		}
		return "[STALE BUNDLE]";
	}

	private static StringBuffer m_sb = new StringBuffer();

	/**
	 * @param s
	 * @return TODO Javadoc
	 */
	public static String getUnderlineString(String s) {
		synchronized (m_sb) {
			m_sb.delete(0, m_sb.length());
			for (int i = 0; i < s.length(); i++) {
				m_sb.append('-');
			}
			return m_sb.toString();
		}
	}

	/**
	 * @param obj
	 * @return TODO Javadoc
	 */
	public static String getValueString(Object obj) {
		synchronized (m_sb) {
			if (obj instanceof String) {
				return (String) obj;
			} else if (obj instanceof String[]) {
				String[] array = (String[]) obj;
				m_sb.delete(0, m_sb.length());
				for (int i = 0; i < array.length; i++) {
					if (i != 0) {
						m_sb.append(", ");
					}
					m_sb.append(array[i].toString());
				}
				return m_sb.toString();
			} else if (obj instanceof Boolean) {
				return ((Boolean) obj).toString();
			} else if (obj instanceof Long) {
				return ((Long) obj).toString();
			} else if (obj instanceof Integer) {
				return ((Integer) obj).toString();
			} else if (obj instanceof Short) {
				return ((Short) obj).toString();
			} else if (obj instanceof Double) {
				return ((Double) obj).toString();
			} else if (obj instanceof Float) {
				return ((Float) obj).toString();
			}

			return "<unknown value type>";
		}
	}
}