package Controlador;

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
        File file=new File("src\\Archivos\\photos2.txt");
        //System.out.println(file);
        try{
            file.createNewFile();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        launch(args);
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
