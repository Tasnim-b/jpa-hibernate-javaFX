package org.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.dao.GenericDAO;
import org.model.Voyage;

import java.time.LocalDate;
import java.util.List;

public class ModifierVoyageController {

    @FXML
    private ComboBox<Voyage> voyageComboBox; // Liste déroulante pour sélectionner le voyage
    @FXML
    private TextField nomVoyageField;
    @FXML
    private TextField prixField;
    @FXML
    private DatePicker dateDepartField;
    @FXML
    private DatePicker dateArriveeField;

    private GenericDAO<Voyage> voyageDAO = new GenericDAO<>(Voyage.class);

    @FXML
    public void initialize() {
        loadVoyages(); // Charger les voyages au démarrage

        // Personnaliser l'affichage de la ComboBox pour afficher le nom du voyage
        voyageComboBox.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Voyage voyage, boolean empty) {
                super.updateItem(voyage, empty);
                setText((empty || voyage == null) ? null : voyage.getNomVoyage());
            }
        });

        voyageComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Voyage voyage, boolean empty) {
                super.updateItem(voyage, empty);
                setText((empty || voyage == null) ? null : voyage.getNomVoyage());
            }
        });
    }


    private void loadVoyages() {
        List<Voyage> voyages = voyageDAO.getAll(); // Récupérer les voyages de la base
        voyageComboBox.getItems().addAll(voyages);
    }

    @FXML
    private void handleSelectionVoyage() {
        Voyage selectedVoyage = voyageComboBox.getValue();
        if (selectedVoyage != null) {
            // Remplir les champs avec les données du voyage sélectionné
            nomVoyageField.setText(selectedVoyage.getNomVoyage());
            prixField.setText(String.valueOf(selectedVoyage.getPrix_par_personne()));
            dateDepartField.setValue(selectedVoyage.getDate_depart());
            dateArriveeField.setValue(selectedVoyage.getDate_arrive());
        }
    }

    @FXML
    private void handleModifierVoyage() {
        Voyage selectedVoyage = voyageComboBox.getValue();
        if (selectedVoyage != null) {
            // Demander confirmation avant modification
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Modifier le Voyage");
            confirmationAlert.setContentText("Êtes-vous sûr de vouloir modifier ce voyage ?");

            if (confirmationAlert.showAndWait().get() == ButtonType.OK) {
                try {
                    selectedVoyage.setNomVoyage(nomVoyageField.getText());
                    selectedVoyage.setPrix_par_personne(Double.parseDouble(prixField.getText()));
                    selectedVoyage.setDate_depart(dateDepartField.getValue());
                    selectedVoyage.setDate_arrive(dateArriveeField.getValue());

                    voyageDAO.update(selectedVoyage); // Mettre à jour dans la base

                    showAlert("Voyage modifié avec succès !");
                } catch (NumberFormatException e) {
                    showAlert("Erreur : Le prix doit être un nombre valide !");
                }
            }
        } else {
            showAlert("Veuillez sélectionner un voyage !");
        }
    }
    @FXML
    private void handleAnnuler() {
        voyageComboBox.getSelectionModel().clearSelection();
        nomVoyageField.clear();
        prixField.clear();
        dateDepartField.setValue(null);
        dateArriveeField.setValue(null);
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
