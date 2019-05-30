package daw.plazas;

import daw.clientes.ClientesVO;

import java.sql.Connection;
import daw.parking.datos.Conexion;
import daw.tickets.TicketsDAO;
import daw.tickets.TicketsVO;
import daw.vehiculos.VehiculoDAO;
import daw.vehiculos.VehiculoVO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author adrian
 */
public class PlazasDAO implements IPlazas {

    private Connection con = null;

    public PlazasDAO() {

        con = Conexion.getInstance();
    }

    // Metodo para averiguar el numero de plazas libres de cada vehiculo
    public void getEstadosPlaza() throws SQLException {

        String plazasTurismo = "select count(*) from plazas where estadoplaza='libre' and tipoPlaza='Turismo'";
        String plazasCaravana = "select count(*) from plazas where estadoplaza='libre' and tipoPlaza='Caravana'";
        String plazasMotocicleta = "select count(*) from plazas where estadoplaza='libre' and tipoPlaza='Motocicleta'";

        try (PreparedStatement prest = con.prepareStatement(plazasTurismo)) {

            ResultSet res = null;
            res = prest.executeQuery();
            if (res.next()) {
                int aux = res.getInt(1);
                System.out.println("Plazas de turismo libres -->" + aux);
            }
        }
        try (PreparedStatement prest = con.prepareStatement(plazasCaravana)) {

            ResultSet res = null;
            res = prest.executeQuery();

            if (res.next()) {
                int aux = res.getInt(1);
                System.out.println("Plazas de caravana libres -->" + aux);
            }
        }
        try (PreparedStatement prest = con.prepareStatement(plazasMotocicleta)) {

            ResultSet res = null;
            res = prest.executeQuery();

            if (res.next()) {
                int aux = res.getInt(1);
                System.out.println("Plazas de motocicleta libres -->" + aux);
            }
        }

    }

