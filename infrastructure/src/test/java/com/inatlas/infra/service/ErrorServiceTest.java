package com.inatlas.infra.service;

import com.inatlas.infra.api.dto.ErrorDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorServiceTest {
  ErrorService errorService;

  @BeforeEach
  public void setUp() {
    errorService = new ErrorService();
  }

  @Test
  @DisplayName("Test a BAD REQUEST response with a runtime exception")
  public void testReturnErrorResponseWithRuntimeException() {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    RuntimeException ex = new RuntimeException("Runtime exception occurred");

    ResponseEntity<ErrorDTO> response = errorService.returnErrorResponse(status, ex);

    assertEquals(status, response.getStatusCode());
    assertEquals(status.value(), response.getBody().getCode());
    assertEquals(ex.getMessage(), response.getBody().getMessage());
  }

  @Test
  @DisplayName("Test a INTERNAL SERVER ERROR response with a IO exception")
  public void testReturnErrorResponseWithIOException() {

    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    IOException ex = new IOException("IO exception occurred");

    ResponseEntity<ErrorDTO> response = errorService.returnErrorResponse(status, ex);

    assertEquals(status, response.getStatusCode());
    assertEquals(status.value(), response.getBody().getCode());
    assertEquals(ex.getMessage(), response.getBody().getMessage());
  }
}
