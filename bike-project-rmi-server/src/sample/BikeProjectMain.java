package sample;

import sample.model.service.BikeServiceImpl;
import sample.model.service.IBikeService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class BikeProjectMain{
    public static void main(String[] args) {
        IBikeService iBikeService = null;
        try {
            iBikeService = BikeServiceImpl.getInstance();
        } catch (Exception e) {
            System.out.println("Error creando el servidor:  iBikeService = BikeServiceImpl.getInstance();");
            e.printStackTrace();
            return;
        }
        try {
            LocateRegistry.createRegistry(1099);
        } catch (RemoteException e) {
            System.out.println("Error en:  LocateRegistry.createRegistry(1099);");
            e.printStackTrace();
            return;
        }
        try {
            Naming.rebind("//127.0.0.1/bike",iBikeService);
        } catch (Exception e) {
            System.out.println("Error naming service: Naming.rebind(//127.0.0.1/bike,iBikeService);");
            e.printStackTrace();
            return;
        }
        System.out.println("Server OK");
    }
}
