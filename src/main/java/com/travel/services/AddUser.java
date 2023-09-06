package com.travel.services;

import com.travel.constants.GlobalConst;
import com.travel.utils.SystemOut;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class AddUser {
    private static String nome;
    private static String cognome;
    private static String data;
    private static String indirizzo;
    private static String documentoId;
    private static int lastId;
    private static String utente;


    public static void inserisciDatiUtente() {
        Scanner scanner = new Scanner(System.in);

        SystemOut.question("Inserisci il tuo nome:");
        nome = inputStringaValida(scanner, "il nome:");

        SystemOut.question("Inserisci il tuo cognome");
        cognome = inputStringaValida(scanner, "il cognome:");

        SystemOut.question("Inserisci la tua data di nascita nel formato dd/MM/yyyy:");
        data = inputDataValida(scanner, "la data di nascita:");

        SystemOut.question("Inserisci il tuo indirizzo");
        indirizzo = scanner.nextLine();

        SystemOut.question("Inserisci un ID di un tuo documento");
        documentoId = scanner.nextLine();

        leggiUltimoID(GlobalConst.CSV_FILENAME_UTENTI, ";");

        utente = lastId + 1 + ";" + nome + ";" + cognome + ";" + data + ";" + indirizzo + ";" + documentoId;

        aggiungiUtente(utente);
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

    public static void aggiungiUtente(String utente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(GlobalConst.CSV_FILENAME_UTENTI, true))) {
            if (fileHaContenuto(GlobalConst.CSV_FILENAME_UTENTI)) {
                writer.newLine(); // Aggiungi una nuova riga vuota prima dell'utente
            }
            writer.write(utente);
            SystemOut.success("Utente aggiunto correttamente.");
        } catch (IOException e) {
            SystemOut.error("Si è verificato un errore durante l'aggiunta dell' utente.");
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
