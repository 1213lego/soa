package sample.model.service;

import sample.model.db.ConnectionDb;
import sample.model.structural.Bike;
import sample.view.IObservable;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BikeServiceImpl extends UnicastRemoteObject implements IBikeService{
    private final static Logger LOGGER = Logger.getLogger(BikeServiceImpl.class.getName());
    private ArrayList<Bike> bikes;
    private ArrayList<IObservable> listeners;
    private static BikeServiceImpl bikeService;
    private ConnectionDb connectionDb;

    private BikeServiceImpl() throws RemoteException {
        connectionDb = ConnectionDb.getInstance();
        bikes = new ArrayList();
        listeners = new ArrayList();
        loadBikesFromDb();
    }
    public static BikeServiceImpl getInstance() throws Exception {
        if(bikeService == null){
            bikeService = new BikeServiceImpl();
        }
        return bikeService;
    }
    private void loadBikesFromDb() {
        ResultSet rs = connectionDb.executeQueryStatement("select * from bike;");
        if(rs==null){
            return;
        }
        try{
            while (rs.next()){
                Bike bike = new Bike();
                bike.setSerial(rs.getString(1));
                bike.setType(Bike.Type.valueOf(rs.getString(2)));
                bike.setBrand(rs.getString(3));
                bike.setWeight(Double.parseDouble(rs.getString(4)));
                bike.setPrice(Double.parseDouble(rs.getString(5)));
                bike.setPurchaseDate(rs.getDate(6).toLocalDate().atStartOfDay());
                bikes.add(bike);
            }
            bikes.forEach(bike -> System.out.println(bike));
        }
        catch (Exception e){
            LOGGER.log(Level.SEVERE, "", e);
        }
    }
    public void saveBike(Bike bike) throws RemoteException {
        addBikeInMemory(bike);
        saveBikeInDb(bike);
        notifyListeners();
    }
    private void addBikeInMemory(Bike bike) throws RemoteException {
        if(findBikeBySerial(bike.getSerial())!=null){
            throw new RemoteException("Duplicate bike serial",new Exception("Duplicate bike serial"));
        }
        bikes.add(bike);
    }
    private void saveBikeInDb(Bike bike)throws RemoteException {
        boolean result = connectionDb.executeUpdateStatement(bike.save());
        if(result==false){
            throw new RemoteException("Bike not save in db, duplicate serial", new Exception("Bike not save in db, duplicate serial"));
        }
    }
    public void deleteBike(String serial) throws RemoteException {
        deleteBikeInMemory(serial);
        deleteFromDb(serial);
        notifyListeners();
    }
    private void deleteBikeInMemory(String serial) throws RemoteException {
        Bike bike = findBikeBySerial(serial);
        if(bike == null) {
            throw new RemoteException("Bike not found with this serial: " + serial,new Exception("Bike not found with this serial: " + serial));
        }
        bikes.remove(bike);
    }
    private void deleteFromDb(String  serial) throws RemoteException {
        boolean result = connectionDb.executeUpdateStatement("delete from bike where serial='"+serial+"';");
        if(result==false){
            throw new RemoteException("Bike not save in db, serial not found",new Exception("Bike not save in db, serial not found"));
        }
    }

    public void updateBike(Bike bikeUpdate) throws RemoteException {
        updateInMemory(bikeUpdate);
        updateBikeFromDB(bikeUpdate);
        notifyListeners();
    }

    private void updateInMemory(Bike bikeUpdate)throws RemoteException{
        int bikeIndex = findBikeIndex(bikeUpdate.getSerial());
        if(bikeIndex==-1){
            throw new RemoteException("Bike not found with this serial: " + bikeUpdate.getSerial(),new Exception("Bike not found with this serial: " + bikeUpdate.getSerial()));
        }
        bikeUpdate.setSerial(bikeUpdate.getSerial());
        bikes.set(bikeIndex,bikeUpdate);
        notifyListeners();
    }

    private void updateBikeFromDB(Bike bikeUpdate) throws RemoteException {
        boolean result = connectionDb.executeUpdateStatement(bikeUpdate.update());
        if(result==false){
            throw new RemoteException("Bike not update in db, serial not found",new Exception("Bike not update in db, serial not found"));
        }
    }

    private int findBikeIndex(String serial){
        for(int i=0;i<bikes.size();i++){
            if(serial.equals(bikes.get(i).getSerial())){
                return i;
            }
        }
        return -1;
    }
    public Bike findBikeBySerial(String serial)throws RemoteException{
        for (Bike bike: bikes) {
            if(bike.getSerial().equals(serial)){
                return bike;
            }
        }
        return null;
    }
    public List<Bike> getBikes()throws RemoteException
    {
        return bikes;
    }
    public void addListener(IObservable iObservable)throws RemoteException{
        listeners.add(iObservable);
    }
    public void removeListener(IObservable iObservable)throws RemoteException{
        listeners.remove(iObservable);
    }
    private void notifyListeners(){
        listeners.removeIf((iObservable -> {
            try {
                iObservable.onDataChange();
                return false;
            } catch (Exception e) {
                return true;
            }
        }));
    }
}
