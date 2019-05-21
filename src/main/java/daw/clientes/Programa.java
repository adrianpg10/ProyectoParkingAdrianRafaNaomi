/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.clientes;

import daw.clientes.ClientesDAO;
import daw.clientes.ClientesVO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adrian
 */
public class Programa {

    public static void main(String[] args) {

        ClientesDAO daoClientes = new ClientesDAO();

        List<ClientesVO> listaClientes = new ArrayList<>();
        listaClientes.add(new ClientesVO("1234ADF", "77859934F", "Manuel", "Fernández", "García", "7896541278965412", "Abonado", "manuelfg@gmail.com"));

        try {

            System.out.println("Nº categorias insertadas " + daoClientes.insertarClientes(listaClientes));
            System.out.println("-----------------------------------------");
            System.out.println("Comprobamos en una nueva lista que se recogen los datos desde la tabla.");
            List<ClientesVO> nuevaLista = daoClientes.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
            nuevaLista.forEach(System.out::println);

        } catch (SQLException sqle) {
            System.out.println("No se ha podido realizar la operación:");
            System.out.println(sqle.getMessage());
        }
        System.out.println();
        listaClientes.forEach(System.out::println);
    }

}

