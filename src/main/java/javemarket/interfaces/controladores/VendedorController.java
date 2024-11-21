package javemarket.interfaces.controladores;

import javemarket.interfaces.vistas.RegistroVendedor;
import javemarket.interfaces.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class VendedorController {

    @FXML private TextField nombreVendedorField;
    @FXML private TextField correoVendedorField;
    @FXML private TextField nombreEmprendimientoField;
    @FXML private ComboBox<String> categoriaComboBox;
    @FXML private PasswordField contrasenaVendedor;

    private RegistroVendedor registradorVendedor;

    public VendedorController() {
        registradorVendedor = new RegistroVendedor();
    }

    @FXML
    private void registrarVendedor(ActionEvent event) {
        String nombre = nombreVendedorField.getText();
        String correo = correoVendedorField.getText();
        String nombreEmprendimiento = nombreEmprendimientoField.getText();
        String categoria = categoriaComboBox.getValue();
        String contrasena = contrasenaVendedor.getText();

        if (nombre.isEmpty() || correo.isEmpty() || nombreEmprendimiento.isEmpty() || categoria == null || contrasena.isEmpty()) {
            showAlert("Campos vacíos", "Por favor, complete todos los campos.");
            return;
        }

        int registrado = registradorVendedor.registrar(nombre, correo, nombreEmprendimiento, categoria, contrasena);

        if (registrado==0) {
            showAlert("Registro Exitoso", "El vendedor se ha registrado exitosamente.");
            nombreVendedorField.clear();
            correoVendedorField.clear();
            nombreEmprendimientoField.clear();
            categoriaComboBox.getSelectionModel().clearSelection();
            contrasenaVendedor.clear();
        } else if (registrado==4){
            showAlert("Error", "No se pudo registrar al vendedor:El correo no es valido.");
        }else showAlert("Error", "No se pudo registrar al vendedor.");
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
