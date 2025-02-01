package org.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.model.Activite;
import org.model.Voyage;
import org.dao.GenericDAO;

import java.util.List;

public class AjoutActiviteController {

    @FXML
    private TextField nomActiviteField;
    @FXML
    private TextField prixActiviteField;
    @FXML
    private TextField descriptionField;
    @FXML
    private ComboBox<Voyage> voyageComboBox;

    private GenericDAO<Activite> activiteDAO = new GenericDAO<>(Activite.class);
    private GenericDAO<Voyage> voyageDAO = new GenericDAO<>(Voyage.class);

    public void initialize() {
        // Charger tous les voyages dans le ComboBox
        List<Voyage> voyages = voyageDAO.getAll();
        voyageComboBox.getItems().addAll(voyages);
    }

    @FXML
    private void handleAjouterActivite() {
        // Vérifier que les champs sont remplis
        if (nomActiviteField.getText().isEmpty() || prixActiviteField.getText().isEmpty() || descriptionField.getText().isEmpty()) {
            showAlert("Tous les champs doivent être remplis.");
            return;
        }

        // Créer une nouvelle instance d'Activite
        Activite activite = new Activite();
        activite.setNom_activite(nomActiviteField.getText());
        activite.setPrix_activite(Double.parseDouble(prixActiviteField.getText()));
        activite.setDescription(descriptionField.getText());

        // Ajouter le voyage sélectionné à l'activité
        Voyage selectedVoyage = voyageComboBox.getValue();
        if (selectedVoyage != null) {
            activite.getVoyages().add(selectedVoyage);
        }

        // Ajouter l'activité à la base de données
        activiteDAO.add(activite);

        // Afficher une alerte de succès
        showAlert("Activité ajoutée avec succès!");
    }

    @FXML
    private void handleAnnuler() {
        // Réinitialiser les champs
        nomActiviteField.clear();
        prixActiviteField.clear();
        descriptionField.clear();
        voyageComboBox.getSelectionModel().clearSelection();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
