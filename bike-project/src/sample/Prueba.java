package sample;

import sample.model.db.ConnectionDb;
import sample.model.structural.Bike;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Prueba {
    public static void main(String [] args){
        ConnectionDb connectionDb = ConnectionDb.getInstance();
        ResultSet rs = connectionDb.executeQueryStatement("select * from bike;");
        Bike bike = new Bike();
       try {
           while (rs.next()){
           }
       }
       catch (SQLException e){
           e.printStackTrace();
       }
    }
}
