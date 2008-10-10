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
package org.ow2.aspirerfid.app.epcis.client.widget;

import java.util.Date;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.thapar.gwt.user.ui.client.util.DateFormat;
import com.thapar.gwt.user.ui.client.widget.simpledatepicker.DateFormatter;
import com.thapar.gwt.user.ui.client.widget.simpledatepicker.SimpleDatePicker;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 */
public class IntervalSelector extends Composite {

	private SimpleDatePicker dateBegin;

	private SimpleDatePicker dateEnd;

	private CheckBox cbLive;

	private HoursSelector beginHours;

	private HoursSelector endHours;

	private ListBox timerIntervalSelector;

	private FlexTable ftDateSelection;

	private Timer requestTimer;

	private DateFormatter dateFormatter;

	/**
	 * TODO Javadoc
	 */
	public IntervalSelector() {

		dateFormatter = new DateFormatter(DateFormat.DATE_FORMAT_MMDDYYYY);

		timerIntervalSelector = new ListBox();
		timerIntervalSelector.addItem("10 sec", "10");
		timerIntervalSelector.addItem("20 sec", "20");
		timerIntervalSelector.addItem("60 sec", "60");
		timerIntervalSelector.addItem("5 min", "300");
		timerIntervalSelector.setItemSelected(1, true);
		timerIntervalSelector.setEnabled(false);

		ftDateSelection = new FlexTable();

		Date today = new Date();

		ftDateSelection.setText(1, 1, "Beginning date : ");
		dateBegin = new SimpleDatePicker();
		dateBegin.setDateFormat(DateFormat.DATE_FORMAT_MMDDYYYY);
		dateBegin.setWeekendSelectable(true);
		dateBegin.setReadOnly(true);
		dateBegin.setText(dateFormatter.formatDate(new Date(today.getTime()
				- 1000 * 60 * 60 * 24 * 3)));

		beginHours = new HoursSelector();
		endHours = new HoursSelector(today.getHours() + 1);
		ftDateSelection.setWidget(1, 2, dateBegin);
		ftDateSelection.setWidget(1, 3, beginHours);

		ftDateSelection.setText(2, 1, "Ending date : ");
		dateEnd = new SimpleDatePicker();
		dateEnd.setDateFormat(DateFormat.DATE_FORMAT_MMDDYYYY);
		dateEnd.setWeekendSelectable(true);
		dateEnd.setReadOnly(true);
		dateEnd.setText(dateFormatter.formatDate(today));
		ftDateSelection.setWidget(2, 2, dateEnd);
		ftDateSelection.setWidget(2, 3, endHours);

		ftDateSelection.setText(3, 2, "or");

		cbLive = new CheckBox();
		cbLive.addClickListener(new LiveResultClickListener(this));

		ftDateSelection.setText(4, 1, "Live result : ");
		ftDateSelection.setWidget(4, 2, cbLive);
		Panel hp = new HorizontalPanel();
		hp.add(new Label("Refresh timer : "));
		hp.add(timerIntervalSelector);
		ftDateSelection.setWidget(4, 3, hp);

		initWidget(ftDateSelection);
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param liveResultListener
	 */
	public void setLiveResultListener(LiveResultListener liveResultListener) {
		requestTimer = new TimerCallback(liveResultListener, this);
	}

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public String getBeginDate() {
		return dateBegin.getText();
	}

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public String getBeginHours() {
		return beginHours.getSelectedTime();
	}

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public String getEndDate() {
		return dateEnd.getText();
	}

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public String getEndHours() {
		return endHours.getSelectedTime();
	}

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public boolean isLiveChecked() {
		return cbLive.isChecked();
	}

	private int getTimerIntervalValue() {
		return Integer.parseInt(timerIntervalSelector
				.getValue(timerIntervalSelector.getSelectedIndex()));
	}

	private void endSelectorEnabled(boolean enabled) {
		dateEnd.setEnabled(!enabled);
		endHours.setEnabled(!enabled);
		timerIntervalSelector.setEnabled(enabled);
	}

	private class TimerCallback extends Timer {

		private LiveResultListener liveResultListener;
		private IntervalSelector intervalSelector;

		public TimerCallback(LiveResultListener liveResultListener,
				IntervalSelector intervalSelector) {
			this.liveResultListener = liveResultListener;
			this.intervalSelector = intervalSelector;
		}

		public void run() {
			// if can't see the page, the timer is cancel
			// for example if you look a another page
			if (intervalSelector.isAttached()) {
				liveResultListener.doReact();
				this.schedule(intervalSelector.getTimerIntervalValue() * 1000);
			} else {
				this.cancel();
			}
		}
	}

	private class LiveResultClickListener implements ClickListener {

		private IntervalSelector intervalSelector;

		public LiveResultClickListener(IntervalSelector intervalSelector) {
			this.intervalSelector = intervalSelector;
		}

		public void onClick(Widget sender) {
			CheckBox checkBox = (CheckBox) sender;
			if (checkBox.isChecked()) {
				if (requestTimer != null) {
					requestTimer.schedule(intervalSelector
							.getTimerIntervalValue() * 1000);
				}
				intervalSelector.endSelectorEnabled(true);
			} else {
				if (requestTimer != null) {
					requestTimer.cancel();
				}
				intervalSelector.endSelectorEnabled(false);
			}
		}
	}
}
