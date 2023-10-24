package com.inatlas.infra.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.inatlas.domain.entity.Order;
import com.inatlas.domain.entity.Promotion;
import com.inatlas.domain.usecase.ApplyPromotionForLatteUseCase;
import com.inatlas.domain.usecase.ApplyPromotionForLatteUseCaseImpl;
import com.inatlas.domain.usecase.ApplyPromotionsUseCase;
import com.inatlas.domain.usecase.FindLastOrderCompletedUseCase;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import static com.inatlas.infra.service.CoffeeTestUtil.generateOrderItemList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ReceiptServiceTest {
  @Mock
  FindLastOrderCompletedUseCase findLastOrderCompletedUseCase;

  @Mock
  ApplyPromotionsUseCase applyPromotionsUseCase;
  @Mock
  ApplyPromotionForLatteUseCase applyPromotionForLatteUseCase;
  @InjectMocks
  ReceiptService receiptService;

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
  @DisplayName(" Test a receipt generation without a completed order")
  void getReceipt_WhenNoCompletedOrders_ReturnsNotFoundResponse() throws IOException {
    when(findLastOrderCompletedUseCase.getLastOrderCompleted()).thenReturn(Optional.empty());

    ResponseEntity<Resource> response = receiptService.getReceipt("pdf");

    assertEquals(ResponseEntity.notFound().build(), response);
  }

  @Test
  @DisplayName(" Test a receipt generation with a completed order")
  void getReceipt_WhenFormatIsPDFAndCompletedOrderExists_ReturnsPDFResource() throws IOException {

    Order completedOrder = new Order(generateOrderItemList());
    completedOrder.setComplete(true);

    when(findLastOrderCompletedUseCase.getLastOrderCompleted()).thenReturn(Optional.of(completedOrder));
    when(applyPromotionsUseCase.applyAndGetTheBestPromotion(any(Order.class))).thenReturn(Optional.ofNullable(new Promotion(1d, completedOrder.getItems(), applyPromotionForLatteUseCase)));


    ResponseEntity<Resource> response = receiptService.getReceipt("pdf");

    String fileSeparator = System.getProperty("file.separator");

    byte[] contentExpected = Files.readAllBytes(Paths.get(System.getProperty("java.io.tmpdir") + fileSeparator +"receipt.pdf"));

    ResponseEntity<Resource> expectedResponse = ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(new ByteArrayResource(contentExpected));

    assertEquals(expectedResponse, response);
  }

  @Test
  @DisplayName("Test a receipt generation with a completed order with an invalid format")
  void getReceipt_WhenFormatIsNotPDF_ThrowsUnsupportedOperationException() throws IOException {
    Order completedOrder = new Order(generateOrderItemList());
    completedOrder.setComplete(true);

    when(findLastOrderCompletedUseCase.getLastOrderCompleted()).thenReturn(Optional.of(completedOrder));

    assertThrows(UnsupportedOperationException.class, () -> receiptService.getReceipt("csv"));
  }


  @Test
  @DisplayName("Test that a PDF receipt is generated in the expected location")
  void writePDF_WithValidLines_GeneratesPDFInExpectedLocation() throws IOException {
    String[] lines = {"Line 1", "Line 2"};

    byte[] result = receiptService.writePDF(lines);

    assertTrue(Files.exists(Paths.get(System.getProperty("java.io.tmpdir") + "/receipt.pdf")));
    assertTrue(result.length > 0);
  }

  @Test
  @DisplayName("Test the generation of a PDF receipt with invalid or empty content")
  void writePDF_WithInvalidLines_ThrowsIOException() {
    String[] lines = {};
    assertThrows(IOException.class, () -> {
      try {
        receiptService.writePDF(lines);
      } catch (IOException e) {
        throw new IOException("Expected IOException to be thrown", e);
      }
    });
  }
}
