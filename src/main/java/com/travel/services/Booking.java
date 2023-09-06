package com.travel.services;

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

public class Booking {



    public static void bookTravel(String userId){
        Travels travels= new Travels();
        Scanner scanner = new Scanner(System.in);
        String travelId;

        SystemOut.question("Inserisci l'ID del viaggio che desideri prenotare: ");
        travelId = inputNumberValida(scanner,"l'ID del viaggio");

        Travels travelsAvailable =  travels.getTravelsById(travelId);

        if (travelsAvailable.getId() != null && travelsAvailable.getAvailable().equals("SI")) {

            String travelID = travelsAvailable.getId();
            List<Reservations> allResevervation = Reservations.getAll();

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
        editViaggi(Travels.getAll(), travelId);
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
