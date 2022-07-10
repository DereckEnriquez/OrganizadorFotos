package Controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
 * @author Dereck Enríquez
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
    private void btnEliminarAlbum(ActionEvent event) {
        String albumSeleccionado = this.cbAlbum.getValue();
        removeLineFromFile("albunes.txt", albumSeleccionado);
        File archivo = new File(albumSeleccionado + ".txt");
        if (archivo.delete()) {
            System.out.println("El fichero " + archivo + " ha sido borrado satisfactoriamente");
            //                  archivo modificar , linea a remover

        } else {
            System.out.println("El fichero no puede ser borrado");
        }

    }

    public void removeLineFromFile(String file, String lineToRemove) {
        try {
            File inFile = new File(file);
            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }
            //Construct the new file that will later be renamed to the original filename. 
            File tempFile = new File(inFile.getAbsolutePath());
            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            String line = null;

            //Read from the original file and write to the new 
            //unless content matches data to be removed.
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
            
            inFile.delete();
            tempFile.renameTo(inFile);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
