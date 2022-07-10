package Controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
 * @author Grupo6
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
        String rutaSeleccionado = this.txtRutaFoto.getText();
        System.out.println("clic btn crear album");
        // Foto fotonueva = new Foto(nombreFoto,descripcionFoto,lugarFoto,fechaFoto,rutaSeleccionado);
        

        
        
        try {
            //escribir en un nuevo archivo -> album
            String ruta = ".txt";                    
            File file = new File(albumSeleccionado+ruta);                    
            FileWriter fw = new FileWriter(file,true);
            PrintWriter l = new PrintWriter(fw);                   
            l.println(nombreFoto + "," + descripcionFoto + "," + lugarFoto + "," + fechaFoto + "," + rutaSeleccionado);
            l.close();
            fw.close();            
            //fichero = new FileWriter(albumSeleccionado + ".txt");
            //pw = new PrintWriter(fichero);
            //pw.println(nombreFoto + "," + descripcionFoto + "," + lugarFoto + "," + fechaFoto + "," + rutaSeleccionado);

        } catch (Exception e) {
            System.out.println(e);;
        } 
        /*finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                System.out.println(e2);;
            }
        }*/
        txtNombreFoto.clear();
        txtDescripcionFoto.clear();
        txtLugarFoto.clear();
        txtRutaFoto.clear();
        imageviewFoto.getImage().cancel();
       
        ActualizarComboBox();
    }

    @FXML
    private void btnSalir(ActionEvent event) {
        try {
            Main.setRoot("/Vista/Ventana");
        } catch (IOException ex) {
            Alert a=new Alert(Alert.AlertType.ERROR,"Error");
            a.show();
        }
    }

    public void llenarComboBox() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try{
        //try (InputStream input = Main.class.getResourceAsStream("/Archivos/albums.txt");
        //        BufferedReader bf = new BufferedReader(new InputStreamReader(input, "UTF-8"))) {
            archivo = new File ("albunes.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);            
            String linea;            
            while ((linea = br.readLine()) != null) {
                //System.out.println(linea);
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
