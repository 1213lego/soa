package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.service.BikeService;
import sample.model.structural.Bike;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteBikeController implements Initializable {

    @FXML
    private TextField txtSearchSerial;

    @FXML
    private TextField txtSerial;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtWeight;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtPurchaseDate;

    private BikeService bikeService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bikeService = BikeService.getInstance();
    }

    @FXML
    void searchBike(ActionEvent event) {
        if(txtFieldIsEmpty(txtSearchSerial)) {
            MainController.showAlert(Alert.AlertType.WARNING,"Information",null,"The serial field can't be empty.");
            return;
        }

        Bike bike = bikeService.findBikeBySerial(txtSearchSerial.getText());
        if(bike == null) {
            MainController.showAlert(Alert.AlertType.ERROR,"Error",null,"Bike not found");
        }
        else {
            fillFieldsBike(bike);
        }
    }

    private boolean txtFieldIsEmpty(TextField textField){
        if(textField.getText()==null || textField.getText().isEmpty()){
            return true;
        }
        textField.setText(textField.getText().trim());
        return false;
    }

    private void fillFieldsBike(Bike bike) {
        txtSerial.setText(bike.getSerial());
        txtType.setText(bike.getType().toString());
        txtBrand.setText(bike.getBrand());
        txtWeight.setText(String.valueOf(bike.getWeight()));
        txtPrice.setText(String.valueOf(bike.getPrice()));
        txtPurchaseDate.setText(BikeService.DATE_FORMAT.format(bike.getPurchaseDate()));
    }

    @FXML
    void deleteBike(ActionEvent event) {
        if(txtFieldIsEmpty(txtSearchSerial)) {
            MainController.showAlert(Alert.AlertType.ERROR,"Error",null,"The serial field can't be empty.");
            return;
        }

        try {
            bikeService.deleteBike(txtSerial.getText());
            MainController.showAlert(Alert.AlertType.INFORMATION,"Information",null,"The bike has been deleted");
            clearFields();
        } catch (Exception e) {
            MainController.showAlert(Alert.AlertType.ERROR,"Error",null, e.getMessage());
        }
    }

    public void clearFields() {
        txtSearchSerial.clear();
        txtSerial.clear();
        txtType.clear();
        txtBrand.clear();
        txtWeight.clear();
        txtPrice.clear();
        txtPurchaseDate.clear();
    }

    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) txtSerial.getScene().getWindow();
        stage.close();
    }
}
