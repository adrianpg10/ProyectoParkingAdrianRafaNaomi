/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.clientes;

import java.util.Random;

/**
 *
 * @author Rafa
 */
public class ClientesVO {

    // Atributos
    private String matricula;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String numTarjetaCredito;
    private int tipoAbono;
    private String email;

    // Constructor parametrizado
    public ClientesVO(String matricula, String dni, String nombre, String apellido1, String apellido2, String numTarjetaCredito, int tipoAbono, String email) {
        this.matricula = matricula;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.numTarjetaCredito = numTarjetaCredito;
        this.tipoAbono = tipoAbono;
        this.email = email;
    }

    // Constructor por defecto
    public ClientesVO() {
        
      
    }

    // Asignamos un numero al tipo de abono, mensual 1, trimestral 2 y anual 4.
    
    public static int NumeroParaAbono(String numero) {
        switch (numero) {
            case "Mensual":
                return 1;

            case "Trimestral":
                return 2;

            case "Semestral":

                return 3;

            case "Anual":
                return 4;

        }
        return 0;
    }
    
  // Metodo en el que mostramos el tipo de abonado
    public static String obtenerTipoAbono(ClientesVO Abonado) {
        switch (Abonado.getTipoAbono()) {
            case 1:
                return "Mensual";
            case 2:
                return "Trimestral";
            case 3:
                return "Semestral";

            case 4:
                return "Anual";
        }
        return null;
    }
    
  
    
    // Getters y setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNumTarjetaCredito() {
        return numTarjetaCredito;
    }

    public void setNumTarjetaCredito(String numTarjetaCredito) {
        this.numTarjetaCredito = numTarjetaCredito;
    }

    public int getTipoAbono() {
        return tipoAbono;
    }

    public void setTipoAbono(int tipoAbono) {
        this.tipoAbono = tipoAbono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // toString
    @Override
    public String toString() {
        return "matricula:" + matricula + ", dni:" + dni + ", nombre:" + nombre + ", apellido1:" + apellido1 + ", apellido2:" + apellido2 + ", numTarjetaCreadito:" + numTarjetaCredito + ", tipoAbono:" + tipoAbono + ", email:" + email;
    }

}
