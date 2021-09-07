package com.GroceryStore.console;

import com.GroceryStore.Products.Drink;
import com.GroceryStore.Products.Fruit;
import com.GroceryStore.Products.Product;
import com.GroceryStore.Store;

import java.util.Locale;
import java.util.Scanner;

public interface UI {
    Store store= new Store("");
    Scanner scanner = new Scanner(System.in);
    String[] MENU =  new String[0];
    String[] PRODUCT_TYPES =  new String[0];
    String WELCOME =  "";
    String MENU_PROMPT = "";
    String ENTER_PROMPT = "";
    String PRODUCT_PROMPT = "";
    String[] PRODUCT_FIELDS =  new String[0];
    String[] DRINK_FIELDS =  new String[0];
    String[] FRUIT_FIELDS =  new String[0];
    String TOSS_PROMPT = "";
    String SELL_PROMPT =  "";
    String[] ERROR_MESS =  new String[0];


    static void welcome(String name) {
        System.out.println(WELCOME + name + "!");
    }

    static void displayOptions(String prompt, String[] options) {
        System.out.println(prompt);
        for (String option : options) {
            System.out.println(option);
        }

    }



    default void start(Store store) {
        welcome(store.getName());
        while (true) {
            displayOptions(MENU_PROMPT, MENU);
            int choice = getInt(1, 5, ENTER_PROMPT);
            handleMenuSelection(choice);
        }
    }

    static int getInt(int min, int max, String prompt) {
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

    static String getString(String prompt, boolean isRequired) {

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

    default void handleMenuSelection(int choice) {
        switch (choice) {
            case 1 -> addProduct();
            case 2 -> throwAwayProduct();
            case 3 -> displayProducts();
            case 4 -> sellProduct();
            case 5 -> System.exit(0);
            default -> System.out.println(ERROR_MESS[3]);
        }
    }

    default void addProduct() {
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

    static Drink getDrinkDetails() {
        return new Drink(
                getString(PRODUCT_FIELDS[0], true),
                getInt(1, Integer.MAX_VALUE, PRODUCT_FIELDS[1]),
                getString(PRODUCT_FIELDS[2], true),
                getString(PRODUCT_FIELDS[3], false),
                getInt(1, Integer.MAX_VALUE, DRINK_FIELDS[0]),
                getInt(0, Drink.UNITS.length,DRINK_FIELDS[1])
        );

    }

    default Fruit getFruitDetails() {
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

    default Product selectProduct(String prompt) {
        displayProducts();
        String choice = getString(prompt, false);
        return store.getProduct(choice);

    }


    default void throwAwayProduct() {
        Product prod = selectProduct(TOSS_PROMPT);

        if (prod == null) {
            System.out.println(ERROR_MESS[5]);
            return;
        }

        store.throwAway(prod);
    }


    default void displayProducts() {
        System.out.println(store.getInventory());
    }

    default void sellProduct() {
        Product prod = selectProduct(SELL_PROMPT);

        if (prod == null) {
            System.out.println(ERROR_MESS[5]);
            return;
        }
        store.purchase(prod);
    }


}
