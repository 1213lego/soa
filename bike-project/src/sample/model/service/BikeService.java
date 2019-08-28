package sample.model.service;

import sample.controller.IObservable;
import sample.model.db.ConnectionDb;
import sample.model.structural.Bike;

import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BikeService {
    public final static DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd - hh:mm");
    public final static DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private ArrayList<Bike> bikes;
    private ArrayList<IObservable> listeners;
    private static BikeService bikeService;
    private ConnectionDb connectionDb;

    private BikeService(){
        connectionDb = ConnectionDb.getInstance();
        bikes = new ArrayList();
        listeners = new ArrayList();
        loadBikesFromDb();
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
            e.printStackTrace();
        }
    }

    public static BikeService getInstance(){
        if(bikeService == null){
            bikeService = new BikeService();
        }
        return bikeService;
    }
    public void saveBike(Bike bike) throws Exception {
        addBikeInMemory(bike);
        saveBikeInDb(bike);
        notifyListeners();
    }
    private void addBikeInMemory(Bike bike) throws Exception {
        if(findBikeBySerial(bike.getSerial())!=null){
            throw new Exception("Duplicate bike serial");
        }
        bikes.add(bike);
    }
    private void saveBikeInDb(Bike bike)throws Exception {
        boolean result = connectionDb.executeUpdateStatement(bike.save());
        if(result==false){
            throw new Exception("Bike not save in db, duplicate serial");
        }
    }
    public void deleteBike(String serial) throws Exception {
        deleteBikeInMemory(serial);
        deleteFromDb(serial);
        notifyListeners();
    }
    private void deleteBikeInMemory(String serial) throws Exception {
        Bike bike = findBikeBySerial(serial);
        if(bike == null) {
            throw new Exception("Bike not found with this serial: " + serial);
        }
        bikes.remove(bike);
    }
    private void deleteFromDb(String  serial) throws Exception {
        boolean result = connectionDb.executeUpdateStatement("delete from bike where serial='"+serial+"';");
        if(result==false){
            throw new Exception("Bike not save in db, serial not found");
        }
    }

    public void updateBike(String serial, Bike bikeUpdate) throws Exception {
        updateInMemory(serial, bikeUpdate);
        updateBikeFromDB(serial, bikeUpdate);
        notifyListeners();
    }

    private void updateInMemory(String serial, Bike bikeUpdate)throws Exception{
        int bikeIndex = findBikeIndex(serial);
        if(bikeIndex==-1){
            throw new Exception("Bike not found with this serial: " + serial);
        }
        bikeUpdate.setSerial(serial);
        bikes.set(bikeIndex,bikeUpdate);
        notifyListeners();
    }

    private void updateBikeFromDB(String serial, Bike bikeUpdate) throws Exception {
        boolean result = connectionDb.executeUpdateStatement(bikeUpdate.update());
        if(result==false){
            throw new Exception("Bike not save in db, serial not found");
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
    public Bike findBikeBySerial(String serial){
        for (Bike bike: bikes) {
            if(bike.getSerial().equals(serial)){
                return bike;
            }
        }
        return null;
    }
    public Bike findBikeInDb(String serial){
        return null;

    }
    public ArrayList <Bike> getBikes(){
        return bikes;
    }
    public void addListener(IObservable iObservable){
        listeners.add(iObservable);
    }
    public void removeListener(IObservable iObservable){
        listeners.remove(iObservable);
    }
    private void notifyListeners(){
        for(IObservable iObservable: listeners){
            iObservable.onDataChange();
        }
    }
}
