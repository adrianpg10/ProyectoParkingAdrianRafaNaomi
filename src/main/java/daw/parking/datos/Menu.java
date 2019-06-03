/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.parking.datos;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author naomi
 */
public class Menu {

    // Creamos la estructura del menu teniendo en cuenta las excepciones que se puedan dar
    public static Comandos menu() {

        Scanner teclado = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("===============PARKING DAW================");
        System.out.println("==========================================");
        System.out.println("1- Entrar en la zona clientes.");
        System.out.println("2- Entrar en la zona administrador.");
        System.out.println("3- Salir");

        int opcion = teclado.nextInt();

        while (!(opcion == 1 || opcion == 2 || opcion == 3)) {
            System.out.println("La opción que has elegido es incorrecta ");
            opcion = teclado.nextInt();
        }

        switch (opcion) {
            case 1:
                System.out.println("Zona cliente. Elija una opción:");
                System.out.println("1- Depositar vehículo.");
                System.out.println("2- Retirar vehículo.");
                System.out.println("3- Depositar Abonado");
                System.out.println("4- Retirar Abonado");
                System.out.println("0- Volver al menú principal");
                int opcion2 = teclado.nextInt();

                while (!(opcion == 1 || opcion == 2 || opcion == 3 || opcion == 4 || opcion == 0)) {
                    System.out.println("La opción que has elegido es incorrecta ");
                    opcion2 = teclado.nextInt();
                }
                switch (opcion2) {
                    case 0:

                        return Comandos.VOLVER_MENU;

                    case 1:
                        return Comandos.DEPOSITAR_VEHICULO;
                    case 2:
                        return Comandos.RETIRAR_VEHICULO;
                    case 3:
                        return Comandos.DEPOSITAR_ABONADO;
                    case 4:
                        return Comandos.RETIRAR_ABONADO;

                }
                break;

            case 2:
                System.out.println("Zona administrador. Elija una opción:");
                System.out.println("1- Consultar estado del parking.");
                System.out.println("2- Facturación entre fechas.");
                System.out.println("3- Facturación abonado");
                System.out.println("4- Dar de alta un abonado.");
                System.out.println("5- Modificar datos abonado.");
                System.out.println("6- Dar de baja un abonado");
                System.out.println("7- Datos caducidad abonado.");
                System.out.println("8- Realizar copia de seguridad.");
                System.out.println("9- Restaurar copia de seguridad");
                System.out.println("0-Volver al menú");
                int opcion3 = teclado.nextInt();
                while (opcion3 < 0 || opcion3 > 9) {
                    System.out.println("La opción que has elegido es incorrecta ");
                    opcion2 = teclado.nextInt();
                }

                switch (opcion3) {
                    case 0:
                        return Comandos.VOLVER_MENU;
                    case 1:
                        return Comandos.ESTADO_PARKING;
                    case 2:
                        return Comandos.FACTURACION_ENTRE_FECHAS;
                    case 3:
                        return Comandos.FACTURACION_ABONADO;
                    case 4:
                        return Comandos.ABONO_ALTA;
                    case 5:
                        return Comandos.ABONO_MODIFICA;
                    case 6:
                        return Comandos.ABONO_BAJA;
                    case 7:
                        return Comandos.ABONO_CADUCIDAD;
                    case 8:
                        return Comandos.COPIA_SEGURIDAD_COPIAR;
                    case 9:
                        return Comandos.COPIA_SEGURIDAD_RESTAURAR;

                    default:
                        throw new AssertionError();
                }
                
            case 3:
                return Comandos.SALIR;
                
            default:
                throw new AssertionError();

        }
        return null;

    }

    public static void main(String[] args) throws SQLException, FileNotFoundException, ParseException {
        Ordenes.realizarOrden(Menu.menu());
    }
}
