package bikeproject.api.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "HashMap")
public class BikeResponse {
    @Element
    private Bike item;
    @Element
    private String message;

    public Bike getItem() {
        return item;
    }

    public void setItem(Bike item) {
        this.item = item;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
