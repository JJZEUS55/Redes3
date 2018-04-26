/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializar;

import java.io.Serializable;

/**
 *
 * @author geoge
 */
public class Animal implements Serializable{

    int tamanio;
    String especie;
    String color;
    int numPatas;
    String habitat;

    public Animal(int tamanio, String especie, String color, int numPatas, String habitat) {
        this.tamanio = tamanio;
        this.especie = especie;
        this.color = color;
        this.numPatas = numPatas;
        this.habitat = habitat;
    }

    public void comer() {
        System.out.println("soy un " + especie + " y estoy comiendo");
    }

    public void dormir() {
        System.out.println("soy un " + especie + " y estoy durmiendo");
    }

    public void reproducrise() {
        System.out.println("soy un " + especie + " y estoy haciendo mas de mi :D");
    }
    
    @Override
    public String toString(){
        return "Especie: " + especie + "\nTamaño: " + tamanio + "\nColor: " + color + "\nNumero de patas: " + numPatas + "\nHabitat: " + habitat;
    }

}
