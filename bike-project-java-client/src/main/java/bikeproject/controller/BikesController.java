package bikeproject.controller;

import bikeproject.api.BikeService;
import bikeproject.api.model.Bike;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class BikesController implements Initializable {
    @FXML
    private TextField txtSearchSerial;

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
    private TableColumn<Bike, String> tcPurchaseDate;

    private BikeService bikeService;

    private ObservableList<Bike> observableBikes = FXCollections.observableArrayList();

    private FilteredList<Bike> filteredData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bikeService = new BikeService();
        setPropertiesBikesController();
        loadDataBikes();
        setSettingsSearchBike();
    }

    private void setPropertiesBikesController() {
        tvBikes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tcSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        tcWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tcPurchaseDate.setCellValueFactory(cellData ->
                new SimpleStringProperty(BikeService.dateFormat.format(cellData.getValue().getPurchaseDate())));
    }

    private void loadDataBikes() {
        observableBikes.clear();
        observableBikes.addAll(bikeService.getBikes());
        tvBikes.setItems(observableBikes);
    }

    private void setSettingsSearchBike() {
        filteredData = new FilteredList<>(observableBikes, usuario -> true);
        txtSearchSerial.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(bike -> {
                if(newValue == null || newValue.isEmpty())
                {
                    return true;
                }
                else if(String.valueOf(bike.getSerial()).toLowerCase().contains(newValue.toLowerCase()))
                {
                    return true;
                }

                return false;
            });
        });

        SortedList sort = new SortedList(filteredData);
        sort.comparatorProperty().bind(tvBikes.comparatorProperty());
        tvBikes.setItems(sort);
    }

    @FXML
    void closeWindows(ActionEvent event) {
        Stage stage = (Stage) tvBikes.getScene().getWindow();
        stage.close();
    }

    public void updateList(ActionEvent actionEvent) {
        loadDataBikes();
    }
}
