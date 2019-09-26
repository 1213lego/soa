package sample.model.service;

import sample.model.db.ConnectionDb;
import sample.model.structural.Bike;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BikeService {
    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd");
    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private List<Bike> bikes;
    private static BikeService bikeService;
    private ConnectionDb connectionDb;

    private BikeService(){
        connectionDb = ConnectionDb.getInstance();
        bikes = new ArrayList<>();
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
                bike.setPurchaseDate(rs.getDate(6));
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

    public void updateBike(Bike bikeUpdate) throws Exception {
        updateInMemory(bikeUpdate);
        updateBikeFromDB(bikeUpdate);
    }

    private void updateInMemory(Bike bikeUpdate)throws Exception{
        int bikeIndex = findBikeIndex(bikeUpdate.getSerial());
        if(bikeIndex==-1){
            throw new Exception("Bike not found with this serial: " + bikeUpdate.getSerial());
        }
        bikeUpdate.setSerial(bikeUpdate.getSerial());
        bikes.set(bikeIndex,bikeUpdate);
    }

    private void updateBikeFromDB(Bike bikeUpdate) throws Exception {
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
    public List <Bike> getBikes(){
        return bikes;
    }
}

