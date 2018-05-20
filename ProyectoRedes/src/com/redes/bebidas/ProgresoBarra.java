/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redes.bebidas;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author geoge
 */
public class ProgresoBarra implements Callable{
    private Bebida bebida;
    private int contBebida;

    public ProgresoBarra(Bebida bebida) {
        this.bebida = bebida;
        this.contBebida = 1;
    }
    
    public ProgresoBarra(){
        
    }
       
    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        contBebida += 1;
        return contBebida;
    }
    
    
}
