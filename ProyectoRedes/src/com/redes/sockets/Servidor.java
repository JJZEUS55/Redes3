/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redes.sockets;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geoge
 */
public class Servidor extends Conexion{
    private String mensajeRecibido;
    
    public Servidor() {
        super("servidor");
    }    
    
    public void iniciarServidor(){
        try {
            System.out.println("Esperando Cliente...");
            cs = ss.accept();
            System.out.println("Cliente Conectado");
            entrada = new DataInputStream(cs.getInputStream());
            this.mensajeRecibido = entrada.readUTF();
            System.out.println("Se ha recibido el mensaje " + this.mensajeRecibido);
            cs.close();
        } catch (IOException ex) {
            System.out.println("Problema Iniciar Servidor: " + ex.getMessage());
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getMensajeRecibido() {
        return mensajeRecibido;
    }    
    
}
