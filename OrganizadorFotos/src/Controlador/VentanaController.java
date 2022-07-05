package Controlador;

import Modelo.CircularDoubleLinkedList;
import Modelo.Foto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    private MenuItem btnImportar;

    Stage stage = new Stage();
    @FXML
    private HBox hbGaleria;
    @FXML
    private VBox vbTitulos;
    @FXML
    private TextField jLDireccion;
    @FXML
    private Label jLDireccion2;
    private ImageView Icon;
    @FXML
    private ImageView foto1;
    
    static CircularDoubleLinkedList<Foto> fotos = Foto.leerFotos();
    @FXML
    private void crearalbum(ActionEvent event) {
        System.out.println("clic crear menu");
        String nombre = JOptionPane.showInputDialog("Nombre del Album:");
        String descripcion=JOptionPane.showInputDialog("Descripción del Album "+nombre+":"); 
        JOptionPane.showMessageDialog(null, "Album " + nombre + " creado con exito!" +"\ncon la descripción:"+descripcion);
        CircularDoubleLinkedList<Foto> album = new CircularDoubleLinkedList(nombre,descripcion);
        System.out.println("ALBUM CREADO: " + album.getNombreAlbum() + "\n");
        System.out.println("Descripcion:"+album.getDescripcion()+"\n");

        FileWriter fichero = null;
        PrintWriter pw = null;
        EscribirAlbumTxt(fichero, pw, album);

    }

    @FXML
    private void salirmenu(ActionEvent event) {
        System.out.println("clic salir menu");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exploradorArchivo.setInitialDirectory(new File("C:"));
        
        
        for (Foto ca : fotos) {
            InputStream input = null;
            try {
                //elementos graficos de la escena
                input = App.class.getResource(ca.getRutaImagen()).openStream();
                Image imagen = new Image(input, 75, 75, true, true);
                ImageView imgview = new ImageView(imagen);
                //creacion de una casa generica
                StackPane st = new StackPane();
                st.getChildren().addAll(imgview);
                //agregar el stackpane al PanelCasas
                PanelCasas.getChildren().addAll(st);

                //fijar la ubicacion del st en la ubicacion del casa
                st.setLayoutX(ca.getUbicacion().getX());
                st.setLayoutY(ca.getUbicacion().getY());
                //evento que al pasar el mouse muestra la informacion de la casa deseada
            
        }
    }
        
    }

    File archivo;
    @FXML
    private void importarFoto() {
        btnImportar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int resultado;
                buscarIMG Buscador = new buscarIMG();
                FileNameExtensionFilter formato = new FileNameExtensionFilter("JPG","PNG","GIF","jpg","png","gif");
                
                Buscador.JFCImg.setFileFilter(formato);
                resultado = Buscador.JFCImg.showOpenDialog(null);   //si damos abrir tomara el valor de 1 y si damos cancelar 0
                
                if(JFileChooser.APPROVE_OPTION == resultado){
                    archivo =  Buscador.JFCImg.getSelectedFile();//Información del archivo
                    
                    jLDireccion.setText(archivo.getAbsolutePath());                          
                                                      
                try{
                    //ImageIcon ImgIcon = new ImageIcon(archivo.toString());
                    System.out.println("Entro al try");
                    System.out.println(archivo.getAbsolutePath());
                    
                    Image image = new Image("foto1.jpg" );
                    
                    foto1.setImage(image);  
                    foto1.setPreserveRatio(true);
                    foto1.setSmooth(true);
                    foto1.setCache(true);
                    //Icon icono = new ImageIcon(ImgIcon.getImage().getScaledInstance(jLImg.getWidth(),jLImg.getHeight(),Image.SCALE_DEFAULT));
                    //JLImg.setIcon(icono);                
                }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error al abrir"+ex);
                
            }
                
                }
                
                
                
                
                    /*
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Buscar Imagen");

                // Agregar filtros para facilitar la busqueda
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("All Images", "*.*"),
                        new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                        new FileChooser.ExtensionFilter("PNG", "*.png")
                );

                // Obtener la imagen seleccionada
                //File imgFile = fileChooser.showOpenDialog(stage);
                //CircularDoubleLinkedList<Foto> list = (CircularDoubleLinkedList<Foto>) fileChooser.showOpenMultipleDialog(stage);
                List<File> list = fileChooser.showOpenMultipleDialog(stage);
                // Mostar la imagen
                for (int i = 0; i < list.size(); i++) {
                    if (list != null) {
                        Image image = new Image("file:" + list.get(i).getAbsolutePath());

                    }
                }
            }*/   
            }});}
                

            
          
    public void EscribirAlbumTxt(FileWriter fichero, PrintWriter pw, CircularDoubleLinkedList<Foto> album) {
        try {
            fichero = new FileWriter("src\\Archivos\\albums.txt", true);
            pw = new PrintWriter(fichero);
            pw.println(album.getNombreAlbum() + "," +album.getDescripcion()+","+ album);
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
        cargarAlbums(album);
    }

    public void cargarAlbums(CircularDoubleLinkedList<Foto> album) {
        vbTitulos.getChildren().add(new Text(album.getNombreAlbum()));
    }
    
}
