package org.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name="activite")
@AllArgsConstructor
@NoArgsConstructor
public class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long id_activite;
    private String nom_activite;
    private double prix_activite;
    private String description;
    @ManyToMany
    @JoinColumn(name = "id_voyage") // Clé étrangère vers voyage
    private List<Voyage> voyages=new ArrayList<>();
    @Override
    public String toString() {
        return nom_activite;
    }
}
