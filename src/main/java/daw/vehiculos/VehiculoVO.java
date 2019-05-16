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
    private String matricula;
    private String tipoVehiculo;
    private String descripcion;

    public VehiculoVO(String matricula, String tipoVehiculo, String descripcion) {
        this.matricula = matricula;
        this.tipoVehiculo = tipoVehiculo;
        this.descripcion = descripcion;
    }

    public VehiculoVO() {
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "matricula:" + matricula + ", tipoVehiculo:" + tipoVehiculo + ", descripcion:" + descripcion;
    }
    
    
}
