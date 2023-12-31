package com.travel.model;

import com.travel.utils.SystemOut;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.travel.constants.GlobalConst.*;

public class Reservations {

    private String id;
    private String idTravel;
    private String idUser;

    // SETTER
    public void setId(String id) {
        this.id = id;
    }

    public void setIdTravel(String idTravel) {
        this.idTravel = idTravel;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }


    // GETTERS
    public String getId() {
        return id;
    }

    public String getIdTravel() {
        return idTravel;
    }

    public String getIdUser() {
        return idUser;
    }

    //FUNCTIONS
    public Reservations getReservationByUserId(String userId) {
        List<Reservations> reservationsList = getAll();
        for (Reservations reservation : reservationsList) {
            if (reservation.getIdUser().equals(userId)) {
                return reservation;
            }
        }
        return null;
    }

    public static List<Reservations> getAll() {

        List<Reservations> reservationList = new ArrayList<>();

        try (InputStream inputStream = new FileInputStream(getCsvFilenamePrenotazioni());
             InputStreamReader reader = new InputStreamReader(inputStream);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withDelimiter(DELIMITER_CHAR)
                     .withHeader(ID,ID_VIAGGIO,ID_UTENTE))) {

            csvParser.iterator().next();

            for (CSVRecord record : csvParser) {
                Reservations reservations = new Reservations();

                reservations.setId(record.get("ID"));
                reservations.setIdTravel(record.get("ID Viaggio"));
                reservations.setIdUser(record.get("ID Utente"));

                reservationList.add(reservations);
            }
        } catch (Exception e) {
            SystemOut.error("Errore nella lettura delle prenotazioni.");
        }

        return reservationList;
    }

    public static void printAll(){
        List<Reservations> reservationList = getAll();

        if(!reservationList.isEmpty()) {
            System.out.println("                                                                 ");
            System.out.printf("%-2s | %-10s | %-10s \n", "ID", "ID Viaggio", "ID Utente");
            System.out.println("----------------------------------------------------------------");

            for (Reservations reservations : reservationList) {
                System.out.printf("%-2s | %-10s | %-10s \n",
                        reservations.getId(), reservations.getIdTravel(), reservations.getIdUser());
            }
        }
    }

}
