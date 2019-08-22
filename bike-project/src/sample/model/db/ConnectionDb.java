/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.model.db;

import java.sql.*;

public class ConnectionDb {
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
           conectar();
        }

        private ConnectionDb() {
            this("postgres","colombia");
        }

        //Metodo para conectarce a una base de datos
        private void conectar(){
            try{

                Class.forName("org.postgresql.Driver");
            }
            catch(ClassNotFoundException e){
                System.err.println("'conectarAccess()' Error al intentar cargar Driver. "+e.getMessage());
                e.printStackTrace();
            }

            try{
                url="jdbc:postgresql://localhost:5432/"+nombreBD;
                con = DriverManager.getConnection(url,user,password);
                con.setAutoCommit(true);
                System.out.println("Conexion exitosa...");
            }catch(Exception e){
                System.out.println("Error al conectarce: "+e);
            }
        }


        //Metodo que permite ejecutar una consulta y retorna un objeto ResulSet con
        //los resultados.
        public ResultSet executeQueryStatement(String cad){
            ResultSet res = null;
            try{
                stmt = con.createStatement();
                res = stmt.executeQuery(cad);
                System.out.println("Consulta realizada...  ");
            }catch(Exception ex){
                System.out.println("No se pudo efectuar la consulta..." + ex);
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
                System.out.println("Actualizacion realizada...  " + r);
                //con.commit();
                stmt.close();
                return true;
            }catch(Exception ex){
                System.out.println("No se pudo efectuar la grabacion..." + ex);
                return false;
            }
        }

        //Metodo para invocar un procedimiento almacenado
        public void executeProcedure(String cadProc){
            try{
                CallableStatement proc =con.prepareCall("{ call " + cadProc + " }");
                proc.execute();
            }catch (SQLException e)
            {
                System.out.println("Problemas con la invocacion del procedimiento " + cadProc);
            }
        }
        //Objeto que cierra la conexion con la base de datos.
        public void closeConecction(){
            try{
                if(con != null){
                    con.close();
                }
            }catch(SQLException e){
                System.out.println("Error! " + e);
            }
        }
        public static ConnectionDb getInstance() {
            if(connectionDb==null){
                connectionDb = new ConnectionDb();
            }
            return connectionDb;
        }

}
