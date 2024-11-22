package javemarket.dominio.repositorios;
import javemarket.dominio.entidades.Vendedor;
import java.util.List;

public interface RepositorioVendedor {

    List<Vendedor> obtenerVendedores();

    Vendedor obtenerVendedorPorCorreo(String correo);

    void eliminarVendedor(String correo);
}
