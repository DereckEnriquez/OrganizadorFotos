package Modelo;

import Controlador.Main;
import Controlador.VentanaController;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Dereck Enríquez
 */
public class Album implements Comparable<CircularDoubleLinkedList>{
    String nombre;
    String descripAlbum;

    public Album(String nombre, String descripAlbum) {
        this.nombre = nombre;
        this.descripAlbum = descripAlbum;
    }
    
    
        //CREA UN ALBUM representado en un .txt con su nombre ingresado
    //añade ese album al album.txt que estan todos los nombres de los albums 
    @FXML
    private void btnCrear(ActionEvent event) throws IOException {
        String nombreAlbum = this.txtNombreAlbum.getText();
        String DescripcionAlbum = this.txtDescripcionAlbum.getText();
        System.out.println("clic btn crear album");
        CircularDoubleLinkedList<Foto> album = new CircularDoubleLinkedList(nombreAlbum, DescripcionAlbum);
        
        System.out.println("ALBUM CREADO: " + album.getNombreAlbum());
        System.out.println("Descripcion:" + album.getDescripcion() + "\n");

        FileWriter fichero = null;
        PrintWriter pw = null;
        
        try {
            //Validación Nombre
            System.out.println("Validacion nOMBRE");
            if(Album.validacionNombre(nombreAlbum)){
                System.out.println("YA EXISTE ESTE NOMBRE");
                Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setTitle("Error");
                mensaje.setContentText("Nombre Existente");
                Label label = new Label("El error es el siguiente:");
                TextArea txtArea = new TextArea("El nombre del Album ya existe, debe colocar otro");
                txtArea.setEditable(false);
                txtArea.setWrapText(true);
                txtArea.setMaxWidth(Double.MAX_VALUE);
                txtArea.setMaxHeight(Double.MAX_VALUE);
                GridPane.setVgrow(txtArea, Priority.ALWAYS);
                GridPane.setHgrow(txtArea, Priority.ALWAYS);
                GridPane contenido = new GridPane();
                contenido.setMaxWidth(Double.MAX_VALUE);
                contenido.add(label, 0, 0);
                contenido.add(txtArea, 0, 1);
                
                mensaje.getDialogPane().setExpandableContent(contenido);
                mensaje.showAndWait();
                
            }else{
            
            //escribir un nuevo archivo -> album
            fichero = new FileWriter("src\\Archivos\\"+nombreAlbum+".txt", true);
            pw = new PrintWriter(fichero);
            pw.println(album.getNombreAlbum() + "," + album.getDescripcion() + "," + album);
            
            //escribir en el archivo de albums.txt que solo lleva la base de datos de los albunes creados y su descripcion
            fichero = new FileWriter("src\\Archivos\\albums.txt", true);
            pw = new PrintWriter(fichero);
            pw.println(album.getNombreAlbum() + "," + album.getDescripcion());
            }
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
        fichero.close();
    }
    
    

    @Override
    public String toString() {
        return "Album{" + "nombre=" + nombre + ", descripAlbum=" + descripAlbum + '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripAlbum() {
        return descripAlbum;
    }

    public void setDescripAlbum(String descripAlbum) {
        this.descripAlbum = descripAlbum;
    }

    @Override
    public int compareTo(CircularDoubleLinkedList o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
