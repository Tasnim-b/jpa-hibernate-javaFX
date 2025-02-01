package org.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CompanniesAetiennes")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CompagnniesAeriennes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long id_CompagnniesArienne ;
    private String nom_compagnnies;
    private String pays;
    private String site_web;


}
