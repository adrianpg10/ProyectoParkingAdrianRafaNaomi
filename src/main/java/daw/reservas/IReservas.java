/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.reservas;

import daw.plazas.PlazasVO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author adrip
 */
public interface IReservas {
     // Método para obtener todos los registros de la tabla
    List<ReservasVO> getAll() throws SQLException;
    
    // Méodo para obtener un registro a partir de la PK
    ReservasVO findByPk(String matricula, int numplaza) throws SQLException;
    
    // Método para insertar un registro
    int insertReservas (ReservasVO reservas) throws SQLException;
    
    // Método para insertar varios registros
    int insertReservas (List<ReservasVO> lista) throws SQLException;
    
    // Método para borrar una categoria
    int deleteReservas (ReservasVO r) throws SQLException;
    
    // Método para borrar toda la tabla
    int deleteReservas() throws SQLException;
    
    // Método para modificar una reserva. Se modifica a la reserva que tenga esa 'pk'
    // con los nuevos datos que traiga la persona 'nuevosDatos'
    int updateReservas (String matricula,int numplaza , ReservasVO nuevosDatos) throws SQLException;
}
