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

public class Booking {

    public static void bookTravel(String userId){
        Travels travels= new Travels();
        Scanner scanner = new Scanner(System.in);
        String travelId = null;
        boolean travelExist = false;

        SystemOut.question("Inserisci l'ID del viaggio che desideri prenotare: ");

        while (!travelExist) {
            travelId = Validation.inputNumberValida(scanner);
            travelExist = Util.checkTravelExist(travelId);

            if (!travelExist) {
                SystemOut.warning("Il viggio non esiste. Provare con un altro ID.");
            }
        }

        Travels travelsAvailable =  travels.getTravelsById(travelId);

        if (travelsAvailable.getId() != null && travelsAvailable.getAvailable().equals(GlobalConst.AVAIABLE)) {

            String travelID = travelsAvailable.getId();
            List<Reservations> allResevervation = Reservations.getAll();

            addReservation(travelID,allResevervation , userId);
        } else {
            SystemOut.error("Il viaggio selezionato non è disponibile.");
        }
    }


    private static void addReservation(String travelId, List<Reservations> reservList, String userId){

        try (FileWriter fileWriter = new FileWriter(getCsvFilenamePrenotazioni());
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT
                     .withHeader(HEADER_PRENOTAZIONI))) {

            for (Reservations reserv : reservList) {
                csvPrinter.printRecord(reserv.getId() + DELIMITER + reserv.getIdTravel() + DELIMITER + reserv.getIdUser());

            }
            csvPrinter.flush();

            int newId = Util.getLastId(GlobalConst.getCsvFilenamePrenotazioni(),DELIMITER);

            csvPrinter.printRecord(newId+ DELIMITER +travelId+ DELIMITER +userId);
        } catch (IOException e) {
            SystemOut.error("Impossibile aggiungere la prenotazione.");
        }
        setTravelNotAvailable(Travels.getAll(), travelId);
        SystemOut.success("Prenotazione avvenuta con successo.");
    }

    private static void setTravelNotAvailable(List<Travels> filteredTravelsList, String travelId) {
        try (FileWriter fileWriter = new FileWriter(getCsvFilenameViaggi());
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(HEADER_VIAGGI))) {

            for (Travels travel : filteredTravelsList) {
                if(travel.getId().equals(travelId) && travel.getAvailable().equals(GlobalConst.AVAIABLE)){
                    travel.setAvailable(GlobalConst.NOT_AVAIABLE);
                }
                csvPrinter.printRecord(travel.getId() + DELIMITER + travel.getDate() + DELIMITER +travel.getDuration() + DELIMITER +travel.getDeparture() + DELIMITER +travel.getArrival() + DELIMITER +travel.getAvailable());
            }

            csvPrinter.flush();
        } catch (IOException e) {
            SystemOut.error("Errore: impossibile modificare il file dei viaggi.");
        }
    }

}
