package com.travel.services;

import com.travel.constants.GlobalConst;
import com.travel.model.Users;
import com.travel.utils.SystemOut;
import com.travel.utils.Validation;

import java.util.Scanner;


public class UserLogin {

    public static void getIdUser(int type, String message) {

        Scanner scanner = new Scanner(System.in);
        String userId = null;

        boolean userExists = false;

        SystemOut.question(message);

        while (!userExists) {
            userId = Validation.inputNumberValida(scanner);
            userExists = checkUserExist(userId);

            if (!userExists) {
                SystemOut.warning("L'utente non esiste. Provare con un altro ID.");
            }
        }

        if (type == GlobalConst.BOOKING_TRAVEL_TYPE) {
            Booking.bookTravel(userId);
        } else {
            Cancellation.cancelReservation(userId);
        }
    }
    private static boolean checkUserExist(String userId){
        Users users = new Users();
        Users user = users.getUserById(userId);

        if(user != null){
            return true;
        }

        return false;
    }
}
