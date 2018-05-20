/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redes.sockets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geoge
 */
public class Cliente extends Conexion{
    
    public Cliente() {
        super("cliente");
    }
    
    public void iniciarCliente(){
        Scanner sc = new Scanner(System.in);
        String bebida = "";
        try {
            System.out.println("Cliente Iniciado");
            salida = new DataOutputStream(cs.getOutputStream());
            System.out.println("Escriba una bebida");
            bebida = sc.nextLine();
            salida.writeUTF(bebida);
            System.out.println("Se ha mandado el mensaje " + bebida);
        } catch (IOException ex) {
            System.out.println("Problema iniciar cliente " + ex.getMessage());
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
