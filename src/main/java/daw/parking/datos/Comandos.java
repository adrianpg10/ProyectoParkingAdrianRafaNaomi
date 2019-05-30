/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.parking.datos;

/**
 *
 * @author rafa
 */
public enum Comandos {
    // Los tipos ENUM tienen dos características fundamentales:
    // 1.- El constructor es privado
    // 2.- Solo pueden instanciarse con un tipo de objeto concreto
    
    // Todos los comandos usados
    VOLVER_MENU(0, "Vuelve al menú principal"),
    ENTRAR_CLIENTE(1, "Entrar a la zona clientes"),
    ENTRAR_ADMIN(2, "Entrar a la zona de admin"),
    DEPOSITAR_VEHICULO(3, "Depositar vehiculo"),
    RETIRAR_VEHICULO(4, "Retirar vehiculo"),
    DEPOSITAR_ABONADO(5, "Depositar abonado"),
    RETIRAR_ABONADO(6, "Retirar abonado"),
    ESTADO_PARKING(7, "Estado del parking"),
    FACTURACION_ENTRE_FECHAS(9, "Facturación entre fechas"),
    FACTURACION_ABONADO(10, "Facturación abonado"),
    ABONO_ALTA(12, "Alta de abono"),
    ABONO_MODIFICA(13, "Modificación de abono"),
    ABONO_BAJA(14, "Baja de abono"),
    ABONO_CADUCIDAD(15, "Caducidad de abono"),
    COPIA_SEGURIDAD_COPIAR(17, "Copiar datos"),
    COPIA_SEGURIDAD_RESTAURAR(18, "Restaurar datos"),
    SALIR(17,"SALIR");

    // Atributos
    private int codigo;

    private String descripcion;

    // Constructor parametrizado
    private Comandos(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;

    }

    
    // Getters y setters
    public int getCodigo() {
        return this.codigo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }
}
