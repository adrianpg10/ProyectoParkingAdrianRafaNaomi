/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.clientes;

import daw.plazas.PlazasDAO;
import daw.plazas.PlazasVO;
import daw.reservas.ReservasDAO;
import daw.reservas.ReservasVO;
import daw.vehiculos.VehiculoDAO;
import daw.vehiculos.VehiculoVO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Rafa
 */
public class ClientesVO {

    // Atributos
    private String matricula;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String numTarjetaCredito;
    private int tipoAbono;
    private String email;

    // Constructor parametrizado
    public ClientesVO(String matricula, String dni, String nombre, String apellido1, String apellido2, String numTarjetaCredito, int tipoAbono, String email) {
        this.matricula = matricula;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.numTarjetaCredito = numTarjetaCredito;
        this.tipoAbono = tipoAbono;
        this.email = email;
    }

    // Constructor por defecto
    public ClientesVO() {

    }

    // Metodo para generar el precio del abono en funcion del tipo que le pasemos, si le pasamos uno diferente de 1 a 4,
    // por defecto le devolveremos el más barato que es el mensual
    public static int generarPrecioAb(int tipoAbono) {
        switch (tipoAbono) {
            case 1:
                return 25;
            case 2:
                return 70;
            case 3:
                return 130;
            case 4:
                return 200;
        }

        return 25;
    }

    // Metodo para asignacion de la plazaAbonado
    public static void asignacionPlzAbonado() throws SQLException, IOException {
        // Pide matricula y tipo de vehiculo
        ClientesDAO aux = new ClientesDAO();
        ClientesVO cliente = new ClientesVO();
        ArrayList<ClientesVO> listaC = new ArrayList<>();
        listaC = (ArrayList<ClientesVO>) aux.getAll();
        String[] plazasEstado = new String[45];
        ArrayList<PlazasVO> listaPlaza = new ArrayList<>();
        PlazasDAO plazas = new PlazasDAO();
        listaPlaza = (ArrayList<PlazasVO>) plazas.getAll();
        VehiculoDAO vehiculos = new VehiculoDAO();
        ArrayList<VehiculoVO> listaV = new ArrayList<>();
        listaV = (ArrayList<VehiculoVO>) vehiculos.mostrarTodo();
        ArrayList<ReservasVO> listaR = new ArrayList<>();
        ReservasDAO r = new ReservasDAO();
        listaR = (ArrayList<ReservasVO>) r.getAll();
        String pin = "";
        double tarifa=0.0;

        Scanner teclado = new Scanner(System.in);
        String matri;
        String dni;
        do {
            System.out.println("Introduzca la matrícula");
            matri = teclado.nextLine();
            // Controlamos la longitud de la matricula
        } while (!(matri.length() == 7));
        do {
            System.out.println("Introduzca su DNI");
            dni = teclado.nextLine();
        } while (!(dni.length() == 9));

        // Comprobamos la matricula y el dni que introduce el usuario que está en la lista de los clientes y va sumando
        int contador = 0;
        for (int i = 0; i < listaC.size(); i++) {
            if (listaC.get(i).getMatricula().equalsIgnoreCase(matri) && listaC.get(i).getDni().equalsIgnoreCase(dni)) {
                contador++;
            }
        }

        // Si el contador es distinto de 0 hacemos de nuevo otro bucle para recorrer la lista de reservas y le indicamos que es abonado
        if (contador != 0) {
            for (int i = 0; i < listaC.size(); i++) {
                if (listaC.get(i).getMatricula().equalsIgnoreCase(matri) && listaC.get(i).getDni().equalsIgnoreCase(dni)) {

                    for (int j = 0; j < listaR.size(); j++) {
                        System.out.println("Eres abonado");
                        if (listaR.get(j).getMatricula().equalsIgnoreCase(matri)) {
                            // Guardamos el pin del usuario que se encuentra en la lista de reservas en una variable 
                            //para luego poder usarla
                            pin = listaR.get(j).getPin_fijo();

                            // Establecemos el nombre a la carpeta que vamos a crear de pines
                            String cadena = "./pines/";
                            System.out.println("Se ha creado el directorio " + cadena);

                            // Creamos la carpeta
                            Path subCarpeta = Paths.get(cadena);
                            Files.createDirectories(subCarpeta);

                            String idfichero = "";

                            // Establecemos el nombre del fichero que sera el dni que se encuentra en la lista
                            idfichero = subCarpeta + "/" + listaC.get(i).getDni() + ".txt";
                            BufferedWriter flujo = new BufferedWriter(new FileWriter(idfichero));

                            // Guardamos dentro de cada fichero el pin de dicho usuario
                            flujo.write(pin);
                            flujo.newLine();

                            flujo.flush();
                        }
                        
                        // Por último actualizamos las plazas donde ya pasan a estar de reservada libre (1) a reservada ocupada (2)
                        for (int k = 0; k < listaPlaza.size(); k++) {
                            if (listaPlaza.get(k).getNumplaza()==listaR.get(j).getNumplaza()) {
                                tarifa=listaPlaza.get(i).getTarifa();
                                plazas.updatePlazas(listaR.get(j).getNumplaza(), new PlazasVO(listaR.get(j).getNumplaza(), listaPlaza.get(k).getTipoPlaza(),listaPlaza.get(k).getEstadoplaza(),tarifa, 2));
                            }
                        }
                        
                    }
                    
                    

                }

            }
        } else {
            System.out.println("No eres abonado");
        }
    }

    // Getters y setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNumTarjetaCredito() {
        return numTarjetaCredito;
    }

    public void setNumTarjetaCredito(String numTarjetaCredito) {
        this.numTarjetaCredito = numTarjetaCredito;
    }

    public int getTipoAbono() {
        return tipoAbono;
    }

    public void setTipoAbono(int tipoAbono) {
        this.tipoAbono = tipoAbono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // toString
    @Override
    public String toString() {
        return matricula + " : " + dni + " : " + nombre + " : " + apellido1 + " : " + apellido2 + " : " + numTarjetaCredito + " : " + tipoAbono + " : " + email;
    }

}
