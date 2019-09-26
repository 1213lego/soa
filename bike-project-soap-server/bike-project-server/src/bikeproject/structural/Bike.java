package bikeproject.structural;
import bikeproject.service.BikeService;

import java.util.Date;

public class Bike {

    public enum Type { ROAD, MOUNTAIN, GRAVEL }

    private String serial;
    private Type type;
    private String brand;
    private double weight;
    private double price;
    private Date purchaseDate;

    public Bike() {
    }
    
    public Bike(String serial, Type type, String brand, double weight, double price, Date purchaseDate) {
        this.serial = serial;
        this.type = type;
        this.brand = brand;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String save(){
        return "INSERT INTO bike VALUES ('"+serial+"', '"+type.toString()+"', '"+brand+"'," +weight+","+price+", '"+BikeService.DATE_FORMAT.format(purchaseDate)+"');";
    }

    public String update() {
        return "UPDATE bike SET type = '" + type + "', " +
                "brand = '" + brand + "', " +
                "weight = '" + weight + "', " +
                "price = '" + price + "', " +
                "purchaseDate = '" + purchaseDate + "' " +
                "WHERE serial = '" + serial + "';";
    }

    @Override
    public String toString() {
        return "Bike{" +
                "serial='" + serial + '\'' +
                ", type=" + type +
                ", brand='" + brand + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
