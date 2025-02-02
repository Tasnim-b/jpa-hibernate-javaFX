package org.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.dao.GenericDAO;
import org.model.GroupeVoyageur;
import org.model.Reservation;
import org.model.Voyage;

import java.time.LocalDate;

public class ReservationController {

    @FXML
    private TextField NomReservationField;

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

    private final GenericDAO<Reservation> reservationDAO = new GenericDAO<>(Reservation.class);

    @FXML
    private void handleAddReservation() {
        try {
            String nomReservation = NomReservationField.getText();
            int nbPlace = Integer.parseInt(nbPlaceField.getText());
            double montantTotal = Double.parseDouble(montantTotalField.getText());
            LocalDate dateDebut = dateDebutPicker.getValue();
            LocalDate dateFin = dateFinPicker.getValue();
            GroupeVoyageur groupe = groupeComboBox.getValue();
            Voyage voyage = voyageComboBox.getValue();

            if (nomReservation.isEmpty() || dateDebut == null || dateFin == null || groupe == null || voyage == null) {
                showAlert("Veuillez remplir tous les champs !");
                return;
            }

            // Création de la réservation
            Reservation reservation = new Reservation();
            reservation.setNomReservation(nomReservation);
            reservation.setNb_place(nbPlace);
            reservation.setMontant_total(montantTotal);
            reservation.setDateDebut(dateDebut);
            reservation.setDateFin(dateFin);
            reservation.setGroupe(groupe);
            reservation.setVoyage(voyage);

            // Enregistrement dans la base de données via la DAO
            reservationDAO.add(reservation);

            showAlert("Réservation ajoutée avec succès !");
            clearForm();
        } catch (NumberFormatException e) {
            showAlert("Veuillez entrer des valeurs numériques valides pour le nombre de places et le montant total.");
        }
    }

    @FXML
    private void handleCancel() {
        clearForm();
    }

    private void clearForm() {
        NomReservationField.clear();
        nbPlaceField.clear();
        montantTotalField.clear();
        dateDebutPicker.setValue(null);
        dateFinPicker.setValue(null);
        groupeComboBox.setValue(null);
        voyageComboBox.setValue(null);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
