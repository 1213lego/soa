
package bikeproject.model;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0-b170214.1743
 * Generated source version: 2.2
 *
 */
@WebService(name = "BikeController", targetNamespace = "http://controller.bikeproject/")
@XmlSeeAlso({ ObjectFactory.class })
public interface BikeService {


    /**
     *
     * @param arg0
     * @return
     *     returns bikeproject.model.Bike
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findBikeBySerial", targetNamespace = "http://controller.bikeproject/",
                    className = "bikeproject.model.FindBikeBySerial")
    @ResponseWrapper(localName = "findBikeBySerialResponse", targetNamespace = "http://controller.bikeproject/",
                     className = "bikeproject.model.FindBikeBySerialResponse")
    @Action(input = "http://controller.bikeproject/BikeController/findBikeBySerialRequest",
            output = "http://controller.bikeproject/BikeController/findBikeBySerialResponse")
    public Bike findBikeBySerial(@WebParam(name = "arg0", targetNamespace = "") String arg0);

    /**
     *
     * @return
     *     returns java.util.List<bikeproject.model.Bike>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getBikes", targetNamespace = "http://controller.bikeproject/",
                    className = "bikeproject.model.GetBikes")
    @ResponseWrapper(localName = "getBikesResponse", targetNamespace = "http://controller.bikeproject/",
                     className = "bikeproject.model.GetBikesResponse")
    @Action(input = "http://controller.bikeproject/BikeController/getBikesRequest",
            output = "http://controller.bikeproject/BikeController/getBikesResponse")
    public List<Bike> getBikes();

    /**
     *
     * @param arg0
     * @throws Exception_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "updateBike", targetNamespace = "http://controller.bikeproject/",
                    className = "bikeproject.model.UpdateBike")
    @ResponseWrapper(localName = "updateBikeResponse", targetNamespace = "http://controller.bikeproject/",
                     className = "bikeproject.model.UpdateBikeResponse")
    @Action(input = "http://controller.bikeproject/BikeController/updateBikeRequest",
            output = "http://controller.bikeproject/BikeController/updateBikeResponse",
            fault =
            { @FaultAction(className = Exception_Exception.class,
                           value = "http://controller.bikeproject/BikeController/updateBike/Fault/Exception")
        })
    public void updateBike(@WebParam(name = "arg0", targetNamespace = "") Bike arg0) throws Exception_Exception;

    /**
     *
     * @param arg0
     * @throws Exception_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "saveBike", targetNamespace = "http://controller.bikeproject/",
                    className = "bikeproject.model.SaveBike")
    @ResponseWrapper(localName = "saveBikeResponse", targetNamespace = "http://controller.bikeproject/",
                     className = "bikeproject.model.SaveBikeResponse")
    @Action(input = "http://controller.bikeproject/BikeController/saveBikeRequest",
            output = "http://controller.bikeproject/BikeController/saveBikeResponse",
            fault =
            { @FaultAction(className = Exception_Exception.class,
                           value = "http://controller.bikeproject/BikeController/saveBike/Fault/Exception")
        })
    public void saveBike(@WebParam(name = "arg0", targetNamespace = "") Bike arg0) throws Exception_Exception;

    /**
     *
     * @param arg0
     * @throws Exception_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "deleteBike", targetNamespace = "http://controller.bikeproject/",
                    className = "bikeproject.model.DeleteBike")
    @ResponseWrapper(localName = "deleteBikeResponse", targetNamespace = "http://controller.bikeproject/",
                     className = "bikeproject.model.DeleteBikeResponse")
    @Action(input = "http://controller.bikeproject/BikeController/deleteBikeRequest",
            output = "http://controller.bikeproject/BikeController/deleteBikeResponse",
            fault =
            { @FaultAction(className = Exception_Exception.class,
                           value = "http://controller.bikeproject/BikeController/deleteBike/Fault/Exception")
        })
    public void deleteBike(@WebParam(name = "arg0", targetNamespace = "") String arg0) throws Exception_Exception;

}
