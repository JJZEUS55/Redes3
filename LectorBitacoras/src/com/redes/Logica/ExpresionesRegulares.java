/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redes.Logica;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author geoge
 */
public class ExpresionesRegulares {

    private static Pattern pat;
    private static Matcher mat;

    public ExpresionesRegulares() {

    }

    //Expresion que obtiene lo que esta entre ' '  sirve para identificar a los usuarios
    public static String expresionUsuario(String usuario) {
        String aux = "";
        //pat = Pattern.compile("\'([^\"]*)\'");
        pat = Pattern.compile("[A-Za-z0-9]+->*");
        mat = pat.matcher(usuario);

        while (mat.find()) {
            aux = mat.group();
        }
        
        return aux;
    }
    
    public static String expresionRegla(String linea){
        String aux = "";
        pat = Pattern.compile("Rule: [0-9]* ");
        mat = pat.matcher(linea);
        
        while(mat.find()){
            aux = mat.group();
        }
        return aux;
    }

}
