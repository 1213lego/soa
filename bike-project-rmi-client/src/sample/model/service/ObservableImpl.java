package sample.model.service;

import sample.view.IObservable;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObservableImpl extends UnicastRemoteObject implements IObservable, Serializable {
    private transient IObservable iObservable;
    private transient IBikeService iBikeService;
    public ObservableImpl(IBikeService iBikeService,IObservable iObservable) throws Exception {
        this.iBikeService = iBikeService;
        this.iObservable = iObservable;
        iBikeService.addListener(this);
    }
    @Override
    public void onDataChange() throws RemoteException {
        iObservable.onDataChange();
    }

}
