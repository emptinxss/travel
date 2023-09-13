package com.travel.constants;

import java.io.File;
import java.net.URISyntaxException;

public abstract class GlobalConst

{
    private static final String ENVIROMENT = "enviroment";

    public static final String OPEN_COLOR = "\u001B";
    public static final String CLOSE_COLOR = "\u001B[0m";

    public static final String COLOR_BLUE = "[34m";
    public static final String COLOR_RED = "[31m";
    public static final String COLOR_GREEN = "[32m";
    public static final String COLOR_YELLOW = "[33m";

    public static final String DELIMITER =";";
    public static final char DELIMITER_CHAR =';';

    private static final String USER_DIR = System.getProperty("user.dir");

    private static String CSV_FILENAME_VIAGGI;
    private static String CSV_FILENAME_PRENOTAZIONI;
    private static String CSV_FILENAME_UTENTI;

    private static final String USER_HOME_PATH = System.getProperty("user.home");
    public static final String USER_DOWNLOADS_PATH = USER_HOME_PATH + File.separator + "Downloads";

    public static final String ID = "ID";
    public static final String DATA = "Data";
    public static final String DURATA = "Durata (ore)";
    public static final String PARTENZA = "Partenza";
    public static final String ARRIVO = "Arrivo";
    public static final String DISPONIBILE = "Disponibile";
    public static final String ID_VIAGGIO ="ID Viaggio";
    public static final String ID_UTENTE = "ID Utente";
    public static final String NOME = "Nome";
    public static final String COGNOME = "Cognome";
    public static final String DATA_DI_NASCITA = "Data di nascita";
    public static final String INDIRIZZO = "Indirizzo";
    public static final String DOCUMENTO_ID = "Documento ID";

    public static final String HEADER_VIAGGI = ID + DELIMITER + DATA + DELIMITER + DURATA + DELIMITER + PARTENZA + DELIMITER + ARRIVO + DELIMITER + DISPONIBILE;
    public static final String HEADER_PRENOTAZIONI = ID + DELIMITER + ID_VIAGGIO + DELIMITER + ID_UTENTE;
    public static final String HEADER_UTENTI = ID + DELIMITER + NOME + DELIMITER + COGNOME + DELIMITER + DATA_DI_NASCITA + DELIMITER + INDIRIZZO + DELIMITER + DOCUMENTO_ID;

    public static final String BOOKING = "prenotare";
    public static final String CANCELLATION = "disdire";

    public static final String AVAIABLE = "SI";
    public static final String NOT_AVAIABLE = "NO";

    public static final String EXIT = "Chiusura del prgramma in corso...";

    public static void setPaths(){
        String jarPath;

        try {
            jarPath = GlobalConst.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        File jarFile = new File(jarPath);
        String jarDirectory = jarFile.getParent();

        CSV_FILENAME_VIAGGI = jarDirectory + "\\data\\viaggi.csv";


        if(ENVIROMENT.equals("development")){
            CSV_FILENAME_VIAGGI = USER_DIR + "\\src\\main\\data\\viaggi.csv";
            CSV_FILENAME_PRENOTAZIONI = USER_DIR + "\\src\\main\\data\\prenotazioni.csv" ;
            CSV_FILENAME_UTENTI = USER_DIR + "\\src\\main\\data\\utenti.csv";
        } else if (ENVIROMENT.equals("production")){
            CSV_FILENAME_VIAGGI = jarDirectory + "\\data\\viaggi.csv";
            CSV_FILENAME_PRENOTAZIONI = jarDirectory + "\\data\\prenotazioni.csv" ;
            CSV_FILENAME_UTENTI = jarDirectory + "\\data\\utenti.csv";
        }
    }

    public static String getCsvFilenameViaggi() {
        return CSV_FILENAME_VIAGGI;
    }

    public static String getCsvFilenamePrenotazioni() {
        return CSV_FILENAME_PRENOTAZIONI;
    }

    public static String getCsvFilenameUtenti() {
        return CSV_FILENAME_UTENTI;
    }

}
