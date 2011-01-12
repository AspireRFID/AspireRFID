set PATH=%PATH%;.\rxtxSerial.dll
java -cp RXTXcomm.jar;target\org.ow2.aspirerfid.rxtx.cmd-0.2.0-SNAPSHOT.jar org.ow2.aspirerfid.rxtx.cmd.console.SerialConsole %1 %2 %3 %4 %5 %6 %7 %8

rem set CLASSPATH=.\RXTXcomm.jar
rem java -jar target\org.ow2.aspirerfid.rxtx.cmd-0.2.0-SNAPSHOT.jar  %1 %2 %3 %4 %5 %6 %7 %8

