/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.plazas;

import daw.clientes.ClientesVO;

import java.sql.Connection;
import daw.parking.datos.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author adrian
 */
public class PlazasDAO implements IPlazas {

    private Connection con = null;

    public PlazasDAO() {

        con = Conexion.getInstance();
    }

    // Metodo para averiguar el numero de plazas libres de cada vehiculo
    public void getEstadosPlaza() throws SQLException {

        String plazasTurismo = "select count(*) from plazas where estadoplaza='0'and tipoPlaza='Turismo'";
        String plazasCaravana = "select count(*) from plazas where estadoplaza='0'and tipoPlaza='Caravana'";
        String plazasMotocicleta = "select count(*) from plazas where estadoplaza='0'and tipoPlaza='Motocicleta'";

        try (PreparedStatement prest = con.prepareStatement(plazasTurismo)) {

            ResultSet res = null;
            res = prest.executeQuery();
            if (res.next()) {
                int aux = res.getInt(1);
                System.out.println("Plazas de turismo libres :" + aux);
            }
        }
        try (PreparedStatement prest = con.prepareStatement(plazasCaravana)) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet

            ResultSet res = null;
            res = prest.executeQuery();
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            if (res.next()) {
                int aux = res.getInt(1);
                System.out.println("Plazas de caravana libres :" + aux);
            }
        }
        try (PreparedStatement prest = con.prepareStatement(plazasMotocicleta)) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet

            ResultSet res = null;
            res = prest.executeQuery();
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            if (res.next()) {
                int aux = res.getInt(1);
                System.out.println("Plazas de motocicleta libres :" + aux);
            }
        }

    }

    @Override
    public List<PlazasVO> getAll() throws SQLException {
        List<PlazasVO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from plazas");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                PlazasVO p = new PlazasVO();
                // Recogemos los datos de la persona, guardamos en un objeto
                p.setNumplaza(res.getInt("numplaza"));
                p.setTipoPlaza(res.getString("tipoPlaza"));
                p.setEstadoplaza(res.getString("estadoplaza"));
                p.setTarifa(res.getDouble("tarifa"));

                //Añadimos el objeto a la lista
                lista.add(p);
            }
        }
        return lista;
    }

    @Override
    public PlazasVO findByPk(int numplaza) throws SQLException {

        ResultSet res = null;
        PlazasVO plazas = new PlazasVO();

        String sql = "select * from plazas where numplaza=?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setInt(1, numplaza);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.first()) {
                // Recogemos los datos de la persona, guardamos en un objeto
                plazas.setNumplaza(res.getInt("numplaza"));
                plazas.setTipoPlaza(res.getString("tipoPlaza"));
                plazas.setEstadoplaza(res.getString("estadoplaza"));
                plazas.setTarifa(res.getDouble("tarifa"));
                return plazas;
            }

            return null;
        }
    }

    @Override
    public int insertPlazas(PlazasVO plazas) throws SQLException {
        int numFilas = 0;
        String sql = "insert into plazas values (?,?,?,?)";

        if (findByPk(plazas.getNumplaza()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setInt(1, plazas.getNumplaza());
                prest.setString(2, plazas.getTipoPlaza());
                prest.setString(3, plazas.getEstadoplaza());
                prest.setDouble(4, plazas.getTarifa());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    @Override
    public int insertPlazas(List<PlazasVO> lista) throws SQLException {
        int numFilas = 0;

        for (PlazasVO tmp : lista) {
            numFilas += insertPlazas(tmp);
        }

        return numFilas;
    }

    @Override
    public int deletePlazas(PlazasVO p) throws SQLException {
        int numFilas = 0;

        String sql = "delete from plazas where numplaza = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setInt(1, p.getNumplaza());
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int deletePlazas() throws SQLException {
        String sql = "delete from plazas";

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
    public int updatePlazas(int numplaza, PlazasVO nuevosDatos) throws SQLException {
        int numFilas = 0;
        String sql = "update plazas set tipoPlaza = ?, estadoplaza = ?, tarifa = ?  where numplaza=?";

        if (findByPk(numplaza) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(1, nuevosDatos.getTipoPlaza());
                prest.setString(2, nuevosDatos.getEstadoplaza());
                prest.setDouble(3, nuevosDatos.getTarifa());
                prest.setInt(4, numplaza);

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    // Metodo para la asignacion de una plaza
    public void asignacionPlz(ClientesVO aux) {

        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca matrícula");
        String matri = teclado.nextLine();
        System.out.println("Introduzca tipo de vehículo");
        String tipo = teclado.nextLine();

        PlazasVO aux2 = new PlazasVO();

    }

 
}
