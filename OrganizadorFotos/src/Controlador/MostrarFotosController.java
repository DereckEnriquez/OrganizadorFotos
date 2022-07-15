/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.CircularDoubleLinkedList;
import Modelo.Foto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author familia torres vera
 */
public class MostrarFotosController implements Initializable {

    @FXML
    private Text nombreAlbum;
    @FXML
    private Button back;
    @FXML
    private TilePane foto;
    
    //public CircularDoubleLinkedList<Foto> album = new CircularDoubleLinkedList<Foto>();
    private int n=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /*File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File("tmp.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String linea;
            linea = br.readLine();
            
            File archivo2 = null;
            FileReader fr2 = null;
            BufferedReader br2 = null;
            try {
                archivo2 = new File(linea +".txt");
                fr2 = new FileReader(archivo2);
                br2 = new BufferedReader(fr2);
                String line;
                while ((line = br2.readLine()) != null) {
                    String[] p = line.split(",");
                    Foto f = new Foto(p[0], p[1],p[2],p[4]);
                    album.addLast(f);                    
                }
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
                ex.printStackTrace();
            }//eliminar archivo tmp
            archivo.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }*/            
        //showName();
        mostrarFoto(0); 
        
          
        
        
    }    

    @FXML
    private void anterior(MouseEvent event) {
        //mostrarFoto(0);   
    }

    @FXML
    private void sigiuiente(MouseEvent event) {  
        //mostrarFoto(0); 
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        Main.setRoot("/Vista/MostrarFotosAlbum");
    }
    
    private void showName() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File("tmp.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String linea;
            linea = br.readLine();
            nombreAlbum.setText(linea);
            //archivo.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void mostrarFoto(int i){
        CircularDoubleLinkedList<Foto> al = new CircularDoubleLinkedList<Foto>();
        //ListIterator<Foto> lit = al.listIterator();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File("tmp.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String linea;
            linea = br.readLine();
            
            File archivo2 = null;
            FileReader fr2 = null;
            BufferedReader br2 = null;
            try {
                archivo2 = new File(linea +".txt");
                fr2 = new FileReader(archivo2);
                br2 = new BufferedReader(fr2);
                String line;
                while ((line = br2.readLine()) != null) {
                line = br2.readLine();
                    String[] p = line.split(",");
                    Foto fot = new Foto(p[4]);
                    //lit.add(fot);
                    al.addFirst(fot);
                    //fot.setRuta(p[4]);
                    //String ruta = al.get(1).getRuta();
                    //Foto f = new Foto(p[0], p[1],p[2],p[4]);
                    //album.addLast(fot);
                        /*try{    
                            Pane bookView = createBookView(ruta);                            
                            foto.getChildren().addAll(bookView);
            
                        }catch(FileNotFoundException e){
                        } */                                                        
                }
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
                ex.printStackTrace();
            }
            
            //archivo.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
                Pane bookView = createBookView(al.get(0).getRuta());
                foto.getChildren().addAll(bookView);
            
            }catch(FileNotFoundException e){
            }
        
        //foto.getChildren().clear();
               
        //System.out.println(album.getFirst().getRuta());
        
        
    }
    
    private Pane createBookView(String foto) throws FileNotFoundException {
        
        VBox vbox = new VBox();
        FileInputStream f = new FileInputStream(foto);        
        Image image = new Image(f, 150, 0, true, false);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(499);
        imageView.setFitHeight(384);
        vbox.getChildren().add(imageView);                                                   
        return vbox;
    }
}
