package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Dereck Enríquez
 */
public class Foto {

    String nombre;
    String ruta;

    public Foto(String nombre, String ruta) {
        this.ruta = ruta;
        this.nombre = nombre;
    }

    public Foto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "Foto{" + "nombre=" + nombre + ", ruta=" + ruta + '}';
    }

    public static CircularDoubleLinkedList<Foto> cargarFotos() {
        CircularDoubleLinkedList<Foto> fotos = new CircularDoubleLinkedList<>();

        try {
            //FileReader f = new FileReader("src\\Archivos\\photos.txt");
            BufferedReader bf = new BufferedReader(new FileReader(new File("src\\Archivos\\photos.txt").getCanonicalFile()));
            String linea;
            while ((linea = bf.readLine()) != null) {
                String[] p = linea.split(",");
                Foto foto = new Foto(p[0].toString(), p[1].toString());
                fotos.addLast(foto);
                System.out.println(fotos);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return fotos;

    }
}
