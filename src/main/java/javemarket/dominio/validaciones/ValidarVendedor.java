package javemarket.dominio.validaciones;

import javemarket.dominio.entidades.Vendedor;
import javemarket.dominio.repositorios.RepositorioVendedor;
import javemarket.dominio.validaciones.excepciones.ValidacionException;

import java.util.regex.Pattern;

public class ValidarVendedor {
    private RepositorioVendedor repoVendedor;

    public ValidarVendedor(RepositorioVendedor repoVendedor) {
        this.repoVendedor = repoVendedor;
    }

    public boolean validarCorreo(String email) {
        // Expresión regular para un correo electrónico que termine en @javeriana.edu.co
        String regex = "^[a-zA-Z0-9._%+-]+@javeriana.edu.co$";
        return Pattern.matches(regex, email);
    }

    public void validar(Vendedor vendedor) {
        // Verificar que el nombre del emprendimiento no esté vacío
        if (vendedor.getNombreEmprendimiento() == null || vendedor.getNombreEmprendimiento().isEmpty()) {
            throw new ValidacionException("El nombre del emprendimiento no puede estar vacío.");
        }

        // Verificar que el correo no esté vacío y sea válido
        if (vendedor.getCorreo() == null || vendedor.getCorreo().isEmpty() || !validarCorreo(vendedor.getCorreo())) {
            throw new ValidacionException("El correo no es válido.");
        }

        // Verificar que la contraseña sea de al menos 8 caracteres
        if (vendedor.getContrasena() == null || vendedor.getContrasena().length() < 8) {
            throw new ValidacionException("La contraseña debe tener al menos 8 caracteres.");
        }

        // Verificar que el vendedor no exista ya en el repositorio
        Vendedor vendedorExistente = repoVendedor.obtenerVendedorPorCorreo(vendedor.getCorreo());
        if (vendedorExistente != null) {
            throw new ValidacionException("El vendedor ya está registrado.");
        }
    }
}
