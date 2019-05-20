/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.clientes;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Rafa
 */
public interface IClientes {
     // Método para obtener todos los registros de la tabla
    List<ClientesVO> getAll() throws SQLException;
    
    // Méodo para obtener un registro a partir de la PK
    ClientesVO findByPk(String matricula) throws SQLException;
    
    // Método para insertar un registro
    int insertClientes (ClientesVO p) throws SQLException;
    
    // Método para insertar varios registros
    int insertClientes (List<ClientesVO> lista) throws SQLException;
    
    // Método para borrar una categoria
    int deleteClientes (ClientesVO p) throws SQLException;
    
    // Método para borrar toda la tabla
    int deleteClientes() throws SQLException;
    
    // Método para modificar una persona. Se modifica a la persona que tenga esa 'pk'
    // con los nuevos datos que traiga la persona 'nuevosDatos'
    int updateClientes (String matricula, ClientesVO nuevosDatos) throws SQLException;
}
