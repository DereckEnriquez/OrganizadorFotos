package Modelo;

/**
 *
 * @author Dereck Enríquez
 */
public class Album {
    String nombre;
    String ruta;
    CircularDoubleLinkedList<Foto> album;

    public Album(String nombre, String ruta, CircularDoubleLinkedList<Foto> album) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.album = album;
    }

    public Album() {
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

    public CircularDoubleLinkedList<Foto> getAlbum() {
        return album;
    }

    public void setAlbum(CircularDoubleLinkedList<Foto> album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Album{" + "nombre=" + nombre + ", ruta=" + ruta + ", album=" + album + '}';
    }
}
