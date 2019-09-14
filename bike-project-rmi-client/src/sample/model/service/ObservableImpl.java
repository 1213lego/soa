package sample.model.service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObservableImpl extends UnicastRemoteObject implements IObservable, Serializable {
    private transient IObservable iObservable;
    public ObservableImpl(IObservable iObservable) throws Exception {
        this.iObservable = iObservable;
        BikeService.getInstance().addListener(this);
    }
    @Override
    public void onDataChange() throws RemoteException {
        iObservable.onDataChange();
    }
    public void removeListener(){
        try {
            BikeService.getInstance().removeListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
