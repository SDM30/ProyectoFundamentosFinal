package javemarket.dominio.entidades;

public class Comprador {
    private String nombre;
    private String correo;
    private String contrasena;
    private String preferenciasEmprendimiento;

    public Comprador(String nombre, String correo, String contrasena, String preferenciasEmprendimiento) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.preferenciasEmprendimiento = preferenciasEmprendimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getPreferenciasEmprendimiento() {
        return preferenciasEmprendimiento;
    }

    public void setPreferenciasEmprendimiento(String preferenciasEmprendimiento) {
        this.preferenciasEmprendimiento = preferenciasEmprendimiento;
    }
}
