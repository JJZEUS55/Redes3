/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoredes;

import com.redes.sockets.Servidor;

/**
 *
 * @author geoge
 */
public class Prueba {

    public static void main(String[] args) {
        Servidor ser = new Servidor();
        while (true) {
            ser.iniciarServidor();
        }
    }
}
