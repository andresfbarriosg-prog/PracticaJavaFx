package co.edu.uniquindio.poo.empresajfx.viewController;

import java.net.URL;
import java.util.ResourceBundle;
import co.edu.uniquindio.poo.empresajfx.App;
import co.edu.uniquindio.poo.empresajfx.controller.ClienteController;
import co.edu.uniquindio.poo.empresajfx.model.Cliente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClienteViewController {

    private App app;
    private ClienteController clienteController;
    private ObservableList<Cliente> listClientes = FXCollections.observableArrayList(); // Lista reactiva para la tabla
    private Cliente selectedCliente; // Guarda el cliente seleccionado actualmente

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    // Campos de texto
    @FXML private TextField txtCedula;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;

    // Tabla y columnas
    @FXML private TableView<Cliente> tblListCliente;
    @FXML private TableColumn<Cliente, String> tbcCedula;
    @FXML private TableColumn<Cliente, String> tbcNombre;
    @FXML private TableColumn<Cliente, String> tbcApellido;

    // Botones
    @FXML private Button btbAgregarCliente;
    @FXML private Button btnActualizarCliente;
    @FXML private Button btnEliminar;
    @FXML private Button btnLimpiar;

    // --- MÉTODOS ASOCIADOS A BOTONES (#OnAction) ---

    @FXML
    void onAgregarCliente() {
        agregarCliente();
    }

    @FXML
    void onActualizarCliente() {
        actualizarCliente();
    }

    @FXML
    void onEliminar() {
        eliminarCliente();
    }

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void initialize() {
    }

    // Recibe la instancia de App e inicializa la vista
    public void setApp(App app) {
        this.app = app;
        this.clienteController = new ClienteController(app.empresa); // Instancia el intermediario con el modelo
        initView();
    }

    private void initView() {
        initDataBinding();    // Enlaza atributos de Cliente con columnas de la tabla
        obtenerClientes();    // Carga los clientes guardados en el modelo
        tblListCliente.getItems().clear();
        tblListCliente.setItems(listClientes); // Conecta la ObservableList con la TableView
        listenerSelection();  // Activa la escucha de clics sobre la tabla
    }

    // Configura qué atributo del objeto Cliente muestra cada columna
    private void initDataBinding() {
        tbcCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
        tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbcApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
    }

    private void obtenerClientes() {
        listClientes.addAll(clienteController.obtenerListaClientes());
    }

    // Detecta cuándo el usuario selecciona una fila de la tabla
    private void listenerSelection() {
        tblListCliente.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedCliente = newSelection;
            mostrarInformacionCliente(selectedCliente);
        });
    }

    // Pasa los datos del cliente seleccionado a las cajas de texto
    private void mostrarInformacionCliente(Cliente cliente) {
        if (cliente != null) {
            txtCedula.setText(cliente.getCedula());
            txtNombre.setText(cliente.getNombre());
            txtApellido.setText(cliente.getApellido());
        }
    }

    // --- ACCIONES CRUD ---

    private void agregarCliente() {
        Cliente cliente = buildCliente();
        if (clienteController.crearCliente(cliente)) {
            listClientes.add(cliente); // La ObservableList actualiza la tabla automáticamente
            limpiarCamposCliente();
        }
    }

    private void actualizarCliente() {
        if (selectedCliente != null &&
                clienteController.actualizarCliente(selectedCliente.getCedula(), txtNombre.getText(), txtApellido.getText())) {

            selectedCliente.setNombre(txtNombre.getText());
            selectedCliente.setApellido(txtApellido.getText());

            tblListCliente.refresh(); // Refresca la vista de la tabla
            limpiarSeleccion();
        }
    }

    private void eliminarCliente() {
        if (selectedCliente != null && clienteController.eliminarCliente(selectedCliente.getCedula())) {
            listClientes.remove(selectedCliente);
            limpiarSeleccion();
        }
    }

    private Cliente buildCliente() {
        return new Cliente(txtCedula.getText(), txtNombre.getText(), txtApellido.getText());
    }

    private void limpiarSeleccion() {
        tblListCliente.getSelectionModel().clearSelection();
        limpiarCamposCliente();
    }

    private void limpiarCamposCliente() {
        txtCedula.clear();
        txtNombre.clear();
        txtApellido.clear();
    }
}