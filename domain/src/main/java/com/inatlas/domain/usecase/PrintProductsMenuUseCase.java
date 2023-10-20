package com.inatlas.domain.usecase;


import java.util.Scanner;

public interface PrintProductsMenuUseCase extends IMenuPrinter  {

  void executeChoice(int choice);
  void printMenu() ;



}
