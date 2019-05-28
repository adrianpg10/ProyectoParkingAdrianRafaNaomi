/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.parking.datos;

import daw.plazas.PlazasDAO;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author adrian
 */
public class Ordenes {

    // Metodo realizar orden que recibe un comando
    public static void realizarOrden(Comandos ordenes) throws SQLException {

        switch (ordenes) {
            case VOLVER_MENU:
               Ordenes.realizarOrden(Menu.menu());
               
                break;
            case ENTRAR_CLIENTE:
                System.out.println("Entrar a la zona clientes");
               
                break;
            case ENTRAR_ADMIN:
                System.out.println("Entrar a la zona de admin");
               
                break;
            case DEPOSITAR_VEHICULO:
                System.out.println("Entrando a la zona de depositar vehiculo..");
                Ordenes.depositarVehiculo();
                
                break;
            case RETIRAR_VEHICULO:
                System.out.println("Retirar vehiculo");
                Ordenes.retirarVehiculo();
               
                break;
            case DEPOSITAR_ABONADO:
                System.out.println("Depositar abonado");
               
                break;
            case RETIRAR_ABONADO:
                System.out.println("Retirar abonado");
              
                break;
            case ESTADO_PARKING:
                System.out.println("Estado del parking");
               
                break;
            case FACTURACION_ENTRE_FECHAS:
                System.out.println("Facturación entre fechas");
                
                break;
            case FACTURACION_ABONADO:
                System.out.println("Facturación abonado");
                
                break;
            case ABONO_ALTA:
                System.out.println("Alta de abono");
               
                break;
            case ABONO_MODIFICA:
                System.out.println("Modificación de abono");
               
                break;
            case ABONO_BAJA:
                System.out.println("Baja de abono");
               
                break;
            case ABONO_CADUCIDAD:
                System.out.println("Caducidad de abono");
                
                break;
            case COPIA_SEGURIDAD_COPIAR:
                System.out.println("Copiar datos");
                
                break;
            case COPIA_SEGURIDAD_RESTAURAR:
                System.out.println("Restaurar datos");
               
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
    public static void retirarVehiculo() {

        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce una matricula");
        
       
    }

    public static void main(String[] args) throws SQLException {
        
        Ordenes.depositarVehiculo();
        System.out.println("------------");
        Ordenes.retirarVehiculo();
    }
}
