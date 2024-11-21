package javemarket.dominio.validaciones;

import javemarket.dominio.entidades.Comprador;
import javemarket.dominio.repositorios.RepositorioComprador;
import javemarket.dominio.validaciones.excepciones.ValidacionException;

import java.util.regex.Pattern;

public class ValidarComprador {
    private RepositorioComprador repoComprador;

    public ValidarComprador(RepositorioComprador repoComprador) {
        this.repoComprador = repoComprador;
    }

    public boolean validarCorreo(String email) {
        // Expresión regular para un correo electrónico que termine en @javeriana.edu.co
        String regex = "^[a-zA-Z0-9._%+-]+@javeriana.edu.co$";
        return Pattern.matches(regex, email);
    }

    public void validar(Comprador comprador) {
        // Verificar que el nombre no esté vacío
        if (comprador.getNombre() == null || comprador.getNombre().isEmpty()) {
            throw new ValidacionException("El nombre no puede estar vacío.");
        }

        // Verificar que el correo no esté vacío y sea válido
        if (comprador.getCorreo() == null || comprador.getCorreo().isEmpty() || !validarCorreo(comprador.getCorreo())) {
            throw new ValidacionException("El correo no es válido.");
        }

        // Verificar que la contraseña sea de al menos 8 caracteres
        if (comprador.getContrasena() == null || comprador.getContrasena().length() < 8) {
            throw new ValidacionException("La contraseña debe tener al menos 8 caracteres.");
        }

        // Verificar que el comprador no exista ya en el repositorio
        Comprador compradorExistente = repoComprador.obtenerCompradorPorCorreo(comprador.getCorreo());
        if (compradorExistente != null) {
            throw new ValidacionException("El comprador ya está registrado.");
        }
    }
}
