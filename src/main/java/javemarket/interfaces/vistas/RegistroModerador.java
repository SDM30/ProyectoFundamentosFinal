package javemarket.interfaces.vistas;

import javemarket.infraestructura.persistencia.ConexionBase;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class RegistroModerador {
    private ConexionBase dbConnection;

    public RegistroModerador() {
        dbConnection = new ConexionBase();
    }

    public boolean validarCorreo(String email) {
        // Expresión regular para un correo electrónico que termine en @javeriana.edu.co
        String regex = "^[a-zA-Z0-9._%+-]+@javeriana.edu.co$";
        return Pattern.matches(regex, email);
    }

    public int registrar(int identificador, String correo, String contrasena, String contrasena2) {
        if (identificador == 0 || correo.isEmpty() || contrasena.isEmpty() || contrasena2.isEmpty()) {
            return 1;  // No se deben registrar campos vacíos
        }
        if (validarCorreo(correo)) {
            try {
                String query = "INSERT INTO compradores (nombre, correo, contrasena, contrasena2) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
                statement.setString(1, String.valueOf(identificador));
                statement.setString(2, correo);
                statement.setString(3, contrasena);
                statement.setString(4, contrasena2);

                int rowsAffected = statement.executeUpdate();
                return 0; // Devuelve true si el registro fue exitoso

            } catch (SQLException e) {
                e.printStackTrace();
                return 1;
            }
        }else return 4;
    }
}
