/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.tickets;

import daw.parking.datos.Conexion;
import daw.reservas.ReservasVO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
                t.setCodticket(res.getInt("codticket"));
                t.setNumplaza(res.getInt("numplaza"));
                t.setMatricula(res.getString("matricula"));
                t.setPin_desechable(res.getString("pin_desechable"));
                t.setFecinipin(res.getDate("fecinipin").toLocalDate());
                t.setFecfinpin(res.getDate("fecfinpin").toLocalDate());
                t.setHoraInicio(res.getTime("horaInicio").toLocalTime());
                t.setHoraFin(res.getTime("horaFin").toLocalTime());
                t.setImporteAbonado(res.getDouble("importeAbonado"));

                //Añadimos el objeto a la lista
                lista.add(t);
            }
        }
        return lista;
    }

    @Override
    public TicketsVO findByPk(int codticket, String matricula) throws SQLException {
        ResultSet res = null;
        TicketsVO tickets = new TicketsVO();

        String sql = "select * from tickets where codticket=? and matricula=?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setInt(1, codticket);
            prest.setString(2, matricula);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.first()) {
                // Recogemos los datos de la persona, guardamos en un objeto
                tickets.setCodticket(res.getInt("codticket"));
                tickets.setNumplaza(res.getInt("numplaza"));
                tickets.setMatricula(res.getString("matricula"));
                tickets.setPin_desechable(res.getString("pin_desechable"));
                tickets.setFecinipin(res.getDate("fecinipin").toLocalDate());
                tickets.setFecfinpin(res.getDate("fecfinpin").toLocalDate());
                tickets.setHoraInicio(res.getTime("horaInicio").toLocalTime());
                tickets.setHoraFin(res.getTime("horaFin").toLocalTime());
                tickets.setImporteAbonado(res.getDouble("importeAbonado"));
                return tickets;
            }

            return null;
        }
    }

    @Override
    public int insertTickets(TicketsVO tickets) throws SQLException {
        int numFilas = 0;
        String sql = "insert into tickets values (?,?,?,?,?,?,?,?,?)";

        if (findByPk(tickets.getCodticket(), tickets.getMatricula()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setInt(1, tickets.getCodticket());
                prest.setInt(2, tickets.getNumplaza());
                prest.setString(3, tickets.getMatricula());
                prest.setString(4, tickets.getPin_desechable());
                prest.setDate(5, Date.valueOf(tickets.getFecinipin()));

                prest.setDate(6, Date.valueOf(tickets.getFecfinpin()));
                prest.setTime(7, Time.valueOf(tickets.getHoraInicio()));
                prest.setTime(8, Time.valueOf(tickets.getHoraFin()));
                prest.setDouble(9, tickets.getImporteAbonado());

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

        String sql = "delete from tickets where codticket= ? and matricula = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setInt(1, t.getCodticket());
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
    public int updateTickets(int codticket, String matricula, TicketsVO nuevosDatos) throws SQLException {
        int numFilas = 0;
        String sql = "update tickets set pin_desechable = ?, fecinipin = ?,fecfinpin =?, horaInicio =?, horaFin =?, numplaza =?, importeAbonado =?  where codticket=? and matricula=?";

        if (findByPk(codticket, matricula) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(1, nuevosDatos.getPin_desechable());
                prest.setDate(2, Date.valueOf(nuevosDatos.getFecinipin()));
                prest.setDate(3, Date.valueOf(nuevosDatos.getFecfinpin()));
                prest.setTime(4, Time.valueOf(nuevosDatos.getHoraInicio()));
                prest.setTime(5, Time.valueOf(nuevosDatos.getHoraFin()));

                prest.setInt(6, nuevosDatos.getNumplaza());
                prest.setDouble(7, nuevosDatos.getImporteAbonado());
                prest.setInt(8, codticket);
                prest.setString(9, matricula);

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    // Metodo para calcular el importe de cada vehiculo en cada plaza donde usamos la sentencia sql para consultar
    // la fecha de inicio y fin y hora de inicio y fin de ese vehiculo en concreto con la matricula que le pasamos
    public double importeVehiculo(String matricula, String numeroPlaza) throws SQLException {
        double precio = 0.0;
        LocalDateTime fin = null;
        LocalDateTime inicio = null;

        ResultSet res = null;
        String sql = "select fecinipin, fecfinpin, horaInicio, horaFin from tickets where matricula = ?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {

            prest.setString(1, matricula);
            res = prest.executeQuery();

            // Si la consulta tiene contenido guardamos en dos variables de tipo LocalDateTime el valor de la fecha y hora de inicio
            // y de la fecha y hora de fin
            if (res.next()) {
                inicio = LocalDateTime.of(res.getDate("fecinipin").toLocalDate(), res.getTime("horaInicio").toLocalTime());
                fin = LocalDateTime.of(res.getDate("fecfinpin").toLocalDate(), res.getTime("horaFin").toLocalTime());

            }

        }
        ResultSet prec = null;

        // Consultamos la tarifa de la plaza donde sea igual a la plaza que le pasamos en el metodo
        String sql2 = "select tarifa from plazas where numplaza = ?";

        try (PreparedStatement prest = con.prepareStatement(sql2)) {

            prest.setString(1, numeroPlaza);
            prec = prest.executeQuery();

            // Si la consulta tiene contenido guardamos en una variable double la tarifa de dicho vehiculo
            if (prec.next()) {
                precio = prec.getDouble("tarifa");

            }
        }
        
        // Usamos chronounit para ver concretamente cuantos minutos han pasado desde que ha entrado el vehiculo hasta
        // que sale
        long minutos = ChronoUnit.MINUTES.between(inicio, fin);

        // Y ya multiplicamos los minutos transcurridos por la tarifa de dicho vehiculo y nos sale lo que hay que pagar
        double importe = precio * minutos;
        return importe;

    }

}
