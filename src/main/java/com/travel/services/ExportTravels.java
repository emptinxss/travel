package com.travel.services;

import com.travel.constants.GlobalConst;

import com.travel.model.Travels;
import com.travel.utils.SystemOut;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.travel.constants.GlobalConst.DELIMITER;

public class ExportTravels {

    public static void getFilterList(){
        List<Travels> filteredList = getFilteredTravels();
        LocalDate oggi = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dataFormattata = oggi.format(formatter);

        String outputFilePath = "viaggi-"+dataFormattata+".csv";

        createCSV(filteredList, outputFilePath);
    }

    private static List<Travels> getFilteredTravels() {
        List<Travels> filteredList = new ArrayList<>();

        List<Travels> allTravels = Travels.getAll();
        for (Travels travel : allTravels){
            String available = travel.getAvailable();

            if(available.equals(GlobalConst.AVAIABLE)){
                Travels travels = new Travels();
                travels.setId(travel.getId());
                travels.setDate(travel.getDate());
                travels.setDuration(travel.getDuration());
                travels.setDeparture(travel.getDeparture());
                travels.setArrival(travel.getArrival());
                travels.setAvailable(available);

                filteredList.add(travels);
            }
        }
        return filteredList;
    }

    private static void createCSV(List<Travels> filteredTravelsList, String outputFilePath) {
        try (FileWriter fileWriter = new FileWriter(outputFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader("ID"+ DELIMITER +"Data"+ DELIMITER + "Durata (ore)"+ DELIMITER + "Partenza"+ DELIMITER + "Arrivo"+ DELIMITER + "Disponibile"))) {

            for (Travels travel : filteredTravelsList) {
                csvPrinter.printRecord(travel.getId() + DELIMITER + travel.getDate() + DELIMITER + travel.getDuration()
                        + DELIMITER + travel.getDeparture() + DELIMITER + travel.getArrival() + DELIMITER + travel.getAvailable());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            SystemOut.error("Errore: Impossibile creare il file CSV.");
        }

        downloadCSV(outputFilePath);
    }

    private static void downloadCSV(String sourceFilePath) {
        File sourceFile = new File(sourceFilePath);
        File destinationFolder = new File(GlobalConst.USER_DOWNLOADS_PATH);

        try {
            if (!destinationFolder.exists()) {
                destinationFolder.mkdirs();
            }

            Path sourcePath = sourceFile.toPath();
            Path destinationPath = new File(destinationFolder, sourceFile.getName()).toPath();

            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

            SystemOut.success("File scaricato con successo in: " + destinationPath);
        } catch (IOException e) {
            SystemOut.error("Errore: Impossibile scaricare il file CSV.");
        }
    }
}
