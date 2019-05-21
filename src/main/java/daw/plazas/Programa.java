/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.plazas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adrian
 */
public class Programa {
    public static void main(String[] args) {
        
    
        PlazasDAO daoPlazas = new PlazasDAO();

        List<PlazasVO> listaPlazas = new ArrayList<>();
        listaPlazas.add(new PlazasVO(17, "Garaje1", "disponible", 2.6));

        try {

            System.out.println("Nº categorias insertadas " + daoPlazas.insertPlazas(listaPlazas));
            System.out.println("-----------------------------------------");
            System.out.println("Comprobamos en una nueva lista que se recogen los datos desde la tabla.");
            List<PlazasVO> nuevaLista = daoPlazas.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
            nuevaLista.forEach(System.out::println);

        } catch (SQLException sqle) {
            System.out.println("No se ha podido realizar la operación:");
            System.out.println(sqle.getMessage());
        }
        System.out.println();
        listaPlazas.forEach(System.out::println);
    }

}