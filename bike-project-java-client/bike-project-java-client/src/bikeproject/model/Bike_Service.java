
package bikeproject.model;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0-b170214.1743
 * Generated source version: 2.2
 *
 */
@WebServiceClient(name = "bike", targetNamespace = "http://controller.bikeproject/",
                  wsdlLocation =
                  "http://127.0.0.1:7101/bike-project-server-bike-project-server-context-root/bikerservice?WSDL#%7Bhttp%3A%2F%2Fcontroller.bikeproject%2F%7Dbike")
public class Bike_Service extends Service {

    private final static URL BIKE_WSDL_LOCATION;
    private final static WebServiceException BIKE_EXCEPTION;
    private final static QName BIKE_QNAME = new QName("http://controller.bikeproject/", "bike");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url =
                new URL("http://127.0.0.1:7101/bike-project-server-bike-project-server-context-root/bikerservice?WSDL#%7Bhttp%3A%2F%2Fcontroller.bikeproject%2F%7Dbike");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        BIKE_WSDL_LOCATION = url;
        BIKE_EXCEPTION = e;
    }

    public Bike_Service() {
        super(__getWsdlLocation(), BIKE_QNAME);
    }

    public Bike_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), BIKE_QNAME, features);
    }

    public Bike_Service(URL wsdlLocation) {
        super(wsdlLocation, BIKE_QNAME);
    }

    public Bike_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BIKE_QNAME, features);
    }

    public Bike_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Bike_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns BikeController
     */
    @WebEndpoint(name = "bikerservice")
    public BikeService getBikerservice() {
        return super.getPort(new QName("http://controller.bikeproject/", "bikerservice"), BikeService.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BikeController
     */
    @WebEndpoint(name = "bikerservice")
    public BikeService getBikerservice(WebServiceFeature... features) {
        return super.getPort(new QName("http://controller.bikeproject/", "bikerservice"), BikeService.class,
                             features);
    }

    private static URL __getWsdlLocation() {
        if (BIKE_EXCEPTION != null) {
            throw BIKE_EXCEPTION;
        }
        return BIKE_WSDL_LOCATION;
    }

}
