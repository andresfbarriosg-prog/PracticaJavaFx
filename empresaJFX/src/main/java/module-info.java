module co.edu.uniquindio.poo.empresajfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.poo.empresajfx to javafx.fxml;
    exports co.edu.uniquindio.poo.empresajfx;

    opens co.edu.uniquindio.poo.empresajfx.viewController to javafx.fxml;
    exports co.edu.uniquindio.poo.empresajfx.viewController;

    opens co.edu.uniquindio.poo.empresajfx.controller to javafx.fxml;
    exports co.edu.uniquindio.poo.empresajfx.controller;

    opens co.edu.uniquindio.poo.empresajfx.model to javafx.fxml;
    exports co.edu.uniquindio.poo.empresajfx.model;
}