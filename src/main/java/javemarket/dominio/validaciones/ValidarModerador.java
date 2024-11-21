package javemarket.dominio.validaciones;

import javemarket.dominio.entidades.Moderador;
import javemarket.dominio.repositorios.RepositorioModerador;
import javemarket.dominio.validaciones.excepciones.ValidacionException;

import java.util.regex.Pattern;

public class ValidarModerador {
    private RepositorioModerador repoModerador;

    public ValidarModerador(RepositorioModerador repoModerador) {
        this.repoModerador = repoModerador;
    }

    public boolean validarCorreo(String email) {
        // Expresión regular para un correo electrónico que termine en @javeriana.edu.co
        String regex = "^[a-zA-Z0-9._%+-]+@javeriana.edu.co$";
        return Pattern.matches(regex, email);
    }

    public void validar(Moderador moderador) {
        // Verificar que el identificador no esté vacío
        if (moderador.getIdentificador() == null || moderador.getIdentificador().isEmpty()) {
            throw new ValidacionException("El identificador no puede estar vacío.");
        }

        // Verificar que el correo no esté vacío y sea válido
        if (moderador.getCorreo() == null || moderador.getCorreo().isEmpty() || !validarCorreo(moderador.getCorreo())) {
            throw new ValidacionException("El correo no es válido.");
        }

        // Verificar que las contraseñas coincidan
        if (!moderador.getContrasena().equals(moderador.getRepetirContrasena())) {
            throw new ValidacionException("Las contraseñas no coinciden.");
        }

        // Verificar que el moderador no exista ya en el repositorio
        Moderador moderadorExistente = repoModerador.obtenerModeradorPorCorreo(moderador.getCorreo());
        if (moderadorExistente != null) {
            throw new ValidacionException("El moderador ya está registrado.");
        }
    }
}
