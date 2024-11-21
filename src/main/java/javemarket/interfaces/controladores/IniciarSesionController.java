package javemarket.interfaces.controladores;

import javemarket.dominio.entidades.IniciarSesion;
import javemarket.interfaces.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class IniciarSesionController {
    @FXML private TextField correoField;
    @FXML private PasswordField contras;

    private IniciarSesion inicioSesion;

    public IniciarSesionController() {
        inicioSesion = new IniciarSesion();
    }

    @FXML
    private void iniciarsesion(ActionEvent event) {

        String correo = correoField.getText();
        String contrasena = contras.getText();

        if ( correo.isEmpty() || contrasena.isEmpty()) {
            showAlert("Campos vacíos", "Por favor, complete todos los campos.");
            return;
        }

        int inicio = inicioSesion.verificarcredenciales(correo, contrasena);

        if (inicio==2 || inicio==3) {
            SceneManager.changeScene("/vistas/pagina.fxml");
        } else if (inicio==404) {
            showAlert("Error", "El usuario no existe porfavor registrarse");
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


