package sample.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.model.service.BikeService;
import sample.model.structural.Bike;
import java.net.URL;
import java.util.ResourceBundle;

public class SaveBikeController implements Initializable {
    @FXML
    private TextField txtSerial;

    @FXML
    private ComboBox<Bike.Type> cbTypes;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtWeight;

    @FXML
    private TextField txtPrice;

    @FXML
    private DatePicker dpPruchaseDate;

    private BikeService bikeService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbTypes.setItems(FXCollections.observableArrayList(Bike.Type.values()));
        bikeService = BikeService.getBikeService();
    }
    public void saveBike(ActionEvent actionEvent) {
        if(txtFieldIsEmpty(txtSerial) || txtFieldIsEmpty(txtBrand) || txtFieldIsEmpty(txtWeight) || txtFieldIsEmpty(txtPrice)
                || cbTypes.getValue()==null || dpPruchaseDate.getValue()==null){
            MainController.showAlert(Alert.AlertType.WARNING,"Information",null,"You should fill all fields");
            return;
        }
        if(!isDouble(txtPrice.getText(),"Price") || !isDouble(txtWeight.getText(),"Weight")){
            return;
        }
        Bike bike = new Bike(txtSerial.getText(),cbTypes.getValue(),txtBrand.getText(),Double.parseDouble(txtWeight.getText()),Double.parseDouble(txtPrice.getText()),dpPruchaseDate.getValue().atStartOfDay());
        try {
            bikeService.addBike(bike);
            MainController.showAlert(Alert.AlertType.INFORMATION,"successful",null,"The bike with serial " + bike.getSerial()+ " has been saved");
        }
        catch (Exception e){
            MainController.showAlert(Alert.AlertType.ERROR,"Error saving de bike",null,e.getMessage());
        }

    }
    private boolean isDouble(String value,String fieldName){
        try{
            Double.parseDouble(value);
            return true;
        }
        catch (Exception e){
            MainController.showAlert(Alert.AlertType.WARNING,"",null,fieldName + " is a numeric field");
            return false;
        }
    }
    private boolean txtFieldIsEmpty(TextField textField){
        if(textField.getText()==null || textField.getText().isEmpty()){
            return true;
        }
        textField.setText(textField.getText().trim());
        return false;
    }

}
