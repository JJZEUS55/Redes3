/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redes.Logica;

import com.redes.Vistas.VistaOssec;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author geoge
 */
public class LeerArchivoAlerts {

    private BufferedReader archivo;
    //private static String reglas 
    private String nombreArchivo;
    private int cantOpciones;
    public String[] lineas;
    public String[] opciones;
    public Hashtable<String, String> reglaSignificado;
    public Hashtable<String, String> reglaNivel;

    public LeerArchivoAlerts(String archivoS) {
        this.lineas = null;
        this.opciones = null;
        this.cantOpciones = 0;
        this.nombreArchivo = archivoS;
        this.reglaSignificado = new Hashtable<String, String>();
        this.reglaNivel = new Hashtable<String, String>();
        setSignificadoReglas();
        setReglasNivel();
        try {
            this.archivo = new BufferedReader(new FileReader(nombreArchivo));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeerArchivoAlerts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSignificadoReglas() {
        reglaSignificado.put("Rule: 550 ", "'Integrity checksum changed.'");
        reglaSignificado.put("Rule: 503 ", "'Ossec agent started.'");
        reglaSignificado.put("Rule: 553 ", "'File deleted. Unable to retrieve checksum.'");
        reglaSignificado.put("Rule: 551 ", "'Integrity checksum changed again (2nd time).'");
        reglaSignificado.put("Rule: 552 ", "'Integrity checksum changed again (3rd time).'");
        reglaSignificado.put("Rule: 516 ", "'System Audit event.'");
        reglaSignificado.put("Rule: 5502 ", "'Login session closed.'");
        reglaSignificado.put("Rule: 5402 ", "'Successful sudo to ROOT executed'");
        reglaSignificado.put("Rule: 2902 ", "'New dpkg (Debian Package) installed.'");
        reglaSignificado.put("Rule: 2901 ", "'New dpkg (Debian Package) requested to install.'");
        reglaSignificado.put("Rule: 3302 ", "'Rejected by access list (Requested action not taken).'");
        reglaSignificado.put("Rule: 5403 ", "'First time user executed sudo.'");
        reglaSignificado.put("Rule: 1002 ", "'Integrity checksum changed.'");
        reglaSignificado.put("Rule: 502 ", "'Ossec server started.'");
        reglaSignificado.put("Rule: 5501 ", "'New ossec agent connected.'");
        reglaSignificado.put("Rule: 554 ", "'File added to the system'");
        reglaSignificado.put("Rule: 501 ", "'New ossec agent connected.'");
        reglaSignificado.put("Rule: 504 ", "'Ossec agent disconnected.'");
    }

    public void setReglasNivel() {
        reglaNivel.put("Rule: 550 ", "(level 7)");
        reglaNivel.put("Rule: 503 ", "(level 3)");
        reglaNivel.put("Rule: 553 ", "(level 7)");
        reglaNivel.put("Rule: 551 ", "(level 7)");
        reglaNivel.put("Rule: 552 ", "(level 7)");
        reglaNivel.put("Rule: 516 ", "(level 3)");
        reglaNivel.put("Rule: 5502 ", "(level 3)");
        reglaNivel.put("Rule: 5402 ", "(level 3)");
        reglaNivel.put("Rule: 2902 ", "(level 7)");
        reglaNivel.put("Rule: 2901 ", "(level 3)");
        reglaNivel.put("Rule: 3302 ", "(level 6)");
        reglaNivel.put("Rule: 5403 ", "(level 4)");
        reglaNivel.put("Rule: 1002 ", "(level 2)");
        reglaNivel.put("Rule: 502 ", "(level 3)");
        reglaNivel.put("Rule: 5501 ", "(level 3)");
        reglaNivel.put("Rule: 554 ", "(level 7)");
        reglaNivel.put("Rule: 501 ", "(level 3)");
        reglaNivel.put("Rule: 504 ", "(level 3)");
    }

    //Divide el archivo y lo guarda en un arreglo, lo hago por que el archivo tiene varios saltos de linea
    public void ordenarArchivo() {
        String aux = "";
        String linea = "";
        try {
            while ((aux = archivo.readLine()) != null) {
                if (aux.contains("Alert")) {
                    linea = linea + "\n" + aux;
                } else {
                    linea = linea + " " + aux;
                }
            }
            lineas = linea.split("\n");
        } catch (IOException ex) {
            Logger.getLogger(LeerArchivoAlerts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dividirOpciones() {
        this.cantOpciones = 0;
        String prueba = "Rule: 502";
        String prueba2 = "level 7";

        for (String linea : lineas) {
            if (linea.contains(prueba)) {
                cantOpciones++;
                System.out.println(linea);
            }
        }
        System.out.println("");
        System.out.println("Cantidad opc: " + cantOpciones);
    }

    //Busqueda solamente si el usuario ingresa la regla
    public void busquedasRegla(String regla) {
        for (String linea : lineas) {
            if (linea.contains(regla)) {
                // opciones = null;
                System.out.println(linea);
                opciones = linea.split(" ");
                dividirBusquedaGeneral(opciones, linea);
                cantOpciones++;
            }
        }
    }

    public void busquedaId(String anio) {
        for (String linea : lineas) {
            if (linea.contains(anio)) {
                // opciones = null;
                System.out.println(linea);
                opciones = linea.split(" ");
                dividirBusquedaGeneral(opciones, linea);
                cantOpciones++;
            }
        }
    }

    public void busquedaNivel(String nivel) {
        for (String linea : lineas) {
            if (linea.contains(nivel)) {
                // opciones = null;
                System.out.println(linea);
                opciones = linea.split(" ");
                dividirBusquedaGeneral(opciones, linea);
                cantOpciones++;
            }
        }
    }

    public void busquedaFecha(String regla, String anio, String mes, String dia, String hora) {
        for (String linea : lineas) {
            if (linea.contains(regla) && linea.contains(anio) && linea.contains(mes) && linea.contains(dia) && linea.contains(hora)) {
                System.out.println(linea);
                opciones = linea.split(" ");
                dividirBusquedaRegla(opciones, regla);
                cantOpciones++;
            }
        }
    }

    public void busquedaMul(String... parametros) {
        String aux = "";
        System.out.println("Aux vale: " + aux);

        for (String linea : lineas) {
            for (String parametro : parametros) {
                if ((linea.contains(parametro))) {
                    System.out.println(linea);
                    opciones = linea.split(" ");
                    dividirBusquedaGeneral(opciones, linea);
                    cantOpciones++;
                    break;
                }
            }

        }
    }

    //IMPORTANTE divide la cadena principal para poder meter los datos en la tabla correspondiente
    public void dividirBusquedaRegla(String[] linea, String regla1) {
        int[] pos = posicionRegla(regla1);
        System.out.println(pos);
        String regla = "";
        String id = linea[2];
        String anio = linea[7 - pos[0]];
        String mes = linea[8 - pos[0]];
        String dia = linea[9 - pos[0]];
        String hora = linea[10 - pos[0]];
        String usuario = linea[11 - pos[0]];
        //Se puede quitar esta parte pero prefiero dejarlo por que meñ :v
        if (regla1.contains("Rule: 503 ")
                || regla1.contains("Rule: 553 ")
                || regla1.contains("Rule: 551 ")
                || regla1.contains("Rule: 552 ")
                || regla1.contains("Rule: 501 ")
                || regla1.contains("Rule: 554 ")) {
            regla = regla1;
        } else {
            //regla = linea[13 - pos[0]];
            regla = regla1;
        }
        String nivelRegla = reglaNivel.get(regla1);
        String significadoRegla = reglaSignificado.get(regla1);

        VistaOssec.setValoresTabla(id, anio, mes, dia, hora, usuario, regla, nivelRegla, significadoRegla);
    }

    //TRATANDO DE OBTENER SIEMPRE LA REGLA YA QUE ESO FACILITA MUCHAS COSAS
    public void dividirBusquedaGeneral(String[] opciones, String linea) {
        String regla = "";
        regla = ExpresionesRegulares.expresionRegla(linea);
        dividirBusquedaRegla(opciones, regla);

    }

    //Utilizado por las posiciones diferentes de las reglas
    public int[] posicionRegla(String regla) {
        //se envian 2 debido a que algunas reglas se acomodan mal solamente en la regla :v
        int[] pos = new int[2];
        System.out.println("Entrando al switch");
        switch (regla) {
            case "Rule: 502 ":
                pos[0] = 0;
                pos[1] = 0;
                break;
            case "Rule: 501 ":
                pos[0] = 0;
                pos[1] = -1;
                break;
            case "Rule: 554 ":
                pos[0] = 0;
                pos[1] = -1;
                break;
            case "Rule: 516 ":
                pos[0] = 2;
                pos[1] = 0;
                break;
            case "Rule: 503 ":
                pos[0] = 0;
                pos[1] = -1;
                break;
            case "Rule: 553 ":
                pos[0] = 0;
                pos[1] = -1;
                break;
            case "Rule: 551 ":
                pos[0] = 0;
                pos[1] = -1;
                break;
            case "Rule: 552 ":
                pos[0] = 0;
                pos[1] = -1;
                break;
            case "Rule: 550 ":
                pos[0] = 0;
                pos[1] = 0;
                break;
            case "Rule: 5403 ":
                pos[0] = 0;
                pos[1] = 0;
                break;
            case "Rule: 2902 ":
                pos[0] = 0;
                pos[1] = 0;
                break;
            case "Rule: 1002 ":
                pos[0] = 0;
                pos[1] = 0;
                break;
            case "Rule: 5502 ":
                pos[0] = 2;
                pos[1] = 2;
                break;
            case "Rule: 5501 "://Completar
                pos[0] = 2;
                pos[1] = 2;
                break;
            case "Rule: 3302 "://Completar
                pos[0] = 2;
                pos[1] = 2;
                break;
            case "Rule: 2901 "://Completar
                pos[0] = 2;
                pos[1] = 2;
                break;
            case "Rule: 5402 ":
                pos[0] = 2;
                pos[1] = 2;
                break;
            default:
                System.out.println("Ingresaste una regla invalida!!!");
                break;
        }
        return pos;
    }

    //Obtenemos Lista de Usuarios con expresion regular
    public List getListaUsuarios() {
        Path dir = Paths.get(nombreArchivo);
        List<String> auxarchivo;
        Set<String> sinRepetir;
        List<String> aux = new ArrayList<String>();
        List<String> usuarios = new ArrayList<String>();
        String soloUsuario = "";

        try {
            auxarchivo = Files.readAllLines(dir);
            sinRepetir = auxarchivo.stream()
                    .filter(s -> s.contains("Agent started:"))
                    .map(s -> s.toString())
                    .collect(Collectors.toSet());

            aux = sinRepetir.stream().collect(Collectors.toList());

            usuarios.add(" ");//Se le añade para que en el combo box no aparesca como primera opcion un usuario

            for (int i = 0; i < aux.size(); i++) {
                soloUsuario = ExpresionesRegulares.expresionUsuario(aux.get(i));
                System.out.println(aux.get(i));
                usuarios.add(soloUsuario);
            }

        } catch (IOException ex) {
            Logger.getLogger(LeerArchivoAlerts.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuarios;
    }

    public int getCantOpciones() {
        return cantOpciones;
    }

}
