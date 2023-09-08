package com.travel.services;

import com.travel.constants.GlobalConst;
import com.travel.utils.SystemOut;
import com.travel.utils.Util;
import com.travel.utils.Validation;

import java.util.Scanner;


public class UserLogin {

    public static void getIdUser(String mode) {

        Scanner scanner = new Scanner(System.in);

        String userId = null;
        boolean userExists = false;

        SystemOut.question("Inserisci l'ID dell'utente con cui vuoi " + mode + " il viaggio:");

        while (!userExists) {
            userId = Validation.inputNumberValida(scanner);
            userExists = Util.checkUserExist(userId);

            if (!userExists) {
                SystemOut.warning("L'utente non esiste. Provare con un altro ID.");
            }
        }

        if (mode.equals(GlobalConst.BOOKING)) {
            Booking.bookTravel(userId);
        } else if (mode.equals(GlobalConst.CANCELLATION)) {
            Cancellation.cancelReservation(userId);
        }


    }

}
