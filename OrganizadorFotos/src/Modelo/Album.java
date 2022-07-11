package Modelo;

import Controlador.Main;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author G6
 */
public class Album implements Comparable<CircularDoubleLinkedList> {

    String nombre;
    String descripAlbum;

    public Album(String nombre, String descripAlbum) {
        this.nombre = nombre;
        this.descripAlbum = descripAlbum;
    }

    /*Validación, para evitar nombres repetidos
        retorna true si ya existe el nombre, falso si no existe
     */
    public static boolean validacionNombre(String nombre) {
        try (InputStream input = Main.class.getResourceAsStream("albunes.txt");
                BufferedReader bf = new BufferedReader(new InputStreamReader(input, "UTF-8"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                String[] p = linea.split(",");
                if ((p[0].equalsIgnoreCase(nombre))) {
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }
        return false;
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
