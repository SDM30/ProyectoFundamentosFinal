package javemarket.dominio.repositorios;
import javemarket.dominio.entidades.Comprador;
import java.util.List;

public interface RepositorioComprador {

    List<Comprador> obtenerCompradores();

    Comprador obtenerCompradorPorCorreo(String correo);

    void eliminarComprador(String correo);
}
