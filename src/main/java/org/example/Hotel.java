package org.example;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.boot.registry.selector.spi.StrategyCreator;
@Entity
@Setter
@Getter
@ToString
@Table(name = "hotel")
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long id_hotel ;
    private String nom_hotel;
    private String adresse;
    private int capacite;
    private float prix_par_nuit;
    private Classement classement;
    private String description;
    //plusieur hôtel peut etre associé à une destination spécifique
    @ManyToOne
    @JoinColumn(name = "id_destination", nullable = false)
    private Destination destionation;




}
