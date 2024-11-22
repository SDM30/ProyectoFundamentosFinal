package javemarket.aplicacion.servicios;

import javemarket.dominio.entidades.Vendedor;
import javemarket.dominio.repositorios.RepositorioVendedor;
import javemarket.infraestructura.persistencia.ConexionBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManejarVendedor implements RepositorioVendedor {

    private ConexionBase conexionBase;

    public ManejarVendedor() {
        this.conexionBase = new ConexionBase();
    }

    @Override
    public List<Vendedor> obtenerVendedores() {
        List<Vendedor> vendedores = new ArrayList<>();
        String sql = "SELECT * FROM vendedores";

        try (Connection connection = conexionBase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vendedor vendedor = new Vendedor(
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("nombre_emprendimiento"),
                        rs.getString("categoria"),
                        rs.getString("contrasena")
                );
                vendedores.add(vendedor);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener la lista de vendedores: " + e.getMessage());
        }

        return vendedores;
    }

    @Override
    public Vendedor obtenerVendedorPorCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede ser nulo o vacío");
        }

        String sql = "SELECT * FROM vendedores WHERE correo = ?";
        Vendedor vendedor = null;

        try (Connection connection = conexionBase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, correo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vendedor = new Vendedor(
                            rs.getString("nombre"),
                            rs.getString("correo"),
                            rs.getString("nombre_emprendimiento"),
                            rs.getString("categoria"),
                            rs.getString("contrasena")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener el vendedor por correo: " + e.getMessage());
        }

        return vendedor;
    }

    @Override
    public void eliminarVendedor(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede ser nulo o vacío");
        }

        String sql = "DELETE FROM vendedores WHERE correo = ?";

        try (Connection connection = conexionBase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, correo);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Vendedor eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún vendedor con el correo: " + correo);
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar el vendedor: " + e.getMessage());
        }
    }
}
