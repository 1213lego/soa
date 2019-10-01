/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bikeproject.db;

import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDb {
    private final static Logger LOGGER = Logger.getLogger(ConnectionDb.class.getName());
       private  Connection con;
       private static ConnectionDb connectionDb;
       private String user;
       private String password;
       private Statement stmt;
       private String url;
       private String nombreBD;

       private ConnectionDb(String us, String pass){
          user = us;
          password = pass;
          nombreBD  = "postgres";
          connect();
       }
       public static ConnectionDb getInstance() {
           if(connectionDb==null){
               connectionDb = new ConnectionDb();
           }
           return connectionDb;
       }
       private ConnectionDb() {
           this("root","root");
       }

       //Metodo para conectarce a una base de datos
       private void connect(){
           try{

               Class.forName("org.postgresql.Driver");
               System.out.println("'connect()': Driver cargado...");
           }
           catch(ClassNotFoundException e){
               LOGGER.log(Level.SEVERE, "connect(): Error al intentar cargar Driver", e);
           }

           try{
               url = "jdbc:postgresql://localhost:5432/" + nombreBD;
               con = DriverManager.getConnection(url, user, password);
               con.setAutoCommit(true);
               System.out.println("'connect()': Conexion exitosa...");
           }
           catch(Exception e){
               LOGGER.log(Level.SEVERE, "connect(): Error al conectar:", e);
               System.exit(0);
           }
       }


       //Metodo que permite ejecutar una consulta y retorna un objeto ResulSet con
       //los resultados.
       public ResultSet executeQueryStatement(String sql){
           ResultSet res = null;
           try{
               stmt = con.createStatement();
               res = stmt.executeQuery(sql);
               System.out.println("'executeQueryStatement()': Consulta realizada: " + sql);
           }
           catch(Exception ex){
               LOGGER.log(Level.SEVERE, null, ex);
           }
           return res;
       }

       //metodo que permite ejecutar una transaccion de insercion o actualizacion
       //o eliminacion
       public boolean executeUpdateStatement(String cad){
           int r = 0;
           try{
               stmt = con.createStatement();
               r = stmt.executeUpdate(cad);
               System.out.println("executeUpdateStatement() Actualizacion realizada...  " + r);
               //con.commit();
               stmt.close();
               return true;
           }
           catch(SQLException ex){
               LOGGER.log(Level.SEVERE, null, ex);
               return false;
           }
       }

       //Metodo para invocar un procedimiento almacenado
       public void executeProcedure(String cadProc){
           try{
               CallableStatement proc =con.prepareCall("{ call " + cadProc + " }");
               proc.execute();
           }
           catch (SQLException e) {
               System.out.println("Problemas con la invocacion del procedimiento " + cadProc);
           }
       }
       //Objeto que cierra la conexion con la base de datos.
       public void closeConecction(){
           try{
               if(con != null){
                   con.close();
               }
           }
           catch(SQLException e){
               System.out.println("Error! " + e);
           }
       }
}
