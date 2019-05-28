/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.vehiculos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adrian
 */
public class Programa {

    // Main de pruebas de VehiculosDAO
    public static void main(String[] args) {

        VehiculoDAO daoVehiculos = new VehiculoDAO();

        List<VehiculoVO> listaVehiculos = new ArrayList<>();
        listaVehiculos.add(new VehiculoVO("1234abc", "turismo"));

        try {

            System.out.println("Nº categorias insertadas " + daoVehiculos.insertarVehiculo(listaVehiculos));
            System.out.println("-----------------------------------------");
            System.out.println("Comprobamos en una nueva lista que se recogen los datos desde la tabla.");
            List<VehiculoVO> nuevaLista = daoVehiculos.mostrarTodo();
            System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
            nuevaLista.forEach(System.out::println);

        } catch (SQLException sqle) {
            System.out.println("No se ha podido realizar la operación:");
            System.out.println(sqle.getMessage());
        }
        System.out.println();
        listaVehiculos.forEach(System.out::println);
    }

}
