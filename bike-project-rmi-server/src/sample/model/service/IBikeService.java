package sample.model.service;

import sample.model.structural.Bike;
import sample.view.IObservable;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IBikeService extends Remote {
    public void saveBike(Bike bike) throws RemoteException;
    public void deleteBike(String serial) throws RemoteException;
    public void updateBike(Bike bikeUpdate) throws RemoteException;
    public Bike findBikeBySerial(String serial) throws RemoteException;
    public void addListener(IObservable iObservable) throws RemoteException;
    public void removeListener(IObservable iObservable) throws RemoteException;
    public List<Bike> getBikes() throws RemoteException;
}
