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

    public VehiculoVO(String matricula, String tipoVehiculo) {
        this.matricula = matricula;
        this.tipoVehiculo = tipoVehiculo;
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

    @Override
    public String toString() {
        return "VehiculoVO{" + "matricula=" + matricula + ", tipoVehiculo=" + tipoVehiculo + '}';
    }
    

 
    
    
}
