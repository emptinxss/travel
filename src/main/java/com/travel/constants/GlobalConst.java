package com.travel.constants;

import java.io.File;

public class GlobalConst

{
    public static final String OPEN_COLOR = "\u001B";
    public static final String CLOSE_COLOR = "\u001B[0m";

    public static final String COLOR_BLUE = "[34m";
    public static final String COLOR_RED = "[31m";
    public static final String COLOR_GREEN = "[32m";
    public static final String COLOR_YELLOW = "[33m";

    private static final String USER_DIR_PATH = System.getProperty("user.dir");
    private static final String CSV_PATH = USER_DIR_PATH + "\\src\\main\\java\\com\\travel\\resources\\";
    public static final String DELIMITER =";";

    private static final String USER_HOME_PATH = System.getProperty("user.home");
    public static final String USER_DOWNLOADS_PATH = USER_HOME_PATH + File.separator + "Downloads";

    public static final String CSV_FILENAME_VIAGGI = CSV_PATH + "viaggi.csv";
    public static final String CSV_FILENAME_PRENOTAZIONI = CSV_PATH + "prenotazioni.csv";
    public static final String CSV_FILENAME_UTENTI = CSV_PATH + "utenti.csv";

    public static final int BOOKING_TRAVEL_TYPE = 2;
    public static final int CANCELLATION_TRAVEL_TYPE = 3;

    public static final String AVAIABLE = "SI";
    public static final String NOT_AVAIABLE = "NO";

    public static final String ARRIVECERDI = "Alla prossima\uD83D\uDC4B";
}
