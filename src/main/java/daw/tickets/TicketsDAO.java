/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.tickets;

import daw.parking.datos.Conexion;
import daw.reservas.ReservasVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adrip
 */
public class TicketsDAO implements ITickets {

    private Connection con = null;

    public TicketsDAO() {
        con = Conexion.getInstance();
    }

    @Override
    public List<TicketsVO> getAll() throws SQLException {
        List<TicketsVO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from tickets");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                TicketsVO t = new TicketsVO();
                // Recogemos los datos de la persona, guardamos en un objeto
                t.setNumplaza(res.getInt("numplaza"));
                t.setMatricula(res.getString("matricula"));
                t.setPin_desechable(res.getString("pin_desechable"));
                t.setFecinipin(res.getDate("fecinipin").toLocalDate());
                t.setFecfinpin(res.getDate("fecfinpin").toLocalDate());

                //Añadimos el objeto a la lista
                lista.add(t);
            }
        }
        return lista;
    }

    @Override
    public TicketsVO findByPk(int numplaza, String matricula) throws SQLException {
        ResultSet res = null;
        TicketsVO tickets = new TicketsVO();

        String sql = "select * from tickets where numplaza=?, matricula=?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setInt(1, numplaza);
            prest.setString(2, matricula);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.first()) {
                // Recogemos los datos de la persona, guardamos en un objeto

                tickets.setNumplaza(res.getInt("numplaza"));
                tickets.setMatricula(res.getString("matricula"));
                tickets.setPin_desechable(res.getString("pin_desechable"));
                tickets.setFecinipin(res.getDate("fecinipin").toLocalDate());
                tickets.setFecfinpin(res.getDate("fecfinpin").toLocalDate());
                return tickets;
            }

            return null;
        }
    }

    @Override
    public int insertTickets(TicketsVO tickets) throws SQLException {
        int numFilas = 0;
        String sql = "insert into tickets values (?,?,?,?,?)";

        if (findByPk(tickets.getNumplaza(), tickets.getMatricula()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setInt(1, tickets.getNumplaza());
                prest.setString(2, tickets.getMatricula());
                prest.setString(3, tickets.getPin_desechable());
                prest.setString(4, tickets.getFecinipin().format(DateTimeFormatter.ISO_DATE));
                prest.setString(5, tickets.getFecfinpin().format(DateTimeFormatter.ISO_DATE));

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    @Override
    public int insertTickets(List<TicketsVO> lista) throws SQLException {
        int numFilas = 0;

        for (TicketsVO tmp : lista) {
            numFilas += insertTickets(tmp);
        }

        return numFilas;
    }

    @Override
    public int deleteTickets(TicketsVO t) throws SQLException {
        int numFilas = 0;

        String sql = "delete from tickets numplaza= ?, matricula = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setInt(1, t.getNumplaza());
            prest.setString(2, t.getMatricula());

            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int deleteTickets() throws SQLException {
        String sql = "delete from tickets";

        int nfilas = 0;

        // Preparamos el borrado de datos  mediante un Statement
        // No hay parámetros en la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecución de la sentencia
            nfilas = st.executeUpdate(sql);
        }

        // El borrado se realizó con éxito, devolvemos filas afectadas
        return nfilas;
    }

    @Override
    public int updateTickets(int numplaza, String matricula, TicketsVO nuevosDatos) throws SQLException {
        int numFilas = 0;
        String sql = "update tickets set pin_desechable = ?, fecinipin = ?,fecfinpin =?  where numplaza=?, matricula=?";

        if (findByPk(numplaza, matricula) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(1, nuevosDatos.getPin_desechable());
                prest.setString(2, nuevosDatos.getFecinipin().format(DateTimeFormatter.ISO_DATE));
                prest.setString(3, nuevosDatos.getFecfinpin().format(DateTimeFormatter.ISO_DATE));

                prest.setInt(4, numplaza);
                prest.setString(5, matricula);

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }
}
