Some of the jars from the Nokia telephone have to be installed in the maven repository.

The files are normally in the path:
C:\Nokia\Devices\Nokia_6131_NFC_SDK_1_1\lib
You have to copy the cldcapi11.zip file, and change the extension to .jar
cldcapi11.zip -> cldcapi11.jar

mvn install:install-file -DgroupId=com.nokia -DartifactId=cldc -Dversion=1.1 -Dpackaging=jar -Dfile=cldcapi11.jar


Also for the files in the path
C:\Nokia\Devices\Nokia_6131_NFC_SDK_1_1\lib\ext
bluetooth.zip -> bluetooth.jar
nfc.zip -> nfc.jar

mvn install:install-file -DgroupId=com.nokia -DartifactId=bluetooth -Dversion=1 -Dpackaging=jar -Dfile=bluetooth.jar
mvn install:install-file -DgroupId=com.nokia -DartifactId=contactless -Dversion=1.0 -Dpackaging=jar -Dfile=nfc.jar
