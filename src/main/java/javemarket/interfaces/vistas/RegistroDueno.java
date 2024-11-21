package javemarket.interfaces.vistas;

import javemarket.infraestructura.persistencia.ConexionBase;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class RegistroDueno {
    private ConexionBase dbConnection;

    public RegistroDueno() {
        dbConnection = new ConexionBase();
    }

    public boolean validarCorreo(String email) {
        // Expresión regular para un correo electrónico que termine en @javeriana.edu.co
        String regex = "^[a-zA-Z0-9._%+-]+@javeriana.edu.co$";
        return Pattern.matches(regex, email);
    }

    public int registrar(int id, int doc, String correo, String contrasena) {
        if (id==0||doc==0 || correo.isEmpty() || contrasena.isEmpty()) {
            return 1; // No se deben registrar campos vacíos
        }
        if (validarCorreo(correo)) {
            try {
                String query = "INSERT INTO duenos (id, doc, correo, contrasena) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
                statement.setString(1, String.valueOf(id));
                statement.setString(2, String.valueOf(doc));
                statement.setString(3, correo);
                statement.setString(4, contrasena);

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
