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
package org.ow2.aspirerfid.common.cron.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * The class CronParse is used to treat a string containing time information
 * written in a cron format. To have more information on the cron format read
 * the help furnish with the schedule bundle
 * 
 * @author Unknown
 * @version 2006
 */
public class CronParse {
	/**
	 * Array use to convert any unity of time in milliseconds
	 */
	private final long[] CONVERTTAB = { 1, 1000, 60000, 3600000, 86400000,
			86400000, 86400000 * 31, 86400000 * 31 * 365 };

	/**
	 * Array containing the number of day per month
	 */
	private final int[] DAY_PER_MONTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30,
			31, 30, 31 };

	private short[] msec;
	private short[] sec;
	private short[] min;
	private short[] hour;
	private short[] dayw;
	private short[] daym;
	private short[] month;
	private short[] year;

	long start, last = 0;
	/**
	 * The interval between two doReact
	 */
	long delay = 0;
	/** The position of the Pipe character in the cron String */
	short posPipe = 0;

	/**
	 * This method take all the elements of a unity information in the cron
	 * string and set them in an array of the right size
	 * 
	 * @param str
	 *            the string containing the elements to set in the array
	 * @param t
	 *            the type of value (0 ms, 1 s, 2 min, 3 h, 4 dayw, 5 daym, 6
	 *            month, 7 year)
	 * @return an array containing all the elements
	 */
	private short[] unityTreatment(String str, int t) {
		// Construct an array of the right size
		short[] tab = new short[countTermNumber(str)];
		int ptTab = 0;
		// Tokenize all elements separated by ','
		StringTokenizer stok = new StringTokenizer(str, ",");
		while (stok.hasMoreTokens()) {
			String vartemp = stok.nextToken();
			// A '*' means there is no condition so just return null
			if (vartemp.compareTo("*") == 0)
				return null;
			// Look for a '-' character
			int position = vartemp.indexOf('-');
			// if there is no '-'
			if (position == -1) {
				// There is just an element to count
				tab[ptTab] = Short.parseShort(vartemp);
				// Test this element function of the unity
				if (!test(tab[ptTab], t)) {
					System.out.println("Bad format : " + t);
					return null;
				}
				ptTab++;
				// If there is a '-' there is an interval
			} else {
				// Get the higher value of the interval
				short hight = Short.parseShort(vartemp.substring(position + 1));
				// Get the lowest value of the interval
				short low = Short.parseShort(vartemp.substring(0, position));
				// Compute the difference
				short delta = (short) (hight - low + 1);
				// Save all the element contained in the interval in the array
				for (short i = 0; i < delta; i++) {
					tab[ptTab] = (short) (low + i);
					// Test this element function of the unity
					if (!test(tab[ptTab], t)) {
						System.out.println("bad format : " + t);
						return null;
					}
					ptTab++;
				}
			}
		}
		// Return the array containing all the elements of a unity type
		return tab;
	}

	/**
	 * This method count the terms present in a string<br/> (ex : 2,3,5,6 --> 4
	 * terms<br/> 2-5 --> 4 terms<br/> 2,7-9 --> 4 terms)
	 * 
	 * @param str
	 *            the string to parse and count terms
	 * @return the number of terms in the string
	 */
	private int countTermNumber(String str) {
		int cpt = 0;
		// Tokenize all elements separated by ','
		StringTokenizer stok = new StringTokenizer(str, ",");
		while (stok.hasMoreTokens()) {
			String vartemp = stok.nextToken();
			// Look for the '-' character
			int position = vartemp.indexOf('-');
			// If there is no '-' character
			if (position == -1) {
				cpt++;
				// If the '-' character is present
			} else {
				// Get the higher value
				int hight = Integer.parseInt(vartemp.substring(position + 1));
				// Get the lower value
				int low = Integer.parseInt(vartemp.substring(0, position));
				// Compute the difference
				cpt = cpt + (hight - low) + 1;
			}
		}
		// Return the number of elements
		return cpt;
	}

	/**
	 * Class Constructor
	 * 
	 * @param cron
	 *            the string containing the time information in a cron format
	 */
	public CronParse(String cron) {
		// Get the current time
		start = System.currentTimeMillis();
		short cpt = 0;
		// Tokenize elements separated by ' '
		StringTokenizer stok = new StringTokenizer(cron, " ");
		while (stok.hasMoreTokens()) {
			String vartemp = stok.nextToken();
			// If it is the pipe character
			if (vartemp.compareTo("|") == 0) {
				// save his position
				posPipe = cpt;
				continue;
			}
			// In other cases treat the element function of its position
			// which reflect its unity
			switch (cpt) {
			case 0:
				msec = unityTreatment(vartemp, 0);
				break;
			case 1:
				sec = unityTreatment(vartemp, 1);
				break;
			case 2:
				min = unityTreatment(vartemp, 2);
				break;
			case 3:
				hour = unityTreatment(vartemp, 3);
				break;
			case 4:
				dayw = unityTreatment(vartemp, 4);
				break;
			case 5:
				daym = unityTreatment(vartemp, 5);
				break;
			case 6:
				month = unityTreatment(vartemp, 6);
				break;
			case 7:
				year = unityTreatment(vartemp, 7);
				break;
			}
			cpt++;
		}
		// Compute the interval between two doReact
		// Add all elements located before the pipe
		// So go from the beginning to the pipe position
		for (int i = 0; i < posPipe; i++) {
			short val = 0;
			switch (i) {
			case 0:
				if (msec != null)
					val = msec[0];
				break;
			case 1:
				if (sec != null)
					val = sec[0];
				break;
			case 2:
				if (min != null)
					val = min[0];
				break;
			case 3:
				if (hour != null)
					val = hour[0];
				break;
			case 4:
				if (dayw != null)
					val = dayw[0];
				break;
			case 5:
				if (daym != null)
					val = daym[0];
				break;
			case 6:
				if (month != null)
					val = month[0];
				break;
			case 7:
				if (year != null)
					val = year[0];
				break;
			}
			// Convert value in millisecond and and it to
			// the delay between two doReact
			delay += val * CONVERTTAB[i];
		}
	}

	/**
	 * This method looks for the existence of a value in an array
	 * 
	 * @param tab
	 *            the array to go through
	 * @param val
	 *            the value we are looking for
	 * @return true if the value exists in the array, false in all other cases
	 */
	private boolean isNotIn(short[] tab, int val) {
		// If no array is present, nothing to compute like
		// the case the value is in the array
		if (tab == null)
			return false;
		// Go through the array to find the value
		for (int i = 0; i < tab.length; i++) {
			// If the value is found return false
			if (tab[i] == val)
				return false;
		}
		// In all other cases return false
		return true;
	}

	/**
	 * This method look in an array that is classified in ascending order which
	 * what is the next superior value knowing the actual one
	 * 
	 * @param tab
	 *            the array to go through
	 * @param val
	 *            the actual value of reference
	 * @return the next value superior at the reference value
	 */
	private int lookForNext(short[] tab, int val) {
		// Go through the array
		for (int i = 0; i < tab.length; i++) {
			// If an array value is higher than the
			// reference value return this value
			if (tab[i] > val)
				return tab[i];
		}
		// In worst case return the first value of the array
		return tab[0];
	}

	/**
	 * This method return the next time the action must be performed
	 * 
	 * @return the time in milliseconds when to perform the action
	 */
	public long getNext() {
		// Modify the last value, setting it to the next react
		if (last == 0) {
			last = start + delay;
		} else {
			last += delay;
		}

		// Create a calendar and set it to the next react action time
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(new Date(last));

		// Function of the Pipe position, check if the next react time value
		// is allowed by the condition set by the user
		// If this time is allowed, the values are in each unity arrays
		// The test on delay for each case is used to just make a react
		// one time when it is ask to react at a precise time
		switch (posPipe) {
		case 0:
			// Check if milliseconds are in the array, if it isn't
			// the enter the block to know when is the next allowed time
			if (isNotIn(msec, cal.get(Calendar.MILLISECOND)) || (delay == 0)) {
				// Look for next allowed time
				int next = lookForNext(msec, cal.get(Calendar.MILLISECOND));
				// Compute to know how many unity must be add to find
				// the next value
				if (next < cal.get(Calendar.MILLISECOND)) {
					next = (999 - cal.get(Calendar.MILLISECOND) + 1 + next);
				} else {
					next = next - cal.get(Calendar.MILLISECOND);
				}
				// If the value must be polled just at one time, this test avoid
				// the react repetition during all the allowed time
				// So we add the milliseconds to go to next react
				if (delay == 0 && next == 0) {
					last += 1000;
				} else {
					last += (next * 1);
				}
				// Set the calendar to the time computed to avoid
				// the reserve problem
				cal.setTime(new Date(last));
			}
		case 1:
			// Check for seconds (see milliseconds)
			if (isNotIn(sec, cal.get(Calendar.SECOND))
					|| (posPipe == 1 && delay == 0)) {
				int next = lookForNext(sec, cal.get(Calendar.SECOND));
				if (next < cal.get(Calendar.SECOND)) {
					next = (59 - cal.get(Calendar.SECOND) + 1 + next);
				} else {
					next = next - cal.get(Calendar.SECOND);
				}
				if (delay == 0 && next == 0) {
					last += 60000;
				} else {
					last += (next * 1000);
				}
				cal.setTime(new Date(last));
			}
		case 2:
			// Check for minutes (see milliseconds)
			if (isNotIn(min, cal.get(Calendar.MINUTE))
					|| (posPipe == 2 && delay == 0)) {
				int next = lookForNext(min, cal.get(Calendar.MINUTE));
				if (next < cal.get(Calendar.MINUTE)) {
					next = (59 - cal.get(Calendar.MINUTE) + 1 + next);
				} else {
					next = next - cal.get(Calendar.MINUTE);
				}
				if (delay == 0 && next == 0) {
					last += 3600000;
				} else {
					last += (next * 60000);
					if (posPipe == 2) {
						last -= (cal.get(Calendar.SECOND) * 1000);
					}
				}
				cal.setTime(new Date(last));
			}
		case 3:
			// Check for hours (see milliseconds)
			if (isNotIn(hour, cal.get(Calendar.HOUR_OF_DAY))
					|| (posPipe == 3 && delay == 0)) {
				int next = lookForNext(hour, cal.get(Calendar.HOUR_OF_DAY));
				if (next < cal.get(Calendar.HOUR_OF_DAY)) {
					next = (23 - cal.get(Calendar.HOUR_OF_DAY) + 1 + next);
				} else {
					next = next - cal.get(Calendar.HOUR_OF_DAY);
				}
				if (delay == 0 && next == 0) {
					last += 86400000;
				} else {
					last += (next * 3600000);
					if (posPipe == 3) {
						last -= (cal.get(Calendar.MINUTE) * 60000);
						last -= (cal.get(Calendar.SECOND) * 1000);
					}
				}
				cal.setTime(new Date(last));
			}
		case 4:
			// Check for dayw (see milliseconds)
			if (isNotIn(dayw, cal.get(Calendar.DAY_OF_WEEK))
					|| (posPipe == 4 && delay == 0)) {
				int next = lookForNext(dayw, cal.get(Calendar.DAY_OF_WEEK));
				if (next < cal.get(Calendar.DAY_OF_WEEK)) {
					next = (7 - cal.get(Calendar.DAY_OF_WEEK) + next);
				} else {
					next = next - cal.get(Calendar.DAY_OF_WEEK);
				}
				if (delay == 0 && next == 0) {
					last += 86400000 * 7;
				} else {
					last += (next * 86400000);
					if (posPipe == 4) {
						last -= (cal.get(Calendar.HOUR_OF_DAY) * 3600000);
						last -= (cal.get(Calendar.MINUTE) * 60000);
						last -= (cal.get(Calendar.SECOND) * 1000);
					}
				}
				cal.setTime(new Date(last));
			}
		case 5:
			// Check for daym (see milliseconds)
			if (isNotIn(daym, cal.get(Calendar.DAY_OF_MONTH))
					|| (posPipe == 5 && delay == 0)) {
				int next = lookForNext(daym, cal.get(Calendar.DAY_OF_MONTH));
				if (next < cal.get(Calendar.DAY_OF_MONTH)) {
					if (isLeapYear(cal.get(Calendar.YEAR))
							&& cal.get(Calendar.MONTH) == 1)
						next = (DAY_PER_MONTH[cal.get(Calendar.MONTH)] + 1
								- cal.get(Calendar.DAY_OF_MONTH) + next);
					else
						next = (DAY_PER_MONTH[cal.get(Calendar.MONTH)]
								- cal.get(Calendar.DAY_OF_MONTH) + next);
				} else {
					next = next - cal.get(Calendar.DAY_OF_MONTH);
				}
				if (delay == 0 && next == 0) {
					last += 86400000 * DAY_PER_MONTH[cal.get(Calendar.MONTH)];
				} else {
					last += (next * 86400000);
					if (posPipe == 5) {
						last -= (cal.get(Calendar.HOUR_OF_DAY) * 3600000);
						last -= (cal.get(Calendar.MINUTE) * 60000);
						last -= (cal.get(Calendar.SECOND) * 1000);
					}
				}
				cal.setTime(new Date(last));
			}
		case 6:
			// Check for month (see milliseconds)
			if (isNotIn(month, cal.get(Calendar.MONTH))
					|| (posPipe == 6 && delay == 0)) {
				int next = lookForNext(month, cal.get(Calendar.MONTH));
				if (next < cal.get(Calendar.MONTH)) {
					next = (11 - cal.get(Calendar.MONTH) + next);
				} else {
					next = next - cal.get(Calendar.MONTH);
				}
				if (delay == 0 && next == 0) {
					last += 86400000 * 365;
				} else {
					last += (long) (next * 86400000 * 31);
					if (posPipe == 6) {
						last -= (cal.get(Calendar.DAY_OF_MONTH) * 86400000);
						last -= (cal.get(Calendar.HOUR_OF_DAY) * 3600000);
						last -= (cal.get(Calendar.MINUTE) * 60000);
						last -= (cal.get(Calendar.SECOND) * 1000);
					}
				}
				cal.setTime(new Date(last));
			}
		case 7:
			// Check for year (see milliseconds)
			if (isNotIn(year, cal.get(Calendar.YEAR))
					|| (posPipe == 7 && delay == 0)) {
				int next = lookForNext(year, cal.get(Calendar.YEAR));
				if (next < cal.get(Calendar.YEAR)) {
					System.out.println("Bad Year");
				} else {
					next = next - cal.get(Calendar.YEAR);
				}
				if (delay == 0 && next == 0) {
					last += 86400000 * 365;
				} else {
					last += (long) (next * 86400000 * 31 * 12);
					if (posPipe == 7) {
						last -= (cal.get(Calendar.DAY_OF_MONTH) * 86400000 * 31);
						last -= (cal.get(Calendar.DAY_OF_MONTH) * 86400000);
						last -= (cal.get(Calendar.HOUR_OF_DAY) * 3600000);
						last -= (cal.get(Calendar.MINUTE) * 60000);
						last -= (cal.get(Calendar.SECOND) * 1000);
					}
				}
			}
			break;
		default:
			System.out.println("Bad value for Pipe position");
		}
		// System.out.println("**** Scheduler get next : "+last+" ****");
		return last;

	}

	/**
	 * Test the validity a time informations depending of the unity
	 * 
	 * @param s
	 *            the value to test
	 * @param t
	 *            the type of value (0 ms, 1 s, 2 min, 3 h, 4 dayw, 5 daym, 6
	 *            month, 7 year)
	 * @return true if the value is correct, false in all other cases
	 */
	private boolean test(short s, int t) {

		switch (t) {
		// Test for milliseconds
		case 0:
			if (s < 0 || s > 999)
				return false;
			return true;
			// Test for seconds
		case 1:
			if (s < 0 || s > 59)
				return false;
			return true;
			// Test for minutes
		case 2:
			if (s < 0 || s > 59)
				return false;
			return true;
			// Test for hours
		case 3:
			if (s < 0 || s > 23)
				return false;
			return true;
			// Test for week days
		case 4:
			if (s < 1 || s > 7)
				return false;
			return true;
			// Test for month days
		case 5:
			if (s < 1 || s > 31)
				return false;
			return true;
			// Test for months
		case 6:
			if (s < 0 || s > 11)
				return false;
			return true;
			// Test for years
		case 7:
			if (s < 2003 || s > 3000)
				return false;
			return true;
		default:
			return false;
		}

	}

	/**
	 * This method is used to know is a year is a leap year or not
	 * 
	 * @param y
	 *            the year to test
	 * @return true if the year is a leap year, false in all other cases
	 */
	boolean isLeapYear(int y) {
		return ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0);
	}

}
