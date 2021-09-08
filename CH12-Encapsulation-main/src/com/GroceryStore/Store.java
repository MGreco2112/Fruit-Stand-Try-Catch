package com.GroceryStore;

import com.GroceryStore.Konsole.*;
import com.GroceryStore.Products.Drink;
import com.GroceryStore.Products.Fruit;
import com.GroceryStore.Products.Product;

import com.Util.Formatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store {
    private final List<Product> inventory = new ArrayList<>();
    private int balance = 500_00;
    private final String name;
    private final Scanner scanner = new Scanner(System.in);
    public UI userInterface;

    public Store(String name) {
        this.name = name;
        setLang();
    }

    public void setLang() {
        int choice = getInt(1, 2, "Select a Language:\n" +
                "1) English\n2) Español");

        switch (choice) {
            case 1 -> this.userInterface = new English(this);
            case 2 -> this.userInterface = new Spanish(this);
            default -> System.out.println("Invalid selection");
        }
        userInterface.start();
    }

    public void addToInventory(Product product) {
        if (product.getPrice() > balance) {
            System.out.println("Not enough funds");
            return;
        }
        balance -= product.getPrice();
        inventory.add(product);
    }

    public void addToInventory(String name, int price, String id, String description, int volume, int vu) {
        Drink drink = new Drink(name, price, id, description, volume, vu);
        addToInventory(drink);
    };

    public void addToInventory(String name, int price, String id, String description, int hardness) {
        Fruit fruit = new Fruit(name, price, id, description, hardness);
        addToInventory(fruit);
    };

    public void throwAway(Product product) {
        inventory.remove(product);
    }

    public String getInventory() {
        String output = "";
        for (Product prod : inventory) {
            output += prod + "\n";
        }
        return output;
    }

    public Product getProduct(String id) {
        for (Product product : inventory) {
            if (id.equals(product.getId())) {
                return product;
            }
        }
        return null;
    }

    public void purchase(Product product) {
        balance += product.getPrice();
        throwAway(product);
    }

    private int getInt(int min, int max, String prompt) {
        int option;
        do {
            System.out.println(prompt);
            String input = scanner.nextLine();
            try {
                option = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("invalid selection");
                option = max - 1;
            }
        } while (option < min || option > max);
        return option;
    }

    public String getBalance() {return Formatter.getDisplayPrice(balance);}

    public String getName() {return name;}

}
