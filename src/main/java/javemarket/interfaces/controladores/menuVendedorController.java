package javemarket.interfaces.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javemarket.aplicacion.servicios.ManejarPedido;
import javemarket.aplicacion.servicios.UsuarioSesion;
import javemarket.dominio.entidades.Pedido;
import javemarket.interfaces.SceneManager;

import java.time.LocalDateTime;
import java.util.List;

public class menuVendedorController {
    @FXML
    private TableView<Pedido> tablaPedidos;
    @FXML
    private TableColumn<Pedido, Integer> idColumn;
    @FXML
    private TableColumn<Pedido, LocalDateTime> fechaColumn;
    @FXML
    private TableColumn<Pedido, String> clienteColumn;
    @FXML
    private TableColumn<Pedido, Double> totalColumn;
    @FXML
    private TableColumn<Pedido, String> comentarioColumn;
    @FXML
    private TableColumn<Pedido, String> edificioColumn;

    private final ManejarPedido manejarPedido;

    public menuVendedorController() {
        manejarPedido = new ManejarPedido();
    }

    @FXML
    public void initialize() {
        // Configurar las columnas de la tabla
        configurarColumnasTabla();

        // Cargar los pedidos en la tabla
        cargarDatosTabla();
    }

    private void configurarColumnasTabla() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idPedido"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        clienteColumn.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        comentarioColumn.setCellValueFactory(new PropertyValueFactory<>("comentario"));
        edificioColumn.setCellValueFactory(new PropertyValueFactory<>("edificio"));
    }

    private void cargarDatosTabla() {
        // Obtener los pedidos desde el servicio
        List<Pedido> pedidos = manejarPedido.obtenerPedidos();

        // Convertir la lista en un ObservableList
        ObservableList<Pedido> pedidosObservable = FXCollections.observableArrayList(pedidos);

        // Establecer los datos en la tabla
        tablaPedidos.setItems(pedidosObservable);
    }

    @FXML
    private void cerrarSesion() {
        UsuarioSesion.getInstancia().cerrarSesion();
        SceneManager.changeScene("/vistas/Iniciosesion.fxml");
    }

    @FXML
    private void irAgregarProducto() {
        SceneManager.changeScene("/vistas/AgregarProducto.fxml");
    }
}
