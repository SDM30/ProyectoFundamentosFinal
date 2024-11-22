package javemarket.interfaces.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javemarket.aplicacion.servicios.ManejarPedido;
import javemarket.aplicacion.servicios.ManejarProducto;
import javemarket.aplicacion.servicios.UsuarioSesion;
import javemarket.dominio.entidades.Carrito;
import javemarket.dominio.entidades.Pedido;
import javemarket.dominio.entidades.Producto;
import javemarket.interfaces.SceneManager;

import java.time.LocalDateTime;
import java.util.List;

public class menuCompradorController {
    @FXML
    private Spinner<Integer> numUnidadesProducto; // Spinner de enteros
    @FXML
    private ComboBox<String> edificioComboBox;
    @FXML
    private Button agregarBtn;
    @FXML
    private TextArea comentarioField;
    @FXML
    private Button cerrarSesionBtn;
    @FXML
    private Button carroComprasBtn;

    // Tabla
    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto, String> nombreColumn;
    @FXML
    private TableColumn<Producto, String> descColumn;
    @FXML
    private TableColumn<Producto, Integer> undColumn;
    @FXML
    private TableColumn<Producto, Double> precioColumn;

    private final ManejarProducto manejarProducto;

    //Pedido a ser enviado
    private static Pedido pedidoCliente = new Pedido();

    private static Carrito carroCompras = new Carrito();

    public menuCompradorController() {
        this.manejarProducto = new ManejarProducto(); // Instancia del servicio
    }

    @FXML
    public void initialize() {
        // Configuración del Spinner con un rango de 1 a 100 y un valor inicial de 1
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        numUnidadesProducto.setValueFactory(valueFactory);

        // Configurar columnas de la tabla
        configurarColumnasTabla();

        // Llenar la tabla con datos desde ManejarProducto
        cargarDatosTabla();

        // Llama al método para obtener los nombres de los edificios
        List<String> nombresEdificios = ManejarPedido.obtenerNombresEdificios();

        // Inicializa el ComboBox con los datos
        edificioComboBox.getItems().addAll(nombresEdificios);
    }

    private void configurarColumnasTabla() {
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        undColumn.setCellValueFactory(new PropertyValueFactory<>("unidades")); // Propiedad de unidades
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
    }

    private void cargarDatosTabla() {
        // Llama al método obtenerProductos() del servicio ManejarProducto
        List<Producto> productos = manejarProducto.obtenerProductos();

        // Convierte la lista a un ObservableList
        ObservableList<Producto> productosObservable = FXCollections.observableArrayList(productos);

        // Establece los datos en la tabla
        tablaProductos.setItems(productosObservable);
    }

    @FXML
    private void agregarProductoAlCarrito() {
        // Verificar si no se ha seleccionado una ciudad
        if (edificioComboBox.getSelectionModel().isEmpty()) {
            mostrarAlerta("Edificio no seleccionado", "Debes seleccionar un edificio antes de agregar un producto al carrito.");
            return;
        }

        // Verificar si el comentario está vacío
        if (comentarioField.getText().trim().isEmpty()) {
            mostrarAlerta("Comentario vacío", "Debes agregar un comentario antes de agregar un producto al carrito.");
            return;
        }

        // Obtener el producto seleccionado
        Producto productoSeleccionado = tablaProductos.getSelectionModel().getSelectedItem();

        if (productoSeleccionado == null) {
            mostrarAlerta("Selecciona un producto", "Debes seleccionar un producto para agregar al carrito.");
            return;
        }

        // Obtener la cantidad seleccionada del Spinner
        int cantidad = numUnidadesProducto.getValue();

        // Validar que haya suficiente stock
        if (cantidad > productoSeleccionado.getUnidades()) {
            mostrarAlerta("Stock insuficiente", "La cantidad solicitada supera el stock disponible.");
            return;
        }

        // Agregar el producto al carrito
        carroCompras.agregarProducto(productoSeleccionado, cantidad);
        pedidoCliente.setFecha(LocalDateTime.now());
        pedidoCliente.setCliente(UsuarioSesion.getInstancia().getCompradorActual().getCorreo());
        pedidoCliente.setComentario(comentarioField.getText());
        pedidoCliente.setEdificio(edificioComboBox.getValue());

        // Actualizar el stock del producto
        productoSeleccionado.setUnidades(productoSeleccionado.getUnidades() - cantidad);

        // Refrescar la tabla
        tablaProductos.refresh();

        mostrarAlerta("Producto agregado", "El producto se agregó al carrito correctamente.");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    private void irCarritoCompra() {
        SceneManager.changeScene("/vistas/CarritoComprador.fxml");
    }

    @FXML
    private void cerrarSesion() {
        UsuarioSesion.getInstancia().cerrarSesion();
        SceneManager.changeScene("/vistas/Iniciosesion.fxml");
    }

    //getter y setter
    public static Pedido getPedidoCliente() {
        return pedidoCliente;
    }

    public static Carrito getCarroCompras() {
        return carroCompras;
    }
}
