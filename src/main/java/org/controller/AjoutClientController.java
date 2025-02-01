package org.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.dao.GenericDAO;
import org.model.Client;

public class AjoutClientController {



    @FXML
    private TextField nameField; // fx:id="nameField"

    @FXML
    private TextField PrenonField; // fx:id="PrenonField"

    @FXML
    private TextField emailField; // fx:id="emailField"

    @FXML
    private TextField phoneField; // fx:id="phoneField"

    @FXML
    private TextField AdressField; // fx:id="AdressField"

    @FXML
    private Button submitButton;   // fx:id="submitButton"

    private GenericDAO<Client> clientDAO;

    @FXML
    private void initialize() {
        // Initialisation du DAO avec la classe Client
        clientDAO = new GenericDAO<>(Client.class);

        // Action lorsque le bouton Ajouter est cliqué
        submitButton.setOnAction(event -> handleAddClient());
    }

    private void handleAddClient() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String prenom = PrenonField.getText();
        String adress = AdressField.getText();

        // Vérifier si les champs obligatoires sont remplis
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            showAlert("Erreur", "Tous les champs doivent être remplis", Alert.AlertType.ERROR);
        } else {
            // Créer un nouvel objet Client
            Client newClient = new Client(name, prenom, email, phone, adress);

            // Ajouter le client à la base de données via le DAO
            clientDAO.add(newClient);

            // Afficher un message de succès
            showAlert("Succès", "Client ajouté avec succès", Alert.AlertType.INFORMATION);
            closeWindow();
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeWindow() {
        // Implémentez la fermeture de la fenêtre si nécessaire
        // Exemple : ((Stage) submitButton.getScene().getWindow()).close();
    }
}
