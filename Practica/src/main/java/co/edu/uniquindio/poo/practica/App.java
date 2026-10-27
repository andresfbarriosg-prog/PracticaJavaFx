package co.edu.uniquindio.poo.practica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

import co.edu.uniquindio.poo.practica.model.Inventario;
import co.edu.uniquindio.poo.practica.model.Producto;
import co.edu.uniquindio.poo.practica.viewController.PrimaryController;
import co.edu.uniquindio.poo.practica.viewController.ProductoViewController;

/**
 * Clase principal de la aplicación JavaFX
 */
public class App extends Application {

    private Stage primaryStage; // Ventana principal física

    // Instancia única del modelo que compartirá toda la aplicación
    public static Inventario inventario = new Inventario("UQ");

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestión de Inventario");

        // 1. Cargar datos iniciales de prueba
        inicializarData();

        // 2. Abrir la pantalla de bienvenida
        openViewPrincipal();
    }

    // Carga la pantalla principal (primary.fxml)
    public void openViewPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();

            // PRUEBA: si imprime null, el archivo no está en el classpath
            System.out.println(App.class.getResource("/primary.fxml"));

            loader.setLocation(App.class.getResource("/primary.fxml"));
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

    // Carga la pantalla del CRUD (inventarioView.fxml)
    public void openViewInventario() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/inventarioView.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();

            // Conectamos el controlador del CRUD con la instancia de App
            ProductoViewController productoViewController = loader.getController();
            productoViewController.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Agrega un producto por defecto para verificar que la lista no inicie vacía
    public void inicializarData() {
        Producto producto = new Producto("Arroz", "P001", 3500.0, 10);
        inventario.crearProducto(producto);
    }

    public static void main(String[] args) {
        launch(); // Arranca el motor de JavaFX
    }
}