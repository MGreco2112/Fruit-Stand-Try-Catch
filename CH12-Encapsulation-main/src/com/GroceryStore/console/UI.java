package com.GroceryStore.console;

import com.GroceryStore.*;

import java.util.Scanner;

public class UI {
    private static Scanner scanner = new Scanner(System.in);

    public static void welcome(String name) {
        System.out.println("Welcome to " + name + "!");
    }

    public static void optionMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1. add product to inventory");
        System.out.println("2. throw away a product");
        System.out.println("3. list products available");
        System.out.println("4. sell a product");
        System.out.println("5. quit");
    }

    public static void start(Store store) {
        welcome(store.getName());
        optionMenu();
        int choice = getInt(1, 5, "Enter selection between 1 and 5:");
    }

    public static int getInt(int min, int max, String prompt) {
        int option;
        do {
            System.out.println(prompt);
            String input = scanner.next();
            // TODO: catch the below in case of error and prevent application from crashing
            try {
                option = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Invalid entry, try again");
                option = max - 1;
            }
        } while (option < min || option > max);
        return option;
    }
}
