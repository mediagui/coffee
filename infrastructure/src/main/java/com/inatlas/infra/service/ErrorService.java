package com.inatlas.infra.service;

import com.inatlas.infra.api.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ErrorService {

  public ResponseEntity<ErrorDTO> returnErrorResponse(HttpStatus status, RuntimeException ex) {
    ErrorDTO errorDTO = new ErrorDTO();
    errorDTO.setCode(status.value());
    errorDTO.setMessage(ex.getMessage());

    return ResponseEntity.status(status).body(errorDTO);

  }

  public ResponseEntity<ErrorDTO> returnErrorResponse(HttpStatus status, IOException ex) {
    ErrorDTO errorDTO = new ErrorDTO();
    errorDTO.setCode(status.value());
    errorDTO.setMessage(ex.getMessage());

    return ResponseEntity.status(status).body(errorDTO);

  }

}
