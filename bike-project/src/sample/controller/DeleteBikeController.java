package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sample.model.service.BikeService;

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

    @FXML
    void closeWindow(ActionEvent event) {

    }

    @FXML
    void deleteBike(ActionEvent event) {

    }

    @FXML
    void searchBike(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
