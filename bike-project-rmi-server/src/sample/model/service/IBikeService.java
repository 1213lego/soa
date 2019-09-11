package sample.model.service;

import sample.model.structural.Bike;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IBikeService extends Remote {
    public void saveBike(Bike bike) throws Exception;
    public void deleteBike(String serial) throws Exception;
    public void updateBike(String serial, Bike bikeUpdate) throws Exception;
    public Bike findBikeBySerial(String serial) throws Exception;
    public void addListener(IObservable iObservable) throws Exception;
    public void removeListener(IObservable iObservable) throws Exception;
    public ArrayList<Bike> getBikes() throws Exception;
}
