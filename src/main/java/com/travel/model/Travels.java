package com.travel.model;

import com.travel.utils.SystemOut;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.travel.constants.GlobalConst.*;

public class Travels {

    private String id;
    private String date;
    private String duration;
    private String departure;
    private String arrival;
    private String available;

    // SETTER
    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    // GETTERS
    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public String getAvailable() {
        return available;
    }

    //FUNCTION
    public Travels getTravelsById(String targetId) {
        List<Travels> travelsList = getAll();
        for (Travels travels : travelsList) {
            if (travels.getId().equals(targetId)) {
                return travels;
            }
        }
        return null;
    }

    public static List<Travels> getAll() {
        List<Travels> travelsList = new ArrayList<>();

        try (InputStream inputStream = new FileInputStream(getCsvFilenameViaggi());
             InputStreamReader reader = new InputStreamReader(inputStream);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withDelimiter(DELIMITER_CHAR)
                     .withHeader(ID,DATA,DURATA,PARTENZA,ARRIVO,DISPONIBILE))) {

            csvParser.iterator().next();

            for (CSVRecord record : csvParser) {

                Travels travels = new Travels();

                travels.setId(record.get("ID"));
                travels.setDate(record.get("Data"));
                travels.setDuration(record.get("Durata (ore)"));
                travels.setDeparture(record.get("Partenza"));
                travels.setArrival(record.get("Arrivo"));
                travels.setAvailable(record.get("Disponibile"));

                travelsList.add(travels);
            }
        } catch (Exception e) {
            SystemOut.error("Errore nella letture dei viaggi.");
        }

        return travelsList;
    }

    public static void printAll(){
        List<Travels> travelsList = getAll();

        if(!travelsList.isEmpty()) {
            System.out.println("                                                                                                                ");
            System.out.printf("%-2s | %-20s | %-20s | %-15s | %-25s | %s\n", "ID", "Data", "Durata (ore)", "Partenza", "Arrivo", "Disponibile");
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            for (Travels travel : travelsList) {
                System.out.printf("%-2s | %-20s | %-20s | %-15s | %-25s | %s\n",
                        travel.getId(), travel.getDate(), travel.getDuration(), travel.getDeparture(), travel.getArrival(), travel.getAvailable());
            }
        }
    }
}
