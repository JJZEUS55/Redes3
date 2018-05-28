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

    private int numBebida = 0;
    private String nombre;
    private int precio;
    private int cantidadActual;
    private int cantidadTomada;
    private final int CANTIDAD_MAXIMA = 600;

    public Bebida(String nombre, int precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadActual = cantidad;
        this.cantidadTomada = 0;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getPrecio() {
        return this.precio;
    }

    public int getCantidadActual() {
        return this.cantidadActual - this.cantidadTomada;
    }

    public int getCantidadMaxima() {
        return this.CANTIDAD_MAXIMA;
    }

    public int getCantidadTomada() {
        return cantidadTomada;
    }

    public void setCantidadTomada(int cantidadTomada) {
        this.cantidadTomada += cantidadTomada;
    }

    public void aumentarNumBebida() {
        numBebida += 1;
    }

    public int getNumBebida() {
        return numBebida;
    }

    public void setNumBebida(int numeroBebidas) {
        this.numBebida = numeroBebidas;
    }

}
