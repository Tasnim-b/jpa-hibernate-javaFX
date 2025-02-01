package org.model;

import jakarta.persistence.*;
import lombok.*;
import org.Enum.EtatTransport;
import org.Enum.TypeTransport;

import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "MoyendeTransport")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MoyenDeTransport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long id_moyen_transport ;
    private TypeTransport typeTransport;
    private int capacite;
    private String nom_compannies;
    private LocalDate date_disponible;
    private EtatTransport etatTransport;
    private float prix_par_personne;
    @ManyToMany
    @JoinColumn(name = "id_voyage", nullable = false)
    private List<Voyage> voyages;
    @ManyToOne
    @JoinColumn(name = "id_destination", nullable = false)
    private Destination destionation;

}
