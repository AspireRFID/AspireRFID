OW2 :: AspireRFID ::  LIB USB JAVA PROXY

This bundle uses the LibUSBJava library

For installation, you can read http://libusbjava.sourceforge.net/wp/?page_id=8

Windows
-------
Copy the 2 files in the system

copy libusb0.dll C:\windows\system32\
copy libusb0.sys C:\windows\system32\drivers\

For other devices (ibuddy, acsacr122, ...), copy the *.inf files in C:\WINDOWS\system32\inf\other\

Linux
-----
In order to use LibUSBJava, you must stop the HID module and the USB module : this avoid an auto-mount of the device

