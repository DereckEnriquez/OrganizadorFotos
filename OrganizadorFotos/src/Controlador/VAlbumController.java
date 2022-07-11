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
 * @author G6
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
  
    @FXML
    private Button btnaddAll;
    
    private  ObservableList<E> lvalbums;
    @FXML
    private Button btnModificar;
    
    /**
     * Initializes the controller class.
     * cellFactoryProperty() : Indica a ListView que comience a editar el elemento en el índice dado, si ListView es editable.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lvalbums = (ObservableList<E>) FXCollections.observableArrayList("Album1","Album2", "Album3","Album4","Album5");
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
        System.out.println(listAlbum.getSelectionModel().getSelectedIndex());
        System.out.println(listAlbum.getSelectionModel().getSelectedItem()); 
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
        if(!listAlbum.getSelectionModel().isEmpty()){
            if(listAlbum.getSelectionModel().getSelectedItems().size() >1){
               lblnoti.setText("Por favor use removeAll para eliminar más de un item");
            }else{
                lvalbums.remove(listAlbum.getSelectionModel().getSelectedItem());
            }
        }else{
               lblnoti.setText("Selecciono un elemento de la Lista");
        }
        listAlbum.getSelectionModel().clearSelection();
        
        
    }

    @FXML
    private void addAll(ActionEvent event) {
        if(!txtinputAlbum.getText().isEmpty()){
            if(txtinputAlbum.getText().contains(",")){
                //lvalbums.add((E) txtinputAlbum.getText().split(",") );
                ObservableList<String> olTemp = FXCollections.observableArrayList(txtinputAlbum.getText().split(","));
                lvalbums.addAll((E)olTemp);   
            }else{
                lblnoti.setText(" Separar los nombres por comas(,) ");
            }
        }else{
            lblnoti.setText("Porfavor ingrese nombre de los albums");
        }
        listAlbum.getSelectionModel().clearSelection();
    }

    @FXML
    private void modificarAlbum(ActionEvent event) {
        //listAlbum (listlist)
        //lvalbums  ()
        if(listAlbum.getSelectionModel().isEmpty() || txtinputAlbum.getText().isEmpty()){
            lblnoti.setText("Por favor agregar el nuevo nombre del Albúm");
        }else{
            if(!(listAlbum.getSelectionModel().getSelectedItems().size()>1)){
                lvalbums.set( listAlbum.getSelectionModel().getSelectedIndex(),(E) txtinputAlbum.getText());
                listAlbum.getSelectionModel().clearSelection();
            }else{
                lblnoti.setText("Usar el boron para ReplaceAll");
            }
        }
    }
}
