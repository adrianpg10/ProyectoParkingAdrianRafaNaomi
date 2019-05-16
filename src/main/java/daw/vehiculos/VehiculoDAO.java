/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.vehiculos;

import daw.parking.datos.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafa
 */
public class VehiculoDAO implements IVehiculo {
    private Connection con = null;

    public VehiculoDAO() {
        con = Conexion.getInstance();
    }

    @Override
    public List<VehiculoVO> mostrarTodo() throws SQLException {
        List<VehiculoVO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from vehiculo");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                VehiculoVO p = new VehiculoVO();
                // Recogemos los datos de la persona, guardamos en un objeto
                p.setMatricula(res.getString("matricula"));
                p.setTipoVehiculo(res.getString("tipoVehiculo"));
                p.setDescripcion(res.getString("descripcion"));

                //Añadimos el objeto a la lista
                lista.add(p);
            }
        }

        return lista;
    }

    @Override
    public VehiculoVO findByPk(int pk) throws SQLException {

        ResultSet res = null;
        VehiculoVO veh = new VehiculoVO();

        String sql = "select * from vehiculo where matricula=?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setInt(1, pk);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.first()) {
                // Recogemos los datos de la persona, guardamos en un objeto
                veh.setMatricula(res.getString("matricula"));
                veh.setTipoVehiculo(res.getString("tipoVehiculo"));
                veh.setDescripcion(res.getString("descripcion"));
                return veh;
            }

            return null;
        }
    }

    @Override
    public int insertarEmpleado(VehiculoVO veh) throws SQLException {

        int numFilas = 0;
        String sql = "insert into vehiculo values (?,?,?)";

        if (findByPk(Integer.parseInt(veh.getMatricula())) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(1, veh.getMatricula());
                prest.setString(2, veh.getTipoVehiculo());
                prest.setString(3, veh.getDescripcion());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }

    }

    @Override
    public int insertarVariosEmpleados(List<VehiculoVO> lista) throws SQLException {
        int numFilas = 0;

        for (VehiculoVO tmp : lista) {
            numFilas += insertarEmpleado(tmp);
        }

        return numFilas;
    }

    @Override
    public int borrarTodosEmpleados() throws SQLException {

        String sql = "delete from vehiculo";

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
    public int borrarEmpleado(VehiculoVO persona) throws SQLException {
        int numFilas = 0;

        String sql = "delete from vehiculo where matricula= ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setString(1, persona.getMatricula());
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int actualizarEmpleado(int pk, VehiculoVO nuevosDatos) throws SQLException {

        int numFilas = 0;
        String sql = "update vehiculo set tipoVehiculo = ?, descripcion = ? where matricula=?";

        if (findByPk(pk) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(1, nuevosDatos.getTipoVehiculo());
                prest.setString(2, nuevosDatos.getDescripcion());
                prest.setInt(3, pk);

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }


}
