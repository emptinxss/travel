package com.travel.model;


import com.travel.utils.SystemOut;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static com.travel.constants.GlobalConst.CSV_FILENAME_UTENTI;

public class Users {

    private String id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String address;
    private String documentId;

    // SETTER
    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    // GETTERS
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public String getDocumentId() {
        return documentId;
    }

    //FUNCTION
    public Users getUserById(String userId) {
        List<Users> userList = getAll();
        for (Users users : userList) {
            if (users.getId().equals(userId)) {
                return users;
            }
        }
        return null;
    }

    public static List<Users> getAll() {
        List<Users> userList = new ArrayList<>();

        try (FileReader fileReader = new FileReader(CSV_FILENAME_UTENTI);
             CSVParser csvParser = CSVFormat.DEFAULT
                     .withDelimiter(';')
                     .withHeader("ID", "Nome", "Cognome", "Data di nascita", "Indirizzo", "Documento ID").parse(fileReader)) {

            csvParser.iterator().next();

            for (CSVRecord record : csvParser) {
                Users user = new Users();

                user.setId(record.get("ID"));
                user.setFirstName(record.get("Nome"));
                user.setLastName(record.get("Cognome"));
                user.setBirthDate(record.get("Data di nascita"));
                user.setAddress(record.get("Indirizzo"));
                user.setDocumentId(record.get("Documento ID"));

                userList.add(user);
            }
        } catch (Exception e) {
            SystemOut.error("Errore nella leettura degli utenti.");
        }

        return userList;
    }

    public static void printAll(){
        List<Users> userList = getAll();

        System.out.printf("%-2s | %-20s | %-20s | %-15s | %-25s | %s\n",
                "ID","Nome","Cognome","Data di nascita","Indirizzo","Documento ID");
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        for (Users user : userList) {
            System.out.printf("%-2s | %-20s | %-20s | %-15s | %-25s | %s\n",
                    user.getId(), user.getFirstName(), user.getLastName(), user.getBirthDate(), user.getAddress(), user.getDocumentId());
        }
    }

}
