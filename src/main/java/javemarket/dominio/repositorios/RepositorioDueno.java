package javemarket.dominio.repositorios;
import javemarket.dominio.entidades.Dueno;
import java.util.List;

public interface RepositorioDueno {
    void crearDueno(Dueno dueno);

    List<Dueno> obtenerDuenos();

    Dueno obtenerDuenoPorCorreo(String correo);

    void eliminarDueno(String correo);
}
