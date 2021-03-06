/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.vehiculos;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author rafa
 */
public interface IVehiculo {
    // Método para obtener todos los registros de la tabla

    List<VehiculoVO> mostrarTodo() throws SQLException;

    // Méodo para obtener un registro a partir de la PK
    VehiculoVO findByPk(String codigo) throws SQLException;

    // Método para insertar un registro
    int insertarVehiculo(VehiculoVO vehiculo) throws SQLException;

    // Método para insertar varios registros
    int insertarVehiculo(List<VehiculoVO> lista) throws SQLException;

    // Método para borrar una persona
    int borrarVehiculo(VehiculoVO p) throws SQLException;

    // Método para borrar toda la tabla
    int borrarTodosVehiculo() throws SQLException;

    // Método para modificar una persona. Se modifica el vehiculo que tenga esa 'pk'
    // con los nuevos datos que traiga el vehiculo 'nuevosDatos'
    int actualizarVehiculo(String pk, VehiculoVO nuevosDatos) throws SQLException;
}
