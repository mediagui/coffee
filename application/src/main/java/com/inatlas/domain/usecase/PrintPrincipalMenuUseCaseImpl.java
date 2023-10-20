/**
 * This class implements the PrintPrincipalMenuUseCase interface.
 * It provides a method to print the principal menu options.
 */
package com.inatlas.domain.usecase;

import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
 @Profile("command-line-runner")
public class PrintPrincipalMenuUseCaseImpl implements PrintPrincipalMenuUseCase {
  
  /**
   * Prints the principal menu options.
   */
  @Override
  public void printMenu() {
    System.out.println("1. Products");
    System.out.println("2. Exit");
  }
}
