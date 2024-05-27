/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iesvigan.jose.basedatos.modelo;

/**
 *
 * @author Jose Antonio
 */
public class Productos {

    private double precio;
    private String nombre;
    private String imagen;
    private int stock;

    public Productos(double precio, String nombre, String imagen, int stock) {
        this.precio = precio;
        this.nombre = nombre;
        this.imagen = imagen;
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{"+"precio=" + precio+  ", nombre='" + nombre + '\''+ ", imagen='" + imagen + '\'' + ", stock=" + stock + '}';
    }

}
