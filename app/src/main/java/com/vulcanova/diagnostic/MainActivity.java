package com.vulcanova.diagnostic;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TextView statusText;
        private UsbManager usbManager;
            private static final String ACTION_USB_PERMISSION = "com.vulcanova.diagnostic.USB_PERMISSION";

                @Override
                    protected void onCreate(Bundle savedInstanceState) {
                            super.onCreate(savedInstanceState);
                                    
                                            // Creamos una interfaz básica directamente en pantalla para ver el estado del escáner
                                                    statusText = new TextView(this);
                                                            statusText.setText("Iniciando Vulcanova Diagnostic Pro...\nBuscando dispositivo USB OTG...");
                                                                    statusText.setTextSize(18f);
                                                                            setContentView(statusText);

                                                                                    usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
                                                                                            checkConnectedDevices();
                                                                                                }

                                                                                                    private void checkConnectedDevices() {
                                                                                                            HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
                                                                                                                    if (deviceList.isEmpty()) {
                                                                                                                                statusText.setText("⚠️ No hay ningún escáner conectado.\nConecta el cable USB OTG al puerto OBD2.");
                                                                                                                                        } else {
                                                                                                                                                    for (UsbDevice device : deviceList.values()) {
                                                                                                                                                                    statusText.setText("✅ Escáner detectado:\n" + device.getDeviceName() + 
                                                                                                                                                                                                       "\nID de Vendedor: " + device.getVendorId() + 
                                                                                                                                                                                                                                          "\nID de Producto: " + device.getProductId());
                                                                                                                                                                                                                                                          // Aquí se enlazará eldriver USB para la lectura de códigos de diagnóstico
                                                                                                                                                                                                                                                                      }
                                                                                                                                                                                                                                                                              }
                                                                                                                                                                                                                                                                                  }
                                                                                                                                                                                                                                                                                  }
                                                                                                                                                                                                                                                                                  