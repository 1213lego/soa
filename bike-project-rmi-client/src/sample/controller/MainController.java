package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import sample.BikeProjectMain;
import sample.model.service.BikeService;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public void quit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void openWindowSaveBike(ActionEvent actionEvent) {
        BikeProjectMain.launchNewWindows("addBike", "New Bike");
    }

    @FXML
    void openWindowFindBike(ActionEvent event) {
        BikeProjectMain.launchNewWindows("findBike", "Find Bike");
    }

    @FXML
    public void openWindowDeleteBike(ActionEvent event) {
        BikeProjectMain.launchNewWindows("deleteBike", "Delete Bike");
    }

    @FXML
    public void openWindowBikeList(ActionEvent actionEvent) {
        BikeProjectMain.launchNewWindows("bikes", "Bike List");
    }

    @FXML
    public void onClickAbout(ActionEvent actionEvent)  {
        showAlert(Alert.AlertType.INFORMATION,"About Bike App",null,"Application developed by Luis Granada and Gabriel Montalvo");
    }

    public static void showAlert(Alert.AlertType alertType,String title,String header, String content){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void openWindowUpdateBike(ActionEvent actionEvent) {
        BikeProjectMain.launchNewWindows("updateBike","Update Bike");
    }

    public void openBikeChart(ActionEvent actionEvent) {
        BikeProjectMain.launchNewWindows("barChart","Bike chart");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            BikeService.getInstance().startListening();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Error","No se puede inicar la aplicacion, no hay conexion con el servidor",e.getMessage());
            System.exit(0);
        }
    }
}
