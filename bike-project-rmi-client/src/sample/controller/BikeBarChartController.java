package sample.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import sample.model.service.BikeService;
import sample.model.service.IObservable;
import sample.model.service.ObservableImpl;
import sample.model.structural.Bike;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class BikeBarChartController implements Initializable , IObservable {
    private BikeService bikeService;
    @FXML
    private BarChart<String, Number> barChart;
    private ObservableImpl observable;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            bikeService = BikeService.getInstance();
            observable = new ObservableImpl(this);
            setUpBarChart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setUpBarChart(){
        barChart.getData().clear();
        barChart.setTitle("Type summary");
        barChart.getXAxis().setLabel("Type");
        barChart.getYAxis().setLabel("Value");
        List<Bike> bikes = bikeService.getBikesFromServer();
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

    @Override
    public void onDataChange() throws RemoteException{
    		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				setUpBarChart();
			}
		});
    }
}
