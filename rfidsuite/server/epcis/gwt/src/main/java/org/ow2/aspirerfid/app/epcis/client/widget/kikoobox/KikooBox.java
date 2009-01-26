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
package org.ow2.aspirerfid.app.epcis.client.widget.kikoobox;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * KikooBox.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class KikooBox extends Composite {

	private final static int TIME_BETWEEN_FRAME = 100; // in milliseconds

	// private final static int STATE_MOVE_DOWN = 1;

	private final static int STATE_MOVE_UP = 2;

	private final static int STATE_FULLVIEW_TO_MOVE_UP = 3;

	private final static int STATE_FULLVIEW = 4;

	private final static int STATE_HIDE = 5;

	private final static int MARGIN_SIZE = 30;

	private AbsolutePanel panel;

	private Widget content;

	private int duration;

	private Timer timer;

	private int state;

	private int contentHeight;

	private int contentWidth;

	private int contentPosX, contentPosY;

	/**
	 * TODO Javadoc
	 */
	public KikooBox() {
		panel = new AbsolutePanel();
		state = STATE_HIDE;
		timer = new AnimationTimer(this);
		content = null;

		initWidget(panel);
		this.setVisible(false);
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param msg
	 * @param duration
	 */
	public void showOk(String msg, int duration) {
		this.duration = duration;
		setContent(new Label(msg), 17, msg.length() * 10, "kikoobox-ok");
		show(duration);
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param msg
	 * @param duration
	 */
	public void showError(String msg, int duration) {
		this.duration = duration;
		setContent(new Label(msg), 17, msg.length() * 10, "kikoobox-error");
		show(duration);
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param msg
	 * @param duration
	 */
	public void showWarning(String msg, int duration) {
		this.duration = duration;
		Panel pWarning = new HorizontalPanel();
		pWarning.add(new Image("img/alert.png"));
		pWarning.add(new Label(msg));
		setContent(pWarning, 37, msg.length() * 10, "kikoobox-warning");
		show(duration);
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param content
	 * @param h
	 * @param w
	 * @param style
	 */
	private void setContent(Widget content, int h, int w, String style) {
		this.content = content;
		this.content.setStyleName(style);

		panel.clear();
		panel.add(content);
		contentHeight = h;
		contentWidth = w;
		contentPosX = RootPanel.get().getOffsetWidth() - MARGIN_SIZE
				- this.contentWidth;
		contentPosY = 0;
		panel.setWidgetPosition(this.content, contentPosX, contentPosY);

		reset();
	}

	/**
	 * TODO Javadoc
	 */
	private void reset() {
		timer.cancel();
		state = STATE_HIDE;
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param millisecond
	 */
	private void show(int millisecond) {
		if (content == null) {
			System.out.println("Warning KikooBox content NULL !");
			return;
		}

		reset();
		duration = millisecond;
		update();
	}

	/**
	 * TODO Javadoc
	 */
	public void update() {
		switch (state) {
		case STATE_HIDE:
			state = STATE_FULLVIEW;
			contentPosY = 0;
			panel.setWidgetPosition(content, contentPosX, contentPosY);
			this.setVisible(true);
			update();
			break;

		// case STATE_MOVE_DOWN:
		// contentPosY ++;
		// panel.setWidgetPosition(content, contentPosX, contentPosY);
		// if (contentPosY >= 0)
		// state = STATE_FULLVIEW;
		// break;

		case STATE_FULLVIEW:
			state = STATE_FULLVIEW_TO_MOVE_UP;
			timer.schedule(duration);
			break;

		case STATE_FULLVIEW_TO_MOVE_UP:
			state = STATE_MOVE_UP;
			timer.cancel();
			timer.scheduleRepeating(TIME_BETWEEN_FRAME);
			break;

		case STATE_MOVE_UP:
			contentPosY--;
			panel.setWidgetPosition(content, contentPosX, contentPosY);
			if (contentPosY <= -contentHeight) {
				state = STATE_HIDE;
				this.setVisible(false);
				timer.cancel();
			}
			break;

		default:
			break;
		}
	}

	private class AnimationTimer extends Timer {
		private KikooBox kb;

		public AnimationTimer(KikooBox kb) {
			this.kb = kb;
		}

		public void run() {
			kb.update();
		}
	}
}
