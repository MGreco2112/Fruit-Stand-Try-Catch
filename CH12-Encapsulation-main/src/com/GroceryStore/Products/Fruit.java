package com.GroceryStore.Products;

public class Fruit extends Product{
    private String type;
    private String supplier;
    private boolean isOrganic;
    private int hardness;

    public Fruit(String name, int price, String id, String description, int hardness) {
        super(name, price, id, description);
        isOrganic = false;
        this.hardness = hardness;
    }

    public Fruit(String name, int price, String id, String description, int hardness, boolean isOrganic) {
        super(name, price, id, description);
        this.isOrganic = isOrganic;
        this.hardness = hardness;
    }

    public int getHardness() {
        return hardness;
    }

    public String toString() {
        return super.toString() + "isOrganic: " + isOrganic + " | hardness: " + hardness + " | ";
    }
}