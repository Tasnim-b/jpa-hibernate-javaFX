package org.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.model.Client;
import org.model.GroupeVoyageur;
import org.model.Reservation;
import org.model.Voyage;
import org.dao.GenericDAO;

import java.time.LocalDate;
import java.util.List;

public class ModifierReservationController {

    @FXML
    private ComboBox<Client> clientComboBox;
    @FXML
    private ComboBox<Voyage> voyageComboBox;
    @FXML
    private ComboBox<GroupeVoyageur> groupeComboBox;
    @FXML
    private TextField nbPlaceTextField;
    @FXML
    private TextField montantTotalTextField;
    @FXML
    private DatePicker dateDebutDatePicker;
    @FXML
    private DatePicker dateFinDatePicker;
    @FXML
    private Button modifierButton;

    private GenericDAO<Reservation> reservationDAO;
    private GenericDAO<Client> clientDAO;
    private GenericDAO<Voyage> voyageDAO;
    private GenericDAO<GroupeVoyageur> groupeDAO;

    private Reservation reservationToEdit;

    // Méthode d'initialisation
    @FXML
    private void initialize() {
        // Initialiser les DAO spécifiques
        reservationDAO = new GenericDAO<>(Reservation.class);
        clientDAO = new GenericDAO<>(Client.class);
        voyageDAO = new GenericDAO<>(Voyage.class);
        groupeDAO = new GenericDAO<>(GroupeVoyageur.class);

        // Charger les données depuis la base en utilisant getAll()
        List<Client> clients = clientDAO.getAll();
        List<Voyage> voyages = voyageDAO.getAll();

        clientComboBox.setItems(FXCollections.observableArrayList(clients));
        voyageComboBox.setItems(FXCollections.observableArrayList(voyages));

        // Mettre à jour la liste des groupes en fonction du voyage sélectionné
        voyageComboBox.setOnAction(event -> updateGroupeComboBox());
    }

    // Mettre à jour la ComboBox des groupes en fonction du voyage sélectionné
    private void updateGroupeComboBox() {
        Voyage selectedVoyage = voyageComboBox.getValue();
        if (selectedVoyage != null) {
            // Si dans votre modèle un voyage a une relation avec des groupes,
            // vous pouvez récupérer directement cette liste, par exemple :
            // List<GroupeVoyageur> groupsForVoyage = selectedVoyage.getGroupes();
            //
            // Sinon, vous pouvez récupérer tous les groupes et filtrer (ici l'exemple est fictif) :
            List<GroupeVoyageur> allGroups = groupeDAO.getAll();
            // Appliquer un filtre si nécessaire. Par exemple, si vous avez un champ dans GroupeVoyageur qui indique l'id du voyage associé
            List<GroupeVoyageur> groupsForVoyage = allGroups; // À adapter selon votre logique de filtrage
            groupeComboBox.setItems(FXCollections.observableArrayList(groupsForVoyage));
        }
    }

    // Méthode appelée lors de la modification de la réservation
    @FXML
    private void handleModifyReservation() {
        Client selectedClient = clientComboBox.getValue();
        Voyage selectedVoyage = voyageComboBox.getValue();
        GroupeVoyageur selectedGroupe = groupeComboBox.getValue();

        if (selectedClient != null && selectedVoyage != null && selectedGroupe != null) {
            // Demander confirmation avant modification
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Modifier la Réservation");
            confirmationAlert.setContentText("Êtes-vous sûr de vouloir modifier cette réservation ?");

            if (confirmationAlert.showAndWait().get() == ButtonType.OK) {
                try {
                    int nbPlace = Integer.parseInt(nbPlaceTextField.getText());
                    double montantTotal = nbPlace * selectedVoyage.getPrix_par_personne(); // Calcul du montant total
                    LocalDate dateDebut = dateDebutDatePicker.getValue();
                    LocalDate dateFin = dateFinDatePicker.getValue();

                    // Mise à jour de l'objet réservation
                    reservationToEdit.setClient(selectedClient);
                    reservationToEdit.setVoyage(selectedVoyage);
                    reservationToEdit.setGroupe(selectedGroupe);
                    reservationToEdit.setNb_place(nbPlace);
                    reservationToEdit.setMontant_total(montantTotal);
                    reservationToEdit.setDateDebut(dateDebut);
                    reservationToEdit.setDateFin(dateFin);

                    // Mettre à jour la réservation dans la base via le DAO générique
                    reservationDAO.update(reservationToEdit);

                    // Afficher une boîte de dialogue pour confirmer la modification
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Succès");
                    successAlert.setHeaderText("Modification réussie");
                    successAlert.setContentText("La réservation a été modifiée avec succès !");
                    successAlert.showAndWait();

                    // Réinitialiser les champs après modification
                    resetFields();

                } catch (NumberFormatException e) {
                    showAlert("Erreur : Le nombre de places doit être un nombre valide !");
                }
            }
        } else {
            showAlert("Veuillez remplir tous les champs.");
        }
    }
    // Méthode pour afficher une boîte de dialogue d'erreur/information
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Réinitialisation des champs après modification
    private void resetFields() {
        clientComboBox.getSelectionModel().clearSelection();
        voyageComboBox.getSelectionModel().clearSelection();
        groupeComboBox.getSelectionModel().clearSelection();
        nbPlaceTextField.clear();
        dateDebutDatePicker.setValue(null);
        dateFinDatePicker.setValue(null);
    }
    // Méthode pour initialiser le contrôleur avec la réservation à modifier
    public void initData(Reservation reservation) {
        this.reservationToEdit = reservation;

        // Préremplir les champs avec les données existantes
        clientComboBox.setValue(reservation.getClient());
        voyageComboBox.setValue(reservation.getVoyage());
        groupeComboBox.setValue(reservation.getGroupe());
        nbPlaceTextField.setText(String.valueOf(reservation.getNb_place()));
        montantTotalTextField.setText(String.valueOf(reservation.getMontant_total()));
        dateDebutDatePicker.setValue(reservation.getDateDebut());
        dateFinDatePicker.setValue(reservation.getDateFin());
    }
}
