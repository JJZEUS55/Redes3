/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectorbitacoras;

import com.redes.Logica.LeerArchivoAlerts;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.List;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author geoge
 */
public class LectorBitacoras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Path dir = Paths.get("alerts.log");
        List<String> archivo;
        Set<String> res;
        List<String> aux;

        try {
            archivo = Files.readAllLines(dir);

            res = archivo.stream()
                    .filter(s -> s.contains("Ossec"))
                    //.filter(prdct)
                    .map(s -> s.toString())
                    .collect(Collectors.toSet());

//            res.stream()
//                    .forEach(s -> System.out.println(s));
            aux = res.stream()
                    .collect(Collectors.toList());
            
            aux.stream()
                    .forEach(s -> System.out.println(s));

           
            String usu = aux.get(0);
            String usu2 = aux.get(3);
                    
            List<String> prueba1 = new ArrayList<>();
            
            Pattern p = Pattern.compile("Rule: [0-9]* ");
            Matcher m = p.matcher(usu);
            Matcher m2 = p.matcher(usu2);
            while (m.find()) {
                System.out.println(m.group());
                prueba1.add(m.group());
            }
            while (m2.find()) {
                System.out.println(m2.group());
                prueba1.add(m2.group());
            }
            
            
            System.out.println(prueba1);
            System.out.println(prueba1.size());

        } catch (IOException ex) {
            Logger.getLogger(LectorBitacoras.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
