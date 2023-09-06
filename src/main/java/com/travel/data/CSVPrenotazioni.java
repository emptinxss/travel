package com.travel;

import com.travel.model.Reservations;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static com.travel.constants.GlobalConst.CSV_FILENAME_PRENOTAZIONI;

public class CSVPrenotazioni {


//    private static final String currentDir = System.getProperty("user.dir");
//    static final String CSV_FILENAME = currentDir + "\\src\\main\\data\\prenotazioni.csv";
//    private static Reservations reservationList = new Reservations();

    public static List<Reservations> getAllReservations() {
        String csvFilePath = CSV_FILENAME_PRENOTAZIONI;

        List<Reservations> reservationList = new ArrayList<>();

        try (FileReader fileReader = new FileReader(csvFilePath);
             CSVParser csvParser = CSVFormat.DEFAULT.withDelimiter(';').withHeader("ID", "ID Viaggio", "ID Utente").parse(fileReader)) {

            csvParser.iterator().next();

            for (CSVRecord record : csvParser) {
                String id = record.get("ID");
                String idTravel = record.get("ID Viaggio");
                String idUser = record.get("ID Utente");

                Reservations reservations = new Reservations();
                reservations.setId(id);
                reservations.setIdTravel(idTravel);
                reservations.setIdUser(idUser);


                reservationList.add(reservations);
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return reservationList;
    }

    public static void printReservations(){
        List<Reservations> reservationList = getAllReservations();

        System.out.printf("%-2s | %-10s | %-10s \n",
                "ID","ID Viaggio","ID Utente");
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        for (Reservations reservations : reservationList) {
            System.out.printf("%-2s | %-10s | %-10s \n",
                    reservations.getId(), reservations.getIdTravel(), reservations.getIdUser());
        }
    }

}