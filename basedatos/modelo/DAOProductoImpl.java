/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iesvigan.jose.basedatos.modelo;

import es.iesvigan.jose.basedatos.controlador.ClientesController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;

/**
 *
 * @author Jose Antonio
 */
public class DAOProductoImpl implements DAOProductos {

    private Database database;
    private Connection connection;

    public DAOProductoImpl() {
        database = new Database();
        connection = database.getConnection();

    }

    @Override
    public Productos obtenerProducto(int idProducto, String categoriaSeleccionada, String marcaSeleccionada) {
        Productos producto = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT p.precio_venta, p.nombre, p.imagen, p.stock FROM producto p "
                    + "JOIN categoria c ON p.id_categoria = c.id_categoria "
                    + "JOIN marca m ON p.id_marca = m.id_marca "
                    + "WHERE p.id_producto = ? AND c.tipo ILIKE ? AND m.nombre_modelo ILIKE ?");
            statement.setInt(1, idProducto);
            statement.setString(2, categoriaSeleccionada);
            statement.setString(3, marcaSeleccionada);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                double precio = resultSet.getDouble("precio_venta");
                String nombre = resultSet.getString("nombre");
                String imagen = resultSet.getString("imagen");
                int stock = resultSet.getInt("stock");

                producto = new Productos(precio, nombre, imagen, stock);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.close();
        }

        return producto;
    }

    @Override
    public boolean actualizarPrecioEnBaseDeDatos(int idProducto, double nuevoPrecio) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE producto SET precio_venta = ? WHERE id_producto = ?");
            statement.setDouble(1, nuevoPrecio);
            statement.setInt(2, idProducto);
            int filasActualizadas = statement.executeUpdate();
            statement.close();

            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean guardarDevolucion(Devoluciones devolucion, Label mensajeErrorDevolucion) {
        try ( Connection connection = database.getConnection();  PreparedStatement statementExists = connection.prepareStatement("SELECT COUNT(*) FROM devolucion WHERE id_devolucion = ?"); 
                PreparedStatement statementInsert = connection.prepareStatement("INSERT INTO devolucion (id_pedido, id_devolucion, fecha) VALUES (?, ?, ?)")) {

            // Verificar si ya existe un registro con el mismo ID de devolución
            statementExists.setInt(1, devolucion.getIdDevolucion());
            ResultSet resultSet = statementExists.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                // El ID de devolución ya existe
                mensajeErrorDevolucion.setText("El ID de devolución ya existe");
                return false;
            }

            // Insertar la devolución en la base de datos
            statementInsert.setInt(1, devolucion.getIdPedido());
            statementInsert.setInt(2, devolucion.getIdDevolucion());
            statementInsert.setDate(3, new java.sql.Date(devolucion.getFecha().getTime()));

            int rowsInserted = statementInsert.executeUpdate();
            if (rowsInserted > 0) {
                // La devolución se insertó correctamente
                return true;
            } else {
                // Ocurrió un error al insertar la devolución
                mensajeErrorDevolucion.setText("No se pudo guardar la devolución");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mensajeErrorDevolucion.setText("Error de base de datos");
            return false;
        } finally {
            database.close();
        }
    }

    @Override
    public List<String> obtenerCategorias() {
        List<String> categorias = new ArrayList<>();
        categorias.add("Electrónica");
        categorias.add("Ropa");
        return categorias;
    }

    @Override
    public List<String> obtenerMarcas() {
        List<String> marcas = new ArrayList<>();
        marcas.add("Marca1");
        marcas.add("Marca2");
        return marcas;
    }

}
