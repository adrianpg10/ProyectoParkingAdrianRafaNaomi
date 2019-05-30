/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.plazas;

/**
 *
 * @author adrian
 */
public class PlazasVO {

    // Atributos
    private int numplaza;
    private String tipoPlaza;
    private String estadoplaza;
    private Double tarifa;
    private boolean estadoReservado;
   

    // Contructor por defecto
    public PlazasVO() {
    }

    // Constructor parametrizado

    public PlazasVO(int numplaza, String tipoPlaza, String estadoplaza, Double tarifa, boolean estadoReservado) {
        this.numplaza = numplaza;
        this.tipoPlaza = tipoPlaza;
        this.estadoplaza = estadoplaza;
        this.tarifa = tarifa;
        this.estadoReservado = estadoReservado;
    }
  

    // Constructor parametrizado al que se le pasa un tipo de plaza y la hacemos libre
    public PlazasVO(String tipoPlaza,int numero) {
        this.numplaza = numero;
        this.tipoPlaza = tipoPlaza;
        this.estadoplaza = "libre";
        this.tarifa = tarifas();
        this.estadoReservado = estadoReservado;

       
    }

    // Añadimos un metodo para calcular las tarifas
    public double tarifas() {

        if (tipoPlaza.equalsIgnoreCase("turismo")) {

            this.tarifa = 0.12;
        } else if (tipoPlaza.equalsIgnoreCase("caravana")) {

            this.tarifa = 0.45;
        } else if (tipoPlaza.equalsIgnoreCase("motocicleta")) {

            this.tarifa = 0.08;
        } else {
            System.out.println("El tipo de vehiculo es incorrecto, Se aplicará la tarifa: 0.0");
            this.tarifa = 0.0;
        }

        return this.tarifa;

    }

    public boolean isEstadoReservado() {
        return estadoReservado;
    }

    public void setEstadoReservado(boolean estadoReservado) {
        this.estadoReservado = estadoReservado;
    }

    // Getters y setters
    public int getNumplaza() {
        return numplaza;
    }

    public void setNumplaza(int numplaza) {
        this.numplaza = numplaza;
    }

    public String getTipoPlaza() {
        return tipoPlaza;
    }

    public void setTipoPlaza(String tipoPlaza) {
        this.tipoPlaza = tipoPlaza;
    }

    public String getEstadoplaza() {
        return estadoplaza;
    }

    public void setEstadoplaza(String estadoplaza) {
        this.estadoplaza = estadoplaza;
    }

    public Double getTarifa() {
        return tarifa;
    }

    public void setTarifa(Double tarifa) {
        this.tarifa = tarifa;
    }

    // toString
    @Override
    public String toString() {
        return numplaza + " : " + tipoPlaza + " : " + estadoplaza + " : " + tarifas() + " : " + estadoReservado;
    }

    public static void main(String[] args) {

        PlazasVO a = new PlazasVO("turismo",3);

        System.out.println(a);
    }
}
