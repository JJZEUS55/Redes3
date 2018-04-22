/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;

/**
 *
 * @author alumno
 */
public class Persona {
    
    private String ApPaterno;
    private String ApMaterno;
    private String Nombre;
    private String FecNacimiento;
    private String Sexo;

    public Persona(String ApPaterno, String ApMaterno, String Nombre, String FecNacimiento, String Sexo) {
        this.ApPaterno = ApPaterno;
        this.ApMaterno = ApMaterno;
        this.Nombre = Nombre;
        this.FecNacimiento = FecNacimiento;
        this.Sexo = Sexo;
    }

    public String getApPaterno() {
        return ApPaterno;
    }

    public String getApMaterno() {
        return ApMaterno;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getFecNacimiento() {
        return FecNacimiento;
    }

    public String getSexo() {
        return Sexo;
    }
    
    
    
    
    
}
