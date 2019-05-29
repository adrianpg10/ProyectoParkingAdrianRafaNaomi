/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.reservas;

import daw.clientes.ClientesDAO;
import daw.parking.datos.Conexion;
import daw.plazas.PlazasVO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author adrip
 */
public class ReservasDAO implements IReservas {

    private Connection con = null;

    public ReservasDAO() {
        con = Conexion.getInstance();
    }

    @Override
    public List<ReservasVO> getAll() throws SQLException {
        List<ReservasVO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from reservas");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                ReservasVO r = new ReservasVO();
                // Recogemos los datos de la persona, guardamos en un objeto
                r.setMatricula(res.getString("matricula"));
                r.setNumplaza(res.getInt("numplaza"));
                r.setFeciniabono(res.getDate("feciniabono").toLocalDate());
                r.setFeciniabono(res.getDate("fecfinabono").toLocalDate());

                //Añadimos el objeto a la lista
                lista.add(r);
            }
        }
        return lista;
    }

    @Override
    public int insertReservas(ReservasVO reservas) throws SQLException {
        int numFilas = 0;
        String sql = "insert into reservas values (?,?,?,?,?)";

        if (findByPk(reservas.getMatricula(), reservas.getNumplaza()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(1, reservas.getMatricula());
                prest.setInt(2, reservas.getNumplaza());
                prest.setString(3, reservas.getPin_fijo());
                prest.setDate(4, Date.valueOf(reservas.getFeciniabono()));
                prest.setDate(5, Date.valueOf(reservas.getFecfinabono()));

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    @Override
    public int insertReservas(List<ReservasVO> lista) throws SQLException {
        int numFilas = 0;

        for (ReservasVO tmp : lista) {
            numFilas += insertReservas(tmp);
        }

        return numFilas;
    }

    @Override
    public int deleteReservas(ReservasVO r) throws SQLException {
        int numFilas = 0;

        String sql = "delete from reservas where matricula = ? and numplaza= ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setString(1, r.getMatricula());
            prest.setInt(2, r.getNumplaza());

            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int deleteReservas() throws SQLException {
        String sql = "delete from reservas";

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
    public ReservasVO findByPk(String matricula, int numplaza) throws SQLException {
        ResultSet res = null;
        ReservasVO reservas = new ReservasVO();

        String sql = "select * from reservas where matricula=? and numplaza=?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setString(1, matricula);
            prest.setInt(2, numplaza);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.first()) {
                // Recogemos los datos de la persona, guardamos en un objeto
                reservas.setMatricula(res.getString("matricula"));
                reservas.setNumplaza(res.getInt("numplaza"));
                reservas.setPin_fijo(res.getString("pin_fijo"));
                reservas.setFeciniabono(res.getDate("feciniabono").toLocalDate());
                reservas.setFecfinabono(res.getDate("fecfinabono").toLocalDate());
                return reservas;
            }

            return null;
        }

    }

    @Override
    public int updateReservas(String matricula, int numplaza, ReservasVO nuevosDatos) throws SQLException {
        int numFilas = 0;
        String sql = "update reservas set pin_fijo = ?, feciniabono = ?,fecfinabono =?  where matricula=? and numplaza=?";

        if (findByPk(matricula, numplaza) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(1, nuevosDatos.getPin_fijo());
                prest.setDate(2, Date.valueOf(nuevosDatos.getFeciniabono()));
                prest.setDate(3, Date.valueOf(nuevosDatos.getFecfinabono()));
                prest.setString(4, matricula);
                prest.setInt(5, numplaza);

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

 

    public static void main(String[] args) throws SQLException {
   //     ReservasDAO.depositarAbo();
    }

}
