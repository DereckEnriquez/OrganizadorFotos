package Controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author G6
 */
public class BusquedaController implements Initializable {

    @FXML
    private ComboBox<String> cbPersonas;
    private ComboBox<String> cbTipo;
    @FXML
    private ComboBox<String> cbLugares;
    @FXML
    private TilePane TPgaleria;
    private String namee = null;
    @FXML
    private CheckBox checkAlbun;
    @FXML
    private CheckBox checkFoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        Main.setRoot("/Vista/Ventana");
    }

    public Pane createBookView() throws FileNotFoundException {
        VBox vbox = new VBox();
        final Image image = new Image(Main.class.getResourceAsStream("/Archivos/album.png"), 150, 0, true, false);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(160);
        imageView.setFitHeight(145);
        vbox.getChildren().add(imageView);
        return vbox;
    }

    public void mostrar() throws IOException {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        File archivo2 = null;
        FileReader fr2 = null;
        BufferedReader br2 = null;
        File archivo3 = null;
        FileReader fr3 = null;
        BufferedReader br3 = null;

        String personaSelec = cbPersonas.getValue();
        String lugarSelec = cbLugares.getValue();

        archivo = new File("albunes.txt");
        fr = new FileReader(archivo);
        br = new BufferedReader(fr);
        String linea;
        String albumtxt = null;

        while ((linea = br.readLine()) != null) {
            String[] p = linea.split(",");
            albumtxt = p[0];

            archivo2 = new File(albumtxt + ".txt");
            fr2 = new FileReader(archivo2);
            br2 = new BufferedReader(fr2);
            String lineaalbum;
            while ((lineaalbum = br2.readLine()) != null) {
                String[] p2 = lineaalbum.split(",");
                String fototxt = p2[0];

                archivo3 = new File(fototxt + ".txt");
                fr3 = new FileReader(archivo3);
                br3 = new BufferedReader(fr3);
                String lineafoto;
                while ((lineafoto = br3.readLine()) != null) {
                    String[] p3 = lineafoto.split(",");
                    String persona = p3[0];
                    String lugar = p3[1];
                    if (persona.equals(personaSelec) && lugar.equals(lugarSelec)) {
                        Label titleLabel = new Label(albumtxt);
                        titleLabel.setMaxWidth(150);
                        titleLabel.setMaxWidth(150);
                        titleLabel.setAlignment(Pos.CENTER);
                        titleLabel.setTextFill(Color.web("#4D5382"));
                        titleLabel.setStyle("-fx-font-size: 20");
                        Pane bookView = createBookView();
                        bookView.getChildren().add(titleLabel);
                        TPgaleria.getChildren().addAll(bookView);

                        titleLabel.setOnMouseClicked(event -> {
                            namee = titleLabel.getText();
                            try {
                                String ruta = "tmp.txt";
                                File file = new File(ruta);
                                // Si el archivo no existe es creado
                                if (!file.exists()) {
                                    file.createNewFile();
                                }
                                FileWriter fw = new FileWriter(file);
                                BufferedWriter bw = new BufferedWriter(fw);
                                bw.write(namee);
                                bw.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                Main.setRoot("/Vista/MostrarFotosAlbum");
                            } catch (IOException ex) {
                                Logger.getLogger(VentanaController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        TPgaleria.getChildren().addAll(bookView);
                    }

                }

            }
        }
    }

    @FXML
    private void checkAlbun() throws FileNotFoundException, IOException {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        File archivo2 = null;
        FileReader fr2 = null;
        BufferedReader br2 = null;
        File archivo3 = null;
        FileReader fr3 = null;
        BufferedReader br3 = null;

        archivo = new File("albunes.txt");
        fr = new FileReader(archivo);
        br = new BufferedReader(fr);
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] p = linea.split(",");
            String albumtxt = p[0];

            archivo2 = new File(albumtxt + ".txt");
            fr2 = new FileReader(archivo2);
            br2 = new BufferedReader(fr2);
            String lineaalbum;
            while ((lineaalbum = br2.readLine()) != null) {
                String[] p2 = lineaalbum.split(",");
                String fototxt = p2[0];

                archivo3 = new File(fototxt + ".txt");
                fr3 = new FileReader(archivo3);
                br3 = new BufferedReader(fr3);
                String lineafoto;
                while ((lineafoto = br3.readLine()) != null) {
                    String[] p3 = lineafoto.split(",");
                    String persona = p3[0];
                    String lugar = p3[1];
                    if (!cbPersonas.getItems().contains(persona)) {
                        cbPersonas.getItems().add(persona);
                    }

                    if (!cbLugares.getItems().contains(lugar)) {
                        cbLugares.getItems().add(lugar);
                    }

                }
            }
            TPgaleria.getChildren().clear();
        }
        if (checkAlbun.isSelected()) {
            mostrar();
        }
    }

    private Pane createBookViewFotos(String titulo, String descripcion, String foto) throws FileNotFoundException {
        VBox vbox = new VBox();
        FileInputStream f = new FileInputStream(foto);
        final Image image = new Image(f, 150, 0, true, false);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(100);
        vbox.getChildren().add(imageView);
        Label titleLabel = new Label(titulo);
        titleLabel.setMaxWidth(150);
        vbox.getChildren().add(titleLabel);
        Label descrip = new Label(descripcion);
        descrip.setTextFill(Color.web("#0000FF"));
        descrip.setStyle("-fx-font-weight: bold");
        vbox.getChildren().add(descrip);
        return vbox;
    }

    private void mostrarFotos() throws FileNotFoundException, IOException {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        File archivo2 = null;
        FileReader fr2 = null;
        BufferedReader br2 = null;
        File archivo3 = null;
        FileReader fr3 = null;
        BufferedReader br3 = null;
        File archivo4 = null;
        FileReader fr4 = null;
        BufferedReader br4 = null;
        File archivo5 = null;
        FileReader fr5 = null;
        BufferedReader br5 = null;

        String personaSelec = cbPersonas.getValue();
        String lugarSelec = cbLugares.getValue();

        archivo = new File("albunes.txt");
        fr = new FileReader(archivo);
        br = new BufferedReader(fr);
        String linea;
        String albumtxt = null;
        String fototxt = null;

        while ((linea = br.readLine()) != null) {
            String[] p = linea.split(",");
            albumtxt = p[0];

            archivo2 = new File(albumtxt + ".txt");
            fr2 = new FileReader(archivo2);
            br2 = new BufferedReader(fr2);
            String lineaalbum;
            while ((lineaalbum = br2.readLine()) != null) {
                String[] p2 = lineaalbum.split(",");
                fototxt = p2[0];

                archivo3 = new File(fototxt + ".txt");
                fr3 = new FileReader(archivo3);
                br3 = new BufferedReader(fr3);
                String lineafoto;
                while ((lineafoto = br3.readLine()) != null) {
                    String[] p3 = lineafoto.split(",");
                    String persona = p3[0];
                    String lugar = p3[1];

            
                        archivo5 = new File(albumtxt + ".txt");
                        fr5 = new FileReader(archivo5);
                        br5 = new BufferedReader(fr5);
                        String line;
                        while ((line = br5.readLine()) != null) {

                            String[] p4 = line.split(",");
                            String lugarfx = p4[2];
                            if (lugarfx.equals(lugarSelec)&& persona.equals(personaSelec)) {
                            Pane bookView = createBookViewFotos(p4[0], p4[1], p4[4]);
                            TPgaleria.getChildren().addAll(bookView);
                        }
                    }
                }//eliminar archivo tmp
                archivo.delete();

            }
        }
    }

    @FXML
    private void checkFoto(MouseEvent event) throws FileNotFoundException, IOException {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        File archivo2 = null;
        FileReader fr2 = null;
        BufferedReader br2 = null;
        File archivo3 = null;
        FileReader fr3 = null;
        BufferedReader br3 = null;

        archivo = new File("albunes.txt");
        fr = new FileReader(archivo);
        br = new BufferedReader(fr);
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] p = linea.split(",");
            String albumtxt = p[0];

            archivo2 = new File(albumtxt + ".txt");
            fr2 = new FileReader(archivo2);
            br2 = new BufferedReader(fr2);
            String lineaalbum;
            while ((lineaalbum = br2.readLine()) != null) {
                String[] p2 = lineaalbum.split(",");
                String fototxt = p2[0];

                archivo3 = new File(fototxt + ".txt");
                fr3 = new FileReader(archivo3);
                br3 = new BufferedReader(fr3);
                String lineafoto;
                while ((lineafoto = br3.readLine()) != null) {
                    String[] p3 = lineafoto.split(",");
                    String persona = p3[0];
                    String lugar = p3[1];
                    if (!cbPersonas.getItems().contains(persona)) {
                        cbPersonas.getItems().add(persona);
                    }

                    if (!cbLugares.getItems().contains(lugar)) {
                        cbLugares.getItems().add(lugar);
                    }

                }
            }
            TPgaleria.getChildren().clear();
        }
        if (checkFoto.isSelected()) {
            mostrarFotos();
        }
    }
}
