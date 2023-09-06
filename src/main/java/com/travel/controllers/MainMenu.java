package com.travel.controllers;

import com.travel.constants.GlobalConst;

import com.travel.model.Travels;
import com.travel.services.AddTravel;
import com.travel.services.AddUser;
import com.travel.services.ExportTravels;
import com.travel.services.UserLogin;
import com.travel.utils.SystemOut;

import java.util.Scanner;

public class MainMenu {

    public static void start(){

        Scanner scanner = new Scanner(System.in);
        int number = 0;

        do {
            System.out.println("------------------------ MAIN MENU -----------------------------");
            System.out.printf("%-10s | %-50s \n", "Comando","Descrizione");
            System.out.println("----------------------------------------------------------------");
            System.out.printf("%-10s | %-50s \n", "1","Visualizzare i viaggio all'interno del sistema");
            System.out.printf("%-10s | %-50s \n", "2","Prenotare un viaggio esistente");
            System.out.printf("%-10s | %-50s \n", "3","Disdire la prenotazione di un viaggio");
            System.out.printf("%-10s | %-50s \n", "4","Aggiungere un nuovo utente");
            System.out.printf("%-10s | %-50s \n", "5","Aggiungere un nuovo viaggio");
            System.out.printf("%-10s | %-50s \n", "6","Esportare un file con i viaggi disponibili");
            System.out.printf("%-10s | %-50s \n", "7","Visualizzare Liste");
            System.out.printf("%-10s | %-50s \n", "0","Uscire dal programma");
            System.out.println("----------------------------------------------------------------");

             number = checkCommand(scanner, number);


        } while (number != 0);

        scanner.close();

    }

    private static int checkCommand(Scanner scanner, int number){

        if (scanner.hasNextInt()) {
            number = scanner.nextInt();
            switch (number) {
                case 0:
                    System.out.println(GlobalConst.ARRIVECERDI);
                    break;
                case 1:
                    Travels.printAll();
                    break;
                case 2:
                    UserLogin.getIdUser(GlobalConst.BOOKING_TRAVEL_TYPE, "Inserisci l'ID dell'utente con cui vuoi fare la prenotazione");
                    break;
                case 3:
                    UserLogin.getIdUser(GlobalConst.CANCELLATION_TRAVEL_TYPE,"Inserisci l'ID dell'utente con cui vuoi disdire la prenotazione");
                    break;
                case 4:
                    AddUser.inserisciDatiUtente();
                    break;
                case 5:
                    AddTravel.inserisciDatiViaggio();
                    break;
                case 6:
                    ExportTravels.getFilterList();
                    break;
                case 7:
                    ListMenu.getList(scanner);
                    break;
                default:
                    SystemOut.error("Comando non valido.");
                    break;
            }
        }
        else {
            if(scanner.hasNextLine()) {
                scanner.nextLine();
            }
           SystemOut.warning("Input non valido. Devi inserire un comando valido.");
//            return number;
            return checkCommand(scanner, number);
        }

        return number;
    }

    private static int nextIntWithRetry(Scanner scanner, String message) {
        while (true) {
            try {
                SystemOut.question(message);
                String input = scanner.next();
                int value = Integer.parseInt(input);
                return value;
            } catch (NumberFormatException e) {
                SystemOut.error("Input non valido. Devi inserire un numero intero.");
                scanner.nextLine(); // Consuma la nuova riga non valida nel buffer
            }
        }
    }
}
