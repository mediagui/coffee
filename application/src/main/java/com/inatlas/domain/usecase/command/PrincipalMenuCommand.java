/**
 * This is a command class for the Principal Menu.
 * It handles the execution of different choices selected from the menu.
 */

package com.inatlas.domain.usecase.command;

import com.inatlas.domain.usecase.PrintPrincipalMenuUseCase;
import com.inatlas.domain.usecase.PrintProductsMenuUseCase;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("principal")
 @Profile("command-line-runner")
public class PrincipalMenuCommand implements Command {
    private final PrintPrincipalMenuUseCase printPrincipalMenuUseCase;
    private final PrintProductsMenuUseCase printProductsMenuUseCase;

    /**
     * Constructor for PrincipalMenuCommand.
     * @param printPrincipalMenuUseCase The use case for printing the Principal Menu.
     * @param printProductsMenuUseCase The use case for printing the Products Menu.
     */
    public PrincipalMenuCommand(PrintPrincipalMenuUseCase printPrincipalMenuUseCase, PrintProductsMenuUseCase printProductsMenuUseCase) {
        this.printPrincipalMenuUseCase = printPrincipalMenuUseCase;
        this.printProductsMenuUseCase = printProductsMenuUseCase;
    }

    /**
     * Executes the Principal Menu command.
     * Prints the menu, gets the user's choice, and executes the selected choice.
     */
    public void execute() {
        printPrincipalMenuUseCase.printMenu();
        int choice = getChoice();
        executeChoice(choice);
    }

    /**
     * Gets the user's choice from the console.
     * @return The user's choice as an integer.
     */
    int getChoice() {
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

    /**
     * Executes the selected choice based on the user's input.
     * @param choice The user's choice.
     */
    private void executeChoice(int choice){
        switch (choice) {
            case 1:
                System.out.println("Products");
                printProductsMenuUseCase.printMenu();
                break;
//            case 2:
//                System.out.println("Order");
//                break;
            case 2:
                System.out.println("Exit");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option");
                execute();
                break;
        }
    }
}
