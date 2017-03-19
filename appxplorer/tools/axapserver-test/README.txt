Appstore Sample Issuance System is a tool, which will demonstrate a NXP-Appstore feature(s) by using external Card Reader. For example BROADCOM NFC Smartcard Reader 1, PC/SC terminal ACS ACR122 0 and etc.

The following NXP Appstore feature(s) are demonstrating by using this tool:
1) View All Card Applets
2) Install Applet(s)
3) Update Applet(s)
4) Delete Applet(s)

Pre-condition(s):
1) User must have Pre-personalized EV2 card with NXP Appstore MISMART Application or Delegated Application keys.
2) User must have external Card reader with supported drivers installed.

APPSTORE Delegated Application Key(s):
NXP-PICCDAMAuthKey(0x10) AES128  10101010101010100123456789ABCDEF
NXP-PICCDAMMACKEY(0x11) AES128   11111111111111110123456789ABCDEF
NXP-PICCDAMEncKEY(0x12) AES128   12121212121212120123456789ABCDEF


How to execute this tool:

Appstore Sample Issuance System is developed based on Java 1.8 programming language with ANT is an build script.

Execution step(s):

Step 1.

The following command gives you list of available targets in the build.xml

C:\>AppStore-Sample-IssuanceSystem>ant -p
Buildfile: C:\>AppStore-Sample-IssuanceSystem\build.xml

Main targets:

Other targets:

 build-appstoreissuancesystem
 build-toListNFCReaders
 compile
 delete
 init
 run-appstoreissuancesystem
 run-toListNFCReaders
Default target: build-appstoreissuancesystem

Step 2.
please execute below ant command to see list of available NFC reader and choose it accordingly.

C:\>AppStore-Sample-IssuanceSystem>ant run-toListNFCReaders

 ....
 run-toListNFCReaders:
     [java] PC/SC terminal Broadcom Corp Contacted SmartCard 0
     [java] PC/SC terminal Broadcom Corp Contactless SmartCard 0
     [java] PC/SC terminal BROADCOM NFC Smartcard Reader 1
	 
Please replace existing property with "PC/SC terminal BROADCOM NFC Smartcard Reader 1" to the C:\>AppStore-Sample-IssuanceSystem\conf\appstore_issuance_sys_config.properties in the below property:

nfc.reader.name=PC/SC terminal BROADCOM NFC Smartcard Reader 1




Step 3.

then build a Appstore Sample Issuance application.
 
C:\>AppStore-Sample-IssuanceSystem>ant build-appstoreissuancesystem
Buildfile: C:\>AppStore-Sample-IssuanceSystem\ant build-appstoreissuancesystem

delete:

init:
    [mkdir] Created dir: C:\>AppStore-Sample-IssuanceSystem\dist
    [mkdir] Created dir: C:\>AppStore-Sample-IssuanceSystem\dist\lib
    [mkdir] Created dir: C:\>AppStore-Sample-IssuanceSystem\dist\conf
    [mkdir] Created dir: C:\>AppStore-Sample-IssuanceSystem\dist\classes

build-appstoreissuancesystem:
    [javac] Compiling 12 source files to C:\>AppStore-Sample-IssuanceSystem\dist\classes
     [copy] Copying 1 file to C:\>AppStore-Sample-IssuanceSystem\dist\classes
      [jar] Building jar: C:\>AppStore-Sample-IssuanceSystem\dist\appstoreissuancesystem.jar
     [copy] Copying 1 file to C:\>AppStore-Sample-IssuanceSystem\dist\conf
     [copy] Copying 14 files to C:\>AppStore-Sample-IssuanceSystem\dist\lib

BUILD SUCCESSFUL
Total time: 2 seconds

Step 4.

the above command it will create a dist folder with all the libraries and properties to execute appstore issuance system. please go to dist folder and execute the below command for installing applets into your card by using exeternal card reader.

C:\>AppStore-Sample-IssuanceSystem\dist>appstoreissuancesystem.jar

then you can see Swing based java application to View All Applets, Install Applet, Update Applet and Remove Applet.

Note:

1)
Currently we have configured in appstore_issuance_sys_config.properties to Install, Update and Remove only Aria Hotel Applet. In order to change other Applets please kindly change the below propertie(s). These values are available in the View All Applets response.

appstore.approved.application.id=507
appstore.application.aid.tobedeleted=526344

2) All logs are capture in appstore_sample_issuanceSys.log file its under dist folder.






