package com.inatlas.infra.controller;

import com.inatlas.infra.api.dto.ErrorDTO;
import com.inatlas.infra.service.ErrorService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ErrorControllerAdviceTest {
  @Mock
  ErrorService errorService;
  @InjectMocks
  ErrorControllerAdvice errorControllerAdvice;

  AutoCloseable closeable;

  @BeforeEach
  void setUp() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }


  @Test
  @DisplayName("Test for NoSuchElementException")
  void handleNoSuchElementException_Positive() {
    NoSuchElementException ex = new NoSuchElementException("Element not found");
    ResponseEntity<ErrorDTO> expectedResponse = new ResponseEntity<>(new ErrorDTO(HttpStatus.NOT_FOUND.value(),"Element not found"), HttpStatus.NOT_FOUND);

    when(errorService.returnErrorResponse(HttpStatus.NOT_FOUND, ex)).thenReturn(expectedResponse);

    ResponseEntity<ErrorDTO> actualResponse = errorControllerAdvice.handleNoSuchElementException(ex);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  @DisplayName("Test for UnsupportedOperationException")
  void handleUnsupportedOperationException_Positive() {
    UnsupportedOperationException ex = new UnsupportedOperationException("Operation not supported");
    ResponseEntity<ErrorDTO> expectedResponse = new ResponseEntity<>(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Operation not supported"), HttpStatus.BAD_REQUEST);

    when(errorService.returnErrorResponse(HttpStatus.BAD_REQUEST, ex)).thenReturn(expectedResponse);

    ResponseEntity<ErrorDTO> actualResponse = errorControllerAdvice.handleUnsupportedOperationException(ex);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  @DisplayName("Test for IOException")
  void handleIOException_Positive() {
    IOException ex = new IOException("IO exception occurred");
    ResponseEntity<ErrorDTO> expectedResponse = new ResponseEntity<>(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),"IO exception occurred"), HttpStatus.INTERNAL_SERVER_ERROR);

    when(errorService.returnErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex)).thenReturn(expectedResponse);

    ResponseEntity<ErrorDTO> actualResponse = errorControllerAdvice.handleIOException(ex);

    assertEquals(expectedResponse, actualResponse);
  }


}


