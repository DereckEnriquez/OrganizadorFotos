package Controlador;

import Modelo.Album;
import Modelo.CircularDoubleLinkedList;
import Modelo.Foto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
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

    private MenuItem btnImportar;

    Stage stage = new Stage();
    @FXML
    private HBox hbGaleria;
    @FXML
    private VBox vbTitulos;

    @FXML
    private void salirmenu(ActionEvent event) {
        System.out.println("clic salir menu");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exploradorArchivo.setInitialDirectory(new File("C:"));
    }

    @FXML
    private void irFormularioAlbumfxml(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/FormularioAlbum.fxml"));
        Parent root = loader.load();
        FormularioAlbumController controlador = loader.getController(); //VentanaController ???
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    public void irFormularioFotoFxml(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/FormularioFoto.fxml"));
        Parent root = loader.load();
        FormularioFotoController controlador = loader.getController(); //VentanaController ???
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
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

    public static CircularDoubleLinkedList<Foto> leerFotos() {
        CircularDoubleLinkedList<Foto> fotos = new CircularDoubleLinkedList<>();
        System.out.println("lista de fotos creada:" + fotos);

        try (InputStream input = Main.class.getResourceAsStream("/Archivos/photos.txt");
                BufferedReader bf = new BufferedReader(new InputStreamReader(input, "UTF-8"))) {

            String linea;
            while ((linea = bf.readLine()) != null) {
                System.out.println(linea);
                String[] p = linea.split(",");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = null;
                localDate = LocalDate.parse(p[3], formatter);
                Foto foto = new Foto(p[0], p[1], p[2], localDate, p[4]);
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

}
