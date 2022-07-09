/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author jeanf
 */
public class VAlbumController <E> implements Initializable {

    @FXML
    private ListView<E> listAlbum;
    @FXML
    private Button btnAnadir;
    @FXML
    private Button btnElim;
    @FXML
    private Button btnHome;
    @FXML
    private Label lblnoti;
    @FXML
    private TextField txtinputAlbum;

    private  ObservableList<E> lvalbums;
    /**
     * Initializes the controller class.
     * cellFactoryProperty() : Indica a ListView que comience a editar el elemento en el índice dado, si ListView es editable.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       lvalbums = (ObservableList<E>) FXCollections.observableArrayList(
                "Album1","Album2", "Album3","Album4","Album5");
        listAlbum.setItems(lvalbums);
        
        lvalbums.addListener(new ListChangeListener(){  //Cuando el ListView es modificado se notificara con el label
            @Override
            public void onChanged(ListChangeListener.Change c) {
                lblnoti.setText("Lista modificada exitosamente");
            }
        });                
    }    
    
    
    @FXML
    private void clickLista(MouseEvent event) {
    }


    @FXML
    private void agregarAlbum(ActionEvent event) {
        if(!txtinputAlbum.getText().isEmpty()){
            
            if(txtinputAlbum.getText().contains(",")){
                lblnoti.setText("Please use AddAll button to add 2+Items");
            }else{lvalbums.add((E) txtinputAlbum.getText());}
        }else{lblnoti.setText("Ingrese nombre del Album");}
        
        listAlbum.getSelectionModel().clearSelection();
    }

    @FXML
    private void eliminarAlbum(ActionEvent event) {
    }
    
}
