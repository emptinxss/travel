package org.travel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVUtenti {


    private static final String currentDir = System.getProperty("user.dir");
    static final String CSV_FILENAME = currentDir + "\\src\\main\\data\\utenti.csv";


    public static void getAllUsers() {
        String csvFilePath = CSV_FILENAME;
        List<Users> userList = new ArrayList<>();

        try (FileReader fileReader = new FileReader(csvFilePath);
             CSVParser csvParser = CSVFormat.DEFAULT.withDelimiter(';').withHeader("ID", "Nome", "Cognome", "Data di nascita", "Indirizzo", "Documento ID").parse(fileReader)) {
            // Salta la prima riga (intestazione delle colonne)
            csvParser.iterator().next();

            for (CSVRecord record : csvParser) {
                String id = record.get("ID");
                String firstName = record.get("Nome");
                String lastName = record.get("Cognome");
                String birthDate = record.get("Data di nascita");
                String address = record.get("Indirizzo");
                String documentId = record.get("Documento ID");

                // Creiamo un oggetto Users e aggiungiamolo alla lista userList
                Users user = new Users();
                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setBirthDate(birthDate);
                user.setAddress(address);
                user.setDocumentId(documentId);

                userList.add(user);
            }
        } catch (Exception e) {
            e.getMessage();
        }

        System.out.printf("%-2s | %-20s | %-20s | %-15s | %-25s | %s\n",
                "ID","Nome","Cognome","Data di nascita","Indirizzo","Documento ID");
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        for (Users user : userList) {
            System.out.printf("%-2s | %-20s | %-20s | %-15s | %-25s | %s\n",
                    user.getId(), user.getFirstName(), user.getLastName(), user.getBirthDate(), user.getAddress(), user.getDocumentId());
        }

    }
}