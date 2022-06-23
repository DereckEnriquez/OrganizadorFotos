/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.nio.file.Path;

/**
 *
 * @author Dereck Enríquez
 */
public class Foto {
    String nombre;  
    Path direccion;
    

    public Foto( String nombre,Path direccion) {
        this.direccion = direccion;
        this.nombre = nombre;
    }

    public Path getDireccion() {
        return direccion;
    }

    public void setDireccion(Path direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Foto{" + "nombre=" + nombre + ", direccion=" + direccion + '}';
    }

    
    
}
