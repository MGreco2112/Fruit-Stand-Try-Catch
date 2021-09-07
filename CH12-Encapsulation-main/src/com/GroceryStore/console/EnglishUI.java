package com.GroceryStore.console;

import com.GroceryStore.*;
import com.GroceryStore.Products.Drink;
import com.GroceryStore.Products.Fruit;
import com.GroceryStore.Products.Product;

import java.util.Locale;
import java.util.Scanner;

public class EnglishUI{
    private Store store;
    private final static Scanner scanner = new Scanner(System.in);
    private Language lang;

    public EnglishUI(Store store) {
        this.store = store;
        setLang();
    }

    private void setLang() {
        int choice = getInt(1, 2, "Select a Language:\n" +
                "1) English\n2) Español");

        switch (choice) {
            case 1 -> this.lang = new English();
            case 2 -> this.lang = new Spanish();
            default -> System.out.println("Invalid selection");
        }
    }


    public void welcome(String name) {
        System.out.println(lang.WELCOME() + name + "!");
    }

    public void displayOptions(String prompt, String[] options) {
        System.out.println(prompt);
        for (String option : options) {
            System.out.println(option);
        }

    }

    public void start() {
        welcome(store.getName());
        while (true) {
            displayOptions(lang.MENU_PROMPT(), lang.MENU());
            int choice = getInt(1, 6, lang.ENTER_PROMPT());
            handleMenuSelection(choice);
        }
    }

    public int getInt(int min, int max, String prompt) {
        int option;
        do {
            System.out.println(prompt);
            String input = scanner.nextLine();
            try {
                option = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println(lang.ERROR_MESS()[1]);
                option = max - 1;
            }
        } while (option < min || option > max);
        return option;
    }

    public String getString(String prompt, boolean isRequired) {

        String input;

        do {
            System.out.println(prompt);
            input = scanner.nextLine();

            if (isRequired && input.length() == 0) {
                System.out.println(lang.ERROR_MESS()[2]);
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
            case 6 -> setLang();
            default -> System.out.println(lang.ERROR_MESS()[3]);
        }
    }

    private void addProduct() {
        displayOptions(lang.PRODUCT_PROMPT(), lang.PRODUCT_TYPES());
        int choice = getInt(1, lang.PRODUCT_TYPES().length, lang.ENTER_PROMPT());
        Product newProduct;
        switch (choice) {
            case 1 -> newProduct = getDrinkDetails();
            case 2 -> newProduct = getFruitDetails();
            default -> {
                System.out.println(lang.ERROR_MESS()[4]);
                newProduct = null;
            }
        }
        store.addToInventory(newProduct);
    }

    private Drink getDrinkDetails() {
        return new Drink(
                getString(lang.PRODUCT_FIELDS()[0], true),
                getInt(1, Integer.MAX_VALUE, lang.PRODUCT_FIELDS()[1]),
                getString(lang.PRODUCT_FIELDS()[2], true),
                getString(lang.PRODUCT_FIELDS()[3], false),
                getInt(1, Integer.MAX_VALUE, lang.DRINK_FIELDS()[0]),
                getInt(0, Drink.UNITS.length, lang.DRINK_FIELDS()[1])
        );

    }

    private Fruit getFruitDetails() {
        String choice = "";
        boolean isOrganic = false;
        do {
            isOrganic = false;
            System.out.println(lang.FRUIT_FIELDS()[0]);
            choice = scanner.nextLine();

            switch (choice.toLowerCase(Locale.ROOT)) {
                case "y":
                    isOrganic = true;
                    System.out.println(lang.FRUIT_FIELDS()[1]);
                    break;
                case "n":
                    System.out.println(lang.FRUIT_FIELDS()[2]);
                    break;
                default:
                    System.out.println(lang.ERROR_MESS()[1]);
            }
        } while (!choice.equals("y") && !choice.equals("n"));

        return new Fruit(
                getString(lang.PRODUCT_FIELDS()[0], true),
                getInt(1, Integer.MAX_VALUE, lang.PRODUCT_FIELDS()[1]),
                getString(lang.PRODUCT_FIELDS()[2], true),
                getString(lang.PRODUCT_FIELDS()[3], false),
                getInt(1, 10, lang.FRUIT_FIELDS()[3]),
                isOrganic
        );
    }

    private Product selectProduct(String prompt) {
        displayProducts();
        String choice = getString(prompt, false);
        return store.getProduct(choice);

    }


    private void throwAwayProduct() {
        Product prod = selectProduct(lang.TOSS_PROMPT());

        if (prod == null) {
            System.out.println(lang.ERROR_MESS()[5]);
            return;
        }

        store.throwAway(prod);
    }


    private void displayProducts() {
        System.out.println(store.getInventory());
    }

    private void sellProduct() {
        Product prod = selectProduct(lang.SELL_PROMPT());

        if (prod == null) {
            System.out.println(lang.ERROR_MESS()[5]);
            return;
        }
        store.purchase(prod);
    }

}
