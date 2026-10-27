package co.edu.uniquindio.poo.empresajfx.viewController;

import java.net.URL;
import java.util.ResourceBundle;
import co.edu.uniquindio.poo.empresajfx.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button primaryButton;

    private App app; // Referencia a la clase principal App

    // Evento del botón "Gestión de clientes" en la pantalla de bienvenida
    @FXML
    void onOpenCrudCliente() {
        app.openCrudCliente(); // Le pide a App que abra la ventana del CRUD
    }

    // Recibe la instancia de App enviada desde App.java
    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    void initialize() {
        // Se ejecuta automáticamente al cargar el FXML
    }
}