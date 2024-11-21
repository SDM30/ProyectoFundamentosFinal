package javemarket.dominio.validaciones;

import javemarket.dominio.entidades.Dueno;
import javemarket.dominio.repositorios.RepositorioDueno;
import javemarket.dominio.validaciones.excepciones.ValidacionException;

import java.util.regex.Pattern;

public class ValidarDueno {
    private RepositorioDueno repoDueno;

    public ValidarDueno(RepositorioDueno repoDueno) {
        this.repoDueno = repoDueno;
    }

    public boolean validarCorreo(String email) {
        // Expresión regular para un correo electrónico que termine en @javeriana.edu.co
        String regex = "^[a-zA-Z0-9._%+-]+@javeriana.edu.co$";
        return Pattern.matches(regex, email);
    }

    public void validar(Dueno dueno) {
        // Verificar que el identificador no esté vacío
        if (dueno.getIdentificador() == null || dueno.getIdentificador().isEmpty()) {
            throw new ValidacionException("El identificador no puede estar vacío.");
        }

        // Verificar que el correo no esté vacío y sea válido
        if (dueno.getCorreo() == null || dueno.getCorreo().isEmpty() || !validarCorreo(dueno.getCorreo())) {
            throw new ValidacionException("El correo no es válido.");
        }

        // Verificar que la contraseña sea de al menos 8 caracteres
        if (dueno.getContrasena() == null || dueno.getContrasena().length() < 8) {
            throw new ValidacionException("La contraseña debe tener al menos 8 caracteres.");
        }

        // Verificar que el dueño no exista ya en el repositorio
        Dueno duenoExistente = repoDueno.obtenerDuenoPorCorreo(dueno.getCorreo());
        if (duenoExistente != null) {
            throw new ValidacionException("El dueño ya está registrado.");
        }
    }
}
