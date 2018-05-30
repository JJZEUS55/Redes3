/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redes.arduino;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author geoge
 */
public class Arduino {
    
    private final String PUERTO = "COM4";
    private PanamaHitek_Arduino ino;
    private SerialPortEventListener listener;
        
    public Arduino() throws ArduinoException{
        ino = new PanamaHitek_Arduino();
        listener = new SerialPortEventListener() {
            @Override
            public void serialEvent(SerialPortEvent spe) {
                try {
                    if(ino.isMessageAvailable()){
                        System.out.println("Resultado Serial Port: " + ino.printMessage());
                    }
                } catch (SerialPortException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(Arduino.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ArduinoException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(Arduino.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        ino.arduinoRXTX(PUERTO, 9600, listener);        
    }
    
    
    public void mandarMensajeArduino(String mensaje) throws ArduinoException, SerialPortException{
        System.out.println("Se ha mandado el mensaje al arduino: " + mensaje);
        ino.sendData(mensaje);
    }
    
    public String recibirMensajeArduino(){
        return "Aun no esta Implementado :'v";
    }
    
}
