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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.felix.shell.Command;
import org.ow2.aspirerfid.ibuddy.service.Action;
import org.ow2.aspirerfid.ibuddy.service.IBuddyDescriptor;
import org.ow2.aspirerfid.ibuddy.service.IIBuddy;
import org.ow2.aspirerfid.ibuddy.service.MyBuddy;

/**
 * provides a shell command to control connected iBuddies.
 * @author El Mehdi Damou
 */
public class IBuddyCommand implements Command {

	private IIBuddy buddy;
	
	/**
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start()  {
		
	}

	/**
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(){
	}

	/**
	 * @see org.apache.felix.shell.Command#execute(java.lang.String, java.io.PrintStream, java.io.PrintStream)
	 */
	public void execute(String cmdline, PrintStream out, PrintStream err) {
		StringTokenizer st=new StringTokenizer(cmdline, " ");
		int n=st.countTokens();

		
		if(n<2) {
			err.println(getFullUsage());
			return;
		}
		
		st.nextToken(); // skip command name
		
		
		String subcommand=st.nextToken();
		n-=2;
		Map<Long, IBuddyDescriptor> buddies =  buddy.getListIbuddy();
		
		if (subcommand.equalsIgnoreCase("list")){
			out.println(buddies.keySet().size()+ " buddy(s) are connected!");
			for (Iterator<Long> iterator = buddies.keySet().iterator(); iterator.hasNext();) {
				Long ibuddyID = iterator.next();
				out.println("Ibuddy" +ibuddyID+" id = " + ibuddyID );	
			}
		}else if(subcommand.equalsIgnoreCase("*")){
			String cmd =null;
			String listcmd = new String();
			while (st.hasMoreTokens()) {
				cmd = st.nextToken();
				if (cmd.equalsIgnoreCase("reset")) {
					Map<Action,List<String>> resetAction = new HashMap<Action, List<String>>();
					resetAction.put(Action.RESET, null);
					for (Iterator<Long> iterator = buddies.keySet().iterator(); iterator.hasNext();) {
						Long ibuddyID = iterator.next();
						new MyBuddy(buddies.get(ibuddyID),resetAction);
					}
					return;
				}
				listcmd = listcmd + " " + cmd;
			}
			
			try {
				List<Map<Action,List<String>>> buddycmd = parseCmdLineOrder(listcmd);
				
				for (Iterator<Long> iterator = buddies.keySet().iterator(); iterator.hasNext();) {
					Long ibuddyID = iterator.next();
					if (listcmd.equalsIgnoreCase("reset")){
						
					}
					out.println("Ibuddy" + ibuddyID+ " : "  );
					for (Iterator<Map<Action, List<String>>> iterator2 = buddycmd.iterator(); iterator2.hasNext();) {
						Map<Action, List<String>> map = iterator2.next();
						for (Iterator<Action> iterator3 = map.keySet().iterator(); iterator3.hasNext();) {
							Action action = iterator3.next();
							
							out.println("	"+action +  " : "  + map.get(action));
						}
						new MyBuddy(buddies.get(ibuddyID),map);
					}
				}
			} catch (CmdException e) {
				err.println(e.getMessageError());
				e.printStackTrace();
			}
			
			
		}else{			
			try{
				Long id = Long.parseLong(subcommand);
				Map <Long, List<Map<Action,List<String>>>> listBuddy =  new HashMap<Long, List<Map<Action,List<String>>>>();
				String cmd =null;
				String listcmd = new String();
				while (st.hasMoreTokens()) {
					cmd = st.nextToken();
					if (cmd.equalsIgnoreCase("reset")){
						Map<Action,List<String>> resetAction = new HashMap<Action, List<String>>();
						resetAction.put(Action.RESET, null);
						new MyBuddy(buddies.get(id),resetAction);
						return;
						
					}else if (cmd.equalsIgnoreCase("ibuddy")){
						List<Map<Action,List<String>>> buddycmd = parseCmdLineOrder(listcmd);
						if (listBuddy.keySet().contains(id)){
							listBuddy.get(id).addAll(buddycmd);
						}else{
							listBuddy.put(id, buddycmd);
						}
						id = Long.parseLong(st.nextToken());
						listcmd = new String();
					}
					else{
						listcmd = listcmd + " " + cmd;
					}
				}
				
				List<Map<Action,List<String>>> buddycmd = parseCmdLineOrder(listcmd);
				if (listBuddy.keySet().contains(id)){
					listBuddy.get(id).addAll(buddycmd);
				}else{
					listBuddy.put(id, buddycmd);
				}
				
				IBuddyDescriptor abudy = null;
				for (Iterator<Long> iterator = listBuddy.keySet().iterator(); iterator
						.hasNext();) {
					Long idbuddy = iterator.next();
					out.println(">>iBuddy " + idbuddy +  ":" );
					abudy = buddies.get(idbuddy);
					List<Map<Action,List<String>>> buddyDo = listBuddy.get(idbuddy);
					for (Iterator<Map<Action, List<String>>> iterator2 = buddyDo.iterator(); iterator2
							.hasNext();) {
						Map<Action, List<String>> map = iterator2.next();
						for (Iterator<Action> iterator3 = map.keySet().iterator(); iterator3
								.hasNext();) {
							Action action = iterator3.next();
							out.println("	"+action +  " : "  + map.get(action));
						}
						new MyBuddy(abudy,map);
					}
				}
			}catch (CmdException e){
				err.println( "\n" +  e.getMessageError());
				e.printStackTrace();
			}
		}

	}


	
	private List<Map<Action,List<String>>> parseCmdLineOrder(String cmdline) throws CmdException{
		
		List<Map<Action,List<String>>> orderList= new ArrayList<Map<Action,List<String>>>(); 
		Map<Action,List<String>> commandToDo = null;
		
		StringTokenizer st=new StringTokenizer(cmdline, " ");
		List<String> actions = null;
		String cmd=null;
		if (st.hasMoreTokens()) cmd = st.nextToken();
		while (st.hasMoreTokens()) {
			commandToDo = new HashMap<Action, List<String>>();
			orderList.add(commandToDo);
	        if (cmd.equalsIgnoreCase("rotate")){
	        	actions = new ArrayList<String>();
	        	if (st.hasMoreTokens()) cmd = st.nextToken();
	        	else throw (new CmdException(cmd + " need paramter! \n tape ibuddy to get list of command "));
	        	Integer times = Integer.parseInt(cmd);
	        	actions.add(times.toString());
        		if (st.hasMoreTokens()) cmd = st.nextToken();
	        	else throw (new CmdException(cmd + " need paramter! \n tape ibuddy to get list of command "));
	        	cmd = cmd.toUpperCase();
	        	while (cmd.equalsIgnoreCase("left") || cmd.equalsIgnoreCase("right")){
		        	cmd = cmd.toUpperCase();
		        	actions.add(cmd);
		        	if (st.hasMoreTokens()) cmd = st.nextToken();
		        	else break;	
	        	}
	        	if (commandToDo.keySet().contains(Action.ROTATE)){
	        		commandToDo.get(Action.ROTATE).addAll(actions);
	        	}else{
	        		commandToDo.put(Action.ROTATE, actions);
	        	}
			}
	        else if (cmd.equalsIgnoreCase("flap")){
	        	actions = new ArrayList<String>();
	        	if (st.hasMoreTokens()) cmd = st.nextToken();
	        	else throw (new CmdException(cmd + " need paramter! \n tape ibuddy to get list of command "));
	        	try{
	        		Integer times = Integer.parseInt(cmd);
	        		actions.add(times.toString());
	        		if (st.hasMoreTokens()) cmd = st.nextToken();
		        	else throw (new CmdException(cmd + " need paramter! \n tape ibuddy to get list of command "));
	        		times = Integer.parseInt(cmd);
	        		actions.add(times.toString());
	        	}catch (NumberFormatException e) {
					throw (new CmdException(cmd + " is incorrect parameter! \n Flap command requiere two numbers parameter, ex : flap 80 10"));	
				}	        	
	        	if (commandToDo.keySet().contains(Action.FLAP)){
	        		commandToDo.get(Action.FLAP).addAll(actions);
	        	}else{
	        		commandToDo.put(Action.FLAP, actions);
	        	}
	        	if (st.hasMoreTokens()) cmd = st.nextToken();
	        }
	        else if (cmd.equalsIgnoreCase("heart")){
	        	actions = new ArrayList<String>();
	        	if (st.hasMoreTokens()) cmd = st.nextToken();
	        	else throw (new CmdException(cmd + " need paramter! \n tape ibuddy to get list of command "));
	        	Integer times = Integer.parseInt(cmd);
        		actions.add(times.toString());
        		if (st.hasMoreTokens()) cmd = st.nextToken();
	        	else throw (new CmdException(cmd + " need paramter! \n tape ibuddy to get list of command "));
	        	cmd = cmd.toUpperCase();
	        	while (cmd.equalsIgnoreCase("on") || cmd.equalsIgnoreCase("off")){
		        	cmd = cmd.toUpperCase();
		        	actions.add(cmd);
		        	if (st.hasMoreTokens()) cmd = st.nextToken();
		        	else break;		
	        	}
	        	
	        	if (commandToDo.keySet().contains(Action.HEART)){
	        		commandToDo.get(Action.HEART).addAll(actions);
	        	}else{
	        		commandToDo.put(Action.HEART, actions);
	        	}
	        }
	        
	        else if(cmd.equalsIgnoreCase("head")){
	        	actions = new ArrayList<String>();
	        	if (st.hasMoreTokens()) cmd = st.nextToken();
	        	else throw (new CmdException(cmd + " need paramter! \n tape ibuddy to get list of command "));
	        	Integer times = Integer.parseInt(cmd);
        		actions.add(times.toString());
        		if (st.hasMoreTokens()) cmd = st.nextToken();
	        	else throw (new CmdException(cmd + " need paramter! \n tape ibuddy to get list of command "));
	        	cmd = cmd.toUpperCase();
	        	while (cmd.equalsIgnoreCase("NONE") || cmd.equalsIgnoreCase("YELLOW") || 
	        			cmd.equalsIgnoreCase("BLUE")|| cmd.equalsIgnoreCase("WHITE") ||
	        			cmd.equalsIgnoreCase("VIOLET")|| cmd.equalsIgnoreCase("CYAN")|| 
	        			cmd.equalsIgnoreCase("GREEN")|| cmd.equalsIgnoreCase("RED")){
	        		
		        	cmd = cmd.toUpperCase();
		        	actions.add(cmd);
		        	if (st.hasMoreTokens()) cmd = st.nextToken();
		        	else break;	
	        	}
	        	
	        	if (commandToDo.keySet().contains(Action.HEAD)){
	        		commandToDo.get(Action.HEAD).addAll(actions);
	        	}else{
	        		commandToDo.put(Action.HEAD, actions);
	        	}
	        }  
	        else throw (new CmdException(cmd  + "is an unknown command"));    
	     }
		return orderList;
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
		return	getName() + " [help]";
	}	

	/**
	 * @see org.apache.felix.shell.Command#getUsage()
	 */
	public String getFullUsage() {
		return			getName() + " list : List connected Ibuddys with id"
		+"\n"+	getName() + " <id|*> rotate <interval> <directions: left|right>: rotate  every interval(>=200) time(in milliseconde) to the directions (eg : ibuddy 0 rotate 500 left right left)"
		+"\n"+	getName() + " <id|*> flap <intensity> <number of flaps>: falp with specific intensity(>=75)  (eg ibuddy 0 flap 75 10)"
		+"\n"+	getName() + " <id|*> head <interval> <colors:none|yellow|blue|white|violet|cyan|green|red>  : change head color every interval(in ms) (eg : ibuddy * head 500 yellow red blue)"
		+"\n"+	getName() + " <id|*> heart <interval> <status:on|off>  : change heart status every interval(in ms) (eg : ibuddy * head 500 yellow red blue)"
		+"\n"+	getName() + " <id|*> reset  : reset the ibuddy (eg : ibuddy * reset)"
		+"\n Informations : \n  You can combine ibuddy actions command eg: ibuddy 1 rotate 500 left right ibuddy 0 flap 90 10"
		;
	}	
}
