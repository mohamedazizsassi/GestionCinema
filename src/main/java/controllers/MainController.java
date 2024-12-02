package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MainController {
    @FXML
    private Label welcomeLabel;

    @FXML
    private Button actionButton;

    @FXML
    public void handleButtonClick(MouseEvent event) {
        welcomeLabel.setText("Bienvenue dans le Movie Dashboard !");
        System.out.println("Le bouton a été cliqué !");
    }

    // Méthode d'initialisation
    @FXML
    public void initialize() {
        System.out.println("MainController initialisé !");
    }
}
