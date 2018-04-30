/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redes.Logica;

import com.redes.Vistas.VistaSnort;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geoge
 */
public class Snort {

    private BufferedReader archivo;
    //private static String reglas 
    private String nombreArchivo;
    private int cantOpciones;
    public String[] lineas;
    public String[] opciones;
    public Hashtable<String, String> reglaSignificado;
    public Hashtable<String, String> reglaNivel;

    public Snort(String archivo) {
        this.lineas = null;
        this.opciones = null;
        this.cantOpciones = 0;
        this.nombreArchivo = archivo;
        try {
            this.archivo = new BufferedReader(new FileReader(nombreArchivo));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeerArchivoAlerts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Ordena el archivo para que todo salga en una linea, es mas facil trabajar en una linea que en varias :v
    public String ordenarArchivo() {
        String aux = "";
        String linea = "";
        try {
            while ((aux = archivo.readLine()) != null) {
                if (aux.contains("[**]")) {
                    linea = linea + "\n" + aux;
                } else {
                    linea = linea + " " + aux;
                }

            }
            //System.out.println(linea);
            lineas = linea.split("\n");
        } catch (IOException ex) {
            Logger.getLogger(LeerArchivoAlerts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return linea;
    }

    public void busqueda(String... parametros) {
        String aux = "";
        System.out.println("Aux vale: " + aux);

        for (String linea : lineas) {
            for (String parametro : parametros) {
                if ((linea.contains(parametro))) {
                    System.out.println(linea);
                    opciones = linea.split(" ");
                    //dividirBusquedaGeneral(opciones, linea);
                    dividirBusqueda(opciones, linea);
                    cantOpciones++;
                    break;
                }
            }

        }
    }

    public void dividirBusqueda(String[] opciones, String linea) {
        //opciones = lineas[1].split(" ");
        String id = opciones[20];        
        String clasficicacion = ExpresionesRegulares.expresionClasificacion(linea);
        String significado = opciones[2];
        String []fechaHora = opciones[13].split("-");//[0] contine fecha [1] contiene hora
        String prioridad = ExpresionesRegulares.expresionPrioridad(linea);
        
        VistaSnort.setValoresTabla(id, fechaHora[0], fechaHora[1], clasficicacion, prioridad, significado);

    }
    
}
