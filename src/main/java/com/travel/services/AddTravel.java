package com.travel.services;

import com.travel.constants.GlobalConst;
import com.travel.utils.SystemOut;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AddTravel {
    private static String data;
    private static String durata;
    private static String partenza;
    private static String arrivo;
    private static String disponibile = "SI";
    private static String viaggio;
    private static int lastId;


    public static void inserisciDatiViaggio() {
        Scanner scanner = new Scanner(System.in);

        SystemOut.question("Inserisci la data del viaggio nel formato dd/MM/yyyy:");
        data = inputDataValida(scanner, "la data del viaggio:");

        SystemOut.question("Inserisci la durata del viaggio in ore:");
        durata = scanner.nextLine();; //TODO FALLO COME NUMERO VALIDO

        SystemOut.question("Inserisci la città di partenza:");
        partenza = inputStringaValida(scanner, "la città di partenza:");

        SystemOut.question("Inserisci la città di arrivo:");
        arrivo = inputStringaValida(scanner, "la città di arrivo:");

        leggiUltimoID(GlobalConst.CSV_FILENAME_VIAGGI, ";");

        viaggio = lastId + 1 + ";" + data + ";" + durata + ";" + partenza + ";" + arrivo +";" + disponibile;

        addUser(viaggio);
    }

    private static String inputDataValida(Scanner scanner, String message) {
        String input = scanner.nextLine();
        Date data = parseData(input);

        if (data != null) {
            return input;
        } else {
            SystemOut.warning("Inserimento non valido. Inserisci di nuovo " + message);
            return inputDataValida(scanner, message);
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
    private static String inputStringaValida(Scanner scanner, String message) {
        String input = scanner.nextLine();
        if (isString(input)) {
            return input;
        } else {
            SystemOut.warning("Inserimento non valido. Inserisci correttamente " + message );
            return inputStringaValida(scanner, message);
        }
    }

    private static boolean isString(String str) {
        return str.matches("[a-zA-Z ]+");
    }

    private static void addUser(String viaggio) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(GlobalConst.CSV_FILENAME_VIAGGI, true))) {
            if (fileHaContenuto(GlobalConst.CSV_FILENAME_VIAGGI)) {
                writer.newLine(); // Aggiungi una nuova riga vuota prima dell'utente
            }
            writer.write(viaggio);
            SystemOut.success("Viaggio aggiunto correttamente.");
        } catch (IOException e) {
            SystemOut.error("Si è verificato un errore durante l'aggiunta del viaggio.");
        }
    }

    private static boolean fileHaContenuto(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.readLine() != null;
        } catch (IOException e) {
            return false;
        }
    }

    private static void leggiUltimoID(String filePath, String delimiter) {
        int ultimoID = -1; // Inizializza con un valore negativo o un valore sensato per la tua logica

        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.nextLine(); // Ignora la prima riga (intestazione)

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] colonne = linea.split(delimiter);
                if (colonne.length > 0) {
                    int id = Integer.parseInt(colonne[0]); // Assumendo che l'ID sia nella prima colonna
                    if (id > ultimoID) {
                        ultimoID = id;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            SystemOut.error("File non trovato: " + e.getMessage());
        }

        lastId = ultimoID;
    }
}
