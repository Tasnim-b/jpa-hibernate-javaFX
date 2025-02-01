package org.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.model.Voyage;
import org.dao.GenericDAO;

import java.time.LocalDate;

public class AjoutVoyageController {

    @FXML
    private TextField nomVoyageField;  // Champ pour le nom du voyage
    @FXML
    private TextField prixField;  // Champ pour le prix par personne
    @FXML
    private DatePicker dateDepartField;  // Champ pour la date de départ
    @FXML
    private DatePicker dateArriveeField;  // Champ pour la date d'arrivée

    private GenericDAO<Voyage> voyageDAO = new GenericDAO<>(Voyage.class);

    // Cette méthode est appelée lors du clic sur le bouton Ajouter
    @FXML
    private void handleAddVoyage() {
        try {
            // Récupérer les données des champs du formulaire
            String nomVoyage = nomVoyageField.getText();
            double prix = Double.parseDouble(prixField.getText());
            LocalDate dateDepart = dateDepartField.getValue();
            LocalDate dateArrivee = dateArriveeField.getValue();

            // Vérifier que tous les champs sont remplis
            if (nomVoyage.isEmpty() || dateDepart == null || dateArrivee == null) {
                showAlert("Tous les champs doivent être remplis correctement !");
                return;
            }

            // Créer un objet Voyage
            Voyage voyage = new Voyage();
            voyage.setNomVoyage(nomVoyage);
            voyage.setPrix_par_personne(prix);
            voyage.setDate_depart(dateDepart);
            voyage.setDate_arrive(dateArrivee);

            // Ajouter le voyage à la base de données via le DAO
            voyageDAO.add(voyage);

            // Afficher un message de succès
            showAlert("Voyage ajouté avec succès !");

        } catch (NumberFormatException e) {
            showAlert("Le prix doit être un nombre valide.");
        }
    }

    // Méthode pour annuler l'ajout et réinitialiser les champs
    @FXML
    private void handleAnnuler() {
        nomVoyageField.clear();
        prixField.clear();
        dateDepartField.setValue(null);
        dateArriveeField.setValue(null);
    }

    // Afficher une alerte pour informer l'utilisateur
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
