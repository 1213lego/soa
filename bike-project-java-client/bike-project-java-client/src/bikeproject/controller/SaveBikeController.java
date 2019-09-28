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

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class SaveBikeController implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbTypes.setItems(FXCollections.observableArrayList(Type.values()));
        bikeService = BikeServiceClient.BIKE_SERVICE;
    }
    public void saveBike(ActionEvent actionEvent) throws ParseException, DatatypeConfigurationException {
        if(txtFieldIsEmpty(txtSerial) || txtFieldIsEmpty(txtBrand) || txtFieldIsEmpty(txtWeight) || txtFieldIsEmpty(txtPrice)
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
            bikeService.saveBike(bike);
            MainController.showAlert(Alert.AlertType.INFORMATION,"successful",null,"The bike with serial " + bike.getSerial()+ " has been saved");
        }
        catch (Exception e){
            MainController.showAlert(Alert.AlertType.ERROR,"Error","Error saving the bike",e.getMessage());
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

    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) txtSerial.getScene().getWindow();
        stage.close();
    }

}
