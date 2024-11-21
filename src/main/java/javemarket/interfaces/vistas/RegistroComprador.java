package javemarket.interfaces.vistas;

import javemarket.infraestructura.persistencia.ConexionBase;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class RegistroComprador {

    private ConexionBase dbConnection;

    public RegistroComprador() {
        dbConnection = new ConexionBase();
    }
    public boolean validarCorreo(String email) {
        // Expresión regular para un correo electrónico que termine en @javeriana.edu.co
        String regex = "^[a-zA-Z0-9._%+-]+@javeriana.edu.co$";
        return Pattern.matches(regex, email);
    }
    public int registrar(String nombre, String correo, String contrasena, String preferencia) {
        if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || preferencia == null) {
            return 1; // No se deben registrar campos vacíos
        }
        if (validarCorreo(correo)) {
            try {
                String query = "INSERT INTO compradores (nombre, correo, contrasena, categoria_preferida) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
                statement.setString(1, nombre);
                statement.setString(2, correo);
                statement.setString(3, contrasena);
                statement.setString(4, preferencia);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0)
                    return 0; // Devuelve true si el registro fue exitoso

            } catch (SQLException e) {

                e.printStackTrace();
                return 1;
            }
        }else return 4;
        return 0;
    }
}


