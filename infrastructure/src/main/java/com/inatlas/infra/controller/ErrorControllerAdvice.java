package com.inatlas.infra.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inatlas.infra.api.dto.ErrorDTO;
import com.inatlas.infra.service.ErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorControllerAdvice {

  private final ErrorService errorService;

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorDTO> handleNoSuchElementException(NoSuchElementException ex) {
    return errorService.returnErrorResponse(HttpStatus.NOT_FOUND, ex);
  }

  @ExceptionHandler(UnsupportedOperationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorDTO> handleUnsupportedOperationException(UnsupportedOperationException ex) {
    return errorService.returnErrorResponse(HttpStatus.BAD_REQUEST, ex);
  }

  @ExceptionHandler({IOException.class, JsonProcessingException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ErrorDTO> handleIOException(IOException ex) {
    return errorService.returnErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex);
  }

  public ErrorControllerAdvice(ErrorService errorService) {
    this.errorService = errorService;
  }

}



