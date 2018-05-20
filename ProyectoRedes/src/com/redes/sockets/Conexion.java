/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redes.sockets;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geoge
 */
public class Conexion {

    protected ServerSocket ss;
    protected Socket cs;
    private String host = "localhost";
    private int puerto = 3060;
    protected DataInputStream entrada;
    protected DataOutputStream salida;

    public Conexion(String tipo) {
        try {
            if (tipo.equals("cliente")) {
                cs = new Socket(host, puerto);
            } else {
                ss = new ServerSocket(puerto);
            }
        } catch (IOException ex) {
            System.out.println("Problema Conexion: " + ex.getMessage());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
