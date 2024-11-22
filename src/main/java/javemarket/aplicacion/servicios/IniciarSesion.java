package javemarket.aplicacion.servicios;

import javemarket.dominio.entidades.Comprador;
import javemarket.dominio.entidades.Vendedor;
import javemarket.infraestructura.persistencia.ConexionBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class IniciarSesion {
    private ConexionBase dbConnection;
    private ManejarComprador manejarComprador;
    private ManejarVendedor manejarVendedor;


    public IniciarSesion() {
        dbConnection = new ConexionBase();
        this.manejarComprador = new ManejarComprador();
        this.manejarVendedor = new ManejarVendedor();
    }
    public boolean validarCorreo(String email) {
        // Expresión regular para un correo electrónico que termine en @javeriana.edu.co
        String regex = "^[a-zA-Z0-9._%+-]+@javeriana.edu.co$";
        return Pattern.matches(regex, email);
    }
    public int verificarcredenciales (String correo, String contrasena) {
        if (correo.isEmpty() || contrasena.isEmpty()) {
            return 1; // No se puede iniciar sesión
        }

        try {
            // Verificar en la tabla compradores
            String queryCompradores = "SELECT * FROM compradores WHERE correo = ? AND contrasena = ?";
            PreparedStatement statementCompradores = dbConnection.getConnection().prepareStatement(queryCompradores);
            statementCompradores.setString(1, correo);
            statementCompradores.setString(2, contrasena);
            ResultSet resultSetCompradores = statementCompradores.executeQuery();

            if (resultSetCompradores.next()) {
                return 2; // Credenciales válidas para un comprador
            }

            // Verificar en la tabla vendedores
            String queryVendedores = "SELECT * FROM vendedores WHERE correo = ? AND contrasena = ?";
            PreparedStatement statementVendedores = dbConnection.getConnection().prepareStatement(queryVendedores);
            statementVendedores.setString(1, correo);
            statementVendedores.setString(2, contrasena);
            ResultSet resultSetVendedores = statementVendedores.executeQuery();

            if (resultSetVendedores.next()) {
                return 3; // Credenciales válidas para un vendedor
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 1; // Error en SQL
        }

        return 404; // Credenciales no coinciden con ninguna tabla
    }

    public Comprador obtenerCompradorPorCorreo(String correo) {
        return manejarComprador.obtenerCompradorPorCorreo(correo);
    }

    public Vendedor obtenerVendedorPorCorreo(String correo) {
        return manejarVendedor.obtenerVendedorPorCorreo(correo);
    }
}
