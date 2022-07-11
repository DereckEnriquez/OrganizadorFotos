package Controlador;

import Modelo.Album;
import Modelo.CircularDoubleLinkedList;
import Modelo.Foto;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * @author G6
 */
public class FormularioAlbumController implements Initializable {

    @FXML
    private TextField txtNombreAlbum;
    @FXML
    private TextField txtDescripcionAlbum;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    //CREA UN ALBUM representado en un .txt con su nombre ingresado
    //añade ese album al album.txt que estan todos los nombres de los albums 
    @FXML
    private void btnCrear(ActionEvent event) throws IOException {
        String nombreAlbum = this.txtNombreAlbum.getText();
        String DescripcionAlbum = this.txtDescripcionAlbum.getText();
        System.out.println("clic btn crear album");
        CircularDoubleLinkedList<Foto> album = new CircularDoubleLinkedList(nombreAlbum, DescripcionAlbum);

        try {
            //Validación Nombre
            System.out.println("Validacion nOMBRE");
            if (Album.validacionNombre(nombreAlbum)) {
                System.out.println("YA EXISTE ESTE NOMBRE");
                Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setTitle("Error");
                mensaje.setContentText("Nombre Existente");
                Label label = new Label("El error es el siguiente:");
                TextArea txtArea = new TextArea("El nombre del Album ya existe, debe colocar otro");
                txtArea.setEditable(false);
                txtArea.setWrapText(true);
                txtArea.setMaxWidth(Double.MAX_VALUE);
                txtArea.setMaxHeight(Double.MAX_VALUE);
                GridPane.setVgrow(txtArea, Priority.ALWAYS);
                GridPane.setHgrow(txtArea, Priority.ALWAYS);
                GridPane contenido = new GridPane();
                contenido.setMaxWidth(Double.MAX_VALUE);
                contenido.add(label, 0, 0);
                contenido.add(txtArea, 0, 1);
                mensaje.getDialogPane().setExpandableContent(contenido);
                mensaje.showAndWait();

            } else {
                //escribir un nuevo archivo -> album
                try {
                    String ruta = ".txt";
                    File file = new File(nombreAlbum + ruta);
                    // Si el archivo no existe es creado
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

//escribir en el archivo de albunes.txt que solo lleva la base de datos de los albunes creados y su descripcion
                try {
                    String ruta = "albunes.txt";
                    File file = new File(ruta);
                    FileWriter fw = new FileWriter(file, true);
                    PrintWriter l = new PrintWriter(fw);
                    l.println(album.getNombreAlbum() + "," + album.getDescripcion());
                    l.close();
                    fw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void btnSalir(ActionEvent event) {
        try {
            Main.setRoot("/Vista/Ventana");
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Error");
            a.show();
        }
    }

    public TextField getTxtNombreAlbum() {
        return txtNombreAlbum;
    }

    public void setTxtNombreAlbum(TextField txtNombreAlbum) {
        this.txtNombreAlbum = txtNombreAlbum;
    }

    public TextField getTxtDescripcionAlbum() {
        return txtDescripcionAlbum;
    }

    public void setTxtDescripcionAlbum(TextField txtDescripcionAlbum) {
        this.txtDescripcionAlbum = txtDescripcionAlbum;
    }

}
