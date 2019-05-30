/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.reservas;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adrip
 */
public class ReservasVO {

    // Atributos
    private String matricula;
    private int numplaza;
    private String pin_fijo;
    private LocalDate feciniabono;
    private LocalDate fecfinabono;
    private int importe;

    // Constructor por defecto
    public ReservasVO() {
    }

    // Constructor parametrizado

    public ReservasVO(String matricula, int numplaza, String pin_fijo, LocalDate feciniabono, LocalDate fecfinabono, int importe) {
        this.matricula = matricula;
        this.numplaza = numplaza;
        this.pin_fijo = pin_fijo;
        this.feciniabono = feciniabono;
        this.fecfinabono = fecfinabono;
        this.importe = importe;
    }


    // Generamos un pin aleatorio para la reserva
    public static String generarPin() {
        String pinDesechable = "";
        Random aleatorio = new Random();
        int generador;
        for (int i = 0; i < 6; i++) {
            generador = aleatorio.nextInt(10);
            pinDesechable += generador;
        }

        return pinDesechable;
    }

    // Getters y setters
    public String getMatricula() {
        return matricula;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getNumplaza() {
        return numplaza;
    }

    public void setNumplaza(int numplaza) {
        this.numplaza = numplaza;
    }

    public String getPin_fijo() {
        return pin_fijo;
    }

    public void setPin_fijo(String pin_fijo) {
        this.pin_fijo = pin_fijo;
    }

    public LocalDate getFeciniabono() {
        return feciniabono;
    }

    public void setFeciniabono(LocalDate feciniabono) {
        this.feciniabono = feciniabono;
    }

    public LocalDate getFecfinabono() {
        return fecfinabono;
    }

    public void setFecfinabono(LocalDate fecfinabono) {
        this.fecfinabono = fecfinabono;
    }

    // toString
    @Override
    public String toString() {
       
        return matricula + " : " + numplaza + " : " + pin_fijo + " : " + feciniabono + " : " + fecfinabono + " : " + importe;
    }

}
