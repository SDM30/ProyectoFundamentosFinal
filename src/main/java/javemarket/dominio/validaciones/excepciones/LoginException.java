package javemarket.dominio.validaciones.excepciones;

public class LoginException extends RuntimeException{
    public LoginException(String mensaje) {
        super(mensaje);
    }
}
