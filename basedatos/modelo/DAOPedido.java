/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.iesvigan.jose.basedatos.modelo;

/**
 *
 * @author Jose Antonio
 */
import java.util.List;

public interface DAOPedido {
    List<Pedidos> obtenerPedidosPorClientes(List<Clientes> clientes, int idProducto);
}
