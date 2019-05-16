/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.clientes;

/**
 *
 * @author Rafa
 */
public class ClientesVO {
    private String matricula;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String numTarjetaCreadito;
    private String tipoAbono;
    private String email;

    public ClientesVO(String matricula, String dni, String nombre, String apellido1, String apellido2, String numTarjetaCreadito, String tipoAbono, String email) {
        this.matricula = matricula;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.numTarjetaCreadito = numTarjetaCreadito;
        this.tipoAbono = tipoAbono;
        this.email = email;
    }

    public ClientesVO() {
    }

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

    public String getNumTarjetaCreadito() {
        return numTarjetaCreadito;
    }

    public void setNumTarjetaCreadito(String numTarjetaCreadito) {
        this.numTarjetaCreadito = numTarjetaCreadito;
    }

    public String getTipoAbono() {
        return tipoAbono;
    }

    public void setTipoAbono(String tipoAbono) {
        this.tipoAbono = tipoAbono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "matricula:" + matricula + ", dni:" + dni + ", nombre:" + nombre + ", apellido1:" + apellido1 + ", apellido2:" + apellido2 + ", numTarjetaCreadito:" + numTarjetaCreadito + ", tipoAbono:" + tipoAbono + ", email:" + email;
    }
    
    
           
}
