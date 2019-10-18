
package bikeproject.model;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0-b170214.1743
 * Generated source version: 2.2
 *
 */
@WebFault(name = "Exception", targetNamespace = "http://controller.bikeproject/")
public class Exception_Exception extends java.lang.Exception {

    /**
     * Java type that goes as soapenv:Fault detail element.
     *
     */
    private bikeproject.model.Exception faultInfo;

    /**
     *
     * @param faultInfo
     * @param message
     */
    public Exception_Exception(String message, bikeproject.model.Exception faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     *
     * @param faultInfo
     * @param cause
     * @param message
     */
    public Exception_Exception(String message, bikeproject.model.Exception faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     *
     * @return
     *     returns fault bean: bikeproject.model.Exception
     */
    public bikeproject.model.Exception getFaultInfo() {
        return faultInfo;
    }

}
