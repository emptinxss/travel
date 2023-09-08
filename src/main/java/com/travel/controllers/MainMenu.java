package com.travel.controllers;

import com.travel.constants.GlobalConst;

import com.travel.model.Travels;
import com.travel.services.AddTravel;
import com.travel.services.AddUser;
import com.travel.services.ExportTravels;
import com.travel.services.UserLogin;
import com.travel.utils.SystemOut;
import com.travel.utils.Util;
import com.travel.utils.Validation;

import java.util.Scanner;

public class MainMenu {

    public static void start(){

        Scanner scanner = new Scanner(System.in);
        int number;

        do {
            System.out.println("                                                                ");
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

             number = checkCommand(scanner);


        } while (number != 0);

        scanner.close();

    }
    private static int checkCommand(Scanner scanner){

        int number = Integer.parseInt(Validation.inputNumberValida(scanner));

            switch (number) {
                case 0:
                    System.out.println(GlobalConst.EXIT);
                    break;
                case 1:
                    Travels.printAll();
                    Util.waitInput(scanner);
                    break;
                case 2:
                    UserLogin.getIdUser(GlobalConst.BOOKING);
                    Util.waitInput(scanner);
                    break;
                case 3:
                    UserLogin.getIdUser(GlobalConst.CANCELLATION);
                    Util.waitInput(scanner);
                    break;
                case 4:
                    AddUser.inserisciDatiUtente();
                    Util.waitInput(scanner);
                    break;
                case 5:
                    AddTravel.inserisciDatiViaggio();
                    Util.waitInput(scanner);
                    break;
                case 6:
                    ExportTravels.getFilterList();
                    Util.waitInput(scanner);
                    break;
                case 7:
                    ListMenu.getList(scanner);
                    break;
                default:
                    SystemOut.error("Comando non esistente.");
                    Util.waitInput(scanner);
                    break;
            }

        return number;
    }

}
