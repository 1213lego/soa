package sample.model.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObservable extends Remote {
    void onDataChange() throws RemoteException;
}
