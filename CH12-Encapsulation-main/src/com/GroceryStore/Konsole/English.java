package com.GroceryStore.Konsole;

import com.GroceryStore.Products.Drink;
import com.GroceryStore.Store;

import java.util.Arrays;

public class English extends UI{
    
    public English(Store store) {
        super(store,
                new String[] {
                "1. add product to inventory",
                "2. throw away a product",
                "3. list products available",
                "4. sell a product",
                "5. quit",
                "6. change language"},
                new String[] {
                "1. Drink",
                "2. Fruit"},
                new String[] {
                        "Name:",
                        "Price:",
                        "ID:",
                        "Description"},
                new String[] {
                        "Volume: ",
                        "Enter the index of the following\n" + Arrays.toString(Drink.UNITS)},
                new String[] {
                        "Is this fruit organic?\n(y)es\n(n)o",
                        "This Fruit is Organic",
                        "The Fruit is not Organic",
                        "Hardness from 1 to 10: "},
                new String[] {
                        "OK",
                        "Invalid entry, try again",
                        "Must enter something",
                        "Invalid selection provided",
                        "Error, bad type",
                        "404 - Product not Found"},
                "Welcome to ",
                "What do you want to do?",
                "Enter selection: ",
                "What kind of Product?",
                "Which ID would you like to throw away?",
                "Enter the ID of the product to sell, press enter to cancel"
                );
    }


}
