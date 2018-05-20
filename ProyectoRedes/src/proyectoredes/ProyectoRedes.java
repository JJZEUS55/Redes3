/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoredes;

import com.redes.sockets.Cliente;

/**
 *
 * @author geoge
 */
public class ProyectoRedes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cliente cli = new Cliente();
        cli.iniciarCliente();
    }
    
}
