package models;

public class Reservation {
    private int idReservation;
    private Client client;
    private Seance seance;
    private int reservedSeats; // Total seats reserved in this reservation

    public Reservation(int idReservation, Client client, Seance seance, int reservedSeats) {
        this.idReservation = idReservation;
        this.client = client;
        this.seance = seance;
        this.reservedSeats = reservedSeats;
        for (int i = 1; i <= reservedSeats; i++) {
            seance.reserveSeat(i); // Reserve seats in the seance
        }
    }

    public int getIdReservation() {
        return idReservation;
    }

    public Client getClient() {
        return client;
    }

    public Seance getSeance() {
        return seance;
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", client=" + client +
                ", seance=" + seance +
                ", reservedSeats=" + reservedSeats +
                '}';
    }
}
