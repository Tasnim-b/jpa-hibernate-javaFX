package org.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.model.Activite;
import org.model.Voyage;
import org.dao.GenericDAO;

import java.util.List;

public class ModifierActiviteController {

    @FXML
    private ComboBox<Activite> activiteComboBox;
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
        // Charger toutes les activités dans le ComboBox
        List<Activite> activites = activiteDAO.getAll();
        activiteComboBox.getItems().addAll(activites);

        // Charger tous les voyages dans le ComboBox pour les associer à l'activité
        List<Voyage> voyages = voyageDAO.getAll();
        voyageComboBox.getItems().addAll(voyages);

        // Quand une activité est sélectionnée, afficher ses détails dans les champs
        activiteComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nomActiviteField.setText(newValue.getNom_activite());
                prixActiviteField.setText(String.valueOf(newValue.getPrix_activite()));
                descriptionField.setText(newValue.getDescription());
                // Vous pouvez gérer la sélection des voyages associés ici si besoin
            }
        });
    }

    @FXML
    private void handleModifierActivite() {
        // Vérifier que tous les champs sont remplis
        if (nomActiviteField.getText().isEmpty() || prixActiviteField.getText().isEmpty() || descriptionField.getText().isEmpty()) {
            showAlert("Tous les champs doivent être remplis.");
            return;
        }

        // Vérifier si une activité est sélectionnée
        Activite activite = activiteComboBox.getValue();
        if (activite == null) {
            showAlert("Veuillez sélectionner une activité à modifier.");
            return;
        }

        // Mettre à jour l'activité avec les nouvelles données
        activite.setNom_activite(nomActiviteField.getText());
        activite.setPrix_activite(Double.parseDouble(prixActiviteField.getText()));
        activite.setDescription(descriptionField.getText());

        // Ajouter le voyage sélectionné à l'activité
        Voyage selectedVoyage = voyageComboBox.getValue();
        if (selectedVoyage != null && !activite.getVoyages().contains(selectedVoyage)) {
            activite.getVoyages().add(selectedVoyage);
        }

        // Mettre à jour l'activité dans la base de données
        activiteDAO.update(activite);

        // Afficher une alerte de succès
        showAlert("Activité modifiée avec succès!");
    }

    @FXML
    private void handleAnnuler() {
        // Réinitialiser les champs
        nomActiviteField.clear();
        prixActiviteField.clear();
        descriptionField.clear();
        activiteComboBox.getSelectionModel().clearSelection();
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
