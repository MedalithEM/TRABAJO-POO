package cineplanet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        URL archivoFXML = Main.class.getResource("/vista-productos.fxml");

        System.out.println("FXML encontrado: " + archivoFXML);

        if (archivoFXML == null) {
            System.out.println("NO SE ENCONTRO EL FXML");
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(archivoFXML);

        Scene scene = new Scene(
                fxmlLoader.load(),
                900,
                600
        );

        stage.setTitle("Cineplanet - Gestión de Productos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
