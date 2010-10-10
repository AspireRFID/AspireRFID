<!--
   Copyright 2005-2008, Aspire
   
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
-->

0) Version
*** These bundles are designed to run with SunSPOT Red SDK (v5.0) ***

1)on-SPOT applications
To be able to use these on-SPOT applications with your SunSPOTs, you should use Netbeans and the SunSPOT module to build and deploy the on-SPOTController application.

2) SunSPOT commons
SunSPOT-host-commons embeds the classes from SunSPOT Red SDK that are required to run applications that communicate with SunSPOTs.

To build com.sun.spot.host.commons you will need to install the following libraries to your maven repository:
SDK_INSTALL_DIRECTORY/lib/multihop_common.jar
SDK_INSTALL_DIRECTORY/lib/spotlib_host.jar
SDK_INSTALL_DIRECTORY/lib/spotlib_common.jar
SDK_INSTALL_DIRECTORY/lib/spotlib_device.jar
SDK_INSTALL_DIRECTORY/bin/squawk_host_classes.jar

For conveniency, these libraries have been included in the resources/lib folder

To do so, use the following maven commands:
mvn install:install-file -Dfile=multihop_common.jar -DgroupId=com.sun.spot -DartifactId=com.sun.spot.commons.multihop -Dversion=5.0.0 -Dpackaging=jar
mvn install:install-file -Dfile=spotlib_host.jar -DgroupId=com.sun.spot -DartifactId=com.sun.spot.spotlib.host -Dversion=5.0.0 -Dpackaging=jar
mvn install:install-file -Dfile=spotlib_common.jar -DgroupId=com.sun.spot -DartifactId=com.sun.spot.spotlib.common -Dversion=5.0.0 -Dpackaging=jar
mvn install:install-file -Dfile=spotlib_device.jar -DgroupId=com.sun.spot -DartifactId=com.sun.spot.spotlib.device -Dversion=5.0.0 -Dpackaging=jar    
mvn install:install-file -Dfile=squawk_host_classes.jar -DgroupId=com.sun.spot -DartifactId=com.sun.spot.squawk.host.classes -Dversion=5.0.0 -Dpackaging=jar

Note that this bundle requires RXTX gnu.io packages and that some versions may not be compatible.
The one provided with SunSpot Red SDK worked fine.

3) Setting the basestation serial port
The final step consists in building the sunspot-controller bundle after having modified the basestation serial port property in iPOJO metadata.
This serial port is the one where the basestation is plugged in and should look like "COM3" for Windows users or "/dev/tty0" for Linux users.

4) Endnote
Now you can enjoy using your sunSPOTs features through dynamic OSGi services.
Try the remote controller example to use your SPOT as a remote controller.