    @Override
    public List<PlazasVO> getAll() throws SQLException {
        List<PlazasVO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from plazas");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                PlazasVO p = new PlazasVO();
                // Recogemos los datos de la persona, guardamos en un objeto
                p.setNumplaza(res.getInt("numplaza"));
                p.setTipoPlaza(res.getString("tipoPlaza"));
                p.setEstadoplaza(res.getString("estadoplaza"));
                p.setTarifa(res.getDouble("tarifa"));
                p.setEstadoReservado(res.getBoolean("estadoReservado"));
                

                //Añadimos el objeto a la lista
                lista.add(p);
            }
        }
        return lista;
    }

    @Override
    public PlazasVO findByPk(int numplaza) throws SQLException {

        ResultSet res = null;
        PlazasVO plazas = new PlazasVO();

        String sql = "select * from plazas where numplaza=?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setInt(1, numplaza);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.first()) {
                // Recogemos los datos de la persona, guardamos en un objeto
                plazas.setNumplaza(res.getInt("numplaza"));
                plazas.setTipoPlaza(res.getString("tipoPlaza"));
                plazas.setEstadoplaza(res.getString("estadoplaza"));
                plazas.setTarifa(res.getDouble("tarifa"));
                plazas.setEstadoReservado(res.getBoolean("estadoReservado"));
                return plazas;
            }

            return null;
        }
    }

    @Override
    public int insertPlazas(PlazasVO plazas) throws SQLException {
        int numFilas = 0;
        String sql = "insert into plazas values (?,?,?,?,?)";

        if (findByPk(plazas.getNumplaza()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setInt(1, plazas.getNumplaza());
                prest.setString(2, plazas.getTipoPlaza());
                prest.setString(3, plazas.getEstadoplaza());
                prest.setDouble(4, plazas.getTarifa());
                prest.setBoolean(5, plazas.isEstadoReservado());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    @Override
    public int insertPlazas(List<PlazasVO> lista) throws SQLException {
        int numFilas = 0;

        for (PlazasVO tmp : lista) {
            numFilas += insertPlazas(tmp);
        }

        return numFilas;
    }

    @Override
    public int deletePlazas(PlazasVO p) throws SQLException {
        int numFilas = 0;

        String sql = "delete from plazas where numplaza = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setInt(1, p.getNumplaza());
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int deletePlazas() throws SQLException {
        String sql = "delete from plazas";

        int nfilas = 0;

        // Preparamos el borrado de datos  mediante un Statement
        // No hay parámetros en la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecución de la sentencia
            nfilas = st.executeUpdate(sql);
        }

        return nfilas;
    }

    @Override
    public int updatePlazas(int numplaza, PlazasVO nuevosDatos) throws SQLException {
        int numFilas = 0;
        String sql = "update plazas set tipoPlaza = ?, estadoplaza = ?, tarifa = ?, estadoReservado= ?  where numplaza=?";

        if (findByPk(numplaza) == null) {
            // La plaza a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(1, nuevosDatos.getTipoPlaza());
                prest.setString(2, nuevosDatos.getEstadoplaza());
                prest.setDouble(3, nuevosDatos.getTarifa());
                prest.setBoolean(4, nuevosDatos.isEstadoReservado());
                prest.setInt(5, numplaza);

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    // Metodo para la asignacion de una plaza
    public static void asignacionPlz() throws SQLException {
        // Pide matricula y tipo de vehiculo
        String matri, tipo;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Introduzca matrícula (1111XXX)");
            matri = teclado.nextLine();
            // Controlamos la longitud de la matricula
        } while (!(matri.length() == 7));
        do {
            System.out.println("Introduzca tipo de vehículo (turismo-caravana-motocicleta)");
            tipo = teclado.nextLine();
        } while (!(tipo.equalsIgnoreCase("turismo") || tipo.equalsIgnoreCase("caravana") || tipo.equalsIgnoreCase("motocicleta")));

        String[] plazasEstado = new String[45];
        ArrayList<PlazasVO> listaPlaza = new ArrayList<>();
        PlazasDAO plazas = new PlazasDAO();
        VehiculoDAO vehiculos = new VehiculoDAO();
        VehiculoVO vehiculo = new VehiculoVO(matri, tipo);

        listaPlaza = (ArrayList<PlazasVO>) plazas.getAll();
        for (int i = 0; i < listaPlaza.size(); i++) {
            plazasEstado[i] = listaPlaza.get(i).getEstadoplaza();
        }

        // Controlamos de si el tipo introducido es un turismo, si esta ocupada que pase a la siguiente y sino, si está libre,
        // la asignamos la plaza, ponemos el vehiculo y se actualiza el estado de libre a ocupada
        if (tipo.equalsIgnoreCase("turismo")) {
            int numeroTurismo = 1;
            for (int i = 31; i <= 45; i++) {

                if (plazasEstado[i].equalsIgnoreCase("ocupada")) {
                    i = i + numeroTurismo;
                    numeroTurismo++;
                } else if (plazasEstado[i].equalsIgnoreCase("libre")) {

                    vehiculos.insertarVehiculo(vehiculo);

                    PlazasVO plazaModificada = listaPlaza.get(i);
                    plazaModificada.setEstadoplaza("ocupada");

                    plazas.updatePlazas(listaPlaza.get(i).getNumplaza(), plazaModificada);
                    System.out.println("La plaza de turismo se ha actualizado");

                    // Se crea un ticket con toda la informacion relevante donde creamos la plaza primero y luego el ticket
                    PlazasVO plazasVO = new PlazasVO(tipo, i);
                    PlazasDAO plazasDAO = new PlazasDAO();
                    plazasDAO.insertPlazas(plazasVO);
                    TicketsVO ticketVO = new TicketsVO(matri, i);
                    TicketsDAO ticketDAO = new TicketsDAO();
                   
                    ticketDAO.insertTickets(ticketVO);

                    System.out.println("Ticket creado--> " + ticketVO);
                    return;

                }

            }

        }

        // Controlamos de si el tipo introducido es una motocicleta, si esta ocupada que pase a la siguiente y sino, si está libre,
        // la asignamos la plaza, ponemos el vehiculo y se actualiza el estado de libre a ocupada
        if (tipo.equalsIgnoreCase("motocicleta")) {
            int numeroMotocicleta = 1;
            for (int i = 1; i <= 15; i++) {

                if (plazasEstado[i].equalsIgnoreCase("ocupada")) {
                    i = i + numeroMotocicleta;
                    numeroMotocicleta++;
                } else if (plazasEstado[i].equalsIgnoreCase("libre")) {

                    vehiculos.insertarVehiculo(vehiculo);

                    PlazasVO plazaModificada = listaPlaza.get(i);
                    plazaModificada.setEstadoplaza("ocupada");

                    plazas.updatePlazas(listaPlaza.get(i).getNumplaza(), plazaModificada);
                    System.out.println("La plaza de motocicleta se ha actualizado");

                    // Se crea un ticket con toda la informacion relevante donde creamos la plaza primero y luego el ticket
                    PlazasVO plazasVO = new PlazasVO(tipo, i);
                    PlazasDAO plazasDAO = new PlazasDAO();
                    plazasDAO.insertPlazas(plazasVO);
                    TicketsVO ticketVO = new TicketsVO(matri, i);
                    TicketsDAO ticketDAO = new TicketsDAO();
                    ticketDAO.insertTickets(ticketVO);
                    System.out.println("Ticket creado--> " + ticketVO);
                    return;
                }
            }

        }

        // Controlamos de si el tipo introducido es una caravana, si esta ocupada que pase a la siguiente y sino, si está libre,
        // la asignamos la plaza, ponemos el vehiculo y se actualiza el estado de libre a ocupada
        if (tipo.equalsIgnoreCase("caravana")) {

            int numeroCaravana = 1;

            for (int i = 16; i <= 30; i++) {

                if (plazasEstado[i].equalsIgnoreCase("ocupada")) {
                    i = i + numeroCaravana;
                    numeroCaravana++;
                } else if (plazasEstado[i].equalsIgnoreCase("libre")) {

                    vehiculos.insertarVehiculo(vehiculo);

                    PlazasVO plazaModificada = listaPlaza.get(i);
                    plazaModificada.setEstadoplaza("ocupada");

                    plazas.updatePlazas(listaPlaza.get(i).getNumplaza(), plazaModificada);
                    System.out.println("La plaza de caravana se ha actualizado");

                    // Se crea un ticket con toda la informacion relevante donde creamos la plaza primero y luego el ticket
                    PlazasVO plazasVO = new PlazasVO(tipo, i);
                    PlazasDAO plazasDAO = new PlazasDAO();
                    plazasDAO.insertPlazas(plazasVO);
                    TicketsVO ticketVO = new TicketsVO(matri, i);
                    TicketsDAO ticketDAO = new TicketsDAO();
                    ticketDAO.insertTickets(ticketVO);
                    System.out.println("Ticket creado--> " + ticketVO);
                    return;
                }
            }

        }

    }

    // Metodo para retirar un vehiculo de una plaza
    public static void retirarPlz() throws SQLException {
        // De nuevo declaramos una serie de variables que nos haran falta, parecido al metodo de arriba de asignarPlz().
        VehiculoDAO vehiculo = new VehiculoDAO();
        TicketsDAO ticket = new TicketsDAO();
        PlazasDAO p = new PlazasDAO();
        String[] plazasEstado = new String[45];
        ArrayList<VehiculoVO> listaVehiculo = new ArrayList<>();
        ArrayList<TicketsVO> listaTicket = new ArrayList<>();
        ArrayList<PlazasVO> listaPlaza = new ArrayList<>();
        listaTicket = (ArrayList<TicketsVO>) ticket.getAll();
        listaPlaza = (ArrayList<PlazasVO>) p.getAll();

        // Pedimos los datos al usuario por teclado
        String matri;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Introduzca la matrícula (1111XXX)");
            matri = teclado.nextLine();
            // Controlamos el formato de la matricula
        } while (!(matri.length() == 7));
        System.out.println("Introduzca el numero de la plaza");
        int numeroPlaza = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Introduce el tipo (turismo-caravana-motocicleta)");
        String tipo = teclado.nextLine();
        System.out.println("Introduzca el pin creado anteriormente en su ticket");
        String pin_des = teclado.nextLine();

        VehiculoVO vehiculoVO = new VehiculoVO(matri, tipo);
        for (int i = 0; i < listaPlaza.size(); i++) {
            plazasEstado[i] = listaPlaza.get(i).getEstadoplaza();
        }

        // Recorremos el ticket y vamos comparando que si la matricula, el pin y la plaza corresponden con los almacenados
        // en el ticket, pues se mete dentro y si el tipo es uno de los tres hace una serie de operaciones
        for (TicketsVO ticketVO : listaTicket) {
            if (matri.equalsIgnoreCase(ticketVO.getMatricula()) && pin_des.equalsIgnoreCase(ticketVO.getPin_desechable()) && numeroPlaza == ticketVO.getNumplaza()) {
                // Si el tipo es motocicleta y la plaza esta libre la pasa y busca la que este ocupada, cuando la encuentra
                // y obtiene el valor, se actualiza quedando de nuevo libre
                if (tipo.equalsIgnoreCase("motocicleta")) {
                    int contador = 1;
                    for (int i = 1; i <= 15; i++) {

                        if (plazasEstado[i].equalsIgnoreCase("libre")) {
                            i = i + contador;
                            contador++;
                        }

                        if (plazasEstado[i].equalsIgnoreCase("ocupada")) {

                            PlazasVO plazaModificada = listaPlaza.get(i);
                            plazaModificada.setEstadoplaza("libre");

                            p.updatePlazas(listaPlaza.get(i).getNumplaza(), plazaModificada);
                            System.out.println("Plaza actualizada correctamente");
                            return;

                        }

                    }
                }

                // Si el tipo es caravana y la plaza esta libre la pasa y busca la que este ocupada, cuando la encuentra
                // y obtiene el valor, se actualiza quedando de nuevo libre
                if (tipo.equalsIgnoreCase("caravana")) {
                    int contador = 1;
                    for (int i = 16; i <= 30; i++) {

                        if (plazasEstado[i].equalsIgnoreCase("libre")) {
                            i = i + contador;
                            contador++;
                        }

                        if (plazasEstado[i].equalsIgnoreCase("ocupada")) {

                            PlazasVO plazaModificada = listaPlaza.get(i);
                            plazaModificada.setEstadoplaza("libre");

                            p.updatePlazas(listaPlaza.get(i).getNumplaza(), plazaModificada);
                            System.out.println("Plaza actualizada correctamente");
                            return;

                        }

                    }
                }

                // Si el tipo es turismo y la plaza esta libre la pasa y busca la que este ocupada, cuando la encuentra
                // y obtiene el valor, se actualiza quedando de nuevo libre
                if (tipo.equalsIgnoreCase("turismo")) {
                    int contador = 1;
                    for (int i = 31; i <= 45; i++) {

                        if (plazasEstado[i].equalsIgnoreCase("libre")) {
                            i = i + contador;
                            contador++;
                        }

                        if (plazasEstado[i].equalsIgnoreCase("ocupada")) {

                            PlazasVO plazaModificada = listaPlaza.get(i);
                            plazaModificada.setEstadoplaza("libre");

                            p.updatePlazas(listaPlaza.get(i).getNumplaza(), plazaModificada);
                            System.out.println("Plaza actualizada correctamente");
                            return;

                        }

                    }
                }
                // Una vez se complete las acciones de arriba, procedemos a borrar el vehiculo de la plaza usando
                // el metodo de su clase DAO
                ticket.deleteTickets(ticketVO);
                vehiculo.borrarVehiculo(vehiculoVO);
                System.out.println("Vehiculo retirado satisfactoriamente");
            }

        }
    }

}
