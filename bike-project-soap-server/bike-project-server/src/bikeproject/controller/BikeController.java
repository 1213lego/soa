package bikeproject.controller;

import bikeproject.service.BikeService;

import java.time.LocalDateTime;

import java.util.List;
import bikeproject.structural.Bike;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(serviceName = "BikeController")
@XmlSeeAlso({ Bike.class, LocalDateTime.class })
public class BikeController {
    private BikeService bikeService;
    public BikeController() {
        super();
        bikeService = BikeService.getInstance();
    }

    @WebMethod
    public void saveBike(@WebParam(name = "arg0") Bike bike) throws Exception {
            bikeService.saveBike(bike);
        }


    @WebMethod
    public void deleteBike(@WebParam(name = "arg0") String serial) throws Exception {
            bikeService.deleteBike(serial);
        }


    @WebMethod
    public void updateBike(@WebParam(name = "arg0") Bike bikeUpdate) throws Exception {
            bikeService.updateBike(bikeUpdate);
        }


    @WebMethod
    public Bike findBikeBySerial(@WebParam(name = "arg0") String serial){
            return bikeService.findBikeBySerial(serial);
        }


    @WebMethod
    public List<Bike> getBikes(){
            return bikeService.getBikes();
        }
}
