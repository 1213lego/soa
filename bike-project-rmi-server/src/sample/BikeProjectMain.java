package sample;

import sample.model.service.BikeServiceImpl;
import sample.model.service.IBikeService;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BikeProjectMain{
    private final static Logger LOGGER = Logger.getLogger(BikeProjectMain.class.getName());
    public static void main(String[] args) {
        try {
            IBikeService iBikeService = BikeServiceImpl.getInstance();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("//192.168.0.107/bike",iBikeService);
            LOGGER.info("Server OK");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"",e);
        }
    }
}
