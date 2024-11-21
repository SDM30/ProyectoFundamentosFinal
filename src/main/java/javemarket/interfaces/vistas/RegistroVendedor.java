package javemarket.interfaces.vistas;

import javemarket.infraestructura.persistencia.ConexionBase;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class RegistroVendedor {

    private ConexionBase dbConnection;

    public RegistroVendedor() {
        dbConnection = new ConexionBase();
    }

    public boolean validarCorreo(String email) {
        // Expresión regular para un correo electrónico que termine en @javeriana.edu.co
        String regex = "^[a-zA-Z0-9._%+-]+@javeriana.edu.co$";
        return Pattern.matches(regex, email);
    }

    public int registrar(String nombre, String correo, String nombreEmprendimiento, String categoria, String contrasena) {
        if (nombre.isEmpty() || correo.isEmpty() || nombreEmprendimiento.isEmpty() || categoria == null || contrasena.isEmpty()) {
            return 1; // No se deben registrar campos vacíos
        }
        if (validarCorreo(correo)) {
            try {
                String query = "INSERT INTO vendedores (nombre, correo, contrasena, nombre_emprendimiento, categoria) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
                statement.setString(1, nombre);
                statement.setString(2, correo);
                statement.setString(3, contrasena);
                statement.setString(4, nombreEmprendimiento);
                statement.setString(5, categoria);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0)
                    return 0; // Devuelve true si el registro fue exitoso // Devuelve true si el registro fue exitoso

            } catch (SQLException e) {
                e.printStackTrace();
                return 1;
            }
        }
        return 0;
    }
}

