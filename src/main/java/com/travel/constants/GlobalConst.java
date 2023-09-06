package com.travel;

public class GlobalConst

{
    public static final String OPEN_COLOR = "\u001B";
    public static final String CLOSE_COLOR = "\u001B[0m";

    public static final String COLOR_BLUE = "[34m";
    public static final String COLOR_RED = "[31m";
    public static final String COLOR_GREEN = "[32m";
    public static final String COLOR_YELLOW = "[33m";

    public static final String USER_DIR = System.getProperty("user.dir");
    private static final String CSV_PATH = USER_DIR + "\\src\\main\\data\\";

    public static final String CSV_FILENAME_VIAGGI = CSV_PATH + "viaggi.csv";
    public static final String CSV_FILENAME_PRENOTAZIONI = CSV_PATH + "prenotazioni.csv";
    public static final String CSV_FILENAME_UTENTI = CSV_PATH + "utenti.csv";
}
