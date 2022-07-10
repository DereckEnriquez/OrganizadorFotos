package Controlador;

import static Controlador.VentanaController.leerAlbums;
import static Controlador.VentanaController.leerFotos;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Grupo 6
 */
public class Main extends Application {
    
    private static Scene scene;

    public static void main(String[] args) {
        leerAlbums();
        leerFotos();
        launch();

    }
    // Metodos para salatar de escena setRoot
    @Override
    public void start(Stage stage) throws Exception {
        
        scene = new Scene(loadFXML("/Vista/Ventana"));
        stage.setTitle("Albums");
        stage.setScene(scene);
        /*Parent root = FXMLLoader.load(getClass().getResource("/Vista/Ventana.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Albums");
        stage.setScene(scene);*/

        stage.show();
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    public static void setRoot(FXMLLoader fxmlLoader) throws IOException {
        scene.setRoot(fxmlLoader.load());
    }

    public static FXMLLoader loadFXMLload(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }

}
