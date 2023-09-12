package com.travel;

import com.travel.constants.GlobalConst;
import com.travel.controllers.MainMenu;

public class App
{
    public static void main(String[] args) {
        System.out.println("                                                                   ");
        System.out.println("\u001b[1m                  WELCOME TO POOLING AROUND!                    \u001b[0m" );

        GlobalConst.setPaths();

        MainMenu.start();
    }
}