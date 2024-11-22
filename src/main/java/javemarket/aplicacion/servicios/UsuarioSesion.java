package javemarket.aplicacion.servicios;

import javemarket.dominio.entidades.Comprador;
import javemarket.dominio.entidades.Vendedor;

public class UsuarioSesion {
    private static UsuarioSesion instancia; // Instancia única de la clase
    private Comprador compradorActual; // Comprador actual
    private Vendedor vendedorActual; // Vendedor actual


    private UsuarioSesion() {
    }

    // Método para obtener la instancia única
    public static UsuarioSesion getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioSesion();
        }
        return instancia;
    }

    // Métodos para manejar el comprador actual
    public void setCompradorActual(Comprador comprador) {
        this.compradorActual = comprador;
        this.vendedorActual = null; // Asegúrate de que no haya un vendedor activo
    }

    public Comprador getCompradorActual() {
        return compradorActual;
    }

    // Métodos para manejar el vendedor actual
    public void setVendedorActual(Vendedor vendedor) {
        this.vendedorActual = vendedor;
        this.compradorActual = null; // Asegúrate de que no haya un comprador activo
    }

    public Vendedor getVendedorActual() {
        return vendedorActual;
    }

    // Método para cerrar la sesión del usuario actual
    public void cerrarSesion() {
        this.compradorActual = null;
        this.vendedorActual = null;
    }
}
