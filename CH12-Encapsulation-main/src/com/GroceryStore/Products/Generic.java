package com.GroceryStore.Products;

public class Generic extends Product {
    Integer intMeta = null;
    String intMetaDesc = null;
    String strMeta = null;
    Object objMeta = null;

    public Generic(String name, int price, String id, String description) {
        super(name, price, id, description);
    }
}
