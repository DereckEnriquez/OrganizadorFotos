package Controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Dereck Enríquez
 */
public class FormularioEliminarFotoController implements Initializable {

    @FXML
    private ImageView imageview;
    @FXML
    private ComboBox<String> cbAlbum;
    @FXML
    private ComboBox<String> cbFoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBoxAlbum();
        llenarComboBoxFoto();
    }

    @FXML
    private void btnEliminarFoto(ActionEvent event) throws IOException {
        String albumSeleccionado = this.cbAlbum.getValue();
        String fotoSeleccionado = this.cbFoto.getValue();

        removeLineFromFile(albumSeleccionado + ".txt", fotoSeleccionado);

        FileCopy("fototemp.txt", albumSeleccionado + ".txt");
    }

    public void FileCopy(String sourceFile, String destinationFile) throws FileNotFoundException, IOException {
        System.out.println("Desde: " + sourceFile);
        System.out.println("Hacia: " + destinationFile);

        File inFile = new File(sourceFile);
        File outFile = new File(destinationFile);

        FileInputStream in = new FileInputStream(inFile);
        FileOutputStream out = new FileOutputStream(outFile);

        int c;
        while ((c = in.read()) != -1) {
            out.write(c);
        }

        in.close();
        out.close();
        File archivo = new File("fototemp.txt");
        archivo.delete();
    }

    public void removeLineFromFile(String file, String lineToRemove) throws FileNotFoundException, IOException {
        File inFile = new File(file);
        if (!inFile.isFile()) {
            System.out.println("Parameter is not an existing file");
            return;
        }
        File tempFile = new File("fototemp.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
        String line = null;

        while ((line = br.readLine()) != null) {
            String[] partes = line.split(",");
            String nombrefoto = partes[0];
            if (!nombrefoto.trim().equals(lineToRemove)) {
                pw.println(line);
                pw.flush();
            }
        }
        pw.close();
        br.close();
    }

    @FXML
    private void btnSalir(ActionEvent event) throws IOException {
        Main.setRoot("/Vista/Ventana");
    }

    public void llenarComboBoxAlbum() {
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

    public void llenarComboBoxFoto() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            String nombrealbum = this.cbAlbum.getValue();
           // archivo = new File("pruebaeliminar.txt");
            archivo = new File(nombrealbum + ".txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(",");
                cbFoto.getItems().add(p[0]);
            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }

    }

    public void actualizarComboBox() {
        cbAlbum.getItems().clear();
        llenarComboBoxAlbum();
        llenarComboBoxFoto();
    }

    @FXML
    private void cbEvent(ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(cbAlbum)) {
            cbFoto.getItems().removeAll(cbFoto.getItems());
            llenarComboBoxFoto();
        }
    }

}
