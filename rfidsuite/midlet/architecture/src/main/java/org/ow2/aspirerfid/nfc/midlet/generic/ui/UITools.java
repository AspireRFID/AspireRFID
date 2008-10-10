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
package org.ow2.aspirerfid.nfc.midlet.generic.ui;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.StringItem;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

/**
 * Those are some tools used from the user layer. Some of them sends the object
 * read in a parameter, and the return is the message.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public class UITools {
	/**
	 * Gets an image that is in the given inputStream.
	 * 
	 * @param inputStream
	 *            Stream where is possible to get an image. obtained.
	 * @return This will be the image. null if the image could not be obtained.
	 */
	public static Image getImageInParam(InputStream inputStream) {
		Image image = null;
		try {
			image = Image.createImage(inputStream);
		} catch (IOException e) {
			// Do nothing, image null
		}
		return image;
	}

	/**
	 * Gets a text that is in the given inputStream.
	 * 
	 * @param inputStream
	 *            Stream where is possible to get a text.
	 * @param title
	 *            Title of the string.
	 * @return StringItem that has the content.
	 */
	public static StringItem getTextInParam(InputStream inputStream,
			String title) {
		StringItem string = null;
		try {
			StringBuffer sb = new StringBuffer();
			int chr = 0;
			// Read until the end of the stream
			while ((chr = inputStream.read()) != -1) {
				sb.append((char) chr);
			}
			string = new StringItem(title, sb.toString());
		} catch (IOException e) {
			// Do nothing, string null
		}
		return string;
	}

	/**
	 * Plays the sound that is in the inputStream.
	 * 
	 * @param inputStream
	 *            Stream where is possible to get the sound.
	 * @return Message about reading the stream.
	 */
	public static String playSound(InputStream inputStream) {
		String message = null;
		try {
			Player player = Manager.createPlayer(inputStream, "audio/x-wav");
			player.start();
		} catch (IOException e) {
			message = "Error C2 obtaining a sound: " + e.getMessage();
		} catch (MediaException e) {
			message = "Error C3 playing a sound: " + e.getMessage();
		} catch (Exception e) {
			message = "Error C4 opening a sound: " + e.getMessage();
		}
		return message;
	}
}
