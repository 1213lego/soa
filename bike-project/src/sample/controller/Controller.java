package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import sample.BikeProjectMain;

public class Controller {
    @FXML
    public void onClickAbout(ActionEvent actionEvent)  {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Bike App");
        alert.setHeaderText(null);
        alert.setContentText("Application developed by Luis Granada and Gabriel Montalvo");
        alert.showAndWait();
    }

    public void quit(ActionEvent actionEvent) {
        BikeProjectMain.launchNewWindows("test");
    }
}
