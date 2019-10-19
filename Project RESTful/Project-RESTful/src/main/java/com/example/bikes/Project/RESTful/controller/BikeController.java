package com.example.bikes.Project.RESTful.controller;

import com.example.bikes.Project.RESTful.model.entity.Bike;
import com.example.bikes.Project.RESTful.model.service.interfaces.IBikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BikeController {

    @Autowired
    private IBikeService bikeService;

    @PostMapping("/bike")
    public ResponseEntity<?> create(@Valid @RequestBody Bike bike, BindingResult result)
    {
        Bike newBike = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors())
        {
            return anexandoErrores(response, result);
        }

        try
        {
            newBike = bikeService.save(bike);
        }
        catch (DataAccessException e)
        {
            response.put("message", e.getMessage());
            response.put("specifications", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(newBike, HttpStatus.CREATED);
    }

    @GetMapping("/bike/{serial}")
    public ResponseEntity<?> show(@PathVariable String serial)
    {
        Bike bike = null;
        Map<String, Object> response = new HashMap<>();
        try
        {
            bike = bikeService.findBikeBySerial(serial);
        }
        catch (DataAccessException e)
        {
            response.put("message", e.getMessage());
            response.put("specifications", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(bike == null)
        {
            response.put("message", "The bike with the serial: " + serial + " doesn't exist in the database.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bike, HttpStatus.OK);
    }

    @PutMapping("/bike/{serial}")
    public ResponseEntity<?> update(@Valid @RequestBody Bike bike, BindingResult result, @PathVariable String serial)
    {
        Bike currentBike = bikeService.findBikeBySerial(serial);
        Bike updateBike = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors())
        {
            return anexandoErrores(response, result);
        }
        if(currentBike == null)
        {
            response.put("mensaje", "Error: Could not be edited because the bike with the serial: " +
                    serial + " doesn't exist in the database.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        try
        {
            currentBike.setType(bike.getType());
            currentBike.setBrand(bike.getBrand());
            currentBike.setWeight(bike.getWeight());
            currentBike.setPrice(bike.getPrice());
            currentBike.setPurchaseDate(bike.getPurchaseDate());
            updateBike = bikeService.save(currentBike);
        }
        catch (DataAccessException e)
        {
            response.put("message", e.getMessage());
            response.put("specifications", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(updateBike, HttpStatus.CREATED);
    }

    @DeleteMapping("/bike/{serial}")
    public ResponseEntity<?> delete(@PathVariable String serial)
    {
        Map<String, Object> response = new HashMap<>();
        try
        {
            bikeService.delete(serial);
        }
        catch (DataAccessException e)
        {
            response.put("message", e.getMessage());
            response.put("specifications", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/bike")
    public List<Bike> index()
    {
        return bikeService.findAllBikes();
    }

    private ResponseEntity<?> anexandoErrores(Map<String, Object> response, BindingResult result)
    {
        List<String> errores = result.getFieldErrors()
                .stream()
                .map(error -> "The field '" + error.getField() + "' " + error.getDefaultMessage())
                .collect(Collectors.toList());

        response.put("errores", errores);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
