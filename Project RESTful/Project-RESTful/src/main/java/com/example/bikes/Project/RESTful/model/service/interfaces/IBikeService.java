package com.example.bikes.Project.RESTful.model.service.interfaces;

import com.example.bikes.Project.RESTful.model.entity.Bike;

import java.util.List;

public interface IBikeService {

    public Bike save(Bike bike);
    public Bike findBikeBySerial(String serial);
    public void delete(String serial);
    public List<Bike> findAllBikes();
}
