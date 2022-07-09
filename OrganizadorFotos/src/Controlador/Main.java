package Controlador;

import static Controlador.VentanaController.leerAlbums;
import static Controlador.VentanaController.leerFotos;
import Modelo.CircularDoubleLinkedList;
import Modelo.Foto;
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

    static CircularDoubleLinkedList<Foto> albums;

    
    public static void main(String[] args) {
        leerAlbums();
        leerFotos();
        launch();
       
           
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/Ventana.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Albums");
        stage.setScene(scene);
        
        stage.show();
    }
    
}
