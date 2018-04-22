/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class Cliente {

    DatagramSocket sockUDP;
    String mensaje;
    byte[] buffer;
    byte[] buffer2;
    InetAddress hostServidor;
    int puerto;
    DatagramPacket peticion;
    DatagramPacket respuesta;

    String apPaterno;
    String apMaterno;
    String nombre;
    String fecNacimiento;
    String sexo;
    String formatoCompleto;

    public Cliente() {
        try {
            sockUDP = new DatagramSocket();
            mensaje = "";
            buffer = new byte[1000];
            buffer2 = new byte[1000];
            hostServidor = InetAddress.getByName("localhost");
            puerto = 9900;

        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String pedirDatos() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese Apellido Paterno : ");
        apPaterno = sc.nextLine();
        
        System.out.println(apPaterno.substring(0, 2));
        
        System.out.println("Ingrese Apellido Materno : ");
        apMaterno = sc.nextLine();
        System.out.println("Ingrese nombre : ");
        nombre = sc.nextLine();
        System.out.println("Ingrese Fecha nacimiento : ");
        fecNacimiento = sc.nextLine();
        System.out.println("Ingrese Sexo : ");
        sexo = sc.nextLine();

        return apPaterno + "-" + apMaterno + "-" + nombre + "-" + fecNacimiento + "-" + sexo;
    }

    public void iniciar() {
        try {
            String curp = pedirDatos();
            buffer2 = curp.getBytes();
//            mensaje[0] = 1;
//            mensaje[1] = 3;
//            mensaje[2] = 7;

            peticion = new DatagramPacket(buffer2, buffer2.length, hostServidor, puerto);
            sockUDP.send(peticion);

            respuesta = new DatagramPacket(buffer, buffer.length);
            sockUDP.receive(respuesta);

            System.out.println("Respuesta : " + new String(respuesta.getData()));

            sockUDP.close();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
