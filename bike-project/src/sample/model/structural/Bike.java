package sample.model.structural;

import java.time.LocalDate;
import java.util.Date;

public class Bike {

    enum Type { ROAD, MOUNTAIN, GRAVEL }

    private String serial;
    private String brand;
    private LocalDate purchaseDate;
    private double totalWeight;
    private double purchasePrice;
    private Type type;

    public Bike() {
    }

    public Bike(String serial, String brand, LocalDate purchaseDate, double totalWeight, double purchasePrice) {
        this.serial = serial;
        this.brand = brand;
        this.purchaseDate = purchaseDate;
        this.totalWeight = totalWeight;
        this.purchasePrice = purchasePrice;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
