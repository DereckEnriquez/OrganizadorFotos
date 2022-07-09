package Controlador;

import Modelo.Album;
import Modelo.CircularDoubleLinkedList;
import Modelo.Foto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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

    File archivo;

    static CircularDoubleLinkedList<Foto> fotos;

    @FXML
    private void salirmenu(ActionEvent event) {
        System.out.println("clic salir menu");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exploradorArchivo.setInitialDirectory(new File("C:"));
        /*for (Foto ca : fotos) {
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
    }*/
    }

    @FXML
    private void importarFoto() {
        btnImportar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int resultado;
                buscarIMG Buscador = new buscarIMG();
                FileNameExtensionFilter formato = new FileNameExtensionFilter("JPG", "PNG", "GIF", "jpg", "png", "gif");

                Buscador.JFCImg.setFileFilter(formato);
                resultado = Buscador.JFCImg.showOpenDialog(null);

                if (JFileChooser.APPROVE_OPTION == resultado) {
                    archivo = Buscador.JFCImg.getSelectedFile();

                    jLDireccion.setText(archivo.getAbsolutePath());

                    try {
                        //ImageIcon ImgIcon = new ImageIcon(archivo.toString());
                        System.out.println("Entro al try");
                        System.out.println(archivo.getAbsolutePath());

                        
                        FileInputStream input = new FileInputStream(archivo);
                        Image image = new Image(input);
                        foto1.setImage(image);
                        foto1.setPreserveRatio(true);
                        foto1.setSmooth(true);
                        foto1.setCache(true);
                        //Icon icono = new ImageIcon(ImgIcon.getImage().getScaledInstance(jLImg.getWidth(),jLImg.getHeight(),Image.SCALE_DEFAULT));
                        //JLImg.setIcon(icono);                
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al abrir" + ex);

                    }

                }
            }
        });
    }

    public void EscribirAlbumTxt(FileWriter fichero, PrintWriter pw, CircularDoubleLinkedList<Foto> album) {
        try {
            fichero = new FileWriter("src\\Archivos\\albums.txt", true);
            pw = new PrintWriter(fichero);
            pw.println(album.getNombreAlbum() + "," + album.getDescripcion());
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
        vbTitulos.getChildren().add(new Text(album.getNombreAlbum()));
    }

    public static void leerBibliotecabeta() {
        try (InputStream input = Main.class.getResourceAsStream("/Archivos/biblioteca.txt");
                BufferedReader bf = new BufferedReader(new InputStreamReader(input, "UTF-8"))) {
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public static CircularDoubleLinkedList<Foto> leerFotos() {
        CircularDoubleLinkedList<Foto> fotos = new CircularDoubleLinkedList<>();
        System.out.println("lista de fotos creada:" + fotos);

        try (InputStream input = Main.class.getResourceAsStream("/Archivos/photos.txt");
                BufferedReader bf = new BufferedReader(new InputStreamReader(input, "UTF-8"))) {

            String linea;
            while ((linea = bf.readLine()) != null) {
                System.out.println(linea);
                String[] p = linea.split(",");
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaDate = null;
                fechaDate = formato.parse(p[3]);
                Foto foto = new Foto(p[0], p[1], p[2], fechaDate, p[4]);
                fotos.addFirst(foto);

            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }
        System.out.println("lista de fotos llena:" + fotos);
        return fotos;
    }

    //album.txt es un archivo con todos los nombres de los albums 
    public static CircularDoubleLinkedList<Album> leerAlbums() {
        CircularDoubleLinkedList<Album> albums = new CircularDoubleLinkedList<>();
        System.out.println("lista de albums creada:" + albums);

        try (InputStream input = Main.class.getResourceAsStream("/Archivos/albums.txt");
                BufferedReader bf = new BufferedReader(new InputStreamReader(input, "UTF-8"))) {

            String linea;
            while ((linea = bf.readLine()) != null) {
                System.out.println(linea);
                String[] p = linea.split(",");
                Album album = new Album(p[0], p[1]);
                albums.addFirst(album);

            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }
        System.out.println("lista de albums llena:" + albums);
        return albums;
    }

    @FXML
    private void irFormulariofxml(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/FormularioAlbum.fxml"));
        Parent root = loader.load();
        FormularioAlbumController controlador = loader.getController(); //VentanaController ???
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
