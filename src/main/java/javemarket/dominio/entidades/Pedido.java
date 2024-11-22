package javemarket.dominio.entidades;

import java.time.LocalDateTime;

public class Pedido {
    private int idPedido; // Este campo se ignora al crear el pedido
    private LocalDateTime fecha;
    private String cliente;
    private double total;
    private String comentario;
    private String edificio;

    // Constructor vacío
    public Pedido() {
    }

    public Pedido(LocalDateTime fecha, String cliente, double total, String comentario, String edificio) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.total = total;
        this.comentario = comentario;
        this.edificio = edificio;
    }

    // Getters y Setters
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", fecha=" + fecha +
                ", cliente='" + cliente + '\'' +
                ", total=" + total +
                ", comentario='" + comentario + '\'' +
                ", edificio='" + edificio + '\'' +
                '}';
    }
}