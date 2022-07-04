package Controlador;

import static Modelo.Foto.cargarFotos;
import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jeanf
 */
public class Main extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
        //cargarFotos();
           
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/Ventana.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("BIENVENIDO");
        stage.setScene(scene);
        stage.show();
    }
    
}
