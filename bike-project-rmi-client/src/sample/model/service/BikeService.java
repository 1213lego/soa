package sample.model.service;

import sample.model.structural.Bike;
import sample.view.IObservable;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BikeService implements IObservable{

    private static final String SERVER_IP= "10.30.1.115";

    public final static DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd - hh:mm");
    public final static DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static BikeService instance;
    private List<Bike> bikes;
    private IBikeService iBikeService;
    //Local Listeners
    private List<IObservable> listeners;
    private ObservableImpl observable;
    private BikeService() throws Exception {
        iBikeService = (IBikeService) Naming.lookup("//"+SERVER_IP+"/bike");
        System.out.println("Se ha establecido la conexio con el servidor");
        bikes = new ArrayList();
        listeners = new ArrayList();
        bikes = iBikeService.getBikes();
    }
    public static BikeService getInstance() throws Exception {
        if(instance == null){
            instance = new BikeService();
        }
        return instance;
    }
    public void saveBike(Bike bike) throws Exception {
        iBikeService.saveBike(bike);
    }
    public void deleteBike(String serial) throws Exception {
        iBikeService.deleteBike(serial);
    }
    public void updateBike(Bike bikeUpdate) throws Exception {
        iBikeService.updateBike(bikeUpdate);
    }
    public Bike findBikeBySerial(String serial){
        for (Bike bike: bikes) {
            if(bike.getSerial().equals(serial)){
                return bike;
            }
        }
        return null;
    }
    public List <Bike> getBikes(){
        return bikes;
    }
    public void addListener(IObservable iObservable) throws Exception {
        if(iObservable==null) return;
        listeners.add(iObservable);
    }
    public void removeListener(IObservable iObservable) throws Exception {
        if(iObservable==null) return;
        listeners.remove(iObservable);
    }
    @Override
    public void onDataChange() throws RemoteException {
        System.out.println("dataChange");
        bikes = iBikeService.getBikes();
        //Observables locales
        for(IObservable listener: listeners){
            listener.onDataChange();
        }
    }
    //Empieza a esta a la escucha de los cambios remotos
    public void startListening() throws Exception {
        if(observable == null){
            observable = new ObservableImpl(iBikeService,this);
        }
    }
}
