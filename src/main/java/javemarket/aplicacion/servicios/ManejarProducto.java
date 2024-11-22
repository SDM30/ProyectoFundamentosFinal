package javemarket.aplicacion.servicios;

import javemarket.dominio.entidades.Producto;
import javemarket.dominio.repositorios.RepositorioProducto;
import javemarket.infraestructura.persistencia.ConexionBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManejarProducto implements RepositorioProducto {

    private ConexionBase dbConnection;

    public ManejarProducto() {
        this.dbConnection = new ConexionBase();
    }

    @Override
    public void crearProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, descripcion, unidades, precio, id_vendedor) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setInt(3, producto.getUnidades());
            stmt.setDouble(4, producto.getPrecio());
            stmt.setInt(5, producto.getIdVendedor()); // Nuevo campo id_vendedor
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Statement stmt = dbConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("unidades"),
                        rs.getDouble("precio")
                );
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setIdVendedor(rs.getInt("id_vendedor")); // Asignar id_vendedor
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public Producto obtenerProductoPorId(int idProducto) {
        Producto producto = null;
        String sql = "SELECT * FROM productos WHERE id_producto = ?";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, idProducto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto(
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getInt("unidades"),
                            rs.getDouble("precio")
                    );
                    producto.setIdProducto(rs.getInt("id_producto"));
                    producto.setIdVendedor(rs.getInt("id_vendedor")); // Asignar id_vendedor
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public void actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, unidades = ?, precio = ?, id_vendedor = ? WHERE id_producto = ?";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setInt(3, producto.getUnidades());
            stmt.setDouble(4, producto.getPrecio());
            stmt.setInt(5, producto.getIdVendedor()); // Actualizar id_vendedor
            stmt.setInt(6, producto.getIdProducto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarProducto(int idProducto) {
        String sql = "DELETE FROM productos WHERE id_producto = ?";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, idProducto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
