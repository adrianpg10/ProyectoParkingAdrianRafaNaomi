/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.vehiculos;

/**
 *
 * @author rafa
 */
public class VehiculoVO {

    // Atributos
    private String matricula;
    private String tipoVehiculo;

    // Constructor parametrizado
    public VehiculoVO(String matricula, String tipoVehiculo) {
        this.matricula = matricula;
        this.tipoVehiculo = tipoVehiculo;
    }

    // Constructor parametrizado al que se le pasa una matricula
    public VehiculoVO(String matricula) {
        this.matricula = matricula;
    }

    // Constructor por defecto
    public VehiculoVO() {
    }

    // Getters y setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    // toString
    @Override
    public String toString() {
        return matricula + ":" + tipoVehiculo;
    }

}
