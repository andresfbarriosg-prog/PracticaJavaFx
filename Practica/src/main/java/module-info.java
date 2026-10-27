module co.edu.uniquindio.poo.practica {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.poo.practica to javafx.fxml;
    opens co.edu.uniquindio.poo.practica.viewController to javafx.fxml;
    opens co.edu.uniquindio.poo.practica.model to javafx.base;

    exports co.edu.uniquindio.poo.practica;
}