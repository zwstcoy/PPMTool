package com.example.ppmtool.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class MapValidationErrorService {

  public ResponseEntity<?> MapValidationErrorService(BindingResult result) {
    if (result.hasErrors()) {

      Map<String, String> errorRespond = new HashMap<>();
      for (FieldError error : result.getFieldErrors()) {
        errorRespond.put(error.getField(), error.getDefaultMessage());
      }
      return new ResponseEntity<Map<String, String>>(errorRespond, HttpStatus.BAD_REQUEST);
    }
    return null;
  }
}
