package org.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Reservation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long id_reservation;
    private String nomReservation;
    private int nb_place;
    private double montant_total;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    @OneToOne
    @JoinColumn(name = "id_groupe", nullable = false)//pour assurer un seul groupe est lié à une seule réservation
    private GroupeVoyageur groupe;
    //plusieurs voyage pour une seul réservation
    @ManyToOne
    @JoinColumn(name = "id_voyage", nullable = false)
    private Voyage voyage;
    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false) // Lien avec un client
    private Client client;  // Nouveau champ ajouté pour le client
}
