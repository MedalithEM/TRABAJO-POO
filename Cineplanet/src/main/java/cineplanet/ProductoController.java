package cineplanet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductoController {
    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtStock;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, Integer> colId;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, String> colCategoria;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TableColumn<Producto, Integer> colStock;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnLimpiar;

    private ProductoCRUD crud = new ProductoCRUD();

    private ObservableList<Producto> listaProductos =
            FXCollections.observableArrayList();
    private int siguienteId = 6;

    @FXML
    public void initialize() {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        colCategoria.setCellValueFactory(
                new PropertyValueFactory<>("categoria")
        );

        colPrecio.setCellValueFactory(
                new PropertyValueFactory<>("precio")
        );

        colStock.setCellValueFactory(
                new PropertyValueFactory<>("stock")
        );

        tablaProductos.setItems(listaProductos);

        // Productos de ejemplo
        registrarProductoInicial(new Producto(1, "Canchita Mediana",
                "Canchita", 12.00, 50)
        );

        registrarProductoInicial(new Producto(2, "Gaseosa Grande",
                "Bebidas", 10.00, 40)
        );

        registrarProductoInicial(new Producto(3, "Hot Dog",
                "Comida", 15.00, 30)
        );

        registrarProductoInicial(new Producto(4, "Nachos",
                "Comida", 18.00, 25)
        );

        registrarProductoInicial(new Producto(5, "Combo Clásico",
                "Combos", 28.00, 20)
        );
    }

    private void registrarProductoInicial(Producto producto) {
        crud.registrar(producto);
        listaProductos.add(producto);
    }

    @FXML
    private void registrar() {

        try {

            int id = siguienteId;
            String nombre = txtNombre.getText();
            String categoria = txtCategoria.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            int stock = Integer.parseInt(txtStock.getText());

            if (nombre.isEmpty() || categoria.isEmpty()) {
                mostrarMensaje(
                        "Error",
                        "Complete todos los campos."
                );
                return;
            }

            if (nombre.isEmpty() || categoria.isEmpty()) {
                mostrarMensaje(
                        "Error",
                        "El ID ya existe."
                );
                return;
            }

            Producto producto = new Producto(
                    id,
                    nombre,
                    categoria,
                    precio,
                    stock
            );

            crud.registrar(producto);
            listaProductos.add(producto);

            siguienteId++;

            limpiar();

            mostrarMensaje(
                    "Correcto",
                    "Producto registrado correctamente."
            );

        } catch (NumberFormatException e) {

            mostrarMensaje(
                    "Error",
                    "Ingrese correctamente los datos numéricos."
            );
        }
    }

    @FXML
    private void actualizar() {

        Producto seleccionado =
                tablaProductos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {

            mostrarMensaje(
                    "Aviso",
                    "Seleccione un producto de la tabla."
            );

            return;
        }

        try {

            String nombre = txtNombre.getText();
            String categoria = txtCategoria.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            int stock = Integer.parseInt(txtStock.getText());

            boolean actualizado = crud.actualizar(
                    seleccionado.getId(),
                    nombre,
                    categoria,
                    precio,
                    stock
            );

            if (actualizado) {

                tablaProductos.refresh();

                limpiar();

                mostrarMensaje(
                        "Correcto",
                        "Producto actualizado correctamente."
                );
            }

        } catch (NumberFormatException e) {

            mostrarMensaje(
                    "Error",
                    "Ingrese correctamente los datos."
            );
        }
    }

    @FXML
    private void eliminar() {

        Producto seleccionado =
                tablaProductos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {

            mostrarMensaje(
                    "Aviso",
                    "Seleccione un producto de la tabla."
            );

            return;
        }

        crud.eliminar(seleccionado.getId());
        listaProductos.remove(seleccionado);

        limpiar();

        mostrarMensaje(
                "Correcto",
                "Producto eliminado correctamente."
        );
    }

    @FXML
    private void limpiar() {

        txtId.clear();
        txtNombre.clear();
        txtCategoria.clear();
        txtPrecio.clear();
        txtStock.clear();

        tablaProductos.getSelectionModel().clearSelection();
    }

    @FXML
    private void seleccionarProducto() {

        Producto producto =
                tablaProductos.getSelectionModel().getSelectedItem();

        if (producto != null) {

            txtId.setText(
                    String.valueOf(producto.getId())
            );

            txtNombre.setText(
                    producto.getNombre()
            );

            txtCategoria.setText(
                    producto.getCategoria()
            );

            txtPrecio.setText(
                    String.valueOf(producto.getPrecio())
            );

            txtStock.setText(
                    String.valueOf(producto.getStock())
            );
        }
    }

    private void mostrarMensaje(String titulo, String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}