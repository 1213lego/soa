package sample.model.service;

import sample.model.structural.Bike;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BikeService {
    public static final String SERVER_IP= "192.168.0.27";
    public final static DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd - hh:mm");
    public final static DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private List<Bike> bikes;
    private static BikeService instance;
    private IBikeService iBikeService;

    private BikeService() throws Exception {
        iBikeService = (IBikeService) Naming.lookup("//"+SERVER_IP+"/bike");
        System.out.println("Conexion exitosa");
        bikes = new ArrayList();
        loadBikesServer();
    }
    private void loadBikesServer() throws Exception {
        bikes = iBikeService.getBikes();
    }
    public static BikeService getInstance() throws Exception {
        if(instance == null){
            instance = new BikeService();
        }
        return instance;
    }
    public void saveBike(Bike bike) throws Exception {
        try{
            iBikeService.saveBike(bike);
        }
        catch (RemoteException remoteException){
            throw new Exception("Se ha perdido la conexion con el servidor \n" + remoteException.getMessage());
        }
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
    public List <Bike> getBikesFromServer() {
        try {
            bikes = iBikeService.getBikes();
        } catch (Exception e) {
            System.out.println("Error obteniendo la lista del servidor");
            e.printStackTrace();
        }
        return bikes;
    }
    public List<Bike> getLocalBikes(){
        return bikes;
    }

    public void addListener(IObservable iObservable) throws Exception {
        if(iObservable==null) return;
        iBikeService.addListener(iObservable);
    }

    public void removeListener(IObservable iObservable) throws Exception {
        if(iObservable==null) return;
        iBikeService.removeListener(iObservable);
    }
}
