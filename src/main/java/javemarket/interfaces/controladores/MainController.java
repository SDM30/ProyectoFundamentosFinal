package javemarket.interfaces.controladores;

import javemarket.interfaces.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Button compradorButton;

    @FXML
    private Button vendedorButton;

    @FXML
    private Button moderadorButton;

    @FXML
    private Button duenoButton;

    @FXML
    private void showRegistrarComprador() {
        SceneManager.changeScene("/vistas/Registrar_Comprador.fxml");
    }

    @FXML
    private void showRegistrarVendedor() {
        SceneManager.changeScene("/vistas/interfazFundamentos.fxml");
    }
    @FXML
    private void showRegistrarDueno() { SceneManager.changeScene("/vistas/Registrar_Dueno.fxml");}
    @FXML
    private void showRegistrarModerador() {
        SceneManager.changeScene("/vistas/Registrar_Moderador.fxml");
    }

    @FXML
    private void showInicioSesion() { SceneManager.changeScene("/vistas/Iniciosesion.fxml"); }
}