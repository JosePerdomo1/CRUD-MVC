package es.iesvigan.jose.basedatos.controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/es/iesvigan/jose/basedatos/vista/Clientes"), 600, 510);
        stage.setScene(scene);
        stage.setTitle("CRUD PRO");
        // Cargar la imagen del icono
        InputStream iconStream = getClass().getResourceAsStream("/img/icono.png");
        if (iconStream != null) {
            Image iconImage = new Image(iconStream);
            // Establecer la imagen del icono
            stage.getIcons().add(iconImage);
        } else {
            System.out.println("No se pudo cargar la imagen del icono.");
        }

        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
