package bikeproject.api.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.Date;
@Root(name = "item")
public class Bike {
    public enum Type { ROAD, MOUNTAIN, GRAVEL }
    @Element
    private String serial;
    @Element
    private Type type;
    @Element
    private double weight;
    @Element
    private double price;
    @Element
    private Date purchaseDate;
    public Bike(){
    }
    public Bike(String serial, Type type,double weight, double price, Date purchaseDate) {
        this.serial = serial;
        this.type = type;
        this.weight = weight;
        this.price = price;
        this.purchaseDate = purchaseDate;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "serial='" + serial + '\'' +
                ", type=" + type +
                ", weight=" + weight +
                ", price=" + price +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
