package javemarket.interfaces.controladores;

import javemarket.aplicacion.servicios.IniciarSesion;
import javemarket.aplicacion.servicios.UsuarioSesion;
import javemarket.dominio.entidades.Comprador;
import javemarket.dominio.entidades.Vendedor;
import javemarket.interfaces.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class IniciarSesionController {
    @FXML private TextField correoField;
    @FXML private PasswordField contrasField;

    private IniciarSesion inicioSesion;

    public IniciarSesionController() {
        inicioSesion = new IniciarSesion();
    }

    @FXML
    private void iniciarsesion(ActionEvent event) {
        String correo = correoField.getText();
        String contrasena = contrasField.getText();

        if (correo.isEmpty() || contrasena.isEmpty()) {
            showAlert("Campos vacíos", "Por favor, complete todos los campos.");
            return;
        }

        // Verificar credenciales
        int inicio = inicioSesion.verificarcredenciales(correo, contrasena);

        if (inicio == 2) { // Comprador
            Comprador comprador = inicioSesion.obtenerCompradorPorCorreo(correo);
            UsuarioSesion.getInstancia().setCompradorActual(comprador);
            System.out.println("[DEBUG] Comprador autenticado: " + comprador.getNombre() + " (" + comprador.getCorreo() + ")");
            SceneManager.changeScene("/vistas/menuComprador.fxml");
        } else if (inicio == 3) { // Vendedor
            Vendedor vendedor = inicioSesion.obtenerVendedorPorCorreo(correo);
            UsuarioSesion.getInstancia().setVendedorActual(vendedor);
            System.out.println("[DEBUG] Vendedor autenticado: " + vendedor.getNombre() + " (" + vendedor.getCorreo() + ")");
            SceneManager.changeScene("/vistas/menuVendedor.fxml");
        } else if (inicio == 404) { // Usuario no encontrado
            showAlert("Error", "El usuario no existe. Por favor, regístrese.");
            System.out.println("[DEBUG] Usuario no encontrado con correo: " + correo);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void volver() {
        SceneManager.changeScene("/vistas/hello-view.fxml");
    }
}
