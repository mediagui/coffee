package com.inatlas.infra.controller;

import com.inatlas.infra.service.ReceiptService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ReceiptControllerTest {
  @Mock
  ReceiptService receiptService;
  @InjectMocks
  ReceiptController receiptController;

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
  public void testGetReceipt_Positive() throws IOException {

    ResponseEntity<Resource> expectedResponse = ResponseEntity.ok().build();
    when(receiptService.getReceipt("pdf")).thenReturn(expectedResponse);

    ResponseEntity<Resource> actualResponse = receiptController.getReceipt("pdf");

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void testGetReceipt_Exception() throws IOException {

    when(receiptService.getReceipt("pdf")).thenThrow(new IOException("Error"));

      RuntimeException exception = assertThrows(RuntimeException.class, () ->
            receiptController.getReceipt("pdf")
    );

    assertEquals("java.io.IOException: Error", exception.getMessage());
  }
}


