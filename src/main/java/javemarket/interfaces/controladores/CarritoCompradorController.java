package javemarket.interfaces.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javemarket.aplicacion.servicios.ManejarPedido;
import javemarket.dominio.entidades.Carrito;
import javemarket.dominio.entidades.Pedido;
import javemarket.dominio.entidades.Producto;
import javemarket.interfaces.SceneManager;
import javemarket.interfaces.controladores.menuCompradorController;
import javemarket.interfaces.controladores.CorreoService;

import java.util.Map;

public class CarritoCompradorController {

    // Tabla de productos
    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto, String> productoColumn;
    @FXML
    private TableColumn<Producto, Integer> undColumn;
    @FXML
    private TableColumn<Producto, Double> valorUnidadColumn;
    @FXML
    private TableColumn<Producto, Double> totalProductoColumn;
    @FXML
    private Label totalPedidoLabel;


    @FXML
    public void initialize() {
        // Configurar columnas de la tabla
        configurarColumnasTabla();

        // Cargar datos del carrito compartido en la tabla
        cargarDatosTabla();

        // Mostrar el total inicial del pedido
        actualizarTotalPedido();
    }

    private void configurarColumnasTabla() {
        productoColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        undColumn.setCellValueFactory(new PropertyValueFactory<>("unidades"));
        valorUnidadColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        totalProductoColumn.setCellValueFactory(celda -> {
            Producto producto = celda.getValue();
            double total = producto.getPrecio() * menuCompradorController.getCarroCompras().getProductos().get(producto);
            return new javafx.beans.property.SimpleDoubleProperty(total).asObject();
        });
    }

    private void cargarDatosTabla() {
        ObservableList<Producto> productosObservable = FXCollections.observableArrayList();

        // Obtener los productos del carrito compartido
        Carrito carritoCompras = menuCompradorController.getCarroCompras();

        for (Map.Entry<Producto, Integer> entry : carritoCompras.getProductos().entrySet()) {
            Producto producto = entry.getKey();
            producto.setUnidades(entry.getValue());
            productosObservable.add(producto);
        }

        // Establecer datos en la tabla
        tablaProductos.setItems(productosObservable);
    }

    @FXML
    private void irMenuComprador() {
        SceneManager.changeScene("/vistas/menuComprador.fxml");
    }

    @FXML
    private void eliminarProducto() {
        // Obtener el producto seleccionado
        Producto productoSeleccionado = tablaProductos.getSelectionModel().getSelectedItem();

        if (productoSeleccionado == null) {
            mostrarAlerta("Producto no seleccionado", "Por favor selecciona un producto para eliminar del carrito.");
            return;
        }

        // Eliminar producto del carrito compartido
        menuCompradorController.getCarroCompras().eliminarProducto(productoSeleccionado);

        // Recargar tabla
        cargarDatosTabla();

        mostrarAlerta("Producto eliminado", "El producto fue eliminado correctamente del carrito.");
    }

    @FXML
    private void enviarPedido() {

        CorreoService correoService = new CorreoService();
        Pedido pedidoCliente = menuCompradorController.getPedidoCliente();
        Carrito carritoCompras = menuCompradorController.getCarroCompras();

        // Validar que el carrito no esté vacío
        if (carritoCompras.getProductos().isEmpty()) {
            mostrarAlerta("Carrito vacío", "No hay productos en el carrito para enviar el pedido.");
            return;
        }

        // Validar que los datos del pedido estén completos
        if (pedidoCliente.getCliente() == null || pedidoCliente.getEdificio() == null || pedidoCliente.getComentario() == null) {
            mostrarAlerta("Datos incompletos", "Por favor completa todos los datos del pedido antes de enviarlo.");
            return;
        }
        actualizarTotalPedido();
        menuCompradorController.getPedidoCliente().setTotal(menuCompradorController.getCarroCompras().getTotal());
        ManejarPedido manejarPedido = new ManejarPedido();
        manejarPedido.crearPedido(pedidoCliente);

        // Notificar al cliente por correo
        String email = pedidoCliente.getCliente(); // Obtén el correo del cliente
        String asunto = "Tu pedido está listo";
        String mensaje = "Hola " + pedidoCliente.getCliente()+ ",\n\n"
                + "Tu pedido ha sido enviado exitosamente.\n"
                + "Total del pedido: $" + String.format("%.2f", pedidoCliente.getTotal()) + "\n\n"
                + "¡Gracias por comprar con nosotros!";

        notificar(correoService, email, asunto, mensaje);

        mostrarAlerta("Pedido enviado", "Tu pedido ha sido enviado exitosamente.");

        // Limpiar el carrito y recargar la tabla
        carritoCompras.limpiarCarrito();
        cargarDatosTabla();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void actualizarTotalPedido() {
        // Recalcular el total del carrito
        menuCompradorController.getCarroCompras().recalcularTotal();

        // Obtener el total del carrito y mostrarlo en la etiqueta
        double total = menuCompradorController.getCarroCompras().getTotal();
        totalPedidoLabel.setText(String.format("Total: $%.2f", total));
    }
    private void notificar(CorreoService correoService,String email, String asunto, String mensaje) {
        try {
            correoService.enviarCorreo(email, asunto, mensaje);
            mostrarAlerta("Correo enviado", "Se ha enviado una notificación al cliente.");
        } catch (Exception e) {
            mostrarAlerta("Error al enviar correo", "No se pudo enviar el correo. Por favor, intenta nuevamente.");
            e.printStackTrace();
        }
    }

}