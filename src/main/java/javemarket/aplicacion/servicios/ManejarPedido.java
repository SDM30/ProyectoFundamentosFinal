package javemarket.aplicacion.servicios;


import javemarket.dominio.entidades.Pedido;
import javemarket.dominio.repositorios.RepositorioPedido;
import javemarket.infraestructura.persistencia.ConexionBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManejarPedido implements RepositorioPedido {

    private ConexionBase dbConnection;

    public ManejarPedido() {
        this.dbConnection = new ConexionBase();
    }

    @Override
    public void crearPedido(Pedido pedido) {
        String sql = "INSERT INTO pedidos (fecha, cliente, total, comentario, id_edificio) " +
                "SELECT ?, ?, ?, ?, e.id_edificio FROM edificios e WHERE e.nombre = ?";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(pedido.getFecha()));
            stmt.setString(2, pedido.getCliente());
            stmt.setDouble(3, pedido.getTotal());
            stmt.setString(4, pedido.getComentario());
            stmt.setString(5, pedido.getEdificio());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pedido> obtenerPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT p.*, e.nombre AS edificio FROM pedidos p " +
                "JOIN edificios e ON p.id_edificio = e.id_edificio";
        try (Statement stmt = dbConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getTimestamp("fecha").toLocalDateTime(),
                        rs.getString("cliente"),
                        rs.getDouble("total"),
                        rs.getString("comentario"),
                        rs.getString("edificio")
                );
                pedido.setIdPedido(rs.getInt("id_pedido"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    @Override
    public Pedido obtenerPedidoPorId(int idPedido) {
        Pedido pedido = null;
        String sql = "SELECT p.*, e.nombre AS edificio FROM pedidos p " +
                "JOIN edificios e ON p.id_edificio = e.id_edificio WHERE p.id_pedido = ?";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pedido = new Pedido(
                            rs.getTimestamp("fecha").toLocalDateTime(),
                            rs.getString("cliente"),
                            rs.getDouble("total"),
                            rs.getString("comentario"),
                            rs.getString("edificio")
                    );
                    pedido.setIdPedido(rs.getInt("id_pedido"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedido;
    }

    @Override
    public List<Pedido> obtenerPedidosPorCliente(String cliente) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT p.*, e.nombre AS edificio FROM pedidos p " +
                "JOIN edificios e ON p.id_edificio = e.id_edificio WHERE p.cliente = ?";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, cliente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pedido pedido = new Pedido(
                            rs.getTimestamp("fecha").toLocalDateTime(),
                            rs.getString("cliente"),
                            rs.getDouble("total"),
                            rs.getString("comentario"),
                            rs.getString("edificio")
                    );
                    pedido.setIdPedido(rs.getInt("id_pedido"));
                    pedidos.add(pedido);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    @Override
    public void eliminarPedido(int idPedido) {
        String sql = "DELETE FROM pedidos WHERE id_pedido = ?";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
