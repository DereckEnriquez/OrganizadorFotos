package Modelo;

import java.time.LocalDate;

/**
 *
 * @author G6
 */
public class Foto {

    String nombre;
    String descripcion;
    String lugar;
    LocalDate fecha;
    String ruta;

    public Foto(String nombre, String descripcion, String lugar, LocalDate fecha, String ruta) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.fecha = fecha;
        this.ruta = ruta;
    }
    public Foto(String nombre, String descripcion, String lugar, String ruta) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.lugar = lugar;        
        this.ruta = ruta;
    }
    public Foto (String ruta){
        this.ruta = ruta;
    }

    public Foto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "Foto{" + "nombre=" + nombre + ", descripcion=" + descripcion + ", lugar=" + lugar + ", fecha=" + fecha + ", ruta=" + ruta + '}';
    }

}
