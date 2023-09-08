package com.travel.controllers;

import com.travel.model.Reservations;
import com.travel.model.Users;
import com.travel.utils.SystemOut;
import com.travel.utils.Util;
import com.travel.utils.Validation;

import java.util.Scanner;

public class ListMenu {

    public static void getList(Scanner scanner){

        int number;

        do {
            System.out.println("                                                                 ");
            System.out.println("-------------------------- LIST MENU ---------------------------");
            System.out.printf("%-10s | %-50s \n", "Comando","Descrizione");
            System.out.println("----------------------------------------------------------------");
            System.out.printf("%-10s | %-50s \n", "1","Visualizzare gli utenti all'interno del sistema");
            System.out.printf("%-10s | %-50s \n", "2","Visualizzare le prenotazioni all'interno del sistema");
            System.out.printf("%-10s | %-50s \n", "0","Torna indietro");
            System.out.println("----------------------------------------------------------------");

            number = checkCommandList(scanner);

        } while (number != 0);

    }

    private static int checkCommandList(Scanner scanner){

        int number = Integer.parseInt(Validation.inputNumberValida(scanner));

            switch (number) {
                case 0:
                    break;
                case 1:
                    Users.printAll();
                    Util.waitInput(scanner);
                    break;
                case 2:
                    Reservations.printAll();
                    Util.waitInput(scanner);
                    break;
                default:
                    SystemOut.error("Comando non esistente.");
                    Util.waitInput(scanner);
                    break;
            }

        return number;
    }
}
