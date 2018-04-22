/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class Servidor {

    DatagramSocket sockUDP;
    byte[] buffer;
    String ms;
    DatagramPacket peticion;
    DatagramPacket respuesta;

    public Servidor() {
        try {
            sockUDP = new DatagramSocket(9900);
            buffer = new byte[1000];

        } catch (SocketException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void iniciar() {
        try {
            System.out.println("Servidor iniciado...");
            peticion = new DatagramPacket(buffer, buffer.length);

            System.out.println("Haciendo peticion");
            sockUDP.receive(peticion);

            respuesta = new DatagramPacket(peticion.getData(), peticion.getLength(), peticion.getAddress(), peticion.getPort());

            //System.out.println("Respuesta del cliente: " + new String(respuesta.getData()));
            generarCurp();
            System.out.println("Enviando Respuesta");
            sockUDP.send(respuesta);

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generarCurp() {
        String apP;
        String apM;
        String n;
        String[] f = new String[3];
        String s;

        String curp = "";
        String[] datos = new String[5];
        String dividir = new String(respuesta.getData());
        datos = dividir.split("-");

        apP = datos[0].substring(0, 2).toUpperCase();
        apM = ("" + datos[1].charAt(0)).toUpperCase();
        n = ("" + datos[2].charAt(0)).toUpperCase();
        f = datos[3].split("/");
        s = datos[4].toUpperCase();

        curp = apP + apM + n + f[2] + f[1] + f[0] + s;
        System.out.println("De acuerdo a los datos proporcionados su curp es: \n"
                + curp);

//        for (int i = 0; i < datos.length; i++) {
//            if(i == 0){
//                datos[i].substring(0, 2);
//                System.out.println(datos[0]);
//            }
//        }
    }

}
