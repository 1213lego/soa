package bikeproject.controller;

import bikeproject.service.BikeService;

import bikeproject.structural.Bike;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(serviceName = "bike", portName = "bikerservice")
@XmlSeeAlso({ Bike.class })
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
    public Bike findBikeBySerial(@WebParam(name = "arg0") String serial) {
        return bikeService.findBikeBySerial(serial);
    }

    @WebMethod
    public List<Bike> getBikes() {
        return bikeService.getBikes();
    }
}
