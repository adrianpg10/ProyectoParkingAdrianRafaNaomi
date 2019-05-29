/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.parking.datos;

import daw.plazas.PlazasDAO;
import daw.plazas.PlazasVO;
import daw.reservas.ReservasDAO;
import daw.tickets.TicketsDAO;
import daw.tickets.TicketsVO;
import daw.vehiculos.VehiculoDAO;
import daw.vehiculos.VehiculoVO;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author adrian
 */
public class Ordenes {

    // Metodo realizar orden que recibe un comando y contiene cada uno de sus metodos correspondientes
    public static void realizarOrden(Comandos ordenes) throws SQLException {

        switch (ordenes) {
            case VOLVER_MENU:
                System.out.println("Has vuelto atrás..");
                Ordenes.realizarOrden(Menu.menu());

                break;
            case ENTRAR_CLIENTE:
                System.out.println("Entrando a la zona clientes..");

                break;
            case ENTRAR_ADMIN:
                System.out.println("Entrando a la zona de admin..");

                break;
            case DEPOSITAR_VEHICULO:
                System.out.println("Entrando a la zona de depositar vehiculo..");
                Ordenes.depositarVehiculo();

                break;
            case RETIRAR_VEHICULO:
                System.out.println("Entrando a la zona de retirar vehiculo..");
                Ordenes.retirarVehiculo();

                break;
            case DEPOSITAR_ABONADO:
                System.out.println("Entrando a la zona de depositar abonado..");
                Ordenes.depositarAbonado();
                break;
            case RETIRAR_ABONADO:
                System.out.println("Entrando a la zona de retirar abonado..");

                break;
            case ESTADO_PARKING:
                System.out.println("Entrando al estado del parking..");

                break;
            case FACTURACION_ENTRE_FECHAS:
                System.out.println("Entrando a la facturación entre fechas..");

                break;
            case FACTURACION_ABONADO:
                System.out.println("Entrando a la facturación abonado..");

                break;
            case ABONO_ALTA:
                System.out.println("Entrando al alta de abono..");

                break;
            case ABONO_MODIFICA:
                System.out.println("Entrando a la modificación de abono..");

                break;
            case ABONO_BAJA:
                System.out.println("Entrando a la baja de abono..");

                break;
            case ABONO_CADUCIDAD:
                System.out.println("Entrando a la caducidad de abono..");

                break;
            case COPIA_SEGURIDAD_COPIAR:
                System.out.println("Entrando a la zona de crear copia de seguridad..");
                CopiaSeguridad.crearCopia();
                break;
            case COPIA_SEGURIDAD_RESTAURAR:
                System.out.println("Entrando a la zona de restaurar copia de seguridad..");
                
                break;

            default:
                throw new AssertionError();
        }

    }

    // Metodo para depositar vehiculo
    public static void depositarVehiculo() throws SQLException {

        PlazasDAO aux = new PlazasDAO();
        System.out.println("A continuacion se le mostrará las plazas libres..");
        System.out.println();
        aux.getEstadosPlaza();
        PlazasDAO.asignacionPlz();
        aux.getEstadosPlaza();

    }

    // Metodo para retirar vehiculo
    public static void retirarVehiculo() throws SQLException {
        PlazasDAO aux2 = new PlazasDAO();
        System.out.println("A continuacion se le mostrará las plazas libres..");
        System.out.println();
        aux2.getEstadosPlaza();
        PlazasDAO.retirarPlz();
        aux2.getEstadosPlaza();
    }

    public static void depositarAbonado() throws SQLException {
        ReservasDAO.depositarAbo();
    }

    
    //    public static int calculoMinTarifa(LocalDate fechaInicio, LocalDate fechaFin, LocalTime horaInicio, LocalTime horaFin) throws ParseException {
    //
    //        int minutosTotales;
    //
    //        String inicio;
    //        String fin;
    //        // Guardamos en cada de una de las variables el formato toString de las fechas y horas que le hemos pasados
    //        inicio = fechaInicio.getYear() + "-" + fechaInicio.getMonthValue() + "-" + fechaInicio.getDayOfMonth() + " " + horaInicio.getHour() + ":" + horaInicio.getMinute() + ":" + horaInicio.getSecond();
    //        fin = fechaFin.getYear() + "-" + fechaFin.getMonthValue() + "-" + fechaFin.getDayOfMonth() + " " + horaFin.getHour() + ":" + horaFin.getMinute() + ":" + horaFin.getSecond();
    //
    //        // Ponemos el formato que tendrá la fehca/hora
    //        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
    //        Date fechaInicial = dateFormat.parse(inicio);
    //        Date fechaFinal = dateFormat.parse(fin);
    //
    //        
    //    }
    public static void main(String[] args) throws SQLException {

        Ordenes.depositarVehiculo();
        System.out.println("------------");
        Ordenes.retirarVehiculo();
    }
}
