package sample.model.service;

import sample.model.structural.Bike;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BikeService {
    public final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd - hh:mm");

    private ArrayList<Bike> bikes;
    private static BikeService bikeService;

    private BikeService(){
        bikes = new ArrayList<Bike>();
        LocalDateTime asd = LocalDateTime.now();
        DateTimeFormatter iso = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        Bike bike = new Bike("asd", Bike.Type.MOUNTAIN, "asdsa", 2343, 234324, asd);
        Bike bike1 = new Bike("pgh", Bike.Type.MOUNTAIN, "asdsa", 2343, 234324, asd);
        Bike bike2 = new Bike("asd1", Bike.Type.MOUNTAIN, "asdsa", 2343, 234324, asd);
        Bike bike3 = new Bike("ph2", Bike.Type.MOUNTAIN, "asdsa", 2343, 234324, asd);
        try {
            addBike(bike);
            addBike(bike1);
            addBike(bike2);
            addBike(bike3);
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
