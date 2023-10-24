package com.inatlas.infra.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.inatlas.domain.entity.Order;
import com.inatlas.domain.entity.Promotion;
import com.inatlas.domain.usecase.ApplyPromotionsUseCase;
import com.inatlas.domain.usecase.FindLastOrderCompletedUseCase;
import com.inatlas.infra.exception.ReceiptNotGeneratedException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Service class that handles operations related to generating receipts.
 */
@Service
public class ReceiptService {

  private final FindLastOrderCompletedUseCase findLastOrderCompletedUseCase;
  private final ApplyPromotionsUseCase applyPromotionsUseCase;

  /**
   * Constructs an instance of ReceiptService with the provided dependencies.
   *
   * @param findLastOrderCompletedUseCase The use case for finding the last completed order.
   * @param applyPromotionsUseCase        The use case for applying promotions to an order.
   */
  public ReceiptService(FindLastOrderCompletedUseCase findLastOrderCompletedUseCase, ApplyPromotionsUseCase applyPromotionsUseCase) {
    this.findLastOrderCompletedUseCase = findLastOrderCompletedUseCase;
    this.applyPromotionsUseCase = applyPromotionsUseCase;
  }

  /**
   * Retrieves the receipt in the specified format.
   *
   * @param format The format of the receipt (e.g., "pdf").
   * @return A ResponseEntity containing the receipt as a Resource.
   * @throws IOException If an I/O error occurs while generating the receipt.
   */
  public ResponseEntity<Resource> getReceipt(String format) throws IOException {
    // Se ha incluido un parámetro opcional para poder pasar un orderCompleted en los test (no debería hacerse así, pero quiero ahorrar tiempo) :-)

    final Optional<Order> lastOrderCompleted = findLastOrderCompletedUseCase.getLastOrderCompleted();

    if (lastOrderCompleted.isEmpty()) {
      return ResponseEntity.notFound().build();
    }


    if ("pdf".equals(format)) {
      Optional<Promotion> promotion = applyPromotionsUseCase.applyAndGetTheBestPromotion(lastOrderCompleted.get());

      if(promotion.isPresent())
        lastOrderCompleted.get().setPromotion(promotion.get().getName());

      // Generate the PDF file
      byte[] bytes = generatePDFReceipt(lastOrderCompleted);

      // Create a resource with the content of the PDF file
      Resource resource = new ByteArrayResource(bytes);

      // Return the resource with the corresponding content type
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(resource);


    } else {
      throw new UnsupportedOperationException("Not supported format");
    }


  }


  /*
   * Generates a PDF receipt for the provided order.
   * @param lastOrderCompleted The order for which to generate the receipt.
   * @return The generated PDF receipt.
   */
  byte[] generatePDFReceipt(Optional<Order> lastOrderCompleted) {
    Order order;

    if (lastOrderCompleted.isPresent()) {
      order = lastOrderCompleted.get();
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
      String json = null;
      try {
        json = mapper.writeValueAsString(order);
        String[] lines = json.split(System.lineSeparator());
        return writePDF(lines);

      } catch (IOException e) {
        throw new ReceiptNotGeneratedException(e);
      }
    }

    return new byte[0];
  }

  /*
   * Write a PDF receipt in the System.getProperty("java.io.tmpdir") folder.
   * @param lines The lines to include in the receipt.
   * @return The generated PDF receipt.
   */
  byte[] writePDF(String[] lines) throws IOException {

    if (lines.length == 0)
      throw new IOException("No lines to write");

    try (PDDocument document = new PDDocument()) {
      PDPage page = new PDPage();
      document.addPage(page);
      PDPageContentStream contentStream = new PDPageContentStream(document, page);
      contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.COURIER), 12);
      contentStream.beginText();
      contentStream.setLeading(14.5f);
      contentStream.newLineAtOffset(25, 725);

      for (String line : lines) {
        contentStream.showText(line);
        contentStream.newLine();
      }

      contentStream.endText();
      contentStream.close();
      String fileSeparator = System.getProperty("file.separator");
      document.save(System.getProperty("java.io.tmpdir") + fileSeparator +"receipt.pdf");
    }
    return Files.readAllBytes(Paths.get(System.getProperty("java.io.tmpdir") + "receipt.pdf"));
  }
}
