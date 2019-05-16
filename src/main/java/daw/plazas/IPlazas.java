/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.plazas;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author adrian
 */
public interface IPlazas {
     // Método para obtener todos los registros de la tabla
    List<PlazasVO> getAll() throws SQLException;
    
    // Méodo para obtener un registro a partir de la PK
    PlazasVO findByPk(int numplaza) throws SQLException;
    
    // Método para insertar un registro
    int insertPlazas (PlazasVO plazas) throws SQLException;
    
    // Método para insertar varios registros
    int insertPlazas (List<PlazasVO> lista) throws SQLException;
    
    // Método para borrar una categoria
    int deletePlazas (PlazasVO p) throws SQLException;
    
    // Método para borrar toda la tabla
    int deletePlazas() throws SQLException;
    
    // Método para modificar una persona. Se modifica a la persona que tenga esa 'pk'
    // con los nuevos datos que traiga la persona 'nuevosDatos'
    int updatePlazas (int numplaza, PlazasVO nuevosDatos) throws SQLException;
}
