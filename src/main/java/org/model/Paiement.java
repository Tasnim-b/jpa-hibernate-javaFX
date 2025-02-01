package org.model;

import jakarta.persistence.*;
import lombok.*;
import org.Enum.ModePaiement;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="paiement")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long id_paiement ;
   private  double montant;
   private LocalDate date_paiement;
   private ModePaiement modePaiement;
   @ManyToMany
   @JoinColumn(name = "id_reservation") // Clé étrangère vers Reservation
   private List<Reservation> reservation;
}
