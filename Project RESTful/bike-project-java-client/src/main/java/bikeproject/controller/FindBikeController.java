package bikeproject.controller;

import bikeproject.api.BikeService;
import bikeproject.api.model.Bike;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class FindBikeController implements Initializable {

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
        try {
            bikeService = new BikeService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchBike(ActionEvent event) {
        if(txtFieldIsEmpty(txtSearchSerial)){
            clearFieldsBike();
            return;
        }
        findBike(txtSearchSerial.getText());
    }

    private void findBike(String serial) {
        try {
            Bike bike = bikeService.findBikeBySerial(serial);
            if(bike == null) {
                clearFieldsBike();
                //MainController.showAlert(Alert.AlertType.ERROR,"Error",null,"Bike not found");
            }
            else {
                fillFieldsBike(bike);
            }
        }
        catch (Exception e){
            MainController.showAlert(Alert.AlertType.ERROR,"Error","",e.getMessage());
            clearFieldsBike();
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
        txtPurchaseDate.setText(BikeService.dateFormat.format(bike.getPurchaseDate()));
    }

    private void clearFieldsBike() {
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
