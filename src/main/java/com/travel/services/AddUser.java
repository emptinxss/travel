package com.travel.services;

import com.travel.constants.GlobalConst;
import com.travel.utils.SystemOut;
import com.travel.utils.Util;
import com.travel.utils.Validation;

import java.io.*;
import java.util.Scanner;

import static com.travel.constants.GlobalConst.DELIMITER;

public class AddUser {

    public static void inserisciDatiUtente() {
        Scanner scanner = new Scanner(System.in);

        String nome;
        String cognome;
        String data;
        String indirizzo;
        String documentoId;
        String utente;
        int lastId;

        SystemOut.question("Inserisci il tuo nome:");
        nome = Validation.inputStringaValida(scanner);

        SystemOut.question("Inserisci il tuo cognome");
        cognome = Validation.inputStringaValida(scanner);

        SystemOut.question("Inserisci la tua data di nascita nel formato dd/MM/yyyy:");
        data = Validation.inputDataValida(scanner);

        SystemOut.question("Inserisci il tuo indirizzo");
        indirizzo = scanner.nextLine();

        SystemOut.question("Inserisci un ID di un tuo documento");
        documentoId = scanner.nextLine();

        lastId = Util.getLastId(GlobalConst.getCsvFilenameUtenti(), DELIMITER);

        utente = lastId + DELIMITER + nome +DELIMITER + cognome + DELIMITER + data + DELIMITER + indirizzo + DELIMITER + documentoId;

        aggiungiUtente(utente);
    }

    public static void aggiungiUtente(String utente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(GlobalConst.getCsvFilenameUtenti(), true))) {
            if (Util.fileHasConent(GlobalConst.getCsvFilenameUtenti())) {
                writer.newLine();
            }
            writer.write(utente);
            SystemOut.success("Utente aggiunto correttamente.");
        } catch (IOException e) {
            SystemOut.error("Si è verificato un errore durante l'aggiunta dell' utente.");
        }
    }

}
