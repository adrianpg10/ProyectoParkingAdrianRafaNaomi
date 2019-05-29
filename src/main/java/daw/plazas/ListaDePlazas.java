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
    
    // Insertamos en una lista las 45 plazas, 15 de cada tipo y por defecto en libres
    
    public static void main(String[] args) throws SQLException {
        PlazasDAO daoPlaza = new PlazasDAO();
        ArrayList<PlazasVO> listaPlazas = new ArrayList<>();
        listaPlazas.add(new PlazasVO("Motocicleta",0));
        listaPlazas.add(new PlazasVO("Motocicleta",1));
        listaPlazas.add(new PlazasVO("Motocicleta",2));
        listaPlazas.add(new PlazasVO("Motocicleta",3));
        listaPlazas.add(new PlazasVO("Motocicleta",4));
        listaPlazas.add(new PlazasVO("Motocicleta",5));
        listaPlazas.add(new PlazasVO("Motocicleta",6));
        listaPlazas.add(new PlazasVO("Motocicleta",7));
        listaPlazas.add(new PlazasVO("Motocicleta",8));
        listaPlazas.add(new PlazasVO("Motocicleta",9));
        listaPlazas.add(new PlazasVO("Motocicleta",10));
        listaPlazas.add(new PlazasVO("Motocicleta",11));
        listaPlazas.add(new PlazasVO("Motocicleta",12));
        listaPlazas.add(new PlazasVO("Motocicleta",13));
        listaPlazas.add(new PlazasVO("Motocicleta",14));
        listaPlazas.add(new PlazasVO("Caravana",15));
        listaPlazas.add(new PlazasVO("Caravana",16));
        listaPlazas.add(new PlazasVO("Caravana",17));
        listaPlazas.add(new PlazasVO("Caravana",18));
        listaPlazas.add(new PlazasVO("Caravana",19));
        listaPlazas.add(new PlazasVO("Caravana",20));
        listaPlazas.add(new PlazasVO("Caravana",21));
        listaPlazas.add(new PlazasVO("Caravana",22));
        listaPlazas.add(new PlazasVO("Caravana",23));
        listaPlazas.add(new PlazasVO("Caravana",24));
        listaPlazas.add(new PlazasVO("Caravana",25));
        listaPlazas.add(new PlazasVO("Caravana",26));
        listaPlazas.add(new PlazasVO("Caravana",27));
        listaPlazas.add(new PlazasVO("Caravana",28));
        listaPlazas.add(new PlazasVO("Caravana",29)); 
        listaPlazas.add(new PlazasVO("Turismo",30));
        listaPlazas.add(new PlazasVO("Turismo",31));
        listaPlazas.add(new PlazasVO("Turismo",32));
        listaPlazas.add(new PlazasVO("Turismo",33));
        listaPlazas.add(new PlazasVO("Turismo",34));
        listaPlazas.add(new PlazasVO("Turismo",35));
        listaPlazas.add(new PlazasVO("Turismo",36));
        listaPlazas.add(new PlazasVO("Turismo",37));
        listaPlazas.add(new PlazasVO("Turismo",38));
        listaPlazas.add(new PlazasVO("Turismo",39));
        listaPlazas.add(new PlazasVO("Turismo",40));
        listaPlazas.add(new PlazasVO("Turismo",41));
        listaPlazas.add(new PlazasVO("Turismo",42));
        listaPlazas.add(new PlazasVO("Turismo",43));
        listaPlazas.add(new PlazasVO("Turismo",44));
        System.out.println("Nº plazas insertados " + daoPlaza.insertPlazas(listaPlazas));
        System.out.println("-----------------------------------------");
        System.out.println("Comprobamos en una nueva lista que se recogen los datos desde la tabla.");
        List<PlazasVO> nuevaLista = daoPlaza.getAll();
        System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
        nuevaLista.forEach(System.out::println);
    }
}
