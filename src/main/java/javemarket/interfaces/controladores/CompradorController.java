package javemarket.interfaces.controladores;

import javemarket.interfaces.vistas.RegistroComprador;
import javemarket.interfaces.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CompradorController {

    @FXML private TextField nombreCompradorField;
    @FXML private TextField correoCompradorField;
    @FXML private PasswordField contrasenaCompradorField;
    @FXML private ComboBox<String> preferenciaComboBox;

    private RegistroComprador registradorComprador;

    public CompradorController() {
        registradorComprador = new RegistroComprador();
    }

    @FXML
    private void registrarComprador(ActionEvent event) {
        String nombre = nombreCompradorField.getText();
        String correo = correoCompradorField.getText();
        String contrasena = contrasenaCompradorField.getText();
        String preferencia = preferenciaComboBox.getValue();

        if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || preferencia == null) {
            showAlert("Campos vacíos", "Por favor, complete todos los campos.");
            return;
        }

        int registrado = registradorComprador.registrar(nombre, correo, contrasena, preferencia);

        if (registrado==0) {
            showAlert("Registro Exitoso", "El comprador se ha registrado exitosamente.");
            nombreCompradorField.clear();
            correoCompradorField.clear();
            contrasenaCompradorField.clear();
            preferenciaComboBox.getSelectionModel().clearSelection();} else if (registrado==4){
            showAlert("Error", "No se pudo registrar al comprador:El correo no es valido.");
        }else showAlert("Error", "No se pudo registrar al comprador.");
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