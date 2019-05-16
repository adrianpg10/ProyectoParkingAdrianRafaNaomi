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

    public PlazasVO() {
    }

    public PlazasVO(int numplaza, String tipoPlaza, String estadoplaza, Double tarifa) {
        this.numplaza = numplaza;
        this.tipoPlaza = tipoPlaza;
        this.estadoplaza = estadoplaza;
        this.tarifa = tarifa;
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
   
   
   
}
