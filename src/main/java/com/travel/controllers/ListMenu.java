package com.travel;

import com.travel.utils.SystemOut;

import java.util.Scanner;

public class Liste {

    public static void getList(Scanner scanner){
//        Scanner scanner = new Scanner(System.in);
        int number = 0;

        do {
            System.out.println("#############################################################");
            System.out.printf("%-10s | %-50s \n", "Comando","Descrizione");
            System.out.println("-------------------------------------------------------------");
            System.out.printf("%-10s | %-50s \n", "1","Visualizzare gli utenti all'interno del sistema");
            System.out.printf("%-10s | %-50s \n", "2","Visualizzare le prenotazioni all'interno del sistema");
            System.out.printf("%-10s | %-50s \n", "0","Torna indietro");
            System.out.println("#############################################################");

            number = checkCommandList(scanner, number);

        } while (number != 0);

//        scanner.close();
    }

    private static int checkCommandList(Scanner scanner, int number){
        if (scanner.hasNextInt()) {
            number = scanner.nextInt();

            switch (number) {
                case 0:
                    break;
                case 1:
                    CSVUtenti.printUsers();
                    break;
                case 2:
                    CSVPrenotazioni.printReservations();
                    break;
            }
        }  else {
            if(scanner.hasNextLine()) {
                scanner.nextLine();
            }
            SystemOut.warning("Input non valido. Devi inserire un comando valido.");
            checkCommandList(scanner, number);
        }
        return number;
    }
}