package com.travel;

import com.travel.utils.SystemOut;

import java.util.Scanner;

public class MainMenu {

    public static void start(){

        Scanner scanner = new Scanner(System.in);
        int number = 0;

        do {
            System.out.println("\u001B[32m#############################################################\u001B[0m");
            System.out.printf("%-10s | %-50s \n", "Comando","Descrizione");
            System.out.println("-------------------------------------------------------------");
            System.out.printf("%-10s | %-50s \n", "1","Visualizzare i viaggio all'interno del sistema");
            System.out.printf("%-10s | %-50s \n", "2","Prenotare un viaggio esistente");
            System.out.printf("%-10s | %-50s \n", "3","Disdire la prenotazione di un viaggio");
            System.out.printf("%-10s | %-50s \n", "4","Aggiungere un nuovo utente");
            System.out.printf("%-10s | %-50s \n", "5","Esportare un file con i viaggi disponibili");
            System.out.printf("%-10s | %-50s \n", "6","Visualizzare Liste");
            System.out.printf("%-10s | %-50s \n", "0","Uscire dal programma");
            System.out.println("-------------------------------------------------------------");

             number = checkCommand(scanner, number);


        } while (number != 0);

        scanner.close();

    }

    private static int checkCommand(Scanner scanner, int number){

        if (scanner.hasNextInt()) {
            number = scanner.nextInt();
            switch (number) {
                case 0:
                    System.out.println("Alla prossima\uD83D\uDC4B ");
                    break;
                case 1:
                    CSVViaggi.printTravels();
                    break;
                case 2:
                    UserLogin.getIdUser(2, "Inserisci l' ID dell'utente con cui vuoi fare la prenotazione");
                    break;
                case 3:
                    UserLogin.getIdUser(3,"Inserisci l' ID dell'utente con cui vuoi disdire la prenotazione");
                    break;
                case 4:
                    AddUser.inserisciDatiUtente();
                    break;
                case 5:
                    ExportTravels.getFilteredTravelsList();
                    break;
                case 6:
                    Liste.getList(scanner);
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
