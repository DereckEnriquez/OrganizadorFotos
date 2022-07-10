package Controlador;

import Modelo.Album;
import Modelo.CircularDoubleLinkedList;
import Modelo.Foto;
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
 * @author Dereck Enríquez
 */
public class FormularioAlbumController implements Initializable {

    @FXML
    private TextField txtNombreAlbum;
    @FXML
    private TextField txtDescripcionAlbum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    
        //CREA UN ALBUM representado en un .txt con su nombre ingresado
    //añade ese album al album.txt que estan todos los nombres de los albums 
    @FXML
    private void btnCrear(ActionEvent event) throws IOException {
        String nombreAlbum = this.txtNombreAlbum.getText();
        String DescripcionAlbum = this.txtDescripcionAlbum.getText();
        System.out.println("clic btn crear album");
        CircularDoubleLinkedList<Foto> album = new CircularDoubleLinkedList(nombreAlbum, DescripcionAlbum);
        
        System.out.println("ALBUM CREADO: " + album.getNombreAlbum());
        System.out.println("Descripcion:" + album.getDescripcion() + "\n");

        FileWriter fichero = null;
        PrintWriter pw = null;
        
        try {
            //Validación Nombre
            System.out.println("Validacion nOMBRE");
            if(Album.validacionNombre(nombreAlbum)){
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
                
            }else{
            
            //escribir un nuevo archivo -> album
            fichero = new FileWriter("src\\Archivos\\"+nombreAlbum+".txt", true);
            pw = new PrintWriter(fichero);
            pw.println(album.getNombreAlbum() + "," + album.getDescripcion() + "," + album);
            
            //escribir en el archivo de albums.txt que solo lleva la base de datos de los albunes creados y su descripcion
            fichero = new FileWriter("src\\Archivos\\albums.txt", true);
            pw = new PrintWriter(fichero);
            pw.println(album.getNombreAlbum() + "," + album.getDescripcion());
            }
        } catch (Exception e) {
            System.out.println(e);;
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                System.out.println(e2);;
            }
        }
        fichero.close();
    }
    
    
@FXML
        private void btnSalir(ActionEvent event) {
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
