package javemarket.infraestructura.persistencia;

import java.sql.*;

public class ConexionBase {
    private Connection connection;

    public ConexionBase() {
        try {
            // Establecer la conexión con la base de datos en Clever Cloud
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://btan63ayniz2qppj77fo-mysql.services.clever-cloud.com:3306/btan63ayniz2qppj77fo",
                    "ufcgj4tcw7upbu2g",  // Usuario o correo
                    "UGNA7scgyDVbtHm5YwEW" // Contraseña
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}


