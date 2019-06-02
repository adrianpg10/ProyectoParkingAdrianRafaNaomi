/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.clientes;

import daw.parking.datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafa
 */
public class ClientesDAO implements IClientes {

    private Connection con = null;

    public ClientesDAO() {

        con = Conexion.getInstance();
    }

    @Override
    public List<ClientesVO> getAll() throws SQLException {
        List<ClientesVO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from clientes");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                ClientesVO p = new ClientesVO();
                // Recogemos los datos de la persona, guardamos en un objeto
                p.setMatricula(res.getString("matricula"));
                p.setDni(res.getString("dni"));
                p.setNombre(res.getString("nombre"));
                p.setApellido1(res.getString("apellido1"));
                p.setApellido2(res.getString("apellido2"));
                p.setNumTarjetaCredito(res.getString("numTarjetaCredito"));
                p.setTipoAbono(res.getInt("tipoAbono"));
                p.setEmail(res.getString("email"));

                //Añadimos el objeto a la lista
                lista.add(p);
            }
        }
        return lista;
    }

    @Override
    public ClientesVO findByPk(String matricula) throws SQLException {

        ResultSet res = null;
        ClientesVO p = new ClientesVO();

        String sql = "select * from clientes where matricula=?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setString(1, matricula);
            

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.first()) {
                // Recogemos los datos de la persona, guardamos en un objeto
                p.setMatricula(res.getString("matricula"));
                p.setDni(res.getString("dni"));
                p.setNombre(res.getString("nombre"));
                p.setApellido1(res.getString("apellido1"));
                p.setApellido2(res.getString("apellido2"));
                p.setNumTarjetaCredito(res.getString("numTarjetaCredito"));
                p.setTipoAbono(res.getInt("tipoAbono"));
                p.setEmail(res.getString("email"));
                return p;
            }

            return null;
        }
    }

    @Override
    public int insertarClientes(ClientesVO c) throws SQLException {
        int numFilas = 0;
        String sql = "insert into clientes values (?,?,?,?,?,?,?,?)";

        if (findByPk(c.getMatricula()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(1, c.getMatricula());
                prest.setString(2, c.getDni());
                prest.setString(3, c.getNombre());
                prest.setString(4, c.getApellido1());
                prest.setString(5, c.getApellido2());
                prest.setString(6, c.getNumTarjetaCredito());
                prest.setInt(7, c.getTipoAbono());
                prest.setString(8, c.getEmail());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    @Override
    public int insertarClientes(List<ClientesVO> lista) throws SQLException {
        int numFilas = 0;

        for (ClientesVO tmp : lista) {
            numFilas += insertarClientes(tmp);
        }

        return numFilas;
    }

    @Override
    public int deleteClientes(ClientesVO p) throws SQLException {
        int numFilas = 0;

        String sql = "delete from clientes where matricula = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setString(1, p.getMatricula());
            
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int deleteClientes() throws SQLException {
        String sql = "delete from clientes";

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
    public int updateClientes(String matricula, ClientesVO nuevosDatos) throws SQLException {
        int numFilas = 0;
        String sql = "update clientes set dni=?, nombre = ?, apellido1 = ?, apellido2 = ?, numTarjetaCredito=?, tipoAbono=?, email=?  where matricula=?";

        if (findByPk(matricula) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(8, matricula);
                prest.setString(1, nuevosDatos.getDni());
                prest.setString(2, nuevosDatos.getNombre());
                prest.setString(3, nuevosDatos.getApellido1());
                prest.setString(4, nuevosDatos.getApellido2());
                prest.setString(5, nuevosDatos.getNumTarjetaCredito());
                prest.setInt(6, nuevosDatos.getTipoAbono());
                prest.setString(7, nuevosDatos.getEmail());
                
                

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }
}
