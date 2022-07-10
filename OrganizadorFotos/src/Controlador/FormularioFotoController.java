package Controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    private ComboBox<String> cbAlbum;
    @FXML
    private TextField txtRutaFoto;
    @FXML
    private ImageView imageviewFoto;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBox();
        ActualizarComboBox();
    }

    @FXML
    private void btnSeleccionarRutaFoto(ActionEvent event) {
        ActualizarComboBox();
        File archivo;
        int resultado;
        buscarIMG Buscador = new buscarIMG();
        FileNameExtensionFilter formato = new FileNameExtensionFilter("JPG", "PNG", "GIF", "jpg", "png", "gif");

        Buscador.JFCImg.setFileFilter(formato);
        resultado = Buscador.JFCImg.showOpenDialog(null);

        if (JFileChooser.APPROVE_OPTION == resultado) {
            archivo = Buscador.JFCImg.getSelectedFile();

            txtRutaFoto.setText(archivo.getAbsolutePath());

            try {
                //ImageIcon ImgIcon = new ImageIcon(archivo.toString());
                System.out.println("Entro al try");
                System.out.println(archivo.getAbsolutePath());

                FileInputStream input = new FileInputStream(archivo);
                Image image = new Image(input);
                imageviewFoto.setImage(image);
                imageviewFoto.setPreserveRatio(true);
                imageviewFoto.setSmooth(true);
                imageviewFoto.setCache(true);
                //Icon icono = new ImageIcon(ImgIcon.getImage().getScaledInstance(jLImg.getWidth(),jLImg.getHeight(),Image.SCALE_DEFAULT));
                //JLImg.setIcon(icono);                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al abrir: " + ex);
            }
        }
    }

    @FXML
    private void btnImportar(ActionEvent event) {
        ActualizarComboBox();
        String nombreFoto = this.txtNombreFoto.getText();
        String descripcionFoto = this.txtDescripcionFoto.getText();
        String lugarFoto = this.txtLugarFoto.getText();
        LocalDate fechaFoto = this.txtDateFoto.getValue();
        String albumSeleccionado = this.cbAlbum.getValue();
        String rutaSeleccionado = this.txtRutaFoto.getText();
        System.out.println("clic btn crear album");
        // Foto fotonueva = new Foto(nombreFoto,descripcionFoto,lugarFoto,fechaFoto,rutaSeleccionado);

        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            //escribir un nuevo archivo -> album
            fichero = new FileWriter("src\\Archivos\\" + albumSeleccionado + ".txt", true);
            pw = new PrintWriter(fichero);
            pw.println(nombreFoto + "," + descripcionFoto + "," + lugarFoto + "," + fechaFoto + "," + albumSeleccionado + "," + rutaSeleccionado);

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
        txtNombreFoto.clear();
        txtDescripcionFoto.clear();
        txtLugarFoto.clear();
        txtRutaFoto.clear();
        ActualizarComboBox();
    }

    @FXML
    private void btnSalir(ActionEvent event) {
    }

    public void llenarComboBox() {
        try (InputStream input = Main.class.getResourceAsStream("/Archivos/albums.txt");
                BufferedReader bf = new BufferedReader(new InputStreamReader(input, "UTF-8"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                System.out.println(linea);
                String[] p = linea.split(",");
                cbAlbum.getItems().add(p[0]);
            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

    public void ActualizarComboBox() {
        cbAlbum.getItems().clear();
        llenarComboBox();
    }
}
