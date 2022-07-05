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
    String ruta;
    String descripAlbum;
    CircularDoubleLinkedList<Foto> album;

    public Album(String nombre, String ruta, String descripAlbum, CircularDoubleLinkedList<Foto> album) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.descripAlbum = descripAlbum;
        this.album = album;
    }

    public Album(String nombre, String descripAlbum, CircularDoubleLinkedList<Foto> album) {
        this.nombre = nombre;
        this.descripAlbum = descripAlbum;
        this.album = album;
    }

    public Album(String nombre, String descripAlbum) {
        this.nombre = nombre;
        this.descripAlbum = descripAlbum;
    }
    
    
    public Album(){}
    /*
    public Foto mostrarFotos(){
        //album
    }*/


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

    public String getDescripAlbum() {
        return descripAlbum;
    }

    public void setDescripAlbum(String descripAlbum) {
        this.descripAlbum = descripAlbum;
    }

    public CircularDoubleLinkedList<Foto> getAlbum() {
        return album;
    }

    public void setAlbum(CircularDoubleLinkedList<Foto> album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Album{" + "nombre=" + nombre + ", ruta=" + ruta + ", descripAlbum=" + descripAlbum + ", album=" + album + '}';
    }   

    @Override
    public int compareTo(CircularDoubleLinkedList o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static CircularDoubleLinkedList<Album> leerAlbums() {
        CircularDoubleLinkedList<Album> albums = new CircularDoubleLinkedList<>();
        System.out.println("lista de fotos creada:" + albums);

        try (InputStream input = Main.class.getResourceAsStream("/Archivos/albums.txt");
                BufferedReader bf = new BufferedReader(new InputStreamReader(input, "UTF-8"))) {

            String linea;
            while ((linea = bf.readLine()) != null) {
                System.out.println(linea);
                String[] p = linea.split(",");
                System.out.println("p[2]:" + p[2]);
                Album album = new Album(p[0], p[1]);
                albums.addFirst(album);

            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }
        System.out.println("lista de albums llena:" + albums);
        return albums;
    }

}
