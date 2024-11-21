package javemarket.interfaces;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javemarket.interfaces.SceneManager;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.setStage(stage); // Configurar el Stage principal

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 402);
        stage.setTitle("JaveMarket");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}