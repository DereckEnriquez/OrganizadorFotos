/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.CircularDoubleLinkedList;
import Modelo.Foto;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    private ImageView imageview;

    @FXML
    private void crearalbum(ActionEvent event) {
        System.out.println("clic crear menu");
        CircularDoubleLinkedList<Foto> album = new CircularDoubleLinkedList<>();
        File archivo = exploradorArchivo.showOpenDialog(new Stage());
        Foto foto = new Foto( archivo.getName(),archivo.toPath());
        System.out.println(foto);
        /*Image imagen= new Image(foto.getDireccion().toString());
        album.addFirst(foto);
        imageview.setImage(imagen);
        panel.getChildren().addAll(imageview);*/
        album.addLast(foto);
        System.out.println("***ALBUM CREADO:\n***"+album);
    }

    @FXML
    private void salirmenu(ActionEvent event) {
        System.out.println("clic salir menu");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exploradorArchivo.setInitialDirectory(new File("C:"));
    }

}
