package com.travel.services;

import com.travel.constants.GlobalConst;
import com.travel.model.Reservations;
import com.travel.model.Travels;
import com.travel.utils.SystemOut;
import com.travel.utils.Validation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static com.travel.constants.GlobalConst.DELIMITER;

public class Cancellation {

    private static Travels travels= new Travels();

    public static void cancelReservation(String userId){

        Scanner scanner = new Scanner(System.in);

        String travelId = null;
        boolean travelExist = false;
        boolean reservationExist = false;


        while (!reservationExist) {
            reservationExist = checkReservationExist(userId);

            if (!reservationExist) {
                SystemOut.warning("L'utente non ha effettuato nessuna prenotazione. Provare con un altro ID.");
                userId = Validation.inputNumberValida(scanner);
            }
        }

        SystemOut.question("Inserisci l'ID del viaggio che desideri disdire:");

        while (!travelExist) {
            travelId = Validation.inputNumberValida(scanner);
            travelExist = checkTravelExist(travelId);

            if (!travelExist) {
                SystemOut.warning("Il viggio non esiste. Provare con un altro ID.");
            }
        }

        Travels travelsAviable =  travels.getTravelsById(travelId);

        if (travelsAviable.getId() != null && travelsAviable.getAvailable().equals(GlobalConst.NOT_AVAIABLE)) {

            String travelID = travelsAviable.getId();
            List<Reservations> allResevervation = Reservations.getAll();

            deleteRow(travelID ,allResevervation, userId);
        } else {
            SystemOut.error("Nessun viaggio prenotato trovato con l'ID specificato.");
        }

    }

    private static void deleteRow(String travelId, List<Reservations> reservList, String userId){

        try (FileWriter fileWriter = new FileWriter(GlobalConst.CSV_FILENAME_PRENOTAZIONI);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader("ID" + DELIMITER +  "ID Viaggio" + DELIMITER +  "ID Utente"))) {

            for (Reservations reserv : reservList) {
                if(!reserv.getIdTravel().equals(travelId) && !reserv.getIdUser().equals(userId)) {
                    csvPrinter.printRecord(reserv.getId() + DELIMITER + reserv.getIdTravel() + DELIMITER + reserv.getIdUser());

                }
            }
            SystemOut.success("Prenotazione disdetta.");
        } catch (IOException e) {
            SystemOut.error("Errore: Impossibile cancellare la prenotazione.");
        }

        editViaggi(Travels.getAll(), travelId);
    }

    private static void editViaggi(List<Travels> filteredTravelsList, String travelId) {
        try (FileWriter fileWriter = new FileWriter(GlobalConst.CSV_FILENAME_VIAGGI);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader("ID"+ DELIMITER + "Data"+ DELIMITER + "Durata (ore)"+ DELIMITER + "Partenza"+ DELIMITER +"Arrivo"+ DELIMITER + "Disponibile"))) {

            for (Travels travel : filteredTravelsList) {
                if(travel.getId().equals(travelId) && travel.getAvailable().equals(GlobalConst.NOT_AVAIABLE)){
                    travel.setAvailable(GlobalConst.AVAIABLE);
                }
                csvPrinter.printRecord(travel.getId() + ";" + travel.getDate() + ";" +travel.getDuration() + ";" +travel.getDeparture() + ";" +travel.getArrival() + ";" +travel.getAvailable());
            }

            csvPrinter.flush();

            SystemOut.success("Il viaggio è ora disponibile.");
        } catch (IOException e) {
            SystemOut.error("Errore: Impossibile modificare il viaggio.");
        }
    }

    private static boolean checkTravelExist(String travelId) {

        Travels travel = travels.getTravelsById(travelId);

        if(travel != null){
            return true;
        }

        return false;
    }
    private static boolean checkReservationExist(String userId) {

        Reservations reservations = new Reservations();
        Reservations reserv = reservations.getReservationByUserId(userId);

        if(reserv != null){
            return true;
        }

        return false;
    }
}
