/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.parking.datos;

import daw.clientes.ClientesDAO;
import daw.clientes.ClientesVO;
import daw.plazas.PlazasDAO;
import daw.plazas.PlazasVO;
import daw.reservas.ReservasDAO;
import daw.reservas.ReservasVO;
import daw.tickets.TicketsDAO;
import daw.tickets.TicketsVO;
import daw.vehiculos.VehiculoDAO;
import daw.vehiculos.VehiculoVO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Rafa
 */
public class CopiaSeguridad {

    // Metodo para crear las copias de seguridad de todas las tablas de nuestra base de datos
    public static void crearCopia() {
        // Declaramos todas las clases DAO correpondientes a nuestras tablas
        ClientesDAO cliente = new ClientesDAO();
        VehiculoDAO vehiculo = new VehiculoDAO();
        PlazasDAO plaza = new PlazasDAO();
        ReservasDAO reservas = new ReservasDAO();
        TicketsDAO ticket = new TicketsDAO();
        // Declaramos la fecha y hora de ahora para poner el nombre al archivo
        LocalDate fechaAct = LocalDate.now();
        LocalTime horaAct = LocalTime.now();

        try {
            // Cremos las listas de cada una de las tablas y usamos el metodo getAll() para que muestre todo lo que contiene
            // y lo meta dentro
            ArrayList<PlazasVO> listaPlaza = (ArrayList<PlazasVO>) plaza.getAll();
            ArrayList<ReservasVO> listaReserva = (ArrayList<ReservasVO>) reservas.getAll();
            ArrayList<TicketsVO> listaTicket = (ArrayList<TicketsVO>) ticket.getAll();
            ArrayList<ClientesVO> listaCliente = (ArrayList<ClientesVO>) cliente.getAll();
            ArrayList<VehiculoVO> listaVehiculo = (ArrayList<VehiculoVO>) vehiculo.mostrarTodo();

            // Mostramos por pantalla todo lo que contiene cada tabla
            listaPlaza.forEach(System.out::println);
            listaReserva.forEach(System.out::println);
            listaTicket.forEach(System.out::println);
            listaCliente.forEach(System.out::println);
            listaVehiculo.forEach(System.out::println);

            // Pasamos la fecha y hora a toString para que podamos ponerla como nombre en la carpeta
            String anio = Integer.toString(fechaAct.getYear());
            String mes = Integer.toString(fechaAct.getMonthValue());
            String dia = Integer.toString(fechaAct.getDayOfMonth());
            String hora = Integer.toString(horaAct.getHour());
            String min = Integer.toString(horaAct.getMinute());
            String seg = Integer.toString(horaAct.getSecond());

            // Metemos en un string el nombre ya completo de la carpeta
            String cadena = "./backup/" + dia + "-" + mes + "-" + anio + "_" + hora + "-" + min + "-" + seg;
            System.out.println("Se ha creado el directorio " + cadena);

            // Creamos la carpeta
            Path subCarpeta = Paths.get(cadena);
            Files.createDirectories(subCarpeta);

            String idfichero = "";

            // Recorremos cada lista asociada a cada tabla y escribos cada valor en el fichero y luego
            // con ayuda de flush conseguimos que se guarde y así hasta acabar
            idfichero = subCarpeta + "/plazas.txt";
            BufferedWriter flujo = new BufferedWriter(new FileWriter(idfichero));
            for (int i = 0; i < listaPlaza.size(); i++) {
                flujo.write(listaPlaza.get(i).toString());
                flujo.newLine();
            }
            flujo.flush();

            idfichero = subCarpeta + "/reservas.txt";
            flujo = new BufferedWriter(new FileWriter(idfichero));
            for (int i = 0; i < listaReserva.size(); i++) {
                flujo.write(listaReserva.get(i).toString());
                flujo.newLine();
            }
            flujo.flush();

            idfichero = subCarpeta + "/tickets.txt";
            flujo = new BufferedWriter(new FileWriter(idfichero));
            for (int i = 0; i < listaTicket.size(); i++) {
                flujo.write(listaTicket.get(i).toString());
                flujo.newLine();
            }
            flujo.flush();

            idfichero = subCarpeta + "/clientes.txt";
            flujo = new BufferedWriter(new FileWriter(idfichero));
            for (int i = 0; i < listaCliente.size(); i++) {
                flujo.write(listaCliente.get(i).toString());
                flujo.newLine();
            }
            flujo.flush();

            idfichero = subCarpeta + "/vehiculos.txt";
            flujo = new BufferedWriter(new FileWriter(idfichero));
            for (int i = 0; i < listaVehiculo.size(); i++) {
                flujo.write(listaVehiculo.get(i).toString());
                flujo.newLine();
            }
            flujo.flush();

            System.out.println("Se ha realizado la copia de seguridad de todas las tablas de la base de datos");
        } catch (SQLException sqlex) {
            System.out.println("No se ha podido hacer la copia de seguridad");
            System.out.println(sqlex.getMessage());
        } catch (IOException e) {
            System.out.println("Problema creando el directorio");
            System.out.println(e.toString());

        }

    }

    public static void main(String[] args) {
        CopiaSeguridad.crearCopia();
    }
}
