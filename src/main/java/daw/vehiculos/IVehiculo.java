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
    VehiculoVO findByPk(int codigo) throws SQLException;
    
    // Método para insertar un registro
    int insertarEmpleado (VehiculoVO persona) throws SQLException;
    
    // Método para insertar varios registros
    int insertarVariosEmpleados (List<VehiculoVO> lista) throws SQLException;
    
    // Método para borrar una persona
    int borrarEmpleado (VehiculoVO p) throws SQLException;
    
    // Método para borrar toda la tabla
    int borrarTodosEmpleados() throws SQLException;
    
    // Método para modificar una persona. Se modifica a la persona que tenga esa 'pk'
    // con los nuevos datos que traiga la persona 'nuevosDatos'
    int actualizarEmpleado (int pk, VehiculoVO nuevosDatos) throws SQLException;
}