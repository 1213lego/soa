package bikeproject.controller;

import bikeproject.api.BikeService;
import bikeproject.api.model.Bike;
import bikeproject.api.model.BikeResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
        txtBrand.setEditable(false);
        bikeService = new BikeService();
    }

    @FXML
    void searchBike(ActionEvent event) {
        if(txtFieldIsEmpty(txtSearchSerial)) {
            MainController.showAlert(Alert.AlertType.WARNING,"Information",null,"The serial field can't be empty.");
            return;
        }
        try{
            Bike bike = bikeService.findBikeBySerial(txtSearchSerial.getText());
            if(bike == null) {
                MainController.showAlert(Alert.AlertType.ERROR,"Error",null,"Bike not found");
                clearFields();
            }
            else {
                fillFieldsBike(bike);
            }
        }
        catch (Exception e){
            MainController.showAlert(Alert.AlertType.ERROR,"Error","",e.getMessage());
            clearFields();
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
        txtWeight.setText(String.valueOf(bike.getWeight()));
        txtPrice.setText(String.valueOf(bike.getPrice()));
        txtPurchaseDate.setText(BikeService.dateFormat.format(bike.getPurchaseDate()));
    }

    @FXML
    void deleteBike(ActionEvent event) {
        if(txtFieldIsEmpty(txtSearchSerial)) {
            MainController.showAlert(Alert.AlertType.ERROR,"Error",null,"The serial field can't be empty.");
            return;
        }
        try {
            BikeResponse bikeResponse = bikeService.deleteBikeBySerial(txtSerial.getText());
            MainController.showAlert(Alert.AlertType.INFORMATION,"Information",null,bikeResponse.getMessage());
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
