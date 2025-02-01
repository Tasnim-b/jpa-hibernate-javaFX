package org.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GroupeVoyageur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long id_groupe;
    private String nom_groupe;
    private int nb_total_membre;
    @ManyToMany
    @JoinColumn(name = "id_client", nullable = false)
    private List<GroupeVoyageur> groupes;
    @ManyToMany
    @JoinColumn(name = "id_agent", nullable = false)
    private List<Agent> agents;
}
