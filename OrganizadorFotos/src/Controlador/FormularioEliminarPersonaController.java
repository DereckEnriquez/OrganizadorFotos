package Controlador;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author jeanf
 */
public class FormularioEliminarPersonaController<E> implements Initializable {

    @FXML
    private ComboBox<E> cmbFotos;
    @FXML
    private ComboBox<E> cmbPersonas;
    
    private  ObservableList<String> fotos;
    private  ObservableList<String> personas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{
            String linea;
            fotos = FXCollections.observableArrayList();
            File file = new File("Biblioteca.txt");
            Scanner scan = new Scanner(file);            
            String fl = "";           
            while(scan.hasNextLine()){
                String temp = "";
                temp = fl.concat(scan.nextLine().split(",")[0]);
                fotos.add(temp);
            }
           cmbFotos.setItems((ObservableList<E>) fotos);
           
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }    

    @FXML
    private void clickcmbFotos(ActionEvent event) {
        String nameFoto = (String) cmbFotos.getValue();
        
        try{

            String linea;
            personas = FXCollections.observableArrayList();
            File file = new File(nameFoto+".txt");
            Scanner scan = new Scanner(file);
            
            String fl = "";
            
            while(scan.hasNextLine()){
                String temp = "";
                temp = fl.concat(scan.nextLine().split(",")[0]);
                personas.add(temp);
                System.out.println(temp);
            }

            cmbPersonas.setItems((ObservableList<E>) personas);

            
        } catch (Exception ex) {
            System.out.println("NO EXISTE");
            //ex.printStackTrace();
        }
        
        
    }
    
}
