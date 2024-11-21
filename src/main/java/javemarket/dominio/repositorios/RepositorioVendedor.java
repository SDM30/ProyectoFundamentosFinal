package javemarket.dominio.repositorios;
import javemarket.dominio.entidades.Vendedor;
import java.util.List;

public interface RepositorioVendedor {
    void crearVendedor(Vendedor vendedor);

    List<Vendedor> obtenerVendedores();

    Vendedor obtenerVendedorPorCorreo(String correo);

    void eliminarVendedor(String correo);
}
