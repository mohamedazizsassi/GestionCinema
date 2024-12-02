package models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Seance {
    private int idSeance;
    private Movie movie;
    private LocalTime horaire;
    private LocalDate date;
    private String salle;
    private int totalSeats;
    private int availableSeats;
    private Set<Integer> reservedSeats;

    public Seance(int idSeance, Movie movie, LocalTime horaire, LocalDate date, String salle, int totalSeats) {
        this.idSeance = idSeance;
        this.movie = movie;
        this.horaire = horaire;
        this.date = date;
        this.salle = salle;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.reservedSeats = new HashSet<>();
    }

    // Getters and Setters
    public int getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalTime getHoraire() {
        return horaire;
    }

    public void setHoraire(LocalTime horaire) {
        this.horaire = horaire;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Set<Integer> getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(Set<Integer> reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public void reserveSeat(int seatNumber) {
        if (!reservedSeats.contains(seatNumber) && availableSeats > 0) {
            reservedSeats.add(seatNumber);
            availableSeats--;
        } else {
            throw new IllegalArgumentException("Seat is already reserved or unavailable.");
        }
    }

    public void cancelSeatReservation(int seatNumber) {
        if (reservedSeats.remove(seatNumber)) {
            availableSeats++;
        }
    }
}
