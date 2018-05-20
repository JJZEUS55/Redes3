/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redes.bebidas;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author geoge
 */
public class Ventas {
    private List<Bebida> bebidasVendidas;
    private int ingresos;
    
    public Ventas(){
        this.bebidasVendidas = new ArrayList<Bebida>();
        this.ingresos = 0;
    }
    
    public void addBebidaVendida(Bebida b){
        this.bebidasVendidas.add(b);
        this.ingresos += b.getPrecio();
    }
    
    public void removeBebidaVendida(int num){
        this.ingresos -= this.bebidasVendidas.get(num).getCantidadActual(); //Eliminamos primero el costo de la bebida        
        this.bebidasVendidas.remove(num); //Eliminamos la bebida vendida
    }
}
