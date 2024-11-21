package javemarket.dominio.entidades;

public class Moderador {
    private String identificador;
    private String correo;
    private String contrasena;
    private String repetirContrasena;

    public Moderador(String identificador, String correo, String contrasena, String repetirContrasena) {
        this.identificador = identificador;
        this.correo = correo;
        this.contrasena = contrasena;
        this.repetirContrasena = repetirContrasena;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRepetirContrasena() {
        return repetirContrasena;
    }

    public void setRepetirContrasena(String repetirContrasena) {
        this.repetirContrasena = repetirContrasena;
    }
}
