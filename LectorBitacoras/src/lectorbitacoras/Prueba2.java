/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectorbitacoras;

import com.redes.Logica.Snort;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author geoge
 */
public class Prueba2 {

    public static void main(String[] args) {
        List<String> archivo;
        Snort leer = new Snort("alert.full");
        String lin = leer.ordenarArchivo();
        

        Path dir = Paths.get("alert.full");
        Set<String> res;
        List<String> aux;
        Set<String> p1;
        
        //leer.dividirBusqueda(lin);

//        try {
//            archivo = Files.readAllLines(dir);
//
//            p1 = archivo.stream()
//                    .filter(s -> s.contains("Priority"))
//                    .collect(Collectors.toSet());
//            
////            p1.stream()
////                    .forEach(s -> System.out.println(s));
//            
//        } catch (IOException e) {
//            System.out.println(e);
//        }

    }
}
