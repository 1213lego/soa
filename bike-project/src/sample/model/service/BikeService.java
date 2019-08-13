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
        System.out.println(asd.format(iso));
    }

    public static BikeService getBikeService(){
        if(bikeService == null){
            bikeService = new BikeService();
        }
        return bikeService;
    }

    public void addBike(Bike bike) {
        bikes.add(bike);
    }

    public ArrayList <Bike> getBikes(){
        return bikes;
    }
}
