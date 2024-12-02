package models;

import java.time.LocalDate;

public class Paiement {
    private int idPaiement;
    private Client client;
    private double montant;
    private LocalDate datePaiement;

    public Paiement(int idPaiement, Client client, double montant, LocalDate datePaiement) {
        this.idPaiement = idPaiement;
        this.client = client;
        this.montant = montant;
        this.datePaiement = datePaiement;
    }

    // Getters and Setters
    public int getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(int idPaiement) {
        this.idPaiement = idPaiement;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    @Override
    public String toString() {
        return "Paiement{" +
                "idPaiement=" + idPaiement +
                ", client=" + client.getName() +
                ", montant=" + montant +
                ", datePaiement=" + datePaiement +
                '}';
    }
}
