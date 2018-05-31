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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortException;

/**
 *
 * @author geoge
 */
public class Servidor extends Conexion {

    private String mensajeRecibido;
    //private Arduino arduino;

    public Servidor() {
        super("servidor");
        
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
        
        ExecutorService ex = Executors.newCachedThreadPool();
        try {
            ex.execute(new Arduino(auxMsg));
            ex.shutdown();
        } catch (ArduinoException ex1) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex1);
        }
       
        
    }

}
