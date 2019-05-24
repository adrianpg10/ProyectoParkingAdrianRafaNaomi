/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.parking.datos;

import daw.plazas.PlazasDAO;
import java.sql.SQLException;

/**
 *
 * @author adrian
 */
public class Ordenes {

    public static void realizarOrden(Comandos ordenes) {

        switch (ordenes) {
            case VOLVER_MENU:
                System.out.println("Vuelve al menú principal");
                break;
            case ENTRAR_CLIENTE:
                System.out.println("Entrar a la zona clientes");
                break;
            case ENTRAR_ADMIN:
                System.out.println("Entrar a la zona de admin");
                break;
            case DEPOSITAR_VEHICULO:
                System.out.println("Depositar vehiculo");
                break;
            case RETIRAR_VEHICULO:
                System.out.println("Retirar vehiculo");
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
    
    public static void depositarVehiculo() throws SQLException{
        
        PlazasDAO aux=new PlazasDAO();
        System.out.println("A continuacion se le mostrará las plazas libres..");
        System.out.println();
        aux.getEstadosPlaza();       
        aux.asignacionPlz();
  
         
        
    }
    
    public static void main(String[] args) throws SQLException {
        Ordenes.depositarVehiculo();
    }
}
