package org.travel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.util.List;

public class CSVPrenotazioni {


    private static final String currentDir = System.getProperty("user.dir");
    static final String CSV_FILENAME = currentDir + "\\src\\main\\data\\prenotazioni.csv";
    private static Reservations reservationList = new Reservations();

    public static List<Reservations> getAllReservations() {
        String csvFilePath = CSV_FILENAME;

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

                reservationList.addReservation(reservations);
            }
        } catch (Exception e) {
            e.getMessage();
        }

        System.out.printf("%-2s | %-10s | %-10s \n",
                "ID","ID Viaggio","ID Utente");
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        List<Reservations> resList = reservationList.getReservationList();

        for (Reservations reservations : resList) {
            System.out.printf("%-2s | %-10s | %-10s \n",
                    reservations.getId(), reservations.getIdTravel(), reservations.getIdUser());
        }

        return resList;
    }


    // Metodo per ottenere la lista delle prenotazioni
    public static List<Reservations> getReservationsList() {
        return reservationList.getReservationList();
    }

}