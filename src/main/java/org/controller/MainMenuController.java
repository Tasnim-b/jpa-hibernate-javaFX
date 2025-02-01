package org.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainMenuController {


    @FXML
    private BorderPane borderPane; // Référence à la BorderPane dans le fichier FXML

    // Méthode pour gérer l'ajout de client
    @FXML
    private void handleAddClient() {
        loadContent("AjoutClient.fxml"); // Charge l'interface d'ajout client
    }

    @FXML
    private void handleModifyClient() {
        loadContent("ModifierClient.fxml"); // Charge l'interface de modification client
    }

    @FXML
    private void handleAddReservation() {
        loadContent("Reservation.fxml");
    }

    @FXML
    private void handleModifyReservation() {
        loadContent("ModifierReservation.fxml");
    }
    @FXML
    private void handleAjouterActivite() {
        loadContent("AjoutActivite.fxml");
    }
    @FXML
    private void handleModifierActivite() {
        loadContent("ModifierActivite.fxml");
    }
    @FXML
    private void handleAddVoyage() {
        loadContent("AjoutVoyage.fxml");
    }
    @FXML
    private void handleModifierVoyage() {
        loadContent("ModifierVoyage.fxml");
    }



    private void loadContent(String fxmlFile) {
        try {
            // Utilisez FXMLLoader pour charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/"+ fxmlFile));

            // Charge le contenu FXML dans un StackPane
           // BorderPane root = loader.load();
            // Charge le contenu FXML dans un Node
            Node content = loader.load();  // Utilisation de Node car cela peut être n'importe quel type de container

            // Ajoute le contenu dans la zone centrale de la BorderPane
            borderPane.setCenter(content);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur lors du chargement de l'interface.");
        }
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Action");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
