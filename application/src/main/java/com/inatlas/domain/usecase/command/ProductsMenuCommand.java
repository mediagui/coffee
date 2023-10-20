/**
 * This is a command class that represents the Products Menu Command.
 * It implements the Command interface and provides the functionality to execute the command.
 * The command displays a menu of products and allows the user to choose an option.
 * The user can select options such as viewing the products menu, placing an order, or exiting the program.
 * The command uses the PrintProductsMenuUseCase to print the menu and execute the selected choice.
 * It prompts the user for input and validates the choice before executing it.
 * The command continues to prompt the user for input until the user selects the exit option.
 */
package com.inatlas.domain.usecase.command;

import com.inatlas.domain.usecase.PrintProductsMenuUseCase;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("products")
 @Profile("command-line-runner")
public class ProductsMenuCommand implements Command {
    private final PrintProductsMenuUseCase printProductsMenuUseCase;

    @Lazy
    public ProductsMenuCommand(PrintProductsMenuUseCase printProductsMenuUseCase) {
        this.printProductsMenuUseCase = printProductsMenuUseCase;
    }

    /**
     * Executes the Products Menu Command.
     * Displays the menu, prompts the user for input, and executes the selected choice.
     * Continues to prompt the user for input until the user selects the exit option.
     */
    @Override
    public void execute() {
        int choice = -1;
        while (choice != 100) {
            printProductsMenuUseCase.printMenu();
            choice = getChoice();
            executeChoice(choice);
        }
    }

    /**
     * Prompts the user for input and retrieves the choice.
     * Validates the choice and returns the integer value.
     * If the choice is invalid, prompts the user again until a valid choice is entered.
     *
     * @return The user's choice as an integer.
     */
    private int getChoice() {
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
     * Performs the corresponding action based on the choice.
     *
     * @param choice The user's choice.
     */
    private void executeChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Products Menu Command");
                break;
            case 2:
                System.out.println("Order");
                break;
            case 3:
                System.out.println("Exit");
                System.exit(0);
                break;
            case 100:
                return;
            default:
                System.out.println("Invalid option");
                break;
        }
    }
}
