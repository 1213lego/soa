package model;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObservable extends Remote {
    void onChange() throws RemoteException;
}
