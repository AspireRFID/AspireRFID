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

package org.ow2.aspirerfid.reader.rp.impl.core.triggers;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This is a sample of a key trigger manager.
 * @author Markus Vitalini
 */
public class KeyIOEdgeTriggerPortManager extends IOEdgeTriggerPortManager {

   /**
    * The frame used to display a window.
    */
   private Frame frame;

   /**
    * The start method of the thread. It starts the port manager
    */
   public final void start() {
      frame = new Frame();
      final int h = 100;
      final int w = 200;
      frame.setSize(h, w);
      frame.addKeyListener(new KeyListener() {
         public void keyPressed(final KeyEvent arg0) {
            checkAndNotifyTriggers(String.valueOf(arg0.getKeyChar()), true);
         }

         public void keyReleased(final KeyEvent arg0) {
            checkAndNotifyTriggers(String.valueOf(arg0.getKeyChar()), false);
         }

         public void keyTyped(final KeyEvent arg0) {
            // TODO Auto-generated method stub

         }
      });
      frame.show();
   }

   /**
    * The stop method of the thread. It stops the port manager
    */
   public final void stop() {
      frame.hide();
   }

}
