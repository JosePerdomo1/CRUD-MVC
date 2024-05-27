/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iesvigan.jose.basedatos.modelo;

/**
 *
 * @author Jose Antonio
 */
public class Clientes {

    private String dni;
    private String nombre;
    private String apellidos;
    private String direccionCorreo;
    private String numeroTelefono;


    public Clientes(String dni, String nombre, String apellidos, String direccionCorreo, String numeroTelefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccionCorreo=direccionCorreo;
        this.numeroTelefono=numeroTelefono;
    }

    // Getters y setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }


    public String getDireccionCorreo() {
        return direccionCorreo;
    }

    public void setDireccionCorreo(String direccionCorreo) {
        this.direccionCorreo = direccionCorreo;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
    

    @Override
    public String toString() {
        return  "dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", apellidos='" + apellidos + '\'' + ", direccionCorreo=" + direccionCorreo + ", numeroTelefono='" + numeroTelefono;
    }
}
