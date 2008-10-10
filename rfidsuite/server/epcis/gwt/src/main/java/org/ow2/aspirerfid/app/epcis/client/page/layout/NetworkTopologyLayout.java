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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.gwtwidgets.client.style.Color;
import org.gwtwidgets.client.wrap.JsGraphicsPanel;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.ow2.aspirerfid.app.epcis.client.IndexModule;
import org.ow2.aspirerfid.app.epcis.client.util.Util;
import org.ow2.aspirerfid.app.epcis.client.widget.topology.ArchiElement;

/**
 * Layout for the page :See network topology.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class NetworkTopologyLayout implements PageLayout {

	private int LAYER_DIM_X;

	private int LAYER_DIM_Y;

	private final static int MIN_LAYER_DIM_X = 600;

	private final static int MIN_LAYER_DIM_Y = 500;

	private final static int MIN_SPACING_Y = 50;

	private final static int TEXT_HEIGHT = 11;

	private final static int TEXT_WIDTH = 5;

	private final static String IMG_SERVER = "img/topo_server.png";

	private final static String IMG_PREMISE = "img/topo_premise.png";

	private final static String IMG_EDGE = "img/topo_edge.png";

	private final static String IMG_READER = "img/topo_reader.png";

	private HashMap elementPosition;

	private JsGraphicsPanel graphicsPanel;

	private int rowY;

	private final VerticalPanel mainPanel;

	/**
	 * TODO Javadoc
	 */
	public NetworkTopologyLayout() {
		LAYER_DIM_X = RootPanel.get().getOffsetWidth() - 250;
		LAYER_DIM_Y = RootPanel.get().getOffsetHeight() - 250;

		if (LAYER_DIM_X < MIN_LAYER_DIM_X) {
			LAYER_DIM_X = MIN_LAYER_DIM_X;
		}

		if (LAYER_DIM_Y < MIN_LAYER_DIM_Y) {
			LAYER_DIM_Y = MIN_LAYER_DIM_Y;
		}

		elementPosition = new HashMap();
		rowY = 20;

		Panel divPanel = new SimplePanel();
		Element elem = DOM.createDiv();
		DOM.setElementAttribute(elem, "id", "g");
		DOM.setStyleAttribute(elem, "position", "absolute");
		DOM.setStyleAttribute(elem, "left", "150px");
		DOM.appendChild(divPanel.getElement(), elem);

		mainPanel = new VerticalPanel();
		mainPanel.add(divPanel);
	}

	/**
	 * Internal class which contain position and dimension
	 * 
	 */
	private class Position {
		public int x;

		public int y;

		public int h;

		public Position(int x, int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}
	}

	/**
	 * Display the network topology
	 */
	public void display() {
		mainPanel.add(Util.defaultLoadingMessage());
		IndexModule.setCentralWidget(mainPanel);

		if (graphicsPanel == null) {
			graphicsPanel = new JsGraphicsPanel("g");
		} else {
			graphicsPanel.clear();
			graphicsPanel.paint();
		}

		IndexModule.getSession().getArchitectureStruct(new GetArchiCallback());
	}

	/**
	 * Display the specified network topology
	 */
	private void display(List/* <ArchiElement> */archi) {
		rowY = 20;
		mainPanel.remove(Util.defaultLoadingMessage());
		graphicsPanel.clear();
		graphicsPanel.paint();

		if (archi == null) {
			graphicsPanel.setColor(Color.RED);
			graphicsPanel.drawString("LDAP server is unreachable,", 40, 50);
			graphicsPanel.drawString(
					"see if your 'server.properties' is correct.", 40, 70);
			graphicsPanel.paint();
			return;
		}

		List servers = new ArrayList();
		List premises = new ArrayList();
		List edges = new ArrayList();
		List readers = new ArrayList();

		int size = archi.size();
		for (int i = 0; i < size; i++) {
			ArchiElement e = (ArchiElement) archi.get(i);
			switch (e.type) {
			case ArchiElement.TYPE_SERVER:
				servers.add(e);
				break;

			case ArchiElement.TYPE_PREMISE:
				premises.add(e);
				break;

			case ArchiElement.TYPE_EDGE:
				edges.add(e);
				break;

			case ArchiElement.TYPE_READER:
				readers.add(e);
				break;
			}
		}

		displayRowImg(servers, ArchiElement.TYPE_SERVER);
		displayRowImg(premises, ArchiElement.TYPE_PREMISE);
		displayRowImg(edges, ArchiElement.TYPE_EDGE);
		displayRowImg(readers, ArchiElement.TYPE_READER);
		displayLine(archi);
	}

	/**
	 * Display row of specified type
	 * 
	 * @param elements
	 *            List of <ArchiElement>
	 * @param type
	 *            ArchiElement.TYPE_XXX
	 */
	private void displayRowImg(List elements, int type) {
		int nbElement = elements.size();
		if (nbElement == 0) {
			return;
		}
		int dim = 0;
		String img = "";
		switch (type) {
		case ArchiElement.TYPE_SERVER:
			img = IMG_SERVER;
			dim = 120;
			break;

		case ArchiElement.TYPE_PREMISE:
			img = IMG_PREMISE;
			dim = 95;
			break;

		case ArchiElement.TYPE_EDGE:
			img = IMG_EDGE;
			dim = 55;
			break;

		case ArchiElement.TYPE_READER:
			img = IMG_READER;
			dim = 80;
			break;
		}

		int x;
		int margin;

		margin = (LAYER_DIM_X - (dim * nbElement)) / (nbElement + 1);

		for (int i = 0; i < nbElement; i++) {
			ArchiElement e = (ArchiElement) elements.get(i);
			x = margin + i * (dim + margin);
			elementPosition.put(e, new Position(x, rowY, dim));
			graphicsPanel.drawImage(img, x, rowY, dim, dim);
			graphicsPanel.setColor(Color.BLACK);
		}

		graphicsPanel.setFont("verdana", "11", JsGraphicsPanel.PLAIN);
		graphicsPanel.setColor(Color.BLUE);
		for (int i = 0; i < nbElement; i++) {
			ArchiElement e = (ArchiElement) elements.get(i);
			x = margin + i * (dim + margin);
			String name = e.name;
			name = name.replaceFirst("cn:", ""); // remove cn :
			int textW = name.length() * TEXT_WIDTH;
			graphicsPanel.drawString(name, x + (dim - textW) / 2, rowY + dim
					- 2);
		}
		rowY += MIN_SPACING_Y + dim;
	}

	/**
	 * Display line connector
	 * 
	 * @param archi
	 *            List <ArchiElement>
	 */
	private void displayLine(List archi) {
		int size = archi.size();
		graphicsPanel.setColor(Color.BLACK);
		for (int i = 0; i < size; i++) {
			ArchiElement e = (ArchiElement) archi.get(i);
			Position end = (Position) elementPosition.get(e);
			if (e.parent != null) {
				Position begin = (Position) elementPosition.get(e.parent);
				graphicsPanel.drawLine(begin.x + begin.h / 2, begin.y + begin.h
						+ TEXT_HEIGHT, end.x + end.h / 2, end.y);
			}
		}
		graphicsPanel.paint();
	}

	/**
	 * Internal callback class
	 * 
	 */
	private class GetArchiCallback implements AsyncCallback {
		public void onFailure(Throwable caught) {
			IndexModule.getKikooBox().showError("Get Network Topology Failed",
					2000);
		}

		public void onSuccess(Object result) {
			display((List) result);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#getTitle()
	 */
	public String getTitle() {
		return "Network topology";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#getCategorie()
	 */
	public String getCategorie() {
		return "Network topology";
	}
}
