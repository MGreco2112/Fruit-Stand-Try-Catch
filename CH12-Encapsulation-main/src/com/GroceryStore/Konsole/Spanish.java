package com.GroceryStore.Konsole;

import com.GroceryStore.Products.Drink;
import com.GroceryStore.Store;

import java.util.Arrays;

public class Spanish extends UI{
    public Spanish(Store store) {
        super(store,
                new String[] {
                        "1. agregar producto al inventario",
                        "2. desechar un producto",
                        "3. enumerar los productos disponibles",
                        "4. vender un producto",
                        "5. salir",
                        "6. cambiar de idioma"
                },
                new String[] {
                        "1. una Bebida",
                        "2. Fruta"
                },
                new String[] {
                        "Nombre:",
                        "Precio:",
                        "IDENTIFICACIÓN:",
                        "Descripción"
                },
                new String[] {
                        "Volumen:",
                        "Introduzca el índice de las siguientes\n" + Arrays.toString(Drink.UNITS)
                },
                new String[] {
                        "¿Esta fruta es orgánica?\n (y) Si\n (n) No",
                        "Esta fruta es orgánica",
                        "La fruta no es orgánica",
                        "Dureza de 1 a 10:"
                },
                new String[] {
                        "OK",
                        "Entrada no válida, inténtalo de nuevo",
                        "Debe ingresar algo",
                        "Se proporcionó una selección no válida",
                        "Error, tipo incorrecto",
                        "404 - Producto no encontrado"
                },
                "Bienvenida a ",
                "¿Qué es lo que quieres hacer?",
                "Ingresar selección: ",
                "¿Qué tipo de producto?",
                "¿Qué identificación te gustaría tirar?",
                "Ingrese el ID del producto a vender, presione enter para cancelar"
        );
    }
}
