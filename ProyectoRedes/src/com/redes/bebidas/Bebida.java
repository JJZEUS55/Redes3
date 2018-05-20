/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redes.bebidas;

/**
 *
 * @author geoge
 */
public class Bebida {
    private static int numBebida = 0;
    private String nombre;
    private int precio;
    private float cantidad;

    public Bebida(String nombre, int precio, float cantidad) {
        this.numBebida++;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getPrecio() {
        return this.precio;
    }

    public float getCantidad() {
        return this.cantidad;
    }
    
    public float cantidadRestante(float cantidadTomada){
        return this.cantidad - cantidadTomada;
    }

    public static int getNumBebida() {
        return numBebida;
    }
    
}
