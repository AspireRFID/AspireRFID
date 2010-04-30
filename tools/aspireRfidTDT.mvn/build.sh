#!/bin/sh
export CLASSPATH=.:./commons-logging-1.0.4.jar:./jaxb-api-2.1.jar:xercesImpl-2.2.1.jar:./lib/tdt-0.9.0.jar:./lib/aspiretdt-0.6.jar

javac -d ./ src/main/java/com/dalsemi/onewire/utils/*.java
javac -d ./ src/main/java/org/ow2/aspirerfid/tdt/onewire/*.java
javac -d ./ src/main/java/org/ow2/aspirerfid/tdt/gsma/*.java
javac -d ./ src/main/java/org/ow2/aspirerfid/tdt/iso/*.java
javac -d ./ src/main/java/org/ow2/aspirerfid/tdt/*.java

mv org/ow2/aspirerfid/tdt/TDTFrontEnd.class ./classes/org/ow2/aspirerfid/tdt/TDTFrontEnd.class

jar cvf aspiretdt-0.6.jar com/dalsemi/onewire/utils/ org/ow2/aspirerfid/ LICENSE.txt NOTICE.txt -C src/main/resources/ . > /dev/null
mv aspiretdt-0.6.jar lib/
rm -Rf org com
