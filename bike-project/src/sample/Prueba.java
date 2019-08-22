package sample;

import sample.model.db.ConnectionDb;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Prueba {
    public static void main(String [] args){
        ConnectionDb connectionDb = ConnectionDb.getInstance();
        ResultSet rs = connectionDb.executeQueryStatement("select * from test;");
        String codigo = "";
        String nombre = "";
       try {
           while (rs.next()){
               codigo = rs.getString("codigo");
               nombre = rs.getString("nombre");
               System.out.println(codigo + " " + nombre);
           }
       }
       catch (SQLException e){
           e.printStackTrace();
       }
    }
}
