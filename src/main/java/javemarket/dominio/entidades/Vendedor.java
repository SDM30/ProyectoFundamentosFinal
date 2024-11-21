package javemarket.dominio.entidades;

public class Vendedor {
    private String nombre;
    private String correo;
    private String nombreEmprendimiento;
    private String categoria;
    private String contrasena;

    public Vendedor(String nombre, String correo, String nombreEmprendimiento, String categoria, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.nombreEmprendimiento = nombreEmprendimiento;
        this.categoria = categoria;
        this.contrasena = contrasena;
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

    public String getNombreEmprendimiento() {
        return nombreEmprendimiento;
    }

    public void setNombreEmprendimiento(String nombreEmprendimiento) {
        this.nombreEmprendimiento = nombreEmprendimiento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
