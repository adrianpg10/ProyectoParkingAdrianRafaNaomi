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
   private int numplaza;
   private String tipoPlaza;
   private String estadoplaza;
   private Double tarifa;
   private static int numero=45;

    public PlazasVO() {
    }

    public PlazasVO(int numplaza, String tipoPlaza, String estadoplaza, Double tarifa) {
        this.numplaza = numplaza;
        this.tipoPlaza = tipoPlaza;
        this.estadoplaza = estadoplaza;
        this.tarifa = tarifa;
    }

    public PlazasVO(String tipoPlaza) {
        this.numplaza = numero;
        this.tipoPlaza = tipoPlaza;
        this.estadoplaza = "libre";
        this.tarifa = tarifas();
        
        numero--;
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

    @Override
    public String toString() {
        return "PlazasVO{" + "numplaza=" + numplaza + ", tipoPlaza=" + tipoPlaza + ", estadoplaza=" + estadoplaza + ", tarifa=" + tarifa + '}';
    }
   
   
    public static void main(String[] args) {
        
       PlazasVO a = new PlazasVO("turismo");
       
        System.out.println(a);
    }
}
