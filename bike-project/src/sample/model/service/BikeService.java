package sample.model.service;

import sample.model.structural.Bike;

import java.util.ArrayList;

public class BikeService {
    private ArrayList<Bike> bikes;
    private static BikeService bikeService;

    private BikeService(){
        bikes = new ArrayList<Bike>();
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
