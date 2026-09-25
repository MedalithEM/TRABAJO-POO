package upeu.edu.pe.cineplanet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("/vista-producto.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Cineplanet");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
