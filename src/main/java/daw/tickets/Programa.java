/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.tickets;

import daw.plazas.PlazasDAO;
import daw.plazas.PlazasVO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adrian
 */
public class Programa {
    public static void main(String[] args) {
        
    
    
        TicketsDAO daoTickets = new TicketsDAO();

        List<TicketsVO> listaTickets = new ArrayList<>();
        listaTickets.add(new TicketsVO(2, 17,"1234abc", "152487", LocalDate.of(2019, Month.MARCH, 8),LocalDate.of(2019, Month.APRIL, 8)));

        try {

            System.out.println("Nº categorias insertadas " + daoTickets.insertTickets(listaTickets));
            System.out.println("-----------------------------------------");
            System.out.println("Comprobamos en una nueva lista que se recogen los datos desde la tabla.");
            List<TicketsVO> nuevaLista = daoTickets.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
            nuevaLista.forEach(System.out::println);

        } catch (SQLException sqle) {
            System.out.println("No se ha podido realizar la operación:");
            System.out.println(sqle.getMessage());
        }
        System.out.println();
        listaTickets.forEach(System.out::println);
    }
    
}

