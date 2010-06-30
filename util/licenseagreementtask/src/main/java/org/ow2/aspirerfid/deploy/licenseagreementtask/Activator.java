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

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * display a license screen during the bundle starting (start level of this bundle could be very low !)
 * @author Didier Donsez
 *
 */
public class Activator implements BundleActivator {
		
	public static String LICENSERSC_PROP="agreement.licensersc";
	public static String IMAGERSC_PROP="agreement.imagersc";
	public static String DURATION_PROP="agreement.duration";
	public static String AGREEMENTPROPERTY_PROP="agreement.agreementproperty";
	
	/**
	 * 
	 */
    public  void start(BundleContext bundleContext) throws Exception
    {
    	String licenseRsc=bundleContext.getProperty(LICENSERSC_PROP);
    	String imageRsc=bundleContext.getProperty(IMAGERSC_PROP);
    	String durationStr=bundleContext.getProperty(DURATION_PROP);
    	String agreementPropertyName=bundleContext.getProperty(AGREEMENTPROPERTY_PROP);

    	if(licenseRsc==null && imageRsc==null) return;
    	
    	
    	LicenseAgreementMain licenseAgreementMain=new LicenseAgreementMain();    	
    	
    	if(licenseRsc!=null) licenseAgreementMain.setLicenseRessource(licenseRsc);
    	if(imageRsc!=null) licenseAgreementMain.setImageRessource(imageRsc);
    	if(durationStr!=null) licenseAgreementMain.setDuration(Integer.parseInt(durationStr));
    	if(agreementPropertyName!=null) licenseAgreementMain.setAgreementProperty(agreementPropertyName);
    	
    	try {
			licenseAgreementMain.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(agreementPropertyName!=null && !("false".equals(System.getProperty(agreementPropertyName)))) {
			bundleContext.getBundle(0).stop();
		}
    }
	
    /**
     * 
     */
    public  void stop(BundleContext bundleContext) throws Exception
    {
    }
}
