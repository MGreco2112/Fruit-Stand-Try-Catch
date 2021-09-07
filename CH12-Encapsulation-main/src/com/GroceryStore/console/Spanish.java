package com.GroceryStore.console;

import com.GroceryStore.Products.Drink;

import java.util.Arrays;

public class Spanish implements Language{
    @Override
    public String[] MENU() {
        return new String[] {
                "1. agregar producto al inventario",
                "2. desechar un producto",
                "3. enumerar los productos disponibles",
                "4. vender un producto",
                "5. salir",
                "6. cambiar de idioma"
        };
    }

    @Override
    public String[] PRODUCT_TYPES() {
        return new String[] {
                "1. una Bebida",
                "2. Fruta"
        };
    }

    @Override
    public String WELCOME() {
        return "Bienvenida a ";
    }

    @Override
    public String MENU_PROMPT() {
        return "¿Qué es lo que quieres hacer?";
    }

    @Override
    public String ENTER_PROMPT() {
        return "Ingresar selección: ";
    }

    @Override
    public String PRODUCT_PROMPT() {
        return "¿Qué tipo de producto?";
    }

    @Override
    public String[] PRODUCT_FIELDS() {
        return new String[] {
                "Nombre:",
                "Precio:",
                "IDENTIFICACIÓN:",
                "Descripción"
        };
    }

    @Override
    public String[] DRINK_FIELDS() {
        return new String[] {
                "Volumen:",
                "Introduzca el índice de las siguientes\n" + Arrays.toString(Drink.UNITS)
        };
    }

    @Override
    public String[] FRUIT_FIELDS() {
        return new String[] {
                "¿Esta fruta es orgánica?\n (y) Si\n (n) No",
                "Esta fruta es orgánica",
                "La fruta no es orgánica",
                "Dureza de 1 a 10:"
        };
    }

    @Override
    public String TOSS_PROMPT() {
        return "¿Qué identificación te gustaría tirar?";
    }

    @Override
    public String SELL_PROMPT() {
        return "Ingrese el ID del producto a vender, presione enter para cancelar";
    }

    @Override
    public String[] ERROR_MESS() {
        return new String[] {
                "OK",
                "Entrada no válida, inténtalo de nuevo",
                "Debe ingresar algo",
                "Se proporcionó una selección no válida",
                "Error, tipo incorrecto",
                "404 - Producto no encontrado"
        };
    }
}
