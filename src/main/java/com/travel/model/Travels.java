package com.travel;

import java.util.List;

public class Travels {

    private String id;
    private String date;
    private String duration;
    private String departure;
    private String arrival;
    private String available;

    // SETTER
    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    // GETTERS
    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public String getAvailable() {
        return available;
    }

//FUNCTION

    public Travels getTravelsById(String targetId) {
        List<Travels> travelsList = CSVViaggi.getAllTravels();
        for (Travels travels : travelsList) {
            if (travels.getId().equals(targetId)) {
                return travels;
            }
        }
        SystemOut.error("ID non valido.");
        return null;
    }
}
