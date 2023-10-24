package com.inatlas.infra.controller;

import com.inatlas.infra.service.ReceiptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ReceiptController.class)
@ContextConfiguration(classes = {ReceiptController.class, ReceiptService.class})
class ReceiptControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  ReceiptService receiptService;

//  AutoCloseable closeable;
//
//  @BeforeEach
//  void setUp() {
//    closeable = MockitoAnnotations.openMocks(this);
//  }
//
//  @AfterEach
//  void tearDown() throws Exception {
//    closeable.close();
//  }


  @Test
  public void testGetReceipt_Positive() throws Exception {

    ResponseEntity<Resource> expectedResponse = ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).build();
    when(receiptService.getReceipt("pdf")).thenReturn(expectedResponse);


    mockMvc.perform(get("/api/v1/coffeeShop/receipt"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_PDF));

    // Prepare the request
    String format = "pdf";


    MvcResult result = mockMvc.perform(get("/api/v1/coffeeShop/receipt")
                    .param("format", format)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    assertEquals("application/pdf", result.getResponse().getContentType());

  }

  @Test
  public void testGetReceipt_InvalidFormat() throws Exception {

    String format = "csv";

    ResponseEntity<Resource> expectedResponse = ResponseEntity.badRequest().build();
    when(receiptService.getReceipt(format)).thenReturn(expectedResponse);


    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/coffeeShop/receipt")
                    .param("format", format))
            .andExpect(status().isBadRequest());


  }
}


