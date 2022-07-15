package Controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author G6
 */
public class MostrarFotosAlbumController implements Initializable {
    
    public String namee = null;

    @FXML
    private TilePane galeria;
    @FXML
    private Label album;
    @FXML
    private Button code;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showName();
        mostrar();
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        Main.setRoot("/Vista/Ventana");
    }

    private Pane createBookView(String titulo, String descripcion, String foto) throws FileNotFoundException {
        VBox vbox = new VBox();
        FileInputStream f = new FileInputStream(foto);
        final Image image = new Image(f, 150, 0, true, false);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(100);
        vbox.getChildren().add(imageView);
        Label titleLabel = new Label(titulo);
        titleLabel.setMaxWidth(150);
        vbox.getChildren().add(titleLabel);
        
        imageView.setOnMouseClicked(event -> {
                    namee = titleLabel.getText();                    
                    try {
                        Main.setRoot("/Vista/MostrarFotos");
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
        
        titleLabel.setOnMouseClicked(event -> {
                    namee = titleLabel.getText();                    
                    try {
                        Main.setRoot("/Vista/MostrarFotos");
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });                
        
        Label descrip = new Label(descripcion);
        descrip.setTextFill(Color.web("#0000FF"));
        descrip.setStyle("-fx-font-weight: bold");
        vbox.getChildren().add(descrip);
        return vbox;
    }

    private void mostrar() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File("tmp.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String linea;
            linea = br.readLine();
            File archivo2 = null;
            FileReader fr2 = null;
            BufferedReader br2 = null;
            try {
                archivo2 = new File(linea + ".txt");
                fr2 = new FileReader(archivo2);
                br2 = new BufferedReader(fr2);
                String line;
                while ((line = br2.readLine()) != null) {
                    String[] p = line.split(",");
                    Pane bookView = createBookView(p[0], p[1], p[4]);
                    galeria.getChildren().addAll(bookView);
                }
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
                ex.printStackTrace();
            }//eliminar archivo tmp
            archivo.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // metodo para mostrar el nombre del album
    private void showName() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File("tmp.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String linea;
            linea = br.readLine();
            album.setText(linea);
            archivo.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
