package org.travel;


import java.util.ArrayList;
import java.util.List;

public class Reservations {

    private List<Reservations> reservationsList;
    private String id;
    private String idTravel;
    private String idUser;


    public Reservations() {
        reservationsList = new ArrayList<>();
    }

    // SETTER
    public void setId(String id) {
        this.id = id;
    }

    public void setIdTravel(String idTravel) {
        this.idTravel = idTravel;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }


    // GETTERS
    public String getId() {
        return id;
    }

    public String getIdTravel() {
        return idTravel;
    }

    public String getIdUser() {
        return idUser;
    }

    public List<Reservations> getReservationList() {
        return reservationsList;
    }

    //FUNCTION
    public void addReservation(Reservations reservation) {
        reservationsList.add(reservation);
    }

    public Reservations getReservationById(String targetId) {
        List<Reservations> reservationsList = CSVPrenotazioni.getAllReservations();
        for (Reservations reservation : reservationsList) {
            if (reservation.getId().equals(targetId)) {
                return reservation;
            }
        }
        System.out.println("ID non valido.");
        return null;
    }

}
