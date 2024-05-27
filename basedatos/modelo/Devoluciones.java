/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iesvigan.jose.basedatos.modelo;

/**
 *
 * @author Jose Antonio
 */
import java.util.Date;

public class Devoluciones {

    private int idPedido;
    private int idDevolucion;
    private Date fecha;

    public Devoluciones(int idPedido, int idDevolucion, Date fecha) {
        this.idPedido = idPedido;
        this.idDevolucion = idDevolucion;
        this.fecha = fecha;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
