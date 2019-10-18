package com.example.bikes.Project.RESTful.model.repository;

import com.example.bikes.Project.RESTful.model.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepository extends JpaRepository<Bike, String> {
}
