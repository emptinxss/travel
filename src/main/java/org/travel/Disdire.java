package org.travel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Disdire {
    private static Reservations reservation= new Reservations();
    private static Scanner scanner = new Scanner(System.in);

    public static void cancelReservation(){

        int reservID;
        System.out.println("Inserisci l'ID della prenotazione che desideri disdire:");
        reservID = scanner.nextInt();

        Reservations targetReservation = reservation.getReservationById(String.valueOf(reservID));
        if (targetReservation.getIdTravel() != null) {
            System.out.println("ID viaggio: " + targetReservation.getIdTravel());
            deleteRow(targetReservation.getIdTravel(), CSVPrenotazioni.getReservationsList());
        } else {
            System.out.println("Nessuna prenotazione trovata con l'ID specificato.");
        }
    }

    private static final String currentDir = System.getProperty("user.dir");
    static final String CSV_FILENAME = currentDir + "\\src\\main\\data\\viaggi.csv";
    static final String CSV_FILENAME_PRENOTAZIONI = currentDir + "\\src\\main\\data\\prenotazioni.csv";


    private static void deleteRow(String travelId, List<Reservations> reservList){
        // Sovrascrivi il file originale con i dati modificati
        try (FileWriter fileWriter = new FileWriter(CSV_FILENAME_PRENOTAZIONI);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader("ID", "ID Viaggio", "ID Utente"))) {

            for (Reservations reserv : reservList) {
                if(!reserv.getIdTravel().equals(travelId)) {
                    csvPrinter.printRecord(reserv.getId() + ";" + reserv.getIdTravel() + ";" + reserv.getIdUser());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        editViaggi(CSVViaggi.getAllTravels(), travelId);
    }

    private static void editViaggi(List<Travels> filteredTravelsList, String travelId) {
        try (FileWriter fileWriter = new FileWriter(CSV_FILENAME);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader("ID", "Data", "Durata (ore)", "Partenza", "Arrivo", "Disponibile"))) {

            for (Travels travel : filteredTravelsList) {
                if(travel.getId().equals(travelId) && travel.getAvailable().equals("NO")){
                    travel.setAvailable("SI");
                }
                csvPrinter.printRecord(travel.getId() + ";" + travel.getDate() + ";" +travel.getDuration() + ";" +travel.getDeparture() + ";" +travel.getArrival() + ";" +travel.getAvailable());
            }

            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
