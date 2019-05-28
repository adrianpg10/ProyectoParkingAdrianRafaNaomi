/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.tickets;

import daw.reservas.ReservasVO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author adrip
 */
public interface ITickets {
    // Método para obtener todos los registros de la tabla

    List<TicketsVO> getAll() throws SQLException;

    // Méodo para obtener un registro a partir de la PK
    TicketsVO findByPk(int codticket, String matricula) throws SQLException;

    // Método para insertar un registro
    int insertTickets(TicketsVO tickets) throws SQLException;

    // Método para insertar varios registros
    int insertTickets(List<TicketsVO> lista) throws SQLException;

    // Método para borrar una categoria
    int deleteTickets(TicketsVO t) throws SQLException;

    // Método para borrar toda la tabla
    int deleteTickets() throws SQLException;

    // Método para modificar una tickets. Se modifica al ticket que tenga esa 'pk'
    // con los nuevos datos que traiga el ticket 'nuevosDatos'
    int updateTickets(int codticket, String matricula, TicketsVO nuevosDatos) throws SQLException;
}
