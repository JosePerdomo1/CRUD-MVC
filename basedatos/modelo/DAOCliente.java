/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.iesvigan.jose.basedatos.modelo;

import java.util.List;

/**
 *
 * @author Jose Antonio
 */

public interface DAOCliente {
    List<Clientes> obtenerClientesPorProducto(int idProducto);
}