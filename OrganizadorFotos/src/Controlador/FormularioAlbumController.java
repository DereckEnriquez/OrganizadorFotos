package Controlador;

import Modelo.Album;
import Modelo.CircularDoubleLinkedList;
import Modelo.Foto;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

    @FXML
    private void btnCrear(ActionEvent event) {
        String nombreAlbum = this.txtNombreAlbum.getText();
        String DescripcionAlbum = this.txtDescripcionAlbum.getText();
        System.out.println("clic btn crear album");
        CircularDoubleLinkedList<Foto> album = new CircularDoubleLinkedList(nombreAlbum, DescripcionAlbum);
        System.out.println("ALBUM CREADO: " + album.getNombreAlbum());
        System.out.println("Descripcion:" + album.getDescripcion() + "\n");

        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src\\Archivos\\albums.txt", true);
            pw = new PrintWriter(fichero);
            pw.println(album.getNombreAlbum() + "," + album.getDescripcion() + "," + album);
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
        //vbTitulos.getChildren().add(new Text(album.getNombreAlbum()));

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
