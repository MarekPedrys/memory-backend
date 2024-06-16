package pl.wszib.memoryapi.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.wszib.memoryapi.services.NotFoundException;

@RestControllerAdvice
public class ErrorHandlerController{

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Void> handleNotFound(){
        return ResponseEntity.notFound().build();
    }


}
