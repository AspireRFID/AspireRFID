/**
 * Copyright (c) 2008-2010, AspireRFID
 *
 * This library is free software; you can redistribute it and/or
 * modify it either under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation
 * (the "LGPL"). If you do not alter this
 * notice, a recipient may use your version of this file under the LGPL.
 *
 * You should have received a copy of the LGPL along with this library
 * in the file COPYING-LGPL-2.1; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY
 * OF ANY KIND, either express or implied. See the LGPL  for
 * the specific language governing rights and limitations.
 *
 * Contact: AspireRFID mailto:aspirerfid@ow2.org
 */

package org.ow2.aspirerfid.libusbjava.host;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import org.ow2.aspirerfid.libusbjava.service.ProxyLibUsbJavaService;
import org.ow2.aspirerfid.libusbjava.service.UsbDevice;

import ch.ntb.usb.LibusbJava;
import ch.ntb.usb.Usb_Bus;
import ch.ntb.usb.Usb_Device;
import ch.ntb.usb.Usb_Device_Descriptor;

/**
 * Lib USB JAVA <br>
 *
 * @author El Mehdi DAMOU, Didier Donsez (for refactoring)
 */
public class ProxyLibUsbJava implements ProxyLibUsbJavaService {

    public static Map<Long , UsbDevice> allUsbDevice = null;

    public long idDevice = 0;

    public static Usb_Bus busUSB ;

    public static boolean firstTime = true;

    /**
     * TODO : replace it with LogService
     */
    private static Logger logger = Logger.getLogger(ProxyLibUsbJava.class.getPackage().getName());

    public void start() {
        // if you don't use the ch.ntb.usb.Device class you must initialise
        // Libusb before use

        // ... even if you use Device, you need to initialize it...
        if(firstTime) {
            LibusbJava.usb_init();
            firstTime = false;
        }

//      initialzing();
        allUsbDevice = new HashMap<Long, UsbDevice>();
        updateListDevice();

        logger.fine("LIB USB JAVA START!");
    }


//  private void initialzing() {
////        if (firstTime){
//      logger.fine(">>>>> "+idDevice);
//          LibusbJava.usb_init();
//
//          if (firstTime){
//              logger.fine("NULLLLL");
//              allUsbDevice = new HashMap<Long, UsbDevice>();
//              idDevice = 0;
//              firstTime= false;
//          }
////        }

