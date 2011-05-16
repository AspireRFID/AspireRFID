/*
 * Copyright (C) 2008-2010, Aspire
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

package org.ow2.aspirerdfid.tracking.demo.client;

import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.maps.client.overlay.PolyStyleOptions;
import com.google.gwt.maps.client.overlay.Polyline;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.user.client.ui.FlexTable;


/**
 * @author Mourtzoukos Konstantinos {email: komo@ait.edu.gr}
 *
 */

public class TrackingDemo implements EntryPoint, ClickHandler,
		KeyPressHandler {
	private DockLayoutPanel mainPanel;
	private HorizontalPanel horizontalPanel;
	private Image image;
	private TextBox tagSearchTextBox;
	private Button button;
	private MapWidget map;
	private TrackingInterfaceDemoServiceAsync trackingInterfaceSrv = GWT
			.create(TrackingInterfaceDemoService.class);
	@SuppressWarnings({ "unchecked", "unused" })
	private CellList cellList;
	private Icon baseIcon;
	// Create a list data provider.
	final ListDataProvider<TagDataPoint> dataProvider = new ListDataProvider<TagDataPoint>();
	private String color = "#FF0000";
	private double opacity = 1.0;
	private int weight = 1;
	private FlexTable epcClassPropTable;

	/**
	 * A simple data type that represents a contact.
	 */
	private static class TagDataPoint {
		private int id;
		private String tag;
		private String formatedDate;
		private String geoData;
		private String epcClassProperties;

		public TagDataPoint(String tag, String formatedDate, String geoData,
				int id, String epcClassProperties) {
			this.id = id;
			this.tag = tag;
			this.formatedDate = formatedDate;
			this.geoData = geoData;
			this.epcClassProperties = epcClassProperties;
		}

		public String getEpcClassProperties() {
			return epcClassProperties;
		}

		@SuppressWarnings("unused")
		public void setEpcClassProperties(String epcClassProperties) {
			this.epcClassProperties = epcClassProperties;
		}

	}

	/**
	 * A custom {@link Cell} used to render a {@link Contact}.
	 */
	private static class TagDataPointCell extends AbstractCell<TagDataPoint> {
		@Override
		public void render(Context context, TagDataPoint value,
				SafeHtmlBuilder sb) {
			if (value != null) {
				sb.appendHtmlConstant("<p class=\"one\">");
				sb.appendEscaped(value.tag);
				sb.appendHtmlConstant("<br />");
				sb.appendEscaped(value.formatedDate);
				sb.appendHtmlConstant("<br />");
				sb.appendEscaped(value.geoData);
				sb.appendHtmlConstant("</p>");
			}
		}
	}

	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();

		mainPanel = new DockLayoutPanel(Unit.EM);
		rootPanel.add(mainPanel, 0, 0);
		//mainPanel.setSize("1024px", "768px");
		mainPanel.setHeight("100%");
		mainPanel.setWidth("100%");

		horizontalPanel = new HorizontalPanel();
		mainPanel.addNorth(horizontalPanel, 7.8);

		image = new Image("images/ApireRFID_logo.png");
		horizontalPanel.add(image);
		image.setSize("163px", "67px");

		tagSearchTextBox = new TextBox();
		tagSearchTextBox.addKeyPressHandler(this);
		tagSearchTextBox.setAlignment(TextAlignment.LEFT);
		horizontalPanel.add(tagSearchTextBox);
		horizontalPanel.setCellVerticalAlignment(tagSearchTextBox,
				HasVerticalAlignment.ALIGN_MIDDLE);
		tagSearchTextBox.setSize("160px", "18px");

		button = new Button("New button");
		button.addClickHandler(this);
		button.setText("Search");
		horizontalPanel.add(button);
		horizontalPanel.setCellHorizontalAlignment(button,
				HasHorizontalAlignment.ALIGN_CENTER);
		horizontalPanel.setCellVerticalAlignment(button,
				HasVerticalAlignment.ALIGN_MIDDLE);
		button.setWidth("92px");
		
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		ProvidesKey<TagDataPoint> keyProvider = new ProvidesKey<TagDataPoint>() {
			public Object getKey(TagDataPoint item) {
				// Always do a null check.
				return (item == null) ? null : item.id;
			}
		};

		// Create a CellList using the keyProvider.
		CellList<TagDataPoint> cellList = new CellList<TagDataPoint>(
				new TagDataPointCell(), keyProvider);
		cellList.setWidth("100%");
		cellList.setPageSize(99);

		// Add a selection model to handle user selection.
		final SingleSelectionModel<TagDataPoint> selectionModel = new SingleSelectionModel<TagDataPoint>();
		cellList.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					@SuppressWarnings("deprecation")
					public void onSelectionChange(SelectionChangeEvent event) {
						TagDataPoint selected = selectionModel
								.getSelectedObject();
						if (selected != null) {
							// Update the properties flexTable
							// ClearFlexTable
							for (int i = epcClassPropTable.getRowCount() - 1; i > 0; i--) {
								epcClassPropTable.removeRow(i);
							}
							if (!selected.getEpcClassProperties().equals("")) {
								JSONValue value = JSONParser.parse(selected
										.getEpcClassProperties());
								JSONArray proparray = value.isArray();
								if (proparray != null) {
									for (int i = 0; i <= proparray.size() - 1; i++) {
										JSONObject propObj = proparray.get(i)
												.isObject();
										for (String key : propObj.keySet()) {
											String keyvalue = propObj.get(key)
													.isString().stringValue();
											epcClassPropTable.setText(i + 1, 0,
													key);
											epcClassPropTable.setText(i + 1, 1,
													keyvalue);
											epcClassPropTable.getCellFormatter().addStyleName(i+1, 0, "watchListNumericColumn");
											epcClassPropTable.getCellFormatter().addStyleName(i+1, 1, "watchListNumericColumn");
										}
									}
								}
							}
						}
					}
				});

		// Add the cellList to the dataProvider.
		dataProvider.addDataDisplay(cellList);
		ScrollPanel scroller = new ScrollPanel(cellList);
		scroller.setStyleName("scrollable");
		scroller.setHeight("100%");
		scroller.setWidth("100%");

		mainPanel.addWest(scroller, 20);

		epcClassPropTable = new FlexTable();
		mainPanel.addEast(epcClassPropTable, 20);
		epcClassPropTable.setText(0, 0, "Property");
		epcClassPropTable.setText(0, 1, "Value");
		epcClassPropTable.getRowFormatter().addStyleName(0,
				"epcClassPropHeader");
		epcClassPropTable.addStyleName("epcClassProp");
		epcClassPropTable.getCellFormatter().addStyleName(0, 0, "watchListNumericColumn");
		epcClassPropTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");

		Maps.loadMapsApi("", "2", false, new Runnable() {
			public void run() {
				buildUi();
			}
		});
	}

	protected void buildUi() {
		LatLng AthensCity = LatLng.newInstance(37.58, 23.43);

		map = new MapWidget(AthensCity, 2);
		map.setSize("100%", "100%");
		// Add some controls for the zoom level
		map.addControl(new LargeMapControl());
		map.setScrollWheelZoomEnabled(true);

		// // Add a marker
		// map.addOverlay(new Marker(AthensCity));

		mainPanel.add(map);

		// Create a base icon for all of our markers that specifies the
		// shadow, icon dimensions, etc.
		baseIcon = Icon.newInstance();
		baseIcon
				.setShadowURL("http://google-maps-icons.googlecode.com/files/shadow.png");
		baseIcon.setIconSize(Size.newInstance(20, 34));
		baseIcon.setShadowSize(Size.newInstance(37, 34));
		baseIcon.setIconAnchor(Point.newInstance(9, 34));
		baseIcon.setInfoWindowAnchor(Point.newInstance(9, 2));

	}

	public void onClick(ClickEvent event) {
		getTagData();
	}

	private void getTagData() {
		final String tag = tagSearchTextBox.getText().trim();
		tagSearchTextBox.setFocus(true);

		// tagSearchTextBox.setText("");

		// Initialize the service proxy.
		if (trackingInterfaceSrv == null) {
			trackingInterfaceSrv = GWT
					.create(TrackingInterfaceDemoService.class);
		}

		// Set up the callback object.
		AsyncCallback<List<TagEventSerialObject>> callback = new AsyncCallback<List<TagEventSerialObject>>() {
			public void onFailure(Throwable caught) {
				// TODO: Do something with errors.
			}

			public void onSuccess(List<TagEventSerialObject> result) {
				processData(result);
			}
		};

		// Make the call to the stock price service.
		trackingInterfaceSrv.getTagData(tag, callback);

	}

	protected void processData(List<TagEventSerialObject> result) {
		if (result.isEmpty())
			return;
		// Clear previous markers etc
		map.clearOverlays();
		// Get the underlying list from data dataProvider.
		List<TagDataPoint> taglist = dataProvider.getList();
		taglist.clear();
		int count = 0;
		LatLng[] polypoint = new LatLng[result.size()];

		for (TagEventSerialObject tagESObj : result) {
			// Add even to list
			taglist.add(new TagDataPoint(tagESObj.getTag(), DateTimeFormat
					.getFormat("dd.MM.yyyy G '@' HH:mm:ss vvvv").format(
							tagESObj.getTimeDate()), tagESObj.getGeoTag(),
					count, tagESObj.getEpcClassProperties()));
			count++;
			String[] temp;
			/* Add markers on the map */
			String delimiter = ":";
			temp = tagESObj.getGeoTag().split(delimiter);
			polypoint[count - 1] = LatLng.newInstance(Double
					.parseDouble(temp[0]), Double.parseDouble(temp[1]));
			;
			map.addOverlay(createMarker(polypoint[count - 1], count, tagESObj));
		}
		
		PolyStyleOptions style = PolyStyleOptions.newInstance(color, weight,
				opacity);
		final Polyline poly = new Polyline(polypoint);
		poly.setStrokeStyle(style);
		map.addOverlay(poly);

	}

	private Marker createMarker(LatLng point, int index,
			final TagEventSerialObject tobj) {
		// Create a lettered icon for this point using our icon class
		Icon icon = Icon.newInstance(baseIcon);
		if (index <= 9) {
			final char digit = (char) ('0' + index);
			icon
					.setImageURL("http://google-maps-icons.googlecode.com/files/red"
							+ '0' + digit + ".png");
		} else {
			final char firstDigit = (char) ('0' + index / 10);
			final char lastDigit = (char) ('0' + index % 10);
			icon
					.setImageURL("http://google-maps-icons.googlecode.com/files/red"
							+ firstDigit + lastDigit + ".png");
		}
		MarkerOptions options = MarkerOptions.newInstance();
		options.setIcon(icon);
		final Marker marker = new Marker(point, options);

		marker.addMarkerClickHandler(new MarkerClickHandler() {

			public void onClick(MarkerClickEvent event) {
				InfoWindow info = map.getInfoWindow();
				info.open(event.getSender(), new InfoWindowContent(
						"Company Name:" + tobj.getName() + "<br />"
								+ "Description:" + tobj.getDescription()
								+ "<br />" + "Address:" + tobj.getAddress()
								+ "<br />" + "Country:" + tobj.getCountry()
								+ "<br />" + "Region:" + tobj.getRegion()
								+ "<br />" + "Email:" + tobj.getEmail()
								+ "<br />" + "Tel:" + tobj.getTel() + "<br />"
								+ "Fax:" + tobj.getFax()));
			}

		});

		return marker;
	}

	public void onKeyPress(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
			getTagData();
		}
	}
}
