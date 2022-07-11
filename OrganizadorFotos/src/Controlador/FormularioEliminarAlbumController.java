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

/**
 * FXML Controller class
 *
 * @author G6
 */
public class FormularioEliminarAlbumController implements Initializable {

    @FXML
    private ComboBox<String> cbAlbum;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBox();
        actualizarComboBox();
    }

    @FXML
    private void btnEliminarAlbum(ActionEvent event) throws IOException {
        String albumSeleccionado = this.cbAlbum.getValue();
        File archivo = new File(albumSeleccionado + ".txt");
        removeLineFromFile("albunes.txt", albumSeleccionado);
        archivo.delete();
        FileCopy("albunestemp.txt", "albunes.txt");
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
        File archivo = new File("albunestemp.txt");
        archivo.delete();
    }

    public void removeLineFromFile(String file, String lineToRemove) throws FileNotFoundException, IOException {
        File inFile = new File(file);
        int cont = 0;
        String nombrearchivo = "albunes.txt";
        if (!inFile.isFile()) {
            System.out.println("Parameter is not an existing file");
            return;
        }
        File tempFile = new File("albunestemp.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
        String line = null;

        while ((line = br.readLine()) != null) {
            String[] partes = line.split(",");
            String nombrealbum = partes[0];
            if (!nombrealbum.trim().equals(lineToRemove)) {
                pw.println(line);
                pw.flush();
            }
        }
        pw.close();
        br.close();
    }

    @FXML
    private void btnsalir(ActionEvent event) throws IOException {
        Main.setRoot("/Vista/Ventana");
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
