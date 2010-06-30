OW2 :: AspireRFID :: License Agreement Ant Task
Copyright 2008 OW2 AspireRFID Project http://wiki.aspire.objectweb.org

* Ant task
* Command line
* Bundle

================================================
This task display license agreement during ant builds

Usage:
* task definition
  <taskdef name="licenseagreement" classname="org.ow2.aspirerfid.deploy.licenseagreementtask.LicenseAgreementTask" classpath="${task.classpath}"/>

* screen displayed until the user agreement
    <licenseagreement agreementProperty="agreement" imageFile="logo.png" licenseFile="LICENSE"/>
    <echo>Agreement is ${agreement}</echo>
    <echo file="AGREEMENT">${env.USER}:  ${agreement} : ${TODAY} </echo>

* screen displayed until the user agreement (the license file is a HTML document)
    <licenseagreement agreementProperty="agreement" url="file:./license.html"/>
    <echo>Agreement is ${agreement}</echo>
    <echo file="AGREEMENT">${env.USER}:  ${agreement} : ${TODAY} </echo>

* screen displayed until a target starting
    <licenseagreement onStartedTarget="run.server" imageFile="logo.png" licenseFile="LICENSE"/>

* screen displayed until a target completion
    <licenseagreement onFinishedTarget="run.server" imageFile="logo.png" licenseFile="LICENSE"/>

* screen displayed during 10 seconds  	
    <licenseagreement duration="10" imageFile="logo.png" licenseFile="LICENSE"/>

    
================================================
This jar can used in a command line

* screen displayed during 10 seconds  	
	java -jar licenceagreementtask-X.Y.Z.jar LICENSE logo.png 10

* screen displayed until the user agreement  	
	java -jar licenceagreementtask-X.Y.Z.jar LICENSE logo.png 10 agreement

The exit code is 0 if the user agree (not 0 else)


================================================
This jar is also a bundle : it displays a license screen during the bundle starting (start level of this bundle could be very low !)

agreement.licensersc=/AGREEMENT-INF/LICENSE
agreement.imagersc=/AGREEMENT-INF/logo.png
agreement.duration=10
agreement.agreementproperty=user.agreement


