package com.example.bikes.Project.RESTful.model.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException{
    private String message;
    public ResourceAlreadyExistsException(String serial){
        super("The bike serial " +serial+ " Already exists");
        message = "The bike serial " +serial+ " Already exists";
    }
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
