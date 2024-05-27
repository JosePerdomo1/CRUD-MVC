/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.iesvigan.jose.basedatos.modelo;

import java.util.List;
import javafx.scene.control.Label;

/**
 *
 * @author Jose Antonio
 */
public interface DAOProductos {

    Productos obtenerProducto(int idProducto, String categoriaSeleccionada, String marcaSeleccionada);

    List<String> obtenerCategorias();

    List<String> obtenerMarcas();

    boolean actualizarPrecioEnBaseDeDatos(int idProducto, double nuevoPrecio);
boolean guardarDevolucion(Devoluciones devolucion, Label mensajeErrorDevolucion);
}
