package org.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "avis")
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long id_Avis;
    private float note;
    private String commentaire ;
    private LocalDate date_avis;
    @ManyToMany
    @JoinColumn(name = "id_client") // Clé étrangère vers Reservation
    private List<Client> clients;
    @ManyToMany
    @JoinColumn(name = "id_voyage") // Clé étrangère vers Reservation
    private List<Voyage> voyages;
    @ManyToMany
    @JoinColumn(name = "id_hotel") // Clé étrangère vers Reservation
    private List<Hotel> hotels;
}
