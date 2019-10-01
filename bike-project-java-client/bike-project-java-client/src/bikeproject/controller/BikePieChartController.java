package bikeproject.controller;

import bikeproject.model.Bike;
import bikeproject.model.BikeService;
import bikeproject.model.BikeServiceClient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class BikePieChartController implements Initializable {

    private BikeService bikeService;
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    @FXML
    private PieChart pcTypeBike;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            bikeService = BikeServiceClient.BIKE_SERVICE;
            setUpBarChart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpBarChart(){
        pcTypeBike.getData().clear();
        pcTypeBike.setTitle("Type summary");
        List<Bike> bikes = bikeService.getBikes();
        Map<String,Integer> counts = new HashMap<>();
        for (Bike bike: bikes){
            if(!counts.containsKey(bike.getType().toString())){
                counts.put(bike.getType().toString(),1);
            }
            else {
                counts.put(bike.getType().toString(),counts.get(bike.getType().toString())+1);
            }
        }
        counts.forEach((k,v)-> {
            pieChartData.add(new PieChart.Data(k,v));
        });

        pcTypeBike.setData(pieChartData);
    }

    public void refresh(ActionEvent actionEvent) {
        setUpBarChart();
    }
}
