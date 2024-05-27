/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iesvigan.jose.basedatos.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Jose Antonio
 */
public class DAOPedidoImpl implements DAOPedido {

    private Database database;
    private Connection connection;

    public DAOPedidoImpl() {
        database = new Database();
        connection = database.getConnection();
    }

   @Override
public List<Pedidos> obtenerPedidosPorClientes(List<Clientes> clientes, int idProducto) {
    List<Pedidos> pedidos = new ArrayList<>();

    try {
        // Crear la consulta SQL para obtener los pedidos relacionados con el producto
        String sql = "SELECT pedido.ID_pedido, pedido.total_pedido, pedido.fecha_pedido, pedido.forma_de_pago, pedido.direccion_envio "
                + "FROM pedido "
                + "INNER JOIN tiene ON pedido.ID_pedido = tiene.ID_pedido "
                + "INNER JOIN producto ON tiene.ID_producto = producto.ID_producto "
                + "WHERE producto.ID_producto = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        // Recorrer los clientes y obtener sus pedidos relacionados con el producto
        for (Clientes cliente : clientes) {
            statement.setInt(1, idProducto);
            ResultSet resultSet = statement.executeQuery();

            // Recorrer los resultados y agregar los pedidos al resultado final
            while (resultSet.next()) {
                int idPedido = resultSet.getInt("ID_pedido");
                double totalPedido = resultSet.getDouble("total_pedido");
                Date fechaPedido = resultSet.getDate("fecha_pedido");
                String formaPago = resultSet.getString("forma_de_pago");
                String direccionEnvio = resultSet.getString("direccion_envio");

                Pedidos pedido = new Pedidos(idPedido, totalPedido, fechaPedido, formaPago, direccionEnvio);
                pedidos.add(pedido);
            }

            resultSet.close();
        }

        // Cerrar el statement
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Cerrar la conexión
        database.close();
    }

    return pedidos;
}


}
