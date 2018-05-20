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
public class ProgresoBarra implements Runnable{
    private Bebida bebida;
    private int contBebida;

    public ProgresoBarra(Bebida bebida) {
        this.bebida = bebida;
        this.contBebida = 0;
    }
    
    @Override
    public void run() {
        contBebida++;
        
    }
    
    
}
