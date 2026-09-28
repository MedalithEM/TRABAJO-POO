package cineplanet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Vector;

public class ProductoController {
    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCategoria;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtStock;
    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, Integer> colId;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, String> colCategoria;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;
    @FXML private Button btnRegistrar;
    @FXML private Button btnActualizar;
    @FXML private Button btnEliminar;
    @FXML private Button btnLimpiar;
    @FXML private TableColumn<Producto, Void>colAccion;


    private ProductoCRUD crud = new ProductoCRUD();

    private ObservableList<Producto> listaProductos =
            FXCollections.observableArrayList();
    private int siguienteId = 6;

    @FXML
    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tablaProductos.setItems(listaProductos);

        colAccion.setCellFactory(param -> new TableCell<Producto,Void>() {

            private final Button btnEliminar = new Button("X");

            {
                btnEliminar.setStyle("-fx-background-color: #e53935;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 3px;" +
                        "-fx-border-radius: 3px;" +
                        "-fx-padding: 4px 8px;"

                );
                btnEliminar.setOnAction(event -> {
                    Producto producto =
                            getTableView().getItems().get(getIndex());

                    crud.eliminar(producto.getId());
                    listaProductos.remove(producto);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnEliminar);
                }
            }
        });


        registrarProductoInicial(new Producto(1, "Canchita Mediana",
                "Canchita", 12.00, 50)
        );

        registrarProductoInicial(new Producto(2, "Gaseosa Inkacola",
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
    private void registrar() {   //metodo

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

            crud.registrar(producto);  //Abstraccion
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