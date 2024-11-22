package javemarket.interfaces.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javemarket.aplicacion.servicios.ManejarProducto;
import javemarket.aplicacion.servicios.ManejarVendedor;
import javemarket.aplicacion.servicios.UsuarioSesion;
import javemarket.dominio.entidades.Producto;
import javemarket.interfaces.SceneManager;

public class AgregarProductoController {
    @FXML
    TextField nombreField;
    @FXML
    TextArea descField;
    @FXML
    TextField precioField;
    @FXML
    TextField unidadField;

    @FXML
    private void guardarProducto() {
        // Validar que todos los campos estén llenos
        if (nombreField.getText().isEmpty() || descField.getText().isEmpty() ||
                precioField.getText().isEmpty() || unidadField.getText().isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, completa todos los campos antes de guardar el producto.");
            return;
        }

        try {
            // Obtener los valores de los campos
            String nombre = nombreField.getText();
            String descripcion = descField.getText();
            double precio = Double.parseDouble(precioField.getText());
            int unidades = Integer.parseInt(unidadField.getText());

            // Crear una instancia de Producto
            Producto nuevoProducto = new Producto(nombre, descripcion, unidades, precio);
            ManejarVendedor gesVendedor = new ManejarVendedor();
            int id_vendedor = gesVendedor.obtenerIdVendedorPorCorreo(UsuarioSesion.getInstancia().getVendedorActual().getCorreo());
            nuevoProducto.setIdVendedor(id_vendedor);

            // Llamar al servicio para guardar el producto
            ManejarProducto manejarProducto = new ManejarProducto();
            manejarProducto.crearProducto(nuevoProducto);

            // Mostrar mensaje de éxito
            mostrarAlerta("Producto guardado", "El producto se guardó correctamente.");

            // Limpiar los campos
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "Asegúrate de que el precio y las unidades sean valores numéricos.");
        }
    }

    @FXML
    private void irMenuVendedor() {
        SceneManager.changeScene("/vistas/menuVendedor.fxml");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void limpiarCampos() {
        nombreField.clear();
        descField.clear();
        precioField.clear();
        unidadField.clear();
    }
}
