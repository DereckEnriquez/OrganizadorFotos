/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
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
 * @author familia torres vera
 */
public class MostrarFotosAlbumController implements Initializable {

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
        // TODO
        showName();
        mostrar();
    }    

    @FXML
    private void back(MouseEvent event) throws IOException {
         Main.setRoot("/Vista/Ventana");
    }
    private Pane createBookView(String foto) throws FileNotFoundException {
        
        VBox vbox = new VBox();
        FileInputStream f =new FileInputStream(foto);
        final Image image = new Image(f,150, 0, true, false);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(100);
        
        vbox.getChildren().add(imageView);
                        
        Label titleLabel = new Label("imagen");
        titleLabel.setMaxWidth(150);
        vbox.getChildren().add(titleLabel);
        Label yearLabel = new Label("" + "desc ");
        yearLabel.setTextFill(Color.web("#0000FF"));
        yearLabel.setStyle("-fx-font-weight: bold");
        vbox.getChildren().add(yearLabel);
        
        return vbox;
    }
    private void mostrar (){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        try {
            archivo = new File ("tmp.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            linea=br.readLine();
            
            try (InputStream input = Main.class.getResourceAsStream("/Archivos/"+linea+".txt");
                BufferedReader bf = new BufferedReader(new InputStreamReader(input, "UTF-8"))) {
                   String line;
                   while ((line = bf.readLine()) != null) {               
                       String[] p = line.split(",");                
                       Pane bookView = createBookView(p[4]);
                       galeria.getChildren().addAll(bookView);                               
                   }
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
                ex.printStackTrace();
            }//eliminar archivo tmp
            archivo.delete();
      }
      catch(Exception e){
         e.printStackTrace();
      }                
       /* try (InputStream input = Main.class.getResourceAsStream("/Archivos/"+""+".txt");
                BufferedReader bf = new BufferedReader(new InputStreamReader(input, "UTF-8"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {               
                String[] p = linea.split(",");                
                Pane bookView = createBookView(p[4]);
                galeria.getChildren().addAll(bookView);                               
            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }*/
    }
    private void showName(){ 
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        try {
         archivo = new File ("tmp.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         linea=br.readLine();
         album.setText(linea);
         archivo.delete();
      }
      catch(Exception e){
         e.printStackTrace();
      }                
    }
    private String  getNombreAlbum() throws IOException{
        String l = null;
        try (InputStream input = Main.class.getResourceAsStream("/Archivos/tmp.txt");
                BufferedReader bf = new BufferedReader(new InputStreamReader(input, "UTF-8"))) {
            String linea;  
            while ((linea = bf.readLine()) != null) {               
                l=linea;                
            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }       
        return l;
    }    
}
