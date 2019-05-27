/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.tickets;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

/**
 *
 * @author adrip
 */
public class TicketsVO {

    //Atributos
    private int codticket;
    private int numplaza;
    private String matricula;
    private String pin_desechable;
    private LocalDate fecinipin;
    private LocalDate fecfinpin;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private static int numero = 45;
    

    //Método constructor parametrizado
    public TicketsVO(int codticket, int numplaza, String matricula, String pin_desechable, LocalDate fecinipin, LocalDate fecfinpin, LocalTime horaInicio, LocalTime horaFin) {
        this.codticket = codticket;
        this.numplaza = numplaza;
        this.matricula = matricula;
        this.pin_desechable = pin_desechable;
        this.fecinipin = fecinipin;
        this.fecfinpin = fecfinpin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public TicketsVO(String matricula) {

        this.matricula = matricula;
        this.codticket = generarcodigo();
        this.numplaza = numero ;
        this.pin_desechable = generarPin();
        this.fecinipin = LocalDate.now();
        this.fecfinpin = LocalDate.now();
        this.horaInicio = LocalTime.now();
        this.horaFin = LocalTime.now();

        numero--;
        
    }

    // Generamos un codigo aleatorio al ticket
    
    private int generarcodigo() {

        Random aleatorio = new Random();

        int codigo = aleatorio.nextInt(50000 - 10100 + 1) + 10100;

        return codigo;

    }


    private String generarPin() {
        String pinDesechable = "";
        Random aleatorio = new Random();
        int generador;
        for (int i = 0; i < 6; i++) {
            generador = aleatorio.nextInt(10);
            pinDesechable += generador;
        }

        return pinDesechable;
    }

    //Constructor por defecto
    public TicketsVO() {

    }

    //Métodos getters y setters
    public int getCodticket() {
        return codticket;
    }

    public void setCodticket(int codticket) {
        this.codticket = codticket;
    }

    public int getNumplaza() {
        return numplaza;
    }

    public void setNumplaza(int numplaza) {
        this.numplaza = numplaza;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPin_desechable() {
        return pin_desechable;
    }

    public void setPin_desechable(String pin_desechable) {
        this.pin_desechable = pin_desechable;
    }

    public LocalDate getFecinipin() {
        return fecinipin;
    }

    public void setFecinipin(LocalDate fecinipin) {
        this.fecinipin = fecinipin;
    }

    public LocalDate getFecfinpin() {
        return fecfinpin;
    }

    public void setFecfinpin(LocalDate fecfinpin) {
        this.fecfinpin = fecfinpin;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    //Método toString
    @Override
    public String toString() {
        return "TicketsVO{" + "codticket=" + codticket + ", numplaza=" + numplaza + ", matricula=" + matricula + ", pin_desechable=" + pin_desechable + ", fecinipin=" + fecinipin + ", fecfinpin=" + fecfinpin + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + '}';
    }

    public static void main(String[] args) {

        TicketsVO t = new TicketsVO("4567asd");
         TicketsVO v = new TicketsVO("1234asd");
           TicketsVO c = new TicketsVO("1477asd");
         
         
         
        System.out.println(t);
        System.out.println(v);
        System.out.println(c);
        System.out.println("-------------------");
//        System.out.println(t.generarCodigo());
    }

}
