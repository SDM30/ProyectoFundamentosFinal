package javemarket.dominio.entidades;

public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private int unidades;
    private double precio;


    private int IdVendedor;

    // Constructor vacío
    public Producto() {
    }

    public Producto(String nombre, String descripcion, int unidades, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidades = unidades;
        this.precio = precio;
    }

    // Getters y Setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", unidades=" + unidades +
                ", precio=" + precio +
                '}';
    }

    public int getIdVendedor() {
        return this.IdVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        IdVendedor = idVendedor;
    }
}