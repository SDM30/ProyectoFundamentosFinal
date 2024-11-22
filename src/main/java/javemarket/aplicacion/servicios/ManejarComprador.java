package javemarket.aplicacion.servicios;

import javemarket.dominio.entidades.Comprador;
import javemarket.dominio.repositorios.RepositorioComprador;
import javemarket.infraestructura.persistencia.ConexionBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManejarComprador implements RepositorioComprador {

    private ConexionBase conexionBase;

    public ManejarComprador() {
        this.conexionBase = new ConexionBase();
    }

    @Override
    public List<Comprador> obtenerCompradores() {
        List<Comprador> compradores = new ArrayList<>();
        String sql = "SELECT * FROM compradores";

        try (Connection connection = conexionBase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Comprador comprador = new Comprador(
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contrasena"),
                        rs.getString("preferencias_emprendimiento")
                );
                compradores.add(comprador);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return compradores;
    }

    @Override
    public Comprador obtenerCompradorPorCorreo(String correo) {
        String sql = "SELECT * FROM compradores WHERE correo = ?";
        Comprador comprador = null;

        try (Connection connection = conexionBase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, correo.trim()); // Elimina espacios en blanco del correo
            System.out.println("Query preparada: " + stmt); // Para depurar

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Datos encontrados para el correo: " + correo);
                    comprador = new Comprador(
                            rs.getString("nombre"),
                            rs.getString("correo"),
                            rs.getString("contrasena"),
                            rs.getString("categoria_preferida") // Nombre de columna corregido
                    );
                } else {
                    System.out.println("No se encontraron datos para el correo: " + correo);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comprador;
    }

    @Override
    public void eliminarComprador(String correo) {
        String sql = "DELETE FROM compradores WHERE correo = ?";

        try (Connection connection = conexionBase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, correo);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Comprador eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún comprador con el correo: " + correo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agregarComprador(Comprador comprador) {
        String sql = "INSERT INTO compradores (nombre, correo, contrasena, preferencias_emprendimiento) VALUES (?, ?, ?, ?)";

        try (Connection connection = conexionBase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, comprador.getNombre());
            stmt.setString(2, comprador.getCorreo());
            stmt.setString(3, comprador.getContrasena());
            stmt.setString(4, comprador.getPreferenciasEmprendimiento());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Comprador agregado correctamente.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
