package javemarket.interfaces.controladores;

import javemarket.interfaces.vistas.RegistroModerador;
import javemarket.interfaces.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ModeradorController{

    @FXML private TextField idField;
    @FXML private TextField correoField;
    @FXML private TextField contrasenaModerador;
    @FXML private TextField contrasenaRModerador;


    private RegistroModerador registroModerador;

    public ModeradorController() {
        registroModerador = new RegistroModerador();
    }

    @FXML
    private void registrarModerador(ActionEvent event) {
        int identificador = Integer.parseInt(idField.getText());
        String correo = correoField.getText();
        String contrasena = contrasenaModerador.getText();
        String contrasenaRepetir = contrasenaRModerador.getText();


        if (identificador==0|| correo.isEmpty() || contrasena.isEmpty() || contrasenaRepetir.isEmpty()) {
            showAlert("Campos vacíos", "Por favor, complete todos los campos.");
            return;
        }

        int registrado = registroModerador.registrar(identificador, correo, contrasena, contrasenaRepetir);

        if (registrado==0) {
            showAlert("Registro Exitoso", "El moderador se ha registrado exitosamente.");
            idField.clear();
            correoField.clear();
            contrasenaModerador.clear();
            contrasenaRModerador.clear();} else if (registrado==4){
            showAlert("Error", "No se pudo registrar al moderador:El correo no es valido.");
        }else showAlert("Error", "No se pudo registrar al moderador.");
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
