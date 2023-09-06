package com.travel.services;

import com.travel.data.CSVReservations;
import com.travel.data.CSVTravels;
import com.travel.constants.GlobalConst;
import com.travel.model.Reservations;
import com.travel.model.Travels;
import com.travel.utils.SystemOut;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Prenotare {
//    private static Reservations reservation= new Reservations();
    private static Travels travels= new Travels();
    private static Scanner scanner = new Scanner(System.in);

    public static void prenota(String userId){

        String travelId;

        SystemOut.question("Inserisci l'ID del viaggio che desideri prenotare: ");
        travelId = inputNumberValida(scanner,"l'ID del viaggio");
//    System.out.println("\u001B[34mInserisci l'ID del viaggio che desideri prenotare:\u001B[0m");
//        travelId = scanner.nextInt();

        Travels travelsAviable =  travels.getTravelsById(String.valueOf(travelId));

        if (travelsAviable.getId() != null && travelsAviable.getAvailable().equals("SI")) {

            String travelID = travelsAviable.getId();
            List<Reservations> allResevervation = CSVReservations.getAllReservations();

            addRow(travelID,allResevervation , userId);
        } else {
            SystemOut.error("Nessun viaggio disponibile trovato con l'ID specificato.");
        }
    }

    private static void addRow(String travelId, List<Reservations> reservList, String userId){
        // Sovrascrivi il file originale con i dati modificati
        try (FileWriter fileWriter = new FileWriter(GlobalConst.CSV_FILENAME_PRENOTAZIONI);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT
                     .withHeader("ID"+ ";" +"ID Viaggio"+ ";" +"ID Utente"))) {

            for (Reservations reserv : reservList) {
                csvPrinter.printRecord(reserv.getId() + ";" + reserv.getIdTravel() + ";" + reserv.getIdUser());

            }
            csvPrinter.flush();

            int newId = leggiUltimoID(GlobalConst.CSV_FILENAME_PRENOTAZIONI,";");

            csvPrinter.printRecord(newId+ ";" +travelId+ ";" +userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        editViaggi(CSVTravels.getAllTravels(), travelId);
        SystemOut.success("Prenotazione avvenuta con successo.");
    }

    private static void editViaggi(List<Travels> filteredTravelsList, String travelId) {
        try (FileWriter fileWriter = new FileWriter(GlobalConst.CSV_FILENAME_VIAGGI);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT
                     .withHeader("ID"+ ";" +"Data"+ ";" +"Durata (ore)"+ ";" +"Partenza"+ ";" +"Arrivo"+ ";" +"Disponibile"))) {

            for (Travels travel : filteredTravelsList) {
                if(travel.getId().equals(travelId) && travel.getAvailable().equals("SI")){
                    travel.setAvailable("NO");
                }
                csvPrinter.printRecord(travel.getId() + ";" + travel.getDate() + ";" +travel.getDuration() + ";" +travel.getDeparture() + ";" +travel.getArrival() + ";" +travel.getAvailable());
            }

            csvPrinter.flush();
        } catch (IOException e) {
            SystemOut.error("Errore: impossibile modificare il file dei viaggi.");
//            e.getMessage();
        }
    }

    private static int leggiUltimoID(String filePath, String delimiter) {
        int ultimoID = 1;

        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.nextLine(); // Ignora la prima riga (intestazione)

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] colonne = linea.split(delimiter);
                if (colonne.length > 0) {
                    int id = Integer.parseInt(colonne[0]); // Assumendo che l'ID sia nella prima colonna
                    if (id >= ultimoID) {
                        ultimoID = id +  1;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File non trovato: " + e.getMessage());
        }

        return ultimoID;
    }

//    public static int nextIntWithRetry(Scanner scanner, String message) {
//        while (true) {
//            try {
////                System.out.print(message);
//                SystemOut.question(message);
//                String input = scanner.next();
//                int value = Integer.parseInt(input);
//                return value;
//            } catch (NumberFormatException e) {
//                SystemOut.error("Input non valido. Devi inserire un numero intero.");
////                System.out.println("Input non valido. Devi inserire un numero intero.");
//                scanner.nextLine(); // Consuma la nuova riga non valida nel buffer
//            }
//        }
//    }

    private static String inputNumberValida(Scanner scanner, String message) {
        String input = scanner.nextLine();
        if (isNumeroInterPositivo(input)) {
            return input;
        } else {
            SystemOut.error("Inserimento non valido. Inserisci correttamente " + message );
            return inputNumberValida(scanner, message);
        }
    }

    private static boolean isNumeroInterPositivo(String id) {
        return id.matches("\\d+");
    }

}
