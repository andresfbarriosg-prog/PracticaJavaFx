package co.edu.uniquindio.poo.practica.viewController;

import co.edu.uniquindio.poo.practica.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {

    private App app;

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    void onOpenCrudInventario(ActionEvent event) {
        if (app != null) {
            app.openViewInventario();
        }
    }
}