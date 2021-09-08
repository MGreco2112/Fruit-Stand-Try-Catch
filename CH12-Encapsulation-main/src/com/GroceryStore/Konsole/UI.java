package com.GroceryStore.Konsole;

import com.GroceryStore.Products.Drink;
import com.GroceryStore.Products.Fruit;
import com.GroceryStore.Products.Product;
import com.GroceryStore.Store;

import java.util.Locale;
import java.util.Scanner;

public abstract class UI {
    protected final String[] MENU;
    protected final String[] PRODUCT_TYPES;
    protected final String[] PRODUCT_FIELDS;
    protected final String[] DRINK_FIELDS;
    protected final String[] FRUIT_FIELDS;
    protected final String[] ERROR_MESS;
    protected final String   WELCOME;
    protected final String   MENU_PROMPT;
    protected final String   ENTER_PROMPT;
    protected final String   PRODUCT_PROMPT;
    protected final String   TOSS_PROMPT;
    protected final String   SELL_PROMPT;

    protected final Store store;

    protected final static Scanner  scanner = new Scanner(System.in);

    public UI(
            Store store,
            String[] MENU,
            String[] PRODUCT_TYPES,
            String[] PRODUCT_FIELDS,
            String[] DRINK_FIELDS,
            String[] FRUIT_FIELDS,
            String[] ERROR_MESS,
            String WELCOME,
            String MENU_PROMPT,
            String ENTER_PROMPT,
            String PRODUCT_PROMPT,
            String TOSS_PROMPT,
            String SELL_PROMPT
            ) {
        this.store = store;
        this.MENU = MENU;
        this.PRODUCT_TYPES = PRODUCT_TYPES;
        this.PRODUCT_FIELDS = PRODUCT_FIELDS;
        this.DRINK_FIELDS = DRINK_FIELDS;
        this.FRUIT_FIELDS = FRUIT_FIELDS;
        this.ERROR_MESS = ERROR_MESS;
        this.WELCOME = WELCOME;
        this.MENU_PROMPT = MENU_PROMPT;
        this.ENTER_PROMPT = ENTER_PROMPT;
        this.PRODUCT_PROMPT = PRODUCT_PROMPT;
        this.TOSS_PROMPT = TOSS_PROMPT;
        this.SELL_PROMPT = SELL_PROMPT;
    }

    private void welcome(String name) {
        System.out.println(WELCOME + name + "!");
    }

    private void displayOptions(String prompt, String[] options) {
        System.out.println(prompt);
        for (String option : options) {
            System.out.println(option);
        }

    }

    public void start() {
        welcome(store.getName());
        while (true) {
            displayOptions(MENU_PROMPT, MENU);
            int choice = getInt(1, 6, ENTER_PROMPT);
            handleMenuSelection(choice);
        }
    }

    private int getInt(int min, int max, String prompt) {
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

    private String getString(String prompt, boolean isRequired) {

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

    private void handleMenuSelection(int choice) {
        switch (choice) {
            case 1 -> addProduct();
            case 2 -> throwAwayProduct();
            case 3 -> displayProducts();
            case 4 -> sellProduct();
            case 5 -> System.exit(0);
            case 6 -> store.setLang();
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

    private Drink getDrinkDetails() {
        return new Drink(
                getString(PRODUCT_FIELDS[0], true),
                getInt(1, Integer.MAX_VALUE, PRODUCT_FIELDS[1]),
                getString(PRODUCT_FIELDS[2], true),
                getString(PRODUCT_FIELDS[3], false),
                getInt(1, Integer.MAX_VALUE, DRINK_FIELDS[0]),
                getInt(0, Drink.UNITS.length, DRINK_FIELDS[1])
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
                getInt(1, Integer.MAX_VALUE, PRODUCT_FIELDS[1]),
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
