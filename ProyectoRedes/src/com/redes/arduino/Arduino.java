/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redes.arduino;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import com.sun.org.apache.xerces.internal.impl.dv.xs.TimeDV;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author geoge
 */
public class Arduino implements Runnable{
    
    private final String PUERTO = "COM12";
    private PanamaHitek_Arduino ino;
    private String mensaje;
        
    public Arduino(String mensaje) throws ArduinoException{
        ino = new PanamaHitek_Arduino();
        ino.arduinoTX(PUERTO, 9600);     
        this.mensaje = mensaje;
    }
    
    
    public void mandarMensajeArduino() throws ArduinoException, SerialPortException, InterruptedException{
        System.out.println("Se ha mandado el mensaje al arduino: " + mensaje);
        ino.sendData(mensaje);
        //TimeUnit.SECONDS.sleep(11);
        
    }
    
    public void apagarArduino() throws ArduinoException, SerialPortException{
        System.out.println("Se esta mandando un 0 ");
        ino.sendData("0");
    }
    
    public String recibirMensajeArduino(){
        return "Aun no esta Implementado :'v";
    }

    @Override
    public void run() {
        try {
            mandarMensajeArduino();
            Thread.sleep(10000);
            apagarArduino();
        } catch (ArduinoException ex) {
            Logger.getLogger(Arduino.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortException ex) {
            Logger.getLogger(Arduino.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Arduino.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
