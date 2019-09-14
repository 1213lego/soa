package sample.model.service;

import sample.model.structural.Bike;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

public interface IBikeService extends Remote {
    public void saveBike(Bike bike) throws Exception;
    public void deleteBike(String serial) throws Exception;
    public void updateBike(Bike bikeUpdate) throws Exception;
    public Bike findBikeBySerial(String serial) throws Exception;
    public void addListener(IObservable iObservable) throws Exception;
    public void removeListener(IObservable iObservable) throws Exception;
    public List<Bike> getBikes() throws Exception;
}
