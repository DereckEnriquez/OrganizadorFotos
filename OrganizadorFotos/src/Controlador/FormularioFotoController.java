package Controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
 * @author G6
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
    @FXML
    private TextField txtPersonas;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBox();
        actualizarComboBox();
    }

    @FXML
    private void btnSeleccionarRutaFoto(ActionEvent event) {
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
                System.out.println("Entro al try");
                System.out.println(archivo.getAbsolutePath());
                FileInputStream input = new FileInputStream(archivo);
                Image image = new Image(input);
                imageviewFoto.setImage(image);
                imageviewFoto.setPreserveRatio(true);
                imageviewFoto.setSmooth(true);
                imageviewFoto.setCache(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al abrir: " + ex);
            }
        }
    }

    @FXML
    private void btnImportar(ActionEvent event) {
        String nombreFoto = this.txtNombreFoto.getText();
        String descripcionFoto = this.txtDescripcionFoto.getText();
        String lugarFoto = this.txtLugarFoto.getText();
        LocalDate fechaFoto = this.txtDateFoto.getValue();
        String albumSeleccionado = this.cbAlbum.getValue();
        String[] personasSeleccionadas = this.txtPersonas.getText().split(",");
        String rutaSeleccionado = this.txtRutaFoto.getText();
        System.out.println("clic btn crear album");

        try {
            //escribir en un nuevo archivo -> album
            String ruta = ".txt";
            File file = new File(albumSeleccionado + ruta);
            FileWriter fw = new FileWriter(file, true);
            PrintWriter l = new PrintWriter(fw);
            l.println(nombreFoto + "," + descripcionFoto + "," + lugarFoto + "," + fechaFoto + "," + rutaSeleccionado);
            l.close();
            fw.close();

            //escribir en un nuevo archivo con el nombre de la foto las persona(s)
            for (int i = 0; i < personasSeleccionadas.length; i++) {
                File file2 = new File(nombreFoto + ruta);
                FileWriter fw2 = new FileWriter(file2, true);
                PrintWriter l2 = new PrintWriter(fw2);
                l2.println(personasSeleccionadas[i] + "," + lugarFoto);
                l2.close();
                fw2.close();
            }

        } catch (Exception e) {
            System.out.println(e);;
        }
        txtNombreFoto.clear();
        txtDescripcionFoto.clear();
        txtLugarFoto.clear();
        txtRutaFoto.clear();
        imageviewFoto.getImage().cancel();
        actualizarComboBox();
    }

    @FXML
    private void btnSalir(ActionEvent event) {
        try {
            Main.setRoot("/Vista/Ventana");
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Error");
            a.show();
        }
    }

    public void llenarComboBox() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File("albunes.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(",");
                cbAlbum.getItems().add(p[0]);
            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

    public void actualizarComboBox() {
        cbAlbum.getItems().clear();
        llenarComboBox();
    }
}
