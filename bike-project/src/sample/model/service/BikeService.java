package sample.model.service;

import sample.model.structural.Bike;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BikeService {
    private ArrayList<Bike> bikes;
    private static BikeService bikeService;

    private BikeService(){
        bikes = new ArrayList<Bike>();
        LocalDateTime asd = LocalDateTime.now();
        DateTimeFormatter iso = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        Bike bike = new Bike("dsasa", Bike.Type.MOUNTAIN, "asdsa", 2343, 234324, asd);
        try {
            addBike(bike);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(asd.format(iso));
    }

    public static BikeService getBikeService(){
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
    }
    public void deleteBike(String serial) throws Exception {
        Bike bike = findBikeBySerial(serial);
        if(bike == null) {
            throw new Exception("Bike not found with this serial: " + serial);
        }

        bikes.remove(bike);
    }
    public void update(String serial, Bike bikeUpdate)throws Exception{
        int bikeIndex = findBikeIndex(serial);
        if(bikeIndex==-1){
            throw new Exception("Bike not found with this serial: " + serial);
        }
        bikeUpdate.setSerial(serial);
        bikes.set(bikeIndex,bikeUpdate);

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
}
