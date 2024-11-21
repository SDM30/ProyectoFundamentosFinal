package javemarket.dominio.validaciones.excepciones;

public class UsuarioNoEncontradoException extends RuntimeException{
    public UsuarioNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
