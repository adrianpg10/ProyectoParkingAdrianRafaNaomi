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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import static java.time.temporal.WeekFields.ISO;
import java.util.ArrayList;
import java.util.Scanner;

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

    // Metodo para restaurar las copias de seguridad de las tablas de la base de datos
    public static void resturarCopia() throws SQLException, FileNotFoundException {
        Scanner teclado = new Scanner(System.in);
        String aux="";
        PlazasDAO pado = new PlazasDAO();
        ReservasDAO rdao = new ReservasDAO();
        TicketsDAO tdao = new TicketsDAO();
        VehiculoDAO vdao = new VehiculoDAO();
        ClientesDAO cdao = new ClientesDAO();

        // Recorremos el directorio donde se encuentra las copias y mostramos lo que contiene para que posteiormente
        // el usuario pueda elegir que copia restaurar
        File carpeta = new File("./backup");
        String[] listado = carpeta.list();
        if (carpeta.exists()) {
            File[] ficheros = carpeta.listFiles();
            for (File file2 : ficheros) {
                System.out.println(file2.getName());
            }

            System.out.println("Estas son las copias de seguridad que dispones..");
            System.out.println("¿Que copias deseas restaurar?");
            aux = teclado.nextLine();

            for (String lista : listado) {
                if (aux.equalsIgnoreCase(lista)) {
                    System.out.println("Perfecto");
                }

            }

        } else {
            System.out.println("El directorio a listar no existe");
        }

        // Se eliminan las tablas antes de restaurar para que no hayan problemas
        tdao.deleteTickets();
        rdao.deleteReservas();
        pado.deletePlazas();
        cdao.deleteClientes();
        vdao.borrarTodosVehiculo();

        // Por cada tabla leemos poniendo la ubicacion que ha elegido el usuario y su respectiva tabla y vamos separando por
        // tokens y cuando lo tengamos vamos añadiendo a la lista de dicha tabla el nuevo objeto con dichos tokens, y así
        // con las 5 tablas
        ArrayList<VehiculoVO> listaV = new ArrayList<>();

        try (Scanner datosFichero = new Scanner(new FileInputStream("./backup/"+aux+"/vehiculos.txt"), "ISO-8859-1")) {
            String[] tokens;
            String linea;
            while (datosFichero.hasNextLine()) {

                linea = datosFichero.nextLine();

                tokens = linea.split(" : ");

                listaV.add(new VehiculoVO(tokens[0], tokens[1]));
            }
            for (VehiculoVO vehiculoVO : listaV) {
                System.out.println(vehiculoVO);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        vdao.insertarVehiculo(listaV);

        ArrayList<ClientesVO> listaC = new ArrayList<>();

        try (Scanner datosFichero = new Scanner(new FileInputStream("./backup/"+aux+"/clientes.txt"), "ISO-8859-1")) {
            String[] tokens;
            String linea;
            while (datosFichero.hasNextLine()) {

                linea = datosFichero.nextLine();

                tokens = linea.split(" : ");

                listaC.add(new ClientesVO(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], Integer.valueOf(tokens[6]), tokens[7]));
            }
            for (ClientesVO clienteVO : listaC) {
                System.out.println(clienteVO);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        cdao.insertarClientes(listaC);

        ArrayList<PlazasVO> listaP = new ArrayList<>();

        try (Scanner datosFichero = new Scanner(new FileInputStream("./backup/"+aux+"/plazas.txt"), "ISO-8859-1")) {
            String[] tokens;
            String linea;
            while (datosFichero.hasNextLine()) {

                linea = datosFichero.nextLine();

                tokens = linea.split(" : ");

                listaP.add(new PlazasVO(Integer.valueOf(tokens[0]), tokens[1], tokens[2], Double.valueOf(tokens[3]),Boolean.valueOf(tokens[4])));
            }
            for (PlazasVO plazaVO : listaP) {
                System.out.println(plazaVO);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        pado.insertPlazas(listaP);

        ArrayList<TicketsVO> listaT = new ArrayList<>();

        try (Scanner datosFichero = new Scanner(new FileInputStream("./backup/"+aux+"/tickets.txt"), "ISO-8859-1")) {
            String[] tokens;
            String linea;
            while (datosFichero.hasNextLine()) {

                linea = datosFichero.nextLine();
                tokens = linea.split(" : ");
                String separador = tokens[4].trim();
                String[] dia_mes_anio = separador.split("-");
                LocalDate fechaI = LocalDate.of(Integer.valueOf(dia_mes_anio[0]), Integer.valueOf(dia_mes_anio[1]), Integer.valueOf(dia_mes_anio[2]));

                String f = tokens[5].trim();
                String[] dia_mes_anio_2 = f.split("-");
                LocalDate fechaF = LocalDate.of(Integer.valueOf(dia_mes_anio_2[0]), Integer.valueOf(dia_mes_anio_2[1]), Integer.valueOf(dia_mes_anio_2[2]));

                String e = tokens[6].trim();
                String[] hora_min_seg = e.split(":");
                LocalTime horaI = LocalTime.of(Integer.valueOf(hora_min_seg[0]), Integer.valueOf(hora_min_seg[1]), Integer.valueOf(hora_min_seg[2]));

                String s = tokens[7].trim();
                String[] hora_min_seg_2 = s.split(":");
                LocalTime horaF = LocalTime.of(Integer.valueOf(hora_min_seg_2[0]), Integer.valueOf(hora_min_seg_2[1]), Integer.valueOf(hora_min_seg_2[2]));

                listaT.add(new TicketsVO(Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1]), tokens[2], tokens[3], fechaI, fechaF, horaI, horaF,Double.valueOf(tokens[8])));
            }
            for (TicketsVO ticketVO : listaT) {
                System.out.println(ticketVO);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        tdao.insertTickets(listaT);

        ArrayList<ReservasVO> listaR = new ArrayList<>();

        try (Scanner datosFichero = new Scanner(new FileInputStream("./backup/"+aux+"/reservas.txt"), "ISO-8859-1")) {
            String[] tokens;
            String linea;
            while (datosFichero.hasNextLine()) {

                linea = datosFichero.nextLine();
                tokens = linea.split(" : ");
                String separador = tokens[3].trim();
                String[] dia_mes_anio = separador.split("-");
                LocalDate fechaIR = LocalDate.of(Integer.valueOf(dia_mes_anio[0]), Integer.valueOf(dia_mes_anio[1]), Integer.valueOf(dia_mes_anio[2]));

                String f = tokens[4].trim();
                String[] dia_mes_anio_2 = f.split("-");
                LocalDate fechaFR = LocalDate.of(Integer.valueOf(dia_mes_anio_2[0]), Integer.valueOf(dia_mes_anio_2[1]), Integer.valueOf(dia_mes_anio_2[2]));

                listaR.add(new ReservasVO(tokens[0], Integer.valueOf(tokens[1]), tokens[2], fechaIR, fechaFR,Integer.valueOf(tokens[5])));
            }
            for (ReservasVO reservaVO : listaR) {
                System.out.println(reservaVO);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        rdao.insertReservas(listaR);

    }

}
