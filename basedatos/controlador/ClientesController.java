package es.iesvigan.jose.basedatos.controlador;

import es.iesvigan.jose.basedatos.modelo.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ClientesController {

    private DAOProductos daoProductos;

    public ClientesController() {
        daoProductos = new DAOProductoImpl(); // Inicializar la implementación DAOProductoImpl
    }
    @FXML
    private TextField txtIDProducto;

    @FXML
    private Button btnBuscarClientes;
    @FXML
    private TableColumn<Clientes, String> columDNI;
    @FXML
    private TableColumn<Clientes, String> columnNombre;
    @FXML
    private TableColumn<Clientes, String> columApellidos;
    @FXML
    private TableColumn<Clientes, String> columnDireccionCorreo;
    @FXML
    private TableColumn<Clientes, String> columnNumeroTelefono;

    @FXML
    private TableColumn<Pedidos, Integer> columnIDPedido;
    @FXML
    private TableColumn<Pedidos, Double> columnTotalPedido;
    @FXML
    private TableColumn<Pedidos, Date> columnFechaPedido;
    @FXML
    private TableColumn<Pedidos, String> columnFormaPago;
    @FXML
    private TableColumn<Pedidos, String> columnDireccionEnvio;

    @FXML
    private Button btnLimpiar1;
    @FXML
    private Button btnLimpiar2;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button buscarCliente2;
    @FXML
    private Button buscarCliente1;
    @FXML
    private Button btnAgregar;

    @FXML
    private Label mensajeError;

    @FXML
    private TableView<Clientes> tableClientes;

    @FXML
    private TableView<Pedidos> tablePedidos;
    @FXML
    private TextField txtIDProductoNuevo;

    @FXML
    private ComboBox<String> comboCategoria;

    @FXML
    private ComboBox<String> comboMarca;

    @FXML
    private TableView<Productos> tableProductos;

    @FXML
    private TableColumn<Productos, Double> columPrecio;

    @FXML
    private TableColumn<Productos, Integer> columnIdProducto;

    @FXML
    private TableColumn<Productos, String> columNombre;

    @FXML
    private TableColumn<Productos, String> columnImagen;

    @FXML
    private TableColumn<Productos, Integer> columnStock;

    @FXML
    private Button actualizarPrecio;

    @FXML
    private Label mensajeErrorNuevo;
    @FXML
    private TabPane tabPane;

    @FXML
    private TableView<Devoluciones> tablaDevoluciones;

    @FXML
    private TableColumn<Devoluciones, Integer> idPedidoColumn;

    @FXML
    private TableColumn<Devoluciones, Date> fechaColumn;

    @FXML
    private TableColumn<Devoluciones, Integer> idDevolucionColumn;

    @FXML
    private TextField idPedidotxt;

    @FXML
    private TextField idDevoluciontxt;

    @FXML
    private DatePicker fechatxt;

    @FXML
    public Label mensajeErrorDevolucion;

    @FXML
    public void initialize() {
        // Configurar la selección de una sola fila en la tabla
        tableProductos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        // Crear una instancia de DAOProductoImpl
        DAOProductoImpl daoProductos = new DAOProductoImpl();

        // Obtener la lista de categorías desde la base de datos
        List<String> categorias = daoProductos.obtenerCategorias();

        // Obtener la lista de marcas desde la base de datos
        List<String> marcas = daoProductos.obtenerMarcas();

        // Agregar las categorías al ComboBox
        comboCategoria.getItems().addAll(categorias);

        // Agregar las marcas al ComboBox
        comboMarca.getItems().addAll(marcas);

        // Adjuntar iconos a los botones
        // Uso del método auxiliar para configurar los botones
        btnLimpiar1.setGraphic(createImageView("/img/limpiar.png", 20, 20));
        btnLimpiar2.setGraphic(createImageView("/img/limpiar.png", 20, 20));
        buscarCliente1.setGraphic(createImageView("/img/icono.png", 20, 20));
        buscarCliente2.setGraphic(createImageView("/img/icono.png", 20, 20));
        btnActualizar.setGraphic(createImageView("/img/Actualizar.png", 20, 20));
        btnAgregar.setGraphic(createImageView("/img/Agregar.png", 20, 20));

    }

    private ImageView createImageView(String imagePath, double fitWidth, double fitHeight) {
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(fitWidth);
        imageView.setFitHeight(fitHeight);
        return imageView;
    }

    @FXML
    private void buscarClientes(ActionEvent event) {
        int productoID = 0;
        // Obtén el ID del producto desde el TextField
        if (txtIDProducto.getText().matches("\\d+")) {
            productoID = Integer.parseInt(txtIDProducto.getText());
        } else {
            mensajeError.setText("El código de producto debe ser numérico");
            return;
        }

        // Crear una instancia de DAOClienteImpl y DAOPedidoImpl
        DAOClienteImpl daoCliente = new DAOClienteImpl();
        DAOPedidoImpl daoPedido = new DAOPedidoImpl();

        // Obtener la lista de clientes que se llevaron el producto
        List<Clientes> clientesList = daoCliente.obtenerClientesPorProducto(productoID);

        // Obtener la lista de pedidos relacionados con los clientes
        List<Pedidos> pedidosList = daoPedido.obtenerPedidosPorClientes(clientesList, productoID);

        // Limpiar las tablas antes de agregar nuevos datos
        tableClientes.getItems().clear();
        tablePedidos.getItems().clear();

        // Actualizar la tabla de clientes con los resultados obtenidos
        tableClientes.getItems().addAll(clientesList);

        // Actualizar la tabla de pedidos con los resultados obtenidos
        tablePedidos.getItems().addAll(pedidosList);

        // Asignar los valores a cada columna de la tabla de clientes
        columDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        columnDireccionCorreo.setCellValueFactory(new PropertyValueFactory<>("direccionCorreo"));
        columnNumeroTelefono.setCellValueFactory(new PropertyValueFactory<>("numeroTelefono"));

        // Asignar los valores a cada columna de la tabla de pedidos
        columnIDPedido.setCellValueFactory(new PropertyValueFactory<>("idPedido"));
        columnTotalPedido.setCellValueFactory(new PropertyValueFactory<>("totalPedido"));
        columnFechaPedido.setCellValueFactory(new PropertyValueFactory<>("fechaPedido"));
        columnFormaPago.setCellValueFactory(new PropertyValueFactory<>("formaPago"));
        columnDireccionEnvio.setCellValueFactory(new PropertyValueFactory<>("direccionEnvio"));
    }

    @FXML
    private void actualizarPrecio(ActionEvent event) {
        String idProductoText = txtIDProductoNuevo.getText();

        int idProducto = Integer.parseInt(idProductoText);
        // Obtener el producto seleccionado de la tabla
        Productos productoSeleccionado = tableProductos.getSelectionModel().getSelectedItem();

        if (productoSeleccionado == null) {
            // No se ha seleccionado ningún producto, mostrar un mensaje de error
            mensajeErrorNuevo.setText("Debes seleccionar un producto");
            return;
        }

        // Mostrar un cuadro de diálogo para ingresar el nuevo precio
        TextInputDialog dialogo = new TextInputDialog();
        dialogo.setTitle("Actualizar Precio");
        dialogo.setHeaderText("Ingrese el nuevo precio para el producto:");
        dialogo.setContentText("Nuevo Precio:");

        Optional<String> resultado = dialogo.showAndWait();

        // Obtener el nuevo precio ingresado por el usuario
        if (resultado.isPresent()) {
            String nuevoPrecioTexto = resultado.get();

            try {
                double nuevoPrecio = Double.parseDouble(nuevoPrecioTexto);

                // Actualizar el precio del producto en la base de datos
                boolean actualizacionExitosa = daoProductos.actualizarPrecioEnBaseDeDatos(idProducto, nuevoPrecio);

                if (actualizacionExitosa) {
                    // Mostrar un mensaje con el nuevo precio
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Actualización Exitosa");
                    alerta.setHeaderText("El precio se ha actualizado correctamente");
                    alerta.setContentText("El nuevo precio para el producto \"" + productoSeleccionado.getNombre() + "\" es: " + nuevoPrecio);
                    alerta.showAndWait();
                } else {
                    // Mostrar un mensaje de error si la actualización falla
                    mensajeErrorNuevo.setText("No se pudo actualizar el precio en la base de datos");
                }
            } catch (NumberFormatException e) {
                // El valor ingresado no es un número válido
                mensajeErrorNuevo.setText("El precio debe ser un valor numérico");
            }
        }
    }

    @FXML
    private void buscarClientes2(ActionEvent event) {
        int productoID = 0;
        // Obtén el ID del producto desde el TextField
        if (txtIDProductoNuevo.getText().isEmpty()) {
            mensajeErrorNuevo.setText("El código de producto no puede estar vacío");
            return;
        }

        if (txtIDProductoNuevo.getText().matches("\\d+")) {
            productoID = Integer.parseInt(txtIDProductoNuevo.getText());
        } else {
            mensajeErrorNuevo.setText("El código de producto debe ser numérico");
            return;
        }

        // Obtener la categoría y marca seleccionadas de los ComboBox
        String categoriaSeleccionada = comboCategoria.getValue();
        String marcaSeleccionada = comboMarca.getValue();

        // Crear una instancia de DAOProductosImpl
        DAOProductoImpl daoProductos = new DAOProductoImpl();

        // Obtener el producto desde la base de datos con la categoría y marca seleccionadas
        Productos producto = daoProductos.obtenerProducto(productoID, categoriaSeleccionada, marcaSeleccionada);

        // Verificar si se encontró un producto válido
        if (producto == null) {
            mensajeErrorNuevo.setText("No se encontró el producto con los criterios seleccionados");
            return;
        }

        // Limpiar la tabla antes de agregar nuevos datos
        tableProductos.getItems().clear();

        // Agregar el producto a la tabla
        tableProductos.getItems().add(producto);

        // Asignar los valores a cada columna de la tabla de productos
        columPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnImagen.setCellValueFactory(new PropertyValueFactory<>("imagen"));
        columnStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    @FXML
    private void ingresaDevolucion(ActionEvent event) {
        // Obtener los valores de los campos de devolución
        int idPedido = Integer.parseInt(idPedidotxt.getText());
        int idDevolucion = Integer.parseInt(idDevoluciontxt.getText());
        Date fecha = Date.valueOf(fechatxt.getValue());

        // Crear un objeto Devoluciones con los valores ingresados
        Devoluciones devolucion = new Devoluciones(idPedido, idDevolucion, fecha);

        // Guardar la devolución en la base de datos
        boolean guardado = daoProductos.guardarDevolucion(devolucion, mensajeErrorDevolucion);

        if (guardado) {
            // La devolución se guardó correctamente en la base de datos

            // Agregar la devolución a la tabla
            tablaDevoluciones.getItems().add(devolucion);

            // Asignar los valores a cada columna de la tabla de devoluciones
            TableColumn<Devoluciones, Integer> columnaIdPedido = new TableColumn<>("ID Pedido");
            columnaIdPedido.setCellValueFactory(new PropertyValueFactory<>("idPedido"));

            TableColumn<Devoluciones, Integer> columnaIdDevolucion = new TableColumn<>("ID Devolución");
            columnaIdDevolucion.setCellValueFactory(new PropertyValueFactory<>("idDevolucion"));

            TableColumn<Devoluciones, Date> columnaFecha = new TableColumn<>("Fecha");
            columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

            // Agregar las columnas a la tabla
            tablaDevoluciones.getColumns().clear();
            tablaDevoluciones.getColumns().addAll(columnaIdPedido, columnaIdDevolucion, columnaFecha);

            // Limpiar los campos de entrada
            idPedidotxt.clear();
            idDevoluciontxt.clear();
            fechatxt.setValue(null);

            // Limpiar el mensaje de error
            mensajeErrorDevolucion.setText("Devolución guardada correctamente");
        } else {
            // Ocurrió un error al guardar la devolución
            mensajeErrorDevolucion.setText("No se pudo guardar la devolución");
        }
    }

    public void limpiarTabla() {
        // Limpia la tabla de clientes y la tabla de pedidos
        tableClientes.getItems().clear();
        tablePedidos.getItems().clear();
        tableProductos.getItems().clear();
    }
}
