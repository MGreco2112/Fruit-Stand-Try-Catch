package com.GroceryStore.console;

import com.GroceryStore.Products.Drink;

import java.util.Arrays;

public class English implements Language{

    @Override
    public String[] MENU() {
        return new String[] {
                "1. add product to inventory",
                "2. throw away a product",
                "3. list products available",
                "4. sell a product",
                "5. quit"
        };
    }

    @Override
    public String[] PRODUCT_TYPES() {
        return new String[] {
                "1. Drink",
                "2. Fruit"
        };
    }

    @Override
    public String WELCOME() {
        return "Welcome to ";
    }

    @Override
    public String MENU_PROMPT() {
        return "What do you want to do?";
    }

    @Override
    public String ENTER_PROMPT() {
        return "Enter selection :";
    }

    @Override
    public String PRODUCT_PROMPT() {
        return "What kind of Product?";
    }

    @Override
    public String[] PRODUCT_FIELDS() {
        return new String[] {
                "Name:",
                "Price:",
                "ID:",
                "Description"
        };

    }

    @Override
    public String[] DRINK_FIELDS() {
        return new String[] {
                "Volume: ",
                "Enter the index of the following\n" + Arrays.toString(Drink.UNITS)
        };
    }

    @Override
    public String[] FRUIT_FIELDS() {
        return new String[] {
                "Is this fruit organic?\n(y)es\n(n)o",
                "This Fruit is Organic",
                "The Fruit is not Organic",
                "Hardness from 1 to 10: "
        };
    }

    @Override
    public String TOSS_PROMPT() {
        return "Which ID would you like to throw away?";
    }

    @Override
    public String SELL_PROMPT() {
        return "Enter the ID of the product to sell, press enter to cancel";
    }

    @Override
    public String[] ERROR_MESS() {
        return new String[] {
                "OK",
                "Invalid entry, try again",
                "Must enter something",
                "Invalid selection provided",
                "Error, bad type",
                "404 - Product not Found"
        };
    }
}
