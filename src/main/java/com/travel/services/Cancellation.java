package com.travel.services;

import com.travel.data.CSVReservations;
import com.travel.data.CSVTravels;
import com.travel.constants.GlobalConst;
import com.travel.model.Reservations;
import com.travel.model.Travels;
import com.travel.utils.SystemOut;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Disdire {
//    private static Reservations reservation= new Reservations();
    private static Travels travels= new Travels();
    private static Scanner scanner = new Scanner(System.in);


    public static void cancelReservation(String userId){

        int travelId;
        SystemOut.question("Inserisci l'ID del viaggio che desideri disdire:");
        //METTI VALIDAZIONE
        travelId = scanner.nextInt();

        Travels travelsAviable =  travels.getTravelsById(String.valueOf(travelId));

        if (travelsAviable.getId() != null && travelsAviable.getAvailable().equals("NO")) {

            String travelID = travelsAviable.getId();
            List<Reservations> allResevervation = CSVReservations.getAllReservations();

            deleteRow(travelID ,allResevervation, userId);
        } else {
            SystemOut.error("Nessun viaggio prenotato trovato con l'ID specificato.");
        }
    }
//CHECK PRENOTAZIONE BY ID???
    private static void deleteRow(String travelId, List<Reservations> reservList, String userId){
        // Sovrascrivi il file originale con i dati modificati
        try (FileWriter fileWriter = new FileWriter(GlobalConst.CSV_FILENAME_PRENOTAZIONI);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader("ID" + ";" +  "ID Viaggio" + ";" +  "ID Utente"))) {

            for (Reservations reserv : reservList) {
                if(reserv.getIdTravel().equals(travelId) && reserv.getIdUser().equals(userId)) {
                } else {
                    csvPrinter.printRecord(reserv.getId() + ";" + reserv.getIdTravel() + ";" + reserv.getIdUser());
                }
            }
        } catch (IOException e) {
            SystemOut.error("Errore: Impossibile modificare la prenotazione.");
        }
        editViaggi(CSVTravels.getAllTravels(), travelId);
    }

    private static void editViaggi(List<Travels> filteredTravelsList, String travelId) {
        try (FileWriter fileWriter = new FileWriter(GlobalConst.CSV_FILENAME_VIAGGI);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader("ID"+ ";" + "Data"+ ";" + "Durata (ore)"+ ";" + "Partenza"+ ";" +"Arrivo"+ ";" + "Disponibile"))) {

            for (Travels travel : filteredTravelsList) {
                if(travel.getId().equals(travelId) && travel.getAvailable().equals("NO")){
                    travel.setAvailable("SI");
                }
                csvPrinter.printRecord(travel.getId() + ";" + travel.getDate() + ";" +travel.getDuration() + ";" +travel.getDeparture() + ";" +travel.getArrival() + ";" +travel.getAvailable());
            }

            csvPrinter.flush();
        } catch (IOException e) {
            SystemOut.error("Errore: Impossibile modificare il viaggio.");
        }
    }
}
