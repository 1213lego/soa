package bikeproject.controller;

import bikeproject.model.Bike;
import bikeproject.model.BikeService;
import bikeproject.model.BikeServiceClient;
import bikeproject.model.Type;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class UpdateBikeController implements Initializable {

    @FXML
    private TextField txtSearchSerial;

    @FXML
    private TextField txtSerial;

    @FXML
    private ComboBox<Type> cbTypes;

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
        Bike bike = bikeService.findBikeBySerial(txtSearchSerial.getText());
        if(bike==null){
            MainController.showAlert(Alert.AlertType.ERROR,"Error",null,"The bike doesn't exist.");
            return;
        }
        txtSerial.setText(bike.getSerial());
        cbTypes.valueProperty().setValue(bike.getType());
        txtBrand.setText(bike.getBrand());
        txtWeight.setText(String.valueOf(bike.getWeight()));
        txtPrice.setText(String.valueOf(bike.getPrice()));
        dpPurchaseDate.valueProperty().setValue(LocalDate.parse(BikeServiceClient.DATE_FORMAT.format(bike.getPurchaseDate().toGregorianCalendar().getTime()),BikeServiceClient.DATE_TIME_FORMATTER));
    }

    @FXML
    void updateBike(ActionEvent event) throws ParseException, DatatypeConfigurationException {
        if(txtFieldIsEmpty(txtBrand) || txtFieldIsEmpty(txtWeight) || txtFieldIsEmpty(txtPrice)
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
        bike.setBrand(txtBrand.getText());
        bike.setWeight(Double.parseDouble(txtWeight.getText()));
        bike.setPrice(Double.parseDouble(txtPrice.getText()));
        Date date = BikeServiceClient.DATE_FORMAT.parse(dpPurchaseDate.getValue().format(BikeServiceClient.DATE_TIME_FORMATTER));
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        XMLGregorianCalendar xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        bike.setPurchaseDate(xmlCalendar);
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
        bikeService = BikeServiceClient.BIKE_SERVICE;
        cbTypes.setItems(FXCollections.observableArrayList(Type.values()));
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
