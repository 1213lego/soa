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

    @PostMapping("/bikes")
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
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "The bike has been created successfully.");
        response.put("bike", newBike);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/bikes/{serial}")
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
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(bike == null)
        {
            response.put("message", "The bike with the serial: " + serial + " doesn't exist in the database.");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Bike>(bike, HttpStatus.OK);
    }

    @PutMapping("/bikes/{serial}")
    public ResponseEntity<?> update(@Valid @RequestBody Bike bike, BindingResult result, @PathVariable String serial)
    {
        Bike currentBike = bikeService.findBikeBySerial(serial);
        Bike updateBike = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors())
        {
            return anexandoErrores(response, result);
        }

        if(updateBike == null)
        {
            response.put("mensaje", "Error: Could not be edited because the bike with the serial: " +
                    serial + " doesn't exist in the database.");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
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

        response.put("message", "The bike has been updated successfully.");
        response.put("bike", updateBike);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/bikes/{serial}")
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
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "The bike has been deleted successfully.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/bikes")
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
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
}
