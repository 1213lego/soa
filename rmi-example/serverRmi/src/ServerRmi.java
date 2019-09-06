/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.EstudianteServiceImpl;

/**
 *
 * @author Usuario
 */
public class ServerRmi extends Application {
    private static Scene scene;
    @Override
    public void start(Stage primaryStage) throws IOException {
        scene = new Scene(loadFXML("main"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Server");
        primaryStage.show();
    }

    public static void setRoot(String fxml){
        try{
            scene.setRoot(loadFXML(fxml));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static Parent loadFXML(String fxml) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(ServerRmi.class.getResource("./view/"+fxml+".fxml"));
        return fxmlLoader.load();
    }

    public static void launchNewWindows(String fxml, String title){
        try {
            Scene scene = new Scene(loadFXML(fxml));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        // TODO code application logic here
        EstudianteServiceImpl estudianteService = EstudianteServiceImpl.getInstance();
        LocateRegistry.createRegistry(1099);
        Naming.rebind("//127.0.0.1/estudiante",estudianteService);
        System.out.println("Todo bien");
        launch();
    }
    
}
