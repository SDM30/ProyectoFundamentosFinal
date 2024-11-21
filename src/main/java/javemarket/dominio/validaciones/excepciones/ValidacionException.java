package javemarket.dominio.validaciones.excepciones;

public class ValidacionException extends RuntimeException {
    public ValidacionException(String mensaje){
        super(mensaje);
    }
}
