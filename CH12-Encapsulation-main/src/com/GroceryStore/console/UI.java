package com.GroceryStore.console;

import com.GroceryStore.*;
import com.GroceryStore.Products.Drink;
import com.GroceryStore.Products.Fruit;
import com.GroceryStore.Products.Product;

import java.util.Arrays;
import java.util.Scanner;

public class UI {
    private Store store;
    private static Scanner scanner = new Scanner(System.in);
    private final static String[] MENU = new String[] {
            "1. add product to inventory",
            "2. throw away a product",
            "3. list products available",
            "4. sell a product",
            "5. quit"
    };
    private final static String[] PRODUCT_TYPES = new String[] {
            "1. Drink",
            "2. Fruit"
    };

    public static void welcome(String name) {
        System.out.println("Welcome to " + name + "!");
    }

    public static void displayOptions(String prompt, String[] options) {
        System.out.println(prompt);
        for (String option : options) {
            System.out.println(option);
        }

    }

    public void start(Store store) {
        this.store = store;
        welcome(store.getName());
        while (true) {
            displayOptions("What do you want to do?", MENU);
            int choice = getInt(1, 5, "Enter selection between 1 and 5:");
            handleMenuSelection(choice);
        }
    }

    public static int getInt(int min, int max, String prompt) {
        int option;
        do {
            System.out.println(prompt);
            String input = scanner.nextLine();
            try {
                option = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Invalid entry, try again");
                option = max - 1;
            }
        } while (option < min || option > max);
        return option;
    }

    public static String getString(String prompt, boolean isRequired) {

        String input;

        do {
            System.out.println(prompt);
            input = scanner.nextLine();

            if (isRequired && input.length() == 0) {
                System.out.println("Must enter something");
                continue;
            }
            break;
        } while (true);
        return input;
    }

    public void handleMenuSelection(int choice) {
        switch (choice) {
            case 1 -> addProduct();
            case 2 -> throwAwayProduct();
            case 3 -> displayProducts();
            case 4 -> sellProduct();
            case 5 -> System.exit(0);
            default -> System.out.println("Invalid selection provided");
        }
    }

    private void addProduct() {
        displayOptions("What kind of Product?", PRODUCT_TYPES);
        int choice = getInt(1, PRODUCT_TYPES.length, "Enter a number:");
        Product newProduct;
        switch (choice) {
            case 1 -> newProduct = getDrinkDetails();
            //TODO finish getFruitDetails
            case 2 -> newProduct = getFruitDetails();
            default -> {
                System.out.println("Error, bad type");
                newProduct = null;
            }
        }
        store.addToInventory(newProduct);
    }

    private static Drink getDrinkDetails() {
        return new Drink(
                getString("DrinkName: ", true),
                getInt(1, Integer.MAX_VALUE, "Price"),
                getString("ID: ", true),
                getString("Description: ", false),
                getInt(1, Integer.MAX_VALUE, "Volume: "),
                getInt(0, Drink.UNITS.length,"Enter the index of the following\n" + Arrays.toString(Drink.UNITS))
        );

    }

    private Product selectProduct(String prompt) {
        System.out.println(displayProducts());
        String choice = getString(prompt, false);
        return store.getProduct(choice);

    }

    //TODO finish this
    private Fruit getFruitDetails() {
        return null;
    }

    private void throwAwayProduct() {
        Product prod = selectProduct("Which ID would you like to throw away?");

        if (prod == null) {
            System.out.println("404 - Product not Found");
            return;
        }

        store.throwAway(prod);
    }

    private String displayProducts() {
        return store.getInventory();
    }


    private void sellProduct() {
        Product prod = selectProduct("Enter the ID of the product to sell, press enter to cancel");

        if (prod == null) {
            System.out.println("404 - Product not Found");
            return;
        }
        store.purchase(prod);
    }

}
