package org.travel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVViaggi {


    private static final String currentDir = System.getProperty("user.dir");
    static final String CSV_FILENAME = currentDir + "\\src\\main\\data\\viaggi.csv";


    public static List<Travels> getAllTravels() {
        String csvFilePath = CSV_FILENAME;
        List<Travels> travelsList = new ArrayList<>();

        try (FileReader fileReader = new FileReader(csvFilePath);
             CSVParser csvParser = CSVFormat.DEFAULT.withDelimiter(';').withHeader("ID", "Data", "Durata (ore)", "Partenza", "Arrivo", "Disponibile").parse(fileReader)) {

            // Salta la prima riga (intestazione delle colonne)
            csvParser.iterator().next();

            for (CSVRecord record : csvParser) {
                String id = record.get("ID");
                String date = record.get("Data");
                String duration = record.get("Durata (ore)");
                String departure = record.get("Partenza");
                String arrival = record.get("Arrivo");
                String available = record.get("Disponibile");

                // Creiamo un oggetto Users e aggiungiamolo alla lista userList
                Travels travels = new Travels();
                travels.setId(id);
                travels.setDate(date);
                travels.setDuration(duration);
                travels.setDeparture(departure);
                travels.setArrival(arrival);
                travels.setAvailable(available);

                travelsList.add(travels);
            }
        } catch (Exception e) {
            e.getMessage();
        }

        System.out.printf("%-2s | %-20s | %-20s | %-15s | %-25s | %s\n",
                "ID","Data","Durata (ore)","Partenza","Arrivo","Disponibile");
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        for (Travels travel : travelsList) {
            System.out.printf("%-2s | %-20s | %-20s | %-15s | %-25s | %s\n",
                    travel.getId(), travel.getDate(), travel.getDuration(), travel.getDeparture(), travel.getArrival(), travel.getAvailable());
        }

        return travelsList;
    }
}