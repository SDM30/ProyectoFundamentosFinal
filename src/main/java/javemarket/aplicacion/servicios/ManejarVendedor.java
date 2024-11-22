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

    private Connection connection;

    public ManejarVendedor() {
        this.connection = new ConexionBase().getConnection();
    }

    @Override
    public List<Vendedor> obtenerVendedores() {
        List<Vendedor> vendedores = new ArrayList<>();
        String sql = "SELECT * FROM vendedores";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
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
            e.printStackTrace();
        }

        return vendedores;
    }

    @Override
    public Vendedor obtenerVendedorPorCorreo(String correo) {
        String sql = "SELECT * FROM vendedores WHERE correo = ?";
        Vendedor vendedor = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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
            e.printStackTrace();
        }

        return vendedor;
    }

    @Override
    public void eliminarVendedor(String correo) {
        String sql = "DELETE FROM vendedores WHERE correo = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, correo);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
