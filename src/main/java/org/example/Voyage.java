package org.example;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long Id_voyage;
    private double prix_par_personne;
    private LocalDate Date_depart;
    private LocalDate Date_arrive;
    private int place_disponible;
    @ManyToMany
    @JoinColumn(name = "id_hotel")
    private List<Hotel> hotels;
    @ManyToMany
    @JoinColumn(name = "id_destination")
    private List<Hotel> destiantions;
}
