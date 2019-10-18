
package bikeproject.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the bikeproject.model package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DeleteBike_QNAME = new QName("http://controller.bikeproject/", "deleteBike");
    private final static QName _DeleteBikeResponse_QNAME =
        new QName("http://controller.bikeproject/", "deleteBikeResponse");
    private final static QName _Exception_QNAME = new QName("http://controller.bikeproject/", "Exception");
    private final static QName _FindBikeBySerial_QNAME =
        new QName("http://controller.bikeproject/", "findBikeBySerial");
    private final static QName _FindBikeBySerialResponse_QNAME =
        new QName("http://controller.bikeproject/", "findBikeBySerialResponse");
    private final static QName _GetBikes_QNAME = new QName("http://controller.bikeproject/", "getBikes");
    private final static QName _GetBikesResponse_QNAME =
        new QName("http://controller.bikeproject/", "getBikesResponse");
    private final static QName _SaveBike_QNAME = new QName("http://controller.bikeproject/", "saveBike");
    private final static QName _SaveBikeResponse_QNAME =
        new QName("http://controller.bikeproject/", "saveBikeResponse");
    private final static QName _UpdateBike_QNAME = new QName("http://controller.bikeproject/", "updateBike");
    private final static QName _UpdateBikeResponse_QNAME =
        new QName("http://controller.bikeproject/", "updateBikeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bikeproject.model
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeleteBike }
     *
     */
    public DeleteBike createDeleteBike() {
        return new DeleteBike();
    }

    /**
     * Create an instance of {@link DeleteBikeResponse }
     *
     */
    public DeleteBikeResponse createDeleteBikeResponse() {
        return new DeleteBikeResponse();
    }

    /**
     * Create an instance of {@link Exception }
     *
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link FindBikeBySerial }
     *
     */
    public FindBikeBySerial createFindBikeBySerial() {
        return new FindBikeBySerial();
    }

    /**
     * Create an instance of {@link FindBikeBySerialResponse }
     *
     */
    public FindBikeBySerialResponse createFindBikeBySerialResponse() {
        return new FindBikeBySerialResponse();
    }

    /**
     * Create an instance of {@link GetBikes }
     *
     */
    public GetBikes createGetBikes() {
        return new GetBikes();
    }

    /**
     * Create an instance of {@link GetBikesResponse }
     *
     */
    public GetBikesResponse createGetBikesResponse() {
        return new GetBikesResponse();
    }

    /**
     * Create an instance of {@link SaveBike }
     *
     */
    public SaveBike createSaveBike() {
        return new SaveBike();
    }

    /**
     * Create an instance of {@link SaveBikeResponse }
     *
     */
    public SaveBikeResponse createSaveBikeResponse() {
        return new SaveBikeResponse();
    }

    /**
     * Create an instance of {@link UpdateBike }
     *
     */
    public UpdateBike createUpdateBike() {
        return new UpdateBike();
    }

    /**
     * Create an instance of {@link UpdateBikeResponse }
     *
     */
    public UpdateBikeResponse createUpdateBikeResponse() {
        return new UpdateBikeResponse();
    }

    /**
     * Create an instance of {@link Bike }
     *
     */
    public Bike createBike() {
        return new Bike();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteBike }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteBike }{@code >}
     */
    @XmlElementDecl(namespace = "http://controller.bikeproject/", name = "deleteBike")
    public JAXBElement<DeleteBike> createDeleteBike(DeleteBike value) {
        return new JAXBElement<DeleteBike>(_DeleteBike_QNAME, DeleteBike.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteBikeResponse }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteBikeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controller.bikeproject/", name = "deleteBikeResponse")
    public JAXBElement<DeleteBikeResponse> createDeleteBikeResponse(DeleteBikeResponse value) {
        return new JAXBElement<DeleteBikeResponse>(_DeleteBikeResponse_QNAME, DeleteBikeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}
     */
    @XmlElementDecl(namespace = "http://controller.bikeproject/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindBikeBySerial }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindBikeBySerial }{@code >}
     */
    @XmlElementDecl(namespace = "http://controller.bikeproject/", name = "findBikeBySerial")
    public JAXBElement<FindBikeBySerial> createFindBikeBySerial(FindBikeBySerial value) {
        return new JAXBElement<FindBikeBySerial>(_FindBikeBySerial_QNAME, FindBikeBySerial.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindBikeBySerialResponse }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindBikeBySerialResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controller.bikeproject/", name = "findBikeBySerialResponse")
    public JAXBElement<FindBikeBySerialResponse> createFindBikeBySerialResponse(FindBikeBySerialResponse value) {
        return new JAXBElement<FindBikeBySerialResponse>(_FindBikeBySerialResponse_QNAME,
                                                         FindBikeBySerialResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBikes }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetBikes }{@code >}
     */
    @XmlElementDecl(namespace = "http://controller.bikeproject/", name = "getBikes")
    public JAXBElement<GetBikes> createGetBikes(GetBikes value) {
        return new JAXBElement<GetBikes>(_GetBikes_QNAME, GetBikes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBikesResponse }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetBikesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controller.bikeproject/", name = "getBikesResponse")
    public JAXBElement<GetBikesResponse> createGetBikesResponse(GetBikesResponse value) {
        return new JAXBElement<GetBikesResponse>(_GetBikesResponse_QNAME, GetBikesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveBike }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SaveBike }{@code >}
     */
    @XmlElementDecl(namespace = "http://controller.bikeproject/", name = "saveBike")
    public JAXBElement<SaveBike> createSaveBike(SaveBike value) {
        return new JAXBElement<SaveBike>(_SaveBike_QNAME, SaveBike.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveBikeResponse }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SaveBikeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controller.bikeproject/", name = "saveBikeResponse")
    public JAXBElement<SaveBikeResponse> createSaveBikeResponse(SaveBikeResponse value) {
        return new JAXBElement<SaveBikeResponse>(_SaveBikeResponse_QNAME, SaveBikeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateBike }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateBike }{@code >}
     */
    @XmlElementDecl(namespace = "http://controller.bikeproject/", name = "updateBike")
    public JAXBElement<UpdateBike> createUpdateBike(UpdateBike value) {
        return new JAXBElement<UpdateBike>(_UpdateBike_QNAME, UpdateBike.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateBikeResponse }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateBikeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controller.bikeproject/", name = "updateBikeResponse")
    public JAXBElement<UpdateBikeResponse> createUpdateBikeResponse(UpdateBikeResponse value) {
        return new JAXBElement<UpdateBikeResponse>(_UpdateBikeResponse_QNAME, UpdateBikeResponse.class, null, value);
    }

}
