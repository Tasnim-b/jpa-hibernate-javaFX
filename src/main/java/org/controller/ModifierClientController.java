package org.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import org.dao.GenericDAO;
import org.model.Client;

import java.util.List;

public class ModifierClientController {

    @FXML
    private ComboBox<Client> clientComboBox; // ComboBox pour sélectionner un client
    @FXML
    private TextField nameField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField adressField;

    private GenericDAO<Client> clientDAO = new GenericDAO<>(Client.class); // DAO pour les clients
    private Client clientActuel; // Le client actuellement sélectionné

    @FXML
    private void initialize() {
        // Charger tous les clients de la base de données et les ajouter au ComboBox
        List<Client> clients = clientDAO.getAll();
        clientComboBox.getItems().setAll(clients);

        // Écouter les changements de sélection dans le ComboBox
        clientComboBox.setOnAction(event -> handleClientSelection());
    }

    private void handleClientSelection() {
        clientActuel = clientComboBox.getSelectionModel().getSelectedItem();
        if (clientActuel != null) {
            // Remplir les champs avec les données du client sélectionné
            nameField.setText(clientActuel.getNom());
            prenomField.setText(clientActuel.getPrenom());
            emailField.setText(clientActuel.getEmail());
            phoneField.setText(clientActuel.getTelephone());
            adressField.setText(clientActuel.getAdresse());
        }
    }

    @FXML
    private void handleUpdateClient() {
        if (clientActuel != null) {
            // Mettre à jour les données du client avec les valeurs saisies
            clientActuel.setNom(nameField.getText());
            clientActuel.setPrenom(prenomField.getText());
            clientActuel.setEmail(emailField.getText());
            clientActuel.setTelephone(phoneField.getText());
            clientActuel.setAdresse(adressField.getText());

            // Mettre à jour le client dans la base de données
            clientDAO.update(clientActuel);

            showAlert("Client mis à jour avec succès !");
        }
    }

    @FXML
    private void handleDeleteClient() {
        if (clientActuel != null) {
            // Supprimer le client de la base de données
            clientDAO.delete(clientActuel.getId_Client());

            // Afficher un message de succès
            showAlert("Client supprimé avec succès !");

            // Réinitialiser le formulaire
            clientComboBox.getItems().remove(clientActuel);
            clearForm();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearForm() {
        nameField.clear();
        prenomField.clear();
        emailField.clear();
        phoneField.clear();
        adressField.clear();
    }
}
