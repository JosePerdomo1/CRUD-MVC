/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iesvigan.jose.basedatos.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose Antonio
 */
public class DAOClienteImpl implements DAOCliente {

    private Database database;
    private Connection connection;

    public DAOClienteImpl() {
        database = new Database();
        connection = database.getConnection();
    }

    @Override
    public List<Clientes> obtenerClientesPorProducto(int idProducto) {
        List<Clientes> listaClientes = new ArrayList<>();

        try {
            // Crear la consulta SQL para obtener los clientes que se llevaron el producto
            String sql = "SELECT cliente.dni, cliente.nombre, cliente.apellidos, cliente.direccion_correo, cliente.numero_de_telefono "
                    + "FROM cliente "
                    + "INNER JOIN pedido ON cliente.dni = pedido.dni "
                    + "INNER JOIN tiene ON pedido.ID_pedido = tiene.ID_pedido "
                    + "INNER JOIN producto ON tiene.ID_producto = producto.ID_producto "
                    + "WHERE producto.ID_producto = " + idProducto;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // Recorrer los resultados y agregar los clientes a la lista
            while (resultSet.next()) {
                String dniCliente = resultSet.getString("dni");
                String nombreCliente = resultSet.getString("nombre");
                String apellidosCliente = resultSet.getString("apellidos");
                String direccionCorreo = resultSet.getString("direccion_correo");
                String numeroTelefono = resultSet.getString("numero_de_telefono");

                Clientes cliente = new Clientes(dniCliente, nombreCliente, apellidosCliente, direccionCorreo, numeroTelefono);
                listaClientes.add(cliente);
            }
            

            // Cerrar el statement y el resultSet
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión
            database.close();
        }

        return listaClientes;
    }

}
