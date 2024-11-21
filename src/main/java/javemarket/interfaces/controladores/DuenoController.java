package javemarket.interfaces.controladores;

import javemarket.interfaces.SceneManager;
import javemarket.interfaces.vistas.RegistroDueno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DuenoController {

    @FXML private TextField identificador;
    @FXML private TextField documento;
    @FXML private TextField correoDueñoField;
    @FXML private TextField contrasenaDueño;

    private RegistroDueno registroDueno;

    public DuenoController() {
        registroDueno = new RegistroDueno();
    }

    @FXML
    private void registrarDueño(ActionEvent event) {
        int id = Integer.parseInt(identificador.getText());
        int doc = Integer.parseInt(documento.getText());
        String correo = correoDueñoField.getText();
        String contrasena = contrasenaDueño.getText();

        if (id ==0 || doc==0 || correo.isEmpty() || contrasena.isEmpty()) {
            showAlert("Campos vacíos", "Por favor, complete todos los campos.");
            return;
        }

        int registrado = registroDueno.registrar(id, doc, correo, contrasena);

        if (registrado==0) {
            showAlert("Registro Exitoso", "El dueño se ha registrado exitosamente.");
            identificador.clear();
            documento.clear();
            correoDueñoField.clear();
            contrasenaDueño.clear();
        } else if (registrado==4){
            showAlert("Error", "No se pudo registrar al dueño:El correo no es valido.");
        }else showAlert("Error", "No se pudo registrar al dueño.");
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

