package org.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.model.Client;
import org.model.Reservation;
import org.model.GroupeVoyageur;
import org.model.Voyage;

import java.time.LocalDate;

public class ReservationController {

    @FXML
    private TextField nbPlaceField;

    @FXML
    private TextField montantTotalField;

    @FXML
    private DatePicker dateDebutPicker;

    @FXML
    private DatePicker dateFinPicker;

    @FXML
    private ComboBox<GroupeVoyageur> groupeComboBox;

    @FXML
    private ComboBox<Voyage> voyageComboBox;

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    // Méthode d'initialisation appelée automatiquement après injection des éléments FXML
   /* @FXML
    private void initialize() {
        // Création d'un groupe de voyageurs avec uniquement l'ID et le nom.
        GroupeVoyageur groupeA = new GroupeVoyageur();
        groupeA.setId_groupe(1);
        groupeA.setNom_groupe("Groupe A");

        GroupeVoyageur groupeB = new GroupeVoyageur();
        groupeB.setId_groupe(2);
        groupeB.setNom_groupe("Groupe B");

        groupeComboBox.setItems(FXCollections.observableArrayList(groupeA, groupeB));

        // Création d'exemples de voyages en définissant uniquement les champs essentiels.
        Voyage voyageParis = new Voyage();
        voyageParis.setId_voyage(1);
        voyageParis.setDate_depart(LocalDate.of(2025, 5, 1));
        voyageParis.setDate_arrive(LocalDate.of(2025, 5, 10));
        // Vous pouvez ajouter d'autres champs, comme le prix et les places disponibles, selon vos besoins.

        Voyage voyageRome = new Voyage();
        voyageRome.setId_voyage(2);
        voyageRome.setDate_depart(LocalDate.of(2025, 6, 1));
        voyageRome.setDate_arrive(LocalDate.of(2025, 6, 8));

        voyageComboBox.setItems(FXCollections.observableArrayList(voyageParis, voyageRome));
    }*/
    @FXML
    private void initialize() {

        // Création d'exemples de voyages (fictifs) pour l'Asie :
        Voyage voyageTokyo = new Voyage();
        voyageTokyo.setId_voyage(1);
        voyageTokyo.setDate_depart(LocalDate.of(2025, 5, 1));
        voyageTokyo.setDate_arrive(LocalDate.of(2025, 5, 10));
        // Ici, on suppose que ce voyage est destiné à Tokyo (destination asiatique)

        Voyage voyageBangkok = new Voyage();
        voyageBangkok.setId_voyage(2);
        voyageBangkok.setDate_depart(LocalDate.of(2025, 6, 1));
        voyageBangkok.setDate_arrive(LocalDate.of(2025, 6, 8));
        // On suppose que ce voyage est destiné à Bangkok (destination asiatique)

        // On ne place dans la ComboBox que les voyages asiatiques
        voyageComboBox.setItems(FXCollections.observableArrayList(voyageTokyo, voyageBangkok));

        // Définir une cellule personnalisée pour afficher un résumé simple du voyage
        voyageComboBox.setCellFactory(lv -> new ListCell<Voyage>() {
            @Override
            protected void updateItem(Voyage item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("");
                } else {
                    setText("Voyage " + item.getId_voyage() + " : "
                            + item.getDate_depart() + " -> " + item.getDate_arrive());
                }
            }
        });
        // Pour le bouton de la ComboBox
        voyageComboBox.setButtonCell(new ListCell<Voyage>() {
            @Override
            protected void updateItem(Voyage item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("");
                } else {
                    setText("Voyage " + item.getId_voyage() + " : "
                            + item.getDate_depart() + " -> " + item.getDate_arrive());
                }
            }
        });

        // Création d'exemples de groupes associés aux voyages.
        // Par exemple, pour le voyage Tokyo, un groupe spécifique,
        // et pour le voyage Bangkok, un autre groupe.
        GroupeVoyageur groupeTokyo = new GroupeVoyageur();
        groupeTokyo.setId_groupe(1);
        groupeTokyo.setNom_groupe("Tokyo Explorers");

        GroupeVoyageur groupeBangkok = new GroupeVoyageur();
        groupeBangkok.setId_groupe(2);
        groupeBangkok.setNom_groupe("Bangkok Adventurers");

        // Lorsqu'un voyage est sélectionné, on met à jour la liste des groupes associés.
        voyageComboBox.setOnAction(e -> {
            Voyage selectedVoyage = voyageComboBox.getValue();
            if (selectedVoyage != null) {
                if (selectedVoyage.getId_voyage() == 1) {
                    groupeComboBox.setItems(FXCollections.observableArrayList(groupeTokyo));
                } else if (selectedVoyage.getId_voyage() == 2) {
                    groupeComboBox.setItems(FXCollections.observableArrayList(groupeBangkok));
                } else {
                    groupeComboBox.setItems(FXCollections.observableArrayList());
                }
            }
        });

        // Définir une cellule personnalisée pour la ComboBox des groupes
        groupeComboBox.setCellFactory(lv -> new ListCell<GroupeVoyageur>() {
            @Override
            protected void updateItem(GroupeVoyageur item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("");
                } else {
                    setText(item.getNom_groupe());
                }
            }
        });
        groupeComboBox.setButtonCell(new ListCell<GroupeVoyageur>() {
            @Override
            protected void updateItem(GroupeVoyageur item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("");
                } else {
                    setText(item.getNom_groupe());
                }
            }
        });
    }


    // Méthode pour gérer l'ajout de la réservation
    @FXML
    private void handleAddReservation() {
        try {
            int nbPlace = Integer.parseInt(nbPlaceField.getText());
            double montantTotal = Double.parseDouble(montantTotalField.getText());
            LocalDate dateDebut = dateDebutPicker.getValue();
            LocalDate dateFin = dateFinPicker.getValue();
            GroupeVoyageur groupe = groupeComboBox.getValue();
            Voyage voyage = voyageComboBox.getValue();

            if (dateDebut == null || dateFin == null || groupe == null || voyage == null) {
                showAlert("Veuillez remplir tous les champs !");
                return;
            }

            // Création de la réservation
            Reservation reservation = new Reservation();
            reservation.setNb_place(nbPlace);
            reservation.setMontant_total(montantTotal);
            reservation.setDateDebut(dateDebut);
            reservation.setDateFin(dateFin);
            reservation.setGroupe(groupe);
            reservation.setVoyage(voyage);

            // Ici vous pouvez appeler votre service pour sauvegarder la réservation en base
            // reservationService.save(reservation);

            showAlert("Réservation ajoutée avec succès !");
            clearForm();
        } catch (NumberFormatException e) {
            showAlert("Veuillez entrer des valeurs numériques valides pour le nombre de places et le montant total.");
        }
    }

    // Méthode pour annuler et réinitialiser le formulaire
    @FXML
    private void handleCancel() {
        clearForm();
    }

    // Méthode utilitaire pour réinitialiser les champs
    private void clearForm() {
        nbPlaceField.clear();
        montantTotalField.clear();
        dateDebutPicker.setValue(null);
        dateFinPicker.setValue(null);
        groupeComboBox.setValue(null);
        voyageComboBox.setValue(null);
    }

    // Méthode utilitaire pour afficher une alerte
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
