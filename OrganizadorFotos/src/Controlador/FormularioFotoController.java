/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Dereck Enríquez
 */
public class FormularioFotoController implements Initializable {

    @FXML
    private TextField txtNombreFoto;
    @FXML
    private TextField txtDescripcionFoto;
    @FXML
    private TextField txtLugarFoto;
    @FXML
    private DatePicker txtDateFoto;
    @FXML
    private ComboBox<?> cbAlbum;
    @FXML
    private TextField txtRutaFoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSeleccionarRutaFoto(ActionEvent event) {
    }

    @FXML
    private void btnImportar(ActionEvent event) {
    }

    @FXML
    private void btnSalir(ActionEvent event) {
    }
    
}
