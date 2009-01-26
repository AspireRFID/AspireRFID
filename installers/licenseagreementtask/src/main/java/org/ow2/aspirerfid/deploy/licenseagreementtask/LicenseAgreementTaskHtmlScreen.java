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
package org.ow2.aspirerfid.deploy.licenseagreementtask;

import java.io.IOException;
import java.net.URL;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildListener;

/**
 * This class displays a license agreement screen
 * @author Didier Donsez
 */

public class LicenseAgreementTaskHtmlScreen extends LicenseAgreementHtmlScreen
	implements BuildListener
	{
	private LicenseAgreementTask licenseAgreementTask;
	
    public LicenseAgreementTaskHtmlScreen(URL url, boolean requireAgreement, final LicenseAgreementTask licenseAgreementTask) throws IOException {
    	super(url, requireAgreement, licenseAgreementTask);
    	this.licenseAgreementTask=licenseAgreementTask;
    }

    
    public void buildStarted(BuildEvent event) {
        actionPerformed(null);
    }

    public void buildFinished(BuildEvent event) {
        actionPerformed(null);
    }
    
    /**
     * end the task when the target is started
     */
    public void targetStarted(BuildEvent event) {
    	if(event.getTarget().getName().equals(licenseAgreementTask.getOnStartedTarget())){
    		licenseAgreementTask.end();
    	} else {
            actionPerformed(null);
    	}
    }

    /**
     * end the task when the target is finished
     */
    public void targetFinished(BuildEvent event) {
    	if(event.getTarget().getName().equals(licenseAgreementTask.getOnFinishedTarget())){
    		licenseAgreementTask.end();
    	} else {
            actionPerformed(null);
    	}
    }

    public void taskStarted(BuildEvent event) {
        actionPerformed(null);
    }

    public void taskFinished(BuildEvent event) {
        actionPerformed(null);
    }

    public void messageLogged(BuildEvent event) {
        actionPerformed(null);
    }
    
}

