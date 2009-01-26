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
package org.ow2.aspirerfid.app.epcis.client.page.layout;

import org.ow2.aspirerfid.app.epcis.client.IndexModule;
import org.ow2.aspirerfid.app.epcis.client.util.Util;
import org.ow2.aspirerfid.app.epcis.client.widget.IntervalSelector;
import org.ow2.aspirerfid.app.epcis.client.widget.LiveResultListener;
import org.ow2.aspirerfid.app.epcis.client.widget.table.RGLMSequentialTable;
import org.ow2.aspirerfid.app.epcis.client.widget.table.ReportGroupListMemberTable;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 */
public class ListTagLayout implements PageLayout, LiveResultListener {

	private IntervalSelector intervalSelector;

	private Label labelNumberOfTag;

	private Panel loadingMessagePanel;

	private boolean numTagListPrivilege;

	private RGLMSequentialTable tableResult;

	private final VerticalPanel vpMain;

	/**
	 * TODO Javadoc
	 * 
	 * @param numTagListPrivilege
	 * @param viewTagListPrivilege
	 */
	public ListTagLayout(boolean numTagListPrivilege,
			boolean viewTagListPrivilege) {

		vpMain = new VerticalPanel();

		intervalSelector = new IntervalSelector();
		intervalSelector.setLiveResultListener(this);
		loadingMessagePanel = new HorizontalPanel();
		labelNumberOfTag = new Label();
		this.numTagListPrivilege = numTagListPrivilege;
		tableResult = new RGLMSequentialTable(intervalSelector);
		tableResult.addField(ReportGroupListMemberTable.FIELD_TAG, "Tag");
		tableResult.addField(ReportGroupListMemberTable.FIELD_GATEWAYNAME,
				"gateway");
		tableResult.addField(ReportGroupListMemberTable.FIELD_READERNAME,
				"reader");
		tableResult.addField(ReportGroupListMemberTable.FIELD_DATE, "Date");

		vpMain.add(intervalSelector);

		vpMain.setWidth("100%");
		HorizontalPanel buttonAndWarning = new HorizontalPanel();
		buttonAndWarning.add(new Button("Search", new OkClickListener()));
		buttonAndWarning.add(loadingMessagePanel);
		buttonAndWarning.setCellHorizontalAlignment(loadingMessagePanel,
				HorizontalPanel.ALIGN_RIGHT);

		vpMain.add(buttonAndWarning);
		vpMain.add(new HTML("<br /> <hr />"));
		vpMain.add(labelNumberOfTag);
		if (viewTagListPrivilege) {
			vpMain.add(tableResult);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#display()
	 */
	public void display() {
		IndexModule.setCentralWidget(vpMain);
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param result
	 */
	private void setResult(List result) {
		tableResult.setContent(result);
		if (numTagListPrivilege && tableResult.getNbResult() > 0) {
			labelNumberOfTag.setText("Tags found : "
					+ tableResult.getNbResult());
		} else {
			labelNumberOfTag.setText(null);
		}
		loadingMessagePanel.clear();
	}

	private class OkClickListener implements ClickListener {
		public void onClick(Widget sender) {
			doReact();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.widget.LiveResultListener#doReact()
	 */
	public void doReact() {
		loadingMessagePanel.add(Util.defaultLoadingMessage());
		String bDate = intervalSelector.getBeginDate();
		String eDate = intervalSelector.getEndDate();
		String bHours = intervalSelector.getBeginHours();
		String eHours = intervalSelector.getEndHours();
		IndexModule.getSession().getCountTagEntry(bDate, bHours, eDate, eHours,
				new NbResultCallback());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#getTitle()
	 */
	public String getTitle() {
		return "List of tag";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#getCategorie()
	 */
	public String getCategorie() {
		return "Reader";
	}

	private class NbResultCallback implements AsyncCallback {
		public void onFailure(Throwable arg0) {
		}

		public void onSuccess(Object arg0) {
			tableResult.setNbResult(((Long) arg0).longValue());

			int nbPage = tableResult.getNbPage();
			int currentPage = tableResult.getCurrentPage();
			if (nbPage < currentPage) {
				tableResult.setCurrentPage(nbPage);
			}

			if (currentPage < 0) {
				tableResult.setCurrentPage(0);
			}

			String bDate = intervalSelector.getBeginDate();
			String eDate = intervalSelector.getEndDate();
			String bHours = intervalSelector.getBeginHours();
			String eHours = intervalSelector.getEndHours();

			IndexModule.getSession().getPartOfTagEntry(bDate, bHours, eDate,
					eHours, 0, tableResult.getMaxResultPerPage(),
					tableResult.getCurrentSort(), tableResult.isSortDesc(),
					new GetTagEntryCallback());
		}
	}

	private class GetTagEntryCallback implements AsyncCallback {
		public void onFailure(Throwable arg0) {
		}

		public void onSuccess(Object arg0) {
			// tableResult.setContent((List) arg0);
			setResult((List) arg0);
		}
	}
}
