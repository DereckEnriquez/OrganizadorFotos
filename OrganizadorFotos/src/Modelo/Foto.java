package Modelo;

import Controlador.Main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

    /*public static CircularDoubleLinkedList<Foto> cargarFotos() {
        CircularDoubleLinkedList<Foto> fotos = new CircularDoubleLinkedList<>();

        try {
            //FileReader f = new FileReader("src\\Archivos\\photos.txt");
            //BufferedReader bf = new BufferedReader(new FileReader(new File("src\\Archivos\\photos.txt")));
            // BufferedReader bf = new BufferedReader(new FileReader(new File(("../src/Archivos/photos.txt"))));
              //BufferedReader bf = new BufferedReader(new FileReader(new File(("..\\src\\Archivos\\photos.txt"))));
               BufferedReader bf = new BufferedReader(new FileReader(new File(("Archivos/photos.txt"))));
               System.out.println(bf);
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

    }*/
    public static CircularDoubleLinkedList<Foto> leerFotos() {
        CircularDoubleLinkedList<Foto> fotos = new CircularDoubleLinkedList<>();
        System.out.println("lista de fotos creada:"+fotos);
        
        try (InputStream input = Main.class.getResourceAsStream("/Archivos/photos.txt");
             BufferedReader bf = new BufferedReader(new InputStreamReader(input, "UTF-8"))
            ) {
           
            String linea;
            while ((linea = bf.readLine()) != null) {
                System.out.println(linea);
                String[] p = linea.split(",");
                Foto foto = new Foto(p[0], p[1]);
                fotos.addFirst(foto);
               
            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }
        System.out.println("lista de fotos llena:"+fotos);
        return fotos;
    }
}
