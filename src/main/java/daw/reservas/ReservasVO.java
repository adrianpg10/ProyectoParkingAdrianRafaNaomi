/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.reservas;

import java.time.LocalDate;

/**
 *
 * @author adrip
 */
public class ReservasVO {
    private String matricula;
    private int numplaza;
    private String pin_fijo;
    private LocalDate feciniabono;
    private LocalDate fecfinabono;

    public ReservasVO() {
    }

    public ReservasVO(String matricula, int numplaza, String pin_fijo, LocalDate feciniabono, LocalDate fecfinabono) {
        this.matricula = matricula;
        this.numplaza = numplaza;
        this.pin_fijo = pin_fijo;
        this.feciniabono = feciniabono;
        this.fecfinabono = fecfinabono;
    }

    public String getMatricula() {
        return matricula;
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

    @Override
    public String toString() {
        return "ReservasVO{" + "matricula=" + matricula + ", numplaza=" + numplaza + ", pin_fijo=" + pin_fijo + ", feciniabono=" + feciniabono + ", fecfinabono=" + fecfinabono + '}';
    }
    
    
}
