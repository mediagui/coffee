package com.inatlas.infra.exception;

public class ReceiptNotGeneratedException extends RuntimeException{
  public ReceiptNotGeneratedException(Throwable cause) {
    super(cause);
  }
}
