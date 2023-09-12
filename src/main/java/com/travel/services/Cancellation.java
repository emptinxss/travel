package com.travel.services;

import com.travel.constants.GlobalConst;
import com.travel.model.Reservations;
import com.travel.model.Travels;
import com.travel.utils.SystemOut;
import com.travel.utils.Util;
import com.travel.utils.Validation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static com.travel.constants.GlobalConst.*;

public class Cancellation {

    public static void cancelReservation(String userId){

        Scanner scanner = new Scanner(System.in);

        String travelId = null;
        boolean travelExist = false;
        boolean reservationExist = false;

        while (!reservationExist) {
            reservationExist = Util.checkReservationExist(userId);

            if (!reservationExist) {
                SystemOut.warning("L'utente non ha effettuato nessuna prenotazione. Provare con un altro ID.");
                userId = Validation.inputNumberValida(scanner);
            }
        }

        SystemOut.question("Inserisci l'ID del viaggio che desideri disdire:");

        while (!travelExist) {
            travelId = Validation.inputNumberValida(scanner);
            travelExist = Util.checkTravelExist(travelId);

            if (!travelExist) {
                SystemOut.warning("Il viggio non esiste. Provare con un altro ID.");
            }
        }

        List<Reservations> allResevervation = Reservations.getAll();

        boolean cancellazioneEffettuata = false;

        for (Reservations reserv : allResevervation) {
            String idUser = reserv.getIdUser();
            String idTravel = reserv.getIdTravel();

            if (idUser.equals(userId) && idTravel.equals(travelId)) {
                deleteReservation(travelId, allResevervation, userId);
                cancellazioneEffettuata = true;
                break;
            }
        }

        if (!cancellazioneEffettuata) {
            SystemOut.warning("Nessuna prenotazione trovata per l'utente e il viaggio specificato.");
        }

    }

    private static void deleteReservation(String travelId, List<Reservations> reservList, String userId){

        try (FileWriter fileWriter = new FileWriter(GlobalConst.getCsvFilenamePrenotazioni());
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(HEADER_PRENOTAZIONI))) {

            for (Reservations reserv : reservList) {
                if(!reserv.getIdTravel().equals(travelId) && reserv.getIdUser().equals(userId)) {
                    csvPrinter.printRecord(reserv.getId() + DELIMITER + reserv.getIdTravel() + DELIMITER + reserv.getIdUser());
                }
            }
            SystemOut.success("Prenotazione disdetta.");
        } catch (IOException e) {
            SystemOut.error("Errore: Impossibile cancellare la prenotazione.");
        }

        setTravelAvaiable(Travels.getAll(), travelId);
    }

    private static void setTravelAvaiable(List<Travels> filteredTravelsList, String travelId) {
        try (FileWriter fileWriter = new FileWriter(GlobalConst.getCsvFilenameViaggi());
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(HEADER_VIAGGI))) {

            for (Travels travel : filteredTravelsList) {
                if(travel.getId().equals(travelId) && travel.getAvailable().equals(GlobalConst.NOT_AVAIABLE)){
                    travel.setAvailable(GlobalConst.AVAIABLE);
                }
                csvPrinter.printRecord(travel.getId() + DELIMITER + travel.getDate() + DELIMITER +travel.getDuration() + DELIMITER +travel.getDeparture() + DELIMITER +travel.getArrival() + DELIMITER +travel.getAvailable());
            }

            csvPrinter.flush();

            SystemOut.success("Il viaggio è ora disponibile.");
        } catch (IOException e) {
            SystemOut.error("Errore: Impossibile modificare il viaggio.");
        }
    }

}
