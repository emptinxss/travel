package com.travel.utils;

import com.travel.constants.GlobalConst;

public class SystemOut {

    private static void printMessage(String color, String message){
        System.out.println(GlobalConst.OPEN_COLOR + color+ message + GlobalConst.CLOSE_COLOR);
    }
    public static void question(String message){
        printMessage(GlobalConst.COLOR_BLUE,message);
    }
    public static void error(String message){
        printMessage(GlobalConst.COLOR_RED,message);
    }
    public static void success(String message){
        printMessage(GlobalConst.COLOR_GREEN,message);
    }
    public static void warning(String message){
        printMessage(GlobalConst.COLOR_YELLOW,message);
    }
}
