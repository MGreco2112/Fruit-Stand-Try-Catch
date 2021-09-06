package com.GroceryStore.console;

import com.GroceryStore.*;
import com.GroceryStore.Products.Drink;
import com.GroceryStore.Products.Fruit;
import com.GroceryStore.Products.Product;

import java.util.Arrays;
import java.util.Locale;
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

    private final static String WELCOME = "Welcome to ";

    private final static String MENU_PROMPT = "What do you want to do?";

    private final static String ENTER_PROMPT = "Enter selection :";

    private final static String PRODUCT_PROMPT = "What kind of Product?";

    private final static String[] PRODUCT_FIELDS = new String[] {
            "Name:",
            "Price:",
            "ID:",
            "Description"
    };

    private final static String[] DRINK_FIELDS = new String[] {
            "Volume: ",
            "Enter the index of the following\n" + Arrays.toString(Drink.UNITS)
    };

    private final static String[] FRUIT_FIELDS = new String[] {
            "Is this fruit organic?\n(y)es\n(n)o",
            "This Fruit is Organic",
            "The Fruit is not Organic",
            "Hardness from 1 to 10: "
    };

    private final static String TOSS_PROMPT = "Which ID would you like to throw away?";

    private final static String SELL_PROMPT = "Enter the ID of the product to sell, press enter to cancel";

    private final static String[] ERROR_MESS = new String[] {"OK", "Invalid entry, try again", "Must enter something"
            , "Invalid selection provided", "Error, bad type", "404 - Product not Found"};

    public static void welcome(String name) {
        System.out.println(WELCOME + name + "!");
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
            displayOptions(MENU_PROMPT, MENU);
            int choice = getInt(1, 5, ENTER_PROMPT);
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
                System.out.println(ERROR_MESS[1]);
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
                System.out.println(ERROR_MESS[2]);
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
            default -> System.out.println(ERROR_MESS[3]);
        }
    }

    private void addProduct() {
        displayOptions(PRODUCT_PROMPT, PRODUCT_TYPES);
        int choice = getInt(1, PRODUCT_TYPES.length, ENTER_PROMPT);
        Product newProduct;
        switch (choice) {
            case 1 -> newProduct = getDrinkDetails();
            case 2 -> newProduct = getFruitDetails();
            default -> {
                System.out.println(ERROR_MESS[4]);
                newProduct = null;
            }
        }
        store.addToInventory(newProduct);
    }

    private static Drink getDrinkDetails() {
        return new Drink(
                getString(PRODUCT_FIELDS[0], true),
                getInt(1, Integer.MAX_VALUE, PRODUCT_FIELDS[1]),
                getString(PRODUCT_FIELDS[2], true),
                getString(PRODUCT_FIELDS[3], false),
                getInt(1, Integer.MAX_VALUE, DRINK_FIELDS[0]),
                getInt(0, Drink.UNITS.length,DRINK_FIELDS[1])
        );

    }

    private Fruit getFruitDetails() {
        String choice = "";
        boolean isOrganic = false;
        do {
            isOrganic = false;
            System.out.println(FRUIT_FIELDS[0]);
            choice = scanner.nextLine();

            switch (choice.toLowerCase(Locale.ROOT)) {
                case "y":
                    isOrganic = true;
                    System.out.println(FRUIT_FIELDS[1]);
                    break;
                case "n":
                    System.out.println(FRUIT_FIELDS[2]);
                    break;
                default:
                    System.out.println(ERROR_MESS[1]);
            }
        } while (!choice.equals("y") && !choice.equals("n"));

        return new Fruit(
                getString(PRODUCT_FIELDS[0], true),
                getInt(1, Integer.MAX_VALUE,PRODUCT_FIELDS[1]),
                getString(PRODUCT_FIELDS[2], true),
                getString(PRODUCT_FIELDS[3], false),
                getInt(1, 10, FRUIT_FIELDS[3]),
                isOrganic
        );
    }

    private Product selectProduct(String prompt) {
        displayProducts();
        String choice = getString(prompt, false);
        return store.getProduct(choice);

    }


    private void throwAwayProduct() {
        Product prod = selectProduct(TOSS_PROMPT);

        if (prod == null) {
            System.out.println(ERROR_MESS[5]);
            return;
        }

        store.throwAway(prod);
    }


    private void displayProducts() {
        System.out.println(store.getInventory());
    }

    private void sellProduct() {
        Product prod = selectProduct(SELL_PROMPT);

        if (prod == null) {
            System.out.println(ERROR_MESS[5]);
            return;
        }
        store.purchase(prod);
    }

}
