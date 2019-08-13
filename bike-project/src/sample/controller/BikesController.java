package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.service.BikeService;
import sample.model.structural.Bike;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class BikesController implements Initializable {

    @FXML
    private TableView<Bike> tvBikes;

    @FXML
    private TableColumn<Bike, String> tcSerial;

    @FXML
    private TableColumn<Bike, String> tcType;

    @FXML
    private TableColumn<Bike, String> tcBrand;

    @FXML
    private TableColumn<Bike, Double> tcWeight;

    @FXML
    private TableColumn<Bike, Double> tcPrice;

    @FXML
    private TableColumn<Bike, LocalDateTime> tcPurchaseDate;

    private BikeService bikeService;

    private ObservableList<Bike> observableBikes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bikeService = BikeService.getBikeService();
        setPropertiesBikesController();
        loadDataBikes();
    }

    private void setPropertiesBikesController() {
        tcSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        tcWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tcPurchaseDate.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
    }

    private void loadDataBikes() {
        observableBikes.clear();
        observableBikes.addAll(bikeService.getBikes());
        tvBikes.setItems(observableBikes);
    }

    @FXML
    void closeCurrentWindow(ActionEvent event) {
        System.exit(0);
    }
}
