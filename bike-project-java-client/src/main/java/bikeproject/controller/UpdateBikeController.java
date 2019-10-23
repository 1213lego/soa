package bikeproject.controller;

import bikeproject.api.BikeService;
import bikeproject.api.model.Bike;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.xml.datatype.DatatypeConfigurationException;
public class UpdateBikeController implements Initializable {

    @FXML
    private TextField txtSearchSerial;

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
    private DatePicker dpPurchaseDate;
    private BikeService bikeService;

    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) dpPurchaseDate.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void searchBike(ActionEvent event) {
        try{
            Bike bike = bikeService.findBikeBySerial(txtSearchSerial.getText());
            if(bike==null){
                MainController.showAlert(Alert.AlertType.ERROR,"Error",null,"The bike doesn't exist.");
                return;
            }
            txtSerial.setText(bike.getSerial());
            cbTypes.valueProperty().setValue(bike.getType());
            txtWeight.setText(String.valueOf(bike.getWeight()));
            txtPrice.setText(String.valueOf(bike.getPrice()));
            dpPurchaseDate.valueProperty().setValue(LocalDate.parse(BikeService.dateFormat.format(bike.getPurchaseDate()), BikeService.dateTimeFormatter));
        }
        catch (Exception e){
            MainController.showAlert(Alert.AlertType.ERROR,"Error","",e.getMessage());
        }
    }

    @FXML
    void updateBike(ActionEvent event) throws ParseException, DatatypeConfigurationException {
        if(txtFieldIsEmpty(txtSerial) || txtFieldIsEmpty(txtPrice)
                || cbTypes.getValue()==null || dpPurchaseDate.getValue()==null){
            MainController.showAlert(Alert.AlertType.WARNING,"Information",null,"You should fill all fields");
            return;
        }
        if(!isDouble(txtPrice.getText(),"Price") || !isDouble(txtWeight.getText(),"Weight")){
            return;
        }
        Bike bike = new Bike();
        bike.setSerial(txtSerial.getText());
        bike.setType(cbTypes.getValue());
        bike.setWeight(Double.parseDouble(txtWeight.getText()));
        bike.setPrice(Double.parseDouble(txtPrice.getText()));
        bike.setPurchaseDate(BikeService.dateFormat.parse(BikeService.dateTimeFormatter.format(dpPurchaseDate.getValue())));
        try {
            bikeService.updateBike(bike);
            MainController.showAlert(Alert.AlertType.INFORMATION,"successful",null,"The bike with serial " + bike.getSerial()+ " has been updated");
        }
        catch (Exception e){
            MainController.showAlert(Alert.AlertType.ERROR,"Error","Error updating the bike",e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bikeService = new BikeService();
        cbTypes.setItems(FXCollections.observableArrayList(Bike.Type.values()));
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
