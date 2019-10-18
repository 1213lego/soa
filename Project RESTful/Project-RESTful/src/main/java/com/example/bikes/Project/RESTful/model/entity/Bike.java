package com.example.bikes.Project.RESTful.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "bike")
public class Bike implements Serializable {
    public enum Type { ROAD, MOUNTAIN, GRAVEL }

    @Id
    private String serial;

    @Enumerated(EnumType.STRING)
    @Column (unique = false, nullable = false)
    private Type type;

    @NotBlank(message = "no puede estar vacio ni contener espacios en blanco.")
    @Size(min = 1, max = 20,  message = "tiene que contener entre 1 y 20 carácteres.")
    @Column (unique = false, nullable = false)
    private String brand;

    @NotBlank(message = "no puede estar vacio ni contener espacios en blanco.")
    @Column (unique = false, nullable = false)
    private double weight;

    @NotBlank(message = "no puede estar vacio ni contener espacios en blanco.")
    @Column (unique = false, nullable = false)
    private double price;

    @Column(name = "purchasedate", unique = false, nullable = false)
    private LocalDateTime purchaseDate;

    @PrePersist
    public void purchaseDatePersist()
    {
        purchaseDate = LocalDateTime.now();
        System.out.println(purchaseDate.toString());
    }
}
