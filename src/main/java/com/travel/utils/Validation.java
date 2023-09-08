package com.travel.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Validation {

    public static String inputNumberValida(Scanner scanner) {
        String input = scanner.nextLine();
        if (isPositiveNumber(input)) {
            return input;
        } else {
            SystemOut.warning("Inserimento non valido. Inserisci un numero." );
            return inputNumberValida(scanner);
        }
    }
    private static boolean isPositiveNumber(String id) {
        return id.matches("\\d+");
    }

    public static String inputStringaValida(Scanner scanner) {
        String input = scanner.nextLine();
        if (isString(input)) {
            return input;
        } else {
            SystemOut.warning("Inserimento non valido. Inserisci un testo." );
            return inputStringaValida(scanner);
        }
    }

    private static boolean isString(String str) {
        return str.matches("[a-zA-Z ]+");
    }

    public static String inputDataValida(Scanner scanner) {
        String input = scanner.nextLine();
        Date data = parseData(input);

        if (data != null) {
            return input;
        } else {
            SystemOut.warning("Inserimento non valido. Inserisci la data nel formato corretto(dd/MM/yyyy). ");
            return inputDataValida(scanner);
        }
    }

    private static Date parseData(String input) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            return dateFormat.parse(input);
        } catch (ParseException e) {
            return null; // Restituisce null in caso di parsing fallito
        }
    }

}
