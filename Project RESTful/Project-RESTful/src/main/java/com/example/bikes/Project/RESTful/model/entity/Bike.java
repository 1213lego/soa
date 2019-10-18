package com.example.bikes.Project.RESTful.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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
    @NotNull
    @Column (nullable = false)
    private Type type;
    @NotBlank(message = "no puede estar vacio ni contener espacios en blanco.")
    @Size(min = 1, max = 20,  message = "tiene que contener entre 1 y 20 carácteres.")
    @Column (nullable = false)
    private String brand;
    @NotNull
    @Positive
    @Column (nullable = false)
    private double weight;
    @NonNull
    @Positive
    @Column (nullable = false)
    private double price;

    @NotNull
    @Column(name = "purchasedate", nullable = false)
    private LocalDateTime purchaseDate;
}
