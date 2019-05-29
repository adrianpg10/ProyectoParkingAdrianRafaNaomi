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
import java.time.Month;
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
        ClientesVO.asignacionPlzAbonado();
    }

    public static void altaAbono() throws SQLException {
        Scanner teclado = new Scanner(System.in);
        String matri, tipo, dni, nombre, ape1, ape2, tarj, email;
        int tipoAb, abo;
        final LocalDate fecIniAb = LocalDate.now();
        LocalDate fecFinAb;
        ClientesDAO aux = new ClientesDAO();
        ReservasDAO aux2 = new ReservasDAO();
        do {
            System.out.println("Introduca su matricula");
            matri = teclado.nextLine();
        } while (!(matri.length() == 7));
        do {
            System.out.println("Introduzca el tipo de vehiculo que tiene usted");
            tipo = teclado.nextLine();
        } while (!(tipo.equalsIgnoreCase("turismo") || tipo.equalsIgnoreCase("caravana") || tipo.equalsIgnoreCase("motocicleta")));
        do {
            System.out.println("Introduzca su DNI");
            dni = teclado.nextLine();
        } while (!(dni.length() == 9));
        System.out.println("Introduzca su nombre");
        nombre = teclado.nextLine();
        System.out.println("Introduzca su primer apellido");
        ape1 = teclado.nextLine();
        System.out.println("Introduzca su segundo apellido");
        ape2 = teclado.nextLine();
        do {
            System.out.println("Introduzca su tarjeta de crédito");
            tarj = teclado.nextLine();
        } while (!(tarj.length() == 16));
        System.out.println("Introduzca su e-mail");
        email = teclado.nextLine();
        System.out.println("Indique el tipo de abono");
        System.out.println("1-Mensual(25€) || 2-Trimestral(70€) || 3-Semestral(130€) || 4-Anual(200€)");
        tipoAb = teclado.nextInt();
        switch (tipoAb) {
            case 1:
                abo = 1;
                fecFinAb = fecIniAb.plusMonths(1);
                System.out.println("He elegido mensual que serían 25€");
                break;
            case 2:
                abo = 2;
                fecFinAb = fecIniAb.plusMonths(3);
                System.out.println("He elegido trimestral que serían 70€");
                break;
            case 3:
                abo = 3;
                fecFinAb = fecIniAb.plusMonths(6);
                System.out.println("He elegido semestral que serían 130€");
                break;
            case 4:
                abo = 4;
                fecFinAb = fecIniAb.plusYears(1);
                System.out.println("He elegido anual que serían 200€");
                break;
            default:
                throw new AssertionError();
        }

        System.out.println("Se va a crear el cliente con los datos que usted ha introducido..");
        ClientesVO cli=new ClientesVO(matri, dni, nombre, ape1, ape2, tarj, tipoAb, email);
        System.out.println("------------------------------------------------------------------");
        System.out.println("Se va a insertar en la base de datos..");
        aux.insertarClientes(cli);
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

        Ordenes.altaAbono();
//        Ordenes.depositarVehiculo();
//        System.out.println("------------");
//        Ordenes.retirarVehiculo();
    }
}
