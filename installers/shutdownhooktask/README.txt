OW2 :: AspireRFID :: ShutdownHook Ant Task
Copyright 2008 OW2 AspireRFID Project http://wiki.aspire.objectweb.org

This task executes a shutdown hook when the JVM is interupted (ie Ctrl-C).

Usage:
* task definition
  <taskdef name="shutdownhook" classname="org.ow2.aspirerfid.deploy.shutdownhook.ShutdownHookTask" classpath="${task.classpath}"/>

* usage
    <shutdownhook target="shutdown"/>
    <shutdownhook target="shutdown2" taskname="hook2"/>
    <shutdownhook target="shutdown3" taskname="hook3" message="Third shutdown hook is started ..."/>

    