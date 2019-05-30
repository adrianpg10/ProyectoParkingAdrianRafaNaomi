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
                Ordenes.altaAbono();
                break;
            case ABONO_MODIFICA:
                System.out.println("Entrando a la modificación de abono..");
                Ordenes.modificarAbono();
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

    // Metodo para dar del alta un abonado
    public static void altaAbono() throws SQLException {
        Scanner teclado = new Scanner(System.in);
        String matri, tipo, dni, nombre, ape1, ape2, tarj, email;
        int tipoAb, abo;
        final LocalDate fecIniAb = LocalDate.now();
        LocalDate fecFinAb;
        ClientesDAO aux = new ClientesDAO();
        ReservasDAO aux2 = new ReservasDAO();
        // Pedimos los datos necesarios al cliente controlando el formato
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
                // Sumamos a la fecha de inicio el mes y lo guardamos en la fecha de fin
                fecFinAb = fecIniAb.plusMonths(1);
                System.out.println("He elegido mensual que serían 25€");
                break;
            case 2:
                abo = 2;
                // Sumamos a la fecha de inicio los 3 meses y lo guardamos en la fecha de fin
                fecFinAb = fecIniAb.plusMonths(3);
                System.out.println("He elegido trimestral que serían 70€");
                break;
            case 3:
                abo = 3;
                // Sumamos a la fecha de inicio los 6 meses y lo guardamos en la fecha de fin
                fecFinAb = fecIniAb.plusMonths(6);
                System.out.println("He elegido semestral que serían 130€");
                break;
            case 4:
                abo = 4;
                // Sumamos a la fecha de inicio el año y lo guardamos en la fecha de fin
                fecFinAb = fecIniAb.plusYears(1);
                System.out.println("He elegido anual que serían 200€");
                break;
            default:
                throw new AssertionError();
        }

        System.out.println("Se va a crear el cliente con los datos que usted ha introducido..");
        // Creamos el cliente con los datos que ha introducido el usuario y posteriormente lo insertamos en la tabla de
        // cliente de la base de datos
        ClientesVO cli = new ClientesVO(matri, dni, nombre, ape1, ape2, tarj, tipoAb, email);
        System.out.println("=================================================================");
        System.out.println("Se va a insertar en la base de datos..");
        aux.insertarClientes(cli);
        // Una vez insertado el cliente, insertaremos la reserva llamando al metodo que hemos creado abajo
        insertarAbonadoReservas(matri, fecFinAb, tipoAb, tipo);
    }

    // Metodo que nos devolvera true o false a la hora de inserta la reserva con los datos que le pasaremos
    public static boolean insertarAbonadoReservas(String matricula, LocalDate fecFinAbono, int tipoAbono, String tipoVehiculo) throws SQLException {
        TicketsDAO daoTicket = new TicketsDAO();
        VehiculoDAO daoVehiculo = new VehiculoDAO();
        PlazasDAO daoPlazas = new PlazasDAO();

        // Declaramos e instanciamos un array con las plazas del parking, como en los metodos anteriores
        String[] plazasEstado = new String[45];
        ArrayList<PlazasVO> listaPlaza = new ArrayList<>();

        try {
            // Guardamos todo lo que tengamos de las plazas y posteriormente guardamos el estado de las plazas
            listaPlaza = (ArrayList<PlazasVO>) daoPlazas.getAll();
            for (int i = 0; i < listaPlaza.size(); i++) {

                plazasEstado[i] = listaPlaza.get(i).getEstadoplaza();
            }
        } catch (SQLException sqle) {
            System.out.println("Error al intentar ejecutar la acción");
            System.out.println(sqle.getMessage());
        }

        // A continuacion comprobaremos el tipo de vehicuclo que le pasamos y dependiendo de si es motocicleta,caravana o
        // turismo, miraremos sus correspondientes numeros de plazas y las recorreremos una a una y si esta libre se ocupara
        // y actualizaremos la plaza
        if (tipoVehiculo.equalsIgnoreCase("motocicleta")) {
            for (int i = 0; i < 14; i++) {
                if (plazasEstado[i].equalsIgnoreCase("libre")) {
                    PlazasVO plazaModificada = listaPlaza.get(i);
                    plazaModificada.setEstadoplaza("ocupada");
                    daoPlazas.updatePlazas(listaPlaza.get(i).getNumplaza(), plazaModificada);
                    // Creamos la reserva de las motocicletas
                    ReservasVO reserva = new ReservasVO(matricula, listaPlaza.get(i).getNumplaza(), ReservasVO.generarPin(), LocalDate.now(), fecFinAbono);
                    ReservasDAO r = new ReservasDAO();
                    r.insertReservas(reserva);
                    return true;

                }
            }
        }
        if (tipoVehiculo.equalsIgnoreCase("caravana")) {
            for (int i = 15; i < 29; i++) {
                if (plazasEstado[i].equalsIgnoreCase("libre")) {
                    PlazasVO plazaModificada = listaPlaza.get(i);
                    plazaModificada.setEstadoplaza("ocupada");
                    daoPlazas.updatePlazas(listaPlaza.get(i).getNumplaza(), plazaModificada);
                    // Creamos la reserva de las caravanas
                    ReservasVO reserva = new ReservasVO(matricula, listaPlaza.get(i).getNumplaza(), ReservasVO.generarPin(), LocalDate.now(), fecFinAbono);
                    ReservasDAO r = new ReservasDAO();
                    r.insertReservas(reserva);
                    return true;

                }
            }

        }
        if (tipoVehiculo.equalsIgnoreCase("turismo")) {
            for (int i = 30; i < 44; i++) {
                if (plazasEstado[i].equalsIgnoreCase("libre")) {
                    PlazasVO plazaModificada = listaPlaza.get(i);
                    plazaModificada.setEstadoplaza("ocupada");
                    daoPlazas.updatePlazas(listaPlaza.get(i).getNumplaza(), plazaModificada);
                    // Creamos la reserva de los turismos
                    ReservasVO reserva = new ReservasVO(matricula, listaPlaza.get(i).getNumplaza(), ReservasVO.generarPin(), LocalDate.now(), fecFinAbono);
                    ReservasDAO r = new ReservasDAO();
                    r.insertReservas(reserva);
                    return true;
                }
            }

        }
        return false;
    }

    // Metodo para modificar un abono
    public static void modificarAbono() throws SQLException {
        Scanner teclado = new Scanner(System.in);
        String matricula, nif, nombre, ape1, ape2, tarjeta, email;
        ClientesDAO clienteDAO = new ClientesDAO();
        ArrayList<ClientesVO> listaClientes = new ArrayList<>();
        ClientesVO clienteVO = new ClientesVO();
        ReservasDAO reservaD = new ReservasDAO();
        ArrayList<ReservasVO> listaReservas = new ArrayList<>();
        ReservasVO reserva = new ReservasVO();
        try {

            listaClientes = (ArrayList<ClientesVO>) clienteDAO.getAll();
        } catch (SQLException sqle) {
            System.out.println("No se ha podido realizar la operación:");
            System.out.println(sqle.getMessage());
        }
        // Le pedimos la matricula para que comprueba si dicha matricula se encuentra en la lista de los clientes y si es asi,
        // buscamos a ese cliente por su clave primaria que es el dni y lo guardamos en un objeto de tipo cliente
        System.out.println("Se va a proceder a modificar el abono, introduzca la matricula para continuar..");
        matricula = teclado.nextLine();
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getMatricula().equalsIgnoreCase(matricula)) {
                clienteVO = clienteDAO.findByPk(matricula);
                ClientesVO clienteAux = clienteVO;
                // Le pedimos los datos personales a modificar
                System.out.println("Introduzca su nuevo DNI");
                nif = teclado.nextLine();
                System.out.println("Introduzca su nuevo nombre");
                nombre = teclado.nextLine();
                System.out.println("Introduzca su nuevo apellido 1");
                ape1 = teclado.nextLine();
                System.out.println("Introduzca su nuevo apellido 2");
                ape2 = teclado.nextLine();
                System.out.println("Introduzca su nueva tarjeta");
                tarjeta = teclado.nextLine();
                System.out.println("Introduzca su nuevo email");
                email = teclado.nextLine();

                // Se crea el cliente con los datos que ha introducido
                clienteVO = new ClientesVO(matricula, nif, nombre, ape1, ape2, tarjeta, clienteAux.getTipoAbono(), email);
                // Se actualiza el nuevo cliente con la mtricula y el cliente que creamos arriba
                clienteDAO.updateClientes(matricula, clienteVO);

                System.out.println("Se han modificado sus datos personales");
            } else {
                System.out.println("No se ha podido modificar sus datos personales");
            }
        }
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

        //Ordenes.altaAbono();      
        Ordenes.modificarAbono();
//        Ordenes.depositarVehiculo();
//        System.out.println("------------");
//        Ordenes.retirarVehiculo();
    }
}
