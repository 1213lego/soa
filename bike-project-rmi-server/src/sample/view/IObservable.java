package sample.view;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObservable extends Remote {
    void onDataChange() throws RemoteException;
}
