/*
   Copyright 2005-2008, OW2 Aspire RFID project 
   
   This library is free software; you can redistribute it and/or modify it 
   under the terms of the GNU Lesser General Public License as published by 
   the Free Software Foundation (the "LGPL"); either version 2.1 of the 
   License, or (at your option) any later version. If you do not alter this 
   notice, a recipient may use your version of this file under either the 
   LGPL version 2.1, or (at his option) any later version.
   
   You should have received a copy of the GNU Lesser General Public License 
   along with this library; if not, write to the Free Software Foundation, 
   Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
   
   This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
   KIND, either express or implied. See the GNU Lesser General Public 
   License for the specific language governing rights and limitations.

   Contact: OW2 Aspire RFID project <X AT Y DOT org> (with X=aspirerfid and Y=ow2)

   LGPL version 2.1 full text http://www.gnu.org/licenses/lgpl-2.1.txt    
*/

package org.ow2.aspirerfid.deploy.shutdownhooktask;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildListener;
import org.apache.tools.ant.taskdefs.CallTarget;

/**
 * Creates a JVM shutdown hook executing a target (and dependencies) if shutdown (ie Ctrl-C).
 * @author Didier Donsez
 */
public class ShutdownHookTask extends CallTarget implements Runnable, BuildListener {

	private static int hookCounter=0;
	
    /**
     * the message to log if shutdown 
     */
    private String message;
    private Thread hook;

	private boolean isHookRunning=false;
    
	/**
     * Execute the task.
     * @throws BuildException on error
     */
    public void execute() throws BuildException {
        hook=new Thread(this);
        hook.setName("ShutdownHookTask-"+(++hookCounter));
		Runtime.getRuntime().addShutdownHook(hook);
		getProject().addBuildListener(this);
    }    

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param name the name of the task
	 */
	public void setTaskName(String name) {
		super.setTaskName(name);
	}

	public void run() {
		isHookRunning=true;
	    if(message!=null) log(message);
	    super.execute();
	}
	
	public void buildFinished(BuildEvent buildEvent) {
		if(!isHookRunning){
			Runtime.getRuntime().removeShutdownHook(hook);
		}
	}

	public void buildStarted(BuildEvent buildEvent) {
	}

	public void messageLogged(BuildEvent buildEvent) {
	}

	public void targetFinished(BuildEvent buildEvent) {
	}

	public void targetStarted(BuildEvent buildEvent) {
	}

	public void taskFinished(BuildEvent buildEvent) {
	}

	public void taskStarted(BuildEvent buildEvent) {
	}
}
