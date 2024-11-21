package javemarket.dominio.entidades;

public class Dueno {
    private String identificador;
    private String documento;
    private String correo;
    private String contrasena;

    public Dueno(String identificador, String documento, String correo, String contrasena) {
        this.identificador = identificador;
        this.documento = documento;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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
}
