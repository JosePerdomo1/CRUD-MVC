/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iesvigan.jose.basedatos.modelo;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Jose Antonio
 */
public class Pedidos {
    private int idPedido;
    private double totalPedido;
    private Date fechaPedido;
    private String formaPago;
    private String direccionEnvio;

    public Pedidos(int idPedido, double totalPedido,Date fechaPedido, String formaPago, String direccionEnvio) {
        this.idPedido = idPedido;
        this.totalPedido = totalPedido;
        this.fechaPedido = fechaPedido;
        this.formaPago = formaPago;
        this.direccionEnvio = direccionEnvio;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }
}
