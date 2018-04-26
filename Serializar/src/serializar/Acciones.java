/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geoge
 */
public class Acciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Animal gato1 = new Animal(10, "Gato", "Blanco", 4, "Casa");
        Animal gato2 = new Animal(10, "Gato", "Negro", 4, "Calle");

        System.out.println("---------------------------------");
        System.out.println("Leyendo los objetos");
        System.out.println(gato1);
        System.out.println("\n");
        System.out.println(gato2);

        //Escribir en archivo
        try (FileOutputStream fos = new FileOutputStream("animal.cer");
                ObjectOutputStream out = new ObjectOutputStream(fos)) {
            System.out.println("---------------------------------");
            System.out.println("Esciribiendo en archivo ----animal.cer----");
            out.writeObject(gato1);
            out.writeObject(gato2);

        } catch (IOException ex) {
            Logger.getLogger(Acciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (FileInputStream fis = new FileInputStream("animal.cer");
                ObjectInputStream in = new ObjectInputStream(fis)) {
            System.out.println("---------------------------------");
            System.out.println("Leeyendo archivo ----animal.cer----");
            Animal gatoLeer = (Animal) in.readObject();
            System.out.println(gatoLeer);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Acciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Acciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Acciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
