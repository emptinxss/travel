package com.travel.services;

import com.travel.constants.GlobalConst;
import com.travel.utils.SystemOut;
import com.travel.utils.Util;
import com.travel.utils.Validation;

import java.io.*;
import java.util.Scanner;

import static com.travel.constants.GlobalConst.DELIMITER;

public class AddTravel {


    public static void inserisciDatiViaggio() {
        Scanner scanner = new Scanner(System.in);

        String data;
        String durata;
        String partenza;
        String arrivo;
        String disponibile = GlobalConst.AVAIABLE;
        String viaggio;
        int lastId;


        SystemOut.question("Inserisci la data del viaggio nel formato dd/MM/yyyy:");
        data = Validation.inputDataValida(scanner);

        SystemOut.question("Inserisci la durata del viaggio in ore:");
        durata = Validation.inputNumberValida(scanner);

        SystemOut.question("Inserisci la città di partenza:");
        partenza = Validation.inputStringaValida(scanner);

        SystemOut.question("Inserisci la città di arrivo:");
        arrivo = Validation.inputStringaValida(scanner);

        lastId = Util.getLastId(GlobalConst.getCsvFilenameViaggi(), DELIMITER);

        viaggio = lastId  + DELIMITER + data + DELIMITER + durata + DELIMITER + partenza + DELIMITER + arrivo + DELIMITER + disponibile;

        addUser(viaggio);
    }

    private static void addUser(String viaggio) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(GlobalConst.getCsvFilenameViaggi(), true))) {
            if (Util.fileHasConent(GlobalConst.getCsvFilenameViaggi())) {
                writer.newLine();
            }
            writer.write(viaggio);
            SystemOut.success("Viaggio aggiunto correttamente.");
        } catch (IOException e) {
            SystemOut.error("Si è verificato un errore durante l'aggiunta del viaggio.");
        }
    }

}
