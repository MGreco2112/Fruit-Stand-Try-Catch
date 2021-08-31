package com.GroceryStore.Products;

public class Drink extends Product {
    public final static String[] UNITS = new String[] {"Gal", "oz", "litre", "ml", "pint", "quart"};
    private int volume;
    private String volumeUnit;


    public Drink(String name, int price, String id, String description, int volume, String volumeUnit) {
        super(name, price, id, description);
        this.volume = volume;
        this.volumeUnit = volumeUnit;
    }

    public Drink(String name, int price, String id, String description, int volume, int volumeUnit) {
        super(name, price, id, description);
        this.volume = volume;
        this.volumeUnit = UNITS[volumeUnit];
    }

    public String toString() {
        return super.toString() + "Volume: " + volume + " " + volumeUnit + " | ";
    }
}
