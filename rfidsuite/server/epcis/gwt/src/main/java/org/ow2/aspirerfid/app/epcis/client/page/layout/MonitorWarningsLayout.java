/*
 * Copyright 2005-2008, Aspire This library is free software; you can
 * redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation (the "LGPL");
 * either version 2.1 of the License, or (at your option) any later version. If
 * you do not alter this notice, a recipient may use your version of this file
 * under either the LGPL version 2.1, or (at his option) any later version. You
 * should have received a copy of the GNU Lesser General Public License along
 * with this library; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA. This software is
 * distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express
 * or implied. See the GNU Lesser General Public License for the specific
 * language governing rights and limitations.
 */
package org.ow2.aspirerfid.app.epcis.client.page.layout;

import org.ow2.aspirerfid.app.epcis.client.IndexModule;
import org.ow2.aspirerfid.app.epcis.client.util.Util;
import org.ow2.aspirerfid.app.epcis.client.widget.table.ReportGroupListMemberTable;
import org.ow2.aspirerfid.app.epcis.client.widget.tags.ReportGroupListMemberGWT;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Surrel
 * @version 2007
 */
public class MonitorWarningsLayout implements PageLayout {

	private VerticalPanel vp;
	private final ReportGroupListMemberTable tableLost;
	private final ReportGroupListMemberTable tableTemp;
	private boolean running;

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param running
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}

	/**
	 * TODO Javadoc
	 */
	public MonitorWarningsLayout() {

		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(5);

		vp = new VerticalPanel();
		vp.setWidth("100%");

		tableLost = new ReportGroupListMemberTable();
		tableLost.addField(ReportGroupListMemberTable.FIELD_DATE,
				"Last appearance");
		tableLost.addField(ReportGroupListMemberTable.FIELD_READERNAME, "On");
		tableLost.addField(ReportGroupListMemberTable.FIELD_INFO, "Warning");
		tableLost.addField(ReportGroupListMemberTable.FIELD_TAG, "tag");

		tableTemp = new ReportGroupListMemberTable();
		tableTemp.addField(ReportGroupListMemberTable.FIELD_DATE, "Date");
		tableTemp.addField(ReportGroupListMemberTable.FIELD_TEMPERATURE,
				"Temperature");
		tableTemp.addField(ReportGroupListMemberTable.FIELD_READERNAME, "On");
		tableTemp.addField(ReportGroupListMemberTable.FIELD_INFO, "Warning");
		tableTemp.addField(ReportGroupListMemberTable.FIELD_TAG, "tag");

		final Timer timer = new WarningsTimer();

		final ListBox timerListBox = new ListBox();
		timerListBox.addItem("10 sec", "10000");
		timerListBox.addItem("20 sec", "20000");
		timerListBox.addItem("60 sec", "60000");
		timerListBox.addItem("5 min", "300000");

		timerListBox.addChangeListener(new TimerChangeListener(this, timer));
		hp.add(timerListBox);

		final HTML status = new HTML();
		Button buttonActivation = new Button();
		buttonActivation.setWidth("70px");
		buttonActivation.addClickListener(new RunButtonClickListener(this,
				timerListBox, timer, status, buttonActivation));
		hp.add(buttonActivation);
		hp.add(status);
		hp.add(Util.defaultLoadingMessage());

		vp.add(hp);
		vp.add(new HTML("<hr /><h3>Lost Objects</h3>"));
		vp.add(tableLost);
		vp.add(new HTML("<hr /><h3>Temperature warnings</h3>"));
		vp.add(tableTemp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#display()
	 */
	public void display() {
		IndexModule.setCentralWidget(vp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#getTitle()
	 */
	public String getTitle() {
		return "Manage warnings";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#getCategorie()
	 */
	public String getCategorie() {
		return "Warning";
	}

	private class WarningsTimer extends Timer {

		private AsyncCallback lostCallback;
		private AsyncCallback tempCallback;

		public WarningsTimer() {
			super();
			lostCallback = new GetWarningsCallback(tableLost);
			tempCallback = new GetWarningsCallback(tableTemp);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.google.gwt.user.client.Timer#run()
		 */
		public void run() {
			IndexModule.getSession().getSavedWarningsLostResult(lostCallback);
			IndexModule.getSession().getSavedWarningsTemperatureResult(
					tempCallback);
		}
	}

	private class GetWarningsCallback implements AsyncCallback {

		private ReportGroupListMemberTable table;
		private Date lastElementDate;

		public GetWarningsCallback(ReportGroupListMemberTable table) {
			this.table = table;
		}

		public void onFailure(Throwable arg0) {
			IndexModule.getKikooBox().showError(
					"Unable to retrieve lost warnings", 2000);
		}

		public void onSuccess(Object result) {
			List resulList = (List) result;
			table.setContent(resulList);
			if (resulList.size() > 0) {
				Date lastElementDateNew = ((ReportGroupListMemberGWT) resulList
						.get(0)).getMemberDate();
				if (lastElementDate == null
						|| (lastElementDate != null && lastElementDate
								.compareTo(lastElementDateNew) < 0)) {
					IndexModule.getKikooBox().showWarning(
							"WARNING : New warnings detected", 5000);
				}
				lastElementDate = lastElementDateNew;
			}
		}
	}

	private class RunButtonClickListener implements ClickListener {

		private MonitorWarningsLayout monitor;
		private Timer timer;
		private HTML status;
		private Button button;
		private ListBox timerListBox;

		public RunButtonClickListener(MonitorWarningsLayout monitor,
				ListBox timerListBox, Timer timer, HTML status, Button button) {
			this.monitor = monitor;
			this.timer = timer;
			this.status = status;
			this.button = button;
			this.timerListBox = timerListBox;
			if (monitor.isRunning()) {
				timer.scheduleRepeating(Integer.parseInt(timerListBox
						.getValue(timerListBox.getSelectedIndex())));
				button.setText("Stop");
				status.setHTML("Status: <b>Running</b>");
			} else {
				button.setText("Start");
				status.setHTML("Status: <b>Stopped</b>");
			}
		}

		public void onClick(Widget arg0) {
			if (monitor.isRunning()) {
				button.setText("Start");
				timer.cancel();
				status.setHTML("Status: <b>Stopped</b>");
				monitor.setRunning(false);
			} else {
				timer.scheduleRepeating(Integer.parseInt(timerListBox
						.getValue(timerListBox.getSelectedIndex())));
				button.setText("Stop");
				status.setHTML("Status: <b>Running</b>");
				monitor.setRunning(true);
			}
		}
	}

	private class TimerChangeListener implements ChangeListener {

		private MonitorWarningsLayout monitor;
		private Timer timer;

		public TimerChangeListener(MonitorWarningsLayout monitor, Timer timer) {
			this.monitor = monitor;
			this.timer = timer;
		}

		public void onChange(Widget widget) {
			ListBox timerListBox = (ListBox) widget;
			if (monitor.isRunning()) {
				timer.cancel();
				timer.scheduleRepeating(Integer.parseInt(timerListBox
						.getValue(timerListBox.getSelectedIndex())));
			}
		}
	}
}
