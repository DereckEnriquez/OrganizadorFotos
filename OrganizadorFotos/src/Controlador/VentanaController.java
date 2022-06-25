package Controlador;

import Modelo.CircularDoubleLinkedList;
import Modelo.Foto;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Dereck Enríquez
 */
public class VentanaController implements Initializable {

    FileChooser exploradorArchivo = new FileChooser();
    @FXML
    private MenuBar menubar;

    @FXML
    private Pane panel;

    @FXML
    private MenuItem btnImportar;

    Stage stage = new Stage();
    @FXML
    private HBox hbGaleria;
    @FXML
    private VBox vbTitulos;

    @FXML
    private void crearalbum(ActionEvent event) {
        System.out.println("clic crear menu");

        String nombre = JOptionPane.showInputDialog("Nombre del Album:");

        JOptionPane.showMessageDialog(null, "Album " + nombre + " creado con exito!");
        CircularDoubleLinkedList<Foto> album = new CircularDoubleLinkedList(nombre);
        System.out.println("ALBUM CREADO: " + album.getNombreAlbum());
        

    }

    @FXML
    private void salirmenu(ActionEvent event) {
        System.out.println("clic salir menu");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exploradorArchivo.setInitialDirectory(new File("C:"));

    }

    @FXML
    private void importarFoto() {
        btnImportar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Buscar Imagen");

                // Agregar filtros para facilitar la busqueda
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("All Images", "*.*"),
                        new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                        new FileChooser.ExtensionFilter("PNG", "*.png")
                );

                // Obtener la imagen seleccionada
                //File imgFile = fileChooser.showOpenDialog(stage);
                //CircularDoubleLinkedList<Foto> list = (CircularDoubleLinkedList<Foto>) fileChooser.showOpenMultipleDialog(stage);
                List<File> list = fileChooser.showOpenMultipleDialog(stage);
                // Mostar la imagen
                for (int i = 0; i < list.size(); i++) {
                    if (list != null) {
                        Image image = new Image("file:" + list.get(i).getAbsolutePath());

                    }
                }
            }
        });
    }

}
