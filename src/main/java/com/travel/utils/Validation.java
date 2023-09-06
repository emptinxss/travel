package com.travel.utils;

import java.util.Scanner;

public class Validation {

    public static String inputNumberValida(Scanner scanner) {
        String input = scanner.nextLine();
        if (isNumeroInterPositivo(input)) {
            return input;
        } else {
            SystemOut.warning("Inserimento non valido. Inserisci un numero." );
            return inputNumberValida(scanner);
        }
    }

    private static boolean isNumeroInterPositivo(String id) {
        return id.matches("\\d+");
    }
}
