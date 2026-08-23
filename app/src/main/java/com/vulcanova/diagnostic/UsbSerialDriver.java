package com.vulcanova.diagnostic;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;

public class UsbSerialDriver {

    private UsbDevice device;
        private UsbDeviceConnection connection;
            private UsbEndpoint endpointIn;
                private UsbEndpoint endpointOut;

                    public UsbSerialDriver(UsbDevice device, UsbDeviceConnection connection) {
                            this.device = device;
                                    this.connection = connection;
                                            initEndpoints();
                                                }

                                                    private void initEndpoints() {
                                                            if (device.getInterfaceCount() > 0) {
                                                                        UsbInterface usbInterface = device.getInterface(0);
                                                                                    for (int i = 0; i < usbInterface.getEndpointCount(); i++) {
                                                                                                    UsbEndpoint ep = usbInterface.getEndpoint(i);
                                                                                                                    if (ep.getType() == android.hardware.usb.UsbConstants.USB_ENDPOINT_XFER_BULK) {
                                                                                                                                        if (ep.getDirection() == android.hardware.usb.UsbConstants.USB_DIR_IN) {
                                                                                                                                                                endpointIn = ep;
                                                                                                                                                                                    } else {
                                                                                                                                                                                                            endpointOut = ep;
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                        }

                                                                                                                                                                                                                                                                            public boolean sendCommand(String command) {
                                                                                                                                                                                                                                                                                    if (connection != null && endpointOut != null) {
                                                                                                                                                                                                                                                                                                byte[] bytes = command.getBytes();
                                                                                                                                                                                                                                                                                                            int result = connection.bulkTransfer(endpointOut, bytes, bytes.length, 2000);
                                                                                                                                                                                                                                                                                                                        return result >= 0;
                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                        return false;
                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                            