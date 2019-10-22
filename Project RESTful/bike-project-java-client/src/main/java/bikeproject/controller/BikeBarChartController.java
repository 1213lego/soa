package bikeproject.controller;

import bikeproject.api.BikeService;
import bikeproject.api.model.Bike;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class BikeBarChartController implements Initializable  {
    private BikeService bikeService;
    @FXML
    private BarChart<String, Number> barChart;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bikeService = new BikeService();
        setUpBarChart();
    }
    private void setUpBarChart(){
        barChart.getData().clear();
        barChart.setTitle("Type summary");
        barChart.getXAxis().setLabel("Type");
        barChart.getYAxis().setLabel("Value");
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
        XYChart.Series serie = new XYChart.Series();
        counts.forEach((k,v)->serie.getData().add(new XYChart.Data<>(k,v)));
        barChart.getData().add(serie);
    }

    public void refresh(ActionEvent actionEvent) {
        setUpBarChart();
    }
}
