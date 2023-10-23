package com.inatlas.infra.controller;

import com.inatlas.infra.api.receipt.ReceiptControllerApi;
import com.inatlas.infra.service.ReceiptService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.io.IOException;


/**
 * Controller class that handles operations related to receipts.
 */
@Controller
public class ReceiptController implements ReceiptControllerApi {

  private final ReceiptService receiptService;
  public ReceiptController(ReceiptService receiptService) {
    this.receiptService = receiptService;
  }

  /**
   * Retrieves a receipt.
   * @param format Format of the receipt (required)
   * @return A ResponseEntity containing the response details as a PDF.
   */
  @Override
  public ResponseEntity<Resource> getReceipt(String format) throws IOException {
    return receiptService.getReceipt(format);
  }


}
