package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.service.BikeService;
import sample.model.structural.Bike;
import sample.view.IObservable;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class FindBikeController implements Initializable, IObservable {

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
            bikeService = BikeService.getInstance();
            bikeService.addListener(this);
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
        Bike bike = bikeService.findBikeBySerial(serial);
        if(bike == null) {
            clearFieldsBike();
            //MainController.showAlert(Alert.AlertType.ERROR,"Error",null,"Bike not found");
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
        txtPurchaseDate.setText(bike.getPurchaseDate().format(BikeService.formatterDateTime));
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

    @Override
    public void onDataChange() throws RemoteException {
        if(txtFieldIsEmpty(txtSearchSerial)){
            clearFieldsBike();
            return;
        }
        findBike(txtSearchSerial.getText());
    }
}
