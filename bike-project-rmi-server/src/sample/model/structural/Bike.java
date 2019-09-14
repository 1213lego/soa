package sample.model.structural;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bike implements Serializable {
    public enum Type { ROAD, MOUNTAIN, GRAVEL }
    public final static DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd - hh:mm");
    public final static DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String serial;
    private Type type;
    private String brand;
    private double weight;
    private double price;
    private LocalDateTime purchaseDate;

    public Bike() {
    }

    public Bike(String serial, Type type, String brand, double weight, double price, LocalDateTime purchaseDate) {
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

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String save(){
        return "INSERT INTO bike VALUES ('"+serial+"', '"+type.toString()+"', '"+brand+"'," +weight+","+price+", '"+purchaseDate.format(formatterDate)+"');";
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
