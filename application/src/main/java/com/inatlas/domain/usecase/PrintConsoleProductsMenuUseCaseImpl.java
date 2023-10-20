/**
 * This class is an implementation of the PrintProductsMenuUseCase interface.
 * It is responsible for printing the products menu to the console and executing the user's choice.
 * The menu includes a list of products, an option to return to the principal menu, and an option to print the receipt.
 * The selected products are added to the order.
 * This implementation is specifically designed for a non-web application.
 */
package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Order;
import com.inatlas.domain.entity.OrderItem;
import com.inatlas.domain.repository.ProductRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

import static com.inatlas.util.LogUtil.debug;
import static com.inatlas.util.LogUtil.info;
import static com.inatlas.util.LogUtil.error;


@Component
 @Profile("command-line-runner")
public class PrintConsoleProductsMenuUseCaseImpl implements PrintProductsMenuUseCase {
    private final ProductRepository productRepository;
    private final PrintReceiptUseCase printReceiptUseCase;
    private static final int RETURN = 100;
    private static final int PRINT_RECEPIT = 101;
    private static final int NEW_ORDER = 102;
    private final AddSelectedProductsToOrderUseCase addSelectedProductsToOrder;
    private final List<OrderItem> orderItems = new LinkedList<>();

    public PrintConsoleProductsMenuUseCaseImpl(ProductRepository productRepository, PrintReceiptUseCase printReceiptUseCase, AddSelectedProductsToOrderUseCase addSelectedProductsToOrder) {
        this.productRepository = productRepository;
        this.printReceiptUseCase = printReceiptUseCase;
        this.addSelectedProductsToOrder = addSelectedProductsToOrder;
    }

    @Override
    public void printMenu() {
        printProductsMenu();
        int choice = getChoice();
        executeChoice(choice);
    }

    private void printProductsMenu() {
        productRepository.findAll().forEach(System.out::println);
        System.out.println("100. Return to principal menu");
        System.out.println("101. Print receipt");
        System.out.println("102. New order");
    }

    @Override
    public void executeChoice(int choice) {
        switch (choice) {
            case RETURN:
                return;
            case PRINT_RECEPIT:
                debug("Products Receipt");
                printReceiptUseCase.print(new Order(orderItems));
                break;
            case NEW_ORDER:
                debug("New order");
                orderItems.clear();
                break;
            default:
                try {
                    addSelectedProductsToOrder.addProduct(choice, orderItems);
                    debug("Product added to order");
                } catch (RuntimeException e) {
                    AssertionError error = new AssertionError("Product not found");
                    error(error.getMessage());

                }
        }
        printMenu();
    }
}