        // pour le debogage
//      LibusbJava.usb_set_debug(255); // Ajout d'un fichier de configuration
//      LibusbJava.usb_find_busses();
//      LibusbJava.usb_find_devices();
//      busUSB =  LibusbJava.usb_get_busses();




//  }

/**
 *
 */
    public void updateListDevice() {
        allUsbDevice.clear();
        idDevice = 0;
        LibusbJava.usb_find_busses();
        LibusbJava.usb_find_devices();
        busUSB =  LibusbJava.usb_get_busses();

        Usb_Device temp = null;
        Usb_Bus tempbus =  busUSB;
        UsbDevice device =null;
        while (tempbus != null) {
            temp = tempbus.getDevices();
            while (temp != null) {
                Usb_Device_Descriptor descriptor = temp.getDescriptor();
                System.out.print("USB Device : VendorID: "  + Integer.toHexString(descriptor.getIdVendor() & 0xFFFF) + ", ProductID: "
                        + Integer.toHexString(descriptor.getIdProduct() & 0xFFFF) );

                device = new UsbDevice(descriptor.getIdVendor(),descriptor.getIdProduct());

                logger.fine( ", Filename : " + temp.getFilename());

                if (!allUsbDevice.containsValue(device)){
                    //if the device is unknown, add it to the list
                    logger.fine(">>>> not Found");
                    device.getDevices().put(idDevice, temp);
                    device.getFilenames().put(idDevice,temp.getFilename());
                    device.getOnUse().put(idDevice, false);
                    device.getPresent().put(idDevice, true);
                    device.setLenght((device.getLenght())+1);
                    allUsbDevice.put(idDevice,device);
                    idDevice++;

                }else{ // looking for the device in the list
                    logger.fine(">>> USB alredy Present");
                    UsbDevice usbdeviceTemp =  null;
                    Long iddeviceTemp = null;
                    boolean found = false;

                    Iterator<Long> it = allUsbDevice.keySet().iterator();
                    while(it.hasNext() && !found){
                        iddeviceTemp=it.next();
                        if (allUsbDevice.get(iddeviceTemp).equals(device)){
                            usbdeviceTemp = allUsbDevice.get(iddeviceTemp);
                            found = true;
                        }
                    }
                    //if the file name is the same, update it
                    if ((usbdeviceTemp != null) && (usbdeviceTemp.getFilenames().values().contains(temp.getFilename()))){
                        logger.info(">>>> Filename");
                        usbdeviceTemp.getDevices().put(iddeviceTemp, temp);
                        usbdeviceTemp.getPresent().put(iddeviceTemp, true);
                     // else create the file
                    }else if (usbdeviceTemp != null){
                        logger.info(">>>> not Filename");
                        usbdeviceTemp.getDevices().put(idDevice, temp);
                        usbdeviceTemp.getFilenames().put(idDevice,temp.getFilename());
                        usbdeviceTemp.getOnUse().put(idDevice, false);
                        usbdeviceTemp.getPresent().put(idDevice, true);
                        usbdeviceTemp.setLenght((usbdeviceTemp.getLenght())+1);
                        idDevice++;
                    }
                }

                temp = temp.getNext();
            }
            tempbus = tempbus.getNext();
        }
        logger.fine(">>>>> "+allUsbDevice.size());
    }

    public Map<Long,String> getAllFilename(short vendorID, short productID){
        Map<Long,String> filenames=null;
        Long idDev = findDevices(vendorID,productID);
        filenames = allUsbDevice.get(idDev).getFilenames();
        return filenames;
    }


    public UsbDevice getDevice(Long id){
        return allUsbDevice.get(id);
    }


    public Long findDevices(short vendorID, short productID) {
        Long iddeviceTemp = null;
        boolean found = false;

        Iterator<Long> it = allUsbDevice.keySet().iterator();
        while(it.hasNext() && !found){
            iddeviceTemp=it.next();
            long tempVdId = allUsbDevice.get(iddeviceTemp).getVendorId();
            long tempPrdId = allUsbDevice.get(iddeviceTemp).getProductId();
            if ((tempVdId == vendorID) &&( tempPrdId == productID) ){
                found = true;
            }
        }
        return iddeviceTemp;
    }


    public void stop() {
        logger.fine(">>LIB USB JAVA STOP!");
    }

    /*
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#findUsbDevice(short, short)
     */
    public Usb_Device findUsbDevice(short vendorID,short productID) {
        Usb_Device temp = null;
        Usb_Device device = null;
        boolean trouve = false;
        Usb_Bus tempbus =  busUSB;
        while (!trouve && (tempbus != null)) {
            temp = tempbus.getDevices();
            while (!trouve && (temp != null)) {
                Usb_Device_Descriptor descriptor = temp.getDescriptor();
                logger.fine("USB Device : VendorID: "  + Integer.toHexString(descriptor.getIdVendor() & 0xFFFF) + ", ProductID: "
                        + Integer.toHexString(descriptor.getIdProduct() & 0xFFFF));

                if (((short)descriptor.getIdVendor() == (short)vendorID)
                        && ((short)descriptor.getIdProduct() == (short)productID)) {
                    logger.fine("Device was found \n "
                            + "    - Vendor id  : "
                            + Integer.toHexString(descriptor.getIdVendor() & 0xff)
                            + "\n    - Product id : "
                            + Integer.toHexString(descriptor.getIdProduct() & 0xff ));
                    trouve = true;
                    device = temp;
                }
                temp = temp.getNext();
            }
            tempbus = tempbus.getNext();
        }
        return device;
    }

