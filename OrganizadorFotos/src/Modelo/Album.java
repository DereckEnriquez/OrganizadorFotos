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
