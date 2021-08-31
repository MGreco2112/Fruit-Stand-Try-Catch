package com.Util;

import java.text.NumberFormat;

public abstract class Formatter {
    public static String getDisplayPrice(int price) {
        return NumberFormat.getCurrencyInstance().format(price / 100.0);
    }
}
