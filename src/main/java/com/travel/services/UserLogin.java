package com.travel;

import com.travel.model.Users;
import com.travel.utils.SystemOut;

import java.util.Scanner;

public class UserLogin {
    private static String userId;
    public static void getIdUser(int type, String message) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);
        userId = inputNumberValida(scanner, "l'ID:");

        boolean exist = checkExist(userId);

        if(exist) {
            if(type == 2) {
                Prenotare.prenota(userId);
            }else {
                Disdire.cancelReservation(userId);
            }
        } else {
            getIdUser(type, message);
        }
    }
    private static boolean checkExist(String userId){
        Users users = new Users();

        Users user = users.getUserById(userId);

        if(user != null){
            return true;
        } else {
            System.out.println("L'utente non esiste. Provare con un'altro ID");
        }
        return false;
    }
    private static String inputNumberValida(Scanner scanner, String message) {
        String input = scanner.nextLine();
        if (isNumeroInterPositivo(input)) {
            return input;
        } else {
            SystemOut.warning("Inserimento non valido. Inserisci di nuovo " + message );
            return inputNumberValida(scanner, message);
        }
    }

    private static boolean isNumeroInterPositivo(String id) {
        return id.matches("\\d+");
    }

}
