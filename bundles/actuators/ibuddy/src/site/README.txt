OW2 :: AspireRFID ::  iBuddy

This bundle provides a service and a command to control one or more iBuddies connected to the host

The i-Buddy is a small blinking and moving USB figurine initially designed as a MSN live avantar.
This actuator can be used in SOHO (Small Office Home Office) usecases.

The bundle provides
* a org.ow2.aspirerfid.ibuddy.service.IIBuddy service per plugged iBuddy
* a command 'ibuddy' for the Felix shell


Installation Windows
--------------------
Uninstall all the iBuddies devices already installed in 'Configuration Panel'>'System'>'Hardware'>'Device Manager'>'HID devices'

Copy the file IBUDDY.INF in the system

copy libusb0.dll C:\windows\system32\
copy libusb0.sys C:\windows\system32\drivers\

Install 
TBC

Linux
--------------------
TODO
??


Command
-------
The bundle provides a command for the Felix shell
The subcommands are:
ibuddy list : List connected Ibuddys with id
ibuddy <id|*> rotate <interval> <directions: left|right>: rotate  every interval (>=200) time(in milliseconde) to the directions (eg : ibuddy 0 rotate 500 left right left)
ibuddy <id|*> flap <intensity> <number of flaps>: falp with specific intensity(>=75)  (eg ibuddy 0 flap 75 10)
ibuddy <id|*> head <interval> <colors:none|yellow|blue|white|violet|cyan|green|red>  : change head color every interval(in ms) (eg : ibuddy * head 500 yellow red blue)
ibuddy <id|*> heart <interval> <status:on|off>  : change heart status every interval (in ms) (eg : ibuddy * head 500 yellow red blue)
ibuddy <id|*> reset  : reset the ibuddy (eg : ibuddy * reset)

Remark:

The command can execute in parallel several actions on a set of connected iBuddies

For instance,
ibuddy 0 rotate 1000 left right left right left right flap 75 30 head 1000 red yellow none cyan yellow heart 1000 on off on off on off on off  ibuddy 1 rotate 1000 left right left right left right flap 75 30 head 1000 red yellow none cyan yellow heart 1000 on off on off on off on

is interpreted as
 
>>iBuddy 0 will excute all those actions in parallel :
        ROTATE : [1000, LEFT, RIGHT, LEFT, RIGHT, LEFT, RIGHT]
        FLAP : [75, 30]
        HEAD : [1000, RED, YELLOW, NONE, CYAN, YELLOW]
        HEART : [1000, ON, OFF, ON, OFF, ON, OFF, ON, OFF]

>>iBuddy 1 will excute all those actions in parallel :
        ROTATE : [1000, LEFT, RIGHT, LEFT, RIGHT, LEFT, RIGHT]
        FLAP : [75, 30]
        HEAD : [1000, RED, YELLOW, NONE, CYAN, YELLOW]
        HEART : [1000, ON, OFF, ON, OFF, ON, OFF, ON]


The first parameter is the duration in milliseconds of the action
The second parameter of the FLAP action is the number of times of the wing flapping with a duration indicated in the first argument



Other links
-----------
"hacking the USB i-buddy" http://cuntography.com/blog/?p=17

http://www.jraf.org/static/maven/sites/jlibibuddy

http://sourceforge.net/projects/usbsnoop


