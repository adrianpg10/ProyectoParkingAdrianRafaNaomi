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
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author adrian
 */
public class Ordenes {

    // Metodo realizar orden que recibe un comando y contiene cada uno de sus metodos correspondientes
    public static void realizarOrden(Comandos ordenes) throws SQLException, FileNotFoundException {

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
                Ordenes.realizarOrden(Menu.menu());

                break;
            case RETIRAR_VEHICULO:
                System.out.println("Entrando a la zona de retirar vehiculo..");
                Ordenes.retirarVehiculo();
                Ordenes.realizarOrden(Menu.menu());

                break;
            case DEPOSITAR_ABONADO:
                System.out.println("Entrando a la zona de depositar abonado..");
                Ordenes.depositarAbonado();
                Ordenes.realizarOrden(Menu.menu());
                break;
            case RETIRAR_ABONADO:
                System.out.println("Entrando a la zona de retirar abonado..");

                break;
            case ESTADO_PARKING:
                System.out.println("Entrando al estado del parking..");
                Ordenes.estadoPlazas();
                Ordenes.realizarOrden(Menu.menu());

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
                Ordenes.realizarOrden(Menu.menu());
                break;
            case ABONO_MODIFICA:
                System.out.println("Entrando a la modificación de abono..");
                Ordenes.modificarAbono();
                Ordenes.realizarOrden(Menu.menu());
                break;
            case ABONO_BAJA:
                System.out.println("Entrando a la baja de abono..");
                Ordenes.eliminarAbono();
                break;
            case ABONO_CADUCIDAD:
                System.out.println("Entrando a la caducidad de abono..");
                Ordenes.caducidadAbono();
                Ordenes.realizarOrden(Menu.menu());
                break;
            case COPIA_SEGURIDAD_COPIAR:
                System.out.println("Entrando a la zona de crear copia de seguridad..");
                CopiaSeguridad.crearCopia();
                Ordenes.realizarOrden(Menu.menu());
                break;
            case COPIA_SEGURIDAD_RESTAURAR:
                System.out.println("Entrando a la zona de restaurar copia de seguridad..");
                CopiaSeguridad.resturarCopia();
                Ordenes.realizarOrden(Menu.menu());
                break;
            case SALIR:
                System.out.println("El programa ha finalizado");
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
                    ReservasVO reserva = new ReservasVO(matricula, listaPlaza.get(i).getNumplaza(), ReservasVO.generarPin(), LocalDate.now(), fecFinAbono, ClientesVO.generarPrecioAb(tipoAbono));
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
                    ReservasVO reserva = new ReservasVO(matricula, listaPlaza.get(i).getNumplaza(), ReservasVO.generarPin(), LocalDate.now(), fecFinAbono, ClientesVO.generarPrecioAb(tipoAbono));
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
                    ReservasVO reserva = new ReservasVO(matricula, listaPlaza.get(i).getNumplaza(), ReservasVO.generarPin(), LocalDate.now(), fecFinAbono, ClientesVO.generarPrecioAb(tipoAbono));
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

        listaClientes = (ArrayList<ClientesVO>) clienteDAO.getAll();

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


    // Metodo para conocer el estado del parking de cada una de las plazas en cada momento, el cual en una lista guardamos 
    // todo lo que tiene en ese momento y luego la recorremos y vamos imprimiendo su numero de plaza y sus posibles 4 estados.
    // L es libre sin abonar, O es ocupada sin abonar, R es reservada para los abonados y NR es no reserevada para los abonados
    public static void estadoPlazas() throws SQLException {
        PlazasDAO aux = new PlazasDAO();
        List<PlazasVO> listaPlazas = new ArrayList<>();
        String libre_ocupada = "";
        int abonada_no = 0;
        listaPlazas = aux.getAll();
        for (PlazasVO tmp : listaPlazas) {
            System.out.print(tmp.getNumplaza() + " || ");
            libre_ocupada = tmp.getEstadoplaza();
            abonada_no = tmp.getEstadoReservado();

            switch (libre_ocupada) {
                case "libre":
                    System.out.print("L ");
                    break;

                case "ocupada":
                    System.out.print("O ");
                    break;

                default:
                    System.out.print("Error");
            }

            switch (abonada_no) {
                case 0:
                    System.out.print("|| NR \r");
                    break;

                case 1:
                    System.out.print("|| R \r");
                    break;
                default:
                    System.out.println("Error");
            }
        }
    }

    // Metodo para ver los abonos que caducan en un mes en concreto donde creamos dos listas, una donde guardaremos todo lo que tiene la
    // tabla de abonados y otra donde al recorrer dicha lista y que cumpla la condicion del mes pues podamos meterla en la otra lista
    // y asi devolver una lista con los abonados que caducasen en dicho mes
    public static ArrayList<ReservasVO> caducidadMes(int mes) throws SQLException {
        ReservasDAO aux = new ReservasDAO();
        List<ReservasVO> listaAb = new ArrayList<>();
        ArrayList<ReservasVO> caducidad = new ArrayList<>();
        listaAb = aux.getAll();
        for (ReservasVO abonados : listaAb) {
            if (mes == abonados.getFecfinabono().getMonth().getValue()) {
                caducidad.add(abonados);
            }
        }
        return caducidad;
    }

    // Metodo para ver los abonos que caducan en los ultimos 10 días, el procedimiento es el mismo que el anterior, solo que ahora la condicion
    // es mas compleja, si se cumple la añadimos y la devuelve
    public static ArrayList<ReservasVO> caducidad10() throws SQLException {
        ReservasDAO aux = new ReservasDAO();
        List<ReservasVO> listaAb = new ArrayList<>();
        ArrayList<ReservasVO> caducidad = new ArrayList<>();
        listaAb = aux.getAll();
        for (ReservasVO tmp : listaAb) {
            if (tmp.getFecfinabono().getDayOfYear() > LocalDate.now().getDayOfYear() && tmp.getFecfinabono().getDayOfYear() > LocalDate.now().plusDays(10).getDayOfYear()) {
                caducidad.add(tmp);
            }
        }
        return caducidad;
    }

    // Metodo principal de caducidad de abonados que contiene un menu donde el usuario puede elegir entre 1 mes en concreto o los
    // últimos 10 días, se controla los fallos y si se cumple uno de los dos casos se hace la llamada a los metodos creados
    // anteriormente y devuelve el resultado
    public static void caducidadAbono() throws SQLException {
        Scanner teclado = new Scanner(System.in);
        int mes;
        System.out.println("Elige la opcion que desee:");
        System.out.println("1- 1 mes en concreto");
        System.out.println("2- Últimos 10 días");
        int opt = teclado.nextInt();
        switch (opt) {
            case 1:
                do {
                    System.out.println("Escriba un numero de mes (1 al 12)");
                    mes = teclado.nextInt();
                } while (mes < 0 || mes > 12);
                ArrayList<ReservasVO> listaMes = Ordenes.caducidadMes(mes);
                listaMes.forEach(System.out::println);
                break;

            case 2:
                System.out.println("Los abonos que van a caducar en los próximos 10 días son los siguientes: ");
                ArrayList<ReservasVO> listaMes10 = Ordenes.caducidad10();
                listaMes10.forEach(System.out::println);
                break;
            default:
                System.out.println("Error, no hay mas opciones disponibles");
        }
    }

    // Metodo para eliminar a un abonado donde le pedimos la matricula y luego hacemos una lista donde metemos todo lo que
    // contiene la tabla clientes y  la recorremos y si la matricula que hay en la lista coincide con la que le escribimos,
    // se eactualiza la tabla y eliminamos el cliente gracias al metodo que le hacemos mas abajo
    public static void eliminarAbono() throws SQLException {
        Scanner teclado = new Scanner(System.in);
        String matri;
        do {
            System.out.println("Introduce la matricula del cliente abonado que quieres eliminar");
            matri = teclado.nextLine();
        } while (!(matri.length() == 7));

        ClientesDAO daoCli = new ClientesDAO();
        ArrayList<ClientesVO> listaCli = new ArrayList<>();
        listaCli = (ArrayList<ClientesVO>) daoCli.getAll();
        for (ClientesVO listaCliente : listaCli) {
            if (listaCliente.getMatricula().equalsIgnoreCase(matri)) {
                daoCli.updateClientes(listaCliente.getMatricula(), eliminarCliente(listaCliente));
            }
        }

    }

    // Creamos un metodo para usar en el metodo de eliminar abono para que al eliminar el cliente abonado, los campos
    // salgan vacios
    public static ClientesVO eliminarCliente(ClientesVO cliente) {
        cliente.setDni("");
        cliente.setNombre("");
        cliente.setApellido1("");
        cliente.setApellido2("");
        cliente.setNumTarjetaCredito("");
        cliente.setTipoAbono(1);
        cliente.setEmail("");

        return cliente;

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
    
    public static long tiempoTranscurrido(LocalTime tiempoInicio, LocalDate fechaInicio){
        LocalDateTime horaEntrada=LocalDateTime.of(fechaInicio, tiempoInicio);
        return ChronoUnit.MINUTES.between(horaEntrada, LocalDateTime.now());
    }
    
    public static void main(String[] args) {
        LocalTime a=LocalTime.of(15, 51, 0);
        LocalDate b=LocalDate.of(2019, 6, 2);
        System.out.println(Ordenes.tiempoTranscurrido(a, b)+ " minutos");
    }
}
