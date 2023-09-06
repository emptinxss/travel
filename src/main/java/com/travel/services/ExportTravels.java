package com.travel;

import com.travel.model.Travels;
import com.travel.utils.SystemOut;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExportTravels {
    private static final String currentDir = System.getProperty("user.dir");
    static final String CSV_FILENAME = currentDir + "\\src\\main\\data\\viaggi.csv";

    public static void getFilteredTravelsList() {

        List<Travels> travelsList = new ArrayList<>();

        try (FileReader fileReader = new FileReader(CSV_FILENAME);
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

                // Verifica se la colonna "Disponibile" è uguale a "SI"
                if (available.equals("SI")) {
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
            }
        } catch (Exception e) {
            e.getMessage();
        }

        LocalDate oggi = LocalDate.now();

        // Formatta la data in una stringa se necessario
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Puoi personalizzare il formato
        String dataFormattata = oggi.format(formatter);

        String outputFilePath = "viaggi-"+dataFormattata+".csv"; // Sostituisci con il percorso desiderato per il file di output

        createCSV(travelsList, outputFilePath);
    }

    private static void createCSV(List<Travels> filteredTravelsList, String outputFilePath) {
        try (FileWriter fileWriter = new FileWriter(outputFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader("ID", "Data", "Durata (ore)", "Partenza", "Arrivo", "Disponibile"))) {

            for (Travels travel : filteredTravelsList) {
                csvPrinter.printRecord(travel.getId(), travel.getDate(), travel.getDuration(), travel.getDeparture(), travel.getArrival(), travel.getAvailable());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            SystemOut.error("Errore: Impossibile creare il file CSV.");
        }

        downloadCSV(outputFilePath);
    }

    private static void downloadCSV(String sourceFilePath) {
        String userHomeFolder = System.getProperty("user.home"); // Ottiene la cartella home dell'utente
        String downloadsFolder = userHomeFolder + File.separator + "Downloads"; // Componi il percorso completo della cartella "Downloads"
        File sourceFile = new File(sourceFilePath);
        File destinationFolder = new File(downloadsFolder);

        try {
            if (!destinationFolder.exists()) {
                destinationFolder.mkdirs();
            }

            Path sourcePath = sourceFile.toPath();
            Path destinationPath = new File(destinationFolder, sourceFile.getName()).toPath();

            Files.copy(sourcePath, destinationPath, StandardCopyOption.COPY_ATTRIBUTES);

            SystemOut.success("File scaricato con successo in: " + destinationPath);
        } catch (IOException e) {
            SystemOut.error("Errore: Impossibile scaricare il file CSV.");
        }
    }
}
