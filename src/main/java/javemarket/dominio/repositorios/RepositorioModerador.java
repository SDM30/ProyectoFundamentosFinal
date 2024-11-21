package javemarket.dominio.repositorios;
import javemarket.dominio.entidades.Moderador;
import java.util.List;

public interface RepositorioModerador {
    void crearModerador(Moderador moderador);

    List<Moderador> obtenerModeradores();

    Moderador obtenerModeradorPorCorreo(String correo);

    void eliminarModerador(String correo);
}
