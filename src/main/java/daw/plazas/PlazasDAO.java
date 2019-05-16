/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.plazas;

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
/**
 *
 * @author adrian
 */
public class PlazasDAO implements IPlazas {
    private Connection con = null;

    public PlazasDAO() {
        
        con = Conexion.getInstance();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertCategorias(PlazasVO plazas) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertCategorias(List<PlazasVO> lista) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteCategorias(PlazasVO p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteCategorias() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateCategorias(int codcat, PlazasVO nuevosDatos) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
