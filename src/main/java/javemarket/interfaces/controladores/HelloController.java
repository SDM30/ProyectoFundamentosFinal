package javemarket.interfaces.controladores;

import javemarket.infraestructura.persistencia.ConexionBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class HelloController {
    @FXML
    private TextField nombreVendedorField;
    @FXML
    private TextField correoVendedorField;
    @FXML
    private TextField nombreEmprendimientoField;
    @FXML
    private ComboBox<String> categoriaComboBox;
    @FXML
    private TextField contrasenaVendedor;
    @FXML
    private TextField nombreCompradorField;
    @FXML
    private TextField correoCompradorField;
    @FXML
    private TextField contrasenaCompradorField;
    @FXML
    private ComboBox<String> preferenciaComboBox;
    @FXML
    private TextField identificadorModerador;
    @FXML
    private TextField correoModerador;
    @FXML
    private TextField contrasenaModerador;
    @FXML
    private TextField contrasenaRepetirModerador;
    @FXML
    private TextField identificadorDueno;
    @FXML
    private TextField documentoDueno;
    @FXML
    private TextField correoDueno;
    @FXML
    private TextField contrasenaDueno;

    private ConexionBase dbConnection;

    public HelloController() {
        dbConnection = new ConexionBase();  // Inicializamos la conexión
        if (dbConnection.getConnection() == null) {
            showAlert("Error de conexión", "No se pudo establecer conexión con la base de datos.");
        }
    }

    @FXML
    private void registrarComprador() {
        String nombre = nombreCompradorField.getText();
        String correo = correoCompradorField.getText();
        String contrasena = contrasenaCompradorField.getText();
        String preferencia = preferenciaComboBox.getValue();

        if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || preferencia == null) {
            showAlert("Campos vacíos", "Por favor, complete todos los campos.");
            return;
        }

        try {
            // Insertar los datos en la base de datos
            String query = "INSERT INTO compradores (nombre, correo, contrasena, categoria_preferida) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, correo);
            statement.setString(3, contrasena);
            statement.setString(4, preferencia);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                showAlert("Registro Exitoso", "El comprador se ha registrado exitosamente.");
            } else {
                showAlert("Error", "No se pudo registrar al comprador.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Hubo un problema al guardar los datos.");
        }
    }

    @FXML
    private void registrarVendedor() {
        String nombre = nombreVendedorField.getText();
        String correo = correoVendedorField.getText();
        String nombreEmprendimiento = nombreEmprendimientoField.getText();
        String categoria = categoriaComboBox.getValue();
        String contrasena = contrasenaVendedor.getText();

        if (nombre.isEmpty() || correo.isEmpty() || nombreEmprendimiento.isEmpty() || categoria == null || contrasena.isEmpty()) {
            showAlert("Campos vacíos", "Por favor, complete todos los campos.");
            return;
        }

        try {
            // Insertar los datos en la base de datos
            String query = "INSERT INTO vendedores (nombre, correo, contrasena, nombre_emprendimiento, categoria) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, correo);
            statement.setString(3, contrasena);
            statement.setString(4, nombreEmprendimiento);
            statement.setString(5, categoria);


            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                showAlert("Registro Exitoso", "El vendedor se ha registrado exitosamente.");
            } else {
                showAlert("Error", "No se pudo registrar al vendedor.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Hubo un problema al guardar los datos.");
        }
    }

    @FXML
    private void registrarModerador() {
        int id = Integer.parseInt(identificadorModerador.getText());
        String correo = correoModerador.getText();
        String contrasena = contrasenaModerador.getText();
        String contrasenaRepetir = contrasenaRepetirModerador.getText();

        if (id==0 || correo.isEmpty() || contrasena.isEmpty() || contrasenaRepetir.isEmpty()) {
            showAlert("Campos vacíos", "Porfavor, complete todos los campos.");
            return;
        }

        try {
            // Insertar los datos en la base de datos
            String query = "INSERT INTO moderadores (id, correo, contrasena, constrasenaRepetir) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
            statement.setString(1, String.valueOf(id));
            statement.setString(2, correo);
            statement.setString(3, contrasena);
            statement.setString(4, contrasenaRepetir);


            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                showAlert("Registro Exitoso", "El moderador se ha registrado exitosamente.");
            } else {
                showAlert("Error", "No se pudo registrar al moderador.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Hubo un problema al guardar los datos.");
        }
    }

    @FXML
    private void registrarDueno() {
        int idDueno = Integer.parseInt(identificadorDueno.getText());
        int documento = Integer.parseInt(documentoDueno.getText());
        String correo = correoDueno.getText();
        String contrasena = contrasenaDueno.getText();

        if (idDueno==0|| documento==0|| correo.isEmpty() || contrasena.isEmpty()) {
            showAlert("Campos vacíos", "Por favor, complete todos los campos.");
            return;
        }

        try {
            // Insertar los datos en la base de datos
            String query = "INSERT INTO duenos (idDueno, documento, correo, contrasena) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
            statement.setString(1, String.valueOf(idDueno));
            statement.setString(2, String.valueOf(documento));
            statement.setString(3, correo);
            statement.setString(4, contrasena);


            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                showAlert("Registro Exitoso", "El dueño se ha registrado exitosamente.");
            } else {
                showAlert("Error", "No se pudo registrar al dueño.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Hubo un problema al guardar los datos.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    public void showRegistrarDueno(ActionEvent event) {
        showScene(event, "Registrar_Dueno.fxml");
    }

    public void showRegistrarModerador(ActionEvent event){
        showScene(event, "Registrar_Moderador");
    }

    public void showRegistrarComprador(ActionEvent event) {
        showScene(event, "Registrar_Comprador.fxml");
    }

    public void showRegistrarVendedor(ActionEvent event) {
        showScene(event, "InterfazFundamentos.fxml");
    }

    public void volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vistas/hello-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Obtener el Stage actual desde el evento
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showScene(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            // Obtener el Stage actual desde el evento
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            currentStage.setScene(scene);
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
