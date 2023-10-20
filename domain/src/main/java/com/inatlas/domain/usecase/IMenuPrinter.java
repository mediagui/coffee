package com.inatlas.domain.usecase;

import java.util.Scanner;

public interface IMenuPrinter {
  default int getChoice() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter your choice: ");
    String choice = scanner.next();

    if (choice.matches("\\d+")) {
      return Integer.parseInt(choice);
    } else {
      System.out.println("Invalid choice");
      return getChoice();
    }
  }
}
