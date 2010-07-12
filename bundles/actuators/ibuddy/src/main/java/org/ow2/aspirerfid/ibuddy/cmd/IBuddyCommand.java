/**
 * Copyright (c) 2008-2010, AspireRFID
 *
 * This library is free software; you can redistribute it and/or
 * modify it either under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation
 * (the "LGPL"). If you do not alter this
 * notice, a recipient may use your version of this file under the LGPL.
 *
 * You should have received a copy of the LGPL along with this library
 * in the file COPYING-LGPL-2.1; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY
 * OF ANY KIND, either express or implied. See the LGPL  for
 * the specific language governing rights and limitations.
 *
 * Contact: AspireRFID mailto:aspirerfid@ow2.org
 */

package org.ow2.aspirerfid.ibuddy.cmd;

import java.io.PrintStream;
import java.util.StringTokenizer;

import org.apache.felix.shell.Command;
import org.ow2.aspirerfid.ibuddy.service.IIBuddy;

/**
 * provides a shell command to control connected iBuddies.
 * 
 * @author El Mehdi Damou
 */
public class IBuddyCommand implements Command {

   private IIBuddy buddy;

   /**
    * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
    */
   public void start() {

   }

   /**
    * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
    */
   public void stop() {
   }

   /**
    * @see org.apache.felix.shell.Command#execute(java.lang.String, java.io.PrintStream, java.io.PrintStream)
    */
   public void execute(String cmdline, PrintStream out, PrintStream err) {
      StringTokenizer st = new StringTokenizer(cmdline, " ");
      int n = st.countTokens();

      if (n < 2) {
         err.println(getFullUsage());
         return;
      }
      buddy.execute(cmdline, out, err, getFullUsage());
   }

   /**
    * @see org.apache.felix.shell.Command#getName()
    */
   public String getName() {
      return "ibuddy";
   }

   /**
    * @see org.apache.felix.shell.Command#getShortDescription()
    */
   public String getShortDescription() {
      return "command to control ibuddy";
   }

   /**
    * @see org.apache.felix.shell.Command#getUsage()
    */
   public String getUsage() {
      return getName() + " [help]";
   }

   /**
    * @see org.apache.felix.shell.Command#getUsage()
    */
   public String getFullUsage() {
      return getName()
            + " list : List connected Ibuddys with id"
            + "\n"
            + getName()
            + " <id|*> rotate <interval> <directions: left|right>: rotate  every interval(>=200) time(in milliseconde) to the directions (eg : ibuddy 0 rotate 500 left right left)"
            + "\n"
            + getName()
            + " <id|*> flap <intensity> <number of flaps>: falp with specific intensity(>=75)  (eg ibuddy 0 flap 75 10)"
            + "\n"
            + getName()
            + " <id|*> head <interval> <colors:none|yellow|blue|white|violet|cyan|green|red>  : change head color every interval(in ms) (eg : ibuddy * head 500 yellow red blue)"
            + "\n"
            + getName()
            + " <id|*> heart <interval> <status:on|off>  : change heart status every interval(in ms) (eg : ibuddy * head 500 yellow red blue)"
            + "\n"
            + getName()
            + " <id|*> reset  : reset the ibuddy (eg : ibuddy * reset)"
            + "\n Informations : \n  You can combine ibuddy actions command eg: ibuddy 1 rotate 500 left right ibuddy 0 flap 90 10";
   }
}
