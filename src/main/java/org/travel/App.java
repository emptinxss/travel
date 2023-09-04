package org.travel;

import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        System.out.println( "WELCOME TO TO THE TRIPIFY!" );

//        CSVPrenotazioni.getAllReservations();
//        CSVViaggi.getAllTravels();
//        CSVUtenti.getAllUsers();

        Scanner scanner = new Scanner(System.in);
        int number = 0;

        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-10s | %-50s \n", "Comando","Descrizione");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-10s | %-50s \n", "1","Visualizzare i viaggi all'interno del sistema");
        System.out.printf("%-10s | %-50s \n", "2","Prenotare un viaggio esistente");
        System.out.printf("%-10s | %-50s \n", "3","Disdire la prenotazione di un viaggio");
        System.out.printf("%-10s | %-50s \n", "4","Aggiungere un nuovo utente");
        System.out.printf("%-10s | %-50s \n", "5","Esportare un file con i viaggi disponibili");
        System.out.printf("%-10s | %-50s \n", "0","Uscire dal programma");
        System.out.println("-------------------------------------------------------------");

         number = scanner.nextInt();

        if(number == 0){
            scanner.close();
            System.out.println("CIAO");
        }else if(number == 11){
            CSVUtenti.getAllUsers();
        }else if(number == 1){
            CSVViaggi.getAllTravels();
        } else if(number == 12){
            CSVPrenotazioni.getAllReservations();
        } else if(number == 3){
            Disdire.cancelReservation();
        } else if(number == 5){
            ExportTravels.getFilteredTravelsList();
        } else if(number == 2){
            Prenotare.prenota();
        }
    }

}
