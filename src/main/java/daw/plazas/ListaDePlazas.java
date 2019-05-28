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
public class ListaDePlazas {
    
    // Aquí es donde hacemos una lista donde las plazas tienen el valor por defecto "libre"
    // Añadimos 15 plazas de cada tipo (motocicleta,caravana y turismo)
    
    public static void main(String[] args) throws SQLException {
        PlazasDAO daoPlaza = new PlazasDAO();
        ArrayList<PlazasVO> listaPlazas = new ArrayList<>();
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        
         listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        System.out.println("Nº plazas insertados " + daoPlaza.insertPlazas(listaPlazas));
        System.out.println("-----------------------------------------");
        System.out.println("Comprobamos en una nueva lista que se recogen los datos desde la tabla.");
        List<PlazasVO> nuevaLista = daoPlaza.getAll();
        System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
        nuevaLista.forEach(System.out::println);
    }
}
