package sample.model.service;

import sample.controller.IObservable;
import sample.model.structural.Bike;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BikeService {
    public final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd - hh:mm");

    private ArrayList<Bike> bikes;
    private ArrayList<IObservable> listeners;
    private static BikeService bikeService;

    private BikeService(){
        bikes = new ArrayList();
        listeners = new ArrayList();
    }

    public static BikeService getInstance(){
        if(bikeService == null){
            bikeService = new BikeService();
        }
        return bikeService;
    }

    public void addBike(Bike bike) throws Exception {
        if(findBikeBySerial(bike.getSerial())!=null){
            throw new Exception("Duplicate bike serial");
        }
        bikes.add(bike);
        notifyListeners();
    }
    public void deleteBike(String serial) throws Exception {
        Bike bike = findBikeBySerial(serial);
        if(bike == null) {
            throw new Exception("Bike not found with this serial: " + serial);
        }

        bikes.remove(bike);
        notifyListeners();
    }
    public void update(String serial, Bike bikeUpdate)throws Exception{
        int bikeIndex = findBikeIndex(serial);
        if(bikeIndex==-1){
            throw new Exception("Bike not found with this serial: " + serial);
        }
        bikeUpdate.setSerial(serial);
        bikes.set(bikeIndex,bikeUpdate);
        notifyListeners();
    }
    private int findBikeIndex(String serial){
        for(int i=0;i<bikes.size();i++){
            if(serial.equals(bikes.get(i).getSerial())){
                return i;
            }
        }
        return -1;
    }
    public Bike findBikeBySerial(String serial){
        for (Bike bike: bikes) {
            if(bike.getSerial().equals(serial)){
                return bike;
            }
        }
        return null;
    }
    public ArrayList <Bike> getBikes(){
        return bikes;
    }
    public void addListener(IObservable iObservable){
        listeners.add(iObservable);
    }
    private void notifyListeners(){
        for(IObservable iObservable: listeners){
            iObservable.onDataChange();
        }
    }
}
