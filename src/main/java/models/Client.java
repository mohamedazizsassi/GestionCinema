package models;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private int idClient;
    private String name;
    private String email;
    private List<Reservation> reservations; // Using List for ordered tracking of reservations

    public Client(int idClient, String name, String email) {
        this.idClient = idClient;
        this.name = name;
        this.email = email;
        this.reservations = new ArrayList<>();
    }

    public int getIdClient() {
        return idClient;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", reservations=" + reservations +
                '}';
    }
}
