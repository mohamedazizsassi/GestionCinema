package models;

import models.Client;
import models.Paiement;

import java.util.HashMap;
import java.util.Map;

public class PaiementManager {
    private Map<Integer, Paiement> paiements;

    public PaiementManager() {
        this.paiements = new HashMap<>();
    }

    // Add a new payment
    public void addPaiement(Paiement paiement) {
        if (!paiements.containsKey(paiement.getIdPaiement())) {
            paiements.put(paiement.getIdPaiement(), paiement);
        } else {
            throw new IllegalArgumentException("Paiement with ID " + paiement.getIdPaiement() + " already exists.");
        }
    }

    // Retrieve a payment by ID
    public Paiement getPaiement(int idPaiement) {
        return paiements.get(idPaiement);
    }

    // Remove a payment by ID
    public void removePaiement(int idPaiement) {
        if (paiements.containsKey(idPaiement)) {
            paiements.remove(idPaiement);
        } else {
            throw new IllegalArgumentException("Paiement with ID " + idPaiement + " does not exist.");
        }
    }

    // List all payments
    public void listPaiements() {
        paiements.values().forEach(System.out::println);
    }

    // Get total payments for a specific client
    public double getTotalPaiementsByClient(Client client) {
        return paiements.values().stream()
                .filter(p -> p.getClient().getIdClient() == client.getIdClient())
                .mapToDouble(Paiement::getMontant)
                .sum();
    }
}
