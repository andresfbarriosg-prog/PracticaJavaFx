package co.edu.uniquindio.poo.practica.viewController;

import co.edu.uniquindio.poo.practica.App;
import co.edu.uniquindio.poo.practica.controller.ProductoController;
import co.edu.uniquindio.poo.practica.model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Optional;

public class ProductoViewController {

    @FXML private TableColumn<Producto, Integer> tbcCantidad;
    @FXML private TableColumn<Producto, String> tbcCodigo;
    @FXML private TableColumn<Producto, String> tbcNombre;
    @FXML private TableColumn<Producto, Double> tbcPrecioUnitario;
    @FXML private TableView<Producto> tblProductos;

    @FXML private TextField txtCantidad;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecioUnitario;

    private App app;
    private ProductoController productoController;
    private ObservableList<Producto> listaProductos = FXCollections.observableArrayList();

    public void setApp(App app) {
        this.app = app;
        this.productoController = new ProductoController(App.inventario);
        actualizarTabla();
    }

    @FXML
    public void initialize() {
        tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        tblProductos.setItems(listaProductos);

        tblProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtCodigo.setText(newSel.getCodigo());
                txtNombre.setText(newSel.getNombre());
                txtPrecioUnitario.setText(String.valueOf(newSel.getPrecio()));
                txtCantidad.setText(String.valueOf(newSel.getCantidad()));
            }
        });
    }

    @FXML
    void onAgregarProducto(ActionEvent event) {
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        double precio = Double.parseDouble(txtPrecioUnitario.getText());
        int cantidad = Integer.parseInt(txtCantidad.getText());

        Producto producto = new Producto(nombre, codigo, precio, cantidad);
        productoController.crearProducto(producto);

        actualizarTabla();
        onLimpiar(event);
    }

    @FXML
    void onActualizarProducto(ActionEvent event) {
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        double precio = Double.parseDouble(txtPrecioUnitario.getText());

        productoController.actualizarProducto(codigo, precio, nombre);

        actualizarTabla();
        onLimpiar(event);
    }

    @FXML
    void onEliminarProducto(ActionEvent event) {
        String codigo = txtCodigo.getText();

        productoController.eliminarProducto(codigo);

        actualizarTabla();
        onLimpiar(event);
    }

    @FXML
    void onBuscarProducto(ActionEvent event) {
        String codigo = txtCodigo.getText();

        Optional<Producto> buscado = productoController.buscarProducto(codigo);
        if (buscado.isPresent()) {
            tblProductos.getSelectionModel().select(buscado.get());
        }
    }

    @FXML
    void onLimpiar(ActionEvent event) {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecioUnitario.clear();
        txtCantidad.clear();
        tblProductos.getSelectionModel().clearSelection();
    }

    private void actualizarTabla() {
        if (productoController != null) {
            listaProductos.setAll(productoController.obtenerProducto());
            tblProductos.refresh();
        }
    }
}