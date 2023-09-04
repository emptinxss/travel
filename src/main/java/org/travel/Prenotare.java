package org.travel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Prenotare {
//    private static Reservations reservation= new Reservations();
    private static Travels travels= new Travels();
    private static Scanner scanner = new Scanner(System.in);

    public static void prenota(){

        int travelId;
        System.out.println("Inserisci l'ID del viaggio che desideri prenotare:");
        travelId = scanner.nextInt();

        Travels travelsAviable =  travels.getTravelsById(String.valueOf(travelId));
//        Reservations targetReservation = reservation.getReservationById(String.valueOf(reservID));
        if (travelsAviable.getId() != null && travelsAviable.getAvailable().equals("SI")) {
            System.out.println("ID viaggio: " + travelsAviable.getId());
            addRow(travelsAviable.getId(), CSVPrenotazioni.getAllReservations(), "82");
        } else {
            System.out.println("Nessun viaggio trovato con l'ID specificato.");
        }
    }

    private static final String currentDir = System.getProperty("user.dir");
    static final String CSV_FILENAME = currentDir + "\\src\\main\\data\\viaggi.csv";
    static final String CSV_FILENAME_PRENOTAZIONI = currentDir + "\\src\\main\\data\\prenotazioni.csv";


    private static void addRow(String travelId, List<Reservations> reservList, String userId){
        // Sovrascrivi il file originale con i dati modificati
        try (FileWriter fileWriter = new FileWriter(CSV_FILENAME_PRENOTAZIONI);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader("ID", "ID Viaggio", "ID Utente"))) {

            int newId = reservList.isEmpty() ? 1 : Integer.parseInt(reservList.get(reservList.size() - 1).getId()) + 1;

            for (Reservations reserv : reservList) {
                csvPrinter.printRecord(reserv.getId() + ";" + reserv.getIdTravel() + ";" + reserv.getIdUser());
            }
            //AGGIUNNGI UNA RIGA CON IL TRAVEL ID E USER ID

            csvPrinter.printRecord(newId, travelId, userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        editViaggi(CSVViaggi.getAllTravels(), travelId);
    }

    private static void editViaggi(List<Travels> filteredTravelsList, String travelId) {
        try (FileWriter fileWriter = new FileWriter(CSV_FILENAME);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader("ID", "Data", "Durata (ore)", "Partenza", "Arrivo", "Disponibile"))) {

            for (Travels travel : filteredTravelsList) {
                if(travel.getId().equals(travelId) && travel.getAvailable().equals("SI")){
                    travel.setAvailable("NO");
                }
                csvPrinter.printRecord(travel.getId() + ";" + travel.getDate() + ";" +travel.getDuration() + ";" +travel.getDeparture() + ";" +travel.getArrival() + ";" +travel.getAvailable());
            }

            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
