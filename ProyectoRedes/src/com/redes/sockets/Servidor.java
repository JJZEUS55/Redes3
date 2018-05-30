/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redes.sockets;

import com.panamahitek.ArduinoException;
import com.redes.arduino.Arduino;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortException;

/**
 *
 * @author geoge
 */
public class Servidor extends Conexion {

    private String mensajeRecibido;
    private Arduino arduino;

    public Servidor() {
        super("servidor");
        try {
            arduino = new Arduino();
        } catch (ArduinoException ex) {
            ex.printStackTrace();
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void iniciarServidor() {
        String mensajeToArduino = "";
        try {
            System.out.println("Esperando Cliente...");
            cs = ss.accept();
            System.out.println("Cliente Conectado");
            entrada = new DataInputStream(cs.getInputStream());
            this.mensajeRecibido = entrada.readUTF();
            System.out.println("Se ha recibido el mensaje " + this.mensajeRecibido);
            seleccionarMandar(mensajeRecibido);          
            cs.close();
        } catch (IOException ex) {
            System.out.println("Problema Iniciar Servidor: " + ex.getMessage());
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getMensajeRecibido() {
        return mensajeRecibido;
    }

    public void seleccionarMandar(String bebida) {
        String auxMsg = "";
        switch (bebida) {
            case "Coca":
               auxMsg = "1";
                break;
            case "Fanta":
                auxMsg = "2";
                break;
            case "Sprite":
                auxMsg = "3";
                break;

            default:
                System.out.println("No mame prro");
                break;
        }
        
        try {
            arduino.mandarMensajeArduino(auxMsg);
        } catch (ArduinoException ex) {
            ex.printStackTrace();
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortException ex) {
            ex.printStackTrace();
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
