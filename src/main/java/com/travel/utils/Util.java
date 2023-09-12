package com.travel.utils;

import com.travel.model.Reservations;
import com.travel.model.Travels;
import com.travel.model.Users;

import java.io.*;
import java.util.Scanner;

public class Util {
    public static boolean checkTravelExist(String travelId) {
        Travels travels= new Travels();
        Travels travel = travels.getTravelsById(travelId);

        if(travel != null){
            return true;
        }

        return false;
    }
    public static boolean checkReservationExist(String userId) {

        Reservations reservations = new Reservations();
        Reservations reserv = reservations.getReservationByUserId(userId);

        if(reserv != null){
            return true;
        }

        return false;
    }

    public static boolean checkUserExist(String userId){
        Users users = new Users();
        Users user = users.getUserById(userId);

        if(user != null){
            return true;
        }

        return false;
    }

    public static int getLastId(String filePath, String delimiter) {
        int ultimoID = 1;

        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] colonne = linea.split(delimiter);
                if (colonne.length > 0) {
                    int id = Integer.parseInt(colonne[0]);
                    if (id >= ultimoID) {
                        ultimoID = id +  1;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File non trovato: " + e.getMessage());
        }

        return ultimoID;
    }

    public static boolean fileHasConent(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.readLine() != null;
        } catch (IOException e) {
            return false;
        }
    }

    public static void waitInput(Scanner scanner){
        System.out.println("                             ");
        System.out.println("Premi invio per continuare...");

        scanner.nextLine();
    }

}
