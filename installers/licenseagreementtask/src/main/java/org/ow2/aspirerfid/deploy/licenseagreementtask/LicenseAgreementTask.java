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

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JWindow;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * Creates a license agreement screen.
 * The screen is displayed for the duration or until the user agreement
 * @author Didier Donsez
 */
public class LicenseAgreementTask extends Task implements AgreementItf {
// TODO extends WaitFor {
    private static final int TEN_SECONDS = 10000;
    private static final int ONE_SECOND = 1000;

    private File imageFile = null;
    private File licenseFile = null;
    private URL url = null;
    private String agreementProperty = null;
    private int duration = TEN_SECONDS;
	private boolean end;

	private String onStartedTarget=null;
	private String onFinishedTarget=null;
	
    private static JWindow screen = null;

	/**
	 * @param imageFile the imageFile to set
	 */
	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	/**
	 * @param licenseFile the licenseFile to set
	 */
	public void setLicenseFile(File licenseFile) {
		this.licenseFile = licenseFile;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @param agreementProperty the agreementProperty to set
	 */
	public void setAgreementProperty(String agreementProperty) {
		this.agreementProperty = agreementProperty;
	}

	/**
	 * @param onStartedTarget the onStartedTarget to set
	 */
	public void setOnStartedTarget(String onStartedTarget) {
		this.onStartedTarget = onStartedTarget;
	}

	/**
	 * @param onFinishedTarget the onFinishedTarget to set
	 */
	public void setOnFinishedTarget(String onFinishedTarget) {
		this.onFinishedTarget = onFinishedTarget;
	}

	/**
     * Execute the task.
     * @throws BuildException on error
     */
    public void execute() throws BuildException {

        boolean agreement = (agreementProperty!=null);
    	
    	if(url!=null) {
	        try {
				screen = new LicenseAgreementTaskHtmlScreen(url,agreement, this);
			} catch (IOException e) {
				throw new BuildException(e);
			}
    	} else {
	    	
	        ImageIcon img=null;
	        String licenseText=null;
	        
	        if(imageFile!=null) {
		        InputStream imageStream;
				try {
					imageStream = new FileInputStream(imageFile);
				} catch (FileNotFoundException fnfe) {
					throw new BuildException(fnfe);
				}        
				
		        if (imageStream != null) {
		            DataInputStream din = new DataInputStream(imageStream);
		            try {
		                ByteArrayOutputStream bout = new ByteArrayOutputStream();
		                int data;
		                while ((data = din.read()) != -1) {
		                    bout.write((byte) data);
		                }
	                    img = new ImageIcon(bout.toByteArray());
	
		            } catch (Exception e) {
		                throw new BuildException(e);
		            } finally {
		                try {
		                    din.close();
		                } catch (IOException ioe) {
		                    throw new BuildException(ioe);
		                }
		            }
		        }
	        }
	        
	        if(licenseFile!=null) {
		        Reader licenseReader;
				try {
					licenseReader = new FileReader(licenseFile);
				} catch (FileNotFoundException fnfe) {
					throw new BuildException(fnfe);
				}        
		        if (licenseReader != null) {
		        	BufferedReader bufferedReader = new BufferedReader(licenseReader);
		            try {
		                StringBuffer sb=new StringBuffer();
		                String line;
		                while ((line = bufferedReader.readLine()) != null) {
		                	sb.append(line).append('\n');
		                }
		                licenseText=sb.toString();
		            } catch (Exception e) {
		                throw new BuildException(e);
		            } finally {
		                try {
		                    bufferedReader.close();
		                } catch (IOException ioe) {
		                    throw new BuildException(ioe);
		                }
		            }
		        }
	        }
	        screen = new LicenseAgreementTaskScreen(img,licenseText,agreement, this);
        }
    	
        end=false;
        screen.setVisible(true);
        screen.toFront();
        //getProject().addBuildListener(screen);

        int countdown=duration;
        while(!end){
	        try {
	            Thread.sleep(ONE_SECOND);
	        } catch (InterruptedException e) {
	            // Ignore Exception
	        }
	        countdown--;
	        if(countdown<=0 && !agreement) end=true;
        }

        //getProject().removeBuildListener(screen);
        screen.setVisible(false);
        screen.dispose();
        screen = null;
    }
    
	public void agree() {
		getProject().setProperty(agreementProperty,"true");
		end();
	}
	
	public void dontagree() {
		getProject().setProperty(agreementProperty,"false");
		end();
	}
	
    void end(){
    	end=true;
    }

	/**
	 * @return the onStartedTarget
	 */
	public String getOnStartedTarget() {
		return onStartedTarget;
	}

	/**
	 * @return the onFinishedTarget
	 */
	public String getOnFinishedTarget() {
		return onFinishedTarget;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(URL url) {
		this.url = url;
	}
}
