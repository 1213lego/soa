package sample.model.structural;

import java.time.LocalDateTime;

public class Bike {

    public enum Type { ROAD, MOUNTAIN, GRAVEL }

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
