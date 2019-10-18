package com.example.bikes.Project.RESTful.model.service;

import com.example.bikes.Project.RESTful.model.entity.Bike;
import com.example.bikes.Project.RESTful.model.repository.BikeRepository;
import com.example.bikes.Project.RESTful.model.service.interfaces.IBikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BikeServiceImpl implements IBikeService {

    @Autowired
    private BikeRepository bikeRepository;

    @Override
    @Transactional
    public Bike save(Bike bike) {
        return bikeRepository.save(bike);
    }

    @Override
    @Transactional(readOnly = true)
    public Bike findBikeBySerial(String serial) {
        return bikeRepository.findById(serial).orElse(null);
    }

    @Override
    @Transactional
    public void delete(String serial) {
        bikeRepository.deleteById(serial);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bike> findAllBikes() {
        return bikeRepository.findAll();
    }
}
