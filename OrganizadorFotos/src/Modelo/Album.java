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
    
}
