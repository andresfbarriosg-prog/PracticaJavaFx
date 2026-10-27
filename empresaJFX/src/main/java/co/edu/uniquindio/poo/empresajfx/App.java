package co.edu.uniquindio.poo.empresajfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

import co.edu.uniquindio.poo.empresajfx.model.Cliente;
import co.edu.uniquindio.poo.empresajfx.model.Empresa;
import co.edu.uniquindio.poo.empresajfx.viewController.ClienteViewController;
import co.edu.uniquindio.poo.empresajfx.viewController.PrimaryController;

/**
 * Clase principal de la aplicación JavaFX
 */
public class App extends Application {

    private Stage primaryStage; // Ventana principal física

    // Instancia única del modelo que compartirá toda la aplicación
    public static Empresa empresa = new Empresa("UQ");

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestión de Clientes");

        // 1. Cargar datos iniciales de prueba
        inicializarData();

        // 2. Abrir la pantalla de bienvenida
        openViewPrincipal();
    }

    // Carga la pantalla principal (primary.fxml)
    private void openViewPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("primary.fxml"));
            VBox rootLayout = (VBox) loader.load();

            // Conectamos el controlador con la instancia de App
            PrimaryController primaryController = loader.getController();
            primaryController.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carga la pantalla del CRUD (crudCliente.fxml)
    public void openCrudCliente() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("crudCliente.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();

            // Conectamos el controlador del CRUD con la instancia de App
            ClienteViewController clienteViewController = loader.getController();
            clienteViewController.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Agrega un cliente por defecto para verificar que la lista no inicie vacía
    public void inicializarData() {
        Cliente cliente = new Cliente("12233", "Juan", "Gómez");
        empresa.crearCliente(cliente);
    }

    public static void main(String[] args) {
        launch(); // Arranca el motor de JavaFX
    }
}