    /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#bulk_read(long, int, byte[], int, int)
     */
    public int bulk_read(long dev_handle, int ep, byte[] bytes, int size, int timeout){
        return LibusbJava.usb_bulk_read(dev_handle,ep, bytes, size, timeout);
    }

    /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#bulk_write(long, int, byte[], int, int)
     */
    public int bulk_write(long dev_handle, int ep, byte[] bytes, int size, int timeout){
        return LibusbJava.usb_bulk_write(dev_handle,ep, bytes, size, timeout);
    }

    /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#claim_interface(long, int)
     */
    public int claim_interface(long dev_handle, int interface_){
        return LibusbJava.usb_claim_interface(dev_handle,interface_);
    }

    /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#clear_halt(long, int)
     */
    public int clear_halt(long dev_handle, int ep){
        return LibusbJava.usb_clear_halt(dev_handle, ep);
    }

    /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#close(long)
     */
    public int close(long dev_handle){
        return LibusbJava.usb_close(dev_handle);
    }

     /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#control_msg(long, int, int, int, int, byte[], int, int)
     */
    public int control_msg(long dev_handle, int requesttype, int request, int value, int index, byte[] bytes, int size, int timeout){
        return LibusbJava.usb_control_msg( dev_handle,  requesttype,  request,  value,  index,  bytes,  size,  timeout);
     }

     /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#get_descriptor_by_endpoint(long, int, byte, byte, int)
     */
    public String get_descriptor_by_endpoint(long dev_handle, int ep, byte type, byte index, int size){
         return LibusbJava.usb_get_descriptor_by_endpoint( dev_handle,  ep,  type,  index,  size);
     }

     /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#get_descriptor(long, byte, byte, int)
     */
    public String get_descriptor(long dev_handle, byte type, byte index, int size){
         return LibusbJava.usb_get_descriptor(dev_handle, type,  index, size);
     }

     /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#get_string_simple(long, int)
     */
    public String   get_string_simple(long dev_handle, int index){
         return LibusbJava.usb_get_string_simple( dev_handle,  index);
     }

     /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#get_string(long, int, int)
     */
    public String   get_string(long dev_handle, int index, int langid){
         return LibusbJava.usb_get_string( dev_handle,  index,  langid);
     }

     /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#interrupt_read(long, int, byte[], int, int)
     */
    public int interrupt_read(long dev_handle, int ep, byte[] bytes, int size, int timeout){
         return LibusbJava.usb_interrupt_read(dev_handle,  ep, bytes, size,  timeout);
     }

     /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#interrupt_write(long, int, byte[], int, int)
     */
    public int interrupt_write(long dev_handle, int ep, byte[] bytes, int size, int timeout){
         return LibusbJava.usb_interrupt_write( dev_handle,  ep, bytes,  size,  timeout);
     }

     /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#open(ch.ntb.usb.Usb_Device)
     */
    public long open(Usb_Device dev){
         return LibusbJava.usb_open(dev);
     }

     /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#release_interface(long, int)
     */
    public int release_interface(long dev_handle, int interface_){
        return LibusbJava.usb_release_interface(dev_handle,interface_);
     }

     /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#usb_reset(long)
     */
    public int usb_reset(long dev_handle){
         return LibusbJava.usb_reset(dev_handle);
     }

     /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#set_altinterface(long, int)
     */
    public int set_altinterface(long dev_handle, int alternate){
         return LibusbJava.usb_set_altinterface( dev_handle, alternate);
     }

     /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#set_configuration(long, int)
     */
    public int set_configuration(long dev_handle, int configuration){
         return LibusbJava.usb_set_configuration( dev_handle, configuration);
     }

     /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#set_debug(int)
     */
    public void set_debug(int level){
         LibusbJava.usb_set_debug(level);
     }

     /**
     * @see org.ow2.aspirerfid.libusbjava.host.ProxyLibUsbJavaService#strerror()
     */
    public String strerror(){
         return LibusbJava.usb_strerror();
     }


}
